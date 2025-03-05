package net.syscon.s4.sa.recordmaintenance.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

import ClickSend.ApiClient;
import ClickSend.Api.ContactApi;
import ClickSend.Api.ContactListApi;
import ClickSend.Api.SmsApi;
import ClickSend.Model.Contact;
import ClickSend.Model.ContactList;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.SystemSettingsBean;
import net.syscon.s4.globalconfiguration.OumsysetService;
import net.syscon.s4.sa.recordmaintenance.SendMail;
import net.syscon.s4.sa.recordmaintenance.SendmailRepository;
import net.syscon.s4.sa.recordmaintenance.SendmailService;

@Service
public class SendmailServiceImpl implements SendmailService {
	@Autowired
	private Environment env;
	@Autowired
	private SendmailRepository sendmailRepository;
	@Autowired
	private OumsysetService oumsysetService;

	private static Logger logger = LogManager.getLogger(SendmailServiceImpl.class.getName());
	private String SOURCE = "java";

	private String getSendGridApi() {
		
		 return oumsysetService.getConfValue("eMail", "SENDGRID", "SG_API_KEY");
	}

	@Override
	public String sendMail(SendMail detModel) {
		String response = "";
		String mailType = sendmailRepository.getMailType();
		logger.info("email_mailType: ", mailType);
		SystemSettingsBean  bean = new SystemSettingsBean();
		bean.setSettingProviderCode("EMAIL_SMS_TEST_FOOTER_CONFIG");
		bean.setSettingType("EMAIL_SMS_TEST_FOOTER_CONFIG");
		bean = oumsysetService.getSysSettingData(bean);
		String emailId="";
		String footer="";
		if(bean.getSettingValue() != null ) {
			 emailId = generateEmailSMS("TEST_EM_OV", bean.getSettingValue());
			 footer = generateEmailSMS("EM_FOOT", bean.getSettingValue());
		}
		if(footer!=null && !"".equals(footer) && !"N".equals(footer)) {	
				detModel.setBody((detModel.getBody().concat("<br><br> "+ footer)));
		}
		if(emailId!=null && !"".equals(emailId) && !"N".equals(emailId)) {	
			String intend="Intended Recipient(s):".concat(" ").concat(detModel.getToId()).concat("<br>");
			if(intend!=null) {			
				detModel.setBody(intend.concat(detModel.getBody().concat("<br><br> ")));
			}
			detModel.setToId(emailId);
		}
		if(!mailType.equals("") && mailType.equals("SENDGRID")) {
			response = sendgridMail(detModel);
		} else if(!mailType.equals("") && mailType.equals("CLICKSEND")) {
			response = clickSendMail(detModel);
		} else if(!mailType.equals("") && mailType.equals("SMTP")) {
			response = smtpMail(detModel);
		} else {
			response = sendgridMail(detModel);
		}
		return response;
	}

	private String sendgridMail(SendMail detModel) {
		Mail mail = new Mail();
		String fromEmailId="";
		String result = "";
		Personalization personalization = new Personalization();
		try {
			
			String[] toidList = detModel.getToId().split(",");
			logger.info("sendgrid_toidList : {}", toidList);

			for (int i = 0, size = toidList.length; i < size; i++) {
				personalization.addTo(new Email(toidList[i]));
			}
			logger.info("sendgrid_toidList_actual : {}", personalization.getTos());
			logger.info("sendgrid_toidList_size : {}", personalization.getTos().size());
			
			List<Map<String,Object>> returnList=oumsysetService.getSysData("eMail","SENDGRID");
			 if(returnList!=null && !returnList.isEmpty()) {
				 for(Map<String,Object> object:returnList) {
		        	 if(object.containsKey("KEY_CODE") && object.get("KEY_CODE")!=null && object.get("KEY_CODE").equals("MAIL_FROM") &&  object.containsKey("VALUE") && object.get("VALUE")!=null) {
		        		 fromEmailId = object.get("VALUE").toString();
					}
				 }
			 }
		        	  
			Email from = new Email(fromEmailId);
			String subject = detModel.getSubject();
			
			Content content = new Content("text/html", "<html><body>" + detModel.getBody() + "</body></html>");
			mail.addPersonalization(personalization);
			mail.setFrom(from);
			mail.setSubject(subject);
			mail.addContent(content);

			SendGrid sg = new SendGrid(getSendGridApi());
			Request request = new Request();

			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			logger.info("sendgrid_StatusCode : {}", response.getStatusCode());
			logger.info("sendgrid_body : {}", response.getBody());
			logger.info("sendgrid_headers : {}", response.getHeaders());
			result = ApplicationConstants.SUCCESS;
		} catch (Exception ex) {
			result = ApplicationConstants.FAIL;
			logger.error("sendgrid_error_msg : {}", ex.getMessage());
			logger.error("sendgrid_error_cause : {}", ex.getCause());
			ex.printStackTrace();
		}
		return result;
	}

	private String clickSendMail(SendMail detModel) {
		String response = "";
		try {
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			 List<Map<String,Object>> returnList=oumsysetService.getSysData("eMail","CLICKSEND");
			 if(returnList!=null && !returnList.isEmpty()) {
				 for(Map<String,Object> object:returnList) {
		        	  if(object.containsKey("KEY_CODE") && object.get("KEY_CODE")!=null && object.get("KEY_CODE").equals("MAIL_HOST") &&  object.containsKey("VALUE") && object.get("VALUE")!=null) {
		        		      mailSender.setHost(object.get("VALUE").toString());
						}else if(object.containsKey("KEY_CODE") && object.get("KEY_CODE")!=null && object.get("KEY_CODE").equals("MAIL_PORT") &&  object.containsKey("VALUE") && object.get("VALUE")!=null) {
			        		  mailSender.setPort(Integer.valueOf(object.get("VALUE").toString()));
						}else if(object.containsKey("KEY_CODE") && object.get("KEY_CODE")!=null && object.get("KEY_CODE").equals("MAIL_USER") &&  object.containsKey("VALUE") && object.get("VALUE")!=null) {
				        		mailSender.setUsername(object.get("VALUE").toString());
						}else if(object.containsKey("KEY_CODE") && object.get("KEY_CODE")!=null && object.get("KEY_CODE").equals("MAIL_PWD") &&  object.containsKey("VALUE") && object.get("VALUE")!=null) {
							Base64.Decoder decoder = Base64.getDecoder();
							String decryptpwd = new String(decoder.decode(object.get("VALUE").toString()));
							mailSender.setPassword(decryptpwd);
						}
		          }
			 }
			

			if (null == mailSender.getHost()) {
				mailSender.setHost(this.env.getProperty("app.mail.host"));
			}
			if (-1 == mailSender.getPort()) {
				mailSender.setPort(Integer.valueOf(this.env.getProperty("app.mail.port")));
			}
			if (null == mailSender.getUsername()) {
				mailSender.setUsername(this.env.getProperty("app.mail.user"));
			}
			if (null == mailSender.getPassword()) {
				mailSender.setPassword(this.env.getProperty("app.mail.password"));
			}

			Properties props = mailSender.getJavaMailProperties();
			props.put("mail.transport.protocol", "smtps");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.debug", "true");

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			
			 if(returnList!=null && !returnList.isEmpty()) {
				 for(Map<String,Object> object:returnList) {
		        	  if(object.containsKey("KEY_CODE") && object.get("KEY_CODE")!=null && object.get("KEY_CODE").equals("MAIL_FROM") &&  object.containsKey("VALUE") && object.get("VALUE")!=null) {
		        		  helper.setFrom(object.get("VALUE").toString());
						}
		          }
			 }
			
			if (null == message.getFrom()) {
				helper.setFrom(this.env.getProperty("app.mail.from"));
			}
			helper.setSubject(detModel.getSubject());
			String[] toidList = detModel.getToId().split(",");
			logger.info("clicksend_toidList : {}", toidList);
			helper.setTo(toidList);
			message.setContent("<html><body>" + detModel.getBody() + "</body></html>", "text/html");
			mailSender.send(message);
			response = ApplicationConstants.SUCCESS;
		} catch (Exception e) {
			response = ApplicationConstants.FAIL;
			logger.error("clicksend_error_msg : {}", e.getMessage());
			logger.error("clicksend_error_cause : {}", e.getCause());
			e.printStackTrace();
		}
		return response;
	}

	private String getUsername() {
		return oumsysetService.getConfValue("eMail", "CLICKSEND", "MAIL_USER");
	}

	private String getPassword() {
		String encryptPwd = oumsysetService.getConfValue("eMail", "CLICKSEND", "MAIL_PWD");
		Base64.Decoder decoder = Base64.getDecoder();
		String decryptpwd = new String(decoder.decode(encryptPwd));
		return decryptpwd;
	}

	@Override
	public String sendSMS(SendMail detModel) {
		String response = "";
		try {
			SystemSettingsBean  bean = new SystemSettingsBean();
			bean.setSettingProviderCode("EMAIL_SMS_TEST_FOOTER_CONFIG");
			bean.setSettingType("EMAIL_SMS_TEST_FOOTER_CONFIG");
			bean = oumsysetService.getSysSettingData(bean);
			if(bean.getSettingValue() != null) {
				String smsId = generateEmailSMS("TEST_SMS_OV", bean.getSettingValue());
				String smsBody = generateEmailSMS("SMS_FOOT", bean.getSettingValue());
				if(smsBody != null ) {
					detModel.setBody(detModel.getBody().concat("<br>" + smsBody)); 
				}
				if(smsId != null ) {
					detModel.setToId(detModel.getToId().concat(","+detModel)); 
				}
			}
			logger.info("SMS toId : {}", bean.getSettingValue());
			logger.info("SMS body : {}", detModel.getBody());
			int listId = createList();
			String[] toidList = detModel.getToId().split(",");
			ArrayList<String> toList = new ArrayList<String>();
			for (int i = 0; i < toidList.length; i++) {
				toList.add(toidList[i]);
			}
			createNewContact(toList, listId);

			ApiClient defaultClient = new ApiClient();
			defaultClient.setUsername(getUsername());
			defaultClient.setPassword(getPassword());
			SmsApi apiInstance = new SmsApi(defaultClient);

			SmsMessage smsMessage = new SmsMessage();
			smsMessage.setBody(detModel.getBody());
			smsMessage.setSource(SOURCE);
			smsMessage.setListId(listId);

			List<SmsMessage> smsMessageList = Arrays.asList(smsMessage);
			SmsMessageCollection smsMessages = new SmsMessageCollection();
			smsMessages.setMessages(smsMessageList);

			String result = apiInstance.smsSendPost(smsMessages);
			logger.info("sendSMS result : {}", result);
			if (result != null) {
				deleteList(listId);
			}
			response = ApplicationConstants.SUCCESS;
		} catch (Exception e) {
			response = ApplicationConstants.FAIL;
			logger.error("SMS_error_msg : {}", e.getMessage());
			logger.error("SMS_error_cause : {}", e.getCause());
			e.printStackTrace();
		}

		return response;
	}

	public Integer createList() {
		int listId = 0;
		ApiClient defaultClient = new ApiClient();
		defaultClient.setUsername(getUsername());
		defaultClient.setPassword(getPassword());
		ContactListApi apiInstance = new ContactListApi(defaultClient);
		ContactList contactList = new ContactList();
		contactList.listName("new_list");
		try {
			String result = apiInstance.listsPost(contactList);
			System.out.println(result);
			JSONObject jObject = new JSONObject(result);
			JSONObject data = jObject.getJSONObject("data");
			listId = data.getInt("list_id");
			logger.info("sendSMS createList result_data : {}", data);
			logger.info("sendSMS createList listId : {}", listId);
		} catch (Exception e) {
			logger.error("sendSMS CreateList_error_msg : {}", e.getMessage());
			logger.error("sendSMS CreateList_error_cause : {}", e.getCause());
			e.printStackTrace();
		}
		return listId;
	}

	public void createNewContact(ArrayList<String> toList, int listId) {
		ApiClient defaultClient = new ApiClient();
		defaultClient.setUsername(getUsername());
		defaultClient.setPassword(getPassword());
		ContactApi apiInstance = new ContactApi(defaultClient);
		for (String to : toList) {
			Contact contact = new Contact();
			contact.phoneNumber(to);
			contact.custom1(to);
			try {
				String result = apiInstance.listsContactsByListIdPost(contact, listId);
				logger.info("sendSMS createNewContact result : {}", result);
			} catch (Exception e) {
				logger.error("sendSMS createNewContact_error_msg : {}", e.getMessage());
				logger.error("sendSMS createNewContact_error_cause : {}", e.getCause());
				e.printStackTrace();
			}
		}
	}

	public void deleteList(Integer listId) {
		ApiClient defaultClient = new ApiClient();
		defaultClient.setUsername(getUsername());
		defaultClient.setPassword(getPassword());
		ContactListApi apiInstance = new ContactListApi(defaultClient);
		try {
			String result = apiInstance.listsByListIdDelete(listId);
			logger.info("sendSMS deleteList result : {}", result);
		} catch (Exception e) {
			logger.error("sendSMS deleteList_error_msg : {}", e.getMessage());
			logger.error("sendSMS deleteList_error_cause : {}", e.getCause());
			e.printStackTrace();
		}
	}
	
	private String smtpMail(SendMail detModel) {
		String response = "";
		try {
			Session session = authentication();
			List <String> userMails=new ArrayList<>();
			String[] toidList = detModel.getToId().split(",");
			for (int i = 0; i < toidList.length; i++) {
				userMails.add(toidList[i]);
			}
			if(session !=null ) {
				MimeMessage message = new MimeMessage(session);
				message.setSubject(detModel.getSubject());
				message.setContent("<html><body>" + detModel.getBody() + "</body></html>", "text/html");
				response = sendingMail(message, userMails);
			}
		} catch (Exception e) {
			response = ApplicationConstants.FAIL;
			logger.error(this.getClass().getName(), " smtpMail and  Exception ", e);
		}
		return response;
	}
	
	private Session authentication() {
		String user = "";   
		String password = "";
		String host = "";
		int port = 0;
		List<Map<String,Object>> returnList=oumsysetService.getSysData("eMail","SMTP");
		 if(returnList!=null && !returnList.isEmpty()) {
			 for(Map<String,Object> object:returnList) {
	        	  if(object.containsKey("KEY_CODE") && object.get("KEY_CODE")!=null && object.get("KEY_CODE").equals("SMTP_HOST") &&  object.containsKey("VALUE") && object.get("VALUE")!=null) {
	        		     host = object.get("VALUE").toString();
					}else if(object.containsKey("KEY_CODE") && object.get("KEY_CODE")!=null && object.get("KEY_CODE").equals("SMTP_PORT") &&  object.containsKey("VALUE") && object.get("VALUE")!=null) {
		        		 port = Integer.parseInt(object.get("VALUE").toString());
					}else if(object.containsKey("KEY_CODE") && object.get("KEY_CODE")!=null && object.get("KEY_CODE").equals("SMTP_USER") &&  object.containsKey("VALUE") && object.get("VALUE")!=null) {
			        	user = object.get("VALUE").toString();
					}else if(object.containsKey("KEY_CODE") && object.get("KEY_CODE")!=null && object.get("KEY_CODE").equals("SMTP_PWD") &&  object.containsKey("VALUE") && object.get("VALUE")!=null) {
						Base64.Decoder decoder = Base64.getDecoder();
						password = new String(decoder.decode(object.get("VALUE").toString()));
					}
	          }
		 }
		final String usr = user;
		final String pwd = password;
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");	
	    props.put("mail.smtp.ssl.trust", host);
		props.put("mail.smtp.user", user);
		props.put("mail.smtp.password", password);
		Session session=null;
		 session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usr, pwd);
			}
		});
		return session;
	}
	
	public String sendingMail(MimeMessage message,List <String> userMails) {
		String user = "";
		String response = "";
		List<Map<String,Object>> returnList=oumsysetService.getSysData("eMail","SMTP");
		 if(returnList!=null && !returnList.isEmpty()) {
			 for(Map<String,Object> object:returnList) {
	        	  if(object.containsKey("KEY_CODE") && object.get("KEY_CODE")!=null && object.get("KEY_CODE").equals("SMTP_USER") &&  object.containsKey("VALUE") && object.get("VALUE")!=null) {
			        	user = object.get("VALUE").toString();
	        	  }
	          }
		 }
		String to = String.join(",", userMails);
		try {
			message.setFrom(new InternetAddress(user));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			Transport.send(message);
			response = ApplicationConstants.SUCCESS;
		} catch (MessagingException e) {
			response = ApplicationConstants.FAIL;
			logger.error(this.getClass().getName(), "SMTP sendingMail and  Exception ", e);
		}
		return response;
	}
	
	private String generateEmailSMS(String keyCode, String systemSettings) {
		try {
			String returnVal ="";
			List<Map<String, Object>> systemSettigs =  new ObjectMapper().readValue(systemSettings, new TypeReference<ArrayList<Map<String,Object>>>(){});
			for(Map<String, Object> obj : systemSettigs ) {
				if(obj.get("KEY_CODE").toString().equals(keyCode) && !obj.get("VALUE").toString().equals("N")) {
					returnVal = obj.get("VALUE").toString();
				}
			}
			return returnVal;
		} catch (Exception e) {
			logger.error(this.getClass().getName(), "generateEmailSMS and  Exception ", e);
		}
		return null;
	}

}

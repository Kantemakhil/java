package net.syscon.s4.sa.recordmaintenance;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.iwp.base.ManageDocController;
import net.syscon.s4.iwp.beans.DocDetails;
import net.syscon.s4.iwp.beans.ManageDocumentRequest;

@EliteController
public class SendmailController {
	@Autowired
	private SendmailService sendmailService;
	
	@Autowired
	private ManageDocController manageDocController;
	
	private static Logger logger = LogManager.getLogger(SendmailController.class.getName());
   
	@RequestMapping(value="/prosmain/postMail",method=RequestMethod.POST)
	public Map<String, Object> sendMail(@RequestBody final String mailDetails, HttpServletRequest httpServletRequest) {
		Map<String, Object> result = new HashMap<String, Object>();
		SendMail detModel = new SendMail();
		ObjectMapper mapper = new ObjectMapper();
		try {
			detModel = mapper.readValue(mailDetails, SendMail.class);
			if(detModel.getEmailTemplate() != "") {
				ManageDocumentRequest manageDocumentRequest = new ManageDocumentRequest();
				DocDetails docDetails = new DocDetails();
				if(detModel.getOffenderBookId() != null && isNumeric(detModel.getOffenderBookId())) {
					docDetails.setOffenderBookId(Long.valueOf(detModel.getOffenderBookId()));
					manageDocumentRequest.setDocDetails(docDetails);
				}
				manageDocumentRequest.setTemplateId(Long.valueOf(detModel.getEmailTemplate()));
				byte[] docbytes = (byte[]) manageDocController.downloadDocument(manageDocumentRequest, httpServletRequest, "Email").getBody();
				String docText = new String(docbytes);
				detModel.setBody(docText);
			}
			String response = sendmailService.sendMail(detModel);
			result.put("message", response);
		} catch(Exception e){
			logger.error("Exception :",e);
			result.put("message", ApplicationConstants.FAIL);;
		}
		return result;
	} 
	
	@RequestMapping(value="/prosmain/sendMail",method=RequestMethod.POST)
	public Map<String, Object> sendMailService(@RequestBody final String mailDetails, HttpServletRequest httpServletRequest) {
		Map<String, Object> result = new HashMap<String, Object>();
		SendMail detModel = new SendMail();
		ObjectMapper mapper = new ObjectMapper();
		JSONObject json=null;
		try {
			detModel = mapper.readValue(mailDetails, SendMail.class);
			String input=detModel.getTemplateInput();
			if(input!=null && !input.equals("")) {
				 json=new JSONObject(input);	
				logger.info("template input",json);
			}
			if(detModel.getEmailTemplate() != "") {
				ManageDocumentRequest manageDocumentRequest = new ManageDocumentRequest();
				DocDetails docDetails = new DocDetails();
				
				if(json!=null && json.has("offenderBookId")&& json.get("offenderBookId")!=null &&isNumeric(json.get("offenderBookId").toString())) {
					docDetails.setOffenderBookId(Long.valueOf(json.get("offenderBookId").toString()));
				}
				if(json!=null && json.has("eventId")&& json.get("eventId")!=null &&isNumeric(json.get("eventId").toString())) {
					docDetails.setEventId(Long.valueOf(json.get("eventId").toString()));
				}
				manageDocumentRequest.setDocDetails(docDetails);
				manageDocumentRequest.setTemplateId(Long.valueOf(detModel.getEmailTemplate()));
				byte[] docbytes = (byte[]) manageDocController.downloadDocument(manageDocumentRequest, httpServletRequest, "Email").getBody();
				String docText = new String(docbytes);
				detModel.setBody(docText);
			}
			String response = sendmailService.sendMail(detModel);
			result.put("message", response);
		} catch(Exception e){
			logger.error("Exception in sendMailService :",e.getMessage());
			result.put("message", ApplicationConstants.FAIL);;
		}
		return result;
	} 
	
	private boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/prosmain/sendEmail", method = RequestMethod.GET)
	public String getOffendersDetails() {
		try {
			sendEMail();
		} catch (final Exception e) {
			logger.error("getOffendersDetails :", e);
			return "Error";
		}
		return ApplicationConstants.SUCCESS;
	}
	
	
	private static void sendEMail() {
		System.out.println("email started");
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.clicksend.com");
        mailSender.setPort(465);
          
        mailSender.setUsername("Corrections.Dev@astria.justice.tas.gov.au");
        mailSender.setPassword("L]6g@i6&t#");
          
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Corrections.Dev@astria.justice.tas.gov.au");
        message.setTo("opandey@harriscomputer.com");
        message.setSubject("Test Email");
        message.setText("Test Email");
        mailSender.send(message);
        System.out.println("email end");
		
	}
	
	@RequestMapping(value="/prosmain/postSms",method=RequestMethod.POST)
	public Map<String, Object> sendSMS(@RequestBody final String mailDetails, HttpServletRequest httpServletRequest) {
		Map<String, Object> result = new HashMap<String, Object>();
		SendMail detModel = new SendMail();
		ObjectMapper mapper = new ObjectMapper();
		try {
			detModel = mapper.readValue(mailDetails, SendMail.class);
			if(detModel.getEmailTemplate() != "") {
				ManageDocumentRequest manageDocumentRequest = new ManageDocumentRequest();
				DocDetails docDetails = new DocDetails();
				if(detModel.getOffenderBookId() != null && isNumeric(detModel.getOffenderBookId())) {
					docDetails.setOffenderBookId(Long.valueOf(detModel.getOffenderBookId()));
					manageDocumentRequest.setDocDetails(docDetails);
				}
				manageDocumentRequest.setTemplateId(Long.valueOf(detModel.getEmailTemplate()));
				byte[] docbytes = (byte[]) manageDocController.downloadDocument(manageDocumentRequest, httpServletRequest, "Email").getBody();
				String docText = new String(docbytes);
				detModel.setBody(docText);
			}
			String response = sendmailService.sendSMS(detModel);
			result.put("message", response);
		} catch(Exception e){
			logger.error("Exception :",e);
			result.put("message", ApplicationConstants.FAIL);;
		}
		return result;
	} 
	
	@RequestMapping(value="/prosmain/sendSms",method=RequestMethod.POST)
	public Map<String, Object> sendSMSService(@RequestBody final String mailDetails, HttpServletRequest httpServletRequest) {
		Map<String, Object> result = new HashMap<String, Object>();
		SendMail detModel = new SendMail();
		ObjectMapper mapper = new ObjectMapper();
		JSONObject json=null;
		try {
			detModel = mapper.readValue(mailDetails, SendMail.class);
			String input=detModel.getTemplateInput();
			if(input!=null && !input.equals("")) {
				 json=new JSONObject(input);	
				logger.info("template input",json);
			}
			if(detModel.getEmailTemplate() != "") {
				ManageDocumentRequest manageDocumentRequest = new ManageDocumentRequest();
				DocDetails docDetails = new DocDetails();
				if(json!=null && json.has("offenderBookId")&& json.get("offenderBookId")!=null &&isNumeric(json.get("offenderBookId").toString())) {
					docDetails.setOffenderBookId(Long.valueOf(json.get("offenderBookId").toString()));
				}
				if(json!=null && json.has("eventId")&& json.get("eventId")!=null &&isNumeric(json.get("eventId").toString())) {
					docDetails.setEventId(Long.valueOf(json.get("eventId").toString()));
				}
				manageDocumentRequest.setDocDetails(docDetails);
				manageDocumentRequest.setTemplateId(Long.valueOf(detModel.getEmailTemplate()));
				byte[] docbytes = (byte[]) manageDocController.downloadDocument(manageDocumentRequest, httpServletRequest, "Email").getBody();
				String docText = new String(docbytes);
				detModel.setBody(docText);
			}
			String response = sendmailService.sendSMS(detModel);
			result.put("message", response);
		} catch(Exception e){
			logger.error("Exception :",e);
			result.put("message", ApplicationConstants.FAIL);;
		}
		return result;
	} 
	
	@RequestMapping(value="/prosmain/postJhubSms",method=RequestMethod.POST)
	public Map<String, Object> sendJhubSMS(@RequestBody final String mailDetails, HttpServletRequest httpServletRequest) {
		Map<String, Object> result = new HashMap<String, Object>();
		SendMail detModel = new SendMail();
		ObjectMapper mapper = new ObjectMapper();
		String docText = "";
		try {
			detModel = mapper.readValue(mailDetails, SendMail.class);
			if(detModel.getEmailTemplate() != "") {
				ManageDocumentRequest manageDocumentRequest = new ManageDocumentRequest();
				DocDetails docDetails = new DocDetails();
				if(detModel.getOffenderBookId() != null && isNumeric(detModel.getOffenderBookId())) {
					docDetails.setOffenderBookId(Long.valueOf(detModel.getOffenderBookId()));
					manageDocumentRequest.setDocDetails(docDetails);
				}
				manageDocumentRequest.setTemplateId(Long.valueOf(detModel.getEmailTemplate()));
				byte[] docbytes = (byte[]) manageDocController.downloadDocument(manageDocumentRequest, httpServletRequest, "Email").getBody();
				docText = new String(docbytes);
				result.put("message", docText);
			} else {
				result.put("message", detModel.getBody());
			}
			
		} catch(Exception e){
			logger.error("Exception :",e);
			result.put("message", docText);;
		}
		return result;
	} 
	
	@RequestMapping(value="/prosmain/sendJhubSms",method=RequestMethod.POST)
	public Map<String, Object> sendJubSMSService(@RequestBody final String mailDetails, HttpServletRequest httpServletRequest) {
		Map<String, Object> result = new HashMap<String, Object>();
		SendMail detModel = new SendMail();
		ObjectMapper mapper = new ObjectMapper();
		JSONObject json=null;
		String docText = "";
		try {
			detModel = mapper.readValue(mailDetails, SendMail.class);
			String input=detModel.getTemplateInput();
			if(input!=null && !input.equals("")) {
				 json=new JSONObject(input);	
				logger.info("template input",json);
			}
			if(detModel.getEmailTemplate() != "") {
				ManageDocumentRequest manageDocumentRequest = new ManageDocumentRequest();
				DocDetails docDetails = new DocDetails();
				if(json!=null && json.has("offenderBookId")&& json.get("offenderBookId")!=null &&isNumeric(json.get("offenderBookId").toString())) {
					docDetails.setOffenderBookId(Long.valueOf(json.get("offenderBookId").toString()));
				}
				if(json!=null && json.has("eventId")&& json.get("eventId")!=null &&isNumeric(json.get("eventId").toString())) {
					docDetails.setEventId(Long.valueOf(json.get("eventId").toString()));
				}
				manageDocumentRequest.setDocDetails(docDetails);
				manageDocumentRequest.setTemplateId(Long.valueOf(detModel.getEmailTemplate()));
				byte[] docbytes = (byte[]) manageDocController.downloadDocument(manageDocumentRequest, httpServletRequest, "Email").getBody();
				docText = new String(docbytes);
				result.put("message", docText);
			} else {
				result.put("message", detModel.getBody());
			}
		} catch(Exception e){
			logger.error("Exception :",e);
			result.put("message", docText);;
		}
		return result;
	}
	
}

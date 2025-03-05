package net.syscon.s4.externalmessage.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import net.syscon.s4.common.beans.AdditionalIdentifier;
import net.syscon.s4.common.beans.Alias;
import net.syscon.s4.common.beans.AstriaMessageHeader;
import net.syscon.s4.common.beans.ExternalMessagePayload;
import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.beans.PrisonerAdmission;
import net.syscon.s4.common.beans.PrisonerDetails;
import net.syscon.s4.common.beans.PrisonerEventDetails;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.externalmessage.ExternalMessageRepository;
import net.syscon.s4.globalconfiguration.OumsysetService;
import net.syscon.s4.globaloffenderrecords.OsiosearRepository;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.demographicsbiometrics.OcdaliasRepository;
import net.syscon.s4.inst.demographicsbiometrics.OidadmisRepository;
import net.syscon.s4.inst.movements.maintenance.OimmholoRepository;

@Service
public class ExternalMessageService {

	private static Logger logger = LogManager.getLogger(ExternalMessageService.class.getName());
	@Autowired
	private OsiosearRepository osiosearDao;
	@Autowired
	private OcdaliasRepository aliasDao;
	@Autowired
	private Environment env;

	@Autowired
	private OidadmisRepository oidadmisRepository;
	@Autowired
	private OimmholoRepository oimmholoRepository;
	
	@Autowired
	private ExternalMessageRepository externalMessageRepository;
	
	@Autowired
	private OumsysetService oumsysetService;

	public void sendMessage(BigDecimal offenderBookId) throws InterruptedException {
		logger.info("sendMessage method started");
		ObjectMapper objectMapper = new ObjectMapper();
		String message = "";
		String queueName=null;
		
		try {
			
			String connectionString=this.env.getProperty("app.azure.connection.string");
			 queueName=this.env.getProperty("app.azure.queue.name");
			
			 
			// create a Service Bus Sender client for the queue
			ServiceBusSenderClient senderClient = new ServiceBusClientBuilder().connectionString(connectionString).sender()
					.queueName(queueName).buildClient();

			// send one message to the queue
			logger.info("ExternalMessageService : senderClient intiated");
			ExternalMessagePayload payload = getOffenderDetails(offenderBookId);
			logger.info("ExternalMessageService : payload Object created ");
			message = objectMapper.writeValueAsString(payload);
			logger.info("ExternalMessageService :sending message:"+message);
			ServiceBusMessage serviceMessage = new ServiceBusMessage(message);
			serviceMessage.setContentType("application/json");
			senderClient.sendMessage(serviceMessage);
		} catch (Exception e) {
			logger.error("Exception in sendMessage" + e.getMessage());
			e.printStackTrace();
		}

		logger.info("Sent a single message to the queue: " + queueName);
		
		
	}
	public Map<String, Object> sendMessage(String data)  {
		logger.info("ExternalMessageService:sendMessage method started :: data :{}",data);
		Map<String, Object> response = new HashMap<>();
		String queueName=null;
		String connectionString=null;
		JsonObject jsonOb = null;
		try {
			if(data!=null) {
			data = data.replaceAll("\"\\[", "[").replaceAll("]\"","]").replaceAll("\"\\{", "{").replaceAll("}\"","}");
			 connectionString=this.env.getProperty("app.azure.connection.string");
			 queueName=this.env.getProperty("app.azure.queue.name");
			 Gson gson = new Gson();
			 jsonOb = gson.fromJson(data, JsonObject.class);
			 List<Map<String,Object>> returnList=oumsysetService.getSysData("SBINTEGRATION","ASB");
			 logger.info(jsonOb.get("queueName").toString());
			 if(returnList!=null && !returnList.isEmpty() && jsonOb!=null && jsonOb.has("queueName") && jsonOb.get("queueName")!=null) {
				 for(Map<String,Object> object:returnList) {
		        	  if(object.containsKey("KEY_CODE") && object.get("KEY_CODE")!=null  && object.get("KEY_CODE").equals(jsonOb.get("queueName").getAsString())) {
		        		  connectionString=object.get("CONNECTION_URL").toString();
		        		  queueName=object.get("QUEUE_NAME").toString();
						}
		          }
			 }
			 
			// create a Service Bus Sender client for the queue
			 if(connectionString!=null && queueName!=null) {
				 ServiceBusSenderClient senderClient = new ServiceBusClientBuilder().connectionString(connectionString).sender()
							.queueName(queueName).buildClient();
			
			// send one message to the queue
			logger.info("ExternalMessageService :senderClient intiated");
			logger.info("ExternalMessageService :payload Object created ");
			logger.info(jsonOb.get("serviceBusInput").toString());
			if(jsonOb.get("serviceBusInput")!=null) {
				ServiceBusMessage serviceMessage = new ServiceBusMessage(jsonOb.get("serviceBusInput").toString());
				serviceMessage.setContentType("application/json");
				
				senderClient.sendMessage(serviceMessage);
				logger.info("ExternalMessageService :message Sent");
			}
			
			 }
			 }
			logger.info("ExternalMessageService:Sent a single message to the queue: " + queueName);
			response.put("Message", "Message Sent Successfully");
		} catch (Exception e) {
			logger.error("ExternalMessageService : Exception in sendMessage" + e.getMessage());
			e.printStackTrace();
			response.put("Message", "Faild to send message");
			// log all errors to DB
			externalMessageRepository.saveServiceBusQueue(jsonOb.get("serviceBusInput").toString(), queueName, connectionString,e.getMessage());
		}
		return response;
	}

	private ExternalMessagePayload getOffenderDetails(BigDecimal offenderBookId) {
		ExternalMessagePayload payload = new ExternalMessagePayload();
		AstriaMessageHeader astriaMessageHeader = new AstriaMessageHeader();
		astriaMessageHeader.setCorrelationId(offenderBookId.toString());
		payload.setAstriaMessageHeader(astriaMessageHeader);
		payload.setPrisonerDetails(getPrisionerDetails(offenderBookId));
		payload.setPrisonerEventDetails(getPrisonerEventDetails(offenderBookId));
		logger.info(payload);
		return payload;

	}

	private PrisonerDetails getPrisionerDetails(BigDecimal offenderBookId) {
		VHeaderBlock offender = new VHeaderBlock();
		ExternalMessagePayload payload = new ExternalMessagePayload();
		PrisonerDetails prisonerData = new PrisonerDetails();
		offender.setOffenderBookId(offenderBookId);
		List<VHeaderBlock> offenderDetailsList = osiosearDao.offbkgGlobalQuery(offender);
		if (offenderDetailsList != null && !offenderDetailsList.isEmpty()) {
			offender = offenderDetailsList.get(0);
			if (offender != null) {
				prisonerData.setGivenName(offender.getLastName());
				prisonerData.setFamilyName(offender.getFirstName());
				prisonerData.setDateOfBirth(parseBirthDate(offender.getBirthDate()));
				prisonerData.setEliteOffenderId(offender.getRootOffenderId());
				prisonerData.setAliases(getOffenderAlias(offender.getRootOffenderId(), prisonerData));
				prisonerData.setAdditionalIdentifiers(null);		
			}

		}
		return prisonerData;

	}

	
	private PrisonerEventDetails getPrisonerEventDetails(BigDecimal offenderBookId) {
		PrisonerEventDetails eventData=new PrisonerEventDetails();
		PrisonerAdmission admission=new PrisonerAdmission();
	  
		BedAssignmentHistories bedDetails=oidadmisRepository.getOffenderBedDetails(offenderBookId);
		if(bedDetails!=null) {
			eventData.setEventType("Prisoner_Admission");
			admission.setAdmissionDateAndTime(parseDate(bedDetails.getAssignmentTime()));
			LivingUnits livingunitData=getLivingUnitDetails(bedDetails.getLivingUnitId());
			if(livingunitData!=null) {
				admission.setHousingFacilityCode(livingunitData.getAgyLocId());
				admission.setHousingLocationCode(livingunitData.getDescription());
				admission.setHousingLevelOneCode(livingunitData.getLevel1Code());
				admission.setHousingLevelTwoCode(livingunitData.getLevel2Code());
				admission.setHousingLevelThreeCode(livingunitData.getLevel3Code());
				admission.setHousingLevelFourCode(livingunitData.getLevel4Code());
			}
			eventData.setPrisonerAdmission(admission);
		}
		logger.info(eventData);
		
		
		
		
		return eventData;
	}
	
	
	private LivingUnits getLivingUnitDetails(Integer livingUnitId) {
		
		return oimmholoRepository.getLivingUnitDetails(livingUnitId);
		
		
	}
	private List<Alias> getOffenderAlias(BigDecimal rootOffenderId, PrisonerDetails prisonerData) {
		List<Offenders> returnList = null;
		List<Alias> alias = new ArrayList<>();
		List<AdditionalIdentifier> identifiers = new ArrayList(); 
		Offenders offender = new Offenders();
		offender.setRootOffenderId(rootOffenderId);
		returnList = aliasDao.offNameSearchOffenders(offender);
		if (returnList != null && !returnList.isEmpty()) {
			for (Offenders data : returnList) {
				Alias aliasObj=new Alias();
				aliasObj.setGivenName(data.getLastName());
				aliasObj.setFamilyName(data.getFirstName());
				aliasObj.setDateOfBirth(parseBirthDate(data.getBirthDate()));
				//aliasObj.setAdditionalIdentifiers( getIdentifiers(data.getOffenderId()));
				identifiers.addAll(getIdentifiers(data.getOffenderId()));
				alias.add(aliasObj);
			}
			prisonerData.setAdditionalIdentifiers(identifiers);
		}

		return alias;

	}

	private List<AdditionalIdentifier> getIdentifiers(Long offenderId) {
		List<OffenderIdentifier> identifierList = null;
		List<AdditionalIdentifier> returnList = new ArrayList<>();
		Offenders offender = new Offenders();
		offender.setOffenderId(offenderId);
		identifierList = aliasDao.offIdSearchOffenderIdentifiers(offender);
		for(OffenderIdentifier data:identifierList) {
			AdditionalIdentifier identifierObj=new AdditionalIdentifier();
			identifierObj.setIdentifierTypeCode(data.getIdentifierType());
			identifierObj.setIdentifier(data.getIdentifier());
			identifierObj.setIssuer(data.getIssuedAuthorityText());
			returnList.add(identifierObj);
		}

		
		return returnList;
	}
	
	public  String  parseDate(final Date strDate) {
		String date=null;
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			date= simpleDateFormat.format(strDate);
		} catch (final Exception e) {
			logger.error("error in parseDate",e);
		}
		return date;
	}
	public  String  parseBirthDate(final Date strDate) {
		String date=null;
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date= simpleDateFormat.format(strDate);
		} catch (final Exception e) {
			logger.error("error in parseBirthDate",e);
		}
		return date;
	}
	
}

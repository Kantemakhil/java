package net.syscon.s4.inst.legalscreens.maintenance;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.global.Omss40Service;
import net.syscon.s4.globalconfiguration.OcmpconfService;
import net.syscon.s4.inst.legals.OcipenscService;
import net.syscon.s4.inst.legals.beans.OffenderSentenceAdjustment;
import net.syscon.s4.inst.legals.beans.OffenderSentenceAdjustmentCommitBean;
import net.syscon.s4.inst.legals.beans.SentenceAdjustment;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalSettings;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class OidcustadController {
	
	@Autowired
	private ProsmainService prosmainService;
	
	@Autowired
	private OidcustadService oidcustadService;
	
	@Autowired
	private OcmpconfService ocmpconfService;
	
	@Autowired
	private Omss40Service omss40Service;
	
	@Autowired
	private OcipenscService ocipenscService;
	
	private static Logger logger = LogManager.getLogger(OidcustadController.class.getName());

	
	@PostMapping("/oidcustad/bookingsdata")
	public Integer saveBookingsData(@RequestBody OffenderSentenceAdjustmentCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		Integer returnValue=null;
		try {
			List<String> authorizationList = headers.get("Authorization");
			String authorization = authorizationList.get(0).split(",")[0];
			if (commitBean != null)
				commitBean.setCreateUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			returnValue = oidcustadService.saveBookingsData(commitBean);
			if (returnValue == 1) {
				if (commitBean.getCalcReason() != null && !commitBean.getCalcReason().trim().isEmpty()) {
					// To call sentence calculation process
					OdynfrmSubmitDataBean odynfrmSubmitDataBean = new OdynfrmSubmitDataBean();
					String formIdentifier = "{\"offenderBookId\":\"" + commitBean.getOffenderBookId()+ "\"}";
					odynfrmSubmitDataBean.setSearchString(formIdentifier);
					odynfrmSubmitDataBean.setFormIdentifier(formIdentifier);
					odynfrmSubmitDataBean.setCalcReason(commitBean.getCalcReason());
					odynfrmSubmitDataBean.setActionType("Modification");
					odynfrmSubmitDataBean.setCreateUserId(commitBean.getCreateUserId());
					logger.info("Adjustments Calc reason " + commitBean.getCalcReason());
					Map<String,Object> applicationStatus = omss40Service.getApplicationStatus();
					List<OdynfrmSubmitDataBean> getoffenderPendEvents = ocipenscService.getPendingSentenceCalcEvents(odynfrmSubmitDataBean);
					if((getoffenderPendEvents != null && getoffenderPendEvents.size()>0) || (!applicationStatus.containsKey(ApplicationConstants.AUTOMATION_ENGINE_STATUS) || applicationStatus.get(ApplicationConstants.AUTOMATION_ENGINE_STATUS)==null || applicationStatus.get(ApplicationConstants.AUTOMATION_ENGINE_STATUS).equals("D")) || (!applicationStatus.containsKey(ApplicationConstants.SENTENCE_ENGINE_STATUS) || applicationStatus.get(ApplicationConstants.SENTENCE_ENGINE_STATUS)==null || applicationStatus.get(ApplicationConstants.SENTENCE_ENGINE_STATUS).equals("D"))) {
						odynfrmSubmitDataBean.setModuleName("OIDCUSTAD");
						returnValue = ocmpconfService.sentenceEngineOffline(odynfrmSubmitDataBean);
						if(returnValue == 2) {
							if(ApplicationConstants.YES_FLAG.equalsIgnoreCase(ocmpconfService.getAutomaticStatusFlag("SENTCALWARNFLAG"))) {
								if((!applicationStatus.containsKey(ApplicationConstants.AUTOMATION_ENGINE_STATUS) || applicationStatus.get(ApplicationConstants.AUTOMATION_ENGINE_STATUS)==null || applicationStatus.get(ApplicationConstants.AUTOMATION_ENGINE_STATUS).equals("D")) || (!applicationStatus.containsKey(ApplicationConstants.SENTENCE_ENGINE_STATUS) || applicationStatus.get(ApplicationConstants.SENTENCE_ENGINE_STATUS)==null || applicationStatus.get(ApplicationConstants.SENTENCE_ENGINE_STATUS).equals("D"))) {
									returnValue = 3;
								}
							} else {
								returnValue = 4;
							}
						}
					} else {
						prosmainService.sentCalcTrigger(odynfrmSubmitDataBean, authorization, "61");
						logger.info("Sentence process called successfully" + commitBean.getCalcReason());
					}
				}
				if (commitBean.getInsertList().size() > 0) {
					for (OffenderSentenceAdjustment offenderSentenceAdjustment : commitBean.getInsertList()) {
						List<OffenderSentenceAdjustment> adjsutmentList = new ArrayList<OffenderSentenceAdjustment>();
						OffenderSentenceAdjustmentCommitBean adjustmentCommitBean = new OffenderSentenceAdjustmentCommitBean();
						if("BOOKING".equals(offenderSentenceAdjustment.getObjectType())) {
							adjsutmentList.add(offenderSentenceAdjustment);
							adjustmentCommitBean.setInsertList(adjsutmentList);
							logger.info("Booking Adjustmemnt trigger " + commitBean.getCalcReason());
							prosmainService.enableTriggers(adjustmentCommitBean, authorization, "111");
						} else if ("CUST".equals(offenderSentenceAdjustment.getObjectType())) {
							adjsutmentList.add(offenderSentenceAdjustment);
							adjustmentCommitBean.setInsertList(adjsutmentList);
							logger.info("Sentence Adjustmemnt trigger " + commitBean.getCalcReason());
							prosmainService.enableTriggers(adjustmentCommitBean, authorization, "112");
						}
					}
				}
			}
			if(commitBean.getUpdateList().size() > 0) {
				for (OffenderSentenceAdjustment offenderSentenceAdjustment : commitBean.getUpdateList()) {
					List<OffenderSentenceAdjustment> adjsutmentList = new ArrayList<OffenderSentenceAdjustment>();
					OffenderSentenceAdjustmentCommitBean adjustmentCommitBean = new OffenderSentenceAdjustmentCommitBean();
					if("BOOKING".equals(offenderSentenceAdjustment.getObjectType())) {
						adjsutmentList.add(offenderSentenceAdjustment);
						adjustmentCommitBean.setInsertList(adjsutmentList);
						logger.info("Booking Adjustmemnt trigger " + commitBean.getCalcReason());
						prosmainService.enableTriggers(adjustmentCommitBean, authorization, "111");
					} else if ("CUST".equals(offenderSentenceAdjustment.getObjectType())) {
						adjsutmentList.add(offenderSentenceAdjustment);
						adjustmentCommitBean.setInsertList(adjsutmentList);
						logger.info("Sentence Adjustmemnt trigger " + commitBean.getCalcReason());
						prosmainService.enableTriggers(adjustmentCommitBean, authorization, "112");
					}
				}
			}
			if(commitBean.getDeleteList().size() > 0) {
				for (OffenderSentenceAdjustment offenderSentenceAdjustment : commitBean.getDeleteList()) {
					List<OffenderSentenceAdjustment> adjsutmentList = new ArrayList<OffenderSentenceAdjustment>();
					OffenderSentenceAdjustmentCommitBean adjustmentCommitBean = new OffenderSentenceAdjustmentCommitBean();
					if("BOOKING".equals(offenderSentenceAdjustment.getObjectType())) {
						adjsutmentList.add(offenderSentenceAdjustment);
						adjustmentCommitBean.setInsertList(adjsutmentList);
						logger.info("Booking Adjustmemnt trigger " + commitBean.getCalcReason());
						prosmainService.enableTriggers(adjustmentCommitBean, authorization, "111");
					} else if ("CUST".equals(offenderSentenceAdjustment.getObjectType())) {
						adjsutmentList.add(offenderSentenceAdjustment);
						adjustmentCommitBean.setInsertList(adjsutmentList);
						logger.info("Sentence Adjustmemnt trigger " + commitBean.getCalcReason());
						prosmainService.enableTriggers(adjustmentCommitBean, authorization, "112");
					}
				}
			}
		}catch(Exception e) {
			getLogMessage("saveOrdersData",e);
		}
		return returnValue;
	}
	
	
	@GetMapping("/oidcustad/fetchBookingsData")
	public List<OffenderSentenceAdjustment> fetchBookingsData(Long offenderBookId) {
		List<OffenderSentenceAdjustment> ordersData=Collections.checkedList(new ArrayList<OffenderSentenceAdjustment>(), OffenderSentenceAdjustment.class);
		try {
			ordersData= oidcustadService.fetchBookingsData(offenderBookId);
		}catch (Exception e) {
			getLogMessage("fetchBookingsData",e);
		}
		return ordersData;
	}
	
	@GetMapping("oidcustad/getBookingCodes")
	public List<SentenceAdjustment> getReferencesCodes(){
		List<SentenceAdjustment> domainData=Collections.checkedList(new ArrayList<SentenceAdjustment>(), SentenceAdjustment.class);
		try {
			domainData= oidcustadService.getBookingCodes();
		}catch (Exception e) {
			getLogMessage("getReferencesCodes",e);
		}
		return domainData;
	}
	
	@GetMapping("/oidcustad/getDebitorCreditCode")
	public String getDebitorCreditCode(String code) {
		String value=null;
		try {
			value= oidcustadService.getDebitorCreditCode(code);
		}catch (Exception e) {
			getLogMessage("getDebitorCreditCode",e);
		}
		return value;
	}
	
	@GetMapping("oidcustad/getSentnceCodes")
	public List<SentenceAdjustment> getSentnceCodes(){
		List<SentenceAdjustment> domainData=Collections.checkedList(new ArrayList<SentenceAdjustment>(), SentenceAdjustment.class);
		try {
			domainData= oidcustadService.getSentenceCodes();
		}catch (Exception e) {
			getLogMessage("getSentnceCodes",e);
		}
		return domainData;
	}
	
	@GetMapping("oidcustad/getSentenceData")
	public List<OffenderSentenceAdjustment> getSentenceData(String objectType, Long offenderBookId) {
		List<OffenderSentenceAdjustment> domainData=Collections.checkedList(new ArrayList<OffenderSentenceAdjustment>(), OffenderSentenceAdjustment.class);
		try {
			domainData= oidcustadService.getSentenceData(objectType,offenderBookId);
		}catch (Exception e) {
			getLogMessage("getSentnceCodes",e);
		}
		return domainData;
	}
	
	@GetMapping("oidcustad/getUsagecode")
	public String getusagecode(String code) {
		String usageCode=null;
		try {
			usageCode=oidcustadService.getusagecode(code);
		}catch (Exception e) {
			getLogMessage("getusagecode",e);
		}
		return usageCode;
	}
	
	@PostMapping("oidcustad/caluculatedays")
	public long caluculatedays(@RequestBody OffenderSentenceAdjustment adj) {
		long totalDays=0;
		try {
			final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			final LocalDate firstDate = LocalDate.parse(formatter.format(adj.getAdjustFromDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()), formatter);
			final LocalDate secondDate = LocalDate.parse(formatter.format(adj.getAdjustToDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()), formatter);
			if( adj.getAdjustCodeType()!=null && adj.getAdjustCodeType().equals("Credit")) {
				totalDays=ChronoUnit.DAYS.between(firstDate, secondDate)+1;
			}else if( adj.getAdjustCodeType()!=null && adj.getAdjustCodeType().equals("Debit")) {
				if (firstDate != null && secondDate != null && firstDate.compareTo(secondDate) == 0) {
					totalDays = ChronoUnit.DAYS.between(firstDate, secondDate);
				} else {
					totalDays = ChronoUnit.DAYS.between(firstDate, secondDate) - 1;
				}
			}
		} catch (Exception e) {
			getLogMessage("caluculatedays",e);
		}
		return totalDays;
	}
	
	@GetMapping("oidcustad/getRemissionEligibility")
	public List<LegalSettings> getRemissionEligibility() {
		List<LegalSettings> remissionEligiblity=new ArrayList<>();
		try {
			remissionEligiblity=oidcustadService.getRemissionEligibility();
		}catch (Exception e) {
			getLogMessage("getRemissionEligibility",e);
		}
		return remissionEligiblity;
	}
	
	@GetMapping("oidcustad/getIntakeDetails")
	public OffenderExternalMovements getIntakeDetails(Long offenderBookId) {
		OffenderExternalMovements movements=new OffenderExternalMovements();
		try {
			movements=oidcustadService.getIntakeDetails(offenderBookId);
		}catch (Exception e) {
			getLogMessage("getIntakeDetails",e);
		}
		return movements;
	}
	private void getLogMessage(String methodName,Exception e) {
		logger.error("Method in " + this.getClass().getName() +" "+ methodName , e);
	}

}

package net.syscon.s4.globalconfiguration;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import net.syscon.s4.cm.courtcasesandorders.maintenance.OimsreqsService;
import net.syscon.s4.cm.intakeclosure.OcdintakRepository;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.FormBuilderBean;
import net.syscon.s4.common.beans.OcmpconfUiBean;
import net.syscon.s4.common.beans.OdynfrmCommitBean;
import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;
import net.syscon.s4.common.beans.Offence;
import net.syscon.s4.common.beans.OffenceByStatute;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SentenceCalcTypes;
import net.syscon.s4.common.beans.SentenceTerms;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.global.Omss40Service;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OffenceResultCodes;
import net.syscon.s4.im.beans.Statutes;
import net.syscon.s4.inst.legals.OcipenscService;
import net.syscon.s4.inst.legals.beans.OffenderPayrolEvent;
import net.syscon.s4.inst.legalscreens.OcmorcodService;
import net.syscon.s4.inst.legalscreens.OimstatuService;
import net.syscon.s4.inst.legalscreens.maintenance.OimoffenService;
import net.syscon.s4.inst.legalscreens.maintenance.bean.AllowableOffenceTypes;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasons;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.iwp.OcdenforService;
import net.syscon.s4.sa.recordmaintenance.CmdactionRepository;
import net.syscon.s4.sa.recordmaintenance.CmdactionService;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;


/**
 * class OumsypflController
 */
@EliteController
public class OcmpconfController {

	@Autowired
	private OcmpconfService ocmpconfService;
	
	@Autowired
	private OumaglocService oumaglocService;
	
	@Autowired
	private OcmorcodService ocmorcodService;
	
	@Autowired
	private ProsmainService prosmainService;
	
	@Autowired
	private OimsreqsService oimsreqsService;
	
	@Autowired
	private OimstatuService oimstatuService;
	
	@Autowired
	private OumrcodeService oumrcodeservice;
	
	@Autowired
	private OimoffenService oimoffenService;
	
	@Autowired
	private CmdactionService cmdactionService;
	
	@Autowired
	CmdactionRepository cmdactionRepository;
	
	@Autowired
	OcdintakRepository ocdintakRepository;
	
	@Autowired
	Omss40Service omss40Service;
	
	@Autowired
	OcipenscService ocipenscService;
	
	@Autowired
	private OcdenforService ocdenforService;
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmpconfController.class.getName());

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmpconf/loadData", method = RequestMethod.GET)
	public List<OcmpconfUiBean> loadData() {
		List<OcmpconfUiBean> compDataList = new ArrayList<OcmpconfUiBean>();
		final OcmpconfUiBean obj = new OcmpconfUiBean();
		try {
			compDataList = ocmpconfService.loadData();
		} catch (Exception e) {
			logger.error("In loadData:", e);
			obj.setErrorMessage(e.getMessage());
			compDataList.add(obj);
		}
		return compDataList;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmpconf/commitformData", method = RequestMethod.POST)
	public Integer commitformData(@RequestBody final OdynfrmCommitBean odynfrmCommitBean) {
		Integer formBuilderData = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		odynfrmCommitBean.setCreateUserId(userName);
		try {
			formBuilderData = ocmpconfService.commitformData(odynfrmCommitBean); 
		} catch (Exception e) {
			logger.error("In commitformData:", e);
		}
		return formBuilderData;
	}



@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmpconf/saveData", method = RequestMethod.POST)
	public Integer saveData(@RequestBody final List<OcmpconfUiBean> compData) {
		Integer returnData = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		compData.forEach(data->{data.setModifyUserId(userName);});
		try {
			returnData = ocmpconfService.saveData(compData);
		} catch (Exception e) {
			logger.error("In loadData:", e);
		}
		return returnData;
	}
	
	
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/dynamicForms/saveData", method = RequestMethod.POST)
	public Integer saveFormbuilderData(@RequestBody final FormBuilderBean formBean) {
		Integer returnData = 0;
		try {
			returnData = ocmpconfService.saveFormbuilderData(formBean);
		} catch (Exception e) {
			logger.error("In saveFormbuilderData:", e);
		}
		return returnData;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/dynamicForms/updateFormbuilderData", method = RequestMethod.POST)
	public Integer updateFormbuilderData(@RequestBody final FormBuilderBean formBean) {
		Integer returnData = 0;
		try {
			returnData = ocmpconfService.updateFormbuilderData(formBean);
		} catch (Exception e) {
			logger.error("In saveFormbuilderData:", e);
		}
		return returnData;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmpconf/loadFormbuilderData", method = RequestMethod.GET)
	public List<FormBuilderBean> loadFormbuilderData() {
		List<FormBuilderBean> formBuilderData = new ArrayList<>();
		final FormBuilderBean obj = new FormBuilderBean();
		try {
			formBuilderData = ocmpconfService.loadFormbuilderData();
		} catch (Exception e) {
			logger.error("In loadData:", e);
			obj.setErrorMessage(e.getMessage());
			formBuilderData.add(obj);
		}
		return formBuilderData;
	}
	
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmpconf/submitFormData", method = RequestMethod.POST)
	public Integer submitFormData(@RequestBody final OdynfrmSubmitDataBean odynfrmSubmitDataBean, @RequestHeader HttpHeaders headers) {
		Integer formBuilderData = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		odynfrmSubmitDataBean.setCreateUserId(userName);
		odynfrmSubmitDataBean.setModifyUserId(userName);
		try {
			List<Map<String, Object>> calcReasonJson = null;
			if("OCDLEGLO".equalsIgnoreCase(odynfrmSubmitDataBean.getFormName())) {
				Map<String,List<Map<String,Object>>> formInfoJson = new ObjectMapper().readValue(odynfrmSubmitDataBean.getFormInfoJson(), HashMap.class);
				calcReasonJson = formInfoJson.get("calcReason");
				if(calcReasonJson!= null && !calcReasonJson.isEmpty()) {
					odynfrmSubmitDataBean.setCalcReason(new ObjectMapper().writeValueAsString(calcReasonJson.get(0)));
				}
				formInfoJson.remove("calcReason");
				odynfrmSubmitDataBean.setFormInfoJson(new ObjectMapper().writeValueAsString(formInfoJson));
			}
			if("OCDLEGLS".equalsIgnoreCase(odynfrmSubmitDataBean.getFormName())) {
				Map<String,List<Map<String,Object>>> calReasonSummaryMap = new ObjectMapper().readValue(odynfrmSubmitDataBean.getCalcReason(), HashMap.class);;
				odynfrmSubmitDataBean.setCalcReason(new ObjectMapper().writeValueAsString(calReasonSummaryMap));
			}
			formBuilderData = ocmpconfService.submitFormData(odynfrmSubmitDataBean); 
			if(odynfrmSubmitDataBean.getCalcReason()== null || odynfrmSubmitDataBean.getCalcReason().equals("[]") || odynfrmSubmitDataBean.getCalcReason().isEmpty()) {
				odynfrmSubmitDataBean.setIsNotCamundaCall(false);
			} else {
				Map<String,Object> applicationStatus = omss40Service.getApplicationStatus();
				Map<String, Object> inputData = new HashMap<>();
				inputData = new ObjectMapper().readValue(odynfrmSubmitDataBean.getFormIdentifier(), new TypeReference<HashMap<String,Object>>(){});
				odynfrmSubmitDataBean.setSearchString("\"offenderBookId\":\""+inputData.get("offenderBookId")+"\"");
				List<OdynfrmSubmitDataBean> getoffenderPendEvents = ocipenscService.getPendingSentenceCalcEvents(odynfrmSubmitDataBean);
				if ((getoffenderPendEvents != null && getoffenderPendEvents.size()>0) || (!applicationStatus.containsKey(ApplicationConstants.AUTOMATION_ENGINE_STATUS) || applicationStatus.get(ApplicationConstants.AUTOMATION_ENGINE_STATUS)==null || applicationStatus.get(ApplicationConstants.AUTOMATION_ENGINE_STATUS).equals("D")) || (!applicationStatus.containsKey(ApplicationConstants.SENTENCE_ENGINE_STATUS) || applicationStatus.get(ApplicationConstants.SENTENCE_ENGINE_STATUS)==null || applicationStatus.get(ApplicationConstants.SENTENCE_ENGINE_STATUS).equals("D"))) {
					odynfrmSubmitDataBean.setIsNotCamundaCall(false);
					formBuilderData = ocmpconfService.sentenceEngineOffline(odynfrmSubmitDataBean);
					if(formBuilderData == 2) {
						if(ApplicationConstants.YES_FLAG.equalsIgnoreCase(ocmpconfService.getAutomaticStatusFlag("SENTCALWARNFLAG"))) {
							if((!applicationStatus.containsKey(ApplicationConstants.AUTOMATION_ENGINE_STATUS) || applicationStatus.get(ApplicationConstants.AUTOMATION_ENGINE_STATUS)==null || applicationStatus.get(ApplicationConstants.AUTOMATION_ENGINE_STATUS).equals("D")) || (!applicationStatus.containsKey(ApplicationConstants.SENTENCE_ENGINE_STATUS) || applicationStatus.get(ApplicationConstants.SENTENCE_ENGINE_STATUS)==null || applicationStatus.get(ApplicationConstants.SENTENCE_ENGINE_STATUS).equals("D"))) {
								formBuilderData = 3;
							}
						} else {
							formBuilderData = 4;
						}
					}
				}
			}
			if("1".equals(String.valueOf(formBuilderData)) && odynfrmSubmitDataBean.getIsNotCamundaCall()) {
//				odynfrmSubmitDataBean.setFormInfoJson("{\"sentences\":[{\"commence_date\":\"2021-05-17\",\"ordered_date\":\"2021-05-28\",\"no\":\"1\",\"court\":\"HMAG\",\"type\":\"Imprisionment\",\"commence_type\":\"Date to commence\",\"charges\":[{\"code\":\"201\",\"matter\":\"6120/2020\",\"charge_id\":\"266\",\"current_status\":\"Remand\"},{\"code\":\"202\",\"matter\":\"6121/2020\",\"charge_id\":\"267\",\"current_status\":\"Sentenced\"}],\"terms\":[{\"term_type\":\"IMP\",\"years\":0,\"months\":2,\"weeks\":3,\"days\":5,\"hours\":0}],\"dependent_sentence\":[{\"commence_date\":\"2021-02-11\",\"court\":\"HMAG\",\"type\":\"Imprisionment\",\"commence_type\":\"Date to commence\",\"charges\":[{\"code\":\"201\",\"matter\":\"6120/2020\",\"charge_id\":\"266\",\"current_status\":\"Remand\"},{\"code\":\"202\",\"matter\":\"6121/2020\",\"charge_id\":\"267\",\"current_status\":\"Sentenced\"}],\"terms\":[{\"term_type\":\"IMP\",\"years\":0,\"months\":2,\"weeks\":3,\"days\":5,\"hours\":0}],\"dependent_sentence\":{\"commence_date\":\"2021-05-16\",\"related_to\":1,\"court\":\"HMAG\",\"type\":\"Imprisionment\",\"commence_type\":\"Date to commence\",\"charges\":[{\"code\":\"201\",\"matter\":\"6120/2020\",\"charge_id\":\"266\",\"current_status\":\"Remand\"},{\"code\":\"202\",\"matter\":\"6121/2020\",\"charge_id\":\"267\",\"current_status\":\"Sentenced\"}],\"terms\":[{\"term_type\":\"IMP\",\"years\":0,\"months\":2,\"weeks\":3,\"days\":5,\"hours\":0}],\"dependent_sentence\":{\"commence_date\":\"2021-05-16\",\"related_to\":1,\"court\":\"HMAG\",\"type\":\"Imprisionment\",\"commence_type\":\"Date to commence\",\"charges\":[{\"code\":\"201\",\"matter\":\"6120/2020\",\"charge_id\":\"266\",\"current_status\":\"Remand\"},{\"code\":\"202\",\"matter\":\"6121/2020\",\"charge_id\":\"267\",\"current_status\":\"Sentenced\"}],\"terms\":[{\"term_type\":\"IMP\",\"years\":0,\"months\":2,\"weeks\":3,\"days\":5,\"hours\":0}]}}},{\"commence_date\":\"2021-05-15\",\"related_to\":1,\"court\":\"HMAG\",\"type\":\"Imprisionment\",\"commence_type\":\"Date to commence\",\"charges\":[{\"code\":\"201\",\"matter\":\"6120/2020\",\"charge_id\":\"266\",\"current_status\":\"Remand\"},{\"code\":\"202\",\"matter\":\"6121/2020\",\"charge_id\":\"267\",\"current_status\":\"Sentenced\"}],\"terms\":[{\"term_type\":\"IMP\",\"years\":0,\"months\":0,\"weeks\":1,\"days\":2,\"hours\":0}],\"dependent_sentence\":{\"commence_date\":\"2021-05-15\",\"related_to\":1,\"court\":\"HMAG\",\"type\":\"Imprisionment\",\"commence_type\":\"Date to commence\",\"charges\":[{\"code\":\"201\",\"matter\":\"6120/2020\",\"charge_id\":\"266\",\"current_status\":\"Remand\"},{\"code\":\"202\",\"matter\":\"6121/2020\",\"charge_id\":\"267\",\"current_status\":\"Sentenced\"}],\"terms\":[{\"term_type\":\"IMP\",\"years\":0,\"months\":2,\"weeks\":3,\"days\":5,\"hours\":0}],\"dependent_sentence\":{\"commence_date\":\"2021-05-15\",\"related_to\":1,\"court\":\"HMAG\",\"type\":\"Imprisionment\",\"commence_type\":\"Date to commence\",\"charges\":[{\"code\":\"201\",\"matter\":\"6120/2020\",\"charge_id\":\"266\",\"current_status\":\"Remand\"},{\"code\":\"202\",\"matter\":\"6121/2020\",\"charge_id\":\"267\",\"current_status\":\"Sentenced\"}],\"terms\":[{\"term_type\":\"IMP\",\"years\":0,\"months\":0,\"weeks\":1,\"days\":2,\"hours\":0}]}}}]}]}");
				odynfrmSubmitDataBean.setFormInfoJsonBlob(null);
//				odynfrmSubmitDataBean.setFormInfoJson(transformJson(odynfrmSubmitDataBean.getFormIdentifier()));
				if("OCDLEGLO".equalsIgnoreCase(odynfrmSubmitDataBean.getFormName())) {
					OdynfrmSubmitDataBean ocdleglsObj = new OdynfrmSubmitDataBean();
					ocdleglsObj.setFormName("OCDLEGLS");
					Map<String, Object> inputData = new HashMap<>();
					ocdleglsObj.setSearchString(odynfrmSubmitDataBean.getFormIdentifier());
					inputData = new ObjectMapper().readValue(odynfrmSubmitDataBean.getFormIdentifier(), HashMap.class);
					ocdleglsObj.setSearchString("\"offenderBookId\":\""+inputData.get("offenderBookId")+"\"");
					ocdleglsObj = getFormData(ocdleglsObj);
					if(inputData.get("orderType").equals("BAIL")) {
						//DO Not Call trigger. Add History in Legal summary history for audit purpose.
						String bailHistory = this.transformBailJson(odynfrmSubmitDataBean.getFormIdentifier());
						Map<String,Object> bailHistoryJson = new ObjectMapper().readValue(bailHistory, HashMap.class);
						if(calcReasonJson!= null && !calcReasonJson.isEmpty()) {
							bailHistoryJson.put("calcReason",  calcReasonJson.get(0));
						}
						OdynfrmSubmitDataBean bailHistoryForm =  new OdynfrmSubmitDataBean();
						String searchString = "{\"offenderBookId\":\""+inputData.get("offenderBookId").toString()+"\"}";
						bailHistoryForm.setFormName("OCDLEGLS");
						bailHistoryForm.setSearchString(searchString);
						bailHistoryForm = getFormData(bailHistoryForm);
						bailHistoryForm.setCreateUserId(userName);
						bailHistoryForm.setModifyUserId(userName);
						bailHistoryForm.setFormInfoJson(new ObjectMapper().writeValueAsString(bailHistoryJson));
						bailHistoryForm.setFormName("OCDLEGLS");
						if(bailHistoryForm.getFormIdentifier() == null || ocdleglsObj.getFormInfoJson().trim().isEmpty()) {
							bailHistoryForm.setActionType("Data Entry");
							bailHistoryForm.setFormIdentifier(searchString);
							ocmpconfService.insertOcdLeglsHyt(bailHistoryForm);
							ocmpconfService.submitFormData(bailHistoryForm);
						} else {
							bailHistoryForm.setActionType("Modification");
							ocmpconfService.insertOcdLeglsHyt(bailHistoryForm);
							bailHistoryForm.setModifyUserId(userName);
							ocmpconfService.submitFormData(bailHistoryForm);
						}
						String sentStatus = "";
						OffenderBookings obj = new OffenderBookings();
						List<OffenderBookings> offenderBookingsList = new ArrayList<OffenderBookings>();
						List<OdynfrmSubmitDataBean> legalSummaryData = new ArrayList<OdynfrmSubmitDataBean>();
						String formIdentifier = "{\"offenderBookId\":\""+inputData.get(ApplicationConstants.OFFENDER_BOOK_ID).toString()+"\"}";
						OffenderBookings offenderBookings = cmdactionRepository.getBookingData(Long.parseLong(inputData.get(ApplicationConstants.OFFENDER_BOOK_ID).toString()));
						if("COMM".equals(offenderBookings.getBookingType()) && "O".equals(offenderBookings.getBookingStatus())) {
							legalSummaryData = ocdintakRepository.getLegalSummaryData(formIdentifier);
							sentStatus = cmdactionService.caclucateSentStatus(legalSummaryData.get(0), new BigDecimal(offenderBookings.getRootOffenderId().toString()).longValue());
						}
						obj.setCommStatus(sentStatus);
						obj.setOffenderBookId(Long.parseLong(inputData.get(ApplicationConstants.OFFENDER_BOOK_ID).toString()));
						obj.setModifyUserId(userName);
						offenderBookingsList.add(obj);
						cmdactionRepository.updateSentStatus(offenderBookingsList);
					} else if(ocdleglsObj.getFormInfoJson()!= null && !ocdleglsObj.getFormInfoJson().trim().isEmpty()) {
						odynfrmSubmitDataBean.setActionType("Modification");
						prosmainService.enableTriggers(odynfrmSubmitDataBean, authorization, "61");
					} else {
						odynfrmSubmitDataBean.setActionType("Data Entry");
						prosmainService.enableTriggers(odynfrmSubmitDataBean, authorization, "61");
					}
				} else if("OCDLEGLS".equalsIgnoreCase(odynfrmSubmitDataBean.getFormName())) {
					odynfrmSubmitDataBean.setActionType("Modification");
					prosmainService.enableTriggers(odynfrmSubmitDataBean, authorization, "61");
				}
			}
			if((odynfrmSubmitDataBean.getOrderOperations() != null && !odynfrmSubmitDataBean.getOrderOperations().trim().isEmpty())|| (odynfrmSubmitDataBean.getOrderUpdateRecords()!= null && !odynfrmSubmitDataBean.getOrderUpdateRecords().trim().isEmpty())) {
				prosmainService.enableTriggers(odynfrmSubmitDataBean, authorization, "132");
			}
		} catch (Exception e) {
			logger.error("In commitformData:", e);
		}
		return formBuilderData;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmpconf/getFormData", method = RequestMethod.POST)
	public OdynfrmSubmitDataBean getFormData(@RequestBody final OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		OdynfrmSubmitDataBean obj = new OdynfrmSubmitDataBean();
		try {
			obj = ocmpconfService.getFormData(odynfrmSubmitDataBean); 
		} catch (Exception e) {
			logger.error("In commitformData:", e);
		}
		return obj;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public List<Map<String,Object>> getFormLovData(@RequestParam("limit") final String limit, @RequestParam("select") final String select) {
		List<Map<String,Object>> obj = new ArrayList<>();
		try {
			obj = ocmpconfService.getFormLovData(); 
		} catch (Exception e) {
			logger.error("In getFormLovData:", e);
		}
		return obj;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/form/{formId}", method = RequestMethod.GET)
	public Map<String, Object> getFormData(@PathVariable(required = false) String formId) {
		Map<String, Object> obj = new HashMap<>();
		try {
			if(formId != null) {
				obj = ocmpconfService.getFormData(formId); 
			}
		} catch (Exception e) {
			logger.error("In getFormData:", e);
		}
		return obj;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "ocmpconf/getFormDataBasedOnModName", method = RequestMethod.POST)
	public List<FormBuilderBean> getFormDataBasedOnModName(@RequestBody final String formModuleName) {
		List<FormBuilderBean> obj = new ArrayList<>();
		try {
			obj = ocmpconfService.getFormDataBasedOnModName(formModuleName); 
		} catch (Exception e) {
			logger.error("In commitformData:", e);
		}
		return obj;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "ocmpconf/populateCourtType", method = RequestMethod.GET)
	public List<Map<String,Object>> populateCourtType() {
		List<Map<String,Object>> respList = new ArrayList<>();
		Map<String, Object> obj = new HashMap<>();
		AgencyLocations agencyLocations= new AgencyLocations();
		agencyLocations.setAgencyLocationType("CRT");
		List<AgencyLocations> agencyList = new ArrayList<>();
		try {
			agencyList = oumaglocService.agyLocExecuteQuery(agencyLocations);
			for (AgencyLocations agency : agencyList) {
				obj = new HashMap<>();
				obj.put("code", agency.getAgyLocId());
				obj.put("description", agency.getDescription());
				respList.add(obj);
			}
		} catch (Exception e) {
			logger.error("In populateCourtType:", e);
		}
		return respList;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "ocmpconf/populateOutcome", method = RequestMethod.GET)
	public List<Map<String,Object>> populateOutcome() {
		List<Map<String,Object>> respList = new ArrayList<>();
		Map<String, Object> obj = new HashMap<>();
		OffenceResultCodes offenceResultCodes= new OffenceResultCodes();
		List<OffenceResultCodes> offenceCodesList = new ArrayList<>();
		try {
			offenceCodesList = ocmorcodService.resCodExecuteQuery(offenceResultCodes);
			for (OffenceResultCodes agency : offenceCodesList) {
				obj = new HashMap<>();
				obj.put("code", agency.getResultCode());
				obj.put("description", agency.getDescription());
				obj.put("disposition", agency.getDispositionCode());
				obj.put("chargeStatus", agency.getChargeStatus());
				obj.put("listSeq", agency.getListSeq());
				obj.put("canDisplay", agency.getActiveFlag().equalsIgnoreCase("Y")?true:false);
				respList.add(obj);
			}
		} catch (Exception e) {
			logger.error("In populateOutcome:", e);
		}
		return respList;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "ocmpconf/populateCommenceType", method = RequestMethod.GET)
	public List<Map<String,Object>> populateCommenceType() {
		List<Map<String,Object>> respList = new ArrayList<>();
		Map<String, Object> obj = new HashMap<>(); 
		try {
			obj.put("code", "DTC");
			obj.put("description", "Date to Commence");
			respList.add(obj);
			obj = new HashMap<>();
			obj.put("code", "CON");
			obj.put("description", "Concurrent");
			respList.add(obj);
			obj = new HashMap<>();
			obj.put("code", "CUM");
			obj.put("description", "Cumulative");
			respList.add(obj);
		} catch (Exception e) {
			logger.error("In populateCommenceType:", e);
		}
		return respList;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "ocmpconf/populateSentType", method = RequestMethod.GET)
	public List<Map<String,Object>> populateSentType(@RequestParam String sentCategory) {
		return ocmpconfService.populateSentType(sentCategory);
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "ocmpconf/getInactiveCharges", method = RequestMethod.GET)
	public List<String> getInactiveCharges() {
		List<String> respList = new ArrayList<>();
		OffenceResultCodes offenceResultCodes = new OffenceResultCodes();
		offenceResultCodes.setActiveFlag("false");
		List<OffenceResultCodes> allStatutes = new ArrayList<>();
		try {
			allStatutes = oimstatuService.getInactiveStatutes(offenceResultCodes);
			for (OffenceResultCodes statute : allStatutes) {
				respList.add(statute.getResultCode());
			}
		} catch (Exception e) {
			logger.error("In getInactiveStatutes:", e);
		}
		return respList;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "ocmpconf/populateStatutes", method = RequestMethod.GET)
	public List<Map<String,Object>> populateStatutes() {
		List<Map<String,Object>> respList = new ArrayList<>();
		Map<String, Object> obj = new HashMap<>();
		Statutes statutes = new Statutes();
		List<Statutes> allStatutes = new ArrayList<>();
		try {
			allStatutes = oimstatuService.statExecuteQuery(statutes);
			for (Statutes statute : allStatutes) {
				obj = new HashMap<>();
				obj.put("code", statute.getStatuteCode());
				obj.put("description", statute.getDescription());
				if(statute.getActiveFlag() != null && statute.getActiveFlag().equalsIgnoreCase("Y")) {
					obj.put("canDisplay", true);
				} else {
					obj.put("canDisplay", false);
				}
				respList.add(obj);
			}
		} catch (Exception e) {
			logger.error("In populateStatutes:", e);
		}
		return respList;
	}
	
	@RequestMapping(value = "ocmpconf/populateTermType", method = RequestMethod.GET)
	public List<ReferenceCodes> populateTermType(@RequestParam final String sentType, @RequestParam final String sentCategory) {
		Map<String, Object> obj = new HashMap<>();
		SentenceTerms sentenceCalcTypes= new SentenceTerms();
		sentenceCalcTypes.setSentenceCategory(sentCategory);
		sentenceCalcTypes.setSentenceCalcType(sentType);
		List<SentenceTerms> sentenceCalcTypeList = new ArrayList<>();
		List<ReferenceCodes> refCodes = new ArrayList<>();
		try {
			sentenceCalcTypeList = oimsreqsService.senTermsExecuteQuery(sentenceCalcTypes);
			ReferenceCodes searchBean = new ReferenceCodes(); 
			searchBean.setDomain("SENT_TERM");
			refCodes = oumrcodeservice.refCodeExecuteQuery(searchBean);
			List<String> calcTypes = sentenceCalcTypeList.stream().filter(i -> i.getActiveFlag()!= null && i.getActiveFlag().equalsIgnoreCase("Y")).map(sent->sent.getTermCode()).collect(Collectors.toList());
			refCodes.forEach(ref->{
				if(!calcTypes.contains(ref.getCode())) {
					ref.setCanDisplay(false);
					ref.setActiveFlag("N");
				}
			});
		} catch (Exception e) {
			logger.error("In populateTermType:", e);
		}
		return refCodes;
	}
	
	@RequestMapping(value = "ocmpconf/getAllowableOffenceType", method = RequestMethod.GET)
	public List<ReferenceCodes> getAllowableOffenceType(@RequestParam final String offenceCode, @RequestParam final String statuteCode) {
		Map<String, Object> obj = new HashMap<>();
		List<ReferenceCodes> refCodes = new ArrayList<>();
		List<AllowableOffenceTypes> searchResult = new ArrayList<>();
		AllowableOffenceTypes allowTypes = new AllowableOffenceTypes();
		allowTypes.setOffenceCode(offenceCode);
		allowTypes.setStatuteCode(statuteCode);
		try {
			searchResult = oimoffenService.alwOtExecuteQuery(allowTypes);
			ReferenceCodes searchBean = new ReferenceCodes(); 
			searchBean.setDomain("OFFENCE_TYPE");
			refCodes = oumrcodeservice.refCodeExecuteQuery(searchBean);
			List<String> offenceTypes = searchResult.stream().map(sent->sent.getOffenceType()).collect(Collectors.toList());
			refCodes.forEach(ref->{
				if(!offenceTypes.contains(ref.getCode())) {
					ref.setCanDisplay(false);
				}
			});
		} catch (Exception e) {
			logger.error("In populateTermType:", e);
		}
		return refCodes;
	}
	
	@RequestMapping(value = "ocmpconf/getOffencesOnStatute", method = RequestMethod.GET)
	public List<OffenceByStatute> getOffencesOnStatute(@RequestParam(required = false) final String statuteCode) {
		List<OffenceByStatute> searchResult = new ArrayList<>();
		try {
			Offence off = new Offence();
			off.setStatuteCode(statuteCode);
			if(statuteCode != null) {
				searchResult = oimoffenService.getOffencesOnStatute(off);
			} else {
				searchResult = oimoffenService.getOffencesOnStatuteList();
			}
		} catch (Exception e) {
			logger.error("In getOffencesOnStatute:", e);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "ocmpconf/getDatatypes", method = RequestMethod.GET)
	public List<Map<String, Object>> getDatatypes() {
		List<Map<String, Object>> respMap = new ArrayList<Map<String,Object>>();
//		respMap.put("custOrd", "[{\"field\":\"no\",\"fieldName\":\"ocdleglo.no\",\"dataType\":\"number\",\"editable\":false},{\"field\":\"orderedDate\",\"fieldName\":\"ocdleglo.ordereddate\",\"dataType\":\"date\",\"required\":true},{\"field\":\"matter\",\"fieldName\":\"ocdleglo.matter\",\"dataType\":\"text\",\"editable\":false},{\"field\":\"court\",\"fieldName\":\"ocdleglo.court\",\"dataType\":\"lov\",\"source\":\"link\",\"url\":\"ocdccase/populateCourtData\",\"sourceType\":\"OUMAGLOC\",\"required\":true},{\"field\":\"type\",\"fieldName\":\"ocdleglo.type\",\"dataType\":\"lov\",\"source\":\"link\",\"url\":\"ocmpconf/populateSentType\",\"sourceType\":\"OIMSREQS\",\"required\":true},{\"field\":\"commenceType\",\"fieldName\":\"ocdleglo.commencetype\",\"dataType\":\"lov\",\"source\":\"domain\",\"url\":\"LO_REL_TYPE\",\"required\":true},{\"field\":\"relatedTo\",\"fieldName\":\"ocdleglo.linkto\",\"dataType\":\"text\",\"editable\":false},{\"field\":\"relatedToLaunch\",\"dataType\":\"hyperlink\",\"link\":\"/relatedToLine\",\"parentField\":[\"relatedTo\"]},{\"field\":\"commenceDate\",\"fieldName\":\"ocdleglo.commencedate\",\"dataType\":\"date\"},{\"field\":\"termTypeAndLength\",\"fieldName\":\"ocdleglo.termtypeandlength\",\"dataType\":\"text\",\"required\":true,\"editable\":false},{\"field\":\"lengthBtn\",\"dataType\":\"hyperlink\",\"link\":\"/termToLine\",\"parentField\":[\"termTypeAndLength\",\"terms\"]},{\"field\":\"pel\",\"fieldName\":\"ocdleglo.pel\",\"dataType\":\"checkbox\"},{\"field\":\"status\",\"fieldName\":\"ocdleglo.status\",\"dataType\":\"lov\",\"source\":\"domain\",\"url\":\"ACTIVE_TYPE\",\"editable\":false},{\"field\":\"terms\",\"hide\":true,\"dataType\":\"text\"},{\"field\":\"charges\",\"hide\":true,\"dataType\":\"text\"}]");
//		respMap.put("terms", "[{\"field\":\"termType\",\"fieldName\":\"termToLine.termtype\",\"dataType\":\"lov\",\"source\":\"link\",\"sourceType\":\"OIMSREQS\",\"url\":\"ocmpconf/populateTermType?sentType=\",\"required\":true},{\"field\":\"years\",\"fieldName\":\"termToLine.years\",\"dataType\":\"number\",\"maxValue\":99.5,\"format\":\"1.1-1\"},{\"field\":\"months\",\"fieldName\":\"termToLine.months\",\"dataType\":\"number\",\"maxValue\":99.5,\"format\":\"1.1-1\"},{\"field\":\"weeks\",\"fieldName\":\"termToLine.weeks\",\"dataType\":\"number\",\"maxValue\":99,\"format\":\"0.0-0\"},{\"field\":\"days\",\"fieldName\":\"termToLine.days\",\"dataType\":\"number\",\"maxValue\":99,\"format\":\"0.0-0\"},{\"field\":\"indefinite\",\"fieldName\":\"termToLine.indefinite\",\"dataType\":\"checkbox\"}]");
//		respMap.put("related", "[{\"field\":\"no\",\"fieldName\":\"ocdleglo.no\",\"dataType\":\"number\",\"editable\":false},{\"field\":\"orderedDate\",\"fieldName\":\"ocdleglo.ordereddate\",\"dataType\":\"date\"},{\"field\":\"matter\",\"fieldName\":\"ocdleglo.matter\",\"dataType\":\"text\"},{\"field\":\"type\",\"fieldName\":\"ocdleglo.type\",\"dataType\":\"lov\",\"source\":\"link\",\"url\":\"ocmpconf/populateSentType\",\"sourceType\":\"OIMSREQS\"},{\"field\":\"termTypeAndLength\",\"fieldName\":\"ocdleglo.termtypeandlength\",\"dataType\":\"text\"},{\"field\":\"commenceDate\",\"fieldName\":\"ocdleglo.commencedate\",\"dataType\":\"date\"},{\"field\":\"status\",\"fieldName\":\"ocdleglo.status\",\"dataType\":\"lov\",\"source\":\"domain\",\"url\":\"ACTIVE_TYPE\"}]");
//		respMap.put("charges", "[{\"field\":\"chargeId\",\"fieldName\":\"\",\"dataType\":\"number\",\"editable\":false,\"hide\":true},{\"field\":\"select\",\"fieldName\":\"ocdchgsu.select\",\"dataType\":\"checkbox\",\"editable\":false},{\"field\":\"matter\",\"fieldName\":\"ocdchgsu.matter\",\"dataType\":\"text\",\"required\":true,\"editable\":true},{\"field\":\"act\",\"fieldName\":\"ocdchgsu.act\",\"dataType\":\"lov\",\"source\":\"link\",\"url\":\"ocmpconf/populateStatutes\",\"sourceType\":\"OIMSTATU\",\"required\":true,\"editable\":true},{\"field\":\"code\",\"fieldName\":\"ocdchgsu.code\",\"dataType\":\"text\",\"required\":false,\"editable\":false},{\"field\":\"description\",\"fieldName\":\"ocdchgsu.description\",\"dataType\":\"text\",\"required\":true,\"editable\":false},{\"field\":\"descriptionLaunch\",\"dataType\":\"hyperlink\",\"displayFields\":[\"code\",\"description\",\"act\",\"legislativeBody\"],\"parentField\":[\"code\",\"description\"],\"lovUrl\":\"ocmpconf/getOffencesOnStatute?statuteCode=:act\",\"link\":\"/multiLov\"},{\"field\":\"type\",\"fieldName\":\"ocdchgsu.type\",\"dataType\":\"lov\",\"source\":\"link\",\"sourceType\":\"OIMOFFEN\",\"parentFields\":[\"code\",\"act\"],\"url\":\"ocmpconf/getAllowableOffenceType?offenceCode=:code&statuteCode=:act\",\"editable\":true},{\"field\":\"incidentDate\",\"fieldName\":\"ocdchgsu.incidentdate\",\"dataType\":\"date\",\"editable\":true},{\"field\":\"Range\",\"fieldName\":\"ocdchgsu.range\",\"dataType\":\"date\",\"editable\":true},{\"field\":\"plea\",\"fieldName\":\"ocdchgsu.plea\",\"dataType\":\"lov\",\"source\":\"domain\",\"url\":\"PLEA_STATUS\",\"editable\":true},{\"field\":\"currentStatus\",\"fieldName\":\"ocdchgsu.currentstatus\",\"dataType\":\"text\",\"editable\":false},{\"field\":\"details\",\"fieldName\":\"ocdchgsu.details\",\"dataType\":\"text\",\"editable\":false}]");
//		respMap.put("chargesChild", "[{\"field\":\"chargeId\",\"fieldName\":\"\",\"dataType\":\"number\",\"editable\":false,\"hide\":true},{\"field\":\"matter\",\"fieldName\":\"ocdchgsu.matter\",\"dataType\":\"text\",\"editable\":false},{\"field\":\"description\",\"fieldName\":\"ocdchgsu.descriptionchild\",\"dataType\":\"text\",\"editable\":false},{\"field\":\"type\",\"fieldName\":\"ocdchgsu.type\",\"dataType\":\"lov\",\"source\":\"domain\",\"url\":\"OFFENCE_TYPE\",\"editable\":false},{\"field\":\"incidentDate\",\"fieldName\":\"ocdchgsu.incidentdate\",\"dataType\":\"date\",\"editable\":false},{\"field\":\"Range\",\"fieldName\":\"ocdchgsu.range\",\"dataType\":\"date\",\"editable\":false},{\"field\":\"plea\",\"fieldName\":\"ocdchgsu.plea\",\"dataType\":\"lov\",\"source\":\"domain\",\"url\":\"PLEA_STATUS\",\"editable\":false},{\"field\":\"outcome\",\"fieldName\":\"ocdchgsu.outcome\",\"dataType\":\"lov\",\"source\":\"link\",\"url\":\"ocmpconf/populateOutcome\",\"sourceType\":\"OCMORCOD\",\"required\":true,\"editable\":true},{\"field\":\"details\",\"fieldName\":\"ocdchgsu.details\",\"dataType\":\"text\",\"editable\":false}]");
//		respMap.put("chargesDlg", "[{\"field\":\"chargeId\",\"fieldName\":\"\",\"dataType\":\"number\",\"editable\":false,\"hide\":true},{\"field\":\"select\",\"dataType\":\"checkbox\",\"fieldName\":\"ocdchgsu.select\",\"editable\":true},{\"field\":\"matter\",\"fieldName\":\"ocdchgsu.matter\",\"dataType\":\"text\",\"required\":true,\"editable\":true},{\"field\":\"act\",\"fieldName\":\"ocdchgsu.act\",\"dataType\":\"lov\",\"source\":\"link\",\"url\":\"ocmpconf/populateStatutes\",\"sourceType\":\"OIMSTATU\",\"required\":true,\"editable\":true},{\"field\":\"code\",\"fieldName\":\"ocdchgsu.code\",\"dataType\":\"text\",\"required\":false,\"editable\":false},{\"field\":\"description\",\"fieldName\":\"ocdchgsu.description\",\"required\":true,\"dataType\":\"text\",\"editable\":false},{\"field\":\"descriptionLaunch\",\"dataType\":\"hyperlink\",\"displayFields\":[\"code\",\"description\",\"act\",\"legislativeBody\"],\"parentField\":[\"code\",\"description\"],\"lovUrl\":\"ocmpconf/getOffencesOnStatute?statuteCode=:act\",\"link\":\"/multiLov\"},{\"field\":\"type\",\"fieldName\":\"ocdchgsu.type\",\"dataType\":\"lov\",\"source\":\"link\",\"sourceType\":\"OIMOFFEN\",\"parentFields\":[\"code\",\"act\"],\"url\":\"ocmpconf/getAllowableOffenceType?offenceCode=:code&statuteCode=:act\",\"editable\":true},{\"field\":\"incidentDate\",\"fieldName\":\"ocdchgsu.incidentdate\",\"dataType\":\"date\",\"editable\":true},{\"field\":\"Range\",\"fieldName\":\"ocdchgsu.range\",\"dataType\":\"date\",\"editable\":true},{\"field\":\"plea\",\"fieldName\":\"ocdchgsu.plea\",\"dataType\":\"lov\",\"source\":\"domain\",\"url\":\"PLEA_STATUS\",\"editable\":true},{\"field\":\"currentStatus\",\"fieldName\":\"ocdchgsu.currentstatus\",\"dataType\":\"text\",\"editable\":false},{\"field\":\"details\",\"fieldName\":\"ocdchgsu.details\",\"dataType\":\"text\",\"editable\":false}]");
//		respMap.put("keyDateSummary", "[{\"field\":\"date_type\",\"dataType\":\"text\",\"fieldName\":\"ocdlegls.datetype\",\"editable\":false},{\"field\":\"value\",\"dataType\":\"date\",\"fieldName\":\"ocdlegls.value\",\"editable\":false}]");
		try {
			respMap = ocmpconfService.dynamicGridConfig();
		} catch (Exception e) {
			logger.error("In getDatatypes:", e);
		}
		return respMap;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "ocmpconf/transformJson", method = RequestMethod.POST)
	public String transformJson(@RequestBody String queryData) {
		return ocmpconfService.transformJson(queryData,false);
	}
	
	private String transformBailJson(String queryData) {
		String respJson = "";
		Map<String, Object> inputData = new HashMap<>();
		try {
			inputData = new ObjectMapper().readValue(queryData, HashMap.class);
			String formIdentifier = "{\"offenderBookId\":\""+inputData.get("offenderBookId")+"\",\"orderType\":\"BAIL\"}";
			OdynfrmSubmitDataBean bailData = new OdynfrmSubmitDataBean();
			bailData.setSearchString(formIdentifier);
			bailData.setFormName("OCDLEGLO");
			bailData = getFormData(bailData);
			
			OdynfrmSubmitDataBean summaryData = new OdynfrmSubmitDataBean();
			String formIdentifierSumm = "{\"offenderBookId\":\""+inputData.get("offenderBookId")+"\"}";
			summaryData.setSearchString(formIdentifierSumm);
			summaryData.setFormName("OCDLEGLS");
			summaryData = getFormData(summaryData);
			List<Map<String, Object>> bailObj = new ArrayList<>();
			Map<String, List<Map<String, Object>>> summaryObj = new HashMap<>();
			if(bailData != null && bailData.getFormInfoJson() != null) {
				JsonObject bailJsonObj = new Gson().fromJson(bailData.getFormInfoJson(), JsonObject.class);
				String bailOrderStr = bailJsonObj.toString();
				bailObj = (List<Map<String, Object>>) new ObjectMapper().readValue(bailOrderStr, HashMap.class).get("myJsonRowData");
				for(Map<String, Object> order : bailObj ) {
					Map<String, Object> sentenceExpiryDate = new HashMap<String, Object>();
					sentenceExpiryDate.put("dateValue",order.get("expiryDate"));
					sentenceExpiryDate.put("indefinite", false);
					order.put("sentenceExpiryDate",sentenceExpiryDate);
					order.put("sentenceOrderDates", Collections.emptyList());
					order.put("sentenceOrderType", order.get("orderType"));
					order.remove("court");
					order.remove("orderType");
					order.remove("matter");
					order.remove("charges");
					order.remove("expiryDate");
				} 
			}
			if(summaryData != null && summaryData.getFormInfoJson() != null && !summaryData.getFormInfoJson().equalsIgnoreCase("")) {
				summaryObj = (Map<String, List<Map<String, Object>>>) new ObjectMapper().readValue(summaryData.getFormInfoJson(), HashMap.class);
			}
			List<Map<String, Object>> bookingDates = new ArrayList<>();
			List<Map<String, Object>> sentenceOrderDates = new ArrayList<>();
			if(summaryObj.containsKey("bookingDates")) {
				bookingDates = summaryObj.get("bookingDates");
				if(bookingDates == null) {
					bookingDates = Collections.emptyList();
				}
			} else {
				bookingDates = Collections.emptyList();
				summaryObj.put("bookingDates", bookingDates);
			}
			if(summaryObj.containsKey("sentenceDates")) {
				sentenceOrderDates = summaryObj.get("sentenceDates");
				if(sentenceOrderDates == null) {
					sentenceOrderDates = Collections.emptyList();
				} else {
					List<Map<String, Object>> removeObj = sentenceOrderDates.stream().filter(i->i.containsKey("sentenceOrderType") && i.get("sentenceOrderType").toString().equalsIgnoreCase("BAIL")).collect(Collectors.toList());
					if(removeObj!= null && !removeObj.isEmpty()) {
						sentenceOrderDates.removeAll(removeObj);
					}
					sentenceOrderDates.addAll(bailObj);
				}
			} else {
				sentenceOrderDates.addAll(bailObj);
				summaryObj.put("sentenceDates", sentenceOrderDates);
			}
			if(summaryObj.containsKey("sentenceDates")) {
				summaryObj.remove(summaryObj.get("calcReason"));
				return new ObjectMapper().writeValueAsString(summaryObj);
			}
			
		} catch (Exception e) {
			logger.error("error in transformBailJson method", e);
			e.printStackTrace();
		}
		return respJson;
	}
	
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "ocmpconf/updateCumKeyDates", method = RequestMethod.POST)
	public int updateCumKeyDates(@RequestBody String queryData) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		logger.info("updateCumKeyDates method ",queryData);
		Map<String, Object> inputData = new HashMap<>();
		int isSaved = 0;
		OdynfrmSubmitDataBean custData = new OdynfrmSubmitDataBean();
		OdynfrmSubmitDataBean nCustData = new OdynfrmSubmitDataBean();
		OdynfrmSubmitDataBean parData = new OdynfrmSubmitDataBean();
		OdynfrmSubmitDataBean keyDatesData = new OdynfrmSubmitDataBean();
		if(queryData.contains("formInfoJson")) {
			queryData = queryData.replace("\"[", "[").replace("\"{", "{").replace("}\"", "}").replace("]\"", "]");
		}
		try {
			inputData = new ObjectMapper().readValue(queryData, HashMap.class);
			String formIdentifier = "{\"offenderBookId\":\""+((Map)inputData.get("formIdentifier")).get("offenderBookId")+"\",\"orderType\":\"CUST\"}";
			custData.setSearchString(formIdentifier);
			custData.setFormName("OCDLEGLO");
			custData = getFormData(custData);
			custData.setIsNotCamundaCall(false);
			String formIdentifierNcust = "{\"offenderBookId\":\""+((Map)inputData.get("formIdentifier")).get("offenderBookId")+"\",\"orderType\":\"NONCUST\"}";
			nCustData.setSearchString(formIdentifierNcust);
			nCustData.setFormName("OCDLEGLO");
			nCustData = getFormData(nCustData);
			nCustData.setIsNotCamundaCall(false);
			String formIdentifierPar = "{\"offenderBookId\":\""+((Map)inputData.get("formIdentifier")).get("offenderBookId")+"\",\"orderType\":\"PAR\"}";
			parData.setSearchString(formIdentifierPar);
			parData.setFormName("OCDLEGLO");
			parData = getFormData(parData);
			parData.setIsNotCamundaCall(false);
			keyDatesData.setSearchString("\"offenderBookId\":\""+((Map)inputData.get("formIdentifier")).get("offenderBookId")+"\"");
			keyDatesData.setFormName("OCDLEGLS");
			keyDatesData = getFormData(keyDatesData);
			Map<String, Object> keyDatesMap = new ObjectMapper().readValue(keyDatesData.getFormInfoJson(), HashMap.class);
			Map<String, Object> initialKeyDates = new ObjectMapper().readValue(keyDatesData.getFormInfoJson(), HashMap.class);
			if(custData.getFormInfoJson() != null) {
				Map<String, Object> custDataMap = new ObjectMapper().readValue(custData.getFormInfoJson(), HashMap.class);
				setCalculatedDates(keyDatesMap, custDataMap);
				custData.setFormInfoJson(new ObjectMapper().writeValueAsString(custDataMap));
				custData.setFormIdentifier(formIdentifier);
				custData.setFormName("OCDLEGLO");
				custData.setModifyUserId(userName);
				isSaved = ocmpconfService.submitFormData(custData);
			}
			if(nCustData.getFormInfoJson() != null) {
				Map<String, Object> nCustDataMap = new ObjectMapper().readValue(nCustData.getFormInfoJson(), HashMap.class);
				setCalculatedDates(keyDatesMap, nCustDataMap);
				nCustData.setFormInfoJson(new ObjectMapper().writeValueAsString(nCustDataMap));
				nCustData.setFormIdentifier(formIdentifierNcust);
				nCustData.setFormName("OCDLEGLO");
				nCustData.setModifyUserId(userName);
				isSaved = ocmpconfService.submitFormData(nCustData);
			}
			if(parData.getFormInfoJson() != null) {
				Map<String, Object> parDataMap = new ObjectMapper().readValue(parData.getFormInfoJson(), HashMap.class);
				setCalculatedDates(keyDatesMap, parDataMap);
				parData.setFormInfoJson(new ObjectMapper().writeValueAsString(parDataMap));
				parData.setFormIdentifier(formIdentifierPar);
				parData.setFormName("OCDLEGLO");
				parData.setModifyUserId(userName);
				isSaved = ocmpconfService.submitFormData(parData);
			}
			if(isSaved == 1 && initialKeyDates!= null && keyDatesMap!=null && !initialKeyDates.equals(keyDatesMap)) {
				// If Order status updated in set calculated dates  inserting into it history table
				try {
					keyDatesData.setFormInfoJson(new ObjectMapper().writeValueAsString(keyDatesMap));
					ocmpconfService.insOcdleglsHtyStatus(keyDatesData);
				} catch (Exception e) {
					logger.error("Insert into Hty",e);
				}
			}
		} catch (Exception e) {
			logger.error("Error in updateCumKeyDates method " + e);
			e.printStackTrace();
		}
		return isSaved;
	}

	private void setCalculatedDates(Map<String, Object> keyDatesMap, Map<String, Object> ordersMap) {
		logger.error("setCalculatedDates for ocdleglo == {}" ,ordersMap);
		((List<Map<String, Object>>) ordersMap.get("myJsonRowData")).forEach(row -> {
			if(keyDatesMap.containsKey(ApplicationConstants.SENTENCE_DATES)) {
				String statusUpdflag = ocmpconfService.getAutomaticStatusFlag("EAOSU");
				List<Map<String, Object>> sentenceDates = (List<Map<String, Object>>) keyDatesMap.get(ApplicationConstants.SENTENCE_DATES);
				sentenceDates.forEach(sentObj->{
					if( row.get("displayNo").equals(sentObj.get("displayNo"))) {
						row.put("commenceDate",sentObj.get(ApplicationConstants.SENTENCE_COMMENCE_DATE));
						if( row.get("orderType").equals("NCUS") || row.get("orderType").equals("PAR")) {
							if(sentObj.containsKey(ApplicationConstants.SENTENCE_EXPIRY_DATE)) {
								Map<String,Object> sentExpObj = new HashMap<String,Object>();
								sentExpObj = (Map<String, Object>) sentObj.get(ApplicationConstants.SENTENCE_EXPIRY_DATE);
								if(sentExpObj.containsKey(ApplicationConstants.INDEFINITE.toLowerCase()) && sentExpObj.get(ApplicationConstants.INDEFINITE.toLowerCase()).equals(true)) {
									row.put(ApplicationConstants.EXPIRY_DATE,ApplicationConstants.INDEFINITE);
								} else if (sentExpObj.containsKey(ApplicationConstants.VALUE)) {
									row.put(ApplicationConstants.EXPIRY_DATE,sentExpObj.get(ApplicationConstants.VALUE));
								}
								if(ApplicationConstants.YES_FLAG.equalsIgnoreCase(statusUpdflag) && (row.get(ApplicationConstants.STATUS).toString().equalsIgnoreCase(ApplicationConstants.ACT)
														|| row.get(ApplicationConstants.STATUS).toString().equalsIgnoreCase(ApplicationConstants.PEND) || row.get(ApplicationConstants.STATUS).toString().equalsIgnoreCase(ApplicationConstants.EXP))) {
									LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
									try {
										Date currTimeStamp = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT).parse(currentDate.toString());
										if(!row.get(ApplicationConstants.EXPIRY_DATE).toString().equals(ApplicationConstants.INDEFINITE) && !row.get(ApplicationConstants.EXPIRY_DATE).toString().trim().equals("")) {
											Date expiryDate = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT).parse(row.get(ApplicationConstants.EXPIRY_DATE).toString());
											if(expiryDate.compareTo(currTimeStamp)<0) {
												if(!row.get(ApplicationConstants.STATUS).toString().equalsIgnoreCase(ApplicationConstants.EXP)) {
													row.put(ApplicationConstants.STATUS, ApplicationConstants.EXP);
													sentObj.put(ApplicationConstants.STATUS, ApplicationConstants.EXP);
												}
												return;
											}
										}
										if(sentObj.containsKey(ApplicationConstants.SENTENCE_COMMENCE_DATE) && !sentObj.get(ApplicationConstants.SENTENCE_COMMENCE_DATE).toString().equals(ApplicationConstants.INDEFINITE)) {
											Date sentCommenceDate = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT).parse(sentObj.get(ApplicationConstants.SENTENCE_COMMENCE_DATE).toString());
											if(sentCommenceDate.compareTo(currTimeStamp)>0 && !row.get(ApplicationConstants.STATUS).toString().equalsIgnoreCase(ApplicationConstants.PEND)) {
												row.put(ApplicationConstants.STATUS, ApplicationConstants.PEND);
												sentObj.put(ApplicationConstants.STATUS, ApplicationConstants.PEND);
											} else if(!(sentCommenceDate.compareTo(currTimeStamp)>0) && !row.get(ApplicationConstants.STATUS).toString().equalsIgnoreCase(ApplicationConstants.ACT)) {
												row.put(ApplicationConstants.STATUS, ApplicationConstants.ACT);
												sentObj.put(ApplicationConstants.STATUS, ApplicationConstants.ACT);
											}
										}
									} catch (Exception e) {
										logger.error("In setCalculatedDates in NCUS:", e);
									}
									}
							}
						}
					}
					try {
						if( ApplicationConstants.YES_FLAG.equalsIgnoreCase(statusUpdflag)  && sentObj.containsKey(ApplicationConstants.SENTENCE_ORDER_DATES)) {
							List<Map<String, Object>> sentence_order_dates = (List<Map<String, Object>>) sentObj.get(ApplicationConstants.SENTENCE_ORDER_DATES);
							sentence_order_dates.forEach(sentOrdDate -> {
								if(row.get(ApplicationConstants.ORDERTYPE).toString().equalsIgnoreCase(ApplicationConstants.CUSTORDER) && row.get(ApplicationConstants.DISPLAY_NO).equals(sentObj.get(ApplicationConstants.DISPLAY_NO)) && !sentObj.get(ApplicationConstants.SENTENCETYPE).equals("CHLD")) {
									if((row.get(ApplicationConstants.STATUS).toString().equalsIgnoreCase(ApplicationConstants.ACT)
														|| row.get(ApplicationConstants.STATUS).toString().equalsIgnoreCase(ApplicationConstants.PEND) || row.get(ApplicationConstants.STATUS).toString().equalsIgnoreCase("SERV"))) {
									try {
										LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
										Date currTimeStamp = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT).parse(currentDate.toString());
											if(sentOrdDate.containsKey(ApplicationConstants.DATE_TYPE) && sentOrdDate.get(ApplicationConstants.DATE_TYPE).toString().equalsIgnoreCase("lrd") &&  sentOrdDate.containsKey(ApplicationConstants.EFFECTIVE_VALUE)) {
												Date sentLrd = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT).parse(sentOrdDate.get(ApplicationConstants.EFFECTIVE_VALUE).toString());
												if(sentLrd.compareTo(currTimeStamp)<0 && !row.get(ApplicationConstants.STATUS).toString().equalsIgnoreCase("SERV")) {
													row.put(ApplicationConstants.STATUS, "SERV");
													sentObj.put(ApplicationConstants.STATUS, "SERV");
												} else if(!(sentLrd.compareTo(currTimeStamp)<0) && row.containsKey("commenceDate") && !row.get("commenceDate").toString().equals(ApplicationConstants.INDEFINITE)) {
													Date sentCommenceDate = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT).parse(row.get("commenceDate").toString());
													if(sentCommenceDate.compareTo(currTimeStamp)>0 && !row.get(ApplicationConstants.STATUS).toString().equalsIgnoreCase(ApplicationConstants.PEND)) {
														row.put(ApplicationConstants.STATUS, ApplicationConstants.PEND);
														sentObj.put(ApplicationConstants.STATUS, ApplicationConstants.PEND);
													} else if(!(sentCommenceDate.compareTo(currTimeStamp)>0) && !row.get(ApplicationConstants.STATUS).toString().equalsIgnoreCase(ApplicationConstants.ACT)) {
														row.put(ApplicationConstants.STATUS, ApplicationConstants.ACT);
														sentObj.put(ApplicationConstants.STATUS, ApplicationConstants.ACT);
													}
												}
											}
									} catch (Exception e) {
										logger.error("In setCalculatedDates in Cust:", e);
									}
								}
								}
							});

						}
					} catch (Exception e) {
						logger.error("In setCalculatedDates in Cust:", e);
					}
				});
			}
		});
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "ocmpconf/populateCatSentType", method = RequestMethod.GET)
	public List<Map<String,Object>> populateCatSentType(@RequestParam String sentCategory) {
		List<Map<String,Object>> respList = new ArrayList<>();
		String [] sentCatList = sentCategory.split("_");
		List<String> sentCategoryList = Arrays.asList(sentCatList);
		for(String sentCat : sentCategoryList) {
			Map<String, Object> obj = new HashMap<>();
			SentenceCalcTypes sentenceCalcTypes= new SentenceCalcTypes();
			sentenceCalcTypes.setSentenceCategory(sentCat);
			List<SentenceCalcTypes> sentenceCalcTypeList = new ArrayList<>();
			try {
				sentenceCalcTypeList = oimsreqsService.senCalcExecuteQuery(sentenceCalcTypes);
				for (SentenceCalcTypes sentType : sentenceCalcTypeList) {
					obj = new HashMap<>();
					obj.put("code", sentType.getSentenceCalcType());
					obj.put("description", sentType.getDescription());
					obj.put("sentType", sentType.getSentenceType());
					obj.put("chargesFlag", sentType.getChargesFlag());
					obj.put("canDisplay", ApplicationConstants.YES.equalsIgnoreCase(sentType.getActiveFlag())? true :false);
					SentenceTerms sentenceTerms = new SentenceTerms();
					sentenceTerms.setSentenceCategory(sentCat);
					sentenceTerms.setSentenceCalcType(sentType.getSentenceCalcType());
					List<SentenceTerms> sentenceTermsList = oimsreqsService.senTermsExecuteQuery(sentenceTerms);
					obj.put("termList", sentenceTermsList);
					
					respList.add(obj);
				}
			} catch (Exception e) {
				logger.error("In populateSentType:", e);
			}
		}
		return respList;
	}
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmpconf/setLatestOutcome", method = RequestMethod.POST)
	public Integer setLatestOutcome(@RequestBody final String formIdentifier) {
		final OdynfrmSubmitDataBean chgSummaryBean = new OdynfrmSubmitDataBean();
		final OdynfrmSubmitDataBean chgHistBean = new OdynfrmSubmitDataBean();
		chgSummaryBean.setSearchString(formIdentifier);
		chgHistBean.setSearchString(formIdentifier);
		chgSummaryBean.setFormName("OCDCHGSU");
		chgHistBean.setFormName("OCUCHGOU");
		OdynfrmSubmitDataBean chgSummaryData = null;
		OdynfrmSubmitDataBean chgHistData = null;
		Integer returnData = 0;
		try {
			chgSummaryData = ocmpconfService.getFormData(chgSummaryBean);
			chgHistData = ocmpconfService.getFormData(chgHistBean);
			logger.info("LatestOutcome In chgHistData {} :", chgHistData);
			if(chgHistData!= null && chgHistData.getFormInfoJson() != null) {
				List<Map<String,Object>> chgHistDataJson = new ObjectMapper().readValue(chgHistData.getFormInfoJson(), ArrayList.class);
				Map<String,String> chgOutMap = new HashMap<>();
				logger.info("LatestOutcome In chgHistDataJson {} :", chgHistDataJson);
				chgHistDataJson.forEach(histObj -> {
					logger.info("LatestOutcome In histObj Start {} :", histObj);
					String chargeId = String.valueOf(histObj.get(ApplicationConstants.OCUCHGOU_CHARGE_ID_KEY));
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
				    Collections.sort((List<Map<String,String>>)histObj.get(ApplicationConstants.OCUCHGOU_CHARGE_HIST_KEY), (s1, s2) -> LocalDateTime.parse(s1.get("updatedDate"), formatter).
				            compareTo(LocalDateTime.parse(s2.get("updatedDate"), formatter)));
				    if(!((List<Map<String,String>>)histObj.get(ApplicationConstants.OCUCHGOU_CHARGE_HIST_KEY)).isEmpty()) {
				    	chgOutMap.put(String.valueOf(chargeId), ((List<Map<String,String>>)histObj.get(ApplicationConstants.OCUCHGOU_CHARGE_HIST_KEY)).get(((List<Map<String,String>>)histObj.get(ApplicationConstants.OCUCHGOU_CHARGE_HIST_KEY)).size()-1).get("outcome"));
				    }
				});
				logger.info("LatestOutcome In chgOutMap {} :", chgOutMap);
				List<Map<String,String>> chgSummaryJson = new ObjectMapper().readValue(chgSummaryData.getFormInfoJson(), ArrayList.class);
				chgSummaryJson.forEach(chg -> {
					chg.put("outcome", chgOutMap.get(String.valueOf(chg.get(ApplicationConstants.OCUCHGOU_CHARGE_ID_KEY))));
				});
				logger.info("LatestOutcome In chgSummaryJson {} :", chgSummaryJson);
				String formJson = String.valueOf(new ObjectMapper().writeValueAsString(chgSummaryJson));
				chgSummaryData.setFormInfoJson(formJson);
				chgSummaryData.setFormName("OCDCHGSU");
				logger.info("LatestOutcome In chgSummaryData {} :", chgSummaryData);
				returnData = ocmpconfService.submitFormData(chgSummaryData);
			}
		} catch (Exception e) {
			logger.error("LatestOutcome In setLatestOutcome:", e.getMessage());
		}
		return returnData;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmpconf/verification", method = RequestMethod.POST)
	public Integer verification(@RequestBody final OdynfrmSubmitDataBean odynfrmSubmitDataBean, @RequestHeader HttpHeaders headers) {
		int result = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		odynfrmSubmitDataBean.setCreateUserId(userName);
		try {
			result = ocmpconfService.verification(odynfrmSubmitDataBean); 
			if(result == 1) {
				prosmainService.enableTriggers(odynfrmSubmitDataBean, authorization, "104");
			}
		} catch (Exception e) {
			logger.error("In commitformData:", e);
		}
		return result;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "ocmpconf/getOcdleglsData", method = RequestMethod.POST)
	public List<OdynfrmSubmitDataBean> getOcdleglsData(@RequestBody String queryData) {
		Map<String, Object> inputData = new HashMap<>();
		List<OdynfrmSubmitDataBean> returnList = new ArrayList<>();
		int isSaved = 0;
		OdynfrmSubmitDataBean keyDatesData = new OdynfrmSubmitDataBean();
		if(queryData.contains("formInfoJson")) {
			queryData = queryData.replace("\"[", "[").replace("\"{", "{").replace("}\"", "}").replace("]\"", "]");
		}
		try {
			inputData = new ObjectMapper().readValue(queryData, HashMap.class);
			keyDatesData.setSearchString("\"offenderBookId\":\""+((Map)inputData.get("formIdentifier")).get("offenderBookId")+"\"");
			keyDatesData.setFormName("OCDLEGLS");
			keyDatesData = getFormData(keyDatesData);
			if(keyDatesData.getId() != null) {
				returnList.add(keyDatesData);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
		return returnList;
	}
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmpconf/getStaffRoles", method = RequestMethod.GET)
	public List<StaffMemberRoles> getStaffRoles() {
		
		List<StaffMemberRoles> recordList = new ArrayList<StaffMemberRoles>();
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		try {
			recordList = ocmpconfService.getStaffRoles(authentication.getName());
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmpconf/getOcdleglsHytData", method = RequestMethod.POST)
	public List<OdynfrmSubmitDataBean> getOcdleglsHytData(@RequestBody final OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		List<OdynfrmSubmitDataBean> obj = new ArrayList<OdynfrmSubmitDataBean>();
		try {
			obj = ocmpconfService.getOcdleglsHytData(odynfrmSubmitDataBean); 
		} catch (Exception e) {
			logger.error("In getOcdleglsHytData:", e);
		}
		return obj;
	}
	
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmpconf/rgConditionCategory", method = RequestMethod.GET)
	public @ResponseBody List<ReferenceCodes> rgConditionCategory(String orderType) {
		List<ReferenceCodes> listOfRecords = new ArrayList<>();
		try {
			listOfRecords = ocmpconfService.rgConditionCategory(orderType);
		} catch (Exception e) {
			logger.error("rgConditionCategory: ", e);
		}
		return listOfRecords;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmpconf/rgOrderStatus", method = RequestMethod.GET)
	public @ResponseBody List<LegalUpdateReasons> rgOrderStatus(String orderType) {
		List<LegalUpdateReasons> listOfRecords = new ArrayList<>();
		try {
			listOfRecords = ocmpconfService.rgOrderStatus(orderType);
		} catch (Exception e) {
			logger.error("rgOrderStatus: ", e);
		}
		return listOfRecords;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmpconf/revokeParOrder", method = RequestMethod.POST)
	public OdynfrmSubmitDataBean revokeParOrder(@RequestBody Map<String, Object> odynfrmSubmitDataBean) {
		OdynfrmSubmitDataBean obj = new  OdynfrmSubmitDataBean();
		try {
			obj = ocmpconfService.revokeParOrder(odynfrmSubmitDataBean,false); 
		} catch (Exception e) {
			logger.error("In revokeParOrder:", e);
		}
		return obj;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmpconf/getAutomaticUpdFlag", method = RequestMethod.GET)
	public @ResponseBody String getAutomaticUpdFlag(@RequestParam String autoUpdateCode) {
		String statusFlag = null;
		try {
			statusFlag = ocmpconfService.getAutomaticStatusFlag(autoUpdateCode);
		} catch (Exception e) {
			logger.error("getAutomaticUpdFlag: ", e);
		}
		return statusFlag;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmpconf/deleteOrderFlag", method = RequestMethod.GET)
	public @ResponseBody String deleteOrderFlag(@RequestParam String code) {
		String statusFlag = null;
		try {
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			statusFlag = ocmpconfService.deleteOrderFlag(code,authentication.getName());
		} catch (Exception e) {
			logger.error("deleteOrderFlag: ", e);
		}
		return statusFlag;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmpconf/offenderCourtEvents", method = RequestMethod.POST)
	public List<CourtEvents> offenderCourtEvents(@RequestBody final CourtEvents searchBean) {
		List<CourtEvents> searchResult = new ArrayList<>();
		try {
			searchResult = ocmpconfService.crtEveExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offenderCourtEvents: ", e);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmpconf/deleteParoleEvents", method = RequestMethod.POST)
	public Integer deleteParoleEvents(@RequestBody final List<OffenderPayrolEvent> paroleEvents) {
		Integer returnData = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		paroleEvents.forEach(pe -> {pe.setModifyUserId(userName);});
		try {
			returnData = ocmpconfService.deleteParoleEvents(paroleEvents); 
		} catch (Exception e) {
			logger.error("In deleteParoleEvents:", e);
		}
		return returnData;
	}
	
	
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmpconf/getOffenderOrders", method = RequestMethod.POST)
	public List<OdynfrmSubmitDataBean> getOffenderOrders(@RequestBody OdynfrmSubmitDataBean searchBean) {
		List<OdynfrmSubmitDataBean> returnList = new ArrayList<OdynfrmSubmitDataBean>();
		try {
			Map<String, Object> inputData = new HashMap<>();
			inputData =  new ObjectMapper().readValue(searchBean.getSearchString(), new TypeReference<HashMap<String,Object>>(){});
			String formIdentifier = "{\"offenderBookId\":\""+inputData.get("offenderBookId");
			searchBean.setSearchString(formIdentifier);
			returnList = ocmpconfService.getOffenderOrders(searchBean); 
		} catch (Exception e) {
			logger.error("In getOffenderOrders:", e);
		}
		return returnList;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmpconf/checkOrderDependency", method = RequestMethod.POST)
	public Boolean  checkOrderDependency(@RequestBody Map<String,Object> inputObj) {
		try {
			String displayNo =  inputObj.containsKey(ApplicationConstants.DISPLAY_NO)?inputObj.get(ApplicationConstants.DISPLAY_NO).toString():null;
			String screenId =  inputObj.containsKey("formName")?inputObj.get("formName").toString():null;
			Integer offenderBookId = inputObj.containsKey("offenderBookId")? Integer.parseInt( inputObj.get("offenderBookId").toString()):null;
			return ocmpconfService.checkOrderDependency(offenderBookId,screenId,displayNo);
		} catch (Exception e) {
			logger.error("checkOrderDependency inputObj: {}",inputObj );
		}
		return false;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmpconf/senTermsExecuteQuery", method = RequestMethod.POST)
	public List<SentenceTerms> senTermsExecuteQuery(@RequestBody final SentenceTerms searchBean) {
		List<SentenceTerms> searchResult = new ArrayList<>();
		try {
			searchResult = oimsreqsService.senTermsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmpconf/getERDHideShowValue", method = RequestMethod.GET)
	public String getPersutHideShowValue(@RequestParam("code") final String code) {
		String returnValue=null;
		try {
			returnValue = ocdenforService.getPersutHideShowValue(code);
		} catch (Exception e) {
			logger.error("Exception : getERDHideShowValue:", e);
			
		}
		return returnValue;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmpconf/getCommenceType", method = RequestMethod.GET)
	public @ResponseBody List<ReferenceCodes> getCommenceType(String orderType) {
		List<ReferenceCodes> listOfRecords = new ArrayList<>();
		try {
			listOfRecords = ocmpconfService.getCommenceType(orderType);
		} catch (Exception e) {
			logger.error("getCommenceType: {}  ", e.getMessage());
		}
		return listOfRecords;
	}
}

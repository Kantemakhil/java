package net.syscon.s4.globalconfiguration.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import net.syscon.s4.cm.courtcasesandorders.maintenance.OimsreqsService;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.FormBuilderBean;
import net.syscon.s4.common.beans.OcmpconfUiBean;
import net.syscon.s4.common.beans.OdynfrmCommitBean;
import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SentenceCalcTypes;
import net.syscon.s4.common.beans.SentenceTerms;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalconfiguration.OcmpconfRepository;
import net.syscon.s4.globalconfiguration.OcmpconfService;
import net.syscon.s4.globalconfiguration.OumsysetService;
import net.syscon.s4.im.beans.IwpDocuments;
import net.syscon.s4.im.beans.LegalUpdateUsages;
import net.syscon.s4.inst.legals.OcipenscService;
import net.syscon.s4.inst.legals.OidpaoeService;
import net.syscon.s4.inst.legals.beans.OffenderPayrolEvent;
import net.syscon.s4.inst.legals.beans.OffenderSentenceAdjustment;
import net.syscon.s4.inst.legalscreens.maintenance.OidcustadService;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasons;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OumsypflServiceImpl
 */
@Service
public class OcmpconfServiceImpl extends BaseBusiness implements OcmpconfService {

	@Autowired
	private OcmpconfRepository ocmpconfRepository;
	@Autowired
	private EliteDateService eliteDateService;
	@Autowired
	private OumsysetService oumsysetService;
	@Autowired
	private OidcustadService oidcustadService;
	@Autowired
	private OidpaoeService oidpaoeService;
	@Autowired
	private OimsreqsService oimsreqsService;
	@Autowired
	private OcipenscService ocipenscService;
	@Autowired
	private ProsmainService prosmainService;
	
	private static Logger logger = LogManager.getLogger(OcmpconfServiceImpl.class.getName());

	/**
	 * Creates new OumsypflServiceImpl class Object
	 */
	public OcmpconfServiceImpl() {
		// OumsypflServiceImpl
	}

	@Override
	public List<OcmpconfUiBean> loadData() {
		// TODO Auto-generated method stub
		return ocmpconfRepository.loadData();
	}

	@Override
	public Integer saveData(List<OcmpconfUiBean> compData) {
		// TODO Auto-generated method stub
		return ocmpconfRepository.saveData(compData);
	}

	@Override
	public Integer saveFormbuilderData(FormBuilderBean formBean) {
		if(formBean.getFormJson() == null || "".equals(formBean.getFormJson())) {
			formBean.setFormJson("{}");
		}
		return ocmpconfRepository.saveFormbuilderData(formBean);
	}

	@Override
	public List<FormBuilderBean> loadFormbuilderData() {
		return ocmpconfRepository.loadFormbuilderData();
	}

	@Transactional
	public Integer commitformData(OdynfrmCommitBean odynfrmCommitBean) {
		int liReturn = 0;
		if (odynfrmCommitBean.getInsertList() != null && odynfrmCommitBean.getInsertList().size() > 0) {
			odynfrmCommitBean.getInsertList().forEach(formBean -> {
				if(formBean.getFormJson() == null || "".equals(formBean.getFormJson())) {
					formBean.setFormJson("{}");
				}
				formBean.setCreateUserId(odynfrmCommitBean.getCreateUserId());
			});
			liReturn = ocmpconfRepository.insertOdynFrmData(odynfrmCommitBean.getInsertList());
			if(liReturn > 0) {
				for(FormBuilderBean formBuilderBean : odynfrmCommitBean.getInsertList()) {
					ocmpconfRepository.createTable(formBuilderBean.getFormName());
					ocmpconfRepository.createModule(formBuilderBean);
				}
			}
		}
		if (odynfrmCommitBean.getUpdateList() != null && odynfrmCommitBean.getUpdateList().size() > 0) {
			odynfrmCommitBean.getUpdateList().forEach(formBean -> {formBean.setModifyUserId(odynfrmCommitBean.getCreateUserId());});
			liReturn = ocmpconfRepository.updateOdynFrmData(odynfrmCommitBean.getUpdateList());
		}
		return liReturn;
	}
	
	
	
	@Override
	public Integer updateFormbuilderData(FormBuilderBean formBean) {
		List<FormBuilderBean> formUpdateList = new ArrayList<>();
		formUpdateList.add(formBean);
		Integer liReturn = ocmpconfRepository.updateOdynFrmData(formUpdateList);
		return liReturn;
	}

	@Override
	public OdynfrmSubmitDataBean getFormData(OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		String status= "";
		OdynfrmSubmitDataBean result = ocmpconfRepository.getFormData(odynfrmSubmitDataBean);
		if("ocdlegls".equals(odynfrmSubmitDataBean.getFormName().toLowerCase())) {
			try {
			Long offenderBookId = Long.parseLong(odynfrmSubmitDataBean.getSearchString().substring(17).replace("\"", "").trim());
			String custodyStatus = ocmpconfRepository.getCustodyStatus(offenderBookId);
			if(custodyStatus!=null && !"".equals(custodyStatus)) {  
				if(custodyStatus.contains("-")) {
					String[] statusList = custodyStatus.split("-");
					for (int i = 0, size = statusList.length; i < size; i++) {
						String statusDesc = ocmpconfRepository.getStatusDesc(statusList[i].trim());
						if(i == 0) {
							status = statusDesc; 
						} else  {
							status = status + " - " + statusDesc;
						}
					}
				} else {
					String statusDesc = ocmpconfRepository.getStatusDesc(custodyStatus);
					status = statusDesc;
				}
			}
			} catch (Exception e) {
				logger.error("In getFormData:: In Custody status "+ e);
			}
			result.setCustodyStatus(status);
		}
		return result;
	}

	@Override
	public Integer submitFormData(OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		if(odynfrmSubmitDataBean.getId()!= null && odynfrmSubmitDataBean.getId()!= 0 ) {
			if(odynfrmSubmitDataBean.getFormInfoJson()!= null && odynfrmSubmitDataBean.getFormName().equalsIgnoreCase("ocdleglo")) {
				try {
					Map<String,List<Map<String,Object>>> myJsonRowData = new ObjectMapper().readValue(odynfrmSubmitDataBean.getFormInfoJson(), HashMap.class);;
					List<Map<String,Object>> orders = myJsonRowData.get("myJsonRowData");
					if(orders.isEmpty()) {
						return ocmpconfRepository.deleteSubmitFormData(odynfrmSubmitDataBean);
					}
				} catch (Exception e) {
					logger.error("submitFormData Delete Orders", e);
				}
			}
			return ocmpconfRepository.updateSubmitFormData(odynfrmSubmitDataBean);
		}
		if("OCDLEGLS".equalsIgnoreCase(odynfrmSubmitDataBean.getFormName())) {
			odynfrmSubmitDataBean.setId(ocmpconfRepository.getOcdleglsSequenceID());
		}else {
			odynfrmSubmitDataBean.setId(ocmpconfRepository.getFormSeqID(odynfrmSubmitDataBean));
		}
		return ocmpconfRepository.submitFormData(odynfrmSubmitDataBean);
	}
	
	@Override
	public List<Map<String, Object>> getFormLovData() {
		return ocmpconfRepository.getFormLovData();
	}

	@Override
	public Map<String, Object> getFormData(String formId) throws JsonParseException, IOException {
		Map<String, Object> formObject = new HashMap<>();
		formObject = ocmpconfRepository.getFormData(formId);
		
		ObjectMapper mapper = new ObjectMapper();
    	JsonFactory factory = mapper.getFactory();
    	JsonParser parser = factory.createParser((String) formObject.get("components"));
	    JsonNode actualObj = mapper.readTree(parser);
		
		formObject.put("components", actualObj.get("components"));
		return formObject;
	}

	@Override
	public List<FormBuilderBean> getFormDataBasedOnModName(String formModuleName) {
		return ocmpconfRepository.getFormDataBasedOnModName(formModuleName);
	}
	
	@Override
	public List<Map<String, Object>> dynamicGridConfig() {
		List<Map<String, Object>> dynamicGridData = new ArrayList<Map<String,Object>>();
		dynamicGridData = ocmpconfRepository.dynamicGridConfig();
		for(Map<String, Object> dynamicGrid: dynamicGridData) {
			byte[] bdata  = (byte[]) dynamicGrid.get("config_json");
			String strConfgData = new String(bdata);
			dynamicGrid.put("configData", strConfgData);
		}
		return dynamicGridData;
	}

	@Override
	public Integer verification(OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		int updateAction = 0;
		int insertHyt = 0;
		updateAction = ocmpconfRepository.updateActionType(odynfrmSubmitDataBean);
		if(updateAction == 1) {
			insertHyt = ocmpconfRepository.insertOcdLeglsHyt(odynfrmSubmitDataBean);
		}
		return insertHyt;
	}

	@Override
	public List<StaffMemberRoles> getStaffRoles(String userId) {
		List<StaffMemberRoles> getStaffRoles = new ArrayList<StaffMemberRoles>();
		getStaffRoles = ocmpconfRepository.getStaffRoles(userId);
		return getStaffRoles;
	}

	@Override
	public List<OdynfrmSubmitDataBean> getOcdleglsHytData(OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		return ocmpconfRepository.getOcdleglsHytData(odynfrmSubmitDataBean);
	}

	@Override
	public List<ReferenceCodes> rgConditionCategory(String orderType) {
		return ocmpconfRepository.rgConditionCategory(orderType);
	}

	@Override
	public List<LegalUpdateReasons> rgOrderStatus(String orderType) {
		List<LegalUpdateReasons> returnList = Collections.checkedList(new ArrayList<LegalUpdateReasons>(), LegalUpdateReasons.class);
		returnList =  ocmpconfRepository.rgOrderStatus(orderType);
		if(orderType != null && !orderType.trim().equals("")) {
			List<LegalUpdateUsages> statusUsageList = ocmpconfRepository.legalUpdateUsages(orderType);
			returnList.forEach(ele -> {
				List<LegalUpdateUsages> orderTypeUsage = statusUsageList.stream().filter(i->(i.getUpdateReasonCode().equalsIgnoreCase(ele.getUpdateReasonCode())) && (i.getLegalClass().equalsIgnoreCase(orderType)) && (i.getActiveFlag().equalsIgnoreCase(ApplicationConstants.YFLAG))).collect(Collectors.toList());
				if(orderTypeUsage.isEmpty() ||(ele.getActiveFlag() != null && !ele.getActiveFlag().equalsIgnoreCase(ApplicationConstants.YFLAG))) {
					ele.setCanDisplay(false);
				}
			});
		} else {
			returnList.forEach(ele -> {
				if(ele.getActiveFlag() != null && !ele.getActiveFlag().equalsIgnoreCase(ApplicationConstants.YFLAG)) {
					ele.setCanDisplay(false);
				}
			});
		}
		return returnList;
	}
	
	@Override
	public void insertOcdLeglsHyt(OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		 ocmpconfRepository.insertOcdLeglsHyt(odynfrmSubmitDataBean);
	}

	@Override
	public OdynfrmSubmitDataBean revokeParOrder(Map<String, Object> odynfrmSubmitDataBean, boolean timerFlag) {
		String statusflag = ocmpconfRepository.getStatusFlag("EASUIOLPO");
		logger.info("revokeParOrder :statusflag ::" + statusflag );
		if(statusflag.equalsIgnoreCase(ApplicationConstants.YES_FLAG)) {
			logger.info("revokeParOrder :updated parole orders" + odynfrmSubmitDataBean );
			try {
				List<Map<String, Object>> updatedOrders =  new ObjectMapper().readValue(odynfrmSubmitDataBean.get("updatedOrders").toString(),  new TypeReference<ArrayList<Map<String, Object>>>(){});
				OdynfrmSubmitDataBean custData = new OdynfrmSubmitDataBean();
				Map<String, Object> inputData = new HashMap<>();
				inputData = new ObjectMapper().readValue(odynfrmSubmitDataBean.get("formIdentifier").toString(),  new TypeReference<HashMap<String,Object>>(){});
				String formIdentifier = "{\"offenderBookId\":\""+inputData.get("offenderBookId")+"\",\"orderType\":\"CUST\"}";
				custData.setSearchString(formIdentifier);
				custData.setFormName("OCDLEGLO");
				custData = ocmpconfRepository.getFormData(custData);
				custData.setFormInfoJson(new String(custData.getFormInfoJsonBlob()));
				Map<String,List<Map<String,Object>>> custFormInfoJson = new ObjectMapper().readValue(custData.getFormInfoJson(),   new TypeReference<HashMap<String,List<Map<String,Object>>>>(){});
				boolean updateFlag = false;
				for (Map<String, Object> custOrder : custFormInfoJson.get("myJsonRowData")) {
					String resultingStatus = custOrder.containsKey(ApplicationConstants.STATUS)? ocmpconfRepository.getResultingStatus(custOrder.get(ApplicationConstants.STATUS).toString()): "";
					logger.info("revokeParOrder :custFormInfoJson" + custOrder );
					for (Map<String, Object> parOrd : updatedOrders) {
						List<String> affectedOrders = (List<String>) parOrd.get("affectedOrders");
						for (String affectedOrder : affectedOrders) {
							if (custOrder.get(ApplicationConstants.DISPLAY_NO).toString().equalsIgnoreCase(affectedOrder)) {
								if (resultingStatus.equalsIgnoreCase(ApplicationConstants.A)) {
									if ("REV".equals(parOrd.get(ApplicationConstants.STATUS))) {
										updateFlag = true;
										custOrder.put(ApplicationConstants.STATUS, ApplicationConstants.ACT);
									} else if(ApplicationConstants.ACT.equals(parOrd.get(ApplicationConstants.STATUS))) {
										updateFlag =true;
										custOrder.put(ApplicationConstants.STATUS,ApplicationConstants.PAR_STATUS);
									}
								}
							}
						}
					}
					logger.info("revokeParOrder :affectedOrders iterated Successfully");
					if(odynfrmSubmitDataBean.containsKey("selectedOrders")) {
						List<String> selectedOrders =  new ObjectMapper().readValue(odynfrmSubmitDataBean.get("selectedOrders").toString(), new TypeReference<ArrayList<String>>(){});
						for(String custNo: selectedOrders) {
							if(custNo.equalsIgnoreCase(custOrder.get(ApplicationConstants.DISPLAY_NO).toString()) && resultingStatus.equalsIgnoreCase(ApplicationConstants.A)) {
								custOrder.put(ApplicationConstants.STATUS, ApplicationConstants.PAR_STATUS);
								updateFlag =true;
							}
						}
						logger.info("revokeParOrder :selectedOrders iterated Successfully");
					}
					if(odynfrmSubmitDataBean.containsKey("selectedOrders")) {
						List<Map<String,Object>> unSelectedOrder = new ObjectMapper().readValue(odynfrmSubmitDataBean.get("unselectedOrders").toString(), new TypeReference<ArrayList<Map<String,Object>>>(){});
						for(Map<String,Object> custNo: unSelectedOrder) {
							if(custNo.get(ApplicationConstants.STATUS).toString().equalsIgnoreCase(ApplicationConstants.PAR_STATUS) && custNo.get(ApplicationConstants.DISPLAY_NO).toString().equalsIgnoreCase(custOrder.get(ApplicationConstants.DISPLAY_NO).toString())) {
								try {
									LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
									Date currTimeStamp = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT).parse(currentDate.toString());
									Date sentLrd = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT).parse(custNo.get("lrd").toString());
									if(sentLrd.compareTo(currTimeStamp)<0) {
										custOrder.put(ApplicationConstants.STATUS, "SERV");
										updateFlag =true;
									}else {
										custOrder.put(ApplicationConstants.STATUS, ApplicationConstants.ACT);
										updateFlag =true;
									}
								} catch (Exception e) {
									logger.info("revokeParOrder :updating cust order status based on keydates " + custData);
								}
							}
						}
						logger.info("revokeParOrder :unSelectedOrder iterated Successfully");
					}
				}
				if (updateFlag == true) {
					custData.setFormInfoJson(new ObjectMapper().writeValueAsString(custFormInfoJson));
					custData.setFormInfoJsonBlob(new ObjectMapper().writeValueAsString(custFormInfoJson).getBytes());
					List<OdynfrmSubmitDataBean> custOrderList = new ArrayList<OdynfrmSubmitDataBean>();
					custOrderList.add(custData);
					logger.info("revokeParOrder :updating cust order " + custData);
					if(!timerFlag) {
						// Inserting into ocdleglo_data if status updated from UI
						ocmpconfRepository.updateLegalsData(custOrderList);
					}
					return custData;
				}
			} catch (Exception e) {
				logger.error("In revokeParOrder:", e);
			}
		}
		return null;
	}

	@Override
	public String getAutomaticStatusFlag(String autoUpdateCode) {
		return ocmpconfRepository.getStatusFlag(autoUpdateCode);
	}

	/*
	Set Status to ocdlegls_data
	*/
	@Override
	public void setOcdleglsStatus(List<OdynfrmSubmitDataBean> ordersUpdated) {
		List<OdynfrmSubmitDataBean> toSelectedList = new ArrayList<OdynfrmSubmitDataBean>();
		logger.info(this.getClass().getName()+ ": setOcdleglsStatus :" + ordersUpdated );
		toSelectedList.addAll(ordersUpdated);
		for(OdynfrmSubmitDataBean obj : ordersUpdated) {
			try {
				logger.info(this.getClass().getName()+ ": ordersUpdated iteration :" + obj );
				Map<String,Object> inputData = new ObjectMapper().readValue(obj.getFormIdentifier(), HashMap.class);
				String formIdentifier = "{\"offenderBookId\":\""+inputData.get("offenderBookId").toString()+"\"}";
				List<OdynfrmSubmitDataBean> toUpdateOrders =new ArrayList<OdynfrmSubmitDataBean>();
				for(OdynfrmSubmitDataBean legloObj:toSelectedList) {
					Map<String,Object> selIden = new ObjectMapper().readValue(legloObj.getFormIdentifier(), HashMap.class);
					String legloFormIdentifer = "{\"offenderBookId\":\""+selIden.get("offenderBookId").toString()+"\"}";
					if(legloFormIdentifer!= null && formIdentifier!= null && formIdentifier.equalsIgnoreCase(legloFormIdentifer)) {
						toUpdateOrders.add(legloObj);
					}
				}
				logger.info(this.getClass().getName()+ ": toUpdateOrdersList :" + toUpdateOrders );
				if(!toUpdateOrders.isEmpty()) {
					toSelectedList.removeAll(toUpdateOrders);
					OdynfrmSubmitDataBean legalSummary = new OdynfrmSubmitDataBean();
					legalSummary.setSearchString(formIdentifier);
					legalSummary.setFormName("OCDLEGLS");
					legalSummary = ocmpconfRepository.getFormData(legalSummary);
					legalSummary.setFormInfoJson(new String(legalSummary.getFormInfoJsonBlob()));
					Map<String,List<Map<String,Object>>> ocdleglsFromInfo = new ObjectMapper().readValue(legalSummary.getFormInfoJson(), HashMap.class);
					logger.info(this.getClass().getName()+ ": legalSummary :" + legalSummary );
					for (OdynfrmSubmitDataBean offOrders : toUpdateOrders) {
						logger.info(this.getClass().getName()+ ": toUpdateOrders iteration :" + offOrders );
						try {
							Map<String,List<Map<String,Object>>> ocdlegloFromInfo = new ObjectMapper().readValue(offOrders.getFormInfoJson(), HashMap.class);
							List<Map<String, Object>> myJsonRowData = ocdlegloFromInfo.get("myJsonRowData");
							List<Map<String, Object>> sentenceDates =  ocdleglsFromInfo.get(ApplicationConstants.SENTENCE_DATES);
							for(Map<String, Object> summaryObj : sentenceDates) {
								if(myJsonRowData!= null && !myJsonRowData.isEmpty()) {
									List<Map<String, Object>> order = myJsonRowData.stream().filter(i-> i.get(ApplicationConstants.DISPLAY_NO).toString().equalsIgnoreCase(summaryObj.get(ApplicationConstants.DISPLAY_NO).toString())).collect(Collectors.toList());
									if(!order.isEmpty()) {
										summaryObj.put("status", order.get(0).get("status"));
									}
								}
							}
							ocdleglsFromInfo.put(ApplicationConstants.SENTENCE_DATES, sentenceDates);
						} catch (Exception e) {
							logger.error("In setOcdleglsStatus: toUpdateOrders iteration", e);
						}
					}
					legalSummary.setFormInfoJson(new ObjectMapper().writeValueAsString(ocdleglsFromInfo));
					insOcdleglsHtyStatus(legalSummary);
				}
			} catch (Exception e) {
				logger.error("In setOcdleglsStatus: ordersUpdated iteration", e);
			}
		}
	}

	/*
	 Inserting into OCDLEGLS_DATA_HTY and OCDLEGLS_DATA
	 */
	@Override
	public void insOcdleglsHtyStatus(OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		logger.info(this.getClass().getName()+ ": insert into legal summary history :" + odynfrmSubmitDataBean );
		try {
			Map<String,Object>  calcReason = generateCalcReason();
			String automationUser = "";
			List<Map<String,Object>> returnList=oumsysetService.getSysData("serverConfig","AUTOMATION_USER");
			if(returnList!=null && !returnList.isEmpty()) {
				for(Map<String,Object> object:returnList) {
					if(object.containsKey("KEY_CODE") && object.get("KEY_CODE")!=null && object.get("KEY_CODE").equals("AUTOMATION_ELITE_USER") &&  object.containsKey("VALUE") && object.get("VALUE")!=null) {
						automationUser = object.get("VALUE").toString();
					}
				}
			}
			Map<String,Map<String,Object>> ocdleglsFromInfo = new ObjectMapper().readValue(odynfrmSubmitDataBean.getFormInfoJson(), HashMap.class);
			ocdleglsFromInfo.put("calcReason", calcReason);
			odynfrmSubmitDataBean.setFormInfoJson(new ObjectMapper().writeValueAsString(ocdleglsFromInfo));
			odynfrmSubmitDataBean.setActionType("Modification");
			odynfrmSubmitDataBean.setCreateUserId(automationUser);
			logger.info(this.getClass().getName()+ ": insert into legal summary history :" + odynfrmSubmitDataBean );
			ocmpconfRepository.insertOcdLeglsHyt(odynfrmSubmitDataBean);
			odynfrmSubmitDataBean.setModifyUserId(automationUser);
			odynfrmSubmitDataBean.setFormName("OCDLEGLS");
			logger.info(this.getClass().getName()+ ": insert into legal summary :" + odynfrmSubmitDataBean );
			submitFormData(odynfrmSubmitDataBean);
		} catch (Exception e) {
			logger.error("Error in insOcdleglsHtyStatus: Insert Ocdlegls Hty Calc Reason Automatically : ",e);
		}
	}

	@Override
	public String deleteOrderFlag(String code, String userId) {
		return ocmpconfRepository.deleteOrderFlag(code,userId);
	}

	@Override
	public List<CourtEvents> crtEveExecuteQuery(CourtEvents searchBean) {
		return ocmpconfRepository.crtEveExecuteQuery(searchBean);
	}

	@Override
	public Integer deleteParoleEvents(List<OffenderPayrolEvent> paroleEvents) {
		return ocmpconfRepository.deleteParoleEvents(paroleEvents);
	}
	
	@Override
	public List<OdynfrmSubmitDataBean> getOffenderOrders(OdynfrmSubmitDataBean searchBean) {
		return ocmpconfRepository.getOffenderOrders(searchBean);
	}

	@Override
	public Boolean checkOrderDependency(Integer offenderBookId, String screenId, String displayNo) {
		List<IwpDocuments> createdDocs = new ArrayList<IwpDocuments>();
		try {
			createdDocs =  ocmpconfRepository.checkOrderDependency(offenderBookId,  screenId,  displayNo);
			if(createdDocs!= null && createdDocs.size()>0) {
				return true;
			}
		} catch (Exception e) {
			logger.error("checkOrderDependency offenderBookId: {},displayNo: {}, screenId: {} ",offenderBookId,displayNo,screenId );
		}
		return false;
	}
	
	@Override
	public String transformJson(String queryData, Boolean failedAppStatus) {
		String respJson = "";
		Map<String, Object> inputData = new HashMap<>();
		try {
			logger.info("transformJson called == {}", queryData);
			inputData = new ObjectMapper().readValue(queryData, new TypeReference<HashMap<String,Object>>(){});
			// orders merge CUST, NCUS, PAR data for consolidated orders
			String formIdentifier = "{\"offenderBookId\":\""+((Map)inputData.get("formIdentifier")).get("offenderBookId")+"\",\"orderType\":\"CUST\"}";
			OdynfrmSubmitDataBean custData = new OdynfrmSubmitDataBean();
			custData.setSearchString(formIdentifier);
			custData.setFormName("OCDLEGLO");
			custData = getFormData(custData);
			formIdentifier = "{\"offenderBookId\":\""+((Map)inputData.get("formIdentifier")).get("offenderBookId")+"\",\"orderType\":\"NONCUST\"}";
			OdynfrmSubmitDataBean nCustData = new OdynfrmSubmitDataBean();
			nCustData.setSearchString(formIdentifier);
			nCustData.setFormName("OCDLEGLO");
			nCustData = getFormData(nCustData);
			formIdentifier = "{\"offenderBookId\":\""+((Map)inputData.get("formIdentifier")).get("offenderBookId")+"\",\"orderType\":\"PAR\"}";
			OdynfrmSubmitDataBean parData = new OdynfrmSubmitDataBean();
			parData.setSearchString(formIdentifier);
			parData.setFormName("OCDLEGLO");
			parData = getFormData(parData);
			
			formIdentifier = "{\"offenderBookId\":\""+((Map)inputData.get("formIdentifier")).get("offenderBookId")+"\",\"orderType\":\"BAIL\"}";
			OdynfrmSubmitDataBean bailData = new OdynfrmSubmitDataBean();
			bailData.setSearchString(formIdentifier);
			bailData.setFormName("OCDLEGLO");
			bailData = getFormData(bailData);
			
			OdynfrmSubmitDataBean summaryData = new OdynfrmSubmitDataBean();
			String formIdentifierSumm = "{\"offenderBookId\":\""+((Map)inputData.get("formIdentifier")).get("offenderBookId")+"\"}";
			summaryData.setSearchString(formIdentifierSumm);
			summaryData.setFormName("OCDLEGLS");
			summaryData = getFormData(summaryData);
			
			List<Map<String, Object>> custObj = new ArrayList<>();
			List<Map<String, Object>> nCustObj = new ArrayList<>();
			List<Map<String, Object>> parObj = new ArrayList<>();
			List<Map<String, Object>> bailObj = new ArrayList<>();
			Map<String, List<Map<String, Object>>> summaryObj = new HashMap<>();
			if(custData != null && custData.getFormInfoJson() != null) {
				custObj = (List<Map<String, Object>>) new ObjectMapper().readValue(custData.getFormInfoJson(), HashMap.class).get("myJsonRowData");
			}
			if(nCustData != null && nCustData.getFormInfoJson() != null) {
				nCustObj = (List<Map<String, Object>>) new ObjectMapper().readValue(nCustData.getFormInfoJson(), HashMap.class).get("myJsonRowData");
			}
			if(parData != null && parData.getFormInfoJson() != null) {
				parObj = (List<Map<String, Object>>) new ObjectMapper().readValue(parData.getFormInfoJson(), HashMap.class).get("myJsonRowData");
			}
			if(bailData != null && bailData.getFormInfoJson() != null) {
				bailObj = (List<Map<String, Object>>) new ObjectMapper().readValue(bailData.getFormInfoJson(), HashMap.class).get("myJsonRowData");
				
			}
			
			if(summaryData != null && summaryData.getFormInfoJson() != null && !summaryData.getFormInfoJson().trim().equalsIgnoreCase("")) {
				summaryObj = (Map<String, List<Map<String, Object>>>) new ObjectMapper().readValue(summaryData.getFormInfoJson(), HashMap.class);
			}
			String offenderBookId = ((Map)inputData.get("formIdentifier")).get("offenderBookId").toString();
			List<OffenderSentenceAdjustment> adjustments=oidcustadService.fetchBookingsData(Long.parseLong(offenderBookId));
			List<Map<String, Object>> paroleAdjusmentMap=new ArrayList<>();
			List<Map<String, Object>> bookingAdjustmentsMap=new ArrayList<>();
			List<Map<String, Object>> sentenceAdjustmentsMap=new ArrayList<>();
			try {
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				//Parole Events
				List<OffenderSentenceAdjustment> paroleAdjustments = getCustodyAdjustments(adjustments,"PAR");
				paroleAdjusmentMap = getParoleEvents(Long.parseLong(offenderBookId),paroleAdjustments);
				//Booking Adjustments
				List<OffenderSentenceAdjustment> bookingAdjustments=getCustodyAdjustments(adjustments,"BOOKING");
				for(OffenderSentenceAdjustment adj:bookingAdjustments) {
					Map<String, Object> bookAdjMap=new HashMap<>();
					bookAdjMap.put("usage_code", adj.getUsageCode());
					bookAdjMap.put("adjust_from_date",adj.getAdjustFromDate()!=null? formatter.format(adj.getAdjustFromDate()):null);
					bookAdjMap.put("adjust_to_date",adj.getAdjustToDate()!=null? formatter.format(adj.getAdjustToDate()):null);
					bookAdjMap.put("duration",adj.getAdjustDays()!=null? adj.getAdjustDays().toString():null);
					bookAdjMap.put("adjustment_type", adj.getDebitCreditCode());
					bookingAdjustmentsMap.add(bookAdjMap);
				}
				//Sentence Adjustments
				List<OffenderSentenceAdjustment> sentenceAdjustments=getCustodyAdjustments(adjustments,"CUST");
				for(OffenderSentenceAdjustment adj:sentenceAdjustments) {
					Map<String, Object> sentAdjMap=new HashMap<>();
					sentAdjMap.put("object_id", adj.getObjectId());
					sentAdjMap.put("usage_code", adj.getUsageCode());
					sentAdjMap.put("adjust_from_date",adj.getAdjustFromDate()!=null? formatter.format(adj.getAdjustFromDate()):null);
					sentAdjMap.put("adjust_to_date",adj.getAdjustToDate()!=null? formatter.format(adj.getAdjustToDate()):null);
					sentAdjMap.put("duration",adj.getAdjustDays()!=null? adj.getAdjustDays().toString():null);
					sentAdjMap.put("adjustment_type", adj.getDebitCreditCode());
					sentenceAdjustmentsMap.add(sentAdjMap);
				}
			} catch (Exception e) {
				logger.error("error in Adjustments Mapping : "+ e);
			}
			List<Map<String, Object>> bookingDates = new ArrayList<>();
			List<Map<String, Object>> sentenceOrderDates = new ArrayList<>();
			if(summaryObj.containsKey(ApplicationConstants.BOOKING_DATES)) {
				bookingDates = summaryObj.get(ApplicationConstants.BOOKING_DATES);
				if(bookingDates == null) {
					bookingDates = Collections.emptyList();
				}
			} else {
				bookingDates = Collections.emptyList();
			}
			if(summaryObj.containsKey(ApplicationConstants.SENTENCE_DATES)) {
				sentenceOrderDates = summaryObj.get(ApplicationConstants.SENTENCE_DATES);
				if(sentenceOrderDates == null) {
					sentenceOrderDates = Collections.emptyList();
				}
			} else {
				sentenceOrderDates = Collections.emptyList();
			}
			List<Map<String,Object>> inputList = new ArrayList<>();
			inputList.addAll(custObj);
			inputList.addAll(nCustObj);
			inputList.addAll(parObj);
			inputList.addAll(bailObj);
			// orders merge end
			logger.info("inputJson == {}", inputList);
			//Setting Resulting status of an order
			List<LegalUpdateReasons> orderStatus =   rgOrderStatus(null);
			List<Map<String,Object>> custSentType = populateSentType("CUST");
			for(Map<String,Object> obj : inputList) {
				if(obj.get("orderType").toString().equalsIgnoreCase("CUST")) {
					Map<String,Object> sentenceType = custSentType.stream().filter(i-> i.get("code").toString().equalsIgnoreCase(obj.get("sentenceCalcType").toString())).findFirst().orElse(null);
					if(sentenceType!= null && sentenceType.containsKey("code")) {
						obj.put("sentence_type", sentenceType.get("sentType"));
					}
				}
				List<LegalUpdateReasons> resultingStatus = orderStatus.stream().filter(i -> i.getCode().equalsIgnoreCase(obj.get("status").toString())).collect(Collectors.toList());
				obj.put("resulting_status", !resultingStatus.isEmpty()?resultingStatus.get(0).getActiveType():"E");
				List<Map<String, Object>> filterSentenceMap =sentenceAdjustmentsMap.stream().filter(i-> i.containsKey("object_id") && 
						obj.get("orderType").toString().equalsIgnoreCase("CUST") && 
						(BigDecimal.valueOf((Integer)obj.get("orderNo"))).compareTo(BigDecimal.valueOf((Long)i.get("object_id")))==0).collect(Collectors.toList());
				for(Map<String, Object> filterObj : filterSentenceMap) {
					filterObj.remove("object_id");
				}
				obj.put("adjust_days", filterSentenceMap);
				if (sentenceOrderDates.isEmpty()) {
					obj.put("sentence_order_dates", Collections.emptyList());
				} else {
					sentenceOrderDates.forEach(sent -> {
						if(sent.containsKey("orderNo")) {
							if(sent.get("orderNo").equals(obj.get("orderNo")) && sent.containsKey("sentenceOrderType") && sent.get("sentenceOrderType").equals(obj.get("orderType"))) {
								obj.put("sentence_order_dates", sent.get("sentenceOrderDates"));
							}
						} else {
							obj.put("sentence_order_dates", Collections.emptyList());
						}
					});
					if(!obj.containsKey("sentence_order_dates")) {
						obj.put("sentence_order_dates", Collections.emptyList());
					}
				}
				extractSpecialTerms(obj);
				if(!obj.containsKey("relatedTo")) continue;

				List<Map<String,Object>> depList = new ArrayList<>();
				Map<String, Object> child = getChildData(inputList, obj);
				if(child != null && "CU".equals(obj.get("commenceType"))) {
					inputList.forEach(ip->{
						if(ip.get("displayNo").equals(obj.get("relatedTo"))) {
							obj.put("commenceDate","");
							if(ip.containsKey("dependent_sentence")) {
								((List<Map<String,Object>>)ip.get("dependent_sentence")).add(obj); 
								ip.put("dependent_sentence", (List<Map<String,Object>>)ip.get("dependent_sentence"));
							}else {
								depList.add(obj);
								ip.put("dependent_sentence", depList);
							}
						}
					});
				}
			}
			for(Map<String,Object> obj : inputList) {
				if ("CC".equals(obj.get("commenceType"))) {
					List<Map<String,Object>> ordList = inputList.stream().filter(order->order.get("displayNo").equals(obj.get("relatedTo"))).collect(Collectors.toList());
					if (!ordList.isEmpty()) {
						obj.put("commenceDate",ordList.get(0).get("commenceDate"));
					}
				}
			}
			inputList.removeAll(inputList.stream().filter(obj->"CU".equals(obj.get("commenceType"))).collect(Collectors.toList()));
			respJson = new ObjectMapper().writeValueAsString(inputList);
			JsonArray mainObj1 = jsonKeysUpper(new Gson().fromJson(respJson, JsonArray.class),failedAppStatus);
			logger.info("After processing Input");
			JsonObject sentences = new JsonObject();
			sentences.add("sentences", mainObj1);
			JsonArray bookingDatesJson = new JsonArray();
			JsonArray paroleDatesJson = new JsonArray();
			JsonArray bookingAdjustmentsJson = new JsonArray();
			bookingDatesJson = jsonKeysUpper(new Gson().fromJson(new ObjectMapper().writeValueAsString(bookingDates), JsonArray.class),failedAppStatus);
			paroleDatesJson = new Gson().fromJson(new ObjectMapper().writeValueAsString(paroleAdjusmentMap), JsonArray.class);
			bookingAdjustmentsJson = new Gson().fromJson(new ObjectMapper().writeValueAsString(bookingAdjustmentsMap), JsonArray.class);
			sentences.add(failedAppStatus?"bookingDates":"booking_dates", bookingDatesJson);
			sentences.add(failedAppStatus?"paroleAdjustment":"parole_adjustment", paroleDatesJson);
			sentences.add(failedAppStatus?"bookingAdjustment":"booking_adjustment", bookingAdjustmentsJson);
			logger.info("sentences Object created");
			respJson = sentences.toString();
			logger.info("respJson == {}", respJson);
		} catch (Exception e) {
			logger.error("error in transformJson method",e.getMessage());
		}
		return respJson;
	}
	
	private List<OffenderSentenceAdjustment> getCustodyAdjustments(List<OffenderSentenceAdjustment> adjustments,String objectType){
		return adjustments.stream().filter(bean->bean.getObjectType()!=null && objectType!=null &&  bean.getObjectType().equals(objectType)).collect(Collectors.toList());
	}
	
	
	private List<Map<String,Object>> getParoleEvents(Long offenderBookId,List<OffenderSentenceAdjustment> offParAdjustments){
		List<Map<String,Object>> returnListMap = new ArrayList<Map<String,Object>>();
		logger.info("getParoleEvents called for " + offenderBookId);
		try {
			List<OffenderPayrolEvent> initialParoleEvents = oidpaoeService.searchByOffebderBookId(offenderBookId);
			if(initialParoleEvents!=null & !initialParoleEvents.isEmpty()) {
				List<OffenderPayrolEvent> filterParoleEvents = new ArrayList<OffenderPayrolEvent>();
				filterParoleEvents.addAll(initialParoleEvents);
				for(OffenderPayrolEvent relaseParEvent : initialParoleEvents) {
					if(relaseParEvent.getParoleEvent().equals("RPAR")) {
						OffenderPayrolEvent isEventValid= filterParoleEvents.stream().filter(i -> Long.compare(i.getParoleEventId(),relaseParEvent.getParoleEventId())== 0).findFirst().orElse(null);
						if(isEventValid== null) {
							continue;
						}
						// Get only revoked parole event of that release(RPAR).
						OffenderPayrolEvent revokedParoleEvent = filterParoleEvents.stream().filter(i -> i.getParoleEvent().equals("PAR_REV") && Long.compare(i.getParoleEventId(),relaseParEvent.getParoleEventId())>=0).findFirst().orElse(null);
						if(revokedParoleEvent!= null) {
							//If revoke par event present get the adjustments to it for json
							List<OffenderSentenceAdjustment> parAdjustment =  offParAdjustments.stream().filter(i-> i.getObjectType().equalsIgnoreCase("PAR") && i.getObjectId().equals(revokedParoleEvent.getParoleEventId())).collect(Collectors.toList());
							if(parAdjustment != null && !parAdjustment.isEmpty()) {
								// If we have adjustments for parole revoked event
								List<Map<String,String>> adjustmentsList =  new ArrayList<Map<String,String>>();
								for (OffenderSentenceAdjustment adj : parAdjustment) {
									Map<String,String> adjustObj = new HashMap<String, String>();
									adjustObj.put("duration", adj.getAdjustDays().toString());
									adjustObj.put("adjustment_type", adj.getDebitCreditCode());
									adjustmentsList.add(adjustObj);
								}
								
								returnListMap.add(setParAdjustmentMap(adjustmentsList,relaseParEvent.getEventDate(),revokedParoleEvent.getEventDate()));
							} else {
								returnListMap.add(setParAdjustmentMap(Collections.emptyList(),relaseParEvent.getEventDate(),revokedParoleEvent.getEventDate()));
							}
//							Remove the Release par to  Revoke par flow and Again follow same flow for new Release event
							List<OffenderPayrolEvent> removeList = filterParoleEvents.stream().filter(i -> Long.compare(i.getParoleEventId(),revokedParoleEvent.getParoleEventId())<=0).collect(Collectors.toList());
							filterParoleEvents.removeAll(removeList);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("In getParoleEvents: for {}", e.getMessage());
		}
		return returnListMap;
	}
	
	private Map<String, Object> setParAdjustmentMap(List<Map<String,String>> adjustmentDurations, Date releaseParDate, Date revokeParDate) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Map<String, Object> parAdjMap = new HashMap<>();
		parAdjMap.put("usage_code", "PAR");
		parAdjMap.put("release_to_parole_date", formatter.format(releaseParDate));
		parAdjMap.put("parole_revoke_date", formatter.format(revokeParDate));
		parAdjMap.put("adjustment_durations", adjustmentDurations);
		return parAdjMap;
	}
	
	@Override
	public List<Map<String,Object>> populateSentType(String sentCategory) {
		List<Map<String,Object>> respList = new ArrayList<>();
		Map<String, Object> obj = new HashMap<>();
		SentenceCalcTypes sentenceCalcTypes= new SentenceCalcTypes();
		sentenceCalcTypes.setSentenceCategory(sentCategory);
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
				sentenceTerms.setSentenceCategory(sentCategory);
				sentenceTerms.setSentenceCalcType(sentType.getSentenceCalcType());
				List<SentenceTerms> sentenceTermsList = oimsreqsService.senTermsExecuteQuery(sentenceTerms);
				obj.put("termList", sentenceTermsList);
				
				respList.add(obj);
			}
		} catch (Exception e) {
			logger.error("In populateSentType:", e);
		}
		return respList;
	}
	
	private void extractSpecialTerms(Map<String,Object> sentence) {
		// Extract special terms like Parole
			List<Map<String,Object>> terms = (List<Map<String,Object>>) sentence.get("terms");
			List<Map<String,Object>> splTerm = new ArrayList<Map<String,Object>>();
			if(terms != null && !terms.isEmpty()) {
				terms.forEach(term->{
					Map<String,Object> newTerm = new HashMap<String,Object>();
					if (term.get("termType").equals(ApplicationConstants.NPP) || term.get("termType").equals(ApplicationConstants.SUSP)) {
						newTerm = new HashMap<String,Object>(term);
						splTerm.add(newTerm);
					}
				});
				if(splTerm.size() > 0) {
					terms.forEach(term->{
						Map<String,Object> newTerm = new HashMap<String,Object>();
						if (term.get("termType").equals(ApplicationConstants.IMP) || term.get("termType").equals(ApplicationConstants.LIFE)) {
							splTerm.forEach(ele -> {
								if(ele.get("termType").equals(ApplicationConstants.NPP)) {
									ele.remove("termType");
									term.put("non_parole_term", ele);
								} else if(ele.get("termType").equals(ApplicationConstants.SUSP)) {
									ele.remove("termType");
									term.put("suspended_term", ele);
								}
							});
						}
					});
					terms.removeIf(((term)->term.get("termType").equals(ApplicationConstants.NPP) || term.get("termType").equals(ApplicationConstants.SUSP)));
					sentence.put("terms", terms);
				}
			}
	}
	
	private Map<String, Object> getChildData(List<Map<String,Object>> totalData, Map<String,Object> currData) {
		if (currData.get("relatedTo") == null || "".equals(currData.get("relatedTo"))) 
			return null;
		else {
			Map<String,Object> currObj2 = new HashMap<>();
			for(Map<String,Object> obj : totalData) {
				if(obj.get("displayNo") == currData.get("relatedTo"))
					currObj2 = obj;
			}
			Map<String, Object> child = getChildData(totalData, currObj2);
			List<Map<String,Object>> depList = new ArrayList<>();
			depList.add(currObj2);
			if(child != null && "CU".equals(currObj2.get("commenceType"))) {
				String relatedTo = String.valueOf(currObj2.get("relatedTo"));
				totalData.forEach(ip->{
					if(ip.get("displayNo").equals(relatedTo)) {
						
//						obj.put("commenceDate", ip.get("commenceDate"));
						  if(ip.containsKey("dependent_sentence")) {
							  ((List<Map<String,Object>>)ip.get("dependent_sentence")).add(depList.get(0)); 
							  ip.put("dependent_sentence", (List<Map<String,Object>>)ip.get("dependent_sentence"));
						  }else {
							  ip.put("dependent_sentence", depList);
						  }
						 
					}
				});
			}
//			if(child != null && "CU".equals(currObj2.get("commenceType"))) currObj2.put("dependent_sentence", depList);
			return currObj2;
		}
	}
	
	private JsonArray jsonKeysUpper(JsonArray arr, Boolean camelCase) {
	    JsonArray aux = new JsonArray();
	    for(int i = 0; i < arr.size(); ++i) {
	    	if(arr.get(i).isJsonPrimitive()) {
	    		if(arr.get(i).getAsJsonPrimitive().isString()) aux.add(arr.get(i).getAsString());
        	}else {
        		aux.add(jsonKeysUpper(arr.get(i).getAsJsonObject(),camelCase));
        	}
	    }
	    return aux;
	}
	
	private JsonObject jsonKeysUpper(JsonObject obj, Boolean camelCase) {
	    JsonObject aux = new JsonObject();
	    if(obj.isJsonPrimitive()) return obj;
	    if(obj.isJsonNull()) return null;
	    // Iterate all properties
	    for(String o : obj.keySet()) {
	        if(obj.get(o).isJsonPrimitive()) {
	        	if(obj.get(o).getAsJsonPrimitive().isBoolean()) {
	        		aux.addProperty(camelCase?o:camelToSnake(o), obj.get(o).getAsBoolean());
	        	}else {
	        		if(o.equalsIgnoreCase(ApplicationConstants.ORDERNO)) {
	        			try {
	        				aux.addProperty(camelCase?o:camelToSnake(o),Integer.parseInt(obj.get(o).toString()));
						} catch (Exception e) {
							logger.error("Error in OrderNo parsing" + e.getMessage());
						}
	        		}else {
	        			aux.addProperty(camelCase?o:camelToSnake(o), obj.get(o).getAsString());
	        		}
	        	}
	        }
	        else if(obj.get(o).isJsonArray())
	            aux.add(camelCase?o:camelToSnake(o), jsonKeysUpper(obj.get(o).getAsJsonArray(),camelCase));
	        else if(obj.get(o).isJsonNull())
	            aux.add(camelCase?o:camelToSnake(o), jsonKeysUpper(JsonNull.INSTANCE));
	        else 
	        	aux.add(camelCase?o:camelToSnake(o), jsonKeysUpper(obj.get(o).getAsJsonObject(),camelCase));
	    }
	    return aux;
	}
	
	private JsonPrimitive jsonKeysUpper(JsonNull obj) {
		return new JsonPrimitive("");
	}
	
	private String camelToSnake(String str)
    {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        str = str.replaceAll(regex, replacement).toLowerCase();
        return str;
    }
	
	@Override
	public Integer sentenceEngineOffline(OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		Integer returnFlag = 0;
		try {
			logger.info("sentenceEngineOffline odynfrmSubmitDataBean: {} ",odynfrmSubmitDataBean.getFormInfoJson());
			OdynfrmSubmitDataBean ocdleglsObj = new OdynfrmSubmitDataBean();
			ocdleglsObj.setFormName("OCDLEGLS");
			Map<String, Object> inputData = new HashMap<>();
			ocdleglsObj.setSearchString(odynfrmSubmitDataBean.getFormIdentifier());
			inputData = new ObjectMapper().readValue(odynfrmSubmitDataBean.getFormIdentifier(), new TypeReference<HashMap<String,Object>>(){});
			ocdleglsObj.setSearchString("\"offenderBookId\":\""+inputData.get("offenderBookId")+"\"");
			ocdleglsObj = getFormData(ocdleglsObj);
			if("OCDLEGLS".equalsIgnoreCase(odynfrmSubmitDataBean.getFormName()) || (ocdleglsObj.getFormInfoJson()!= null && !ocdleglsObj.getFormInfoJson().trim().isEmpty())) {
				odynfrmSubmitDataBean.setActionType(ApplicationConstants.VERIFY_MODIFICATION);
			} else {
				ocdleglsObj.setSearchString("\"offenderBookId\":\""+inputData.get("offenderBookId")+"\"");
				List<OdynfrmSubmitDataBean> pendingList = ocipenscService.getPendingSentenceCalcEvents(ocdleglsObj);
				if(pendingList != null && !pendingList.isEmpty()) {
					odynfrmSubmitDataBean.setActionType(ApplicationConstants.VERIFY_MODIFICATION);
				}else {
					odynfrmSubmitDataBean.setActionType(ApplicationConstants.VERIFY_DATA_ENTRY);
				}
			}
			HashMap<String, Object> formIdentifierMap = new HashMap<String,Object>();
			formIdentifierMap.put("formIdentifier", new ObjectMapper().readValue(odynfrmSubmitDataBean.getFormIdentifier(), HashMap.class));
			String transformJsonResponse = transformJson(new ObjectMapper().writeValueAsString(formIdentifierMap),true);
			HashMap<String,Object> formInfoJson = new ObjectMapper().readValue(transformJsonResponse,new TypeReference<HashMap<String,Object>>(){});
			HashMap<String,Object> setFormIdentifier= new  ObjectMapper().readValue(odynfrmSubmitDataBean.getFormIdentifier(),new TypeReference<HashMap<String,Object>>(){});
			setFormIdentifier.remove(ApplicationConstants.ORDERTYPE);
			odynfrmSubmitDataBean.setFormIdentifier(new ObjectMapper().writeValueAsString(setFormIdentifier));
			odynfrmSubmitDataBean.setFormInfoJson(new ObjectMapper().writeValueAsString(formInfoJson));
			odynfrmSubmitDataBean.setCalcEngineInput(new ObjectMapper().writeValueAsString(formInfoJson).getBytes());
			logger.info("Pending events before save odynfrmSubmitDataBean: {} ",odynfrmSubmitDataBean.getFormInfoJson());
			returnFlag = ocipenscService.insertOcdLeglsPendingData(odynfrmSubmitDataBean);
		} catch (Exception e) {
			logger.error("sentenceEngineOffline Exception: {} ",e.getMessage());
		}
		return returnFlag;
	}
	
	@Override
	public Boolean isRfcOrderDependent(OdynfrmSubmitDataBean odynfrmSubmitDataSummary) {
		try {
			logger.info(this.getClass().getName()+ " isRfcOrderDependent form_info_json : {} ",odynfrmSubmitDataSummary.getFormIdentifier());
			Map<String, Object> formInfoJsonSum = new ObjectMapper()
					.readValue(odynfrmSubmitDataSummary.getFormInfoJson(), new TypeReference<Map<String, Object>>() {});
			if (formInfoJsonSum.containsKey(ApplicationConstants.SENTENCE_DATES)) {
				List<Map<String, Object>> sentence_dates = (List<Map<String, Object>>) formInfoJsonSum.get(ApplicationConstants.SENTENCE_DATES);
				if (sentence_dates.stream().filter(
						i -> i.get(ApplicationConstants.SENTENCEORDERTYPE).equals(ApplicationConstants.NCUS) && i.get("sentenceCommenceType").equals("RFC"))
						.collect(Collectors.toList()).size() > 0) {
					List<Map<String, Object>> custOrders = sentence_dates.stream()
							.filter(i -> i.get(ApplicationConstants.SENTENCEORDERTYPE).equals(ApplicationConstants.CUSTORDER) && !ocmpconfRepository.getResultingStatus(i.get(ApplicationConstants.STATUS).toString()).equals("E"))
							.collect(Collectors.toList());
					if (custOrders.size() < 2) {
						return true; // Can not update
					} 
					List<Map<String, Object>> holdOrders =  custOrders.stream().filter(i -> i.get(ApplicationConstants.SENTENCETYPE).equals("CHLD")).collect(Collectors.toList());
					if(holdOrders!=null && !holdOrders.isEmpty() && holdOrders.size() == custOrders.size() && compareHedDate(holdOrders)) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName(), " isRfcOrderDependent error : {} ", e);
		}
		return false;
	}

	@Override
	public void updateHoldExpiryOrders(List<OdynfrmSubmitDataBean> holdOrdersUpated, String authorization) {
		try {
			logger.info(this.getClass().getName() + " updateHoldExpiryDate" );
			if (holdOrdersUpated != null && !holdOrdersUpated.isEmpty()) {
				for (OdynfrmSubmitDataBean odynfrmSubmitDataBean : holdOrdersUpated) {
					logger.info(this.getClass().getName() + " updateHoldExpiryDate form_identifier : {} ",odynfrmSubmitDataBean.getFormIdentifier());
					odynfrmSubmitDataBean.setActionType("Modification");
					Map<String,Object>  calcReason = generateCalcReason();
					odynfrmSubmitDataBean.setCalcReason(new ObjectMapper().writeValueAsString(calcReason));
					prosmainService.enableTriggers(odynfrmSubmitDataBean, authorization, "61");
				}
			}
		} catch (Exception e) {
			logger.error("Error in  updateHoldExpiryDate Exception: {}",e.getMessage());
		}
	}

	private Boolean compareHedDate(List<Map<String, Object>> holdOrders) {
		try {
			int i = 0;
			LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Date currTimeStamp = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT).parse(currentDate.toString());
			for(Map<String,Object> sentOrdDate : holdOrders) { 
				if(sentOrdDate.containsKey(ApplicationConstants.SENTENCE_ORDER_DATES)) { 
					for(Map<String,Object> sentenceDates : (List<Map<String, Object>>) sentOrdDate.get(ApplicationConstants.SENTENCE_ORDER_DATES)) { 
						if(sentenceDates.containsKey(ApplicationConstants.DATE_TYPE)  && sentenceDates.get(ApplicationConstants.DATE_TYPE).toString().equalsIgnoreCase("hed")  &&  sentenceDates.containsKey(ApplicationConstants.VALUE) && !ApplicationConstants.INDEFINITE.equals(sentenceDates.get(ApplicationConstants.VALUE))) {
							Date sentHed = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT).parse(sentenceDates.get(ApplicationConstants.VALUE).toString());
							if(sentHed.compareTo(currTimeStamp)<0) {
								i++;
							}
						}
					}
				}
			}
			if(i == holdOrders.size()) {
				return true; // Cannot update
			}
		} catch (Exception e) {
			logger.error("Error in  compareHedDate Exception: {}",e.getMessage());
		}
		return false;
	}

	private Map<String,Object> generateCalcReason() {
		Map<String,Object>  calcReason = new HashMap<String, Object>();
		calcReason.put("staffName","System-generated");
		calcReason.put("calculationReason","AUTOUPDATE");
		calcReason.put("calcCode","AUTOUPDATE");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String currTimeStamp = sdf.format(eliteDateService.getDBTime());
		calcReason.put("sentDate",currTimeStamp);
		calcReason.put("sentTime",currTimeStamp);
		return calcReason;
	}

	@Override
	public Map<String, String> getResultingStatusMap() {
		Map<String, String> returnMap = new HashMap<String, String>();
		try {

			List<LegalUpdateReasons> status = ocmpconfRepository.rgOrderStatus(null);
			if (status != null && !status.isEmpty()) {
				status.forEach(e -> {
					returnMap.put(e.getUpdateReasonCode(), e.getActiveType());
				});
			}
		} catch (Exception e) {
			logger.error("Error in  getResultingStatusMap Exception: {}",e.getMessage());
		}
		return returnMap;
	}

	@Override
	public List<ReferenceCodes> getCommenceType(String orderType) {
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		returnList = ocmpconfRepository.getCommenceType();
		try {
			if(returnList!= null && !returnList.isEmpty()) {
				returnList.forEach(ele -> {
					if(ele.getActiveFlag().equals(ApplicationConstants.YES)) {
						if(ele.getParentCode() == null || ele.getParentCode().equals("") || ele.getParentCode().equalsIgnoreCase(orderType)
								|| ele.getParentCode().equalsIgnoreCase(ApplicationConstants.BOTH)) {
							ele.setCanDisplay(true);
						}else {
							ele.setCanDisplay(false);
						}
					} else {
						ele.setCanDisplay(false);
					}
				});
			}
		} catch (Exception e) {
			logger.error("Exception in getCommenceType {} :",e.getMessage());
		}
		return returnList;
	}
}
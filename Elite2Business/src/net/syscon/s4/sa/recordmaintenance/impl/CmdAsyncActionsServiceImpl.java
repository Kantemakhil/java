package net.syscon.s4.sa.recordmaintenance.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.cm.intakeclosure.OcdintakRepository;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;
import net.syscon.s4.common.beans.OdynfrmSubmitDataCommitBean;
import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderCustodyStatus;
import net.syscon.s4.common.beans.SentenceCalcTypes;
import net.syscon.s4.globalconfiguration.OcmpconfRepository;
import net.syscon.s4.globalconfiguration.OcmpconfService;
import net.syscon.s4.globalconfiguration.OumsysetService;
import net.syscon.s4.inst.demographicsbiometrics.OidadmisService;
import net.syscon.s4.inst.institutionalactivities.OcdcgpayService;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OffAllowPayDetailsCommitBean;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.sa.recordmaintenance.CmdAsyncActionsService;
import net.syscon.s4.sa.recordmaintenance.CmdactionRepository;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;
import net.syscon.s4.sa.recordmaintenance.SendMail;
import net.syscon.s4.sa.recordmaintenance.SendmailService;

@Service
public class CmdAsyncActionsServiceImpl implements CmdAsyncActionsService{
	
	private static Logger logger = LogManager.getLogger(CmdAsyncActionsServiceImpl.class.getName());
	
	@Autowired
	private OcmpconfService ocmpconfService;
	
	@Autowired
	private OcmpconfRepository ocmpconfRepository;
	
	@Autowired
	private OcdintakRepository ocdintakRepository;
	
	@Autowired
	private ProsmainService prosmainService;
	
	@Autowired
	private OumsysetService oumsysetService;
	
	@Autowired
	private CmdactionRepository cmdactionRepository;
	
	@Autowired
	private OidadmisService oidadmisService;
	
	@Autowired
	private SendmailService sendmailService;
	
	@Autowired
	private OcdcgpayService ocdcgpayService;
	
	@Override
	@Async
	public void updateLegalsData(String query, String authorization, String modifyUserId) {
		List<Map<String, Object>> responseList = new ArrayList<Map<String,Object>>();
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Date commenceDate = null;
		Date expiryDate = null;
		List<Map<String, Object>> conditionUpdateOrders = new ArrayList<Map<String,Object>>();
		List<OdynfrmSubmitDataBean> legalsDataHty = new ArrayList<OdynfrmSubmitDataBean>();
		List<OdynfrmSubmitDataBean> custodialData = new ArrayList<OdynfrmSubmitDataBean>();
		List<OdynfrmSubmitDataBean> holdRecordsUpdated = new ArrayList<OdynfrmSubmitDataBean>();
		List<OdynfrmSubmitDataBean> recordsToUpdate = new ArrayList<OdynfrmSubmitDataBean>();
		try {
			LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Date currTimeStamp = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT).parse(currentDate.toString());
			logger.info(this.getClass().getName()+ "Checking time updateLegalsData Triggered at : "+ currTimeStamp);
			Map<String,String> resultingStatusMap = ocmpconfService.getResultingStatusMap();
			updateConditions(resultingStatusMap); 
			String statusflag = ocmpconfRepository.getStatusFlag("EAOSU");
			if(ApplicationConstants.YES_FLAG.equals(statusflag)) {
				List<OdynfrmSubmitDataBean> legalsData = ocmpconfRepository.getLegalsData(); //OCDLEGLO_DATA
				List<OdynfrmSubmitDataBean> legalSummaryData = ocmpconfRepository.getLegalSummaryData();//OCDLEGLS_DATA
				List<Map<String,Object>> parUpdateList = new ArrayList<Map<String,Object>>();
				ObjectMapper objectMapper = new ObjectMapper();
				for(OdynfrmSubmitDataBean odynfrmSubmitDataBean : legalsData) {
					try {
						odynfrmSubmitDataBean.setFormInfoJson(new String(odynfrmSubmitDataBean.getFormInfoJsonBlob()));
						Map<String,List<Map<String,Object>>> formInfoJson = objectMapper.readValue(odynfrmSubmitDataBean.getFormInfoJson(), new TypeReference<HashMap<String,List<Map<String,Object>>>>(){});
						List<Map<String,Object>> paroleOrders =  new ArrayList<Map<String,Object>>();
						boolean isHtyIns = false;
						boolean isHoldExp = false; // Flag used to call Sentence Engine
						for(Map<String,Object> myJsonRowDataObj :formInfoJson.get("myJsonRowData")) {
							commenceDate = null;
							expiryDate = null;
							if(myJsonRowDataObj.containsKey(ApplicationConstants.COMMENCE_DATE) && !"".equals(myJsonRowDataObj.get(ApplicationConstants.COMMENCE_DATE)) && myJsonRowDataObj.get(ApplicationConstants.COMMENCE_DATE)!= null && !ApplicationConstants.INDEFINITE.equals(myJsonRowDataObj.get(ApplicationConstants.COMMENCE_DATE))) {
								try {
									commenceDate = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT).parse(myJsonRowDataObj.get(ApplicationConstants.COMMENCE_DATE).toString());
								} catch( Exception e) {
									commenceDate = null;
									logger.error("In updateLegalsData method : Commence Date:", e);
								}
							}
							if(myJsonRowDataObj.containsKey(ApplicationConstants.EXPIRY_DATE) && !"".equals(myJsonRowDataObj.get(ApplicationConstants.EXPIRY_DATE))&& myJsonRowDataObj.get(ApplicationConstants.EXPIRY_DATE)!= null  && !ApplicationConstants.INDEFINITE.equals(myJsonRowDataObj.get(ApplicationConstants.EXPIRY_DATE))) {
								try {
									expiryDate = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT).parse(myJsonRowDataObj.get(ApplicationConstants.EXPIRY_DATE).toString());
								}catch (Exception e) {
									expiryDate = null;
									logger.error("In updateLegalsData method : Expiry Date:", e);
								}
							}
							if(commenceDate != null && ApplicationConstants.PEND.equals(myJsonRowDataObj.get(ApplicationConstants.STATUS)) && commenceDate.compareTo(currTimeStamp) < 1) {
								if (myJsonRowDataObj.get("orderType").toString().equalsIgnoreCase("PAR")) {
									paroleOrders.add(myJsonRowDataObj);
								}
								myJsonRowDataObj.put(ApplicationConstants.STATUS, ApplicationConstants.ACT);
								conditionUpdateOrders.add(myJsonRowDataObj);
								if("CUST".equals(myJsonRowDataObj.get("orderType"))) {
									custodialData.add(odynfrmSubmitDataBean);
								}
								isHtyIns = true;
							} 
							String resultingStatus = myJsonRowDataObj.containsKey(ApplicationConstants.STATUS)?resultingStatusMap.get(myJsonRowDataObj.get(ApplicationConstants.STATUS).toString()): "";   
							if(resultingStatus!= null && !resultingStatus.equals("") && expiryDate != null && ("NCUS".equals(myJsonRowDataObj.get("orderType")) || "BAIL".equals(myJsonRowDataObj.get("orderType")) || "PAR".equals(myJsonRowDataObj.get("orderType"))) &&
									ApplicationConstants.A.equals(resultingStatus) && expiryDate.compareTo(currTimeStamp) < 0 && !ApplicationConstants.EXP.equals(myJsonRowDataObj.get(ApplicationConstants.STATUS))) {
								myJsonRowDataObj.put(ApplicationConstants.STATUS, ApplicationConstants.EXP);
								conditionUpdateOrders.add(myJsonRowDataObj);
								isHtyIns = true;
							}
							if("CUST".equals(myJsonRowDataObj.get("orderType")) && ApplicationConstants.A.equals(resultingStatus)) {
								for(OdynfrmSubmitDataBean odynfrmSubmitDataBeanSum : legalSummaryData) {
									try {
										Map<String, String> formIdMap = objectMapper.readValue(odynfrmSubmitDataBean.getFormIdentifier(), new TypeReference<HashMap<String,String>>(){});
										Map<String, String> formIdMapSum = objectMapper.readValue(odynfrmSubmitDataBeanSum.getFormIdentifier(), new TypeReference<HashMap<String,String>>(){});
										if(formIdMap.get(ApplicationConstants.OFFENDER_BOOK_ID).equals(formIdMapSum.get(ApplicationConstants.OFFENDER_BOOK_ID))) {
											odynfrmSubmitDataBeanSum.setFormInfoJson(new String(odynfrmSubmitDataBeanSum.getFormInfoJsonBlob()));
											Map<String,List<Map<String,Object>>> formInfoJsonSum = objectMapper.readValue(odynfrmSubmitDataBeanSum.getFormInfoJson(), HashMap.class);
											if(formInfoJsonSum.containsKey(ApplicationConstants.SENTENCE_DATES)) {
												List<Map<String, Object>> sentence_order_dates = (List<Map<String, Object>>) formInfoJsonSum.get(ApplicationConstants.SENTENCE_DATES);
												for(Map<String,Object> sentOrdDate : sentence_order_dates) {
													boolean affectedOrders = getLinkedOrders(legalsData, odynfrmSubmitDataBean.getFormIdentifier(), (String) myJsonRowDataObj.get("displayNo")); 
													if (sentOrdDate.get(ApplicationConstants.DISPLAY_NO).equals(myJsonRowDataObj.get(ApplicationConstants.DISPLAY_NO)) && affectedOrders == false) {
														if(sentOrdDate.containsKey(ApplicationConstants.SENTENCE_ORDER_DATES)) {
															for(Map<String,Object> sentenceDates : (List<Map<String, Object>>) sentOrdDate.get(ApplicationConstants.SENTENCE_ORDER_DATES)) {
																if(sentOrdDate.containsKey(ApplicationConstants.SENTENCETYPE) && sentOrdDate.get(ApplicationConstants.SENTENCETYPE).toString().equalsIgnoreCase("CHLD")) {
																	if(sentenceDates.containsKey(ApplicationConstants.DATE_TYPE)  && sentenceDates.get(ApplicationConstants.DATE_TYPE).toString().equalsIgnoreCase("hed")  &&  sentenceDates.containsKey(ApplicationConstants.VALUE) && !ApplicationConstants.INDEFINITE.equals(sentenceDates.get(ApplicationConstants.VALUE))) {
																		Date sentHed = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT).parse(sentenceDates.get(ApplicationConstants.VALUE).toString());
																		if(sentHed.compareTo(currTimeStamp)<0 && !ocmpconfService.isRfcOrderDependent(odynfrmSubmitDataBeanSum)
																				&& !myJsonRowDataObj.get(ApplicationConstants.STATUS).toString().equalsIgnoreCase("EXPE")) {
																			myJsonRowDataObj.put(ApplicationConstants.STATUS, "EXPE");
																			conditionUpdateOrders.add(myJsonRowDataObj);
//																			custodialData.add(odynfrmSubmitDataBean);
																			isHoldExp = true;
																			isHtyIns = true;
																		}
																	}
																} else if(sentenceDates.containsKey(ApplicationConstants.DATE_TYPE) && sentenceDates.get(ApplicationConstants.DATE_TYPE).toString().equalsIgnoreCase("lrd")  &&  sentenceDates.containsKey(ApplicationConstants.EFFECTIVE_VALUE) && !ApplicationConstants.INDEFINITE.equals(sentenceDates.get(ApplicationConstants.EFFECTIVE_VALUE))) {
																	Date sentLrd = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT).parse(sentenceDates.get(ApplicationConstants.EFFECTIVE_VALUE).toString());
																	if(sentLrd.compareTo(currTimeStamp)<0 && !myJsonRowDataObj.get(ApplicationConstants.STATUS).toString().equalsIgnoreCase("SERV")) {
																		myJsonRowDataObj.put(ApplicationConstants.STATUS, "SERV");
																		conditionUpdateOrders.add(myJsonRowDataObj);
																		custodialData.add(odynfrmSubmitDataBean);
																		isHtyIns = true;
																	}
																}
															}
														}
													}
												}
											}
											break;
										}
									} catch (Exception e) {
										logger.error(this.getClass().getName()+ " The error in  updateLegalsData Legals Summary Data  for {}", odynfrmSubmitDataBeanSum.getFormIdentifier());
										logger.error( "In updateLegalsData, legals Summary Data Iteration ", e);
									}
								}
							}
						} 
						if(isHtyIns == true) {
							odynfrmSubmitDataBean.setModifyUserId(modifyUserId);
							recordsToUpdate.add(odynfrmSubmitDataBean);
							if(isHoldExp == true) {
								holdRecordsUpdated.add(odynfrmSubmitDataBean);
							} else {
								legalsDataHty.add(odynfrmSubmitDataBean);
							}
						}
						if(paroleOrders != null && !paroleOrders.isEmpty()) {
							Map<String,Object> parInput = new HashMap<String,Object>();
							parInput.put("updatedOrders", objectMapper.writeValueAsString(paroleOrders));
							Map<String, Object> inputData = objectMapper.readValue(odynfrmSubmitDataBean.getFormIdentifier(),new TypeReference<HashMap<String,Object>>(){});
							parInput.put("formIdentifier", objectMapper.writeValueAsString(inputData));
							parUpdateList.add(parInput);
						}
						odynfrmSubmitDataBean.setFormInfoJson(objectMapper.writeValueAsString(formInfoJson));
						odynfrmSubmitDataBean.setFormInfoJsonBlob(objectMapper.writeValueAsString(formInfoJson).getBytes());
					} catch (Exception e) {
						logger.error(this.getClass().getName()+ "The error for the updateLegalsData Legals Data : " + odynfrmSubmitDataBean.getFormIdentifier());
						logger.error("In updateLegalsData, legalsData Iteration ", e);
					}
				}
				for(Map<String, Object> parOrder: parUpdateList) {
					try {
						String parStatusFlag = ocmpconfRepository.getStatusFlag("EASUIOLPO");
						if(parStatusFlag.equalsIgnoreCase(ApplicationConstants.YES_FLAG)) {
							Map<String, Object> inputData = new HashMap<>();
							inputData = objectMapper.readValue(parOrder.get("formIdentifier").toString(), new TypeReference<HashMap<String,Object>>(){});
							String formIdentifier = "{\"offenderBookId\":\""+inputData.get("offenderBookId")+"\",\"orderType\":\"CUST\"}";
							for(OdynfrmSubmitDataBean bean:legalsData) {
								if(bean.getFormIdentifier().equalsIgnoreCase(formIdentifier)) {
									List<Map<String, Object>> updatedOrders =  objectMapper.readValue(parOrder.get("updatedOrders").toString(), new TypeReference<ArrayList<Map<String, Object>>>(){});
									Map<String,List<Map<String,Object>>> custFormInfoJson = objectMapper.readValue(bean.getFormInfoJson(), new TypeReference<HashMap<String,List<Map<String,Object>>>>(){});
									for (Map<String, Object> custOrder : custFormInfoJson.get("myJsonRowData")) {
										for (Map<String, Object> parOrd : updatedOrders) {
											List<String> affectedOrders = (List<String>) parOrd.get("affectedOrders");
											for (String affectedOrder : affectedOrders) {
												if (custOrder.get("displayNo").toString().equalsIgnoreCase(affectedOrder)) {
													String resultingStatus = custOrder.containsKey(ApplicationConstants.STATUS)? resultingStatusMap.get(custOrder.get(ApplicationConstants.STATUS).toString()) : "";
													if (resultingStatus.equalsIgnoreCase(ApplicationConstants.A) && "ACT".equals(parOrd.get(ApplicationConstants.STATUS))) {
															custOrder.put(ApplicationConstants.STATUS, "PAR");
													}
												}
											}
										}
									}
									bean.setFormInfoJson(objectMapper.writeValueAsString(custFormInfoJson));
									bean.setFormInfoJsonBlob(objectMapper.writeValueAsString(custFormInfoJson).getBytes());
									OdynfrmSubmitDataBean toUpdHtyOrd = legalsDataHty.stream().filter(i-> i.getFormIdentifier().equalsIgnoreCase(bean.getFormIdentifier())).findFirst().orElse(null);
									if(toUpdHtyOrd != null) {
										legalsDataHty.forEach(e -> {
											if(e.getFormIdentifier().equalsIgnoreCase(bean.getFormIdentifier())) {
												e.setFormInfoJson(bean.getFormInfoJson());
												e.setFormInfoJsonBlob(bean.getFormInfoJsonBlob());
											}
										});
									} else {
										OdynfrmSubmitDataBean toUpdatedCustOnPar = recordsToUpdate.stream().filter(i-> i.getFormIdentifier().equalsIgnoreCase(bean.getFormIdentifier())).findFirst().orElse(null);
										bean.setModifyUserId(modifyUserId);
										if(toUpdatedCustOnPar == null) {
											recordsToUpdate.add(bean);
										}
										legalsDataHty.add(bean);
									}
								}
							}
						}
					} catch (Exception e) {
						logger.error("Parole updated list iteration " + parOrder);
						logger.error("Error in Parole update list iteration " + e);
					}
				}
				Integer updResult = 0;
				if(recordsToUpdate != null && !recordsToUpdate.isEmpty()) {
					logger.info("updateLegalsData records to be updated list size {} ",recordsToUpdate.size());
					updResult = ocmpconfRepository.updateLegalsData(recordsToUpdate);
				} else {
					updResult = 1;
				}
				if(updResult == 1) {
					if(recordsToUpdate != null && !recordsToUpdate.isEmpty()) {
						updateCustodyStatus(recordsToUpdate, authorization);
					}
					if(legalsDataHty!=null && !legalsDataHty.isEmpty()) {
						ocmpconfService.setOcdleglsStatus(legalsDataHty);
					}
					if(holdRecordsUpdated.size()>0) {
						ocmpconfService.updateHoldExpiryOrders(holdRecordsUpdated, authorization);  //Sentence engine is triggered to get latest key dates
					}
					conditionUpdateOrders(conditionUpdateOrders,resultingStatusMap);
					updateSentStatus(legalsDataHty);
				}
			}
			responseMap.put("Response", "Query Executed Successfully");
			logger.info(this.getClass().getName()+ "Checking time  updateLegalsData executed successfully on  : "+ currTimeStamp);
		} catch (Exception e) {
			responseMap.put("Response", "Query Not Executed");
			logger.error( "In updateLegalsData: ", e);
		}
		responseList.add(responseMap);
		return;
		
	}
	
	private boolean getLinkedOrders(List<OdynfrmSubmitDataBean> legaloData, String formIdentifier, String displayNo) {
		boolean result = false;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			for(OdynfrmSubmitDataBean obj  : legaloData) {
				Map<String, String> formIdMap = objectMapper.readValue(obj.getFormIdentifier(), HashMap.class);
				Map<String, String> searchFormIdMap = objectMapper.readValue(formIdentifier, HashMap.class);
				if(formIdMap.get(ApplicationConstants.OFFENDER_BOOK_ID) != null && searchFormIdMap.get(ApplicationConstants.OFFENDER_BOOK_ID) != null && formIdMap.get("orderType") != null) {
					if(formIdMap.get(ApplicationConstants.OFFENDER_BOOK_ID).equals(searchFormIdMap.get(ApplicationConstants.OFFENDER_BOOK_ID)) && formIdMap.get("orderType").equals("PAR")) {
						obj.setFormInfoJson(new String(obj.getFormInfoJsonBlob()));
						Map<String,List<Map<String,Object>>> formInfoJson = objectMapper.readValue(obj.getFormInfoJson(), HashMap.class);
						for(Map<String,Object> myJsonRowDataObj :formInfoJson.get("myJsonRowData")) {
							List<String> affectedOrders = (List<String>) myJsonRowDataObj.get("affectedOrders");
							if(affectedOrders.contains(displayNo)) {
								return true;
							} else {
								result = false;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("In getLinkedOrders:", e);
		}
		return result;
	}
	
	private void updateConditions(Map<String,String> resultingStatusMap) {
		logger.info(this.getClass().getName()+ " updateConditions Triggered ");
		Date startDate = null;
		Date endDate = null;
		String automationUser = "";
		try {
			List<Map<String,Object>> returnList=oumsysetService.getSysData("serverConfig","AUTOMATION_USER");
			if(returnList!=null && !returnList.isEmpty()) {
				 for(Map<String,Object> object:returnList) {
		        	  if(object.containsKey("KEY_CODE") && object.get("KEY_CODE")!=null && object.get("KEY_CODE").equals("AUTOMATION_ELITE_USER") &&  object.containsKey("VALUE") && object.get("VALUE")!=null) {
		        		  automationUser = object.get("VALUE").toString();
						}
				 }
			}
			LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Date currTimeStamp = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT).parse(currentDate.toString());
			String statusflag = ocmpconfRepository.getStatusFlag("EACSU");
			if (ApplicationConstants.YES_FLAG.equals(statusflag)) {
				List<OffenderSentConditions> condData = ocmpconfRepository.getConditionsData();
				List<OffenderSentConditions> condToBeUpdated = new ArrayList<OffenderSentConditions>();
				for (OffenderSentConditions offenderSentConditions : condData) {
					boolean condUpdFlag = false;
					if (offenderSentConditions.getStartDate() != null) {
						startDate = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT)
								.parse(offenderSentConditions.getStartDate().toString());
					}
					if (offenderSentConditions.getExpiryDate() != null) {
						endDate = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT)
								.parse(offenderSentConditions.getExpiryDate().toString());
					}
					offenderSentConditions.setModifyUserId(automationUser);
					if (startDate != null && startDate.compareTo(currTimeStamp) < 1
							&& ApplicationConstants.PEND.equals(offenderSentConditions.getConditionStatus())) {
						offenderSentConditions.setConditionStatus(ApplicationConstants.ACT);
						condUpdFlag = true;
					}
					String resultingStatus = offenderSentConditions.getConditionStatus().equals("") ? ""
							: resultingStatusMap.get(offenderSentConditions.getConditionStatus());
					if (endDate != null && !offenderSentConditions.getAllocationFlag().equalsIgnoreCase(ApplicationConstants.YES) && ApplicationConstants.A.equals(resultingStatus)
							&& endDate.compareTo(currTimeStamp) < 0) {
						offenderSentConditions.setConditionStatus(ApplicationConstants.EXP);
						condUpdFlag = true;
					}
					if(condUpdFlag == true) {
						condToBeUpdated.add(offenderSentConditions);
					}
				}
				logger.info("updateConditions Update call : {} ", condToBeUpdated);
				if(condToBeUpdated != null && !condToBeUpdated.isEmpty()) {
					ocmpconfRepository.updateConditions(condToBeUpdated);
				}
				logger.info("updateConditions Update call success");
			}
		} catch (Exception e) {
			logger.error("In updateConditions:", e);
		}
	}
	
	private Integer updateCustodyStatus(List<OdynfrmSubmitDataBean> legalsData, String authorization) {
		int result = 0;
		List<OdynfrmSubmitDataBean> odynfrmDataList = new ArrayList<OdynfrmSubmitDataBean>();
		OdynfrmSubmitDataCommitBean commitBean = new OdynfrmSubmitDataCommitBean();
		List<OffenderCustodyStatus> offenderCustodyStatusList = new ArrayList<OffenderCustodyStatus>();
		try { 
			ObjectMapper objectMapper = new ObjectMapper();
			for(OdynfrmSubmitDataBean odynfrmSubmitDataBean : legalsData) {
				Map<String, String> formIdMap = objectMapper.readValue(odynfrmSubmitDataBean.getFormIdentifier(), HashMap.class);
				if("CUST".equals(formIdMap.get("orderType"))){
					String custodyStatus = oidadmisService.calculateCustodyStatus(Long.parseLong(formIdMap.get("offenderBookId")));
//					odynfrmSubmitDataBean.setCustodyStatus(custodyStatus);
					OffenderCustodyStatus offenderCustodyStatus = new OffenderCustodyStatus();
					offenderCustodyStatus.setCustodyStatus(custodyStatus);
					offenderCustodyStatus.setOffenderBookId(Long.parseLong(formIdMap.get("offenderBookId")));
					offenderCustodyStatus.setModifyUserId(odynfrmSubmitDataBean.getModifyUserId());
					offenderCustodyStatusList.add(offenderCustodyStatus);
					if(custodyStatus == null || "NO_STATUS".equals(custodyStatus) || custodyStatus.isEmpty()) {
						OdynfrmSubmitDataBean odynfrmData = new OdynfrmSubmitDataBean();
						odynfrmData.setCustodyStatus(custodyStatus);
						odynfrmData.setOffenderBookId(Long.parseLong(formIdMap.get("offenderBookId")));
						odynfrmDataList.add(odynfrmData);
					}
				}
			}
			result = ocdintakRepository.updateCustodyStatus(offenderCustodyStatusList);
			if(result > 0) {
				commitBean.setInsertList(odynfrmDataList);
				prosmainService.enableTriggers(commitBean, authorization, "113");
			}
		} catch (Exception e) {
			logger.error("In updateCustodyStatus: {} ", e.getMessage());
		}
		return result;
	}
	
	public Integer custodyStatusUpdate(String offenderBookId, String authorization) {
		int result = 0;
		String automationUser = "";
		OffenderCustodyStatus offenderCustodyStatus = new OffenderCustodyStatus();
		List<OffenderCustodyStatus> offenderCustodyStatusList = new ArrayList<OffenderCustodyStatus>();
		try { 
			List<Map<String,Object>> returnList=oumsysetService.getSysData("serverConfig","AUTOMATION_USER");
			if(returnList!=null && !returnList.isEmpty()) {
				 for(Map<String,Object> object:returnList) {
		        	  if(object.containsKey("KEY_CODE") && object.get("KEY_CODE")!=null && object.get("KEY_CODE").equals("AUTOMATION_ELITE_USER") &&  object.containsKey("VALUE") && object.get("VALUE")!=null) {
		        		  automationUser = object.get("VALUE").toString();
						}
				 }
			}
			String custodyStatus = oidadmisService.calculateCustodyStatus(Long.parseLong(offenderBookId));
			offenderCustodyStatus.setOffenderBookId(Long.parseLong(offenderBookId));
			offenderCustodyStatus.setModifyUserId(automationUser);
			offenderCustodyStatus.setCustodyStatus(custodyStatus);
			offenderCustodyStatusList.add(offenderCustodyStatus);
			result = ocdintakRepository.updateCustodyStatus(offenderCustodyStatusList);
			if(result > 0) {
				if(custodyStatus == null || "NO_STATUS".equals(custodyStatus) || custodyStatus.isEmpty()) {
					OdynfrmSubmitDataBean odynfrmData = new OdynfrmSubmitDataBean();
					List<OdynfrmSubmitDataBean> odynfrmDataList = new ArrayList<OdynfrmSubmitDataBean>();
					OdynfrmSubmitDataCommitBean commitBean = new OdynfrmSubmitDataCommitBean();
					odynfrmData.setOffenderBookId(Long.parseLong(offenderBookId));
					odynfrmData.setCustodyStatus(custodyStatus);
					odynfrmDataList.add(odynfrmData);
					commitBean.setInsertList(odynfrmDataList);
					prosmainService.enableTriggers(commitBean, authorization, "113");
				}
			}
		} catch (Exception e) {
			logger.error("In custodyStatusUpdate: {} ", e.getMessage());
		}
		return result;
	}
	
	
	public Integer sentStatusUpdate(String offenderBookId) {
		int result = 0;
		String sentStatus = "";
		String automationUser = "";
		OffenderBookings obj = new OffenderBookings();
		List<OffenderBookings> offenderBookingsList = new ArrayList<OffenderBookings>();
		try {
			List<Map<String,Object>> returnList=oumsysetService.getSysData("serverConfig","AUTOMATION_USER");
			if(returnList!=null && !returnList.isEmpty()) {
				for(Map<String,Object> object:returnList) {
					if(object.containsKey(ApplicationConstants.KEY_CODE) && object.get(ApplicationConstants.KEY_CODE)!=null && object.get(ApplicationConstants.KEY_CODE).equals("AUTOMATION_ELITE_USER") &&  object.containsKey(ApplicationConstants.VAL) && object.get(ApplicationConstants.VAL)!=null) {
						automationUser = object.get(ApplicationConstants.VAL).toString();
					}
				}
			}
			List<OdynfrmSubmitDataBean> legalSummaryData = new ArrayList<OdynfrmSubmitDataBean>();
			String formIdentifier = "{\"offenderBookId\":\""+offenderBookId+"\"}";
			OffenderBookings offenderBookings = cmdactionRepository.getBookingData(Long.parseLong(offenderBookId));
			if("COMM".equals(offenderBookings.getBookingType()) && "O".equals(offenderBookings.getBookingStatus())) {
				legalSummaryData = ocdintakRepository.getLegalSummaryData(formIdentifier);
				sentStatus = caclucateSentStatus(legalSummaryData.get(0), new BigDecimal(offenderBookings.getRootOffenderId().toString()).longValue());
			}
			obj.setCommStatus(sentStatus);
			obj.setOffenderBookId(Long.parseLong(offenderBookId));
			obj.setModifyUserId(automationUser);
			offenderBookingsList.add(obj);
			result = cmdactionRepository.updateSentStatus(offenderBookingsList);
		} catch (Exception e) {
			logger.error(this.getClass().getName(), " sentStatusUpdate and  Exception ", e);
		}
		return result;
	}
	
	public String caclucateSentStatus(OdynfrmSubmitDataBean odynfrmSubmitDataBeanSum, long rootOffenderId) {
		String sentStatus = "";
		String headerLabel = "";
		int headerSeq = -1;
		int activeOrders = 0;
		int orders = 0;
		try {
			Map<String,String> resultStatusMap = ocmpconfService.getResultingStatusMap();
			odynfrmSubmitDataBeanSum.setFormInfoJson(new String(odynfrmSubmitDataBeanSum.getFormInfoJsonBlob()));
			Map<String,List<Map<String,Object>>> formInfoJsonSum = new ObjectMapper().readValue(odynfrmSubmitDataBeanSum.getFormInfoJson(), HashMap.class);
			if(formInfoJsonSum.containsKey(ApplicationConstants.SENTENCE_DATES)) {
				List<Map<String, Object>> sentenceOrderDates = (List<Map<String, Object>>) formInfoJsonSum.get(ApplicationConstants.SENTENCE_DATES);
				for(Map<String,Object> sentOrdDate : sentenceOrderDates) {
					if(sentOrdDate.containsKey(ApplicationConstants.SENTENCEORDERTYPE) && (ApplicationConstants.NCUS.equals(sentOrdDate.get(ApplicationConstants.SENTENCEORDERTYPE)) ||
							ApplicationConstants.PAR.equals(sentOrdDate.get(ApplicationConstants.SENTENCEORDERTYPE)) || ApplicationConstants.BAIL.equals(sentOrdDate.get(ApplicationConstants.SENTENCEORDERTYPE)))) {
						orders++;
						String resultingStatus =  resultStatusMap.get(sentOrdDate.get(ApplicationConstants.STATUS).toString());
						if(ApplicationConstants.A.equals(resultingStatus)) {
							activeOrders++;
							SentenceCalcTypes sentenceCalc = new SentenceCalcTypes();
							sentenceCalc.setSentenceCategory(sentOrdDate.get(ApplicationConstants.SENTENCEORDERTYPE).toString());
							sentenceCalc.setSentenceCalcType(sentOrdDate.get(ApplicationConstants.SENTENCECALCTYPE).toString());
							SentenceCalcTypes sentenceCalcTypes = cmdactionRepository.getSentenceCalcTypes(sentenceCalc);
							if(sentenceCalcTypes.getHeaderSeq() != null && (headerSeq == -1 || new BigDecimal(sentenceCalcTypes.getHeaderSeq().toString()).intValue() < headerSeq)) {
								headerSeq = new BigDecimal(sentenceCalcTypes.getHeaderSeq().toString()).intValue();
								headerLabel = sentenceCalcTypes.getHeaderLabel();
							}
						}
					}
				}
			}
			if(orders == 0) {
				OffenderBookingEvent object = new OffenderBookingEvent();
				object.setRootOffenderId(rootOffenderId);
				Integer pBookCount = ocdintakRepository.oldContactPbookCount(object);
				if(pBookCount > 1) {
					sentStatus = ocdintakRepository.getProfileValueConstants(ApplicationConstants.CLIENT, ApplicationConstants.PROB_STATUS2);
				} else {
					sentStatus = ocdintakRepository.getProfileValueConstants(ApplicationConstants.CLIENT, ApplicationConstants.PROB_STATUS1);
				}
			} else if(activeOrders == 0) {
					sentStatus = ocdintakRepository.getProfileValueConstants(ApplicationConstants.CLIENT, ApplicationConstants.PROB_STATUS4);
			} else if (headerSeq > 0) {
					sentStatus = headerLabel;
			} else {
					sentStatus = ocdintakRepository.getProfileValueConstants(ApplicationConstants.CLIENT, ApplicationConstants.PROB_STATUS3);
			}
		} catch(Exception e) {
			logger.error(this.getClass().getName(), " caclucateSentStatus and  Exception ", e);
		}
		return sentStatus;
	}
	
	public Integer sentStatusUpdatewithStatus(String offenderBookId) {
		int result = 0;
		String sentStatus = "";
		String automationUser = "";
		OffenderBookings obj = new OffenderBookings();
		List<OffenderBookings> offenderBookingsList = new ArrayList<OffenderBookings>();
		try {
			List<Map<String,Object>> returnList=oumsysetService.getSysData("serverConfig","AUTOMATION_USER");
			if(returnList!=null && !returnList.isEmpty()) {
				for(Map<String,Object> object:returnList) {
					if(object.containsKey(ApplicationConstants.KEY_CODE) && object.get(ApplicationConstants.KEY_CODE)!=null && object.get(ApplicationConstants.KEY_CODE).equals("AUTOMATION_ELITE_USER") &&  object.containsKey(ApplicationConstants.VAL) && object.get(ApplicationConstants.VAL)!=null) {
						automationUser = object.get(ApplicationConstants.VAL).toString();
					}
				}
			}
			List<OdynfrmSubmitDataBean> legalSummaryData = new ArrayList<OdynfrmSubmitDataBean>();
			String formIdentifier = "{\"offenderBookId\":\""+offenderBookId+"\"}";
			OffenderBookings offenderBookings = cmdactionRepository.getBookingData(Long.parseLong(offenderBookId));
			if("COMM".equals(offenderBookings.getBookingType()) && "O".equals(offenderBookings.getBookingStatus())) {
				OdynfrmSubmitDataBean searchBean = new OdynfrmSubmitDataBean();
				searchBean.setFormName("OCDLEGLO");
				searchBean.setSearchString(offenderBookId);
				List<OdynfrmSubmitDataBean> legloOrders = ocmpconfService.getOffenderOrders(searchBean);
				
//				legalSummaryData = ocdintakRepository.getLegalSummaryData(formIdentifier);
				sentStatus = caclSentStatus(legloOrders, new BigDecimal(offenderBookings.getRootOffenderId().toString()).longValue());
			}
			obj.setCommStatus(sentStatus);
			obj.setOffenderBookId(Long.parseLong(offenderBookId));
			obj.setModifyUserId(automationUser);
			offenderBookingsList.add(obj);
			result = cmdactionRepository.updateSentStatus(offenderBookingsList);
		} catch (Exception e) {
			logger.error(this.getClass().getName(), " sentStatusUpdate and  Exception ", e);
		}
		return result;
	}

	public String caclSentStatus(List<OdynfrmSubmitDataBean> odynfrmSubmitDataList, long rootOffenderId) {
		String sentStatus = "";
		String headerLabel = "";
		int headerSeq = -1;
		int activeOrders = 0;
		int orders = 0;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String,String> resultStatusMap = ocmpconfService.getResultingStatusMap();
			for(OdynfrmSubmitDataBean OdynfrmSubmitDataBean : odynfrmSubmitDataList) {
				Map<String, String> formIdentifier = objectMapper.readValue(OdynfrmSubmitDataBean.getFormIdentifier(), HashMap.class);
				if(("NONCUST".equals(formIdentifier.get(ApplicationConstants.ORDERTYPE)) || ApplicationConstants.PAR.equals(formIdentifier.get(ApplicationConstants.ORDERTYPE)) || 
					ApplicationConstants.BAIL.equals(formIdentifier.get(ApplicationConstants.ORDERTYPE)))) {
					Map<String, Object> formInfoJson = objectMapper.readValue(OdynfrmSubmitDataBean.getFormInfoJson(), HashMap.class);
					List<Map<String, Object>> myJsonRowDataList = objectMapper.convertValue(formInfoJson.get("myJsonRowData"),ArrayList.class);
					if(myJsonRowDataList.size() > 0) {
						orders++;
						for(Map<String, Object> myJsonRowData : myJsonRowDataList) {
							String resultingStatus = resultStatusMap.get(myJsonRowData.get(ApplicationConstants.STATUS).toString());
							if(ApplicationConstants.A.equals(resultingStatus)) {
								activeOrders++;
								SentenceCalcTypes sentenceCalc = new SentenceCalcTypes();
								sentenceCalc.setSentenceCategory(myJsonRowData.get(ApplicationConstants.ORDERTYPE).toString());
								sentenceCalc.setSentenceCalcType(myJsonRowData.get(ApplicationConstants.SENTENCECALCTYPE).toString());
								SentenceCalcTypes sentenceCalcTypes = cmdactionRepository.getSentenceCalcTypes(sentenceCalc);
								if(sentenceCalcTypes.getHeaderSeq() != null && (headerSeq == -1 || new BigDecimal(sentenceCalcTypes.getHeaderSeq().toString()).intValue() < headerSeq)) {
									headerSeq = new BigDecimal(sentenceCalcTypes.getHeaderSeq().toString()).intValue();
									headerLabel = sentenceCalcTypes.getHeaderLabel();
								}
							}
						}
					}
				}
			}
			if(orders == 0) {
				OffenderBookingEvent object = new OffenderBookingEvent();
				object.setRootOffenderId(rootOffenderId);
				Integer pBookCount = ocdintakRepository.oldContactPbookCount(object);
				if(pBookCount > 1) {
					sentStatus = ocdintakRepository.getProfileValueConstants(ApplicationConstants.CLIENT, ApplicationConstants.PROB_STATUS2);
				} else {
					sentStatus = ocdintakRepository.getProfileValueConstants(ApplicationConstants.CLIENT, ApplicationConstants.PROB_STATUS1);
				}
			} else if(activeOrders == 0) {
					sentStatus = ocdintakRepository.getProfileValueConstants(ApplicationConstants.CLIENT, ApplicationConstants.PROB_STATUS4);
			} else if (headerSeq > 0) {
					sentStatus = headerLabel;
			} else {
					sentStatus = ocdintakRepository.getProfileValueConstants(ApplicationConstants.CLIENT, ApplicationConstants.PROB_STATUS3);
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName(), " caclSentStatus and  Exception ", e);
		}
		return sentStatus;
	}
	
	private Integer updateSentStatus(List<OdynfrmSubmitDataBean> ocdleglsData) {
		int result = 0;
		String automationUser = "";
		List<OffenderBookings> offenderBookingsList = new ArrayList<OffenderBookings>();
		try { 
			ObjectMapper objectMapper = new ObjectMapper();
			List<Map<String,Object>> returnList=oumsysetService.getSysData("serverConfig","AUTOMATION_USER");
			if(returnList!=null && !returnList.isEmpty()) {
				for(Map<String,Object> object:returnList) {
					if(object.containsKey(ApplicationConstants.KEY_CODE) && object.get(ApplicationConstants.KEY_CODE)!=null && object.get(ApplicationConstants.KEY_CODE).equals("AUTOMATION_ELITE_USER") &&  object.containsKey(ApplicationConstants.VAL) && object.get(ApplicationConstants.VAL)!=null) {
						automationUser = object.get(ApplicationConstants.VAL).toString();
					}	
				}
			}
//			List<OdynfrmSubmitDataBean> legalSummaryData = ocmpconfRepository.getLegalSummaryData();
			for(OdynfrmSubmitDataBean odynfrmSubmitDataBeanSum : ocdleglsData) {
				Map<String, String> formIdMap = objectMapper.readValue(odynfrmSubmitDataBeanSum.getFormIdentifier(), HashMap.class);
				OffenderBookings offenderBookings = cmdactionRepository.getBookingData(Long.parseLong(formIdMap.get(ApplicationConstants.OFFENDER_BOOK_ID)));
				if("COMM".equals(offenderBookings.getBookingType()) && "O".equals(offenderBookings.getBookingStatus())) {
					String sentStatus = caclucateSentStatus(odynfrmSubmitDataBeanSum, new BigDecimal(offenderBookings.getRootOffenderId().toString()).longValue());
					OffenderBookings obj = new OffenderBookings();
					obj.setCommStatus(sentStatus);
					obj.setOffenderBookId(Long.parseLong(formIdMap.get(ApplicationConstants.OFFENDER_BOOK_ID)));
					obj.setModifyUserId(automationUser);
					offenderBookingsList.add(obj);
				} else {
					OffenderBookings obj = new OffenderBookings();
					obj.setCommStatus("");
					obj.setOffenderBookId(Long.parseLong(formIdMap.get(ApplicationConstants.OFFENDER_BOOK_ID)));
					obj.setModifyUserId(automationUser);
					offenderBookingsList.add(obj);
				}
			}
			result = cmdactionRepository.updateSentStatus(offenderBookingsList);
		} catch (Exception e) {
			logger.error(this.getClass().getName(), " updateCustodyStatus and  Exception ", e);
		}
		return result;
	}
	
	private void conditionUpdateOrders(List<Map<String, Object>> ordersUpdated,Map<String,String> resultingStatusMap) {
		logger.info(this.getClass().getName()+ " conditionUpdateOrders Order Status Updated  : "+ ordersUpdated);
		String statusflag = ocmpconfRepository.getStatusFlag("EACSU");
		if (statusflag.equalsIgnoreCase(ApplicationConstants.YES_FLAG) && !ordersUpdated.isEmpty()) {
			try {
				Date startDate = null;
				Date endDate = null;
				List<OffenderSentConditions> condData = ocmpconfRepository.getConditionsData();
				LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				Date currTimeStamp = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT)
						.parse(currentDate.toString());
				List<OffenderSentConditions> updateList = new ArrayList<OffenderSentConditions>();
				for (Map<String, Object> order : ordersUpdated) {
					try {
						
						List<OffenderSentConditions> conditions = condData.stream().filter(
								i -> (i.getOffenderBookId() == Long.parseLong(order.get(ApplicationConstants.OFFENDER_BOOK_ID).toString()))&& (i.getSentenceSeq().compareTo(new BigDecimal(order.get("orderNo").toString()))==0)
								&& order.containsKey("orderType") &&  (i.getCommConditionType().equalsIgnoreCase(order.get("orderType").toString()))).collect(Collectors.toList());
						logger.info(this.getClass().getName()+ " conditionUpdateOrders Conditions of the order  : {} "+ conditions);
						if (conditions != null && !conditions.isEmpty()) {
							for (OffenderSentConditions offenderSentConditions : conditions) {
								if (!offenderSentConditions.getAllocationFlag().equalsIgnoreCase(ApplicationConstants.YES)) {
									if (offenderSentConditions.getStartDate() != null) {
										startDate = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT).parse(offenderSentConditions.getStartDate().toString());
									}
									if (offenderSentConditions.getExpiryDate() != null) {
										endDate = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT).parse(offenderSentConditions.getExpiryDate().toString());
									}
									if (order.get(ApplicationConstants.STATUS).toString().equalsIgnoreCase(ApplicationConstants.ACT) && (startDate != null && startDate.compareTo(currTimeStamp) < 1) && (endDate == null || ( endDate.compareTo(currTimeStamp) >= 0))) {
										offenderSentConditions.setConditionStatus(ApplicationConstants.ACT);
										updateList.add(offenderSentConditions);
										continue;
									}
									String resultingStatus = order.get(ApplicationConstants.STATUS).toString().equals(ApplicationConstants.A) ? ""
											:resultingStatusMap.get(order.get(ApplicationConstants.STATUS).toString());
									if (resultingStatus != null && !resultingStatus.equalsIgnoreCase(ApplicationConstants.A) &&  (endDate == null || ( endDate.compareTo(currTimeStamp) <1))) {
										offenderSentConditions.setConditionStatus(ApplicationConstants.EXP);
										updateList.add(offenderSentConditions);
										continue;
									}
								}
							}
						}
					} catch (Exception e) {
						logger.error( "In conditionUpdateOrders: ordersUpdated iteration {} ", e.getMessage());
						logger.error("Error Order  : {} ", order);
					}
				}
				logger.info(" conditionUpdateOrders Conditions to be updated  : {} ", updateList);
				ocmpconfRepository.updateConditions(updateList);
			} catch (Exception e) {
				logger.error("In conditionUpdateOrders: {} ", e.getMessage());
			}
		}
	}
	
	@Override
	@Async
	public String remissionDueNotify(Map<String, Object> newMemoModel) {
		String result = "";
		String mailResponse = "";
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			List<OdynfrmSubmitDataBean> legalSummaryData = ocmpconfRepository.getLegalSummaryData();
			List<Map<String, Object>> offenderList = new ArrayList<Map<String,Object>>();
			for(OdynfrmSubmitDataBean odynfrmSubmitDataBeanSum : legalSummaryData) {
				try {
					Map<String, Object> obj = new HashMap<String, Object>();
					obj.put(ApplicationConstants.OFFENDER_BOOK_ID, null);
					obj.put(ApplicationConstants.SENTENCE_RED, null);
					obj.put(ApplicationConstants.BOOKING_RED, null);
					obj.put(ApplicationConstants.REMISSION_DATE, null);
					
					Map<String, String> formIdMapSum = objectMapper.readValue(odynfrmSubmitDataBeanSum.getFormIdentifier(), HashMap.class);
					odynfrmSubmitDataBeanSum.setFormInfoJson(new String(odynfrmSubmitDataBeanSum.getFormInfoJsonBlob()));
					Map<String,List<Map<String,Object>>> formInfoJsonSum = objectMapper.readValue(odynfrmSubmitDataBeanSum.getFormInfoJson(), HashMap.class);
					long offenderBookId = Long.parseLong(formIdMapSum.get(ApplicationConstants.OFFENDER_BOOK_ID));
					if(formInfoJsonSum.containsKey(ApplicationConstants.SENTENCE_DATES) && formInfoJsonSum.get(ApplicationConstants.SENTENCE_DATES) != null && formInfoJsonSum.get(ApplicationConstants.SENTENCE_DATES).size() > 0) {
						List<Map<String, Object>> sentenceDates = (List<Map<String, Object>>) formInfoJsonSum.get(ApplicationConstants.SENTENCE_DATES);
						for(Map<String,Object> sentenceDate : sentenceDates) {
							if(sentenceDate.containsKey(ApplicationConstants.SENTENCEORDERTYPE) && (ApplicationConstants.CUSTORDER.equals(sentenceDate.get(ApplicationConstants.SENTENCEORDERTYPE)))) {
								if(sentenceDate.containsKey(ApplicationConstants.SENTENCE_ORDER_DATES)) {
									List<Map<String, Object>> sentenceOrderDates = (List<Map<String, Object>>) sentenceDate.get(ApplicationConstants.SENTENCE_ORDER_DATES);
									for(Map<String,Object> sentenceOrderDate : sentenceOrderDates) {
										if(sentenceOrderDate.containsKey(ApplicationConstants.DATE_TYPE) && ApplicationConstants.RED.equals(sentenceOrderDate.get(ApplicationConstants.DATE_TYPE)) && newMemoModel.get(ApplicationConstants.REMISSION_EXPIRY_DATE).equals(sentenceOrderDate.get(ApplicationConstants.EFFECTIVE_VALUE))) {
											obj.put(ApplicationConstants.OFFENDER_BOOK_ID, offenderBookId);
											obj.put(ApplicationConstants.SENTENCE_RED, ApplicationConstants.YES);
											obj.put(ApplicationConstants.REMISSION_DATE, newMemoModel.get(ApplicationConstants.REMISSION_DUE_DATE));
										}
									}
								}
							}
						}
					}
					if(formInfoJsonSum.containsKey(ApplicationConstants.BOOKING_DATES) && formInfoJsonSum.get(ApplicationConstants.BOOKING_DATES) != null && formInfoJsonSum.get(ApplicationConstants.BOOKING_DATES).size() > 0) {
						List<Map<String, Object>> bookingDates = (List<Map<String, Object>>) formInfoJsonSum.get(ApplicationConstants.BOOKING_DATES);
						for(Map<String,Object> bookingDate : bookingDates) {
							if(bookingDate.containsKey(ApplicationConstants.DATE_TYPE) && bookingDate.get(ApplicationConstants.DATE_TYPE).equals("booking_RED")) {
								if(bookingDate.containsKey(ApplicationConstants.OVERRIDE_DATE_VALUE) && newMemoModel.get(ApplicationConstants.REMISSION_EXPIRY_DATE).equals(bookingDate.get(ApplicationConstants.OVERRIDE_DATE_VALUE))) {
									obj.put(ApplicationConstants.OFFENDER_BOOK_ID, offenderBookId);
									obj.put(ApplicationConstants.BOOKING_RED, ApplicationConstants.YES);
									obj.put(ApplicationConstants.REMISSION_DATE, newMemoModel.get(ApplicationConstants.REMISSION_DUE_DATE));
								} else if(bookingDate.containsKey(ApplicationConstants.VALUE) && newMemoModel.get(ApplicationConstants.REMISSION_EXPIRY_DATE).equals(bookingDate.get(ApplicationConstants.VALUE))) {
									obj.put(ApplicationConstants.OFFENDER_BOOK_ID, offenderBookId);
									obj.put(ApplicationConstants.BOOKING_RED, ApplicationConstants.YES);
									obj.put(ApplicationConstants.REMISSION_DATE, newMemoModel.get(ApplicationConstants.REMISSION_DUE_DATE));
								}
							}
						}
					}
					if(obj.get(ApplicationConstants.OFFENDER_BOOK_ID) != null) {
						offenderList.add(obj);
					}
				} catch (Exception e) {
					logger.error("In remissionDueNotify Iterations for offfenderBookId : {} ", odynfrmSubmitDataBeanSum.getFormIdentifier());
					logger.error("In remissionDueNotify Iterations: {} ", e.getMessage());
				}
				
			}
			logger.info("In remissionDueNotify Offender List Size {} " ,offenderList.size());
			if(offenderList.size() > 0 && !"".equals(newMemoModel.get(ApplicationConstants.MAIL_ID))) {
				mailResponse = sendRemissionDueMail(offenderList, newMemoModel.get(ApplicationConstants.MAIL_ID).toString());
			}
			if(ApplicationConstants.SUCCESSMSG.equals(mailResponse)) {
				result = "Successfully Sent Remission Assessment Due Notification";
			} else {
				result = "Failed to Send Remission Assessment Due Notification";
			}
		} catch(Exception e) {
			result = "Failed to Send Remission Assessment Due Notification";
			logger.error("In remissionDueNotify: {} ", e.getMessage());
		}
		return result;
	}
	
	public String sendRemissionDueMail(List<Map<String, Object>> offenderList, String mailId) {
		String resonse = "";
		String offDetailsQuery = "select o.offender_id_display, o.last_name, o.first_name, to_char(o.birth_date, 'DD/MM/YYYY') as dob, case when ob.agy_loc_id is null then (select description from agency_locations al where agy_loc_id = ob.intake_agy_loc_id) else (select description from agency_locations al where agy_loc_id = ob.agy_loc_id) end as facility from offenders o, offender_bookings ob where o.offender_id = ob.offender_id and ob.offender_book_id = :offenderBookId";
		List<Map<String, Object>> offenderDetailsList = new ArrayList<Map<String,Object>>();
		try {
			for(Map<String, Object> offender: offenderList) {
				String body = "";
				SendMail sendMail = new SendMail();
				Map<String, Object> objMap = new HashMap<String, Object>();
				objMap.put(ApplicationConstants.OFFENDER_BOOK_ID, offender.get(ApplicationConstants.OFFENDER_BOOK_ID));
				offenderDetailsList  = cmdactionRepository.executeSelectQuery(objMap, offDetailsQuery);
				if(ApplicationConstants.YES.equals(offender.get(ApplicationConstants.BOOKING_RED)) && ApplicationConstants.YES.equals(offender.get(ApplicationConstants.SENTENCE_RED))) {
					body = "This email is to notify you that a Remission Assessment is now due for <br><br>" + offenderDetailsList.get(0).get("first_name") + "<br>" + offenderDetailsList.get(0).get("last_name") + "<br>" + offenderDetailsList.get(0).get("offender_id_display") + "<br>" + offenderDetailsList.get(0).get("dob") + "<br>" + offenderDetailsList.get(0).get("facility") + "<br><br>" + "who has an upcoming Remission Eligibility Date: <br>" + "Sentence RED: " + offender.get("remissionDate") + "<br>" + "Booking Red: " + offender.get("remissionDate") + "<br><br>" + "This is a system generated email from ASTRIA, please do not reply.";
				}  else if(ApplicationConstants.YES.equals(offender.get(ApplicationConstants.BOOKING_RED))) {
					body = "This email is to notify you that a Remission Assessment is now due for <br><br>" + offenderDetailsList.get(0).get("first_name") + "<br>" + offenderDetailsList.get(0).get("last_name") + "<br>" + offenderDetailsList.get(0).get("offender_id_display") + "<br>" + offenderDetailsList.get(0).get("dob") + "<br>" + offenderDetailsList.get(0).get("facility") + "<br><br>" + "who has an upcoming Remission Eligibility Date: <br>" + "Booking Red: " + offender.get("remissionDate") + "<br><br>" + "This is a system generated email from ASTRIA, please do not reply.";
				} else {
					body = "This email is to notify you that a Remission Assessment is now due for <br><br>" + offenderDetailsList.get(0).get("first_name") + "<br>" + offenderDetailsList.get(0).get("last_name") + "<br>" + offenderDetailsList.get(0).get("offender_id_display") + "<br>" + offenderDetailsList.get(0).get("dob") + "<br>" + offenderDetailsList.get(0).get("facility") + "<br><br>" + "who has an upcoming Remission Eligibility Date: <br>" + "Sentence RED: " + offender.get("remissionDate") + "<br><br>" + "This is a system generated email from ASTRIA, please do not reply.";
				}
				sendMail.setToId(mailId);
				sendMail.setFromId(ApplicationConstants.DONOT_REPLY);
				sendMail.setSubject("Please commence a Remission assessment");
				sendMail.setBody(body);
				sendmailService.sendMail(sendMail);
			}
			resonse = ApplicationConstants.SUCCESSMSG;
		} catch(Exception e) {
			logger.error("In remissionDueNotify: {} ", e.getMessage());
		}
		return resonse;
	}
	
	@Override
	@Async
	public String saveOffAllowPayDetValues(OffAllowPayDetailsCommitBean commitBean) {
		logger.info("SaveOffAllowPayDetValues is called ");
		ocdcgpayService.saveOffAllowPayDetValues(commitBean);
		logger.info("SaveOffAllowPayDetValues is Executed ");
		return ApplicationConstants.SUCCESSMSG;
	}

}

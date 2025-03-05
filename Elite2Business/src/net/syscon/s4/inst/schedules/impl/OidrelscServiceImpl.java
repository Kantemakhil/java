package net.syscon.s4.inst.schedules.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;
import net.syscon.s4.common.beans.OffenceByStatute;
import net.syscon.s4.common.beans.OffenceIndicator;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalconfiguration.OcmpconfRepository;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.OffenderAlerts;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogs;
import net.syscon.s4.inst.casemanagement.beans.WorkFlows;
import net.syscon.s4.inst.demographicsbiometrics.OcdalertRepository;
import net.syscon.s4.inst.legalscreens.OcmorcodRepository;
import net.syscon.s4.inst.legalscreens.maintenance.OimoffenRepository;
import net.syscon.s4.inst.schedules.OidrelscRepository;
import net.syscon.s4.inst.schedules.OidrelscService;
import net.syscon.s4.inst.schedules.bean.KeyDateValueBean;
import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetailKeyDatesBean;
import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetails;
import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetailsCommitBean;
import net.syscon.s4.inst.schedules.maintenance.OimrelscRepository;
import net.syscon.s4.inst.schedules.maintenance.bean.ReleaseSchedulesSettingsBean;
import net.syscon.s4.iwp.OcdenforRepository;
import net.syscon.s4.pkgs.osinames.OsinamesPkgService;
import net.syscon.s4.triggers.OffenderReleaseDetailsT2Service;

/**
 * Class OidrelscServiceImpl
 */
@Service
public class OidrelscServiceImpl extends BaseBusiness implements OidrelscService {

	@Autowired
	private OidrelscRepository oidrelscRepository;

	@Autowired
	private OsinamesPkgService osinamesService;

	@Autowired
	private OffenderReleaseDetailsT2Service offenderReleaseDetailsT2Service;

	@Autowired
	private OcdalertRepository ocdalertDao;

	@Autowired
	private OcmpconfRepository ocmpconfRepository;
	
	@Autowired
	private OcdenforRepository ocdenforRepository;

	@Autowired
	private OimoffenRepository oimoffenRepository;

	@Autowired
	private OimrelscRepository oimrelscRepository;
	
	@Autowired
	private OcmorcodRepository ocmorcodRepository;

	private static Logger logger = LogManager.getLogger(OidrelscServiceImpl.class.getName());

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderReleaseDetails> offRelDetExecuteQuery(final OffenderReleaseDetails searchRecord) {
		List<OffenderReleaseDetails> offenderList = new ArrayList<>();
		VHeaderBlock vheaderBlock;
		VHeaderBlock returnVheader;
		offenderList = oidrelscRepository.offRelDetExecuteQuery(searchRecord);
		for (final OffenderReleaseDetails object : offenderList) {
			vheaderBlock = new VHeaderBlock();
			vheaderBlock.setOffenderBookId(BigDecimal.valueOf(object.getOffenderBookId()));
			String offenderIdDisplay = oidrelscRepository.getOffIdDisplay(object.getOffenderBookId(),
					searchRecord.getCreateUserId());
			vheaderBlock.setOffenderIdDisplay(offenderIdDisplay);
			object.setOffenderIdDisplay(offenderIdDisplay);
			returnVheader = getOffDetailsByBookId(vheaderBlock);
			object.setLastName(returnVheader.getLastName());
			object.setFirstName(returnVheader.getFirstName());
			object.setNbtName(returnVheader.getLastName() + "," + returnVheader.getFirstName());
			object.setOffenderId(vheaderBlock.getOffenderId());
			object.setRootOffenderId(vheaderBlock.getRootOffenderId());
		}

		return offenderList;

	}

	private VHeaderBlock getOffDetailsByBookId(final VHeaderBlock bean) {
		VHeaderBlock returnVheader = new VHeaderBlock();
		Map<String, Object> offDetBookMap = osinamesService.getOffDetailsByBookId(bean.getOffenderBookId().longValue());
		returnVheader.setFirstName(
				offDetBookMap.get("P_FIRST_NAME") != null ? String.valueOf(offDetBookMap.get("P_FIRST_NAME")) : null);
		returnVheader.setLastName(
				offDetBookMap.get("P_LAST_NAME") != null ? String.valueOf(offDetBookMap.get("P_LAST_NAME")) : null);
		returnVheader.setAgyLocId(
				offDetBookMap.get("P_AGY_LOC_ID") != null ? String.valueOf(offDetBookMap.get("P_AGY_LOC_ID")) : null);
		return returnVheader;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_REL_DET
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<OffenderReleaseDetails> offRelDetCommit(final OffenderReleaseDetailsCommitBean commitBean) {
		final List<OffenderReleaseDetails> returnList = new ArrayList<>();
		final OffenderReleaseDetails returnObject = new OffenderReleaseDetails();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OffenderReleaseDetails bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
				if (bean.getEventId() == null) {
					bean.setEventId(oidrelscRepository.getEventId());
				}
				liReturn = oidrelscRepository.offRelDetInsertOffenderReleaseDetails(commitBean.getInsertList());
				for (OffenderReleaseDetails bean1 : commitBean.getInsertList()) {
					bean1.setNbtName("INSERTING");
					offenderReleaseDetailsT2Service.offenderReleaseDetailsT2(bean1);
				}
			}
			returnObject.setReturnValue(liReturn);
			// Trigger call
		}
		
		
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for(OffenderReleaseDetails bean:commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oidrelscRepository.offRelDetUpdateOffenderReleaseDetails(commitBean.getUpdateList());
			WorkFlows workFlowObj = new WorkFlows();
			workFlowObj.setObjectCode("RELEASE");
			workFlowObj.setObjectId(new BigDecimal(commitBean.getUpdateList().get(0).getOffenderBookId()));
			workFlowObj.setObjectSeq(new BigDecimal(commitBean.getUpdateList().get(0).getOffenderBookId()));
			WorkFlowLogs workFlowLogsObj = oidrelscRepository.getWorkFLowMaxID(commitBean.getUpdateList().get(0).getOffenderBookId(), 1L);
			if(workFlowLogsObj!=null && workFlowLogsObj.getWorkFlowId() != null && workFlowLogsObj.getWorkFlowId() != 0 && !"Y".equalsIgnoreCase(commitBean.getUpdateList().get(0).getVerifyPopUpCloseFlag())){
				WorkFlowLogs logsaveBean  = new WorkFlowLogs();
				logsaveBean.setWorkFlowId(workFlowLogsObj.getWorkFlowId());
				logsaveBean.setWorkFlowSeq(workFlowLogsObj.getWorkFlowSeq()+1);
					logsaveBean.setCreateUserId(commitBean.getCreateUserId());
					logsaveBean.setWorkFlowStatus("DONE");
					logsaveBean.setWorkActionCode("MOD");
					oidrelscRepository.insertIntoWorkFlowLogs(logsaveBean);
			}
			returnObject.setReturnValue(liReturn);
		}
		returnList.add(returnObject);
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> rgAgyLocationsRecordGroup(final String caseloadId) {
		List<AgencyLocations> returnList = new ArrayList<>();
		Integer order = 0;
		returnList = oidrelscRepository.rgAgyLocationsRecordGroup(caseloadId);
		for (AgencyLocations agencyLocations : returnList) {
			order = order + 1;
			agencyLocations.setListSeq(order);
		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgDateTypeRecordGroup() {
		List<ReferenceCodes> returnList = new ArrayList<>();
		returnList = oidrelscRepository.rgDateTypeRecordGroup();
		Integer order = 0;
		for (ReferenceCodes referenceCodes : returnList) {
			order = order + 1;
			referenceCodes.setListSeq(BigDecimal.valueOf(order));
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<MovementReasons> rgMovementReasonsRecordGroup() {
		return oidrelscRepository.rgMovementReasonsRecordGroup();

	}

	@Override
	public Long ctlWhenValidateRecord(final OffenderReleaseDetails paramBean) {
		return oidrelscRepository.ctlWhenValidateRecord(paramBean);
	}

	@Override
	public List<WorkFlows> verificationButton(WorkFlows paramBean) {
		List<WorkFlows> returnList = new ArrayList<>();
		paramBean = oidrelscRepository.insertWorkFlowEnt(paramBean);
		paramBean = oidrelscRepository.getMaxFlowId(paramBean);
		if (paramBean.getWorkFlowId() != 0) {
			returnList = checkWorkFlow(paramBean);
		}
		returnList.add(paramBean);
		return returnList;
	}

	private List<WorkFlows> checkWorkFlow(WorkFlows paramBean) {
		final List<WorkFlows> returnList = new ArrayList<>();
		Integer vcount = null;
		vcount = oidrelscRepository.chkWorkFlows(paramBean);
		paramBean.setReturnValue(vcount);
		returnList.add(paramBean);
		return returnList;
	}

	@Override
	public List<WorkFlows> insWorkFlows(WorkFlows paramBean) {
		List<WorkFlows> returnList = new ArrayList<>();
		paramBean = oidrelscRepository.insertWorkFlowEnt(paramBean);
		paramBean = oidrelscRepository.getMaxFlowId(paramBean);
		if (paramBean.getWorkFlowId() == 0) {
			returnList = insertWorkFlowObject(paramBean);
		}
		return returnList;
	}

	private List<WorkFlows> insertWorkFlowObject(WorkFlows paramBean) {
		Long curserCount = null;
		BigDecimal maxCurser = null;
		Long workflowSeq = null;
		Integer returnValue = 0;
		final WorkFlowLogs logsaveBean = new WorkFlowLogs();
		final List<WorkFlows> returnList = new ArrayList<>();
		curserCount = oidrelscRepository.getCountCursor(paramBean);
		maxCurser = oidrelscRepository.insertWorkFlowEntInsert(paramBean);
		paramBean.setObjectSeq(maxCurser);
		if (curserCount == 0) {
			workflowSeq = oidrelscRepository.getWorkFlowInsertSequence();
			paramBean.setWorkFlowId(workflowSeq);
			logsaveBean.setCreateDate(paramBean.getCreateDate());
			logsaveBean.setWorkFlowId(workflowSeq);
			logsaveBean.setCreateUserId(paramBean.getCreateUserId());
			logsaveBean.setWorkFlowStatus("DONE");
			logsaveBean.setWorkActionCode("ENT");
			logsaveBean.setWorkFlowSeq(1);
		}

		returnValue = oidrelscRepository.insertIntoWorkFlows(paramBean);
		returnValue = oidrelscRepository.insertIntoWorkFlowLogs(logsaveBean);
		paramBean.setReturnValue(returnValue);
		returnList.add(paramBean);
		return returnList;
	}

	@Override
	public List<WorkFlows> updWorkFlows(final WorkFlows paramBean) {
		return null;
	}

	@Override
	public List<VHeaderBlock> getOffenderList(VHeaderBlock paramBean) {
		return oidrelscRepository.getOffenderList(paramBean);
	}

	@Override
	public List<OffenderReleaseDetails> offRelDetLegalExecuteQuery(OffenderReleaseDetails searchBean) {
		List<OffenderReleaseDetails> returnListFinal = new ArrayList<OffenderReleaseDetails>();
		List<OffenderReleaseDetails> returnListFinalDates = new ArrayList<OffenderReleaseDetails>();
		List<OffenderReleaseDetailKeyDatesBean> returnList = new ArrayList<OffenderReleaseDetailKeyDatesBean>();
		List<OffenderReleaseDetailKeyDatesBean> returnListDates = new ArrayList<OffenderReleaseDetailKeyDatesBean>();
		List<OffenderReleaseDetailKeyDatesBean> offidListResult = new ArrayList<OffenderReleaseDetailKeyDatesBean>();
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String datePattern = "\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}";
		List<ReferenceCodes> keyDateRetList=new ArrayList<ReferenceCodes>();
		String keyDate = null;
		try {
			returnList = oidrelscRepository.offRelDetLegalExecuteQuery(searchBean);
			ReleaseSchedulesSettingsBean objectData = new ReleaseSchedulesSettingsBean();
			List<ReleaseSchedulesSettingsBean> maintainData = oimrelscRepository.retrieveGridData(objectData);
			keyDateRetList = rgKeyDatesRecordGroup();
			for (OffenderReleaseDetailKeyDatesBean obj : returnList) {
				Integer existRecord=0;
				final ObjectMapper mapper = new ObjectMapper();
				HashMap returnedObj = (HashMap) mapper.readValue(obj.getFormInfoJson(), Map.class);		
				if(!returnedObj.containsKey(ApplicationConstants.BOOKING_DATES)) {
					continue;
				}
				List<HashMap<String, Object>> formInfoJsonList = new ArrayList<HashMap<String, Object>>();
						formInfoJsonList = (List<HashMap<String, Object>>) returnedObj.get(ApplicationConstants.BOOKING_DATES);
						if( obj.getReleaseDate()!= null ) {
							HashMap<String, Object> newKeyDateMap = new LinkedHashMap<>();
							newKeyDateMap.put(ApplicationConstants.DATE_TYPE, ApplicationConstants.BOOKING_CRD);
							newKeyDateMap.put("dateValue", inputFormat.format(obj.getReleaseDate()));							
							formInfoJsonList.add(newKeyDateMap);
							
						}
						
				List<KeyDateValueBean> keyDateList = new ArrayList<KeyDateValueBean>();
				for (HashMap<String, Object> jsonObject : formInfoJsonList) {
					if (jsonObject.containsKey(ApplicationConstants.DATE_TYPE)
							&& jsonObject.get(ApplicationConstants.DATE_TYPE) != null) {
						KeyDateValueBean beanObj = new KeyDateValueBean();
						
						if ((jsonObject.containsKey(ApplicationConstants.VALUE)
								&& jsonObject.get(ApplicationConstants.VALUE) != null) || (jsonObject.containsKey(ApplicationConstants.OVERRIDE_DATE_VALUE)
								&& (jsonObject.get(ApplicationConstants.OVERRIDE_DATE_VALUE) != null && jsonObject.get(ApplicationConstants.OVERRIDE_DATE_VALUE) != ""))
									|| (jsonObject.containsKey(ApplicationConstants.OVERRIDE_INDEFINITE))) {
							
							if(jsonObject.get(ApplicationConstants.DATE_TYPE).toString().equalsIgnoreCase(ApplicationConstants.BOOKING_HOLD_EXPIRY_DATE)) {
								beanObj.setDateType(String.valueOf(ApplicationConstants.BOOKING_HED));
							} else {
								beanObj.setDateType(String.valueOf(jsonObject.get(ApplicationConstants.DATE_TYPE)));
							}
							if(jsonObject.containsKey(ApplicationConstants.OVERRIDE_DATE_VALUE) && jsonObject.get(ApplicationConstants.OVERRIDE_DATE_VALUE)!=null && jsonObject.get(ApplicationConstants.OVERRIDE_DATE_VALUE) != "") {
								beanObj.setDateValue(String.valueOf(jsonObject.get(ApplicationConstants.OVERRIDE_DATE_VALUE)));							
								inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
								String date = String.valueOf(jsonObject.get(ApplicationConstants.OVERRIDE_DATE_VALUE));
								
								boolean isDate = date.matches(datePattern);
								if (isDate) {
									Date dates = inputFormat
											.parse(String.valueOf(jsonObject.get(ApplicationConstants.OVERRIDE_DATE_VALUE)));
									if (!dates.before(searchBean.getFromDate()) && !dates.after(searchBean.getToDate())) {
										beanObj.setDateValue(dates.toString());
										for (ReferenceCodes referenceCodes : keyDateRetList) {
											if (referenceCodes.getCode().equalsIgnoreCase(beanObj.getDateType())) {
												existRecord =1;
												keyDateList.add(beanObj);
												break;
											}
										}

									}
								}
								
							} else if(jsonObject.containsKey(ApplicationConstants.OVERRIDE_INDEFINITE) && jsonObject.get(ApplicationConstants.OVERRIDE_INDEFINITE).toString().equalsIgnoreCase("true")) {
								continue;
							}else {								
								beanObj.setDateValue(String.valueOf(jsonObject.get(ApplicationConstants.VALUE)));								
								
								String date = String.valueOf(jsonObject.get(ApplicationConstants.VALUE));
								 datePattern = "\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}";
								boolean isDate = date.matches(datePattern);
								if (isDate) {
									Date dates = inputFormat
											.parse(String.valueOf(jsonObject.get(ApplicationConstants.VALUE)));
									if (!dates.before(searchBean.getFromDate()) && !dates.after(searchBean.getToDate())) {
										beanObj.setDateValue(dates.toString());
										for (ReferenceCodes referenceCodes : keyDateRetList) {
											if (referenceCodes.getCode().equalsIgnoreCase(beanObj.getDateType())) {
												existRecord =1;
												keyDateList.add(beanObj);
												break;
											}
										}

									}
								}
							}
						}
					}
				}
				obj.setKeyDateListObj(keyDateList);
				if(existRecord == 1) {
					returnListDates.add(obj);
				}
			}
			if (searchBean.getDateType() != null) {
				for (OffenderReleaseDetailKeyDatesBean dateTypeObj : returnListDates) {
					List<KeyDateValueBean> dateList = new ArrayList<KeyDateValueBean>();
					for (KeyDateValueBean list : dateTypeObj.getKeyDateListObj()) {
						String dtType = list.getDateType().toUpperCase();
						if (dtType != null && dtType.equals(searchBean.getDateType())) {
							dateList.add(list);
						}
					}
					dateTypeObj.setKeyDateListObj(dateList);
				}
			}
			if (searchBean.getOffenderIdDisplay() != null &&  !searchBean.getOffenderIdDisplay().equals("")) {
				for (OffenderReleaseDetailKeyDatesBean offIdCheckObj : returnListDates) {
						if (searchBean.getOffenderIdDisplay().equals(offIdCheckObj.getOffenderIdDisplay())) {
							returnListDates = new ArrayList<OffenderReleaseDetailKeyDatesBean>();
							returnListDates.add(offIdCheckObj);
							offidListResult.add(offIdCheckObj);
						} 
				else if(searchBean.getOffenderIdDisplay().length() < 10) {
							String offIdDisplay=null;
							offIdDisplay = searchBean.getOffenderIdDisplay();
							for (int i = offIdDisplay.length(); i < 10; i++) {							
								offIdDisplay= "0" + offIdDisplay;
							}						
							if (offIdDisplay.equals(offIdCheckObj.getOffenderIdDisplay())) {
								returnListDates = new ArrayList<OffenderReleaseDetailKeyDatesBean>();
								returnListDates.add(offIdCheckObj);
								offidListResult.add(offIdCheckObj);
							} 						
						}
				}
				if(offidListResult.isEmpty()) {
					returnListDates = new ArrayList<OffenderReleaseDetailKeyDatesBean>();
				}
			}			
			for (OffenderReleaseDetailKeyDatesBean finalObj : returnListDates) {
				if (!finalObj.getKeyDateListObj().isEmpty()) {					
					OffenderReleaseDetails ordDisplayObj = new OffenderReleaseDetails();
					String alertsData = null;
					String indicatorsData = null;
					List<KeyDateValueBean> keyDateListOff = new ArrayList<KeyDateValueBean>();
					ordDisplayObj.setOffenderIdDisplay(finalObj.getOffenderIdDisplay());
					ordDisplayObj.setOffenderBookId(finalObj.getOffenderBookId().longValue());
					ordDisplayObj.setNbtName(finalObj.getNbtName());
					List<OffenderReleaseDetailKeyDatesBean> offenderBookIdBasedData = new ArrayList<OffenderReleaseDetailKeyDatesBean>();
					offenderBookIdBasedData = oidrelscRepository
							.getOffenderKeyDatesBasedOnBookId(finalObj.getOffenderBookId());
					if (!offenderBookIdBasedData.isEmpty()) {
						final ObjectMapper mapper = new ObjectMapper();					
						HashMap returnedObj = (HashMap) mapper.readValue(offenderBookIdBasedData.get(0).getFormInfoJson(), Map.class);				
						List<HashMap<String, Object>> formInfoJsonList = new ArrayList<HashMap<String, Object>>();
								formInfoJsonList = (List<HashMap<String, Object>>) returnedObj.get(ApplicationConstants.BOOKING_DATES);
															
								if( finalObj.getReleaseDate()!= null) {
									HashMap<String, Object> newKeyDateMap = new LinkedHashMap<>();
									newKeyDateMap.put(ApplicationConstants.DATE_TYPE, ApplicationConstants.BOOKING_CRD);
									newKeyDateMap.put("dateValue", inputFormat.format(finalObj.getReleaseDate()));									
									formInfoJsonList.add(newKeyDateMap);									
								}
								
						for (HashMap<String, Object> jsonObject : formInfoJsonList) {
							if (jsonObject.containsKey(ApplicationConstants.DATE_TYPE)
									&& jsonObject.get(ApplicationConstants.DATE_TYPE) != null) {
								KeyDateValueBean beanObj = new KeyDateValueBean();
								
								if ((jsonObject.containsKey(ApplicationConstants.VALUE)
										&& jsonObject.get(ApplicationConstants.VALUE) != null) || (jsonObject.containsKey(ApplicationConstants.OVERRIDE_DATE_VALUE)
												&& (jsonObject.get(ApplicationConstants.OVERRIDE_DATE_VALUE) != null && jsonObject.get(ApplicationConstants.OVERRIDE_DATE_VALUE) != ""))
									|| (jsonObject.containsKey(ApplicationConstants.OVERRIDE_INDEFINITE))) {
									
									if(jsonObject.get(ApplicationConstants.DATE_TYPE).toString().equalsIgnoreCase(ApplicationConstants.BOOKING_HOLD_EXPIRY_DATE)) {
										beanObj.setDateType(String.valueOf(ApplicationConstants.BOOKING_HED));
									} else {
										beanObj.setDateType(String.valueOf(jsonObject.get(ApplicationConstants.DATE_TYPE)));
									}
									
									if(jsonObject.containsKey(ApplicationConstants.OVERRIDE_DATE_VALUE) && jsonObject.get(ApplicationConstants.OVERRIDE_DATE_VALUE)!=null && jsonObject.get(ApplicationConstants.OVERRIDE_DATE_VALUE) != "") {										
										beanObj.setDateValue(String.valueOf(jsonObject.get(ApplicationConstants.OVERRIDE_DATE_VALUE)));
										String date = String.valueOf(jsonObject.get(ApplicationConstants.OVERRIDE_DATE_VALUE));
										 datePattern = "\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}";
										boolean isDate = date.matches(datePattern);
										if (isDate) {
											keyDate = (String.valueOf(jsonObject.get(ApplicationConstants.DATE_TYPE)))
													.concat(", ");
											keyDateListOff.add(beanObj);
										}
									}  else if((jsonObject.containsKey(ApplicationConstants.OVERRIDE_INDEFINITE) && jsonObject.get(ApplicationConstants.OVERRIDE_INDEFINITE).toString().equalsIgnoreCase("true")) || (jsonObject.containsKey(ApplicationConstants.INDEFINITE_KEY_DATE) && jsonObject.get(ApplicationConstants.INDEFINITE_KEY_DATE).toString().equalsIgnoreCase("true"))) {
										beanObj.setDateValue("Indefinite");
										keyDateListOff.add(beanObj);
									}  		
									else {
										beanObj.setDateValue(String.valueOf(jsonObject.get(ApplicationConstants.VALUE)));
										String date = String.valueOf(jsonObject.get(ApplicationConstants.VALUE));
										 datePattern = "\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}";
										boolean isDate = date.matches(datePattern);
										if (isDate) {
											keyDate = (String.valueOf(jsonObject.get(ApplicationConstants.DATE_TYPE)))
													.concat(", ");
											keyDateListOff.add(beanObj);
										}
									}
																	
								}
							}
						}

					}
					ordDisplayObj.setKeyDateListObj(keyDateListOff);
					List<OffenderAlerts> lstOffenderAlerts = new ArrayList<OffenderAlerts>();
					List<OffenderAlerts> mappingAlerts = new ArrayList<OffenderAlerts>();
					OffenderAlerts searchAlertBean = new OffenderAlerts();
					searchAlertBean.setOffenderBookId(finalObj.getOffenderBookId().longValue());
					lstOffenderAlerts = ocdalertDao.searchOffenderAlerts(searchAlertBean);
					if (!lstOffenderAlerts.isEmpty()) {
						for (OffenderAlerts alertObj : lstOffenderAlerts) {
							if (!maintainData.isEmpty()) {
								for (ReleaseSchedulesSettingsBean releaseSchedulesSettingsBean : maintainData) {
									if ("ALERTS".equals(releaseSchedulesSettingsBean.getRelSchSettingType())) {
										final ObjectMapper mapper = new ObjectMapper();
										List<HashMap<String, Object>> jsonMapTwo = mapper.readValue(
												releaseSchedulesSettingsBean.getRelSchSettingValue(),
												new TypeReference<List<HashMap<String, Object>>>() {
												});
										for (HashMap<String, Object> jsonObject : jsonMapTwo) {
											if (jsonObject.containsKey(ApplicationConstants.ALERT_TYPE)
													&& jsonObject.get(ApplicationConstants.ALERT_TYPE) != null
													&& jsonObject.containsKey(ApplicationConstants.ALERT_CODE)
													&& jsonObject.get(ApplicationConstants.ALERT_CODE) != null) {
												if (jsonObject.get(ApplicationConstants.ALERT_TYPE).toString()
														.equals(alertObj.getAlertType())
														&& jsonObject.get(ApplicationConstants.ALERT_CODE).toString()
																.equals(alertObj.getAlertCode())) {
													if (jsonObject.containsKey(ApplicationConstants.LIST_SEQ) && jsonObject.get(ApplicationConstants.LIST_SEQ)!=null) {
														alertObj.setSeqValue(
																Integer.valueOf(jsonObject.get(ApplicationConstants.LIST_SEQ).toString()));
													} else {
														alertObj.setSeqValue(Integer.valueOf(0));
													}
													mappingAlerts.add(alertObj);
												}
											}
										}
									}
								}
							}
						}
						if (!mappingAlerts.isEmpty()) {
							List<OffenderAlerts> newList = mappingAlerts.stream()
									.filter(distinctByKey(p -> p.getAlertType())).collect(Collectors.toList());
							newList.sort((o1, o2) -> o1.getSeqValue().compareTo(o2.getSeqValue()));
							for (OffenderAlerts gridSettingObj : newList) {
								if (alertsData != null) {
									alertsData = alertsData.concat(gridSettingObj.getAlertType().concat(", "));
								} else {
									alertsData = gridSettingObj.getAlertType().concat(",");
								}
							}
						}
						ordDisplayObj.setAlertsList(lstOffenderAlerts);

						if (alertsData != null) {
							alertsData = alertsData.toString().trim();

							if (alertsData.endsWith(",")) {
								alertsData = alertsData.trim().substring(0, alertsData.length() - 1);
							}
						}
						ordDisplayObj.setAlertsData(alertsData);
					}
					OdynfrmSubmitDataBean inputBean = new OdynfrmSubmitDataBean();
					OdynfrmSubmitDataBean outputBean = new OdynfrmSubmitDataBean();
					inputBean.setFormName("ocdchgsu");
					inputBean.setSearchString("{" + '"' + "offenderBookId" + '"' + ":" + '"'
							+ String.valueOf(finalObj.getOffenderBookId()) + '"' + "}");
					outputBean = ocmpconfRepository.getFormData(inputBean);
					if(outputBean.getFormInfoJson()!=null) {
						
						List<OffenceByStatute> searchResult = new ArrayList<>();
						searchResult = oimoffenRepository.getOffencesOnStatuteList();
						final ObjectMapper chargeMap = new ObjectMapper();					
						List<HashMap<String, Object>> jsonMap =
								chargeMap.readValue(outputBean.getFormInfoJson(), new
										TypeReference<List<HashMap<String, Object>>>() { });						
						List<OffenceIndicator> finalindicatList = new ArrayList<>();
						List<OffenceIndicator> indicatList = new ArrayList<>();
						List<OffenceIndicator> mappingIndicatList = new ArrayList<>();
						for (OffenceByStatute object : searchResult) {
							for (HashMap<String, Object> jsonObject : jsonMap) {
								try {
									if (jsonObject.containsKey(ApplicationConstants.OFFENCEID)
											&& jsonObject.get(ApplicationConstants.OFFENCEID) != null
											&& object.getOffenceId().compareTo(Long.parseLong(jsonObject.get(ApplicationConstants.OFFENCEID).toString())) == 0) {
										OffenceIndicator searchObj = new OffenceIndicator();
										searchObj.setOffenceId(object.getOffenceId());
										if(jsonObject.containsKey(ApplicationConstants.OUTCOME)&& jsonObject.get(ApplicationConstants.OUTCOME)!= null && jsonObject.get(ApplicationConstants.OUTCOME).toString()!=null) {								
											String chargeStatus=ocmorcodRepository.getChargeStatus(jsonObject.get(ApplicationConstants.OUTCOME).toString());
											if(chargeStatus!=null && "A".equals(chargeStatus)) {									
												indicatList = oimoffenRepository.offIndExecuteQuery(searchObj);
												for (OffenceIndicator indObj : indicatList) {
													if (!maintainData.isEmpty()) {
														for (ReleaseSchedulesSettingsBean releaseSchedulesSettingsBean : maintainData) {
															if ("CHARGE_IND"
																	.equals(releaseSchedulesSettingsBean.getRelSchSettingType())) {
																final ObjectMapper mapper = new ObjectMapper();
																List<HashMap<String, Object>> jsonMapThree = mapper.readValue(
																		releaseSchedulesSettingsBean.getRelSchSettingValue(),
																		new TypeReference<List<HashMap<String, Object>>>() {
																		});
																for (HashMap<String, Object> jsonObjectInd : jsonMapThree) {
																	if (jsonObjectInd.containsKey(ApplicationConstants.CHARGE_INDICATOR)
																			&& jsonObjectInd.get(ApplicationConstants.CHARGE_INDICATOR) != null) {
																		if (jsonObjectInd.get(ApplicationConstants.CHARGE_INDICATOR).toString()
																				.equals(indObj.getIndicatorCode())) {
																			if (jsonObjectInd.containsKey(ApplicationConstants.LIST_SEQ) && jsonObjectInd.get(ApplicationConstants.LIST_SEQ) != null) {
																				indObj.setListSeq(Integer
																						.valueOf(jsonObjectInd.get(ApplicationConstants.LIST_SEQ).toString()));
																			} else {
																				indObj.setListSeq(Integer.valueOf(0));
																			}
																			mappingIndicatList.add(indObj);
																		}
																	}
																}
															}
														}
													}
												}
												for (OffenceIndicator indObj : indicatList) {
													finalindicatList.add(indObj);
												}
											}
										}
									}
								} catch (Exception e) {
									logger.error("Error in offRelDetLegalExecuteQuery : Charge indicators" + e);
								}
							}
						}
						if (!mappingIndicatList.isEmpty()) {
							List<OffenceIndicator> newList = mappingIndicatList.stream()
									.filter(distinctByKey(p -> p.getIndicatorCode()))
									.collect(Collectors.toList());
							newList.sort((o1, o2) -> o1.getListSeq().compareTo(o2.getListSeq()));
							for (OffenceIndicator gridSettingObj : newList) {
								if (indicatorsData != null) {
									indicatorsData = indicatorsData
											.concat(gridSettingObj.getIndicatorCode().concat(", "));
								} else {
									indicatorsData = gridSettingObj.getIndicatorCode().concat(",");
								}
							}
						}
						if (indicatorsData != null) {
							indicatorsData = indicatorsData.toString().trim();
							
							if (indicatorsData.endsWith(",")) {
								indicatorsData = indicatorsData.trim().substring(0, indicatorsData.length() - 1);
							}
						}
						ordDisplayObj.setChargeIndData(finalindicatList);
					}
					ordDisplayObj.setIndicatorsData(indicatorsData);
					ordDisplayObj.setDataExistFlag(finalObj.getDataExistFlag());
					ordDisplayObj.setReleaseDate(finalObj.getReleaseDate());
					ordDisplayObj.setCommentText(finalObj.getCommentText());
					ordDisplayObj.setMovementType(finalObj.getMovementType());
					ordDisplayObj.setMovementReasonCode(finalObj.getMovementReasonCode());
					ordDisplayObj.setEventId(finalObj.getEventId());
					ordDisplayObj.setEventStatus(finalObj.getEventStatus());
					ordDisplayObj.setCreateDatetime(finalObj.getCreateDatetime());
					ordDisplayObj.setCreateUserId(finalObj.getCreateUserId());
					ordDisplayObj.setModifyDatetime(finalObj.getModifyDatetime());
					ordDisplayObj.setModifyUserId(finalObj.getModifyUserId());
					ordDisplayObj.setVerifiedFlag(finalObj.getVerifiedFlag());
					ordDisplayObj.setAgyLocIdDesc(finalObj.getAgyLocIdDesc());;
					returnListFinal.add(ordDisplayObj);
				}

			}
		} catch (Exception e) {
			logger.error("offRelDetLegalExecuteQuery : oidrelsc" + e);
		}
        if(searchBean.getDateType() != null) {
        	for (OffenderReleaseDetails obj : returnListFinal) {
				for (KeyDateValueBean keyDateObj : obj.getKeyDateListObj()) {
					if(searchBean.getDateType().equalsIgnoreCase(keyDateObj.getDateType())) {
						 inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
						 datePattern = "\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}";
						 boolean isDate = keyDateObj.getDateValue().matches(datePattern);
						Date dates=new Date();
						if (isDate) {
							try {
								dates = inputFormat.parse(String.valueOf(keyDateObj.getDateValue()));
							} catch (ParseException e) {
								logger.error("offRelDetLegalExecuteQuery : oidrelsc" + e);
							}
						}
						if (!dates.before(searchBean.getFromDate()) && !dates.after(searchBean.getToDate())) {
							returnListFinalDates.add(obj);
							break;
						}
					}
					
				}
			}
        } else {
        	returnListFinalDates = returnListFinal;
        }
		return returnListFinalDates;
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	@Override
	public List<ReferenceCodes> getKeyDatesDataLovData(String domainName) {
		return oidrelscRepository.getKeyDatesDataLovData(domainName);
	}

	@Override
	public List<ReferenceCodes> rgKeyDatesRecordGroup() {
		ReleaseSchedulesSettingsBean searchBean = new ReleaseSchedulesSettingsBean();
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		String showHideErdValue = getErdHideShowValue("DERD");
		List<ReferenceCodes> keyDateRetList = oidrelscRepository.getKeyDatesDataLovData("KEY_DATES");
		try {
			List<ReleaseSchedulesSettingsBean> maintainData = oimrelscRepository.retrieveGridData(searchBean);
			ReleaseSchedulesSettingsBean keyDateObj=new ReleaseSchedulesSettingsBean();
			if(!maintainData.isEmpty()) {
				for (ReleaseSchedulesSettingsBean releaseSchedulesSettingsBean : maintainData) {
					if(releaseSchedulesSettingsBean.getRelSchSettingType().equalsIgnoreCase("KEY_DATE")) {
						keyDateObj = releaseSchedulesSettingsBean;
					}
				}
			}
			if (keyDateObj != null && keyDateObj != null
					&& keyDateObj.getRelSchSettingType().equals("KEY_DATE")) {
				final ObjectMapper mapper = new ObjectMapper();
				List<HashMap<String, Object>> jsonMap = mapper.readValue(keyDateObj.getRelSchSettingValue(),
						new TypeReference<List<HashMap<String, Object>>>() {
						});
				for (HashMap<String, Object> jsonObject : jsonMap) {
					for (ReferenceCodes keyDateOb : keyDateRetList) {
						if (jsonObject.containsKey(ApplicationConstants.RELEASE_SCH_DATE_TYPE)
								&& jsonObject.get(ApplicationConstants.RELEASE_SCH_DATE_TYPE) != null
								&& jsonObject.get(ApplicationConstants.RELEASE_SCH_DATE_TYPE).toString().toUpperCase()
										.equalsIgnoreCase(keyDateOb.getCode().toUpperCase())) {
							ReferenceCodes lovObj = new ReferenceCodes();
							lovObj.setCode(jsonObject.get(ApplicationConstants.RELEASE_SCH_DATE_TYPE).toString());
							lovObj.setDescription(keyDateOb.getDescription());
							if("BOOKING_ERD".equals(lovObj.getCode())&& ApplicationConstants.NO_FLAG.equalsIgnoreCase(showHideErdValue)) {
									lovObj.setCanDisplay(false);								
							}								
							if (jsonObject.get(ApplicationConstants.LIST_SEQ) != null
									&& jsonObject.get(ApplicationConstants.LIST_SEQ).toString() != null) {
								lovObj.setListSeq(
										new BigDecimal(jsonObject.get(ApplicationConstants.LIST_SEQ).toString()));
							} else {
								lovObj.setListSeq(BigDecimal.ZERO);
							}
							returnList.add(lovObj);
						}

					}
				}
			}
		} catch (Exception e) {
			logger.error("rgKeyDatesRecordGroup : oidrelsc" + e);
		}

		returnList.sort((o1, o2) -> o1.getListSeq().compareTo(o2.getListSeq()));
		return returnList;
	}
	
	public Date getReleaseDate(Long offenderBookId) {
		return oidrelscRepository.getReleaseDate(offenderBookId);
	}

	@Override
	public String getErdHideShowValue(String code) {
		return ocdenforRepository.getPersutHideShowValue(code);		
	}

	@Override
	public Integer updateCommentText(OffenderReleaseDetails offenderReleaseDetails) {
		return oidrelscRepository.updateCommentText(offenderReleaseDetails);
	}

}
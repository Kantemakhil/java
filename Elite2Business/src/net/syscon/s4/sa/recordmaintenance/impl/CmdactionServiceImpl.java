package net.syscon.s4.sa.recordmaintenance.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.cm.intakeclosure.OcdintakRepository;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.Utilities;
import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;
import net.syscon.s4.common.beans.OdynfrmSubmitDataCommitBean;
import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderCustodyStatus;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SentenceCalcTypes;
import net.syscon.s4.globalconfiguration.OcmpconfRepository;
import net.syscon.s4.globalconfiguration.OcmpconfService;
import net.syscon.s4.globalconfiguration.OumsysetService;
import net.syscon.s4.inst.demographicsbiometrics.OidadmisService;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OffAllowPayDetailsCommitBean;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.sa.recordmaintenance.ActionApi;
import net.syscon.s4.sa.recordmaintenance.AutomationApiQuery;
import net.syscon.s4.sa.recordmaintenance.AutomationApiQueryCommitBean;
import net.syscon.s4.sa.recordmaintenance.AutomationQueryParameters;
import net.syscon.s4.sa.recordmaintenance.AutomationQueryParametersCommitBean;
import net.syscon.s4.sa.recordmaintenance.CmdAsyncActionsService;
import net.syscon.s4.sa.recordmaintenance.CmdactionRepository;
import net.syscon.s4.sa.recordmaintenance.CmdactionService;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@Service
public class CmdactionServiceImpl implements CmdactionService{
	@Autowired
	private CmdactionRepository cmdactionRepository;
	@Autowired
	private OcmpconfRepository ocmpconfRepository;
	@Autowired
	private OcmpconfService ocmpconfService;
	@Autowired
	private OumsysetService oumsysetService;
	@Autowired
	private OidadmisService oidadmisService;
	@Autowired
	private OcdintakRepository ocdintakRepository;
	@Autowired
	private ProsmainService prosmainService;
	
	@Autowired
	private CmdAsyncActionsService cmdAsyncActionsService;
	
	private static Logger logger = LogManager.getLogger(CmdactionServiceImpl.class.getName());
	
	public static final String NO = "NO";
	public static final String YES = "YES";
	public static final String CONFLICT = "conflict";

	@Override
	public List<AutomationApiQuery> quickActionsExecuteQuery() {
		return cmdactionRepository.quickActionsExecuteQuery();
	}

	@Transactional
	public Integer commitQuickActions(AutomationApiQueryCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(ele->ele.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = cmdactionRepository.insertQuickActions(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = cmdactionRepository.updateQuickActions(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			if(preDelete(commitBean.getDeleteList()) == 1) {
				liReturn = cmdactionRepository.deleteQuickActions(commitBean.getDeleteList());
			} else {
				liReturn = 2;
			}
		}
		return liReturn;
	}
	
	public Integer preDelete(List<AutomationApiQuery> queryList) {
		int liReturn = 0;
		for (AutomationApiQuery automationApiQuery : queryList) {
			List<ActionApi> apiList = cmdactionRepository.preDelete(automationApiQuery);
			if(apiList.size() > 0) {
				return liReturn;
			}
		}
		return 1;
	}

	@Override
	public List<AutomationQueryParameters> parametersExecuteQuery(AutomationApiQuery searchBean) {
		return cmdactionRepository.parametersExecuteQuery(searchBean);
	}

	@Transactional
	public Integer commitParameters(AutomationQueryParametersCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(ele->ele.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = cmdactionRepository.insertParameters(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = cmdactionRepository.updateParameters(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = cmdactionRepository.deleteParameters(commitBean.getDeleteList());
		}
		return liReturn;
	}

	@Override
	public String executeUpdateQuery(Map<String, Object> queryData) {
		int result = 0;
		int paramcount = 0;
		String query = cmdactionRepository.getActionQuery(queryData.get("queryKey").toString());
		if(!query.toUpperCase().contains("DELETE")) {
			result =  cmdactionRepository.executeUpdateQuery(queryData, query);
		}
		if(result == 1) {
			return "Query executed Successfully";
		} else {
			return "Query not executed";
		}
	}

	@Override
	public List<Map<String, Object>> executeSelectQuery(Map<String, Object> queryData, String authorization) {
		int paramcount = 0;
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		List<Long> list = new ArrayList<Long>();
		String query = cmdactionRepository.getActionQuery(queryData.get("queryKey").toString());
		if(queryData.get("queryKey").toString().equals("GET_LEGAL_ORDERS_DATA")) {
			cmdAsyncActionsService.updateLegalsData(query, authorization,queryData.get("modifyUserId").toString());
		} else if(queryData.get("queryKey").toString().equals("UPDATE_CUSTODY_STATUS")) {
			custodyStatusUpdate(queryData.get("offenderBookId").toString(), authorization);
			sentStatusUpdate(queryData.get("offenderBookId").toString());
		} else {
			List<AutomationQueryParameters> paramList = cmdactionRepository.getParamList(queryData.get("queryKey").toString());
			Map<String, Object> objMap = new HashMap<String, Object>();
			paramList.forEach(ele -> {
				List<String> stringList = new ArrayList<String>();
				if(ele.getParameterType().equals("Number")) {
					objMap.put(ele.getParameterCode().toString(), Long.parseLong((String) queryData.get(ele.getParameterCode().toString())));
				} else if(ele.getParameterType().equals("String")){
					objMap.put(ele.getParameterCode().toString(), queryData.get(ele.getParameterCode().toString()));
				} else {
					String s = ((String)queryData.get(ele.getParameterCode())).replace("[", " ").replace("]", " ");
					List<String> myList = new ArrayList<String>(Arrays.asList(s.split(",")));
					for (String offId : myList) {
						if (isNumber(offId.trim())) {
							list.add(Long.parseLong(offId.trim())); // Is used for List of OffenderBookId inputs
						} else {
							stringList.add(offId.trim()); // Is used for List of Strings
						}
					}
					if (list.size() > 0) {
						objMap.put(ele.getParameterCode().toString(), list);
					} else if (stringList.size() > 0) {
						objMap.put(ele.getParameterCode().toString(), stringList);
					}
				}
			});
			result =  cmdactionRepository.executeSelectQuery(objMap, query);
		}
		return result; 
	}
	
	private boolean isNumber(String input) {
		try {
			Long.parseLong(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<ReferenceCodes> rgApiIdRecordGroup() {
		return cmdactionRepository.rgApiIdRecordGroup();
	}

	@Override
	public String batchUpdateQuery(Map<String, Object> queryData) {
		int result = 0;
		String query = cmdactionRepository.getActionQuery(queryData.get("queryKey").toString());
		
		for (Map.Entry<String, Object> set : queryData.entrySet()) {
			if(!set.getKey().equals("queryKey") && !set.getKey().equals("createUserId") && !set.getKey().equals("modifyUserId")) {
				List<Map<String, Object>> obj = (List<Map<String, Object>>) set.getValue();
				obj.forEach(e -> {
					e.put("createUserId", queryData.get("createUserId"));
					e.put("modifyUserId", queryData.get("modifyUserId"));
				});
				result =  cmdactionRepository.batchUpdateQuery(obj, query);
			}
		}
		if(result == 1) {
			return "Query executed Successfully";
		} else {
			return "Query not executed";
		}
	}
	
	@Override
	public List<ReferenceCodes> rgParameterRecordGroup() {
		List<ReferenceCodes> paramList = new ArrayList<ReferenceCodes>();
		ReferenceCodes referenceCodes = new ReferenceCodes();
		referenceCodes.setCode("String");
		referenceCodes.setDescription("String");
		paramList.add(referenceCodes);
		ReferenceCodes referenceCodes1 = new ReferenceCodes();
		referenceCodes1.setCode("Number");
		referenceCodes1.setDescription("Number");
		paramList.add(referenceCodes1);
		ReferenceCodes referenceCodes2 = new ReferenceCodes();
		referenceCodes2.setCode("Date");
		referenceCodes2.setDescription("Date");
		paramList.add(referenceCodes2);
		ReferenceCodes referenceCodes3 = new ReferenceCodes();
		referenceCodes3.setCode("List");
		referenceCodes3.setDescription("List");
		paramList.add(referenceCodes3);
		return paramList;
	}
	
	@Override
	public String templateText(Map<String, Object> queryData) {
		String templateText = null;
		String query = cmdactionRepository.getActionQuery(queryData.get("queryKey").toString());
		List<AutomationQueryParameters> paramList = cmdactionRepository.getParamList(queryData.get("queryKey").toString());
		Map<String, Object> objMap = new HashMap<String, Object>();
		paramList.forEach(ele -> {
			objMap.put(ele.getParameterCode().toString(), queryData.get(ele.getParameterCode().toString()));
		});
		
		byte[] templateBody = cmdactionRepository.templateText(objMap, query);
		if(templateBody != null) {
			try {
				 byte[] data = new byte[templateBody.length];
				 ByteArrayInputStream instream = null;
			        try {
			        instream = new ByteArrayInputStream(templateBody);
			        instream.read(data);
			        } catch (Exception ex) {
			        throw new Exception(ex.getMessage());
			        } finally {
			        instream.close();
			        }
			        
			        ByteArrayInputStream bis = new ByteArrayInputStream(data);
			        GZIPInputStream gis = new GZIPInputStream(bis);

			         int length = 0;
			        byte[] buffer = new byte[templateBody.length];
			        ByteArrayOutputStream bos = new ByteArrayOutputStream();
			        while ((length = gis.read(buffer, 0, templateBody.length)) != -1) {
			        bos.write(buffer, 0, length);
			        }

			        gis.close();
			        bis.close();
			        bos.close();
			        
			        templateText = bos.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return templateText;
	}
	
	public byte[] exportActions(List<ActionApi> lstOfActionApi, String savedLocation) throws Exception {
		String respJson = null;
		String jsonFileName = "exportedActions.json";
		byte[] outZip = null;
		ObjectMapper oMapper = new ObjectMapper();
		
		for(ActionApi actionApi :lstOfActionApi) {
			List<Map<String, Object>> actionParams = new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> quickAction = new ArrayList<Map<String, Object>>();
			
			List<AutomationQueryParameters> listOfParams = cmdactionRepository.getActionParams(actionApi.getQueryKey());
			for(AutomationQueryParameters actionParam : listOfParams) {
				Map<String, Object> paramMap = oMapper.convertValue(actionParam, Map.class);
				actionParams.add(paramMap);
			}
			 
			List<AutomationApiQuery> quickActionList = cmdactionRepository.getquickAction(actionApi.getQueryKey());
			for(AutomationApiQuery quickActionObj : quickActionList) {
				Map<String, Object> quickActionMap = oMapper.convertValue(quickActionObj, Map.class);
				quickAction.add(quickActionMap);
			}
			
			actionApi.setQuickAction(quickAction);
			actionApi.setActionParams(actionParams);
		}

		// tracks the created files for deletion 
		List<File> fileList = new ArrayList<>();

		try {
			// Create the json object from list and remove null values
			respJson = new ObjectMapper().setSerializationInclusion(Include.NON_NULL).writeValueAsString(lstOfActionApi);
			
			// Create a json file
			File jsonFile = new File(savedLocation+jsonFileName);
			fileList.add(jsonFile);
			FileOutputStream fo = new FileOutputStream(jsonFile);

			// write to json file
			fo.write(respJson.getBytes());

			// save and close the file
			fo.flush();
			fo.close();

			// Compress all files
			outZip = Utilities.compressFiles(fileList);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			if(!fileList.isEmpty()) {
				Iterator<File> iter = fileList.iterator();
				while(iter.hasNext()) {
					Path filePath = iter.next().toPath();
					// delete files 
					Files.deleteIfExists(filePath);
//					iter.next().delete();
				}
			}
		}
		return outZip;
	}
	
	public Map<String,Object> importActions(MultipartFile zipFile, String savedLocation) throws Exception {
		Map<String, Object> response  = new HashMap<String, Object>();
		String jsonFileName = "exportedActions.json";
		StringBuilder fileData = new StringBuilder();
		byte[] bytes = new byte[1024];
		int length;
		List<Map<String,Object>> parsedJson = new ArrayList<>();
		
		// tracks the created files for deletion 
		List<File> fileList = new ArrayList<>();

		try {
			// extract the zip file
			fileList = Utilities.unZipFiles(zipFile, savedLocation);
			
			// read json file
			FileInputStream fis = new FileInputStream(savedLocation+jsonFileName);
			while((length = fis.read(bytes)) >= 0) {
				fileData.append(new String(bytes));
			}
			fis.close();
			
			// parse json to List
			parsedJson = new ObjectMapper().readValue(fileData.toString(), ArrayList.class);
					
			List<Map<String, Object>> quickActions = new ArrayList<Map<String,Object>>();
			List<Map<String, Object>> parameters = new ArrayList<Map<String,Object>>();
			for(Map<String,Object> parsedJsonObj : parsedJson) {
				List<Map<String, Object>> actionList =  (List<Map<String, Object>>) parsedJsonObj.get("quickAction");
				List<Map<String, Object>> paramList  =  (List<Map<String, Object>>) parsedJsonObj.get("actionParams");
				for(Map<String,Object> actionListObj : actionList) {
					quickActions.add(actionListObj);
				}
				for(Map<String,Object> paramListObj : paramList) {
					parameters.add(paramListObj);
				}
				parsedJsonObj.remove("quickAction");
				parsedJsonObj.remove("actionParams");
				List<ActionApi> list = cmdactionRepository.getActionApi(parsedJsonObj.get("queryKey").toString());	
				if(list.size() > 0) {
					parsedJsonObj.put(CONFLICT, YES);
				} else {
					parsedJsonObj.put(CONFLICT, NO);
				}
			}
			response.put("apiConf", parsedJson);
			response.put("quickActions", quickActions);
			response.put("parameters", parameters);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			
			if(!fileList.isEmpty()) {
				Iterator<File> iter = fileList.iterator();
				while(iter.hasNext()) {
					Path filePath = iter.next().toPath();
					// delete files 
					Files.deleteIfExists(filePath);
//					iter.next().delete();
				}
			}
		}
		return response;
	}
	
	@Transactional
	public Integer updateActions(Map<String, Object> obj, String userName) {
		int response = 0;
		ObjectMapper mapper = new ObjectMapper();
		List<AutomationApiQuery> quickActionsInsertList = new ArrayList<AutomationApiQuery>();
		List<AutomationApiQuery> quickActionsUpdateList = new ArrayList<AutomationApiQuery>();
		List<AutomationQueryParameters> paramsInsertList = new ArrayList<AutomationQueryParameters>();
		List<AutomationQueryParameters> paramsUpdateList = new ArrayList<AutomationQueryParameters>();
		List<ActionApi> apiInsertList = new ArrayList<ActionApi>();
		List<ActionApi> apiUpdateList = new ArrayList<ActionApi>();
 		
		List<Map<String, Object>> quickActions = (List<Map<String, Object>>) obj.get("quickActions");
		List<Map<String, Object>> parameters = (List<Map<String, Object>>) obj.get("parameters");
		List<Map<String, Object>> apiConf = (List<Map<String, Object>>) obj.get("apiConf");
		logger.info(this.getClass().getName(), "updateActions quickActions, parameters and apiConf : {}", quickActions, parameters, apiConf);
		try {
			for (Map<String, Object> quickAction : quickActions) {
				AutomationApiQuery automationApiQuery = new AutomationApiQuery();
				automationApiQuery = mapper.readValue(new ObjectMapper().writeValueAsString(quickAction), AutomationApiQuery.class);
				List<AutomationApiQuery> list = cmdactionRepository.getQuickAction(quickAction.get("queryKey").toString());
				if(list.size() > 0) {
					automationApiQuery.setQueryId(list.get(0).getQueryId());
					automationApiQuery.setModifyUserId(userName);;
					quickActionsUpdateList.add(automationApiQuery);
				} else {
					automationApiQuery.setCreateUserId(userName);
					quickActionsInsertList.add(automationApiQuery);
				}
				cmdactionRepository.deleteParams(quickActionsInsertList);
				cmdactionRepository.deleteParams(quickActionsUpdateList);
			}
			if(quickActionsInsertList != null && quickActionsInsertList.size() > 0) {
				response = cmdactionRepository.insertQuickActions(quickActionsInsertList);
				logger.info(this.getClass().getName(), "updateActions Quick Actions Insert : {}", response);
				if(response == 1 && quickActionsUpdateList != null && quickActionsUpdateList.size() > 0) {
					response = cmdactionRepository.updateQuickActions(quickActionsUpdateList);
					logger.info(this.getClass().getName(), "updateActions Quick Actions Update : {}", response);
				}
			} else  if(quickActionsUpdateList != null && quickActionsUpdateList.size() > 0) {
				response = cmdactionRepository.updateQuickActions(quickActionsUpdateList);
				logger.info(this.getClass().getName(), "updateActions Quick Actions Update : {}", response);
			}
			
			if(response == 1 && parameters!=null &&!parameters.isEmpty()) {
				for (Map<String, Object> parameter : parameters) {
					AutomationQueryParameters automationQueryParameters = mapper.readValue(new ObjectMapper().writeValueAsString(parameter), AutomationQueryParameters.class);
					paramsInsertList.add(automationQueryParameters);
				}
				response = cmdactionRepository.insertParameters(paramsInsertList);
			}
			
			if(response == 1) {
				for (Map<String, Object> apiConfig : apiConf) {
					ActionApi actionApi = new ActionApi();
					actionApi = mapper.readValue(new ObjectMapper().writeValueAsString(apiConfig), ActionApi.class);
					List<ActionApi> list = cmdactionRepository.getActionApi(apiConfig.get("queryKey").toString());
					if(list.size() > 0) {
						actionApi.setModifyUserId(userName);
						apiUpdateList.add(actionApi);
					} else {
						actionApi.setCreateUserId(userName);
						apiInsertList.add(actionApi);
					}
				}
				if(response == 1 && apiInsertList != null && apiInsertList.size() > 0) {
					response = cmdactionRepository.insertActionApi(apiInsertList);
					logger.info(this.getClass().getName(), "Action Api Insert : {}", response);
				}
				if(response == 1 && apiUpdateList != null && apiUpdateList.size() > 0) {
					response = cmdactionRepository.updateActionApi(apiUpdateList);
					logger.info(this.getClass().getName(), "Action Api Update : {}", response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(this.getClass().getName(), "updateActions Exception : {}", e);
			response = 0;
		}
		return response;
	}

	@Override
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

	@Override
	public Map<String, Object> getSeqQuery(Map<String, Object> queryData) {
		List<AutomationQueryParameters> paramList = cmdactionRepository.getParamList(queryData.get("queryKey").toString());
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		Map<String, Object> objMap = new HashMap<String, Object>();
		paramList.forEach(ele -> objMap.put(ele.getParameterCode().toString(), queryData.get(ele.getParameterCode().toString())));
		String query = cmdactionRepository.getActionQuery(queryData.get("queryKey").toString());
		result =  cmdactionRepository.executeSelectQuery(objMap, query);
		String caseloadSeq = result.get(0).get("nextval").toString();
		if (!caseloadSeq.isEmpty()) {
			String numlength = caseloadSeq.toString()+objMap.get(paramList.get(0).getParameterCode()).toString();
			while (numlength.length() < 15) {
				numlength = "0"+numlength;
				caseloadSeq = "0" + caseloadSeq;
			}
			String caseloadSeqTemp = objMap.get(paramList.get(0).getParameterCode()).toString()+caseloadSeq;
			objMap.put(paramList.get(0).getParameterCode().toString(), caseloadSeqTemp);
			return objMap;
		}
		return null;
	}
	
	private Integer updateSentStatus(List<OdynfrmSubmitDataBean> ocdleglsData) {
		int result = 0;
		String automationUser = "";
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
//			List<OdynfrmSubmitDataBean> legalSummaryData = ocmpconfRepository.getLegalSummaryData();
			for(OdynfrmSubmitDataBean odynfrmSubmitDataBeanSum : ocdleglsData) {
				Map<String, String> formIdMap = new ObjectMapper().readValue(odynfrmSubmitDataBeanSum.getFormIdentifier(), new TypeReference<HashMap<String,String>>(){});
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
	
	@Override
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
	
	@Override
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
	
	@Override
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
			Map<String,String> resultStatusMap = ocmpconfService.getResultingStatusMap();
			for(OdynfrmSubmitDataBean OdynfrmSubmitDataBean : odynfrmSubmitDataList) {
				Map<String, String> formIdentifier = new ObjectMapper().readValue(OdynfrmSubmitDataBean.getFormIdentifier(), HashMap.class);
				if(("NONCUST".equals(formIdentifier.get(ApplicationConstants.ORDERTYPE)) || ApplicationConstants.PAR.equals(formIdentifier.get(ApplicationConstants.ORDERTYPE)) || 
					ApplicationConstants.BAIL.equals(formIdentifier.get(ApplicationConstants.ORDERTYPE)))) {
					Map<String, Object> formInfoJson = new ObjectMapper().readValue(OdynfrmSubmitDataBean.getFormInfoJson(), HashMap.class);
					List<Map<String, Object>> myJsonRowDataList =  new ObjectMapper().convertValue(formInfoJson.get("myJsonRowData"), new TypeReference<List<Map<String, Object>>>() {});
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

	@Override
	public String remissionDueNotify(Map<String, Object> newMemoModel) {
		return cmdAsyncActionsService.remissionDueNotify(newMemoModel);
	}

	@Override
	public void saveOffAllowPayDetValues(OffAllowPayDetailsCommitBean commitBean) {
		cmdAsyncActionsService.saveOffAllowPayDetValues(commitBean);
	}
	

}

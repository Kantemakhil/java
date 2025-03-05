package net.syscon.s4.sa.recordmaintenance.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.common.Utilities;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.globalconfiguration.OumsysetService;
import net.syscon.s4.sa.recordmaintenance.BpmnProcess;
import net.syscon.s4.sa.recordmaintenance.BpmnProcessCommitBean;
import net.syscon.s4.sa.recordmaintenance.CmdworkRepository;
import net.syscon.s4.sa.recordmaintenance.ProsdeacService;
import net.syscon.s4.sa.recordmaintenance.ProsmainRepository;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;
import net.syscon.s4.sa.recordmaintenance.WorkItems;

@Service
public class ProsmainServiceImpl implements ProsmainService{
	@Autowired
	private ProsmainRepository prosmainRepository;
	@Autowired
	private CmdworkRepository cmdworkRepository;
	@Autowired
	private EliteDateService eliteDateService;
	
	@Autowired
	private OumsysetService oumsysetService;
	@Autowired
	private ProsdeacService prosdeacService;
	
	public static final String KEY_CODE = "KEY_CODE";
	public static final String VALUE = "VALUE";
	
	
	private static Logger logger = LogManager.getLogger(ProsmainServiceImpl.class.getName());

	@Override
	public List<BpmnProcess> processExecuteQuery() {
		
		List<BpmnProcess> bpmnProcessList = prosmainRepository.processExecuteQuery();
		try {
			for(BpmnProcess bpmnProcess : bpmnProcessList) {
				if(bpmnProcess.getBpmnFile()!=null) {
					byte[] bdata = bpmnProcess.getBpmnFile();
					String bpmn = new String(bdata);
					bpmnProcess.setBpmnFile(null);
					bpmnProcess.setBpmn(bpmn);
				}
				
			}
		} catch(Exception e) {
			logger.error("Process processExecuteQuery Faild and Exception is : {}", e.getMessage());
			e.printStackTrace();
		}
		return bpmnProcessList;
	}

	@Transactional
	public Integer processCommit(BpmnProcessCommitBean commitBean) {
		int liReturn = 0;
		List<BpmnProcess> processList = new ArrayList<BpmnProcess>();
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				commitBean.getInsertList().forEach(ele->ele.setCreateUserId(commitBean.getCreateUserId()));
				liReturn = prosmainRepository.insertProcess(commitBean.getInsertList());
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				commitBean.getUpdateList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
				if(commitBean.getSourceModule()!=null && (commitBean.getSourceModule().equals("PROSMAIN") || commitBean.getSourceModule().equals("CMNPROSS"))) {
					liReturn = prosmainRepository.updateProcessCategory(commitBean.getUpdateList());
				}else {
					processList = modifyBpmn(commitBean.getUpdateList());
					for(BpmnProcess bpmnData:processList) {
						byte[] byteArrray = bpmnData.getBpmn().getBytes();
						bpmnData.setBpmnByte(byteArrray);
						if(bpmnData.getDefVersion() == null || bpmnData.getDefVersion() == 1) {
							bpmnData.setDefVersion((long) 1);
						}
					}
					liReturn = prosmainRepository.updateProcess(commitBean.getUpdateList());
				}
				
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				liReturn = prosmainRepository.deleteProcess(commitBean.getDeleteList());
			}
		} catch(Exception ex) {
			logger.error("Process processCommit Faild and Exception is : {}", ex.getMessage());
			ex.printStackTrace();
		}
		return liReturn;
	}
	
	private String getAutomationUrl() {
		return oumsysetService.getConfValue("serverConfig", "PRODUCT", "AUT_SER_URL");
	} 
	
	RestTemplate restTemplate = new RestTemplate();
	@Transactional
	public Integer deployeBpmn(BpmnProcessCommitBean commitBean, String path) {
		int liReturn = 0;
		logger.info("Process Deployment Started");
		try {
				CloseableHttpClient httpClient = HttpClients.createDefault();
			    HttpPost httpPost = new HttpPost(getAutomationUrl()+"/engine-rest/deployment/create");
			    logger.info("Process Deployment url :{}", getAutomationUrl());
				for (BpmnProcess bpmnProcess : commitBean.getUpdateList()) {
					String bpmn = getBpmnProcess(bpmnProcess);
					StringBody deploymentName = new StringBody(bpmnProcess.getProcessDesc(), ContentType.TEXT_PLAIN);
					StringBody enableDuplicateFiltering = new StringBody("true", ContentType.TEXT_PLAIN);
					StringBody deployChangedOnly = new StringBody("true", ContentType.TEXT_PLAIN);
		
					logger.info("Process Deployment name :{}", deploymentName);
					MultipartEntityBuilder builder = MultipartEntityBuilder.create().addPart("deployment-name", deploymentName)
							.addPart("enable-duplicate-filtering", enableDuplicateFiltering)
							.addPart("deploy-changed-only", deployChangedOnly);
		
					byte[] buff = bpmn.getBytes();
					File temp = null;
		
					temp = File.createTempFile("Sample", ".bpmn");
					logger.info("Process Deployment name :{} , temp File created", deploymentName);
					FileOutputStream outputStream = new FileOutputStream(temp);
					outputStream.write(buff);
		
					File resourceFile = new File(temp.getAbsolutePath());
					FileBody fileBody = new FileBody(resourceFile);
					builder.addPart(resourceFile.getName(), fileBody);
		
					org.apache.http.HttpEntity httpEntity = builder.build();
					httpPost.setEntity(httpEntity);
		
					logger.info("Process Deployment name :{} , execution started", deploymentName);
					HttpResponse response = httpClient.execute(httpPost);
					logger.info("Process Deployment name :{} , execution done", deploymentName);
					String retSrc = EntityUtils.toString(response.getEntity());
					ObjectMapper objectMapper = new ObjectMapper();
					Map<?, ?> deployeMap = objectMapper.readValue(retSrc, Map.class);
					Map<String, Object> dpdMap = (Map<String, Object>) deployeMap.get("deployedProcessDefinitions");
					Map<String, Object> result = (Map<String, Object>) dpdMap.get((String) dpdMap.keySet().toArray()[0]);
		
					bpmnProcess.setProcessKey(result.get("key").toString());
					bpmnProcess.setDeployeId(result.get("deploymentId").toString());
					bpmnProcess.setProcDefId(result.get("id").toString());
					bpmnProcess.setDeployDatetime(eliteDateService.getDBTime());
					
					logger.info("Process Deployment name :{} , execution done, bomn info :{} ", deploymentName, bpmnProcess);
		
					liReturn = updateDeployeId(bpmnProcess);
		
					if (liReturn > 0) {
						liReturn = deleteWorkItemsByTriggerId(bpmnProcess);
						liReturn = insertWorkItems(bpmnProcess);
						suspendActiveInstances(bpmnProcess.getProcessKey());
					}
					logger.info("Process Deployment name :{} , execution done, update DB done and temp file removal started", deploymentName);
					
					temp.deleteOnExit();
					Files.deleteIfExists(Paths.get(temp.getAbsolutePath()));
					
					logger.info("Process Deployment name :{} , execution done, update DB done and temp file removal done.", deploymentName);
				}
			
			} catch (ClientProtocolException e) {
				logger.error("Process Deployment Faild and Exception is : {}", e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				logger.error("Process Deployment Faild and Exception is : {}", e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				logger.error("Process Deployment Faild and Exception is : {}", e.getMessage());
				e.printStackTrace();
			}
		return liReturn;
	}
	
	private Integer insertWorkItems(BpmnProcess BpmnProcess) {
		try {
			List<WorkItems> workItemsList = new ArrayList<WorkItems>();
			WorkItems workItems = new WorkItems();
			workItems.setTriggerId(BpmnProcess.getTriggerId());
			workItems.setProcess(BpmnProcess.getProcessId());
			workItems.setCreateDatetime(BpmnProcess.getCreateDatetime());
			workItems.setCreateUserId(BpmnProcess.getCreateUserId());
			workItemsList.add(workItems);
			Long workId = cmdworkRepository.getWorkId(BpmnProcess.getProcessId());
			if(workId == null) {
              for(WorkItems workItemObj:workItemsList) {
            	  workItemObj.setAddTrigger("Y");
            	  workItemObj.setUpdateTrigger("Y");
            	  workItemObj.setDeleteTrigger("Y");
              }
				return cmdworkRepository.insertWorkItems(workItemsList);
			} else {
				workItemsList.get(0).setWorkId(workId);
				return cmdworkRepository.updateWorkItemsOnProcessDeploy(workItemsList);
			}
		} catch(Exception ex) {
			logger.error("Process Deployment insertWorkItems Error :{}", ex.getMessage());
			return 0;
		}
	}
	
	private Integer insertBulkWorkItems(List<BpmnProcess> bpmnProcessList) {
		List<WorkItems> workItemsInsertList = new ArrayList<WorkItems>();
		List<WorkItems> workItemsUpdateList = new ArrayList<WorkItems>();
		int updateOp = 0;
		int insertOp = 0;
		int cmnCount = 0;
		try {
			for(BpmnProcess bpmnProcess : bpmnProcessList) {
				WorkItems workItems = new WorkItems();
				workItems.setTriggerId(bpmnProcess.getTriggerId());
				workItems.setProcess(bpmnProcess.getProcessId());
				workItems.setAddTrigger("Y");
				workItems.setUpdateTrigger("Y");
				workItems.setDeleteTrigger("Y");
				workItems.setCreateDatetime(bpmnProcess.getCreateDatetime());
				workItems.setCreateUserId(bpmnProcess.getCreateUserId());
				Long workId = prosmainRepository.getWorkIdByTrigger(bpmnProcess.getTriggerId());
				if(!"Y".equals(bpmnProcess.getCommonProcess())) {
					if(workId != null && !workId.equals("")) {
						workItems.setWorkId(workId);
						workItemsUpdateList.add(workItems);
					} else {
						workItemsInsertList.add(workItems);
					}
				} else {
					cmnCount = 1;
				}
			}
			if(!workItemsUpdateList.isEmpty()) {
				updateOp = cmdworkRepository.updateWorkItems(workItemsUpdateList);
			}
			if(!workItemsInsertList.isEmpty()) {
				insertOp = cmdworkRepository.insertWorkItems(workItemsInsertList);
			}
		} catch(Exception ex) {
			logger.error("Process Deployment insertWorkItems Error :{}", ex.getMessage());
			return null;
		}
		return updateOp + insertOp + cmnCount;
	}
	
	private String getBpmnProcess(BpmnProcess bpmnProcess) {
		String bpmn = null;
		BpmnProcess process = prosmainRepository.getBpmnProcess(bpmnProcess);
		try {
			if(process.getBpmnFile()!=null) {
				byte[] bdata = process.getBpmnFile();
				bpmn = new String(bdata);
			}
		} catch (Exception e) {
			logger.error("Process getBpmnProcess Error :{}", e.getMessage());
			e.printStackTrace();
		}
		return bpmn;
	}
	
	private Integer updateDeployeId(BpmnProcess bpmnProcess) {
		int liReturn = 0;
		try {
			liReturn = prosmainRepository.updateDeployeId(bpmnProcess);
		} catch (Exception ex) {
			logger.error("Process updateDeployeId Error :{}", ex.getMessage());
			ex.printStackTrace();
		}
		return liReturn;
	}
	
	private Integer updateBulkDeployeId(List<BpmnProcess> bpmnProcessList) {
		int liReturn = 0;
		try {
			liReturn = prosmainRepository.updateBulkDeployeId(bpmnProcessList);
		} catch (Exception ex) {
			logger.error("Process updateDeployeId Error :{}", ex.getMessage());
			ex.printStackTrace();
		}
		return liReturn;
	}

	@Override
	public List<BpmnProcess> getVersionHistory(BpmnProcess bpmnProcess) {
		List<BpmnProcess> bpmnProcessList = prosmainRepository.getVersionHistory(bpmnProcess);
		
		try {
			for(BpmnProcess bpmnProc : bpmnProcessList) {
				if(bpmnProc.getBpmnFile()!=null) {
					byte[] bdata = bpmnProc.getBpmnFile();
					String bpmn = new String(bdata);
					bpmnProc.setBpmnFile(null);
					bpmnProc.setBpmn(bpmn);
				}
			}
		} catch(Exception e) {
			logger.error("Process versionHistory Failed : {}" , e.getMessage());
			e.printStackTrace();
		}
		return bpmnProcessList;
	}
	
	@Override
	@Async
	public void enableTriggers(Object commitBean, String authorization, String triggerId) {
		logger.info("Process triggerId : {}" , triggerId);
		try {
		for (Method method : commitBean.getClass().getDeclaredMethods()) {
			if(method.getName().contains("getInsertList")) {
				List<Object> objList = (List<Object>) method.invoke(commitBean, null);
				if(objList != null && objList.size() > 0) {
					Long processId = prosmainRepository.getInsertProcess(triggerId);
					logger.info("Process triggerId : {}, processId :{}" , triggerId, processId);
					if(processId != null && !processId.equals("")) {
							for(Object obj: objList) {
								String instanceId = getProcessInstance(processId, authorization, obj);
							}
					}
				}
			} else if(method.getName().contains("getUpdateList")) {
				List<Object> objList = (List<Object>) method.invoke(commitBean, null);
				if(objList != null && objList.size() > 0) {
					Long processId = prosmainRepository.getUpdateProcess(triggerId);
					logger.info("Process triggerId : {}, processId :{}" , triggerId, processId);
					if(processId != null && !processId.equals("")) {
							for(Object obj: objList) {
								String instanceId = getProcessInstance(processId, authorization, obj);
							}
					}
				}
			} else if(method.getName().contains("getDeleteList")) {
				List<Object> objList = (List<Object>) method.invoke(commitBean, null);
				if(objList != null && objList.size() > 0) {
					Long processId = prosmainRepository.getDeleteProcess(triggerId);
					logger.info("Process triggerId : {}, processId :{}" , triggerId, processId);
					if(processId != null && !processId.equals("")) {
							for(Object obj: objList) {
								String instanceId = getProcessInstance(processId, authorization, obj);
							}
					}
				}
			} else if(method.getName().equals("getFormInfoJson")) {
					Long processId = prosmainRepository.getInsertProcess(triggerId);
					logger.info("getFormInfoJson ::: Process triggerId : {}, processId :{}" , triggerId, processId);
					if(processId != null && !processId.equals("")) {
								 getProcessInstance(processId, authorization, commitBean);
					}
			}
		}
		} catch (Exception e) {
			logger.info("Process triggerId : {}, exception :{}" , triggerId, e.getMessage());
			e.printStackTrace();
		}
	}
	
	private String getSysconUrl() {
		return oumsysetService.getConfValue("serverConfig", "PRODUCT", "APP_SER_URL");
	}
	
	private String getSentenceEngineUrl() {
		return oumsysetService.getConfValue("serverConfig", "PRODUCT", "SCAL_SER_URL");
	}
	
	public String getProcessInstance(Long processId ,String authorization, Object moduleObject) {
		JSONObject variables = new JSONObject();
		String processKey = "";
		JSONObject jsonObject = new JSONObject();
		try {
			logger.info("Process Process Id : {} " ,processId);
			processKey = prosmainRepository.getProcessKey(processId);
			logger.info("Process Process Id : {} Procress Key : {}" ,processId, processKey);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			String url= getAutomationUrl()+"/engine-rest/process-definition/key/"+processKey+"/start";
				
			JSONObject dynamicUrl = new JSONObject();
			JSONObject authToken = new JSONObject();
			JSONObject dynamicSentUrl = new JSONObject();
				
			dynamicUrl.put("value", getSysconUrl());
			authToken.put("value", authorization);
			dynamicSentUrl.put("value", getSentenceEngineUrl());
				
			variables.put("url", dynamicUrl);
			variables.put("authorization", authToken);
			variables.put("sentenceEngineUrl", dynamicSentUrl);
			
			logger.info("Process Process Id : {} Procress Key : {} and URL :{}" ,processId, processKey, url);
			Map<String, Object> moduleData = getModuleData(moduleObject, processKey);
			if(moduleData.size() > 0) {
				for(String key: moduleData.keySet()){
					if( moduleData.get(key)!=null) {
						variables.put(key, new JSONObject().put("value", moduleData.get(key)));
					}else {
						variables.put(key, new JSONObject().put("value", "''"));
					}	
				}
			}
			logger.info("Process Process Id : {} Procress Key : {} variables : {}" ,processId, processKey,variables);
			jsonObject.put("variables",variables);
			if(!Utilities.hasJackson2HttpMessageConverter(restTemplate)) {
				restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			}
			HttpEntity<String> request =
				new HttpEntity<String>(jsonObject.toString(), headers);
		
			logger.info("url : {}" ,url);
			logger.info("headers : {}" ,headers);
			Map<String, Object> response = (Map<String, Object>) restTemplate.postForObject(url, request, Object.class);
			logger.info("Process Process Id : {} response from camunda engine : {}",processId, response);
		} catch (Exception ex) {
			logger.error("Process Process Id : {} Exception : {}",processId, ex);
			ex.printStackTrace();
			ex.getMessage();
			JSONObject errorObject = new JSONObject();
			errorObject.put("expLocation", "Process Init");
			errorObject.put("expType", ex.getClass());
			errorObject.put("expMsg", ex.getMessage());
			errorObject.put("expCause", ex.getCause());
			errorObject.put("processKey", processKey);
			errorObject.put("processId", processId);
			errorObject.put("procInputData", jsonObject);
			prosmainRepository.saveCamundaErr(errorObject.toString(), processKey, processId);
		}
		return "";
	}
	
	private Map<String, Object> getModuleData(Object object, String processKey) {
		Map<String, Object> moduleVariables = new HashMap<String, Object>();
		try {
			String procDefId = prosmainRepository.getProcDefId(processKey);
			String formVariableUrl = getAutomationUrl()+"/engine-rest/process-definition/"+procDefId+"/form-variables";
			logger.info("Process processKey :{} procDefId : {} formVariableUrl :{}", processKey, procDefId, formVariableUrl);
			
			String formVariables= restTemplate.getForObject(formVariableUrl, String.class);
			logger.info("Process processKey :{} procDefId : {} formVariables :{}", processKey, procDefId, formVariables);
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> variableMap = new HashMap<String, Object>();
			variableMap = objectMapper.readValue(formVariables,Map.class);
			List<String> variablesList = new ArrayList<String>(variableMap.keySet());
			for (Method method : object.getClass().getDeclaredMethods()) {
				if (method.getName().contains("get")) {
					for(String variable : variablesList) {
						String var = method.getName().substring(3);
						var = var.substring(0, 1).toLowerCase() + var.substring(1);
						if(variable.equals(var)) {
							moduleVariables.put(var, method.invoke(object, null));
						}
					}
				}
			}
			
		} catch(Exception e) {
			logger.error("Process processKey :{},  exception is :{}",processKey, e.getMessage());
			e.printStackTrace();
		}
		return moduleVariables;
	}

	@Override
	public List<BpmnProcess> getProcess(BpmnProcess bpmnProcess) {
		List<BpmnProcess> bpmnProcessList  =null;
		try {
			bpmnProcessList = prosmainRepository.getProcess(bpmnProcess);
			return bpmnProcessList;
		} catch(Exception ex) {
			logger.error("Process Failed to get BPMN Process :{}", ex.getMessage());
		}
		return null;
	}

	@Override
	public BpmnProcess getProcessData(String processId) {
		BpmnProcess processData = null;
			byte[] byteData;
			try {
					processData= prosmainRepository.getProcessData(Integer.parseInt(processId));
					if(processData !=null && processData.getBpmnFile()!=null) {
						byteData = processData.getBpmnFile();
					String bpmn = new String(byteData);
					processData.setBpmnFile(null);
					processData.setBpmn(bpmn);
				
				} 
			}
			catch (Exception e) {
				logger.error("Process processId :{},  exception is :{}",processId, e.getMessage());
				e.printStackTrace();
			}
		return processData;
	}

	public byte[] exportProcesses(List<BpmnProcess> lstOfProcessMain, String savedLocation) throws Exception {
		String respJson = null;
		String jsonFileName = "exportedProcesses.json";
		String xmlFileExt = ".bpmn";
		byte[] outZip = null;
		List<File> fileList = new ArrayList<>();

		try {
			Iterator<BpmnProcess> procIter = lstOfProcessMain.iterator();
			while(procIter.hasNext()) {
				BpmnProcess bpmn  = procIter.next();
				if(bpmn.getProcessKey() != null) {
					String xmlFileName = bpmn.getProcessKey()+xmlFileExt;
					bpmn.setXmlFileName(xmlFileName);
					File xmlFile = new File(savedLocation+xmlFileName);
					fileList.add(xmlFile);
					FileOutputStream fo = new FileOutputStream(xmlFile);
					fo.write(bpmn.getBpmn().getBytes());
					fo.flush();
					fo.close();
					bpmn.setBpmn(null);
				} else {
					procIter.remove();
				}
			}
			respJson = new ObjectMapper().setSerializationInclusion(Include.NON_NULL).writeValueAsString(lstOfProcessMain);
			File jsonFile = new File(savedLocation+jsonFileName);
			fileList.add(jsonFile);
			FileOutputStream fo = new FileOutputStream(jsonFile);
			fo.write(respJson.getBytes());
			fo.flush();
			fo.close();
			outZip = Utilities.compressFiles(fileList);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			if(!fileList.isEmpty()) {
				Iterator<File> iter = fileList.iterator();
				while(iter.hasNext()) {
					Path filePath = iter.next().toPath();
					Files.deleteIfExists(filePath);
				}
			}
		}
		return outZip;
	}
	
	public List<Map<String,Object>> importProcesses(MultipartFile zipFile, String savedLocation) throws Exception {
		String jsonFileName = "exportedProcesses.json";
		StringBuilder fileData = new StringBuilder();
		byte[] bytes = new byte[1024];
		int length;
		List<Map<String,Object>> parsedJson = new ArrayList<>();
		List<File> fileList = new ArrayList<>();
		try {
			fileList = Utilities.unZipFiles(zipFile, savedLocation);
			FileInputStream fis = new FileInputStream(savedLocation+jsonFileName);
			while((length = fis.read(bytes)) >= 0) {
				fileData.append(new String(bytes));
			}
			fis.close();
			parsedJson = new ObjectMapper().readValue(fileData.toString(), ArrayList.class);
			List<BpmnProcess> existingProcesses = new ArrayList<>();
			existingProcesses = processExecuteQuery();
			for (Map<String,Object> processObj : parsedJson) {
				fileData = new StringBuilder();
				bytes = new byte[1024];
				String xmlFileName = String.valueOf(processObj.get("xmlFileName"));
				String extension = StringUtils.getFilenameExtension(xmlFileName);
				if(extension == null || extension.equals("")|| !extension.equalsIgnoreCase("bpmn")) {
					return Collections.emptyList();
				}
				fis = new FileInputStream(savedLocation+xmlFileName);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				while((xmlFileName = br.readLine()) != null) {
					fileData.append(xmlFileName);
					fileData.append(System.lineSeparator());
				}
				processObj.put("bpmn", fileData.toString());
				List<BpmnProcess> existingProcess = existingProcesses.stream().filter(existingProc->existingProc.getProcessDesc().equals(processObj.get("processDesc"))).collect(Collectors.toList());
				if(existingProcess.size() > 0) {
					processObj.put("defVersion", existingProcess.get(0).getDefVersion());
					processObj.put("processId", existingProcess.get(0).getProcessId());
					processObj.put("conflict", true);
				} else {
					processObj.put("conflict", false);
				}
				//List<BpmnProcess> existingTrigger = existingProcesses.stream().filter(existingProc->(processObj.get("triggerId") != null && !"".equals(processObj.get("triggerId"))) && (processObj.get("triggerId").equals(existingProc.getTriggerId()))).collect(Collectors.toList());
 				
				for (BpmnProcess existObj : existingProcesses) {
					if(existObj!=null && existObj.getTriggerId()!=null && !existObj.getTriggerId().isEmpty() && processObj.get("triggerId") != null && !processObj.get("triggerId").toString().isEmpty() && (processObj.get("triggerId").equals(existObj.getTriggerId()))) {
						processObj.put("existingTrigger", true);
						processObj.put("processname", existObj.getProcessDesc());
					} else {
						processObj.put("existingTrigger", false);
					}
					
				}
				
				/*
				 * if(existingTrigger.size() > 0) { processObj.put("existingTrigger", true);
				 * 
				 * } else { processObj.put("existingTrigger", false); }
				 */
				fis.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			
			if(!fileList.isEmpty()) {
				Iterator<File> iter = fileList.iterator();
				while(iter.hasNext()) {
					Path filePath = iter.next().toPath();
					Files.deleteIfExists(filePath);
				}
			}
		}
		return parsedJson;
	}

	@Override
	public Map<String, Object> deployBulkProcesses(List<BpmnProcess> bpmnList, String path) {
		Map<String,Object> respObj = new HashMap<>();
		List<String> successList = new ArrayList<>();
		List<String> failureList = new ArrayList<>();
		List<File> fileList = new ArrayList<>();
		int liReturn = 0;
		logger.info("Process Deployment Started");
		try {
				CloseableHttpClient httpClient = HttpClients.createDefault();
			    HttpPost httpPost = new HttpPost(getAutomationUrl()+"/engine-rest/deployment/create");
			    logger.info("Process Deployment url :{}", getAutomationUrl());
			    StringBody deploymentName = new StringBody("bulk_deployment", ContentType.TEXT_PLAIN);
			    StringBody enableDuplicateFiltering = new StringBody("true", ContentType.TEXT_PLAIN);
			    StringBody deployChangedOnly = new StringBody("true", ContentType.TEXT_PLAIN);
			    MultipartEntityBuilder builder = MultipartEntityBuilder.create().addPart("deployment-name", deploymentName)
			    		.addPart("enable-duplicate-filtering", enableDuplicateFiltering)
			    		.addPart("deploy-changed-only", deployChangedOnly);
			    logger.info("Bulk Deployment name :{}", deploymentName);
				for (BpmnProcess bpmnProcess : bpmnList) {
					String bpmn = bpmnProcess.getBpmn();
					byte[] buff = bpmn.getBytes();
					File temp = null;	
					temp = File.createTempFile(bpmnProcess.getProcessKey(), ".bpmn");
					logger.info("Process Deployment name :{} , temp File created", deploymentName);
					FileOutputStream outputStream = new FileOutputStream(temp);
					outputStream.write(buff);
					File resourceFile = new File(temp.getAbsolutePath());
					fileList.add(resourceFile);
					FileBody fileBody = new FileBody(resourceFile);
					builder.addPart(resourceFile.getName(), fileBody);
				}
				org.apache.http.HttpEntity httpEntity = builder.build();
				httpPost.setEntity(httpEntity);
				logger.info("Process Deployment name :{} , execution started", deploymentName);
				HttpResponse response = httpClient.execute(httpPost);
				logger.info("Process Deployment name :{} , execution done", deploymentName);
				String retSrc = EntityUtils.toString(response.getEntity());
				ObjectMapper objectMapper = new ObjectMapper();
				Map<?, ?> deployeMap = objectMapper.readValue(retSrc, Map.class);
				Map<String, Object> dpdMap = (Map<String, Object>) deployeMap.get("deployedProcessDefinitions");
				Map<String, Object> result = (Map<String, Object>) dpdMap.get((String) dpdMap.keySet().toArray()[0]);
				Date currDate = eliteDateService.getDBTime();
				List<BpmnProcess> existingProcesses = new ArrayList<>();
				existingProcesses = processExecuteQuery();
				for(String deployedProcDef: dpdMap.keySet()) {
					Map<String, Object> deployedProcMap = (Map<String, Object>) dpdMap.get(deployedProcDef);
					for(int i=0; i<bpmnList.size(); i++) {
						if(bpmnList.get(i).getProcessKey().equals(deployedProcMap.get("key"))) {
							successList.add(bpmnList.get(i).getProcessDesc());
							Map<String, Object> res = (Map<String, Object>) dpdMap.get(deployedProcMap.get("id"));
							bpmnList.get(i).setProcessKey(res.get("key").toString());
							bpmnList.get(i).setDeployeId(res.get("deploymentId").toString());
							bpmnList.get(i).setProcDefId(res.get("id").toString());
							bpmnList.get(i).setDeployDatetime(currDate);
							suspendActiveInstances(res.get("key").toString());
							break;
						}
					}
				}
				
				if(successList.size() < bpmnList.size()) {
					bpmnList.forEach(bpmnProcess->{
						if(!successList.contains(bpmnProcess.getProcessDesc())) {
							failureList.add(bpmnProcess.getProcessDesc());
						}
					});
				}
				respObj.put("successList", successList);
				respObj.put("failureList", failureList);				
				liReturn = updateBulkDeployeId(bpmnList);
				if (liReturn > 0) {
					liReturn = insertBulkWorkItems(bpmnList);
				}
				logger.info("Process Deployment name :{} , execution done, update DB done and temp file removal started", deploymentName);
				
				logger.info("Process Deployment name :{} , execution done, update DB done and temp file removal done.", deploymentName);
			
			} catch (ClientProtocolException e) {
				logger.error("Process Deployment Faild and Exception is : {}", e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				logger.error("Process Deployment Faild and Exception is : {}", e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				logger.error("Process Deployment Faild and Exception is : {}", e.getMessage());
				e.printStackTrace();
			} finally {
				fileList.forEach(file->{
					try {
					file.deleteOnExit();
						Files.deleteIfExists(Paths.get(file.getAbsolutePath()));
					} catch (IOException e) {
						logger.error("Bulk file deletion Faild and Exception is : {}", e.getMessage());
					}
				});
			}  	
		return respObj;
	}
	
	@Override
	public List<ReferenceCodes> getCommonProcess() {
		return prosmainRepository.getCommonProcess();
	}
	
	@Override
	@Transactional
	public int processBulkCommit(BpmnProcessCommitBean bpmnProcessCommitBean) {
		int liReturn = 0;
		List<BpmnProcess> insertList = new ArrayList<BpmnProcess>();
		List<BpmnProcess> updateList = new ArrayList<BpmnProcess>();
		try {
			if(bpmnProcessCommitBean.getInsertList() != null && bpmnProcessCommitBean.getInsertList().size() > 0) {
				List<BpmnProcess> processList = modifyBpmn(bpmnProcessCommitBean.getInsertList());
				for(BpmnProcess bpmnData:processList) {
					byte[] byteArrray = bpmnData.getBpmn().getBytes();
					bpmnData.setBpmnByte(byteArrray);
					if(!"Y".equals(bpmnData.getTimerProcess())) {
						bpmnData.setTimerProcess("N");
					}
					insertList.add(bpmnData);
				}
				liReturn = prosmainRepository.insertBulkProcess(insertList);
			}
			if (bpmnProcessCommitBean.getUpdateList() != null && bpmnProcessCommitBean.getUpdateList().size() > 0) {
				List<BpmnProcess> processList = modifyBpmn(bpmnProcessCommitBean.getUpdateList());
				for(BpmnProcess bpmnData:processList) {
					bpmnData.setModifyUserId(bpmnProcessCommitBean.getCreateUserId());
					byte[] byteArrray = bpmnData.getBpmn().getBytes();
					bpmnData.setBpmnByte(byteArrray);
					if(bpmnData.getDefVersion() == null) {
						bpmnData.setDefVersion((long) 0);
					}
					if(!"Y".equals(bpmnData.getTimerProcess())) {
						bpmnData.setTimerProcess("N");
					}
					updateList.add(bpmnData);
				}
				liReturn = prosmainRepository.updateBulkProcess(updateList);
			}
		} catch(Exception ex) {
			logger.error("Process processCommit Faild and Exception is : {}", ex.getMessage());
			ex.printStackTrace();
		}
		return liReturn;
	}

	private List<BpmnProcess> modifyBpmn(List<BpmnProcess> BpmnProcessList) {
		StringBuffer sb = new StringBuffer();
		String appServerUrl = "";
		String userName = "";
		String password = "";
		Boolean startEvent = false;
		
		List<Map<String,Object>> responseList=oumsysetService.getSysData("serverConfig","PRODUCT");
		if (responseList != null && !responseList.isEmpty()) {
			for (Map<String, Object> object : responseList) {
				if (object.containsKey(KEY_CODE) && object.get(KEY_CODE) != null
						&& object.get(KEY_CODE).equals("APP_SER_URL") && object.containsKey(VALUE)
						&& object.get(VALUE) != null) {
					appServerUrl = object.get(VALUE).toString();
				} 
			}
		}
		
		List<Map<String,Object>> userResponseList=oumsysetService.getSysData("serverConfig","AUTOMATION_USER");
		if (userResponseList != null && !userResponseList.isEmpty()) {
			for (Map<String, Object> object : userResponseList) {
				if (object.containsKey(KEY_CODE) && object.get(KEY_CODE) != null
						&& object.get(KEY_CODE).equals("AUTOMATION_ELITE_USER") && object.containsKey(VALUE)
						&& object.get(VALUE) != null) {
					userName = object.get(VALUE).toString();
				} else if (object.containsKey(KEY_CODE) && object.get(KEY_CODE) != null
						&& object.get(KEY_CODE).equals("AUTOMATION_ELITE_PASSWORD") && object.containsKey(VALUE)
						&& object.get(VALUE) != null) {
					String encryptPwd = object.get(VALUE).toString();
					Base64.Decoder decoder = Base64.getDecoder();
					password = new String(decoder.decode(encryptPwd));
				}
			}
		}
		
		String exe = "execution.setVariable('url',"+ "\"" + appServerUrl + "\"" + ")";
		String replaceXml = "<camunda:script scriptFormat=\"Javascript\">" + exe + "</camunda:script>";
		for(BpmnProcess bpmnData : BpmnProcessList) {
			bpmnData.setTimerProcess("N");
			sb = new StringBuffer();
			String lines[] = bpmnData.getBpmn().split("\\r?\\n");
			for(int i = 0; i < lines.length; i++) {
				System.out.println(lines[i]);
				if(lines[i].contains("execution.setVariable('url',")) {
					lines[i] = replaceXml;
				} else if(lines[i].contains("\"grant_type\" :")) {
					lines[i] = "\"grant_type\" : \"password\",";
				} else if(lines[i].contains("\"username\" :")) {
					lines[i] = "\"username\" : " +  "\"" + userName +  "\",";
				} else if(lines[i].contains("\"password\" :")) {
					lines[i] = "\"password\" : "+  "\"" + password +  "\"";
				}
				
				if(lines[i].contains("bpmn:startEvent")) {
					startEvent = !startEvent;
				}
				if(startEvent) {
					if(lines[i].contains("bpmn:timerEventDefinition")) {
						bpmnData.setTimerProcess("Y");
					}
				}
				 sb.append(lines[i]);
				 sb.append(System.lineSeparator());
			}
			bpmnData.setBpmn(sb.toString());
		}
		return BpmnProcessList;
	}
	
	private Integer deleteWorkItemsByTriggerId(BpmnProcess BpmnProcess) {
		try {
			Integer workId = cmdworkRepository.deleteWorkItemsByTriggerId(BpmnProcess.getTriggerId());
			return 1;
		} catch(Exception ex) {
			logger.error("deleteWorkItemsByTriggerId Error :{}", ex.getMessage());
			return 0;
		}
	}
	
	
	@Override
	public void sentCalcTrigger(Object commitBean, String authorization, String triggerId) {
		logger.info("Sentence Calc Trigger ::: Process triggerId : {}", triggerId);
		try {
			for (Method method : commitBean.getClass().getDeclaredMethods()) {
				if (method.getName().equals("getFormInfoJson")) {
					Long processId = prosmainRepository.getInsertProcess(triggerId);
					logger.info("sentCalcTrigger ::: Process triggerId : {}, processId :{}", triggerId, processId);
					if (processId != null && !processId.equals("")) {
						logger.info("Sentence Calc Trigger called getSentenceProcessInstance()");
						getSentenceProcessInstance(processId, authorization, commitBean);
					}
				}
			}
		} catch (Exception e) {
			logger.info("Sentence Calc Trigger ::: Process triggerId : {}, exception :{}" , triggerId, e.getMessage());
		}
	}
	
	public String getSentenceProcessInstance(Long processId ,String authorization, Object moduleObject) {
		logger.info("getSentenceProcessInstance :: called " ,processId);
		JSONObject variables = new JSONObject();
		String processKey = "";
		JSONObject jsonObject = new JSONObject();
		try {
			logger.info("getSentenceProcessInstance ::: Process Process Id : {} " ,processId);
			processKey = prosmainRepository.getProcessKey(processId);
			logger.info("getSentenceProcessInstance ::: Process Process Id : {} Procress Key : {}" ,processId, processKey);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			String url= getAutomationUrl()+"/engine-rest/process-definition/key/"+processKey+"/start";
				
			JSONObject dynamicUrl = new JSONObject();
			JSONObject authToken = new JSONObject();
			JSONObject dynamicSentUrl = new JSONObject();
				
			dynamicUrl.put("value", getSysconUrl());
			authToken.put("value", authorization);
			dynamicSentUrl.put("value", getSentenceEngineUrl());
				
			variables.put("url", dynamicUrl);
			variables.put("authorization", authToken);
			variables.put("sentenceEngineUrl", dynamicSentUrl);
			
			logger.info("getSentenceProcessInstance ::: Process Process Id : {} Procress Key : {} and URL :{}" ,processId, processKey, url);
			Map<String, Object> moduleData = getModuleData(moduleObject, processKey);
			if(moduleData.size() > 0) {
				for(String key: moduleData.keySet()){
					if( moduleData.get(key)!=null) {
						variables.put(key, new JSONObject().put("value", moduleData.get(key)));
					}else {
						variables.put(key, new JSONObject().put("value", "''"));
					}
				}
			}
			logger.info("getSentenceProcessInstance ::: Process Process Id : {} Procress Key : {} variables : {}" ,processId, processKey,variables);
			jsonObject.put("variables",variables);
			if(!Utilities.hasJackson2HttpMessageConverter(restTemplate)) {
				restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			}
			HttpEntity<String> request =
				new HttpEntity<String>(jsonObject.toString(), headers);
			logger.info("getSentenceProcessInstance ::: url : {}" ,url);
			logger.info("getSentenceProcessInstance ::: headers : {}" ,headers);
			Map<String, Object> response = (Map<String, Object>) restTemplate.postForObject(url, request, Object.class);
			logger.info("getSentenceProcessInstance() ::: Process Process Id : {} response from camunda engine : {}",processId, response);
		} catch (Exception ex) {
			logger.error("getSentenceProcessInstance ::: Process Process Id : {} Exception : {}",processId, ex);
			ex.printStackTrace();
			// log all errors to DB
			JSONObject errorObject = new JSONObject();
			errorObject.put("expLocation", "Process Init");
			errorObject.put("expType", ex.getClass());
			errorObject.put("expMsg", ex.getMessage());
			errorObject.put("expCause", ex.getCause());
			errorObject.put("processKey", processKey);
			errorObject.put("processId", processId);
			errorObject.put("procInputData", jsonObject);
			prosmainRepository.saveCamundaErr(errorObject.toString(), processKey, processId);
		}
		return "";
	}
	
	private void suspendActiveInstances(String processKey) {
		String url= getAutomationUrl()+"/engine-rest/process-definition?keyLike="+processKey+"&active=true&sortBy=version&sortOrder=asc";
		int count = 0;
		BpmnProcess bpmnProcess= new BpmnProcess();
		try {
			List<Map<String, Object>> response = (List<Map<String, Object>>) restTemplate.getForObject(url, Object.class);
			for(Map<String, Object> obj: response) {
				count++;
				if(count < response.size()) {
					bpmnProcess.setProcDefId((String)obj.get("id"));
					prosdeacService.suspendProcess(bpmnProcess);
				}
			}
		} catch (Exception e) {
			logger.error("suspendActiveInstances : {}", e.getMessage());
		}
	}
		
}

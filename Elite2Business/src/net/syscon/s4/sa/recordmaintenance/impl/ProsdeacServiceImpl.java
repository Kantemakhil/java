package net.syscon.s4.sa.recordmaintenance.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.syscon.s4.globalconfiguration.OumsysetService;
import net.syscon.s4.sa.recordmaintenance.BpmnProcess;
import net.syscon.s4.sa.recordmaintenance.ProsdeacRepository;
import net.syscon.s4.sa.recordmaintenance.ProsdeacService;

@Service
public class ProsdeacServiceImpl implements ProsdeacService{
	@Autowired
	private ProsdeacRepository prosdeacRepository;
	
	@Autowired
	private OumsysetService oumsysetService;
	
	public static final String KEY_CODE = "KEY_CODE";
	public static final String VALUE = "VALUE";
	
	private static Logger logger = LogManager.getLogger(ProsdeacServiceImpl.class.getName());
	
	private String getAutomationUrl() {
		return oumsysetService.getConfValue("serverConfig", "PRODUCT", "AUT_SER_URL");
	} 
	
	RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public List<BpmnProcess> processDeacExecuteQuery() {
		List<BpmnProcess> processList = new ArrayList<BpmnProcess>();
		List<String> procDefIdList = new ArrayList<String>();
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			String url= getAutomationUrl()+"/engine-rest/process-definition";
			List<Map<String, Object>> response = (List<Map<String, Object>>) restTemplate.getForObject(url, Object.class);
			for(Map<String, Object> obj: response) {
				procDefIdList.add((String) obj.get("id"));
			}
			List<BpmnProcess> processDetList = getProcessDetList(procDefIdList);
			for(Map<String, Object> obj: response) {
				for(BpmnProcess processDet : processDetList) {
					if(processDet.getProcDefId().equals(obj.get("id"))) {
						if("Y".equals(processDet.getTimerProcess())) {
							String bpmn = new String(processDet.getBpmnFile());
							processDet.setBpmn(bpmn);
							processDet.setProcessDesc((String) obj.get("key"));
							processDet.setProcessKey((String) obj.get("key"));
							processDet.setDefVersion(((Number) obj.get("version")).longValue());
							processDet.setDeployeId((String) obj.get("deploymentId"));
							processDet.setProcDefId((String) obj.get("id"));
							
							if((boolean) obj.get("suspended") == false) {
								processDet.setStatus("ACTIVE");
							} else {
								processDet.setStatus("INACTIVE");
							}
							processList.add(processDet);
						}
					}
				}
			}
			if (processList.size() > 0) {
				Collections.sort(processList, (final BpmnProcess object1, final BpmnProcess object2) -> {
						return object2.getDefVersion().compareTo(object1.getDefVersion());
					}
				);
			}
		} catch (Exception e) {
			logger.error("processDeacExecuteQuery : {}", e.getMessage());
		}
		return processList;
	}

	@Override
	public Integer suspendProcess(BpmnProcess bpmnProcess) {
		int objReturn = 0;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			String url= getAutomationUrl()+"/engine-rest/process-definition/"+bpmnProcess.getProcDefId()+"/suspended";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("suspended", true);
			jsonObject.put("includeProcessInstances", false);
			HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);
			restTemplate.put(url, request);
			objReturn = 1;
		} catch (Exception e) {
			logger.error("suspendProcess : {}", e.getMessage());
		}
		return objReturn;
	}
	
	private List<BpmnProcess> getProcessDetList(List<String> procDefIdList){
		List<BpmnProcess> processList = new ArrayList<BpmnProcess>();
		try {
			processList = prosdeacRepository.getProcessDetList(procDefIdList);
		} catch (Exception e) {
			logger.error("getProcessDetList : {}", e.getMessage());
		}
		return processList;
	}


	@Override
	public Integer activatedProcess(BpmnProcess bpmnProcess) {
		int objReturn = 0;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			String url= getAutomationUrl()+"/engine-rest/process-definition/"+bpmnProcess.getProcDefId()+"/suspended";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("suspended", false);
			jsonObject.put("includeProcessInstances", false);
			HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);
			restTemplate.put(url, request);
			objReturn = 1;
		} catch (Exception e) {
			logger.error("activatedProcess : {}", e.getMessage());
		}
		return objReturn;
	}

	@Override
	public Integer deleteProcess(BpmnProcess bpmnProcess) {
		int objReturn = 0;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			String url= getAutomationUrl()+"/engine-rest/process-definition/"+bpmnProcess.getProcDefId()+"?cascade=false";
			restTemplate.delete(url);
			objReturn = 1;
		} catch (Exception e) {
			logger.error("deleteProcess : {}", e.getMessage());
		}
		return objReturn;
	}
	
}

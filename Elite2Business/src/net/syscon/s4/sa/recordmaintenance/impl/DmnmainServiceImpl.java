package net.syscon.s4.sa.recordmaintenance.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.common.Utilities;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.globalconfiguration.OumsysetService;
import net.syscon.s4.sa.recordmaintenance.DmnProcess;
import net.syscon.s4.sa.recordmaintenance.DmnProcessCommitBean;
import net.syscon.s4.sa.recordmaintenance.DmnmainRepository;
import net.syscon.s4.sa.recordmaintenance.DmnmainService;

@Service
public class DmnmainServiceImpl implements DmnmainService{
	@Autowired
	private DmnmainRepository dmnmainRepository;
	@Autowired
	private OumsysetService oumsysetService;
	
	

	@Override
	public List<DmnProcess> dmnsExecuteQuery() {
		
		List<DmnProcess> dmnProcessList = dmnmainRepository.dmnsExecuteQuery();
		try {
			for(DmnProcess dmnProcess : dmnProcessList) {
				if(dmnProcess.getDmnFile()!=null) {
				//	byte[] bdata = dmnProcess.getDmnFile().getBytes(1, (int) dmnProcess.getDmnFile().length());
					byte[] bdata = dmnProcess.getDmnFile();
					String dmn = new String(bdata);
					dmnProcess.setDmnFile(null);
					dmnProcess.setDmn(dmn);
				}
					
				}
				
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dmnProcessList;
	}

	@Transactional
	public Integer dmnsCommit(DmnProcessCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(ele->ele.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = dmnmainRepository.insertDecision(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {	
			commitBean.getUpdateList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			if(commitBean.getSourceModule()!=null && (commitBean.getSourceModule().equals("DMNMAIN"))) {
				liReturn = dmnmainRepository.updateDecisionProcessCategory(commitBean.getUpdateList());
			}else {
			for(DmnProcess dmnData:commitBean.getUpdateList()) {
				byte[] byteArrray = dmnData.getDmn().getBytes();
				dmnData.setDmnByte(byteArrray);
				if(dmnData.getDefVersion() == null || dmnData.getDefVersion() == 1) {
					dmnData.setDefVersion((long) 1);
				}
			}
			liReturn = dmnmainRepository.updateDecision(commitBean.getUpdateList());
		}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			liReturn = dmnmainRepository.deleteDecision(commitBean.getDeleteList());
		}
		return liReturn;
	}
	
	
	
	
	private String getAutomationUrl() {
		return oumsysetService.getConfValue("serverConfig", "PRODUCT", "AUT_SER_URL");
	}
	
	RestTemplate restTemplate = new RestTemplate();
	@Override
	public Integer deployeDmn(DmnProcessCommitBean commitBean, String path) {
		int liReturn = 0;
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost(getAutomationUrl()+"/engine-rest/deployment/create");
	    
	    for(DmnProcess dmnDetails : commitBean.getUpdateList()) {
	    	String dmnFile = getDmnFile(dmnDetails);
	    	StringBody deploymentName = new StringBody(dmnDetails.getDefinitionDesc(), ContentType.TEXT_PLAIN);
	    	StringBody enableDuplicateFiltering = new StringBody("true", ContentType.TEXT_PLAIN);
	    	StringBody deployChangedOnly = new StringBody("true", ContentType.TEXT_PLAIN);

	    	MultipartEntityBuilder builder = MultipartEntityBuilder.create()
	    		.addPart("deployment-name", deploymentName)
	    		.addPart("enable-duplicate-filtering", enableDuplicateFiltering)
	    		.addPart("deploy-changed-only", deployChangedOnly);
	    
	    	byte[] buff = dmnFile.getBytes();
	    	File temp = null;
	    	
	    	try {
	    		temp = File.createTempFile("Sample", ".dmn");
	    		FileOutputStream outputStream = new FileOutputStream(temp);
	    		outputStream.write(buff);
	    	} catch (IOException e1) {
	    		e1.printStackTrace();
	    	}

	    	File resourceFile = new File(temp.getAbsolutePath());
	    	FileBody fileBody = new FileBody(resourceFile);
	    	builder.addPart(resourceFile.getName(), fileBody);
	    
	    	org.apache.http.HttpEntity httpEntity = builder.build();
	    	httpPost.setEntity(httpEntity);

	    	try {
	    		HttpResponse response = httpClient.execute(httpPost);
	    		String retSrc = EntityUtils.toString(response.getEntity());
	    		ObjectMapper objectMapper = new ObjectMapper();
	            Map<?,?> deployeMap = objectMapper.readValue(retSrc,Map.class);
	            if(deployeMap != null) {
	            	 Map<String, Object> dpdMap = (Map<String, Object>) deployeMap.get("deployedDecisionDefinitions");
	            	 if(dpdMap!=null) {
	            		 Map<String, Object> result = (Map<String, Object>) dpdMap.get((String) dpdMap.keySet().toArray()[0]);
	            		 if(result!=null) {
	            			 dmnDetails.setDefinitionKey(result.get("key").toString());
	            			 dmnDetails.setDeployeId(result.get("deploymentId").toString());
	            			 dmnDetails.setDefinitionId(result.get("id").toString());
	            		 }
	            		
	            	 }
	            	
	            }
	           
	            
	            
				
			
				liReturn = updateDeployeId(dmnDetails);
	    	} catch (ClientProtocolException e) {
	    		e.printStackTrace();
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}
	    	temp.deleteOnExit();
	    	try {
				Files.deleteIfExists(Paths.get(temp.getAbsolutePath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	    }
	    
		return liReturn;
	}
	
	private String getDmnFile(DmnProcess dmnProcess) {
		String dmnFile = null;
		DmnProcess process = dmnmainRepository.getDmnProcess(dmnProcess);
		try {
			if(process.getDmnFile()!=null) {
			//	byte[] dmnData = process.getDmnFile().getBytes(1, (int) process.getDmnFile().length());
				byte[] dmnData = process.getDmnFile();
				dmnFile = new String(dmnData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dmnFile;
	}
	
	private Integer updateDeployeId(DmnProcess bpmnProcess) {
		int liReturn = 0;
		liReturn = dmnmainRepository.updateDeployeId(bpmnProcess);
		return liReturn;
	}

	@Override
	public List<DmnProcess> getVersionHistory(DmnProcess dmnProcess) {
		List<DmnProcess> dmnProcessList = dmnmainRepository.getVersionHistory(dmnProcess);
		
		try {
			for(DmnProcess dmnProc : dmnProcessList) {
				if(dmnProc.getDmnFile()!=null) {
				//	byte[] bdata = dmnProc.getDmnFile().getBytes(1, (int) dmnProc.getDmnFile().length());
					byte[] bdata = dmnProc.getDmnFile();
					String bpmn = new String(bdata);
					dmnProc.setDmnFile(null);
					dmnProc.setDmn(bpmn);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dmnProcessList;
	}
	
	
	
	

	@Override
	public List<ReferenceCodes> getDmnsDeployedList() {
		return dmnmainRepository.getDmnsDeployedList();
	}

	@Override
	public List<DmnProcess> getDmnDataByDmnDesc(DmnProcess dmnDetails) {
		List<DmnProcess> dmnProcessList = dmnmainRepository.getDmnDataByDmnDesc(dmnDetails);
		try {
			for(DmnProcess dmnProcess : dmnProcessList) {
				if(dmnProcess.getDmnFile()!=null) {
				//	byte[] bdata = dmnProcess.getDmnFile().getBytes(1, (int) dmnProcess.getDmnFile().length());
					byte[] bdata = dmnProcess.getDmnFile();
					String dmn = new String(bdata);
					dmnProcess.setDmnFile(null);
					dmnProcess.setDmn(dmn);
				}
					
				}
				
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dmnProcessList;
	}

	@Override
	public String getDmnFile(String dmnProcessKey) {
		String dmn = null;
		Blob dmnFile =  dmnmainRepository.getDmnFile(dmnProcessKey);
		try {
			if(dmnFile != null) {
				byte[] bdata = dmnFile.getBytes(1, (int) dmnFile.length());
				dmn = new String(bdata);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return dmn;
	}
	public byte[] exportDmns(List<DmnProcess> lstOfDmns, String savedLocation) throws Exception {
		String respJson = null;
		String jsonFileName = "exportedDmns.json";
		String xmlFileExt = ".dmn";
		byte[] outZip = null;

		// tracks the created files for deletion 
		List<File> fileList = new ArrayList<>();

		try {
			
			// Create xml files	
			Iterator<DmnProcess> procIter = lstOfDmns.iterator();
			while(procIter.hasNext()) {
				DmnProcess dmnObj  = procIter.next();
				if(dmnObj.getDefinitionKey() != null) {
					String xmlFileName = dmnObj.getDefinitionKey()+xmlFileExt;

					// link file to process
					dmnObj.setXmlFileName(xmlFileName);

					// Create an xml file
					File xmlFile = new File(savedLocation+xmlFileName);
					fileList.add(xmlFile);
					FileOutputStream fo = new FileOutputStream(xmlFile);

					// write to xml file
					fo.write(dmnObj.getDmn().getBytes());

					// save and close the file
					fo.flush();
					fo.close();
					dmnObj.setDmn(null);
				} else {
					procIter.remove();
				}
			}
			
			// Create the json object from list and remove null values
			respJson = new ObjectMapper().setSerializationInclusion(Include.NON_NULL).writeValueAsString(lstOfDmns);
			
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
	
	public List<Map<String,Object>> importDmns(MultipartFile zipFile, String savedLocation) throws Exception {
		String jsonFileName = "exportedDmns.json";
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
			
			// add bpmn xml code to the json pbject
			for (Map<String,Object> dmnObj : parsedJson) {
				fileData = new StringBuilder();
				bytes = new byte[1024];
				String xmlFileName = String.valueOf(dmnObj.get("xmlFileName"));
				fis = new FileInputStream(savedLocation+xmlFileName);
				
				// using buffered reader to bypass text cut-off issue
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				while((xmlFileName = br.readLine()) != null) {
					fileData.append(xmlFileName);
				}
				
				// assign xml data to json
				dmnObj.put("bpmn", fileData.toString());
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
					// delete files 
					Files.deleteIfExists(filePath);
//					iter.next().delete();
				}
			}
		}
		return parsedJson;
	}

}

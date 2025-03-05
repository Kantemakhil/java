package net.syscon.s4.sa.recordmaintenance;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.DocManageUtilities;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OffAllowPayDetailsCommitBean;

@EliteController
public class CmdactionController {
	@Autowired
	private CmdactionService cmdactionService;
	
	@Autowired
	private DocManageUtilities docManageUtilities;
	
	private static Logger logger = LogManager.getLogger(CmdactionController.class.getName());
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/cmdaction/rgApiIdRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgApiIdRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = cmdactionService.rgApiIdRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')") 
	@RequestMapping(value = "/cmdaction/quickActionsExecuteQuery", method = RequestMethod.GET)
	public List<AutomationApiQuery> quickActionsExecuteQuery() {
		List<AutomationApiQuery> searchResult = new ArrayList<>();
		try {
			searchResult = cmdactionService.quickActionsExecuteQuery();
		} catch (Exception e) {
			AutomationApiQuery bean = new AutomationApiQuery();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/cmdaction/commitQuickActions")
	public @ResponseBody Integer commitQuickActions(@RequestBody AutomationApiQueryCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = cmdactionService.commitQuickActions(commitBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/cmdaction/parametersExecuteQuery", method = RequestMethod.POST)
	public List<AutomationQueryParameters> parametersExecuteQuery(@RequestBody AutomationApiQuery searchBean) {
		List<AutomationQueryParameters> searchResult = new ArrayList<>();
		try {
			searchResult = cmdactionService.parametersExecuteQuery(searchBean);
		} catch (Exception e) {
			AutomationQueryParameters bean = new AutomationQueryParameters();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/cmdaction/commitParameters")
	public @ResponseBody Integer commitParameters(@RequestBody AutomationQueryParametersCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = cmdactionService.commitParameters(commitBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	@RequestMapping(value="/cmdaction/executeUpdateQuery",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> executeUpdateQuery(@RequestBody String queryData, @RequestHeader HttpHeaders headers) {
		String respose = "";
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> newMemoModel = null;
		try {
			List<String> querykeyList = headers.get("querykey");
			String querykey = querykeyList.get(0).split(",")[0];
			if(queryData.contains("formInfoJson")) {
				queryData = queryData.replace("\"[", "[").replace("\"{", "{").replace("}\"", "}").replace("]\"", "]");
			}
			newMemoModel = new ObjectMapper().readValue(queryData, HashMap.class);
			newMemoModel.put("queryKey", querykey);
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			newMemoModel.put("createUserId",userName);
			newMemoModel.put("modifyUserId",userName);
			if(newMemoModel.containsKey("formInfoJson")) {
				newMemoModel.replace("formInfoJson", new ObjectMapper().writeValueAsString(newMemoModel.get("formInfoJson")).getBytes());
				newMemoModel.replace("formIdentifier", new ObjectMapper().writeValueAsString(newMemoModel.get("formIdentifier")));
			}
			respose = cmdactionService.executeUpdateQuery(newMemoModel);
			result.put("message", respose);
		} catch (Exception e) { 
			logger.error("cmdaction/executeUpdateQuery Error : "+ e.getMessage());
			e.printStackTrace();
			respose = "Query not executed";
			result.put("message", respose);
		}
		return result;
	}
	
	@RequestMapping(value="/cmdaction/batchUpdateQuery",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> batchUpdateQuery(@RequestBody String queryData, @RequestHeader HttpHeaders headers) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> newMemoModel = null;
		String respose = "";
		TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {};
		try {
			List<String> querykeyList = headers.get("querykey");
			String querykey = querykeyList.get(0).split(",")[0];
			queryData = queryData.replace("\"[", "[").replace("]\"", "]");
			newMemoModel = new ObjectMapper().readValue(queryData, typeRef);
		    newMemoModel.put("queryKey", querykey);
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			newMemoModel.put("createUserId",userName);
			newMemoModel.put("modifyUserId",userName);
			respose = cmdactionService.batchUpdateQuery(newMemoModel);
			result.put("message", respose);
		} catch (Exception e) { 
			logger.error("cmdaction/batchUpdateQuery Error : "+ e.getMessage());
			e.printStackTrace();
			respose = "Query not executed";
			result.put("message", respose);
		}
		return result;
	}
	
	@RequestMapping(value="/cmdaction/executeSelectQuery",method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> executeSelectQuery(@RequestBody String queryData, @RequestHeader HttpHeaders headers) {
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		List<Map<String, Object>> respose = new ArrayList<Map<String,Object>>();
		Map<String, Object> newMemoModel = null;
		try {
			List<String> querykeyList = headers.get("querykey");
			if(queryData.contains("formInfoJson")) {
				queryData = queryData.replace("\"[", "[").replace("\"{", "{").replace("}\"", "}").replace("]\"", "]");
			}
			String querykey = querykeyList.get(0).split(",")[0];
			newMemoModel = new ObjectMapper().configure(
				    JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), 
				    true
				).readValue(queryData, HashMap.class);
			newMemoModel.put("queryKey", querykey);
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			newMemoModel.put("createUserId",userName);
			newMemoModel.put("modifyUserId",userName);
			respose = cmdactionService.executeSelectQuery(newMemoModel, authorization);
		} catch (Exception e) { 
			logger.error("cmdaction/executeSelectQuery Error : "+ e.getMessage());
			e.printStackTrace();
		}
		return respose;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/cmdaction/rgParameterRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgParameterRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = cmdactionService.rgParameterRecordGroup();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@RequestMapping(value="/cmdaction/templateText",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> templateText(@RequestBody String queryData, @RequestHeader HttpHeaders headers) {
		String templateText = null;
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> mapObject = null;
		List<String> querykeyList = headers.get("querykey");
		
		String querykey = querykeyList.get(0).split(",")[0];
		try {
			mapObject = new ObjectMapper().readValue(queryData, HashMap.class);
			mapObject.put("queryKey", querykey);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		try {
			templateText =cmdactionService.templateText(mapObject);
		} catch (Exception e) {
			logger.error("Exception in getSeqQuery:", e);
		}
		result.put("templateText", templateText);
		return result;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/cmdaction/exportActions", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity exportActions(HttpServletRequest httpServletRequest, @RequestBody List<ActionApi> lstOfActionApi) {
		byte[] outFile = null;
		HttpHeaders headerRes = new HttpHeaders();
		Map<String, String> errorObj = new HashMap<>();
		errorObj.put("error", "");
		try {
			String savedLocation = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/action-export/");
			if(!new File(savedLocation).exists()) {
				new File(savedLocation).mkdir();
			}
			headerRes = docManageUtilities.getHttpHeadersForFileUpload("application/octet-stream", null, false);
			outFile = cmdactionService.exportActions(lstOfActionApi, savedLocation);
		} catch (Exception e) {
			logger.error("Exception :", e);
			errorObj.put("error", e.getMessage());
			return new ResponseEntity<>(errorObj, null, HttpStatus.OK);
			
		}
		return new ResponseEntity<>(outFile, headerRes, HttpStatus.OK);
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/cmdaction/importActions", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> importActions(HttpServletRequest httpServletRequest, @RequestParam("importFile") MultipartFile importFile) {
		Map<String,Object> importedActions = new HashMap<String, Object>();
		List<String> extensionList = Arrays.asList("zip");
		try {
			String savedLocation = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/action-export/");
			if(!new File(savedLocation).exists()) {
				new File(savedLocation).mkdir();
			}
			String extension = StringUtils.getFilenameExtension(importFile.getOriginalFilename());
			if(extension!=null && !extensionList.contains(extension.toLowerCase())) {
				return Collections.emptyMap();
			}
			importedActions = cmdactionService.importActions(importFile, savedLocation);
		} catch (Exception e) {
			logger.error("Exception :", e);
			return importedActions;
			
		}
		return importedActions;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/cmdaction/commitActions")
	public @ResponseBody Integer commitActions(@RequestBody Map<String,Object> obj) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			liReturn = cmdactionService.updateActions(obj, userName);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/cmdaction/getSeqQuery", method = RequestMethod.POST)
	public Map<String, Object> getSeqQuery(@RequestBody String queryData , @RequestHeader HttpHeaders headers) {
		Map<String, Object> searchResult = null;
		Map<String, Object> mapObject = null;
		List<String> querykeyList = headers.get("querykey");
		try {
			String querykey = querykeyList.get(0).split(",")[0];
			mapObject = new ObjectMapper().readValue(queryData, HashMap.class);
			mapObject.put("queryKey", querykey);
			searchResult = cmdactionService.getSeqQuery(mapObject);
		} catch (Exception e) {
			logger.error("Exception in getSeqQuery:", e);
		}
		return searchResult;
	}
	
	@RequestMapping(value="/cmdaction/remissionDueNotify",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> remissionDueNotify(@RequestBody String queryData, @RequestHeader HttpHeaders headers) {
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String result = "";
		Map<String, Object> response = new HashMap<String, Object>();
		Map<String, Object> newMemoModel = null;
		try {
			List<String> querykeyList = headers.get("querykey");
			if(queryData.contains("formInfoJson")) {
				queryData = queryData.replace("\"[", "[").replace("\"{", "{").replace("}\"", "}").replace("]\"", "]");
			}
			String querykey = querykeyList.get(0).split(",")[0];
			newMemoModel = new ObjectMapper().configure(
				    JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), 
				    true
				).readValue(queryData, HashMap.class);
			newMemoModel.put("queryKey", querykey);
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			newMemoModel.put("createUserId",userName);
			newMemoModel.put("modifyUserId",userName);
			result = cmdactionService.remissionDueNotify(newMemoModel);
			response.put("message", result);
		} catch (Exception e) { 
			response.put("message", "Failed to Send Remission Assessment Due Notification");
			logger.error("cmdaction/remissionDueNotify Error : "+ e.getMessage());
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(value="/cmdaction/saveOffAllowPayDet", method=RequestMethod.POST)
	public Map<String, Object> saveOffAllowPayDet(@RequestBody String queryData , @RequestHeader HttpHeaders headers) {
		Map<String, Object> response = new HashMap<String, Object>();
		final String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		OffAllowPayDetailsCommitBean commitBean = new OffAllowPayDetailsCommitBean();
		commitBean.setCreateUserId(user);
		try {
			cmdactionService.saveOffAllowPayDetValues(commitBean);
			response.put("message", ApplicationConstants.SUCCESSMSG);
		} catch (Exception e) {
			response.put("message", "Failed to Send Remission Assessment Due Notification");
			logger.error("cmdaction/saveOffAllowPayDet Error : {}  ", e.getMessage());
		}
		return response;
	}
}

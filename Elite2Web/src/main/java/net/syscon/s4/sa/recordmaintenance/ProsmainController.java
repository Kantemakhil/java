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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.syscon.s4.common.DocManageUtilities;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;

@EliteController
public class ProsmainController {
	@Autowired
	private ProsmainService processService;
	
	@Autowired
	private DocManageUtilities docManageUtilities;
	private static Logger logger = LogManager.getLogger(OumbadmiController.class.getName());
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/prosmain/processExecuteQuery", method = RequestMethod.GET)
	public List<BpmnProcess> processExecuteQuery() {
		List<BpmnProcess> searchResult = new ArrayList<>();
		try {
			searchResult = processService.processExecuteQuery();
		} catch (Exception e) {
			BpmnProcess bean = new BpmnProcess();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/prosmain/processCommit")
	public @ResponseBody Integer processCommit(@RequestBody BpmnProcessCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = processService.processCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/prosmain/deployeBpmn", method = RequestMethod.POST)
	public @ResponseBody Integer deployeBpmn(@RequestBody BpmnProcessCommitBean commitBean, HttpServletRequest request) {
		int liReturn = 0;
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/");
		try {
			liReturn = processService.deployeBpmn(commitBean, path);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/prosmain/gerVersionHistory", method = RequestMethod.POST)
	public @ResponseBody List<BpmnProcess> getVersionHistory(@RequestBody BpmnProcess bpmnProcess) {
		List<BpmnProcess> searchResult = new ArrayList<>();
		try {
			searchResult = processService.getVersionHistory(bpmnProcess);
		} catch (Exception e) {
			BpmnProcess bean = new BpmnProcess();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/prosmain/getProcess", method = RequestMethod.POST)
	public @ResponseBody List<BpmnProcess> getProcess(@RequestBody BpmnProcess bpmnProcess) {
		List<BpmnProcess> searchResult = new ArrayList<>();
		try {
			searchResult = processService.getProcess(bpmnProcess);
		} catch (Exception e) {
			BpmnProcess bean = new BpmnProcess();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/prosmain/getProcessData", method = RequestMethod.GET)
	public @ResponseBody BpmnProcess getProcessData(@RequestParam(value = "processId") final String processId) {
	BpmnProcess processData = new BpmnProcess();
		try {
			processData = processService.getProcessData(processId);
		} catch (Exception e) {
			logger.error("Exception :", e);
			processData.setErrorMessage(e.getMessage());
			
		}
		return processData;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/prosmain/exportProcesses", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity exportProcesses(HttpServletRequest httpServletRequest, @RequestBody List<BpmnProcess> lstOfProcessMain) {
		byte[] outFile = null;
		HttpHeaders headerRes = new HttpHeaders();
		Map<String, String> errorObj = new HashMap<>();
		errorObj.put("error", "");
		try {
			String savedLocation = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/process-export/");
			if(!new File(savedLocation).exists()) {
				new File(savedLocation).mkdir();
			}
			headerRes = docManageUtilities.getHttpHeadersForFileUpload("application/octet-stream", null, false);
			outFile = processService.exportProcesses(lstOfProcessMain, savedLocation);
		} catch (Exception e) {
			logger.error("Exception :", e);
			errorObj.put("error", e.getMessage());
			return new ResponseEntity<>(errorObj, null, HttpStatus.OK);
			
		}
		return new ResponseEntity<>(outFile, headerRes, HttpStatus.OK);
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/prosmain/importProcesses", method = RequestMethod.POST)
	public @ResponseBody List<Map<String,Object>> importProcesses(HttpServletRequest httpServletRequest, @RequestParam("importFile") MultipartFile importFile) {
		List<Map<String,Object>> importedPrecesses = new ArrayList<>();
		List<String> extensionList = Arrays.asList("zip");
		try {
			String savedLocation = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/process-export/");
			if(!new File(savedLocation).exists()) {
				new File(savedLocation).mkdir();
			}
			
			String extension = StringUtils.getFilenameExtension(importFile.getOriginalFilename());
			if(extension!=null && !extensionList.contains(extension.toLowerCase())) {
				return Collections.emptyList();
			}
			importedPrecesses = processService.importProcesses(importFile, savedLocation);
		} catch (Exception e) {
			logger.error("Exception :", e);
			return importedPrecesses;
			
		}
		return importedPrecesses;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/prosmain/deployBulkProcesses", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> deployBulkProcesses(@RequestBody List<BpmnProcess> bpmnList, HttpServletRequest request) {
		Map<String,Object> liReturn = new HashMap<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		for(BpmnProcess bpmnProcess : bpmnList) {
			bpmnProcess.setCreateUserId(userName);
			bpmnProcess.setModifyUserId(userName);
		}
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/process-deploy/");
		if(!new File(path).exists()) {
			new File(path).mkdir();
		}
		try {
			liReturn = processService.deployBulkProcesses(bpmnList, path);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/prosmain/commonProcess", method = RequestMethod.GET)
	public List<ReferenceCodes> getCommonProcess() {
		List<ReferenceCodes> searchResult = new ArrayList<>();
		try {
			searchResult = processService.getCommonProcess();
		} catch (Exception e) {
			ReferenceCodes bean = new ReferenceCodes();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/prosmain/processBulkCommit")
	public @ResponseBody Integer processBulkCommit(@RequestBody BpmnProcessCommitBean bpmnProcessCommitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			bpmnProcessCommitBean.setCreateUserId(userName);
			if(bpmnProcessCommitBean.getInsertList() != null && !bpmnProcessCommitBean.getInsertList().isEmpty())
			bpmnProcessCommitBean.getInsertList().forEach(obj -> {
				obj.setCreateUserId(userName);
				obj.setModifyUserId(userName);
			});
			if(bpmnProcessCommitBean.getUpdateList() != null && !bpmnProcessCommitBean.getUpdateList().isEmpty())
				bpmnProcessCommitBean.getUpdateList().forEach(obj -> {
					obj.setCreateUserId(userName);
					obj.setModifyUserId(userName);
				});
			liReturn = processService.processBulkCommit(bpmnProcessCommitBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}
}

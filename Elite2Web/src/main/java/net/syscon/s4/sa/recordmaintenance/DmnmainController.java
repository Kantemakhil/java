package net.syscon.s4.sa.recordmaintenance;

import java.io.File;
import java.util.ArrayList;
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
public class DmnmainController {
	@Autowired
	private DmnmainService dmnService;
	
	@Autowired
	private DocManageUtilities docManageUtilities;
	
	private static Logger logger = LogManager.getLogger(OumbadmiController.class.getName());
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/dmnmain/dmnExecuteQuery", method = RequestMethod.GET)
	public List<DmnProcess> dmnExecuteQuery() {
		List<DmnProcess> searchResult = new ArrayList<>();
		try {
			searchResult = dmnService.dmnsExecuteQuery();
		} catch (Exception e) {
			DmnProcess bean = new DmnProcess();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/dmnmain/decisionCommit")
	public @ResponseBody Integer decisionCommit(@RequestBody DmnProcessCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = dmnService.dmnsCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/dmnmain/deployeDmn", method = RequestMethod.POST)
	public @ResponseBody Integer deployeDmn(@RequestBody DmnProcessCommitBean commitBean, HttpServletRequest request) {
		int liReturn = 0;
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/TRIM/");
		try {
			liReturn = dmnService.deployeDmn(commitBean, path);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/dmnmain/gerVersionHistory", method = RequestMethod.POST)
	public @ResponseBody List<DmnProcess> getVersionHistory(@RequestBody DmnProcess dmnProcess) {
		List<DmnProcess> searchResult = new ArrayList<>();
		try {
			searchResult = dmnService.getVersionHistory(dmnProcess);
		} catch (Exception e) {
			DmnProcess bean = new DmnProcess();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/dmnmain/getDmns", method = RequestMethod.GET)
	public List<ReferenceCodes> getDmnsDeployedList() {
		List<ReferenceCodes> searchResult = new ArrayList<>();
		try {
			searchResult = dmnService.getDmnsDeployedList();
		} catch (Exception e) {
			ReferenceCodes bean = new ReferenceCodes();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/dmnmain/getDmnDataByDmnDesc", method = RequestMethod.POST)
	public @ResponseBody List<DmnProcess> getDmnDataByDmnDesc(@RequestBody DmnProcess dmnProcess) {
		List<DmnProcess> searchResult = new ArrayList<>();
		try {
			searchResult = dmnService.getDmnDataByDmnDesc(dmnProcess);
		} catch (Exception e) {
			DmnProcess bean = new DmnProcess();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/dmnmain/getDmnFile", method = RequestMethod.POST)
	public String getDmnFile(@RequestBody String dmnProcessKey) {
		String dmnFile = null;
		try {
			dmnFile = dmnService.getDmnFile(dmnProcessKey);
		} catch (Exception e) {
			return dmnFile;
		}
		return dmnFile;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/dmnmain/exportDmns", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity exportDmns(HttpServletRequest httpServletRequest, @RequestBody List<DmnProcess> lstOfDmns) {
		byte[] outFile = null;
		HttpHeaders headerRes = new HttpHeaders();
		Map<String, String> errorObj = new HashMap<>();
		errorObj.put("error", "");
		try {
			String savedLocation = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/dmn-export/");
			if(!new File(savedLocation).exists()) {
				new File(savedLocation).mkdir();
			}
			headerRes = docManageUtilities.getHttpHeadersForFileUpload("application/octet-stream", null, false);
			outFile = dmnService.exportDmns(lstOfDmns, savedLocation);
		} catch (Exception e) {
			logger.error("Exception :", e);
			errorObj.put("error", e.getMessage());
			return new ResponseEntity<>(errorObj, null, HttpStatus.OK);
			
		}
		return new ResponseEntity<>(outFile, headerRes, HttpStatus.OK);
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/dmnmain/importDmns", method = RequestMethod.POST)
	public @ResponseBody List<Map<String,Object>> importDmns(HttpServletRequest httpServletRequest, @RequestParam("importFile") MultipartFile importFile) {
		List<Map<String,Object>> importedDmns = new ArrayList<>();
		try {
			String savedLocation = httpServletRequest.getSession().getServletContext().getRealPath("/WEB-INF/classes/dmn-export/");
			if(!new File(savedLocation).exists()) {
				new File(savedLocation).mkdir();
			}
			importedDmns = dmnService.importDmns(importFile, savedLocation);
		} catch (Exception e) {
			logger.error("Exception :", e);
			return importedDmns;
			
		}
		return importedDmns;
	}
}

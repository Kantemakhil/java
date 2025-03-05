package net.syscon.s4.cm.programsservices.maintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OcmsvmodController {
	@Autowired
	private OcmsvmodService ocmsvmodService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmsvmodController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmsvmod/vPrgMdlsExecuteQuery", method = RequestMethod.POST)
	public List<VProgramModules> vPrgMdlsExecuteQuery(@RequestBody VProgramModules searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<VProgramModules> searchResult = new ArrayList<>();
		try {
			searchResult = ocmsvmodService.vPrgMdlsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update in the
	 * database table
	 * 
	 * @Param commitBean
	 * @return VProgramModules
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmsvmod/vPrgMdlsCommit", method = RequestMethod.POST)
	public @ResponseBody VProgramModules vPrgMdlsCommit(@RequestBody VProgramModulesCommitBean commitBean) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		VProgramModules liReturn = new VProgramModules();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmsvmodService.vPrgMdlsCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception : Ocmsvmod", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the Max value of listSeq from database table
	 * 
	 * @Param obj
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocmsvmod/valueSeq", method = RequestMethod.POST)
	public Integer getListSeq(@RequestBody VProgramModules obj) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		Integer searchResult = null;
		try {
			searchResult = ocmsvmodService.getListSeqMaxCount(obj.getProgramPhaseId());
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCMSVMOD");
	}

}
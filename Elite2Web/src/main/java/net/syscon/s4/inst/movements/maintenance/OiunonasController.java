package net.syscon.s4.inst.movements.maintenance;

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
import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.common.beans.AgyIntLocProfilesCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * Class OiunonasController
 */
@EliteController
public class OiunonasController {
	@Autowired
	private OiunonasService oiunonasService;
	
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiunonasController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiunonas/intLocProfExecuteQuery", method = RequestMethod.POST)
	public List<AgyIntLocProfiles> intLocProfExecuteQuery(@RequestBody final AgyIntLocProfiles searchBean) {
		List<AgyIntLocProfiles> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oiunonasService.intLocProfExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiunonas/intLocProfCommit", method = RequestMethod.POST)
	public @ResponseBody Integer intLocProfCommit(@RequestBody final AgyIntLocProfilesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			liReturn = oiunonasService.intLocProfCommit(commitBean);
		} catch (Exception e) {
			String error = e.getMessage().toUpperCase();
			if (error.contains("AGY_INT_LOC_PROFILES_PK")) {
				liReturn = 2;
			}
			logger.error("Exception : Oiunonas", e);
		}
		return liReturn;
	}

	/**
	 * getting rgNonAssoType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiunonas/rgNonAssoTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgNonAssoTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oiunonasService.rgNonAssoTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oiunonas:", e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OIUNONAS");
	}

}
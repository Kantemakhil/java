package net.syscon.s4.inst.securitythreatgroups;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.im.beans.SecurityThreatGroupsCommitBean;
import net.syscon.s4.im.beans.StgLocations;
import net.syscon.s4.im.beans.StgLocationsCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OidstghlController {
	@Autowired
	private OidstghlService oidstghlService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidstghlController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstghl/stgExecuteQuery", method = RequestMethod.POST)
	public List<SecurityThreatGroups> stgExecuteQuery(@RequestBody final SecurityThreatGroups searchBean) {
		List<SecurityThreatGroups> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oidstghlService.stgExecuteQuery(searchBean);
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
	@RequestMapping(value = "/oidstghl/stgCommit", method = RequestMethod.POST)
	public @ResponseBody Integer stgCommit(@RequestBody final SecurityThreatGroupsCommitBean commitBean) {
		int liReturn = 0;
		if (!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidstghlService.stgCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception : Oidstghl", e);
		}
		return liReturn;
	}

	/**
	 * getting recCity LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstghl/recCityRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> recCityRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oidstghlService.recCityRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Oidstghl:", e);
		}
		return recordList;
	}

	/**
	 * getting recState LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstghl/recStateRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> recStateRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oidstghlService.recStateRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Oidstghl:", e);
		}
		return recordList;
	}

	/**
	 * getting recCountry LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstghl/recCountryRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> recCountryRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oidstghlService.recCountryRecordGroup();
		} catch (Exception e) {
			logger.error("Exception : Oidstghl:", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstghl/stgLocationsExecuteQuery", method = RequestMethod.POST)
	public List<StgLocations> stgLocationsExecuteQuery(@RequestBody final StgLocations searchBean) {
		List<StgLocations> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oidstghlService.stgLocationsExecuteQuery(searchBean);
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
	@RequestMapping(value = "/oidstghl/stgLocationsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer stgLocationsCommit(@RequestBody final StgLocationsCommitBean commitBean) {
		int liReturn = 0;
		if (!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidstghlService.stgLocationsCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception : Oidstghl", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstghl/stgOnCheckDeleteMaster", method = RequestMethod.POST)
	public List<StgLocations> stgOnCheckDeleteMaster(@RequestBody final StgLocations searchBean) {
		List<StgLocations> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oidstghlService.stgOnCheckDeleteMaster(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstghl/cgwhenNewFormInstance", method = RequestMethod.GET)
	public List<StaffMemberRoles> cgwhenNewFormInstance() {
		List<StaffMemberRoles> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchResult = oidstghlService.cgwhenNewFormInstance(userName);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role, "OIDSTGHL");
	}
}
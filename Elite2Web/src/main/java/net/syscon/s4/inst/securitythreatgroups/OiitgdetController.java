package net.syscon.s4.inst.securitythreatgroups;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.StgValidations;
import net.syscon.s4.common.beans.VStgLocationPresence;
import net.syscon.s4.common.beans.VStgRacialMakeup;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OiitgdetController {
	@Autowired
	private OiitgdetService oiitgdetService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiitgdetController.class.getName());

	/**
	 * getting rgStg2 LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiitgdet/rgStg2RecordGroup", method = RequestMethod.GET)
	public List<SecurityThreatGroups> rgStg2RecordGroup() {
		List<SecurityThreatGroups> recordList = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oiitgdetService.rgStg2RecordGroup();
		} catch (final Exception e) {
			final SecurityThreatGroups obj = new SecurityThreatGroups();
			logger.error("rgStg2RecordGroup : ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStg1 LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiitgdet/rgStg1RecordGroup", method = RequestMethod.GET)
	public List<SecurityThreatGroups> rgStg1RecordGroup() {
		List<SecurityThreatGroups> recordList = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oiitgdetService.rgStg1RecordGroup();
		} catch (final Exception e) {
			final SecurityThreatGroups obj = new SecurityThreatGroups();
			logger.error("rgStg1RecordGroup : ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStg3 LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiitgdet/rgStg3RecordGroup", method = RequestMethod.GET)
	public List<SecurityThreatGroups> rgStg3RecordGroup() {
		List<SecurityThreatGroups> recordList = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oiitgdetService.rgStg3RecordGroup();
		} catch (final Exception e) {
			final SecurityThreatGroups obj = new SecurityThreatGroups();
			logger.error("rgStg3RecordGroup : ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiitgdet/stgValidationsExecuteQuery", method = RequestMethod.POST)
	public List<StgValidations> stgValidationsExecuteQuery(@RequestBody final StgValidations searchBean) {
		List<StgValidations> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oiitgdetService.stgValidationsExecuteQuery(searchBean);
		} catch (final Exception e) {
			final StgValidations bean = new StgValidations();
			logger.error("stgValidationsExecuteQuery : ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiitgdet/stgRaceMakeupExecuteQuery", method = RequestMethod.POST)
	public List<VStgRacialMakeup> stgRaceMakeupExecuteQuery(@RequestBody final VStgRacialMakeup searchBean) {
		List<VStgRacialMakeup> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oiitgdetService.stgRaceMakeupExecuteQuery(searchBean);
		} catch (final Exception e) {
			final VStgRacialMakeup bean = new VStgRacialMakeup();
			logger.error("stgRaceMakeupExecuteQuery : ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiitgdet/stgLocationPresenceExecuteQuery", method = RequestMethod.POST)
	public List<VStgLocationPresence> stgLocationPresenceExecuteQuery(
			@RequestBody final VStgLocationPresence searchBean) {
		List<VStgLocationPresence> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oiitgdetService.stgLocationPresenceExecuteQuery(searchBean);
		} catch (final Exception e) {
			final VStgLocationPresence bean = new VStgLocationPresence();
			logger.error("stgLocationPresenceExecuteQuery : ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiitgdet/fafExecuteQuery", method = RequestMethod.POST)
	public List<FormAccessibleForms> fafExecuteQuery(@RequestBody final FormAccessibleForms searchBean) {
		List<FormAccessibleForms> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = oiitgdetService.fafExecuteQuery(searchBean);
		} catch (final Exception e) {
			final FormAccessibleForms bean = new FormAccessibleForms();
			logger.error("fafExecuteQuery : ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiitgdet/stgDetailKeyExeqry", method = RequestMethod.GET)
	public Map<String, BigDecimal> stgDetailKeyExeqry(@RequestParam(value = "stgId") final BigDecimal stgId) {
		Map<String, BigDecimal> searchResult = null;
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oiitgdetService.stgDetailKeyExeqry(stgId);
		} catch (final Exception e) {
			logger.error("stgDetailKeyExeqry : ", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiitgdet/oiitgdetWhenNewFormInstance", method = RequestMethod.GET)
	public Map<String, String> oiitgdetWhenNewFormInstance() {
		Map<String, String> searchResult = new HashMap<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oiitgdetService.oiitgdetWhenNewFormInstance();
		} catch (final Exception e) {
			searchResult.put("value", null);
			logger.error("oiitgdetWhenNewFormInstance : ", e);
		}
		return searchResult;

	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiitgdet/oiitgdetPrimaryCur", method = RequestMethod.GET)
	public String oiitgdetPrimaryCur(@RequestParam(value = "stgId") final BigDecimal stgId) {
		String searchResult = "Y";
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oiitgdetService.oiitgdetPrimaryCur(stgId);
		} catch (final Exception e) {
			logger.error("oiitgdetPrimaryCur : ", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiitgdet/oiitgdetGetProfileValue", method = RequestMethod.GET)
	public String oiitgdetGetProfileValue(@RequestParam(value = "profileType") final String profileType,
			@RequestParam(value = "profileCode") final String profileCode) {
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		String searchResult = "N";
		try {
			searchResult = oiitgdetService.oiitgdetGetProfileValue(profileType, profileCode);
		} catch (final Exception e) {
			logger.error("oiitgdetGetProfileValue : ", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiitgdet/oiitgdetGetGroupPrivilege", method = RequestMethod.GET)
	public String oiitgdetGetProfileValue() {
		String searchResult = "";
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchResult = oiitgdetService.oiitgdetGetGroupPrivilege(userName);
		} catch (final Exception e) {
			logger.error("oiitgdetGetGroupPrivilege : ", e);
		}
		return searchResult;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role, "OIITGDET");
	}

}
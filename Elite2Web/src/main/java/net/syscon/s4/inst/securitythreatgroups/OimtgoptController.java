package net.syscon.s4.inst.securitythreatgroups;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.SecurityThreatGroupsHty;
import net.syscon.s4.common.beans.SecurityThreatGroupsHtyCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * OimtgoptController
 */
@EliteController
public class OimtgoptController {
	@Autowired
	private OimtgoptService oimtgoptService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimtgoptController.class.getName());

	/**
	 * getting lNation LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oimtgopt/lNationRecordGroup", method = RequestMethod.GET)
	public List<SecurityThreatGroups> lNationRecordGroup(
			@RequestParam(value = "parentStgId") final Integer parentStgId) {
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<SecurityThreatGroups> recordList = new ArrayList<SecurityThreatGroups>();
		try {
			recordList = oimtgoptService.lNationRecordGroup(parentStgId);
		} catch (final Exception e) {
			logger.error("lNationRecordGroup :", e);

		}
		return recordList;
	}

	/**
	 * getting lGang LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oimtgopt/lGangRecordGroup", method = RequestMethod.GET)
	public List<SecurityThreatGroups> lGangRecordGroup(@RequestParam(value = "parentStgId") final Integer parentStgId) {
		List<SecurityThreatGroups> recordList = new ArrayList<SecurityThreatGroups>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oimtgoptService.lGangRecordGroup(parentStgId);
		} catch (final Exception e) {
			logger.error("lGangRecordGroup :", e);

		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oimtgopt/stgHtyExecuteQuery", method = RequestMethod.POST)
	public List<SecurityThreatGroupsHty> stgHtyExecuteQuery(@RequestBody final SecurityThreatGroupsHty searchBean) {
		List<SecurityThreatGroupsHty> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oimtgoptService.stgHtyExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("stgHtyExecuteQuery :", e);

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
	@RequestMapping(value = "/oimtgopt/stgHtyCommit", method = RequestMethod.POST)
	public @ResponseBody Integer stgHtyCommit(@RequestBody final SecurityThreatGroupsHtyCommitBean commitBean) {
		int liReturn = 0;
		if (!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (Optional.ofNullable(commitBean).isPresent())
				commitBean.setCreateUserId(
						SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			liReturn = oimtgoptService.stgHtyCommit(commitBean);
		} catch (final Exception e) {

			logger.error("stgHtyCommit :", e); 
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oimtgopt/stgHty1ExecuteQuery", method = RequestMethod.POST)
	public List<SecurityThreatGroupsHty> stgHty1ExecuteQuery(@RequestBody final SecurityThreatGroupsHty searchBean) {
		List<SecurityThreatGroupsHty> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oimtgoptService.stgHty1ExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("stgHty1ExecuteQuery :", e);

		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oimtgopt/cgtewhenRadioChanged", method = RequestMethod.GET)
	public BigDecimal cgtewhenRadioChanged(@RequestParam(value = "stgId") final BigDecimal stgId) {
		BigDecimal searchResult = BigDecimal.ZERO;
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oimtgoptService.cgtewhenRadioChanged(stgId);
		} catch (final Exception e) {
			logger.error("cgtewhenRadioChanged :", e);

		}
		return searchResult;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role, "OIMTGOPT");
	}
}
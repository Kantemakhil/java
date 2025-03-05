package net.syscon.s4.inst.securitythreatgroups;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.im.beans.StgRelationships;
import net.syscon.s4.im.beans.StgRelationshipsCommitBean;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OidstgaeController {
	@Autowired
	private OidstgaeService oidstgaeService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidstgaeController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgae/stgRltExecuteQuery", method = RequestMethod.POST)
	public List<StgRelationships> stgRltExecuteQuery(@RequestBody final StgRelationships searchBean) {
		List<StgRelationships> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oidstgaeService.stgRltExecuteQuery(searchBean);
		} catch (final Exception e) {
			final StgRelationships bean = new StgRelationships();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
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
	@RequestMapping(value = "/oidstgae/stgRltCommit", method = RequestMethod.POST)
	public @ResponseBody String stgRltCommit(@RequestBody final StgRelationshipsCommitBean commitBean) {
		String liReturn = "0";
		if (!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidstgaeService.stgRltCommit(commitBean);
		} catch (final Exception e) {
			liReturn = e.getMessage();
			logger.error("Exception : Oidstgae: stgRltCommit: ", e);
		}
		return liReturn;
	}

	/**
	 * getting rgStg2 LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgae/rgStg2RecordGroup", method = RequestMethod.GET)
	public List<SecurityThreatGroups> rgStg2RecordGroup(@RequestParam(value = "stgId") final BigDecimal stgId) {
		List<SecurityThreatGroups> recordList = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oidstgaeService.rgStg2RecordGroup(stgId);
		} catch (final Exception e) {
			logger.error("Exception : Oidstgae: rgStg2RecordGroup ", e);
		}
		return recordList;
	}

	/**
	 * getting rgStg1 LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgae/rgStg1RecordGroup", method = RequestMethod.GET)
	public List<SecurityThreatGroups> rgStg1RecordGroup(@RequestParam(value = "stgId") final BigDecimal stgId) {
		List<SecurityThreatGroups> recordList = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oidstgaeService.rgStg1RecordGroup(stgId);
		} catch (final Exception e) {
			logger.error("Exception : Oidstgae: rgStg1RecordGroup ", e);
		}
		return recordList;
	}

	/**
	 * getting rgStg3 LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgae/rgStg3RecordGroup", method = RequestMethod.GET)
	public List<SecurityThreatGroups> rgStg3RecordGroup(@RequestParam(value = "stgId") final BigDecimal stgId) {
		List<SecurityThreatGroups> recordList = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oidstgaeService.rgStg3RecordGroup(stgId);
		} catch (final Exception e) {
			logger.error("Exception : Oidstgae: rgStg3RecordGroup : ", e);
		}
		return recordList;
	}

	/**
	 * getting recReason2 LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgae/recReason2RecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> recReason2RecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oidstgaeService.recReason2RecordGroup();
		} catch (final Exception e) {
			logger.error("Exception : Oidstgae: recReason2RecordGroup : ", e);
		}
		return recordList;
	}

	/**
	 * getting recStg2 LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgae/recStg2RecordGroup", method = RequestMethod.GET)
	public List<SecurityThreatGroups> recStg2RecordGroup() {
		List<SecurityThreatGroups> recordList = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oidstgaeService.recStg2RecordGroup();
		} catch (final Exception e) {
			logger.error("Exception : Oidstgae: recStg2RecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting recStg LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgae/recStgRecordGroup", method = RequestMethod.GET)
	public List<SecurityThreatGroups> recStgRecordGroup() {
		List<SecurityThreatGroups> recordList = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oidstgaeService.recStgRecordGroup();
		} catch (final Exception e) {
			logger.error("Exception : Oidstgae: recStgRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting recReason LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgae/recReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> recReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oidstgaeService.recReasonRecordGroup();
		} catch (final Exception e) {
			logger.error("Exception : Oidstgae: recReasonRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgae/stgRelationshipsExecuteQuery", method = RequestMethod.POST)
	public List<StgRelationships> stgRelationshipsExecuteQuery(@RequestBody final StgRelationships searchBean) {
		List<StgRelationships> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oidstgaeService.stgRelationshipsExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception : oidstgae: stgRelationshipsExecuteQuery: ", e);
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
	@RequestMapping(value = "/oidstgae/stgRelationshipsCommit", method = RequestMethod.POST)
	public @ResponseBody String stgRelationshipsCommit(@RequestBody final StgRelationshipsCommitBean commitBean) {
		String liReturn = "0";
		if (!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidstgaeService.stgRelationshipsCommit(commitBean);
		} catch (final Exception e) {
			liReturn = e.getMessage();
			logger.error("Exception : Oidstgae", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgae/groupLovRecordGroupNumber", method = RequestMethod.GET)
	public String groupLovRecordGroupNumber() {
		String returnVal = null;
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			returnVal = oidstgaeService.groupLovRecordGroupNumber();
		} catch (final Exception e) {
			logger.error("Exception : Oidstgae: groupLovRecordGroupNumber: ", e);
		}
		return returnVal;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgae/stgRltGroupPostChange", method = RequestMethod.GET)
	public BigDecimal stgRltGroupPostChange(@RequestParam(value = "stgId") final BigDecimal stgid,
			@RequestParam(value = "relatedStgId") final BigDecimal relatedStgId) {
		BigDecimal returnVal = BigDecimal.ZERO;
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			returnVal = oidstgaeService.stgRltGroupPostChange(stgid, relatedStgId);
		} catch (final Exception e) {
			logger.error("Exception : Oidstgae: stgRltGroupPostChange: ", e);
		}
		return returnVal;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgae/stgRltCheckBoxChange", method = RequestMethod.GET)
	public BigDecimal stgRltCheckBoxChange(@RequestParam(value = "stgId") final BigDecimal stgid,
			@RequestParam(value = "relatedStgId") final BigDecimal relatedStgId) {
		BigDecimal returnVal = BigDecimal.ZERO;
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			returnVal = oidstgaeService.stgRltCheckBoxChange(stgid, relatedStgId);
		} catch (final Exception e) {
			logger.error("Exception : Oidstgae: stgRltCheckBoxChange: ", e);
		}
		return returnVal;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstgae/stgRelationshipsCheckBoxChange", method = RequestMethod.GET)
	public BigDecimal stgRelationshipsCheckBoxChange(@RequestParam(value = "stgId") final BigDecimal stgid,
			@RequestParam(value = "relatedStgId") final BigDecimal relatedStgId) {
		BigDecimal returnVal = BigDecimal.ZERO;
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			returnVal = oidstgaeService.stgRelationshipsCheckBoxChange(stgid, relatedStgId);
		} catch (final Exception e) {
			logger.error("Exception : Oidstgae: stgRelationshipsCheckBoxChange: ", e);
		}
		return returnVal;
	}

	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role, "OIDSTGAE");
	}
}
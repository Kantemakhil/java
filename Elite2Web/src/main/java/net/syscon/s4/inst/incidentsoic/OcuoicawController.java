package net.syscon.s4.inst.incidentsoic;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderOicSanctions;
import net.syscon.s4.common.beans.OffenderOicSanctionsCommitBean;
import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.inst.incidentsoic.maintenance.OicSanctionLimits;
import net.syscon.s4.pkgs.common.CommonService;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * class OcuoicawController
 */
@EliteController
public class OcuoicawController {
	@Autowired
	private OcuoicawService ocuoicawService;
	
	@Autowired
	private ProsmainService prosmainService;
	
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuoicawController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<OffenderOicSanctions>
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoicaw/oicSancExecuteQuery", method = RequestMethod.POST)
	public List<OffenderOicSanctions> oicSancSearchOffenderOicSanctions(
			@RequestBody final OffenderOicSanctions searchBean) {
		List<OffenderOicSanctions> searchResult = new ArrayList<>();
		final OffenderOicSanctions bean = new OffenderOicSanctions();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = ocuoicawService.oicSancSearchOffenderOicSanctions(searchBean);
		} catch (Exception e) {
			logger.error("Exception raised in oicSancSearchOffenderOicSanctions ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Performing insert,delete, update
	 * 
	 * @return Integer
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoicaw/oicSancCommit", method = RequestMethod.POST)
	public @ResponseBody List<OffenderOicSanctions> oicSancCommit(@RequestBody final OffenderOicSanctionsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		List<OffenderOicSanctions> listcount = null;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			listcount = ocuoicawService.oicSancCommit(commitBean);
			if(listcount != null && listcount.size() > 0) {
				prosmainService.enableTriggers(commitBean, authorization, "129");
			}
		} catch (Exception e) {
			logger.error("Exception raised in oicSancCommit", e);
		}
		return listcount;
	}

	/**
	 * getting rgOtherSanctions LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuoicaw/rgOtherSanctionsRecordGroup", method = RequestMethod.POST)
	public List<OffenderOicSanctions> rgOtherSanctionsRecordGroup(
			@RequestBody final OffenderOicSanctions offenderOicSan) {
		List<OffenderOicSanctions> recordList = new ArrayList<OffenderOicSanctions>();
		final OffenderOicSanctions obj = new OffenderOicSanctions();
		try {
			recordList = ocuoicawService.rgOtherSanctionsRecordGroup(offenderOicSan);
		} catch (Exception e) {
			logger.error("Exception raised in rgOtherSanctionsRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgSanct LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuoicaw/rgSanctRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSanctRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocuoicawService.rgSanctRecordGroup();
		} catch (Exception e) {
			logger.error("Exception raised in rgSanctRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgSanctStRecordGroup LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuoicaw/rgSanctStRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSanctStRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocuoicawService.rgSanctStRecordGroup();
		} catch (Exception e) {
			logger.error("Exception raised in rgSanctStRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * method for query calling
	 * 
	 * @return List<OicOffences>
	 * @param paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuoicaw/whenvalidateitemgetoicoffencecodecur", method = RequestMethod.POST)
	public @ResponseBody List<OicOffences> whenValidateItemgetOicOffenceCodeCur(
			@RequestBody final OicOffences paramBean) {
		List<OicOffences> listOfRecords = new ArrayList<>();
		final OicOffences bean = new OicOffences();
		try {
			listOfRecords = ocuoicawService.whenValidateItemgetOicOffenceCodeCur(paramBean);
		} catch (Exception e) {
			logger.error("Exception raised in whenValidateItemgetOicOffenceCodeCur", e);
			listOfRecords.add(bean);
		}
		return listOfRecords;
	}

	/**
	 * method for query calling
	 * 
	 * @return SystemProfiles
	 * @param paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuoicaw/getprofilevaluevsprofvalcur", method = RequestMethod.GET)
	public @ResponseBody SystemProfiles getprofilevaluevsprofvalcur(@RequestBody final SystemProfiles paramBean) {
		SystemProfiles dataObj = new SystemProfiles();
		try {
			dataObj = ocuoicawService.getprofilevaluevsprofvalcur(paramBean);
		} catch (Exception e) {
			logger.error("Exception raised in getprofilevaluevsprofvalcur", e);
		}
		return dataObj;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuoicaw/getHearingType", method = RequestMethod.POST)
	public @ResponseBody OicSanctionLimits getHearingType(@RequestBody final OffenderOicSanctions offenderOicSan) {
		OicSanctionLimits dataObj = new OicSanctionLimits();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			dataObj = ocuoicawService.getHearingType(offenderOicSan);
		} catch (Exception e) {
			logger.error("Exception raised in getprofilevaluevsprofvalcur", e);
		}
		return dataObj;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCUOICAW");
	}
}
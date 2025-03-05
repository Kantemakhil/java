package net.syscon.s4.inst.casemanagement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.inst.casemanagement.beans.OffenderSampleSubstances;
import net.syscon.s4.inst.casemanagement.beans.OffenderSampleSubstancesCommitBean;
import net.syscon.s4.inst.casemanagement.beans.OffenderSamples;
import net.syscon.s4.inst.casemanagement.beans.OffenderSamplesCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OidstestController
 */
@EliteController
public class OidstestController {
	@Autowired
	private OidstestService oidstestService;
	
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidstestController.class.getName());

	/**
	 * getting rgWitness LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstest/rgWitnessRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgWitnessRecordGroup(@RequestParam(value = "parentField") final String parentField) {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = oidstestService.rgWitnessRecordGroup(parentField);
		} catch (Exception e) {
			logger.error("In method rgWitnessRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgSubTesRslt LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstest/rgSubTesRsltRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSubTesRsltRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidstestService.rgSubTesRsltRecordGroup();
		} catch (Exception e) {
			logger.error("In method rgSubTesRsltRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgSubTesDisp LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstest/rgSubTesDispRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSubTesDispRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidstestService.rgSubTesDispRecordGroup();
		} catch (Exception e) {
			logger.error("In method rgSubTesDispRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgSubTesType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstest/rgSubTesTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSubTesTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidstestService.rgSubTesTypeRecordGroup();
		} catch (Exception e) {
			logger.error("In method rgSubTesTypeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgSubTesRsn LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstest/rgSubTesRsnRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSubTesRsnRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidstestService.rgSubTesRsnRecordGroup();
		} catch (Exception e) {
			logger.error("In method rgSubTesRsnRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgTakenBy LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstest/rgTakenByRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgTakenByRecordGroup(@RequestParam(value = "parentField") final String parentField) {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = oidstestService.rgTakenByRecordGroup(parentField);
		} catch (Exception e) {
			logger.error("In method rgTakenByRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgTestedBy LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstest/rgTestedByRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgTestedByRecordGroup(@RequestParam(value = "parentField") final String parentField) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidstestService.rgTestedByRecordGroup(parentField);
		} catch (Exception e) {
			logger.error("In method rgTestedByRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgSubstance LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstest/rgSubstanceRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSubstanceRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidstestService.rgSubstanceRecordGroup();
		} catch (Exception e) {
			logger.error("In method rgSubstanceRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @param searchBean{@link OffenderSamples}
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstest/subSamplExecuteQuery", method = RequestMethod.POST)
	public List<OffenderSamples> subSamplExecuteQuery(@RequestBody final OffenderSamples searchBean) {
		List<OffenderSamples> searchResult = new ArrayList<>();
		try {
			searchResult = oidstestService.subSamplExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In method subSamplExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidstest/offSamplCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offSamplCommit(@RequestBody final OffenderSamplesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oidstestService.offSamplCommit(commitBean);
			if(1==liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "30");
			}
		} catch (Exception e) {
			logger.error("In method subSamplCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link OffenderSampleSubstances}
	 * @return a list of the OffenderSampleSubstances {@link OffenderSampleSubstances} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstest/offenderSampleSubstancesExecuteQuery", method = RequestMethod.POST)
	public List<OffenderSampleSubstances> offenderSampleSubstancesExecuteQuery(
			@RequestBody final OffenderSampleSubstances searchBean) {
		List<OffenderSampleSubstances> searchResult = new ArrayList<>();
		try {
			searchResult = oidstestService.offenderSampleSubstancesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In method offenderSampleSubstancesExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidstest/subTestCommit", method = RequestMethod.POST)
	public @ResponseBody Integer subTestCommit(@RequestBody final OffenderSampleSubstancesCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oidstestService.subTestCommit(commitBean);
			if(1==liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "31");
			}
		} catch (Exception e) {
			logger.error("In method subTestCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link SystemProfiles}
	 * @return a list of the SystemProfiles {@link SystemProfiles} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstest/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oidstestService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("In method sysPflExecuteQuery", e);
		}
		return searchResult;
	}


	/**
	 * Fetching the staffId from database table
	 * 
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidstest/getStaffId", method = RequestMethod.GET)
	public BigDecimal getStaffId() {
		BigDecimal returnVal = BigDecimal.ZERO;
		final String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			returnVal = oidstestService.getStaffId(userId);
		} catch (Exception e) {
			logger.error("In method getStaffId", e);
		}
		return returnVal;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidstest/checkDocumentDependency", method = RequestMethod.POST)
	public Boolean  checkDocumentDependency(@RequestBody Map<String,Object> inputObj) {
		try {
			String displayNo =  inputObj.containsKey(ApplicationConstants.DISPLAY_NO)?inputObj.get(ApplicationConstants.DISPLAY_NO).toString():null;
			String screenId =  inputObj.containsKey("formName")?inputObj.get("formName").toString():null;
			Integer offenderBookId = inputObj.containsKey("offenderBookId")? Integer.parseInt( inputObj.get("offenderBookId").toString()):null;
			return oidstestService.checkDocumentDependency(offenderBookId,screenId,displayNo);
		} catch (Exception e) {
			logger.error("checkOrderDependency inputObj: {}",inputObj );
		}
		return false;
	}
}
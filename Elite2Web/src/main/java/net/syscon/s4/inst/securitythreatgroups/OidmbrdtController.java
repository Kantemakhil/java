package net.syscon.s4.inst.securitythreatgroups;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.OffenderStgAffiliationsCommitBean;
import net.syscon.s4.im.beans.OffenderStgDetails;
import net.syscon.s4.im.beans.OffenderStgDetailsCommitBean;

/**
 * OidmbrdtController
 */
@EliteController
public class OidmbrdtController {
	@Autowired
	private OidmbrdtService oidmbrdtService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidmbrdtController.class.getName());

	/**
	 * getting rgGroup LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmbrdt/rgGroupRecordGroup", method = RequestMethod.GET)
	public List<SecurityThreatGroups> rgGroupRecordGroup() {
		List<SecurityThreatGroups> recordList = new ArrayList<>();
		try {
			recordList = oidmbrdtService.rgGroupRecordGroup();
		} catch (Exception e) {
			final SecurityThreatGroups obj = new SecurityThreatGroups();
			logger.error("Exception : Oidmbrdt:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmbrdt/rgReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oidmbrdtService.rgReasonRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oidmbrdt:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgExpReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmbrdt/rgExpReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgExpReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oidmbrdtService.rgExpReasonRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oidmbrdt:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStg1 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmbrdt/rgStg1RecordGroup", method = RequestMethod.GET)
	public List<SecurityThreatGroups> rgStg1RecordGroup() {
		List<SecurityThreatGroups> recordList = new ArrayList<>();
		try {
			recordList = oidmbrdtService.rgStg1RecordGroup();
		} catch (Exception e) {
			final SecurityThreatGroups obj = new SecurityThreatGroups();
			logger.error("Exception : Oidmbrdt:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStg2 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmbrdt/rgStg2RecordGroup", method = RequestMethod.GET)
	public List<SecurityThreatGroups> rgStg2RecordGroup() {
		List<SecurityThreatGroups> recordList = new ArrayList<>();
		try {
			recordList = oidmbrdtService.rgStg2RecordGroup();
		} catch (Exception e) {
			final SecurityThreatGroups obj = new SecurityThreatGroups();
			logger.error("Exception : Oidmbrdt:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStg3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmbrdt/rgStg3RecordGroup", method = RequestMethod.GET)
	public List<SecurityThreatGroups> rgStg3RecordGroup() {
		List<SecurityThreatGroups> recordList = new ArrayList<>();
		try {
			recordList = oidmbrdtService.rgStg3RecordGroup();
		} catch (Exception e) {
			final SecurityThreatGroups obj = new SecurityThreatGroups();
			logger.error("Exception : Oidmbrdt:", e);
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
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmbrdt/offenderAssessmentsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderAssessments> offenderAssessmentsExecuteQuery(
			@RequestBody final OffenderAssessments searchBean) {
		List<OffenderAssessments> searchResult = new ArrayList<>();
		try {
			searchResult = oidmbrdtService.offenderAssessmentsExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderAssessments bean = new OffenderAssessments();
			logger.error("Exception :", e);
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
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmbrdt/offenderStgAffiliationsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderStgAffiliations> offenderStgAffiliationsExecuteQuery(
			@RequestBody final OffenderStgAffiliations searchBean) {
		List<OffenderStgAffiliations> searchResult = new ArrayList<>();
		try {
			searchResult = oidmbrdtService.offenderStgAffiliationsExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderStgAffiliations bean = new OffenderStgAffiliations();
			logger.error("Exception :", e);
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
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidmbrdt/offenderStgAffiliationsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offenderStgAffiliationsCommit(
			@RequestBody final OffenderStgAffiliationsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidmbrdtService.offenderStgAffiliationsCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception : Oidmbrdt", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmbrdt/offenderStgDetailsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderStgDetails> offenderStgDetailsExecuteQuery(@RequestBody final OffenderStgDetails searchBean) {
		List<OffenderStgDetails> searchResult = new ArrayList<>();
		try {
			searchResult = oidmbrdtService.offenderStgDetailsExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderStgDetails bean = new OffenderStgDetails();
			logger.error("Exception :", e);
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
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidmbrdt/offenderStgDetailsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offenderStgDetailsCommit(@RequestBody final OffenderStgDetailsCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = oidmbrdtService.offenderStgDetailsCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception : Oidmbrdt", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmbrdt/formAccessibleFormsExecuteQuery", method = RequestMethod.GET)
	public List<FormAccessibleForms> formAccessibleFormsExecuteQuery(
			@RequestParam(value = "offenderBookId") final String offenderBookId,
			@RequestParam(value = "offenderId") final String offenderId) {
		List<FormAccessibleForms> searchResult = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchResult = oidmbrdtService.formAccessibleFormsExecuteQuery(offenderBookId, offenderId, userName);
		} catch (Exception e) {
			final FormAccessibleForms bean = new FormAccessibleForms();
			logger.error("Exception :", e);
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
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmbrdt/populateRg", method = RequestMethod.GET)
	public SystemProfiles populateRg() {
		SystemProfiles searchParam = new SystemProfiles();
		try {
			searchParam = oidmbrdtService.populateRg();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchParam;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidmbrdt/checkGroupFlag", method = RequestMethod.POST)
	public List<Integer> checkGroupFlag(@RequestBody final OffenderStgAffiliations searchBean) {
		List<Integer> searchResult = new ArrayList<>();
		try {
			searchResult = oidmbrdtService.checkGroupFlag(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
}
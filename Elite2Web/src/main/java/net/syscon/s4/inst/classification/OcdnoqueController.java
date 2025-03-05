package net.syscon.s4.inst.classification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.beans.OffenderAssessmentsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.classification.beans.AssessmentSupervisions;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.AssessmentsCommitBean;
import net.syscon.s4.inst.classification.beans.VOffassAss;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OcdnoqueController
 */
@EliteController
public class OcdnoqueController {
	@Autowired
	private OcdnoqueService ocdnoqueService;
	
	@Autowired
	private OcunoqueService ocunoqueService;
	
	@Autowired
	private ProsmainService prosmainService;
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdnoqueController.class.getName());

	/**
	 * getting rgAssessmentTypeId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdnoque/rgAssessmentTypeIdRecordGroup", method = RequestMethod.GET)
	public List<Assessments> rgAssessmentTypeIdRecordGroup(
			@RequestParam(value = "programid") final BigDecimal programid) {
		List<Assessments> recordList = new ArrayList<Assessments>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			recordList = ocdnoqueService.rgAssessmentTypeIdRecordGroup(programid, userName);
		} catch (Exception e) {
			final Assessments obj = new Assessments();
			logger.error("In method rgAssessmentTypeIdRecordGroup", e);
			recordList.add(obj);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdnoque/rgAssessmentTypeIdRecordGroupWithoutProgramid", method = RequestMethod.GET)
	public List<Assessments> rgAssessmentTypeIdRecordGroupWithoutProgramid() {
		List<Assessments> recordList = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			recordList = ocdnoqueService.rgAssessmentTypeIdRecordGroupWithoutProgramid(userName);
		} catch (Exception e) {
			final Assessments obj = new Assessments();
			logger.error("In method rgAssessmentTypeIdRecordGroup", e);
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAssessCommitteCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdnoque/rgAssessCommitteCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAssessCommitteCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			recordList = ocdnoqueService.rgAssessCommitteCodeRecordGroup(userName);
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgAssessCommitteCodeRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgAgencyLocations LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdnoque/rgAgencyLocationsRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgencyLocationsRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			recordList = ocdnoqueService.rgAgencyLocationsRecordGroup(userName);
		} catch (Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("In method rgAgencyLocationsRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgStaffMembers LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdnoque/rgStaffMembersRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgStaffMembersRecordGroup() {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = ocdnoqueService.rgStaffMembersRecordGroup();
		} catch (Exception e) {
			final StaffMembers obj = new StaffMembers();
			logger.error("In method rgStaffMembersRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgOverridedSupLevelType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdnoque/rgOverridedSupLevelTypeRecordGroup", method = RequestMethod.GET)
	public List<AssessmentResults> rgOverridedSupLevelTypeRecordGroup(
			@RequestParam(value = "assessmentId") final Integer assessmentId) {
		List<AssessmentResults> recordList = new ArrayList<AssessmentResults>();
		try {
			recordList = ocdnoqueService.rgOverridedSupLevelTypeRecordGroup(assessmentId);
		} catch (Exception e) {
			final AssessmentResults obj = new AssessmentResults();
			logger.error("In method rgOverridedSupLevelTypeRecordGroup", e);
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgPlaceAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdnoque/rgPlaceAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgPlaceAgyLocIdRecordGroup() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ocdnoqueService.rgPlaceAgyLocIdRecordGroup(userName);
		} catch (Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("In method rgPlaceAgyLocIdRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgOverrideReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdnoque/rgOverrideReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgOverrideReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdnoqueService.rgOverrideReasonRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgOverrideReasonRecordGroup", e);
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
	@RequestMapping(value = "/ocdnoque/offAssExecuteQuery", method = RequestMethod.POST)
	public List<VOffassAss> offAssExecuteQuery(@RequestBody final VOffassAss searchBean) {
		List<VOffassAss> searchResult = new ArrayList<>();
		try {
			searchResult = ocdnoqueService.offAssExecuteQuery(searchBean);
		} catch (Exception e) {
			final VOffassAss bean = new VOffassAss();
			logger.error("In method offAssExecuteQuery", e);
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
	@RequestMapping(value = "/ocdnoque/offAss1ExecuteQuery", method = RequestMethod.POST)
	public List<OffenderAssessments> offAss1ExecuteQuery(@RequestBody final OffenderAssessments searchBean) {
		List<OffenderAssessments> searchResult = new ArrayList<>();
		try {
			searchResult = ocdnoqueService.offAss1ExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderAssessments bean = new OffenderAssessments();
			logger.error("In method offAss1ExecuteQuery", e);
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
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdnoque/offAss1Commit", method = RequestMethod.POST)
	public @ResponseBody Integer offAss1Commit(@RequestBody final OffenderAssessmentsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocdnoqueService.offAss1Commit(commitBean);
		} catch (Exception e) {
			logger.error("In method offAss1Commit", e);
		}
		return liReturn;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdnoque/offAss1PreInsert", method = RequestMethod.POST)
	public @ResponseBody Integer offAss1PreInsert(@RequestBody final OffenderAssessments searchBean) {
		Integer liReturn = 0;
		try {
			liReturn = ocdnoqueService.offAss1PreInsert(searchBean);
		} catch (Exception e) {
			logger.error("In method offAss1Commit", e);
		}
		return liReturn;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdnoque/getMaxAssessmentDateCur", method = RequestMethod.POST)
	public @ResponseBody String getMaxAssessmentDateCur(@RequestBody final OffenderAssessments searchBean) {
		String liReturn = null;
		try {
			liReturn = ocdnoqueService.getMaxAssessmentDateCur(searchBean);
			if (liReturn == null) {
				liReturn = " ";
			}
		} catch (Exception e) {
			logger.error("In method getMaxAssessmentDateCur", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdnoque/offAssPostQuery", method = RequestMethod.POST)
	public @ResponseBody Integer offAssPostQuery(@RequestBody final OffenderAssessments searchBean) {
		Integer liReturn = 0;
		try {
			liReturn = ocdnoqueService.offAssPostQuery(searchBean);
		} catch (Exception e) {
			logger.error("In method offAssPostQuery", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdnoque/scoreRange", method = RequestMethod.GET)
	public List<AssessmentSupervisions> scoreRange() {
		List<AssessmentSupervisions> returnList = new ArrayList<AssessmentSupervisions>();
		try {
			returnList = ocdnoqueService.scoreRange();
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdnoque/assessmentDetailsAuthority", method = RequestMethod.GET)
	public List<ReferenceCodes> assessmentDetailsAuthority(@RequestParam final String parentcode) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();

		try {
			recordList = ocdnoqueService.assessmentDetailsAuthority(parentcode);
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method assessmentDetailsAuthority", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	
	// copied form ocunoqueController for security reasons 
	
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdnoque/assessExecuteQuery", method = RequestMethod.POST)
	public List<Assessments> assessExecuteQuery(@RequestBody final Assessments searchBean) {
		List<Assessments> searchResult = new ArrayList<>();
		try {
			searchResult = ocunoqueService.assessExecuteQuery(searchBean);
		} catch (Exception e) {
			final Assessments bean = new Assessments();
			logger.error(e);
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
	@RequestMapping(value = "/ocdnoque/getCommentText", method = RequestMethod.POST)
	public OffenderAssessments getCommentText(@RequestBody final OffenderAssessments searchBean) {
		OffenderAssessments searchResult = new OffenderAssessments();
		try {
			searchResult = ocunoqueService.getCommentText(searchBean);
		} catch (Exception e) {
			logger.error("getCommentText: ", e);
		}
		return searchResult;
	}

	/**
	 * getting rgRank LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdnoque/rgRankRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgRankRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocunoqueService.rgRankRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
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
	@RequestMapping(value = "/ocdnoque/assessQuestionsExecuteQuery", method = RequestMethod.POST)
	public List<Assessments> assessQuestionsExecuteQuery(@RequestBody final Assessments searchBean) {
		List<Assessments> searchResult = new ArrayList<>();
		try {
			searchResult = ocunoqueService.assessQuestionsExecuteQuery(searchBean);
		} catch (Exception e) {
			final Assessments bean = new Assessments();
			logger.error(e);
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
	@RequestMapping(value = "/ocdnoque/answersExecuteQuery", method = RequestMethod.POST)
	public List<Assessments> answersExecuteQuery(@RequestBody final Assessments searchBean) {
		List<Assessments> searchResult = new ArrayList<>();
		try {
			searchResult = ocunoqueService.answersExecuteQuery(searchBean);
		} catch (Exception e) {
			final Assessments bean = new Assessments();
			logger.error(e);
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
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdnoque/answersCommit", method = RequestMethod.POST)
	public @ResponseBody Integer answersCommit(@RequestBody final AssessmentsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);

			liReturn = ocunoqueService.answersCommit(commitBean);
			if(liReturn == 1) {
				OffenderAssessmentsCommitBean triggerBean = new OffenderAssessmentsCommitBean();
				List<OffenderAssessments> insertList = new ArrayList<OffenderAssessments>();
				insertList.add(commitBean.getOffAssesModel());
				triggerBean.setInsertList(insertList);
				prosmainService.enableTriggers(triggerBean, authorization, "118");
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdnoque/assessCommitExecuteQuery", method = RequestMethod.POST)
	public AssessmentsCommitBean assessCommitExecuteQuery(@RequestBody final Assessments searchBean) {
		AssessmentsCommitBean searchResult = new AssessmentsCommitBean();
		try {
			searchResult = ocunoqueService.assessCommitExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("assessCommitExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdnoque/getAssessmentScore", method = RequestMethod.POST)
	public AssessmentSupervisions getAssessmentScore(@RequestBody final OffenderAssessments searchBean) {
		AssessmentSupervisions searchResult = new AssessmentSupervisions();
		try {
			searchResult = ocunoqueService.getAssessmentScore(searchBean);
		} catch (Exception e) {
			logger.error("getCommentText: ", e);
		}
		return searchResult;
	}
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdnoque/rgAssessmentTypeEVALRecordGroup", method = RequestMethod.GET)
	public List<Assessments> rgAssessmentTypeEVALRecordGroup() {
		List<Assessments> recordList = new ArrayList<Assessments>();
		try {
			recordList = ocdnoqueService.rgAssessmentTypeEVALRecordGroup();
		} catch (Exception e) {
			final Assessments obj = new Assessments();
			logger.error("In method rgAssessmentTypeEVALRecordGroup", e);
			recordList.add(obj);
		}
		return recordList;
	}
}
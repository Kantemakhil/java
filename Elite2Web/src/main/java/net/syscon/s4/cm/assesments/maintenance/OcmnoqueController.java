package net.syscon.s4.cm.assesments.maintenance;

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

import net.syscon.s4.cm.assesments.beans.AssessmentResultsCommitBean;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.AssessmentsCommitBean;

@EliteController
public class OcmnoqueController {
	@Autowired
	private OcmnoqueService ocmnoqueService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmnoqueController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmnoque/assessExecuteQuery", method = RequestMethod.POST)
	public List<Assessments> assessExecuteQuery(@RequestBody final Assessments searchBean) {
		List<Assessments> searchResult = new ArrayList<>();
		try {
			searchResult = ocmnoqueService.assessExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocmnoque/assessCommit", method = RequestMethod.POST)
	public @ResponseBody Integer assessCommit(@RequestBody final AssessmentsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocmnoqueService.assessCommit(commitBean);
		} catch (final Exception e) {
			logger.error("Exception : Ocmnoque", e);
		}
		return liReturn;
	}

	/**
	 * getting rgCaseloadType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmnoque/rgCaseloadTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCaseloadTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmnoqueService.rgCaseloadTypeRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Ocmnoque:", e);
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
	@RequestMapping(value = "/ocmnoque/assSectExecuteQuery", method = RequestMethod.GET)
	public List<Assessments> assSectExecuteQuery(@RequestParam final Long assessmentId) {
		List<Assessments> searchResult = new ArrayList<>();
		final Assessments objAssessments = new Assessments();
		objAssessments.setAssessmentId(assessmentId);
		try {
			searchResult = ocmnoqueService.assSectExecuteQuery(objAssessments);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmnoque/assSectLov", method = RequestMethod.GET)
	public List<Assessments> assSectLov(@RequestParam(value = "assessmentId") final Long assessmentId) {
		List<Assessments> searchResult = new ArrayList<>();

		try {
			searchResult = ocmnoqueService.assSectLov(assessmentId);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmnoque/assSectionLov", method = RequestMethod.GET)
	public List<Assessments> assSectionLov(@RequestParam(value = "assessmentId") final Long assessmentId) {
		List<Assessments> searchResult = new ArrayList<>();

		try {
			searchResult = ocmnoqueService.assSectLov(assessmentId);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmnoque/assQueLov", method = RequestMethod.GET)
	public List<Assessments> assQueLov(@RequestParam(value = "assessmentId") final Long assessmentId) {
		List<Assessments> searchResult = new ArrayList<>();

		try {
			searchResult = ocmnoqueService.assQueLov(assessmentId);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmnoque/assQuestionsLov", method = RequestMethod.GET)
	public List<Assessments> assQuestionsLov(@RequestParam(value = "assessmentId") final Long assessmentId) {
		List<Assessments> searchResult = new ArrayList<>();

		try {
			searchResult = ocmnoqueService.assQueLov(assessmentId);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmnoque/assQueExecuteQuery", method = RequestMethod.POST)
	public List<Assessments> assQue2ExecuteQuery(@RequestBody final Assessments searchBean) {
		List<Assessments> searchResult = new ArrayList<>();
		try {
			searchResult = ocmnoqueService.assQueExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmnoque/assAnsExecuteQuery", method = RequestMethod.POST)
	public List<Assessments> assAns2ExecuteQuery(@RequestBody final Assessments searchBean) {
		List<Assessments> searchResult = new ArrayList<>();
		try {
			searchResult = ocmnoqueService.assAnsExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmnoque/assResExecuteQuery", method = RequestMethod.POST)
	public List<AssessmentResults> assResExecuteQuery(@RequestBody final AssessmentResults searchBean) {
		List<AssessmentResults> searchResult = new ArrayList<>();
		try {
			searchResult = ocmnoqueService.assResExecuteQuery(searchBean);
		} catch (final Exception e) {
			final AssessmentResults bean = new AssessmentResults();
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
	@RequestMapping(value = "/ocmnoque/assResCommit", method = RequestMethod.POST)
	public @ResponseBody Integer assResCommit(@RequestBody final AssessmentResultsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocmnoqueService.assResCommit(commitBean);
		} catch (final Exception e) {

			logger.error("Exception : Ocmnoque", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmnoque/validateCaseLoad", method = RequestMethod.GET)
	public Integer validateCaseLoad(@RequestParam(value = "assessmentId") final Long assessmentId) {
		Integer count = 0;

		try {
			count = ocmnoqueService.validateCaseLoad(assessmentId);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */

	@PreAuthorize("hasEliteRole('read')")

	@RequestMapping(value = "/ocmnoque/checkAssesRelations", method = RequestMethod.GET)
	public Boolean checkAssesRelations(@RequestParam(value = "assessmentId") final Long assessmentId) {
		Boolean checkAssesRelations = false;

		try {
			checkAssesRelations = ocmnoqueService.checkAssesRelations(assessmentId);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return checkAssesRelations;
	}

	/**
	 * Assess key delete rec.
	 *
	 * @param assessmentId
	 *            the assessment id
	 * @return the boolean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmnoque/assessKeyDeleteRec", method = RequestMethod.GET)
	public Boolean assessKeyDeleteRec(@RequestParam(value = "assessmentId") final Long assessmentId) {
		Boolean checkAssesRelations = false;

		try {
			checkAssesRelations = ocmnoqueService.assessKeyDeleteRec(assessmentId);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return checkAssesRelations;
	}

	/**
	 * Assess res key delete rec.
	 *
	 * @param searchBean
	 *            the search bean
	 * @return the boolean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmnoque/assessResKeyDeleteRec", method = RequestMethod.POST)
	public Boolean assessResKeyDeleteRec(@RequestBody final AssessmentResults searchBean) {
		Boolean checkAssesResRelation = false;
		try {
			checkAssesResRelation = ocmnoqueService.assessResKeyDeleteRec(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return checkAssesResRelation;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmnoque/getDefaultAssessmentType", method = RequestMethod.GET)
	public String getDefaultAssessmentType() {
		String assessmentType = null;
		try {
			assessmentType = ocmnoqueService.getDefaultAssessmentType();
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return assessmentType;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmnoque/rgBookMarkRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgBookMarkRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocmnoqueService.rgBookMarkRecordGroup();
		} catch (final Exception e) {
			logger.error(this.getClass().getName()+" Exception : in rgBookMarkRecordGroupmethod call", e);
		}
		return recordList;
	}
}
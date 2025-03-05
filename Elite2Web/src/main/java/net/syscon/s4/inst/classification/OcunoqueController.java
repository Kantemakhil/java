package net.syscon.s4.inst.classification;

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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.beans.OffenderAssessmentsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.classification.beans.AssessmentSupervisions;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.AssessmentsCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * @author Arkin Software Technologies
 * @version 1.0
 */
@EliteController
public class OcunoqueController {
	@Autowired
	private OcunoqueService ocunoqueService;
	
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcunoqueController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocunoque/assessExecuteQuery", method = RequestMethod.POST)
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
	@RequestMapping(value = "/ocunoque/getCommentText", method = RequestMethod.POST)
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
	@RequestMapping(value = "/ocunoque/rgRankRecordGroup", method = RequestMethod.GET)
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
	@RequestMapping(value = "/ocunoque/assessQuestionsExecuteQuery", method = RequestMethod.POST)
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
	@RequestMapping(value = "/ocunoque/answersExecuteQuery", method = RequestMethod.POST)
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
	@RequestMapping(value = "/ocunoque/answersCommit", method = RequestMethod.POST)
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
	@RequestMapping(value = "/ocunoque/assessCommitExecuteQuery", method = RequestMethod.POST)
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
	@RequestMapping(value = "/ocunoque/getAssessmentScore", method = RequestMethod.POST)
	public AssessmentSupervisions getAssessmentScore(@RequestBody final OffenderAssessments searchBean) {
		AssessmentSupervisions searchResult = new AssessmentSupervisions();
		try {
			searchResult = ocunoqueService.getAssessmentScore(searchBean);
		} catch (Exception e) {
			logger.error("getCommentText: ", e);
		}
		return searchResult;
	}
}
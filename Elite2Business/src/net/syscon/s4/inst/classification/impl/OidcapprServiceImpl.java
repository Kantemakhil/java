package net.syscon.s4.inst.classification.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderAssessment;
import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.beans.OffenderAssessmentsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.classification.OcunoqueService;
import net.syscon.s4.inst.classification.OidcapprRepository;
import net.syscon.s4.inst.classification.OidcapprService;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.AssessmentsCommitBean;
import net.syscon.s4.inst.classification.beans.VOffassAss;
import net.syscon.s4.inst.classification.beans.VOffassAssCommitBean;
import net.syscon.s4.inst.demographicsbiometrics.OcuverifRepository;
import net.syscon.s4.triggers.OffenderAssessmentsT1Service;
import net.syscon.s4.triggers.OffenderAssessmentsTwf1Service;

/**
 * Class OidcapprServiceImpl
 */
@Service
public class OidcapprServiceImpl extends BaseBusiness implements OidcapprService {

	@Autowired
	private OidcapprRepository oidcapprRepo;
	@Autowired
	private OcunoqueService ocunoqueService;

	@Autowired
	private OffenderAssessmentsT1Service offenderAssessmentsT1Service;

	@Autowired
	private OffenderAssessmentsTwf1Service offenderAssessmentsTwf1Service;
	
	@Autowired
	private OcuverifRepository ocuverifRepo;

	/**
	 * Creates new OidcapprServiceImpl class Object
	 */
	public OidcapprServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public Assessments offAss1PostQuery(final Assessments paramBean) {
		return oidcapprRepo.offAss1PostQuery(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffAss1OffAssR2(final ReferenceCodes paramBean) {
		return oidcapprRepo.cgfkchkOffAss1OffAssR2(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public AgencyLocations cgfkchkOffAss1OffAssAgy(final AgencyLocations paramBean) {
		return oidcapprRepo.cgfkchkOffAss1OffAssAgy(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public Assessments postQueryForOffAss(final Assessments paramBean) {
		return oidcapprRepo.postQueryForOffAss(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes getComSupDef(final ReferenceCodes paramBean) {
		return oidcapprRepo.getComSupDef(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes getInstSupDef(final ReferenceCodes paramBean) {
		return oidcapprRepo.getInstSupDef(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes getRefCodeDesc(final ReferenceCodes paramBean) {
		return oidcapprRepo.getRefCodeDesc(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public Assessments cgfkchkOffAssOffAssAss(final Assessments paramBean) {
		return oidcapprRepo.cgfkchkOffAssOffAssAss(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffAssOffAssRef(final ReferenceCodes paramBean) {
		return oidcapprRepo.cgfkchkOffAssOffAssRef(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VOffassAss> offAssExecuteQuery(final VOffassAss searchRecord) {
		final List<VOffassAss> returnList = oidcapprRepo.offAssExecuteQuery(searchRecord);
		Assessments assessments = new Assessments();
		Assessments assObj = new Assessments();
		for (final VOffassAss obj : returnList) {
			assessments = new Assessments();
			assessments.setAssessmentId(Long.valueOf(obj.getAssessmentTypeId().toString()));
			assObj = oidcapprRepo.cgfkchkOffAssOffAssAss(assessments);
			obj.setAssessmentTypeDesc(assObj.getDescription());
			if (obj.getEvaluationResultCode() != null) {
				if (obj.getReviewCommitteCode() == null && obj.getEvaluationResultCode().equalsIgnoreCase("APP")) {
					final Assessments returnAss = oidcapprRepo.postQueryForOffAss(assessments);
					if (returnAss.getDetermineSupLevelFlag().equalsIgnoreCase("Y")
							&& returnAss.getRequireApprovalFlag().equalsIgnoreCase("N")) {
						obj.setReviewCommitteCode("SYSTEM");
					}
				}
			}
			if (obj.getCalcSupLevelType() != null) {
				obj.setCalcSupLevelType(obj.getCalcSupLevelType().toUpperCase());
			}
			if (obj.getOverridedSupLevelType() != null) {
				obj.setOverridedSupLevelType(obj.getOverridedSupLevelType().toUpperCase());
			}
			if (obj.getReviewSupLevelType() != null) {
				obj.setReviewSupLevelType(obj.getReviewSupLevelType().toUpperCase());
			}
			if (obj.getReviewCommitteCode() != null) {
				obj.setReviewCommitteCode(obj.getReviewCommitteCode().toUpperCase());
			}

		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_ASS
	 *
	 */
	@Transactional
	public Integer offAssCommit(final VOffassAssCommitBean CommitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderAssessments> offAss1ExecuteQuery(final OffenderAssessments searchRecord) {
		final List<OffenderAssessments> returnList = oidcapprRepo.offAss1ExecuteQuery(searchRecord);
		Assessments assessments = new Assessments();
		for (final OffenderAssessments obj : returnList) {
			assessments = new Assessments();
			assessments.setAssessmentId(Long.valueOf(obj.getAssessmentTypeId().toString()));
			if (obj.getEvaluationResultCode() != null) {
				final Assessments returnAss = oidcapprRepo.postQueryForOffAss(assessments);
				if (returnAss.getDetermineSupLevelFlag().equalsIgnoreCase("Y")
						&& returnAss.getRequireApprovalFlag().equalsIgnoreCase("N")) {
					if (obj.getEvaluationResultCode().equalsIgnoreCase("APP")) {
						obj.setReviewCommitteCode("SYSTEM");
					}
				}
			}

		}
		String userId = null;
		String userName = null;
		for (OffenderAssessments list : returnList) {
			userId = list.getModifyUserId();
			userName = ocuverifRepo.getUserName(userId);
			list.setModifyUserId(userName);
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_ASS1
	 *
	 */
	@Transactional
	public Integer offAss1Commit(final OffenderAssessmentsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderAssessments offAss : commitBean.getUpdateList()) {
				offAss.setModifyUserId(commitBean.getCreateUserId());

				offenderAssessmentsT1Service.offenderAssessmentsT1Trigger(offAss);
			}
			liReturn = oidcapprRepo.offAss1UpdateOffenderAssessments(commitBean.getUpdateList());
			for (OffenderAssessments offAss : commitBean.getUpdateList()) {
				OffenderAssessment offAssment = new OffenderAssessment();

				offAssment.setAssessmentTypeId(offAss.getAssessmentTypeId());
				offAssment.setReviewSupLevelType(offAss.getReviewSupLevelType());
				offAssment.setAssessStatus(offAss.getAssessStatus());
				offAssment.setEvaluationResultCode(offAss.getEvaluationResultCode());
				offAssment.setOffenderBookId(offAss.getOffenderBookId());
				offAssment.setAssessmentSeq(offAss.getAssessmentSeq());
				offAssment.setCreateDatetime(offAss.getCreateDatetime());
				offAssment.setCreateUserId(offAss.getCreateUserId());

				offenderAssessmentsTwf1Service.OffenderAssessmentsTwf1(offAssment);
			}
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkOffAss1ReviewCommitteRecordGroup(final String caseLoadType) {
		final ReferenceCodes sysValue = new ReferenceCodes();
		sysValue.setCode("SYSTEM");
		sysValue.setDescription("SYSTEM APPROVED");
		sysValue.setSeqValue(0);
		final List<ReferenceCodes> resultList = oidcapprRepo.cgfkOffAss1ReviewCommitteRecordGroup(caseLoadType);
		resultList.add(sysValue);
		resultList.forEach(result -> {
			if (result.getSeqValue() == 0) {
				result.setCanDisplay(false);
			}
		});
		return resultList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<AgencyLocations> cgfkOffAss1ReviewPlaceAgyRecordGroup(final String caseLoadType) {
		return oidcapprRepo.cgfkOffAss1ReviewPlaceAgyRecordGroup(caseLoadType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkOffAss1ReviewSupLevelRecordGroup(final Integer assTypeId) {
		return oidcapprRepo.cgfkOffAss1ReviewSupLevelRecordGroup(assTypeId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkOffAss1EvaluationResulRecordGroup() {
		return oidcapprRepo.cgfkOffAss1EvaluationResulRecordGroup();

	}
	
	@Override
	public List<Assessments> getAssessments(OffenderAssessments paramBean) {
		return oidcapprRepo.getAssessments(paramBean);
	}

	@Override
	public AssessmentsCommitBean getAssessmentsCommit(OffenderAssessmentsCommitBean commitBean) {
		Assessments assessments = new Assessments();
		assessments.setOffenderBookId(commitBean.getUpdateList().get(0).getOffenderBookId());
		assessments.setAssessmentSeq(commitBean.getUpdateList().get(0).getAssessmentSeq());
		assessments.setAssessmentId(commitBean.getUpdateList().get(0).getAssessmentTypeId().longValue());
		AssessmentsCommitBean searchResult = ocunoqueService.assessCommitExecuteQuery(assessments);
		List<Assessments> assessmentList= getAssessments(commitBean.getUpdateList().get(0));
		for(Assessments assessmentModel : assessmentList) {
			for(Assessments answerModel : searchResult.getAssesAnsList()) {
				if(assessmentModel.getAssessmentId().equals(answerModel.getAssessmentId())) {
					for(Assessments questionModel : searchResult.getAssesQuestList()) {
						if(answerModel.getParentAssessmentId().equals(questionModel.getAssessmentId())) {
							for(Assessments assessModel : searchResult.getAssesList()) {
								if(questionModel.getParentAssessmentId().equals(assessModel.getAssessmentId())) {
									assessmentModel.setAnswerCode(answerModel.getAssessmentCode());
									assessmentModel.setQuestionCode(questionModel.getAssessmentCode());
									assessmentModel.setSessionCode(assessModel.getAssessmentCode());
								}
							}
						}
					}
				}
			}
		}
		AssessmentsCommitBean  assessCommitBean = new AssessmentsCommitBean();
		assessCommitBean.setInsertList(assessmentList);
		return assessCommitBean;
	}
}
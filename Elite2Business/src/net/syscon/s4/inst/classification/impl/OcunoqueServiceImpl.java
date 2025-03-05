package net.syscon.s4.inst.classification.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.AssessSectionNotifications;
import net.syscon.s4.common.beans.OffenderAssessment;
import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.casemanagement.OcdiplanRepository;
import net.syscon.s4.inst.casemanagement.OcdiplanService;
import net.syscon.s4.inst.casemanagement.beans.OffApV1;
import net.syscon.s4.inst.casemanagement.beans.OffApV1CommitBean;
import net.syscon.s4.inst.casemanagement.beans.OffenderCriminogenicNeeds;
import net.syscon.s4.inst.classification.OcdnoqueRepository;
import net.syscon.s4.inst.classification.OcunoqueRepository;
import net.syscon.s4.inst.classification.OcunoqueService;
import net.syscon.s4.inst.classification.assessmentmaintenance.OimslevlRepository;
import net.syscon.s4.inst.classification.assessmentmaintenance.impl.OimslevlServiceImpl;
import net.syscon.s4.inst.classification.beans.AssessmentSupervisions;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.AssessmentsCommitBean;
import net.syscon.s4.inst.classification.beans.OffenderAssessmentItems;
import net.syscon.s4.inst.classification.beans.VAssOffNeeds;
import net.syscon.s4.inst.classification.beans.VAssTreatProts;
import net.syscon.s4.inst.classification.beans.VOffassAss;
import net.syscon.s4.triggers.OffenderAssessmentsT1Service;
import net.syscon.s4.triggers.OffenderAssessmentsTwf1Service;

/**
 * Class OcunoqueServiceImpl
 */
@Service
public class OcunoqueServiceImpl extends BaseBusiness implements OcunoqueService {

	@Autowired
	private OcunoqueRepository ocunoqueRepository;

	@Autowired
	private OcdnoqueRepository ocdnoqueRepository;

	@Autowired
	private OffenderAssessmentsT1Service offenderAssessmentsT1Service;

	@Autowired
	private OffenderAssessmentsTwf1Service offenderAssessmentsTwf1Service;

	@Autowired
	private OimslevlServiceImpl oimslevlServiceImpl;

	@Autowired
	private OimslevlRepository oimslevlRepository;
	
	@Autowired
	private OcdiplanService ocdiPlanService;

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcunoqueServiceImpl.class.getName());

	/**
	 * Creates new OcunoqueServiceImpl class Object
	 */
	public OcunoqueServiceImpl() {
	}

	/**
	 * 
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public OffenderAssessments getCommentText(final OffenderAssessments paramBean) {
		OffenderAssessments offenderAssessments = ocunoqueRepository.getCommentText(paramBean);
		if (offenderAssessments != null) {
			BigDecimal Score = ocunoqueRepository.saveAssessmentItemsGetMaxScore(paramBean).getMaxScore();
			BigDecimal maxScore = (Score == null ? BigDecimal.ZERO : Score);
			offenderAssessments.setScore(maxScore);
		}
		return offenderAssessments;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public OffenderAssessmentItems InitiateSaveProcess(final OffenderAssessmentItems paramBean) {
		OffenderAssessmentItems offenderAssessmentItems = new OffenderAssessmentItems();
		return offenderAssessmentItems;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<Assessments> assessExecuteQuery(final Assessments searchRecord) {
		return ocunoqueRepository.assessExecuteQuery(searchRecord);
	}

	public List<Assessments> assessQuestionsExecuteQuery(final Assessments searchRecord) {
		List<Assessments> queList = new ArrayList<>();
		OffenderAssessmentItems offenderAssessmentItems;
		queList = ocunoqueRepository.assessQuestionsExecuteQuery(searchRecord);
		for (final Assessments queBean : queList) {
			if (queBean.getAssessmentType().equals(ApplicationConstants.EXCLUSIVE)) {
				offenderAssessmentItems = new OffenderAssessmentItems();
				offenderAssessmentItems.setOffenderBookId(searchRecord.getOffenderBookId());
				offenderAssessmentItems.setAssessmentSeq(searchRecord.getAssessmentSeq());
				offenderAssessmentItems.setAssessmentId(BigDecimal.valueOf(queBean.getAssessmentId()));
				offenderAssessmentItems = ocunoqueRepository.getAnsIfExistsCur(offenderAssessmentItems);
				queBean.setAnsId((offenderAssessmentItems.getAssessmentId() == null) ? 0
						: Long.valueOf(offenderAssessmentItems.getAssessmentId().toString()));
			}
		}
		return queList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstASSESS
	 *
	 * 
	 */
	@Transactional
	public Integer assessCommit(AssessmentsCommitBean CommitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstASSESS1
	 *
	 * 
	 */
	@Transactional
	public Integer assess1Commit(final AssessmentsCommitBean CommitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<Assessments> answersExecuteQuery(final Assessments searchRecord) {
		List<Assessments> ansList = new ArrayList<>();
		Assessments obj = new Assessments();
		Long answerId = 0L;
		OffenderAssessmentItems offenderAssessmentItems = new OffenderAssessmentItems();
		if (searchRecord.getAssessmentType().equals(ApplicationConstants.EXCLUSIVE)) {
			offenderAssessmentItems.setOffenderBookId(searchRecord.getOffenderBookId());
			offenderAssessmentItems.setAssessmentSeq(searchRecord.getAssessmentSeq());
			offenderAssessmentItems.setAssessmentId(BigDecimal.valueOf(searchRecord.getAssessmentId()));
			offenderAssessmentItems = ocunoqueRepository.getAnsIfExistsCur(offenderAssessmentItems);
			answerId = (offenderAssessmentItems.getAssessmentId() == null) ? 0
					: Long.valueOf(offenderAssessmentItems.getAssessmentId().toString());
		}
		ansList = ocunoqueRepository.answersExecuteQuery(searchRecord);
		for (final Assessments ansBean : ansList) {
			offenderAssessmentItems = new OffenderAssessmentItems();
			offenderAssessmentItems.setOffenderBookId(searchRecord.getOffenderBookId());
			offenderAssessmentItems.setAssessmentSeq(searchRecord.getAssessmentSeq());
			offenderAssessmentItems.setAssessmentId(BigDecimal.valueOf(ansBean.getAssessmentId()));
			offenderAssessmentItems.setParentAssessmentId(BigDecimal.valueOf(ansBean.getParentAssessmentId()));
			offenderAssessmentItems = ocunoqueRepository.checkForUserAnswergetanscur(offenderAssessmentItems);
			ansBean.setAnswers(offenderAssessmentItems.getAnsweredFlag());
			ansBean.setAssessComment(offenderAssessmentItems.getCommentText());
			ansBean.setItemSeq(offenderAssessmentItems.getItemSeq());
			ansBean.setRank(offenderAssessmentItems.getRank());
			ansBean.setRequiredFlag(offenderAssessmentItems.getRequiredFlag());
			ansBean.setAnsId(answerId);
			if (ApplicationConstants.BAR.equals(ansBean.getBookmarkCondition())) {
				obj.setAnsBookMark(ansBean.getAnsBookMark());
				obj.setQuery(ocunoqueRepository.getQuery(obj));
				obj.setOffenderBookId(searchRecord.getOffenderBookId());
				ansBean.setAge(ocunoqueRepository.getOffenderAge(obj));
			} else if (ApplicationConstants.EAW.equals(ansBean.getBookmarkCondition())) {
				obj.setAnsBookMark(ansBean.getAnsBookMark());
				obj.setQuery(ocunoqueRepository.getQuery(obj));
				obj.setOffenderBookId(searchRecord.getOffenderBookId());
				ansBean.setAnswerValue(ocunoqueRepository.getBookMarkAnswers(obj));
			}
		}
		return ansList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstANSWERS
	 *
	 * 
	 */
	@Transactional
	public Integer answersCommit(final AssessmentsCommitBean commitBean) {
		Integer liReturn = null;
		for (Assessments bo : commitBean.getAssesAnsList()) {
			bo.setModifyUserId(commitBean.getCreateUserId());
			if ("false".equalsIgnoreCase(bo.getAnswers()) || ApplicationConstants.NO.equalsIgnoreCase(bo.getAnswers())) {
				liReturn = ocunoqueRepository.removeExistingAssItems(bo);
			}
		}
		List<Assessments> ansList =  commitBean.getAssesAnsList().stream().collect(Collectors.toMap(Assessments::getAssessmentId, e -> e, (e1, e2) -> e1)).values().stream()
                .collect(Collectors.toList());
		commitBean.setAssesAnsList(ansList);
		Long nextItemSeq = null;
		Integer lvRecCnt = null;
		Long totalScore = null;
		Long orgTotalScore = null;
		BigDecimal Score = null;
		String superLevel = null;
		String userName = commitBean.getCreateUserId();
		Integer count = ocunoqueRepository.existsOffenderAssessments(commitBean.getOffAssesModel());
		if (count == 0) {
			commitBean.getOffAssesModel().setAssessmentSeq(
					ocunoqueRepository.getAssesmentSeq(commitBean.getOffAssesModel().getOffenderBookId()));
		}
		List<OffenderAssessments> InsertUpdateList = new ArrayList<>();
		OffenderAssessments InsertUpdateBean = new OffenderAssessments();
		InsertUpdateBean.setCreateUserId(commitBean.getCreateUserId());
		List<Assessments> insertList = new ArrayList<>();
		Score = ocunoqueRepository.saveAssessmentItemsGetCurrentTotalScore(commitBean.getOffAssesModel()).getScore();
		totalScore = (Score == null) ? 0 : Score.longValue();

		Score = ocunoqueRepository.saveAssessmentItemsGetMaxScore(commitBean.getOffAssesModel()).getMaxScore();
		Long maxScore = (Score == null) ? 0 : Score.longValue();
		Score = ocunoqueRepository.saveAssessmentItemsGetMinScore(commitBean.getOffAssesModel()).getMiniScore();
		Long minScore = (Score == null) ? 0 : Score.longValue();
		//Section Config wise calc marks.
		Assessments bean = new Assessments();
		bean.setAssessmentId(commitBean.getOffAssesModel().getAssessmentTypeId().longValue());
		List<Assessments> secConfigList = oimslevlRepository.assSectExecuteQuery(bean);
		totalScore = 0L;
		
		//For Include In Total Flag
		//Section loop
		for (Assessments beanOne : secConfigList) {
			//Quation loop
			for (Assessments beanTwo : commitBean.getAssesQuestList()) {
				//Answer loop
				for (final Assessments beanThree : commitBean.getAssesAnsList()) {
					if(beanOne.getAssessmentId().equals(beanTwo.getParentAssessmentId())  && beanTwo.getAssessmentId().equals(beanThree.getParentAssessmentId())  && ApplicationConstants.YFLAG.equalsIgnoreCase(beanOne.getSectScoreIncludeFlag()) && ApplicationConstants.YFLAG.equalsIgnoreCase(beanOne.getActiveFlag())) {
						if (beanThree.getAnswers() != null && beanThree.getScore() != null && (beanThree.getAnswers().equals("true") || beanThree.getAnswers().equals(ApplicationConstants.YFLAG))) {
							totalScore = totalScore + Long.valueOf(beanThree.getScore().toString());
						}
						if (beanThree.getScore() == null) {
							beanThree.setScore(new BigDecimal(0));
						}
					}
				}
			}
		}
		
		//For Override Total Flag
		//Section loop
		Long overrTotalScore = null;
		for (Assessments beanOne : secConfigList) {
			//Quation loop
			for (Assessments beanTwo : commitBean.getAssesQuestList()) {
				//Answer loop
				for (final Assessments beanThree : commitBean.getAssesAnsList()) {
					if(beanOne.getAssessmentId().equals(beanTwo.getParentAssessmentId())  && beanTwo.getAssessmentId().equals(beanThree.getParentAssessmentId())  && ApplicationConstants.YFLAG.equalsIgnoreCase(beanOne.getSectScoreOverrideFlag()) && ApplicationConstants.YFLAG.equalsIgnoreCase(beanOne.getActiveFlag())) {
						if (beanThree.getAnswers() != null && beanThree.getScore() != null && (beanThree.getAnswers().equals("true") || beanThree.getAnswers().equals(ApplicationConstants.YFLAG))) {
							overrTotalScore = (overrTotalScore!= null ? overrTotalScore : 0l) + (beanThree.getScore()!= null ? Long.valueOf(beanThree.getScore().toString()):0l );
						}
						if (beanThree.getScore() == null) {
							beanThree.setScore(new BigDecimal(0));
						}
					}
				}
			}
		}
		if (overrTotalScore !=null) {
			totalScore = 0l;
			totalScore = overrTotalScore;
		}
		orgTotalScore = totalScore;
		InsertUpdateBean = commitBean.getOffAssesModel();
		/* InsertUpdateBean.setScore(BigDecimal.valueOf(totalScore)); */
		final AssessmentSupervisions AssessmentSupervisionsBean = ocunoqueRepository
				.saveAssessmentItemsGetSuperLevelType(InsertUpdateBean);
		
		if (AssessmentSupervisionsBean.getSupervisionLevelType() == null) {
			if (ApplicationConstants.YFLAG.equalsIgnoreCase(oimslevlServiceImpl
					.getEnforcementFlag(commitBean.getOffAssesModel().getAssessmentTypeId().longValue()))) {
				/* totalScore = 0L; */
//				if (maxScore == 0 || minScore == 0) {
//					return 289;
//				} else
				if (totalScore >= maxScore) {
					totalScore = maxScore;
					superLevel = ocunoqueRepository.saveAssessmentItemsGetMaxScore(commitBean.getOffAssesModel())
							.getSupervisionLevelType();
				} else if (totalScore <= minScore) {
					totalScore = minScore;
					superLevel = ocunoqueRepository.saveAssessmentItemsGetMinScore(commitBean.getOffAssesModel())
							.getSupervisionLevelType();
				} else {
					superLevel = ocunoqueRepository.getSupervisionLevelTypeForScore(
							commitBean.getOffAssesModel().getAssessmentTypeId().longValue(), totalScore);
				}
			} else {
				superLevel = ocunoqueRepository.getSupervisionLevelTypeForScore(
						commitBean.getOffAssesModel().getAssessmentTypeId().longValue(), totalScore);
			}
		}
		InsertUpdateBean.setScore(BigDecimal.valueOf(totalScore));

		if (AssessmentSupervisionsBean.getSupervisionLevelType() != null) {
			superLevel = AssessmentSupervisionsBean.getSupervisionLevelType();
		}

		/*
		 * This procedure would be called to save assessment record.
		 */
		lvRecCnt = ocunoqueRepository.initiateSaveProcessgetanscountcur(commitBean.getOffAssesModel());
		nextItemSeq = ocunoqueRepository
				.addRemoveAnswerGetItemSeqCur(commitBean.getOffAssesModel());
		
		if (commitBean.getAssesAnsList() != null && commitBean.getAssesAnsList().size() > 0) {
			
			for (final Assessments answersBean : commitBean.getAssesAnsList()) {
				answersBean.setModifyUserId(commitBean.getCreateUserId());
					if (answersBean.getAnswers() != null && "false".equals(answersBean.getAnswers()) || ApplicationConstants.NFLAG.equals(answersBean.getAnswers())) {
						answersBean.setOffenderBookId(commitBean.getOffAssesModel().getOffenderBookId());
						answersBean.setAssessmentSeq(commitBean.getOffAssesModel().getAssessmentSeq());
						liReturn = ocunoqueRepository.deleteOffenderAssessmentItems(answersBean);
					}
					if (answersBean.getAnswers() != null && "true".equals(answersBean.getAnswers()) || ApplicationConstants.YFLAG.equals(answersBean.getAnswers())) {
						answersBean.setOffenderBookId(commitBean.getOffAssesModel().getOffenderBookId());
						answersBean.setAssessmentSeq(commitBean.getOffAssesModel().getAssessmentSeq());
						if (count != 0) {
							Integer assCount = ocunoqueRepository.checkAssExist(answersBean);
							if (assCount > 0) {
								liReturn = ocunoqueRepository.removeExistingAssItems(answersBean);
							}
						}
						answersBean.setItemSeq(nextItemSeq);
						
						insertList.add(answersBean);
						nextItemSeq=nextItemSeq+1;
					}
				
			}
			
			
			
		}

		commitBean.setInsertList(insertList);
		if (commitBean.getInsertList().size() > 0) {
			for (Assessments assessments : commitBean.getInsertList()) {
				liReturn = ocunoqueRepository.updateOffenderAssessmentItems(assessments);
				if (liReturn == 0) {
					if (assessments.getScore() == null) {
						assessments.setScore(new BigDecimal(0));
					}
					liReturn = ocunoqueRepository.insertOffenderAssessmentItems(assessments);
				}
			}
		}

		if (count != null && count == 1 && liReturn != null && liReturn == 1) {
			InsertUpdateBean = commitBean.getOffAssesModel();
			InsertUpdateBean.setScore(BigDecimal.valueOf(totalScore));
			final Assessments assReturnObj = ocdnoqueRepository.offAss1PostQuery(commitBean.getOffAssesModel());
			InsertUpdateBean.setCalcSupLevelType(superLevel);
			if (ApplicationConstants.YFLAG.equals(assReturnObj.getDetermineSupLevelFlag())
					&& ApplicationConstants.YFLAG.equals(assReturnObj.getRequireApprovalFlag())) {
				InsertUpdateBean.setAssessStatus("P");
				// InsertUpdateBean.setEvaluationResultCode("APP");
				InsertUpdateBean.setEvaluationDate(InsertUpdateBean.getAssessmentDate());
				//InsertUpdateBean.setReviewSupLevelType(InsertUpdateBean.getCalcSupLevelType());
			} else {
				InsertUpdateBean.setReviewSupLevelType(null);
				InsertUpdateBean.setAssessStatus("P");
			}
			if (InsertUpdateBean.getAssessmentStatus().equals("Submitted")) {
				if (ApplicationConstants.NFLAG.equals(assReturnObj.getRequireApprovalFlag())) {
					InsertUpdateBean.setEvaluationResultCode("APP");
					InsertUpdateBean.setAssessStatus("A");
					InsertUpdateBean.setReviewSupLevelType(InsertUpdateBean.getCalcSupLevelType());
				} else {
					InsertUpdateBean.setEvaluationResultCode("PEN");
					InsertUpdateBean.setAssessStatus("P");
				}
			}
			if (InsertUpdateBean.getAssessmentStatus().equals(ApplicationConstants.DRAFT)) {
				InsertUpdateBean.setAssessStatus("D");
				InsertUpdateBean.setScore(null);
				InsertUpdateBean.setCalcSupLevelType(null);
				InsertUpdateBean.setReviewSupLevelType(null);
				InsertUpdateBean.setEvaluationResultCode(null);
				InsertUpdateBean.setEvaluationDate(null);
			} else {
				InsertUpdateBean.setAssessmentDate(new Date());
				if(commitBean.getOffAssesModel().getNextReviewDate()!=null) {				
					InsertUpdateBean.setNextReviewDate(commitBean.getOffAssesModel().getNextReviewDate());  
				}
			}
			InsertUpdateList.add(InsertUpdateBean);
			offenderAssessmentsT1Service.offenderAssessmentsT1Trigger(InsertUpdateBean);
			liReturn = ocunoqueRepository.updateOffenderAssessments(InsertUpdateList);
			for (OffenderAssessments Offass : InsertUpdateList) {
				OffenderAssessment newRef = new OffenderAssessment();
				try {
					BeanUtils.copyProperties(newRef, Offass);
				} catch (Exception e) {
					e.printStackTrace();
				}
				offenderAssessmentsTwf1Service.OffenderAssessmentsTwf1(newRef);
			}

		}
		if (count != null && count == 0 && liReturn == 1) {
			InsertUpdateBean = commitBean.getOffAssesModel();
			final StaffMembers staffMemObj = ocdnoqueRepository
					.offAss1PreInsertGetStaffId(commitBean.getCreateUserId());
			InsertUpdateBean.setAssessStaffId(BigDecimal.valueOf(staffMemObj.getStaffId()));
			final Assessments assReturnObj = ocdnoqueRepository.offAss1PostQuery(commitBean.getOffAssesModel());
			InsertUpdateBean.setCalcSupLevelType(superLevel);
			if ((ApplicationConstants.YFLAG.equals(assReturnObj.getDetermineSupLevelFlag())
					&& ApplicationConstants.NO.equals(assReturnObj.getRequireApprovalFlag()))
					|| (ApplicationConstants.NO.equals(assReturnObj.getDetermineSupLevelFlag())
							&& ApplicationConstants.NO.equals(assReturnObj.getRequireApprovalFlag()))
					|| (ApplicationConstants.NO.equals(assReturnObj.getDetermineSupLevelFlag())
							&& ApplicationConstants.YFLAG.equals(assReturnObj.getRequireApprovalFlag()))
					|| (ApplicationConstants.YFLAG.equals(assReturnObj.getDetermineSupLevelFlag())
							&& ApplicationConstants.YFLAG.equals(assReturnObj.getRequireApprovalFlag()))) {
				InsertUpdateBean.setAssessStatus("A");
				if (InsertUpdateBean.getAssessmentStatus().equals(ApplicationConstants.SUBMITTED)) {
					if (ApplicationConstants.NO.equals(assReturnObj.getRequireApprovalFlag())) {
						InsertUpdateBean.setEvaluationResultCode("APP");
						InsertUpdateBean.setAssessStatus("A");
					} else {
						InsertUpdateBean.setEvaluationResultCode("PEN");
						InsertUpdateBean.setAssessStatus("P");
					}
				}
				InsertUpdateBean.setEvaluationDate(InsertUpdateBean.getAssessmentDate());
				if (ApplicationConstants.NO.equals(assReturnObj.getRequireApprovalFlag())) {
					InsertUpdateBean.setReviewSupLevelType(InsertUpdateBean.getCalcSupLevelType());
				} else {
					InsertUpdateBean.setReviewSupLevelType(null);
					InsertUpdateBean.setAssessStatus("P");
				}

				if (ApplicationConstants.NFLAG.equals(assReturnObj.getCalculateTotalFlag())) {
					InsertUpdateBean.setScore(BigDecimal.ZERO);
				} else {
					InsertUpdateBean.setScore(BigDecimal.valueOf(totalScore));
				}

			}
			// InsertUpdateBean.setScore(BigDecimal.valueOf(totalScore));
			InsertUpdateBean.setCreateUserId(userName);
			InsertUpdateBean.setCreationUser(userName);
			if (InsertUpdateBean.getAssessmentStatus().equals(ApplicationConstants.DRAFT)) {
				InsertUpdateBean.setAssessStatus("D");
				InsertUpdateBean.setScore(null);
				InsertUpdateBean.setCalcSupLevelType(null);
				InsertUpdateBean.setReviewSupLevelType(null);
				InsertUpdateBean.setEvaluationResultCode(null);
				InsertUpdateBean.setEvaluationDate(null);
			} else {
				InsertUpdateBean.setAssessmentDate(new Date());
				if(commitBean.getOffAssesModel().getNextReviewDate()!=null) {				
					InsertUpdateBean.setNextReviewDate(commitBean.getOffAssesModel().getNextReviewDate());  
				}
			}
			/*if(ApplicationConstants.NFLAG.equalsIgnoreCase(assReturnObj.getDetermineSupLevelFlag()) ||
					(ApplicationConstants.NFLAG.equals(assReturnObj.getCalculateTotalFlag()) && ApplicationConstants.NFLAG.equalsIgnoreCase(assReturnObj.getDetermineSupLevelFlag()) 
							&& ApplicationConstants.NFLAG.equalsIgnoreCase(assReturnObj.getCalculateTotalFlag()))) {
				InsertUpdateBean.setReviewSupLevelType(null);
				InsertUpdateBean.setEvaluationResultCode(null);
				InsertUpdateBean.setEvaluationDate(null);
			}*/
			InsertUpdateList.add(InsertUpdateBean);
			liReturn = ocdnoqueRepository.offAss1InsertOffenderAssessments(InsertUpdateList);
			final VOffassAss vOffassAssObj = ocunoqueRepository
					.inactivatePrviousAssessment(commitBean.getOffAssesModel(), commitBean.getCreateUserId());
			if (vOffassAssObj.getRequireApprovalFlag() != null && !(vOffassAssObj.getRequireApprovalFlag().equals(ApplicationConstants.YFLAG))
					&& vOffassAssObj.getReviewSupLevelType() != null) {
				offenderAssessmentsT1Service.offenderAssessmentsT1Trigger(InsertUpdateBean);
				// liReturn =
				// ocunoqueRepository.inActivateOffenderAssessments(InsertUpdateList);
				for (OffenderAssessments Offass : InsertUpdateList) {
					OffenderAssessment newRef = new OffenderAssessment();
					try {
						BeanUtils.copyProperties(newRef, Offass);
					} catch (Exception e) {
						e.printStackTrace();
					}
					offenderAssessmentsTwf1Service.OffenderAssessmentsTwf1(newRef);
				}
			}
		}
		
		Long casePlanCount = ocunoqueRepository.casePlanCount(commitBean.getOffAssesModel().getOffenderBookId());
		Long preSectionScore = 0l;
		if (casePlanCount != null && ApplicationConstants.SUBMITTED.equals(InsertUpdateBean.getAssessmentStatus())) {
			// Section loop
			for (Assessments beanOne : secConfigList) {
				// Quation loop
				for (Assessments beanTwo : commitBean.getAssesQuestList()) {
					// Answer loop
					for (final Assessments beanThree : commitBean.getAssesAnsList()) {
						if (beanOne.getAssessmentId().equals(beanTwo.getParentAssessmentId())
								&& beanTwo.getAssessmentId().equals(beanThree.getParentAssessmentId())
								&& ApplicationConstants.YFLAG.equalsIgnoreCase(beanOne.getActiveFlag())) {
							if (beanThree.getAnswers() != null && beanThree.getScore() != null
									&& (beanThree.getAnswers().equals("true")
											|| beanThree.getAnswers().equals(ApplicationConstants.YFLAG))) {
								preSectionScore = preSectionScore + Long.valueOf(beanThree.getScore().toString());

							}
							if (beanThree.getScore() == null) {
								beanThree.setScore(new BigDecimal(0));
							}
						}
					}
				}

				List<VAssOffNeeds> criminogenicNeedsList = ocunoqueRepository
						.getCriminogenicNeeds(beanOne.getAssessmentId());
				// to get criminogenicNeeds data
				if (criminogenicNeedsList != null && criminogenicNeedsList.size() > 0
						&& !criminogenicNeedsList.isEmpty()) {
					for (VAssOffNeeds criminogenicBean : criminogenicNeedsList) {

						if (criminogenicBean != null
								&& ApplicationConstants.YES.equals(criminogenicBean.getActiveFlag())) {
							// to get treatmentProtocol data
							List<VAssTreatProts> treatmentProtocolsList = ocunoqueRepository.getTreatmentProtocols(
									commitBean.getOffAssesModel().getOffenderBookId(), preSectionScore,
									criminogenicBean.getOffAssNeedId());
							if (treatmentProtocolsList != null && treatmentProtocolsList.size() > 0
									&& !treatmentProtocolsList.isEmpty()) {

								List<VAssTreatProts> treatmentProtocolsListFinalList = new ArrayList<VAssTreatProts>();
								for (VAssTreatProts treatmentProto : treatmentProtocolsList) {
									if ( ApplicationConstants.NO.equals(treatmentProto.getReferralFlag())
											|| (ApplicationConstants.YES.equals(treatmentProto.getReferralFlag())
													&& treatmentProto.getWaitListCount() > 0)) {
										treatmentProtocolsListFinalList.add(treatmentProto);
									}

								}
								if (treatmentProtocolsListFinalList != null
										&& treatmentProtocolsListFinalList.size() > 0) {
									OffenderCriminogenicNeeds offenderCriminogenicNeeds = new OffenderCriminogenicNeeds();
									Long crimNeedId = ocunoqueRepository.getoffCrimNeedIdSeq();
									offenderCriminogenicNeeds.setOffCrimNeedId(crimNeedId);
									offenderCriminogenicNeeds.setCasePlanId(BigDecimal.valueOf(casePlanCount));
									offenderCriminogenicNeeds.setOffenderBookId(
											BigDecimal.valueOf(commitBean.getOffAssesModel().getOffenderBookId()));
									offenderCriminogenicNeeds
											.setAssessedNeedCode(criminogenicBean.getAssessedNeedCode());
									offenderCriminogenicNeeds.setObjective(criminogenicBean.getObjective());
									offenderCriminogenicNeeds.setStatusCode("ACTIVE");
									offenderCriminogenicNeeds.setCreateUserId(commitBean.getCreateUserId());
									List<OffenderCriminogenicNeeds> returnObj = new ArrayList<OffenderCriminogenicNeeds>();
									returnObj.add(offenderCriminogenicNeeds);
									//Criminogenic Needs insert into caseplan
									ocunoqueRepository.offCriNeedsInsertOffenderCriminogenicNeeds(returnObj);

									List<OffApV1> list = new ArrayList<OffApV1>();
									for (VAssTreatProts treatmentProtocoltbean : treatmentProtocolsListFinalList) {
										OffApV1 offApV1 = new OffApV1();
										offApV1.setOffCrimNeedId(BigDecimal.valueOf(crimNeedId));
										offApV1.setCaseworkType(treatmentProtocoltbean.getCaseworkType());
										offApV1.setProgramId(treatmentProtocoltbean.getProgramId());
										list.add(offApV1);
									}
									OffApV1CommitBean offApV1CommitBean = new OffApV1CommitBean();
									offApV1CommitBean.setInsertList(list);
									offApV1CommitBean.setCreateUserId(commitBean.getCreateUserId());
									// Treatment Protocols data insert into case Plan
									ocdiPlanService.offActionPlansV1Commit(offApV1CommitBean);
								}

							}

						}
					}
				}
			}
		}
			
			if (liReturn == null) {
				return 3;
			} else {
				return liReturn;
			}
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgRankRecordGroup() {
		return ocunoqueRepository.rgRankRecordGroup();
	}

	public AssessmentsCommitBean assessCommitExecuteQuery(final Assessments searchRecord) {
		final AssessmentsCommitBean assessHeavyBean = new AssessmentsCommitBean();
		final List<Assessments> assessQueAnsList = new ArrayList<>();
		final List<Assessments> assessAnsList = new ArrayList<>();
		List<Assessments> assessList = new ArrayList<>();
		List<Assessments> asessQAList = new ArrayList<>();
		try {
			assessList = ocunoqueRepository.assessExecuteQuery(searchRecord);
			assessHeavyBean.setAssesList(assessList);
			for (final Assessments assessBean : assessList) {
				assessBean.setOffenderBookId(searchRecord.getOffenderBookId());
				assessBean.setAssessmentSeq(searchRecord.getAssessmentSeq());
				asessQAList = assessQuestionsExecuteQuery(assessBean);
				asessQAList.forEach(bo -> {
					bo.setAssCodeTemp(assessBean.getAssessmentCode());
				});
				assessQueAnsList.addAll(asessQAList);
				if (assessQueAnsList != null && assessQueAnsList.size() > 0) {
					assessQueAnsList.forEach(bo -> {
						AssessSectionNotifications bean = new AssessSectionNotifications();
						bean.setAssessmentId(bo.getParentAssessmentId());
						List<AssessSectionNotifications> list = ocunoqueRepository
								.assessSectionNotificationsExecuteQuery(bean);
						bo.setAssSecNoti(list);
					});
				}
			}
			assessHeavyBean.setAssesQuestList(assessQueAnsList);
			asessQAList = new ArrayList<>();
			for (final Assessments assessQuesBean : assessQueAnsList) {
				assessQuesBean.setOffenderBookId(searchRecord.getOffenderBookId());
				assessQuesBean.setAssessmentSeq(searchRecord.getAssessmentSeq());
				asessQAList = answersExecuteQuery(assessQuesBean);
				if (asessQAList != null && asessQAList.size() > 0) {
					asessQAList.forEach(bo -> {
						
						bo.setAssCodeTemp(assessQuesBean.getAssCodeTemp());
					});
				}
				assessAnsList.addAll(asessQAList);
			}
			assessHeavyBean.setAssesAnsList(assessAnsList);
		} catch (Exception e) {
			logger.error("assessCommitExecuteQuery", e);
		}
		String enForcevalue=ocunoqueRepository.getAssmentEnforceFlag(searchRecord.getAssessmentId());
		assessHeavyBean.setEnforceFlag(enForcevalue!=null && enForcevalue.equals("Y")?true:false);
		return assessHeavyBean;

	}

	public AssessmentSupervisions getAssessmentScore(final OffenderAssessments paramBean) {
		AssessmentSupervisions assessScore = new AssessmentSupervisions();
		BigDecimal score = null;
		assessScore = ocunoqueRepository.getAssessmentScore(paramBean);
		score = assessScore.getScore();
		assessScore.setAssessmentId(Long.valueOf(paramBean.getAssessmentTypeId().toString()));
		assessScore.setScore(score);
		assessScore = ocunoqueRepository.getAssessmentSupervisionLevel(assessScore);
		assessScore.setScore(score);
		return assessScore;
	}

}
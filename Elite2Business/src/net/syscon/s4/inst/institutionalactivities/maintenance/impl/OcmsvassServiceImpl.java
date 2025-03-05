package net.syscon.s4.inst.institutionalactivities.maintenance.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.casemanagement.beans.ProgramAssessments;
import net.syscon.s4.inst.casemanagement.beans.ProgramAssessmentsCommitBean;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmsvassRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmsvassService;

/**
 * Creates OcmsvassServiceImpl class Object
 */
@Service
public class OcmsvassServiceImpl extends BaseBusiness implements OcmsvassService {

	@Autowired
	private OcmsvassRepository ocmsvassRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<ProgramAssessments> prgQstExecuteQuery(final ProgramAssessments obj) {
		List<ProgramAssessments> returnList = ocmsvassRepository.prgQstExecuteQuery(obj);
		returnList.forEach(ele -> {
			if (ele.getAssessmentId() != null) {
				String assessmentCode = ocmsvassRepository.getAssessmentCode(ele.getAssessmentId());
				if (assessmentCode != null) {
					ele.setAssessmentCode(assessmentCode);
				}
			}
		});
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPRG_QST
	 *
	 * 
	 */
	@Transactional
	public Integer prgQstCommit(final ProgramAssessmentsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final ProgramAssessments obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				if (obj.getAssessmentCode() != null) {
					BigDecimal assessmentId = ocmsvassRepository.getAssessmentId(obj.getAssessmentCode());
					if (assessmentId != null) {
						obj.setAssessmentId(assessmentId);
					}
				}
			}
			liReturn = ocmsvassRepository.prgQstInsertProgramAssessments(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final ProgramAssessments obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				if (obj.getAssessmentCode() != null) {
					BigDecimal assessmentId = ocmsvassRepository.getAssessmentId(obj.getAssessmentCode());
					if (assessmentId != null) {
						obj.setAssessmentId(assessmentId);
					}
				}
			}
			liReturn = ocmsvassRepository.prgQstUpdateProgramAssessments(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (final ProgramAssessments obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				if (obj.getAssessmentCode() != null) {
					BigDecimal assessmentId = ocmsvassRepository.getAssessmentId(obj.getAssessmentCode());
					if (assessmentId != null) {
						obj.setAssessmentId(assessmentId);
					}
				}
			}
			liReturn = ocmsvassRepository.prgQstDeleteProgramAssessments(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<Assessments> rgAssessmentsRecordGroup() {
		return ocmsvassRepository.rgAssessmentsRecordGroup();

	}

}
package net.syscon.s4.cm.assesments.maintenance.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.assesments.beans.AssessmentResultsCommitBean;
import net.syscon.s4.cm.assesments.maintenance.OcmnoqueService;
import net.syscon.s4.cm.assessments.maintenance.OcmnoqueRepository;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.AssessmentsCommitBean;
import net.syscon.s4.inst.legalscreens.maintenance.bean.TableColumnNameBean;
import net.syscon.s4.pkgs.tag_assessment.TagAssessmentService;

/**
 * Class OcmnoqueServiceImpl
 */
@Service
public class OcmnoqueServiceImpl extends BaseBusiness implements OcmnoqueService {

	@Autowired
	private OcmnoqueRepository ocmnoqueRepository;
	@Autowired
	private TagAssessmentService tagAssessmentService;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<Assessments> assessExecuteQuery(final Assessments searchRecord) {
		return ocmnoqueRepository.assessExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstASSESS
	 *
	 */
	@Transactional
	public Integer assessCommit(final AssessmentsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (Assessments obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			List<Assessments> recordSavingObject;
			for (int i = 0; i < commitBean.getInsertList().size(); i++) {
				recordSavingObject = new ArrayList<>();
				final Assessments offenderPropertyItemObj = commitBean.getInsertList().get(i);
				final Long assId = tagAssessmentService.getAssessmentId();
				offenderPropertyItemObj.setAssessmentId(assId);
				recordSavingObject.add(offenderPropertyItemObj);
				liReturn = ocmnoqueRepository.assessInsertAssessments(recordSavingObject);
			}
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (Assessments obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmnoqueRepository.assessUpdateAssessments(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (Assessments obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmnoqueRepository.assessDeleteAssessments(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<Assessments> assSectExecuteQuery(final Assessments searchRecord) {
		return ocmnoqueRepository.assSectExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<Assessments> assQueExecuteQuery(final Assessments searchRecord) {
		return ocmnoqueRepository.assQueExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<Assessments> assAnsExecuteQuery(final Assessments searchRecord) {
		return ocmnoqueRepository.assAnsExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<AssessmentResults> assResExecuteQuery(final AssessmentResults searchRecord) {
		return ocmnoqueRepository.assResExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstASS_RES
	 *
	 */
	@Transactional
	public Integer assResCommit(final AssessmentResultsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (AssessmentResults obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmnoqueRepository.assResInsertAssessmentResults(commitBean.getInsertList());
			return liReturn;
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (AssessmentResults obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmnoqueRepository.assResUpdateAssessmentResults(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (AssessmentResults obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmnoqueRepository.assResDeleteAssessmentResults(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgCaseloadTypeRecordGroup() {
		return ocmnoqueRepository.rgCaseloadTypeRecordGroup();

	}

	public List<Assessments> assSectLov(final Long assessmentId) {
		return ocmnoqueRepository.assSectLov(assessmentId);
	}

	public List<Assessments> assQueLov(final Long assessmentId) {
		return ocmnoqueRepository.assQueLov(assessmentId);
	}

	@Override
	public Integer validateCaseLoad(final Long assessmentId) {
		return ocmnoqueRepository.validateCaseLoad(assessmentId);
	}

	@Override
	public Boolean checkAssesRelations(final Long assessmentId) {
		Boolean checkAssesRelations = false;
		final Integer assRelCount = ocmnoqueRepository.getAssRelCount(assessmentId);
		final Integer assResRelCount = ocmnoqueRepository.getAssResRelCount(assessmentId);
		if (assRelCount > 0 || assResRelCount > 0) {
			checkAssesRelations = true;
		}
		return checkAssesRelations;
	}

	@Override
	public boolean assessKeyDeleteRec(final Long assessmentId) {
		final List<TableColumnNameBean> assessKeyDelList = ocmnoqueRepository.assessKeyDelList();
		for (TableColumnNameBean bean : assessKeyDelList)
			if (bean.getTableName() != null && bean.getColumnName() != null) {
				final Integer count = ocmnoqueRepository.getAssessKeyDeleteRecRowCount(bean.getTableName(), bean.getColumnName(),assessmentId);
				if (count > 0) {
					return false;
				}
			}
		return true;
	}

	@Override
	public Boolean assessResKeyDeleteRec(final AssessmentResults searchBean) {
		final Integer assessmentSupCount = ocmnoqueRepository.getAssessmentSupCount(searchBean);
		final Integer offenderAssCount = ocmnoqueRepository.getOffenderAssCount(searchBean);
		if (assessmentSupCount > 0 || offenderAssCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getDefaultAssessmentType() {
		return ocmnoqueRepository.getDefaultAssessmentType();
	}
	
	@Override
	public List<ReferenceCodes> rgBookMarkRecordGroup() {
		return ocmnoqueRepository.rgBookMarkRecordGroup();
	}
}
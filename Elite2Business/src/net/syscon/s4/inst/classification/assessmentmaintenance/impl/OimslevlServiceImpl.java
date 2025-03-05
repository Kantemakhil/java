package net.syscon.s4.inst.classification.assessmentmaintenance.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.AssessmentSupervisionsCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.classification.assessmentmaintenance.OimslevlRepository;
import net.syscon.s4.inst.classification.assessmentmaintenance.OimslevlService;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.classification.beans.AssessmentSupervisions;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.AssessmentsCommitBean;

/**
 * Class OimslevlServiceImpl
 */
@Service
public class OimslevlServiceImpl extends BaseBusiness implements OimslevlService {

	@Autowired
	private OimslevlRepository oimslevlRepository;



	public List<Assessments> assTypeExecuteQuery(final Assessments searchRecord) {
		return oimslevlRepository.assTypeExecuteQuery(searchRecord);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<AssessmentSupervisions> typeAssSupExecuteQuery(final AssessmentSupervisions searchRecord) {

		final List<AssessmentSupervisions> list = oimslevlRepository.typeAssSupExecuteQuery(searchRecord);

		if (!list.isEmpty()) {
			for (final AssessmentSupervisions assemt : list) {
				assemt.setSuperVsnCode(assemt.getSupervisionLevelType());
			}
		}

		return list;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstTYPE_ASS_SUP
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<AssessmentSupervisions> typeAssSupCommit(final AssessmentSupervisionsCommitBean commitBean) {
		final List<AssessmentSupervisions> liReturnData = new ArrayList<>();
		final AssessmentSupervisions asssup = new AssessmentSupervisions();
		int liReturn = 0;
		if (!commitBean.getInsertList().isEmpty()) {
			for(AssessmentSupervisions data:commitBean.getInsertList()) {
				data.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oimslevlRepository.typeAssSupInsertAssessmentSupervisions(commitBean.getInsertList());
		}
		if (!commitBean.getUpdateList().isEmpty()) {
			for(AssessmentSupervisions data:commitBean.getUpdateList()) {
				data.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimslevlRepository.typeAssSupUpdateAssessmentSupervisions(commitBean.getUpdateList());
		}
		if (!commitBean.getDeleteList().isEmpty()) {
			for(AssessmentSupervisions data:commitBean.getDeleteList()) {
				data.setModifyUserId(commitBean.getCreateUserId());
				List<AssessmentSupervisions> list = new ArrayList<>();
				list.add(data);
				final Integer returnCount = validateDelRow(list);
				if (returnCount > 0) {
					asssup.setListSeq(BigDecimal.valueOf(5));
					liReturnData.add(asssup);
					return liReturnData;
				}
				liReturn = oimslevlRepository.typeAssSupDeleteAssessmentSupervisions(list);
			}
		}
		asssup.setListSeq(BigDecimal.valueOf(liReturn));
		liReturnData.add(asssup);
		return liReturnData;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<Assessments> assSectExecuteQuery(final Assessments searchRecord) {
		return oimslevlRepository.assSectExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<AssessmentSupervisions> secAssSupExecuteQuery(final AssessmentSupervisions searchRecord) {

		final List<AssessmentSupervisions> list = oimslevlRepository.secAssSupExecuteQuery(searchRecord);
		if (!list.isEmpty()) {
			for (final AssessmentSupervisions assemt : list) {
				assemt.setSuperVsnCode(assemt.getSupervisionLevelType());
			}
		}
		return list;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSEC_ASS_SUP
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<AssessmentSupervisions> secAssSupCommit(final AssessmentSupervisionsCommitBean commitBean) {
		final List<AssessmentSupervisions> liReturnData = new ArrayList<>();
		try {
			final AssessmentSupervisions assSupervsn = new AssessmentSupervisions();
			int liReturn = 0;
			if (!commitBean.getInsertList().isEmpty()) {
				for(AssessmentSupervisions data:commitBean.getInsertList()){
					data.setCreateUserId(commitBean.getCreateUserId());
				}
				liReturn = oimslevlRepository.typeAssSupInsertAssessmentSupervisions(commitBean.getInsertList());
			}
			if (!commitBean.getUpdateList().isEmpty()) {
				for(AssessmentSupervisions data:commitBean.getUpdateList()){
					data.setModifyUserId(commitBean.getCreateUserId());
				}
				liReturn = oimslevlRepository.typeAssSupUpdateAssessmentSupervisions(commitBean.getUpdateList());
			}
			if (!commitBean.getDeleteList().isEmpty()) {
				for(AssessmentSupervisions data:commitBean.getDeleteList()){
					Long value=data.getParentAssessmentId();
					data.setModifyUserId(commitBean.getCreateUserId());
					List<Long> returnObj=new ArrayList<Long>();
					returnObj.add(value);
					final Integer dupCount = oimslevlRepository.validateDelRow(returnObj);
					if (dupCount > 0) {
						assSupervsn.setListSeq(BigDecimal.valueOf(5));
						liReturnData.add(assSupervsn);
						return liReturnData;
					}
					List<AssessmentSupervisions> list=new ArrayList<AssessmentSupervisions>();
					list.add(data);
					liReturn = oimslevlRepository.typeAssSupDeleteAssessmentSupervisions(list);
				}
			}

			assSupervsn.setListSeq(BigDecimal.valueOf(liReturn));
			liReturnData.add(assSupervsn);
		}	catch(Exception e) {
			if(e.getMessage().contains("assessment_supversions_pk")) {
				final AssessmentSupervisions error = new AssessmentSupervisions();
				String errorMsg = "Error : OMS_OWNER.ASSESSMENT_SUPVERSIONS_PK";
				error.setErrorMessage(errorMsg);
				liReturnData.add(error);
			}
		}
		return liReturnData;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<Assessments> rgAssessmentSectionsRecordGroup() {
		return oimslevlRepository.rgAssessmentSectionsRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<Assessments> rgAssessmentTypesRecordGroup(String userId) {
		final List<Assessments> returnList = oimslevlRepository.rgAssessmentTypesRecordGroup(userId);
		returnList.forEach(action -> {
			action.setAssessmentCode(action.getCode());
		});
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AssessmentResults> rgAssessmentResultsRecordGroup(final Long assessmentId) {
		return oimslevlRepository.rgAssessmentResultsRecordGroup(assessmentId);
	}

	@Override
	public List<Assessments> assTypeAssSectExecuteQuery(final Assessments searchRecord) {
		return oimslevlRepository.assTypeAssSectExecuteQuery(searchRecord);
	}

	public Integer validateDelRow(final List<AssessmentSupervisions> deleteList) {
		final List<Long> returnObj = deleteList.stream().map(data -> data.getAssessmentId())
				.collect(Collectors.toList());
		return oimslevlRepository.validateDelRow(returnObj);
	}

	@Override
	public String checkLovData(final Long assessmentId) {
		return oimslevlRepository.checkLovData(assessmentId);
	}

	@Override
	public Integer updateEnforceFlag(Long assesmentId, String enforcementFlag, String userName) {
		return oimslevlRepository.updateEnforceFlag(assesmentId, enforcementFlag, userName);
	}

	@Override
	public String getEnforcementFlag(Long assessmentId) {
		return oimslevlRepository.getEnforcementFalg(assessmentId);
	}
	
	@Override
	public Integer updateSectionsDetails(AssessmentsCommitBean commitBean) {
		int result = 0;
		if (commitBean != null && commitBean.getUpdateList().size() > 0 && !commitBean.getUpdateList().isEmpty()) {
			commitBean.getUpdateList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			result = oimslevlRepository.updateSectionsDetails(commitBean.getUpdateList());
		}
		return result;
	}

}
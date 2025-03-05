package net.syscon.s4.iwp.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.casemanagement.beans.CaseReviewPeriods;
import net.syscon.s4.inst.casemanagement.beans.CaseReviewPeriodsCommitBean;
import net.syscon.s4.iwp.OcmcprevRepository;
import net.syscon.s4.iwp.OcmcprevService;

/**
 * Class OcmcprevServiceImpl
 */
@Service
public class OcmcprevServiceImpl extends BaseBusiness implements OcmcprevService {

	@Autowired
	private OcmcprevRepository ocmcprevRepository;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<ReferenceCodes> caseReviewPeriodsPostQuery(final ReferenceCodes paramBean) {
		return ocmcprevRepository.caseReviewPeriodsPostQuery(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<CaseReviewPeriods> caseReviewPeriodsExecuteQuery(final CaseReviewPeriods searchRecord) {
		return ocmcprevRepository.caseReviewPeriodsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCASE_REVIEW_PERIODS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<CaseReviewPeriods> caseReviewPeriodsCommit(final CaseReviewPeriodsCommitBean commitBean) {
		final List<CaseReviewPeriods> caseReviewPeriodsList = new ArrayList<>();
		final CaseReviewPeriods caseReviewPeriods = new CaseReviewPeriods();
		int liReturn = 0;
		// insertRecords
		if (Optional.ofNullable(commitBean.getInsertList()).isPresent() && !commitBean.getInsertList().isEmpty()) {
			for (CaseReviewPeriods obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmcprevRepository.caseReviewPeriodsInsertCaseReviewPeriods(commitBean.getInsertList());
		}
		// updateRecords
		if (Optional.ofNullable(commitBean.getUpdateList()).isPresent() && !commitBean.getUpdateList().isEmpty()) {
			for (CaseReviewPeriods obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmcprevRepository.caseReviewPeriodsUpdateCaseReviewPeriods(commitBean.getUpdateList());
		}
		// deleteRecords
		if (Optional.ofNullable(commitBean.getDeleteList()).isPresent() && !commitBean.getDeleteList().isEmpty()) {
			for (CaseReviewPeriods obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmcprevRepository.caseReviewPeriodsDeleteCaseReviewPeriods(commitBean.getDeleteList());
		}
		caseReviewPeriods.setReturnValue(liReturn);
		caseReviewPeriodsList.add(caseReviewPeriods);
		return caseReviewPeriodsList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgSupLevelRecordGroup() {
		return ocmcprevRepository.rgSupLevelRecordGroup();

	}

}
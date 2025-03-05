package net.syscon.s4.inst.legalscreens.maintenance.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.legals.beans.SentenceAdjustment;
import net.syscon.s4.inst.legals.beans.SentenceAdjustmentCommitBean;
import net.syscon.s4.inst.legalscreens.maintenance.OimsatypRepository;
import net.syscon.s4.inst.legalscreens.maintenance.OimsatypService;

/**
 * Class OimsatypServiceImpl
 */
@Service
public class OimsatypServiceImpl extends BaseBusiness implements OimsatypService {

	@Autowired
	private OimsatypRepository oimsatypRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<SentenceAdjustment> sentenceAdjustmentsExecuteQuery(final SentenceAdjustment searchRecord) {
		return oimsatypRepository.sentenceAdjustmentExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSENTENCE_ADJUSTMENTS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer sentenceAdjustmentsCommit(final SentenceAdjustmentCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (SentenceAdjustment obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			final String returnValue = oimsatypRepository.sentenceAdjustmentInsertQuery(commitBean.getInsertList());
			if (returnValue.contains("SA_PK")) {
				return 2;
			} else if ("1".equals(returnValue)) {
				liReturn = 1;
			} else {
				return 0;
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (SentenceAdjustment obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimsatypRepository.sentenceAdjustmentUpdateQuery(commitBean.getUpdateList());

		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oimsatypRepository.sentenceAdjustmentDeleteQuery(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkSentenceAdjustmentsDspRecordGroup() {
		return oimsatypRepository.cgfkSentenceAdjustmentDspRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkSentenceAdjustmentsUsagRecordGroup() {
		return oimsatypRepository.cgfkSentenceAdjustmentUsagRecordGroup();

	}

}
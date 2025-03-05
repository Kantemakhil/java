package net.syscon.s4.inst.legalscreens.maintenance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.legalscreens.maintenance.OimlegstRepository;
import net.syscon.s4.inst.legalscreens.maintenance.OimlegstService;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasons;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasonsCommitBean;
import net.syscon.s4.inst.legalscreens.maintenance.bean.TableColumnNameBean;

/**
 * Class OimlegstServiceImpl
 */
@Service
public class OimlegstServiceImpl extends BaseBusiness implements OimlegstService {

	@Autowired
	private OimlegstRepository oimlegstRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<LegalUpdateReasons> updateReasonsExecuteQuery(final LegalUpdateReasons searchRecord) {
		final List<LegalUpdateReasons> returnList = oimlegstRepository.updateReasonsExecuteQuery(searchRecord);
		returnList.forEach(ele -> {
			oimlegstRepository.getNbtReasonCategory(ele);
			oimlegstRepository.getNbtActiveType(ele);
		});
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstUPDATE_REASONS
	 */
	@Transactional
	public List<LegalUpdateReasons> updateReasonsCommit(final LegalUpdateReasonsCommitBean commitBean) {
		final List<LegalUpdateReasons> liReturnData = new ArrayList<>();
		final LegalUpdateReasons legalUpdateReasons = new LegalUpdateReasons();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (LegalUpdateReasons obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			
			}
			liReturn = oimlegstRepository.updateReasonsInsertLegalUpdateReasons(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (LegalUpdateReasons obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimlegstRepository.updateReasonsUpdateLegalUpdateReasons(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (LegalUpdateReasons obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimlegstRepository.updateReasonsDeleteLegalUpdateReasons(commitBean.getDeleteList());
		}
		legalUpdateReasons.setReturnValue(BigDecimal.valueOf(liReturn));
		liReturnData.add(legalUpdateReasons);
		return liReturnData;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgCatRecordGroup() {
		return oimlegstRepository.rgCatRecordGroup();
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgStatRecordGroup() {
		return oimlegstRepository.rgStatRecordGroup();
	}

	/**
	 * Performing the if the record is having parent data or not
	 * 
	 * @return
	 */
	public Integer getDeleteRecordOrNot(final LegalUpdateReasons searchRecord) {
		Integer returnCount = 0;
		List<TableColumnNameBean> returnList = oimlegstRepository.getTableColumNames();
		for (TableColumnNameBean bean : returnList)
			if (bean.getTableName() != null && bean.getColumnName() != null
					&& searchRecord.getUpdateReasonCode() != null) {
				final Integer count = oimlegstRepository.senCalcKeyDelrec(bean.getTableName(), bean.getColumnName(),
						searchRecord.getUpdateReasonCode());
				if (count > 0) {
					return count;
				}
			}
		return returnCount;
	}
}
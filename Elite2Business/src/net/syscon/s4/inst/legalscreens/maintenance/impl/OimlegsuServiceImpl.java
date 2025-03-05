package net.syscon.s4.inst.legalscreens.maintenance.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.LegalUpdateReasons;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.LegalUpdateUsages;
import net.syscon.s4.im.beans.LegalUpdateUsagesCommitBean;
import net.syscon.s4.inst.legalscreens.maintenance.OimlegsuRepository;
import net.syscon.s4.inst.legalscreens.maintenance.OimlegsuService;

/**
 * Class OimlegsuServiceImpl
 */
@Service
public class OimlegsuServiceImpl extends BaseBusiness implements OimlegsuService {

	@Autowired
	private OimlegsuRepository oimlegsuRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<LegalUpdateUsages> lglUpdUsagesExecuteQuery(final LegalUpdateUsages searchRecord) {
		final List<LegalUpdateUsages> returnList = oimlegsuRepository.lglUpdUsagesExecuteQuery(searchRecord);
		returnList.forEach(action -> {
			if (action.getUpdateReasonCode() != null) {
				final LegalUpdateUsages data = oimlegsuRepository.postQueryData(action.getUpdateReasonCode());
				if (data != null) {
					action.setDescription(data.getDescription());
					action.setReasonCategory(data.getReasonCategory());
					action.setActiveType(data.getActiveType());
					;
				}
			}
		});
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstLGL_UPD_USAGES
	 */
	@Transactional
	public LegalUpdateUsages lglUpdUsagesCommit(final LegalUpdateUsagesCommitBean commitBean) {
		LegalUpdateUsages returnData = new LegalUpdateUsages();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final LegalUpdateUsages obj : commitBean.getInsertList()) {
				if (obj.getExpiryDate() != null) {
					obj.setExpiryDate(trunc(obj.getExpiryDate()));
				}
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oimlegsuRepository.lglUpdUsagesInsertLegalUpdateUsages(commitBean.getInsertList());
			if (liReturn == 1) {
				returnData.setSealFlag("1");
			} else {
				returnData.setSealFlag("0");
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final LegalUpdateUsages obj : commitBean.getUpdateList()) {
				if (obj.getExpiryDate() != null) {
					obj.setExpiryDate(trunc(obj.getExpiryDate()));
				}
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimlegsuRepository.lglUpdUsagesUpdateLegalUpdateUsages(commitBean.getUpdateList());
			if (liReturn == 1) {
				returnData.setSealFlag("1");
			} else {
				returnData.setSealFlag("0");
			}
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			returnData = oimlegsuRepository.lglUpdUsagesDeleteLegalUpdateUsages(commitBean.getDeleteList());
		}
		return returnData;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgLegalClassRecordGroup() {
		return oimlegsuRepository.rgLegalClassRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<LegalUpdateReasons> rgUpdateReasonCodeRecordGroup() {
		return oimlegsuRepository.rgUpdateReasonCodeRecordGroup();

	}

	@Override
	public LegalUpdateUsages postQueryData(final String updateReasonCode) {
		return oimlegsuRepository.postQueryData(updateReasonCode);
	}

	public Integer deleteKeyDelRec(final LegalUpdateUsages objSearchDao) {
		return oimlegsuRepository.deleteKeyDelRec(objSearchDao);
	}

	public Date trunc(final Date date) {
		if (date != null) {
			final Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			calender.set(Calendar.HOUR, 0);
			calender.set(Calendar.MINUTE, 0);
			calender.set(Calendar.SECOND, 0);
			calender.set(Calendar.MILLISECOND, 0);
			final Date returnDate = calender.getTime();
			return returnDate;
		}
		return null;
	}
}
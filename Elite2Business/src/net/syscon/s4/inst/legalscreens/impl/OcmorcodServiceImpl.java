package net.syscon.s4.inst.legalscreens.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenceResultCodes;
import net.syscon.s4.im.beans.OffenceResultCodesCommitBean;
import net.syscon.s4.inst.legalscreens.OcmorcodRepository;
import net.syscon.s4.inst.legalscreens.OcmorcodService;

/**
 * Class OcmorcodServiceImpl
 */
@Service
public class OcmorcodServiceImpl extends BaseBusiness implements OcmorcodService {

	@Autowired
	private OcmorcodRepository ocmorcodRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<OffenceResultCodes> resCodExecuteQuery(final OffenceResultCodes searchRecord) {
		return ocmorcodRepository.resCodExecuteQuery(searchRecord);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstRES_COD
	 */
	@Transactional
	public List<OffenceResultCodes> resCodCommit(final OffenceResultCodesCommitBean commitBean) {
		List<OffenceResultCodes> resultList = new ArrayList<>();
		OffenceResultCodes resultCodes = new OffenceResultCodes();
		int liReturn = 0;
		if (!commitBean.getInsertList().isEmpty()) {
			for (final OffenceResultCodes obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				if (obj.getExpiryDate() != null) {
					obj.setExpiryDate(trunc(obj.getExpiryDate()));
				}
			}
			liReturn = ocmorcodRepository.resCodInsertOffenceResultCodes(commitBean.getInsertList());
		}
		if (!commitBean.getUpdateList().isEmpty()) {
			for (final OffenceResultCodes obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				if (obj.getExpiryDate() != null) {
					obj.setExpiryDate(trunc(obj.getExpiryDate()));
				}
			}
			liReturn = ocmorcodRepository.resCodUpdateOffenceResultCodes(commitBean.getUpdateList());
		}
		if (liReturn > 0) {
			resultCodes.setSealFlag("success");
		} else {
			resultCodes.setSealFlag("fail");
		}
		resultList.add(resultCodes);
		return resultList;
	}

	/**
	 * @param date
	 * @return the date
	 */
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
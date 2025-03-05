package net.syscon.s4.inst.careinplacement.maintenance.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.careinplacement.beans.PlacementDurations;
import net.syscon.s4.inst.careinplacement.beans.PlacementDurationsCommitBean;
import net.syscon.s4.inst.careinplacement.maintenance.OimpldurRepository;
import net.syscon.s4.inst.careinplacement.maintenance.OimpldurService;

/**
 * Class OimpldurServiceImpl
 */
@Service
public class OimpldurServiceImpl extends BaseBusiness implements OimpldurService {

	@Autowired
	private OimpldurRepository oimpldurRepository;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<ReferenceCodes> placementDurPostQuery(final ReferenceCodes paramBean) {
		return oimpldurRepository.placementDurPostQuery(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<PlacementDurations> placementDurExecuteQuery(final PlacementDurations searchRecord) {
		return oimpldurRepository.placementDurExecuteQuery(searchRecord);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPLACEMENT_DUR
	 */
	@Transactional
	public PlacementDurations placementDurCommit(final PlacementDurationsCommitBean commitBean) {
		int liReturn = 0;
		final PlacementDurations returnData = new PlacementDurations();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final PlacementDurations obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				if (obj.getExpiryDate() != null) {
					obj.setExpiryDate(trunc(obj.getExpiryDate()));
				}
			}
			liReturn = oimpldurRepository.placementDurInsertPlacementDurations(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final PlacementDurations obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				if (obj.getExpiryDate() != null) {
					obj.setExpiryDate(trunc(obj.getExpiryDate()));
				}
			}
			liReturn = oimpldurRepository.placementDurUpdatePlacementDurations(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oimpldurRepository.placementDurDeletePlacementDurations(commitBean.getDeleteList());
		}
		if (liReturn == 1) {
			returnData.setSealFlag("1");
		} else {
			returnData.setSealFlag("0");
		}
		return returnData;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgDurationTypeRecordGroup() {
		return oimpldurRepository.rgDurationTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgPlacementTypeRecordGroup() {
		return oimpldurRepository.rgPlacementTypeRecordGroup();

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
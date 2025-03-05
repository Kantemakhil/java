package net.syscon.s4.inst.booking.maintainence.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ProfileCategory;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.ProfileCategoriesCommitBean;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.im.beans.ProfileTypesCommitBean;
import net.syscon.s4.inst.booking.maintainence.OimprfcaRepository;
import net.syscon.s4.inst.booking.maintainence.OimprfcaService;

/**
 * Class OimprfcaServiceImpl
 */
@Service
public class OimprfcaServiceImpl extends BaseBusiness implements OimprfcaService {

	@Autowired
	private OimprfcaRepository oimprfcaRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<ProfileTypes> pflTypeExecuteQuery(final ProfileTypes searchRecord) {
		return oimprfcaRepository.pflTypeExecuteQuery(searchRecord);

	}

	@Transactional
	public List<ProfileCategory> pflCatCommit(final ProfileCategoriesCommitBean commitBean) {
		final List<ProfileCategory> list = new ArrayList<>();
		final ProfileCategory category = new ProfileCategory();
		Integer liReturn = 0;
		if (!commitBean.getInsertList().isEmpty()) {
			for (ProfileCategory bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oimprfcaRepository.pflCatInsertProfileCategories(commitBean.getInsertList());
		}
		if (!commitBean.getUpdateList().isEmpty()) {
			for (ProfileCategory bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimprfcaRepository.pflCatUpdateProfileCategories(commitBean.getUpdateList());
		}
		if (!commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oimprfcaRepository.pflCatDeleteProfileCategories(commitBean.getDeleteList());
		}
		category.setSealFlag(liReturn.toString());
		list.add(category);
		return list;
	}

	@Override
	public List<ProfileCategory> pflCatExecuteQuery(final ProfileCategory obj) {
		return oimprfcaRepository.pflCatExecuteQuery(obj);
	}

	@Transactional
	public List<ProfileTypes> pflTypeCommit(final ProfileTypesCommitBean commitBean) {
		final List<ProfileTypes> returnList = new ArrayList<>();
		final ProfileTypes returnBean = new ProfileTypes();
		int liReturn = 0;
		if (!commitBean.getInsertList().isEmpty()) {
			for (final ProfileTypes obj : commitBean.getInsertList()) {
				if (obj.getExpiryDate() != null) {
					obj.setExpiryDate(trunc(obj.getExpiryDate()));
				}
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oimprfcaRepository.pflTypeInsertProfileTypes(commitBean.getInsertList());
		}
		if (!commitBean.getUpdateList().isEmpty()) {
			for (final ProfileTypes obj : commitBean.getUpdateList()) {
				if (obj.getExpiryDate() != null) {
					obj.setExpiryDate(trunc(obj.getExpiryDate()));
				}
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimprfcaRepository.pflTypeUpdateProfileTypes(commitBean.getUpdateList());
		}
		if (liReturn > 0) {
			returnBean.setSealFlag("success");
		} else {
			returnBean.setSealFlag("fail");
		}
		returnList.add(returnBean);
		return returnList;
	}

	/**
	 * Trunc.
	 *
	 * @param date
	 *            the date
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
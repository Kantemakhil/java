package net.syscon.s4.inst.booking.maintainence.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.booking.beans.ContactPersonTypes;
import net.syscon.s4.inst.booking.beans.ContactPersonTypesCommitBean;
import net.syscon.s4.inst.booking.maintainence.OimctactRepository;
import net.syscon.s4.inst.booking.maintainence.OimctactService;

/**
 * Class OimctactServiceImpl
 */
@Service
public class OimctactServiceImpl extends BaseBusiness implements OimctactService {

	@Autowired
	private OimctactRepository oimctactRepository;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 */
	public List<StaffMembers> contactPersonTypesPostQuery(final StaffMembers paramBean) {
		return oimctactRepository.contactPersonTypesPostQuery(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<ContactPersonTypes> contactPersonTypesExecuteQuery(final ContactPersonTypes searchRecord) {
		return oimctactRepository.contactPersonTypesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCONTACT_PERSON_TYPES
	 */
	@Transactional
	public Integer contactPersonTypesCommit(final ContactPersonTypesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final ContactPersonTypes obj : commitBean.getInsertList()) {
				if (obj.getExpiryDate() != null) {
					obj.setExpiryDate(trunc(obj.getExpiryDate()));
				}
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oimctactRepository.contactPersonTypesInsertContactPersonTypes(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final ContactPersonTypes obj : commitBean.getUpdateList()) {
				if (obj.getExpiryDate() != null) {
					obj.setExpiryDate(trunc(obj.getExpiryDate()));
				}
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimctactRepository.contactPersonTypesUpdateContactPersonTypes(commitBean.getUpdateList());
		}
		return liReturn;

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<HashMap<String, Object>> rgRelationshipTypeRecordGroup() {
		final List<ReferenceCodes> returnList = oimctactRepository.rgRelationshipTypeRecordGroup();
		List<HashMap<String, Object>> result = new ArrayList<>();
		if (returnList != null && !returnList.isEmpty()) {
			result = returnList.stream().map(data -> {
				HashMap<String, Object> record = new HashMap<>();
				if (data.getDescription() != null) {
					record.put("description", data.getDescription().toUpperCase());
					record.put("listDescription", data.getDescription());
				}
				if (data.getCode() != null) {
					record.put("code", data.getCode());
				}
				return record;
			}).collect(Collectors.toList());
		}
		return result;

	}

	/**
	 * This method is used to execute a record group
	 */
	public List<HashMap<String, Object>> rgContactTypeRecordGroup() {
		final List<ReferenceCodes> returnList = oimctactRepository.rgContactTypeRecordGroup();
		List<HashMap<String, Object>> result = new ArrayList<>();
		if (returnList != null && !returnList.isEmpty()) {
			result = returnList.stream().map(data -> {
				HashMap<String, Object> record = new HashMap<>();
				if (data.getDescription() != null) {
					record.put("description", data.getDescription().toUpperCase());
					record.put("listDescription", data.getDescription());
				}
				if (data.getCode() != null) {
					record.put("code", data.getCode());
				}
				return record;
			}).collect(Collectors.toList());
		}
		return result;

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
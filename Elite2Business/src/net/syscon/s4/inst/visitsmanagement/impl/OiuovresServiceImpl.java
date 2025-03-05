package net.syscon.s4.inst.visitsmanagement.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.booking.beans.PersonsCommitBean;
import net.syscon.s4.inst.visitsmanagement.OiuovresRepository;
import net.syscon.s4.inst.visitsmanagement.OiuovresService;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderPersonRestricts;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderPersonRestrictsCommitBean;

@Service
public class OiuovresServiceImpl extends BaseBusiness implements OiuovresService {

	@Autowired
	private OiuovresRepository oiuovresRepository;

	/**
	 * Logger object used to print the log in the file
	 */

	/**
	 * Creates new OiuovresServiceImpl class Object
	 */
	public OiuovresServiceImpl() {
		// OiuovresServiceImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<Persons> perExecuteQuery(final Persons searchRecord) {
		return oiuovresRepository.perExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPER
	 *
	 * 
	 */
	@Transactional
	public Integer perCommit(final PersonsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = oiuovresRepository.perInsertPersons(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = oiuovresRepository.perUpdatePersons(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<OffenderPersonRestricts> offConRestExecuteQuery(final OffenderPersonRestricts searchRecord) {
		List<OffenderPersonRestricts> returnList = oiuovresRepository.offConRestExecuteQuery(searchRecord);
		for (OffenderPersonRestricts offenderPersonRestricts : returnList) {
			//setting staff name to Description field
			offenderPersonRestricts
					.setStringEnteredStaffId(oiuovresRepository.getStaffName(offenderPersonRestricts.getEnteredStaffId()));
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_CON_REST
	 *
	 * 
	 */
	@Transactional
	public Integer offConRestCommit(final OffenderPersonRestrictsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for(OffenderPersonRestricts obj :  commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setEnteredStaffId(oiuovresRepository.getStaffId(commitBean.getCreateUserId()));
			}
			liReturn = oiuovresRepository.offConRestInsertOffenderPersonRestricts(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for(OffenderPersonRestricts obj : commitBean.getUpdateList() ) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				obj.setEnteredStaffId(oiuovresRepository.getStaffId(commitBean.getCreateUserId()));
			}
			liReturn = oiuovresRepository.offConRestUpdateOffenderPersonRestricts(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgOffRestrictionTypeRecordGroup() {
		return oiuovresRepository.rgOffRestrictionTypeRecordGroup();

	}

	public List<ReferenceCodes> rgStaffIdRecordGroup() {
		return oiuovresRepository.rgStaffIdRecordGroup();
	}

}
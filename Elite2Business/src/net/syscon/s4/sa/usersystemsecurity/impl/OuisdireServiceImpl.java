package net.syscon.s4.sa.usersystemsecurity.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.InternetAddressesCommitBean;
import net.syscon.s4.im.beans.PhonesCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.sa.usersystemsecurity.OuisdireRepository;
import net.syscon.s4.sa.usersystemsecurity.OuisdireService;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffSkills;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffSkillsCommitBean;
import net.syscon.s4.sa.usersystemsecurity.beans.VMemberSkills;
import net.syscon.s4.sa.usersystemsecurity.beans.VMemberSkillsCommitBean;

/**
 * Class OuisdireServiceImpl
 */
@Service
public class OuisdireServiceImpl extends BaseBusiness implements OuisdireService {

	@Autowired
	private OuisdireRepository ouisdireRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	@Override
	public List<VMemberSkills> vms1ExecuteQuery(final VMemberSkills objVMemberSkills) {
		String cityCode = null;
		List<VMemberSkills> recordList = new ArrayList<>();
		recordList = ouisdireRepository.vms1ExecuteQuery(objVMemberSkills);
		for (final VMemberSkills vMemberSkills : recordList) {
			cityCode = ouisdireRepository.getcityCode(vMemberSkills.getAgyLocId());
			vMemberSkills.setCity(cityCode);
		}
		return recordList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstVMS1
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer vms1Commit(final VMemberSkillsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			liReturn = ouisdireRepository.vms1InsertVMemberSkills(commitBean.getInsertList());

		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<StaffSkills> stskExecuteQuery(final StaffSkills searchRecord) {
		return ouisdireRepository.stskExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTSK
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer stskCommit(final StaffSkillsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(data->data.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = ouisdireRepository.stskInsertStaffSkills(commitBean.getInsertList());

		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<Phones> hmPhoneExecuteQuery(final Phones searchRecord) {
		return ouisdireRepository.hmPhoneExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<Phones> busPhoneExecuteQuery(final Phones searchRecord) {
		return ouisdireRepository.busPhoneExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstHM_PHONE
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer hmPhoneCommit(final PhonesCommitBean commitBean) {
		return 0;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstBS_PHONE
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer bsPhoneCommit(final PhonesCommitBean commitBean) {
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<InternetAddresses> emailExecuteQuery(final InternetAddresses searchRecord) {
		return ouisdireRepository.emailExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstEMAIL
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer emailCommit(final InternetAddressesCommitBean commitBean) {
		return 0;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<Areas> nomRegionRgRecordGroup() {
		return ouisdireRepository.nomRegionRgRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkStskSkillTypeRecordGroup() {
		return ouisdireRepository.cgfkStskSkillTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkStskSubTypeRecordGroup(final String subType) {
		return ouisdireRepository.cgfkStskSubTypeRecordGroup(subType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkVmsSexCodeRecordGroup() {
		return ouisdireRepository.cgfkVmsSexCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkVmsAgencyLocationTypeRecordGroup() {
		return ouisdireRepository.cgfkVmsAgencyLocationTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> cgfkVmsAgyLocIdRecordGroup() {
		List<AgencyLocations> returnList= new ArrayList<AgencyLocations>();
		returnList = ouisdireRepository.cgfkVmsAgyLocIdRecordGroup();
		Integer order = 0;
		for (AgencyLocations agencyLocations : returnList) {
			order = order+1;
			agencyLocations.setListSeq(order);
		}
		
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkVmsCityRecordGroup() {
		return ouisdireRepository.cgfkVmsCityRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkVmsScheduleTypeRecordGroup() {
		return ouisdireRepository.cgfkVmsScheduleTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkVmsPositionRecordGroup() {
		return ouisdireRepository.cgfkVmsPositionRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkVmsRoleRecordGroup() {
		return ouisdireRepository.cgfkVmsRoleRecordGroup();

	}

	@Override
	public List<InternetAddresses> vms1OnCheckDeleteMaster(final InternetAddresses paramBean) {
		return null;
	}

	@Override
	public List<AgencyLocations> cgfkchkVms1AgyLocIdF3(final AgencyLocations paramBean) {
		return ouisdireRepository.cgfkchkVms1AgyLocIdF3(paramBean);
	}

	@Override
	public Phones vms1WhenNewRecordInstance(final Phones paramBean) {
		return null;
	}

}
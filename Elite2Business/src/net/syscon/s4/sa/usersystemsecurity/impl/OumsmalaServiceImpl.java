package net.syscon.s4.sa.usersystemsecurity.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.StaffMembersCommitBean;
import net.syscon.s4.sa.usersystemsecurity.OumsmalaRepository;
import net.syscon.s4.sa.usersystemsecurity.OumsmalaService;
import net.syscon.s4.sa.usersystemsecurity.beans.OffenderWorkAssignments;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRoles;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRolesCommitBean;

/**
 * Class OumsmalaServiceImpl
 */
@Service
public class OumsmalaServiceImpl extends BaseBusiness implements OumsmalaService {

@Autowired
private OumsmalaRepository oumsmalaRepository;
	


/**
 * This method is execute a Dao class method when trigger event is fired
 *
 * @param params
 *
*/
	public List<ReferenceCodes> cgfkchkSlrCSlrSchedType(ReferenceCodes paramBean)  {
		return  oumsmalaRepository.cgfkchkSlrCSlrSchedType(paramBean);
}
		
/**
 * This method is execute a Dao class method when trigger event is fired
 *
 * @param params
 *
 * @throws Exception
*/
	public Integer cguvchkSlrPk(final StaffLocationRoles paramBean)  {
		final Integer count = oumsmalaRepository.cguvchkSlrPk(paramBean);
		return count;
	}
/**
 * This method is execute a Dao class method when trigger event is fired
 *
 * @param params
 *
 * @throws Exception
*/
	public List<OffenderWorkAssignments> CgrichkStaffLocationRoles(OffenderWorkAssignments paramBean)  {
		return oumsmalaRepository.cgrichkStaffLocationRoles(paramBean);
}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<StaffMembers> smExecuteQuery(StaffMembers searchRecord) {
		return oumsmalaRepository.smExecuteQuery(searchRecord);
	}

	

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> calExecuteQuery(AgencyLocations searchRecord) {
		return oumsmalaRepository.calExecuteQuery(searchRecord);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<StaffLocationRoles> slrExecuteQuery(StaffLocationRoles searchRecord) {
		return oumsmalaRepository.slrExecuteQuery(searchRecord);

	}

/**Insert the records from database table
 *
 * @param lstSLR
 *
 * @throws SQLException
 */
@Transactional
public StaffLocationRoles slrCommit(final StaffLocationRolesCommitBean commitBean)  {
		StaffLocationRoles returnData = new StaffLocationRoles();
			if (commitBean.getInsertList() != null	&& commitBean.getInsertList().size() > 0) {
				for (StaffLocationRoles obj : commitBean.getInsertList()) {
					obj.setCreateUserId(commitBean.getCreateUserId());
				}
				returnData = oumsmalaRepository.slrInsertStaffLocationRoles(commitBean.getInsertList());
			}
			if (commitBean.getUpdateList() != null	&& commitBean.getUpdateList().size() > 0) {
				for (StaffLocationRoles obj : commitBean.getUpdateList()) {
					obj.setModifyUserId(commitBean.getCreateUserId());
				}
				returnData = oumsmalaRepository.slrUpdateStaffLocationRoles(commitBean.getUpdateList());
			}
			return returnData;
}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkSlrPositionRecordGroup() {
		return oumsmalaRepository.cgfkSlrPositionRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkSlrRoleRecordGroup() {
		return oumsmalaRepository.cgfkSlrRoleRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkSlrStaffUnitRecordGroup() {
		return oumsmalaRepository.cgfkSlrStaffUnitRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkSlrScheduleTypeRecordGroup() {
		return oumsmalaRepository.cgfkSlrScheduleTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> cgfkCalAgyLocIdRecordGroup() {
		return oumsmalaRepository.cgfkCalAgyLocIdRecordGroup();

	}/**
		 * This method is used to execute a record group
		 *
		 * @throws SQLException
		 */
	@Override
	public List<ReferenceCodes> CgfkchkSlrCSlrSchedType(ReferenceCodes paramBean) {
		return null;
	}

	@Override
	public Integer smCommit(StaffMembersCommitBean commitBean) {
		return null;
	}

	@Override
	public List<ReferenceCodes> CgfkchkSlrCSlrRoleFk(ReferenceCodes paramBean) {
		return null;
	}

	@Override
	public List<ReferenceCodes> CgfkchkSlrCSlrPosnFk(ReferenceCodes paramBean) {
		return null;
	}

	@Override
	public List<Caseloads> cgfkSacCaseloadIdRecordGroup() {
		return null;
	}

	@Override
	public List<StaffLocationRoles> CguvchkSlrPk(StaffLocationRoles paramBean) {
		return null;
	}

}
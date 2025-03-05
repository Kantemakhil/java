package net.syscon.s4.iwp.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.iwp.OcmshierRepository;
import net.syscon.s4.iwp.OcmshierService;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRoles;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRolesCommitBean;

/**
 * Class OcmshierServiceImpl
 */
@Service
public class OcmshierServiceImpl extends BaseBusiness implements OcmshierService {

	@Autowired
	private OcmshierRepository ocmshierRepo;

	/**
	 * This method is used to execute a record group
	 * 
	 * @param caseloadid
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> calAgyLocIdRecordGroup(final String caseloadId) {
		return ocmshierRepo.calAgyLocIdRecordGroup(caseloadId);

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 *
	 */
	public List<ReferenceCodes> staffLrPositionRecordGroup() {
		return ocmshierRepo.staffLrPositionRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 * 
	 */
	public List<ReferenceCodes> staffLrRoleRecordGroup() {
		return ocmshierRepo.staffLrRoleRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<ReferenceCodes>
	 * 
	 */
	public List<ReferenceCodes> staffLrScheduleTypeRecordGroup() {
		return ocmshierRepo.staffLrScheduleTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @return List<StaffMembers>
	 * 
	 */
	public List<StaffMembers> staffLr1DspLastNameRecordGroup(final String agyLocIdLov) {
		return ocmshierRepo.staffLr1DspLastNameRecordGroup(agyLocIdLov);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @return List<StaffLocationRoles>
	 */
	public List<StaffLocationRoles> staffLrExecuteQuery(final StaffLocationRoles searchRecord) {

		return ocmshierRepo.staffLrExecuteQuery(searchRecord);

	}

	/**
	 * This method is used to execute a record group
	 * 
	 * @param agyLocIdLov
	 * @return List<StaffMembers>
	 */
	public List<StaffMembers> staffLrDspLastNameRecordGroup(final String agyLocIdLov) {
		return ocmshierRepo.staffLrDspLastNameRecordGroup(agyLocIdLov);

	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
//	public List<AgencyLocations> CgfkchkCalCsldAlAgyLoc(AgencyLocations paramBean)  {
//
//			List<AgencyLocations> agencyLocationsList = ocmshierRepo.cgfkchkCalCsldAlAgyLocFc(paramBean);
//	
//return agencyLocationsList;
//}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 * 
	 * 
	 */
//	public ReferenceCodes CgfkchkStaffLrStaffLrRef(final ReferenceCodes paramBean)  {
//			ReferenceCodes referenceCodes = ocmshierRepo.cgfkchkStaffLrStaffLrRefc(paramBean);
//return referenceCodes;
//}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @return ReferenceCodes
	 */
	public ReferenceCodes cgfkchkStaffLrStaffLr(final ReferenceCodes paramBean) {

		final ReferenceCodes referenceCodes = ocmshierRepo.cgfkchkStaffLrStaffLr(paramBean);

		return referenceCodes;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
//	public StaffMembers CgfkchkStaffLrStaffLrSta(final StaffMembers paramBean)  {
//
//			StaffMembers staffMembers = ocmshierRepo.cgfkchkStaffLrStaffLrStac(paramBean);
//		
//return staffMembers;
//}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
//	public StaffMembers CgfklkpStaffLrStaffLrSta(final StaffMembers paramBean)  {
//		StaffMembers staffMembers = ocmshierRepo.cgfklkpStaffLrStaffLrStac(paramBean);
//		return staffMembers
//}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
//	public ReferenceCodes CgfkchkStaffLr1StaffLrRe(final ReferenceCodes paramBean)  {
//
//			ReferenceCodes referenceCodes = ocmshierRepo.cgfkchkStaffLr1StaffLrRec(paramBean);
//	
//return referenceCodes;
//}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
//	public ReferenceCodes CgfkchkStaffLr1StaffLr2(final ReferenceCodes paramBean)  {
//
//			ReferenceCodes referenceCodes = ocmshierRepo.cgfkchkStaffLr1StaffLr2c(paramBean);
//		
//return referenceCodes;
//}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
//	public ReferenceCodes CgfkchkStaffLr1StaffLr3(final ReferenceCodes paramBean)  {
//
//			ReferenceCodes referenceCodes = ocmshierRepo.cgfkchkStaffLr1StaffLr3c(paramBean);
//		
//return referenceCodes;
//}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
//	public StaffMembers CgfkchkStaffLr1StaffLrSt(final StaffMembers paramBean)  {
//
//			StaffMembers staffMembers = ocmshierRepo.cgfkchkStaffLr1StaffLrStc(paramBean);
//		
//return staffMembers;
//}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
//	public StaffMembers CgfklkpStaffLr1StaffLrSt(final StaffMembers paramBean)  {
//		StaffMembers staffMembers = ocmshierRepo.cgfklkpStaffLr1StaffLrStc(paramBean);
//		
//return staffMembers;
//}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 * @return List<CaseloadAgencyLocations>
	 * 
	 */
	public List<CaseloadAgencyLocations> calExecuteQuery(final CaseloadAgencyLocations searchRecord) {
		return ocmshierRepo.calExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCAL
	 *
	 * @throws SQLException
	 */
//@Transactional
//public Integer calCommit(CaseloadAgencyLocationsCommitBean commitBean)  {
//		int liReturn = 0;
//			//insertRecords
//			if (commitBean.getInsertList() != null	&& commitBean.getInsertList().size() > 0) {
//				liReturn = ocmshierRepo.calInsertcal(commitBean.getInsertList());
//return liReturn;
//			}
//}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 * @return Integer
	 */
	@Transactional
	public Integer staffCommit(final StaffLocationRolesCommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @return List<StaffLocationRoles>
	 */
	public List<StaffLocationRoles> staffLr1ExecuteQuery(final StaffLocationRoles searchRecord) {
		return ocmshierRepo.staffLr1ExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 * @return Integer
	 */
	@Transactional
	public Integer staffLr1Commit(final StaffLocationRolesCommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkStaffLr1PositionRecordGroup() {
		return ocmshierRepo.cgfkStaffLr1PositionRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkStaffLr1ScheduleTypeRecordGroup() {
		return ocmshierRepo.cgfkStaffLr1ScheduleTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkStaffLr1RoleRecordGroup() {
		return ocmshierRepo.cgfkStaffLr1RoleRecordGroup();

	}

	/**
	 * Insert the records from database table
	 *
	 * @param staffLRole
	 *
	 * @return Integer
	 */

	@Override
	public Integer stafflr1Commit(final StaffLocationRoles staffLRole) {
		int liReturn = 0;
		liReturn = ocmshierRepo.deleteStaffLr1Officer(staffLRole);
		return liReturn;

	}

}
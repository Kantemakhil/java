package net.syscon.s4.inst.casemanagement.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.casemanagement.OciocnotRepository;
import net.syscon.s4.inst.casemanagement.OciocnotService;
import net.syscon.s4.inst.casemanagement.beans.VOffenderCaseNotes;

/**
 * Class OciocnotServiceImpl
 */
@Service
public class OciocnotServiceImpl extends BaseBusiness implements OciocnotService {

	@Autowired
	private OciocnotRepository ociocnotRepo;

	/**
	 * Creates new OciocnotServiceImpl class Object
	 */
	public OciocnotServiceImpl() {
		// OciocnotServiceImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VOffenderCaseNotes> caseNoteExecuteQuery(final VOffenderCaseNotes searchRecord) {
		return ociocnotRepo.caseNoteExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCASE_NOTE
	 *
	 */

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgTypeRecordGroup() {
		return ociocnotRepo.rgTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgSubTypeRecordGroup() {
		return ociocnotRepo.rgSubTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgStaffNameRecordGroup() {
		return ociocnotRepo.rgStaffNameRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgLocationRecordGroup(final String caseLoadId, String userName) {
		return ociocnotRepo.rgLocationRecordGroup(caseLoadId, userName);

	}

	/**
	 * This method is used to get staffId from Staff Members
	 *
	 */
	public Integer toGetStaffId(String userName) {
		return ociocnotRepo.toGetStaffId(userName);
	}

	/**
	 * Used to get first name, last name from Staff Members
	 * 
	 * @return StaffMembers
	 */
	public StaffMembers toGetFirstAndLastName(final Integer staffId) {
		return ociocnotRepo.toGetFirstAndLastName(staffId);
	}

	@Override
	public Integer checkPrevExists(String userName) {
		return ociocnotRepo.checkPrevExists(userName);
	}

}
package net.syscon.s4.inst.workflow.maintenance.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.legals.beans.StaffDetails;
import net.syscon.s4.inst.workflow.maintenance.OcucstafRepository;
import net.syscon.s4.inst.workflow.maintenance.OcucstafService;

/**
 * Class OcucstafServiceImpl
 */
@Service
public class OcucstafServiceImpl extends BaseBusiness implements OcucstafService {

	@Autowired
	private OcucstafRepository ocucstafRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<StaffDetails> staffDetailsExecuteQuery(final StaffDetails searchRecord) {
		return ocucstafRepository.staffDetailsExecuteQuery(searchRecord);

	}

	
	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgAgencyTypeRecordGroup() {
		return ocucstafRepository.rgAgencyTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<Areas> rgAreaRecordGroup(final String areaType) {
		return ocucstafRepository.rgAreaRecordGroup(areaType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgStaffStatusRecordGroup() {
		return ocucstafRepository.rgStaffStatusRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> rgLocationRecordGroup(final String areaCode,final String areaType) {
		return ocucstafRepository.rgLocationRecordGroup(areaCode, areaType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgRoleRecordGroup() {
		return ocucstafRepository.rgRoleRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgPositionRecordGroup() {
		return ocucstafRepository.rgPositionRecordGroup();

	}

}
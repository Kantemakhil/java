package net.syscon.s4.workspace.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.workspace.OcdsmemoRepository;
import net.syscon.s4.workspace.OcdsmemoService;

/**
 * Class OcdsmemoServiceImpl
 */
@Service
public class OcdsmemoServiceImpl extends BaseBusiness implements OcdsmemoService {

	@Autowired
	private OcdsmemoRepository ocdsmemoRepository;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<VHeaderBlock> getOffDetails(final VHeaderBlock paramBean) {

		final List<VHeaderBlock> vHeaderBlockList = ocdsmemoRepository.getOffDetails(paramBean);

		return vHeaderBlockList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgWorkTypeRecordGroup(final String caseloadType) {
		return ocdsmemoRepository.rgWorkTypeRecordGroup(caseloadType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgWorkSubTypeRecordGroup(final String workType, final String caseloadType) {
		return ocdsmemoRepository.rgWorkSubTypeRecordGroup(workType, caseloadType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgSeverityRecordGroup() {
		return ocdsmemoRepository.rgSeverityRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<StaffMembers> rgStaffRecordGroup() {
		final List<StaffMembers> returnList = ocdsmemoRepository.rgStaffRecordGroup();
		for (final StaffMembers staffMembers : returnList) {
			staffMembers.setCode(staffMembers.getStaffId());
			staffMembers.setDescription(staffMembers.getStaffName());
		}

		return returnList;

	}

	@Override
	public String staffMemoComitt(final StaffMembers object) {
		return ocdsmemoRepository.staffMemoComitt(object);
	}

	@Override
	public String getStaffMessage(final StaffMembers object) {
		final String code = ocdsmemoRepository.getStaffMessage(object);
		return code;
	}

}
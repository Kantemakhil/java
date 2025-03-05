package net.syscon.s4.inst.offenderissuestracking.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.offenderissuestracking.OiustinvRepository;
import net.syscon.s4.inst.offenderissuestracking.OiustinvService;
import net.syscon.s4.inst.offenderissuestracking.beans.OffenderGrievStaffs;
import net.syscon.s4.inst.offenderissuestracking.beans.OffenderGrievStaffsCommitBean;

/**
 * Class OiustinvServiceImpl
 */
@Service
public class OiustinvServiceImpl extends BaseBusiness implements OiustinvService {

	@Autowired
	private OiustinvRepository oiustinvRepository;

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<StaffMembers> offenderGrievStaffsPostQuery(final StaffMembers paramBean) {
		return oiustinvRepository.offenderGrievStaffsPostQuery(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OmsModules> createFormGlobals(final OmsModules paramBean) {
		return oiustinvRepository.createFormGlobals(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderGrievStaffs> offenderGrievStaffsExecuteQuery(final OffenderGrievStaffs searchRecord) {
		List<OffenderGrievStaffs> offenderGrievStaffsList = new ArrayList<>();
		StaffMembers staffMembers;
		offenderGrievStaffsList = oiustinvRepository.offenderGrievStaffsExecuteQuery(searchRecord);
		if (offenderGrievStaffsList != null && !offenderGrievStaffsList.isEmpty()) {
			for (final OffenderGrievStaffs offenderGrievStaffs : offenderGrievStaffsList) {
				if (Optional.ofNullable(offenderGrievStaffs).isPresent()) {
					staffMembers = new StaffMembers();
					staffMembers.setStaffId(offenderGrievStaffs.getStaffId());
					final List<StaffMembers> staffMembersList = offenderGrievStaffsPostQuery(staffMembers);
					if (staffMembersList != null && !staffMembersList.isEmpty()) {
						offenderGrievStaffs.setFirstName(staffMembersList.get(0).getFirstName());
						offenderGrievStaffs.setMiddleName(staffMembersList.get(0).getMiddleName());
						offenderGrievStaffs.setLastName(staffMembersList.get(0).getLastName());
					}
				}

			}
		}
		return offenderGrievStaffsList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFFENDER_GRIEV_STAFFS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<OffenderGrievStaffs> offenderGrievStaffsCommit(final OffenderGrievStaffsCommitBean commitBean) {
		final List<OffenderGrievStaffs> liReturnData = new ArrayList<>();
		final OffenderGrievStaffs offenderGrievStaffs = new OffenderGrievStaffs();
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for(OffenderGrievStaffs bean :commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oiustinvRepository.offenderGrievStaffsInsertOffenderGrievStaffs(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for(OffenderGrievStaffs bean :commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oiustinvRepository.offenderGrievStaffsUpdateOffenderGrievStaffs(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			commitBean.getDeleteList().stream().forEach(e -> e.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oiustinvRepository.offenderGrievStaffsDeleteOffenderGrievStaffs(commitBean.getDeleteList());
		}
		offenderGrievStaffs.setReturnValue(liReturn);
		liReturnData.add(offenderGrievStaffs);
		return liReturnData;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgStaffRecordGroup() {
		return oiustinvRepository.rgStaffRecordGroup();

	}

}

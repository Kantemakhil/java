package net.syscon.s4.inst.institutionalactivities.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.CourseActivityParties;
import net.syscon.s4.im.beans.CourseActivityPartiesCommitBean;
import net.syscon.s4.inst.institutionalactivities.OcmssvctRepository;
import net.syscon.s4.inst.institutionalactivities.OcmssvctService;

/**
 * Class OcmssvctServiceImpl
 */
@Service
public class OcmssvctServiceImpl extends BaseBusiness implements OcmssvctService {

	@Autowired
	private OcmssvctRepository ocmssvctRepository;


	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<CourseActivityParties> crsActPtyExecuteQuery(final CourseActivityParties searchRecord) {
		List<CourseActivityParties> recordList = new ArrayList<CourseActivityParties>();
		List<StaffMembers> staffMemeber = new ArrayList<>();
		recordList = ocmssvctRepository.crsActPtyExecuteQuery(searchRecord);
		for (final CourseActivityParties object : recordList) {
			staffMemeber = ocmssvctRepository.getStaffMemberRecord(object.getStaffId());
			if(staffMemeber!=null && !staffMemeber.isEmpty()){
				object.setNbtLastName(staffMemeber.get(0).getLastName());
				object.setNbtFirstName(staffMemeber.get(0).getFirstName());
			}
		}
		return recordList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCRS_ACT_PTY
	 *
	 * @throws SQLException
	 */
	@Transactional
	public List<CourseActivityParties> crsActPtyCommit(final CourseActivityPartiesCommitBean commitBean) {
		final List<CourseActivityParties> returnList = new ArrayList<>();
		 CourseActivityParties returnObject = new CourseActivityParties();
		final List<CourseActivityParties> recordSaveLit = new ArrayList<>();
		int liReturn = 0;
		 if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (int i = 0; i < commitBean.getInsertList().size(); i++) {
				List<StaffMembers> staffMemeber = new ArrayList<>();
				final CourseActivityParties courseObject = commitBean.getInsertList().get(i);
				courseObject.setCrsActyPartyId(ocmssvctRepository.getCrtPartyId());
				courseObject.setCreateUserId(commitBean.getCreateUserId());
				staffMemeber = ocmssvctRepository.getStaffMemberRecord(courseObject.getStaffId());
				if(staffMemeber!=null && !staffMemeber.isEmpty()){
					courseObject.setFirstName(staffMemeber.get(0).getLastName());
					courseObject.setLastName(staffMemeber.get(0).getFirstName());
				}
				recordSaveLit.add(courseObject);	
			}
			liReturn = ocmssvctRepository.crsActPtyInsertCourseActivityParties(recordSaveLit);
			returnObject.setReturnValue(liReturn);
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (CourseActivityParties bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocmssvctRepository.crsActPtyUpdateCourseActivityParties(commitBean.getUpdateList());
			returnObject.setReturnValue(liReturn);
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			returnObject = ocmssvctRepository.crsActPtyDeleteCourseActivityParties(commitBean.getDeleteList());
		}
		returnList.add(returnObject);
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgStaffNameInstRecordGroup(final String caseloadType,final String providerPartyCode) {
		Long listSeq = 1l;
		List<ReferenceCodes> returnList =  ocmssvctRepository.rgStaffNameInstRecordGroup(caseloadType, providerPartyCode);
		for (ReferenceCodes result : returnList) {
			result.setListSeq(BigDecimal.valueOf(listSeq++));
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgStaffNameCommRecordGroup(final Long providerId) {
		Long listSeq = 1l;
		List<ReferenceCodes> returnList =  ocmssvctRepository.rgStaffNameCommRecordGroup(providerId);
		for (ReferenceCodes result : returnList) {
			result.setListSeq(BigDecimal.valueOf(listSeq++));
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<TeamMembers> rgTeamMembersRecordGroup() {
		return ocmssvctRepository.rgTeamMembersRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgStaffNameInstProgRecordGroup(final String providerPartyCode,final Long programId) {
		Long listSeq = 1l;
		List<ReferenceCodes> returnList = ocmssvctRepository.rgStaffNameInstProgRecordGroup(providerPartyCode, programId);
		for (ReferenceCodes result : returnList) {
			result.setListSeq(BigDecimal.valueOf(listSeq++));
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgStaffNameCommProgRecordGroup(final Long providerPartyId, final Long programId) {
		Long listSeq = 1l;
		List<ReferenceCodes> returnList =  ocmssvctRepository.rgStaffNameCommProgRecordGroup(providerPartyId, programId);
		for (ReferenceCodes result : returnList) {
			result.setListSeq(BigDecimal.valueOf(listSeq++));
		}
		return returnList;
	}

	@Override
	public Object crsActPtyPreInsert() {
		return ocmssvctRepository.crsActPtyPreInsert();
	}

	@Override
	public Object extConPreInsert() {
		return ocmssvctRepository.extConPreInsert();
	}

	public List<CourseActivityParties> extConExecuteQuery(final CourseActivityParties searchRecord) {
		return ocmssvctRepository.extConExecuteQuery(searchRecord);

	}
}
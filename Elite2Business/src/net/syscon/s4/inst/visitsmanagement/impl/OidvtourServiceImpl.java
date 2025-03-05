package net.syscon.s4.inst.visitsmanagement.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.visitsmanagement.OidvtourRepository;
import net.syscon.s4.inst.visitsmanagement.OidvtourService;
import net.syscon.s4.inst.visitsmanagement.beans.VisitingGroups;
import net.syscon.s4.inst.visitsmanagement.beans.VisitingGroupsCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.VisitingMembers;
import net.syscon.s4.inst.visitsmanagement.beans.VisitingMembersCommitBean;

/**
 * Class OidvtourServiceImpl
 */
@Service
public class OidvtourServiceImpl extends BaseBusiness implements OidvtourService {

	@Autowired
	private OidvtourRepository oidvtourRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<VisitingGroups> visitingGroupsExecuteQuery(final VisitingGroups searchRecord) {

		final List<VisitingGroups> returnList = oidvtourRepository.visitingGroupsExecuteQuery(searchRecord);
		for (final VisitingGroups obj : returnList) {
			if (obj.getGroupPurpose() != null) {
				obj.setGroupPurposeTemp(obj.getGroupPurpose());
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstVISITING_GROUPS
	 *
	 * @
	 */
	@Transactional
	public Integer visitingGroupsCommit(final VisitingGroupsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(data -> data.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = visitingGroupsInsertVisitingGroups(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(data -> data.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = visitingGroupsUpdateVisitingGroups(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(data -> data.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = visitingGroupsDeleteVisitingGroups(commitBean.getDeleteList());
		}
		return liReturn;
	}

	public Integer visitingGroupsInsertVisitingGroups(final List<VisitingGroups> insertVisitGroups) {
		final Integer listReturn;
		for (final VisitingGroups obj : insertVisitGroups) {
			if (obj.getAgyLocId() != null) {
				final Integer groupId = oidvtourRepository.visitingGroupsPreInsert();
				obj.setGroupId(groupId);
			}
			/*
			 * if (obj.getApprovedbyIdTemp() != null &&
			 * !"".equals(obj.getApprovedbyIdTemp())) {
			 * obj.setApprovedById(Integer.parseInt(obj.getApprovedbyIdTemp())); }
			 */
			if (obj.getGroupPurposeTemp() != null && !"".equals(obj.getGroupPurposeTemp())) {
				obj.setGroupPurpose(obj.getGroupPurposeTemp());
			}
			/*
			 * if (obj.getEscortedByIdTemp() != null &&
			 * !"".equals(obj.getEscortedByIdTemp())) {
			 * obj.setEscortedById(Integer.parseInt(obj.getEscortedByIdTemp())); }
			 */
		}
		listReturn = oidvtourRepository.visitingGroupsInsertVisitingGroups(insertVisitGroups);
		return listReturn;

	}

	public Integer visitingGroupsUpdateVisitingGroups(final List<VisitingGroups> updateVisitingGroups) {
		for (final VisitingGroups obj : updateVisitingGroups) {
			/*
			 * if(obj.getApprovedbyIdTemp() !=null){
			 * obj.setApprovedById(Integer.parseInt(obj.getApprovedbyIdTemp())); }
			 */
			if (obj.getGroupPurposeTemp() != null) {
				obj.setGroupPurpose(obj.getGroupPurposeTemp());
			}
			/*
			 * if (obj.getEscortedByIdTemp() != null) {
			 * obj.setEscortedById(Integer.parseInt(obj.getEscortedByIdTemp())); }
			 */

		}
		return oidvtourRepository.visitingGroupsUpdateVisitingGroups(updateVisitingGroups);
	}

	public Integer visitingGroupsDeleteVisitingGroups(final List<VisitingGroups> updateVisitingGroups) {
		return oidvtourRepository.visitingGroupsDeleteVisitingGroups(updateVisitingGroups);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<VisitingMembers> visitingMembersExecuteQuery(final VisitingMembers searchRecord) {
		return oidvtourRepository.visitingMembersExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstVISITING_MEMBERS
	 *
	 * @
	 */
	@Transactional
	public Integer visitingMembersCommit(final VisitingMembersCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(data -> data.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = visitingMembersInsertVisitingMembers(commitBean.getInsertList());

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(data -> data.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = visitingMembersUpdateVisitingMembers(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(data -> data.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = visitingMembersDeleteVisitingMembers(commitBean.getDeleteList());
		}
		return liReturn;
	}

	public Integer visitingMembersInsertVisitingMembers(final List<VisitingMembers> insertvisitingMembers) {
		Integer memberSeq = 0;
		Boolean checkFlag = true;
		for (final VisitingMembers visitingMembers : insertvisitingMembers) {
			if (checkFlag) {
				checkFlag = false;
				memberSeq = oidvtourRepository.getMemberSeq(visitingMembers.getGroupId());
			}
			visitingMembers.setMemberSeq(memberSeq);
			memberSeq = memberSeq + 1;
		}
		return oidvtourRepository.visitingMembersInsertVisitingMembers(insertvisitingMembers);
	}

	public Integer visitingMembersUpdateVisitingMembers(final List<VisitingMembers> insertvisitingMembers) {
		return oidvtourRepository.visitingMembersUpdateVisitingMembers(insertvisitingMembers);
	}

	public Integer visitingMembersDeleteVisitingMembers(final List<VisitingMembers> insertvisitingMembers) {
		return oidvtourRepository.visitingMembersDeleteVisitingMembers(insertvisitingMembers);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgGroupPurposRecordGroup() {
		final List<ReferenceCodes> returnList = oidvtourRepository.rgGroupPurposRecordGroup();
		returnList.forEach(result -> {
			result.setCode(result.getCode());
			result.setDescription(result.getDescription());
		});
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgIdTypeRecordGroup() {
		return oidvtourRepository.rgIdTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<StaffMembers> rgStaffMembersRecordGroup() {
		final List<StaffMembers> returnList = oidvtourRepository.rgStaffMembersRecordGroup();
		for (StaffMembers staffMembers : returnList) {
			staffMembers.setCode(staffMembers.getStaffId());
			staffMembers.setDescription(staffMembers.getStaffName());
			if(staffMembers.getStatus()!=null && (staffMembers.getStatus().equals("INACT") || staffMembers.getStatus().equals("SUS"))) {
				staffMembers.setCanDisplay(false);
			}
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<AgencyLocations> rgAgencyLocationsRecordGroup(final String caseloadId, final String caseloadType) {
		final List<AgencyLocations> returnList = oidvtourRepository.rgAgencyLocationsRecordGroup(caseloadId,
				caseloadType);
		returnList.forEach(result -> {
			result.setCode(result.getAgyLocId());
			result.setDescription(result.getDescription());

		});
		if(Optional.ofNullable(returnList).isPresent()) {
			returnList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return returnList;
	}

}
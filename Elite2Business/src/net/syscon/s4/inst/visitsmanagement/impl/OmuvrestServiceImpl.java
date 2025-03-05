package net.syscon.s4.inst.visitsmanagement.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.VisitorRestrictions;
import net.syscon.s4.im.beans.VisitorRestrictionsCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.booking.beans.PersonsCommitBean;
import net.syscon.s4.inst.visitsmanagement.OmuvrestRepository;
import net.syscon.s4.inst.visitsmanagement.OmuvrestService;

/**
 * Class OmuvrestServiceImpl
 */
@Service
public class OmuvrestServiceImpl extends BaseBusiness implements OmuvrestService {

	@Autowired
	private OmuvrestRepository omuvrestRepository;

	/**
	 * Creates new OmuvrestServiceImpl class Object
	 */
	public OmuvrestServiceImpl() {
		// OmuvrestServiceImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<Persons> perExecuteQuery(final Persons searchRecord) {
		return omuvrestRepository.perExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param CommitBean
	 */
	@Transactional
	public Integer perCommit(final PersonsCommitBean CommitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<VisitorRestrictions> visrRestExecuteQuery(final VisitorRestrictions searchRecord) {
		List<VisitorRestrictions> returnList = new ArrayList<>();
		returnList = omuvrestRepository.visrRestExecuteQuery(searchRecord);
		for (final VisitorRestrictions visitorRestrictions : returnList) {
			if (visitorRestrictions.getEnteredStaffId() != null) {
				StaffMembers staffMembers = new StaffMembers();
				staffMembers.setStaffId(visitorRestrictions.getEnteredStaffId());
				String description = omuvrestRepository.desriptionPostInsert(staffMembers);
				visitorRestrictions.setDescription(description);
			}
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 */
	@Transactional
	public Integer visrRestCommit(final VisitorRestrictionsCommitBean commitBean) {
		int liReturn = 0;
		Integer grievancesId = null;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			List<VisitorRestrictions> recordSavingList = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (int i = 0; i < commitBean.getInsertList().size(); i++) {
					recordSavingList = new ArrayList<>();
					final VisitorRestrictions offenderPropertyItemObj = commitBean.getInsertList().get(i);
					grievancesId = omuvrestRepository.visitorRestrictionsPreInsert();
					offenderPropertyItemObj.setVisitorRestrictionId(grievancesId);
					if (offenderPropertyItemObj.getEnteredStaffId() == null) {
						Integer enteredStaffId = omuvrestRepository.enteredStaffIdPreInsert(commitBean.getCreateUserId());
						if (enteredStaffId > 0) {
							offenderPropertyItemObj.setEnteredStaffId(enteredStaffId);
						}
					}
					recordSavingList.add(offenderPropertyItemObj);
					recordSavingList.forEach(bean->bean.setCreateUserId(commitBean.getCreateUserId()));
					liReturn = omuvrestRepository.visrRestInsertVisitorRestrictions(recordSavingList);
				}
			}
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean->bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = omuvrestRepository.visrRestUpdateVisitorRestrictions(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgVisrRestVisitRestrictiRecordGroup() {
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		returnList = omuvrestRepository.rgVisrRestVisitRestrictiRecordGroup();
		if(returnList != null && !returnList.isEmpty()) {
			returnList.forEach(ele -> {
				if(!ApplicationConstants.YES.equalsIgnoreCase(ele.getActiveFlag())) {
					ele.setCanDisplay(false);
				}
			});
		}
		return returnList ;

	}

}
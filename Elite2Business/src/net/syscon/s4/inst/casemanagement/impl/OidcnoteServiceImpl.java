package net.syscon.s4.inst.casemanagement.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.CaseNotePermissions;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderCaseNotesCommitBean;
import net.syscon.s4.inst.casemanagement.OidcnoteRepository;
import net.syscon.s4.inst.casemanagement.OidcnoteService;

/**
 * Class OidcnoteServiceImpl
 */
@Service
public class OidcnoteServiceImpl extends BaseBusiness implements OidcnoteService {

	@Autowired
	private OidcnoteRepository oidcnoteRepository;

	/**
	 * Fetch the records from database table
	 */
	public List<OffenderCaseNotes> offNotesExecuteQuery(final OffenderCaseNotes searchRecord) {
		List<OffenderCaseNotes> returnList = new ArrayList<OffenderCaseNotes>();
		List<OffenderCaseNotes> finalReturnList = new ArrayList<OffenderCaseNotes>();
		returnList = oidcnoteRepository.offNotesExecuteQuery(searchRecord);
		StaffMembers staffObj = new StaffMembers();
		for (final OffenderCaseNotes obj : returnList) {
			if (obj.getOffenderBookId() != null) {
				staffObj = oidcnoteRepository.getStaffnameQuery(searchRecord.getCreateUserId());
				obj.setStaffIdTemp(staffObj.getStaffId() + "");
			}
			if (obj.getCaseNoteType() != null) {
				String moduleName = oidcnoteRepository.getmoduleName(obj.getCaseNoteType(), obj.getCaseNoteSubType());
				obj.setModuleName(moduleName);
			}
			if ("Y".equals(obj.getViewFlag())) {
				finalReturnList.add(obj);
			}

		}
		return finalReturnList;

	}

	public Integer offNotesInsertOffenderCaseNotes(final List<OffenderCaseNotes> lstOffenderCaseNotes) {
		Integer caseNoteId = 0;
		for (final OffenderCaseNotes obj : lstOffenderCaseNotes) {
			caseNoteId = oidcnoteRepository.getcaseNoteId();
			obj.setCaseNoteId(caseNoteId);
		}
		return oidcnoteRepository.offNotesInsertOffenderCaseNotes(lstOffenderCaseNotes);

	}

	public Integer offNotesUpdateOffenderCaseNotes(final List<OffenderCaseNotes> lstOffenderCaseNotes) {

		return oidcnoteRepository.offNotesUpdateOffenderCaseNotes(lstOffenderCaseNotes);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_NOTES
	 *
	 * @
	 */
	@Transactional
	public Integer offNotesCommit(final OffenderCaseNotesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bo -> {
				bo.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = offNotesInsertOffenderCaseNotes(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = offNotesUpdateOffenderCaseNotes(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oidcnoteRepository.offNotesDeleteOffenderCaseNotes(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgnoteSourceRecordGroup() {
		return oidcnoteRepository.rgnoteSourceRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgCasenoteTypeRecordGroup(final String caseloadType, final String userName) {
		List<ReferenceCodes> lovList =oidcnoteRepository.rgCasenoteTypeRecordGroup(caseloadType, userName);
		List<CaseNotePermissions>  caseNoteList=oidcnoteRepository.getCaseNoteLovs(userName);
		List<ReferenceCodes> newList = new ArrayList<ReferenceCodes>();
		for (ReferenceCodes obj : lovList) {
			for (CaseNotePermissions obj1:caseNoteList) {
				if(obj1.getWorkId().compareTo(new BigDecimal(obj.getWorkId()))==0) {
					if(obj1.getCreateFlag().equalsIgnoreCase(ApplicationConstants.YFLAG) && obj.getActiveFlag().equalsIgnoreCase(ApplicationConstants.YFLAG) && (caseloadType.equalsIgnoreCase(obj.getState()) || "BOTH".equalsIgnoreCase(obj.getState()))) {
						obj.setCanDisplay(true);
					}else if(obj.getCanDisplay()== null || obj.getCanDisplay()== false) {
						obj.setCanDisplay(false);
					}
				}
			}
		}
	   
		List<ReferenceCodes> collect = lovList.stream().filter(e->e.getCanDisplay()).collect(Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(ReferenceCodes::getDescription)))).stream().collect(Collectors.toList());
		List<ReferenceCodes> collect2 = lovList.stream().filter(e->!e.getCanDisplay()).collect(Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(ReferenceCodes::getDescription)))).stream().collect(Collectors.toList());
		
		newList.addAll(collect);
		newList.addAll(collect2);
		return newList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */

	public List<ReferenceCodes> rgCasenoteSubtypeRecordGroup(final String caseNoteType, final String userName,String caseloadType) {
		List<ReferenceCodes> lovList=oidcnoteRepository.rgCasenoteSubtypeRecordGroup(caseNoteType, userName,caseloadType);
	List<CaseNotePermissions>  caseNoteList=oidcnoteRepository.getCaseNoteLovs(userName);
	
	for (ReferenceCodes obj : lovList) {
		for (CaseNotePermissions obj1:caseNoteList) {
			if(obj1.getWorkId().compareTo(new BigDecimal(obj.getWorkId()))==0) {
				if(obj1.getCreateFlag().equalsIgnoreCase("Y") && obj.getActiveFlag().equalsIgnoreCase(ApplicationConstants.YFLAG) && (caseloadType.equalsIgnoreCase(obj.getState()) || "BOTH".equalsIgnoreCase(obj.getState()))) {
					obj.setCanDisplay(true);
				}else if(obj.getCanDisplay()== null || obj.getCanDisplay()== false) {
					obj.setCanDisplay(false);
				}
			}
		}
	}
	
		return lovList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<StaffMembers> rgStaffnameRecordGroup(String userName) {
		final List<StaffMembers> returnList = oidcnoteRepository.rgStaffnameRecordGroup(userName);
		for (final StaffMembers staffMembers : returnList) {
			staffMembers.setCode(staffMembers.getStaffId());
			staffMembers.setDescription(staffMembers.getStaffName());
		}

		return returnList;
	}

	@Override
	public String checkCasenoteSubType(final String caseNoteType, final String caseNoteCode, final String userName) {
		return oidcnoteRepository.checkCasenoteSubType(caseNoteType, caseNoteCode, userName);

	}
}

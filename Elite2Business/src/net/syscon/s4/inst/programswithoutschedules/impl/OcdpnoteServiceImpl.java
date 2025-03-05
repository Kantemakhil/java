package net.syscon.s4.inst.programswithoutschedules.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderCaseNotesCommitBean;
import net.syscon.s4.inst.programswithoutschedules.OcdpnoteRepository;
import net.syscon.s4.inst.programswithoutschedules.OcdpnoteService;
import net.syscon.s4.pkgs.tag_contact_log.TagContactLogService;

@Service
public class OcdpnoteServiceImpl extends BaseBusiness implements OcdpnoteService {

	@Autowired
	private OcdpnoteRepository ocdpnoteRepository;
	
	@Autowired
	private TagContactLogService tagContactLogService;
	
	private static Logger logger = LogManager.getLogger(OcdpnoteServiceImpl.class);

	/**
	 * Creates new OcdpnoteServiceImpl class Object
	 */
	public OcdpnoteServiceImpl() {
		// OcdpnoteServiceImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<OffenderCaseNotes> offenderCaseNotesExecuteQuery(final OffenderCaseNotes searchRecord) {
		return ocdpnoteRepository.offenderCaseNotesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFFENDER_CASE_NOTES
	 *
	 * 
	 */
	@Transactional
	public Integer offenderCaseNotesCommit(final OffenderCaseNotesCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			commitBean.getInsertList().forEach(data->data.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = ocdpnoteRepository.offenderCaseNotesInsertOffenderCaseNotes(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			commitBean.getUpdateList().forEach(data->data.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocdpnoteRepository.offenderCaseNotesUpdateOffenderCaseNotes(commitBean.getUpdateList());
		}


		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgSubTypeRecordGroup() {
		return ocdpnoteRepository.rgSubTypeRecordGroup();

	}

	@Override
	public List<ReferenceCodes> ocdpnoteGlobalUserAndCaseloadtype(String userName) {
		return ocdpnoteRepository.ocdpnoteGlobalUserAndCaseloadtype(userName);
	}

	@Override
	public List<ReferenceCodes> ocdpnoteStaffMemberName() {
		return ocdpnoteRepository.ocdpnoteStaffMemberName();
	}

	@Override
	public OffenderCaseNotes getModuleName(final OffenderCaseNotes objOffenderCaseNotes) {
		String moduleName=null;
		try {
			moduleName=tagContactLogService.getModuleName(objOffenderCaseNotes.getCaseNoteType(), objOffenderCaseNotes.getCaseNoteSubType());
			//moduleName=ocdpnoteRepository.getModuleName(objOffenderCaseNotes);
		} catch (Exception e) {
			logger.error(e);
		}
		if(moduleName!=null) {
			objOffenderCaseNotes.setModuleName(moduleName);
		}
		
		return objOffenderCaseNotes;
	}

}
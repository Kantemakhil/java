package net.syscon.s4.inst.securitythreatgroups.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.StgCaseNotes;
import net.syscon.s4.im.beans.StgCaseNotesCommitBean;
import net.syscon.s4.inst.securitythreatgroups.OidstgcnRepository;
import net.syscon.s4.inst.securitythreatgroups.OidstgcnService;

/**
 * Class OidstgcnServiceImpl
 * 
 */
@Service
public class OidstgcnServiceImpl extends BaseBusiness implements OidstgcnService {

	@Autowired
	private OidstgcnRepository oidstgcnRepository;

	/**
	 * Creates new OidstgcnServiceImpl class Object
	 */
	public OidstgcnServiceImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<StgCaseNotes> stgCaseNotesExecuteQuery(final StgCaseNotes searchRecord) {
		return oidstgcnRepository.stgCaseNotesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTG_CASE_NOTES
	 */
	@Transactional
	public Integer stgCaseNotesCommit(final StgCaseNotesCommitBean commitBean) {
		int liReturn = 0;
		Long stgId = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final StgCaseNotes stgCaseNotes : commitBean.getInsertList()) {
				stgId = stgCaseNotes.getStgId();
			}
			List<StgCaseNotes> saveObj = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (int i = 0; i < commitBean.getInsertList().size(); i++) {
					saveObj = new ArrayList<>();
					final Long identifierSeq = oidstgcnRepository.identifierSeqData(stgId);
					final StgCaseNotes offenderAlertObj = commitBean.getInsertList().get(i);
					offenderAlertObj.setNoteSeq(identifierSeq);
					offenderAlertObj.setCreateUserId(commitBean.getCreateUserId());
					saveObj.add(offenderAlertObj);
					liReturn = oidstgcnRepository.stgCaseNotesInsertStgCaseNotes(saveObj);
				}
			}
		}

		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean -> bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oidstgcnRepository.stgCaseNotesUpdateStgCaseNotes(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bean -> bean.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oidstgcnRepository.stgCaseNotesDeleteStgCaseNotes(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgNoteTypeRecordGroup() {
		return oidstgcnRepository.rgNoteTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgNoteReasonRecordGroup() {
		return oidstgcnRepository.rgNoteReasonRecordGroup();

	}

	@Override
	public List<String> getParentCodes() {
		return oidstgcnRepository.getParentCodes();
	}

}
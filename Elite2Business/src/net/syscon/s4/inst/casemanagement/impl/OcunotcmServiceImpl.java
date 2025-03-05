package net.syscon.s4.inst.casemanagement.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffCaseNoteRecipients;
import net.syscon.s4.im.beans.OffCaseNoteRecipientsCommitBean;
import net.syscon.s4.inst.casemanagement.OcunotcmRepository;
import net.syscon.s4.inst.casemanagement.OcunotcmService;

/**
 * Class OcunotcmServiceImpl
 */
@Service
public class OcunotcmServiceImpl extends BaseBusiness implements OcunotcmService {

	@Autowired
	private OcunotcmRepository ocunotcmRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffCaseNoteRecipients> offCaseNrExecuteQuery(final OffCaseNoteRecipients searchRecord) {
		String teamIdDesc = "";
		final List<OffCaseNoteRecipients> returnList = ocunotcmRepository.offCaseNrExecuteQuery(searchRecord);
		if (returnList.size() > 0) {
			for (final OffCaseNoteRecipients obj : returnList) {
				if (obj.getTeamId() != null) {
					teamIdDesc = ocunotcmRepository.offCasegetteamIdDesc(obj.getTeamId());
					obj.setTeamIdDesc(teamIdDesc);
				}
				if (obj.getStaffId() != null) {
					obj.setStaffIdDesc(obj.getStaffId().toString());
				}
			}

		}
		return returnList;

	}

	public Integer offCaseNrInsertOffCaseNoteRecipients(
			final List<OffCaseNoteRecipients> lstOffenderCaseNoteRecipients) {
		Integer caseNoteRecipientId = 0;
		Integer teamId = 0;
		for (final OffCaseNoteRecipients obj : lstOffenderCaseNoteRecipients) {
			caseNoteRecipientId = ocunotcmRepository.getcaseRecipientId();
			teamId = ocunotcmRepository.getteamId(obj.getTeamIdDesc());
			obj.setOffCaseNoteRecipientId(caseNoteRecipientId);
			if (obj.getStaffIdDesc() != null) {
				obj.setStaffId(Integer.parseInt(obj.getStaffIdDesc()));
			}
			obj.setTeamId(teamId);
		}
		return ocunotcmRepository.offCaseNrInsertOffCaseNoteRecipients(lstOffenderCaseNoteRecipients);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_CASE_NR
	 *
	 * @
	 */
	@Transactional
	public Integer offCaseNrCommit(final OffCaseNoteRecipientsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bo->bo.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = offCaseNrInsertOffCaseNoteRecipients(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bo->bo.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocunotcmRepository.offCaseNrUpdateOffCaseNoteRecipients(commitBean.getUpdateList());
		}

		
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<StaffMembers> rgStaffDtlsRecordGroup(final String teamIdDesc) {
		final List<StaffMembers> resultList = ocunotcmRepository.rgStaffDtlsRecordGroup(teamIdDesc);
		final SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
		resultList.forEach(result -> {
			result.setCode(result.getStaffId());
			result.setDescription(result.getLastName());
			if (result.getFromDate() != null) {
				result.setTitle(formatter.format(result.getFromDate()).toUpperCase());
			}
		});
		return resultList;

	}

	@Override
	public Object offCaseNrPreInsert() {

		return ocunotcmRepository.offCaseNrPreInsert();
	}

}
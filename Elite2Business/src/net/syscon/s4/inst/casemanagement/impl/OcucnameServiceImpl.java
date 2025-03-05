package net.syscon.s4.inst.casemanagement.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderAlerts;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderCaseNotesCommitBean;
import net.syscon.s4.inst.casemanagement.OcucnameRepository;
import net.syscon.s4.inst.casemanagement.OcucnameService;

/**
 * Class OcucnameServiceImpl
 */
@Service
public class OcucnameServiceImpl extends BaseBusiness implements OcucnameService {

	@Autowired
	private OcucnameRepository ocucnameRepository;

	/**
	 * Creates new OcucnameServiceImpl class Object
	 */
	public OcucnameServiceImpl() {
		// OcucnameServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffenderCaseNotes> ocucnameWhenNewFormInstance(final OffenderCaseNotes paramBean) {
		final List<OffenderCaseNotes> returnList = new ArrayList<>();
		final OffenderCaseNotes returnObj = ocucnameRepository
				.ocucnameWhenNewFormInstancewhenNewFormInstance(paramBean);
		returnList.add(returnObj);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffenderCaseNotes> offCaseNoteExecuteQuery(final OffenderCaseNotes searchRecord) {
		return ocucnameRepository.offCaseNoteExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_CASE_NOTE
	 *
	 * @
	 */
	@Transactional
	public Integer offCaseNoteCommit(final OffenderCaseNotesCommitBean commitBean) {
		int liReturn = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderCaseNotes bean : commitBean.getUpdateList()) {				
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = ocucnameRepository.offNotesUpdateOffenderCaseNotes(commitBean.getUpdateList());
		}
		return liReturn;
	}

}
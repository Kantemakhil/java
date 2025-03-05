package net.syscon.s4.sa.audit.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.sa.audit.JournalTableView;
import net.syscon.s4.sa.audit.JournalTableViewCommitBean;
import net.syscon.s4.sa.audit.OmsaljntRepository;
import net.syscon.s4.sa.audit.OmsaljntService;

/**
 * Class OmsaljntServiceImpl
 */
@Service
public class OmsaljntServiceImpl extends BaseBusiness implements OmsaljntService {

	@Autowired
	private OmsaljntRepository omsaljntRepository;


	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<JournalTableView> journalTableViewExecuteQuery(final JournalTableView searchRecord) {
		return omsaljntRepository.journalTableViewExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstJOURNAL_TABLE_VIEW
	 */
	@Transactional
	public Integer journalTableViewCommit(final JournalTableViewCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			liReturn = omsaljntRepository.journalTableViewUpdateJournalTableView(commitBean.getUpdateList());
		}
		return liReturn;
	}

}
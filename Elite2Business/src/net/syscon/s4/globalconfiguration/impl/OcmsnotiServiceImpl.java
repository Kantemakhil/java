package net.syscon.s4.globalconfiguration.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalconfiguration.OcmsnotiRepository;
import net.syscon.s4.globalconfiguration.OcmsnotiService;
import net.syscon.s4.im.beans.SanctionNotices;
import net.syscon.s4.im.beans.SanctionNoticesCommitBean;

/**
 * Class OcmsnotiServiceImpl
 */
@Service
public class OcmsnotiServiceImpl extends BaseBusiness implements OcmsnotiService {

	@Autowired
	private OcmsnotiRepository ocmsnotiRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<SanctionNotices> sanNotExecuteQuery(final SanctionNotices searchRecord) {
		return ocmsnotiRepository.sanNotExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSAN_NOT
	 *
	 * @throws SQLException
	 */
	@Transactional
	public SanctionNotices sanNotCommit(final SanctionNoticesCommitBean commitBean) {
		int liReturn = 0;
		final SanctionNotices returnObj = new SanctionNotices();
		// insertRecords
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			liReturn = ocmsnotiRepository.sanNotInsertSanctionNotices(commitBean.getInsertList());

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			commitBean.getUpdateList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = ocmsnotiRepository.sanNotUpdateSanctionNotices(commitBean.getUpdateList());
		}
		if (liReturn == 1) {
			returnObj.setSealFlag("1");
		} else {
			returnObj.setSealFlag("0");
		}
		return returnObj;
	}

}
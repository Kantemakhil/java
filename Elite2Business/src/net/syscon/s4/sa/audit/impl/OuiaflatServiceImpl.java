package net.syscon.s4.sa.audit.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.sa.audit.OuiaflatRepository;
import net.syscon.s4.sa.audit.OuiaflatService;
import net.syscon.s4.sa.audit.TagLoginAlerts;
import net.syscon.s4.sa.audit.TagLoginAlertscommitBean;

/**
 * Class OuiaflatServiceImpl
 */
@Service
public class OuiaflatServiceImpl extends BaseBusiness implements OuiaflatService {

	@Autowired
	private OuiaflatRepository ouiaflatRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<TagLoginAlerts> loginAlertsBlkExecuteQuery(final TagLoginAlerts searchRecord) {
		return ouiaflatRepository.loginAlertsBlkExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstLOGIN_ALERTS_BLK
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer loginAlertsBlkCommit(final TagLoginAlertscommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;
	}

}
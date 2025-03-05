package net.syscon.s4.cf.offendertransactions.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cf.offendertransactions.OcipphisRepository;
import net.syscon.s4.cf.offendertransactions.OcipphisService;
import net.syscon.s4.cf.offendertransactions.beans.VPaymentPlanHistories;
import net.syscon.s4.genericservices.BaseBusiness;

/**
 * Class OcipphisServiceImpl
 */
@Service
public class OcipphisServiceImpl extends BaseBusiness implements OcipphisService {

	@Autowired
	private OcipphisRepository ocipphisRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VPaymentPlanHistories> vPaymentPlanHistoriesExecuteQuery(final VPaymentPlanHistories searchRecord) {
		return ocipphisRepository.vPaymentPlanHistoriesExecuteQuery(searchRecord);

	}

}
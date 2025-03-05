package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffFeeBillsT1Repository;

/**
 * Class OffFeeBillsT1RepositoryImpl
 *
 */
@Repository
public class OffFeeBillsT1RepositoryImpl extends RepositoryBase implements OffFeeBillsT1Repository {

	private static Logger logger = LogManager.getLogger(OffFeeBillsT1RepositoryImpl.class.getName());
	@Override
	public String offFeeBillsT1(OffFeeBills bean) {
		final String sql = getQuery("OFF_FEE_BILLS_T1_LV_AR_START_DATE");
		String raiseApplicationError = null;
		try {
			raiseApplicationError = namedParameterJdbcTemplate.queryForObject(sql, createParams("lv_bill_date", bean.getBillDate(),"back_bill",bean.getBackBill()),
					String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" offFeeBillsT1 method call"+e);
		}
		return raiseApplicationError;
	}

}

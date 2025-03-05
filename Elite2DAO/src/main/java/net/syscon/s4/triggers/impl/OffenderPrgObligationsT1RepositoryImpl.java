package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.triggers.OffenderPrgObligationsT1Repository;
@Repository
public class OffenderPrgObligationsT1RepositoryImpl extends RepositoryBase implements OffenderPrgObligationsT1Repository{
	
	private static Logger logger = LogManager.getLogger(OffenderPrgObligationsT1RepositoryImpl.class);
	
	@Override
	public Integer insertOffenderPrgObligationHty(OffenderPrgObligations obj) {
		Integer returnData = 0;
		final String sql = getQuery("OFFENDER_PRG_OBLIGATIONS_T1_INSERT_OFFENDER_PRG_OBLIGATION_HTY");
		try {
			returnData = namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("Exception in insertOffenderPrgObligationHty:", e);
		}
		return returnData;
	}

}

package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderExternalMovementsT9Repository;
@Repository
public class OffenderExternalMovementsT9RepositoryImpl extends RepositoryBase implements OffenderExternalMovementsT9Repository{

	private static Logger logger = LogManager.getLogger(OffenderExternalMovementsT9RepositoryImpl.class.getName());
	
	@Override
	public OffenderExternalMovements getOffenderExternalMovements(final Long offenderBookId,Long movementSeq) {
		final String sql = getQuery("GET_OFFENDER_EXTERNAL_MOVEMENTS_NEW");
		OffenderExternalMovements retObj = new OffenderExternalMovements();
		try {
			if(offenderBookId != null && movementSeq != null) {
				retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_BOOK_ID", offenderBookId,"MOVEMENT_SEQ",movementSeq),
						new BeanPropertyRowMapper<OffenderExternalMovements>(OffenderExternalMovements.class));
			}
		} catch (Exception e) {
			logger.error("getOffenderExternalMovements", e);
			return null;
		}
		return retObj;
	}

}

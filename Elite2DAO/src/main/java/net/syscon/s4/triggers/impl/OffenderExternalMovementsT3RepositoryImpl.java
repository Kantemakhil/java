package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderExternalMovementsT3Repository;

@Repository
public class OffenderExternalMovementsT3RepositoryImpl extends RepositoryBase
		implements OffenderExternalMovementsT3Repository {
	Logger logger = LogManager.getLogger(OffenderExternalMovementsT3RepositoryImpl.class);

	@Override
	public OffenderExternalMovements getOffenderExternalMovements(final Long offenderBookId, final Long movementSeq) {
		final String sql = getQuery("OFFENDER_EXTERNAL_MOVEMENTS_T3_OFFENDER_EXTERNAL_MOVEMENTS_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", offenderBookId, "movementSeq", movementSeq),
					new BeanPropertyRowMapper<OffenderExternalMovements>(OffenderExternalMovements.class));
		} catch (final Exception e) {
			logger.error("getOffenderBookings", e);
			return null;
		}
	}

	@Override
	public String getLocationCur(final String agyLocId) {
		final String sql = getQuery("OFFENDER_EXTERNAL_MOVEMENTS_T3_GET_LOCATION_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("agyLocId", agyLocId), String.class);
		} catch (final Exception e) {
			logger.error("getLocationCur", e);
			return null;
		}
	}

	@Override
	public OffenderBookings communityActiveCur(final OffenderExternalMovements obj) {
		final String sql = getQuery("OFFENDER_EXTERNAL_MOVEMENTS_T3_OFFENDER_EXTERNAL_MOVEMENTS_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", obj.getOffenderBookId(),"movementSeq",obj.getMovementSeq()),
					new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
		} catch (final Exception e) {
			logger.error("communityActiveCur", e);
			return null;
		}
	}

}

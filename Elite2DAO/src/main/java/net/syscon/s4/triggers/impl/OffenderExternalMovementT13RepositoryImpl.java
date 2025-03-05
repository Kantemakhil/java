package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderBillingProfiles;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.triggers.OffenderExternalMovementT13Repository;

@Repository
public class OffenderExternalMovementT13RepositoryImpl extends RepositoryBase
		implements OffenderExternalMovementT13Repository {
	Logger logger = LogManager.getLogger(OffenderExternalMovementT13RepositoryImpl.class);

	@Override
	public MovementReasons closeBookingCur(final String movementType, final String movReasonCode) {
		final String sql = getQuery("OFFENDER_EXTERNAL_MOVEMENT_T13_CLOSE_BOOKING_CUR");
		MovementReasons movementReasons = null;
		try {
			if(movementType != null && movReasonCode != null) {
				movementReasons = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("MOVEMENT_TYPE", movementType, "movementReasonCode", movReasonCode),
					new BeanPropertyRowMapper<MovementReasons>(MovementReasons.class));
			}
		} catch (final Exception e) {
			return null;
		}
		return movementReasons;
	}

	@Override
	public Integer update(final OffenderBillingProfiles offeBillProfiles) {
		final String sql = getQuery("OFFENDER_EXTERNAL_MOVEMENT_T13_OFFENDER_BILLING_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(offeBillProfiles));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("update", e);
			return 0;
		}
		if (1 == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

}

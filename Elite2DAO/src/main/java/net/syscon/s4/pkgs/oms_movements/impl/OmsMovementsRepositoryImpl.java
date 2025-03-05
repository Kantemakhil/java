package net.syscon.s4.pkgs.oms_movements.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.pkgs.oms_movements.OmsMovementsRepository;

@Repository
public class OmsMovementsRepositoryImpl extends RepositoryBase implements OmsMovementsRepository {

	private static Logger logger = LogManager.getLogger(OmsMovementsRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> offExtMovntsMapp = new ImmutableMap.Builder<String, FieldMapper>().build();

	@Override
	public String getLastToAgyLocId(final Integer pBookingId) {
		final String sql = getQuery("GET_LAST_TO_AGY_LOC_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_BOOKING_ID", pBookingId), String.class);
	}

	@Override
	public String getLastToCity(final Integer pBookingId) {
		final String sql = getQuery("GET_LAST_TO_CITY");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_BOOKING_ID", pBookingId), String.class);
	}

	@Override
	public String getvDirection(final Integer pBookingId) {
		final String sql = getQuery("GET_V_DIRECTION");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_BOOKING_ID", pBookingId), String.class);
	}

	@Override
	public String getNextDirectionCode(final String vDirection) {
		final String sql = getQuery("GET_NEXT_DIRECTION_CODE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("V_DIRECTION", vDirection), String.class);
	}

	@Override
	public String getLastMovementReasonCode(final Integer pBookingId) {
		final String sql = getQuery("GET_LAST_MOVEMENT_REASON_CODE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_BOOKING_ID", pBookingId), String.class);
	}

	@Override
	public String getLastFromAgyLocId(final Integer pBookingId) {
		final String sql = getQuery("GET_LAST_FROM_AGY_LOC_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_BOOKING_ID", pBookingId), String.class);
	}

	@Override
	public String getLastMovementCode(final Integer offBookId) {
		final String sql = getQuery("GET_LAST_MOVEMENT_CODE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_BOOKING_ID", offBookId), String.class);
	}

	@Override
	public String getLastFromCity(final Integer offBookId) {
		final String sql = getQuery("GET_LAST_FROM_CITY");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_BOOKING_ID", offBookId), String.class);
	}

	@Override
	public Integer checkActiveSentence(final Long offBookId) {
		final String sql = getQuery("CHECK_ACTIVE_SENTENCE");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFENDER_BOOK_ID", offBookId),
					Integer.class);
		} catch (Exception e) {
			logger.error("checkActiveSentence :" + e);
		}
		return count;
	}

	// This PROCEDURE is used to release_date_check data from database
	@Override
	public List<Object[]> checkReleaseDateCur(final Long offenderBookId) {
		final String sql = getQuery("CHECK_RELEASE_DATE_CUR");
		final RowMapper<Object[]> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Object[].class, offExtMovntsMapp);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id", offenderBookId), mRowMapper);
	}

	@Override
	public String getToAddress(final Integer pBookingId) {
		final String sql = getQuery("GET_TO_ADDRESS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFENDER_BOOK_ID", pBookingId),
				String.class);
	}

}
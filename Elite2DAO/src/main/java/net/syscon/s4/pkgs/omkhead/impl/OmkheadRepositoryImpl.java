package net.syscon.s4.pkgs.omkhead.impl;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.omkhead.OmkheadRepository;

@Repository("OmkheadRepositoryImpl_pkg")
public class OmkheadRepositoryImpl extends RepositoryBase implements OmkheadRepository {


	@Override
	public String inOutStatus(final Long offBookId) {
		final String sql = getQuery("IN_OUT_STATUS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_book_id", offBookId), String.class);
	}

	@Override
	public Integer offenderBookingsUpdateOut(final Long offBookId, final String userName) {
		final String sql = getQuery("OFFENDER_BOOKINGS_UPDATE_OUT");
		return namedParameterJdbcTemplate.update(sql, createParams("p_book_id", offBookId, "modifyUserId", userName));
	}

	@Override
	public Integer offenderBookingsUpdateIn(final Long offBookId, final String userName) {
		final String sql = getQuery("OFFENDER_BOOKINGS_UPDATE_IN");
		return namedParameterJdbcTemplate.update(sql, createParams("p_book_id", offBookId, "modifyUserId", userName));
	}
}
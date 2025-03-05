package net.syscon.s4.triggers.impl;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderBookingsT4Repository;
@Repository
public class OffenderBookingsT4RepositoryImpl extends RepositoryBase implements OffenderBookingsT4Repository {

	@Override
	public Integer offenderBookingsT4Insert(final Long offenderBookId,String userId) {
		final String sql=getQuery("OFFENDER_BOOKING_DETAILS_INSERT");
		return namedParameterJdbcTemplate.update(sql, createParams("Offender_Book_ID",offenderBookId,"createUserId",userId));
	}
}

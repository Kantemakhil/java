package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.AddressesT1Repository;

@Repository
public class AddressesT1RepositoryImpl extends RepositoryBase implements AddressesT1Repository {

	private static final String OWNER_ID = "ownerId";

	@Override
	public Integer offendersRecord(final Long ownerId) {
		final String sql = getQuery("ADDRESES_T1_OFFENDERS_RECORD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(OWNER_ID, ownerId), Integer.class);
	}

	@Override
	public Integer personRecord(final Long ownerId) {
		final String sql = getQuery("ADDRESES_T1_PERSON_RECORD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(OWNER_ID, ownerId), Integer.class);
	}

	@Override
	public Integer corporateRecord(final Long ownerId) {
		final String sql = getQuery("ADDRESES_T1_CORPORATE_RECORD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(OWNER_ID, ownerId), Integer.class);
	}

	@Override
	public Integer staffRecord(final Long ownerId) {
		final String sql = getQuery("ADDRESES_T1_STAFF_RECORD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(OWNER_ID, ownerId), Integer.class);
	}

	@Override
	public Integer locationRecord(final String ownerCode) {
		final String sql = getQuery("ADDRESES_T1_LOCATION_RECORD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("ownerCode", ownerCode), Integer.class);
	}

	@Override
	public Integer offenderEducationRecord(final Long ownerId, final BigDecimal ownerSeq) {
		final String sql = getQuery("ADDRESES_T1_OFFENDER_EDUCATION_RECORD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(OWNER_ID, ownerId, "ownerSeq", ownerSeq),
				Integer.class);
	}

	@Override
	public Integer offenderEmploymentRecord(final Long ownerId, final BigDecimal ownerSeq) {
		final String sql = getQuery("ADDRESES_T1_OFFENDER_EMPLOYMENT_RECORD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(OWNER_ID, ownerId, "ownerSeq", ownerSeq),
				Integer.class);
	}

	@Override
	public Integer personEmploymentRecord(final Long ownerId, final BigDecimal ownerSeq) {
		final String sql = getQuery("ADDRESES_T1_PERSON_EMPLOYMENT_RECORD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(OWNER_ID, ownerId, "ownerSeq", ownerSeq),
				Integer.class);
	}

}

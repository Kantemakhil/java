package net.syscon.s4.triggers.impl;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.InternetAddressesT1Repository;

@Repository
public class InternetAddressesT1RepositoryImpl extends RepositoryBase implements InternetAddressesT1Repository {
	@Override
	public Integer offendersRecord(final Long ownerId) {
		final String sql = getQuery("INTERNET_ADDRESSES_T1_OFFENDERS_RECORD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("ownerId", ownerId), Integer.class);
	}

	@Override
	public Integer personRecord(final Long ownerId) {
		final String sql = getQuery("INTERNET_ADDRESSES_T1_PERSON_RECORD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("ownerId", ownerId), Integer.class);
	}

	@Override
	public Integer corporateRecord(final Long ownerId) {
		final String sql = getQuery("INTERNET_ADDRESSES_T1_CORPORATE_RECORD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("ownerId", ownerId), Integer.class);
	}

	@Override
	public Integer staffRecord(final Long ownerId) {
		final String sql = getQuery("INTERNET_ADDRESSES_T1_STAFF_RECORD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("ownerId", ownerId), Integer.class);
	}

	@Override
	public Integer locationRecord(final String ownerCode) {
		final String sql = getQuery("INTERNET_ADDRESSES_T1_LOCATION_RECORD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("ownerCode", ownerCode), Integer.class);
	}

	@Override
	public Integer offenderEducationRecord(final Long ownerId, final Long ownerSeq) {
		final String sql = getQuery("INTERNET_ADDRESSES_T1_OFFENDER_EDUCATION_RECORD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("ownerId", ownerId, "ownerSeq", ownerSeq),
				Integer.class);
	}

	@Override
	public Integer offenderEmploymentRecord(final Long ownerId, final Long ownerSeq) {
		final String sql = getQuery("INTERNET_ADDRESSES_T1_OFFENDER_EMPLOYMENT_RECORD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("ownerId", ownerId, "ownerSeq", ownerSeq),
				Integer.class);
	}

	@Override
	public Integer personEmploymentRecord(final Long ownerId, final Long ownerSeq) {
		final String sql = getQuery("INTERNET_ADDRESSES_T1_PERSON_EMPLOYMENT_RECORD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("ownerId", ownerId, "ownerSeq", ownerSeq),
				Integer.class);
	}

	@Override
	public Integer addressRecord(Long ownerId) {
		final String sql = getQuery("INTERNET_ADDRESSES_T1_ADDRESS_RECORD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("ownerId", ownerId), Integer.class);
	}

	
	@Override
	public Integer workflowRecord(final Long ownerId) {
		final String sql = getQuery("INTERNET_ADDRESSES_T1_WORKFLOW_RECORD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("ownerId", ownerId), Integer.class);
	}

}

package net.syscon.s4.pkgs.tag_prg.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.tag_prg.TagPrgRepository;

@Repository
public class TagPrgRepositoryImpl extends RepositoryBase implements TagPrgRepository {

	private static Logger logger = LogManager.getLogger(TagPrgRepositoryImpl.class.getName());

	@Override
	public String getProviderNameFromTeams(final Long providerPartyId) {
		final String sql = getQuery("PROVIDER_NAME_FROM_TEAMS");
		String providerName = null;
		try {
			providerName = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_PROVIDER_ID", providerPartyId), String.class);
		} catch (Exception e) {
			logger.error("getProviderNameFromTeams :", e);
		}
		return providerName;
	}

	@Override
	public String getProviderNameFromCorporates(final Long providerPartyId) {
		final String sql = getQuery("PROVIDER_NAME_FROM_CORPORATES");
		String providerName = null;
		try {
			providerName = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_PROVIDER_ID", providerPartyId), String.class);
		} catch (Exception e) {
			logger.error("getProviderNameFromCorporates :", e);
		}
		return providerName;
	}

	@Override
	public String getProviderNameFromPerson(final Long providerPartyId) {
		final String sql = getQuery("PROVIDER_NAME_FROM_PERSONS");
		String providerName = null;
		try {
			providerName = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_PROVIDER_ID", providerPartyId), String.class);
		} catch (Exception e) {
			logger.error("getProviderNameFromPerson :", e);
		}
		return providerName;
	}

	@Override
	public String getProviderNameFromAgencyLocation(final String providerPartyCode) {
		final String sql = getQuery("PROVIDER_NAME_FROM_AGENCY_LOCATIONS");
		String providerName = null;
		try {
			providerName = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_PROVIDER_CODE", providerPartyCode), String.class);
		} catch (Exception e) {
			logger.error("getProviderNameFromAgencyLocation :", e);
		}
		return providerName;
	}

	@Override
	public Long getCapacity(final Long courseActivityId) {
		final String sql = getQuery("TAG_PRG_COURSE_ACTIVITY_CAPACITY");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_course_activity_id", courseActivityId),
				Long.class);
	}

	@Override
	public Long getCount(final Long courseActivityId) {
		final String sql = getQuery("TAG_PRG_COUNT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_course_activity_id", courseActivityId),
				Long.class);
	}

	@Override
	public OffenderBookings getRecFrmOffBkgs(final Long offenderBookId) {
		final String sql = getQuery("GET_REC_FRM_OFF_BKGS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_book_id", offenderBookId),
				new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
	}

	@Override
	public BigDecimal creditHoursWithoutSentCond(BigDecimal offenderBookId, BigDecimal seq) {
		final String sql = getQuery("TAG_PRG_CREDITED_HOURS_WITHOUT_SENT_COND_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", offenderBookId,"SEQ",seq),
				BigDecimal.class);
	}

	@Override
	public BigDecimal creditHoursWithSentCond(BigDecimal offenderBookId, BigDecimal seq, BigDecimal offenderSentCondId) {
		final String sql = getQuery("TAG_PRG_CREDITED_HOURS_WITH_SENT_COND_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", offenderBookId,"SEQ",seq,"OFFENDERSENTCONDID",offenderSentCondId),
				BigDecimal.class);
	}
}
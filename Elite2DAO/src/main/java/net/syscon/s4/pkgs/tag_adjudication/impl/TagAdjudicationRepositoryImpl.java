package net.syscon.s4.pkgs.tag_adjudication.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.tag_adjudication.TagAdjudicationRepository;

@Service
public class TagAdjudicationRepositoryImpl extends RepositoryBase implements TagAdjudicationRepository {
	private static Logger logger = LogManager.getLogger(TagAdjudicationRepositoryImpl.class.getName());
	@Override
	public Integer getNextSanctionSeq(final Integer pOffenderBookId) {
		final String sql = getQuery("GET_NEXT_SANCTION_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFENDER_BOOK_ID", pOffenderBookId),
				Integer.class);
	}

	@Override
	public Integer getAdjudicationFromSanction(final Integer pSanctionSeq, final Integer pOffenderBookId) {
		final String sql = getQuery("GET_ADJUDICATION_FROM_SANCTION");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_OFFENDER_BOOK_ID", pOffenderBookId, "P_SANCTION_SEQ", pSanctionSeq), Integer.class);
	}

	@Override
	public Integer getNextAgencyIncidentId() {
		final String sql = getQuery("GET_NEXT_AGENCY_INCIDENT_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	@Override
	public Integer getNextPartySeq(final Integer pAgencyIncidentId) {
		final String sql = getQuery("GET_NEXT_PARTY_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_AGENCY_INCIDENT_ID", pAgencyIncidentId),
				Integer.class);
	}

	
	@Override
	public Integer getnextchargeseq(final Integer pAgencyIncidentId) {
		final String sql = getQuery("GET_NEXT_CHARGE_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_AGENCY_INCIDENT_ID", pAgencyIncidentId),
				Integer.class);
	}

	
	@Override
	public Integer getnextrepairseq(final Integer pAgencyIncidentId) {
		final String sql = getQuery("GET_NEXT_REPAIR_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_AGENCY_INCIDENT_ID", pAgencyIncidentId),
				Integer.class);
	}
	
	
	@Override
	public OicOffences getoffencedetails(BigDecimal pOffenceId) {
		final String sql = getQuery("TAG_ADJUDICATION_GET_OFFENCE_DETAILS");
		try {
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFENCE_ID", pOffenceId),
				new BeanPropertyRowMapper<OicOffences>(OicOffences.class));
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String getHearingType(Integer hearingId) {
		String returnValue=null;
		final String sql = getQuery("TAG_ADJUDICATION_GET_HEARING_TYPE");
		try {
			returnValue= namedParameterJdbcTemplate.queryForObject(sql, createParams("p_hearing_id", hearingId),
					String.class);
		} catch (Exception e) {
			logger.error("Exception raised in getHearingType", e);	
		}
		return returnValue;
	}
	
	
}
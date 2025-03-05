package net.syscon.s4.pkgs.tag_assessment.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.OffenderBookingDetails;
import net.syscon.s4.pkgs.tag_assessment.TagAssessmentRepository;

@Repository
public class TagAssessmentRepositoryImpl extends RepositoryBase implements TagAssessmentRepository {

	private static Logger logger = LogManager.getLogger(TagAssessmentRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> offendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("living_unit_id", new FieldMapper("offenderId"))
			.put("offender_book_id", new FieldMapper("offenderBookId")).put("ns_type", new FieldMapper("nameType"))
			.build();

	@Override
	public Long getAssessmentId() {
		final String sql = getQuery("TAG_ASSESSMENT_GET_ASSESSMENT_ID");
		Long retVal = 0l;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (final Exception e) {
			logger.error("getAssessmentId :", e);
		}
		return retVal;
	}

	@Override
	public Integer chkBkgCsa(final BigDecimal offenderBookId) {
		final String sql = getQuery("CHK_BKG_CSA");
		Integer result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					Integer.class);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("chkBkgCsa :", e);
			result = 0;
		}
		return result;
	}

	@Override
	public List<Offenders> getCsaOccupants(final BigDecimal livingUnitId) {
		final String sql = getQuery("GET_CSA_OCCUPANTS");
		List<Offenders> returnList = new ArrayList<Offenders>();
		final RowMapper<Offenders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, offendersMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("livingUnitId", livingUnitId), rowMapper);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("getCsaOccupants :", e);
		}
		return returnList;

	}

	@Override
	public String csaResult(final BigDecimal pAssessmentId, final String pAssessmentCode) {

		final String sql = getQuery("CHK_ASSESSMENT_CSA_CSA_RESULT");
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pAssessmentId", pAssessmentId, "pAssessmentCode", pAssessmentCode), String.class);
	}

	@Override
	public Integer updateBkgCsa(final OffenderBookingDetails inputObj) {
		Integer returnValue = 0;
		final String sql = getQuery("OFFENDER_BOOKING_DETAILS_UPDATE_BKG_CSA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(inputObj));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("updateBkgCsa ", e);
		}
		return returnValue;

	}

}
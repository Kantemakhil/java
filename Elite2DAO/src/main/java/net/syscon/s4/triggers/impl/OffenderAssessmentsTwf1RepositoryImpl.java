package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderAssessmentsTwf1Repository;

@Repository
public class OffenderAssessmentsTwf1RepositoryImpl extends RepositoryBase implements OffenderAssessmentsTwf1Repository {
	private final Logger logger = LogManager.getLogger(OffenderAssessmentsTwf1RepositoryImpl.class);

	@Override
	public OffenderAssessments getOffenderAssessments(final Long offenderBookId, final Long assessmentSeq) {
		final String sql = getQuery("OFFENDER_ASSESSMENTS_TWF1_OFFENDER_ASSESSMENTS_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":offenderBookId", offenderBookId, ":assessmentSeq", assessmentSeq),
					new BeanPropertyRowMapper<OffenderAssessments>(OffenderAssessments.class));
		} catch (final Exception e) {
			logger.error("getOffenderAssessments", e);
			return null;
		}
	}

	@Override
	public Long curAssessmentType() {
		final String sql = getQuery("OFFENDER_ASSESSMENTS_TWF1_CUR_ASSESSMENT_TYPE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (final Exception e) {
			logger.error("curAssessmentType", e);
			return 0l;
		}
	}

	@Override
	public String curInmateIsAtRisk(final Long offenderBookId, final Long assessmentSeq, final BigDecimal assessmentTypeId) {
		final String sql = getQuery("CUR_INMATE_IS_AT_RISK");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(":pOffenderBookId", offenderBookId,
					":pAssessmentSeq", assessmentSeq, ":pAssessmentTypeId", assessmentTypeId), String.class);
		} catch (final Exception e) {
			logger.error("curInmateIsAtRisk", e);
			return null;
		}
	}

}

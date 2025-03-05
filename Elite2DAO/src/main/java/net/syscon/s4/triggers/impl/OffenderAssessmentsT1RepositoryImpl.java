package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderAssessmentsT1Repository;

@Repository
public class OffenderAssessmentsT1RepositoryImpl extends RepositoryBase implements OffenderAssessmentsT1Repository {
	private final Logger logger = LogManager.getLogger(OffenderAssessmentsT1RepositoryImpl.class);

	@Override
	public OffenderAssessments getOffenderAssessments(final Long offenderBookId, final Long assessmentSeq) {
		final String sql = getQuery("OFFENDER_ASSESSMENTS_T1_OFFENDER_ASSESSMENTS_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":offenderBookId", offenderBookId, ":assessmentSeq", assessmentSeq),
					new BeanPropertyRowMapper<OffenderAssessments>(OffenderAssessments.class));
		} catch (final Exception e) {
			logger.error("getOffenderAssessments", e);
			return null;
		}
	}

}

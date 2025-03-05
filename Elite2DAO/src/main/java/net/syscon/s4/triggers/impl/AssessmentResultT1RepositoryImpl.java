package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.triggers.AssessmentResultT1Repository;

@Repository
public class AssessmentResultT1RepositoryImpl extends RepositoryBase implements AssessmentResultT1Repository {
	private final Logger logger = LogManager.getLogger(AssessmentResultT1RepositoryImpl.class.getName());

	@Override
	public AssessmentResults getAssessmentResults(final AssessmentResults assessmentResults) {
		final String sql = getQuery("ASSESSMENT_RESULT_T1_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":assessmentId", assessmentResults.getAssessmentId(), "supervisionLevelType",
							assessmentResults.getSupervisionLevelType()),
					new BeanPropertyRowMapper<AssessmentResults>(AssessmentResults.class));
		} catch (final Exception e) {
			logger.error("getAssessmentResults", e);
			return null;
		}
	}

}

package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.triggers.AssessmentResultsTjnRepository;
@Repository
public class AssessmentResultsTjnRepositoryImpl extends RepositoryBase implements AssessmentResultsTjnRepository {

	private static Logger logger = LogManager.getLogger(SystemProfilesT1RepositoryImpl.class);

	private final Map<String, FieldMapper> assessmentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ASSESSMENT_ID", 		new FieldMapper("assessmentId"))
			.build();
	private final Map<String, FieldMapper> assessmentResultsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ASSESSMENT_ID", 		new FieldMapper("assessmentId"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ASSESSMENT_CODE", 	new FieldMapper("assessmentCode"))
			.put("CODE", 				new FieldMapper("code"))
			.put("DESCRIPTION", 		new FieldMapper("description"))
			.put("ASSESSMENT_ID", 		new FieldMapper("assessmentId"))
			.put("ASSESSMENT_TYPE", 	new FieldMapper("assessmentType"))
			.build();
	private final Map<String, FieldMapper> parentMaqpping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TABLE_NAME", 							new FieldMapper("tableName"))
			.put("COLUMN_NAME", 						new FieldMapper("columnName"))
			.build();
	@Override
	public Integer insertAssessmentResult(List<AssessmentResults> assessmentResult) {
		final String sql =getQuery("INSERT_INTO_DB_PATCHES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AssessmentResults assessment : assessmentResult) {
			parameters.add(new BeanPropertySqlParameterSource(assessment));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (assessmentResult.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	@Override
	public Integer updateAssessmentResult(List<AssessmentResults> assessmentResult) {
		final String sql =getQuery("ASSESSMENT_RESULTS_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AssessmentResults assessment : assessmentResult) {
			parameters.add(new BeanPropertySqlParameterSource(assessment));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (assessmentResult.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	@Override
	public Integer deleteAssessmentResult(List<AssessmentResults> assessmentResult) {
		final String sql =getQuery("ASSESSMENT_RESULTS_DELETE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AssessmentResults assessment : assessmentResult) {
			parameters.add(new BeanPropertySqlParameterSource(assessment));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (assessmentResult.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
	
}
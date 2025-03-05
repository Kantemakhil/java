package net.syscon.s4.inst.classification.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.classification.OmuclassRepository;
import net.syscon.s4.inst.classification.beans.AssessmentSectionScoresV1;

/**
 * Class OmuclassRepositoryImpl
 */
@Repository
public class OmuclassRepositoryImpl extends RepositoryBase implements OmuclassRepository {

	private final Map<String, FieldMapper> assessmentSectionScoresV1Mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("PARENT_ASSESSMENT_ID", new FieldMapper("parentAssessmentId"))
			.put("EFFECTIVE_DATE", new FieldMapper("effectiveDate")).put("SCORE", new FieldMapper("score"))
			.put("ASSESSMENT_SEQ", new FieldMapper("assessmentSeq")).put("SECTION", new FieldMapper("section"))
			.put("DESCRIPTION", new FieldMapper("description")).put("ASSESSMENT_ID", new FieldMapper("assessmentId"))
			.build();
	private final Map<String, FieldMapper> referenceCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();

	/**
	 * Creates new OmuclassRepositoryImpl class Object
	 */
	public OmuclassRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao AssessmentSectionScoresV1
	 *
	 * @return List<AssessmentSectionScoresV1>
	 *
	 * @
	 */
	public List<AssessmentSectionScoresV1> subTotalsExecuteQuery(final AssessmentSectionScoresV1 objSearchDao) {
		final String sql = getQuery("OMUCLASS_SUBTOTALS_FIND_ASSESSMENT_SECTION_SCORES_V1");
		final RowMapper<AssessmentSectionScoresV1> AssessmentSectionScoresV1RowMapper = Row2BeanRowMapper
				.makeMapping(sql, AssessmentSectionScoresV1.class, assessmentSectionScoresV1Mapping);
		final ArrayList<AssessmentSectionScoresV1> returnList = (ArrayList<AssessmentSectionScoresV1>) namedParameterJdbcTemplate
				.query(sql, createParams("ASSESSMENT_SEQ", objSearchDao.getAssessmentSeq(), "PARENT_ASSESSMENT_ID",
						objSearchDao.getParentAssessmentId(), "OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId()),
						AssessmentSectionScoresV1RowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<Object> cgwhenNewFormInstance(final SysDual paramBean) {
		final String sql = getQuery("OMUCLASS_CGWHEN_NEW_FORM_INSTANCE");
		final ArrayList<Object> returnList = (ArrayList<Object>) namedParameterJdbcTemplate.queryForList(sql,
				createParams(), Object.class);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao AssessmentSectionScoresV1
	 *
	 * @return List<AssessmentSectionScoresV1>
	 *
	 * @
	 */
	public String preQueryGetCaseloadType(final String userName) {
		final String sql = getQuery("OMUCLASS_PRE_QUERY_GET_CASELOADTYPE");
		final String returnvalue = namedParameterJdbcTemplate.queryForObject(sql, createParams("userName", userName),
				String.class);
		return returnvalue;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao AssessmentSectionScoresV1
	 *
	 * @return List<AssessmentSectionScoresV1>
	 *
	 * @
	 */
	public String preQueryGetSecurityLevel(final AssessmentSectionScoresV1 searchBean) {
		final String sql = getQuery("OMUCLASS_PRE_QUERY_GET_SECURITYLEVEL");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("DOMAIN", searchBean.getDomain(), "ASSESSMENT_ID", searchBean.getAssessmentId(),
							"SCORE", searchBean.getScore()),
					String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

}

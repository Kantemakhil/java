package net.syscon.s4.inst.securitythreatgroups.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.StgValidations;
import net.syscon.s4.common.beans.VStgLocationPresence;
import net.syscon.s4.common.beans.VStgRacialMakeup;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.securitythreatgroups.OiitgdetRepository;
import oracle.jdbc.internal.OracleTypes;

@Repository
public class OiitgdetRepositoryImpl extends RepositoryBase implements OiitgdetRepository {

	private final Map<String, FieldMapper> securityThreatGroupsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NULL", new FieldMapper(" null")).put("DESCRIPTI", new FieldMapper("descripti"))
			.put("STG_ID", new FieldMapper("stgId")).put("PROFILE_VALUE", new FieldMapper(" profileValue "))
			.put("DESCRIPTIO", new FieldMapper("descriptio")).put("STG_COD", new FieldMapper("stgCod")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STG_ID", new FieldMapper("stgId")).put("CODE", new FieldMapper("code"))
			.put("STG_CODE", new FieldMapper("stgCode")).put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACTION", new FieldMapper("action")).put("DESCRIPTION", new FieldMapper(" description "))
			.put("DESIGNATION", new FieldMapper("designation")).build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NULL", new FieldMapper(" null")).put("DESCRIPTI", new FieldMapper("descripti"))
			.put("STG_ID", new FieldMapper("stgId")).put("PROFILE_VALUE", new FieldMapper(" profileValue "))
			.put("DESCRIPTIO", new FieldMapper("descriptio")).put("STG_COD", new FieldMapper("stgCod")).build();
	private final Map<String, FieldMapper> stgValidationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STG_ID", new FieldMapper("stgId")).put("VALIDATION_SEQ", new FieldMapper("validationSeq"))
			.put("VALIDATION_DATE", new FieldMapper("validationDate")).put("ACTION", new FieldMapper("action"))
			.put("DESIGNATION", new FieldMapper("designation")).put("REASON", new FieldMapper("reason"))
			.put("REVIEW_DATE", new FieldMapper("reviewDate")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();

	private final Map<String, FieldMapper> formAccessibleFormsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ORIGINATING_FORM", new FieldMapper("originatingForm"))
			.put("DESTINATION_FORM", new FieldMapper("destinationForm")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();

	private final Map<String, FieldMapper> vStgMakeupMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STG_ID", new FieldMapper("stgId")).put("RACE_DESCRIPTION", new FieldMapper("raceDescription"))
			.put("RACE_COUNT", new FieldMapper("raceCount")).build();

	private final Map<String, FieldMapper> vStgLocationMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STG_ID", new FieldMapper("stgId")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("LOCATION_DESCRIPTION", new FieldMapper("locationDescription"))
			.put("LOCATION_COUNT", new FieldMapper("locationCount")).build();

	/**
	 * Creates new OiitgdetRepositoryImpl class Object
	 */
	public OiitgdetRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao StgValidations
	 *
	 * @return List<StgValidations>
	 *
	 * 
	 */
	public List<StgValidations> stgValidationsExecuteQuery(final StgValidations objSearchDao) {
		final String sql = getQuery("OIITGDET_STGVALIDATIONS_FIND_STG_VALIDATIONS");
		final RowMapper<StgValidations> StgValidationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				StgValidations.class, stgValidationsMapping);
		final List<StgValidations> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("stgId", objSearchDao.getStgId()), StgValidationsRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VStgRacialMakeup
	 *
	 * @return List<VStgRacialMakeup>
	 *
	 * 
	 */
	public List<VStgRacialMakeup> stgRaceMakeupExecuteQuery(final VStgRacialMakeup objSearchDao) {
		final String sql = getQuery("OIITGDET_STGRACEMAKEUP_FIND_V_STG_RACIAL_MAKEUP");
		final RowMapper<VStgRacialMakeup> VStgRacialMakeupRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VStgRacialMakeup.class, vStgMakeupMapping);
		final List<VStgRacialMakeup> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("stgId", objSearchDao.getStgId()), VStgRacialMakeupRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VStgLocationPresence
	 *
	 * @return List<VStgLocationPresence>
	 *
	 * 
	 */
	public List<VStgLocationPresence> stgLocationPresenceExecuteQuery(final VStgLocationPresence objSearchDao) {
		final String sql = getQuery("OIITGDET_STGLOCATIONPRESENCE_FIND_V_STG_LOCATION_PRESENCE");
		final RowMapper<VStgLocationPresence> VStgLocationPresenceRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VStgLocationPresence.class, vStgLocationMapping);
		final List<VStgLocationPresence> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("stgId", objSearchDao.getStgId()), VStgLocationPresenceRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao FormAccessibleForms
	 *
	 * @return List<FormAccessibleForms>
	 *
	 * 
	 */
	public List<FormAccessibleForms> fafExecuteQuery(final FormAccessibleForms objSearchDao) {
		final String sql = getQuery("OIITGDET_FAF_FIND_FORM_ACCESSIBLE_FORMS");
		final RowMapper<FormAccessibleForms> FormAccessibleFormsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FormAccessibleForms.class, formAccessibleFormsMapping);
		final List<FormAccessibleForms> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("USER_NAME", objSearchDao.getCreateUserId()), FormAccessibleFormsRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * 
	 * 
	 * /**        Used to capture results from select query
	 * @return List<M>
	 */
	public List<SecurityThreatGroups> rgStg2RecordGroup() {
		final String sql = getQuery("OIITGDET_FIND_RGSTG2");
		final RowMapper<SecurityThreatGroups> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<SecurityThreatGroups> rgStg1RecordGroup() {
		final String sql = getQuery("OIITGDET_FIND_RGSTG1");
		final RowMapper<SecurityThreatGroups> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<SecurityThreatGroups> rgStg3RecordGroup() {
		final String sql = getQuery("OIITGDET_FIND_RGSTG3");
		final RowMapper<SecurityThreatGroups> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * stgDetailKeyExeqry
	 *
	 * @param params
	 *
	 */
	public BigDecimal stgDetailKeyExeqry(final BigDecimal param) {
		final String sql = getQuery("OIITGDET_STG_DETAIL_KEYEXEQRY_MEMBER");
		final BigDecimal returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("StgId", param),
				BigDecimal.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * stgDetailKeyExeqry
	 *
	 * @param params
	 *
	 */
	public BigDecimal stgDetailKeyExeqryValidated(final BigDecimal param) {
		final String sql = getQuery("OIITGDET_STG_DETAIL_KEYEXEQRY_VALIDATED");
		final BigDecimal returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("StgId", param),
				BigDecimal.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * stgValidationsPostQuery
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> stgValidationsPostQueryOne(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIITGDET_STG_VALIDATIONS_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * stgValidationsPostQuery
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> stgValidationsPostQuery(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIITGDET_STG_VALIDATIONS_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * fafPostQuery
	 *
	 * @param params
	 *
	 */

	public String fafPostQueryDescription(final String param) {
		final String sql = getQuery("OIITGDET_FAF_POSTQUERY_DESCRIPTION");
		try {
			final String returnValue = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("DESTINATIONFORM", param), String.class);
			return returnValue;
		} catch (final Exception e) {
			return "";
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * fafPostQuery
	 *
	 * @param params
	 *
	 */
	public BigDecimal fafPostQueryVAlidDataCur(final BigDecimal param) {
		final String sql = getQuery("OIITGDET_FAF_POSTQUERY_VALID_DATA_CUR");
		final BigDecimal returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("stgId", param),
				BigDecimal.class);
		return returnValue;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * fafPostQuery
	 *
	 * @param params
	 *
	 */
	public BigDecimal fafPostQueryVAlidDataCurTwo(final BigDecimal param) {
		final String sql = getQuery("OIITGDET_FAF_POSTQUERY_VALID_DATA_CUR_TWO");
		final BigDecimal returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("stgId", param),
				BigDecimal.class);
		return returnValue;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * fafPostQuery
	 *
	 * @param params
	 *
	 */
	public BigDecimal fafPostQueryTwoValidIwpCur(final BigDecimal param) {
		final String sql = getQuery("OIITGDET_FAF_POSTQUERY_VALID_IWP_CUR");
		final BigDecimal returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("stgId", param.toString()),
				BigDecimal.class);
		return returnValue;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oiitgdetWhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public String oiitgdetWhenNewFormInstance() {
		final String sql = getQuery("OIITGDET_OIITGDET_WHENNEWFORMINSTANCE");
		try {
			final String returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
			return returnVal;
		} catch (final Exception e) {
			return null;
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oiitgdetWhenNewFormInstancestg_description_cur2
	 *
	 * @param params
	 *
	 */
	public String oiitgdetOiitgdetWhennewforminstanceStgDescriptionCur2(final String lvGroup) {
		final String sql = getQuery("OIITGDET_OIITGDET_WHENNEWFORMINSTANCE_STG_DESCRIPTION_CUR2");
		try {
			final String returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("lvGroup", lvGroup),
					String.class);
			return returnValue;
		} catch (final Exception e) {
			return null;
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oiitgdetWhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<SecurityThreatGroups> oiitgdetWhenNewFormInstanceTwo(final SecurityThreatGroups paramBean) {
		final String sql = getQuery("OIITGDET_OIITGDET_WHENNEWFORMINSTANCE");
		final RowMapper<SecurityThreatGroups> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, securityThreatGroupsMapping);
		final List<SecurityThreatGroups> returnList = (ArrayList<SecurityThreatGroups>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oiitgdetWhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<SecurityThreatGroups> oiitgdetWhenNewFormInstanceOne(final SecurityThreatGroups paramBean) {
		final String sql = getQuery("OIITGDET_OIITGDET_WHENNEWFORMINSTANCE");
		final RowMapper<SecurityThreatGroups> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, securityThreatGroupsMapping);
		final ArrayList<SecurityThreatGroups> returnList = (ArrayList<SecurityThreatGroups>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oiitgdetWhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<SecurityThreatGroups> oiitgdetWhenNewFormInstance(final SecurityThreatGroups paramBean) {
		final String sql = getQuery("OIITGDET_OIITGDET_WHENNEWFORMINSTANCE");
		final RowMapper<SecurityThreatGroups> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, securityThreatGroupsMapping);
		final ArrayList<SecurityThreatGroups> returnList = (ArrayList<SecurityThreatGroups>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OIITGDET_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				systemProfilesMapping);
		final ArrayList<OmsModules> returnList = (ArrayList<OmsModules>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	public String checkDataAvailableStg(final String destForm, final BigDecimal stgId) {
		final SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_ORIG_FORM", OracleTypes.VARCHAR),
				new SqlParameter("P_DEST_FORM", OracleTypes.VARCHAR), new SqlParameter("P_STG_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_DATA_AVAIL", OracleTypes.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_FORM_ACCESS").withProcedureName("CHECK_DATA_AVAILABLE_STG")
				.declareParameters(sqlParameters);
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_ORIG_FORM", "OIITGDET");
		inParamMap.put("P_DEST_FORM", destForm);
		inParamMap.put("P_STG_ID", stgId);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
		if (returnObject.get("P_DATA_AVAIL") != null) {
			return returnObject.get("P_DATA_AVAIL").toString();
		}
		return null;
	}

	public String oiitgdetPrimaryCur(final BigDecimal stgId) {
		final String sql = getQuery("OIITGDET_PRIMARY_CUR");
		try {
			final String returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("stgId", stgId),
					String.class);
			return returnValue;
		} catch (final Exception e) {
			return null;
		}
	}

	public String oiitgdetGetProfileValue(final String profileType, final String profileCode) {
		final String sql = getQuery("OIITGDET_GET_PROFILE_VALUE");
		try {
			final String returnValue = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("profileType", profileType, "profileCode", profileCode), String.class);
			return returnValue;
		} catch (final Exception e) {
			return "N";
		}
	}

	public String oiitgdetGetGroupPrivilege(String username) {
		final String sql = getQuery("OIITGDET_GET_GROUP_PRIVILEGE");
		try {
			final String returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("username",username), String.class);
			return returnValue;
		} catch (final Exception e) {
			return "";
		}
	}

}

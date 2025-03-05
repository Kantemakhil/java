package net.syscon.s4.inst.incidentsoic.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderOicSanctions;
import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.incidentsoic.OcuoicawRepository;
import net.syscon.s4.inst.incidentsoic.maintenance.OicSanctionLimits;
import oracle.jdbc.OracleTypes;
import net.syscon.s4.inst.incidentsoic.maintenance.OicSanctionLimits;

/**
 * class OcuoicawRepositoryImpl
 */
@Repository
public class OcuoicawRepositoryImpl extends RepositoryBase implements OcuoicawRepository {
	private static Logger logger = LogManager.getLogger(OcuoicawRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> oicOffencesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MAX_PENALTY_DAYS", new FieldMapper("maxPenaltyDays"))
			.put("MAX_PENALTY_MONTHS", new FieldMapper("maxPenaltyMonths"))
			.put("OIC_OFFENCE_CODE", new FieldMapper("oicOffenceCode")).build();
	private final Map<String, FieldMapper> offenderOicSanctionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("SANCTION_DAYS", new FieldMapper("sanctionDays"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("CREATE_USER_ID", new FieldMapper("createUserID"))
			.put("OFFENDER_ADJUST_ID", new FieldMapper("offenderAdjustId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("SANCTION_SEQ", new FieldMapper("sanctionSeq"))
			.put("OIC_SANCTION_CODE", new FieldMapper("oicSanctionCode"))
			.put("LIDS_SANCTION_NUMBER", new FieldMapper("lidsSanctionNumber"))
			.put("APPEALING_DATE", new FieldMapper("appealingDate"))
			.put("COMPENSATION_AMOUNT", new FieldMapper("compensationAmount"))
			.put("SANCTION_MONTHS", new FieldMapper("sanctionMonths"))
			.put("RESULT_SEQ", new FieldMapper("resultSeq"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("EFFECTIVE_DATE", new FieldMapper("effectiveDate"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("OIC_INCIDENT_ID", new FieldMapper("oicIncidentId"))
			.put("STATUS", new FieldMapper("status"))
			.put("CONSECUTIVE_OFFENDER_BOOK_ID", new FieldMapper("consecutiveOffenderBookId"))
			.put("CONSECUTIVE_SANCTION_SEQ", new FieldMapper("consecutiveSanctionSeq"))
			.put("STATUS_DATE", new FieldMapper("statusDate"))
			.put("OIC_HEARING_ID", new FieldMapper("oicHearingId"))
			.put("OIC_OFFENCE_CODE", new FieldMapper("oicOffenceCode"))
			.put("OIC_OFFENCE_TYPE", new FieldMapper("oicOffenceType"))
			.put("OIC_CHARGE_ID", new FieldMapper("oicChargeId"))
			.put("HEARING_DATE", new FieldMapper("hearingDate"))
			.put("HEARING_TIME", new FieldMapper("hearingTime"))
			.put("CHARGE_SEQ", new FieldMapper("chargeSeq"))
			.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2"))
			.put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("PROFILE_CODE", new FieldMapper("profileCode"))
			.build();
	public final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DOMAIN", new FieldMapper("domain")).put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("ACTIVE_FLAG", new FieldMapper("active_flag"))
			.put("LIST_SEQ", new FieldMapper("list_seq"))
			.put("SYSTEM_DATA_FLAG", new FieldMapper("systemDataFlag"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("EXPIRED_DATE", new FieldMapper("expiredDate"))
			.put("NEW_CODE", new FieldMapper("newCode"))
			.put("PARENT_CODE", new FieldMapper("parentCode"))
			.put("PARENT_DOMAIN", new FieldMapper("parentDomain"))
			.put("CREATE_USER_ID", new FieldMapper("createUserID"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();

	/**
	 * Creates new OcuoicawRepositoryImpl class Object
	 */
	public OcuoicawRepositoryImpl() {
		super();
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<OffenderOicSanctions> rgOtherSanctionsRecordGroup(final OffenderOicSanctions offenderOicSan) {
		final String sql = getQuery("OCUOICAW_FIND_RGOTHERSANCTIONS");
		List<OffenderOicSanctions> listRefence = null;
		final RowMapper<OffenderOicSanctions> offenderOicmapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderOicSanctions.class, offenderOicSanctionsMapping);
		listRefence = namedParameterJdbcTemplate.query(sql, createParams("OFFENDERBOOKID",
				offenderOicSan.getOffenderBookId(), "SANCTIONSEQ", offenderOicSan.getSanctionSeq()), offenderOicmapper);
		return listRefence;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgSanctRecordGroup() {
		final String sql = getQuery("OCUOICAW_FIND_RGSANCT");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgSanctStRecordGroup() {
		final String sql = getQuery("OCUOICAW_FIND_RGSANCTST");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderOicSanctions
	 *
	 * @return List<OffenderOicSanctions>
	 *
	 * @
	 */
	public List<OffenderOicSanctions> oicSancSearchOffenderOicSanctions(final OffenderOicSanctions objSearchDao) {

		final String sql = getQuery("OCUOICAW_OICSANC_FIND_OFFENDER_OIC_SANCTIONS");
		final RowMapper<OffenderOicSanctions> OffenderOicSanctionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderOicSanctions.class, offenderOicSanctionsMapping);
		ArrayList<OffenderOicSanctions> returnList = new ArrayList<>();
		returnList = (ArrayList<OffenderOicSanctions>) namedParameterJdbcTemplate.query(sql,
				createParams("oicHearingId", objSearchDao.getOicHearingId(), "resultSeq", objSearchDao.getResultSeq()),
				OffenderOicSanctionsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderOicSanctions
	 *            List<OffenderOicSanctions>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer oicsancInsertOffenderOicSanctions(final List<OffenderOicSanctions> lstOffenderOicSan) {
		Integer sanctionSeq = 0;
		final String sql = getQuery("OCUOICAW_OICSANC_INSERT_OFFENDER_OIC_SANCTIONS");
		for (final OffenderOicSanctions offenderOicSan : lstOffenderOicSan) {
			sanctionSeq = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(offenderOicSan));
		}
		if (sanctionSeq == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * AgencyIncidentPreInsertcDAO
	 *
	 *
	 */
	public Integer offesencPreInsertcsanseq(final Integer offBookId) {
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[1];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.VARCHAR),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_ADJUDICATION").withFunctionName("GETNEXTSANCTIONSEQ")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", offBookId);
		inParamMap.put("P_RETURN", "true");
		SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		String value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return Integer.parseInt(value);
	}

	
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * AgencyIncidentPreInsertcDAO
	 *
	 *
	 */
	public Integer offesencPostInsertcsanseq(final Integer offBookId,final Integer consegativeSeq,Integer  incidentid) {
		Integer sanvalue=0;
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("P_SANCTION_SEQ", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.VARCHAR),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_ADJUDICATION").withFunctionName("GetAdjudicationFromSanction")
				.declareParameters(sqlParameters);
		inParamMap.put("P_SANCTION_SEQ", consegativeSeq);
		inParamMap.put("P_OFFENDER_BOOK_ID", offBookId);
		inParamMap.put("P_RETURN", "true");
		SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		String value = simpleJDBCCall.executeFunction(String.class, inParameter);
		if(value !=null){
			sanvalue =Integer.parseInt(value);
		}
		return sanvalue;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderOicSanctions
	 *            List<OffenderOicSanctions>
	 *
	 * @
	 */
	public Integer oicSancUpdateOffenderOicSanctions(final List<OffenderOicSanctions> lstOffenderOic) {
		final String sql = getQuery("OCUOICAW_OICSANC_UPDATE_OFFENDER_OIC_SANCTIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderOicSanctions offenderOicSan : lstOffenderOic) {
			parameters.add(new BeanPropertySqlParameterSource(offenderOicSan));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderOic.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * whenValidateItemgetOicOffenceCodeCur
	 *
	 * @param params
	 *
	 */
	public List<OicOffences> whenValidateItemgetOicOffenceCodeCur(final OicOffences paramBean) {

		final String sql = getQuery("OCUOICAW_OCUOICAW_WHENVALIDATEITEM_GET_OIC_OFFENCE_CODE_CUR");
		final RowMapper<OicOffences> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OicOffences.class,
				oicOffencesMapping);
		ArrayList<OicOffences> returnList = new ArrayList<>();
		try {
			returnList = (ArrayList<OicOffences>) namedParameterJdbcTemplate.query(sql,
					createParams("oicOffenceCode", paramBean.getOicOffenceCode()), columnRowMapper);
			return returnList;
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getProfileValuevsProfvalCur
	 *
	 * @param params
	 *
	 */

	@Override
	public SystemProfiles getprofilevaluevsprofvalcur(final SystemProfiles paramBean) {
		SystemProfiles returnObj = new SystemProfiles();
		final String sql = getQuery("OCUOICAW_GET_PROFILE_VALUE_2_VS_PROFVAL_CUR");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("profileCode", paramBean.getProfileCode(), "profileType", paramBean.getProfileType()),
				columnRowMapper);
		return returnObj;
	}
	/*
	 * Below method used to get oicIncidentId by using function
	 * params offBookId,sanctionSeq
	 * returns oicIncidentId
	 */
	public Integer getOicIncidentValueFromSanction(final Integer offBookId, final Integer sanctionSeq) {
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[1];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_SANCTION_SEQ", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.NUMBER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_ADJUDICATION").withFunctionName("GETADJUDICATIONFROMSANCTION")
				.declareParameters(sqlParameters);
		inParamMap.put("P_SANCTION_SEQ", sanctionSeq);
		inParamMap.put("P_OFFENDER_BOOK_ID", offBookId);
		inParamMap.put("P_RETURN", "true");
		SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		BigDecimal value = simpleJDBCCall.executeFunction(BigDecimal.class, inParameter);
		return Integer.valueOf(value.toString());
	}
	
	@Override
	public OicSanctionLimits gettingSactionLimits(String oicHearingType, String oicSanctionCode) {
		final String sql = getQuery("OCUOICAW_GET_OIC_SANCTION_LIMITS");
		final RowMapper<OicSanctionLimits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OicSanctionLimits.class,
				oicOffencesMapping);
		OicSanctionLimits dataObj = new OicSanctionLimits();
		try {
			dataObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("SANCTION_CODE", oicSanctionCode,"HEARING_TYPE",oicHearingType), columnRowMapper);
			return dataObj;
		} catch (Exception e) {
			logger.error("Exception raised in gettingSactionLimits", e);
		}
		return dataObj;
	}

}

package net.syscon.s4.inmate.trust.financialreports.impl;

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
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OiufsoffGetGeneralOffenders;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inmate.trust.financialreports.OiufsoffRepository;
import oracle.jdbc.OracleTypes;

@Repository("iufsoffRepository1")
public class OiufsoffRepositoryImpl extends RepositoryBase implements OiufsoffRepository {

	/**
	 * Creates new OiufsoffRepositoryImpl class Object
	 */
	public OiufsoffRepositoryImpl() {
		// OiufsoffRepositoryImpl
	}

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OiufsoffGetGeneralOffenders
	 *
	 * @return List<OiufsoffGetGeneralOffenders>
	 */
	public List<OiufsoffGetGeneralOffenders> vOffBkgExecuteQuery(OiufsoffGetGeneralOffenders objSearchDao) {
		Map<String, Object> returnObject = null;
		final List<OiufsoffGetGeneralOffenders> lListObj = new ArrayList<OiufsoffGetGeneralOffenders>();
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("RESULTSET", OracleTypes.CURSOR),
				new SqlParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_LV_1_ID", OracleTypes.NUMBER), new SqlParameter("P_LV_2_ID", OracleTypes.NUMBER),
				new SqlParameter("P_LV_3_ID", OracleTypes.NUMBER), new SqlParameter("P_LAST_NAME", OracleTypes.VARCHAR),
				new SqlParameter("P_FIRST_NAME", OracleTypes.VARCHAR),
				new SqlParameter("P_MIDDLE_NAME", OracleTypes.VARCHAR),
				new SqlParameter("P_ACTIVE_FLAG", OracleTypes.VARCHAR),
				new SqlParameter("P_CASELOAD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_REPORT_APPLN_CODE", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_ID_DISPLAY", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIUFSOFF").withProcedureName("GET_GENERAL_OFFENDERS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_AGY_LOC_ID", objSearchDao.getpAgyLocId());
		inParamMap.put("P_LV_1_ID", objSearchDao.getpLv1Id());
		inParamMap.put("P_LV_2_ID", objSearchDao.getpLv2Id());
		inParamMap.put("P_LV_3_ID", objSearchDao.getpLv3Id());
		inParamMap.put("P_LAST_NAME", objSearchDao.getpLastName());
		inParamMap.put("P_FIRST_NAME", objSearchDao.getpFirstName());
		inParamMap.put("P_MIDDLE_NAME", objSearchDao.getpMiddleName());
		inParamMap.put("P_ACTIVE_FLAG", objSearchDao.getpActiveFlag());
		inParamMap.put("P_CASELOAD_ID", objSearchDao.getpCaseloadId());
		inParamMap.put("P_REPORT_APPLN_CODE", objSearchDao.getpReportApplnCode());
		inParamMap.put("P_OFFENDER_ID_DISPLAY", objSearchDao.getpOffenderIdDisplay());
		SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = (Map<String, Object>) simpleJDBCCall.withoutProcedureColumnMetaDataAccess()
					.execute(inParameter);
			List<Map<String, Object>> list = (List<Map<String, Object>>) returnObject.get("RESULTSET");
			if (list != null) {
				list.forEach(data -> {
					final OiufsoffGetGeneralOffenders bean = new OiufsoffGetGeneralOffenders();
					if (data.get("LAST_NAME") != null) {
						bean.setLastName(data.get("LAST_NAME").toString());
					}
					if (data.get("FIRST_NAME") != null) {
						bean.setFirstName(data.get("FIRST_NAME").toString());
					}
					if (data.get("MIDDLE_NAME") != null) {
						bean.setMiddleName(data.get("MIDDLE_NAME").toString());
					}
					if (data.get("AGY_LOC_ID") != null) {
						bean.setAgyLocId(data.get("AGY_LOC_ID").toString());
					}
					if (data.get("PRISON_LOCATION") != null) {
						bean.setPrisonLocation(data.get("PRISON_LOCATION").toString());
					}
					if (data.get("OFFENDER_ID_DISPLAY") != null) {
						bean.setOffenderIdDisplay(data.get("OFFENDER_ID_DISPLAY").toString());
					}
					if (data.get("OFFENDER_ID") != null) {
						bean.setOffenderId((BigDecimal) data.get("OFFENDER_ID"));
					}
					if (data.get("ROOT_OFFENDER_ID") != null) {
						bean.setRootOffenderId((BigDecimal) data.get("ROOT_OFFENDER_ID"));
					}
					if (data.get("OFFENDER_BOOK_ID") != null) {
						bean.setOffenderBookId((BigDecimal) data.get("OFFENDER_BOOK_ID"));
					}
					lListObj.add(bean);
				});
			}
		} catch (Exception e) {
			lListObj.add(null);
		}
		return lListObj;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOiufsoffGetGeneralOffenders
	 *            List<OiufsoffGetGeneralOffenders>
	 *
	 * @return List<Integer>
	 *
	 */
	public Integer vOffBkgInsertOiufsoffGetGeneralOffenders(
			final List<OiufsoffGetGeneralOffenders> lstOiufsoffGetGeneralOffenders) {
		int insertCount = 0;
		String sql = getQuery("OIUFSOFF_VOFFBKG_INSERT_OIUFSOFF.GET_GENERAL_OFFENDERS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		for (int i = 0; i < returnArray.length; i++) {
			insertCount = insertCount++;
		}
		if (lstOiufsoffGetGeneralOffenders.size() == insertCount) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOiufsoffGetGeneralOffenders
	 *            List<OiufsoffGetGeneralOffenders>
	 */
	public Integer vOffBkgUpdateOiufsoffGetGeneralOffenders(
			final List<OiufsoffGetGeneralOffenders> lstOiufsoffGetGeneralOffenders) {
		String sql = getQuery("OIUFSOFF_VOFFBKG_UPDATE_OIUFSOFF_GET_GENERAL_OFFENDERS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OiufsoffGetGeneralOffenders oiufsoffGetGeneralOffenders : lstOiufsoffGetGeneralOffenders) {
			parameters.add(new BeanPropertySqlParameterSource(oiufsoffGetGeneralOffenders));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOiufsoffGetGeneralOffenders.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> cgfkAgyLocIdRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIUFSOFF_FIND_CGFKAGYLOCID");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadId", caseloadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> cgfkHousingLevelOneRecordGroup(final String agyLocId) {
		final String sql = getQuery("OIUFSOFF_FIND_CGFKHOUSINGLEVEL1");
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agyLocId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> cgfkHousingLevelTwoRecordGroup(final String agyLocId,
			final BigDecimal parentLivingUnitId) {
		final String sql = getQuery("OIUFSOFF_FIND_CGFKHOUSINGLEVEL2");
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("agyLocId", agyLocId, "parentLivingUnitId", parentLivingUnitId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> cgfkHousingLevelThreeRecordGroup(final String agyLocId,
			final BigDecimal parentLivingUnitId) {
		final String sql = getQuery("OIUFSOFF_FIND_CGFKHOUSINGLEVEL3");
		final RowMapper<LivingUnits> mRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("agyLocId", agyLocId, "parentLivingUnitId", parentLivingUnitId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vOffBkgPostQuery
	 *
	 * @param params
	 *
	 */
	public String vOffBkgPostQuery(final String caseloadId, final String agyLocId, final BigDecimal rootOffenderId) {
		final String sql = getQuery("OIUFSOFF_V_OFF_BKG_POSTQUERY");
		try {
			final String returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("caseloadId", caseloadId, "agyLocId", agyLocId, "rootOffenderId", rootOffenderId),
					String.class);
			return returnList;
		} catch (Exception e) {
			return null;
		}
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
		final String sql = getQuery("OIUFSOFF_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mMapping);
		final ArrayList<OmsModules> returnList = (ArrayList<OmsModules>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

}

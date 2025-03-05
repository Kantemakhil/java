package net.syscon.s4.sa.admin.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.sa.admin.OumrareaRepository;

/**
 * Class OumrareaRepositoryImpl
 * 
 */
@Repository
public class OumrareaRepositoryImpl extends RepositoryBase implements OumrareaRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumrareaRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> areasMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AREA_CLASS", new FieldMapper("areaClass")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime")).put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("PARENT_AREA_CODE", new FieldMapper("parentAreaCode")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).build();

	/**
	 * Creates new OumrareaRepositoryImpl class Object
	 */
	public OumrareaRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Areas
	 *
	 * @return List<Areas>
	 *
	 * @throws SQLException
	 */
	public List<Areas> maintRegExecuteQuery(final Areas objSearchDao) {
		final String sql = getQuery("OUMRAREA_MAINTREG_FIND_AREAS");
		final StringBuffer pSql = new StringBuffer(sql);
		String preparedSql = "";
		final MapSqlParameterSource param = new MapSqlParameterSource();
		if (objSearchDao != null) {
			pSql.append(" WHERE AREA_CLASS='REGION' AND ");
			if (objSearchDao.getAreaCode() != null && !objSearchDao.getAreaCode().isEmpty()
					&& !"".equals(objSearchDao.getAreaCode().trim())) {
				pSql.append(" AREA_CODE LIKE :areaCode  AND ");
				param.addValue("areaCode", objSearchDao.getAreaCode().trim());
			}
			if (objSearchDao.getDescription() != null && !objSearchDao.getDescription().isEmpty()
					&& !"".equals(objSearchDao.getDescription().trim())) {
				pSql.append(" DESCRIPTION LIKE :description AND ");
				param.addValue("description", objSearchDao.getDescription().trim());
			}
			if (objSearchDao.getListSeq() != null) {
				pSql.append("LIST_SEQ::text like (:listSeq::text) AND ");
				param.addValue("listSeq", objSearchDao.getListSeq());
			}
			if (objSearchDao.getActiveFlag() != null && !objSearchDao.getActiveFlag().isEmpty()) {
				if ("true".equals(objSearchDao.getActiveFlag())) {
					objSearchDao.setActiveFlag("Y");
				} else {
					objSearchDao.setActiveFlag("N");
				}
				pSql.append(" ACTIVE_FLAG LIKE :activeFlag AND ");
				param.addValue("activeFlag", objSearchDao.getActiveFlag());
			}
			if (objSearchDao.getExpiryDate() != null) {
				pSql.append(" EXPIRY_DATE = :expiryDate AND ");
				param.addValue("expiryDate", objSearchDao.getExpiryDate());
			}
		}
		preparedSql = pSql.toString().trim();

		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" order by area_code asc ");
		final RowMapper<Areas> HoCodesRowMapper = Row2BeanRowMapper.makeMapping(sql, Areas.class, areasMapping);
		ArrayList<Areas> returnList = new ArrayList<>();
		try {
			returnList = (ArrayList<Areas>) namedParameterJdbcTemplate.query(preparedSql, param, HoCodesRowMapper);
		} catch (Exception e) {
			logger.error("maintRegExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstAreas
	 *            List<Areas>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer maintRegInsertAreas(final List<Areas> lstAreas) {
		final String sql = getQuery("OUMRAREA_MAINTREG_INSERT_AREAS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Areas areas : lstAreas) {
			parameters.add(new BeanPropertySqlParameterSource(areas));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAreas.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAreas
	 *            List<Areas>
	 *
	 * @throws SQLException
	 */
	public Areas maintRegUpdateAreas(final List<Areas> lstAreas) {
		final Areas returnData = new Areas();
		final String sql = getQuery("OUMRAREA_MAINTREG_UPDATE_AREAS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Areas areas : lstAreas) {
			parameters.add(new BeanPropertySqlParameterSource(areas));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage().toUpperCase();
				if(error.contains("AREAS_PK")) {
					returnData.setErrorMessage("Error : " +error);
				} 
					else if (error.contains("AREAS_AREAS_FK")){
					returnData.setSealFlag("area_code");
					returnData.setServerCode(2292);
				}
				logger.error("agyVisitTimesUpdateAgencyVisitTimes", e);
				return returnData;

		}

		if (lstAreas.size() == returnArray.length) {
			returnData.setReturnValue(1);
			return returnData;
		} else {
			returnData.setReturnValue(0);
			return returnData;
		}

	}

	public String errorNameValidationTimes(final String errorName) {
		final String sql = getQuery("OUMRAREA_CONSTRAINT_VIOLATIONS");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("CONSTRAINTNAME", errorName),
					String.class);
		} catch (Exception e) {
			logger.error("errorNameValidationTimes", e);
			returnData = null;
			return returnData;
		}
		return returnData;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Areas
	 *
	 * @return List<Areas>
	 *
	 * @throws SQLException
	 */
	public List<Areas> maintAreaExecuteQuery(final Areas objSearchDao) {
		final String sql = getQuery("OUMRAREA_MAINTAREA_FIND_AREAS");
		final RowMapper<Areas> AreasRowMapper = Row2BeanRowMapper.makeMapping(sql, Areas.class, areasMapping);
		return (ArrayList<Areas>) namedParameterJdbcTemplate.query(sql,
				createParams("parentAreaCode", objSearchDao.getParentAreaCode()), AreasRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstAreas
	 *            List<Areas>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer maintAreaInsertAreas(final List<Areas> lstAreas) {
		final String sql = getQuery("OUMRAREA_MAINTAREA_INSERT_AREAS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Areas areas : lstAreas) {
			parameters.add(new BeanPropertySqlParameterSource(areas));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAreas.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAreas
	 *            List<Areas>
	 *
	 * @throws SQLException
	 */
	public Areas maintAreaUpdateAreas(final List<Areas> lstAreas) {
		final Areas returnData = new Areas();
		final String sql = getQuery("OUMRAREA_MAINTAREA_UPDATE_AREAS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Areas areas : lstAreas) {
			parameters.add(new BeanPropertySqlParameterSource(areas));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage().toUpperCase();
			if(error.contains("AREAS_PK")) {
				returnData.setErrorMessage("Error : " +error);
			} 
				else if (error.contains("AREAS_AREAS_FK")){
				returnData.setSealFlag("area_code");
				returnData.setServerCode(2292);
			}
			}

		


		if (lstAreas.size() == returnArray.length) {
			returnData.setReturnValue(1);
			return returnData;
		} else {
			returnData.setReturnValue(0);
			return returnData;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgAreaTypeRecordGroup() {
		final String sql = getQuery("OUMRAREA_FIND_RGAREATYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgAreaTypeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * maintRegOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<Areas> maintRegOnCheckDeleteMaster(final Areas paramBean) {
		final String sql = getQuery("OUMRAREA_MAINT_REG_ONCHECKDELETEMASTER");
		final RowMapper<Areas> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Areas.class, areasMapping);
		return (ArrayList<Areas>) namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * maintAreaOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<Areas> maintAreaOnCheckDeleteMaster(final Areas paramBean) {
		final String sql = getQuery("OUMRAREA_MAINT_AREA_ONCHECKDELETEMASTER");
		final RowMapper<Areas> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Areas.class, areasMapping);
		return (ArrayList<Areas>) namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Areas
	 *
	 * @return List<Areas>
	 *
	 * @throws SQLException
	 */
	 public List<Areas> maintSubAreaExecuteQuery(final Areas objSearchDao) {
		final String sql = getQuery("OUMRAREA_MAINTSUBAREA_FIND_AREAS");
		final RowMapper<Areas> AreasRowMapper = Row2BeanRowMapper.makeMapping(sql, Areas.class, areasMapping);
		return (ArrayList<Areas>) namedParameterJdbcTemplate.query(sql,
				createParams("parentAreaCode", objSearchDao.getParentAreaCode()), AreasRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstAreas
	 *            List<Areas>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer maintSubAreaInsertAreas(final List<Areas> lstAreas) {
		 final String sql = getQuery("OUMRAREA_MAINTSUBAREA_INSERT_AREAS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Areas areas : lstAreas) {
			parameters.add(new BeanPropertySqlParameterSource(areas));
		}
	 	 returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAreas.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAreas
	 *            List<Areas>
	 *
	 * @throws SQLException
	 */
	public Areas maintSubAreaUpdateAreas(final List<Areas> lstAreas) {
		final Areas returnData = new Areas();
		final String sql = getQuery("OUMRAREA_MAINTSUBAREA_UPDATE_AREAS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Areas areas : lstAreas) {
			parameters.add(new BeanPropertySqlParameterSource(areas));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage().toUpperCase();
			if(error.contains("AREAS_PK")) {
				returnData.setErrorMessage("Error : " +error);
			} 
				else if (error.contains("AREAS_AREAS_FK")){
				returnData.setSealFlag("AREA_CODE");
				returnData.setServerCode(2292);
			}
		}
		
		if (lstAreas.size() == returnArray.length) {
			returnData.setReturnValue(1);
			return returnData;
		} else {
			returnData.setReturnValue(0);	
			return returnData;
			}

	}

}

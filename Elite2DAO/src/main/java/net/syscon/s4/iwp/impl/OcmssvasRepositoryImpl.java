package net.syscon.s4.iwp.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.CourseActivityAreas;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.iwp.OcmssvasRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OcmssvasRepositoryImpl
 */
@Repository
public class OcmssvasRepositoryImpl extends RepositoryBase implements OcmssvasRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmssvasRepositoryImpl.class.getName());

	/**
	 * Creates new OcmssvasRepositoryImpl class Object
	 */
	public OcmssvasRepositoryImpl() {
		/* OcmssvasRepositoryImpl */
	}

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 				new FieldMapper("code"))
			.put("AREA_CODE", 			new FieldMapper("areaCode"))
			.put("DESCRIPTION", 		new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> areaMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 				new FieldMapper("code"))
			.put("AREA_CODE", 			new FieldMapper("areaCode"))
			.put("AREA_CLASS", 			new FieldMapper("areaClass"))
			.put("DESCRIPTION", 		new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> courseActivityAreasMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CRS_ACTY_ID", 		new FieldMapper("crsActyId"))
			.put("AREA_CODE", 			new FieldMapper("areaCode"))
			.put("CREATE_DATETIME", 	new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", 		new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", 	new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", 		new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", 			new FieldMapper("sealFlag"))
			.build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao CourseActivityAreas
	 *
	 * @return List<CourseActivityAreas>
	 *
	 */
	public List<CourseActivityAreas> cActAExecuteQuery(final CourseActivityAreas objSearchDao) {
		final String sql = getQuery("OCMSSVAS_CACTA_FIND_COURSE_ACTIVITY_AREAS");
		final RowMapper<CourseActivityAreas> courseActivityMapper = Row2BeanRowMapper.makeMapping(sql,
				CourseActivityAreas.class, courseActivityAreasMapping);
		List<CourseActivityAreas> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("crsActyId", objSearchDao.getCrsActyId()),
					courseActivityMapper);
		} catch (Exception e) {
			logger.error("cActAExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstCourseActivityAreas List<CourseActivityAreas>
	 *
	 * @return List<Integer>
	 *
	 */
	@Override
	public CourseActivityAreas cActAInsertCourseActivityAreas(final List<CourseActivityAreas> lstCourseActivityAreas) {
		final String sql = getQuery("OCMSSVAS_CACTA_INSERT_COURSE_ACTIVITY_AREAS");
		int[] returnArray = new int[] {};
		CourseActivityAreas returnData = new CourseActivityAreas();
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final CourseActivityAreas courseActivityAreas : lstCourseActivityAreas) {
			parameters.add(new BeanPropertySqlParameterSource(courseActivityAreas));
		}try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage().toUpperCase();
			if (error.contains("COURSE_ACTIVITY_AREAS_PK")) {
//				error = error.substring(error.indexOf("OMS_OWNER."), error.indexOf(")")).replaceFirst("OMS_OWNER.", "");
//				final String tableName = errorNameValidation(error);
	//			error = error.substring(error.indexOf("constraint"), error.indexOf("Detail"))
			//			.replaceFirst("constraint", "").trim();
	//			final String tableName = errorNameValidation( error.substring(1, error.length()-1));
				
				returnData.setSealFlag("area_code");
				returnData.setSeqOne(BigDecimal.valueOf(2292));
				return returnData;
			}
		}
		if (lstCourseActivityAreas.size() == returnArray.length) {
			returnData.setSealFlag("1");
			return returnData;
		} else {
			returnData.setSealFlag("0");
			return returnData;
		}

	}
	
	@Override
	public CourseActivityAreas cActADeleteCourseActivityAreas(final List<CourseActivityAreas> lstCourseActivityAreas) {
		final String sql = getQuery("OCMSSVAS_CACTA_DELETE_COURSE_ACTIVITY_AREAS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		CourseActivityAreas returnData = new CourseActivityAreas();
		for (final CourseActivityAreas courseActivityAreas : lstCourseActivityAreas) {
			parameters.add(new BeanPropertySqlParameterSource(courseActivityAreas));
		}
		try {
			String tableName = "COURSE_ACTIVITY_AREAS";
			String whereCondition = "CRS_ACTY_ID=:crsActyId AND AREA_CODE=:areaCode";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("constraint")) {
//				error = error.substring(error.indexOf("OMS_OWNER."), error.indexOf(")")).replaceFirst("OMS_OWNER.", "");
//				final String tableName = errorNameValidation(error);

				error =  error.substring(error.indexOf("constraint"), error.indexOf("\" on"))
						.replaceFirst("constraint", "").trim();
					final String tableName = errorNameValidation( error.substring(1, error.length()));
				returnData.setSealFlag(tableName);
				returnData.setSeqOne(BigDecimal.valueOf(2292));
				return returnData;
			}
		}
		if (lstCourseActivityAreas.size() == returnArray.length) {
			returnData.setSealFlag("1");
			return returnData;
		} else {
			returnData.setSealFlag("0");
			return returnData;
		}
	}


	

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgAreaClassRecordGroup() {
		final String sql = getQuery("OCMSSVAS_FIND_RGAREACLASS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgAreaClassRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<Areas> rgAreaRegionRecordGroup(final String areaType, final String areaClass) {
		final String sql = getQuery("OCMSSVAS_FIND_RGAREAREGION");
		final RowMapper<Areas> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Areas.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("areaType", areaType, "areaClass", areaClass),
					mRowMapper);
		} catch (Exception e) {
			logger.error("rgAreaRegionRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public String rgAreaClassRecordGroup(final String areaType, final String areaCod) {
			String openAnAccount = null;
			Map<String, Object> returnObject = null;
			final Map<String, Object> inParamMap = new HashMap<String, Object>();
			SqlParameter[] sqlParameters = new SqlParameter[10];
			sqlParameters = new SqlParameter[] { new SqlParameter("P_AREA_CODE", OracleTypes.VARCHAR),
					new SqlOutParameter("P_AREA_TYPE_DESCRIPTION", OracleTypes.VARCHAR),
					new SqlOutParameter("P_AREA_CODE_DESCRIPTION", OracleTypes.VARCHAR)};
			SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
					.withCatalogName("TAG_SERVICE").withProcedureName("GET_COURSE_ACTIVITY_AREA_DESC")
					.declareParameters(sqlParameters);
			inParamMap.put("P_AREA_CODE", areaCod);
			final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
			try {
				returnObject = simpleJDBCCall.execute(inParameter);
				openAnAccount = String.valueOf(returnObject.get("P_AREA_TYPE_DESCRIPTION"));
				if (openAnAccount == "null") {
					openAnAccount = "TRUE";
				}
			} catch (Exception e) {
				logger.error("getGroupPrivilege :" + e);
			}
			return openAnAccount;
		}
	
	
	public String errorNameValidation(final String errorName) {
		final String sql = getQuery("OCMSSVAS_CONSTRAINT_VALIDATIONS");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("CONSTRAINTNAME", errorName),
					String.class);
		} catch (Exception e) {
			returnData = null;
			return returnData;
		}
		return returnData;
	}

}

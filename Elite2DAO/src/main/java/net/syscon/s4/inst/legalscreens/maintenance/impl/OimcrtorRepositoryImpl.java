package net.syscon.s4.inst.legalscreens.maintenance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.IwpTemplateObjects;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.im.beans.OrderTypes;
import net.syscon.s4.inst.legalscreens.maintenance.OimcrtorRepository;

/**
 * Class OimcrtorRepositoryImpl
 */
@Repository
public class OimcrtorRepositoryImpl extends RepositoryBase implements OimcrtorRepository {

	/**
	 * Creates new OimcrtorRepositoryImpl class Object
	 */
	public OimcrtorRepositoryImpl() {
		/**
		 * OimcrtorRepositoryImpl
		 */
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimcrtorRepositoryImpl.class);
	private final Map<String, FieldMapper> iwpTemplateObjectsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("IWP_TEMPLATE_OBJECT_ID", new FieldMapper("iwpTemplateObjectId"))
			.put("OBJECT_TYPE", new FieldMapper("objectType")).put("OBJECT_CODE", new FieldMapper("objectCode"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> orderTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("NO_OF_HOLD_DAYS", new FieldMapper("noOfHoldDays"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("SCHEDULE_FLAG", new FieldMapper("scheduleFlag")).put("ORDER_TYPE", new FieldMapper("orderType"))
			.put("CUSTODY_DAYS", new FieldMapper("custodyDays")).put("CASELOAD_TYPE", new FieldMapper("caseloadType"))
			.put("ORDER_CATEGORY", new FieldMapper("orderCategory")).put("CHARGES_FLAG", new FieldMapper("chargesFlag"))
			.put("TIME_SENSITIVE_FLAG", new FieldMapper("timeSensitiveFlag"))
			.put("SEVERITY_RANK", new FieldMapper("severityRank")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("UPDATE_ALLOWED_FLAG", new FieldMapper("updateAllowedFlag"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("YOUTH_ORDER_FLAG", new FieldMapper("youthOrderFlag"))
			.put("CUSTODY_FLAG", new FieldMapper("custodyFlag")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OrderTypes
	 *
	 * @return List<OrderTypes>
	 */
	public List<OrderTypes> orderTypesExecuteQuery(final OrderTypes objSearchDao) {
		final String sql = getQuery("OIMCRTOR_ORDERTYPES_FIND_ORDER_TYPES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		List<OrderTypes> returnList;
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE");
			if (objSearchDao.getOrderType() != null && !objSearchDao.getOrderType().trim().equals("")) {
				sqlQuery.append(" ORDER_TYPE  = :orderType and");
				params.addValue("orderType", objSearchDao.getOrderType());
			}
			if (objSearchDao.getDescription() != null && !objSearchDao.getDescription().trim().equals("")) {
				sqlQuery.append(" DESCRIPTION = :description and");
				params.addValue("description", objSearchDao.getDescription());
			}
			if (objSearchDao.getOrderCategory() != null && !objSearchDao.getOrderCategory().equals("")) {
				sqlQuery.append(" ORDER_CATEGORY  = :orderCategory and");
				params.addValue("orderCategory", objSearchDao.getOrderCategory());
			}
			if (objSearchDao.getCustodyDays() != null) {
				sqlQuery.append(" CUSTODY_DAYS  = :custodyDays and");
				params.addValue("custodyDays", objSearchDao.getCustodyDays());
			}
			if (objSearchDao.getListSeq() != null) {
				sqlQuery.append(" LIST_SEQ = :listSeq and");
				params.addValue("listSeq", objSearchDao.getListSeq());
			}
			if (objSearchDao.getActiveFlag() != null) {
				sqlQuery.append(" ACTIVE_FLAG  = :activeFlag and");
				params.addValue("activeFlag", objSearchDao.getActiveFlag());
			}
			if (objSearchDao.getExpiryDate() != null) {
				sqlQuery.append(" EXPIRY_DATE  = :expiryDate");
				params.addValue("expiryDate", objSearchDao.getExpiryDate());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql
				.concat(" ORDER BY case ACTIVE_flag  when 'Y' then 1 when 'N' then 2 end, ORDER_TYPE ASC");
		final RowMapper<OrderTypes> orderTypesRowMapper = Row2BeanRowMapper.makeMapping(preparedSql, OrderTypes.class,
				orderTypesMapping);
		returnList = namedParameterJdbcTemplate.query(preparedSql, params, orderTypesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOrderTypes List<OrderTypes>
	 *
	 * @return List<Integer>
	 */
	public Integer orderTypesInsertOrderTypes(final List<OrderTypes> lstOrderTypes) {
		final String sql = getQuery("OIMCRTOR_ORDERTYPES_INSERT_ORDER_TYPES");
		int[] returnArray = null;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OrderTypes orderTypes : lstOrderTypes) {
			parameters.add(new BeanPropertySqlParameterSource(orderTypes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			if (e.getMessage().contains("unique")) {
				return 2;
			} else {
				return 0;
			}
		}
		if (lstOrderTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOrderTypes List<OrderTypes>
	 */
	public Integer orderTypesUpdateOrderTypes(final List<OrderTypes> lstOrderTypes) {
		final String sql = getQuery("OIMCRTOR_ORDERTYPES_UPDATE_ORDER_TYPES");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OrderTypes orderTypes : lstOrderTypes) {
			parameters.add(new BeanPropertySqlParameterSource(orderTypes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOrderTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao IwpTemplateObjects
	 *
	 * @return List<IwpTemplateObjects>
	 */
	public List<IwpTemplateObjects> iwpTemplateObjectsExecuteQuery(final IwpTemplateObjects objSearchDao) {
		final String sql = getQuery("OIMCRTOR_IWPTEMPLATEOBJECTS_FIND_IWP_TEMPLATE_OBJECTS");
		List<IwpTemplateObjects> returnList;
		final RowMapper<IwpTemplateObjects> iwpTemplateObjectsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				IwpTemplateObjects.class, iwpTemplateObjectsMapping);
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("objectcode", objSearchDao.getObjectCode(), "p_template_id", objSearchDao.getTemplateId()),
				iwpTemplateObjectsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstIwpTemplateObjects List<IwpTemplateObjects>
	 *
	 * @return List<Integer>
	 */
	public Integer iwpTemplateObjectsInsertIwpTemplateObjects(final List<IwpTemplateObjects> lstIwpTemplateObjects) {
		final String sql = getQuery("OIMCRTOR_IWPTEMPLATEOBJECTS_INSERT_IWP_TEMPLATE_OBJECTS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final IwpTemplateObjects iwpTemplateObjects : lstIwpTemplateObjects) {
			parameters.add(new BeanPropertySqlParameterSource(iwpTemplateObjects));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstIwpTemplateObjects.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstIwpTemplateObjects List<IwpTemplateObjects>
	 *
	 */
	public Integer iwpTemplateObjectsUpdateIwpTemplateObjects(final List<IwpTemplateObjects> lstIwpTemplateObjects) {
		final String sql = getQuery("OIMCRTOR_IWPTEMPLATEOBJECTS_UPDATE_IWP_TEMPLATE_OBJECTS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final IwpTemplateObjects iwpTemplateObjects : lstIwpTemplateObjects) {
			parameters.add(new BeanPropertySqlParameterSource(iwpTemplateObjects));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstIwpTemplateObjects.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstIwpTemplateObjects List<IwpTemplateObjects>
	 *
	 */
	public Integer iwpTemplateObjectsDeleteIwpTemplateObjects(final List<IwpTemplateObjects> lstIwpTemplateObjects) {
		final String sql = getQuery("OIMCRTOR_IWPTEMPLATEOBJECTS_DELETE_IWP_TEMPLATE_OBJECTS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final IwpTemplateObjects iwpTemplateObjects : lstIwpTemplateObjects) {
			parameters.add(new BeanPropertySqlParameterSource(iwpTemplateObjects));
		}
		try {
			batchUpdatePreDeletedRows("IWP_TEMPLATE_OBJECTS", "IWP_TEMPLATE_OBJECT_ID  = :iwpTemplateObjectId", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in iwpTemplateObjectsDeleteIwpTemplateObjects"+e);
		}
		
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstIwpTemplateObjects.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<IwpTemplates> rgTemplateRecordGroup() {
		final String sql = getQuery("OIMCRTOR_FIND_RGTEMPLATE");
		final RowMapper<IwpTemplates> mRowMapper = Row2BeanRowMapper.makeMapping(sql, IwpTemplates.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
	}

	public String getAttchedTemplateCode(final BigDecimal templateId) {
		final String sql = getQuery("OIMCRTOR_GET_TMEPLATE_CODE");
		String retObj=null;
		try {
			retObj= namedParameterJdbcTemplate.queryForObject(sql, createParams("templateId", templateId), String.class);
		}catch (Exception e) {
			logger.error(e);
		}
		return retObj;
	}

	@Override
	public BigDecimal getTemplateId(final String templateName) {
		final String sql = getQuery("OIMCRTOR_GET_TMEPLATE_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("templateName", templateName),
				BigDecimal.class);
	}

	@Override
	public BigDecimal getNextIwpId() {
		final String sql = getQuery("OIMCRTOR_GET_NEXT_IWPID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
	}

	@Override
	public Integer iwpTempOnCheckDeleteMaster(final BigDecimal templateId) {
		final String sql = getQuery("OIMCRTOR_IWP_TEMP_OBJ_ONCHECKDELETEMASTER");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("templateId", templateId), Integer.class);
	}
}

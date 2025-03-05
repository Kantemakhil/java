package net.syscon.s4.inst.securitythreatgroups.impl;

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
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.securitythreatgroups.OimtgngsRepository;

/**
 * Class OimtgngsRepositoryImpl
 * 
 */
@Repository
public class OimtgngsRepositoryImpl extends RepositoryBase implements OimtgngsRepository {



	private final Map<String, FieldMapper> securityThreatGroupsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STG_ID", 						new FieldMapper("stgId"))
			.put("STG_LEVEL", 					new FieldMapper("stgLevel"))
			.put("STG_CODE", 					new FieldMapper("stgCode"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("PARENT_STG_ID", 				new FieldMapper("parentStgId"))
			.put("LIST_SEQ", 					new FieldMapper("listSeq"))
			.put("EFFECTIVE_DATE", 				new FieldMapper("effectiveDate"))
			.put("HISTORY_TEXT", 				new FieldMapper("historyText"))
			.put("ACTIVE_FLAG", 				new FieldMapper("activeFlag"))
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime"))
			.put("EXPIRY_DATE", 				new FieldMapper("expiryDate"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.build();
	private static Logger logger = LogManager.getLogger(OimtgngsRepositoryImpl.class.getName());

	
	/**
	 * Creates new OimtgngsRepositoryImpl class Object
	 */
	public OimtgngsRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SecurityThreatGroups
	 *
	 * @return List<SecurityThreatGroups>
	 *
	 * 
	 */
	public List<SecurityThreatGroups> secGrpExecuteQuery(final SecurityThreatGroups objSearchDao) {
		final String sql = getQuery("OIMTGNGS_SECGRP_FIND_SECURITY_THREAT_GROUPS");
		final RowMapper<SecurityThreatGroups> securityThreatGroupsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, securityThreatGroupsMapping);
		List<SecurityThreatGroups> returnList = new ArrayList<SecurityThreatGroups>();
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE PARENT_STG_ID IS NULL AND ");

			if (objSearchDao.getStgCode() != null) {
				sqlQuery.append("STG_CODE = :STG_CODE" + " AND ");
				inParameterSource.addValue("STG_CODE", objSearchDao.getStgCode());
			}
			if (objSearchDao.getStgId() != null) {
				sqlQuery.append("STG_ID = :STG_ID" + " AND ");
				inParameterSource.addValue("STG_ID", objSearchDao.getStgId());
			}
			if (objSearchDao.getDescription() != null) {
				sqlQuery.append(" LOWER(DESCRIPTION) = LOWER(:DESCRIPTION)" + " AND ");
				inParameterSource.addValue("DESCRIPTION", objSearchDao.getDescription());
			}
			if (objSearchDao.getListSeq() != null) {
				sqlQuery.append(" LIST_SEQ = :LIST_SEQ");
				inParameterSource.addValue("LIST_SEQ", objSearchDao.getListSeq());

			}
			preparedSql = sqlQuery.toString().trim();
			if (preparedSql.endsWith("WHERE")) {
				preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
			}
			if (preparedSql.endsWith("AND")) {
				preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
			}
			preparedSql = preparedSql.concat(" ORDER BY LIST_SEQ, STG_CODE");

			returnList = namedParameterJdbcTemplate.query(preparedSql, inParameterSource,
					securityThreatGroupsRowMapper);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstSecurityThreatGroups
	 *            List<SecurityThreatGroups>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer secGrpInsertSecurityThreatGroups(final List<SecurityThreatGroups> lstSecurityThreatGroups) {
		final String sql = getQuery("OIMTGNGS_SECGRP_INSERT_SECURITY_THREAT_GROUPS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SecurityThreatGroups securityThreatGroups : lstSecurityThreatGroups) {
			parameters.add(new BeanPropertySqlParameterSource(securityThreatGroups));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error(this.getClass().getName() + " error in refDmnUpdateReferenceDomains " + e);
			if(e!=null && e.getMessage()!=null && e.getMessage().contains("stg_uk1")) {
				return 18;
			}
		}
		if (lstSecurityThreatGroups.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstSecurityThreatGroups
	 *            List<SecurityThreatGroups>
	 *
	 * 
	 */
	public Integer secGrpUpdateSecurityThreatGroups(final List<SecurityThreatGroups> lstSecurityThreatGroups) {
		final String sql = getQuery("OIMTGNGS_SECGRP_UPDATE_SECURITY_THREAT_GROUPS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SecurityThreatGroups securityThreatGroups : lstSecurityThreatGroups) {
			parameters.add(new BeanPropertySqlParameterSource(securityThreatGroups));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error(this.getClass().getName() + " error in secGrpUpdateSecurityThreatGroups " + e);
		}
		if (lstSecurityThreatGroups.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SecurityThreatGroups
	 *
	 * @return List<SecurityThreatGroups>
	 *
	 * 
	 */
	public List<SecurityThreatGroups> secGrp1ExecuteQuery(final SecurityThreatGroups objSearchDao) {
		final String sql = getQuery("OIMTGNGS_SECGRP1_FIND_SECURITY_THREAT_GROUPS");
		final RowMapper<SecurityThreatGroups> SecurityThreatGroupsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, securityThreatGroupsMapping);
		final ArrayList<SecurityThreatGroups> returnList = (ArrayList<SecurityThreatGroups>) namedParameterJdbcTemplate
				.query(sql, createParams("parentStgId", objSearchDao.getParentStgId()), SecurityThreatGroupsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstSecurityThreatGroups
	 *            List<SecurityThreatGroups>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer secGrp1InsertSecurityThreatGroups(final List<SecurityThreatGroups> lstSecurityThreatGroups) {
		final String sql = getQuery("OIMTGNGS_SECGRP1_INSERT_SECURITY_THREAT_GROUPS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SecurityThreatGroups securityThreatGroups : lstSecurityThreatGroups) {
			parameters.add(new BeanPropertySqlParameterSource(securityThreatGroups));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSecurityThreatGroups.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstSecurityThreatGroups
	 *            List<SecurityThreatGroups>
	 *
	 * 
	 */
	public Integer secGrp1UpdateSecurityThreatGroups(final List<SecurityThreatGroups> lstSecurityThreatGroups) {
		final String sql = getQuery("OIMTGNGS_SECGRP_UPDATE_SECURITY_THREAT_GROUPS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SecurityThreatGroups securityThreatGroups : lstSecurityThreatGroups) {
			parameters.add(new BeanPropertySqlParameterSource(securityThreatGroups));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSecurityThreatGroups.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * secGrp1OnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<SecurityThreatGroups> secGrp1OnCheckDeleteMaster(final SecurityThreatGroups paramBean) {
		final String sql = getQuery("OIMTGNGS_SEC_GRP1_ONCHECKDELETEMASTER");
		final RowMapper<SecurityThreatGroups> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, securityThreatGroupsMapping);
		final ArrayList<SecurityThreatGroups> returnList = (ArrayList<SecurityThreatGroups>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * secGrp1WhenNewRecordInstance
	 *
	 * @param params
	 *
	 */
	public List<SecurityThreatGroups> secGrp1WhenNewRecordInstance(final SecurityThreatGroups paramBean) {
		final String sql = getQuery("OIMTGNGS_SEC_GRP1_WHENNEWRECORDINSTANCE");
		final RowMapper<SecurityThreatGroups> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, securityThreatGroupsMapping);
		final ArrayList<SecurityThreatGroups> returnList = (ArrayList<SecurityThreatGroups>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is used to execute the data base tables based on
	 * 
	 * @param lstSecurityThreatGroups
	 *            List<SecurityThreatGroups>
	 * 
	 * 
	 */

	public List<SecurityThreatGroups> secGrp2ExecuteQuery(final SecurityThreatGroups searchBean) {
		final String sql = getQuery("OIMTGNGS_SECGRP2EXECUTEQUERY");
		final RowMapper<SecurityThreatGroups> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, securityThreatGroupsMapping);
		final ArrayList<SecurityThreatGroups> returnList = (ArrayList<SecurityThreatGroups>) namedParameterJdbcTemplate
				.query(sql, createParams("parentStgId", searchBean.getParentStgId()), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is used to get the next value data base Dual
	 */
	public Integer getNextValue() {
		final String sql = getQuery("OIMTGNGS_GETNEXTVALUE");
		Integer nextVal = null;
		nextVal = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		return nextVal;
	}

	/**
	 * This method is used to get the next value data base Dual
	 */
	public Integer getGangNextVal() {
		final String sql = getQuery("OIMTGNGS_GETGANGNEXTVAL");
		Integer nextVal = null;
		nextVal = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		return nextVal;
	}

	/**
	 * This method is used to get the next value data base Dual
	 */
	public Integer getSetNextVal() {
		final String sql = getQuery("OIMTGNGS_GETSETNEXTVAL");
		Integer nextVal = null;
		nextVal = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		return nextVal;
	}

	/**
	 * This method is used to insert the data base tables based on
	 * 
	 * @param insertList
	 *            List<SecurityThreatGroups>
	 * 
	 * 
	 */
	public Integer secGrp2InsertSecurityThreatGroups(final List<SecurityThreatGroups> insertList) {
		final String sql = getQuery("OIMTGNGS_SECGRP2INSERTSECURITYTHREATGROUPS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SecurityThreatGroups securityThreatGroups : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(securityThreatGroups));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 * 
	 * @param updateList
	 *            List<SecurityThreatGroups>
	 * 
	 * 
	 */

	public Integer secGrp2UpdateSecurityThreatGroups(final List<SecurityThreatGroups> updateList) {
		final String sql = getQuery("OIMTGNGS_SECGRP_UPDATE_SECURITY_THREAT_GROUPS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SecurityThreatGroups securityThreatGroups : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(securityThreatGroups));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to get duplicate STG code
	 * 
	 * @param stgCode
	 * 
	 * 
	 */
	public Integer getDuplicateStgCode(final String stgCode) {
		final String sql = getQuery("OIMTGNGS_GETDUPLICATESTGCODE");
		Integer count = null;
		count = namedParameterJdbcTemplate.queryForObject(sql, createParams("stgCode", stgCode), Integer.class);
		return count;
	}

	/**
	 * This method is used to get Profile Value STG code
	 */
	public Integer getLpValue() {
		final String sql = getQuery("OIMTGNGS_GET_LPVALUE");
		Integer count = null;
		count = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		return count;
	}

	/**
	 * This method is used to get lv_gang Value StgId
	 */
	public Integer getLpGang(final Integer stgId) {
		final String sql = getQuery("OIMTGNGS_GET_LV_GANG");
		Integer count = null;
		count = namedParameterJdbcTemplate.queryForObject(sql, createParams("stgId", stgId), Integer.class);
		return count;
	}

	/**
	 * This method is used to get lv_set Value StgId
	 */
	public Integer getLpSet(final Integer parentStgId) {
		final String sql = getQuery("OIMTGNGS_GET_LPSET");
		Integer count = null;
		count = namedParameterJdbcTemplate.queryForObject(sql, createParams("stgId", parentStgId), Integer.class);
		return count;
	}

	/**
	 * This method is used to get lv_set Value StgId
	 */
	public Integer getDuplicateGangsStgCode(final String stgCode) {
		final String sql = getQuery("OIMTGNGS_GETDUPLICATEGANGSSTGCODE");
		Integer count = null;
		count = namedParameterJdbcTemplate.queryForObject(sql, createParams("stgCode", stgCode), Integer.class);
		return count;
	}

	/**
	 * This method is used to get lv_set Value StgId
	 */
	public Integer getDuplicateSetsStgCode(final String stgCode) {
		final String sql = getQuery("OIMTGNGS_GETDUPLICATESETSSTGCODE");
		Integer count = null;
		count = namedParameterJdbcTemplate.queryForObject(sql, createParams("stgCode", stgCode), Integer.class);
		return count;
	}

	/**
	 * This method is used to get lv_set Value StgId
	 */
	public Integer offStgCur(final Integer STG_ID) {
		final String sql = getQuery("OIMTGNGS_OFF_STG_CUR_SEC_GRP");
		Integer count = null;
		count = namedParameterJdbcTemplate.queryForObject(sql, createParams("STG_ID", STG_ID), Integer.class);
		return count;
	}

	/**
	 * This method is used to get countValue
	 */
	public Integer offStgCurSecGrp(final Integer STG_ID) {
		final String sql = getQuery("OIMTGNGS_OFF_STG_CUR_SEC_GRP_ONE");
		Integer count = null;
		count = namedParameterJdbcTemplate.queryForObject(sql, createParams("STG_ID", STG_ID), Integer.class);
		return count;
	}
}

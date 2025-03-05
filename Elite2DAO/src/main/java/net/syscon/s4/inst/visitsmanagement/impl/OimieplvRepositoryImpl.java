package net.syscon.s4.inst.visitsmanagement.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.visitsmanagement.OimieplvRepository;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;

@Repository
public class OimieplvRepositoryImpl extends RepositoryBase implements OimieplvRepository {

	private final Map<String, FieldMapper> iepLeveRowMapper = new ImmutableMap.Builder<String, FieldMapper>()
			.put("IEP_LEVEL_CODE", new FieldMapper("iepLevelCode"))
			.put("IEP_LEVEL_DESCRIPTION", new FieldMapper("iepLeveldescription"))
			.put("LIST_SEQ", new FieldMapper("sequence")).put("REVIEW_DAYS", new FieldMapper("reviewDays"))
			.put("INTAKE_IEP", new FieldMapper("intakeIpe")).put("CANTEEN_LIMITS", new FieldMapper("canteenLimit"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.build();

	private static Logger logger = LogManager.getLogger(OimieplvRepositoryImpl.class.getName());

	@Override
	public Integer insertIepLevelRecord(List<IepLevelBean> insertList) {
		String sql = getQuery("OIMIEPLV_INSERT_IEP_LEVEL_CODES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final IepLevelBean iepLevelBean : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(iepLevelBean));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in insertIepLevelRecord method :: " + e);
		}
		return (insertList.size() == returnArray.length) ? 1 : 0;
	}

	@Override
	public Integer updateIepLevelRecord(List<IepLevelBean> updateList) {


		String sql = getQuery("OIMIEPLV_UPDATE_IEP_LEVEL_CODES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final IepLevelBean iepLevelBean : updateList) {
				parameters.add(new BeanPropertySqlParameterSource(iepLevelBean));
		}
					try {
						returnArray = namedParameterJdbcTemplate.batchUpdate(sql,
								parameters.toArray(new SqlParameterSource[0]));
					} catch (Exception e) {
						logger.error(getClass().getName() + " error in updateIepLevelRecord method :: " + e);
					}

		return (updateList.size() == returnArray.length) ? 1 : 0;
	}


	@Override
	public Integer deleteIepLevelRecord(List<IepLevelBean> deleteList) {
		String sql = getQuery("OIMIEPLV_DELETE_IEP_LEVEL_BY_IEP_LEVEL_CODE");
		int[] returnArray = new int[] {};
		int count = 0;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final IepLevelBean iepLevelBean : deleteList) {
			String checkDelete = getQuery("OIMIEPLV_CHECK_UPDATE_OR_DELETE_ACTIVE_FALG");
			count = namedParameterJdbcTemplate.queryForObject(checkDelete,
					createParams("iepLevelCode", iepLevelBean.getIepLevelCode()), Integer.class);
			if (count == 0) {
				parameters.add(new BeanPropertySqlParameterSource(iepLevelBean));
				try {
					String tableName = "incentives_earn_privs";
					String whereCondition = "iep_level_code=:iepLevelCode";
					batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
				} catch (Exception e) {
					logger.error(e);
				}
				try {
					returnArray = namedParameterJdbcTemplate.batchUpdate(sql,
							parameters.toArray(new SqlParameterSource[0]));
				} catch (Exception e) {
					logger.error(getClass().getName() + " error in deleteIepLevelRecord method :: " + e);
				}
			} else
				return 3;
		}

		return (deleteList.size() == returnArray.length) ? 1 : 0;

	}

	@Override
	public List<IepLevelBean> getAllIepLevelCodes() {
		List<IepLevelBean> returnList = new ArrayList<IepLevelBean>();

		String sql = getQuery("OIMIEPLV_GET_ALL_IEP_LEVEL_CODES");
		final RowMapper<IepLevelBean> rowMapper = Row2BeanRowMapper.makeMapping(sql, IepLevelBean.class,
				iepLeveRowMapper);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), rowMapper);
		} catch (Exception e) {
			returnList = Collections.emptyList();
			logger.error(getClass().getName() + " error in getAllIepLevelCodes method :: " + e);
		}

		return returnList;
	}

	@Override
	public String getSystemProfileValue() {
		String sql = getQuery("OIMIEPLV_GET_PROFILE_VALUE");
		String resultValue = null;
		try {
			resultValue = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in getSystemProfileValue method :: " + e);
		}
		return resultValue;
	}
}

package net.syscon.s4.sa.admin.impl;

import java.util.ArrayList;
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
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.admin.OumrelmdRepository;

@Repository
public class OumrelmdRepositoryImpl extends RepositoryBase implements OumrelmdRepository {

	private final static Logger logger = LogManager.getLogger(OumrelmdRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> omsMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("MODULE_NAME", new FieldMapper("moduleName"))
			.put("CODE", new FieldMapper("code")).build();

	@Override
	public List<OmsModules> getRelatedModulesByModuleName(String moduleName) {
		List<OmsModules> relatedModulesList = new ArrayList<OmsModules>();
		final String sql = getQuery("OUMRELMD_SELECT_BY_MODULE_NAME");
		final RowMapper<OmsModules> rowMap = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, omsMap);
		try {
			relatedModulesList = namedParameterJdbcTemplate.query(sql, createParams("moduleName", moduleName), rowMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getRelatedModulesByModuleName" + e);
		}
		return relatedModulesList;
	}

	@Override
	public List<OmsModules> getRelatedModulesLov(String moduleName) {
		List<OmsModules> relatedModulesList = new ArrayList<OmsModules>();
		final String sql = getQuery("OUMRELMD_RELATED_MODULE_LOV");
		final RowMapper<OmsModules> rowMap = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, omsMap);
		try {
			relatedModulesList = namedParameterJdbcTemplate.query(sql, createParams(), rowMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getRelatedModulesByModuleName" + e);
		}
		return relatedModulesList;
	}

	@Override
	public Integer insertRelatedModules(List<OmsModules> insertList) {
		String sql = getQuery("OUMRELMD_INSERT_RELATED_MODULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OmsModules omsModules : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(omsModules));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in insertRelatedModules method :: " + e);
		}
		return (insertList.size() == returnArray.length) ? 1 : 0;
	}

	@Override
	public Integer updateRelatedModules(List<OmsModules> updateList) {

		String sql = getQuery("OUMRELMD_UPDATE_RELATED_MODULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OmsModules omsModules : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(omsModules));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in updateRelatedModules method :: " + e);
		}
		return (updateList.size() == returnArray.length) ? 1 : 0;

	}

	@Override
	public Integer deleteRelatedModules(List<OmsModules> deleteList) {

		String sql = getQuery("OUMRELMD_DELETE_RELATED_MODULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OmsModules omsModules : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(omsModules));
		}
		try {
			String tableName = "MODULE_ACCESSIBLE_FORMS";
			String whereClause = "module_name=:moduleName and  access_module_name=:accessModuleName";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteRelatedModules", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in deleteRelatedModules method :: " + e);
		}
		return (deleteList.size() == returnArray.length) ? 1 : 0;
	}

}

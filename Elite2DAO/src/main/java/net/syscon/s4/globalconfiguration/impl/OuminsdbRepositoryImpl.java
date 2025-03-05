package net.syscon.s4.globalconfiguration.impl;

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
import net.syscon.s4.common.beans.ModuleInsDashboardBean;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globalconfiguration.OuminsdbRepository;

@Repository
public class OuminsdbRepositoryImpl extends RepositoryBase implements OuminsdbRepository{
	
	private static Logger logger = LogManager.getLogger(OuminsdbRepositoryImpl.class.getName());
	
	public OuminsdbRepositoryImpl() {
	}
	
	private final Map<String, FieldMapper> moduleInsDashboardMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODULE",					new FieldMapper("module"))
			.put("DASHBOARD", 				new FieldMapper("dashboard"))
			.put("ACTIVE_FLAG",				new FieldMapper("activeFlag"))
			.put("OFFENDE_SPECIFIC_FLAG",	new FieldMapper("offendeSpecificFlag"))
			.put("CREATE_DATETIME",         new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID",          new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME",         new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID",          new FieldMapper("modifyUserId")) 
			.build();

	@Override
	public List<ModuleInsDashboardBean> getInsModDashboard() {
		final String sql = getQuery("OUMINSDB_GET_INS_MOD_DASHBOARD");
		ArrayList<ModuleInsDashboardBean> returnList = new ArrayList<ModuleInsDashboardBean>();
		try {
			final RowMapper<ModuleInsDashboardBean> quickActionsRowMapper = Row2BeanRowMapper.makeMapping(sql, ModuleInsDashboardBean.class,
					moduleInsDashboardMapping);
			returnList = (ArrayList<ModuleInsDashboardBean>) namedParameterJdbcTemplate.query(sql,
					createParams(), quickActionsRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getInsModDashboard and Exception is : {}", e.getMessage());
			e.printStackTrace();
		}
		return returnList;
	}

	@Override
	public Integer insModDashboardInsert(List<ModuleInsDashboardBean> insertList) {
		int result = 0;
		String sql = getQuery("OUMINSDB_INS_MOD_DASHBOARD_INSERT");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (final ModuleInsDashboardBean moduleInsDashboardBean : insertList) {
				parameters.add(new BeanPropertySqlParameterSource(moduleInsDashboardBean));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (insertList.size() == returnArray.length) {
				result = 1;
			} else {
				result = 0;
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " InsModDashboardInsert and Exception is : {}", e.getMessage());
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	@Override
	public Integer updateInsdbFlag(List<ModuleInsDashboardBean> moduleInsList) {
		final String sql = getQuery("OUMINSDB_UPDATE_INSDB_FLAG");
		int result = 0;
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (final ModuleInsDashboardBean moduleInsDashboardBean : moduleInsList) {
				parameters.add(new BeanPropertySqlParameterSource(moduleInsDashboardBean));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (moduleInsList.size() == returnArray.length) {
				result = 1;
			} else {
				result = 0;
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " updateInsdbFlag and Exception is : {}", e.getMessage());
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	@Override
	public Integer insModDashboardUpdate(List<ModuleInsDashboardBean> updateList) {
		int result = 0;
		String sql = getQuery("OUMINSDB_INS_MOD_DASHBOARD_UPDTAE");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (final ModuleInsDashboardBean moduleInsDashboardBean : updateList) {
				parameters.add(new BeanPropertySqlParameterSource(moduleInsDashboardBean));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (updateList.size() == returnArray.length) {
				result = 1;
			} else {
				result = 0;
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " insModDashboardUpdate and Exception is : {}", e.getMessage());
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	@Override
	public Integer insModDashboardDelete(List<ModuleInsDashboardBean> deleteList) {
		int result = 0;
		String sql = getQuery("OUMINSDB_INS_MOD_DASHBOARD_DELETE");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (final ModuleInsDashboardBean moduleInsDashboardBean : deleteList) {
				parameters.add(new BeanPropertySqlParameterSource(moduleInsDashboardBean));
			}
			try {
				String tableName = "OMS_MODULE_INS_DASHBOARD";
				String whereClause = "MODULE = :module";
				batchUpdatePreDeletedRows(tableName, whereClause , parameters);
			} catch (Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " in method insModDashboardDelete", e);
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (deleteList.size() == returnArray.length) {
				result = 1;
			} else {
				result = 0;
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " insModDashboardDelete and Exception is : {}", e.getMessage());
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	@Override
	public ModuleInsDashboardBean getInsDashboardId(String moduleName) {
		final String sql = getQuery("OUMINSDB_GET_INS_DASHBOARD_ID");
		final RowMapper<ModuleInsDashboardBean> quickActionsRowMapper = Row2BeanRowMapper.makeMapping(sql, ModuleInsDashboardBean.class,
				moduleInsDashboardMapping);
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("moduleName", moduleName), quickActionsRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getInsDashboardId and Exception is : {}", e.getMessage());
			return new ModuleInsDashboardBean();
		}
	}
	
	@Override
	public Integer postDelete(List<ModuleInsDashboardBean> deleteList) {
		int result = 0;
		String sql = getQuery("OUMINSDB_POST_DELETE");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (final ModuleInsDashboardBean moduleInsDashboardBean : deleteList) {
				parameters.add(new BeanPropertySqlParameterSource(moduleInsDashboardBean));
			}
			try {
				String tableName = "OMS_MODULES";
				String whereClause = "MODULE_NAME = :module";
				batchUpdatePreDeletedRows(tableName, whereClause , parameters);
			} catch (Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " in method postDelete", e);
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (deleteList.size() == returnArray.length) {
				result = 1;
			} else {
				result = 0;
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " postDelete and Exception is : {}", e.getMessage());
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	@Override
	public Integer postInsert(List<ModuleInsDashboardBean> insertList) {
		int result = 0;
		String sql = getQuery("OUMINSDB_POST_INSERT");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (final ModuleInsDashboardBean moduleInsDashboardBean : insertList) {
				parameters.add(new BeanPropertySqlParameterSource(moduleInsDashboardBean));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (insertList.size() == returnArray.length) {
				result = 1;
			} else {
				result = 0;
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " InsModDashboardInsert and Exception is : {}", e.getMessage());
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	@Override
	public Integer offenderSpecificModuleInsert(ModuleInsDashboardBean moduleData) {
		int result = 0;
		String sql = getQuery("OFFENDER_SPECIFIC_MOODULE_INSERT");
		try {
			result=namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(moduleData));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " offenderSpecificModuleInsert and Exception is : {}"+ e.getMessage());
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	@Override
	public Integer offenderSpecificModuleDelete(String moduleName) {
		int result = 0;
		String sql = getQuery("OFFENDER_SPECIFIC_MOODULE_DELETE");
		try {
			result=namedParameterJdbcTemplate.update(sql, createParams("moduleName",moduleName));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " offenderSpecificModuleDelete and Exception is : {}"+ e.getMessage());
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	


}

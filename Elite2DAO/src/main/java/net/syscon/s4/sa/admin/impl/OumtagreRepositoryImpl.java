package net.syscon.s4.sa.admin.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AuditLog;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.admin.OumtagreRepository;
import net.syscon.s4.sa.admin.beans.ModuleTabColumns;
import net.syscon.s4.sa.admin.beans.ModuleTables;

@Repository
public class OumtagreRepositoryImpl extends RepositoryBase implements OumtagreRepository {

     private static Logger logger = LogManager.getLogger(OumtagreRepositoryImpl.class.getName());
    private final Map<String, FieldMapper> omsMap = new ImmutableMap.Builder<String, FieldMapper>()
            .put("DESCRIPTION", new FieldMapper("description"))
            .put("MODULE_NAME", new FieldMapper("moduleName"))
            .build();
    private final Map<String, FieldMapper> mtMap = new ImmutableMap.Builder<String, FieldMapper>()
            .put("MODULE_NAME", new FieldMapper("moduleName")).build();
    private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
            .put("TABLE_NAME", new FieldMapper("tableName"))
            .put("MODULE_NAME", new FieldMapper("moduleName"))
            .put("COLUMN_NAME", new FieldMapper("columnName"))
            .put("DESCRIPTION", new FieldMapper("description"))
            .put("CODE", new FieldMapper("code")).build();

    /**
     * Creates new OumtagreRepositoryImpl class Object
     */
    public OumtagreRepositoryImpl() {
    }

    /**
     * Fetch the records from database table
     *
     * @param objSearchDao OmsModules
     * @return List<OmsModules>
     * @throws SQLException
     */
    @Override
    public List<OmsModules> rleInarcExecuteQuery(final OmsModules objSearchDao) {
        final String sql = getQuery("OUMTAGRE_RLEINARC_FIND_OMS_MODULES");
        final RowMapper<OmsModules> omsRowMap = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, omsMap);
        return namedParameterJdbcTemplate.query(sql,
                createParams(), omsRowMap);
    }

    /**
     * Fetch the records from database table
     *
     * @param objSearchDao ModuleTables
     * @return List<ModuleTables>
     * @throws SQLException
     */
    @Override
    public List<ModuleTables> moduleTablesExecuteQuery(final ModuleTables objSearchDao) {
        final String sql = getQuery("OUMTAGRE_MODULETABLES_FIND_MODULE_TABLES");
        final RowMapper<ModuleTables> rowMap = Row2BeanRowMapper.makeMapping(sql, ModuleTables.class,
                mtMap);
        return namedParameterJdbcTemplate.query(sql,
                createParams("moduleName", objSearchDao.getModuleName()), rowMap);
    }

    /**
     * This method is used to insert the records in the data base tables based on
     *
     * @param lstModuleTables List<ModuleTables>
     * @return List<Integer>
     * @throws SQLException
     */
    @Override
    public ModuleTables moduleTablesInsertModuleTables(final List<ModuleTables> lstModuleTables) {
        final String sql = getQuery("OUMTAGRE_MODULETABLES_INSERT_MODULE_TABLES");
        ModuleTables returnObj=new ModuleTables();
        int[] returnArray;
        final List<SqlParameterSource> parameters = new ArrayList<>();
        for (final ModuleTables moduleTables : lstModuleTables) {
            parameters.add(new BeanPropertySqlParameterSource(moduleTables));
        }
        returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

        if (lstModuleTables.size() == returnArray.length) {
        	returnObj.setSealFlag("1");
        } else {
        	returnObj.setSealFlag("0");
        }
        return returnObj;
    }

    /**
     * This method is used to update the data base tables based on
     *
     * @param lstModuleTables List<ModuleTables>
     * @throws SQLException
     */
    @Override
    public ModuleTables moduleTablesUpdateModuleTables(final List<ModuleTables> lstModuleTables) {
        final String sql = getQuery("OUMTAGRE_MODULETABLES_UPDATE_MODULE_TABLES");
        int[] returnArray;
        ModuleTables returnObj=new ModuleTables();
        final List<SqlParameterSource> parameters = new ArrayList<>();
        for (final ModuleTables moduleTables : lstModuleTables) {
            parameters.add(new BeanPropertySqlParameterSource(moduleTables));
        }
        returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
        if (lstModuleTables.size() == returnArray.length) {
        	returnObj.setSealFlag("1");
        } else {
        	returnObj.setSealFlag("0");
        }
        return returnObj;

    }

    /**
     * This method is used to delete records from data base tables based on
     *
     * @param lstModuleTables List<ModuleTables>
     * @throws SQLException
     */
    @Override
    public ModuleTables moduleTablesDeleteModuleTables(final List<ModuleTables> lstModuleTables) {
        final String sql = getQuery("OUMTAGRE_MODULETABLES_DELETE_MODULE_TABLES");
        int[] returnArray;
        ModuleTables returnObj=new ModuleTables();
        final List<SqlParameterSource> parameters = new ArrayList<>();
        for (final ModuleTables moduleTables : lstModuleTables) {
            parameters.add(new BeanPropertySqlParameterSource(moduleTables));
        }
        try {
			String tableName = "MODULE_TABLES";
			String whereClause = "MODULE_TAB_ID = :moduleTabId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method moduleTablesDeleteModuleTables", e);
		}
        returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
        if (lstModuleTables.size() == returnArray.length) {
        	returnObj.setSealFlag("1");
        } else {
        	returnObj.setSealFlag("0");
        }
        return returnObj;


    }

    /**
     * Fetch the records from database table
     *
     * @param objSearchDao ModuleTabColumns
     * @return List<ModuleTabColumns>
     * @throws SQLException
     */
    @Override
    public List<ModuleTabColumns> moduleTabColumnsExecuteQuery(final ModuleTabColumns objSearchDao) {
        final String sql = getQuery("OUMTAGRE_MODULETABCOLUMNS_FIND_MODULE_TAB_COLUMNS");
        final RowMapper<ModuleTabColumns> rowMap = Row2BeanRowMapper.makeMapping(sql,
                ModuleTabColumns.class, mMapping);
        return namedParameterJdbcTemplate
                .query(sql, createParams("moduleTabId", objSearchDao.getModuleTabId()), rowMap);
    }

    /**
     * This method is used to insert the records in the data base tables based on
     *
     * @param lstModTabCol List<ModuleTabColumns>
     * @return List<Integer>
     * @throws SQLException
     */
    @Override
    public Integer moduleTabColumnsInsertModuleTabColumns(final List<ModuleTabColumns> lstModTabCol) {
        final String sql = getQuery("OUMTAGRE_MODULETABCOLUMNS_INSERT_MODULE_TAB_COLUMNS");
        int[] returnArray;
        final List<SqlParameterSource> parameters = new ArrayList<>();
        for (final ModuleTabColumns moduleTabColumns : lstModTabCol) {
            parameters.add(new BeanPropertySqlParameterSource(moduleTabColumns));
        }
        returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

        if (lstModTabCol.size() == returnArray.length) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * This method is used to update the data base tables based on
     *
     * @param lstModTabCol List<ModuleTabColumns>
     * @throws SQLException
     */
    @Override
    public Integer moduleTabColumnsUpdateModuleTabColumns(final List<ModuleTabColumns> lstModTabCol) {
        final String sql = getQuery("OUMTAGRE_MODULETABCOLUMNS_UPDATE_MODULE_TAB_COLUMNS");
        int[] returnArray;
        final List<SqlParameterSource> parameters = new ArrayList<>();
        for (final ModuleTabColumns moduleTabColumns : lstModTabCol) {
            parameters.add(new BeanPropertySqlParameterSource(moduleTabColumns));
        }
        returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
        if (lstModTabCol.size() == returnArray.length) {
            return 1;
        } else {
            return 0;
        }

    }

    /**
     * This method is used to delete records from data base tables based on
     *
     * @param lstModTabCol List<ModuleTabColumns>
     * @throws SQLException
     */
    @Override
    public Integer moduleTabColumnsDeleteModuleTabColumns(final List<ModuleTabColumns> lstModTabCol) {
        final String sql = getQuery("OUMTAGRE_MODULETABCOLUMNS_DELETE_MODULE_TAB_COLUMNS");
        int[] returnArray;
        final List<SqlParameterSource> parameters = new ArrayList<>();
        for (final ModuleTabColumns moduleTabColumns : lstModTabCol) {
            parameters.add(new BeanPropertySqlParameterSource(moduleTabColumns));
        }
        try {
			String tableName = "MODULE_TAB_COLUMNS";
			String whereClause = "MODULE_TAB_ID = :moduleTabId and MODULE_TAB_SEQ = :moduleTabSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method moduleTabColumnsDeleteModuleTabColumns", e);
		}
        returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
        if (lstModTabCol.size() == returnArray.length) {
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
    @Override
    public List<ReferenceCodes> rgModuleNameRecordGroup() {
        final String sql = getQuery("OUMTAGRE_FIND_RGMODULENAME");
         final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

        try {
            return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Exception :", e);
            return Collections.emptyList();
        }
    }

    /**
     * Used to capture results from select query
     *
     * @return List<M>
     */
    @Override
    public List<ReferenceCodes> rgObjectNameRecordGroup() {
        final String sql = getQuery("OUMTAGRE_FIND_RGOBJECTNAME");
        final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

        try {
            return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Exception :", e);
            return Collections.emptyList();
        }
    }

    /**
     * Used to capture results from select query
     *
     * @return List<M>
     */
    @Override
    public List<ReferenceCodes> rgColumnNameRecordGroup(final String objectName, final String modTabId) {
        final String sql = getQuery("OUMTAGRE_FIND_RGCOLUMNNAME");
        final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

        try {
            return namedParameterJdbcTemplate.query(sql, createParams("objectName", objectName, "modTabId", modTabId), mRowMapper);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Exception :", e);
            return Collections.emptyList();
        }
    }

    /**
     * Used to capture results from select query
     *
     * @return List<M>
     */
    @Override
    public List<OmsModules> rgSetupModuleRecordGroup() {
        final String sql = getQuery("OUMTAGRE_FIND_RGSETUPMODULE");
        final RowMapper<OmsModules> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mMapping);

        try {
            return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Exception :", e);
            return Collections.emptyList();
        }
    }

    /**
     * This method is execute a sql query when trigger event is called
     * <p>
     * oumtagrePreInsert
     *
     * @param moduleTabId
     */
    @Override
    public Long moduleTabColumnsPreInsert(final Long moduleTabId) {
        final String sql = getQuery("OUMTAGRE_MODULE_TAB_COLUMNS_PREINSERT");
        return namedParameterJdbcTemplate.queryForObject(sql, createParams("moduleTabId", moduleTabId), Long.class);
    }

    /**
     * This method is execute a sql query when trigger event is called
     * <p>
     * oumtagrePreInsert
     */
    @Override
    public Long moduleTabPreInsert() {
        final String sql = getQuery("OUMTAGRE_MODULE_TAB_PREINSERT");
        return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
    }

	@Override
	public ModuleTables enableAuditOnTable(String tableName) {
		ModuleTables returnData=new ModuleTables();
		String preparedSql = "CREATE TRIGGER log_elite_generic_trigger AFTER INSERT OR DELETE OR UPDATE ON oms_owner.:tableName FOR EACH ROW EXECUTE FUNCTION oms_owner.elite_generic_log()";
		preparedSql = preparedSql.replace(":tableName", tableName);
		try {
			namedParameterJdbcTemplate.execute(preparedSql, new PreparedStatementCallback<Boolean>() {

				@Override
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					return ps.execute();
				}

			});
		} catch (Exception e) {
			logger.error("error in enableAuditOnTable"+e.getMessage());
			String error = "Error : " + e.getMessage();
			if (error.contains("already exists")) {
				returnData.setSealFlag("log_elite_generic_trigger for relation"+" "+tableName+" "+"already exists");
				
			}else {
				returnData.setSealFlag("0");
			}
			return returnData;
		}
		returnData.setSealFlag("1");
		return returnData;

	}

	@Override
	public ModuleTables deleteAuditOnTable(String tableName) {
		String preparedSql = "DROP TRIGGER IF EXISTS log_elite_generic_trigger ON oms_owner.:tableName";
		preparedSql = preparedSql.replace(":tableName", tableName);
		ModuleTables returnData=new ModuleTables();
		try {
			namedParameterJdbcTemplate.execute(preparedSql, new PreparedStatementCallback<Boolean>() {

				@Override
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
					return ps.execute();
				}

			});
		} catch (Exception e) {
			logger.error("error in deleteAuditOnTable"+e.getMessage());
			returnData.setSealFlag("0");
			return returnData;
		}
		returnData.setSealFlag("1");
		return returnData;
	}

	@Override
	public Integer validateTriggerExist(String tableName) {
		Integer triggerCount = 0;
		final String sql = getQuery("VALIDATE_TRIGGER_EXIST");
		try {
			triggerCount = namedParameterJdbcTemplate.queryForObject(sql, createParams("tableName", tableName),
					Integer.class);
		} catch (final Exception e) {
			logger.error("Error in validateTriggerExist"+e.getMessage());
		}
		return triggerCount;
	}

	
	@Override
	public ModuleTables insertTableAuditDetails(List<ModuleTables> listObj) {
		 ModuleTables returnObj=new ModuleTables();
		final String sql = getQuery("MAINTAIN_TABLE_AUDIT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ModuleTables list : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (EmptyResultDataAccessException e) {
			logger.error("OiiobalxRepositoryImpl::  error in insertTableAuditDetails  "+ e.getMessage());
		}
		if (listObj.size() == returnArray.length) {
			returnObj.setSealFlag("1");
		} else {
			returnObj.setSealFlag("0");
		}
		return returnObj;
	}

	@Override
	public ModuleTables updateTableAuditDetails(List<ModuleTables> listObj) {
		final String sql = getQuery("UPDATE_TABLE_AUDIT");
		ModuleTables returnObj=new ModuleTables();
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ModuleTables list : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("OiiobalxRepositoryImpl::  error in updateTableAuditDetails  "+ e.getMessage());
		}
		if (listObj.size() == returnArray.length) {
			returnObj.setSealFlag("1");
		} else {
			returnObj.setSealFlag("0");
		}
		return returnObj;
	}

	@Override
	public List<ModuleTables> auditTablesExecuteQuery() {
        final String sql = getQuery("GET_AUDIT_TABLES_DATA");
        final RowMapper<ModuleTables> rowMap = Row2BeanRowMapper.makeMapping(sql, ModuleTables.class,
                mtMap);
        return namedParameterJdbcTemplate.query(sql,
                createParams(), rowMap);
    }

	@Override
	public ModuleTables insertTableAuditData(ModuleTables obj) {
		 final String sql = getQuery("MAINTAIN_TABLE_AUDIT");
			int returnArray = 0 ;
			ModuleTables returnObj=new ModuleTables();
			SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(obj);
			try {
				returnArray = namedParameterJdbcTemplate.update(sql, sqlParam);
			} catch (Exception e) {
				logger.error("OiiobalxRepositoryImpl::  error in insertTableAuditData  "+ e.getMessage());
				
			}
			
			if (returnArray > 0) {
				returnObj.setSealFlag("1");
			} else {
				returnObj.setSealFlag("0");
			}
			return returnObj;
	}

	@Override
	public ModuleTables updateTableAuditData(ModuleTables obj) {
		 final String sql = getQuery("UPDATE_TABLE_AUDIT");
			int returnArray = 0 ;
			ModuleTables returnObj=new ModuleTables();
			SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(obj);
			try {
				returnArray = namedParameterJdbcTemplate.update(sql, sqlParam);
			} catch (Exception e) {
				logger.error("OiiobalxRepositoryImpl::  error in updateTableAuditData  "+ e.getMessage());
				
			}
			
			if (returnArray > 0) {
				returnObj.setSealFlag("1");
			} else {
				returnObj.setSealFlag("0");
			}
			return returnObj;
	}

	@Override
	public List<ModuleTables> getModuleNamesAssociatedWithTable(ModuleTables obj) {
		List<ModuleTables> returnList=null;
        final String sql = getQuery("VALIDATE_TABLE_ASSOCIATION");
        final RowMapper<ModuleTables> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ModuleTables.class, mMapping);;
        try {
        	 returnList= namedParameterJdbcTemplate.query(sql,
                     createParams("tableName",obj.getObjectName()), mRowMapper);
        	
        }catch(Exception e) {
        	
        }
       return returnList;
    }

	@Override
	public Integer getTableAuditRecord(String tableName) {
		Integer triggerCount = 0;
		final String sql = getQuery("GET_AUDIT_TABLES_RECORD_COUNT");
		try {
			triggerCount = namedParameterJdbcTemplate.queryForObject(sql, createParams("tableName", tableName),
					Integer.class);
		} catch (final Exception e) {
			logger.error("Error in getTableAuditRecord"+e.getMessage());
		}
		return triggerCount;
	}

	@Override
	public List<ModuleTables> getTablesDescription() {
		List<ModuleTables> returnList=null;
		final String sql = getQuery("GET_TABLE_COMMENTS");
    final RowMapper<ModuleTables> rowMap = Row2BeanRowMapper.makeMapping(sql, ModuleTables.class,
            mtMap);
    try {
    	returnList=namedParameterJdbcTemplate.query(sql, createParams(), rowMap);
    }catch(final Exception e) {
    	logger.error("Error in getTablesDescription"+e.getMessage());
    }
    return returnList;
	}
	@Override
	public String getViewAuditFlag(String moduleName) {
		final String sql = getQuery("OUMTAGRE_GET_MODULE_VIEW_AUDIT_FLAG");
		String auditFlag = "N";
		try {
			auditFlag = namedParameterJdbcTemplate.queryForObject(sql, createParams("moduleName", moduleName), String.class);
		} catch (Exception e) {
			logger.error("In getEnableAuditFlag: {} ", e.getMessage());
		}
		return auditFlag;
	}
	
	@Override
	public int updateViewAuditFlag(final AuditLog auditLog) {
		final String sql = getQuery("OUMTAGRE_UPDATE_OMS_MODULES_VIEW_AUDIT_FLAG");
		Integer returnArray = null;
		try {
			returnArray = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(auditLog));
		} catch (Exception e) {
			logger.error("In updateViewAuditFlag : {} ", e.getMessage());
			return 0;
		}
		if (returnArray == 0) {
			return 0;
		}
		return 1;

	}
}

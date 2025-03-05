package net.syscon.s4.sa.admin.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.SystemLables;
import net.syscon.s4.sa.admin.OumsylabRepository;

@Repository
public class OumsylabRepositoryImpl extends RepositoryBase implements OumsylabRepository {

    private static Logger logger = LogManager.getLogger(OumsylabRepositoryImpl.class.getName());

    private final Map<String, FieldMapper> omsMap = new ImmutableMap.Builder<String, FieldMapper>()
            .put("DESCRIPTION", new FieldMapper("description"))
            .put("MODULE_NAME", new FieldMapper("moduleName"))
            .build();
    private final Map<String, FieldMapper> sysProfMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE",              new FieldMapper("profileCode"))
			.put("PROFILE_TYPE",              new FieldMapper("profileType"))
			.put("CREATE_USER_ID",            new FieldMapper("createUserId"))
			.put("SEAL_FLAG",                 new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",           new FieldMapper("createDateTime"))
			.put("MODIFY_USER_ID",            new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME",            new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE",             new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME",           new FieldMapper("modifyDateTime"))
			.put("PROFILE_VALUE_2",           new FieldMapper("profileValue2"))
			.put("DESCRIPTION",               new FieldMapper("description"))
			.build();
    private final Map<String, FieldMapper> systemLableMapping = new ImmutableMap.Builder<String, FieldMapper>()
			 .put("LABEL_ID"    ,  new FieldMapper("labelId"))
			 .put("MODULE_NAME" ,  new FieldMapper ("moduleName"))
			 .put("MSG_KEY",    	new FieldMapper("msgKey"))
			 .put("MSG_VALUE",    new FieldMapper ("msgValue"))
			 .put("MSG_TYPE", 		new FieldMapper("msgType")) 
			 .put("CREATE_DATETIME", new FieldMapper("createDateTime"))   
			 .put("CREATE_USER_ID",   new FieldMapper("createUserId"))  
			 .put("MODIFY_DATETIME",  new FieldMapper ("modifyDateTime"))  
			 .put("MODIFY_USER_ID",   new FieldMapper ("modifyUserId")).build();

    /**
     * Creates new OumrestaRepositoryImpl class Object
     */
    public OumsylabRepositoryImpl() {
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
        final String sql = getQuery("OUMRESTA_RLEINARC_FIND_OMS_MODULES");
        final RowMapper<OmsModules> rowMap = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, omsMap);
        return namedParameterJdbcTemplate.query(sql,
                createParams(), rowMap);
    }

	@Override
	public List<SystemLables> labelExecuteQuery(String codeInput) {
		 final String sql = getQuery("OUMSYLAB_EXECUTE_SYSTEM_LABEL");
	        final RowMapper<SystemLables> rowMap = Row2BeanRowMapper.makeMapping(sql, SystemLables.class, systemLableMapping);
	        return namedParameterJdbcTemplate.query(sql,createParams("moduleName",codeInput), rowMap);
	}
	@Override
	public List<SystemLables> labelByKeyExecuteQuery(String msgKey) {
		 final String sql = getQuery("OUMSYLAB_EXECUTE_BYKEY_SYSTEM_LABEL");
	        final RowMapper<SystemLables> rowMap = Row2BeanRowMapper.makeMapping(sql, SystemLables.class, systemLableMapping);
	        return namedParameterJdbcTemplate.query(sql,createParams("msgKey",msgKey), rowMap);
	}
	

	@Override
	public int updateSystemlabel(List<SystemLables> updateList) {
		final String sql = getQuery("OUMSYLAB_LABEL_UPDATE_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SystemLables list : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<SystemLables> getPropertiesFromDb() {
		 final String sql = getQuery("OUMSYLAB_EXECUTE_ALL_SYSTEM_LABEL");
	        final RowMapper<SystemLables> rowMap = Row2BeanRowMapper.makeMapping(sql, SystemLables.class, systemLableMapping);
	        return namedParameterJdbcTemplate.query(sql,createParams(), rowMap);
	}
	
public Integer getLabelId() {
		
		final String sql = getQuery("OUMSYLAB_SET_LABLE_ID");
		final Integer returnObj = namedParameterJdbcTemplate.queryForObject(sql,createParams(),Integer.class);
		return returnObj;
	}

	@Override
	public Integer setSystemLables(SystemLables systemlable) {
		final String sql = getQuery("OUMSYLAB_SET_SYSTEM_LABLES");
		systemlable.setLabelId(getLabelId());
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(systemlable));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			} catch (DataIntegrityViolationException | UncategorizedSQLException e) {
				return 10;
			} catch (Exception e) {
				logger.error("returnArray.length +",returnArray.length+"Exception"+e);
			}
			if (returnArray.length>0) {
				return 1;
			} else {
				return 0;
			}
	}

	@Override
	public List<SystemProfiles> getSystemProfiles() {
		 final String sql = getQuery("OUMSYLAB_EXECUTE_SYSTEM_PROFILE");
	        final RowMapper<SystemProfiles> rowMap = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class, sysProfMapping);
	        return namedParameterJdbcTemplate.query(sql,createParams(), rowMap);
	}

	@Override
	public Integer setSystemProfileToSystemlabels(SystemLables systemLablesTemp) {
		final String sql = getQuery("OUMSYLAB_SET_SYSTEM_LABLES");
		systemLablesTemp.setLabelId(getLabelId());
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(systemLablesTemp));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			} catch (DataIntegrityViolationException | UncategorizedSQLException e) {
				return 10;
			} catch (Exception e) {
				logger.error("returnArray.length +",returnArray.length+"Exception"+e);
				e.printStackTrace();
			}
			if (returnArray.length>0) {
				return 1;
			} else {
				return 0;
			}
	}

	@Override
	public Integer countOfLabel() {
		final String sql = getQuery("OUMSYLAB_COUNT_LABELS");
		final Integer returnObj = namedParameterJdbcTemplate.queryForObject(sql,createParams(),Integer.class);
		return returnObj;
	}

	@Override
	public Integer countOfProfile() {
		final String sql = getQuery("OUMSYLAB_COUNT_PROFILE");
		final Integer returnObj = namedParameterJdbcTemplate.queryForObject(sql,createParams(),Integer.class);
		return returnObj;
	}

	@Override
	public List<SystemLables> getSystemLabel(int startIndex, int lastIndex) {
		final String sql = getQuery("OUMSYLAB_GET_ALL_SYSTEM_LABEL");
        final RowMapper<SystemLables> rowMap = Row2BeanRowMapper.makeMapping(sql, SystemLables.class, systemLableMapping);
        return namedParameterJdbcTemplate.query(sql,createParams("startIndex",startIndex,"lastIndex",lastIndex), rowMap);
	}



}

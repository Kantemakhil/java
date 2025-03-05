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
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.BaseHelpUrl;
import net.syscon.s4.im.beans.OmsModulesHelp;
import net.syscon.s4.sa.admin.OmshelpRepository;

@Repository
public class OmshelpRepositoryImpl extends RepositoryBase implements OmshelpRepository {

    private static Logger logger = LogManager.getLogger(OmshelpRepositoryImpl.class.getName());

    private final Map<String, FieldMapper> omsHelpMap = new ImmutableMap.Builder<String, FieldMapper>()
            .put("DESCRIPTION", new FieldMapper("description"))
            .put("HELP_TYPE", new FieldMapper("helpType"))
            .put("HELP_URL", new FieldMapper("helpUrl"))
            .put("HELP_DESC", new FieldMapper("helpDesc"))
            .put("ROW_ID", new FieldMapper("rowId"))
            .build();
    private final Map<String, FieldMapper> baseHelpurlMapping = new ImmutableMap.Builder<String, FieldMapper>()
    		.put("ID", 						new FieldMapper("id"))
    		.put("BASE_HELP_PDF_URL", 		new FieldMapper("baseHelpPdfUrl"))
			.put("BASE_HELP_VIDEO_URL", 	new FieldMapper("baseHelpVideoUrl"))
			.put("BASE_HELP_HTML_URL",     new FieldMapper("baseHelpHtmlUrl"))
    		.put("STATUS", 					new FieldMapper("status"))
    		.build();

    /**
     * Creates new OumrestaRepositoryImpl class Object
     */
    
    public OmshelpRepositoryImpl() {
    }

    /**
     * Fetch the records from database table
     *
     * @param objSearchDao OmsModules
     * @return List<OmsModules>
     * @throws SQLException
     */

	
	static java.sql.Date getCurrentDatetime() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}
	

	@Override
	public List<OmsModulesHelp> moduleHelpExecuteQuery() {
		 final String sql = getQuery("OUMSYLAB_EXECUTE_ALL_OMSMODULEHELP");
	        final RowMapper<OmsModulesHelp> rowMap = Row2BeanRowMapper.makeMapping(sql, OmsModulesHelp.class, omsHelpMap);
	        return namedParameterJdbcTemplate.query(sql,createParams(), rowMap);
	}

	@Override
	public int moduleHelpInsertCommit(List<OmsModulesHelp> insertList) {
		
		final String sql = getQuery("OMSHELP_INSERT_VALUES");
		
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OmsModulesHelp list : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int moduleHelpUpdateCommit(List<OmsModulesHelp> updateList) {
		final String sql = getQuery("OMSHELP_UPDATE_VALUES");
		
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OmsModulesHelp list : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int moduleHelpDeleteCommit(List<OmsModulesHelp> deleteList) {
		final String sql = getQuery("OMSHELP_DELETE_VALUES");
		
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OmsModulesHelp list : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			String tableName = "OMS_MODULES_HELP";
			String whereClause = "MODULE_NAME=:moduleName AND HELP_TYPE=:helpType AND  HELP_URL=:helpUrl";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method moduleHelpDeleteCommit", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int insertBaseUrl(BaseHelpUrl commitBean) {
		final String sql = getQuery("BASEURL_HELP_INSERT");
		int[] returnArray = new int[] {};
		String rep="";
		commitBean.setId(getBaseUrlId());
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(commitBean));
		BaseHelpUrl  searchBean= new BaseHelpUrl();
		if(commitBean !=null && commitBean.getPid()!=null) {
			searchBean.setId(commitBean.getPid());
			rep=setInactive(searchBean);
		}else if(commitBean !=null && commitBean.getPid()==null){
			rep="SUCCESS";
		}
		try {
			if(rep.equals("SUCCESS")) {
				returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			}
			} catch (DataIntegrityViolationException | UncategorizedSQLException e) {
				logger.error("error in insertBaseUrl"+e.getMessage());
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
	
	private String setInactive(BaseHelpUrl baseHelpUrl) {
		final String sql = getQuery("OMSHELP_INACTIVE_STATUS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(baseHelpUrl));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			} catch (DataIntegrityViolationException | UncategorizedSQLException e) {
				return "";
			} catch (Exception e) {
				logger.error("returnArray.length +",returnArray.length+"Exception"+e);
				e.printStackTrace();
			}
			if (returnArray.length>0) {
				return "SUCCESS";
			} else {
				return "FAIL";
			}
	}
	
public Integer getBaseUrlId() {
		final String sql = getQuery("BASE_HELP_URl_ID");
		final Integer returnObj = namedParameterJdbcTemplate.queryForObject(sql,createParams(),Integer.class);
		return returnObj;
	}

	@Override
	public BaseHelpUrl urlExecuteQuery() {
		final String sql = queryBuilderFactory.getQueryBuilder(getQuery("OMSHELP_EXECUTE_QUERY"),baseHelpurlMapping ).build();
		final RowMapper<BaseHelpUrl> imagesRowMapper = Row2BeanRowMapper.makeMapping(sql, BaseHelpUrl.class, baseHelpurlMapping);
		String preparedSql = null;
		List<BaseHelpUrl> im =new ArrayList<>();
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		preparedSql = sqlQuery.toString().trim();
		im= namedParameterJdbcTemplate.query(preparedSql, inParameterSource, imagesRowMapper);
		return im.get(0);
	}




}

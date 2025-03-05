package net.syscon.s4.inst.casemanagement.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.IwpDocuments;
import net.syscon.s4.im.beans.IwpTemplateModules;
import net.syscon.s4.im.beans.IwpTemplateObjects;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.inst.casemanagement.OiuiwpveRepository;
import oracle.jdbc.OracleTypes;
/**
 * Class OiuiwpveRepositoryImpl
 */
@Repository
public class OiuiwpveRepositoryImpl extends RepositoryBase implements OiuiwpveRepository{

	private Logger log=LogManager.getLogger(OiuiwpveRepositoryImpl.class);

private final Map<String, FieldMapper> iwpDocumentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("MODULE_NAME", 						new FieldMapper("moduleName"))
.put("TEMPLATE_ID", 						new FieldMapper("templateId"))
.put("'Y'", 						new FieldMapper(" 'y' "))
.put("P_SUB_TYPE", 						new FieldMapper("pSubType"))
.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
.put("TEMPLATE_NAME", 						new FieldMapper(" templateName"))
.put("P_TYPE", 						new FieldMapper("pType"))
.put("OFFENDER_BOOK_ID", 						new FieldMapper("offenderBookId"))
.put("P_OBJECT_TYPE", 						new FieldMapper("pObjectType"))
.build();
private final Map<String, FieldMapper> iwpTemplatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("MODULE_NAME", 						new FieldMapper("moduleName"))
.put("TEMPLATE_ID", 						new FieldMapper("templateId"))
.put("'Y'", 						new FieldMapper(" 'y' "))
.put("P_SUB_TYPE", 						new FieldMapper("pSubType"))
.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
.put("TEMPLATE_NAME", 						new FieldMapper("templateName"))
.put("P_TYPE", 						new FieldMapper("pType"))
.put("OFFENDER_BOOK_ID", 						new FieldMapper("offenderBookId"))
.put("P_OBJECT_TYPE", 						new FieldMapper("pObjectType"))
.build();
private final Map<String, FieldMapper> iwpTemplateObjectsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("ACTIVE_FLA", 						new FieldMapper("activeFla"))
.put("'N')", 						new FieldMapper(" 'n') "))
.put("P_TYPE", 						new FieldMapper("pType"))
.build();
private final Map<String, FieldMapper> iwpTemplateModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 						new FieldMapper(" description"))
.put("MODULE_NAME", 						new FieldMapper("moduleName"))
.put("TEMPLATE_ID", 						new FieldMapper(" templateId"))
.put("P_SUB_TYPE", 						new FieldMapper("pSubType"))
.put("ACTIVE_FLAG", 						new FieldMapper(" activeFlag"))
.put("TEMPLATE_NAME", 						new FieldMapper(" templateName"))
.put("P_TYPE", 						new FieldMapper("pType"))
.put("OFFENDER_BOOK_ID", 						new FieldMapper("offenderBookId"))
.put("P_OBJECT_TYPE", 						new FieldMapper("pObjectType"))
.put("BLOCK_NAME", 						new FieldMapper("blockName"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("TEMPLATE_NAME", 						new FieldMapper("templateName "))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("TEMPLATE_ID", 						new FieldMapper("templateId"))
.put("CODE", 						new FieldMapper("code"))
.build();

/**
 * Creates new OiuiwpveRepositoryImpl class Object 
 */
public OiuiwpveRepositoryImpl() {
	//OiuiwpveRepositoryImpl
}
/**
* Fetch the records from database table
*
* @param objSearchDao IwpDocuments
*
* @return List<IwpDocuments>
*
*/
 public List<IwpDocuments> iwpDocExecuteQuery(final IwpDocuments objSearchDao) {
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		 List<IwpDocuments> lstDocuments = new ArrayList<IwpDocuments>();
		if (objSearchDao != null) {
			if (objSearchDao.getOffenderBookId() != null && objSearchDao.getTemplateId() != null) {
				final String sql = getQuery("OIUIWPVE_IWPDOC_FIND_IWP_DOCUMENTS");
				final RowMapper<IwpDocuments> iwpDocRowMapper = Row2BeanRowMapper.makeMapping(sql, IwpDocuments.class,iwpDocumentsMapping);
				sqlQuery.append(sql);
				sqlQuery.append(" where ");
				sqlQuery.append(
						"OFFENDER_BOOK_ID  = :offenderBookId and TEMPLATE_ID = :templateId ORDER BY DATE_MODIFIED DESC, DOCUMENT_NAME ");
				valuesList.addValue("offenderBookId", objSearchDao.getOffenderBookId());
				valuesList.addValue("templateId", objSearchDao.getTemplateId());
				preparedSql = sqlQuery.toString().trim();
				lstDocuments = ( List<IwpDocuments>)namedParameterJdbcTemplate.query(preparedSql, valuesList, iwpDocRowMapper);
			}
		}
		return lstDocuments;
} 
/**
* @param 
*
* @throws SQLException 
*
*/
public int preInsert() {
return 0;
}

/**
* This method is used to update the data base tables based on
*
* @param lstIwpDocuments List<IwpDocuments>
*
* @throws SQLException
*/
 public Integer iwpDocUpdateIwpDocuments(final List<IwpDocuments> lstIwpDocuments) {
	final String sql = getQuery("OIUIWPVE_IWPDOC_UPDATE_IWP_DOCUMENTS");
	int returnValue = 0;
	int[] returnArray = new int[] {};
	final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (final IwpDocuments iwpDocuments : lstIwpDocuments) {
		parameters.add(new BeanPropertySqlParameterSource(iwpDocuments));
	}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	if (lstIwpDocuments.size() == returnArray.length) {
		returnValue = 1;
	} 
	
	return returnValue;

}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<ReferenceCodes> rgStatusRecordGroup() {
 	final String sql = getQuery("OIUIWPVE_FIND_RGSTATUS");
 	final RowMapper<ReferenceCodes>mRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mMapping);
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams("MODE", "ENTER-QUERY"),mRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		log.error("rgStatusRecordGroup",e);
  		return Collections.emptyList();  
	}
}

	/**
	 * Used to capture results from select query
	 * 
	 * @return ListReferenceCodesM>
	 */
	public List<IwpTemplates> rgTemplateRecordGroup(final String offenderBookId, final String moduleName,
			final String pObjectType, final String pType, final String pSubType, final String pObjectId,
			final String blockName,final String username) {
		final String sql = getQuery("OIUIWPVE_FIND_RGTEMPLATE");
		final RowMapper<IwpTemplates> mRowMapper = Row2BeanRowMapper.makeMapping(sql, IwpTemplates.class,
				iwpTemplatesMapping);
		String paramObjectType = "XXXX";
		BigDecimal pOffenderBookId = null;
		final String str = iwpSystemParameters();
		if (offenderBookId != null) {
			pOffenderBookId = new BigDecimal(offenderBookId);
		}
		if (pObjectType != null) {
			paramObjectType = pObjectType;
		}
		System.out.println("--------------------------System Parameters--------------------------->    " + str);
		final ArrayList<IwpTemplates> returnList = (ArrayList<IwpTemplates>) namedParameterJdbcTemplate.query(sql,
				createParams("MODULENAME", moduleName, "POBJECTTYPE", paramObjectType, "POBJECTID", pObjectId, "BLOCKNAME",
						blockName, "OFFENDERBOOKID", pOffenderBookId, "PTYPE", pType, "PSUBTYPE", pSubType,"username",username),
				mRowMapper);

		return returnList;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * populateDefltDoc
	 *
	 * @param params
	 *
	 */
	public List<IwpTemplates> populateDefltDoc(final IwpTemplates paramBean) {
		final String sql = getQuery("OIUIWPVE_POPULATE_DEFLT_DOC");
		final RowMapper<IwpTemplates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,IwpTemplates.class,  iwpTemplatesMapping);
		final ArrayList<IwpTemplates> returnList = (ArrayList<IwpTemplates>)namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * populateDefltDoc
	 *
	 * @param params
	 *
	 */
	public List<IwpTemplateModules> populateDefltDoc(final IwpTemplateModules paramBean) {
		final String sql = getQuery("OIUIWPVE_POPULATE_DEFLT_DOC");
		final RowMapper<IwpTemplateModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,IwpTemplateModules.class,  iwpTemplateModulesMapping);
		final ArrayList<IwpTemplateModules> returnList = (ArrayList<IwpTemplateModules>)namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * isTemplateHasBody
	 *
	 * @param params
	 *
	 */
	public List<IwpTemplates> isTemplateHasBody(final IwpTemplates paramBean) {
		final String sql = getQuery("OIUIWPVE_IS_TEMPLATE_HAS_BODY");
		final RowMapper<IwpTemplates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,IwpTemplates.class,  iwpTemplatesMapping);
		final ArrayList<IwpTemplates> returnList = (ArrayList<IwpTemplates>)namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkVicNotifTemplActive
	 *
	 * @param params
	 *
	 */
	public List<IwpTemplateObjects> checkVicNotifTemplActive(final IwpTemplateObjects paramBean) {
		final String sql = getQuery("OIUIWPVE_CHECK_VIC_NOTIF_TEMPL_ACTIVE");
		final RowMapper<IwpTemplateObjects> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,IwpTemplateObjects.class,  iwpTemplateObjectsMapping);
		final ArrayList<IwpTemplateObjects> returnList = (ArrayList<IwpTemplateObjects>)namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}
	
	public String iwpSystemParameters() {
		String value = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] {
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("IWP_10G").withFunctionName("GET_SYSTEM_PARAMS")
				.declareParameters(sqlParameters);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			value = simpleJDBCCall.executeFunction(String.class, inParameter);
			System.out.println("iwpSystemParameters -----> " + value);
		} catch (Exception e) {
			value = "";
		}
		return value;
	}


}

package net.syscon.s4.inst.casemanagement.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.IwpTemplateModules;
import net.syscon.s4.inst.casemanagement.OiuiwpgnRepository;
/**
 * Class OiuiwpgnRepositoryImpl
 */
@Repository
public class OiuiwpgnRepositoryImpl extends RepositoryBase implements OiuiwpgnRepository{

private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CODE", 						new FieldMapper(" code    "))
.build();
private final Map<String, FieldMapper> iwpTemplateModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DOCUMENT_CONTEXT_FLAG", 						new FieldMapper("documentContextFlag"))
.put("MODULE_NAME", 						new FieldMapper("moduleName"))
.put("PARAMETER_NAME", 						new FieldMapper("parameterName"))
.put("BLOCK_NAME", 						new FieldMapper("blockName"))
.put("TEMPLATE_I", 						new FieldMapper("templateI"))
.put("BLOCK_DESCRIPTIO", 						new FieldMapper("blockDescriptio"))
.put("BOOKMARK_NAME", 						new FieldMapper("bookmarkName"))
.put("FIELD_NAME", 						new FieldMapper("fieldName"))
.put("PARAMETER_DATA_TYPE", 						new FieldMapper("parameterDataType"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();

/**
 * Creates new OiuiwpgnRepositoryImpl class Object 
 */
public OiuiwpgnRepositoryImpl() {

}

/**
* Used to capture results from select query
* @return List<ReferenceCodes> 
*/
public List<ReferenceCodes> rgStatusRecordGroup() {
 	final String sql = getQuery("OIUIWPGN_FIND_RGSTATUS");
 	final RowMapper<ReferenceCodes>mRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams("MODE","ENTER-QUERY"),mRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		return Collections.emptyList();  
	}
}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getReferenceCode
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> getReferenceCode(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIUIWPGN_GET_REFERENCE_CODE");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class,  referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>)namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * assignContextParam
	 *
	 * @param params
	 *
	 */
	public List<IwpTemplateModules> assignContextParam(final IwpTemplateModules paramBean) {
		final String sql = getQuery("OIUIWPGN_ASSIGN_CONTEXT_PARAM");
		final RowMapper<IwpTemplateModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,IwpTemplateModules.class,  iwpTemplateModulesMapping);
		final ArrayList<IwpTemplateModules> returnList = (ArrayList<IwpTemplateModules>)namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

}

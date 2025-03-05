package net.syscon.s4.iwp.base.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.iwp.base.TemplateRepository;

@Repository
public class TempletRepositoryImpl extends RepositoryBase implements TemplateRepository {

	private static Logger log = LogManager.getLogger(TempletRepositoryImpl.class);

	private final Map<String, FieldMapper> IWPTemplateMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TEMPLATE_ID", 				new FieldMapper("templateId"))
			.put("TEMPLATE_NAME",  				new FieldMapper("templateName"))
			.put("DESCRIPTION",  				new FieldMapper("description"))
			.put("LOCATION",  					new FieldMapper("location"))
			.put("ACTIVE_FLAG",  				new FieldMapper("activeFlag"))
			.put("TEMPLATE_BODY",  				new FieldMapper("templateBody"))
			.put("DATE_CREATED", 				new FieldMapper("dateCreated"))
			.put("USER_CREATED",  				new FieldMapper("userCreated"))
			.put("LOCK_PASSWORD",  				new FieldMapper("lockPassword"))
			.put("OBJECT_TYPE",  				new FieldMapper("objectType"))
			.put("CREATE_DATETIME",  			new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID",  			new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME",  			new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID",  			new FieldMapper("modifyUserId"))
			.put("EDRMS_RECORD_NO",  			new FieldMapper("edrmsRecordNo"))
			.put("EDRMS_FOLDER",  				new FieldMapper("edrmsFolder"))
			.build();



	@Cacheable("getAllIWPTemplateList")
	@Override
	public List<IwpTemplates> getAllIWPTemplateList() {

		List<IwpTemplates> returnObj= null;

		final String sql=getQuery("GET_ALL_EMPLATE_DATA_LIST");

		final RowMapper<IwpTemplates> rowMapper = Row2BeanRowMapper.makeMapping(sql, IwpTemplates.class, IWPTemplateMapping);
		 try{
			 returnObj = namedParameterJdbcTemplate.query(sql, rowMapper);
			 }catch(Exception e){
				 log.error("getAllIWPTemplateList "+e.getMessage());
			 }
			return returnObj;

	}



	@Override
	public List<IwpTemplates> getIWPTemplateList(String moduleName) {
		// TODO Auto-generated method stub
		return null;
	}


	@Cacheable("IWPTemplateListByTemplateId")
	@Override
	public IwpTemplates getIWPTemplate(String templateId) {
		IwpTemplates returnObj= null;

		final String sql=getQuery("GET_IWP_TEMPLATE_DATA");

		final RowMapper<IwpTemplates> rowMapper = Row2BeanRowMapper.makeMapping(sql, IwpTemplates.class, IWPTemplateMapping);
		 try{
			 returnObj = namedParameterJdbcTemplate.queryForObject(sql,createParams("TEMPLATE_ID",templateId), rowMapper);
			 }catch(Exception e){
				 log.error("getIWPTemplate "+e.getMessage());
			 }
			return returnObj;
	}


	@Cacheable("IWPTemplateListByName")
	@Override
	public IwpTemplates getIWPTemplateByName(String templateName) {
		IwpTemplates returnObj= null;

		final String sql=getQuery("GET_IWP_TEMPLATE_DATA_BY_NAME");

		final RowMapper<IwpTemplates> rowMapper = Row2BeanRowMapper.makeMapping(sql, IwpTemplates.class, IWPTemplateMapping);
		 try{
			 returnObj = namedParameterJdbcTemplate.queryForObject(sql,createParams("TEMPLATE_NAME",templateName), rowMapper);
			 }catch(Exception e){
				 log.error("getIWPTemplate "+e.getMessage());
			 }
			return returnObj;
	}


	@Cacheable("IWPTemplateListByModule")
	@Override
	public List<IwpTemplates> getIWPTemplatesByModule(String moduleName) {
		List<IwpTemplates> returnObj= null;

		final String sql=getQuery("GET_MODULE_TEMPLATE_DATA_LIST");

		final RowMapper<IwpTemplates> rowMapper = Row2BeanRowMapper.makeMapping(sql, IwpTemplates.class, IWPTemplateMapping);
		 try{
			 returnObj = namedParameterJdbcTemplate.query(sql,createParams("MODULE_NAME",moduleName), rowMapper);
			 }catch(Exception e){
				 log.error("getIWPTemplatesByModule "+e.getMessage());
			 }
			return returnObj;
	}

}

package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legals.OculegstRepository;
import net.syscon.s4.inst.legals.beans.UpdateReason;
import net.syscon.s4.inst.legals.beans.UpdateUser;
import net.syscon.s4.inst.legals.beans.Sentences;

@Repository
public class OculegstRepositoryImpl extends RepositoryBase implements OculegstRepository{
	
	private static Logger logger = LogManager.getLogger(OculegstRepositoryImpl.class.getName());
	
	
	private final Map<String, FieldMapper> UpdateReasonMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 		 	new FieldMapper("code"))			
			.put("DESCRIPTION",   	new FieldMapper("description"))
			.put("STATUS",  	 	new FieldMapper("status"))
			.put("CATEGORY",  	 	new FieldMapper("category")).build();
	
	private final Map<String, FieldMapper> updateReasonMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 			new FieldMapper("description"))			
			.put("CODE",  					new FieldMapper("code")).build();
			
	private final Map<String, FieldMapper> UpdateUserMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFF_ID", 		  new FieldMapper("staffId"))
			.put("LAST_NAME", 		  new FieldMapper("lastName"))
			.put("FIRST_NAME",   	  new FieldMapper("firstName")).build();
	
	
	
	@Override
	public List<UpdateReason> getUpdateCaseReason() {
		final String sql = getQuery("Get_UPDATE_CASE_REASON"); 	
		final RowMapper<UpdateReason> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,UpdateReason.class, UpdateReasonMapping);
		List<UpdateReason> returnObj = new ArrayList<UpdateReason>();
		try {
			returnObj=  namedParameterJdbcTemplate.query(sql,columnRowMapper);
		} catch (Exception e) {
			logger.error("getUpdateCaseReason"+e.getMessage());
		}
		return returnObj;
	}
	
	@Override
	public List<Sentences> populateUpdateReason(String category, String sentenceCalcType) {
		 List<Sentences> resultList = new ArrayList<Sentences>();
			final String sql = getQuery("LOV_UPDATE_REASON_RG_SENT");
			final RowMapper<Sentences> updateReasonRowMapper = Row2BeanRowMapper.makeMapping(sql,Sentences.class, updateReasonMapping);
			try {
				resultList=  namedParameterJdbcTemplate.query(sql,createParams("category" ,category, "sentenceCalcType",sentenceCalcType),updateReasonRowMapper);
				logger.error("updated reason"+resultList.size());	
			} catch (Exception e) {
					logger.error("populateUpdateReason"+e.getMessage());
			}
			return resultList;
			
	}

	@Override
	public UpdateUser getUpdateUser(String name) {
		final String sql = getQuery("Get_UPDATE_USER"); 	
		final RowMapper<UpdateUser>  columnRowMapper = Row2BeanRowMapper.makeMapping(sql,UpdateUser.class, UpdateUserMapping);
		UpdateUser returnObj = new UpdateUser();
		try {
			returnObj=  namedParameterJdbcTemplate.queryForObject(sql,createParams("name",name),columnRowMapper);
		} catch (Exception e) {
			logger.error("getUpdateUser"+e.getMessage());
		}
		return returnObj;
	}

	@Override
	public List<UpdateReason> getUpdateConditionReason() {
		final String sql = getQuery("GET_UPDATE_CONDITION_REASON"); 	
		final RowMapper<UpdateReason> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,UpdateReason.class, UpdateReasonMapping);
		List<UpdateReason> returnObj = new ArrayList<UpdateReason>();
		try {
			returnObj=  namedParameterJdbcTemplate.query(sql,columnRowMapper);
		} catch (Exception e) {
			logger.error("getUpdateConditionReason"+e.getMessage());
		}
		return returnObj;
	}

}

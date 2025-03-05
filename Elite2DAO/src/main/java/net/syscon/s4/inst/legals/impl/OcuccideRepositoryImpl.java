package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legals.OcuccideRepository;
import net.syscon.s4.inst.legals.beans.CaseIdentifiers;
import net.syscon.s4.inst.legals.beans.Holds;
import net.syscon.s4.inst.legals.beans.IdentifierCommitBean;
import net.syscon.s4.inst.legals.beans.IdentifierType;


@Repository
public class OcuccideRepositoryImpl extends RepositoryBase implements OcuccideRepository {
	
	private static Logger logger = LogManager.getLogger(OcuccideRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> CaseIdentifiersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("IDENTIFIER_TYPE", 					  new FieldMapper("type"))			
			.put("IDENTIFIER_NO",  			      new FieldMapper("number"))
			.put("CASE_ID", 				  new FieldMapper("caseId")).build();
	
	private final Map<String, FieldMapper> IdentifierTypeyMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("IDENTIFIER_TYPE", 	new FieldMapper("description"))			
			.put("CODE",  			    new FieldMapper("code")).build();

	@Override
	public List<CaseIdentifiers> caseIdentifiers(Long caseId) {
		final String sql = getQuery("GRID_CASE_IDENTIFIRE");		
		
		final RowMapper<CaseIdentifiers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,CaseIdentifiers.class, CaseIdentifiersMapping);
		List<CaseIdentifiers> returnObj = new ArrayList<CaseIdentifiers>();
		try {
			returnObj = namedParameterJdbcTemplate.query(sql,createParams("caseId" ,caseId),columnRowMapper);	
			if(returnObj.size() > 0) {
				for(int i=0; i<returnObj.size(); i++ ) {
					 returnObj.get(i).setSeqNumber(i);					
				}
			}
		} catch (Exception e) {
			System.err.println("caseIdentifiers"+ e.getMessage());
		}
		return returnObj;
	}
		
	@Override
	public List<IdentifierType> identifierType() {
		final String sql = getQuery("LOV_IDENTIFIRE_TYPE"); 	
		final RowMapper<IdentifierType> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,IdentifierType.class, IdentifierTypeyMapping);
		List<IdentifierType> returnObj = new ArrayList<IdentifierType>();
		try {
			returnObj=  namedParameterJdbcTemplate.query(sql,columnRowMapper);
		} catch (Exception e) {
			logger.error("identifierType"+e.getMessage());
		}
		return returnObj;
	}

	@Override
	public int[] insertIdentifierData(List<CaseIdentifiers> insertList) {	
		
		final String sql = getQuery("INSERT_IDENTEFIER_DATA");
	    int[] returnArray = {};
	    final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	    for (final CaseIdentifiers list : insertList){
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			System.err.println("insertIdentifierData"+ e);
		}
		return returnArray;		
	}

	@Override
	public int[] updateIdentifierData(List<CaseIdentifiers> updateRecord) {
		final String sql = getQuery("UPDATE_IDENTEFIER_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CaseIdentifiers list : updateRecord){
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("updateIdentifierData"+ e);
		}
		return returnArray;
	}	
	
	@Override
	public int[] deleteIdentifierData(final List<CaseIdentifiers> deleteList) {
		final String sql = getQuery("DELETE_IDENTEFIER_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CaseIdentifiers list : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			batchUpdatePreDeletedRows("OFFENDER_CASE_IDENTIFIERS", "IDENTIFIER_TYPE= :type AND IDENTIFIER_NO= :number", parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try{
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (EmptyResultDataAccessException e){
			logger.error("deleteIdentifierData"+e.getMessage());
		}
		return returnArray;
	}

}

package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.LovList;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legals.OcucalcrRepository;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legals.beans.SentenceCalculation;
import oracle.jdbc.OracleTypes;

@Repository
public class OcucalcrRepositoryImpl extends RepositoryBase implements OcucalcrRepository {
	
	private static Logger logger = LogManager.getLogger(OcucalcrRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> calcReasonLovMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 	new FieldMapper("description"))			
			.put("CODE",  			    new FieldMapper("code")).build();

	@Override
	public List<LovList> populateCalculationReasonList() {
		final String sql = getQuery("LOV_CALCULATION_REASON"); 	
		final RowMapper<LovList> calcReasonRowMapper = Row2BeanRowMapper.makeMapping(sql,LovList.class, calcReasonLovMapping);
		List<LovList> calcReasonList = new ArrayList<LovList>();
		try {
			calcReasonList=  namedParameterJdbcTemplate.query(sql,calcReasonRowMapper);
		} catch (Exception e) {
			logger.error("PopulateCalculationReasonList Exception: "+e.getMessage());
		}
		return calcReasonList;
	}

	@Override
	public String populateStaffName(Integer staffId) {
		final String sql = getQuery("FETCH_STAFF_NAME");
		final String staffName = namedParameterJdbcTemplate.queryForObject(sql,createParams("staffId",staffId),String.class);
		return staffName;
		
	}

	@Override
	public Integer generateStaffId(String username) {
		final String sql = getQuery("GENERATE_STAFF_ID");
		final Integer staffId = namedParameterJdbcTemplate.queryForObject(sql,createParams("username",username),Integer.class);
		return staffId;
	}

	@Override
	public String calExpDate(SentenceCalculation sentenceCalc) {
		String successFlag = "F";
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Map<String, Object> returnObject = null;
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
				
				new SqlParameter("P_OFFBOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CALC_REASON", OracleTypes.VARCHAR),
				new SqlParameter("P_COMMENT_TEXT", OracleTypes.VARCHAR),
				new SqlOutParameter("P_SUCCESS_FLAG", OracleTypes.VARCHAR)
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SENTENCE_CALC")
				.withProcedureName("CALCULATE_SENTENCES")
				.declareParameters(sqlParameters);
		
		inParamMap.put("P_OFFBOOK_ID", sentenceCalc.getOffenderBookId());    
		inParamMap.put("P_CALC_REASON",sentenceCalc.getCalculationReason());
		inParamMap.put("P_COMMENT_TEXT",sentenceCalc.getComment());    
		inParamMap.put("P_SUCCESS_FLAG",successFlag);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			successFlag =   returnObject.get("P_SUCCESS_FLAG").toString();
		} catch (Exception e) {
			logger.error("calExpDate"+e.getMessage());
			}
		return successFlag;
	}
	
	
	@Override
	public List<LovList> getStaffMembers() {
		final String sql = getQuery("GET_ALL_STAFF_DATA");
		final RowMapper<LovList> calcReasonRowMapper = Row2BeanRowMapper.makeMapping(sql,LovList.class, calcReasonLovMapping);
		List<LovList> calcReasonList = new ArrayList<LovList>();
		try {
			calcReasonList=  namedParameterJdbcTemplate.query(sql,calcReasonRowMapper);
		} catch (Exception e) {
			logger.error("getStaffMembers Exception: "+e.getMessage());
		}
		return calcReasonList;
		
	}

}

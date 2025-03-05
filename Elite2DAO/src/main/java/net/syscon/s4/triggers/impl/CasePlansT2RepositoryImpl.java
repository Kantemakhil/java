package net.syscon.s4.triggers.impl;


import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.triggers.CasePlansT2Repository;

/**
 * Class CasePlansT2RepositoryImpl
 * 
 */
@Repository
public class CasePlansT2RepositoryImpl extends RepositoryBase implements CasePlansT2Repository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(CasePlansT2RepositoryImpl.class);

	final Map<String, FieldMapper> casePlansMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).build();
	
	
	@Override
	public Integer workFlowsCount(final CasePlans casePlans) {
		final String sql = getQuery("WORK_FLOW_ID_COUNT");
		Integer retVal = 0;
		try {
		retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("offender_book_id", casePlans.getOffenderBookId(),
				"case_plan_id", casePlans.getCasePlanId()), Integer.class);
		}
		catch(Exception e) {
			logger.error("workFlowsCount :"+e);
		}
		return retVal;
	}

	@Override
	public Integer workFlowsIdNextVal() {
		final String sql = getQuery("WORK_FLOW_ID_NEXTVAL");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}
	
	@Override
	public Integer insertWorkFlows(final CasePlans casePlans,final Integer lvWorkFlowId) {
		final String sql = getQuery("INSERT_INTO_WORK_FLOWS");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer retVal = 0;

		inParamMap.put("lv_work_flow_id", lvWorkFlowId);
		inParamMap.put("offender_book_id", casePlans.getOffenderBookId());
		inParamMap.put("case_plan_id", casePlans.getCasePlanId());
        inParamMap.put("createUserId", casePlans.getCreateUserId());
		try {
			retVal = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			retVal = 0;
			logger.error("insertWorkFlows", e);
		}
		return retVal;
	}
	
	@Override
	public Integer insertWorkFlowsLogs(final Integer lvWorkFlowId, final String userName,String locateAgyLocId) {
		final String sql = getQuery("INSERT_INTO_WORK_FLOWS_LOGS");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer retVal = 0;
		inParamMap.put("lv_work_flow_id", lvWorkFlowId);
		inParamMap.put("USERNAME", userName);
		inParamMap.put("locateAgyLocId", locateAgyLocId);
		try {
			retVal = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			retVal = 0;
			logger.error("insertWorkFlows", e);
		}
		return retVal;
	}
	}



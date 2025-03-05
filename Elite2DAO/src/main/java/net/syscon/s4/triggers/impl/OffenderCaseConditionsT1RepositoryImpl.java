package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.casemanagement.beans.OffenderCaseConditions;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.triggers.OffenderCaseConditionsT1Repository;
@Repository
public class OffenderCaseConditionsT1RepositoryImpl extends RepositoryBase implements OffenderCaseConditionsT1Repository{
	
	private static Logger logger = LogManager.getLogger(OffenderCaseConditionsT1RepositoryImpl.class.getName());

	@Override
		public OffenderSentConditions getOffenderSentConditions(final Long offenderSentConditionId) {
			final String sql = getQuery("GET_OFFENDER_SENT_CONDITIONS");
			OffenderSentConditions retObj = new OffenderSentConditions();
			try {
				retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_SENT_CONDITION_ID", offenderSentConditionId),
						new BeanPropertyRowMapper<OffenderSentConditions>(OffenderSentConditions.class));
			} catch (Exception e) {
				retObj = null;
			}
			return retObj;
		}
		
	@Override
	public int getCommunityConditions(final String commConditionType, final String commConditionCode, final String categoryType) {
		final String sql = getQuery("GET_COMMUNITY_CONDITIONS");
		Integer retObj = 0;
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("comm_condition_type", commConditionType,
					"comm_condition_code", commConditionCode,"category_type", categoryType),
					Integer.class);
		} catch (Exception e) {
			retObj = 0;
		}
		return retObj;
	}
	
	@Override
		public Map getCasePlans(final BigDecimal offenderBookId) {
			final String sql = getQuery("GET_CASE_PLANS");
			Map<String, Object> map = new HashMap<>();
			try {
				map = namedParameterJdbcTemplate.queryForMap(sql, createParams("offender_book_id", offenderBookId));
			} catch (Exception e) {
				map=null;
			}
			return map;
		}	
	
		@Override
		public Long getOffCaseConIid() {
			final String sql = getQuery("GET_OFF_CASE_COND_ID");
			Long retObj = null;
			try {
				retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
						Long.class);
			} catch (Exception e) {
				logger.error("getOffCaseConIid :" + e);
			}
			return retObj;
		}
		
		@Override
		public Integer insertOffenderCaseConditions(final Long vOffCcId, final Long vObi, final Long vCaseplanId, final OffenderSentConditions offenderSentConditions) {
			final String sql = getQuery("INSERT_OFFENDER_CASE_CONDITIONS");
			final Map<String, Object> inParamMap = new HashMap<String, Object>();
			
			inParamMap.put("v_off_cc_id", vOffCcId);
			inParamMap.put("v_obi", vObi);
			inParamMap.put("v_caseplan_id", vCaseplanId);

			inParamMap.put("offender_sent_condition_id", offenderSentConditions.getOffenderSentConditionId());
			inParamMap.put("comm_condition_type", offenderSentConditions.getCommConditionType());
			inParamMap.put("comm_condition_code", offenderSentConditions.getCommConditionCode()); 	

			inParamMap.put("Category_Type", offenderSentConditions.getCategoryType());
			inParamMap.put("length", offenderSentConditions.getLength());
			
			inParamMap.put("length_unit", offenderSentConditions.getLengthUnit());
			inParamMap.put("start_date", offenderSentConditions.getStartDate()); 	

			inParamMap.put("expiry_date", offenderSentConditions.getExpiryDate());
			inParamMap.put("condition_status", offenderSentConditions.getConditionStatus());
			inParamMap.put("createUserId", offenderSentConditions.getCreateUserId());
			
			return namedParameterJdbcTemplate.update(sql, inParamMap);
		}
		
		@Override
		public OffenderCaseConditions getOffenderCaseConditions(final Long offenderSentConditionId) {
			final String sql = getQuery("GET_OFFENDER_CASE_CONDITIONS_T1_TRIGGER");
			OffenderCaseConditions retObj = new OffenderCaseConditions();
			try {
				retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_SENT_CONDITION_ID", offenderSentConditionId),
						new BeanPropertyRowMapper<OffenderCaseConditions>(OffenderCaseConditions.class));
			} catch (Exception e) {
				retObj = null;
			}
			return retObj;
		}
		
		@Override
		public Integer updateOffenderCaseConditions(final Long vOffCcId, final OffenderSentConditions offenderSentConditions) {
			final String sql = getQuery("UPDATE_OFFENDER_CASE_CONDITIONS");
			final Map<String, Object> inParamMap = new HashMap<String, Object>();
			
			inParamMap.put("off_case_cond_id", vOffCcId);
			
			inParamMap.put("offender_sent_condition_id", offenderSentConditions.getOffenderSentConditionId());
			inParamMap.put("length", offenderSentConditions.getLength());
			inParamMap.put("length_unit", offenderSentConditions.getLengthUnit());
			inParamMap.put("start_date", offenderSentConditions.getStartDate()); 	
			inParamMap.put("expiry_date", offenderSentConditions.getExpiryDate());
			inParamMap.put("condition_status", offenderSentConditions.getConditionStatus());
			return namedParameterJdbcTemplate.update(sql, inParamMap);
		}


}

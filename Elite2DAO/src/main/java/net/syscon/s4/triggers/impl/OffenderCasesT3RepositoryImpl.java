package net.syscon.s4.triggers.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.triggers.OffenderCasesT3Repository;
@Repository
public class OffenderCasesT3RepositoryImpl extends RepositoryBase implements OffenderCasesT3Repository{

	@Override
	public OffenderCases getOffenderCases(final Long caseId) {
		final String sql = getQuery("GET_OFFENDER_CASES_CASE_ID");
		return  namedParameterJdbcTemplate
				.queryForObject(sql, createParams("CASE_ID",caseId),new BeanPropertyRowMapper<OffenderCases>(OffenderCases.class));
		
	}
	
	@Override
	public Integer insertOffenderCaseStatuses(final OffenderCases oldOffenderCases, final OffenderCases newOffenderCases) {
		final String sql = getQuery("INSERT_OFFENDER_CASE_STATUSES");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("status_update_reason1", oldOffenderCases.getStatusUpdateReason());
		inParamMap.put("status_update_reason2", newOffenderCases.getStatusUpdateReason());
		inParamMap.put("status_update_comment1", oldOffenderCases.getStatusUpdateComment());
		inParamMap.put("status_update_comment2", newOffenderCases.getStatusUpdateComment());
		inParamMap.put("status_update_date1", oldOffenderCases.getStatusUpdateDate());
		inParamMap.put("status_update_date2", newOffenderCases.getStatusUpdateDate());
		inParamMap.put("status_update_staff_id1", oldOffenderCases.getStatusUpdateStaffId());
		inParamMap.put("status_update_staff_id2", newOffenderCases.getStatusUpdateStaffId());
		inParamMap.put("case_id", oldOffenderCases.getCaseId());

		return namedParameterJdbcTemplate.update(sql, inParamMap);
	}
}


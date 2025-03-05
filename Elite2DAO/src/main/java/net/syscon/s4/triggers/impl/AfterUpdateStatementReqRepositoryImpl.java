package net.syscon.s4.triggers.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffStatusesPkgSpec;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitVisitors;
import net.syscon.s4.triggers.AddressesT2Repository;
import net.syscon.s4.triggers.AfterUpdateStatementReqRepository;

@Repository
public class AfterUpdateStatementReqRepositoryImpl extends RepositoryBase implements AfterUpdateStatementReqRepository {

	@Override
	public Integer insertOffenderInternalStatuses(final OffStatusesPkgSpec offStatusesPkgSpec) {
		final String sql = getQuery("INSERT_OFFENDER_INTERNAL_STATUSES");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("lv_book_id", offStatusesPkgSpec.getLvBookId());
		inParamMap.put("lv_internal_status_seq", offStatusesPkgSpec.getLvInternalStatusSeq());
		inParamMap.put("lv_request_reason", offStatusesPkgSpec.getLvRequestReason());
		inParamMap.put("lv_request_type", offStatusesPkgSpec.getLvRequestType());
		inParamMap.put("lv_duration_code", offStatusesPkgSpec.getLvDurationCode());
		inParamMap.put("lv_party_type", offStatusesPkgSpec.getLvPartyType());
		inParamMap.put("lv_agy_loc_id", offStatusesPkgSpec.getLvAgyLocId());
		inParamMap.put("lv_request_date", offStatusesPkgSpec.getLvRequesDate());
		inParamMap.put("lv_next_review", offStatusesPkgSpec.getLvNextReview());
		return namedParameterJdbcTemplate.update(sql, inParamMap);
	}
}

package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffStatusesPkgSpec;
import net.syscon.s4.common.beans.OffenderInstRequests;
import net.syscon.s4.triggers.AfterUpdateOffInstReqRepository;
import net.syscon.s4.triggers.AfterUpdateOffInstReqService;
@Service
public class AfterUpdateOffInstReqServiceImpl implements AfterUpdateOffInstReqService{
@Autowired
private AfterUpdateOffInstReqRepository afterUpdateOffInstReqRepository;
	@Override
	public OffStatusesPkgSpec afterUpdateOffInstReq(final OffenderInstRequests newOffenderInstRequests) {
		OffStatusesPkgSpec offStatusesPkgSpec =new OffStatusesPkgSpec();
		if(Objects.nonNull(newOffenderInstRequests)) {
			OffenderInstRequests oldOffenderInstRequests = afterUpdateOffInstReqRepository.getOffenderInstRequests(newOffenderInstRequests.getOffInstReqId());
			if(Objects.nonNull(oldOffenderInstRequests) && (Objects.isNull(newOffenderInstRequests.getSealFlag()) || StringUtils.equals(oldOffenderInstRequests.getSealFlag(), newOffenderInstRequests.getSealFlag()))
					&& Objects.nonNull(newOffenderInstRequests.getRequestStatus()) 
					&& !StringUtils.equals(newOffenderInstRequests.getRequestStatus(), oldOffenderInstRequests.getRequestStatus())) {
				offStatusesPkgSpec.setLvBookId(oldOffenderInstRequests.getOffenderBookingId());
				offStatusesPkgSpec.setLvRequesDate(oldOffenderInstRequests.getRequestDate());
				offStatusesPkgSpec.setLvRequestType(oldOffenderInstRequests.getRequestType());
				offStatusesPkgSpec.setLvRequestReason(oldOffenderInstRequests.getRequestReason());
				offStatusesPkgSpec.setLvNewRequestStatus(oldOffenderInstRequests.getRequestStatus());
				Map<String, Object> resultMap = afterUpdateOffInstReqRepository.getData(oldOffenderInstRequests.getOffenderBookingId());
				if(resultMap == null || resultMap.isEmpty()) {
					return new OffStatusesPkgSpec();
				}
				offStatusesPkgSpec.setLvDurationCode((String) resultMap.get("duration_code"));
				offStatusesPkgSpec.setLvInternalStatusSeq((String) resultMap.get("internal_status_seq"));
				offStatusesPkgSpec.setLvPartyType((String) resultMap.get("lv_party_type"));
				offStatusesPkgSpec.setLvAgyLocId((BigDecimal) resultMap.get("agy_loc_id"));
				resultMap = afterUpdateOffInstReqRepository.getData1(offStatusesPkgSpec.getLvDurationCode(),
						offStatusesPkgSpec.getLvRequestType(), offStatusesPkgSpec.getLvRequestReason());
				if(resultMap == null || resultMap.isEmpty()) {
					return new OffStatusesPkgSpec();
				}
				offStatusesPkgSpec.setLvNextReview((String) resultMap.get("next_review"));
				offStatusesPkgSpec.setLvRevBox((String) resultMap.get("rev_box"));;
			}
		}
		return offStatusesPkgSpec;
		
	}

}

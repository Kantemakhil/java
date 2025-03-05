package net.syscon.s4.triggers;

import java.math.BigDecimal;
import java.util.Map;

import net.syscon.s4.common.beans.OffenderInstRequests;

public interface AfterUpdateOffInstReqRepository {

	OffenderInstRequests getOffenderInstRequests(BigDecimal offInstReqId);

	Map<String, Object> getData(BigDecimal offenderBookingId);

	Map<String, Object> getData1(String lvDurationCode, String lvRequestType, String lvRequestReason);

}

package net.syscon.s4.pkgs.tag_wf_court_report;

import java.math.BigDecimal;

import net.syscon.s4.common.beans.Orders;

public interface TagWfCourtReportService {

	Integer updateOrderStatus(final BigDecimal pOffenderBookId, final BigDecimal pMsgId, final String userName);

	void createReportDone(final Orders orders);
}
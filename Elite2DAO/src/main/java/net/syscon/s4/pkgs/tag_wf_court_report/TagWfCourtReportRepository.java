package net.syscon.s4.pkgs.tag_wf_court_report;

import java.math.BigDecimal;

public interface TagWfCourtReportRepository {

	Long getEventIdForUpOrdStatus(final BigDecimal pOffenderBookId, final BigDecimal pMsgId);

	Integer updateOrderStatusForOrders(final BigDecimal pOffenderBookId, final BigDecimal pMsgId,
			final String userName);
}
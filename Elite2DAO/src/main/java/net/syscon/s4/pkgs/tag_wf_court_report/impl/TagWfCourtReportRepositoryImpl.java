package net.syscon.s4.pkgs.tag_wf_court_report.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.tag_wf_court_report.TagWfCourtReportRepository;

@Repository
public class TagWfCourtReportRepositoryImpl extends RepositoryBase implements TagWfCourtReportRepository {

	private static Logger logger = LogManager.getLogger(TagWfCourtReportRepositoryImpl.class.getName());

	@Override
	public Long getEventIdForUpOrdStatus(final BigDecimal pOffenderBookId, final BigDecimal pMsgId) {
		final String sql = getQuery("UPDATE_ORDER_STATUS_GET_EVENT_ID");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_OFFENDER_BOOK_ID", pOffenderBookId, "P_MSG_ID", pMsgId), Long.class);
	}

	@Override
	public Integer updateOrderStatusForOrders(final BigDecimal pOffenderBookId, final BigDecimal pMsgId,
			final String userName) {
		final String sql = getQuery("UPDATE_ORDER_STATUS_UPDATE_ODER_STATUS");
		return namedParameterJdbcTemplate.update(sql,
				createParams("P_OFFENDER_BOOK_ID", pOffenderBookId, "P_MSG_ID", pMsgId, "modifyUserId", userName));
	}
}
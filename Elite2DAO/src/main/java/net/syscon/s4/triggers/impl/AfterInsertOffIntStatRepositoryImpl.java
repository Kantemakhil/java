package net.syscon.s4.triggers.impl;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.AfterInsertOffIntStatRepository;
import net.syscon.s4.triggers.OffenderInstTequests;

@Repository
public class AfterInsertOffIntStatRepositoryImpl extends RepositoryBase implements AfterInsertOffIntStatRepository {
	public AfterInsertOffIntStatRepositoryImpl() {
		// AfterInsertOffIntStatRepositoryImpl
	}

	@Override
	public Integer save(final OffenderInstTequests offenInstTequests) {
		final String sql = getQuery("AFTER_INSERT_OFF_INT_STAT_INSERT");
		return namedParameterJdbcTemplate.update(sql,
				createParams(":offenderbookid", offenInstTequests.getOffenderBookingId(), ":reviewdate",
						offenInstTequests.getReviewdate(), ":internalstatus", offenInstTequests.getInternalstatus(),
						":intStsReasonCode", offenInstTequests.getIntStsReasonCode()));
	}

}

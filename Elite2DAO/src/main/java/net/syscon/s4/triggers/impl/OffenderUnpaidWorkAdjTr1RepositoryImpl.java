package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.cm.programsservices.OffenderUnpaidWorkAdj;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderUnpaidWorkAdjTr1Repository;

@Repository
public class OffenderUnpaidWorkAdjTr1RepositoryImpl extends RepositoryBase
		implements OffenderUnpaidWorkAdjTr1Repository {
	private final Logger logger = LogManager.getLogger(OffenderUnpaidWorkAdjTr1RepositoryImpl.class.getName());

	@Override
	public OffenderUnpaidWorkAdj getOffenderUnpaidWorkAdj(final OffenderUnpaidWorkAdj offenderUnpaidWorkAdj) {
		final String sql = getQuery("OFFENDER_UNPAID_WORK_ADJ_TR1_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderUnpaidWorkAdjId", offenderUnpaidWorkAdj.getOffenderUnpaidWorkAdjId()),
					new BeanPropertyRowMapper<OffenderUnpaidWorkAdj>(OffenderUnpaidWorkAdj.class));
		} catch (final Exception e) {
			logger.error("getOffenderUnpaidWorkAdj", e);
			return null;
		}
	}

	@Override
	public Integer vNumrows(final OffenderUnpaidWorkAdj offenderUnpaidWorkAdj) {
		final String sql = getQuery("OFFENDER_UNPAID_WORK_ADJ_TR1_V_NUMROWS");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("reasonCode", offenderUnpaidWorkAdj.getReasonCode()), Integer.class);
		} catch (final Exception e) {
			count = 0;
		}
		return count;
	}

}

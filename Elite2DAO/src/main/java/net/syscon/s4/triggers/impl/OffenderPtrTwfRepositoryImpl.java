package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderPtr;
import net.syscon.s4.triggers.OffenderPtrTwfRepository;

@Repository
public class OffenderPtrTwfRepositoryImpl extends RepositoryBase implements OffenderPtrTwfRepository {
	private static Logger logger = LogManager.getLogger(OffenderPtrTwfRepositoryImpl.class);

	@Override
	public OffenderPtr getOffenderPtr(final Long ptrId) {
		final String sql = getQuery("GET_OFFENDER_EXTERNAL_MOVEMENTS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(":ptrId", ptrId),
					new BeanPropertyRowMapper<OffenderPtr>(OffenderPtr.class));
		} catch (final Exception e) {
			logger.error("getOffenderPtr :" + e);
			return null;
		}
	}

}

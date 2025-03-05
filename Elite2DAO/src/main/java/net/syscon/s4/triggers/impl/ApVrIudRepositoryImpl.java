package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.casemanagement.beans.OffApV1;
import net.syscon.s4.triggers.ApVrIudRepository;

@Repository
public class ApVrIudRepositoryImpl extends RepositoryBase implements ApVrIudRepository {
	Logger logger = LogManager.getLogger(ApVrIudRepositoryImpl.class);

	@Override
	public OffApV1 getApVrIud(final BigDecimal offActionPlanId) {
		final String sql = getQuery("AP_VR_IUD_OFF_AP_V1_OLD");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offActionPlanId", offActionPlanId),
					new BeanPropertyRowMapper<OffApV1>(OffApV1.class));
		} catch (final Exception e) {
			logger.error("getApVrIud  ", e);
			return null;
		}
	}

}

package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.casemanagement.beans.OffApV2;
import net.syscon.s4.triggers.Ap2VrIudRepository;

@Repository
public class Ap2VrIudRepositoryImpl extends RepositoryBase implements Ap2VrIudRepository {
	Logger logger = LogManager.getLogger(Ap2VrIudRepositoryImpl.class);
	@Override
	public OffApV2 getAp2VrIud(final BigDecimal offActionPlanId) {
		final String sql = getQuery("AP_VR_IUD_OFF_AP_V2_OLD");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offActionPlanId", offActionPlanId),
					new BeanPropertyRowMapper<OffApV2>(OffApV2.class));
		} catch (final Exception e) {
			logger.error("getAp2VrIud  ", e);
			return null;
		}
	}

}

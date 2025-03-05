package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.AonVrIudRepository;
import net.syscon.s4.triggers.VAssOffNeeds;

@Repository
public class AonVrIudRepositoryImpl extends RepositoryBase implements AonVrIudRepository {
	private static Logger logger = LogManager.getLogger(AonVrIudRepositoryImpl.class);

	@Override
	public VAssOffNeeds getVAssOffNeeds(final Long offAssNeedId) {
		final String sql = getQuery("AON_VR_IUD_V_ASS_OFF_NEEDS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offAssNeedId", offAssNeedId),
					new BeanPropertyRowMapper<VAssOffNeeds>(VAssOffNeeds.class));
		} catch (final Exception e) {
			logger.error("getVAssOffNeeds  ", e);
			return null;
		}
	}

}

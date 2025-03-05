package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.triggers.OmtocontRepository;

@Repository
public class OmtocontRepositoryImpl extends RepositoryBase implements OmtocontRepository {
	private final Logger logger = LogManager.getLogger(OmtocontRepositoryImpl.class);

	@Override
	public OffenderPptyContainers getOffenderPptyContainers(final Integer propContaiId) {
		final String sql = getQuery("OMTOCONT_OFFENDER_PPTY_CONTAINERS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("propertyContainerId", propContaiId),
					new BeanPropertyRowMapper<OffenderPptyContainers>(OffenderPptyContainers.class));
		} catch (final Exception e) {
			logger.error("getOffenderPptyContainers", e);
			return null;
		}
	}

}

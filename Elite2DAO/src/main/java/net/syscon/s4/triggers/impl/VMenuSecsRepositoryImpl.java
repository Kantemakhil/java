package net.syscon.s4.triggers.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.VMenuSecs;
import net.syscon.s4.triggers.VMenuSecsRepository;

@Repository
public class VMenuSecsRepositoryImpl extends RepositoryBase implements VMenuSecsRepository {
	private final Logger logger = LogManager.getLogger(VMenuSecsRepositoryImpl.class);
	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	@Override
	public List<VMenuSecs> getVMenuSecs() {
		final String sql = getQuery("V_MENU_SECS_GET_LIST");
		final RowMapper<VMenuSecs> rowMapper = Row2BeanRowMapper.makeMapping(sql, VMenuSecs.class, mapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), rowMapper);
		} catch (final Exception e) {
			logger.error("getVMenuSecs", e);
			return Collections.emptyList();
		}
	}
}

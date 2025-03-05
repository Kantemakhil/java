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
import net.syscon.s4.triggers.QmAtRepository;
import net.syscon.s4.triggers.VQmAt;

@Repository
public class QmAtRepositoryImpl extends RepositoryBase implements QmAtRepository {
	private final Logger logger = LogManager.getLogger(QmAtRepositoryImpl.class);
	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	@Override
	public List<VQmAt> getVQmAt() {
		final String sql = getQuery("QM_AT_V_QM_AT");
		final RowMapper<VQmAt> rowMapper = Row2BeanRowMapper.makeMapping(sql, VQmAt.class, mapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), rowMapper);
		} catch (final Exception e) {
			logger.error("getVQmAt", e);
			return Collections.emptyList();
		}
	}

}

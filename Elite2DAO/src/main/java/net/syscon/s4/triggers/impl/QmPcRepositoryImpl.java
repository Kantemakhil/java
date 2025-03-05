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
import net.syscon.s4.triggers.QmPcRepository;
import net.syscon.s4.triggers.VQmPc;

@Repository
public class QmPcRepositoryImpl extends RepositoryBase implements QmPcRepository {
	private final Logger logger = LogManager.getLogger(QmAtRepositoryImpl.class);
	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	@Override
	public List<VQmPc> getVQmPc() {
		final String sql = getQuery("QM_PC_V_QM_PC");
		final RowMapper<VQmPc> rowMapper = Row2BeanRowMapper.makeMapping(sql, VQmPc.class, mapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), rowMapper);
		} catch (final Exception e) {
			logger.error("getVQmPc", e);
			return Collections.emptyList();
		}
	}

}

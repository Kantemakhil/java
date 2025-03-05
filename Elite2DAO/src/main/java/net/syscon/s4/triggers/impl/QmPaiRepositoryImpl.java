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
import net.syscon.s4.triggers.QmPaiRepository;
import net.syscon.s4.triggers.VQmPai;

@Repository
public class QmPaiRepositoryImpl extends RepositoryBase implements QmPaiRepository {
	private final Logger logger = LogManager.getLogger(QmPaiRepositoryImpl.class);
	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	public List<VQmPai> getVQmPai() {
		final String sql = getQuery("QM_PAI_V_QM_PAI");
		final RowMapper<VQmPai> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql, VQmPai.class, mapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper);
		} catch (final Exception e) {
			logger.error("getVQmPai", e);
			return Collections.emptyList();
		}
	}

}

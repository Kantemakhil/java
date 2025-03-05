package net.syscon.s4.pkgs.otdttacc.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.pkgs.otdttacc.OtdttaccPkgRepository;

@Repository
public class OtdttaccPkgRepositoryImpl extends RepositoryBase implements OtdttaccPkgRepository {

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId")).build();

	@Override
	public List<BigDecimal> getListOfRootOffenderId() {
		final String sql = getQuery("GET_ROOT_OFFENDER_ID_FM_OFFENDERS");
		final RowMapper<BigDecimal> rowMapper = Row2BeanRowMapper.makeMapping(sql, BigDecimal.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), rowMapper);
	}

}
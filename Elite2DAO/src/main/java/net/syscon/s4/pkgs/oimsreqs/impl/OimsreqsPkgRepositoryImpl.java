package net.syscon.s4.pkgs.oimsreqs.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.LegalUpdateReasons;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.pkgs.oimsreqs.OimsreqsPkgRepository;

@Repository
public class OimsreqsPkgRepositoryImpl extends RepositoryBase implements OimsreqsPkgRepository {

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ORDER_PROPOSAL_CONDITION_ID", new FieldMapper("orderProposalConditionId")).build();

	@Override
	public List<LegalUpdateReasons> getDescNdActive(final String updateReasonCode) {
		final String sql = getQuery("GET_DESC_ND_ACTIVE");
		final RowMapper<LegalUpdateReasons> rowMapper = Row2BeanRowMapper.makeMapping(sql, LegalUpdateReasons.class,
				mapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_code", updateReasonCode), rowMapper);
	}

}

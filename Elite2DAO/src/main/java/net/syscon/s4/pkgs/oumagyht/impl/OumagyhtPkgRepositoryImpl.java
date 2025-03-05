package net.syscon.s4.pkgs.oumagyht.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocationAmendments;
import net.syscon.s4.pkgs.oumagyht.OumagyhtPkgRepository;

@Repository
public class OumagyhtPkgRepositoryImpl extends RepositoryBase implements OumagyhtPkgRepository {
	public OumagyhtPkgRepositoryImpl() {

	}

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	@Override
	public Integer insertIntoAgyLocAmendments(final AgencyLocationAmendments agyLocAmendments) {
		final String sql = getQuery("OUMAGYHT_INSERT_INTO_AGY_LOC_AMENDMENTS");
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(agyLocAmendments));
		if (returnArray == 0) {
			returnArray = 0;
			return returnArray;
		}
		return returnArray;
	}

	@Override
	public List<Addresses> getAddressOwnerCode(final Long pAddrId) {
		final String sql = getQuery("OUMAGYHT_GET_ADDRESS_OWNER_CODE");
		final RowMapper<Addresses> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Addresses.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("pAddrId", pAddrId), mRowMapper);
		} catch (final Exception e) {
			return Collections.emptyList();
		}
	}

}

package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.AddressesT4Repository;

@Repository
public class AddressesT4RepositoryImpl extends RepositoryBase implements AddressesT4Repository {
	private static final Logger logger = LogManager.getLogger(AddressesT4RepositoryImpl.class);

	@Override
	public Integer deleteReleasePlansOp(final Addresses object) {
		final String sql = getQuery("DELETE_RELEASE_PLANS_OTHER_OCCUPENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(object));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("deleteReleasePlansOp", e);
		}
		if (1 == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

}

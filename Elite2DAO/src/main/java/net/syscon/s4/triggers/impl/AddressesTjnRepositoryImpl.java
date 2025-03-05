package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.AddressesTjnRepository;

@Repository
public class AddressesTjnRepositoryImpl extends RepositoryBase implements AddressesTjnRepository {

	private static final Logger logger = LogManager.getLogger(AddressesTjnRepositoryImpl.class);
	@Override
	public void addressesTjnInsertOperation(final Addresses newBean) {
		final String sql = getQuery("ADDRESSES_TJN_INSERT_OPERATION");
		try {
			namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(newBean));
		} catch (Exception e) {
			logger.error("addressesTjnInsertOperation", e);
		}

	}

}

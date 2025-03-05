package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.AddressesTwfRepository;

@Repository
public class AddressesTwfRepositoryImpl extends RepositoryBase implements AddressesTwfRepository {
	private static final Logger logger = LogManager.getLogger(AddressesTwfRepositoryImpl.class);

	@Override
	public Addresses getAddressesObject(final Long addressId) {
		final String sql = getQuery("GET_ADDRESSES_OBJECT");
		Addresses retObj = new Addresses();
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("addressId", addressId),
					new BeanPropertyRowMapper<Addresses>(Addresses.class));
		} catch (Exception e) {
			logger.error("getAddressesObject", e);
			retObj = null;
		}
		return retObj;
	}

	@Override
	public BigDecimal getOffenderBookId(final BigDecimal offenderBookId) {
		final String sql = getQuery("GET_OFFENDER_BOOK_ID");
		BigDecimal resp = null;
		try {
			resp = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_offender_book_id", offenderBookId), BigDecimal.class);
		} catch (Exception e) {
			logger.error("getOffenderBookId :" + e);
		}
		return resp;
	}
	
	@Override
	public BigDecimal getAddressId(final BigDecimal offenderBookId) {
		final String sql = getQuery("GET_ADDRESS_ID");
		BigDecimal resp = null;
		try {
			resp = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offender_book_id", offenderBookId), BigDecimal.class);
		} catch (Exception e) {
			logger.error("getAddressId :" + e);
		}
		return resp;
	}
	
	@Override
	public Integer getDistinct1(final BigDecimal offenderBookId, final String modifyUserId, final String createUserId) {
		final String sql = getQuery("GET_DISTINCT_1");
		Integer resp = null;
		try {
			resp = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offender_book_id", offenderBookId,
							"modify_user_id", modifyUserId,
							"create_user_id", createUserId), Integer.class);
		} catch (Exception e) {
			logger.error("getDistinct1 :" + e);
		}
		return resp;
	}


}

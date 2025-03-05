package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.OffenderInstRequests;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.triggers.AddressesT4Repository;
import net.syscon.s4.triggers.AfterUpdateOffInstReqRepository;

@Repository
public class AfterUpdateOffInstReqRepositoryImpl extends RepositoryBase implements AfterUpdateOffInstReqRepository {
	private static final Logger logger = LogManager.getLogger(AfterUpdateOffInstReqRepositoryImpl.class);

	@Override
	public OffenderInstRequests getOffenderInstRequests(final BigDecimal offInstReqId) {
		final String sql = getQuery("GET_OFFENDER_INST_REQUESTS");
		OffenderInstRequests retObj = new OffenderInstRequests();
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFF_INST_REQ_ID", offInstReqId),
					OffenderInstRequests.class);
		} catch (Exception e) {
			logger.error("getOffenderInstRequests", e);
			retObj = null;
		}
		return retObj;
	}
	
	@Override
	public Map<String, Object> getData(final BigDecimal offenderBookingId) {
		final String sql = getQuery("GET_DATA");
		Map<String, Object> map = new HashMap<>();
		try {
			map = namedParameterJdbcTemplate.queryForMap(sql, createParams("offender_booking_id", offenderBookingId));
		} catch (Exception e) {
			logger.error("getData :", e);
		}
		return map;
	}
	
	@Override
	public Map<String, Object> getData1(final String lvDurationCode,final String lvRequestType,final String lvRequestReason) {
		final String sql = getQuery("GET_DATA");
		Map<String, Object> map = new HashMap<>();
		try {
			map = namedParameterJdbcTemplate.queryForMap(sql, createParams("lv_duration_code", lvDurationCode,
					"lv_request_type", lvRequestType,"lv_request_reason", lvRequestReason));
		} catch (Exception e) {
			logger.error("getData1 :", e);
		}
		return map;
	}

}

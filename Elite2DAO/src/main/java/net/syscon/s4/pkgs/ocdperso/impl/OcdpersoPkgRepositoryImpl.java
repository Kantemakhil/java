package net.syscon.s4.pkgs.ocdperso.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderVisitVisitors;
import net.syscon.s4.pkgs.ocdperso.OcdpersoPkgRepository;

@Repository
public class OcdpersoPkgRepositoryImpl extends RepositoryBase implements OcdpersoPkgRepository {
	private static Logger logger = LogManager.getLogger(OcdpersoPkgRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_VISIT_ID", new FieldMapper("offenderVisitId")).build();

	@Override
	public List<OffenderVisitVisitors> getfutureVisits(final Long pOffenderBookId, final Long pPersonId) {
		final String sql = getQuery("GET_FUTURE_VISITS");
		List<OffenderVisitVisitors> retList = new ArrayList<OffenderVisitVisitors>();
		final RowMapper<OffenderVisitVisitors> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderVisitVisitors.class, mapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql,
					createParams("P_OFFENDER_BOOK_ID", pOffenderBookId, "P_PERSON_ID", pPersonId), rowMapper);
		} catch (Exception e) {
			logger.error("getfutureVisits :", e);
			retList = null;
		}
		return retList;
	}

	@Override
	public Integer getCount(final BigDecimal pVisitId) {
		final String sql = getQuery("GET_COUNT");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_VISIT_ID", pVisitId), Integer.class);
		} catch (Exception e) {
			logger.error("getCount:" + e);
			count = 0;
		}
		return count;
	}

	@Override
	public Integer updateOffVisitVisitorsOne(final BigDecimal pVisitId, final Long pPersonId, final String userName) {
		final String sql = getQuery("CANCEL_FUTURE_VISIT_UPDATE_ONE");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("OFFENDER_VISIT_ID", pVisitId, "P_PERSON_ID", pPersonId, "modifyUserId", userName));
		} catch (DataAccessException e) {
			logger.error("updateOffVisitVisitorsOne :" + e);
			count = 0;
		}
		return count;
	}

	@Override
	public Integer updateOffenderVisitSecond(final BigDecimal pVisitId, final String userName) {
		final String sql = getQuery("CANCEL_FUTURE_VISIT_UPDATE_SECOND");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("OFFENDER_VISIT_ID", pVisitId, "modifyUserId", userName));
		} catch (DataAccessException e) {
			logger.error("updateOffenderVisitSecond :" + e);
			count = 0;
		}
		return count;
	}

	@Override
	public Integer updateOffVisitVisitorsThird(final BigDecimal pVisitId, final String userName) {
		final String sql = getQuery("CANCEL_FUTURE_VISIT_UPDATE_THIRD");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("OFFENDER_VISIT_ID", pVisitId, "modifyUserId", userName));
		} catch (DataAccessException e) {
			logger.error("updateOffVisitVisitorsThird :" + e);
			count = 0;
		}
		return count;
	}

	@Override
	public List<Persons> getNamesCur(final Long personId) {
		final String sql = getQuery("GET_NAMES_CUR");
		final RowMapper<Persons> rowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, mapping);
		List<Persons> retList = new ArrayList<Persons>();
		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams("p_person_id", personId), rowMapper);
		} catch (Exception e) {
			logger.error("getNamesCur", e);
			retList = null;
		}
		return retList;
	}

	@Override
	public Integer getNextAddressId() {
		final String sql = getQuery("GET_NEW_PERSON_ID_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	@Override
	public Integer InsertOpCopyOffAddr(final Integer lvAddressId, final BigDecimal pRootOffId, final Long pPersonId,
			final String userName) {
		final String sql = getQuery("INSERT_OPERATION_COPY_OFF_ADDR");
		return namedParameterJdbcTemplate.update(sql, createParams("LV_ADDRESS_ID", lvAddressId, "P_PERSON_ID",
				pPersonId, "P_ROOT_OFF_ID", pRootOffId, "createUserId", userName));
	}

	@Override
	public Addresses getAddressT1Object(final BigDecimal pRootOffId) {
		final String sql = getQuery("GET_ADDRESS_T1_OBJECT");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_ROOT_OFF_ID", pRootOffId),
					new BeanPropertyRowMapper<Addresses>(Addresses.class));
		} catch (EmptyResultDataAccessException e) {
			return new Addresses();
		}
	}

	@Override
	public Addresses getAddressT3Object(final Integer lvAddressId) {
		final String sql = getQuery("GET_ADDRESS_T3_OBJECT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("ADDRESS_ID", lvAddressId),
				new BeanPropertyRowMapper<Addresses>(Addresses.class));
	}

}
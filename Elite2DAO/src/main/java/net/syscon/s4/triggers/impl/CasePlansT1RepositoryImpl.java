package net.syscon.s4.triggers.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderActionPlans;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.triggers.CasePlansT1Repository;

/**
 * Class CasePlansT1RepositoryImpl
 * 
 */
@Repository
public class CasePlansT1RepositoryImpl extends RepositoryBase implements CasePlansT1Repository{

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(CasePlansT1RepositoryImpl.class);
	private final Map<String, FieldMapper> offenderActionPlansMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("end_date", new FieldMapper("endDate")).build();

	final Map<String, FieldMapper> casePlansMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).build();
	
	@Override
	public Integer updateOffenderBookings(final CasePlans casePlans) {
		final String sql = getQuery("UPDATE_OFFENDER_BOOKINGS_CASE_PLANST1");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer retVal = 0;

		inParamMap.put("role", casePlans.getRole());
		inParamMap.put("SAC_STAFF_ID", casePlans.getSacStaffId());
		inParamMap.put("Offender_Book_ID", casePlans.getOffenderBookId());
		inParamMap.put("modifyUserId", casePlans.getModifyUserId());
		try {
			retVal = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			retVal = 0;
			logger.error("updateOffenderBookings", e);
		}
		return retVal;
	}
	
	@Override
	public List<OffenderBookings> getOffBookOldRec(final Long offenderBookId) {
		final String sql = getQuery("UPDATE_OFFENDER_BOOKINGS_OLD_RECORD");
		List<OffenderBookings> obj = new ArrayList<OffenderBookings>();
		final RowMapper<OffenderBookings> offenderActionPlansRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookings.class, offenderActionPlansMapping);
		try {
			obj = namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_BOOK_ID", offenderBookId),
					offenderActionPlansRowMapper);
		} catch (Exception e) {
			logger.error("getOffBooOldRec :" + e);
		}
		return obj;
	}

	
	}



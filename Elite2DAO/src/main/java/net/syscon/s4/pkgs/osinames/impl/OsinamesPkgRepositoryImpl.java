package net.syscon.s4.pkgs.osinames.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.pkgs.osinames.OsinamesPkgRepository;

@Repository
public class OsinamesPkgRepositoryImpl extends RepositoryBase implements OsinamesPkgRepository {

	private static Logger logger = LogManager.getLogger(OsinamesPkgRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).put("LAST_NAME", new FieldMapper("dspLastName"))
			.put("FIRST_NAME", new FieldMapper("dspFirstName")).put("AGY_LOC_ID", new FieldMapper("agyLocId")).build();

	@Override
	public Object[] offDetCur(final Long offenderBookId) {
		final String sql = getQuery("GET_OFF_DETAILS_BY_BOOK_ID_OFF_DET_CUR");
		Object[] returnValue = null;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_OFFENDER_BOOK_ID", offenderBookId), mapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnValue;
	}

	RowMapper<Object[]> mapper = new RowMapper<Object[]>() {
		public Object[] mapRow(final ResultSet rs, int rowNum) throws SQLException {
			Object[] obj = new Object[4];
			obj[0] = rs.getString("OFFENDER_ID_DISPLAY");
			obj[1] = rs.getString("LAST_NAME");
			obj[2] = rs.getString("FIRST_NAME");
			obj[3] = rs.getString("AGY_LOC_ID");

			return obj;
		}
	};

	@Override
	public OffenderBookings getOffenderDetails(String pOffenderIdDisplay, String pAgyLocId, String pCaseloadId) {
		String sql = getQuery("GET_OFFENDER_DETAILS_NEW");
		String sql1 = " select distinct OB.OFFENDER_BOOK_ID, O.LAST_NAME ,O.FIRST_NAME, OB.AGY_LOC_ID from OFFENDERS O, OFFENDER_BOOKINGS OB where O.OFFENDER_ID_DISPLAY ="
				+"'"+pOffenderIdDisplay.toUpperCase()+"'" + " and O.OFFENDER_ID = OB.OFFENDER_ID and OB.ACTIVE_FLAG = 'Y' and "
				+ sql;
		OffenderBookings obj = new OffenderBookings();
		final RowMapper<OffenderBookings> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				mMapping);
		try {
			obj = namedParameterJdbcTemplate.queryForObject(sql1, createParams("P_OFFENDER_ID_DISPLAY",
					pOffenderIdDisplay, "P_CASELOAD_ID", pCaseloadId, "P_AGY_LOC_ID", pAgyLocId),
					rowMapper);
		} catch (Exception e) {
			logger.error("getOffenderDetails " + e);
		}
		return obj;
	}

	@Override
	public BigDecimal getOffBookId(final String vstOffIdDisplay,String userId) {
		final String sql = getQuery("GET_OFF_BOOK_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_off_id_disp", vstOffIdDisplay,"userId",userId),
				BigDecimal.class);
	}

}
package net.syscon.s4.cm.intakeclosure.intakeclosureaddremoveoffices.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.intakeclosure.intakeclosureaddremoveoffices.OcdrlfccRepository;
import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;

/**
 * Class OcdrlfccRepositoryImpl
 */
@Repository
public class OcdrlfccRepositoryImpl extends RepositoryBase implements OcdrlfccRepository {

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", 					new FieldMapper("listSeq"))
			.put("CODE", 						new FieldMapper("code"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("DSP_DESCRIPTION", 			new FieldMapper("dspDescription"))
			.put("MODE", 						new FieldMapper("mode"))
			.put("REMOVED_REASON_CODE", 		new FieldMapper("removedReasonCode"))
			.build();
	private final Map<String, FieldMapper> offenderBookingAgyLocsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ADDITION_DATE", 				new FieldMapper("additionDate"))
			.put("CASELOAD_ID", 				new FieldMapper("caseloadId"))
			.put("AGY_LOC_ID", 					new FieldMapper("agyLocId"))
			.put("OFFENDER_BOOK_ID", 			new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", 					new FieldMapper("listSeq"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", 					new FieldMapper("agyLocId"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("LONG_DESCRIPTION", 			new FieldMapper("longDescription"))
			.put("CASELOAD_ID", 				new FieldMapper("caseloadId"))
			.put("OFFENDER_BOOK_ID", 			new FieldMapper("offenderBookId"))
			.build();

	/**
	 * Creates new OcdrlfccRepositoryImpl class Object
	 */
	public OcdrlfccRepositoryImpl() {
		// OcdrlfccRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderBookingAgyLocs
	 *
	 * @return List<OffenderBookingAgyLocs>
	 */
	public List<OffenderBookingAgyLocs> offagyExecuteQuery(final OffenderBookingAgyLocs objSearchDao) {
		final String sql = getQuery("OCDRLFCC_OFFAGY_FIND_OFFENDER_BOOKING_AGY_LOCS");
		final RowMapper<OffenderBookingAgyLocs> offBALRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookingAgyLocs.class, offenderBookingAgyLocsMapping);
		List<OffenderBookingAgyLocs> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDERBOOKID", objSearchDao.getOffenderBookId()), offBALRowMapper);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkOffagy1DspDescriptionRecordGroup() {
		final String sql = getQuery("OCDRLFCC_FIND_CGFKOFFAGY1DSPDESCRIPTION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 */
	public List<OffenderBookingAgyLocs> offBkgOnCheckDeleteMaster(final OffenderBookingAgyLocs paramBean) {
		final String sql = getQuery("OCDRLFCC_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderBookingAgyLocs> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookingAgyLocs.class, offenderBookingAgyLocsMapping);
		List<OffenderBookingAgyLocs> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offagyOnCheckDeleteMaster
	 *
	 * @param params
	 */
	public List<OffenderBookingAgyLocs> offagyOnCheckDeleteMaster(final OffenderBookingAgyLocs paramBean) {
		final String sql = getQuery("OCDRLFCC_OFFAGY_ONCHECKDELETEMASTER");
		final RowMapper<OffenderBookingAgyLocs> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookingAgyLocs.class, offenderBookingAgyLocsMapping);
		List<OffenderBookingAgyLocs> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffagyAgencyLocatio
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> cgfkchkOffagyAgencyLocatio(final AgencyLocations paramBean) {
		final String sql = getQuery("OCDRLFCC_CGFKCHK_OFFAGY_AGENCY_LOCATIO");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		List<AgencyLocations> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffagy1OffagyRefCo
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkOffagy1OffagyRefCo(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDRLFCC_CGFKCHK_OFFAGY1_OFFAGY_REF_CO");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffagy1OffagyRefCo
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfklkpOffagy1OffagyRefCo(final ReferenceCodes paramBean) {
		final String sql = getQuery("OCDRLFCC_CGFKLKP_OFFAGY1_OFFAGY_REF_CO");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffagy1AgencyLocati
	 *
	 * @param params
	 *
	 */
	public String cgfkchkOffagyAgencyLocation(final OffenderBookingAgyLocs paramBean) {
		final String sql = getQuery("OCDRLFCC_CGFKCHK_OFFAGY_AGENCY_LOCATION");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("AGYLOCID", paramBean.getAgyLocId()), String.class);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * String
	 *
	 * @param paramBean
	 *
	 */
	public String gettingValidation(final OffenderBookingAgyLocs paramBean) {
		final String sql = getQuery("OCDRLFCC_OFFENDER_BOOKING_AGY_LOCS_INTAKE");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate
					.queryForObject(sql,
							createParams("AGYLOCID", paramBean.getAgyLocId(), "OFFENDERBOOKID",
									paramBean.getOffenderBookId(), "ADDITIONDATE", paramBean.getAdditionDate()),
							String.class);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * String
	 *
	 * @param paramBean
	 *
	 */
	public Integer countOffenderBookId(final Long offenderBookId) {
		final String sql = getQuery("OCDRLFCC_COUNT_OFFENDER_BOOK_ID");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", offenderBookId),
					Integer.class);
		} catch (Exception e) {
			returnList = 0;
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * String
	 *
	 * @param paramBean
	 *
	 */
	public String getYOffenderBookingAgyLocs(final OffenderBookingAgyLocs paramBean) {
		final String sql = getQuery("OCDRLFCC_Y_OFFENDER_BOOKING_AGY_LOCS");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID",
					paramBean.getOffenderBookId(), "ADDITIONDATE", paramBean.getAdditionDate()), String.class);
		} catch (Exception e) {
			returnList = "N";
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * String
	 *
	 * @param paramBean
	 *
	 */
	public String agyLocIdEndDate(final OffenderBookingAgyLocs paramBean) {
		final String sql = getQuery("OCDRLFCC_AGY_LOC_ID_END_DATE");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()), String.class);
		} catch (Exception e) {
			returnList = "N";
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param offBook
	 *            OffenderBookings
	 *
	 * @return Integer
	 */
	public Integer updateOffenderBookingAgyLocs(final OffenderBookingAgyLocs offBook) {
		final String sql = getQuery("OCDRLFCC_UPDATE_OFFENDER_BOOKING_AGY_LOCS");
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(offBook));
		if (returnArray == 0) {
			returnArray = 0;
			return returnArray;
		}
		return returnArray;
	}

	/**
	 * method for preInsert
	 * 
	 * @return Integer
	 * @param offenderBookId
	 */
	public BigDecimal alertPreInsertc(final Long offenderBookId) {
		BigDecimal returnObj = null;
		final String sql = getQuery("OCDRLFCC_MAX_EVENT_SEQ");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
				BigDecimal.class);
		return returnObj;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param offBook
	 *            OffenderBookings
	 *
	 * @return Integer
	 */
	public Integer insertOffenderBookingAgyLocs(final OffenderBookingAgyLocs offBook) {
		final String sql = getQuery("OCDRLFCC_INSERT_OFFENDER_BOOKING_AGY_LOCS");
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(offBook));
		if (returnArray == 0) {
			returnArray = 0;
			return returnArray;
		}
		return returnArray;
	}
}

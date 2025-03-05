package net.syscon.s4.cm.intakeclosure.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.intakeclosure.OcdaltccRepository;
import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CommunityHeaderStatuses;
import net.syscon.s4.im.beans.ReferenceCodes;

/**
 * Class OcdaltccRepositoryImpl
 * 
 */
@Repository
public class OcdaltccRepositoryImpl extends RepositoryBase implements OcdaltccRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdaltccRepositoryImpl.class);
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("DSP_DESCRIPTION", new FieldMapper("dspDescription"))
			.put("REASON_CODE", new FieldMapper("reasonCode"))
			.build();
	private final Map<String, FieldMapper> offenderBookingAgyLocsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ADDITION_DATE", new FieldMapper("additionDate"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("OFFENDER_STATUS", new FieldMapper("offenderStatus"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("REASON_CODE", new FieldMapper("reasonCode"))
			.put("REMOVED_REASON_CODE", new FieldMapper("removedReasonCode"))
			.put("REMOVED_DATE", new FieldMapper("removedDate"))
			.put("ADDITION_COMMENT", new FieldMapper("additionComment"))
			.put("ADDITION_TIME", new FieldMapper("additionTime"))
			.build();
	private final Map<String, FieldMapper> offenderBookingEventsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EVENT_DATE", new FieldMapper("eventDate"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("STATUS_CODE", new FieldMapper("statusCode"))
			.put("CODE", new FieldMapper("code"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))			
			.build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("DSP_DESCRIPTION22", new FieldMapper("dspDescription22"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.build();
	
	/**
	 * Creates new OcdaltccRepositoryImpl class Object
	 */
	public OcdaltccRepositoryImpl() {
		// OcdaltccRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderAssessments
	 *
	 * @return List<OffenderAssessments>
	 *
	 * @throws SQLException
	 */
	public List<OffenderBookingAgyLocs> offagyExecuteQuery(final OffenderBookingAgyLocs objSearchDao) {
		final String sql = getQuery("OCDALTCC_OFFAGY_FIND_OFFENDER_BOOKING_AGY_LOCS");
		final RowMapper<OffenderBookingAgyLocs> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookingAgyLocs.class, offenderBookingAgyLocsMapping);
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getOffenderBookId() != 0) {
				sqlQuery.append("OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID" + " AND ");
				inParameterSource.addValue("OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId());
			}

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		if (objSearchDao != null && objSearchDao.getReasonCode() != null) {
			preparedSql = preparedSql + "ORDER BY ADDITION_DATE ASC";
		} else{
		 preparedSql = preparedSql + "ORDER BY ADDITION_DATE DESC";
		}
		List<OffenderBookingAgyLocs> returnList = namedParameterJdbcTemplate.query(preparedSql, inParameterSource,
				rowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderBookingAgyLocs
	 *            List<OffenderBookingAgyLocs>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */


	public Integer offagyInsertOffenderBookingAgyLocsEvents(final List<OffenderBookingAgyLocs> requestData) {
		final String sql = getQuery("OCDALTCC_PRE_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderBookingAgyLocs obj : requestData) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (requestData.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderBookingAgyLocs
	 *            List<OffenderBookingAgyLocs>
	 *
	 * 
	 */
	public Integer offagyUpdateOffenderBookingAgyLocs(final List<OffenderBookingAgyLocs> listObj) {
		final String sql = getQuery("OCDALTCC_OFFAGY_UPDATE_OFFENDER_BOOKING_AGY_LOCS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderBookingAgyLocs obj : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<CommunityHeaderStatuses> offenderStatusRecordGroup() {
		final String sql = getQuery("OCDALTCC_FIND_OFFENDERSTATUS");
		final RowMapper<CommunityHeaderStatuses> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CommunityHeaderStatuses.class, mMapping);
		List<CommunityHeaderStatuses> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("offenderStatusRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> cgfkOffagy1DspDescription22RecordGroup(final Long offenderBookId) {
		final String sql = getQuery("OCDALTCC_FIND_CGFKOFFAGY1DSPDESCRIPTION22");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		List<AgencyLocations> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId),
					mRowMapper);
		} catch (Exception e) {
			logger.error("cgfkOffagy1DspDescription22RecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkOffagy1DspDescriptionRecordGroup() {
		final String sql = getQuery("OCDALTCC_FIND_CGFKOFFAGY1DSPDESCRIPTION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("cgfkOffagy1DspDescriptionRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offagyOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderBookingAgyLocs> offagyOnCheckDeleteMaster(final OffenderBookingAgyLocs paramBean) {
		final String sql = getQuery("OCDALTCC_OFFAGY_ONCHECKDELETEMASTER");
		final RowMapper<OffenderBookingAgyLocs> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookingAgyLocs.class, offenderBookingAgyLocsMapping);
		final ArrayList<OffenderBookingAgyLocs> returnList = (ArrayList<OffenderBookingAgyLocs>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
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
		final String sql = getQuery("OCDALTCC_CGFKCHK_OFFAGY_AGENCY_LOCATIO");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final ArrayList<AgencyLocations> returnList = (ArrayList<AgencyLocations>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
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
		final String sql = getQuery("OCDALTCC_CGFKCHK_OFFAGY1_OFFAGY_REF_CO");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
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
		final String sql = getQuery("OCDALTCC_CGFKLKP_OFFAGY1_OFFAGY_REF_CO");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
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
	public List<AgencyLocations> cgfkchkOffagy1AgencyLocati(final AgencyLocations paramBean) {
		final String sql = getQuery("OCDALTCC_CGFKCHK_OFFAGY1_AGENCY_LOCATI");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final ArrayList<AgencyLocations> returnList = (ArrayList<AgencyLocations>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfklkpOffagy1AgencyLocati
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> cgfklkpOffagy1AgencyLocati(final AgencyLocations paramBean) {
		final String sql = getQuery("OCDALTCC_CGFKLKP_OFFAGY1_AGENCY_LOCATI");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final ArrayList<AgencyLocations> returnList = (ArrayList<AgencyLocations>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cguvchkOffagyPk
	 *
	 * @param params
	 *
	 */
	public List<OffenderBookingAgyLocs> cguvchkOffagyPk(final OffenderBookingAgyLocs paramBean) {
		final String sql = getQuery("OCDALTCC_CGUVCHK_OFFAGY_PK");
		final RowMapper<OffenderBookingAgyLocs> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookingAgyLocs.class, offenderBookingAgyLocsMapping);
		final ArrayList<OffenderBookingAgyLocs> returnList = (ArrayList<OffenderBookingAgyLocs>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	public String cgfkchkOffagyAgencyLocatio(final String agyLocId) {
		final String sql = getQuery("OCDALTCC_CGFKCHK_OFFAGY_AGENCY_LOCATIO");
		String desc = null;
		try {
			desc = namedParameterJdbcTemplate.queryForObject(sql, createParams("AGYLOCID", agyLocId), String.class);
		} catch (Exception e) {
			logger.error("cgfkchkOffagyAgencyLocatio", e);
		}
		return desc;
	}

	public Date eventDate(final OffenderBookingAgyLocs searchRecord) {
		final String sql = getQuery("OCDALTCC_EVENTDATE");
		Date eventDate = null;
		try {
			eventDate = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", searchRecord.getOffenderBookId()), Date.class);
		} catch (Exception e) {
			logger.error("eventDate", e);
		}
		return eventDate;
	}

	public OffenderBookingEvent evntDate(final OffenderBookingAgyLocs searchBean) {
		final String sql = getQuery("OCDALTCC_EVENTDATE");
		OffenderBookingEvent returnObj = new OffenderBookingEvent();
		try {
			final RowMapper<OffenderBookingEvent> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffenderBookingEvent.class, offenderBookingEventsMapping);
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", searchBean.getOffenderBookId()), columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			returnObj = new OffenderBookingEvent();
		}
		return returnObj;
	}

	public Long eventSeq(final Long offenderBookId) {
		final String sql = getQuery("OCDALTCC_EVENTSEQ");
		Long evenSeq = null;
		try {
			evenSeq = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					Long.class);
		} catch (Exception e) {
			logger.error("eventDate", e);
		}
		return evenSeq;
	}
	public List<OffenderBookingAgyLocs> offagy1ExecuteQuery(final OffenderBookingAgyLocs objSearchDao) {
		final String sql = getQuery("OCDALTCC_OFFAGY_FIND_OFFENDER_BOOKING_AGY_ONE_LOCS");
		final RowMapper<OffenderBookingAgyLocs> StaffLocationRolesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookingAgyLocs.class, agencyLocationsMapping);
		List<OffenderBookingAgyLocs> returnList = new ArrayList<OffenderBookingAgyLocs>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", objSearchDao.getOffenderBookId(), "caseloadId",
							objSearchDao.getCaseloadId(), "agyLocId", objSearchDao.getAgyLocId()),
					StaffLocationRolesRowMapper);
		} catch (Exception e) {
			logger.error("calExecuteQuery", e);
		}

		return returnList;
	}

	@Override
	public Integer offagyInsertOffenderBookingAgyLocs(OffenderBookingAgyLocs obj) {
		final String sql = getQuery("OCDALTCC_OFFAGY_INSERT_OFFENDER_BOOKING_AGY_LOCS");
		Integer count = null;
		OffenderBookingAgyLocs bean = new OffenderBookingAgyLocs();
		try {
			count = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("offagyInsertOffenderBookingAgyLocs", e);
			return 0;
		}
		return count;
	}
	
	public Date ocdaltccAdditionDateTrigger(Date additionDate,Date additionTime) {
		final String sql = getQuery("OCDALTCC_ADDITION_DATE_TRIGGER");
		Date getDate=new Date();
		try {
			getDate = namedParameterJdbcTemplate.queryForObject(sql, createParams("ADDITION_DATE",additionDate,"ADDITION_TIME",additionTime), Date.class);
		}catch (Exception e) { 
			logger.error("offagyInsertOffenderBookingAgyLocs", e);
		}
		return getDate;
	}
		
	
	@Override
	public List<ReferenceCodes> getOPenCtRsndomainValues() {
		final String sql = getQuery("OCDALTCC_GET_REASON_DOMAIN_VALUES");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(),
					mRowMapper);
		} catch (Exception e) {
			logger.error("getOPenCtRsndomainValues", e);
		}
		return returnList;
	}

	
	
}

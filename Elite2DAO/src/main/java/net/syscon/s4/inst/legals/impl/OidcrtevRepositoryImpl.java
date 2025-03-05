package net.syscon.s4.inst.legals.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.legals.OidcrtevRepository;
import net.syscon.s4.inst.legals.beans.Court;
import net.syscon.s4.inst.schedules.bean.CourtEvents;

@Repository
public class OidcrtevRepositoryImpl extends RepositoryBase implements OidcrtevRepository {

	private static Logger logger = LogManager.getLogger(OidcrtevRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> courtEvMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EVENT_DATE", new FieldMapper("eventDate")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).put("RESULT_CODE", new FieldMapper("resultCode"))
			.put("COURT_EVENT_TYPE", new FieldMapper("hearingReason")).put("JUDGE_NAME", new FieldMapper("judgeName"))
			.put("START_TIME", new FieldMapper("startTime")).put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("EVENT_OUTCOME", new FieldMapper("eventOutcome")).put("CASE_ID", new FieldMapper("caseId"))
			.put("EVENT_ID", new FieldMapper("eventId")).put("OUTCOME_DATE", new FieldMapper("outcomeDate"))
			.put("OUTCOME_REASON_CODE", new FieldMapper("outcomeReasonCode"))
			.put("AGY_LOC_ID", new FieldMapper("court")).put("END_TIME", new FieldMapper("endTime"))
			.put("LINK_DATA", new FieldMapper("linkData"))			
			.build();

	private final Map<String, FieldMapper> courtListMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("AGY_LOC_ID", new FieldMapper("code")).build();

	private final Map<String, FieldMapper> agyLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).
			put("AGY_LOC_ID", new FieldMapper("agyLocId")).build();
	
	private final Map<String, FieldMapper> mMappingOffender = new ImmutableMap.Builder<String, FieldMapper>()
			.put("startTime", new FieldMapper("createDateTime")).put("eventDate", new FieldMapper("modifyDateTime"))
			.build();

	public List<CourtEvents> crtEveExecuteQuery(final CourtEvents objSearchDao) {
		logger.info(objSearchDao.toString());
		final String sql = getQuery("OIDCRTEV_CRTEVE_FIND_COURT_EVENTS");
		final RowMapper<CourtEvents> courtEvRowMapper = Row2BeanRowMapper.makeMapping(sql, CourtEvents.class,
				courtEvMapping);
		List<CourtEvents> returnList = new ArrayList<CourtEvents>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("offender_book_id", objSearchDao.getOffenderBookId()), courtEvRowMapper);
		} catch (Exception e) {
			returnList = Collections.emptyList();
			logger.error(this.getClass().getName() + " error in crtEveExecuteQuery" + e);
		}
		return returnList;
	}

	@Override
	public List<Court> populateCourtData() {
		List<Court> courtList = new ArrayList<Court>();
		final String sql = getQuery("COURT_DATA");
		final RowMapper<Court> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, Court.class,
				courtListMapping);
		try {
			courtList = namedParameterJdbcTemplate.query(sql, referenceCodeRowMapper);
		} catch (Exception e) {
			courtList = Collections.emptyList();
			logger.error(this.getClass().getName() + " error in populateCourtData" + e.getMessage());
		}
		return courtList;
	}

	public Integer checkScheduleConflict(final CourtEvents objCourts) {
		logger.info(objCourts.toString());
		final String sql = getQuery("OIDCRTEV_CHECK_SCHEDULE_CONFLICT");
		Integer returnList = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String eventDate = sdf.format(objCourts.getEventDate());
		if (objCourts.getEventDate() != null && objCourts.getOffenderBookId() > 0) {
			try {
				returnList = namedParameterJdbcTemplate.queryForObject(sql,
						createParams("OFFENDER_BOOK_ID", objCourts.getOffenderBookId(), "EVENT_DATE", eventDate),
						Integer.class);
			} catch (Exception e) {
				returnList = null;
				logger.error(this.getClass().getName() + " error in checkScheduleConflict" + e.getMessage());
			}
			if (returnList != null && returnList > 0) {
				returnList = objCourts.getOffenderBookId();
			}
		}
		return returnList;
	}

	public Integer crtEveInsertCourtEvents(final List<CourtEvents> lstCourtEvents) {
		logger.info(lstCourtEvents.toString());
		int returnValue = 0;
		final String sql = getQuery("OIDCRTEV_CRTEVE_INSERT_COURT_EVENTS_PRE_EVENT_ID");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourtEvents courtEvents : lstCourtEvents) {
			courtEvents.setEventId(crtEvePreInsert());
			parameters.add(new BeanPropertySqlParameterSource(courtEvents));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in crtEveInsertCourtEvents" + e.getMessage());
		}
		if (lstCourtEvents.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;
	}

	public Integer crtEveUpdateCourtEvents(final List<CourtEvents> lstCourtEvents) {
		logger.info(lstCourtEvents.toString());
		final String sql = getQuery("OIDCRTEV_CRTEVE_UPDATE_COURT_EVENTS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourtEvents courtEvents : lstCourtEvents) {
			parameters.add(new BeanPropertySqlParameterSource(courtEvents));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in crtEveUpdateCourtEvents" + e.getMessage());
		}
		if (lstCourtEvents.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;
	}

	public Integer crtEvePreInsert() {
		final String sql = getQuery("OIDCRTEV_CRT_EVE_PREINSERT");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in crtEvePreInsert" + e.getMessage());
			return null;
		}
	}

	public List<AgencyLocations> hearingreasonRecordGroup() {
		final String sql = getQuery("OIDCRTEV_HEARING_REASON_RECORDGROUP");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agyLocMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + "error in hearingreasonRecordGroup " + e.getMessage());
			return Collections.emptyList();
		}
	}

	@Override
	public List<AgencyLocations> apperancelocationRecordGroup(String agyLocId) {
		final String sql = getQuery("OIDCRTEV_APPERANCE_LOCATION_RECORDGROUP");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agyLocMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in apperancelocationRecordGroup " + e.getMessage());
			return Collections.emptyList();
		}
	}

	public List<CourtEvents> getOffenderCourtData(BigDecimal nsOffenderBookId, CourtEvents courtEvent) {
		final String sql = getQuery("OIDCRTEV_NONASSOCIATION_FIND_COURT_EVENTS_BY_INP");
		final RowMapper<CourtEvents> courtEvRowMapper = Row2BeanRowMapper.makeMapping(sql, CourtEvents.class,
				courtEvMapping);
		List<CourtEvents> returnList = new ArrayList<CourtEvents>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("nsOffenderBookId", nsOffenderBookId,
					"agyLocId", courtEvent.getCourt(), "eventDate", courtEvent.getEventDate()), courtEvRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in getOffenderCourtData " + e.getMessage());
		}
		return returnList;

	}

	@Override
	public List<CourtEvents> getOffenderCourtDataByOMEORVID(BigDecimal nsOffenderBookId, String appearanceLocation,
			Date eventDate) {
		final String sql = getQuery("OIDCRTEV_NONASSOCIATION_FIND_COURT_EVENTS_BY_OEM_OR_VID");
		final RowMapper<CourtEvents> courtEvRowMapper = Row2BeanRowMapper.makeMapping(sql, CourtEvents.class,
				courtEvMapping);
		List<CourtEvents> returnList = new ArrayList<CourtEvents>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("nsOffenderBookId", nsOffenderBookId,
					"appearanceLocation", appearanceLocation, "eventDate", eventDate), courtEvRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in getOffenderCourtDataByOMEORVID " + e.getMessage());
		}

		return returnList;
	}

	@Override
	public List<AgyIntLocProfiles> getNonAssociationType(String internalLocationCode) {
		logger.info(internalLocationCode != null ? internalLocationCode : null);
		final String sql = getQuery("OIDCRTEV_NONASSOCIATATION_TYPES");
		final RowMapper<AgyIntLocProfiles> AgyIntLocMapper = Row2BeanRowMapper.makeMapping(sql, AgyIntLocProfiles.class,
				agyLocMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("internalLocationCode", internalLocationCode),
					AgyIntLocMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in getNonAssociationType" + e.getMessage());
			return Collections.emptyList();
		}

	}

	@Override
	public List<Offenders> getIndividualNonassocationDetailsByOMEORVID(CourtEvents courtevents) {
		
		final String sql = getQuery("OIDCRTEV_GET_NON_ASSOCATION_OFFENDER_DETAILS");
		List<Offenders> returnList = new ArrayList<Offenders>();
		final RowMapper<Offenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class, mMappingOffender);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", courtevents.getOffenderBookId(), "caseLoad",
							courtevents.getCaseLoad(), "internalLocationCode", courtevents.getAppearanceLocation(),
							"eventDate", courtevents.getEventDate()),
					mRowMapper);
		} catch (Exception e) {
			logger.error("In getExternalGangNonAssocationForINP method : ", e);
			returnList = Collections.emptyList();
		}

		return returnList;
		
	}

	@Override
	public String getDefaultCancellationReason() {
		final String sql = getQuery("OIDCRTEV_GET_DEFAULT_CANCELATION_REASON");
		String cancelReason = null;
		try {
			cancelReason = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			cancelReason = null;
			logger.error("In getDefaultCancellationReason method : ", e);
		}
		return cancelReason;
	}

	@Override
	public Boolean checkScreenAccess(String userId) {
		Integer count = null;
		String sql = getQuery("OIDCRTEV_SCREEN_ACESS_CHECK");
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userId), Integer.class);
		} catch (Exception e) {
			count = 0;
		}

		return count != null && count >= 1 ? true : false;
	}

	@Override
	public Object[] phoneNumberAndEmailCheck(BigDecimal offenderBookId) {
		final String sql = getQuery("OIDCRTEV_EMAIL_AND_PHONE_NUMBER_CHECK");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					new RowMapper<Object[]>() {
						public Object[] mapRow(final ResultSet rs, int rowNum) throws SQLException {
							Object[] obj = new Object[2];
							obj[0] = rs.getInt("emailCount");
							obj[1] = rs.getInt("phoneNumberCount");

							return obj;
						}
					});
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

	}
	
	@Override
	public List<AgencyLocations> getApperancelocationRecordGroup(String agyLocId) {
		final String sql = getQuery("OIDCRTEV_GET_APPERANCE_LOCATION_RECORDGROUP");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agyLocMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("AGY_LOC_ID",agyLocId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(this.getClass().getName() + " error in apperancelocationRecordGroup " + e.getMessage());
			return Collections.emptyList();
		}
	}

}

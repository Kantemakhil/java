package net.syscon.s4.cm.programsservices.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.programsservices.OcsproinRepository;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.Teams;

@Repository
public class OcsproinRepositoryImpl extends RepositoryBase implements OcsproinRepository {

	public OcsproinRepositoryImpl() {

	}

	private static Logger logger = LogManager.getLogger(OcsproinRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_NAME", new FieldMapper("corporateName")).put("CORPORATE_ID", new FieldMapper("corporateId"))
			.put("PROVIDER_PARTY_ID", new FieldMapper("providerPartyId")).put("AREA", new FieldMapper("area"))
			.put("COUNTRY", new FieldMapper("country")).put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code")).put("TEAM_CODE", new FieldMapper("teamCode"))
			.put("ZIP_POSTAL_CODE", new FieldMapper("zipPostalCode")).put("HOUSE", new FieldMapper("house"))
			.put("STREET", new FieldMapper("street")).put("TEAM_ID", new FieldMapper("teamId"))
			.put("placementCorporateId", new FieldMapper("placementCorporateId"))
			.put("allocatedOffender", new FieldMapper("allocatedOffender"))
			.put("referredOffenders", new FieldMapper("referredOffenders"))
			.put("target_off_flag", new FieldMapper("targetOffFlag"))
			.put("sentence_seq", new FieldMapper("sentenceSeq"))

			
			.build();

	private final Map<String, FieldMapper> offExecMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LAST_NAME", new FieldMapper("dspLastName")).put("FIRST_NAME", new FieldMapper("dspFirstName"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("CONDITION_START_DATE", new FieldMapper("conditionStartDate"))
			.put("CONDITION_END_DATE", new FieldMapper("conditionEndDate"))
			.put("OFFENDER_END_DATE", new FieldMapper("offenderEndDate"))
			.put("CONDITION_LENGTH", new FieldMapper("conditionLength")).put("WEEKDAY", new FieldMapper("weekday"))
			.put("REMAININGHOURS", new FieldMapper("remainingHours"))
			.put("REFERRAL_DATE", new FieldMapper("referralDate")).put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).build();

	public List<Teams> rgTeamRecordGroup(String user) {
		final String sql = getQuery("OCSPROIN_FIND_RECORD_GROUP_TEAM");
		final RowMapper<Teams> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("createUserId", user), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in Class " + this.getClass().getName() + "rgTeamRecordGroup :" + e.getMessage());
			return Collections.emptyList();
		}
	}

	@Override
	public List<CourseActivities> rgProjectsRecordGroup(String teamId) {
		final String sql = getQuery("OCSPROIN_FIND_RGPROJECTS_TEAM");
		final RowMapper<CourseActivities> mRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("NBTTEAMID", teamId), mRowMapper);
		} catch (final Exception e) {
			logger.error(
					"Exception in Class " + this.getClass().getName() + "rgProjectsRecordGroup :" + e.getMessage());

			return Collections.emptyList();
		}
	}

	public List<CourseActivities> courseActExecuteQuery(CourseActivities objSearchDao) {
		final String sql = getQuery("OCSPROIN_COURSEACT_FIND_COURSE_ACTIVITIES");
		final RowMapper<CourseActivities> CourseActivitiesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CourseActivities.class, mMapping);

		List<CourseActivities> returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("team_id", objSearchDao.getProviderPartyId(), "crs_acty_id",
							objSearchDao.getCrsActyId(), "monday_flag", objSearchDao.getMondayFlag(), "tuesday_flag",
							objSearchDao.getTuesdayFlag(), "wednesday_flag", objSearchDao.getWednesdayFlag(),
							"thursday_flag", objSearchDao.getThursdayFlag(), "friday_flag",
							objSearchDao.getFridayFlag(), "saturday_flag", objSearchDao.getSaturdayFlag(),
							"sunday_flag", objSearchDao.getSundayFlag()),
					CourseActivitiesRowMapper);
		} catch (Exception e) {

			logger.error("Exception in OcsproinRepositoryImpl class courseActExecuteQuery : ", e);
			// TODO: handle exception
		}
		return returnList;
	}

	@Override
	public List<CourseActivities> rgProjectRgNoTeam() {
		final String sql = getQuery("OCSPROIN_FIND_RGPROJECTS_WITHOUT_TEAM");
		final RowMapper<CourseActivities> mRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final Exception e) {
			logger.error("Exception in OcsproinRepositoryImpl class rgProjectRgNoTeam : ", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<OffenderBookings> offenderExecQuery(Integer crsActyId) {
		final String sql = getQuery("OCSPROIN_PROGRAM_PROFILES_EXECUTE_QUERY");
		final RowMapper<OffenderBookings> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offExecMapping);
		List<OffenderBookings> returnList = new ArrayList<OffenderBookings>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("CRS_ACTY_ID", crsActyId), mRowMapper);
			returnList.stream().forEach(i -> {
			});
		} catch (final Exception e) {
			logger.error("Exception in Class " + this.getClass().getName() + "offenderExecQuery :" + e.getMessage());
			return Collections.emptyList();
		}

		return returnList;
	}

	@Override
	public List<OffenderBookings> refExecuteQuery(CourseActivities searchBean) {
		final String sql = getQuery("OCSPROIN_PROGRAM_PROFILES_REFERRED_LIST");
		final RowMapper<OffenderBookings> CourseActivitiesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookings.class, offExecMapping);
		List<OffenderBookings> returnList = new ArrayList<OffenderBookings>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("CRS_ACTY_ID", searchBean.getCrsActyId()),
					CourseActivitiesRowMapper);
		} catch (Exception e) {
			logger.error("Exception in Class " + this.getClass().getName() + "refExecuteQuery :" + e.getMessage());
			return Collections.emptyList();
		}
		return returnList;
	}
	
	@Override
	public List<String> schWeekDays(Long offenderBookId,Integer crsActyId) {

		final String sql = getQuery("OCSPROIN_SCH_WEEKDAYS");
		try {
			return namedParameterJdbcTemplate.query(sql,  createParams("CRS_ACTY_ID", crsActyId,"OFFENDER_BOOK_ID",offenderBookId),
					new RowMapper<String>() {
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getString(1);
				}
			});
		} catch (Exception e) {
			logger.error("Exception in Class " + this.getClass().getName() + "schWeekDays :" + e.getMessage());
			return Collections.emptyList();
		}
	}
	
	
}

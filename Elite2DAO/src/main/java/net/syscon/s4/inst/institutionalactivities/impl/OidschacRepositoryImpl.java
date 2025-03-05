package net.syscon.s4.inst.institutionalactivities.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.institutionalactivities.OidschacRepository;
import net.syscon.s4.inst.institutionalactivities.beans.VSchdPrisonActivities;

@Repository
public class OidschacRepositoryImpl extends RepositoryBase implements OidschacRepository{

/**
 * Creates new OidschacRepositoryImpl class Object 
 */
public OidschacRepositoryImpl() {
}
private final Map<String, FieldMapper> vPrisonActMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("SCHEDULE_END_DATE", 				new FieldMapper("scheduleEndDate"))
.put("INTERNAL_LOCATION_DESC", 			new FieldMapper("internalLocationDesc"))
.put("CRS_ACTY_ID", 					new FieldMapper("crsActyId"))
.put("SCHEDULE_DATE", 					new FieldMapper("scheduleDate"))
.put("PROGRAM_ID", 						new FieldMapper("programId"))
.put("SERVICE", 						new FieldMapper("service"))
.put("AGY_LOC_DESC", 					new FieldMapper("agyLocDesc"))
.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
.put("INTERNAL_LOCATION_ID", 			new FieldMapper("internalLocationId"))
.put("ACTIVITY", 						new FieldMapper("activity"))
.put("END_TIME", 						new FieldMapper("endTime"))
.put("CRS_SCH_ID", 						new FieldMapper("crsSchId"))
.put("SCHEDULE_START_DATE", 			new FieldMapper("scheduleStartDate"))
.put("START_TIME", 						new FieldMapper("startTime"))
.build();

/**
* Fetch the records from database table
*
* @param objSearchDao VSchdPrisonActivities
*
* @return List<VSchdPrisonActivities>
*
* @throws SQLException
*/
 public List<VSchdPrisonActivities> schActExecuteQuery(final VSchdPrisonActivities objSearchDao)  {
	 
	 final String sql = getQuery("OIDSCHAC_SCHACT_FIND_V_SCHD_PRISON_ACTIVITIES");
	    Date startTime = null;
	    Date endTime   = null;
		String preparedSql = null;
		Date scheduleDate = null;
		
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			if(objSearchDao.getStartTime()!= null  ) {

				 startTime = truncStart(objSearchDao.getStartTime());
				 
			}
			if(objSearchDao.getEndTime()!= null) {
	
				endTime = truncStart(objSearchDao.getEndTime());
			}
			if(objSearchDao.getScheduleDate()!= null) {
				
				scheduleDate = trunc(objSearchDao.getScheduleDate());
			}
			
			params.addValue("programId", objSearchDao.getProgramId());
			
			params.addValue("agyLocId", objSearchDao.getAgyLocId());
			
			params.addValue("scheduleDate", scheduleDate);
				sqlQuery.append("   AND  ");
			if (objSearchDao.getActivity() != null   && !"".equals(objSearchDao.getActivity())) {
				sqlQuery.append(" ACTIVITY  = :activity " + "AND ");
				params.addValue("activity", objSearchDao.getActivity());
			}

			if (objSearchDao.getInternalLocationDesc() != null && !"".equals(objSearchDao.getInternalLocationDesc())) {
				sqlQuery.append(" INTERNAL_LOCATION_DESC  = :internalLocationDesc " + "AND ");
				params.addValue("internalLocationDesc", objSearchDao.getInternalLocationDesc());
			}
			
			if (objSearchDao.getStartTime()!= null) {
				sqlQuery.append(" START_TIME  = :startTime " + "AND ");
				params.addValue("startTime", startTime);
			}
			if (objSearchDao.getEndTime()!= null) {
				sqlQuery.append(" END_TIME  = :endTime " + " AND ");
				params.addValue("endTime",endTime);
			}

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}

		preSqlQuery = preparedSql.concat("  ORDER BY activity, start_time asc");
		final RowMapper<VSchdPrisonActivities> mapper = Row2BeanRowMapper.makeMapping(preSqlQuery,
				VSchdPrisonActivities.class, vPrisonActMapping);
		List<VSchdPrisonActivities> returnList = new ArrayList<>();
	 
		returnList =	namedParameterJdbcTemplate.query(preSqlQuery, params, mapper);
				return returnList;
	
} 
 
 
 public Date truncStart(final Date date) {
		if (date != null) {
			final Calendar calender = Calendar.getInstance();
			calender.setTime(date);
		
			 calender.set(Calendar.SECOND, 0);
			
			final Date returnDate = calender.getTime();
			return returnDate;
		}
		return null;
	}
 public Date trunc(final Date date) {
		if (date != null) {
			final Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			calender.set(Calendar.HOUR, 0);
			calender.set(Calendar.MINUTE, 0);
			calender.set(Calendar.SECOND, 0);
			calender.set(Calendar.MILLISECOND, 0);
			final Date returnDate = calender.getTime();
			return returnDate;
		}
		return null;
	}

}

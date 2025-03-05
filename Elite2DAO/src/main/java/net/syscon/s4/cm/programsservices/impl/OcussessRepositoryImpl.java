package net.syscon.s4.cm.programsservices.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.programsservices.OcussessRepository;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
/**
 * Class OcussessRepositoryImpl
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@Repository
public class OcussessRepositoryImpl extends RepositoryBase implements OcussessRepository{

/**
 * Creates new OcussessRepositoryImpl class Object 
 */
public OcussessRepositoryImpl() {
}

private final Map<String, FieldMapper> courseSchedulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CRS_SCH_ID", new FieldMapper("crsSchId"))
.put("START_TIME", new FieldMapper("startTime"))
.put("DESCRIPTION", new FieldMapper(" description "))
.put("NBT_INTERNAL_LOCATION_ID", new FieldMapper("nbtInternalLocationId"))
.put("NBT_EVENT_DATE", new FieldMapper("nbtEventDate"))
.put("END_TIME", new FieldMapper("endTime"))
.put("SCHEDULE_DATE", new FieldMapper("scheduleDate"))
.put("CRS_ACTY_ID", new FieldMapper("crsActyId"))
.put("SESSION_NO", new FieldMapper("sessionNo"))
.build();

/**
* Fetch the records from database table
*
* @param objSearchDao CourseSchedules
*
* @return List<CourseSchedules>
*
* @throws SQLException
*/
 public List<CourseSchedules> crsSchExecuteQuery(CourseSchedules objSearchDao)  {
		final String sql = getQuery("OCUSSESS_CRSSCH_FIND_COURSE_SCHEDULES");
		final RowMapper<CourseSchedules> CourseSchedulesRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseSchedules.class,courseSchedulesMapping);
		final ArrayList<CourseSchedules> returnList = (ArrayList<CourseSchedules>)namedParameterJdbcTemplate.query(sql, createParams("crsActyId", objSearchDao.getCrsActyId()), CourseSchedulesRowMapper);
		return returnList;
} 
/**
* @param 
*
* @throws SQLException 
*
*/
public int PRE_INSERT()  {
return 0;
}

}

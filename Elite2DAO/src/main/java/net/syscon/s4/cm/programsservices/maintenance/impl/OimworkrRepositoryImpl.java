package net.syscon.s4.cm.programsservices.maintenance.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import net.syscon.s4.cm.programsservices.maintenance.OimworkrRepository;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;

@Repository
public class OimworkrRepositoryImpl extends RepositoryBase implements OimworkrRepository {

	private static Logger logger = LogManager.getLogger(OimworkrRepositoryImpl.class.getName());

	public OimworkrRepositoryImpl() {
	}
	private final Map<String, FieldMapper> vAddressesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NBT_PROVIDER", new FieldMapper("nbtProvider"))
			.put("ADDRESS_ID", new FieldMapper(" addressId "))
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("PROGRAM_CODE", new FieldMapper("programCode"))
			.put("PROGRAM_ID", new FieldMapper("programId"))
			.build();

	
	public List<CourseActivities> crsActyExecuteQuery(CourseActivities objSearchDao) {
		final String sql = getQuery("OIMWORKR_CRSACTY_FIND_COURSE_ACTIVITIES");
		final RowMapper<CourseActivities> CourseActivitiesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CourseActivities.class, vAddressesMapping);
		final List<CourseActivities> returnList =  namedParameterJdbcTemplate
				.query(sql, createParams("caseloadId",objSearchDao.getCaseloadId()), CourseActivitiesRowMapper);
		return returnList;
	}


	public int PRE_INSERT() {
		return 0;
	}

	
	public Integer crsActyUpdateCourseActivities(final List<CourseActivities> lstCourseActivities) {
		String sql = getQuery("OIMWORKR_CRSACTY_UPDATE_COURSE_ACTIVITIES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CourseActivities courseActivities : lstCourseActivities) {
			parameters.add(new BeanPropertySqlParameterSource(courseActivities));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error(this.getClass().getName() + " crsActyUpdateCourseActivities" + e);		}
		if (lstCourseActivities.size() == lstCourseActivities.size()) {
			return 1;
		} else {
			return 0;
		}

	}


 public Integer crsActyDeleteCourseActivities(final List<CourseActivities> lstCourseActivities)  {
 	int deleteCount=0;
	String sql = getQuery("OIMWORKR_CRSACTY_DELETE_COURSE_ACTIVITIES");
	int[] returnArray = new int[] {};
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (CourseActivities courseActivities : lstCourseActivities) {
		 parameters.add(new BeanPropertySqlParameterSource(courseActivities));
	}
	try {
		String tableName = "COURSE_ACTIVITIES";
		String whereClause = null;
		batchUpdatePreDeletedRows(tableName, whereClause , parameters);
	} catch (Exception e) {
		logger.error("Exception occured in " + this.getClass().getName() + " in method crsActyDeleteCourseActivities", e);
	}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	for (int i = 0; i < returnArray.length; i++) {
		deleteCount = deleteCount++;
	}
	if (lstCourseActivities.size() == deleteCount) {
		return 1;
	} else {
		return 0;
	}

}

	
	public List<ProgramServices> rgProjectTypeRecordGroup() {
		final String sql = getQuery("OIMWORKR_FIND_RGPROJECTTYPE");
		final RowMapper<ProgramServices> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class, vAddressesMapping);
	
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	
	public List<Corporates> rgProviderRecordGroup() {
		final String sql = getQuery("OIMWORKR_FIND_RGPROVIDER");
		final RowMapper<Corporates> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class, vAddressesMapping);
	
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	
	public List<VAddresses> rgProjectLocationRecordGroup(Integer providerId) {
		final String sql = getQuery("OIMWORKR_FIND_RGPROJECTLOCATION");
		final RowMapper<VAddresses> mRowMapper = Row2BeanRowMapper.makeMapping(sql, VAddresses.class, vAddressesMapping);
	
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("providerPartyId",providerId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	
	public List<AgencyLocations> rgAgencyLocationRecordGroup(String caseLoadId) {
		final String sql = getQuery("OIMWORKR_FIND_RGAGENCYLOCATION");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class, vAddressesMapping);
	
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID",caseLoadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	
	public ProgramServices crsActyPostQuery(ProgramServices paramBean) {
		final String sql = getQuery("OIMWORKR_CRS_ACTY_POSTQUERY");
		final RowMapper<ProgramServices> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				vAddressesMapping);
		ProgramServices returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	
	
	public Corporates crsActyPostQuery(Corporates paramBean) {
		final String sql = getQuery("OIMWORKR_CRS_ACTY_POSTQUERY");
		final RowMapper<Corporates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,
				vAddressesMapping);
		Corporates returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	
	public OmsModules createFormGlobals(OmsModules paramBean) {
		final String sql = getQuery("OIMWORKR_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				vAddressesMapping);
		OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	
	public ProgramServices callOcumpvav(ProgramServices paramBean) {
		final String sql = getQuery("OIMWORKR_CALL_OCUMPVAV");
		final RowMapper<ProgramServices> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				vAddressesMapping);
		ProgramServices returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	
	public List<VAddresses> getDefaultLocation(VAddresses paramBean) {
		final String sql = getQuery("OIMWORKR_GET_DEFAULT_LOCATION");
		final RowMapper<VAddresses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VAddresses.class,
				vAddressesMapping);
		final List<VAddresses> returnList = namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	
	public List<CourseScheduleRules> deleteCrsActy(CourseScheduleRules paramBean) {
		final String sql = getQuery("OIMWORKR_DELETE_CRS_ACTY");
		final RowMapper<CourseScheduleRules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CourseScheduleRules.class, vAddressesMapping);
		final List<CourseScheduleRules> returnList =  namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	
	public List<CourseActivities> checkDuplicateCodes(CourseActivities paramBean) {
		final String sql = getQuery("OIMWORKR_CHECK_DUPLICATE_CODES");
		final RowMapper<CourseActivities> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				vAddressesMapping);
		final List<CourseActivities> returnList =namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	
	public Integer crsActyInsertCourseActivities(List<CourseActivities> lstCourseActivities) {
		String sql = getQuery("OIMWORKR_CRSACTY_INSERT_COURSE_ACTIVITIES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for(CourseActivities bean : lstCourseActivities) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" crsActyInsertCourseActivities"+e);
		}
		if (lstCourseActivities.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
	@Override
	public Boolean deleteRecordExist(Long crsActyId) {
		String sql = getQuery("OIMWORKR_COURSE_ACTIVITY_DELETE_EXIST");
		Boolean result = false;
		String data = null;
		try {
			data = namedParameterJdbcTemplate.queryForObject(sql, createParams("crsActy", crsActyId), String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " deleteRecordExist" + e);
		}
		if (data != null) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}
	
	@Override
	public Integer crsActyUpdateCourseActivitiesOne(CourseActivities bean) {
		String sql = getQuery("OIMWORKR_CRSACTY_UPDATE_COURSE_ACTIVITIES_TEMP");
		
		Integer count = 0 ;
		Map<String,Object> map = new HashMap<>();
		map.put("scheduleStartDate", bean.getScheduleStartDate());
		map.put("scheduleEndDate", bean.getScheduleEndDate());
		map.put("modifyUserId",bean.getModifyUserId());
		map.put("crsActyId", bean.getCrsActyId());
		map.put("capacity", bean.getCapacity());
		map.put("activeFlag", bean.getActiveFlag());
		map.put("expiryDate", bean.getExpiryDate());
		map.put("servicesAddressId", bean.getServicesAddressId());

		try {
			count = namedParameterJdbcTemplate.update(sql,map);
		}catch (Exception e) {
			logger.error(this.getClass().getName() + " crsActyUpdateCourseActivitiesOne" + e);
		}
		return count;
	}
	
	public Long CrsActyIdNextVal() {
		final String sql = getQuery("OIMWORKR_CRS_ACTY_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}
	
}

package net.syscon.s4.inst.visitsmanagement.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyVisitSlots;
import net.syscon.s4.inst.visitsmanagement.OiivisitRepository;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderAllVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitDetails;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisits;

/**
 * Class OiivisitRepositoryImpl
 */
@Repository
public class OiivisitRepositoryImpl extends RepositoryBase implements OiivisitRepository {
	private static Logger logger = LogManager.getLogger(OiivisitRepositoryImpl.class.getName());
	/**
	 * Creates new OiivisitRepositoryImpl class Object
	 */
	public OiivisitRepositoryImpl() {
	}

	private final Map<String, FieldMapper> vOffenderVisitDetailsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).put("VISIT_DATE", new FieldMapper("visitDate"))
			.put("INTERNAL_LOCATION_DESC", new FieldMapper("internalLocationDesc"))
			.put("OFFENDER_VISIT_ID", new FieldMapper("offenderVisitId"))
			.put("VISIT_STATUS", new FieldMapper("visitStatus"))
			.put("LIVING_UNIT_DESC", new FieldMapper("livingUnitDesc")).put("END_TIME", new FieldMapper("endTime"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName"))
			.put("VISIT_TYPE", new FieldMapper("visitType")).put("START_TIME", new FieldMapper("startTime"))
			.put("OUTCOME_REASON_CODE", new FieldMapper("outcomeReasonCode"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.build();
	private final Map<String, FieldMapper> vOffenderAllVisitorsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("RELATIONSHIP", new FieldMapper("relationship")).put("VISITOR_ID", new FieldMapper("visitorId"))
			.put("OFFENDER_VISIT_ID", new FieldMapper("offenderVisitId"))
			.put("MIDDLE_NAME", new FieldMapper("middleName")).put("VISITOR_TYPE", new FieldMapper("visitorType"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName")).build();
	
	private final Map<String, FieldMapper> lovMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.put("START_TIME", new FieldMapper("startTime")).put("DESCRIPTION", new FieldMapper("description"))
			.put("'HH24:MI'", new FieldMapper("'hh24:mi'")).put("DISTINCT", new FieldMapper("distinct"))
			.put("TO_CHAR", new FieldMapper("toChar")).put("END_TIME", new FieldMapper("endTime"))
			.put("WEEK_DAY", new FieldMapper("weekDay")).put("agyLocId", new FieldMapper("agyLocId"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOffenderVisitDetails
	 *
	 * @return List<VOffenderVisitDetails>
	 *
	 * 
	 */
	public List<VOffenderVisitDetails> offVisExecuteQuery(final VOffenderVisitDetails objSearchDao) {
		final String sql = getQuery("OIIVISIT_OFFVIS_FIND_V_OFFENDER_VISIT_DETAILS");
		
		final RowMapper<VOffenderVisitDetails> vOffVisitDetRowMapper = Row2BeanRowMapper.makeMapping(sql, VOffenderVisitDetails.class,
				vOffenderVisitDetailsMapping);
		List<VOffenderVisitDetails> returnList = new ArrayList<>();
		    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		    String fromDate = null;
		    String toDate=null;
		    if(objSearchDao.getFromDate()!=null) {	
		    	fromDate=inputFormat.format(objSearchDao.getFromDate());
		    }
		    if(objSearchDao.getToDate()!=null) {
		    	toDate=inputFormat.format(objSearchDao.getToDate());
		    }
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("agyLocId",objSearchDao.getFacility(),
					"fromDate",fromDate,"toDate",toDate,"dayOfTheWeek",objSearchDao.getDayOfTheWeek(),"timeSlotSeq",objSearchDao.getTimeSlotSeq(),
					"visitInternalLocationId",objSearchDao.getVisitInternalLocationId(),"offenderIdDisplay",objSearchDao.getOffenderIdDisplay(),"visitStatus",objSearchDao.getVisitStatus()),
					vOffVisitDetRowMapper);
		} catch (Exception e) {
			logger.error("offVisExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOffenderAllVisitors
	 *
	 * @return List<VOffenderAllVisitors>
	 *
	 * 
	 */
	public List<VOffenderAllVisitors> offImpExecuteQuery(final VOffenderVisitDetails objSearchDao) {
		final String sql = getQuery("OIIVISIT_OFFIMP_FIND_V_OFFENDER_ALL_VISITORS");
		final RowMapper<VOffenderAllVisitors> VOffenderAllVisitorsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderAllVisitors.class, vOffenderAllVisitorsMapping);
		final ArrayList<VOffenderAllVisitors> returnList = (ArrayList<VOffenderAllVisitors>) namedParameterJdbcTemplate
				.query(sql, createParams("offenderVisitId", objSearchDao.getOffenderVisitId(), "offenderIdDisplay",
						objSearchDao.getOffenderIdDisplay()), VOffenderAllVisitorsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstVOffenderAllVisitors List<VOffenderAllVisitors>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer offImpInsertVOffenderAllVisitors(final List<VOffenderAllVisitors> lstVOffenderAllVisitors) {
		final String sql = getQuery("OIIVISIT_OFFIMP_INSERT_V_OFFENDER_ALL_VISITORS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VOffenderAllVisitors list : lstVOffenderAllVisitors) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVOffenderAllVisitors.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	
	public List<AgencyLocations> rgAgencyLocationsRecordGroup(final String caseloadId, final String caseloadType) {
		final String sql = getQuery("OIIVISIT_FIND_RGAGENCYLOCATIONS_DATA");
		final RowMapper<AgencyLocations> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				lovMapping);
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseloadId, "CASELOADTYPE", caseloadType), mMRowMapper);
		} catch (Exception e) {
			logger.error("rgAgencyLocationsRecordGroup", e);
		}
		return returnList;
	}

	@Override
	public List<AgencyInternalLocations> rgAgyVisitDayOfWeekRecordGroup(String agyLocId) {
		final String sql = getQuery("OIIVISIT_FIND_RG_DAY_OF_THE_WEEK_LOV_DATA");
		final RowMapper<AgencyInternalLocations> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyInternalLocations.class,
				lovMapping);
		List<AgencyInternalLocations> returnList = new ArrayList<AgencyInternalLocations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("agyLocId", agyLocId), mMRowMapper);
		} catch (Exception e) {
			logger.error("rgAgencyLocationsRecordGroup", e);
		}
		return returnList;
	}

	@Override
	public List<VOffenderVisits> rgAgyVisitTimeSlotRecRecordGroup(String agyLocId, String weekDay) {
		final String sql = getQuery("OIIVISIT_FIND_RG_TIME_SLOT_OF_THE_WEEK_LOV_DATA");
		final RowMapper<VOffenderVisits> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, VOffenderVisits.class,
				lovMapping);
		List<VOffenderVisits> returnList = new ArrayList<VOffenderVisits>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("agyLocId", agyLocId,"weekDay",weekDay), mMRowMapper);
		} catch (Exception e) {
			logger.error("rgAgencyLocationsRecordGroup", e);
		}
		return returnList;
	}

	@Override
	public List<AgencyVisitSlots> rgAgyVisitSlotsRecRecordGroup(String agyLocId, String weekDay, String timeSlotSeq) {
		final String sql = getQuery("OIIVISIT_FIND_RG_VISIT_SLOTS_LOV_DATA");
		final RowMapper<AgencyVisitSlots> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyVisitSlots.class,
				lovMapping);
		List<AgencyVisitSlots> returnList = new ArrayList<AgencyVisitSlots>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("agyLocId", agyLocId,"weekDay",weekDay,"timeSlotSeq",timeSlotSeq), mMRowMapper);
		} catch (Exception e) {
			logger.error("rgAgencyLocationsRecordGroup", e);
		}
		return returnList;
	}

	@Override
	public Integer offenderVisitsSaveForm(List<VOffenderVisits> updateList) {
		final String sql = getQuery("OIIVISIT_OFFVST_BULK_UPDATE_OFFENDER_VISITS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VOffenderVisits vOffenderVisits : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(vOffenderVisits));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in offenderVisitsSaveForm ", e);
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<AgencyVisitSlots> rgVisitLocationWithoutDay(String agyLocId) {
		final String sql = getQuery("OIIVISIT_FIND_RG_VISIT_SLOTS_LOV_DATA_WITHOUT_DAY");
		final RowMapper<AgencyVisitSlots> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyVisitSlots.class,
				lovMapping);
		List<AgencyVisitSlots> returnList = new ArrayList<AgencyVisitSlots>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("agyLocId",agyLocId), mMRowMapper);
		} catch (Exception e) {
			logger.error("rgVisitLocationWithoutDay", e);
		}
		return returnList;
	}
	
}

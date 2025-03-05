package net.syscon.s4.inst.systemsearch.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.systemsearch.OiimyoffRepository;
@Repository
public class OiimyoffRepositoryImpl extends RepositoryBase implements OiimyoffRepository{

	private static Logger logger = LogManager.getLogger(OiimyoffRepositoryImpl.class);
	
	private final Map<String, FieldMapper> vheaderBlockMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID",          new FieldMapper("offenderId"))
			.put(" ALIAS_OFFENDER_ID  ", new FieldMapper("aliasOffenderId"))
			.put("OFFENDER_ID_DISPLAY",  new FieldMapper("offenderIdDisplay"))
			.put("LAST_NAME", 			 new FieldMapper("lastName"))
			.put("FIRST_NAME",           new FieldMapper("firstName"))
			.put("MIDDLE_NAME", 		 new FieldMapper("middleName"))
			.put("SUFFIX", 				 new FieldMapper("suffix"))
			.put("BIRTH_DATE", 			 new FieldMapper("birthDate"))
			.put("OFFENDER_BOOK_ID", 	 new FieldMapper("offenderBookId"))
			.put("BOOKING_NO", 			 new FieldMapper("bookingNo"))
			.put("BOOKING_BEGIN_DATE",   new FieldMapper("bookingBeginDate"))
			.put("BOOKING_END_DATE",     new FieldMapper("bookingEndDate"))
			.put("AGY_LOC_ID",           new FieldMapper("agyLocId"))
			.put("AGENCY_IML_ID",        new FieldMapper("agencyImlId"))
			.put("LIVING_UNIT_ID",       new FieldMapper("livingUnitId"))
			.put("DISCLOSURE_FLAG",      new FieldMapper("disclosureFlag"))
			.put("ACTIVE_FLAG", 		 new FieldMapper("activeFlag"))
			.put("BOOKING_STATUS",		 new FieldMapper("bookingStatus"))
			.put("LIVING_UNIT_DESCRIPTION", new FieldMapper("livingUnitDescription"))
			.put("IN_OUT_STATUS",        new FieldMapper("inOutStatus"))
			.put("STATUS_DISPLAY",       new FieldMapper("statusDisplay"))
			.put("ROOT_OFFENDER_ID",	 new FieldMapper("rootOffenderId"))
			.put("ASSIGNED_STAFF_ID",	 new FieldMapper("assignedStaffId"))
			.put("AGY_LOC_TYPE", 		 new FieldMapper("agyLocType"))
			.put("CREATE_AGY_LOC_ID",	 new FieldMapper("createAgyLocId"))
			.put("BOOKING_TYPE", 		 new FieldMapper("bookingType"))
			.put("BOOKING_CREATED_DATE", new FieldMapper("bookingCreatedDate"))
			.put("LOCATION_CODE", 		 new FieldMapper("locationCode"))
			.put("INTAKE_AGY_LOC_ID",	 new FieldMapper("intakeAgyLocId"))
			.put("COMMUNITY_ACTIVE_FLAG",new FieldMapper("communityActiveFlag"))
			.put("CREATE_INTAKE_AGY_LOC_ID", new FieldMapper("createIntakeAgyLocId"))
			.put("COMMUNITY_STATUS",     new FieldMapper("communityStatus"))
			.put("STATUS_REASON",        new FieldMapper("statusReason"))
			.put("HEADER_STATUS",        new FieldMapper("headerStatus"))
			.put("AGE", 				 new FieldMapper("age"))
			.put("GENDER", 				 new FieldMapper("gender"))
			.put("OFF_ALERTS",      	 new FieldMapper("offAlerts"))
			.put("STATUS_1", 			 new FieldMapper("status1"))
			.put("STATUS_2", 			 new FieldMapper("status2"))
			.put("STATUS_3", 			 new FieldMapper("status3"))
			.put("ETHNICITY",			 new FieldMapper("ethnicity"))
			.put("MOVEMENT_REASON", 	 new FieldMapper("movementReason"))
			.put("IMAGE_ID", 	         new FieldMapper("imageId"))
			.put("OFF_SUP_LEVEL",		 new FieldMapper("offSupLevel"))
			.put("living_unit_description",		 new FieldMapper("livingUnitDescription"))		
			.put("CASE_PLAN_FLAG",		 new FieldMapper("casePlanFlag"))
			.build();
	
	public List<VHeaderBlock> getMyOffenderList(final VHeaderBlock vHeaderBlock){
		final String sql = getQuery("GET_OIIMYOFF_MY_OFFENDERS_LIST_DATA");	
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vheaderBlockMapping);
		List<VHeaderBlock> myOffendersList=new ArrayList<VHeaderBlock>();
		try {		
			myOffendersList = namedParameterJdbcTemplate.query(sql, createParams("userId",vHeaderBlock.getUserId(),"caseLoadId",vHeaderBlock.getCaseLoadId()), columnRowMapper);
			return myOffendersList;
		} catch(Exception e) {
			logger.error("getMyOffenderList", e);
		}
		return myOffendersList;
	}

	@Override
	public String getMyOffenderCasePlanRole(Integer offenderBookId) {
		final String sql = getQuery("GET_OIIMYOFF_MY_OFFENDERS_CASE_PLAN_ROLE");	
		String returnobj=null;
		try {
			returnobj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", offenderBookId),String.class);
		} catch (Exception e) {
			returnobj=null;
			logger.error("getMyOffenderCasePlanRole", e);
		}
		return returnobj;
	}
			
}

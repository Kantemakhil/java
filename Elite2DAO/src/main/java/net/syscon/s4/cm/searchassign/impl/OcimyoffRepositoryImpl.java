package net.syscon.s4.cm.searchassign.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.searchassign.OcimyoffRepository;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
@Repository
public class OcimyoffRepositoryImpl extends RepositoryBase implements OcimyoffRepository{

	private static Logger logger = LogManager.getLogger(OcimyoffRepositoryImpl.class);
	
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
			.put("description",		 new FieldMapper("commConditionType"))
			.build();
	
	private final Map<String, FieldMapper> conditionGridMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COMM_CONDITION_CODE", new FieldMapper("conditionTypeCode"))
			.put("LENGTH", 	new FieldMapper("length"))	
			.put("LENGTH_UNIT", 	new FieldMapper("lengthUnit"))	
			.put("START_DATE", 	new FieldMapper("startDate"))
			.put("EXPIRY_DATE", 	new FieldMapper("expiryDate"))	
			.put("Referral", 	new FieldMapper("programReferral"))	
			.put("OFFENDER_SENT_CONDITION_ID", 	new FieldMapper("sentConditionId"))	
			.put("CONDITION_STATUS",  new FieldMapper("conditionStatus"))
			.put("order_no",  new FieldMapper("orderNo"))
			.put("description",  new FieldMapper("description"))
			.build();
	
	public List<VHeaderBlock> getMyOffenderList(final VHeaderBlock vHeaderBlock){
		final String sql = getQuery("GET_OCIMYOFF_MY_OFFENDERS_LIST_DATA_RESULT");	
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
	
	public List<OffenderSentConditions> offenderConditionExcuteQuery(final VHeaderBlock vHeaderBlock){
		final String sql = getQuery("GET_OCIMYOFF_MY_OFFENDERS_ALLOCATED_CONDITION");
		
		final RowMapper<OffenderSentConditions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderSentConditions.class,
				conditionGridMapping);
		List<OffenderSentConditions> offenderConditionList=new ArrayList<OffenderSentConditions>();
		try {		
			offenderConditionList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId",vHeaderBlock.getOffenderBookId() , "userId",vHeaderBlock.getUserId()), columnRowMapper);
			return offenderConditionList;
		} catch(Exception e) {
			logger.error("getMyOffenderList", e);
		}
		return offenderConditionList;
	}
			
}

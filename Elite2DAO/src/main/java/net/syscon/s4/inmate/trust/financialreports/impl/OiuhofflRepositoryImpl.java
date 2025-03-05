package net.syscon.s4.inmate.trust.financialreports.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inmate.trust.financialreports.OiuhofflRepository;

/**
 * Class OiuhofflRepositoryImpl
 */
@Repository
public class OiuhofflRepositoryImpl extends RepositoryBase implements OiuhofflRepository {

	private final Map<String, FieldMapper> vHeaderBlockMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", 		    new FieldMapper("offenderId"))
			.put("ALIAS_OFFENDER_ID",    	new FieldMapper("aliasOffenderId"))
			.put("OFFENDER_ID_DISPLAY",	    new FieldMapper("offenderIdDisplay"))
			.put("LAST_NAME", 			    new FieldMapper("lastName"))
			.put("FIRST_NAME", 			    new FieldMapper("firstName"))
			.put("MIDDLE_NAME", 			new FieldMapper("middleName"))
			.put("SUFFIX", 					new FieldMapper("suffix"))
			.put("BIRTH_DATE",			    new FieldMapper("birthDate"))
			.put("OFFENDER_BOOK_ID", 		new FieldMapper("offenderBookId"))
			.put("BOOKING_NO", 				new FieldMapper("bookingNo"))
			.put("BOOKING_BEGIN_DATE", 		new FieldMapper("bookingBeginDate"))
			.put("BOOKING_END_DATE", 		new FieldMapper("bookingEndDate"))
			.put("AGY_LOC_ID", 				new FieldMapper("agyLocId"))
			.put("AGENCY_IML_ID", 			new FieldMapper("agencyImlId"))
			.put("LIVING_UNIT_ID", 			new FieldMapper("livingUnitId"))
			.put("DISCLOSURE_FLAG", 		new FieldMapper("disclosureFlag"))
			.put("ACTIVE_FLAG", 			new FieldMapper("activeFlag"))
			.put("BOOKING_STATUS", 			new FieldMapper("bookingStatus"))
			.put("LIVING_UNIT_DESCRIPTION", new FieldMapper("livingUnitDescription"))
			.put("IN_OUT_STATUS", 			new FieldMapper("inOutStatus"))
			.put("STATUS_DISPLAY", 			new FieldMapper("statusDisplay"))
			.put("ROOT_OFFENDER_ID", 		new FieldMapper("rootOffenderId"))
			.put("ASSIGNED_STAFF_ID", 		new FieldMapper("assignedStaffId"))
			.put("AGY_LOC_TYPE", 			new FieldMapper("agyLocType"))
			.put("CREATE_AGY_LOC_ID", 		new FieldMapper("createAgyLocId"))
			.put("BOOKING_TYPE", 			new FieldMapper("bookingType"))
			.put("BOOKING_CREATED_DATE", 	new FieldMapper("bookingCreatedDate"))
			.put("LOCATION_CODE", 			new FieldMapper("locationCode"))
			.put("INTAKE_AGY_LOC_ID", 		new FieldMapper("intakeAgyLocId"))
			.put("COMMUNITY_ACTIVE_FLAG", 	new FieldMapper("communityActiveFlag"))
			.put("CREATE_INTAKE_AGY_LOC_ID",new FieldMapper("createIntakeAgyLocId"))
			.put("COMMUNITY_STATUS", 		new FieldMapper("communityStatus"))
			.put("STATUS_REASON", 			new FieldMapper("statusReason"))
			.put("HEADER_STATUS", 			new FieldMapper("headerStatus"))
			.put("AGE", 					new FieldMapper("age"))
			.put("GENDER",                  new FieldMapper("gender"))
			.put("OFF_ALERTS", 				new FieldMapper("offAlerts"))
			.put("STATUS_1", 				new FieldMapper("status1"))
			.put("STATUS_2", 				new FieldMapper("status2"))
			.put("STATUS_3", 				new FieldMapper("status3"))
			.put("ETHNICITY", 				new FieldMapper("ethnicity"))
			.put("MOVEMENT_REASON",		    new FieldMapper("movementReason"))
			.put("IMAGE_ID", 				new FieldMapper("imageId"))
			.put("IMAGE_THUMBNAIL", 		new FieldMapper("imageThumbnail"))
			.put("OFF_SUP_LEVEL", 			new FieldMapper("offSupLevel"))
			.put("PPTY_AGY_LOC_ID", 	    new FieldMapper("pptyAgyLocId"))
			.put("OFFICER",                 new FieldMapper("officer"))
			.put("TRUSTACCOUNT",            new FieldMapper("trustAccount"))
			.build();

	/**
	 * Creates new OiuhofflRepositoryImpl class Object
	 */
	public OiuhofflRepositoryImpl() {
		// OiuhofflRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VHeaderBlock
	 *
	 * @return List<VHeaderBlock>
	 */
	public List<VHeaderBlock> vOffBkgExecuteQuery(VHeaderBlock objSearchDao) {
		String sql = getQuery("OIUHOFFL_VOFFBKG_FIND_V_HEADER_BLOCK");
		final RowMapper<VHeaderBlock> VHeaderBlockRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vHeaderBlockMapping);
		if(objSearchDao.getOffenderIdDisplay() != null) {
			sql = sql + "  AND OFFENDER_ID_DISPLAY = '"+ objSearchDao.getOffenderIdDisplay() +"'";
		} 
		sql = sql + "  order by OFFENDER_ID_DISPLAY";
		final List<VHeaderBlock> returnList = namedParameterJdbcTemplate.query(sql, createParams("caseload_type",
				objSearchDao.getAgyLocType(), "caseload_id", objSearchDao.getCaseLoadId()), VHeaderBlockRowMapper);
		return returnList;
	}

}

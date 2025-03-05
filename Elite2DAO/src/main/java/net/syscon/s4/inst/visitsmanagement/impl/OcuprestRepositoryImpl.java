package net.syscon.s4.inst.visitsmanagement.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.visitsmanagement.OcuprestRepository;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictions;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderRestrictions;
import oracle.jdbc.OracleTypes;

/**
 * Class OcuprestRepositoryImpl
 */
@Repository
public class OcuprestRepositoryImpl extends RepositoryBase implements OcuprestRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuprestRepositoryImpl.class.getName());

private final Map<String, FieldMapper> vOffenderRestrictionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OFFENDER_BOOK_ID", 					new FieldMapper("offenderBookId"))
.put("RESTRICTION_TYPE", 					new FieldMapper("restrictionType"))
.put("COMMENT_TEXT", 						new FieldMapper("commentText"))
.put("ENTERED_STAFF_ID", 					new FieldMapper("enteredStaffId"))
.put("RESTRICTION_DESC", 					new FieldMapper("restrictionDesc"))
.put("AUTHROISED_STAFF_NAME", 				new FieldMapper("authroisedStaffName"))
.put("OFFENDER_RESTRICTION_ID", 			new FieldMapper("offenderRestrictionId"))
.put("EFFECTIVE_DATE", 						new FieldMapper("effectiveDate"))
.put("EXPIRY_DATE", 						new FieldMapper("expiryDate"))
.put("ENTERED_STAFF_NAME", 					new FieldMapper("enteredStaffName"))
.put("AUTHORISED_STAFF_ID", 				new FieldMapper("authorisedStaffId"))
.build();
private final Map<String, FieldMapper> vHeaderBlockMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OFFENDER_ID_DISPLAY", 			new FieldMapper("offenderIdDisplay"))
.put("OFFICER", 						new FieldMapper("officer"))
.put("SUFFIX", 						    new FieldMapper("suffix"))
.put("LIV_UNIT_DESC", 					new FieldMapper("livUnitDesc"))
.put("PRISON_LOCATION", 				new FieldMapper("prisonLocation"))
.put("DISCLOSURE_FLAG", 				new FieldMapper("disclosureFlag"))
.put("ROOT_OFFENDER_ID", 				new FieldMapper("rootOffenderId"))
.put("CREATE_AGY_LOC_ID", 				new FieldMapper("createAgyLocId"))
.put("LIVING_UNIT_ID", 					new FieldMapper("livingUnitId"))
.put("COMMUNITY_STATUS", 				new FieldMapper("communityStatus"))
.put("MIDDLE_NAME", 					new FieldMapper("middleName"))
.put("STATUS_3", 						new FieldMapper("status3"))
.put("LIVING_UNIT_DESCRIPTION", 		new FieldMapper("livingUnitDescription"))
.put("IN_OUT_STATUS", 					new FieldMapper("inOutStatus"))
.put("BOOKING_CREATED_DATE", 			new FieldMapper("bookingCreatedDate"))
.put("OFFENDER_ID", 					new FieldMapper("offenderId"))
.put("LAST_NAME", 						new FieldMapper("lastName"))
.put("LOCATION_CODE", 					new FieldMapper("locationCode"))
.put("STATUS_1", 						new FieldMapper("status1"))
.put("INTAKE_AGY_LOC_ID", 				new FieldMapper("intakeAgyLocId"))
.put("MOVEMENT_REASON", 				new FieldMapper("movementReason"))
.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
.put("ACTIVE_FLAG", 				    new FieldMapper("activeFlag"))
.put("BOOKING_BEGIN_DATE", 				new FieldMapper("bookingBeginDate"))
.put("AGENCY_IML_ID", 					new FieldMapper("agencyImlId"))
.put("STATUS_2", 						new FieldMapper("status2"))
.put("STATUS_REASON", 					new FieldMapper("statusReason"))
.put("STATUS_DISPLAY", 					new FieldMapper("statusDisplay"))
.put("OFF_SUP_LEVEL", 					new FieldMapper("offSupLevel"))
.put("ALIAS_OFFENDER_ID", 				new FieldMapper("aliasOffenderId"))
.put("BOOKING_TYPE", 					new FieldMapper("bookingType"))
.put("BOOKING_END_DATE", 				new FieldMapper("bookingEndDate"))
.put("COMMUNITY_ACTIVE_FLAG", 			new FieldMapper("communityActiveFlag"))
.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
.put("BOOKING_NO", 						new FieldMapper("bookingNo"))
.put("HEADER_STATUS", 					new FieldMapper("headerStatus"))
.put("ASSIGNED_STAFF_ID", 				new FieldMapper("assignedStaffId"))
.put("AGY_LOC_TYPE", 					new FieldMapper("agyLocType"))
.put("GENDER", 						    new FieldMapper("gender"))
.put("BIRTH_DATE", 						new FieldMapper("birthDate"))
.put("FIRST_NAME", 						new FieldMapper("firstName"))
.put("BOOKING_STATUS", 					new FieldMapper("bookingStatus"))
.put("CREATE_INTAKE_AGY_LOC_ID", 		new FieldMapper("createIntakeAgyLocId"))
.put("AGE", 						    new FieldMapper("age"))
.put("OFF_ALERTS", 						new FieldMapper("offAlerts"))
.put("ETHNICITY", 						new FieldMapper("ethnicity"))
.build();
private final Map<String, FieldMapper> offenderRestrictionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
.build();
private final Map<String, FieldMapper> referenceCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CODE", 						    new FieldMapper("code"))
.put("DESCRIPTION", 					new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> staffmembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CODE", 						   new FieldMapper("code"))
.put("DESCRIPTION", 				   new FieldMapper("description"))
.build();

	/**
	 * Creates new OcuprestRepositoryImpl class Object
	 */
	public OcuprestRepositoryImpl() {
		// OcuprestRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VHeaderBlock
	 *
	 * @return List<VHeaderBlock>
	 *
	 * @
	 */
	public List<VHeaderBlock> offExecuteQuery(final VHeaderBlock objSearchDao) {
		final String sql = getQuery("OCUPREST_OFF_FIND_V_HEADER_BLOCK");
		final RowMapper<VHeaderBlock> VHeaderBlockRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vHeaderBlockMapping);
		final ArrayList<VHeaderBlock> returnList = (ArrayList<VHeaderBlock>) namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId(), "USERID",
						objSearchDao.getCreateUserId()),
				VHeaderBlockRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VOffenderRestrictions
	 *
	 * @return List<VOffenderRestrictions>
	 *
	 * @
	 */
	public List<VOffenderRestrictions> vOffRestExecuteQuery(final VOffenderRestrictions objSearchDao) {
		final String sql = getQuery("OCUPREST_VOFFREST_FIND_V_OFFENDER_RESTRICTIONS");
		final RowMapper<VOffenderRestrictions> VOffenderRestrictionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderRestrictions.class, vOffenderRestrictionsMapping);
		final ArrayList<VOffenderRestrictions> returnList = (ArrayList<VOffenderRestrictions>) namedParameterJdbcTemplate
				.query(sql, createParams("OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId()),
						VOffenderRestrictionsRowMapper);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<StaffMembers> rgAuthorisedByRecordGroup() {
		final String sql = getQuery("OCUPREST_FIND_RGAUTHORISEDBY");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffmembersMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("In method rgAuthorisedByRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgRestrictionTypeRecordGroup() {
		final String sql = getQuery("OCUPREST_FIND_RGRESTRICTIONTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodeMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("In method rgRestrictionTypeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderRestrictions> offOnCheckDeleteMaster(final OffenderRestrictions paramBean) {
		final String sql = getQuery("OCUPREST_OFF_ONCHECKDELETEMASTER");
		final RowMapper<OffenderRestrictions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderRestrictions.class, offenderRestrictionsMapping);
		final ArrayList<OffenderRestrictions> returnList = (ArrayList<OffenderRestrictions>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * chkNonAssociation
	 *
	 *
	 */
	public Integer getOffenderBookId(final VHeaderBlock objSearchDao) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[1];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("P_RETURN", OracleTypes.NUMBER),
				new SqlParameter("P_CONTACT_ROOT_OFFENDER_ID", OracleTypes.NUMBER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_VISITS").withFunctionName("GET_CONTACT_OFFENDER_BOOK_ID")
				.declareParameters(sqlParameters);
		inParamMap.put("P_CONTACT_ROOT_OFFENDER_ID", objSearchDao.getRootOffenderId());
		inParamMap.put("P_RETURN", OracleTypes.NUMBER);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final BigDecimal value = simpleJDBCCall.executeFunction(BigDecimal.class, inParameter);
		return Integer.parseInt(value.toString());
	}
}

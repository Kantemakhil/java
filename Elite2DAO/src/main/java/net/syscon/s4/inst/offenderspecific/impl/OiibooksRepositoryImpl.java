package net.syscon.s4.inst.offenderspecific.impl;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.offenderspecific.OiibooksRepository;

/**
 * Class OiibooksRepositoryImpl
 */
@Repository
public class OiibooksRepositoryImpl extends RepositoryBase implements OiibooksRepository {

	/**
	 * Creates new OiibooksRepositoryImpl class Object
	 */

	private final Map<String, FieldMapper> offBookMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("BOOKING_BEGIN_DATE", new FieldMapper("bookingBeginDate"))
			.put("BOOKING_END_DATE", new FieldMapper("bookingEndDate")).put("BOOKING_NO", new FieldMapper("bookingNo"))
			.put("OFFENDER_ID", new FieldMapper("offenderId")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("DISCLOSURE_FLAG", new FieldMapper("disclosureFlag")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("BOOKING_STATUS", new FieldMapper("bookingStatus"))
			.put("YOUTH_ADULT_CODE", new FieldMapper("youthAdultCode"))
			.put("FINGER_PRINTED_STAFF_ID", new FieldMapper("fingerPrintedStaffId"))
			.put("SEARCH_STAFF_ID", new FieldMapper("searchStaffId"))
			.put("PHOTO_TAKING_STAFF_ID", new FieldMapper("photoTakingStaffId"))
			.put("ASSIGNED_STAFF_ID", new FieldMapper("assignedStaffId"))
			.put("CREATE_AGY_LOC_ID", new FieldMapper("createAgyLocId"))
			.put("BOOKING_TYPE", new FieldMapper("bookingType"))
			.put("BOOKING_CREATED_DATE", new FieldMapper("bookingCreatedDate"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("AGENCY_IML_ID", new FieldMapper("agencyImlId"))
			.put("SERVICE_FEE_FLAG", new FieldMapper("serviceFeeFlag"))
			.put("EARNED_CREDIT_LEVEL", new FieldMapper("earnedCreditLevel"))
			.put("EKSTRAND_CREDIT_LEVEL", new FieldMapper("ekstrandCreditLevel"))
			.put("INTAKE_AGY_LOC_ID", new FieldMapper("intakeAgyLocId"))
			.put("ACTIVITY_DATE", new FieldMapper("activityDate"))
			.put("INTAKE_CASELOAD_ID", new FieldMapper("intakeCaseloadId"))
			.put("INTAKE_USER_ID", new FieldMapper("intakeUserId"))
			.put("CASE_OFFICER_ID", new FieldMapper("caseOfficerId")).put("CASE_DATE", new FieldMapper("caseDate"))
			.put("CASE_TIME", new FieldMapper("caseTime"))
			.put("COMMUNITY_ACTIVE_FLAG", new FieldMapper("communityActiveFlag"))
			.put("CREATE_INTAKE_AGY_LOC_ID", new FieldMapper("createIntakeAgyLocId"))
			.put("COMM_STAFF_ID", new FieldMapper("commStaffId")).put("COMM_STATUS", new FieldMapper("commStatus"))
			.put("COMMUNITY_AGY_LOC_ID", new FieldMapper("communityAgyLocId"))
			.put("NO_COMM_AGY_LOC_ID", new FieldMapper("noCommAgyLocId"))
			.put("COMM_STAFF_ROLE", new FieldMapper("commStaffRole"))
			.put("AGY_LOC_ID_LIST", new FieldMapper("agyLocIdList"))
			.put("STATUS_REASON", new FieldMapper("statusReason"))
			.put("TOTAL_UNEXCUSED_ABSENCES", new FieldMapper("totalUnexcusedAbsences"))
			.put("REQUEST_NAME", new FieldMapper("requestName")).put("RECORD_USER_ID", new FieldMapper("recordUserId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("INTAKE_AGY_LOC_ASSIGN_DATE", new FieldMapper("intakeAgyLocAssignDate"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();
	private final Map<String, FieldMapper> formAccesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ORIGINATING_FORM", new FieldMapper("originatingForm"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("DESTINATION_FORM", new FieldMapper("destinationForm"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).build();

	private final Map<String, FieldMapper> vhBlockMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put(" ALIAS_OFFENDER_ID  ", new FieldMapper("aliasOffenderId"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName"))
			.put("MIDDLE_NAME", new FieldMapper("middleName")).put("SUFFIX", new FieldMapper("suffix"))
			.put("BIRTH_DATE", new FieldMapper("birthDate")).put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("BOOKING_NO", new FieldMapper("bookingNo"))
			.put("BOOKING_BEGIN_DATE", new FieldMapper("bookingBeginDate"))
			.put("BOOKING_END_DATE", new FieldMapper("bookingEndDate")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("AGENCY_IML_ID", new FieldMapper("agencyImlId")).put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("DISCLOSURE_FLAG", new FieldMapper("disclosureFlag")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("BOOKING_STATUS", new FieldMapper("bookingStatus"))
			.put("LIVING_UNIT_DESCRIPTION", new FieldMapper("livingUnitDescription"))
			.put("IN_OUT_STATUS", new FieldMapper("inOutStatus"))
			.put("STATUS_DISPLAY", new FieldMapper("statusDisplay"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("ASSIGNED_STAFF_ID", new FieldMapper("assignedStaffId"))
			.put("AGY_LOC_TYPE", new FieldMapper("agyLocType"))
			.put("CREATE_AGY_LOC_ID", new FieldMapper("createAgyLocId"))
			.put("BOOKING_TYPE", new FieldMapper("bookingType"))
			.put("BOOKING_CREATED_DATE", new FieldMapper("bookingCreatedDate"))
			.put("LOCATION_CODE", new FieldMapper("locationCode"))
			.put("INTAKE_AGY_LOC_ID", new FieldMapper("intakeAgyLocId"))
			.put("COMMUNITY_ACTIVE_FLAG", new FieldMapper("communityActiveFlag"))
			.put("CREATE_INTAKE_AGY_LOC_ID", new FieldMapper("createIntakeAgyLocId"))
			.put("COMMUNITY_STATUS", new FieldMapper("communityStatus"))
			.put("STATUS_REASON", new FieldMapper("statusReason")).put("HEADER_STATUS", new FieldMapper("headerStatus"))
			.put("AGE", new FieldMapper("age")).put("GENDER", new FieldMapper("gender"))
			.put("OFF_ALERTS", new FieldMapper("offAlerts")).put("STATUS_1", new FieldMapper("status1"))
			.put("STATUS_2", new FieldMapper("status2")).put("STATUS_3", new FieldMapper("status3"))
			.put("ETHNICITY", new FieldMapper("ethnicity")).put("MOVEMENT_REASON", new FieldMapper("movementReason"))
			.put("IMAGE_ID", new FieldMapper("imageId")).put("OFF_SUP_LEVEL", new FieldMapper("offSupLevel")).build();

	public OiibooksRepositoryImpl() {
	}

	/**
	 * Logger object used to print the log in the file
	 */

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderBookings
	 *
	 * @return List<OffenderBookings>
	 * 
	 */
	public List<OffenderBookings> offBooksExecuteQuery(final OffenderBookings objSearchDao) {
		final String sql = getQuery("OIIBOOKS_OFFBOOKS_FIND_OFFENDER_BOOKINGS");
		final RowMapper<OffenderBookings> OffBookRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offBookMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", objSearchDao.getRootOffenderId()),
				OffBookRowMapper);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            FormAccessibleForms
	 *
	 * @return List<FormAccessibleForms>
	 *
	 */
	public List<FormAccessibleForms> bookDetailExecuteQuery(final FormAccessibleForms objSearchDao) {
		final String sql = getQuery("OIIBOOKS_BOOKDETAIL_FIND_FORM_ACCESSIBLE_FORMS");
		final RowMapper<FormAccessibleForms> formRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FormAccessibleForms.class, formAccesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("originatingForm", "OIIBOOKS"), formRowMapper);

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * bookDetailPostQuery
	 *
	 * @param params
	 *
	 */

	public String bookDetailPostQuery(final String caseloadId) {
		final String sql = getQuery("OIIBOOKS_BOOK_DETAIL_POSTQUERY");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("destinationForm", caseloadId),
				String.class);

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * checkFormAccess
	 *
	 * @param params
	 *
	 */
	public Integer checkFormAccess(final FormAccessibleForms paramBean) {
		final String sql = getQuery("OIIBOOKS_CHECK_FORM_ACCESS");
		Integer modulePrevilege;
		try {
			modulePrevilege = (Integer) namedParameterJdbcTemplate.queryForObject(sql,
					createParams("modulename", "OIIBOOKS","create_userid",paramBean.getCreateUserId()), Integer.class);
		}

		catch (final Exception e) {
			modulePrevilege = 0;
		}
		return modulePrevilege;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param FormAccessibleForms
	 *            objSearchDao
	 *
	 * @return String dataAvail
	 * 
	 */

	public String getDataAvail(final FormAccessibleForms objSearchDao) {
		Map<String, Object> returnObject = null;
		String dataAvail = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_ORIG_FORM", Types.VARCHAR),
				new SqlParameter("P_DEST_FORM", Types.VARCHAR), new SqlParameter("P_BOOK_ID", Types.NUMERIC),
				new SqlParameter("P_OFFENDER_ID", Types.NUMERIC), new SqlOutParameter("P_DATA_AVAIL", Types.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_FORM_ACCESS").withProcedureName("CHECK_DATA_AVAILABLE")
				.declareParameters(sqlParameters);
		inParamMap.put("P_ORIG_FORM", objSearchDao.getOriginatingForm());
		inParamMap.put("P_DEST_FORM", objSearchDao.getDestinationForm());
		inParamMap.put("P_BOOK_ID", objSearchDao.getBookId());
		inParamMap.put("P_OFFENDER_ID", objSearchDao.getOffenderId());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			dataAvail = (String) returnObject.get("P_DATA_AVAIL");
		} catch (final Exception e) {
			dataAvail = null;
		}
		return dataAvail;
	}

	/**
	 * This method is execute a sql query when getOffenderObject event is called
	 *
	 * getOffenderObject
	 *
	 * @param paramBean
	 *
	 */
	@Override
	public VHeaderBlock getOffenderObject(final VHeaderBlock paramBean) {
		final String sql = getQuery("GET_OFFENDER_OBJECT_ON_ID");

		VHeaderBlock returnObj = new VHeaderBlock();
		try {

			final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
					vhBlockMapping);
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", paramBean.getOffenderBookId(),"USERID",paramBean.getCreateUserId()), columnRowMapper);

		} catch (final Exception e) {
			return returnObj;
		}
		return returnObj;
	}

}
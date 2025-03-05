package net.syscon.s4.sa.admin.mergeoffenders.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.OracleSimpleJdbcCall;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.sa.admin.mergeoffenders.OummerofRepository;
import net.syscon.s4.sa.recordmaintenance.MergeTransactionBean;
import oracle.jdbc.OracleTypes;

/**
 * Class OummerofRepositoryImpl
 * 
 */
@Repository
public class OummerofRepositoryImpl extends RepositoryBase implements OummerofRepository {

	private static Logger logger = LogManager.getLogger(OummerofRepositoryImpl.class.getName());

	/**
	 * Creates new OummerofRepositoryImpl class Object
	 */
	public OummerofRepositoryImpl(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("BOOKING_STATUS", new FieldMapper("bookingStatus"))
			.put("COUNT", new FieldMapper("count")).put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> offenderTrustAccountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("BOOKING_STATUS", new FieldMapper("bookingStatus"))
			.put("COUNT", new FieldMapper("count")).build();
	private final Map<String, FieldMapper> vHeaderMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay")).put("OFFICER", new FieldMapper("officer"))
			.put("SUFFIX", new FieldMapper("suffix")).put("LIV_UNIT_DESC", new FieldMapper("livUnitDesc"))
			.put("PRISON_LOCATION", new FieldMapper("prisonLocation"))
			.put("DISCLOSURE_FLAG", new FieldMapper("disclosureFlag"))
			.put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("CREATE_AGY_LOC_ID", new FieldMapper("createAgyLocId"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("COMMUNITY_STATUS", new FieldMapper("communityStatus"))
			.put("MIDDLE_NAME", new FieldMapper("middleName")).put("STATUS_3", new FieldMapper("status3"))
			.put("LIVING_UNIT_DESCRIPTION", new FieldMapper("livingUnitDescription"))
			.put("IN_OUT_STATUS", new FieldMapper("inOutStatus"))
			.put("BOOKING_CREATED_DATE", new FieldMapper("bookingCreatedDate"))
			.put("OFFENDER_ID", new FieldMapper("offenderId")).put("LAST_NAME", new FieldMapper("lastName"))
			.put("LOCATION_CODE", new FieldMapper("locationCode")).put("STATUS_1", new FieldMapper("status1"))
			.put("INTAKE_AGY_LOC_ID", new FieldMapper("intakeAgyLocId"))
			.put("MOVEMENT_REASON", new FieldMapper("movementReason"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("BOOKING_BEGIN_DATE", new FieldMapper("bookingBeginDate"))
			.put("AGENCY_IML_ID", new FieldMapper("agencyImlId")).put("STATUS_2", new FieldMapper("status2"))
			.put("STATUS_REASON", new FieldMapper("statusReason"))
			.put("STATUS_DISPLAY", new FieldMapper("statusDisplay"))
			.put("OFF_SUP_LEVEL", new FieldMapper("offSupLevel"))
			.put("ALIAS_OFFENDER_ID", new FieldMapper("aliasOffenderId"))
			.put("BOOKING_TYPE", new FieldMapper("bookingType"))
			.put("BOOKING_END_DATE", new FieldMapper("bookingEndDate"))
			.put("COMMUNITY_ACTIVE_FLAG", new FieldMapper("communityActiveFlag"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("BOOKING_NO", new FieldMapper("bookingNo"))
			.put("HEADER_STATUS", new FieldMapper("headerStatus"))
			.put("ASSIGNED_STAFF_ID", new FieldMapper("assignedStaffId"))
			.put("AGY_LOC_TYPE", new FieldMapper("agyLocType")).put("GENDER", new FieldMapper("gender"))
			.put("BIRTH_DATE", new FieldMapper("birthDate")).put("FIRST_NAME", new FieldMapper("firstName"))
			.put("BOOKING_STATUS", new FieldMapper("bookingStatus"))
			.put("CREATE_INTAKE_AGY_LOC_ID", new FieldMapper("createIntakeAgyLocId")).put("AGE", new FieldMapper("age"))
			.put("OFF_ALERTS", new FieldMapper("offAlerts")).put("ETHNICITY", new FieldMapper("ethnicity"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).
			build();
	private final Map<String, FieldMapper> offenderBookingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
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

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderBookings
	 *
	 * @return List<OffenderBookings>
	 *
	 * @throws SQLException
	 */
	public List<OffenderBookings> offBooksExecuteQuery(OffenderBookings objSearchDao) {
		final String sql = getQuery("OUMMEROF_OFFBOOKS_FIND_OFFENDER_BOOKINGS");
		final RowMapper<OffenderBookings> OffenderBookingsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookings.class, offenderBookingsMapping);
		final List<OffenderBookings> returnList = namedParameterJdbcTemplate.query(
				sql, createParams("offenderBookId", objSearchDao.getRootOffenderId()), OffenderBookingsRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderBookings> offBkgOnCheckDeleteMaster(OffenderBookings paramBean) {
		final String sql = getQuery("OUMMEROF_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderBookings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offenderTrustAccountsMapping);
		final List<OffenderBookings> returnList =  namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBooksPostQuery
	 *
	 * @param params
	 *
	 */
	public String offBooksPostQuery(OffenderBookings paramBean) {
		final String sql = getQuery("OUMMEROF_OFF_BOOKS_POSTQUERY");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("bookingStatus", paramBean.getBookingStatus()), String.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkg2OnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderBookings> offBkg2OnCheckDeleteMaster(OffenderBookings paramBean) {
		final String sql = getQuery("OUMMEROF_OFF_BKG2_ONCHECKDELETEMASTER");
		final RowMapper<OffenderBookings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offenderTrustAccountsMapping);
		final List<OffenderBookings> returnList =  namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBooks2PostQuery
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> offBooks2PostQuery(ReferenceCodes paramBean) {
		final String sql = getQuery("OUMMEROF_OFF_BOOKS2_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final List<ReferenceCodes> returnList =  namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBooks2PostQuery
	 *
	 * @param params
	 *
	 */
	public List<OffenderTrustAccounts> offBooks2PostQuery(OffenderTrustAccounts paramBean) {
		final String sql = getQuery("OUMMEROF_OFF_BOOKS2_POSTQUERY");
		final RowMapper<OffenderTrustAccounts> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTrustAccounts.class, offenderTrustAccountsMapping);
		final List<OffenderTrustAccounts> returnList =  namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	public List<VHeaderBlock> vOffBkgExecuteQuery(final VHeaderBlock objSearchDao) {
		String sql = getQuery("OUMMEROF_GET_OFFENDER_BOOK_ID");
		final RowMapper<VHeaderBlock> vHedBlockRM = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vHeaderMapping);
		BigDecimal offenderBookId = null;
		if (objSearchDao != null && !objSearchDao.isInsertedFlag()) {
			if (objSearchDao.getOffenderIdDisplay() != null) {
				final List<VHeaderBlock> lstVHeaderB = vOffBkgOffenderExecuteQuery(objSearchDao);
				if (lstVHeaderB != null && lstVHeaderB.size() > 0) {
					for (final VHeaderBlock vHeader : lstVHeaderB) {
						offenderBookId = vHeader.getOffenderBookId();
					}
				} else {
					return lstVHeaderB;
				}
			}
		}
		sql = getQuery("OUMMEROF_VOFFBKG_FIND_V_HEADER_BLOCK");
		final StringBuffer sqlQuery = new StringBuffer();
		String preparedSql = null;
		sqlQuery.append(sql);
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		if (objSearchDao != null) {
			sqlQuery.append(
					" where BOOKING_BEGIN_DATE = ( SELECT MAX( VHB2.BOOKING_BEGIN_DATE ) FROM V_HEADER_BLOCK_fn(:user_name) VHB2  "
							+ " WHERE VHB2.ROOT_OFFENDER_ID = VHB1.ROOT_OFFENDER_ID  AND coalesce(VHB2.AGY_LOC_ID,'') = coalesce(VHB1.AGY_LOC_ID,'')) and ");
			if (objSearchDao.getOffenderBookId() != null && objSearchDao.isInsertedFlag()) {
				sqlQuery.append(" OFFENDER_BOOK_ID != :offenderBookId and ");
				valuesList.addValue("offenderBookId", objSearchDao.getOffenderBookId());
			} else {
				if (offenderBookId != null) {
					sqlQuery.append(" OFFENDER_BOOK_ID = :offenderBookId and ");
					valuesList.addValue("offenderBookId", offenderBookId);
				}
			}
			
			if (!objSearchDao.isInsertedFlag()) {

				if (objSearchDao.getFirstName() != null && !objSearchDao.getFirstName().trim().equals("")) {
					sqlQuery.append("regexp_replace(upper(FIRST_NAME),'[- \\,''\"]','','g') LIKE regexp_replace(upper(:firstName),'[- \\,''\"]','','g')" + " AND ");
					//sqlQuery.append(" FIRST_NAME like :firstName and  ");
					valuesList.addValue("firstName", objSearchDao.getFirstName().trim() + "%");
				}
				if (objSearchDao.getLastName() != null && !objSearchDao.getLastName().trim().equals("")) {
					sqlQuery.append("regexp_replace(upper(LAST_NAME),'[- \\,''\"]','','g') LIKE regexp_replace(upper(:lastName),'[- \\,''\"]','','g')" + " and ");
					//sqlQuery.append(" LAST_NAME like :lastName and  ");
					valuesList.addValue("lastName", objSearchDao.getLastName().trim() + "%");
				}

				if (objSearchDao.getMiddleName() != null && !objSearchDao.getMiddleName().trim().equals("")) {
					sqlQuery.append("regexp_replace(upper(MIDDLE_NAME),'[- \\,''\"]','','g') LIKE regexp_replace(upper(:middleName),'[- \\,''\"]','','g')" + " and ");
					//sqlQuery.append(" MIDDLE_NAME like :middleName and  ");
					valuesList.addValue("middleName", objSearchDao.getMiddleName().trim() + "%");
				}

				if (objSearchDao.getBookingNo() != null && !objSearchDao.getBookingNo().equals("")) {
					sqlQuery.append(" BOOKING_NO = :bookingNo and  ");
					valuesList.addValue("bookingNo", objSearchDao.getBookingNo());
				}
				if (objSearchDao.getBirthDate() != null) {
					sqlQuery.append("BIRTH_DATE =:birthDate  and");
					valuesList.addValue("birthDate", objSearchDao.getBirthDate());
				}
			}
			valuesList.addValue("user_name",objSearchDao.getCreateUserId());
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		List<VHeaderBlock> lstVHeader = new ArrayList<VHeaderBlock>();
		lstVHeader = (List<VHeaderBlock>) namedParameterJdbcTemplate.query(preparedSql, valuesList, vHedBlockRM);
		return lstVHeader;
	}

	public List<VHeaderBlock> vOffBkgOffenderExecuteQuery(final VHeaderBlock objSearchDao) {
		final String sql = getQuery("OUMMEROF_VOFFBKG_FIND_V_HEADER_BLOCK");
		final RowMapper<VHeaderBlock> vHedBlockRM = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vHeaderMapping);

		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		String preparedSql = null;
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			if (objSearchDao.getOffenderIdDisplay() != null) {
				sqlQuery.append(" where OFFENDER_ID_DISPLAY = :offenderIdDisplay ");
				valuesList.addValue("offenderIdDisplay", objSearchDao.getOffenderIdDisplay());
			}
		}
		valuesList.addValue("user_name",objSearchDao.getCreateuserId());
		preparedSql = sqlQuery.toString().trim();
		List<VHeaderBlock> list = new ArrayList<>();
		try {
		 list = namedParameterJdbcTemplate.query(preparedSql, valuesList, vHedBlockRM);
		}catch(Exception e) {
			logger.error("In vOffBkgOffenderExecuteQuery method : ", e);
		}
		return list;
	}

	@Override
	public List<ReferenceCodes> rgAssignmentReasonRecordGroup() {
		final String sql = getQuery("OUMMEROF_FIND_RGASSIGNMENTREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> lstRefCodes = new ArrayList<>();
		try {
			lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			 logger.error("In rgSchInternalScheduleRecordGroup method : ", e);
		}
		return lstRefCodes;
	}

	@Override
	public Integer getTrustAccoutFlag(OffenderBookings searchRecord) {
		final String sql = getQuery("OUMMEROF_OFF_BOOKS2_POSTQUERY");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", searchRecord.getOffenderId()),
				Integer.class);
	}

	public String manualCreateRequest(final MergeTransactionBean paramBean) {
		String returnVal = "success";
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("RETURN_VALUE", OracleTypes.NUMBER),
				new SqlParameter("p_offender_book_id_1", OracleTypes.NUMBER),
				new SqlParameter("P_ROOT_OFFENDER_ID_1", OracleTypes.NUMBER),
				new SqlParameter("p_offender_id_1", OracleTypes.NUMBER),
				new SqlParameter("p_offender_id_display_1", OracleTypes.NUMBER),
				new SqlParameter("p_last_name_1", OracleTypes.VARCHAR),
				new SqlParameter("p_first_name_1", OracleTypes.VARCHAR),
				new SqlParameter("p_offender_book_id_2", OracleTypes.NUMBER),
				new SqlParameter("p_root_offender_id_2", OracleTypes.NUMBER),
				new SqlParameter("p_offender_id_2", OracleTypes.NUMBER),
				new SqlParameter("p_offender_id_display_2", OracleTypes.NUMBER),
				new SqlParameter("p_last_name_2", OracleTypes.VARCHAR),
				new SqlParameter("p_first_name_2", OracleTypes.VARCHAR) };

		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("MERGE_TRANSACTION_REQUEST").withFunctionName("manual_create_request")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID_1", paramBean.getpToOffBookId());
		inParamMap.put("P_ROOT_OFFENDER_ID_1", paramBean.getpToRootOffId());
		inParamMap.put("p_offender_id_1", paramBean.getpToOffenderId());
		inParamMap.put("p_offender_id_display_1", paramBean.getpToOffIdDisplay());
		inParamMap.put("p_last_name_1", paramBean.getpToLastName());
		inParamMap.put("p_first_name_1", paramBean.getpToFirstName());
		inParamMap.put("P_OFFENDER_BOOK_ID_2", paramBean.getpFromOffBookId());
		inParamMap.put("P_ROOT_OFFENDER_ID_2", paramBean.getpFromRootOffId());
		inParamMap.put("p_offender_id_2", paramBean.getpFromOffenderId());
		inParamMap.put("p_offender_id_display_2", paramBean.getpFromOffIdDisplay());
		inParamMap.put("p_last_name_2", paramBean.getpFromLastname());
		inParamMap.put("p_first_name_2", paramBean.getpToFirstName());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			return e.getMessage();
		}
		return returnVal;
	}

	@Override
	public String chkOffendersForTransfer(final MergeTransactionBean bean) {
		String result = null;
		final SqlParameterSource args = new MapSqlParameterSource()
				.addValue("p_from_off_root_id", bean.getpFromRootOffId())
				.addValue("p_from_off_book_id", bean.getpFromOffBookId())
				.addValue("p_to_off_root_id", bean.getpToRootOffId())
				.addValue("p_to_off_book_id", bean.getpToOffBookId())
				.addValue("p_merge_transaction_id", bean.getpMergeTransactionId());

		final SimpleJdbcCall jdbcCall = new OracleSimpleJdbcCall(jdbcTemplate).withSchemaName("OMS_OWNER")
				.withCatalogName("OUMTRNBK").withFunctionName("CHK_OFFENDERS_FOR_TRANSFER")
				.withoutProcedureColumnMetaDataAccess();

		jdbcCall.declareParameters(new SqlOutParameter("RETURN_VALUE", Types.BOOLEAN),
				new SqlParameter("p_from_off_root_id", Types.INTEGER),
				new SqlParameter("p_from_off_book_id", Types.INTEGER),
				new SqlParameter("p_to_off_root_id", Types.INTEGER),
				new SqlParameter("p_to_off_book_id", Types.INTEGER),
				new SqlParameter("p_merge_transaction_id", Types.INTEGER));
		try {
			result = Boolean.toString(jdbcCall.executeFunction(Boolean.class, args));
			result = "success";
		} catch (Exception e) {
			return e.getMessage();
		}
		return result;
	}

	@Override
	public Integer updateOffenderBookingRecords(List<OffenderBookings> returnList) {
		final String sql = getQuery("OUMMEROF_UPDATE_OFFENDER_BOOKINGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderBookings sentenceCalcTypes : returnList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceCalcTypes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("In updateOffenderBookingRecords method : ", e);
		}
		if (returnList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Integer getMergeTransactionID() {
		final String sql = getQuery("OUMMEROF_GET_MERGE_TRANSACTION_ID_VAL");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	@Override
	public Integer insertMergeTransactionRecord(List<MergeTransactionBean> saveList) {
		final String sql = getQuery("OUMMEROF_MERGE_TRANSACTION_RECORD_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final MergeTransactionBean list : saveList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}

		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (saveList.size() == returnArray.length) {
			return 1;
		} else {

			return 0;
		}
	}

	@Override
	public Integer updateOffenderRecords(List<Offenders> offenderUpdate) {
		final String sql = getQuery("OUMMEROF_UPDATE_OFFENDER_RECORD");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Offenders sentenceCalcTypes : offenderUpdate) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceCalcTypes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
		}
		if (offenderUpdate.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}


	@Override
	public Long getOffenderBookId(Long offenderId) {
		final String sql = getQuery("OUMMEROF_GET_OFF_BOOK_ID");
		Long offenderBookId = null;
		try {
			offenderBookId = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", offenderId), Long.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getOffenderBookId", e);
		}
		return offenderBookId;
	}

	

	
	@Override
	public Integer updateOffenderRecords1(Long offenderId, String lastName, String firstName) {
		final String sql = getQuery("OUMMEROF_UPDATE_OFFENDER_RECORD");
		int result = 0;
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("offenderId", offenderId, "lastName", lastName , "firstName",firstName  ));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffenderRecords", e);
		}
		return result;
	}

	@Override
	public Integer updateOffenderRecords2(Long offenderId, Long rootOffenderId) {
		final String sql = getQuery("OUMMEROF_UPDATE_OFFENDER_ID_RECORD");
		int result = 0;
		try {
			result= namedParameterJdbcTemplate.update(sql, createParams("offenderId", offenderId, "rootOffenderId",rootOffenderId));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateOffenderRecords", e);
		}
		return result;
	}

	@Override
	public List<Offenders> getOldOffenderData(Long offenderId) {

		final String sql = getQuery("OUMMEROF_OLD_OFF_DATA");
		List<Offenders> Offenders = new ArrayList<Offenders>();
		try {
			Offenders = namedParameterJdbcTemplate.query(sql, createParams("offenderId", offenderId),
					new BeanPropertyRowMapper<Offenders>(Offenders.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getOldOffenderData", e);
		}
		return Offenders;
	}

	@Override
	public Long getNewOffId(Long offBookId) {
		final String sql = getQuery("OUMMEROF_GET_NEW_OFF_ID");
		Long offenderId = null;
		try {
			offenderId = namedParameterJdbcTemplate.queryForObject(sql, createParams("offBookId", offBookId),Long.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getNewOffId", e);
		}
		return offenderId;
	}
	
}

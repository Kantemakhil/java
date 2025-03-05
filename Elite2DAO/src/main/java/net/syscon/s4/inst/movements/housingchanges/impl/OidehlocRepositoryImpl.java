package net.syscon.s4.inst.movements.housingchanges.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.movements.housingchanges.OidehlocRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OidehlocRepositoryImpl
 */
@Repository
public class OidehlocRepositoryImpl extends RepositoryBase implements OidehlocRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidehlocRepositoryImpl.class.getName());

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
			.put("OFF_ALERTS", new FieldMapper("offAlerts")).put("ETHNICITY", new FieldMapper("ethnicity")).build();
	private final Map<String, FieldMapper> sysProfMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", new FieldMapper("profileCode")).put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2")).put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description")).build();

	/**
	 * Creates new OidehlocRepositoryImpl class Object
	 */
	public OidehlocRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VHeaderBlock
	 *
	 * @return List<VHeaderBlock>
	 *
	 */
	public List<VHeaderBlock> vOffBkgExecuteQuery(final VHeaderBlock objSearchDao) {
		String sql = getQuery("OIDEHLOC_GET_OFFENDER_BOOK_ID");
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
		sql = getQuery("OIDEHLOC_VOFFBKG_FIND_V_HEADER_BLOCK");
		final StringBuffer sqlQuery = new StringBuffer();
		String preparedSql = null;
		sqlQuery.append(sql);
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		if (objSearchDao != null) {
			valuesList.addValue("USERID", objSearchDao.getCreateuserId());
			sqlQuery.append(
					" where BOOKING_BEGIN_DATE = ( SELECT MAX( VHB2.BOOKING_BEGIN_DATE ) FROM V_HEADER_BLOCK_FN(:USERID) VHB2  "
							+ " WHERE VHB2.ROOT_OFFENDER_ID = V_HEADER_BLOCK.ROOT_OFFENDER_ID  AND VHB2.AGY_LOC_ID = V_HEADER_BLOCK.AGY_LOC_ID) and ");
			if (objSearchDao.getOffenderBookId() != null && objSearchDao.isInsertedFlag()) {
				sqlQuery.append(" OFFENDER_BOOK_ID != :offenderBookId and ");
				valuesList.addValue("offenderBookId", objSearchDao.getOffenderBookId());
			} else {
				if (offenderBookId != null) {
					sqlQuery.append(" OFFENDER_BOOK_ID = :offenderBookId and ");
					valuesList.addValue("offenderBookId", offenderBookId);
				}
			}
			if (objSearchDao.getAgyLocId() != null) {
				sqlQuery.append(" AGY_LOC_ID = :agyLocId and ");
				valuesList.addValue("agyLocId", objSearchDao.getAgyLocId());
			}
			if (!objSearchDao.isInsertedFlag()) {

				if (objSearchDao.getFirstName() != null && !objSearchDao.getFirstName().trim().equals("")) {
					sqlQuery.append(" FIRST_NAME like :firstName and  ");
					valuesList.addValue("firstName", objSearchDao.getFirstName().trim() + "%");
				}
				if (objSearchDao.getLastName() != null && !objSearchDao.getLastName().trim().equals("")) {
					sqlQuery.append(" LAST_NAME like :lastName and  ");
					valuesList.addValue("lastName", objSearchDao.getLastName().trim() + "%");
				}

				if (objSearchDao.getMiddleName() != null && !objSearchDao.getMiddleName().trim().equals("")) {
					sqlQuery.append(" MIDDLE_NAME like :middleName and  ");
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

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VHeaderBlock
	 *
	 * @return List<VHeaderBlock>
	 *
	 */
	public List<VHeaderBlock> vOffBkgOffenderExecuteQuery(final VHeaderBlock objSearchDao) {
		final String sql = getQuery("OIDEHLOC_VOFFBKG_FIND_V_HEADER_BLOCK");
		final RowMapper<VHeaderBlock> vHedBlockRM = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vHeaderMapping);
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		String preparedSql = null;
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			valuesList.addValue("USERID", objSearchDao.getCreateuserId());
			if (objSearchDao.getOffenderIdDisplay() != null) {
				sqlQuery.append(" where OFFENDER_ID_DISPLAY = :offenderIdDisplay ");
				valuesList.addValue("offenderIdDisplay", objSearchDao.getOffenderIdDisplay());
			}
		}
		preparedSql = sqlQuery.toString().trim();

		return namedParameterJdbcTemplate.query(preparedSql, valuesList, vHedBlockRM);
	}

	/**
	 *
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVHeaderBlock
	 *            List<VHeaderBlock>
	 *
	 */
	public Integer vOffBkgUpdateVHeaderBlock(final List<VHeaderBlock> lstVHeaderBlock) {
		final String sql = getQuery("OIDEHLOC_VOFFBKG_UPDATE_V_HEADER_BLOCK");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VHeaderBlock vHeaderBlock : lstVHeaderBlock) {
			parameters.add(new BeanPropertySqlParameterSource(vHeaderBlock));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVHeaderBlock.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OIDEHLOC_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> sysProfRM = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				sysProfMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), sysProfRM);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgAssignmentReasonRecordGroup() {
		final String sql = getQuery("OIDEHLOC_FIND_RGASSIGNMENTREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> lstRefCodes = new ArrayList<>();
		try {
			lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In rgSchInternalScheduleRecordGroup method : ", e);
		}
		return lstRefCodes;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param paramBean<Object>
	 *
	 */
	public List<Object> cgwhenNewFormInstance(final SysDual paramBean) {
		final String sql = getQuery("OIDEHLOC_CGWHEN_NEW_FORM_INSTANCE");
		return namedParameterJdbcTemplate.queryForList(sql, createParams(), Object.class);

	}

	/**
	 * For all the setup of the non associations of the offender, find the most
	 * upper living unit level and then check if p_liv_unit_id is under that entire
	 * branch.
	 * 
	 * @return String
	 *
	 *
	 */
	public String chkNonAssociation(final BigDecimal offenderId, final BigDecimal livingUnitId) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER),
				new SqlParameter(" P_LIV_UNIT_ID", OracleTypes.NUMBER),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("NON_ASSOCIATION").withFunctionName("CHECK_NON_ASSOCIATION")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_ID", offenderId);
		inParamMap.put("P_LIV_UNIT_ID", livingUnitId);
		inParamMap.put("RETURN_VALUE", OracleTypes.VARCHAR);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final String value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return value;
	}

	/**
	 * check for MOVE_ADMIN role to override housing change.
	 * 
	 * @return boolean
	 */
	public boolean allowOverride(String userId) {
		boolean blFlag = false;
		String roleAssigned = null;
		String sql = getQuery("OIDEHLOC_STAFF_MEMBERS_ID");
		final Integer staffId = namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userId),
				Integer.class);

		if (staffId != null && staffId > 0) {
			sql = getQuery("OIDEHLOC_STAFF_MEMBER_ROLES");
			try {
				roleAssigned = namedParameterJdbcTemplate.queryForObject(sql, createParams("STAFFID", staffId),
						String.class);
			} catch (DataAccessException e) {
				roleAssigned = null;
				logger.error("StaffMembersRoles", e.getMessage());
			}
		}

		if (roleAssigned != null && "Y".equals(roleAssigned)) {
			blFlag = false;
		} else {
			blFlag = false;
		}
		return blFlag;

	}

	/**
	 * This procedure is used to define explicit cursor to get living unit
	 * description and rewrite get_rev_sup_level_cur cursor to get caseload specific
	 * approved supervision level type
	 * 
	 * @return String
	 *
	 *
	 */
	public String checkSecurity(final BigDecimal offenderBookId, final BigDecimal livingUnitId) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFF_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_LIVING_UNIT_ID", OracleTypes.NUMBER),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("NON_ASSOCIATION").withFunctionName("CHECK_SECURITY").declareParameters(sqlParameters);
		inParamMap.put("P_OFF_BOOK_ID", offenderBookId);
		inParamMap.put("P_LIVING_UNIT_ID", livingUnitId);
		inParamMap.put("RETURN_VALUE", OracleTypes.VARCHAR);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final String value = simpleJDBCCall.executeFunction(String.class, inParameter);
		return value;
	}

	/**
	 * check for status of the offenders of header block and vBi
	 * 
	 * @return boolean
	 */
	public String checkOffStatus(final BigDecimal offenderBookId) {
		final String sql = getQuery("OIDEHLOC_CHECK_OFF_STATUS");
		final String activeFlag = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", offenderBookId), String.class);
		return activeFlag;

	}

	/**
	 * This method is used to update offender booking of offbkg and voffbkg blocks
	 *
	 * @param lstVHeaderBlock
	 *            List<VHeaderBlock>
	 *
	 */
	public Integer updOffBook(final VHeaderBlock objVHeaderBlock, final VHeaderBlock objHeaderBlock) {
		final String sql = getQuery("OIDEHLOC_OFFENDER_BOOKINGS");
		int returnValue = 0;
		OffenderBookings objOffBooking = null;
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		final List<OffenderBookings> lstOffBookings = new ArrayList<OffenderBookings>();
		final List<OffenderBookings> lstUpdateOff = new ArrayList<OffenderBookings>();

		objOffBooking = new OffenderBookings();
		if (objVHeaderBlock.getOffenderBookId() != null) {
			final String strobjVHrBlock = String.valueOf(objVHeaderBlock.getOffenderBookId());
			objOffBooking.setOffenderBookId(Long.parseLong(strobjVHrBlock));
			objOffBooking.setModifyUserId(objVHeaderBlock.getModifyUserId());
			lstOffBookings.add(objOffBooking);
		}

		objOffBooking = new OffenderBookings();
		if (objHeaderBlock.getOffenderBookId() != null) {
			final String strobjHrBlock = String.valueOf(objHeaderBlock.getOffenderBookId());
			objOffBooking.setOffenderBookId(Long.parseLong(strobjHrBlock));
			objOffBooking.setModifyUserId(objVHeaderBlock.getModifyUserId());
			lstOffBookings.add(objOffBooking);
		}

		for (final OffenderBookings offBooking : lstOffBookings) {
			parameters.add(new BeanPropertySqlParameterSource(offBooking));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffBookings.size() == returnArray.length) {
			returnValue = 1;
		}

		if (returnValue == 1) {
			returnArray = new int[] {};
			returnValue = 0;
			objOffBooking = new OffenderBookings();
			final String strobjVHrBlock = String.valueOf(objVHeaderBlock.getOffenderBookId());
			objOffBooking.setOffenderBookId(Long.parseLong(strobjVHrBlock));
			objOffBooking.setLivingUnitId(objHeaderBlock.getLivingUnitId());
			lstUpdateOff.add(objOffBooking);

			objOffBooking = new OffenderBookings();
			final String strobjHrBlock = String.valueOf(objHeaderBlock.getOffenderBookId());
			objOffBooking.setOffenderBookId(Long.parseLong(strobjHrBlock));
			objOffBooking.setLivingUnitId(objVHeaderBlock.getLivingUnitId());
			objOffBooking.setModifyUserId(objVHeaderBlock.getModifyUserId());
			lstUpdateOff.add(objOffBooking);
			parameters = new ArrayList<SqlParameterSource>();
			for (final OffenderBookings offBooking : lstUpdateOff) {
				parameters.add(new BeanPropertySqlParameterSource(offBooking));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (lstUpdateOff.size() == returnArray.length) {
				returnValue = 1;
			}

		}
		if (returnValue == 1) {
			returnValue = insBedHist(objVHeaderBlock, objHeaderBlock.getOffenderBookId(),
					objVHeaderBlock.getLivingUnitId());
			if (returnValue == 1) {
				returnValue = insBedHist(objVHeaderBlock, objVHeaderBlock.getOffenderBookId(),
						objHeaderBlock.getLivingUnitId());
			}
		}
		return returnValue;
	}

	/**
	 * This method creates create bed assignment history records.
	 * 
	 * @param objVHeaderBlock
	 * @param offenderBookId
	 * @param livingUnitId
	 * @return Integer
	 */
	public Integer insBedHist(final VHeaderBlock objVHeaderBlock, final BigDecimal offenderBookId,
			final BigDecimal livingUnitId) {
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		int[] returnArray = new int[] {};
		Integer returnValue = 0;
		Integer mpSeqMax = 0;
		Integer maxSeq = 0;
		BedAssignmentHistories objBedHistory = null;
		String sql = getQuery("OIDEHLOC_BED_ASSIGNMENT_HISTORIES");
		maxSeq = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
				Integer.class);

		boolean blEnter = false;
		if (maxSeq != null && maxSeq != 0) {
			mpSeqMax = maxSeq;
			mpSeqMax = mpSeqMax + 1;
			blEnter = true;
			parameters = new ArrayList<SqlParameterSource>();
			objBedHistory = new BedAssignmentHistories();
			objBedHistory.setOffenderBookId(Integer.valueOf(offenderBookId.toString()));
			objBedHistory.setBedAssignSeq(maxSeq);
			if (livingUnitId != null) {
				objBedHistory.setLivingUnitId(Integer.valueOf(livingUnitId.toString()));
			}
			objBedHistory.setModifyUserId(objVHeaderBlock.getCreateUserId());
			sql = getQuery("OIDEHLOC_UPDATE_BED_ASSIGNMENT_HISTORIES");
			parameters.add(new BeanPropertySqlParameterSource(objBedHistory));
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} else {
			mpSeqMax++;
		}

		if (blEnter) {
			final int inValue = returnArray.length;
			if (inValue == 0) {
				return returnValue;
			}
		}

		returnArray = new int[] {};
		objBedHistory = new BedAssignmentHistories();
		objBedHistory.setOffenderBookId(Integer.valueOf(offenderBookId.toString()));
		if (livingUnitId != null) {
			objBedHistory.setLivingUnitId(Integer.valueOf(livingUnitId.toString()));
		}
		objBedHistory.setBedAssignSeq(mpSeqMax);
		objBedHistory.setAssignmentReason(objVHeaderBlock.getNbtAssignReason());
		objBedHistory.setCreateUserId(objVHeaderBlock.getModifyUserId());
		sql = getQuery("OIDEHLOC_INSERT_BED_ASSIGNMENT_HISTORIES");
		parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(objBedHistory));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			returnValue = 1;
		}
		return returnValue;
	}


	@Override
	public OffenderBookings gettingOldData(BigDecimal offenderBookId) {
		final String sql = getQuery("OIDEHLOC_GETTING_OLD_RECORDS");
		OffenderBookings returnObj = new OffenderBookings();
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBokkId", offenderBookId),
				new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
		return returnObj;
	}

	@Override
	public Boolean checkOverrideLocation(String userId) {
		final String sql = getQuery("OIDEHLOC_CHECK_OVERRIDE_LOCATION");
		try {
			String flag = namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userId), String.class);
			return flag.equalsIgnoreCase("Y") ? true : false;
		} catch (Exception e) {
			logger.error(e);
		}

		return false;
	}
}

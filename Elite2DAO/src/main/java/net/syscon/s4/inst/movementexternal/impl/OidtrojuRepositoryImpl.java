package net.syscon.s4.inst.movementexternal.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.movementexternal.OidtrojuRepository;
import oracle.jdbc.OracleTypes;

@Repository
public class OidtrojuRepositoryImpl extends RepositoryBase implements OidtrojuRepository{
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidtrojuRepositoryImpl.class.getName());

private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("TO_PROV_STAT_CODE", 						new FieldMapper("toProvStatCode"))
.put("DESCRIPTION", 						   new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> offenderExternalMovementsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("REPORTING_TIME", 						new FieldMapper("reportingTime"))
.put("TO_AGY_LOC_ID", 						new FieldMapper("toAgyLocId"))
.put("TO_ADDRESS_ID", 						new FieldMapper("toAddressId"))
.put("ESCORT_CODE", 						new FieldMapper("escortCode"))
.put("APPLICATION_TIME", 					new FieldMapper("applicationTime"))
.put("TO_CITY", 							new FieldMapper("toCity"))
.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
.put("TO_PROV_STAT_CODE", 					new FieldMapper("toProvStatCode"))
.put("ESCORT_TEXT", 						new FieldMapper("escortText"))
.put("PARENT_EVENT_ID", 					new FieldMapper("parentEventId"))
.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
.put("MOVEMENT_TYPE", 						new FieldMapper("movementType"))
.put("TO_COUNTRY_CODE", 					new FieldMapper("toCountryCode"))
.put("DIRECTION_CODE", 						new FieldMapper("directionCode"))
.put("OFFENDER_BOOK_ID", 					new FieldMapper("offenderBookId"))
.put("COMMENT_TEXT", 						new FieldMapper("commentText"))
.put("REPORTING_DATE", 						new FieldMapper("reportingDate"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
.put("OJ_LOCATION_CODE", 					new FieldMapper("ojLocationCode"))
.put("FROM_AGY_LOC_ID", 					new FieldMapper("fromAgyLocId"))
.put("FROM_ADDRESS_ID", 					new FieldMapper("fromAddressId"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("APPLICATION_DATE", 					new FieldMapper("applicationDate"))
.put("MOVEMENT_TIME", 						new FieldMapper("movementTime"))
.put("INTERNAL_SCHEDULE_TYPE", 				new FieldMapper("internalScheduleType"))
.put("INTERNAL_SCHEDULE_REASON_CODE", 		new FieldMapper("internalScheduleReasonCode"))
.put("EVENT_ID", 							new FieldMapper("eventId"))
.put("FROM_CITY", 							new FieldMapper("fromCity"))
.put("MOVEMENT_SEQ", 						new FieldMapper("movementSeq"))
.put("0", 									new FieldMapper("0"))
.put("MOVEMENT_REASON_CODE", 				new FieldMapper("movementReasonCode"))
.put("NVL(MAX(MOVEMENT_SEQ)", 				new FieldMapper(" nvl(max(movementSeq)"))
.put("ARREST_AGENCY_LOC_ID", 				new FieldMapper("arrestAgencyLocId"))
.put("MOVEMENT_DATE", 						new FieldMapper("movementDate"))
.build();
private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("PROFILE_CODE", 						new FieldMapper("profileCode"))
.put("PROFILE_TYPE", 						new FieldMapper("profileType"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("OLD_TABLE_NAME", 						new FieldMapper("oldTableName"))
.put("PROFILE_VALUE", 						new FieldMapper("profileValue"))
.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
.put("PROFILE_VALUE_2", 					new FieldMapper("profileValue2"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CODE", 						new FieldMapper("code"))
.put("DESCRIPTION", 				new FieldMapper("description"))
.put("ACTIVE_FLAG", 				new FieldMapper("activeFlag"))
.build();
private final Map<String, FieldMapper> movementReasonsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CLOSE_CONTACT_FLAG", 						new FieldMapper("closeContactFlag"))
.build();
private final Map<String, FieldMapper> offenderBookingMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("COMMUNITY_ACTIVE_FLAG", 						new FieldMapper("communityActiveFlag"))
.put("OFFENDER_BOOK_ID", 						    new FieldMapper("offenderBookId"))

.build();
private final Map<String, FieldMapper> vheaderblockMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OFFENDER_ID", 						new FieldMapper("offenderId"))
.put("OFFENDER_BOOK_ID", 						    new FieldMapper("offenderBookId"))
.build();

	/**
	 * Creates new OidtrojuRepositoryImpl class Object
	 */
	public OidtrojuRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderExternalMovements
	 *
	 * @return List<OffenderExternalMovements>
	 *
	 * @
	 */
	public List<OffenderExternalMovements> offEmExecuteQuery(final OffenderExternalMovements objSearchDao) {
		final String sql = getQuery("OIDTROJU_OFFEM_FIND_OFFENDER_EXTERNAL_MOVEMENTS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getOffenderBookId() != null) {
				sqlQuery.append("OFFENDER_BOOK_ID =  :offenderBookId " + " and ");
				valuesList.addValue("offenderBookId", objSearchDao.getOffenderBookId());
			}
			if (objSearchDao.getMovementSeq() != null) {
				sqlQuery.append("MOVEMENT_SEQ =  :movementSeq " + " and ");
				valuesList.addValue("movementSeq", objSearchDao.getMovementSeq());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<OffenderExternalMovements> OffenderExternalMovementsRowMapper = Row2BeanRowMapper
				.makeMapping(preparedSql, OffenderExternalMovements.class, offenderExternalMovementsMapping);
		final ArrayList<OffenderExternalMovements> returnList = (ArrayList<OffenderExternalMovements>) namedParameterJdbcTemplate
				.query(preparedSql, valuesList, OffenderExternalMovementsRowMapper);
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
	 *            SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 * @
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OIDTROJU_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), SystemProfilesRowMapper);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkOffEmToProvStatCodeRecordGroup() {
		final String sql = getQuery("OIDTROJU_FIND_CGFKOFFEMTOPROVSTATCODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("In method cgfkOffEmToProvStatCodeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offEmPreInsert
	 *
	 * @param params
	 *
	 */
	public Integer offEmPreInsert(final OffenderExternalMovements paramBean) {
		Integer returnValue = 0;
		final String sql = getQuery("OIDTROJU_OFF_EM_PREINSERT");
		final RowMapper<OffenderExternalMovements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offenderExternalMovementsMapping);
		OffenderExternalMovements returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()), columnRowMapper);
		if (returnObj != null) {
			returnValue = Integer.parseInt(returnObj.getMovementSeq().toString());
		}
		return returnValue;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmRefTo
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEmOffEmRefTo(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDTROJU_CGFKCHK_OFF_EM_OFF_EM_REF_TO_");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<Object> cgwhenNewFormInstance(final SysDual paramBean) {
		final String sql = getQuery("OIDTROJU_CGWHEN_NEW_FORM_INSTANCE");
		final ArrayList<Object> returnList = (ArrayList<Object>) namedParameterJdbcTemplate.queryForObject(sql,
				createParams(), Object.class);
		return returnList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Integer
	 * @Param insertList
	 */
	@Override
	public Integer offEmInsertoffEm(final List<OffenderExternalMovements> insertList) {
		final String sql = getQuery("OIDTROJU_OFFEM_INSERT_OFFENDER_EXTERNAL_MOVEMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderExternalMovements list : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Date
	 * @Param paramBean
	 */
	@Override
	public Date offExmGetMaxMovDate(final OffenderExternalMovements paramBean) {
		final String sql = getQuery("OIDTROJU_MAX_MOVEDATE");
		final Date value = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()), Date.class);
		return value;

	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return MovementReasons
	 */
	@Override
	public MovementReasons clostContactFlag() {
		final String sql = getQuery("OIDTROJU_CLOSE_CONTACT_FLAG");
		final RowMapper<MovementReasons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, MovementReasons.class,
				movementReasonsMapping);
		final MovementReasons returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;

	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return OffenderBookings
	 * @Param offbkgId
	 */
	@Override
	public OffenderBookings commFlagCur(final Integer offbkgId) {
		final String sql = getQuery("OIDTROJU_COMM_FLAG_CUR");
		final RowMapper<OffenderBookings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offenderBookingMapping);
		final OffenderBookings returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDER_BOOK_ID", offbkgId), columnRowMapper);
		return returnObj;

	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Integer
	 * @Param updateList
	 */
	@Override
	public Integer offbkgUpdateQueryForN(final OffenderBookings updateList) {
		final String sql = getQuery("OIDTROJU_UPDATE_OFFBKG_FOR_N");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(updateList));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Integer
	 * @Param updateList
	 */
	@Override
	public Integer offbkgUpdateQueryForY(final OffenderBookings updateList) {
		final String sql = getQuery("OIDTROJU_UPDATE_OFFBKG_FOR_Y");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(updateList));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Integer
	 * @Param updateList
	 */
	@Override
	public Integer offbkgUpdateQuery(final OffenderBookings updateList) {
		final String sql = getQuery("OIDTROJU_UPDATE_OFFBKG");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(updateList));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Integer
	 * @Param offIddisplay,caseloadId,sysdate
	 */
	public Integer payrollUpdateWorkAsgnStatuses(final Integer offIddisplay, final String caseloadId,
			final Date sysdate) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		sqlParameters = new SqlParameter[] { new SqlParameter("OFF_ID", OracleTypes.NUMBER),
				new SqlParameter("CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_MOVEMENT_DATE", OracleTypes.DATE), };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("PAYROLL").withProcedureName("UPDATE_WORK_ASGN_STATUSES")
				.declareParameters(sqlParameters);
		inParamMap.put("OFF_ID", offIddisplay);
		inParamMap.put("CSLD_ID", caseloadId);
		inParamMap.put("P_MOVEMENT_DATE", sysdate);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			return 1;
		}
		return 0;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Integer
	 * @Param offbkgId
	 */
	@Override
	public Integer getMaxBedAssignSeqCur(final Integer offbkgId) {
		final String sql = getQuery("OIDTROJU_GET_MAX_BED_ASSIGN_SEQ_CUR");
		final Integer returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDER_BOOK_ID", offbkgId), Integer.class);
		return returnObj;

	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Integer
	 * @Param updateList
	 */
	@Override
	public Integer updateBedAssignmentHistories(final BedAssignmentHistories updateList) {
		final String sql = getQuery("OIDTROJU_UPDATE_BED_ASSIGNMENT_HISTORIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(updateList));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return VHeaderBlock
	 * @Param offIddisplay
	 */
	@Override
	public VHeaderBlock getVsHeadcurOffId(final String offIddisplay,String userId) {
		final String sql = getQuery("OIDTROJU_VS_HEADCUR_OFF_ID");
		VHeaderBlock returnObj = new VHeaderBlock();
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vheaderblockMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_ID_DISPLAY", offIddisplay,"USERID",userId), columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return returnObj = new VHeaderBlock();
		}
		return returnObj;

	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Integer
	 * @Param paramBean
	 */
	public Integer insNotification(final OffenderExternalMovements paramBean) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFF_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_MVT_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_MVT_REASON", OracleTypes.VARCHAR),
				new SqlParameter("P_MVT_DATE", OracleTypes.DATE), };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDTROJU").withProcedureName("INS_NOTIFICATION").declareParameters(sqlParameters);
		inParamMap.put("P_OFF_BOOK_ID", paramBean.getOffenderBookId());
		inParamMap.put("P_MVT_TYPE", paramBean.getMovementType());
		inParamMap.put("P_MVT_REASON", paramBean.getMovementReasonCode());
		inParamMap.put("P_MVT_DATE", paramBean.getMovementDate());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			return 1;
		}
		return 0;
	}

	@Override
	public OffenderBookings getOldOffenderBookingsRecords(final OffenderBookings paramBean) {
		final String sql =getQuery("OIDTROJU_OFFENDER_BOOKINGS");
		OffenderBookings returnList = new OffenderBookings();
		 returnList =  namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()), new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
		 return returnList;
	}
	
}

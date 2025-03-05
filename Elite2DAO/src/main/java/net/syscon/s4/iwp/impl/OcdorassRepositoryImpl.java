package net.syscon.s4.iwp.impl;

import java.math.BigDecimal;
import java.sql.Types;
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
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cf.deductions.maintenance.beans.FeeOverrideDetails;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.ExtOwnershipTransfer;
import net.syscon.s4.cm.primaryofficeragentassignment.beans.VOmTeamMembers;
import net.syscon.s4.common.beans.OffenderBookingEvent;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderCommunityFile;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.legals.beans.OffenderAllocationsSentences;
import net.syscon.s4.inst.legals.beans.OffenderCondTransfer;
import net.syscon.s4.iwp.OcdorassRepository;
import net.syscon.s4.sa.usersystemsecurity.beans.AssignmentTransfers;
import oracle.jdbc.OracleTypes;

/**
 * Class OcdorassRepositoryImpl
 * 
 */
@Repository
public class OcdorassRepositoryImpl extends RepositoryBase implements OcdorassRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdorassRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE",                              new FieldMapper("code"))
			.put("DESCRIPTION",                       new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> vOmTeamMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE",                                           new FieldMapper("code"))
			.put("DESCRIPTION",                                    new FieldMapper("description"))
			.put("COUNT",                                          new FieldMapper("count"))
			.build();
	private final Map<String, FieldMapper> offenderBookingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE",                                             new FieldMapper("code"))
			.put("DSP_LAST_NAME",                                    new FieldMapper("dspLastName"))
			.put("DSP_FIRST_NAME",                                   new FieldMapper("dspFirstName"))
			.put("DESCRIPTION",                                      new FieldMapper("description"))
			.put("OFFENDER_ID",          new FieldMapper("offenderId"))
			.put("OFFENDER_ID_DISPLAY",  new FieldMapper("offenderIdDisplay"))
			.put("LAST_NAME", 			 new FieldMapper("lastName"))
			.put("FIRST_NAME",           new FieldMapper("firstName"))
			.build();
	private final Map<String, FieldMapper> extOwnershipTransferMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE",                                                 new FieldMapper("code"))
			.put("DESCRIPTION",                                          new FieldMapper("description"))
			.put("AGY_LOC_ID_FROM",                                      new FieldMapper("agyLocIdFrom"))
			.build();
	private final Map<String, FieldMapper> fodMapping = new ImmutableMap.Builder<String, FieldMapper>()
            .put("COMMENTS", new FieldMapper("comments"))
            .put("CREATE_DATETIME", new FieldMapper("createDatetime"))
            .put("CREATE_USER_ID", new FieldMapper("createUserId"))
            .put("FEE_ID", new FieldMapper("feeId"))
            .put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
            .put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
            .put("OVERRIDE_AMOUNT", new FieldMapper("overrideAmount"))
            .put("OVERRIDE_END_DATE", new FieldMapper("overrideEndDate"))
            .put("OVERRIDE_START_DATE", new FieldMapper("overrideStartDate"))
            .put("OVERRIDE_TYPE", new FieldMapper("overrideType"))
            .put("PRIORITY_INDICATOR", new FieldMapper("priorityIndicator"))
            .put("ADDED_BY", new FieldMapper("addedBy"))
            .put("ADDED_DATE", new FieldMapper("addedDate"))
            .put("FEE_OVERRIDE_ID", new FieldMapper("feeOverrideId"))
            .put("OFFENDER_FEE_ID", new FieldMapper("offenderFeeId"))
            .put("FEE_ACT_OVERRIDE_STATUS", new FieldMapper("feeActStatus"))
            
            .build();
	
	private final Map<String, FieldMapper> sentenceMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ID", new FieldMapper("Id")).put("FORM_INFO_JSON", new FieldMapper("formInfoJson"))
			.put("FORM_IDENTIFIER", new FieldMapper("formIdentifier"))
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName")).build();

	/**
	 * Creates new OcdorassRepositoryImpl class Object             
	 */
	public OcdorassRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderBookings
	 *
	 * @return List<OffenderBookings>
	 *
	 */
	public List<OffenderBookings> offBkg1ExecuteQuery(OffenderBookings objSearchDao) {
		final String sql = getQuery("OCDORASS_OFFBKG1_FIND_OFFENDER_BOOKINGS");
		final RowMapper<OffenderBookings> OffenderBookingsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookings.class, offenderBookingsMapping);
		List<OffenderBookings>returnList = new ArrayList<OffenderBookings>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("INTAKEAGYLOCID", objSearchDao.getIntakeAgyLocId()), OffenderBookingsRowMapper);
		}catch (Exception e) {
			logger.error("", e);
		}
		return returnList;
	}
	

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderBookings
	 *            List<OffenderBookings>
	 *
	 */
	public Integer offBkg1UpdateOffenderBookings(final List<OffenderBookings> lstOffenderBookings) {
		String sql = getQuery("OCDORASS_OFFBKG1_UPDATE_OFFENDER_BOOKINGS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderBookings offenderBookings : lstOffenderBookings) {
			parameters.add(new BeanPropertySqlParameterSource(offenderBookings));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderBookings.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            ExtOwnershipTransfer
	 *
	 * @return List<ExtOwnershipTransfer>
	 *
	 */
	public List<ExtOwnershipTransfer> extOtExecuteQuery(ExtOwnershipTransfer objSearchDao) {
		final String sql = getQuery("OCDORASS_EXTOT_FIND_EXT_OWNERSHIP_TRANSFER");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		StringBuffer subWhere = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getAgyLocIdTo() != null) {
				sqlQuery.append("EXT_OWNERSHIP_TRANSFER.AGY_LOC_ID_TO = :CAL_AGY_LOC_ID" + 
						" AND EXT_OWNERSHIP_TRANSFER.TRANSFER_DATE IS NOT NULL " + 
						" AND EXT_OWNERSHIP_TRANSFER.TRANSFER_FLAG = 'N'" + 
						" AND exists (SELECT *  FROM OFFENDER_BOOKINGS WHERE OFFENDER_BOOK_ID = EXT_OWNERSHIP_TRANSFER.OFFENDER_BOOK_ID  AND BOOKING_STATUS != 'C')" + " AND ");
				params.addValue("CAL_AGY_LOC_ID", objSearchDao.getAgyLocIdTo());
			}
			if (objSearchDao.getTransferDate() != null) {
//				if (objSearchDao.getTransferDate() == "%") {
////					null;
//			} else {
				sqlQuery.append(" AND EXT_OWNERSHIP_TRANSFER.TRANSFER_DATE = :transferDate  AND " );
				params.addValue("transferDate", objSearchDao.getTransferDate());
			}
//				    subWhere = "";
					if (objSearchDao.getDspLastName() != null) {
						subWhere.append(" (LAST_NAME LIKE :dspLastName)  AND ");
						params.addValue("dspLastName", objSearchDao.getDspLastName());
					}
					if (objSearchDao.getDspFirstName() != null) {
						subWhere.append(" (FIRST_NAME LIKE :dspFirstName)  AND ");
						params.addValue("dspFirstName", objSearchDao.getDspFirstName());
					}
					if (subWhere != null) {
						sqlQuery.append(" ((ASS_STAFF_ID) IN (SELECT STAFF_ID FROM STAFF_MEMBERS  " + subWhere + ")) AND ");
					}
					
					subWhere = null;
					if (objSearchDao.getDescription() != null) {
						subWhere.append(" (DESCRIPTION LIKE :sealFlag )" + " AND " );
						params.addValue("sealFlag", objSearchDao.getSealFlag());
					}
					if (subWhere != null) {
						sqlQuery.append("((AGY_LOC_ID_FROM) IN  (SELECT AGY_LOC_ID FROM AGENCY_LOCATIONS WHERE " + subWhere + ")) AND ");
					}
					subWhere = null;
					if (objSearchDao.getOffenderIdDisplay() != null) {
						subWhere.append(" (OFFENDER_ID_DISPLAY LIKE :offenderIdDisplay) AND ");
						params.addValue("offenderIdDisplay", objSearchDao.getOffenderIdDisplay());
					}
					if (objSearchDao.getDspLastName() != null && subWhere != null) {
						subWhere.append(subWhere + " (LAST_NAME LIKE :dspLastName) AND ");
						params.addValue("dspLastName", objSearchDao.getDspLastName());
					} else if (objSearchDao.getDspLastName() != null && subWhere == null) {
						subWhere.append(" (LAST_NAME LIKE :dspLastName) AND ");
						params.addValue("dspLastName", objSearchDao.getDspLastName());
					}
					if (objSearchDao.getDspFirstName() != null && subWhere != null) {
						subWhere.append(subWhere + " (FIRST_NAME LIKE :dspFirstName) AND ");
						params.addValue("dspFirstName", objSearchDao.getDspFirstName());
					} else if (objSearchDao.getDspFirstName() != null && subWhere == null) {
						subWhere.append(" (FIRST_NAME LIKE :dspFirstName) AND ");
						params.addValue("dspFirstName", objSearchDao.getDspFirstName());
					}
					if (subWhere != null) {
						sqlQuery.append(" ((OFFENDER_BOOK_ID) IN  (SELECT OFFENDER_BOOK_ID FROM V_HEADER_BLOCK_FN(:USERID) V_HEADER_BLOCK  WHERE" + subWhere + "))");
						params.addValue("USERID", objSearchDao.getCreateUserId());
					}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" ORDER BY TRANSFER_DATE ASC ");
		final RowMapper<ExtOwnershipTransfer> ExtOwnershipTransferRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ExtOwnershipTransfer.class, extOwnershipTransferMapping);
		return namedParameterJdbcTemplate.query(preparedSql, params, ExtOwnershipTransferRowMapper);
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstExtOwnershipTransfer
	 *            List<ExtOwnershipTransfer>
	 *
	 */
	public Integer extOtUpdateExtOwnershipTransfer(final List<ExtOwnershipTransfer> lstExtOwnershipTransfer) {
		String sql = getQuery("OCDORASS_EXTOT_UPDATE_EXT_OWNERSHIP_TRANSFER");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ExtOwnershipTransfer extOwnershipTransfer : lstExtOwnershipTransfer) {
			parameters.add(new BeanPropertySqlParameterSource(extOwnershipTransfer));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstExtOwnershipTransfer.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VOmTeamMembers
	 *
	 * @return List<VOmTeamMembers>
	 *
	 */
	public List<VOmTeamMembers> vOffDetExecuteQuery(VOmTeamMembers objSearchDao) {
		final String sql = getQuery("OCDORASS_VOFFDET_FIND_V_OM_TEAM_MEMBERS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		StringBuffer subWhere = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			String teamFlag = null;
			String profileValue = null;
			String profileValueOne = null;
			Boolean flag = ocdorassGetOmTeamMand();
			if (flag == true) {
				teamFlag = "Y";
			} else {
				teamFlag = "N";
			}
			SystemProfiles systemProfiles = getProfileValue();
			if (systemProfiles != null) {
				if (systemProfiles.getProfileValue() != null) {
					profileValue = systemProfiles.getProfileValue();
				}
				if (systemProfiles.getProfileValue2() != null) {
					profileValueOne = systemProfiles.getProfileValue2();
				}
			}
			sqlQuery.append(" (V_OM_TEAM_MEMBERS.CAL_AGY_LOC_ID = :CAL_AGY_LOC_ID) AND " );
			params.addValue("CAL_AGY_LOC_ID" , objSearchDao.getCalAgyLocId());
			if (objSearchDao.getStaffName() != null) {
				sqlQuery.append(" (V_OM_TEAM_MEMBERS.STAFF_NAME LIKE :staffName)  AND ");
				params.addValue("staffName", "%" + objSearchDao.getStaffName());
			}
			if (objSearchDao.getCalAgyLocId() != null) {
				sqlQuery.append("  (( :team_req = 'N' "
						+ " AND coalesce(:in_staff_qry_position, V_OM_TEAM_MEMBERS.position) = V_OM_TEAM_MEMBERS.position) "
						+ " OR :team_req = 'Y') AND (( :team_req = 'N' "
						+ " AND coalesce(:in_staff_qry_role, V_OM_TEAM_MEMBERS.role) = V_OM_TEAM_MEMBERS.role) "
						+ " OR :team_req = 'Y') ");
				
				params.addValue("team_req", teamFlag);
				params.addValue("in_staff_qry_position", profileValue);
				params.addValue("in_staff_qry_role", profileValueOne);
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" ORDER BY STAFF_NAME ");
		final RowMapper<VOmTeamMembers> VOmTeamMembersRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOmTeamMembers.class, vOmTeamMembersMapping);
		List<VOmTeamMembers> returnList = new ArrayList<VOmTeamMembers>();
		return namedParameterJdbcTemplate.query(preparedSql, params, VOmTeamMembersRowMapper);
	}
	

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVOmTeamMembers
	 *            List<VOmTeamMembers>
	 *
	 */
	public Integer vOffDetUpdateVOmTeamMembers(final List<VOmTeamMembers> lstVOmTeamMembers) {
		String sql = getQuery("OCDORASS_VOFFDET_UPDATE_V_OM_TEAM_MEMBERS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (VOmTeamMembers vOmTeamMembers : lstVOmTeamMembers) {
			parameters.add(new BeanPropertySqlParameterSource(vOmTeamMembers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVOmTeamMembers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgAgyLocIdRecordGroup(final String caseLoadId) {
		final String sql = getQuery("OCDORASS_FIND_RGAGYLOCID");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseLoadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgPositionRecordGroup() {
		final String sql = getQuery("OCDORASS_FIND_RGPOSITION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgRoleRecordGroup() {
		final String sql = getQuery("OCDORASS_FIND_RGROLE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgScheduleTypeRecordGroup() {
		final String sql = getQuery("OCDORASS_FIND_RGSCHEDULETYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgSexCodeRecordGroup() {
		final String sql = getQuery("OCDORASS_FIND_RGSEXCODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<Teams> rgTeamRecordGroup(String position, String role, Long staffid) {
		final String sql = getQuery("OCDORASS_FIND_RGTEAM");
		final RowMapper<Teams> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("STAFFID", staffid, "POSITION", position, "ROLE", role), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public Offenders offenderBookingsPostQuery(final BigDecimal rootOffenderId) {

		final String sql = getQuery("OFFENDER_AWAITING_LIST_POST_QUERY");
		final Offenders bean = new Offenders();
		final RowMapper<Offenders> OffenderBookingsRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				offenderBookingsMapping);
		List<Offenders> returnList = new ArrayList<Offenders>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("rootOffenderId", rootOffenderId),
					OffenderBookingsRowMapper);
		} catch (Exception e) {
			logger.error("", e);
		}

		if (!returnList.isEmpty()) {
			bean.setLastName(
					returnList.get(0).getLastName() != null ? String.valueOf(returnList.get(0).getLastName()) : null);
			bean.setFirstName(
					returnList.get(0).getFirstName() != null ? String.valueOf(returnList.get(0).getFirstName()) : null);
			bean.setOffenderIdDisplay(returnList.get(0).getOffenderIdDisplay() != null
					? String.valueOf(returnList.get(0).getOffenderIdDisplay())
					: null);
		}
		return bean;
	}


	public Long ocdorassExtotGetCountCasePlans(final VOmTeamMembers searchBean) {
		long returnVal = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Map<String, Object> returnValObj = null;
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_AGY_LOC_ID", Types.VARCHAR),
				new SqlParameter("P_STAFF_ID", Types.INTEGER), new SqlParameter("P_POSITION", Types.VARCHAR),
				new SqlParameter("P_ROLE", Types.VARCHAR), new SqlParameter("P_FROM_DATE", Types.DATE),
				new SqlOutParameter("RETURN_VAL", OracleTypes.NUMBER) };

		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("COMUNITY_PKG").withFunctionName("GET_OFFICER_PO").declareParameters(sqlParameters);

		inParamMap.put("p_agy_loc_id", searchBean.getCalAgyLocId());
		inParamMap.put("p_staff_id", searchBean.getStaffId());
		inParamMap.put("p_position", searchBean.getPosition());
		inParamMap.put("p_role", searchBean.getRole());
		inParamMap.put("p_from_date", searchBean.getFromDate());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnValObj = simpleJDBCCall.execute(inParameter);
			returnVal = ((BigDecimal) returnValObj.get("RETURN_VAL")).longValue();
		} catch (final Exception e) {
			// returnVal = 0;
			e.printStackTrace();
		}
		return returnVal;
	}

	public List<ExtOwnershipTransfer> ocdorassExtotGetLastNameFirstName(final ExtOwnershipTransfer paramBean) {
		final String sql = getQuery("OCDORASS_EXTOT_GET_LAST_NAME_FIRST_NAME");
		final RowMapper<ExtOwnershipTransfer> OffenderBookingsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ExtOwnershipTransfer.class, offenderBookingsMapping);
		List<ExtOwnershipTransfer> returnList = new ArrayList<ExtOwnershipTransfer>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("STAFFID", paramBean.getAssStaffId()),
					OffenderBookingsRowMapper);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;

	}

	public String ocdorassExtotGetAgyLocDesc(final ExtOwnershipTransfer paramBean) {
		final String sql = getQuery("OCDORASS_EXTOT_GET_AGY_LOC_DESC");

		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("AGYLOCIDFROM", paramBean.getAgyLocIdFrom()), String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	@Override
	public Offenders ocdorassExtotGetPostQuery(final Long offenderBookId) {
		Map<String, Object> returnObject = null;
		final Offenders bean = new Offenders();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[6];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFF_BOOK_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_ROOT_OFF_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_OFF_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_OFF_ID_DISP", OracleTypes.VARCHAR),
				new SqlOutParameter("P_OFF_LAST_NAME", OracleTypes.VARCHAR),
				new SqlOutParameter("P_OFF_FIRST_NAME", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW_ADM").withProcedureName("GET_OFF_DETAILS_TRANS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFF_BOOK_ID", offenderBookId);

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);

			bean.setLastName(
					returnObject.get("P_OFF_LAST_NAME") != null ? String.valueOf(returnObject.get("P_OFF_LAST_NAME"))
							: null);
			bean.setFirstName(
					returnObject.get("P_OFF_FIRST_NAME") != null ? String.valueOf(returnObject.get("P_OFF_FIRST_NAME"))
							: null);
			bean.setOffenderIdDisplay(
					returnObject.get("P_OFF_ID_DISP") != null ? String.valueOf(returnObject.get("P_OFF_ID_DISP"))
							: null);

		} catch (Exception e) {
			logger.error("In method getOffenderDetails", e);
		}
		return bean;
	}

	public Long ocdorassGetCountTeamMembers(final VOmTeamMembers paramBean) {
		final String sql = getQuery("OCDORASS_GET_COUNT_TEAM_MEMBERS");
		Long returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("STAFFID", paramBean.getStaffId()),
					Long.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public Long ocdorassGetCountTwoTeamMembers(final VOmTeamMembers paramBean) {
		final String sql = getQuery("OCDORASS_GET_COUNT_TWO_TEAM_MEMBERS");
		Long returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("STAFFID", paramBean.getStaffId()),
					Long.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public Long ocdorassGetTeamCodeDescription(final VOmTeamMembers paramBean) {
		final String sql = getQuery("OCDORASS_GET_TEAM_CODE_DESCRIPTION");
		Long returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("TEAMID", paramBean.getNbtTeamId()), Long.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	@Override
	public Integer defaultTeam(Long staffId) {
		Map<String, Object> returnObject = null;
		Integer returnValue = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[1];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_STAFF_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_TEAM_ID", OracleTypes.NUMBER), };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW_ADM").withProcedureName("DEFAULT_TEAM").declareParameters(sqlParameters);
		inParamMap.put("P_STAFF_ID", staffId);

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.size() == 0) {
				returnValue = 1;
			}
		} catch (Exception e) {
			returnValue = 0;
		}
		return returnValue;
	}

	public String getTeamDesc(final Integer teamId) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[4];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_TEAM_ID", OracleTypes.NUMBER),
				new SqlInOutParameter("P_TEAM_CODE", OracleTypes.VARCHAR),
				new SqlInOutParameter("P_TEAM_NAME", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW_ADM").withFunctionName("GET_TEAM_DESC").declareParameters(sqlParameters);
		inParamMap.put("P_TEAM_ID", teamId);

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		return simpleJDBCCall.executeFunction(String.class, inParameter);
	}

	public Boolean ocdorassGetOmTeamMand() {
		final String sql = getQuery("OCDORASS_GET_OM_TEAM_MAND");
		String returnData = null;
		returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		if (returnData.equals("Y")) {
			return true;
		} else
			return false;
	}

	public Integer ocdorassGetCaseOfficerId(Long offenderBookId) {
		final String sql = getQuery("OCDORASS_GET_CASE_OFFICER_ID");
		Integer returndata = null;
		try {
			returndata = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", offenderBookId),
					Integer.class);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public Integer ocdorassGetCountCasePlans(final Long offenderBookId) {
		final String sql = getQuery("OCDORASS_GET_COUNT_CASE_PLANS");
		Integer returndata = null;
		try {
			returndata = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", offenderBookId),
					Integer.class);
		} catch (Exception e) {
			return returndata;
		}
		return returndata;

	}

	public String getSuperVersionLevel(final Long offenderBookId) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Map<String, Object> returnObject = null;
		String getSupervisionLevel = null;
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlOutParameter("RETURN_VALUE", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("PIMS_WEIGHT").withFunctionName("GET_SUP_LEVEL").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", offenderBookId);

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			getSupervisionLevel = (String) inParamMap.get("RETURN_VALUE");
		} catch (Exception e) {
			return getSupervisionLevel;
		}
		return getSupervisionLevel;
	}

	public Integer getReviewPeriodCaseReviewPeriods(final String vSuperVisionLevel) {
		final String sql = getQuery("OCDORASS_GET_REVIEW_PERIOD_CASE_REVIEW_PERIODS");
		Integer returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("VSUPERVISIONLEVEL", vSuperVisionLevel), Integer.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public Long getMaxCasePlanId(final Long offenderBookId) {
		final String sql = getQuery("OCDORASS_GET_MAX_CASE_PLAN_ID");
		Long returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", offenderBookId),
					Long.class);
		} catch (Exception e) {
			returnData = 0L;
			return returnData;
		}
		return returnData;

	}

	public Date getFromDateStaffLocationRoles(final VOmTeamMembers paramBean) {
		final String sql = getQuery("OCDORASS_GET_FROM_DATE_STAFF_LOCATION_ROLES");
		Date returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("STAFFID", paramBean.getStaffId(), "CALAGYLOCID", paramBean.getAgyLocId(), "POSITION",
							paramBean.getPosition(), "ROLE", paramBean.getRole()),
					Date.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public Integer getUpdateCasePlans(final VOmTeamMembers lstCasePlans) {
		String sql = getQuery("OCDORASS_GET_UPDATE_CASE_PLANS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(lstCasePlans));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	public String getCreationUserCasePlans(final Long offenderBookId, final long casePlanId) {
		final String sql = getQuery("OCDORASS_GET_CREATION_USER_CASE_PLANS");

		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERBOOKID", offenderBookId, "CASEPLANID", casePlanId), String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;

	}

	public List<CasePlans> getInstInfoCasePlans(final CasePlans paramBean) {
		final String sql = getQuery("OCDORASS_GET_INST_INFO_CASE_PLANS");
		final RowMapper<CasePlans> OffenderBookingsRowMapper = Row2BeanRowMapper.makeMapping(sql, CasePlans.class,
				offenderBookingsMapping);
		List<CasePlans> returnList = new ArrayList<CasePlans>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("OFFENDERBOOKID",
					paramBean.getOffenderBookId(), "CASEPLANID", paramBean.getCasePlanId()), OffenderBookingsRowMapper);

		} catch (Exception e) {
			return returnList;
		}
		return returnList;

	}
	public Integer ocdorassInsertCasePlans(final VOmTeamMembers lstCasePlans) {
		String sql = getQuery("OCDORASS_INSERT_CASE_PLANS");
		int[] returnArray = new int[] {};


		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(lstCasePlans));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	public String getOffenderWorkAssignments(final String agyLocId, final long offenderBookId) {
		final String sql = getQuery("OCDORASS_SELECT_OFFENDER_WORK_ASSIGNMENTS");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("AGYLOCID", agyLocId, "OFFENDERBOOKID", offenderBookId), String.class);

		} catch (Exception e) {
			return returnData;
		}
		return returnData;

	}

	public VOmTeamMembers getPreviousOffenderWorkAssignments(final long offenderBookId) {
		final String sql = getQuery("OCDORASS_GET_PREVIOUS_OFFENDER_WORK_ASSIGNMENTS");
		VOmTeamMembers returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", offenderBookId),
					VOmTeamMembers.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;

	}

	public Long getAsstraSeqNextval() {
		final String sql = getQuery("OCDORASS_GET_ASSTRA_SEQ_NEXTVAL");
		Long returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);

		} catch (Exception e) {
			return returnData;
		}
		return returnData;

	}

	public Integer ocdorassInsertAssignmentTransfers(final VOmTeamMembers lstAssignmentTransfers) {
		String sql = getQuery("OCDORASS_INSERT_ASSIGNMENT_TRANSFERS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(lstAssignmentTransfers));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	public Integer ocdorassUpdateAssignmentTransfers(final VOmTeamMembers lstAssignmentTransfers) {
		String sql = getQuery("OCDORASS_UPDATE_ASSIGNMENT_TRANSFERS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(lstAssignmentTransfers));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	public String assaignOffender(final Long offenderBookId, Integer nbtTeamId) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFF_BOOK_ID", OracleTypes.NUMBER),
				new SqlInOutParameter("P_TEAM_ID", OracleTypes.NUMBER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW_ADM").withFunctionName("ASSIGN_OFFENDER")
				.declareParameters(sqlParameters);
		inParamMap.put("P_TEAM_ID", nbtTeamId);

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		return simpleJDBCCall.executeFunction(String.class, inParameter);
	}

	public Integer updateExtOwnershipTransfer(final ExtOwnershipTransfer lstExtOwnershipTransfer) {
		String sql = getQuery("OCDORASS_UPDATE_EXT_OWNERSHIP_TRANSFER");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(lstExtOwnershipTransfer));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (returnArray.length == 1) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
	}

	public Long countOffenderBookingAgyLocs(Long offenderBookId, String agyLocId) {
		final String sql = getQuery("OCDORASS_COUNT_OFFENDER_BOOKING_AGY_LOCS");

		Long returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERBOOKID", offenderBookId, "AGYLOCID", agyLocId), Long.class);
		} catch (Exception e) {
			logger.error("countOffenderBookingAgyLocs", e);
		}
		return returnData;
	}

	public Integer insertOffenderBookingAgyLocs(final OffenderBookings lstOffenderBookingAgyLocs) {
		String sql = getQuery("OCDORASS_INSERT_INTO_OFFENDER_BOOKING_AGY_LOCS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(lstOffenderBookingAgyLocs));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	public Integer updateOffenderBookingAgyLocs(final OffenderBookings lstOffenderBookingAgyLocs) {
		String sql = getQuery("OCDORASS_UPDATE_OFFENDER_BOOKING_AGY_LOCS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(lstOffenderBookingAgyLocs));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	public Long getEventSeqOffenderBookingsEvents(Long offenderBookId) {
		final String sql = getQuery("OCDORASS_GET_EVENT_SEQ_OFFENDER_BOOKINGS_EVENTS");

		Long returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", offenderBookId),
					Long.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public Integer insertIntoOffenderBookingEvents(final ExtOwnershipTransfer lstOffenderBookingEvent) {
		String sql = getQuery("OCDORASS_INSERT_INTO_OFFENDER_BOOKING_EVENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(lstOffenderBookingEvent));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	public Integer getUpdateOffenderBookingAgyLocs(final OffenderBookings lstOffenderBookingAgyLocs) {
		String sql = getQuery("UPDATE_OFFENDER_BOOKING_AGY_LOCS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(lstOffenderBookingAgyLocs));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	public AssignmentTransfers getOffenderWorkAssignmentsOffBkgCommit(final Long offenderBookId) {
		final String sql = getQuery("OCDORASS_SELECT_OFFENDER_WORK_ASSIGNMENTS_OFF_BKG_COMMIT");
		AssignmentTransfers returndata = null;
		try {
			returndata = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),
					new BeanPropertyRowMapper<AssignmentTransfers>(AssignmentTransfers.class));
		} catch (Exception e) {
			return returndata;
		}
		return returndata;
	}

	public String getProfileValueSystemProfiles() {
		final String sql = getQuery("GET_PROFILE_VALUE_SYSTEM_PROFILES");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public Long getOffenderFileSeqOffenderCommunityFiles(Long offenderId) {
		final String sql = getQuery("GET_OFFENDER_FILE_SEQ_OFFENDER_COMMUNITY_FILES");
		Long returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERID", offenderId),
					Long.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public Integer getUpdateOffenderCommunityFiles(final VOmTeamMembers lstOffenderCommunityFile) {
		String sql = getQuery("GET_UPDATE_OFFENDER_COMMUNITY_FILES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(lstOffenderCommunityFile));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	public String transferFile(final ExtOwnershipTransfer searchBean) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[9];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_FILE_SEQ", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER),
				new SqlParameter("P_FILE_TRANS_COMMENT", OracleTypes.VARCHAR),
				new SqlParameter("P_AGY_LOC_ID_FROM", OracleTypes.VARCHAR),
				new SqlParameter("P_AGY_LOC_ID_TO", OracleTypes.VARCHAR),
				new SqlParameter("P_STAFF_ID_FROM", OracleTypes.NUMBER),
				new SqlParameter("P_STAFF_ID_TO", OracleTypes.NUMBER),
				new SqlParameter("P_NON_OFFICER_FROM", OracleTypes.VARCHAR),
				new SqlParameter("P_NON_OFFICER_TO", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("PIMS_FILE_TRACKING").withProcedureName("TRANSFER_FILE")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_FILE_SEQ", searchBean.getOffenderFileSeq());
		inParamMap.put("P_OFFENDER_ID", searchBean.getOffenderId());
		inParamMap.put("P_FILE_TRANS_COMMENT", null);
		inParamMap.put("P_AGY_LOC_ID_FROM", searchBean.getAgyLocIdFrom());
		inParamMap.put("P_AGY_LOC_ID_TO", searchBean.getAgyLocIdFrom());
		inParamMap.put("P_STAFF_ID_FROM", null);
		inParamMap.put("P_STAFF_ID_TO", searchBean.getAssStaffId());
		inParamMap.put("P_NON_OFFICER_FROM", searchBean.getNonOfficerStatus());
		inParamMap.put("P_NON_OFFICER_TO", null);

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		return simpleJDBCCall.executeFunction(String.class, inParameter);
	}

	public Long getOffenderIdOffenderBookings(final Long vBookId) {
		final String sql = getQuery("SELECT_GET_OFFENDER_ID_OFFENDER_BOOKINGS");
		Long returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", vBookId),
					Long.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public List<OffenderCommunityFile> getDetailsOffenderCommunityFiles(final ExtOwnershipTransfer paramBean) {
		final String sql = getQuery("SELECT_GET_DETAILS_OFFENDER_COMMUNITY_FILES");
		List<OffenderCommunityFile> returnData = new ArrayList<>();
		final RowMapper<OffenderCommunityFile> OffenderBookingsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCommunityFile.class, offenderBookingsMapping);
		try {
			returnData = namedParameterJdbcTemplate.query(sql, createParams("OFFENDERID", paramBean.getOffenderId()),
					OffenderBookingsRowMapper);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}

	public Integer getUpdateOffenderCommunityFilesTwo(final ExtOwnershipTransfer lstOffenderCommunityFile) {
		String sql = getQuery("GET_UPDATE_OFFENDER_COMMUNITY_FILES_TWO");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(lstOffenderCommunityFile));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	public Boolean getCgnbtSkillSubTypeVOmTeamMembers(final Long staffId) {
		final String sql = getQuery("GET_CGNBT_SKILL_SUB_TYPE_V_OM_TEAM_MEMBERS");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("staffid", staffId), String.class);
			if (returnData.equals("Y")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public Integer ocdorassGetupdateOffenderBookingAgyLocs(final OffenderBookings lstOffenderBookingAgyLoc) {
		String sql = getQuery("OCDORASS_GET_UPDATE_OFFENDER_BOOKING_AGY_LOCS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(lstOffenderBookingAgyLoc));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	public Integer ocdorassGetinsertOffenderBookingAgyLocs(final OffenderBookings lstOffenderBookingAgyLoc) {
		String sql = getQuery("OCDORASS_GET_INSERT_OFFENDER_BOOKING_AGY_LOCS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(lstOffenderBookingAgyLoc));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}
	
	public List<Teams> getCountOfTeamEnable() {
		final String sql = getQuery("OCDORASS_GET_CASE_LOV_DISABLE");
		final RowMapper<Teams> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Teams.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}
	
	public SystemProfiles getProfileValue() {
		final String sql = getQuery("GET_PROFILE_VALUE_SYSTEM_PROFILES_POST_QUERY");
		final RowMapper<SystemProfiles> mRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class, mMapping);
		SystemProfiles returnData = new SystemProfiles();
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}
	
	@Override
	public List<FeeOverrideDetails> getFeeOverride(Long offenderBookId, String agyLocIdFrom) {
		final String sql = getQuery("OCDORASS_GET_FEE_OVERRIDES");
		final RowMapper<FeeOverrideDetails> feeRowMapper = Row2BeanRowMapper.makeMapping(sql, FeeOverrideDetails.class, fodMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("offenderBookId",offenderBookId,"caseLoadId",agyLocIdFrom), feeRowMapper);
	}
	
	@Override
	public BigDecimal getOffenderId(Long offenderBookId) {
		final String sql = getQuery("OCDORASS_GET_OFFENDER_ID");
		BigDecimal returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",offenderBookId), BigDecimal.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}
	
	@Override
	public Integer checkFeeAccountStatus(Long offenderBookId, String agyLocIdTo) {
		final String sql = getQuery("OCDORASS_CHECK_FEE_ACCOUNT_STATUS");
		Integer returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",offenderBookId,"caseLoadId",agyLocIdTo), Integer.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}
	
	@Override
	public Integer checkSupvAccnt(Long offenderFeeId) {
		final String sql = getQuery("OCDORASS_CHECK_SUPV_ACCOUNT");
		Integer returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderFeeId",offenderFeeId), Integer.class);
		} catch (Exception e) {
			return returnData;
		}
		return returnData;
	}
	
	@Override
	public List<FeeAccountProfiles> getActiveFeeOverridesAtReceiveCounty(Long offenderBookId, String agyLocIdTo) {
		final String sql = getQuery("OCDORASS_GET_ACTIVE_FEE_OVERRIDE_AT_RECEIVE_COUNTY");
		final RowMapper<FeeAccountProfiles> mRowMapper = Row2BeanRowMapper.makeMapping(sql, FeeAccountProfiles.class, fodMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("offenderBookId",offenderBookId,"agyLocIdTo",agyLocIdTo),mRowMapper);
	}

	@Override
	public OffenderBookings getOffBkgsOldRec(final OffenderBookings bean) {
		final String sqlQuery = getQuery("GET_OFF_BKGS_OLD_REC");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlQuery,
					createParams("offenderBookId", bean.getOffenderBookId()),
					new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
		} catch (Exception e) {
			logger.error("getOffBkgsOldRec ", e);
			return new OffenderBookings();
		}
	}

	@Override
	public void executingOmtofsbTrigger(String modifyUserId) {
		final String sqlQuery = getQuery("EXECUTING_OMTOFSB_TRIGGER");
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("modifyUserId", modifyUserId);
			try {
				String tableName = "";
				String whereCondition = null;
				updatePreDeletedRow(tableName, whereCondition, map);
			} catch (Exception e) {
				logger.error(e);
			}
			namedParameterJdbcTemplate.update(sqlQuery, map);
		} catch (Exception e) {
			logger.error("executingOmtofsbTrigger", e);
		}
	}
	
	@Override
	public Integer getOffTierLevelWlUnits(final Long offenderBookId) {
		final String sqlQuery = getQuery("GET_OFF_TIER_LEVEL_WORKLOAD_UNITS");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sqlQuery, createParams("OFFENDERBOOKID", offenderBookId),
					Integer.class);
		} catch (Exception e) {
			logger.error("getOffTierLevelWlUnits", e);
			retVal = null;
		}
		return retVal;
	}
	@Override
	public List<Integer> getWorkedStaffMembers(Long offenderBookId) {
		final String sql = getQuery("OCDORASS_GET_WORKED_STAFF_MEMBERS");
		try {
			return namedParameterJdbcTemplate.queryForList(sql, createParams("offenderBookId",offenderBookId), Integer.class);
		} catch (Exception e) {
			logger.error("getWorkedStaffMembers", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<OffenderCondTransfer> getOffenderCondition(Long vBookId) {
		final String sql = getQuery("OCDORASS_GET_OFFENDER_CONDITION");
	
		try {
			final RowMapper<OffenderCondTransfer> sentenceRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffenderCondTransfer.class, sentenceMapping);
			return namedParameterJdbcTemplate.query(sql,
					createParams( "offenderBookId", vBookId),
					sentenceRowMapper);
		} catch (Exception e) {
			logger.error("Exception in  getOffenderCondition method : ", e);
			return Collections.emptyList();
		}
		
	
	}

}

package net.syscon.s4.inst.offenderissuestracking.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.GrievanceTxns;
import net.syscon.s4.im.beans.GrievanceTypes;
import net.syscon.s4.im.beans.OffenderGrievanceTxns;
import net.syscon.s4.im.beans.OffenderGrievances;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.offenderissuestracking.OidissueRepository;

/**
 * Class OidissueRepositoryImpl
 */
@Repository
public class OidissueRepositoryImpl extends RepositoryBase implements OidissueRepository {
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidissueRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> offenderGrievanceTxnsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFICIAL_RESPONSE", 		new FieldMapper("officialResponse"))
			.put("DAYS_RESPOND", 			new FieldMapper("daysRespond"))
			.put("PARENT_CODE", 			new FieldMapper("parentCode"))
			.put("GRIEVANCE_ID", 			new FieldMapper("grievanceId"))
			.put("ASSIGNED_STAFF_ID", 		new FieldMapper("assignedStaffId"))
			.put("GRIEV_TYPE", 				new FieldMapper("grievType"))
			.put("TXN_TYPE", 				new FieldMapper("txnType")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", 				new FieldMapper("agyLocId"))
			.put("CODE", 					new FieldMapper("code"))
			.put("USER_ID", 				new FieldMapper("userId"))
			.put("TXN_TYPE", 				new FieldMapper("txnType"))
			.put("LAST_NAME||'", 			new FieldMapper("lastName||'"))
			.put("GRIEV_REASON_CODE", 		new FieldMapper("grievReasonCode "))
			.put("'||FIRST_NAME", 			new FieldMapper("'||firstName"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NVL(MAX(TXN_SEQ)", 		new FieldMapper(" nvl(max(txnSeq)"))
			.put("AGY_LOC_ID", 				new FieldMapper("agyLocId"))
			.put("CODE", 					new FieldMapper(" code"))
			.put("0", 						new FieldMapper("0"))
			.put("GRIEVANCE_ID", 			new FieldMapper("grievanceId"))
			.put("GRIEV_REASON_CODE", 		new FieldMapper(" grievReasonCode"))
			.put("DAYS_RESPOND", 			new FieldMapper(" daysRespond"))
			.put("GRIEV_LEVEL", 			new FieldMapper(" grievLevel"))
			.put("STATUS", 					new FieldMapper("status "))
			.put("GRIEV_TYPE",				new FieldMapper("grievType"))
			.put("DESCRIPTION", 			new FieldMapper(" description "))
			.put("TXN_TYPE", 				new FieldMapper(" txnType")).build();
	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STATUS", 					new FieldMapper("status"))
			.put("GRIEVANCE_ID", 			new FieldMapper("grievanceId"))
			.put("GRIEV_REASON_CODE", 		new FieldMapper(" grievReasonCode"))
			.put("PARENT_CODE", 			new FieldMapper(" parentCode "))
			.put("DAYS_RESPOND", 			new FieldMapper(" daysRespond"))
			.put("GRIEV_TYPE", 				new FieldMapper("grievType"))
			.put("DESCRIPTION", 			new FieldMapper(" description "))
			.put("TXN_TYPE", 				new FieldMapper(" txnType")).build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PARENT_CODE", 			new FieldMapper(" parentCode "))
			.put("DAYS_RESPOND", 			new FieldMapper(" daysRespond"))
			.put("STAFF_INVOLVED_FLAG", 	new FieldMapper(" staffInvolvedFlag "))
			.put("'X'", 					new FieldMapper(" 'x' "))
			.put("GRIEV_TYPE", 				new FieldMapper("grievType"))
			.put("TXN_TYPE", 				new FieldMapper(" txnType")).build();
	private final Map<String, FieldMapper> offenderGrievancesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("GRIEVANCE_ID", 			new FieldMapper("grievanceId"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("AGY_LOC_ID", 				new FieldMapper("agyLocId"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("GRIEV_REASON_CODE", 		new FieldMapper("grievReasonCode"))
			.put("PARTY_SEQ", 				new FieldMapper("partySeq"))
			.build();
	private final Map<String, FieldMapper> omsOwneroffenderGrievanceTxnsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("GRIEVANCE_ID", 			new FieldMapper("grievanceId"))
			.put("'X'",                     new FieldMapper(" 'x' ")).build();
	private final Map<String, FieldMapper> vheaderBlock = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", 		    new FieldMapper("offenderId"))
			.put(" ALIAS_OFFENDER_ID  ",    new FieldMapper("aliasOffenderId"))
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
			.put("AGE", 					new FieldMapper("age")).put("GENDER", new FieldMapper("gender"))
			.put("OFF_ALERTS", 				new FieldMapper("offAlerts"))
			.put("STATUS_1", 				new FieldMapper("status1"))
			.put("STATUS_2", 				new FieldMapper("status2"))
			.put("STATUS_3", 				new FieldMapper("status3"))
			.put("ETHNICITY", 				new FieldMapper("ethnicity"))
			.put("MOVEMENT_REASON",		    new FieldMapper("movementReason"))
			.put("IMAGE_ID", 				new FieldMapper("imageId"))
			.put("IMAGE_THUMBNAIL", 		new FieldMapper("imageThumbnail"))
			.put("OFF_SUP_LEVEL", 			new FieldMapper("offSupLevel")).build();
	
	private final Map<String, FieldMapper> grievanceTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFF_INVOLVED_FLAG", new FieldMapper("staffInvolvedFlag")).build();

	/**
	 * Creates new OidissueRepositoryImpl class Object
	 */
	public OidissueRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderGrievances
	 *
	 * @return List<OffenderGrievances>
	 *
	 * @throws SQLException
	 */
	public List<OffenderGrievances> offenderGrievancesExecuteQuery(final OffenderGrievances objSearchDao) {
		final String sql = getQuery("OIDISSUE_OFFENDERGRIEVANCES_FIND_OFFENDER_GRIEVANCES");
		final RowMapper<OffenderGrievances> OffenderGrievancesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderGrievances.class, offenderGrievancesMapping);
		List<OffenderGrievances> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDERBOOKID", objSearchDao.getOffenderBookId() ,"user" ,objSearchDao.getCreateUserId() ), OffenderGrievancesRowMapper);
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
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderGrievances List<OffenderGrievances>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offenderGrievancesInsertOffenderGrievances(final List<OffenderGrievances> lstOffenderGrievances) {
		final String sql = getQuery("OIDISSUE_OFFENDERGRIEVANCES_INSERT_OFFENDER_GRIEVANCES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderGrievances offenderGrievances : lstOffenderGrievances) {
			offenderGrievances.setModifyUserId(null);
			parameters.add(new BeanPropertySqlParameterSource(offenderGrievances));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderGrievances.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderGrievances List<OffenderGrievances>
	 *
	 * @throws SQLException
	 */
	public Integer offenderGrievancesUpdateOffenderGrievances(final List<OffenderGrievances> lstOffenderGrievances) {
		final String sql = getQuery("OIDISSUE_OFFENDERGRIEVANCES_UPDATE_OFFENDER_GRIEVANCES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderGrievances offenderGrievances : lstOffenderGrievances) {
			parameters.add(new BeanPropertySqlParameterSource(offenderGrievances));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderGrievances.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderGrievanceTxns
	 *
	 * @return List<OffenderGrievanceTxns>
	 *
	 * @throws SQLException
	 */
	public List<OffenderGrievanceTxns> offenderGrievanceTxnsExecuteQuery(final OffenderGrievanceTxns objSearchDao) {
		final String sql = getQuery("OIDISSUE_OFFENDERGRIEVANCETXNS_FIND_OFFENDER_GRIEVANCE_TXNS");
		final RowMapper<OffenderGrievanceTxns> OffenderGrievanceTxnsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderGrievanceTxns.class, offenderGrievanceTxnsMapping);
		List<OffenderGrievanceTxns> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("GRIEVANCEID", objSearchDao.getGrievanceId()),
				OffenderGrievanceTxnsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderGrievanceTxns List<OffenderGrievanceTxns>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offenderGrievanceTxnsInsertOffenderGrievanceTxns(
			final List<OffenderGrievanceTxns> lstOffenderGrievanceTxns) {
		final String sql = getQuery("OIDISSUE_OFFENDERGRIEVANCETXNS_INSERT_OFFENDER_GRIEVANCE_TXNS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderGrievanceTxns offenderGrievanceTxns : lstOffenderGrievanceTxns) {
			offenderGrievanceTxns.setModifyUserId(null);
			parameters.add(new BeanPropertySqlParameterSource(offenderGrievanceTxns));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderGrievanceTxns.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderGrievanceTxns List<OffenderGrievanceTxns>
	 *
	 * @throws SQLException
	 */
	public Integer offenderGrievanceTxnsUpdateOffenderGrievanceTxns(
			final List<OffenderGrievanceTxns> lstOffenderGrievanceTxns) {
		final String sql = getQuery("OIDISSUE_OFFENDERGRIEVANCETXNS_UPDATE_OFFENDER_GRIEVANCE_TXNS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderGrievanceTxns offenderGrievanceTxns : lstOffenderGrievanceTxns) {
			parameters.add(new BeanPropertySqlParameterSource(offenderGrievanceTxns));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderGrievanceTxns.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderGrievanceTxns List<OffenderGrievanceTxns>
	 *
	 * @throws SQLException
	 */
	public Integer offenderGrievanceTxnsUpdateOffenderGrievanceTxnsPreInsert(
			final OffenderGrievanceTxns lstOffenderGrievanceTxns) {
		final String sql = getQuery("UPDATE_OFFENDER_GRIEVANCE_TXNS_PRE_INSERT");
		Integer returnArray = null;
		try {
			returnArray = namedParameterJdbcTemplate.update(sql,
					new BeanPropertySqlParameterSource(lstOffenderGrievanceTxns));
		} catch (final Exception e) {
			return returnArray;
		}
		return returnArray;
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderGrievanceTxns List<OffenderGrievanceTxns>
	 *
	 * @throws SQLException
	 */
	public Integer offenderGrievanceTxnsDeleteOffenderGrievanceTxns(
			final List<OffenderGrievanceTxns> lstOffenderGrievanceTxns) {
		final String sql = getQuery("OIDISSUE_OFFENDERGRIEVANCETXNS_DELETE_OFFENDER_GRIEVANCE_TXNS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderGrievanceTxns offenderGrievanceTxns : lstOffenderGrievanceTxns) {
			parameters.add(new BeanPropertySqlParameterSource(offenderGrievanceTxns));
		}
		try {
			batchUpdatePreDeletedRows("OFFENDER_GRIEVANCE_TXNS", "GRIEVANCE_ID  = :grievanceId AND TXN_SEQ  = :txnSeq", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in offenderGrievanceTxnsDeleteOffenderGrievanceTxns"+e);
		}
		
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderGrievanceTxns.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<AgencyLocations> rgAgyLocRecordGroup() {
		final String sql = getQuery("OIDISSUE_FIND_RGAGYLOC");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<AgencyLocations> rgAgyLocAllRecordGroup() {
		final String sql = getQuery("OIDISSUE_FIND_RGAGYLOCALL");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<GrievanceTypes> rgGrievTypeRecordGroup(String user) {
		final String sql = getQuery("OIDISSUE_FIND_RGGRIEVTYPE");
		final RowMapper<GrievanceTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, GrievanceTypes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("user", user), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgGrievReasonRecordGroup( String grieveType ,String user ) {
		final String sql = getQuery("OIDISSUE_FIND_RGGRIEVREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("GRIEVTYPE", grieveType , "user" , user), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgGrievReasonAllRecordGroup() {
		final String sql = getQuery("OIDISSUE_FIND_RGGRIEVREASONALL");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<GrievanceTxns> rgTxnTypeRecordGroup(final String grieveType) {
		final String sql = getQuery("OIDISSUE_FIND_RGTXNTYPE");
		final RowMapper<GrievanceTxns> mRowMapper = Row2BeanRowMapper.makeMapping(sql, GrievanceTxns.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("GRIEVTYPE", grieveType), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<GrievanceTxns> rgTxnTypeAllRecordGroup(final String grieveType) {
		final String sql = getQuery("OIDISSUE_FIND_RGTXNTYPEALL");
		final RowMapper<GrievanceTxns> mRowMapper = Row2BeanRowMapper.makeMapping(sql, GrievanceTxns.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("GRIEVTYPE", grieveType), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<StaffMembers> rgStaffRecordGroup(String userName) {
		final String sql = getQuery("OIDISSUE_FIND_RGSTAFF");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("userId",userName), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<StaffMembers> rgStaffAllRecordGroup() {
		final String sql = getQuery("OIDISSUE_FIND_RGSTAFFALL");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgFindingRecordGroup() {
		final String sql = getQuery("OIDISSUE_FIND_RGFINDING");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgFindingAllRecordGroup() {
		final String sql = getQuery("OIDISSUE_FIND_RGFINDINGALL");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgLevelRecordGroup() {
		final String sql = getQuery("OIDISSUE_FIND_RGLEVEL");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgLevelAllRecordGroup() {
		final String sql = getQuery("OIDISSUE_FIND_RGLEVELALL");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgStatusRecordGroup() {
		final String sql = getQuery("OIDISSUE_FIND_RGSTATUS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderGrievances> offBkgOnCheckDeleteMaster(final OffenderGrievances paramBean) {
		final String sql = getQuery("OIDISSUE_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderGrievances> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderGrievances.class, offenderGrievancesMapping);
		List<OffenderGrievances> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievancesKeyDelrec
	 *
	 * @param params
	 *
	 */
	public List<OffenderGrievanceTxns> offenderGrievancesKeyDelrec(final OffenderGrievanceTxns paramBean) {
		final String sql = getQuery("OIDISSUE_OFFENDER_GRIEVANCES_KEYDELREC");
		final RowMapper<OffenderGrievanceTxns> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderGrievanceTxns.class, omsOwneroffenderGrievanceTxnsMapping);
		List<OffenderGrievanceTxns> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievancesPostQuery
	 *
	 * @param params
	 *
	 */
	public OffenderGrievanceTxns offenderGrievancesPostQuery(final OffenderGrievanceTxns paramBean) {
		final String sql = getQuery("OIDISSUE_OFFENDER_GRIEVANCES_POSTQUERY");
		final RowMapper<OffenderGrievanceTxns> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderGrievanceTxns.class, offenderGrievanceTxnsMapping);
		final OffenderGrievanceTxns returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievancesPostQuery
	 *
	 * @param params
	 *
	 */
	public OffenderGrievanceTxns offenderGrievancesPostQueryStatus(final OffenderGrievances paramBean) {
		final String sql = getQuery("OIDISSUE_OFFENDER_GRIEVANCES_POSTQUERY_STATUS");
		final RowMapper<OffenderGrievanceTxns> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderGrievanceTxns.class, offenderGrievanceTxnsMapping);
		OffenderGrievanceTxns returnObj = new OffenderGrievanceTxns();
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("GRIEVANCEID", paramBean.getGrievanceId()), columnRowMapper);
		} catch (final Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievancesPostQuery
	 *
	 * @param params
	 *
	 */
	public OffenderGrievanceTxns offenderGrievancesPostQueryStatusDesc(final OffenderGrievances paramBean) {
		final String sql = getQuery("OIDISSUE_OFFENDER_GRIEVANCES_POSTQUERYLV_GRIEVANCE_DESC_CUR");
		final RowMapper<OffenderGrievanceTxns> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderGrievanceTxns.class, offenderGrievanceTxnsMapping);
		final OffenderGrievanceTxns returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("currStatus", paramBean.getCurrStatus()), columnRowMapper);
		return returnObj;
	}

	public List<OffenderGrievanceTxns> offenderGrievancesPostQueryStatusGrievanceLevel(
			final OffenderGrievances paramBean) {
		final String sql = getQuery("OIDISSUE_OFFENDER_GRIEVANCES_POSTQUERY_STATUS_GRIEV_LEVEL");
		final RowMapper<OffenderGrievanceTxns> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderGrievanceTxns.class, offenderGrievanceTxnsMapping);
		List<OffenderGrievanceTxns> returnObj = new ArrayList<>();
		returnObj = namedParameterJdbcTemplate.query(sql, createParams("GRIEVANCEID", paramBean.getGrievanceId()),
				columnRowMapper);
		return returnObj;
	}

	public String offenderGrievancesPostQueryGrievanceLevel(final OffenderGrievanceTxns paramBean) {
		final String sql = getQuery("OIDISSUE_OFFENDER_GRIEVANCES_POSTQUERY_GRIEV_LEVEL");
		final String returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("GRIEVLEVEL", paramBean.getGrievLevel()), String.class);
		return returnObj;
	}

	public String offenderGrievancesPostQueryAgyLocId(final OffenderGrievances paramBean) {
		final String sql = getQuery("OIDISSUE_OFFENDER_GRIEVANCES_WHENNEWRECORDINSTANCE");
		final String returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("AGYLOCID", paramBean.getAgyLocId()), String.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievancesPostQuery
	 *
	 * @param params
	 *
	 */
	public String offenderGrievancesPostQueryReportDate(final String agyLocId) {
		final String sql = getQuery("OIDISSUE_OFFENDER_GRIEVANCES_POSTQUERY_REPORT_DATE");
		final String returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("AGYLOCID", agyLocId),
				String.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievancesPostQuery
	 *
	 * @param params
	 *
	 */
	public Long daysRespondData(final String grieveType, final String txnType) {
		final String sql = getQuery("DAYS_RESPOND_DATA");
		Long returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("GRIEVETYPE", grieveType, "TXNTYPE", txnType), Long.class);
		} catch (final Exception e) {
			returnObj = null;
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievancesPostQuerylv_grievance_desc_cur
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes offenderGrievancesPostQuerylvGrievanceDescCur(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDISSUE_OFFENDER_GRIEVANCES_POSTQUERY_LV_GRIEVANCE_DESC_CUR");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievancesPreInsert
	 *
	 * @param params
	 *
	 */
	public Long offenderGrievancesPreInsert() {
		final String sql = getQuery("OIDISSUE_OFFENDER_GRIEVANCES_PREINSERT");
		Long obj = null;
		Long returnval = null;
		obj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		if (obj != null) {
			returnval = obj;
		}
		return returnval;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievancesWhenNewRecordInstance
	 *
	 * @param params
	 *
	 */
	public AgencyLocations offenderGrievancesWhenNewRecordInstance(final AgencyLocations paramBean) {
		final String sql = getQuery("OIDISSUE_OFFENDER_GRIEVANCES_WHENNEWRECORDINSTANCE");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		final AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievancesOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public OffenderGrievanceTxns offenderGrievancesOnCheckDeleteMaster(final OffenderGrievanceTxns paramBean) {
		final String sql = getQuery("OIDISSUE_OFFENDER_GRIEVANCES_ONCHECKDELETEMASTER");
		final RowMapper<OffenderGrievanceTxns> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderGrievanceTxns.class, offenderGrievanceTxnsMapping);
		final OffenderGrievanceTxns returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievanceTxnsPostQuery
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes offenderGrievanceTxnsPostQuery(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDISSUE_OFFENDER_GRIEVANCE_TXNS_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievanceTxnsPostQuery
	 *
	 * @param params
	 *
	 */
	public StaffMembers offenderGrievanceTxnsPostQuery(final StaffMembers paramBean) {
		final String sql = getQuery("OIDISSUE_OFFENDER_GRIEVANCE_TXNS_POSTQUERY");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		final StaffMembers returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievanceTxnsWhenNewRecordInstance
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes offenderGrievanceTxnsWhenNewRecordInstance(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDISSUE_OFFENDER_GRIEVANCE_TXNS_WHENNEWRECORDINSTANCE");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievanceTxnsWhenNewRecordInstance
	 *
	 * @param params
	 *
	 */
	public OffenderGrievanceTxns offenderGrievanceTxnsWhenNewRecordInstance(final OffenderGrievanceTxns paramBean) {
		final String sql = getQuery("OIDISSUE_OFFENDER_GRIEVANCE_TXNS_WHENNEWRECORDINSTANCE");
		final RowMapper<OffenderGrievanceTxns> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderGrievanceTxns.class, offenderGrievanceTxnsMapping);
		final OffenderGrievanceTxns returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offwarZonesPreInsertc
	 *
	 * @param paramBean
	 *
	 */
	public Long offenderGrievanceTxnsPreInsert(final Long paramBean) {
		final String sql = getQuery("OIDISSUE_OFFENDER_GRIEVANCE_TXNS_PREINSERT");
		Long obj = null;
		Long returnval = null;
		obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("GRIEVANCEID", paramBean), Long.class);
		if (obj != null) {
			returnval = obj;
		}
		return returnval;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievanceTxnsWhenValidateRecord
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes offenderGrievanceTxnsWhenValidateRecord(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDISSUE_OFFENDER_GRIEVANCE_TXNS_WHENVALIDATERECORD");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidissueKeyCommit
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes oidissueKeyCommit(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDISSUE_OIDISSUE_KEYCOMMIT");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public OmsModules createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OIDISSUE_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				referenceCodesMapping);
		final OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * isActiveTxn
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes isActiveTxn(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDISSUE_IS_ACTIVE_TXN");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * return Header block details
	 * 
	 * @param searchBean
	 * @return List<VHeaderBlock>
	 */
	@Override
	public List<VHeaderBlock> offbkgInstGlobalQuery(final VHeaderBlock searchBean) {
		final String sql = queryBuilderFactory.getQueryBuilder(getQuery("OIDISSUE_FIND_V_HEADER_BLOCK"), vheaderBlock).build();
		final RowMapper<VHeaderBlock> aliasesRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vheaderBlock);
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		inParameterSource.addValue("userId", searchBean.getCreateUserId());
		if (searchBean.getOffenderId() != null) {
			sqlQuery.append("OFFENDER_ID = :OFFENDER_ID" + " AND ");
			inParameterSource.addValue("OFFENDER_ID", searchBean.getOffenderId());
		}

		if (searchBean.getRootOffenderId() != null) {
			sqlQuery.append("ROOT_OFFENDER_ID = :ROOT_OFFENDER_ID" + " AND ");
			inParameterSource.addValue("ROOT_OFFENDER_ID", searchBean.getRootOffenderId());
		}
		if (searchBean.getLastName() != null) {
			sqlQuery.append("LAST_NAME  LIKE :LAST_NAME" + " AND ");
			inParameterSource.addValue("LAST_NAME", searchBean.getLastName() + "%");

		}
		if (searchBean.getFirstName() != null) {
			sqlQuery.append("FIRST_NAME LIKE :FIRST_NAME" + " AND ");
			inParameterSource.addValue("FIRST_NAME", searchBean.getFirstName() + "%");

		}
		if (searchBean.getMiddleName() != null) {
			sqlQuery.append("MIDDLE_NAME LIKE :MIDDLE_NAME" + " AND ");
			inParameterSource.addValue("MIDDLE_NAME", searchBean.getMiddleName() + "%");

		}
		if (searchBean.getBirthDate() != null) {
			sqlQuery.append("BIRTH_DATE =  :BIRTH_DATE" + " AND ");
			inParameterSource.addValue("BIRTH_DATE", searchBean.getBirthDate());

		}
		if (searchBean.getBookingNo() != null && !searchBean.getBookingNo().isEmpty()) {
			sqlQuery.append("BOOKING_NO = :BOOKING_NO" + " AND ");
			inParameterSource.addValue("BOOKING_NO", searchBean.getBookingNo());

		}
		if (searchBean.getOffenderBookId() != null) {
			sqlQuery.append("OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID" + " AND ");
			inParameterSource.addValue("OFFENDER_BOOK_ID", searchBean.getOffenderBookId());

		}
		if (searchBean.getOffenderIdDisplay() != null) {
			sqlQuery.append("OFFENDER_ID_DISPLAY = :OFFENDER_ID_DISPLAY" + " AND ");
			inParameterSource.addValue("OFFENDER_ID_DISPLAY", searchBean.getOffenderIdDisplay());

		}
		if (searchBean.getAgyLocId() != null && searchBean.getAgyLocType().equals("INST")) {
			sqlQuery.append(
					" ((V.BOOKING_BEGIN_DATE = ( SELECT MAX(VHB2.BOOKING_BEGIN_DATE) FROM V_HEADER_BLOCK_FN(:userId) VHB2 WHERE VHB2.ROOT_OFFENDER_ID = "
							+ " V.ROOT_OFFENDER_ID AND VHB2.ACTIVE_FLAG ='Y' AND EXISTS ( SELECT cal.agy_loc_id FROM caseload_agency_locations cal, "
							+ " caseloads csld WHERE csld.caseload_type = 'INST' AND csld.caseload_id = cal.caseload_id AND cal.agy_loc_id = vhb2.agy_loc_id "
							+ " AND cal.caseload_id = :caseloadId AND :agyLocTpye = 'INST' )) AND V.ACTIVE_FLAG IS NOT NULL  ) "
							+ " OR V.BOOKING_BEGIN_DATE = ( SELECT MAX(VHB2.BOOKING_BEGIN_DATE) FROM V_HEADER_BLOCK_FN(:userId) VHB2 "
							+ " WHERE VHB2.ROOT_OFFENDER_ID = V.ROOT_OFFENDER_ID AND NOT EXISTS (SELECT NULL FROM V_HEADER_BLOCK_FN(:userId) VHB4 "
							+ " WHERE VHB4.ROOT_OFFENDER_ID = VHB2.ROOT_OFFENDER_ID AND VHB4.ACTIVE_FLAG='Y' ) AND EXISTS ( SELECT cal.agy_loc_id "
							+ " FROM caseload_agency_locations cal, caseloads csld WHERE csld.caseload_type = 'INST' AND csld.caseload_id = cal.caseload_id "
							+ " AND cal.agy_loc_id = vhb2.agy_loc_id AND cal.caseload_id = :caseloadId AND :agyLocTpye = 'INST')) "
							+ "  OR V.BOOKING_BEGIN_DATE = ( SELECT MAX(VHB2.BOOKING_BEGIN_DATE) FROM V_HEADER_BLOCK_FN(:userId) VHB2 "
							+ " WHERE VHB2.ROOT_OFFENDER_ID = V.ROOT_OFFENDER_ID AND NOT EXISTS (SELECT NULL FROM V_HEADER_BLOCK_FN(:userId) VHB4 "
							+ " WHERE VHB4.ROOT_OFFENDER_ID = VHB2.ROOT_OFFENDER_ID AND VHB4.ACTIVE_FLAG = 'Y'))) ");
			inParameterSource.addValue("caseloadId", searchBean.getAgyLocId());
			inParameterSource.addValue("agyLocTpye", searchBean.getAgyLocType());
		} else {
			sqlQuery.append(
					" (V_HEADER_BLOCK.OFFENDER_BOOK_ID  = (DECODE(   (SELECT DISTINCT('Y')   FROM OFFENDER_BOOKINGS VHB "
							+ " WHERE VHB.ROOT_OFFENDER_ID = V_HEADER_BLOCK.ROOT_OFFENDER_ID AND VHB.COMMUNITY_ACTIVE_FLAG IS NOT NULL), "
							+ " 'Y', (SELECT MAX(VHB2.OFFENDER_BOOK_ID) FROM OFFENDER_BOOKINGS VHB2 "
							+ " WHERE VHB2.ROOT_OFFENDER_ID = V_HEADER_BLOCK.ROOT_OFFENDER_ID AND ( VHB2.COMMUNITY_ACTIVE_FLAG = 'Y' "
							+ " OR (NOT EXISTS ( SELECT 'X' FROM OFFENDER_BOOKINGS VHB3 WHERE VHB3.COMMUNITY_ACTIVE_FLAG = 'Y' "
							+ " AND VHB3.ROOT_OFFENDER_ID = VHB2.ROOT_OFFENDER_ID))) AND (  (VHB2.INTAKE_AGY_LOC_ID > 'CLOSE' "
							+ " AND EXISTS ( SELECT 'X' FROM   OFFENDER_BOOKING_AGY_LOCS LOCS, CASELOAD_AGENCY_LOCATIONS CAL1 "
							+ " WHERE  CAL1.CASELOAD_ID =:caseloadId AND CAL1.AGY_LOC_ID = LOCS.AGY_LOC_ID AND LOCS.OFFENDER_BOOK_ID = VHB2.OFFENDER_BOOK_ID "
							+ " AND LOCS.REMOVED_DATE IS  NULL AND :agyLocTpye  = 'COMM')) OR (   VHB2.INTAKE_AGY_LOC_ID = 'CLOSE' AND EXISTS ( SELECT 'X' "
							+ " FROM  OFFENDER_BOOKING_AGY_LOCS LOCS, CASELOAD_AGENCY_LOCATIONS CAL1  WHERE CAL1.CASELOAD_ID = :caseloadId "
							+ " AND   CAL1.AGY_LOC_ID = LOCS.AGY_LOC_ID AND LOCS.OFFENDER_BOOK_ID = VHB2.OFFENDER_BOOK_ID AND :agyLocTpye  = 'COMM')))), "
							+ " ( SELECT MAX(VHB2.OFFENDER_BOOK_ID) FROM   V_HEADER_BLOCK_FN(:userId)  VHB2 WHERE VHB2.ROOT_OFFENDER_ID = V_HEADER_BLOCK.ROOT_OFFENDER_ID "
							+ " AND (NOT EXISTS (SELECT NULL FROM V_HEADER_BLOCK_FN(:userId)  VHB4 WHERE VHB4.ROOT_OFFENDER_ID = VHB2.ROOT_OFFENDER_ID "
							+ " AND VHB4.ACTIVE_FLAG = 'Y') OR EXISTS (SELECT NULL FROM V_HEADER_BLOCK_FN(:userId)  VHB4 WHERE VHB4.ROOT_OFFENDER_ID = VHB2.ROOT_OFFENDER_ID "
							+ " AND VHB4.ACTIVE_FLAG = 'Y')))))) ");
			inParameterSource.addValue("caseloadId", searchBean.getAgyLocId());
			inParameterSource.addValue("agyLocTpye", searchBean.getAgyLocType());
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, aliasesRowMapper);
	}

	public String caseloadTypeData(String userid) {
		final String sql = getQuery("FIND_WORKING_CASELOAD_ID");
		final String returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("userId",userid), String.class);
		return returnObj;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<OffenderGrievances> maxGrievanceIdComparison(final Long grievanceId) {
		final String sql = getQuery("FIND_MAX_START_DATE");
		final RowMapper<OffenderGrievances> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderGrievances.class,
				offenderGrievancesMapping);
		List<OffenderGrievances> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", grievanceId), mRowMapper);
		} catch (final Exception e) {
			logger.error("rgProposedEmploymentRecordGroup", e);
			return returnList;
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return Long
	 */
	public Long maxGrievanceId() {
		final String sql = getQuery("FIND_MAX_GRIEVANCE_ID");
		Long returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return Long
	 */
	public String maxGrievanceType(final Long paramBean) {
		final String sql = getQuery("FIND_MAX_GRIEV_TYPE");
		String returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("GRIEVANCEID", paramBean),
				String.class);
		return returnList;
	}

	public Integer offenderGrievanceTxnsDaysLeft(final OffenderGrievanceTxns paramBean) {
		final String sql = getQuery("OIDISSUE_OFFENDER_GRIEVANCE_TXNS_DAYS_LEFT");
		int returnValue = 0;
		returnValue = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("GRIEVANCEID", paramBean.getGrievanceId()), Integer.class);

		return returnValue;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievanceTxnsWhenValidateRecord
	 *
	 * @param params
	 *
	 */
	public GrievanceTypes offenderGrievanceTxnsWhenValidateRecord(final String grievType) {
		final String sql = getQuery("OIDISSUE_OFFENDER_GRIEVANCE_TXNS_WHENVALIDATEREC");
		final RowMapper<GrievanceTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, GrievanceTypes.class,
				grievanceTypesMapping);
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("LV_TYPE", grievType), columnRowMapper);
		} catch (final Exception e) {
			logger.error("offenderGrievanceTxnsWhenValidateRecord :", e);
			return null;
		}
	}

	public Integer grievanceIdCur(final Long grievanceId) {
		final String sql = getQuery("OIDISSUE_ENABLE_STAFF_BUTTON");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("GRIEVANCE_ID", grievanceId),
					Integer.class);
		} catch (final Exception e) {
			logger.error("grievanceIdCur :", e);
			return null;
		}
	}

	public Integer validationStaff(final Long grievanceId) {
		final String sql = getQuery("OIDISSUE_OFFENDER_GRIEV_STAFFS_VALID");
		Integer returnData = 0;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("GRIEVANCEID", grievanceId),
					Integer.class);
		} catch (final Exception e) {
			return returnData;
		}
		return returnData;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidissueWhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public Integer oidissueWhenNewFormInstance(String userName) {
		final String sql = getQuery("OIDISSUE_OIDISSUE_WHENNEWFORMINSTANCE");
		Integer returnData = 0;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("userName",userName),
					Integer.class);
		} catch (final Exception e) {
			return returnData;
		}
		return returnData;
	}

	@Override
	public Integer offenderGrievancesDeleteOffenderGrievances(List<OffenderGrievances> lstOffenderGrievances) {

		final String sql = getQuery("OIDISSUE_OFFENDERGRIEVANCES_DELETE_OFFENDER_GRIEVANCES");

		Integer count = 0;
		for (int i = 0; i < lstOffenderGrievances.size(); i++) {
			try {
				Map<String, Object> inputMap = new HashMap<String, Object>();
				inputMap.put("grievanceId", lstOffenderGrievances.get(i).getGrievanceId());
				inputMap.put("modify_user_id", lstOffenderGrievances.get(i).getModifyUserId());
				updatePreDeletedRow("OFFENDER_GRIEVANCES", "GRIEVANCE_ID  = :grievanceId", inputMap);
			} catch (Exception e) {
				logger.error("batchUpdatePreDeletedRows in offenderGrievancesDeleteOffenderGrievances"+e);
			}
			
			try {
				count = namedParameterJdbcTemplate.update(sql,
						createParams("grievanceId", lstOffenderGrievances.get(i).getGrievanceId()));
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " offenderGrievancesDeleteOffenderGrievances " + e);
			}
			if (count == 0) {
				return 0;
			}
		}
		return count;

	}

}

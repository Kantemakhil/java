package net.syscon.s4.inst.movements.housingchanges.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
import net.syscon.s4.inst.movements.beans.ReserveBedLocations;
import net.syscon.s4.inst.movements.housingchanges.OmurmresRepository;

/**
 * Class OmurmresRepositoryImpl
 */
@Repository
public class OmurmresRepositoryImpl extends RepositoryBase implements OmurmresRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OmurmresRepositoryImpl.class.getName());

private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DOMAIN", 						new FieldMapper("domain"))
.put("DESCRIPTION", 				new FieldMapper("description"))
.put("REMOVE_REASON", 				new FieldMapper("removeReason"))
.build();
private final Map<String, FieldMapper> vHeaderBlockMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OFFENDER_ID", 			new FieldMapper("offenderId"))
.put("LAST_NAME", 				new FieldMapper("lastName"))
.put("FIRST_NAME", 				new FieldMapper("firstName"))
.put("OFFENDER_BOOK_ID", 	 	new FieldMapper("offenderBookId"))
.put("OFFENDER_ID_DISPLAY",  	new FieldMapper("offenderIdDisplay"))
.put("AGY_LOC_ID",           	new FieldMapper("agyLocId"))
.put("LIVING_UNIT_DESCRIPTION", new FieldMapper("livingUnitDescription"))
.put("LIV_UNIT_DESC", 			new FieldMapper("livUnitDesc"))
.build();
private final Map<String, FieldMapper> reserveBedLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("COMMENT_TEXT", 						new FieldMapper("commentText"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("RESERVE_UNTIL_DATE", 					new FieldMapper("reserveUntilDate"))
.put("REMOVE_REASON", 						new FieldMapper("removeReason"))
.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
.put("AGY_LOC_ID", 							new FieldMapper("agyLocId"))
.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("LIVING_UNIT_ID", 						new FieldMapper("livingUnitId"))
.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
.put("RESERVE_BED_ID", 						new FieldMapper("reserveBedId"))
.put("OFFENDER_ID", 						new FieldMapper("offenderId"))
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
.put("DOMAIN", 						new FieldMapper("domain"))
.build();

	/**
	 * Creates new OmurmresRepositoryImpl class Object
	 */
	public OmurmresRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            ReserveBedLocations
	 *
	 * @return List<ReserveBedLocations>
	 *
	 * @
	 */
	public List<ReserveBedLocations> resBlExecuteQuery(final ReserveBedLocations objSearchDao) {
		final String sql = getQuery("OMURMRES_RESBL_FIND_RESERVE_BED_LOCATIONS");
		final RowMapper<ReserveBedLocations> ReserveBedLocationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReserveBedLocations.class, reserveBedLocationsMapping);
		final ArrayList<ReserveBedLocations> returnList = (ArrayList<ReserveBedLocations>) namedParameterJdbcTemplate
				.query(sql, createParams(), ReserveBedLocationsRowMapper);
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
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstReserveBedLocations
	 *            List<ReserveBedLocations>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer resBlInsertReserveBedLocations(final List<ReserveBedLocations> lstReserveBedLocations) {
		final String sql = getQuery("OMURMRES_RESBL_INSERT_RESERVE_BED_LOCATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ReserveBedLocations reserveBedLocations : lstReserveBedLocations) {
			parameters.add(new BeanPropertySqlParameterSource(reserveBedLocations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstReserveBedLocations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstReserveBedLocations
	 *            List<ReserveBedLocations>
	 *
	 * @
	 */
	public Integer resBlUpdateReserveBedLocations(final List<ReserveBedLocations> lstReserveBedLocations) {
		final String sql = getQuery("OMURMRES_RESBL_UPDATE_RESERVE_BED_LOCATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ReserveBedLocations reserveBedLocations : lstReserveBedLocations) {
			parameters.add(new BeanPropertySqlParameterSource(reserveBedLocations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstReserveBedLocations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstReserveBedLocations
	 *            List<ReserveBedLocations>
	 *
	 * @
	 */
	public Integer resBlDeleteReserveBedLocations(final List<ReserveBedLocations> lstReserveBedLocations) {
		final String sql = getQuery("OMURMRES_RESBL_DELETE_RESERVE_BED_LOCATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ReserveBedLocations reserveBedLocations : lstReserveBedLocations) {
			parameters.add(new BeanPropertySqlParameterSource(reserveBedLocations));
		}
		try {
			batchUpdatePreDeletedRows("RESERVE_BED_LOCATIONS", "RESERVE_BED_ID = :reserveBedId", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in resBlDeleteReserveBedLocations"+e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstReserveBedLocations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

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
		final String sql = getQuery("OMURMRES_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), SystemProfilesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstSystemProfiles
	 *            List<SystemProfiles>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer sysPflInsertSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OMURMRES_SYSPFL_INSERT_SYSTEM_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSystemProfiles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstSystemProfiles
	 *            List<SystemProfiles>
	 *
	 * @
	 */
	public Integer sysPflDeleteSystemProfiles(final List<SystemProfiles> lstSystemProfiles) {
		final String sql = getQuery("OMURMRES_SYSPFL_DELETE_SYSTEM_PROFILES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SystemProfiles systemProfiles : lstSystemProfiles) {
			parameters.add(new BeanPropertySqlParameterSource(systemProfiles));
		}
		try {
			batchUpdatePreDeletedRows("SYSTEM_PROFILES", "PROFILE_CODE = :profileCode", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in sysPflDeleteSystemProfiles"+e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSystemProfiles.size() == returnArray.length) {
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
	public List<ReferenceCodes> cgfkResBlRemoveReasonRecordGroup() {
		final String sql = getQuery("OMURMRES_FIND_CGFKRESBLREMOVEREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("In method cgfkResBlRemoveReasonRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkResBlResBlRefCod
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkResBlResBlRefCod(final ReferenceCodes paramBean) {
		final String sql = getQuery("OMURMRES_CGFKCHK_RES_BL_RES_BL_REF_COD");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkResBlResBlVHbF1
	 *
	 * @param params
	 *
	 */
	public List<VHeaderBlock> cgfkchkResBlResBlVHbF1(final VHeaderBlock paramBean) {
		final String sql = getQuery("OMURMRES_CGFKCHK_RES_BL_RES_BL_V_HB_F1");
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vHeaderBlockMapping);
		final ArrayList<VHeaderBlock> returnList = (ArrayList<VHeaderBlock>) namedParameterJdbcTemplate.query(sql,
				createParams("USERID",paramBean.getCreateUserId()), columnRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            ReserveBedLocations
	 *
	 * @return List<ReserveBedLocations>
	 *
	 * @
	 */
	public VHeaderBlock displayItemsExecuteQuery(final VHeaderBlock objSearchDao) {
		final String sql = getQuery("OMURMRES_DISPLAY_ITEMS_CURSOR");
		 VHeaderBlock returnList = new VHeaderBlock();
		final RowMapper<VHeaderBlock> vHeaderBlockRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vHeaderBlockMapping);
		try {
			returnList = (VHeaderBlock) namedParameterJdbcTemplate.queryForObject(sql, createParams("V_OFFENDER_ID",
					objSearchDao.getOffenderId(), "V_AGY_LOC_ID", objSearchDao.getAgyLocId(),"USERID",objSearchDao.getCreateUserId()), vHeaderBlockRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param vblock
	 *
	 * @return Integer
	 *
	 * @
	 */
	@Override
	public Integer offBookingUpdate(final VHeaderBlock vblock) {

		final String sql = getQuery("OMURMRES_UPDATE_OFFENDER_BOOKINGS");
		int[] returnArray = new int[] {};
		final ArrayList<VHeaderBlock> list = new ArrayList<VHeaderBlock>();
		list.add(vblock);
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VHeaderBlock vhbList : list) {
			parameters.add(new BeanPropertySqlParameterSource(vhbList));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param BedAssignmentHistories
	 * @return List<Integer>
	 *
	 */
	public Integer bedAhInsertBedAssignmentHistories(final BedAssignmentHistories bedassignlist) {
		final String sql = getQuery("OMURMRES_INSERT_BED_ASSIGNMENT_HISTORIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		final ArrayList<BedAssignmentHistories> list = new ArrayList<BedAssignmentHistories>();
		list.add(bedassignlist);
		for (final BedAssignmentHistories lstBedAsgnHist : list) {
			parameters.add(new BeanPropertySqlParameterSource(lstBedAsgnHist));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * bedAhPreInsert
	 *
	 * @param params
	 *
	 */
	public Integer bedAhPreInsert(final BedAssignmentHistories paramBean) {
		final String sql = getQuery("OMURMRES_BED_AH_PREINSERT");
		Integer returnObj = 0;
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()), Integer.class);
		return returnObj;
	}
	
	@Override
	public List<OffenderBookings> getoffBookOldRec(BigDecimal OffenderBookId) {
	final String sql= getQuery("GET_OFF_BOOK_OLD_REC");
	final RowMapper<OffenderBookings> vHeaderBlockRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
			vHeaderBlockMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("offender_book_id",OffenderBookId), vHeaderBlockRowMapper);
	}
}

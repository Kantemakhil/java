package net.syscon.s4.inst.movementexternal.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.VNameSearch;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.movementexternal.OidbutabRepository;

/**
 * Class OidbutabRepositoryImpl
 */
@Repository
public class OidbutabRepositoryImpl extends RepositoryBase implements OidbutabRepository {
	private static Logger logger = LogManager.getLogger(OidbutabRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> vHeaderBlockMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("HEADER_STATUS", new FieldMapper("headerStatus")).put("AGY_LOC_TYPE", new FieldMapper("agyLocType"))
			.put("BOOKING_STATUS", new FieldMapper("bookingStatus")).put("AGE", new FieldMapper("age"))
			.put("ETHNICITY", new FieldMapper("ethnicity")).put("OFFICER", new FieldMapper("officer"))
			.put("SUFFIX", new FieldMapper("suffix")).put("PRISON_LOCATION", new FieldMapper("prisonLocation"))
			.put("DISCLOSURE_FLAG", new FieldMapper("disclosureFlag"))
			.put("CREATE_AGY_LOC_ID", new FieldMapper("createAgyLocId"))
			.put("LIVING_UNIT_ID", new FieldMapper("livingUnitId"))
			.put("COMMUNITY_STATUS", new FieldMapper("communityStatus"))
			.put("OFFENDER_ID", new FieldMapper("offenderId")).put("LAST_NAME", new FieldMapper("lastName"))
			.put("LOCATION_CODE", new FieldMapper("locationCode")).put("STATUS_1", new FieldMapper("status1"))
			.put("MOVEMENT_REASON", new FieldMapper("movementReason")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("BOOKING_BEGIN_DATE", new FieldMapper("bookingBeginDate"))
			.put("STATUS_REASON", new FieldMapper("statusReason")).put("OFF_SUP_LEVEL", new FieldMapper("offSupLevel"))
			.put("ALIAS_OFFENDER_ID", new FieldMapper("aliasOffenderId"))
			.put("BOOKING_END_DATE", new FieldMapper("bookingEndDate"))
			.put("COMMUNITY_ACTIVE_FLAG", new FieldMapper("communityActiveFlag"))
			.put("BOOKING_NO", new FieldMapper("bookingNo")).put("IN_OUT_STATUS", new FieldMapper(" inOutStatus"))
			.put("PROFILE_VALUE", new FieldMapper(" profileValue "))
			.put("ASSIGNED_STAFF_ID", new FieldMapper("assignedStaffId")).put("GENDER", new FieldMapper("gender"))
			.put("BIRTH_DATE", new FieldMapper("birthDate"))
			.put("CREATE_INTAKE_AGY_LOC_ID", new FieldMapper("createIntakeAgyLocId"))
			.put("OFF_ALERTS", new FieldMapper("offAlerts")).build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();
	private final Map<String, FieldMapper> livingUnitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIVING_UNIT_CODE", new FieldMapper("livingUnitCode        "))
			.put("LIST_SEQ", new FieldMapper("listSeq        "))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();
	private final Map<String, FieldMapper> movementReasonsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.build();
	private final Map<String, FieldMapper> offenderExternalMovementsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'HH24MI')", new FieldMapper(" 'hh24mi')")).put("TO_CITY)", new FieldMapper(" toCity)"))
			.put("TRUNC(MOVEMENT_DATE)", new FieldMapper(" trunc(movementDate)"))
			.put("NVL(TO_CHAR(MOVEMENT_TIME", new FieldMapper(" nvl(toChar(movementTime"))
			.put("NVL(TO_AGY_LOC_ID", new FieldMapper(" nvl(toAgyLocId")).put("'0000')", new FieldMapper(" '0000') "))
			.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LAST_NAME", new FieldMapper(" lastName")).put("IN_OUT_STATUS", new FieldMapper(" inOutStatus"))
			.put("PROFILE_VALUE", new FieldMapper(" profileValue ")).put("AGY_LOC_ID", new FieldMapper(" agyLocId"))
			.build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId "))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	/**
	 * Creates new OidbutabRepositoryImpl class Object
	 */
	public OidbutabRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VHeaderBlock
	 *
	 * @return List<VHeaderBlock>
	 *
	 * 
	 */
	public List<VHeaderBlock> vhbExecuteQuery(final VHeaderBlock objSearchDao) {
		final String sql = getQuery("OIDBUTAB_VHB_FIND_V_HEADER_BLOCK");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			valuesList.addValue("USERID", objSearchDao.getCreateuserId());
			sqlQuery.append(" where ");
			if (objSearchDao.getInOutStatus().equals("OUT")) {
				sqlQuery.append("v_header_block.agy_loc_id = :agyLocId and v_header_block.active_flag = 'Y' "
						+ " and v_header_block.in_out_status = 'OUT' "
						+ " and  exists (select 1 from offender_external_movements oem1 "
						+ "	where oem1.offender_book_id = v_header_block.OFFENDER_BOOK_ID  "
						+ " and oem1.movement_seq = (select max(movement_seq) from offender_external_movements oem2 where oem2.offender_book_id = v_header_block.offender_book_id) "
						+ " and oem1.movement_type = 'TAP' and oem1.direction_code = 'OUT')" + " and ");
				valuesList.addValue("agyLocId", objSearchDao.getAgyLocId());
			}
			if (objSearchDao.getInOutStatus().equals("IN")) {
				sqlQuery.append("v_header_block.agy_loc_id = :agyLocId and v_header_block.active_flag = 'Y' "
						+ "and v_header_block.in_out_Status = 'IN' "
						+ " and v_header_block.living_unit_id in (with recursive cte as (select lu.living_unit_id from living_units lu where lu.living_unit_id = :livingUnitId union all "
						+ " select lu.living_unit_id from living_units lu join cte c on  "
						+ "(c.living_unit_id = lu.parent_living_unit_id) ) select * from cte) " + " and ");
				valuesList.addValue("agyLocId", objSearchDao.getAgyLocId());
				valuesList.addValue("livingUnitId", objSearchDao.getLivingUnitId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<VHeaderBlock> VOffenderAllSchedulesRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				VHeaderBlock.class, vHeaderBlockMapping);
		List<VHeaderBlock> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, valuesList, VOffenderAllSchedulesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVHeaderBlock List<VHeaderBlock>
	 *
	 * 
	 */
	public Integer vhbUpdateVHeaderBlock(final List<VHeaderBlock> lstVHeaderBlock) {
		final int insertCount = 0;
		final String sql = getQuery("OIDBUTAB_VHB_UPDATE_V_HEADER_BLOCK");
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VHeaderBlock vHeaderBlock : lstVHeaderBlock) {
			parameters.add(new BeanPropertySqlParameterSource(vHeaderBlock));
		}
		 namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVHeaderBlock.size() == insertCount) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> rgInstitutionRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIDBUTAB_FIND_RGINSTITUTION");
		final RowMapper<AgencyLocations> agencyLocationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyLocations.class, agencyLocationsMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseloadId),
					agencyLocationsRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> rgActiveAgencyRecordGroup(final String agyLocId) {
		final String sql = getQuery("OIDBUTAB_FIND_RGACTIVEAGENCY");
		final RowMapper<AgencyLocations> agencyLocationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyLocations.class, agencyLocationsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("AGYLOCID", agyLocId), agencyLocationsRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> rgActiveAgencyRecordGroupForGrid(final String agyLocId) {
		final String sql = getQuery("OIDBUTAB_FIND_RGACTIVEAGENCY_FORGRID");
		final RowMapper<AgencyLocations> agencyLocationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyLocations.class, agencyLocationsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("AGYLOCID", agyLocId), agencyLocationsRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> rgLuLevel1RecordGroup(final String agyLocId) {
		final String sql = getQuery("OIDBUTAB_FIND_RGLULEVEL1");
		final RowMapper<LivingUnits> livingUnitsRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("AGYLOCID", agyLocId), livingUnitsRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> rgLuLevel2RecordGroup(final String agyLocId, final String livingUnitId) {
		final String sql = getQuery("OIDBUTAB_FIND_RGLULEVEL2");
		final RowMapper<LivingUnits> livingUnitsRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("AGYLOCID", agyLocId, "LIVINGUNITID", livingUnitId), livingUnitsRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> rgLuLevel3RecordGroup(final String agyLocId, final String livingUnitId) {
		final String sql = getQuery("OIDBUTAB_FIND_RGLULEVEL3");
		final RowMapper<LivingUnits> livingUnitsRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("AGYLOCID", agyLocId, "LIVINGUNITID", livingUnitId), livingUnitsRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgCityRecordGroup() {
		final String sql = getQuery("OIDBUTAB_FIND_RGCITY");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MovementReasons>
	 */
	public List<MovementReasons> rgReasonRecordGroup() {
		final String sql = getQuery("OIDBUTAB_FIND_RGREASON");
		final RowMapper<MovementReasons> movementReasonsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				MovementReasons.class, movementReasonsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), movementReasonsRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * qryBlkWhenNewBlockInstanceWHEN-NEW-BLOCK-INSTANCE
	 *
	 * @param params
	 *
	 */
	public AgencyLocations qryBlkWhenNewBlockInstance(final AgencyLocations paramBean) {
		AgencyLocations returnObj;
		final String sql = getQuery("OIDBUTAB_QRY_BLK_WHENNEWBLOCKINSTANCE_WHENNEWBLOCKINSTANCE");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vhbPostQuery
	 *
	 * @param params
	 *
	 */
	public OffenderExternalMovements vhbPostQuery(final OffenderExternalMovements paramBean) {
		final String sql = getQuery("OIDBUTAB_VHB_POSTQUERY");
		final RowMapper<OffenderExternalMovements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offenderExternalMovementsMapping);
		OffenderExternalMovements returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidbutabKeyCommit
	 *
	 * @param params
	 *
	 */
	public OffenderExternalMovements oidbutabKeyCommit(final OffenderExternalMovements paramBean) {
		final String sql = getQuery("OIDBUTAB_OIDBUTAB_KEYCOMMIT");
		final RowMapper<OffenderExternalMovements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offenderExternalMovementsMapping);
		OffenderExternalMovements returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobalsCREATE_FORM_GLOBALS
	 *
	 * @param params
	 *
	 */
	public OmsModules createFormGlobalsCREATE_FORM_GLOBALS(final OmsModules paramBean) {
		OmsModules returnObj;
		final String sql = getQuery("OIDBUTAB_CREATE_FORM_GLOBALS_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * setOffIdDisplayPrompt
	 *
	 * @param params
	 *
	 */
	public SystemProfiles setOffIdDisplayPrompt(final SystemProfiles paramBean) {
		SystemProfiles returnObj;
		final String sql = getQuery("OIDBUTAB_SET_OFF_ID_DISPLAY_PROMPT");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * hasLaterMovement
	 *
	 * @param params
	 *
	 */
	public OffenderExternalMovements hasLaterMovement(final OffenderExternalMovements paramBean) {
		OffenderExternalMovements returnObj;
		final String sql = getQuery("OIDBUTAB_HAS_LATER_MOVEMENT");
		final RowMapper<OffenderExternalMovements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offenderExternalMovementsMapping);
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", paramBean.getOffenderBookId()), columnRowMapper);
		return returnObj;
	}

	public Integer updateOffenderBookings(final OffenderBookings offBookObj) {
		final String sql = getQuery("OIDBUTAB_OFFENDER_BOOKINGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		final OffenderBookings bean = new OffenderBookings();
		bean.setOffenderBookId(Long.valueOf(offBookObj.getOffenderBookId()));
		bean.setInOutStatus(offBookObj.getInOutStatus());
		parameters.add(new BeanPropertySqlParameterSource(bean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	public Integer updateOffenderExternalMovements(final OffenderBookings offBookObj) {
		final String sql = getQuery("UPDATE_OFFENDER_EXTERNAL_MOVEMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		final OffenderBookings bean = new OffenderBookings();
		bean.setOffenderBookId(Long.valueOf(offBookObj.getOffenderBookId()));
		bean.setActiveFlag(offBookObj.getActiveFlag());
		parameters.add(new BeanPropertySqlParameterSource(bean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	public Integer insertOffenderExternalMovements(final OffenderExternalMovements offExMovObj) {
		final String sql = getQuery("INSERT_OFFENDER_EXTERNAL_MOVEMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(offExMovObj));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertOffenderExternalMovements", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	public Integer insertOffenderExternalMovements(final List<OffenderExternalMovements> lstoffExMovResults) {
		final String sql = getQuery("INSERT_OFFENDER_EXTERNAL_MOVEMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderExternalMovements offExterMovgResults : lstoffExMovResults) {
			offExterMovgResults.setMovementSeq(4L);
			parameters.add(new BeanPropertySqlParameterSource(offExterMovgResults));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertOffenderExternalMovements", e);
		}
		if (lstoffExMovResults.size() == returnArray.length) {
			return returnArray.length;
		} else {
			return 0;
		}

	}

	public OffenderExternalMovements generateMovementSeq(final OffenderExternalMovements offExMovObj) {
		final String sql = getQuery("OIDBUTAB_GET_MOVEMENT_SEQ");
		final RowMapper<OffenderExternalMovements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offenderExternalMovementsMapping);
		OffenderExternalMovements returnList = new OffenderExternalMovements();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", offExMovObj.getOffenderBookId()), columnRowMapper);
		} catch (Exception e) {
			returnList = new OffenderExternalMovements();
		}
		return returnList;
	}

	public Integer whenValidateItem(final VNameSearch vnamesearch) {
		Integer movementSeq = null;
		String sql = getQuery("OCDBUTAB_WHENVALIDATEITEM_ONE");
		sql = sql + "and v_header_block.offender_id_display ='" + vnamesearch.getOffenderIdDisplay()
				+ "' and v_header_block.living_unit_id in (with recursive cte as ( select lu.living_unit_id from living_units "
				+ "lu where lu.living_unit_id =" + vnamesearch.getLivingUnitDescription() + "";
		sql = sql + getQuery("OCDBUTAB_WHENVALIDATEITEM_TWO");
		try {
			movementSeq = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderIdDisplay", vnamesearch.getOffenderIdDisplay(), "agyLocId",
							vnamesearch.getAgyLocId(), "livingUnitDesc", vnamesearch.getLivingUnitDescription(),"USERID",vnamesearch.getCreateUserId()),
					Integer.class);
		} catch (Exception e) {
			logger.error("whenValidateItem", e);
		}
		return movementSeq;
	}

	public OffenderExternalMovements getlastMoveAndLoc(final OffenderExternalMovements offExMovObj) {
		final String sql = getQuery("GET_LASTMOVEANDLOC");
		final RowMapper<OffenderExternalMovements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offenderExternalMovementsMapping);
		OffenderExternalMovements returnList = new OffenderExternalMovements();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",
					offExMovObj.getOffenderBookId(), "fromAgyLocId", offExMovObj.getFromAgyLocId()), columnRowMapper);
		} catch (Exception e) {
			returnList = new OffenderExternalMovements();
		}
		return returnList;
	}

	@Override
	public OffenderBookings getOldOffenderBookingsData(OffenderBookings bean) {
		final String sql = getQuery("GET_OLD_OFFENDER_BOOKINGS_DATA");
		OffenderBookings bean1 = new OffenderBookings();
		try {
			bean1 = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offender_book_id", bean.getOffenderBookId()),
					new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
		} catch (Exception e) {
			logger.error("getOldOffenderBookingsData", e);
		}
		return bean1;
	}

	@Override
	public List<OffenderExternalMovements> getOldDataOfExternalMovements(final Long offenderBookId) {
		final String sql = getQuery("OIDBUTAB_OLD_DATA_EXTERNAL_MOVEMENTS");
		final RowMapper<OffenderExternalMovements> movementReasonsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, movementReasonsMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("offnderBookId", offenderBookId),
					movementReasonsRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

}

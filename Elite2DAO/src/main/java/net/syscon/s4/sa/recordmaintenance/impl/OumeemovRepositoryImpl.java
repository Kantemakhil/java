package net.syscon.s4.sa.recordmaintenance.impl;

import java.sql.SQLException;
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
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.sa.recordmaintenance.OumeemovRepository;

/**
 * Class OumeemovRepositoryImpl
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
@Repository
public class OumeemovRepositoryImpl extends RepositoryBase implements OumeemovRepository {

	/**
	 * Creates new OumeemovRepositoryImpl class Object
	 */
	public OumeemovRepositoryImpl() {
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumeemovRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("FROM_CITY", new FieldMapper("fromCity"))
			.put("MOVEMENT_REASON_CODE", new FieldMapper("movementReasonCode"))
			.put("DESCRIPTION", new FieldMapper("description")).put("MODE", new FieldMapper("mode"))
			.put("DIRECTION_CODE", new FieldMapper("directionCode"))
			.put("MOVEMENT_TYPE", new FieldMapper("movementType")).put("TO_CITY", new FieldMapper("toCity"))
			.put("'1'", new FieldMapper("  '1' ")).build();
	private final Map<String, FieldMapper> movementReasonsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MOVEMENT_REASON_CODE", new FieldMapper("movementReasonCode"))
			.put("DESCRIPTION", new FieldMapper("description")).put("MOVEMENT_TYPE", new FieldMapper("movementType"))
			.build();
	private final Map<String, FieldMapper> mAgyLocIdMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_I", new FieldMapper("agyLocI")).build();
	private final Map<String, FieldMapper> offenderExternalMovementsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MAX(MOVEMENT_DATE)", new FieldMapper(" max(movementDate) "))
			.put("REPORTING_TIME", new FieldMapper("reportingTime")).put("TO_AGY_LOC_ID", new FieldMapper("toAgyLocId"))
			.put("TO_ADDRESS_ID", new FieldMapper("toAddressId")).put("ESCORT_CODE", new FieldMapper("escortCode"))
			.put("APPLICATION_TIME", new FieldMapper("applicationTime")).put("TO_CITY", new FieldMapper("toCity"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("TO_PROV_STAT_CODE", new FieldMapper("toProvStatCode")).put("'Y'", new FieldMapper(" 'y' "))
			.put("ESCORT_TEXT", new FieldMapper("escortText")).put("PARENT_EVENT_ID", new FieldMapper("parentEventId"))
			.put("0)+1", new FieldMapper("0)+1 ")).put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MOVEMENT_TYPE", new FieldMapper("movementType"))
			.put("TO_COUNTRY_CODE", new FieldMapper("toCountryCode"))
			.put("DIRECTION_CODE", new FieldMapper("directionCode"))

			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("REPORTING_DATE", new FieldMapper("reportingDate"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("MOVEMENT_SEQ", new FieldMapper("movementSeq"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("OJ_LOCATION_CODE", new FieldMapper("ojLocationCode"))
			.put("MAX(MOVEMENT_TIME)", new FieldMapper(" max(movementTime) "))
			.put("FROM_AGY_LOC_ID", new FieldMapper("fromAgyLocId"))
			.put("FROM_ADDRESS_ID", new FieldMapper("fromAddressId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("APPLICATION_DATE", new FieldMapper("applicationDate"))
			.put("MOVEMENT_TIME", new FieldMapper("movementTime"))
			.put("INTERNAL_SCHEDULE_TYPE", new FieldMapper("internalScheduleType"))
			.put("INTERNAL_SCHEDULE_REASON_CODE", new FieldMapper("internalScheduleReasonCode"))
			.put("EVENT_ID", new FieldMapper("eventId")).put("PROPOSED_MVMNT_SEQ", new FieldMapper("proposedMvmntSeq"))
			.put("FROM_CITY", new FieldMapper("fromCity")).put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("MOVEMENT_REASON_CODE", new FieldMapper("movementReasonCode"))
			.put("NVL(MAX(MOVEMENT_SEQ)", new FieldMapper(" nvl(max(movementSeq)"))
			.put("ARREST_AGENCY_LOC_ID", new FieldMapper("arrestAgencyLocId"))
			.put("MOVEMENT_DATE", new FieldMapper("movementDate")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("CODE", new FieldMapper("code"))
			.put("MOVEMENT_REASON_CODE", new FieldMapper("movementReasonCode"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FROM_AGY_LOC_ID", new FieldMapper("fromAgyLocId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("TO_AGY_LOC_ID", new FieldMapper("toAgyLocId")).build();

	
	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderExternalMovements
	 *
	 * @return List<OffenderExternalMovements>
	 *
	 * @throws SQLException
	 */
	public List<OffenderExternalMovements> offEmExecuteQuery(OffenderExternalMovements objSearchDao) {
		final String sql = getQuery("OUMEEMOV_OFFEM_FIND_OFFENDER_EXTERNAL_MOVEMENTS");
		final RowMapper<OffenderExternalMovements> OffenderExternalMovementsRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderExternalMovements.class, offenderExternalMovementsMapping);
		List<OffenderExternalMovements> returnList = new ArrayList<OffenderExternalMovements>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", objSearchDao.getOffenderBookId()),OffenderExternalMovementsRowMapper);
		return returnList;
	}


	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderExternalMovements List<OffenderExternalMovements>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offEmInsertOffenderExternalMovements(
			final OffenderExternalMovements obj) {
		String sql = getQuery("OUMEEMOV_OFFEM_INSERT_OFFENDER_EXTERNAL_MOVEMENTS");
		Integer returnArray = 0;
		try {

			returnArray = namedParameterJdbcTemplate.update(sql, createParams("offenderBookId",obj.getOffenderBookId(),"movementDate", obj.getMovementDate(),
																			   "movementTime",obj.getMovementTime(),"movementType",obj.getMovementType(),
																			   "movementReasonCode",obj.getMovementReasonCode(), "directionCode",obj.getDirectionCode(),
																			   "fromAgyLocId",obj.getFromAgyLocId(),"toAgyLocId",obj.getToAgyLocId(),"activeFlag",obj.getActiveFlag(),
																			   "commentText",obj.getCommentText(),"toCity",obj.getToCity(),"fromCity",obj.getFromCity(),
																			   "createUserId", obj.getCreateUserId()));
		} catch (Exception e) {
			logger.error("offEmInsertOffenderExternalMovements: ", e);
			returnArray = 0;
			
		}
		return returnArray;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderExternalMovements List<OffenderExternalMovements>
	 *
	 * @throws SQLException
	 */
	public Integer offEmUpdateOffenderExternalMovements(
			final List<OffenderExternalMovements> lstOffenderExternalMovements) {
		String sql = getQuery("OUMEEMOV_OFFEM_UPDATE_OFFENDER_EXTERNAL_MOVEMENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderExternalMovements offenderExternalMovements : lstOffenderExternalMovements) {
			parameters.add(new BeanPropertySqlParameterSource(offenderExternalMovements));
		}
		try {

			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offEmUpdateOffenderExternalMovements: ", e);
		}
		if (lstOffenderExternalMovements.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderExternalMovements List<OffenderExternalMovements>
	 *
	 * @throws SQLException
	 */
	public Integer offEmDeleteOffenderExternalMovements(
			final List<OffenderExternalMovements> lstOffenderExternalMovements) {
		String sql = getQuery("OUMEEMOV_OFFEM_DELETE_OFFENDER_EXTERNAL_MOVEMENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderExternalMovements offenderExternalMovements : lstOffenderExternalMovements) {
			parameters.add(new BeanPropertySqlParameterSource(offenderExternalMovements));
		}
		try {
			String tableName = "OFFENDER_EXTERNAL_MOVEMENTS";
			String whereClause = "OFFENDER_BOOK_ID= :offenderBookId AND MOVEMENT_SEQ = :movementSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offEmDeleteOffenderExternalMovements", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offEmDeleteOffenderExternalMovements: ", e);
		}
		if (lstOffenderExternalMovements.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MCityRefCode2.description>
	 */
	public List<ReferenceCodes> cgfkOffEmFromCityRecordGroup() {
		final String sql = getQuery("OUMEEMOV_FIND_CGFKOFFEMFROMCITY");
		final RowMapper<ReferenceCodes> rowmaper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), rowmaper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MAgyLocId>
	 */
	public List<AgencyLocations> cgfkOffEmFromAgyLocIdRecordGroup() {
		final String sql = getQuery("OUMEEMOV_FIND_CGFKOFFEMFROMAGYLOCID");
		final RowMapper<AgencyLocations> mAgyLocIdRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mAgyLocIdMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mAgyLocIdRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> cgfkOffEmToAgyLocIdRecordGroup() {
		final String sql = getQuery("OUMEEMOV_FIND_CGFKOFFEMTOAGYLOCID");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

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
	public List<ReferenceCodes> cgfkOffEmMovementTypeRecordGroup() {
		final String sql = getQuery("OUMEEMOV_FIND_CGFKOFFEMMOVEMENTTYPE");
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
	public List<ReferenceCodes> cgfkOffEmDirectionCodeRecordGroup() {
		final String sql = getQuery("OUMEEMOV_FIND_CGFKOFFEMDIRECTIONCODE");
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
	public List<MovementReasons> cgfkOffEmMovementReasonCoRecordGroup(String movementType) {
		final String sql = getQuery("OUMEEMOV_FIND_CGFKOFFEMMOVEMENTREASONCO");
		final RowMapper<MovementReasons> mRowMapper = Row2BeanRowMapper.makeMapping(sql, MovementReasons.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("MOVEMENT_TYPE", movementType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("cgfkOffEmMovementReasonCoRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkOffEmToCityRecordGroup() {
		final String sql = getQuery("OUMEEMOV_FIND_CGFKOFFEMTOCITY");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
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
	public Long offEmPreInsert(OffenderExternalMovements paramBean) {
		final String sql = getQuery("OUMEEMOV_OFF_EM_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", paramBean.getOffenderBookId()), Long.class);

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmRefFro
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEmOffEmRefFro(ReferenceCodes paramBean) {
		final String sql = getQuery("OUMEEMOV_CGFKCHK_OFF_EM_OFF_EM_REF_FRO");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmRefTo
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEmOffEmRefTo(ReferenceCodes paramBean) {
		final String sql = getQuery("OUMEEMOV_CGFKCHK_OFF_EM_OFF_EM_REF_TO_");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmRefMov
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEmOffEmRefMov(ReferenceCodes paramBean) {
		final String sql = getQuery("OUMEEMOV_CGFKCHK_OFF_EM_OFF_EM_REF_MOV");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmMoveRs
	 *
	 * @param params
	 *
	 */
	public MovementReasons cgfkchkOffEmOffEmMoveRs(MovementReasons paramBean) {
		final String sql = getQuery("OUMEEMOV_CGFKCHK_OFF_EM_OFF_EM_MOVE_RS");
		final RowMapper<MovementReasons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, MovementReasons.class,
				movementReasonsMapping);
		MovementReasons returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmDirecti
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEmOffEmDirecti(ReferenceCodes paramBean) {
		final String sql = getQuery("OUMEEMOV_CGFKCHK_OFF_EM_OFF_EM_DIRECTI");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmMoveRe
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffEmOffEmMoveRe(ReferenceCodes paramBean) {
		final String sql = getQuery("OUMEEMOV_CGFKCHK_OFF_EM_OFF_EM_MOVE_RE");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmAgyLoc
	 *
	 * @param params
	 *
	 */
	public AgencyLocations cgfkchkOffEmOffEmAgyLoc(AgencyLocations paramBean) {
		final String sql = getQuery("OUMEEMOV_CGFKCHK_OFF_EM_OFF_EM_AGY_LOC");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffEmOffEmAgy
	 *
	 * @param params
	 *
	 */
	public AgencyLocations cgfkchkOffEmOffEmAgy(AgencyLocations paramBean) {
		final String sql = getQuery("OUMEEMOV_CGFKCHK_OFF_EM_OFF_EM_AGY_2");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);
		AgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * validateMovementDatetime
	 *
	 * @param params
	 *
	 */
	public OffenderExternalMovements validateMovementDatetime(OffenderExternalMovements paramBean) {
		final String sql = getQuery("OUMEEMOV_VALIDATE_MOVEMENT_DATETIME");
		final RowMapper<OffenderExternalMovements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offenderExternalMovementsMapping);
		OffenderExternalMovements returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * validateMovementDate
	 *
	 * @param params
	 *
	 */
	public OffenderExternalMovements validateMovementDate(OffenderExternalMovements paramBean) {
		final String sql = getQuery("OUMEEMOV_VALIDATE_MOVEMENT_DATE");
		final RowMapper<OffenderExternalMovements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offenderExternalMovementsMapping);
		OffenderExternalMovements returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * validateSeqNum
	 *
	 * @param params
	 *
	 */
	public String validateSeqNum(OffenderExternalMovements paramBean) {
		final String sql = getQuery("OUMEEMOV_VALIDATE_SEQ_NUM");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);

	}
	
	public OffenderExternalMovements getMovementType(final Long offenderBookId, final Long movementSeq) {
		final String sql = getQuery("OUMEEMOV_GET_MOVEMENT_TYPE");
		OffenderExternalMovements resp = new OffenderExternalMovements();
		try {
			if(offenderBookId != null && movementSeq != null) {
			resp = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", offenderBookId,"movementSeq",movementSeq),
					new BeanPropertyRowMapper<OffenderExternalMovements>(OffenderExternalMovements.class));
			}
		} catch (Exception e) {
			logger.error("getOffenderExternalMovements :" + e);
		}
		return resp;	
	}
	
}

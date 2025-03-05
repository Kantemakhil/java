package net.syscon.s4.inst.movements.housingchanges.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.movements.beans.ReserveBedLocations;
import net.syscon.s4.inst.movements.housingchanges.OidrhlocRepository;
import oracle.jdbc.OracleTypes;
/**
 * Class OidrhlocRepositoryImpl
 */
@Repository
public class OidrhlocRepositoryImpl extends RepositoryBase implements OidrhlocRepository{
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OidrhlocRepositoryImpl.class.getName());

private final Map<String, FieldMapper> reserveBedLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("COMMENT_TEXT", 						new FieldMapper("commentText"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("RESERVE_UNTIL_DATE", 					new FieldMapper("reserveUntilDate"))
.put("REMOVE_REASON", 						new FieldMapper("removeReason"))
.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
.put("AGY_LOC_ID", 							new FieldMapper("agyLocId"))
.put("CREATE_DATETIME", 					new FieldMapper("createDateTime"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("LIVING_UNIT_ID", 						new FieldMapper("livingUnitId"))
.put("MODIFY_DATETIME", 					new FieldMapper("modifyDateTime"))
.put("RESERVE_BED_ID", 						new FieldMapper("reserveBedId"))
.put("OFFENDER_ID", 						new FieldMapper("offenderId"))
.build();
private final Map<String, FieldMapper> caseloadAgencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("AGY_LOC_ID", 							new FieldMapper("agyLocId"))
.put("UPDATE_ALLOWED_FLAG", 				new FieldMapper("updateAllowedFlag"))
.put("CASELOAD_ID", 						new FieldMapper("caseloadId"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> offendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("LAST_NAME", 				new FieldMapper("lastName"))
.put("FIRST_NAME", 				new FieldMapper("firstName"))
.put("OFFENDER_ID_DISPLAY", 	new FieldMapper("offenderIdDisplay"))
.put("ROOT_OFFENDER_ID", 		new FieldMapper("rootOffenderId"))
.put("OFFENDER_ID", 			new FieldMapper("offenderId"))
.build();
private final Map<String, FieldMapper> livingUnitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("AGY_LOC_ID",          new FieldMapper("agyLocId"))
.put("DESCRIPTION",         new FieldMapper("description"))
.put("LIVING_UNIT_ID",      new FieldMapper("livingUnitId"))
.build();
private final Map<String, FieldMapper> offenderBookingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OFFENDER_ID", 			new FieldMapper("offenderId"))
.put("OFFENDER_BOOK_ID", 		new FieldMapper("offenderBookId"))
.put("ACTIVE_FLAG", 			new FieldMapper("activeFlag"))
.put("LIVING_UNIT_ID", 			new FieldMapper("livingUnitId"))
.build();

	/**
	 * Creates new OidrhlocRepositoryImpl class Object
	 */
	public OidrhlocRepositoryImpl() {
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
		final String sql = getQuery("OIDRHLOC_RESBL_FIND_RESERVE_BED_LOCATIONS");
		final RowMapper<ReserveBedLocations> ReserveBedLocationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReserveBedLocations.class, reserveBedLocationsMapping);
		final ArrayList<ReserveBedLocations> returnList = (ArrayList<ReserveBedLocations>) namedParameterJdbcTemplate
				.query(sql, createParams("CASELOADID", objSearchDao.getAgyLocId()), ReserveBedLocationsRowMapper);
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
		final String sql = getQuery("OIDRHLOC_RESBL_INSERT_RESERVE_BED_LOCATIONS");
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
		final String sql = getQuery("OIDRHLOC_RESBL_UPDATE_RESERVE_BED_LOCATIONS");
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
		final String sql = getQuery("OIDRHLOC_RESBL_DELETE_RESERVE_BED_LOCATIONS");
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
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<CaseloadAgencyLocations> cgfkResBlAgyLocIdRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIDRHLOC_FIND_CGFKRESBLAGYLOCID");
		final RowMapper<CaseloadAgencyLocations> MRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAgencyLocations.class, caseloadAgencyLocationsMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseloadId), MRowMapper);
		} catch (Exception e) {
			logger.error("In method cgfkResBlAgyLocIdRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkResBlResBlCsldAl
	 *
	 * @param params
	 *
	 */
	public CaseloadAgencyLocations cgfkchkResBlResBlCsldAl(final CaseloadAgencyLocations paramBean) {
		final String sql = getQuery("OIDRHLOC_CGFKCHK_RES_BL_RES_BL_CSLD_AL");
		final RowMapper<CaseloadAgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAgencyLocations.class, caseloadAgencyLocationsMapping);
		final CaseloadAgencyLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getLivingUnitIddesc
	 *
	 * @param params
	 *
	 */
	public LivingUnits getLivingUnitIddesc(final LivingUnits searchBean) {
		final String sql = getQuery("OIDRHLOC_GET_LIVING_UNIT_DESC");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		final LivingUnits returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("LIVING_UNIT_ID", searchBean.getLivingUnitId()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getOffenderDetails
	 *
	 * @param params
	 *
	 */
	public Offenders getOffenderDetails(final Offenders searchBean) {
		final String sql = getQuery("OIDRHLOC_GET_OFFENDER_DETAILS");
		Offenders returnObj = new Offenders();
		final RowMapper<Offenders> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				offendersMapping);
		try {
			List<Offenders> returnList = namedParameterJdbcTemplate.query(sql,
					createParams("OFFENDER_ID", searchBean.getOffenderId()), columnRowMapper);
			if (returnList.size() > 0) {
				returnObj = returnList.get(0);
			}
		} catch (EmptyResultDataAccessException e) {
			return returnObj;
		} catch (Exception e) {
			logger.error("In method getOffenderDetails", e);
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * validateLivingUnitId
	 *
	 *
	 */
	public Integer validateLivingUnitId(final OffenderBookings searchBean) {
		final String sql = getQuery("OIDRHLOC_VALIDATE_LIV_UNIT_ID");
		Integer returnObj;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offender_book_id",
					searchBean.getOffenderBookId(), "living_unit_id", searchBean.getLivingUnitId()), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			returnObj = 0;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getOffenderBookId
	 *
	 *
	 */
	public Integer getOffenderBookId(final ReserveBedLocations searchBean) {
		final String sql = getQuery("OIDRHLOC_GET_OFFENDER_BOOK_ID");
		Integer returnObj;
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDER_ID", searchBean.getOffenderId(), "CASELOAD_ID", searchBean.getAgyLocId()),
				Integer.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkAllConflictsQuery
	 *
	 *
	 */
	@Override
	public ReserveBedLocations checkAllConflictsQuery(final ReserveBedLocations obj) {
		OffenderBookings bkgBean = new OffenderBookings();
		bkgBean = getBookingInfoCur(obj);
		Map<String, Object> returnObject = null;
		final ReserveBedLocations bean = new ReserveBedLocations();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[6];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER),
				new SqlParameter("P_LIVING_UNIT_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_WARNING_MSG", OracleTypes.VARCHAR),
				new SqlOutParameter("P_WARNING_PROMPT", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMUAVBED").withProcedureName("GET_CONFLICT_WARNING").declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", bkgBean.getOffenderBookId());
		inParamMap.put("P_OFFENDER_ID", 0);
		inParamMap.put("P_LIVING_UNIT_ID", obj.getLivingUnitId());
		inParamMap.put("P_WARNING_MSG", null);
		inParamMap.put("P_WARNING_PROMPT", null);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			bean.setWarningMsg(returnObject.get("P_WARNING_MSG") != null
					? String.valueOf(returnObject.get("P_WARNING_MSG")) : null);
			bean.setWarningPrompt(returnObject.get("P_WARNING_PROMPT") != null
					? String.valueOf(returnObject.get("P_WARNING_PROMPT")) : null);

		} catch (Exception e) {
			logger.error("In method checkAllConflictsQuery", e);
		}
		return bean;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getBookingInfoCur
	 *
	 * @param params
	 *
	 */
	public OffenderBookings getBookingInfoCur(final OffenderBookings searchBean) {
		OffenderBookings returnObj = new OffenderBookings();
		final String sql = getQuery("OIDRHLOC_GET_CB_FLAG");
		final RowMapper<OffenderBookings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offenderBookingsMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("ROOT_OFFENDER_ID",
					searchBean.getRootOffenderId(), "CASELOAD_ID", searchBean.getAgyLocId()), columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			returnObj = new OffenderBookings();
		}
		return returnObj;

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getBookingInfoCur
	 *
	 * @param params
	 *
	 */
	public LivingUnits getBookingInfoCur(final String rootOffenderId, final String caelosdId) {
		LivingUnits returnObj = new LivingUnits();
		final String sql = getQuery("OIDRHLOC_GET_CB_FLAG");
		final RowMapper<LivingUnits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				livingUnitsMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("ROOT_OFFENDER_ID",Long.valueOf( rootOffenderId), "CASELOAD_ID", caelosdId), columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			returnObj = new LivingUnits();
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getOccCountCur
	 *
	 *
	 */
	public Integer getOccCountCur(final ReserveBedLocations searchBean) {
		final String sql = getQuery("OIDRHLOC_GET_V_CAPACITY");
		Integer returnObj = 0;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("LIVING_UNIT_ID", searchBean.getLivingUnitId()), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			return returnObj;
		}
		return returnObj;
	}


	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getVcountCur
	 *
	 *
	 */
	public Integer getVcountCur(final ReserveBedLocations searchBean) {
		final String sql = getQuery("OIDRHLOC_GET_OCC_COUNT_CUR");
		Integer returnObj;
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("LIVING_UNIT_ID", searchBean.getLivingUnitId()), Integer.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getVcountCur
	 *
	 *
	 */
	public OffenderBookings getBookingInfoCur(final ReserveBedLocations searchBean) {
		final String sql = getQuery("OIDRHLOC_GET_BOOKING_INFO_CUR");
		OffenderBookings returnObj = new OffenderBookings();
		final RowMapper<OffenderBookings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class,
				offenderBookingsMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("root_offender_id",
					searchBean.getOffenderId(), "caseload_id", searchBean.getAgyLocId()), columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return returnObj;
		}
		return returnObj;
	}

}

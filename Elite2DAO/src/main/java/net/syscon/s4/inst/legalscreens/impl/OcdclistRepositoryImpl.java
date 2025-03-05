package net.syscon.s4.inst.legalscreens.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.legalscreens.OcdclistRepository;
import net.syscon.s4.inst.legalscreens.bean.OcdclistCourtListQuery;
import net.syscon.s4.inst.legalscreens.bean.VCourtEvents;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import oracle.jdbc.OracleTypes;

/**
 * Class OcdclistRepositoryImpl
 */
@Repository
public class OcdclistRepositoryImpl extends RepositoryBase implements OcdclistRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdclistRepositoryImpl.class.getName());

private final Map<String, FieldMapper> vCourtEventsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
.put("MOVEMENT_REASON_CODE", 			new FieldMapper("movementReasonCode"))
.put("LEVEL_3_CODE", 					new FieldMapper("level3Code"))
.put("COURT_EVENT_TYPE_DESC", 			new FieldMapper("courtEventTypeDesc"))
.put("END_TIME", 						new FieldMapper("endTime"))
.put("EVENT_CLASS", 					new FieldMapper("eventClass"))
.put("BIRTH_DATE", 						new FieldMapper("birthDate"))
.put("FIRST_NAME", 						new FieldMapper("firstName"))
.put("CASE_ID", 						new FieldMapper("caseId"))
.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))


.build();
private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("SEQVALUE", 						    new FieldMapper("seqValue"))
.build();
private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("PROFILE_VALUE_2", 						new FieldMapper(" profileValue2 "))
.build();
private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("TRN_FROM_AGY_LOC_ID",  new FieldMapper("trnFromAgyLocId"))
.put("AGY_LOC_ID", 			 new FieldMapper("agyLocId"))
.put("TRN_TO_AGY_LOC_ID", 	 new FieldMapper("trnToAgyLocId"))
.put("DESCRIPTION", 		 new FieldMapper("description"))
.put("CODE", 				 new FieldMapper("code"))
.build();

	/**
	 * Creates new OcdclistRepositoryImpl class Object
	 */
	public OcdclistRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OcdclistCourtListQuery
	 *
	 * @return List<OcdclistCourtListQuery>
	 *
	 * @
	 */
	public List<OcdclistCourtListQuery> ctlLstExecuteQuery(final OcdclistCourtListQuery objSearchDao) {
		Map<String, Object> returnObject = null;
		List<OcdclistCourtListQuery> lListObj = new ArrayList<OcdclistCourtListQuery>();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[17];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("COURT_LIST_DATA", OracleTypes.CURSOR),
				new SqlParameter("P_COURT_EVENT_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_CHECK_SUM", OracleTypes.NUMBER), new SqlParameter("P_BIRTH_DATE", OracleTypes.DATE),
				new SqlParameter("P_CASE_INFO_PREFIX", OracleTypes.VARCHAR),
				new SqlParameter("P_CASE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_MIDDLE_NAME", OracleTypes.VARCHAR),
				new SqlParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_START_TIME", OracleTypes.DATE),
				new SqlParameter("P_LAST_NAME", OracleTypes.VARCHAR),
				new SqlParameter("P_CASE_INFO_NUMBER", OracleTypes.VARCHAR),
				new SqlParameter("P_START_DATE", OracleTypes.DATE), new SqlParameter("P_COURT_DATE", OracleTypes.DATE),
				new SqlParameter("P_EVENT_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFF_BKG_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFF_DISPLAY", OracleTypes.VARCHAR),
				new SqlParameter("P_FIRST_NAME", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OCDCLIST").withProcedureName("COURT_LIST_QUERY").declareParameters(sqlParameters);
		inParamMap.put("COURT_LIST_DATA", OracleTypes.CURSOR);
		inParamMap.put("P_LAST_NAME", objSearchDao.getpLastName());
		inParamMap.put("P_FIRST_NAME", objSearchDao.getpFirstName());
		inParamMap.put("P_MIDDLE_NAME", objSearchDao.getpMiddleName());
		inParamMap.put("P_BIRTH_DATE", objSearchDao.getpBirthDate());
		inParamMap.put("P_OFF_DISPLAY", objSearchDao.getpOffDisplay());
		inParamMap.put("P_START_TIME", objSearchDao.getpStartTime());
		inParamMap.put("P_START_DATE", objSearchDao.getpStartDate());
		inParamMap.put("P_CASE_INFO_PREFIX", objSearchDao.getpCaseInfoprefix());
		inParamMap.put("P_CASE_INFO_NUMBER", objSearchDao.getpCaseInfoNumber());
		inParamMap.put("P_COURT_EVENT_TYPE", objSearchDao.getpCourtEventType());
		inParamMap.put("P_COURT_EVENT_TYPE_DESC", objSearchDao.getpCourtEventTypeDesc());
		inParamMap.put("P_COURT_DATE", objSearchDao.getpCourtDate());
		inParamMap.put("P_AGY_LOC_ID", objSearchDao.getpAgyLocId());
		inParamMap.put("P_OFF_BKG_ID", objSearchDao.getpOffBkgId());
		inParamMap.put("P_EVENT_ID", objSearchDao.getpEventId());
		inParamMap.put("P_CASE_ID", objSearchDao.getpCaseId());
		inParamMap.put("P_CHECK_SUM", objSearchDao.getpCheckSum());

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			returnObject = (Map<String, Object>) simpleJDBCCall.withoutProcedureColumnMetaDataAccess()
					.execute(inParameter);
			lListObj = new ArrayList<>();
			final List<Map<String, String>> list = (List<Map<String, String>>) returnObject.get("COURT_LIST_DATA");
			for (int i = 0; i < list.size(); i++) {
				final Map<String, String> childMap = list.get(i);
				final OcdclistCourtListQuery bean = new OcdclistCourtListQuery();
				final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				Date date;
				bean.setpEventId(Integer.parseInt(String.valueOf(childMap.get("EVENT_ID"))));
				bean.setpLastName(childMap.get("LAST_NAME"));
				bean.setpFirstName(childMap.get("FIRST_NAME"));
				bean.setpMiddleName(childMap.get("MIDDLE_NAME"));
				if (childMap.get("BIRTH_DATE") != null) {
					date = format.parse(String.valueOf(childMap.get("BIRTH_DATE")));
					bean.setpBirthDate(date);
				}
				bean.setpOffDisplay(childMap.get("OFFENDER_ID_DISPLAY"));
				if (childMap.get("START_TIME") != null) {
					date = format.parse(String.valueOf(childMap.get("START_TIME")));
					bean.setpStartTime(date);
				}
				bean.setpCaseInfoprefix(childMap.get("CASE_INFO_PREFIX"));
				bean.setpCaseInfoNumber(childMap.get("CASE_INFO_NUMBER"));
				bean.setpCourtEventTypeDesc(childMap.get("COURT_EVENT_TYPE_DESC"));
				bean.setpCourtEventType(childMap.get("COURT_EVENT_TYPE"));
				if (childMap.get("CASE_ID") != null) {
					bean.setpCaseId(Integer.parseInt(String.valueOf(childMap.get("CASE_ID"))));
				}
				bean.setpOffBkgId(Integer.parseInt(String.valueOf(childMap.get("OFFENDER_BOOK_ID"))));
				lListObj.add(bean);
			}
		} catch (Exception e) {
			logger.error("In method ctlLstExecuteQuery", e);
		}
		return lListObj;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOcdclistCourtListQuery
	 *            List<OcdclistCourtListQuery>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer ctlLstInsertOcdclistCourtListQuery(final List<OcdclistCourtListQuery> list) {
		final String sql = getQuery("OCDCLIST_CTLLST_INSERT_OCDCLIST.COURT_LIST_QUERY");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OcdclistCourtListQuery obj : list) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOcdclistCourtListQuery
	 *            List<OcdclistCourtListQuery>
	 *
	 * @
	 */
	public Integer ctlLstUpdateOcdclistCourtListQuery(final List<OcdclistCourtListQuery> list) {
		final String sql = getQuery("OCDCLIST_CTLLST_UPDATE_COURT_LIST_QUERY");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OcdclistCourtListQuery obj : list) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VCourtEvents
	 *
	 * @return List<VCourtEvents>
	 *
	 * @
	 */
	public List<VCourtEvents> ctlUnExecuteQuery(final VCourtEvents objSearchDao) {
		final String sql = getQuery("OCDCLIST_CTLUN_FIND_V_COURT_EVENTS");
		final RowMapper<VCourtEvents> vcourtEvetsMapper = Row2BeanRowMapper.makeMapping(sql, VCourtEvents.class,
				vCourtEventsMapping);
		final ArrayList<VCourtEvents> returnList = (ArrayList<VCourtEvents>) namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId()), vcourtEvetsMapper);
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
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> rgAgyLocRecordGroup() {
		final String sql = getQuery("OCDCLIST_FIND_RGAGYLOC");
		final RowMapper<AgencyLocations> agyLocRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agencyLocationsMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), agyLocRowMapper);
		} catch (Exception e) {
			logger.error("In method rgAgyLocRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgHearingRecordGroup() {
		final String sql = getQuery("OCDCLIST_FIND_RGHEARING");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("MODE", "ENTER-QUERY"), mRowMapper);
		} catch (Exception e) {
			logger.error("In method rgHearingRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * ctlLstOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<VCourtEvents> ctlLstOnCheckDeleteMaster(final VCourtEvents paramBean) {
		final String sql = getQuery("OCDCLIST_CTL_LST_ONCHECKDELETEMASTER");
		final RowMapper<VCourtEvents> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VCourtEvents.class,
				vCourtEventsMapping);
		final ArrayList<VCourtEvents> returnList = (ArrayList<VCourtEvents>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getProfileValue
	 *
	 * @param params
	 *
	 */
	public SystemProfiles getProfileValue(final SystemProfiles paramBean) {
		final String sql = getQuery("OCDCLIST_GET_PROFILE_VALUE_2");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		final SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * AgencyIncidentPreInsertcDAO
	 *
	 *
	 */
	public String courtEventExistsFunction(final OcdclistCourtListQuery courtListBean) {
		String value = null;
		try {
			final Map<String, Object> inParamMap = new HashMap<String, Object>();
			SqlParameter[] sqlParameters = new SqlParameter[4];
			sqlParameters = new SqlParameter[] { new SqlParameter("P_OFF_BKG_ID", OracleTypes.NUMBER),
					new SqlParameter("P_EVENT_DATE", OracleTypes.DATE),
					new SqlParameter("P_START_TIME", OracleTypes.NUMBER),
					new SqlParameter("P_AGY_LOC_ID", OracleTypes.VARCHAR),
					new SqlOutParameter("RETURN_VALUE", OracleTypes.BOOLEAN) };
			SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
					.withCatalogName("TAG_SCHEDULE").withFunctionName("CHECK_SCHEDULE_CONFLICT")
					.declareParameters(sqlParameters);
			inParamMap.put("P_OFF_BKG_ID", courtListBean.getpOffBkgId());
			inParamMap.put("P_EVENT_DATE", new Date());
			inParamMap.put("P_EVENT_ID", courtListBean.getpStartTime());
			inParamMap.put("P_AGY_LOC_ID", courtListBean.getpAgyLocId());
			final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
			value = simpleJDBCCall.executeFunction(String.class, inParameter);
		} catch (Exception e) {
			logger.error("In Method courtEventExistsFunction", e);
		}
		return value;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOcdclistCourtListQuery
	 *            List<OcdclistCourtListQuery>
	 *
	 * @
	 */
	public Integer ctlLstUpdateCourtListQuery(final List<CourtEvents> list) {
		final String sql = getQuery("OCDCLIST_CTLLST_UPDATE_OCDCLIST.COURT_LIST_QUERY");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourtEvents obj : list) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
}

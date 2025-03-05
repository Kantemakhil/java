package net.syscon.s4.inst.visitsmanagement.maintenance.impl;

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
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;
import net.syscon.s4.inst.visitsmanagement.beans.VisitCycleLimits;
import net.syscon.s4.inst.visitsmanagement.beans.VisitTypeLimits;
import net.syscon.s4.inst.visitsmanagement.maintenance.OimvlimtRepository;

/**
 * Class OimvlimtRepositoryImpl
 * 
 */
@Repository
public class OimvlimtRepositoryImpl extends RepositoryBase implements OimvlimtRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimvlimtRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> visitTypeLimitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("VISIT_CYCLE_LIMIT_ID", new FieldMapper("visitCycleLimitId"))
			.put("REINSTATE_FLAG", new FieldMapper("reinstateFlag"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MAX_HRS_TYPE", new FieldMapper("maxHrsType")).put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("MAX_VISITS_TYPE", new FieldMapper("maxVisitsType")).build();
	private final Map<String, FieldMapper> visitCycleLimitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("VISIT_CYCLE_LIMIT_ID", new FieldMapper("visitCycleLimitId"))
			.put("TOT_VISITS", new FieldMapper("totVisits")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("SEC_LEVEL", new FieldMapper("secLevel"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("NBT_AGY_ID", new FieldMapper("nbtAgyId"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime")).put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("TOT_HRS", new FieldMapper("totHrs")).put("CYCLE_TYPE", new FieldMapper("cycleType"))
			.put("iep_level", new FieldMapper("iepLevel")).put("iep_level_config", new FieldMapper("iepLevelConfig"))
			.put("security_level_config", new FieldMapper("securityLevelConfig"))
			.put("iep_level_description", new FieldMapper("iepLeveldescription"))
			.put("next_review_date", new FieldMapper("nextReviewDate"))
			.put("visit_config_type_value", new FieldMapper("visitConfigTypeValue"))
			.put("visitCount", new FieldMapper("visitCount"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();
	private final Map<String, FieldMapper> iepLeveRowMapper = new ImmutableMap.Builder<String, FieldMapper>()
			.put("IEP_LEVEL_CODE", new FieldMapper("iepLevelCode"))
			.put("IEP_LEVEL_DESCRIPTION", new FieldMapper("iepLeveldescription"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.build();

	/**
	 * Creates new OimvlimtRepositoryImpl class Object
	 */
	public OimvlimtRepositoryImpl() {
		// OimvlimtRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VisitCycleLimits
	 *
	 * @return List<VisitCycleLimits>
	 */
	public List<VisitCycleLimits> visCycExecuteQuery(final VisitCycleLimits objSearchDao) {
		final String sql = getQuery("OIMVLIMT_VISCYC_FIND_VISIT_CYCLE_LIMITS");
		final RowMapper<VisitCycleLimits> VisitCycleLimitsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VisitCycleLimits.class, visitCycleLimitsMapping);
		List<VisitCycleLimits> returnList = new ArrayList<VisitCycleLimits>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("agylocid", objSearchDao.getAgyLocId(), "visitConfigType", objSearchDao.getVisitConfigType()),
					VisitCycleLimitsRowMapper);
		} catch (Exception e) {
			logger.error("visCycExecuteQuery", e);

		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstVisitCycleLimits List<VisitCycleLimits>
	 *
	 * @return List<Integer>
	 */
	public Integer visCycInsertVisitCycleLimits(final List<VisitCycleLimits> lstVisitCycleLimits) {
		final String sql = getQuery("OIMVLIMT_VISCYC_INSERT_VISIT_CYCLE_LIMITS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VisitCycleLimits visitCycleLimits : lstVisitCycleLimits) {
			parameters.add(new BeanPropertySqlParameterSource(visitCycleLimits));
		}
		try {

			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {

			logger.error("visCycInsertVisitCycleLimits", e);

		}
		if (lstVisitCycleLimits.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVisitCycleLimits List<VisitCycleLimits>
	 */
	public Integer visCycUpdateVisitCycleLimits(final List<VisitCycleLimits> lstVisitCycleLimits) {
		final String sql = getQuery("OIMVLIMT_VISCYC_UPDATE_VISIT_CYCLE_LIMITS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VisitCycleLimits visitCycleLimits : lstVisitCycleLimits) {
			parameters.add(new BeanPropertySqlParameterSource(visitCycleLimits));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVisitCycleLimits.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VisitTypeLimits
	 *
	 * @return List<VisitTypeLimits>
	 */
	public List<VisitTypeLimits> visTypExecuteQuery(final VisitTypeLimits objSearchDao) {
		final String sql = getQuery("OIMVLIMT_VISTYP_FIND_VISIT_TYPE_LIMITS");
		final RowMapper<VisitTypeLimits> VisitTypeLimitsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VisitTypeLimits.class, visitTypeLimitsMapping);
		List<VisitTypeLimits> returnList = new ArrayList<VisitTypeLimits>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("visitcyclelimitid",
					objSearchDao.getVisitCycleLimitId(), "visit_type", objSearchDao.getVisitType()),
					VisitTypeLimitsRowMapper);
		} catch (Exception e) {
			logger.error("visTypExecuteQuery", e);

		}

		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstVisitTypeLimits List<VisitTypeLimits>
	 *
	 * @return List<Integer>
	 */
	public Integer visTypInsertVisitTypeLimits(final List<VisitTypeLimits> lstVisitTypeLimits) {
		final String sql = getQuery("OIMVLIMT_VISTYP_INSERT_VISIT_TYPE_LIMITS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VisitTypeLimits visitTypeLimits : lstVisitTypeLimits) {
			parameters.add(new BeanPropertySqlParameterSource(visitTypeLimits));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVisitTypeLimits.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVisitTypeLimits List<VisitTypeLimits>
	 */
	public Integer visTypUpdateVisitTypeLimits(final List<VisitTypeLimits> lstVisitTypeLimits) {
		final String sql = getQuery("OIMVLIMT_VISTYP_UPDATE_VISIT_TYPE_LIMITS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VisitTypeLimits visitTypeLimits : lstVisitTypeLimits) {
			parameters.add(new BeanPropertySqlParameterSource(visitTypeLimits));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVisitTypeLimits.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgSecLvlRecordGroup() {
		final String sql = getQuery("OIMVLIMT_FIND_RGSECLVL");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgSecLvlRecordGroup", e);
			;
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<OIMVLIMT_FIND_RGCYCTYP>
	 */
	public List<ReferenceCodes> rgCycTypRecordGroup() {
		final String sql = getQuery("OIMVLIMT_FIND_RGCYCTYP");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgCycTypRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgVisTypRecordGroup() {
		final String sql = getQuery("OIMVLIMT_FIND_RGVISTYP");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgVisTypRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<OIMVLIMT_FIND_RGSTRDAY>
	 */
	public List<ReferenceCodes> rgStrDayRecordGroup() {
		final String sql = getQuery("OIMVLIMT_FIND_RGSTRDAY");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgStrDayRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgAgyIntLocRecordGroup(String userName) {
		final String sql = getQuery("OIMVLIMT_FIND_RGAGYINTLOC");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("p_User_Id", userName), mRowMapper);
		} catch (Exception e) {
			logger.error("rgAgyIntLocRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * visCycPreInsert
	 *
	 * @param params
	 *
	 */
	public Long visCycPreInsert(final VisitCycleLimits paramBean) {
		final String sql = getQuery("OIMVLIMT_VIS_CYC_PREINSERT_SEQ");
		Long returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			logger.error("visCycPreInsert", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * visCycOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<VisitTypeLimits> visCycOnCheckDeleteMaster(final VisitTypeLimits paramBean) {
		final String sql = getQuery("OIMVLIMT_VIS_CYC_ONCHECKDELETEMASTER");
		final RowMapper<VisitTypeLimits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VisitTypeLimits.class,
				visitTypeLimitsMapping);
		final List<VisitTypeLimits> returnObj = new ArrayList<VisitTypeLimits>();
		try {
			namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("visCycOnCheckDeleteMaster", e);
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * visCycPreUpdate
	 *
	 * @param params
	 *
	 */
	public VisitCycleLimits visCycPreUpdate(final VisitCycleLimits paramBean) {
		final String sql = getQuery("OIMVLIMT_VIS_CYC_PREUPDATE");
		final RowMapper<VisitCycleLimits> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VisitCycleLimits.class,
				visitCycleLimitsMapping);
		VisitCycleLimits returnObj = new VisitCycleLimits();
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * visCycPreInsert
	 *
	 * @param params
	 *
	 */
	public Long visitCycleLimitId() {
		final String sql = getQuery("OIMVLIMT_VISIT_CYCLE_LIMIT_ID");
		Long returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			logger.error("visCycPreInsert", e);
		}
		return returnList;
	}

	@Override
	public List<IepLevelBean> rgIepLevelRecordGroup() {
		String sql = getQuery("OIMVLIMT_VIS_RG_IEP_LEVEL");
		final RowMapper<IepLevelBean> mMMRowMapper = Row2BeanRowMapper.makeMapping(sql, IepLevelBean.class,
				iepLeveRowMapper);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mMMRowMapper);
		} catch (Exception e) {
			logger.error("In method rgAccreditedProgramsRecordGroup", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<VisitCycleLimits> iepLevelExcuteQuery(VisitCycleLimits searchBean) {
		final String sql = getQuery("OIMVLIMT_VISCYC_FIND_IEP_LEVEL");
		final RowMapper<VisitCycleLimits> VisitCycleLimitsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VisitCycleLimits.class, visitCycleLimitsMapping);
		List<VisitCycleLimits> returnList = new ArrayList<VisitCycleLimits>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("agylocid", searchBean.getAgyLocId(),"visitConfigType",searchBean.getVisitConfigType()),
					VisitCycleLimitsRowMapper);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + "In visCycExecuteQuery method : ", e);
		}
		return returnList;
	}

	@Override
	public List<IepLevelBean> getIEPDetails(Long offenderBookId) {
		final String sql = getQuery("GET_IEP_LEVEL_DETAILS");
		List<IepLevelBean> returnList = new ArrayList<IepLevelBean>();

		final RowMapper<IepLevelBean> VisitCycleLimitsRowMapper = Row2BeanRowMapper.makeMapping(sql, IepLevelBean.class,
				visitCycleLimitsMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId",offenderBookId), VisitCycleLimitsRowMapper);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + "In getIEPDetails method : ", e);
		}
		return returnList;

	}

	@Override
	public Integer saveIepSecData(String facility, String iepSecLevel, String user, String operaion) {
		String sql = null;
		if (ApplicationConstants.UPDATE.equals(operaion)) {
			sql = getQuery("OIMVLIMT_UPDATE_IEP_SEC_DATA");
		} else {
			sql = getQuery("OIMVLIMT_SAVE_IEP_SEC_DATA");
		}
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("agyLocId", facility);
		map.addValue("visitConfigType", iepSecLevel);//agencyVisitConfig visitConfigType
		map.addValue("createUserId", user);
		map.addValue("modifyUserId", user);
		try {
			return namedParameterJdbcTemplate.update(sql, map);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + "In saveIepSecData method : ", e);
			return 0;
		}
	}

	@Override
	public VisitCycleLimits getIepVisitLimis(String agyLocId) {
		String sql = getQuery("OIMVLIMT_GET_IEP_SEC_DATA");
		MapSqlParameterSource map = new MapSqlParameterSource();
		VisitCycleLimits limit = new VisitCycleLimits();
		sql = sql + " where agy_loc_id =:agyLocId";
		map.addValue("agyLocId", agyLocId);
		try {
			limit = namedParameterJdbcTemplate.queryForObject(sql, map,
					new BeanPropertyRowMapper<VisitCycleLimits>(VisitCycleLimits.class));
		} catch (EmptyResultDataAccessException e) {
			limit.setAgencyVisitConfig(ApplicationConstants.EMPTYDATA);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + "In getIepVisitLimis method : ", e);
		}
		return limit;
	}

	@Override
	public List<ReferenceCodes> getIepSecLov() {
		final String sql = getQuery("OIMVLIMT_GET_LOV");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("getIepSecLov", e);
		}
		return returnList;
	}

}

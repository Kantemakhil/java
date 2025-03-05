package net.syscon.s4.inst.careinplacement.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.careinplacement.OidciponRepository;
import net.syscon.s4.inst.careinplacement.beans.OffenderCipDetails;
import net.syscon.s4.inst.careinplacement.beans.PlacementDurations;

/**
 * Class OidciponRepositoryImpl
 */
@Repository
public class OidciponRepositoryImpl extends RepositoryBase implements OidciponRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidciponRepositoryImpl.class.getName());

	private static final String MODE = "QUERY";

	private final Map<String, FieldMapper> offCipDetMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DURATION_TYPE", new FieldMapper("durationType")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("AUTH_BY_PER_NAME", new FieldMapper("authByPerName"))
			.put("REQ_BY_PER_CODE", new FieldMapper("reqByPerCode"))
			.put("EFFECTIVE_TIME", new FieldMapper("effectiveTime")).put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.build();
	private final Map<String, FieldMapper> refCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("MINIMUM_DURATION", new FieldMapper(" minimumDuration"))
			.put("DURATION_TYPE", new FieldMapper("durationType"))
			.put("AUTH_BY_PER_CODE", new FieldMapper("authByPerCode"))
			.put("MAXIMUM_DURATION", new FieldMapper(" maximumDuration "))
			.put("PLACEMENT_TYPE", new FieldMapper("placementType"))
			.put("REL_BY_PER_CODE", new FieldMapper("relByPerCode"))
			.put("PLACEMENT_REASON_CODE", new FieldMapper("placementReasonCode"))
			.put("REQ_BY_PER_CODE", new FieldMapper("reqByPerCode")).build();
	private final Map<String, FieldMapper> offExtMovMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("HH:MI(AM)'", new FieldMapper("hh:mi(am)'")).put("DESCRIPTION", new FieldMapper("description"))
			.put("MOVEMENT_TIME", new FieldMapper("movementTime")).put("MO", new FieldMapper("mo"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("TIME_STRING", new FieldMapper("timeString")).put("DSP_DESCRIPTION", new FieldMapper("dspDescription"))
			.build();
	private final Map<String, FieldMapper> placeDurMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("MINIMUM_DURATION", new FieldMapper("minimumDuration"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("DURATION_TYPE", new FieldMapper("durationType"))
			.put("AUTH_BY_PER_CODE", new FieldMapper("authByPerCode"))
			.put("MAXIMUM_DURATION", new FieldMapper("maximumDuration"))
			.put("PLACEMENT_TYPE", new FieldMapper("placementType"))
			.put("REL_BY_PER_CODE", new FieldMapper("relByPerCode"))
			.put("PLACEMENT_REASON_CODE", new FieldMapper("placementReasonCode"))
			.put("REQ_BY_PER_CODE", new FieldMapper("reqByPerCode")).build();
	private final Map<String, FieldMapper> sysProMapping = new ImmutableMap.Builder<String, FieldMapper>()
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
			.put("DURATION_TYPE", new FieldMapper("durationType")).put("CODE", new FieldMapper("code"))
			.put("TO_CHAR(MINIMUM_DURATION", new FieldMapper("minimumDuration")).build();
	private final Map<String, FieldMapper> agyLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("MINIMUM_DURATION", new FieldMapper("minimumDuration"))
			.put("DESCRIPTION", new FieldMapper("description")).put("DURATION_TYPE", new FieldMapper("durationType"))
			.put("AUTH_BY_PER_CODE", new FieldMapper("authByPerCode"))
			.put("MAXIMUM_DURATION", new FieldMapper("maximumDuration"))
			.put("PLACEMENT_TYPE", new FieldMapper("placementType"))
			.put("REL_BY_PER_CODE", new FieldMapper("relByPerCode"))
			.put("PLACEMENT_REASON_CODE", new FieldMapper("placementReasonCode"))
			.put("REQ_BY_PER_CODE", new FieldMapper("reqByPerCode")).build();
	private final Map<String, FieldMapper> clAgyLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_Id", new FieldMapper("agyLocId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).build();

	/**
	 * Creates new OidciponRepositoryImpl class Object
	 */
	public OidciponRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderCipDetails
	 *
	 * @return List<OffenderCipDetails>
	 *
	 */
	public List<OffenderCipDetails> offCipDetailsExecuteQuery(final OffenderCipDetails objSearchDao) {
		final String sql = getQuery("OIDCIPON_OFFCIPDETAILS_FIND_OFFENDER_CIP_DETAILS");
		final RowMapper<OffenderCipDetails> offCipDetRM = Row2BeanRowMapper.makeMapping(sql, OffenderCipDetails.class,
				offCipDetMapping);
		List<OffenderCipDetails> lstReturn = new ArrayList<OffenderCipDetails>();
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getOffenderBookId() != null) {
				sqlQuery.append("OFFENDER_BOOK_ID =  :offenderBookId ");
				valuesList.addValue("offenderBookId", objSearchDao.getOffenderBookId());
			}
			if (objSearchDao.getPlacementSeq() > 0) {
				sqlQuery.append(" and PLACEMENT_SEQ =  :placementSeq");
				valuesList.addValue("placementSeq", objSearchDao.getPlacementSeq());
			}
			sqlQuery.append(" ORDER BY EFFECTIVE_DATE DESC,EFFECTIVE_TIME DESC ");

			preparedSql = sqlQuery.toString().trim();
			lstReturn = (List<OffenderCipDetails>) namedParameterJdbcTemplate.query(preparedSql, valuesList,
					offCipDetRM);
		}
		return lstReturn;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffCipDet List<OffenderCipDetails>
	 *
	 * @return Integer
	 *
	 */
	public Integer offCipDetailsInsertOffenderCipDetails(final List<OffenderCipDetails> lstOffCipDet) {
		int returnValue = 0;
		final String sql = getQuery("OIDCIPON_OFFCIPDETAILS_INSERT_OFFENDER_CIP_DETAILS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderCipDetails offCipDetails : lstOffCipDet) {
			parameters.add(new BeanPropertySqlParameterSource(offCipDetails));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (lstOffCipDet.size() == returnArray.length) {
				returnValue = 1;
			}
		} catch (Exception e) {
			logger.error("error :" + e);
		}

		return returnValue;

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffCipDet List<OffenderCipDetails>
	 * 
	 * @return Integer
	 *
	 */
	public Integer offCipDetailsUpdateOffenderCipDetails(final List<OffenderCipDetails> lstOffCipDet) {
		final String sql = getQuery("OIDCIPON_OFFCIPDETAILS_UPDATE_OFFENDER_CIP_DETAILS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderCipDetails offCipDetails : lstOffCipDet) {
			parameters.add(new BeanPropertySqlParameterSource(offCipDetails));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffCipDet.size() == returnArray.length) {
			returnValue = 1;
		}

		return returnValue;

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffCipDet List<OffenderCipDetails>
	 *
	 */
	public Integer offCipDetailsDeleteOffenderCipDetails(final List<OffenderCipDetails> lstOffCipDet) {
		final String sql = getQuery("OIDCIPON_OFFCIPDETAILS_DELETE_OFFENDER_CIP_DETAILS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderCipDetails offCipDetails : lstOffCipDet) {
			parameters.add(new BeanPropertySqlParameterSource(offCipDetails));
		}
		try {
			String tableName = "OFFENDER_CIP_DETAILS";
			String whereClause = "OFFENDER_BOOK_ID  = :offenderBookId and PLACEMENT_SEQ  = :placementSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offCipDetailsDeleteOffenderCipDetails", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffCipDet.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OIDCIPON_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> syProRM = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				sysProMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), syProRM);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgPlacementReasonRecordGroup(final String placementType) {
		final String sql = getQuery("OIDCIPON_FIND_RGPLACEMENTREASON");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodesMapping);
		List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
		try {
			lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
					createParams("PLACEMENTTYPE", placementType, "MODE", MODE), mRowMapper);
		} catch (Exception e) {
			logger.error("In rgPlacementReasonRecordGroup method : ", e);
		}
		return lstRefCodes;

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgPlacementTypeRecordGroup() {
		final String sql = getQuery("OIDCIPON_FIND_RGPLACEMENTTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodesMapping);
		List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
		try {
			lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams("MODE", MODE),
					mRowMapper);
		} catch (Exception e) {
			logger.error("In rgPlacementTypeRecordGroup method : ", e);
		}
		return lstRefCodes;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<AgencyLocations> rgAgyLocsRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIDCIPON_FIND_RGAGYLOCS");
		final RowMapper<AgencyLocations> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				clAgyLocMapping);

		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		try {
			returnList = (List<AgencyLocations>) namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseloadId, "MODE", MODE), mMRowMapper);
		} catch (Exception e) {
			logger.error("In rgAgyLocsRecordGroup method : ", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgRequestedByRecordGroup() {
		final String sql = getQuery("OIDCIPON_FIND_RGREQUESTEDBY");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodesMapping);
		List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
		try {
			lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams("MODE", MODE),
					mRowMapper);
		} catch (Exception e) {
			logger.error("In rgRequestedByRecordGroup method : ", e);
		}
		return lstRefCodes;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgAuthorizedByRecordGroup() {
		final String sql = getQuery("OIDCIPON_FIND_RGAUTHORIZEDBY");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodesMapping);

		List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
		try {
			lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams("MODE", MODE),
					mRowMapper);
		} catch (Exception e) {
			logger.error("In rgAuthorizedByRecordGroup method : ", e);
		}
		return lstRefCodes;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<PlacementDurations>
	 */
	public List<PlacementDurations> rgDurationTypeRecordGroup(final String placementType) {
		final String sql = getQuery("OIDCIPON_FIND_RGDURATIONTYPE");
		final RowMapper<PlacementDurations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, PlacementDurations.class,
				placeDurMapping);
		List<PlacementDurations> lstPlacements = new ArrayList<PlacementDurations>();

		final List<PlacementDurations> lstRetPlacements = new ArrayList<PlacementDurations>();
		PlacementDurations objPlace = null;
		try {
			if (placementType != null && !placementType.equals("") && !placementType.equals("undefined")) {
				lstPlacements = (List<PlacementDurations>) namedParameterJdbcTemplate.query(sql,
						createParams("PLACEMENTTYPE", placementType, "MODE", MODE), mRowMapper);
				if (lstPlacements != null && lstPlacements.size() > 0) {
					for (final PlacementDurations objPlacements : lstPlacements) {
						objPlace = new PlacementDurations();
						objPlace.setDescription(objPlacements.getDurationType());
						objPlace.setMinimumDuration(objPlacements.getMinimumDuration());
						objPlace.setMaximumDuration(objPlacements.getMaximumDuration());
						objPlace.setCode(objPlacements.getDurationType());
						objPlace.setDurationType(objPlacements.getDurationType());
						if ("Y".equals(objPlacements.getActiveFlag())) {
							objPlace.setCanDisplay(true);
							lstRetPlacements.add(objPlace);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("In rgDurationTypeRecordGroup method : ", e);
		}
		return lstRetPlacements;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgReleasedByRecordGroup() {
		final String sql = getQuery("OIDCIPON_FIND_RGRELEASEDBY");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
		try {
			lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams("MODE", MODE),
					mRowMapper);
		} catch (Exception e) {
			logger.error("In rgReleasedByRecordGroup method : ", e);
		}
		return lstRefCodes;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public OffenderCipDetails offBkgOnCheckDeleteMaster(final OffenderCipDetails paramBean) {
		final String sql = getQuery("OIDCIPON_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderCipDetails> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCipDetails.class, offCipDetMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offCipDetailsPreInsert
	 *
	 * @param params
	 *
	 */
	public Integer offCipDetailsPreInsert(final OffenderCipDetails paramBean) {
		final String sql = getQuery("OIDCIPON_OFF_CIP_DETAILS_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()), Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offCipDetailsPostQuery
	 *
	 * @param params
	 *
	 */
	public AgencyLocations offCipDetailsPostQuery(final AgencyLocations paramBean) {
		final String sql = getQuery("OIDCIPON_OFF_CIP_DETAILS_POSTQUERY");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agyLocMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offCipDetailsPostQuery
	 *
	 * @param params
	 *
	 */
	public PlacementDurations offCipDetailsPostQuery(final PlacementDurations paramBean) {
		final String sql = getQuery("OIDCIPON_OFF_CIP_DETAILS_POSTQUERY");
		final RowMapper<PlacementDurations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				PlacementDurations.class, placeDurMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offCipDetailsPostQuery
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes offCipDetailsPostQuery(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDCIPON_OFF_CIP_DETAILS_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
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
		final String sql = getQuery("OIDCIPON_CGWHEN_NEW_FORM_INSTANCE");
		return (List<Object>) namedParameterJdbcTemplate.queryForList(sql, createParams(), Object.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobalsCREATE_FORM_GLOBALS
	 *
	 * @param params
	 *
	 */
	public OmsModules createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OIDCIPON_CREATE_FORM_GLOBALS_CREATE_FORM_GLOBALS");
		return (OmsModules) namedParameterJdbcTemplate.queryForObject(sql, createParams(), Object.class);

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * defaultAgyLocId
	 *
	 * @param params
	 *
	 */
	public List<Object> defaultAgyLocId(final CaseloadAgencyLocations paramBean) {
		final String sql = getQuery("OIDCIPON_DEFAULT_AGY_LOC_ID");
		return (List<Object>) namedParameterJdbcTemplate.queryForList(sql, createParams(), Object.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * defaultDurationType
	 *
	 * @param params
	 *
	 */
	public List<PlacementDurations> defaultDurationType(final PlacementDurations paramBean) {
		final String sql = getQuery("OIDCIPON_DEFAULT_DURATION_TYPE");
		final RowMapper<PlacementDurations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				PlacementDurations.class, placeDurMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("PLACEMENTTYPE", paramBean.getPlacementType()),
				columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * dtValidationForInactiveOff
	 *
	 * @param params
	 *
	 */
	public List<OffenderExternalMovements> dtValidationForInactiveOff(final OffenderExternalMovements paramBean) {
		final String sql = getQuery("OIDCIPON_DT_VALIDATION_FOR_INACTIVE_OFF");
		final RowMapper<OffenderExternalMovements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offExtMovMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()),
				columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkForActiveCpRec
	 *
	 * @param params
	 *
	 */
	public List<OffenderCipDetails> checkForActiveCpRec(final OffenderCipDetails paramBean) {
		final String sql = getQuery("OIDCIPON_CHECK_FOR_ACTIVE_CP_REC");
		final RowMapper<OffenderCipDetails> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCipDetails.class, offCipDetMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()),
				columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkDate
	 *
	 * @param params
	 *
	 */
	public List<OffenderCipDetails> checkDate(final VHeaderBlock paramBean) {
		final String sql = getQuery("OIDCIPON_CHECK_DATE");
		final RowMapper<OffenderCipDetails> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderCipDetails.class, offCipDetMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()),
				columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * checkDate
	 *
	 * @param params
	 *
	 */
	public Integer checkDateCount(final VHeaderBlock paramBean) {
		final String sql = getQuery("OIDCIPON_CHECK_DATE_");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()), Integer.class);

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * computeForHours
	 *
	 * @param params
	 *
	 */
	public Double computeForHours(final Date effectiveTime, final Date releaseTime) {
		final String sql = getQuery("OIDCIPON_OFF_CIP_DETAILS_COMPUTE_HOURS");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("effectiveTime", effectiveTime, "releaseTime", releaseTime), Double.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * computeForTime
	 *
	 * @param params
	 *
	 */
	public Double computeForTime(final Double lvTtInHours) {
		final String sql = getQuery("OIDCIPON_OFF_CIP_DETAILS_COMPUTE_MINUTES");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("LVTTINHOURS", lvTtInHours), Double.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * computeForHoursFormat
	 *
	 * @param params
	 *
	 */
	public String computeForHoursFormat(final Double lvRelTime) {
		final String sql = getQuery("OIDCIPON_OFF_CIP_DETAILS_COMPUTE_HOURS_FORMAT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("lvRelTime", lvRelTime), String.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * computeForFloorHours
	 *
	 * @param params
	 *
	 */
	public Integer computeForFloorHours(final Double lvTtInHours) {
		final String sql = getQuery("OIDCIPON_OFF_CIP_DETAILS_COMPUTE_FLOOR_HOURS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("lvTtInHours", lvTtInHours), Integer.class);
	}

}

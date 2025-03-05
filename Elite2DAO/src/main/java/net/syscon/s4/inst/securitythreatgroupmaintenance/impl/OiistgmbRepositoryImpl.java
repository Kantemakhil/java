package net.syscon.s4.inst.securitythreatgroupmaintenance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.VStgLocationMembers;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.securitythreatgroupmaintenance.OiistgmbRepository;

/**
 * Class OiistgmbRepositoryImpl
 */
@Repository
public class OiistgmbRepositoryImpl extends RepositoryBase implements OiistgmbRepository {

	private static Logger logger = LogManager.getLogger(OiistgmbRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> assessmentMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("REVIEW_SUP_LEVEL_TYPE",            new FieldMapper("reviewSupLevelType"))
			.put("REQUIRE_APPROVAL_FLAG",            new FieldMapper("requireApprovalFlag"))
			.put("OVERRIDED_SUP_LEVEL_TYPE",         new FieldMapper("overridedSupLevelType"))
			.put("NULL",                             new FieldMapper("null"))
			.put("PROFILE_VALUE",                    new FieldMapper("profileValue"))
			.put("CALC_SUP_LEVEL_TYPE",              new FieldMapper("calcSupLevelType"))
			.put("DECODE(OVERRIDED_SUP_LEVEL_TYPE",  new FieldMapper("decode(overridedSupLevelType"))
			.put("OFFENDER_BOOK_ID",                 new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> offndrAsmtMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("REVIEW_SUP_LEVEL_TYPE",             new FieldMapper("reviewSupLevelType"))
			.put("REQUIRE_APPROVAL_FLAG",             new FieldMapper("requireApprovalFlag"))
			.put("OVERRIDED_SUP_LEVEL_TYPE",          new FieldMapper("overridedSupLevelType"))
			.put("NULL",                              new FieldMapper("null"))
			.put("PROFILE_VALUE",                     new FieldMapper("profileValue "))
			.put("CALC_SUP_LEVEL_TYPE",               new FieldMapper("calcSupLevelType"))
			.put("DECODE(OVERRIDED_SUP_LEVEL_TYPE",   new FieldMapper("decode(overridedSupLevelType"))
			.put("OFFENDER_BOOK_ID",                  new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> vStgLctnMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID",                        new FieldMapper("agyLocId"))
			.put("OFFENDER_BOOK_ID",                  new FieldMapper("offenderBookId"))
			.put("STG_ID",                            new FieldMapper("stgId"))
			.put("LIST_SEQ",                          new FieldMapper("listseq"))
			.build();
	private final Map<String, FieldMapper> ofndrStgMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION",                             new FieldMapper("description"))
			.put("STG_ID",                                  new FieldMapper("stgId"))
			.build();
	private final Map<String, FieldMapper> agyLcnsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID",                              new FieldMapper("agyLocId"))
			.put("DESCRIPTION",                             new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> lvngUntsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIVING_UNIT_ID",                             new FieldMapper("livingUnitId"))
			.put("AGY_LOC_ID",                                 new FieldMapper("agyLocId"))
			.put("LIVING_UNIT_TYPE",                           new FieldMapper("livingUnitType"))
			.put("LIVING_UNIT_CODE",                           new FieldMapper("livingUnitCode"))
			.put("DESCRIPTION",                                new FieldMapper("description"))
			.put("LEVEL_1_CODE",                               new FieldMapper("level1Code"))
			.put("LEVEL_2_CODE",                               new FieldMapper("level2Code"))
			.put("LEVEL_3_CODE",                               new FieldMapper("level3Code"))
			.put("LEVEL_4_CODE",                               new FieldMapper("level4Code"))
			.put("USER_DESC",                                  new FieldMapper("userDesc"))
			.put("ACA_CAP_RATING",                             new FieldMapper("acaCapRating"))
			.put("SECURITY_LEVEL_CODE",                        new FieldMapper("securityLevelCode"))
			.put("LIST_SEQ",                                   new FieldMapper("listSeq"))
			.put("PARENT_LIVING_UNIT_ID",                      new FieldMapper("parentLivingUnitCode"))
			.put("HOUSING_UNIT_TYPE",                          new FieldMapper("housingUnitType"))
			.put("ACTIVE_FLAG",                                new FieldMapper("activeFlag"))
			.put("CONTROL_ACTIVE_FLAG",                        new FieldMapper("controlActiveFlag"))
			.put("CNA_NO",                                     new FieldMapper("cnaNo"))
			.put("CAPACITY",                                   new FieldMapper("capacity"))
			.put("OPERATION_CAPACITY",                         new FieldMapper("operationCapacity"))
			.put("CERTIFIED_FLAG",                             new FieldMapper("certifiedFlag"))
			.put("DEACTIVATE_DATE",                            new FieldMapper("deactivateDate"))
			.put("REACTIVATE_DATE",                            new FieldMapper("reactivateDate"))
			.put("DEACTIVATE_REASON_CODE",                     new FieldMapper("deactivateReasonCode"))
			.put("COMMENT_TEXT",                               new FieldMapper("commentText"))
			.put("LOWEST_LEVEL_FLAG",                          new FieldMapper("lowestLevelFlag"))
			.put("REACH_OPER_CAPACITY_FLAG",                   new FieldMapper("reachOperCapacityFlag"))
			.put("NO_OF_OCCUPANT",                             new FieldMapper("noOfOccupant"))
			.build();

	private final Map<String, FieldMapper> refrnceCodMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DOMAIN",                                   new FieldMapper("domain"))
			.put("CODE",                                     new FieldMapper("code"))
			.put("DESCRIPTION",                              new FieldMapper("description"))
			.put("LIST_SEQ",                                 new FieldMapper("listSeq"))
			.put("ACTIVE_FLAG",                              new FieldMapper("activeFlag"))
			.put("SYSTEM_DATA_FLAG",                         new FieldMapper("systemDataFlag"))
			.put("MODIFY_USER_ID",                           new FieldMapper("modifyUserId"))
			.put("EXPIRED_DATE",                             new FieldMapper("expiredDate"))
			.put("NEW_CODE",                                 new FieldMapper("newCode"))
			.put("PARENT_CODE",                              new FieldMapper("parentCode"))
			.put("PARENT_DOMAIN",                            new FieldMapper("parentDomain"))
			.put("CREATE_DATETIME",                          new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID",                           new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME",                          new FieldMapper("modifyDateTime"))
			.put("SEAL_FLAG",                                new FieldMapper("sealFlag"))
			.build();

	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODULE_NAME",                               new FieldMapper("moduleName"))
			.put("DESCRIPTION",                               new FieldMapper("description"))
			.put("MODULE_TYPE",                               new FieldMapper("moduleType"))
			.put("PRINT_FORMAT_CODE",                         new FieldMapper("printFormatCode"))
			.put("PREVIEW_FLAG",                              new FieldMapper("previewFlag"))
			.put("DEFAULT_COPY",                              new FieldMapper("defaultCopy"))
			.put("APPLN_CODE",                                new FieldMapper("applnCode"))
			.put("HELP_DIRECTORY",                            new FieldMapper("helpDirectory"))
			.put("CREATE_DATETIME",                           new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID",                            new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME",                           new FieldMapper("modifyDateTime"))
			.put("MODIFY_USER_ID",                            new FieldMapper("modifyUserId"))
			.put("OUTPUT_TYPE",                               new FieldMapper("outputType"))
			.put("SEAL_FLAG",                                 new FieldMapper("sealFlag"))
			.build();

	/**
	 * Creates new OiistgmbRepositoryImpl class Object
	 */

	public OiistgmbRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            LivingUnits
	 *
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> livingUnitsExecuteQuery(final LivingUnits objSearchDao) {
		final String sql = getQuery("OIISTGMB_LIVINGUNITS_FIND_LIVING_UNITS");
		final RowMapper<LivingUnits> lvngUtsRowMapper = Row2BeanRowMapper.makeMapping(sql, LivingUnits.class,
				lvngUntsMapping);
		List<LivingUnits> returnList = new ArrayList<LivingUnits>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("AGYLOCID", objSearchDao.getAgyLocId()),
					lvngUtsRowMapper);
		} catch (Exception e) {

			logger.error("livingUnitsExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VStgLocationMembers
	 *
	 * @return List<VStgLocationMembers>
	 *
	 */
	public List<VStgLocationMembers> vStgLocationMembersExecuteQuery(final VStgLocationMembers objSearchDao) {
		final String sql = getQuery("OIISTGMB_VSTGLOCATIONMEMBERS_FIND_V_STG_LOCATION_MEMBERS");
		final RowMapper<VStgLocationMembers> vStgLcnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VStgLocationMembers.class, vStgLctnMapping);
		List<VStgLocationMembers> returnList = new ArrayList<VStgLocationMembers>();
		try {
			returnList = namedParameterJdbcTemplate
					.query(sql,
							createParams("AGYLOCID", objSearchDao.getAgyLocId(), "LIVINGUNITID",
									objSearchDao.getLivingUnitId(), "STGID", objSearchDao.getStgId()),
							vStgLcnRowMapper);
		} catch (Exception e) {
			logger.error("vStgLocationMembersExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> vStgLocationMembersPreQuerygetOffenderId(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIISTGMB_V_STG_LOCATION_MEMBERS_PREQUERY_GET_OFFENDER_ID");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refrnceCodMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("vStgLocationMembersPreQuerygetOffenderId", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OIISTGMB_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		List<OmsModules> returnList = new ArrayList<OmsModules>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {

			logger.error("createFormGlobals", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getLocationDescription
	 *
	 * @param params
	 *
	 */
	public List<AgencyLocations> getLocationDescription(final AgencyLocations paramBean) {
		final String sql = getQuery("OIISTGMB_GET_LOCATION_DESCRIPTION");
		final RowMapper<AgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				agyLcnsMapping);
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("AGYLOCID", paramBean.getAgyLocId()),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("getLocationDescription", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getNumberOfMembers
	 *
	 * @param params
	 *
	 */
	public List<OffenderStgAffiliations> getNumberOfMembers(final OffenderStgAffiliations paramBean) {
		final String sql = getQuery("OIISTGMB_GET_NUMBER_OF_MEMBERS");
		final RowMapper<OffenderStgAffiliations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderStgAffiliations.class, ofndrStgMapping);
		List<OffenderStgAffiliations> returnList = new ArrayList<OffenderStgAffiliations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {

			logger.error("getNumberOfMembers", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getStatus
	 *
	 * @param params
	 *
	 */
	public String getStatus() {
		final String sql = getQuery("OIISTGMB_GET_STATUS");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			logger.error("getStatus", e);
		}
		return returnData;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getStatus
	 *
	 * @param params
	 *
	 */
	public String getStatusAssesments(final String profileType) {
		final String sql = getQuery("OIISTGMB_GET_STATUS_ASSESSMENTS");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("PROFILETYPE", profileType),
					String.class);
		} catch (Exception e) {
			logger.error("getStatusAssesments", e);
		}
		return returnData;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getStatus
	 *
	 * @param params
	 *
	 */
	public String getStatusOffenderAssesmentsDataOne(final Long offenderBookId, final String profileType) {
		final String sql = getQuery("OIISTGMB_GET_STATUS_OFFENDER_ASSESSMENTS_DATA_ONE");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERBOOKID", offenderBookId, "PROFILETYPE", profileType), String.class);
		} catch (Exception e) {
			return null;
		}
		return returnData;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getStatus
	 *
	 * @param params
	 *
	 */
	public String getStatusOffenderAssesmentsDataTwo(final Long offenderBookId, final String profileType) {
		final String sql = getQuery("OIISTGMB_GET_STATUS_OFFENDER_ASSESSMENTS");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERBOOKID", offenderBookId, "PROFILETYPE", profileType), String.class);
		} catch (Exception e) {
			logger.error("getStatusOffenderAssesmentsDataTwo", e);
		}
		return returnData;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getStatus
	 *
	 * @param params
	 *
	 */
	public List<Assessments> getStatus(final Assessments paramBean) {
		final String sql = getQuery("OIISTGMB_GET_STATUS_ASSESSMENTS");
		final RowMapper<Assessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentMapping);
		List<Assessments> returnList = new ArrayList<Assessments>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("getStatus", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param params
	 * @return List<OffenderAssessments>
	 *
	 */
	public List<OffenderAssessments> getStatus(final OffenderAssessments paramBean) {
		final String sql = getQuery("OIISTGMB_GET_STATUS_OFFENDER_ASSESSMENTS");
		final RowMapper<OffenderAssessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAssessments.class, offndrAsmtMapping);
		List<OffenderAssessments> returnList = new ArrayList<OffenderAssessments>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("REQUIREAPPROVALFLAG", paramBean),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("getStatus", e);
		}
		return returnList;
	}

	/**
	 * @param stgId,description
	 * @return Integer
	 */
	public Integer getCountOfNumber(final String agyLocId, final BigDecimal livingUnitId,final Long stgId) {
		final String sql = getQuery("OIISTGMB_GETCOUNTOFNUMBER");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("agy_loc_id",agyLocId,"living_unit_id",livingUnitId,"stg_id", stgId), Integer.class);
		} catch (Exception e) {
			logger.error("getCountOfNumber", e);
		}
		return count;

	}

	/**
	 * @param assesmentOne
	 * @return String
	 */
	@Override
	public String getStatusData(final String assesmentOne) {
		final String sql = getQuery("OIISTGMB_OMS_MISCELLANEOUS_GETDESCCODE");
		String status = null;
		status = namedParameterJdbcTemplate.queryForObject(sql, createParams("ASSDATA", assesmentOne), String.class);
		if (status != null) {
			return status;
		}
		return status;
	}
}

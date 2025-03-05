package net.syscon.s4.inst.schedules.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderEmployments;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AddressUsages;
import net.syscon.s4.im.beans.OffenderChecklistDetails;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.im.beans.ReleasePlans;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.property.bean.Persons;
import net.syscon.s4.inst.schedules.OidrplanRepository;

/**
 * Class OidrplanRepositoryImpl
 */
@Repository
public class OidrplanRepositoryImpl extends RepositoryBase implements OidrplanRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidrplanRepositoryImpl.class);

	private final Map<String, FieldMapper> offenderEmploymentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ASSESSMENT_SEQ", new FieldMapper("assessmentSeq")).put("DESCRIPTION", new FieldMapper("description"))
			.put("EMPLOY_SEQ", new FieldMapper("employSeq")).put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("ASSESSMENT_DATE", new FieldMapper(" assessmentDate "))
			.put("STREET_INFORMATION||", new FieldMapper("streetInformation||"))
			.put("ASSESSMENT_SE", new FieldMapper("assessmentSe")).put("ADDRESS_ID", new FieldMapper(" addressId "))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.put("GETDESCCODE('OCCUPATION'", new FieldMapper("getdesccode('occupation'"))
			.put("GETDESCCODE('EMPLOY_STS'", new FieldMapper("getdesccode('employSts'"))
			.put("LAST_NAME||'", new FieldMapper("lastName||' ")).put("'||FIRST_NAME", new FieldMapper("'||firstName"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("EMPLOYMENT_POST_CODE", new FieldMapper("employmentPostCode"))
			.put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("PROPOSED_EMPLOYMENT", new FieldMapper("proposedEmployment"))
			.put("EMPLOY_SEQ", new FieldMapper("employSeq")).build();
	private final Map<String, FieldMapper> vAddressesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ASSESSMENT_SEQ", new FieldMapper("assessmentSeq")).put("DESCRIPTION", new FieldMapper("description"))
			.put("EMPLOY_SEQ", new FieldMapper("employSeq")).put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("ASSESSMENT_DATE", new FieldMapper("assessmentDate"))
			.put("STREET_INFORMATION||", new FieldMapper("streetInformation||"))
			.put("ASSESSMENT_SE", new FieldMapper("assessmentSe")).put("ADDRESS_ID", new FieldMapper("addressId"))
			.put("ADDR", new FieldMapper("addr")).put("PROPOSED_HOUSING", new FieldMapper("proposedHousing")).build();
	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'Y'", new FieldMapper(" 'y' ")).build();
	private final Map<String, FieldMapper> offenderChecklistDetailsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", new FieldMapper("profileCode")).put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("PROFILE_TYPE", new FieldMapper("profileType")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("PROFILE_SEQ", new FieldMapper("profileSeq")).put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("RELEASE_PLAN_ID", new FieldMapper("releasePlanId"))
			.put("CHECKLIST_TYPE", new FieldMapper("checklistType"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("CASELOAD_TYPE", new FieldMapper("caseloadType"))
			.put("OFFENDER_CHECKLIST_ID", new FieldMapper("offenderChecklistId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("'Y'", new FieldMapper(" 'y' ")).put("DESCRIPTION", new FieldMapper("description"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("FIRST_NAME||", new FieldMapper("firstName||")).put("COUNT", new FieldMapper("count")).build();
	private final Map<String, FieldMapper> profileCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE_VALUE_TYPE", new FieldMapper(" codeValueType ")).put("CONTACT_NAME", new FieldMapper("dspName"))
			.put("Y", new FieldMapper("employer")).put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("PROFILE_CODE", new FieldMapper("profileCode")).build();
	private final Map<String, FieldMapper> addressUsagesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ASSESSMENT_SEQ", new FieldMapper("assessmentSeq")).put("DESCRIPTION", new FieldMapper("description"))
			.put("EMPLOY_SEQ", new FieldMapper("employSeq")).put("ROOT_OFFENDER_ID", new FieldMapper("rootOffenderId"))
			.put("ASSESSMENT_DATE", new FieldMapper(" assessmentDate "))
			.put("STREET_INFORMATION||", new FieldMapper("streetInformation||"))
			.put("ADDRESS_ID", new FieldMapper("addressId")).put("ASSESSMENT_SE", new FieldMapper("assessmentSe"))
			.build();
	private final Map<String, FieldMapper> releasePlansMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROPOSED_EMPLOYMENT", new FieldMapper("proposedEmployment"))
			.put("ADDRESS_ID", new FieldMapper("addressId")).put("UPDATED_BY", new FieldMapper("updatedBy"))
			.put("RELEASE_PLAN_ID", new FieldMapper("releasePlanId")).put("PLAN_STATUS", new FieldMapper("planStatus"))
			.put("CREATE_DATE", new FieldMapper("createDate"))
			.put("PROPOSED_HOUSING", new FieldMapper("proposedHousing"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("CASE_MANAGER_ID", new FieldMapper("caseManagerId"))
			.put("PAROLE_AGENT_ID", new FieldMapper("paroleAgentId"))
			.put("HOUSING_COMMENT", new FieldMapper("housingComment"))
			.put("EMPLOYMENT_STATUS", new FieldMapper("employmentStatus"))
			.put("EMPLOYMENT_COMMENT", new FieldMapper("employmentComment"))
			.put("AGENT_RECOMMEND", new FieldMapper("agentRecommend"))
			.put("ADDRESS_TYPE", new FieldMapper("addressType"))
			.put("ASSESSMENT_DATE", new FieldMapper("assessmentDate"))
			.put("REVIEW_SUP_LEVEL_TYPE", new FieldMapper("reviewSupLevelType")).build();
	private final Map<String, FieldMapper> profileTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("UPDATED_ALLOWED_FLAG", new FieldMapper("updatedAllowedFlag"))
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE_VALUE_TYPE", new FieldMapper("codeValueType"))
			.put("PROFILE_TYPE", new FieldMapper("profileType")).put("PROFILE_CODE", new FieldMapper("profileCode"))
			.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("'Y'", new FieldMapper(" 'y' ")).build();

	/**
	 * Creates new OidrplanRepositoryImpl class Object
	 */
	public OidrplanRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao ReleasePlans
	 *
	 * @return List<ReleasePlans>
	 *
	 * @throws SQLException
	 */
	public List<ReleasePlans> releasePlansExecuteQuery(final ReleasePlans objSearchDao) {
		final String sql = getQuery("OIDRPLAN_RELEASEPLANS_FIND_RELEASE_PLANS");
		final RowMapper<ReleasePlans> ReleasePlansRowMapper = Row2BeanRowMapper.makeMapping(sql, ReleasePlans.class,
				releasePlansMapping);
		List<ReleasePlans> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("offenderBookId", objSearchDao.getOffenderBookId()), ReleasePlansRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstReleasePlans List<ReleasePlans>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer releasePlansInsertReleasePlans(ReleasePlans lstReleasePlans) {
		String sql = getQuery("OIDRPLAN_RELEASEPLANS_INSERT_RELEASE_PLANS");
		Integer returnArray = null;
		try {
			returnArray = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(lstReleasePlans));
		} catch (Exception e) {
			logger.error("releasePlansInsertReleasePlans :" + e);
		}
		return returnArray;

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstReleasePlans List<ReleasePlans>
	 *
	 * @throws SQLException
	 */
	public Integer releasePlansUpdateReleasePlans(final List<ReleasePlans> lstReleasePlans) {
		String sql = getQuery("OIDRPLAN_RELEASEPLANS_UPDATE_RELEASE_PLANS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ReleasePlans releasePlans : lstReleasePlans) {
			parameters.add(new BeanPropertySqlParameterSource(releasePlans));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstReleasePlans.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstReleasePlans List<ReleasePlans>
	 *
	 * @throws SQLException
	 */
	public Integer updateReleasePlans(final List<ReleasePlans> lstReleasePlans) {
		String sql = getQuery("OIDRPLAN_UPDATE_RELEASE_PLANS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ReleasePlans releasePlans : lstReleasePlans) {
			parameters.add(new BeanPropertySqlParameterSource(releasePlans));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstReleasePlans.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstReleasePlans List<ReleasePlans>
	 *
	 * @throws SQLException
	 */
	public Integer releasePlansDeleteReleasePlans(final List<ReleasePlans> lstReleasePlans) {
		String sql = getQuery("OIDRPLAN_RELEASEPLANS_DELETE_RELEASE_PLANS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ReleasePlans releasePlans : lstReleasePlans) {
			parameters.add(new BeanPropertySqlParameterSource(releasePlans));
		}
		try {
			String tableName = "RELEASE_PLANS";
			String whereCondition = "RELEASE_PLAN_ID =:releasePlanId AND OFFENDER_BOOK_ID = :offenderBookId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstReleasePlans.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderChecklistDetails
	 *
	 * @return List<OffenderChecklistDetails>
	 *
	 * @throws SQLException
	 */
	public List<OffenderChecklistDetails> offChecklistDetExecuteQuery(final OffenderChecklistDetails objSearchDao) {
		final String sql = getQuery("OIDRPLAN_OFFCHECKLISTDET_FIND_OFFENDER_CHECKLIST_DETAILS");
		final RowMapper<OffenderChecklistDetails> OffenderChecklistDetailsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderChecklistDetails.class, offenderChecklistDetailsMapping);
		List<OffenderChecklistDetails> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("offenderChecklistId", objSearchDao.getOffenderChecklistId()),
				OffenderChecklistDetailsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstReleasePlans List<ReleasePlans>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offenderCheckListInsertOffenderCheckList(final ReleasePlans lstReleasePlans) {
		String sql = getQuery("INSERT_OFFENDER_CHECKLIST_DETAILS");
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(lstReleasePlans));
		return returnArray;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderChecklistDetails List<OffenderChecklistDetails>
	 *
	 * @throws SQLException
	 */
	public Integer offChecklistDetUpdateOffenderChecklistDetails(
			final List<OffenderChecklistDetails> lstOffenderChecklistDetails) {
		String sql = getQuery("OIDRPLAN_OFFCHECKLISTDET_UPDATE_OFFENDER_CHECKLIST_DETAILS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderChecklistDetails offenderChecklistDetails : lstOffenderChecklistDetails) {
			parameters.add(new BeanPropertySqlParameterSource(offenderChecklistDetails));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderChecklistDetails.size() == returnArray.length) {
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
	public List<StaffMembers> rgCaseManagersRecordGroup(final String caseLoadId) {
		final String sql = getQuery("OIDRPLAN_FIND_RGCASEMANAGERS");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseLoadId), mRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<StaffMembers> rgParoleAgentsRecordGroup(final String caseLoadId) {
		final String sql = getQuery("OIDRPLAN_FIND_RGPAROLEAGENTS");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseLoadId), mRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgPlanStatusRecordGroup(final String userName) {
		final String sql = getQuery("OIDRPLAN_FIND_RGPLANSTATUS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("userName",userName), mRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgEmploymentStatusRecordGroup() {
		final String sql = getQuery("OIDRPLAN_FIND_RGEMPLOYMENTSTATUS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM(selectRc.description>
	 */
	public List<VAddresses> rgProposedHousingRecordGroup(final String rootOffenderId) {
		final String sql = getQuery("OIDRPLAN_FIND_RGPROPOSEDHOUSING");
		final RowMapper<VAddresses> RowMapper = Row2BeanRowMapper.makeMapping(sql, VAddresses.class, vAddressesMapping);
		List<VAddresses> vAddresses = new ArrayList<>();
		try {
			vAddresses = namedParameterJdbcTemplate.query(sql,
					createParams("rootOffenderId", rootOffenderId != null ? new BigDecimal(rootOffenderId) : null),
					RowMapper);
		} catch (Exception e) {
			logger.error(e);
			return vAddresses;
		}
		return vAddresses;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<OffenderEmployments> rgProposedEmploymentRecordGroup(final Long offenderBookId) {
		final String sql = "SELECT EMPLOYER_NAME||' - '||         OMS_MISCELLANEOUS_GETDESCCODE('OCCUPATION' , OCCUPATIONS_CODE )||' - '||        OMS_MISCELLANEOUS_GETDESCCODE('EMPLOY_STS' , EMPLOYMENT_POST_CODE ) PROPOSED_EMPLOYMENT , 	   EMPLOY_SEQ   FROM OFFENDER_EMPLOYMENTS  WHERE OFFENDER_BOOK_ID = :offenderBookId  ORDER BY PROPOSED_EMPLOYMENT"; //getQuery("OIDRPLAN_FIND_RGPROPOSEDEMPLOYMENT_ONE");
		final RowMapper<OffenderEmployments> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderEmployments.class,
				mMapping);
		List<OffenderEmployments> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId),
					mRowMapper);
		} catch (Exception e) {
			logger.error("rgProposedEmploymentRecordGroup", e);
			return returnList;
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ProfileCodes> rgChecklistAnsRecordGroup(final String profileType) {
		final String sql = getQuery("OIDRPLAN_FIND_RGCHECKLISTANS");
		final RowMapper<ProfileCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileCodes.class, mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("profileType", profileType), mRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<ReleasePlans> offBkgOnCheckDeleteMaster(final ReleasePlans paramBean) {
		final String sql = getQuery("OIDRPLAN_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<ReleasePlans> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReleasePlans.class,
				releasePlansMapping);
		final ArrayList<ReleasePlans> returnList = (ArrayList<ReleasePlans>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * releasePlansPostQuery
	 *
	 * @param params
	 *
	 */
	public List<ReleasePlans> releasePlansOffenderassesmentsPostQuery(final Long offenderBookId) {
		final String sql = getQuery("OIDRPLAN_RELEASE_PLANS_POSTQUERY_OFF_BOOK_ID");
		final RowMapper<ReleasePlans> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReleasePlans.class,
				releasePlansMapping);
		List<ReleasePlans> returnObj = new ArrayList<ReleasePlans>();
		try {
			returnObj = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("releasePlansOffenderassesmentsPostQuery", e);
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * releasePlansPostQuery
	 *
	 * @param params
	 *
	 */
	public List<ReleasePlans> releasePlansAssesmentsPostQuery(final Long offenderBookId,
			final BigDecimal assessmentSeq) {
		final String sql = getQuery("OIDRPLAN_RELEASE_PLANS_POSTQUERY");
		final RowMapper<ReleasePlans> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReleasePlans.class,
				releasePlansMapping);
		List<ReleasePlans> returnObj = new ArrayList<ReleasePlans>();
		returnObj = namedParameterJdbcTemplate.query(sql,
				createParams("offenderBookId", offenderBookId, "assessmentSeq", assessmentSeq), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * releasePlansPostQuery
	 *
	 * @param params
	 *
	 */
	public List<VAddresses> releasePlansPostQuery(final VAddresses paramBean) {
		final String sql = getQuery("OIDRPLAN_RELEASE_PLANS_POSTQUERY");
		final RowMapper<VAddresses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VAddresses.class,
				vAddressesMapping);
		List<VAddresses> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("ADDRESSID", paramBean.getAddressId()),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * releasePlansPostQuery
	 *
	 * @param params
	 *
	 */
	public String releasePlansPostQueryAddress(final BigDecimal addressId) {
		final String sql = getQuery("OIDRPLAN_RELEASE_PLANS_POSTQUERY_ADDRESS_ID");
		String returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("ADDRESSID", addressId), String.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * releasePlansPostQuery
	 *
	 * @param params
	 *
	 */
	public String releasePlansPostQueryProposedEmployments(final Long offenderBookId, final BigDecimal employSeq) {
		final String sql = getQuery("OIDRPLAN_RELEASE_PLANS_PROPOSED_EMPLOYMENTS_POSTQUERY");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDERBOOKID", offenderBookId, "EMPLOYSEQ", employSeq), String.class);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * releasePlansPostQuery
	 *
	 * @param params
	 *
	 */
	public ReleasePlans releasePlansPostQueryHousing(final BigDecimal rootOffenderId) {
		final String sql = getQuery("OIDRPLAN_RELEASE_PLANS_POSTQUERY_HOUSING_LOCATION");
		final RowMapper<ReleasePlans> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReleasePlans.class,
				vAddressesMapping);
		ReleasePlans returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("rootOffenderId", rootOffenderId),
					columnRowMapper);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * releasePlansPostQuery
	 *
	 * @param params
	 *
	 */
	public Object releasePlansAddressUsagesPostQuery(final AddressUsages paramBean) {
		final String sql = getQuery("OIDRPLAN_RELEASE_PLANS_POSTQUERY");
		final RowMapper<AddressUsages> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AddressUsages.class,
				addressUsagesMapping);
		AddressUsages returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * releasePlansPostQuery
	 *
	 * @param params
	 *
	 */
	public OffenderEmployments releasePlansOffenderEmploymentsPostQuery(final OffenderEmployments paramBean) {
		final String sql = getQuery("OIDRPLAN_RELEASE_PLANS_POSTQUERY");
		final RowMapper<OffenderEmployments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderEmployments.class, offenderEmploymentsMapping);
		OffenderEmployments returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * releasePlansPreInsert
	 *
	 * @param params
	 *
	 */
	public VAddresses releasePlansPreInsert(final VAddresses paramBean) {
		final String sql = getQuery("OIDRPLAN_RELEASE_PLANS_ADDRESS_ID_PREINSERT");
		final RowMapper<VAddresses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VAddresses.class,
				vAddressesMapping);
		VAddresses returnList = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("rootOffenderId", paramBean.getAddressId()), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * releasePlansPreInsert
	 *
	 * @param params
	 *
	 */
	public String releasePlansPreInsert(final BigDecimal paramBean) {
		String returnObj = null;
		final String sql = getQuery("OIDRPLAN_RELEASE_PLANS_ADDRESS_TYPE_PREINSERT");
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("addressId", paramBean),
					String.class);
		} catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * releasePlansPreRecord
	 *
	 * @param params
	 *
	 */
	public Object releasePlansPreRecord(final SystemProfiles paramBean) {
		final String sql = getQuery("OIDRPLAN_RELEASE_PLANS_PRERECORD");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * releasePlansWhenNewRecordInstance
	 *
	 * @param params
	 *
	 */
	public Object releasePlansWhenNewRecordInstance(final StaffMembers paramBean) {
		final String sql = getQuery("OIDRPLAN_RELEASE_PLANS_WHENNEWRECORDINSTANCE");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		StaffMembers returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * releasePlansKeyDelrec
	 *
	 * @param params
	 *
	 */
	public List<OffenderChecklistDetails> releasePlansKeyDelrec(final OffenderChecklistDetails paramBean) {
		final String sql = getQuery("OIDRPLAN_RELEASE_PLANS_KEYDELREC");
		final RowMapper<OffenderChecklistDetails> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderChecklistDetails.class, offenderChecklistDetailsMapping);
		List<OffenderChecklistDetails> returnObj = namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * releasePlansPreDelete
	 *
	 * @param params
	 *
	 */
	public Integer releasePlansPreDelete(final List<ReleasePlans> lstReleasePlans) {
		String sql = getQuery("OIDRPLAN_DELETE_OFFENDER_CHECKLIST_DETAILS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ReleasePlans releasePlans : lstReleasePlans) {
			parameters.add(new BeanPropertySqlParameterSource(releasePlans));
		}
		try {
			String tableName = "OFFENDER_CHECKLIST_DETAILS";
			String whereCondition = "OFFENDER_CHECKLIST_ID = :releasePlanId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstReleasePlans.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offChecklistDetPostQuery
	 *
	 * @param params
	 *
	 */
	public ProfileTypes offChecklistDetPostQuery(final ProfileTypes paramBean) {
		final String sql = getQuery("OIDRPLAN_OFF_CHECKLIST_DET_POSTQUERY");
		final RowMapper<ProfileTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileTypes.class,
				profileTypesMapping);
		ProfileTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("profileType", paramBean.getProfileType()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offChecklistDetPostQuery
	 *
	 * @param params
	 *
	 */
	public ProfileCodes offChecklistDetPostQuery(final ProfileCodes paramBean) {
		final String sql = getQuery("OIDRPLAN_OFF_CHECKLIST_DET_POSTQUERY");
		final RowMapper<ProfileCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileCodes.class,
				profileCodesMapping);
		ProfileCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offChecklistDetWhenNewRecordInstance
	 *
	 * @param params
	 *
	 */
	public ProfileTypes offChecklistDetWhenNewRecordInstance(final ProfileTypes paramBean) {
		final String sql = getQuery("OIDRPLAN_OFF_CHECKLIST_DET_WHENNEWRECORDINSTANCE");
		final RowMapper<ProfileTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileTypes.class,
				profileTypesMapping);
		ProfileTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * rpReadyForApproval
	 *
	 * @param params
	 *
	 */
	public String rpReadyChkConditionExistsCur(final ReleasePlans paramBean) {
		final String sql = getQuery("OIDRPLAN_CHK_CONDITION_EXISTS_CUR");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("releasePlanId",
					paramBean.getReleasePlanId(), "offenderBookId", paramBean.getOffenderBookId()), String.class);
		} catch (Exception e) {
			logger.error("OIDRPLAN_CHK_CONDITION_EXISTS_CUR", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * rpReadyForApproval
	 *
	 * @param params
	 *
	 */
	public String rpReadyChkOtherOccExistsCur(final ReleasePlans paramBean) {
		final String sql = getQuery("OIDRPLAN_CHK_OTHER_OCC_EXISTS_CUR");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("releasePlanId", paramBean.getReleasePlanId()), String.class);
		} catch (Exception e) {
			logger.error("OIDRPLAN_CHK_OTHER_OCC_EXISTS_CUR", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * rpReadyForApproval
	 *
	 * @param paramBean
	 *
	 */
	public List<Persons> rpReadyChkPrimaryOccExistsCur(final ReleasePlans paramBean) {
		final String sql = getQuery("OIDRPLAN_CHK_PRIMARY_OCC_EXISTS_CUR");
		final RowMapper<Persons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class,
				profileCodesMapping);
		final List<Persons> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("releasePlanId", paramBean.getReleasePlanId()), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * rpReadyForApproval
	 *
	 * @param params
	 *
	 */
	public String rpReadyChkPrimOccContactedCur(final ReleasePlans paramBean) {
		final String sql = getQuery("OIDRPLAN_CHK_PRIM_OCC_CONTACTED_CUR");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("releasePlanId", paramBean.getReleasePlanId()), String.class);
		} catch (Exception e) {
			logger.error("OIDRPLAN_CHK_PRIM_OCC_CONTACTED_CUR", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * rpReadyForApproval
	 *
	 * @param params
	 *
	 */
	public List<ProfileTypes> rpReadyChkAllMandQuesCur(final ReleasePlans paramBean) {
		final String sql = getQuery("OIDRPLAN_CHK_ALL_MAND_QUES_CUR");
		List<ProfileTypes> returnList = new ArrayList<>();
		final RowMapper<ProfileTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileTypes.class,
				profileCodesMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("releasePlanId", paramBean.getReleasePlanId()), columnRowMapper);
		} catch (Exception e) {
			logger.error("OIDRPLAN_CHK_ALL_MAND_QUES_CUR", e);
		}
		return returnList;
	}

	/**
	 * To get description of agylocid param code
	 */
	public StaffMembers getDescriptionOfStaffId(final Integer code) {
		final String sql = getQuery("OIDRPLAN_STAFF_MEMBERS_STAFF_ID");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		final StaffMembers returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("staffId", code),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 */
	public Integer releasePlanPreInsertc() {
		final String sql = getQuery("OIDRPLAN_RELEASE_PLANS_PREINSERTPRE_INSERT");
		String obj = null;
		Integer returnval = null;
		obj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		if (obj != null) {
			returnval = Integer.parseInt(obj);
		}
		return returnval;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean
	 *
	 */
	@Override
	public Integer existsReleasePlansInprgress(final ReleasePlans paramBean) {
		final String sql = getQuery("OIDRPLAN_OFF_BKG_ONCHECKUPDATE");
		Integer returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",
					paramBean.getOffenderBookId(), "releasePlanId", paramBean.getReleasePlanId()), Integer.class);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	@Override
	public String releasePlansPostQueryCondition(final ReleasePlans paramBean) {
		final String sql = getQuery("OIDRPLAN_OIDRPLAN_POSTQUERY");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID",
					paramBean.getOffenderBookId(), "RELEASEPLANID", paramBean.getReleasePlanId()), String.class);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

}

package net.syscon.s4.global.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.HelpMedia;
import net.syscon.s4.common.beans.MenuNode;
import net.syscon.s4.common.beans.MenuSecurities;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.WorkFlowFolders;
import net.syscon.s4.common.beans.WorkflowScreens;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.global.Omss40Repository;
import net.syscon.s4.im.beans.BaseHelpUrl;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ReferenceCodes;
import oracle.jdbc.OracleTypes;

/**
 * Class Omss40RepositoryImpl 
 */
@Repository
public class Omss40RepositoryImpl extends RepositoryBase implements Omss40Repository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger log = LogManager.getLogger(Omss40RepositoryImpl.class.getName());

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION",  new FieldMapper("description"))
			.put("CODE",         new FieldMapper("code"))
			.put("DOMAIN",       new FieldMapper("domain"))
			.put("OID",          new FieldMapper("oid"))
			.put("NAME",         new FieldMapper("name")).build();
	private final Map<String, FieldMapper> baseHelpurlMapping = new ImmutableMap.Builder<String, FieldMapper>()
    		.put("ID", 						new FieldMapper("id"))
    		.put("BASE_HELP_PDF_URL", 		new FieldMapper("baseHelpPdfUrl"))
			.put("BASE_HELP_VIDEO_URL", 	new FieldMapper("baseHelpVideoUrl"))
    		.put("STATUS", 					new FieldMapper("status"))
    		.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_TYPE", 	new FieldMapper("profileType"))
			.put("PROFILE_CODE", 	new FieldMapper("profileCode"))
			.put("DESCRIPTION", 	new FieldMapper("description"))
			.put("PROFILE_VALUE",   new FieldMapper("profileValue"))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2"))
			.put("MODIFY_USER_ID",  new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME",  new FieldMapper("oldTableName"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID",  new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("SEAL_FLAG", 		new FieldMapper("sealFlag"))
			.put("LV_ROLE_BASE", 	new FieldMapper("profileValue"))
			.put("ROLE_PSWD", 		new FieldMapper("profileValue"))
			.build();
	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LV_LABEL", 				new FieldMapper("userId"))
			.put("STAFF_ID", 				new FieldMapper("staffId"))
			.put("ASSIGNED_CASELOAD_ID", 	new FieldMapper("assignedCaseloadId"))
			.put("WORKING_STOCK_LOC_ID", 	new FieldMapper("workingStockLocId"))
			.put("WORKING_CASELOAD_ID", 	new FieldMapper("workingCaseloadId"))
			.put("USER_ID", 				new FieldMapper("userId"))
			.put("BADGE_ID", 				new FieldMapper("badgeId"))
			.put("LAST_NAME", 				new FieldMapper("lastName"))
			.put("FIRST_NAME", 				new FieldMapper("firstName"))
			.put("MIDDLE_NAME", 			new FieldMapper("middleName"))
			.put("ABBREVIATION", 			new FieldMapper("abbreviation"))
			.put("POSITION", 				new FieldMapper("position"))
			.put("BIRTHDATE", 				new FieldMapper("birthdate"))
			.put("TERMINATION_DATE", 		new FieldMapper("terminationDate"))
			.put("UPDATE_ALLOWED_FLAG", 	new FieldMapper("updateAllowedFlag"))
			.put("DEFAULT_PRINTER_ID",		new FieldMapper("defaultPrinterId"))
			.put("SUSPENDED_FLAG", 			new FieldMapper("suspendedFlag"))
			.put("SUPERVISOR_STAFF_ID", 	new FieldMapper("supervisorStaffId"))
			.put("COMM_RECEIPT_PRINTER_ID", new FieldMapper("commReceiptPrinterId"))
			.put("PERSONNEL_TYPE", 			new FieldMapper("personnelType"))
			.put("AS_OF_DATE", 				new FieldMapper("asOfDate"))
			.put("EMERGENCY_CONTACT", 		new FieldMapper("emergencyContact"))
			.put("ROLE", 					new FieldMapper("role"))
			.put("SEX_CODE", 				new FieldMapper("sexCode"))
			.put("STATUS", 					new FieldMapper("status"))
			.put("SUSPENSION_DATE", 		new FieldMapper("suspensionDate"))
			.put("SUSPENSION_REASON", 		new FieldMapper("suspensionReason"))
			.put("FORCE_PASSWORD_CHANGE_FLAG", new FieldMapper("forcePasswordChangeFlag"))
			.put("LAST_PASSWORD_CHANGE_DATE", new FieldMapper("lastPasswordChangeDate"))
			.put("LICENSE_CODE", 			new FieldMapper("licenseCode"))
			.put("LICENSE_EXPIRY_DATE", 	new FieldMapper("licenseExpiryDate"))
			.put("CREATE_DATETIME", 		new FieldMapper("CreateDatetime"))
			.put("CREATE_USER_ID", 			new FieldMapper("CreateUserId"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("TITLE", 					new FieldMapper("title"))
			.put("NAME_SEQUENCE", 			new FieldMapper("nameSequence"))
			.put("QUEUE_CLUSTER_ID", 		new FieldMapper("queueClusterId"))
			.put("FIRST_LOGON_FLAG", 		new FieldMapper("firstLogonFlag"))
			.put("SUFFIX", 					new FieldMapper("Suffix"))
			.put("STAFF_NAME", 				new FieldMapper("lastName"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag")).build();
	private final Map<String, FieldMapper> modulePrivilegesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PARENT_ROLE_CODE", 		new FieldMapper("parentRoleCode"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("MODULE_NAME", 			new FieldMapper("moduleName"))
			.put("ACCESS_PRIVILEGE", 		new FieldMapper("accessPrivilege"))
			.put("ROLE_ID", 				new FieldMapper("roleId"))
			.put("ROLE_SEQ", 				new FieldMapper("roleSeq"))
			.put("VERIFICATION_FLAG", 		new FieldMapper("verificationFlag"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("ROLE_CODE", 				new FieldMapper("roleCode"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("ROLE_NAME", 				new FieldMapper("roleName")).build();
	
	private final Map<String, FieldMapper> profileCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", 		new FieldMapper("profileCode"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("PROFILE_VALUE", 			new FieldMapper("profileValue"))
			.put("PROFILE_VALUE_2", 			new FieldMapper("profileValue2"))
			.put("OLD_TABLE_NAME", 		new FieldMapper("oldTableName"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			
			.build();
	
	
	private final Map<String, FieldMapper> workflowScreensMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LABELS", 					new FieldMapper("description"))
			.put("ICON", 					new FieldMapper("icon"))
			.put("DEPTH", 					new FieldMapper("depth"))
			.put("WORKFLOW_SEQ", 			new FieldMapper("workFlowSeq"))
			.put("DATA", new FieldMapper("moduleName")).build();
	private final Map<String, FieldMapper> workflowFoldersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LABELS", 					new FieldMapper("description"))
			.put("DATA", 					new FieldMapper("workFlowCode"))
			.put("STATE", 					new FieldMapper("state"))
			.put("DEPTH", 					new FieldMapper("depth"))
			.put("WORKFLOW_SEQ", 			new FieldMapper("workFlowSeq"))
			.put("ICON", 					new FieldMapper("icon")).build();
	private final Map<String, FieldMapper> menuSecuritiesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MENU_ITEM", 				new FieldMapper("menuItem"))
			.put("MENU_ID", 				new FieldMapper("menuId")).build();
	private final Map<String, FieldMapper> caseloadsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID", 			new FieldMapper("caseloadId"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("CASELOAD_TYPE", 			new FieldMapper("caseloadType"))
			.put("LIST_SEQ", 				new FieldMapper("listSeq"))
			.put("TRUST_ACCOUNTS_FLAG",		new FieldMapper("trustAccountsFlag"))
			.put("ACCESS_PROPERTY_FLAG", 	new FieldMapper("acessPropertyFlag"))
			.put("TRUST_CASELOAD_ID", 		new FieldMapper("trustCaseLoadId"))
			.put("PAYROLL_FLAG", 			new FieldMapper("payrollFlag"))
			.put("ACTIVE_FLAG", 			new FieldMapper("activeFlag"))
			.put("DEACTIVATION_DATE", 		new FieldMapper("deactivationDate"))
			.put("COMMISSARY_FLAG", 		new FieldMapper("comissaryFlag"))
			.put("PAYROLL_TRUST_CASELOAD", 	new FieldMapper("payrollTrustCaseLoad"))
			.put("COMMISSARY_TRUST_CASELOAD", new FieldMapper("commissaryTrustCaseLoad"))
			.put("TRUST_COMMISSARY_CASELOAD", new FieldMapper("trustCommissaryCaseLoad"))
			.put("COMMUNITY_TRUST_CASELOAD", new FieldMapper("communityTrustCaseLoad"))
			.put("MDT_FLAG", 				new FieldMapper("mdtFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", 			new FieldMapper("createuserId"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDateTime"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("BILLING_FLAG", 			new FieldMapper("billingFlag"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag")).build();
	private final Map<String, FieldMapper> menuNodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MENU_ID", 				new FieldMapper("id"))
			.put("PARENT_MENU_ID", 			new FieldMapper("parentId"))
			.put("SORT_ORDER", 				new FieldMapper("order"))
			.put("STATE", 					new FieldMapper("state"))
			.put("LEVEL", 					new FieldMapper("level"))
			.put("MENU_ITEM", 				new FieldMapper("name"))
			.put("ICON", 					new FieldMapper("icon"))
			.put("DYNAMIC_FORM", 			new FieldMapper("dynamicForm"))
			.put("DATA1", 					new FieldMapper("href")).build();
			
			private final Map<String, FieldMapper> vheaderBlockMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID",          new FieldMapper("offenderId"))
			.put(" ALIAS_OFFENDER_ID  ", new FieldMapper("aliasOffenderId"))
			.put("OFFENDER_ID_DISPLAY",  new FieldMapper("offenderIdDisplay"))
			.put("LAST_NAME", 			 new FieldMapper("lastName"))
			.put("FIRST_NAME",           new FieldMapper("firstName"))
			.put("MIDDLE_NAME", 		 new FieldMapper("middleName"))
			.put("SUFFIX", 				 new FieldMapper("suffix"))
			.put("BIRTH_DATE", 			 new FieldMapper("birthDate"))
			.put("OFFENDER_BOOK_ID", 	 new FieldMapper("offenderBookId"))
			.put("BOOKING_NO", 			 new FieldMapper("bookingNo"))
			.put("BOOKING_BEGIN_DATE",   new FieldMapper("bookingBeginDate"))
			.put("BOOKING_END_DATE",     new FieldMapper("bookingEndDate"))
			.put("AGY_LOC_ID",           new FieldMapper("agyLocId"))
			.put("AGENCY_IML_ID",        new FieldMapper("agencyImlId"))
			.put("LIVING_UNIT_ID",       new FieldMapper("livingUnitId"))
			.put("DISCLOSURE_FLAG",      new FieldMapper("disclosureFlag"))
			.put("ACTIVE_FLAG", 		 new FieldMapper("activeFlag"))
			.put("BOOKING_STATUS",		 new FieldMapper("bookingStatus"))
			.put("LIVING_UNIT_DESCRIPTION", new FieldMapper("livingUnitDescription"))
			.put("IN_OUT_STATUS",        new FieldMapper("inOutStatus"))
			.put("STATUS_DISPLAY",       new FieldMapper("statusDisplay"))
			.put("ROOT_OFFENDER_ID",	 new FieldMapper("rootOffenderId"))
			.put("ASSIGNED_STAFF_ID",	 new FieldMapper("assignedStaffId"))
			.put("AGY_LOC_TYPE", 		 new FieldMapper("agyLocType"))
			.put("CREATE_AGY_LOC_ID",	 new FieldMapper("createAgyLocId"))
			.put("BOOKING_TYPE", 		 new FieldMapper("bookingType"))
			.put("BOOKING_CREATED_DATE", new FieldMapper("bookingCreatedDate"))
			.put("LOCATION_CODE", 		 new FieldMapper("locationCode"))
			.put("INTAKE_AGY_LOC_ID",	 new FieldMapper("intakeAgyLocId"))
			.put("COMMUNITY_ACTIVE_FLAG",new FieldMapper("communityActiveFlag"))
			.put("CREATE_INTAKE_AGY_LOC_ID", new FieldMapper("createIntakeAgyLocId"))
			.put("COMMUNITY_STATUS",     new FieldMapper("communityStatus"))
			.put("STATUS_REASON",        new FieldMapper("statusReason"))
			.put("HEADER_STATUS",        new FieldMapper("headerStatus"))
			.put("AGE", 				 new FieldMapper("age"))
			.put("GENDER", 				 new FieldMapper("gender"))
			.put("OFF_ALERTS",      	 new FieldMapper("offAlerts"))
			.put("STATUS_1", 			 new FieldMapper("status1"))
			.put("STATUS_2", 			 new FieldMapper("status2"))
			.put("STATUS_3", 			 new FieldMapper("status3"))
			.put("ETHNICITY",			 new FieldMapper("ethnicity"))
			.put("MOVEMENT_REASON", 	 new FieldMapper("movementReason"))
			.put("IMAGE_ID", 	         new FieldMapper("imageId"))
			.put("OFF_SUP_LEVEL",		 new FieldMapper("offSupLevel")).build();
			
	 private final Map<String, FieldMapper> recentoffenderMapping = new ImmutableMap.Builder<String, FieldMapper>()
					.put("OFFENDER_ID_DISPLAY",  new FieldMapper("offenderIdDisplay"))
					.put("LAST_NAME", 			 new FieldMapper("lastName"))
					.put("FIRST_NAME",           new FieldMapper("firstName"))
					.put("MIDDLE_NAME", 		 new FieldMapper("middleName"))
					.put("OFFENDER_BOOK_ID", 	 new FieldMapper("offenderBookId"))
					.put("AGY_LOC_ID",           new FieldMapper("agyLocId"))
					.put("AGENCY_IML_ID",        new FieldMapper("agencyImlId"))
					.put("OFFENDER_ID", 	     new FieldMapper("offenderId"))
					.put("CREATE_USER_ID", 	     new FieldMapper("createuserId"))
					.put("CASELOAD_ID", 		 new FieldMapper("caseLoadId"))
					.put("IMAGE_ID", 	         new FieldMapper("imageId")).build();
			
			
		private final Map<String, FieldMapper> helpMediaMapping = new ImmutableMap.Builder<String, FieldMapper>()
					.put("MODULE_NAME", 				new FieldMapper("moduleName"))
					.put("HELP_TYPE", 				new FieldMapper("helpType"))
					.put("HELP_URL", 	new FieldMapper("helpLink"))
					.put("HELP_DESC", 	new FieldMapper("helpDesc")).build();
					
	/**
	 * Creates new Omss40RepositoryImpl class Object
	 */
	public Omss40RepositoryImpl() {
		// Omss40RepositoryImpl
	}

	/**
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> menuRgRecordGroup(){
		final String sql = getQuery("OMSS40_FIND_MENURG");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,	ReferenceCodes.class, referenceCodesMapping);
		ArrayList<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList= (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("", e);
		}
		return refList;
	}

	/**
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgWorkflowRecordGroup(){
		final String sql = getQuery("OMSS40_FIND_RGWORKFLOW");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList= namedParameterJdbcTemplate.query(sql, referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("", e);
		}
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkStaffDspDescriptionRecordGroup(){
		final String sql = getQuery("OMSS40_FIND_CGFKSTAFFDSPDESCRIPTION");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList= namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("", e);
		}
		return refList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * @param paramBean
	 * @return List<SystemProfiles>
	 */
	public List<SystemProfiles> preFormPtypeClient(final SystemProfiles paramBean){
		final String sql = getQuery("OMSS40_OMSS40_PREFORM_CLIENT");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		List<SystemProfiles>returnObj= new ArrayList<SystemProfiles>();
	       returnObj =  namedParameterJdbcTemplate.query(sql,columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * @param paramBean
	 * @return List<SystemProfiles>
	 */
	public List<SystemProfiles> preFormPtypeSys(final SystemProfiles paramBean){
		final String sql = getQuery("OMSS40_OMSS40_PREFORM_SYS");
		List<SystemProfiles> returnObj = new ArrayList<SystemProfiles>();
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,systemProfilesMapping);
		returnObj = namedParameterJdbcTemplate.query(sql, columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called	 
	 * @param paramBean
	 * @return List<MenuSecurities>
	 *
	 */
	public List<MenuSecurities> whenNewFormInstanceMenuNameCur(final MenuSecurities paramBean){
		final String sql = getQuery("OMSS40_OMSS40_WHENNEWFORMINSTANCE_MENU_NAME_CUR");
		List<MenuSecurities> returnObj = new ArrayList<MenuSecurities>();
		final RowMapper<MenuSecurities> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, MenuSecurities.class,
				menuSecuritiesMapping);
		returnObj = namedParameterJdbcTemplate.query(sql, columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @param params
	 * @return  List<WorkFlowFolders>
	 */
	public List<WorkFlowFolders> buildworkFlowmenurgmainWorkflowCur(final WorkflowScreens paramBean){
		final String sql = getQuery("OMSS40_BUILD_WORKFLOW_MENU_RG_MAIN_WORKFLOW_CUR");
		List<WorkFlowFolders> returnList = new ArrayList<WorkFlowFolders>();
		final RowMapper<WorkFlowFolders> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, WorkFlowFolders.class,
				workflowFoldersMapping);
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("MODULE_NAME", paramBean.getModuleName(), "CASELOAD_TYPE", paramBean.getCaseLoadType()),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called	 
	 * @param paramBean
	 * @return WorkflowScreens
	 */
	public WorkflowScreens buildworkFlowmenurgsubWorkflowCur(final WorkflowScreens paramBean){
		final String sql = getQuery("OMSS40_BUILD_WORKFLOW_MENU_RG_SUB_WORKFLOW_CUR");
		final RowMapper<WorkflowScreens> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, WorkflowScreens.class,
				workflowScreensMapping);
		WorkflowScreens returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("WORKFLOW_CODE", paramBean.getWorkFlowCode()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * @param pUser
	 * @return String
	 *
	 */
	public String getCurrentCaseload(final String pUser){
		final String sql = getQuery("OMSS40_GET_CURRENT_CASELOAD");		
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("USER_ID", pUser), String.class);
		} catch (Exception e) {
			log.error("", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * @param paramBean
	 * @return List<MenuSecurities>
	 *
	 */
	public List<MenuSecurities> mainpopList(final MenuSecurities paramBean){
		final String sql = getQuery("OMSS40_MAIN_POP_LIST");
		List<MenuSecurities> returnObj = new ArrayList<MenuSecurities>();
		final RowMapper<MenuSecurities> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, MenuSecurities.class,
				menuSecuritiesMapping);
		returnObj = namedParameterJdbcTemplate.query(sql, columnRowMapper);
		return returnObj;
	}

	public List<Caseloads> getCaseloads(final StaffMembers searchBean){
		final String sql = getQuery("OMSS40_FIND_CGFKSTAFFDSPDESCRIPTION");
		final RowMapper<Caseloads> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class,
				caseloadsMapping);
		List<Caseloads> returnList = new ArrayList<Caseloads>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("userId", searchBean.getCreateUserId()), columnRowMapper);
		} catch (Exception e) {
			log.error("", e);
		}
		return returnList;
	}
	
	public List<VHeaderBlock> getCaseLoadAgencies(String userId) {
		final String sql = getQuery("OMSS40_GET_CASELOAD_AGENCIES");
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,VHeaderBlock.class,
				recentoffenderMapping);
		return namedParameterJdbcTemplate.query(sql,createParams("USER_ID", userId), columnRowMapper);
	}
	
	public String changeWorkingCaseLoad(final String lvCaseloadId){
		String returnValue = "";
		try{
			Map<String, Object> returnObject = null;
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			SqlParameter[] sqlParameters = new SqlParameter[2];
			sqlParameters = new SqlParameter[] {
					new SqlParameter("P_CASELOAD_ID", OracleTypes.VARCHAR),
					new SqlInOutParameter("P_RETURN", OracleTypes.VARCHAR)
					};
			SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
												.withProcedureName("CHANGE_WORKING_CASELOAD").declareParameters(sqlParameters);
			inParamMap.put("P_CASELOAD_ID", lvCaseloadId);
			inParamMap.put("P_RETURN", "false");
			SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
				returnObject = simpleJDBCCall.execute(inParameter);
			returnValue =   returnObject.get("P_RETURN").toString();
		}catch(Exception e){
			log.error("Exception : ",e);
		}
		return returnValue;
	}
	
	public List<MenuNode> getMenus(final String userId){
		final String sql = getQuery("OMSS40_MENU_LIST");
		final RowMapper<MenuNode> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, MenuNode.class,
				menuNodesMapping);
		final List<MenuNode> returnObj = namedParameterJdbcTemplate.query(sql, createParams("USER_ID", userId), columnRowMapper);
		return returnObj;
	}
	
	
	public List<ModulePrivileges> getUserRolesInfo(final String userId){
		final String sql = getQuery("OMSS40_USER_ROLEINFO");	
		final RowMapper<ModulePrivileges> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ModulePrivileges.class,
				modulePrivilegesMapping);
		List<ModulePrivileges> returnObj = namedParameterJdbcTemplate.query(sql, createParams("USER_ID", userId), columnRowMapper);
		return returnObj;
	}
	
	@Override
	public List<ProfileCodes> searchProfileCodes(final List<String> profileType) {
		final String sql = getQuery("OMSS40_GET_SYSTEM_PROFILES");
		List<ProfileCodes> profileCodesList = new ArrayList<ProfileCodes>();
		final RowMapper<ProfileCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileCodes.class,
				profileCodesMapping);
		try {
			profileCodesList =  namedParameterJdbcTemplate.query(sql,new MapSqlParameterSource("profiletype", profileType),
					columnRowMapper);
			
		} catch (EmptyResultDataAccessException e) {
			log.error("searchProfileCodes"+e.getMessage());
		}
		return profileCodesList;
	}
	
	public List<VHeaderBlock> getAssignedOffenderList(final String userId, final String currentCaseLoadType){
		final String sql = getQuery("OMSS40_ASSIGNED_OFFENDERS_LIST_NEW");	
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vheaderBlockMapping);
		List<VHeaderBlock> assignedOffenders = namedParameterJdbcTemplate.query(sql, createParams("USER_ID",userId,"currentCaseLoadType",currentCaseLoadType), columnRowMapper);
		return assignedOffenders;
	}
	
	public List<VHeaderBlock> updateRecentOffenderList(VHeaderBlock paramBean) {
		final String sql = getQuery("OMSS40_GET_USER_RECENT_OFFENDER");
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				recentoffenderMapping);
		List<VHeaderBlock> returnObj = null;
		try {
			if (paramBean.getUserId() != null && paramBean.getCaseLoadId() != null && paramBean.getOffenderId() != null) {
				returnObj = namedParameterJdbcTemplate.query(sql, createParams("USER_ID", paramBean.getUserId(),
						"CASELOAD_ID", paramBean.getCaseLoadId(), "OFFENDER_ID", paramBean.getOffenderId()),
						columnRowMapper);
				if (returnObj.size() == 0) {
					final String insertsql = getQuery("OMSS40_INSERT_USER_RECENT_OFFENDERS");
					Map<String, Object> paramMap = new HashMap<>();
					paramMap.put("offenderId", paramBean.getOffenderId());
					paramMap.put("offenderBookId", paramBean.getOffenderBookId());
					paramMap.put("userId", paramBean.getUserId());
					paramMap.put("createuserId", paramBean.getUserId());
					paramMap.put("caseLoadId", paramBean.getCaseLoadId());
					paramMap.put("activeDatetime", paramBean.getActiveDatetime());
					paramMap.put("createDatetime", paramBean.getCreateDatetime());
					namedParameterJdbcTemplate.update(insertsql, paramMap);
				} else if (returnObj.size() > 0) {
					final String updatesql = getQuery("OMSS40_UPDATE_USER_RECENT_OFFENDERS");
					SqlParameterSource namedParameters = new MapSqlParameterSource()
							.addValue("USER_ID", paramBean.getUserId()).addValue("OFFENDER_ID", paramBean.getOffenderId())
							.addValue("CASELOAD_ID", paramBean.getCaseLoadId())
							.addValue("ACTIVE_DATETIME", paramBean.getActiveDatetime());

					int status = namedParameterJdbcTemplate.update(updatesql, namedParameters);
				}
			}

		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return returnObj;
	}
	
	public List<VHeaderBlock> getRecentOffenderList(final String userId,final String caseLoadId, final Integer count ){
		final String sql = getQuery("OMSS40_GET_USER_RECENT_OFFENDERS");
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,VHeaderBlock.class,
				recentoffenderMapping);
		final List<VHeaderBlock> returnObj = namedParameterJdbcTemplate.query(sql,createParams("USER_ID", userId,"CASELOAD_ID",caseLoadId, "ROWNUM",count), columnRowMapper);
		return returnObj;
	}
	
	public String getServerTime() {
		final String sql = getQuery("OMSS40_GET_SERVER_TIME");
		return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), String.class);
	}

	@Override
	public String getCurrentCaseloadType(final String userId) {
		final String sql = getQuery("OMSS40_GET_CURRENT_CASELOADTYPE");		
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userId), String.class);
		} catch (Exception e) {
			log.error("getCurrentCaseloadType", e);
		}
		return returnList;
	}

	@Override
	public StaffMembers getCurrentStaffDetail(final String userId) {
		final String sql = getQuery("OMSS40_GET_CURRENT_STAFFDETAIL");	
		StaffMembers returnList = null;
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,StaffMembers.class,
				staffMembersMapping);
		try{
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userId),columnRowMapper);
		}catch (Exception e) {
			log.error("getCurrentStaffDetail", e);
		}
		return returnList;
	}
	
	@Override
	public List<HelpMedia> getHelpMedia(String moduleName) {
		final String sql = getQuery("OMSS40_HELP_VIDEO");
		final String sqlUrl=getQuery("OMSHELP_EXECUTE_QUERY"); 
		List<HelpMedia> helpMedias = null;
		List<BaseHelpUrl> baseHelpUrl = null;
		final RowMapper<HelpMedia> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, HelpMedia.class,helpMediaMapping);
		final RowMapper<BaseHelpUrl> columnRowMapperUrl = Row2BeanRowMapper.makeMapping(sqlUrl,BaseHelpUrl.class,baseHelpurlMapping);
		try {
			baseHelpUrl=namedParameterJdbcTemplate.query(sqlUrl, createParams(),columnRowMapperUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		try{
			helpMedias = namedParameterJdbcTemplate.query(sql, createParams("moduleName", moduleName),columnRowMapper);
		}catch (Exception e) {
			log.error("getHelpMedia", e);
		}
		for (HelpMedia helpMedia : helpMedias){
			if(helpMedia.getHelpType().equals("V")) {
				helpMedia.setHelpLink(baseHelpUrl.get(0).getBaseHelpVideoUrl()+"/"+helpMedia.getHelpLink());
			}else if(helpMedia.getHelpType().equals("P")){
				helpMedia.setHelpLink(baseHelpUrl.get(0).getBaseHelpPdfUrl()+"/"+helpMedia.getHelpLink());
			}else {
				helpMedia.setHelpLink(baseHelpUrl.get(0).getBaseHelpHtmlUrl()+"/"+helpMedia.getHelpLink());
			}
		}
		return helpMedias;
	}

	
	@Override
	public int iwpOnScreen(String moduleName) {
		int count;
		final String sql = getQuery("OMSS40_IWP_MODULE_TEMPLATE_MAPPING");
		count = namedParameterJdbcTemplate.queryForObject(sql, createParams("moduleName", moduleName), Integer.class);
		return count;
	}

	@Override
	public String getEncryPassword(String userName) {
		final String sql = getQuery("OMSS40_GET_ENCRY_PASSWORD");
		String returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userName), String.class);
		} catch (Exception e) {
			log.error("", e);
			returnVal = null;
		}
		return returnVal;
	}
	@Override
	public String getUserId(String mailId) {
		final String sql = getQuery("OMSS40_GET_USER_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("mailId", mailId), String.class);
	}

	@Override
	public String getEncryptPwd(String userId) {
		final String sql = getQuery("OMSS40_GET_ENCRYPT_PWD");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userId), String.class);
	}

	@Override
	public String getStatus(String userId) {
		final String sql = getQuery("OMSS40_GET_STATUS");
		String status = "";
		try {
			status= namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userId), String.class);
		} catch (Exception e) {
			log.error("getStatus" + e);
		}
		return status;
	}

	@Override
	public String getmailId(String userId) {
		final String sql = getQuery("OMSS40_GET_MAIL_ID");
		String mailId = "";
		try {
			mailId= namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userId), String.class);
		} catch (Exception e) {
			log.error("getmailId" + e);
		}
		return mailId;
	}

	@Override
	public List<ProfileCodes> specficSystemProfile() {
		// TODO Auto-generated method stub
		final String sql = getQuery("OMSS_SPECIFIC_SYSTEMPROFILE");
		final RowMapper<ProfileCodes> agencyLocationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ProfileCodes.class, profileCodesMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams(), agencyLocationsRowMapper);
	}
	

}

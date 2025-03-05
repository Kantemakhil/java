package net.syscon.s4.iwp.base.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.EoffenderUtilities;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.eoffender.beans.CaseLoadType;
import net.syscon.s4.eoffender.beans.EoffenderDetails;
import net.syscon.s4.eoffender.beans.FileLimits;
import net.syscon.s4.eoffender.beans.MetaDataParameters;
import net.syscon.s4.eoffender.beans.UploadMetaData;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.iwp.base.EoffenderRepository;
import oracle.jdbc.OracleTypes;

@Repository
public class EoffenderRepositoryImpl extends RepositoryBase implements EoffenderRepository {

	private static Logger log = LogManager.getLogger(EoffenderRepositoryImpl.class.getName());

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
	
	private final Map<String, FieldMapper> caseloadType = new ImmutableMap.Builder<String, FieldMapper>()
			.put("WORKING_CASELOAD_ID", 			new FieldMapper("workingCaseLoad"))		
			.put("CASELOAD_TYPE",	  			new FieldMapper("caseLoadType")).build();
	private final Map<String, FieldMapper> FileLimits = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE", 			new FieldMapper("profileValue"))		
			.put("PROFILE_CODE",	  			new FieldMapper("profileCode")).build();
	private final Map<String, FieldMapper> images = new ImmutableMap.Builder<String, FieldMapper>()
			.put("IMAGE_DATA", 				new FieldMapper("imageData")).build();
	/**
	 * get_user_from_key
	 */
	@Override
	public EoffenderDetails getEoffenderDetails(String keyLogin) {
		EoffenderDetails result = new EoffenderDetails();
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters = new SqlParameter[] {

				new SqlOutParameter("p_user_id", OracleTypes.VARCHAR),
				new SqlOutParameter("p_offender_book_id", OracleTypes.NUMBER),
				new SqlOutParameter("p_offender_id_display", OracleTypes.VARCHAR),
				new SqlOutParameter("p_object_type", OracleTypes.VARCHAR),
				new SqlOutParameter("p_object_id", OracleTypes.NUMBER),
				new SqlOutParameter("p_module_name", OracleTypes.VARCHAR),
				new SqlOutParameter("p_osuser", OracleTypes.VARCHAR),
				new SqlOutParameter("p_prison_location", OracleTypes.VARCHAR),
				new SqlOutParameter("p_off_sup_level", OracleTypes.VARCHAR),
				new SqlOutParameter("p_trim_user", OracleTypes.VARCHAR),
				new SqlOutParameter("p_status_display", OracleTypes.VARCHAR)};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OBJECTS")
				.withCatalogName("WEB_EOFFENDER_SUPPORT").withProcedureName("GET_USER_FROM_KEY")
				.declareParameters(sqlParameters);
		inParamMap.put("p_key", keyLogin);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			result.setUserId((String) returnObject.get("p_user_id"));

			result.setOffenderBookId(((BigDecimal) returnObject.get("p_offender_book_id")).longValue());
			result.setOffenderIdDisplay((String) returnObject.get("p_offender_id_display"));
			result.setObjectType((String) returnObject.get("p_object_type"));
			result.setObjectId(returnObject.get("p_object_id") != null ? ((BigDecimal) returnObject.get("p_object_id")).longValue() : null);
			result.setModuleName((String) returnObject.get("p_module_name"));
			result.setDescription(getModuleDescription(result.getModuleName()));
			result.setUserRole(getUserRoleInfo(result.getModuleName(),result.getUserId()));
			result.setOsUser((String)returnObject.get("p_osuser"));
			result.setPrisonLocation((String)returnObject.get("p_prison_location"));
			result.setOffSupLevel((String)returnObject.get("p_off_sup_level"));
			result.setTrimUser((String)returnObject.get("p_trim_user"));
			result.setStatusDisplay((String)returnObject.get("p_status_display"));
			result.setCurrentCaseLoad(getCurrentCaseLoad(result.getUserId()));
		} catch (Exception e) {
			log.error("populateOffensesData" + e.getMessage());
		}
		return result;
	}
	
	  private String getCurrentCaseLoad(String userId){
		  List<CaseLoadType> returnObj = new ArrayList<>();
		  String result ="";
		  final String sql = getQuery("GET_CURRENT_CASELOAD");
		  final RowMapper<CaseLoadType> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,	CaseLoadType.class, caseloadType);
			try {
				returnObj = namedParameterJdbcTemplate.query(sql,createParams("userId", userId), referenceCodeRowMapper);
				if(!returnObj.isEmpty()){
					result = returnObj.get(0).getCaseLoadType();
				}
			} catch (Exception e) {
	
			}
			return result;
	  }
	
	  private String getModuleDescription(String moduleName){
		  String result = "";
          if(moduleName.contains("_")){
        	  String[] str = moduleName.split("_");
        	  String modName = str[0]; 
        	  moduleName = modName;
          }
          final String sql = getQuery("GET_MODULE_DESCRIPTION");
          List<String> description = namedParameterJdbcTemplate.queryForList(sql,createParams("moduleName",moduleName),String.class);
          result = description.get(0);
          return result;

   }
	  private String  getUserRoleInfo(String moduleName, String userId){
		  final String sql = getQuery("USER_ROLEINFO");
			final RowMapper<ModulePrivileges> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ModulePrivileges.class,
					modulePrivilegesMapping);
			List<ModulePrivileges> userRoles = namedParameterJdbcTemplate.query(sql, createParams("USER_ID", userId, "MODULE_NAME",moduleName), columnRowMapper);
			String role="";
		   if (userRoles != null && !userRoles.isEmpty())
	         { 
				 for (ModulePrivileges pre : userRoles)
				   {
					 if(pre.getAccessPrivilege().equals("A") || pre.getAccessPrivilege().equals("V")) 
						 role = "full"; 
					  else 
						  role = "read"; 
					 }
		     } 
		   return role;
	  }

	@Override
	public List<UploadMetaData> ocdccaseUploadMetaData(MetaDataParameters metaDataParameters) {
		List<UploadMetaData> returnObj = new ArrayList<>();
		String sql="";
		
		try {
			
			if(EoffenderUtilities.isEmpty(metaDataParameters.getObjectType())){
				throw new Exception("Empty Object Type");
			}
			
			switch (metaDataParameters.getObjectType()) {
			case "WARRANT":
				sql = getQuery("OCDCCASE_METADATA");
				break;
			case "BAILDET":
				sql = getQuery("OCDCCASE_METADATA");
				break;
			case "SENTENCE":
				sql = getQuery("OCDCCASE_COMMUNITY_METADATA");
				break;
			default:
				throw new Exception("Invalid Object type");
			}
			returnObj = namedParameterJdbcTemplate.query(sql, createParams("objectId", metaDataParameters.getObjectId(),
					"offenderBookId", metaDataParameters.getOffenderBookId()), (ResultSet rs) -> {
						List<UploadMetaData> returnObj1 = new ArrayList<>();
						if (metaDataParameters.getObjectType().equals("SENTENCE")) {
							if (rs.next()) {
								returnObj1.add(new UploadMetaData("Order Type", rs.getString("ORDER_TYPE")));
								returnObj1.add(new UploadMetaData("Order Sub-Type",rs.getString("ORDER_CODE")));
								returnObj1.add(new UploadMetaData("Description",rs.getString("DESCRIPTION")));
							}
						} else {
							if (rs.next()) {
								returnObj1.add(new UploadMetaData("Court Date", rs.getString("EVENT_DATE")));
								returnObj1.add(new UploadMetaData("Court Location",
										getDescription(rs.getString("AGY_LOC_ID"))));
							}
						}
						return returnObj1;
					});
		} catch (Exception e) {
			log.error("ocdccaseUploadMetaData", e.getMessage());
		}
		return returnObj;
	}

	@Override
	public List<UploadMetaData> OCDREQUEUploadMetaData(MetaDataParameters metaDataParameters) {
		List<UploadMetaData> returnObj = new ArrayList<>();

		final String sql = getQuery("OCDREQUE_METADATA");
		try {
			returnObj = namedParameterJdbcTemplate.query(sql,createParams("offenderBookId",metaDataParameters.getOffenderBookId(),"requestSeq", metaDataParameters.getObjectId()),
					(ResultSet rs) -> {
						List<UploadMetaData> returnObj1 = new ArrayList<>();
						if (rs.next()) {
							returnObj1.add(new UploadMetaData("Order Type", rs.getString("order_type")));
							returnObj1.add(new UploadMetaData("Order Code", rs.getString("order_code")));
							returnObj1.add(new UploadMetaData("Description", rs.getString("DESCRIPTION")));
						}
						return returnObj1;
					});
		} catch (Exception e) {
			log.error("OCDREQUEUploadMetaData" + e.getMessage());
		}
		return returnObj;
	}

	@Override
	public List<UploadMetaData> OCDPROGRUploadMetaData(MetaDataParameters metaDataParameters) {
		List<UploadMetaData> returnObj = new ArrayList<>();

		final String sql = getQuery("OCDPROGR_METADATA");
		try {
			returnObj = namedParameterJdbcTemplate.query(sql, createParams("eventId", metaDataParameters.getObjectId(),"offenderBookId", metaDataParameters.getOffenderBookId()),
					(ResultSet rs) -> {
						List<UploadMetaData> returnObj1 = new ArrayList<>();
						if (rs.next()) {
							returnObj1.add(new UploadMetaData("Course Date", rs.getString("EVENT_DATE")));
							returnObj1.add(new UploadMetaData("Description", rs.getString("ACTIVITY_DESC")));
						}
						return returnObj1;
					});
		} catch (Exception e) {
			log.error("OCDPROGRUploadMetaData" + e.getMessage());
		}
		return returnObj;
	}

	@Override
	public List<UploadMetaData> OCUPATOFUploadMetaData(MetaDataParameters metaDataParameters) {
		List<UploadMetaData> returnObj = new ArrayList<>();

		final String sql = getQuery("OCUPATOF_METADATA");
		try {
			returnObj = namedParameterJdbcTemplate.query(sql, createParams("eventId", metaDataParameters.getObjectId(),"offenderBookId", metaDataParameters.getOffenderBookId()),
					(ResultSet rs) -> {
						List<UploadMetaData> returnObj1 = new ArrayList<>();
						if (rs.next()) {
							returnObj1.add(new UploadMetaData("Course Date", rs.getString("EVENT_DATE")));
						}
						return returnObj1;
					});
		} catch (Exception e) {
			log.error("OCUPATOFUploadMetaData" + e.getMessage());
		}
		return returnObj;
	}

	@Override
	public List<UploadMetaData> OIDHWDETUploadMetaData(MetaDataParameters metaDataParameters) {
		List<UploadMetaData> returnObj = new ArrayList<>();

		final String sql = getQuery("OIDHWDET_METADATA");
		try {
			returnObj = namedParameterJdbcTemplate.query(sql, createParams("hwdId", metaDataParameters.getObjectId(),"offenderBookId", metaDataParameters.getOffenderBookId()),
					(ResultSet rs) -> {
						List<UploadMetaData> returnObj1 = new ArrayList<>();
						if (rs.next()) {
							returnObj1.add(new UploadMetaData("Date Recevied", rs.getString("RECEIVED_DATE")));
							returnObj1.add(
									new UploadMetaData("Court Location", getDescription(rs.getString("ISSUING_AGY"))));
						}
						return returnObj1;
					});
		} catch (Exception e) {
			log.error("OIDHWDETUploadMetaData" + e.getMessage());
		}
		return returnObj;
	}

	@Override
	public List<UploadMetaData> OIIVNTIFUploadMetaData(MetaDataParameters metaDataParameters) {
		List<UploadMetaData> returnObj = new ArrayList<>();

		final String sql = getQuery("OIIVNTIF_METADATA");
		try {
			returnObj = namedParameterJdbcTemplate.query(sql,
					createParams("notificationHtyId", metaDataParameters.getObjectId(),"offenderBookId", metaDataParameters.getOffenderBookId()), (ResultSet rs) -> {
						List<UploadMetaData> returnObj1 = new ArrayList<>();
						if (rs.next()) {
							returnObj1.add(new UploadMetaData("Date", rs.getString("EVENT_DATE")));
							returnObj1.add(new UploadMetaData("Type", rs.getString("NOTIFICATION_CODE")));
						}
						return returnObj1;
					});
		} catch (Exception e) {
			log.error("OIIVNTIFUploadMetaData" + e.getMessage());
		}
		return returnObj;
	}

	@Override
	public List<UploadMetaData> OPDPDECIUploadMetaData(MetaDataParameters metaDataParameters) {
		List<UploadMetaData> returnObj = new ArrayList<>();
		final String sql = getQuery("OPDPDECI_METADATA");
		try {
			returnObj = namedParameterJdbcTemplate.query(sql,
					createParams("pbScheduleId", metaDataParameters.getObjectId()), (ResultSet rs) -> {
						List<UploadMetaData> returnObj1 = new ArrayList<>();
						if (rs.next()) {
							returnObj1.add(new UploadMetaData("Hearing Date", rs.getString("HEARING_DATE")));
							returnObj1.add(new UploadMetaData("Hearing Type", rs.getString("DESCRIPTION")));
						}
						return returnObj1;
					});
		} catch (Exception e) {
			log.error("OPDPDECIUploadMetaData" + e.getMessage());
		}
		return returnObj;
	}

	/*
	 * OCDUPROJ – Attendance Tab
	 * @see net.syscon.s4.iwp.base.EoffenderRepository#OCDUPROJ_02UploadMetaData(net.syscon.s4.eoffender.beans.MetaDataParameters)
	 */
	@Override
	public List<UploadMetaData> OCDUPROJ_02UploadMetaData(MetaDataParameters metaDataParameters) {
		List<UploadMetaData> returnObj = new ArrayList<>();
		final String sql = getQuery("OCDUPROJ_METADATA");
		try {
			returnObj = namedParameterJdbcTemplate.query(sql, createParams("eventId", metaDataParameters.getObjectId(),"offenderBookId", metaDataParameters.getOffenderBookId()),
					(ResultSet rs) -> {
						List<UploadMetaData> returnObj1 = new ArrayList<>();
						if (rs.next()) {
							returnObj1.add(new UploadMetaData("Course Date", rs.getString("EVENT_DATE")));
						}
						return returnObj1;
					});
		} catch (Exception e) {
			log.error("OCDUPROJUploadMetaData" + e.getMessage());
		}
		return returnObj;
	}

	/*
	 * OCDUPROJ – Allocations Tab
	 * @see net.syscon.s4.iwp.base.EoffenderRepository#OCDUPROJ_01UploadMetaData(net.syscon.s4.eoffender.beans.MetaDataParameters)
	 */
	@Override
	public List<UploadMetaData> OCDUPROJ_01UploadMetaData(MetaDataParameters metaDataParameters) {
		List<UploadMetaData> resultList = new ArrayList<>();
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] {

				new SqlOutParameter("P_PROJECT_CODE", OracleTypes.VARCHAR),
				new SqlOutParameter("P_PROJECT_DESC", OracleTypes.VARCHAR),
				new SqlOutParameter("P_PROVIDER_DESC", OracleTypes.VARCHAR),
				new SqlOutParameter("P_PROGRAM_DESC", OracleTypes.VARCHAR),
				new SqlOutParameter("P_MAX_CAPACITY", OracleTypes.NUMBER),
				new SqlOutParameter("P_PROGRAM_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_SCH_START_DATE", OracleTypes.DATE),
				new SqlOutParameter("P_SCH_END_DATE", OracleTypes.DATE) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OBJECTS")
				.withCatalogName("TAG_UNPAID_WORK").withProcedureName("GET_PROJECT_ALLOC_DETAILS")
				.declareParameters(sqlParameters);

		inParamMap.put("p_crs_acty_id", OCDUPROJCrsActyId(metaDataParameters.getObjectId()));

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			UploadMetaData uploadMetaData1 = new UploadMetaData("Service", (String) returnObject.get("P_PROGRAM_DESC"));
			UploadMetaData uploadMetaData2 = new UploadMetaData("Code", (String) returnObject.get("P_PROJECT_CODE"));
			UploadMetaData uploadMetaData3 = new UploadMetaData("Description",
					(String) returnObject.get("P_PROJECT_DESC"));
			resultList.add(uploadMetaData1);
			resultList.add(uploadMetaData2);
			resultList.add(uploadMetaData3);
		} catch (Exception e) {
			log.error("populateOffensesData" + e.getMessage());
		}
		return resultList;
	}
	
	
	private String OCDUPROJCrsActyId(Long offPrgrefId){
        final String sql=getQuery("OCDUPROJ_CRS_ACTY_ID");
        final String crsActyId = namedParameterJdbcTemplate.queryForObject(sql,createParams("offPrgrefId",offPrgrefId),String.class);
        return crsActyId;

 }
	

	private String getDescription(String agyLocId) {
		final String sql = getQuery("GET_AGY_LOC_DESCRIPTION");
		final String description = namedParameterJdbcTemplate.queryForObject(sql, createParams("agyLocId", agyLocId),
				String.class);
		return description;

	}

	@Override
    public List<UploadMetaData> OCDENFORUploadMetaData(MetaDataParameters metaDataParameters) {
           List<UploadMetaData> returnObj= new ArrayList<>();
           String proceedingType = OCDENFORProceedingType(metaDataParameters.getObjectId(),metaDataParameters.getOffenderBookId());
		try {
			returnObj.add(new UploadMetaData("Start Date", OCDENFORgetStartDate(metaDataParameters.getObjectId(),metaDataParameters.getOffenderBookId())));
			returnObj.add(new UploadMetaData("Proceeding", OCDENFORgetProceeding(proceedingType)));
		} catch (Exception e) {
			log.error("OCDENFORUploadMetaData" + e.getMessage());
		}
		return returnObj;
	}
	
	private String OCDENFORProceedingType(Long offenderProceedingId, Long offenderBookId){
        final String sql=getQuery("OCDENFOR_PROCEEDING_TYPE");
        final String proceedingType = namedParameterJdbcTemplate.queryForObject(sql,createParams("offenderProceedingId",offenderProceedingId,"offenderBookId",offenderBookId),String.class);
      
        return proceedingType;

 }
	
    private String OCDENFORgetStartDate(Long offenderProceedingId, Long offenderBookId){
           final String sql=getQuery("OCDENFOR_GET_START_DATE");
           final Date date = namedParameterJdbcTemplate.queryForObject(sql,createParams("offenderProceedingId",offenderProceedingId,"offenderBookId",offenderBookId),Date.class);
           DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
           String strDate = dateFormat.format(date);
           return strDate;

    }

    //OCDENFOR Proceeding function call
    private String OCDENFORgetProceeding(String proceedingType){
           String result=null;
           final String sqlQuery = getQuery("OCDENFOR_PROCEEDING_FUNCTION_CALL");
           result = namedParameterJdbcTemplate.queryForObject(sqlQuery,createParams("proceedingType",proceedingType),String.class);
           return result;

    }

	@Override
	public List<FileLimits> getEoffenderProfileValues() {
		List<FileLimits> fileLimitsValues = new ArrayList<>();
		  final String sql = getQuery("GET_EOFFENDER_FILELIMITS");
		  final RowMapper<FileLimits> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,FileLimits.class, FileLimits);
			try {
				fileLimitsValues = namedParameterJdbcTemplate.query(sql, referenceCodeRowMapper);
			} catch (Exception e) {
				// logger.error(""+e.getMessage());
			}

		return fileLimitsValues;
	}

	@Override
	public List<Images> imageExecuteQuery(final Images searchBean) {
		List<Images> imageList = new ArrayList<>();
		final String sql = queryBuilderFactory.getQueryBuilder(getQuery("EOFFENDER_IMAGE_FIND_IMAGES"), images).build();
		final RowMapper<Images> imagesRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class, images);
		try {
			imageList = namedParameterJdbcTemplate.query(sql,createParams("imageObjectId",searchBean.getImageObjectId()), imagesRowMapper);
		} catch (DataAccessException e) {			
			e.printStackTrace();
		}
		return imageList;
	}
	

}

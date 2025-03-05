package net.syscon.s4.iwp.base.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.eoffender.beans.DBDocument;
import net.syscon.s4.eoffender.beans.Document;
import net.syscon.s4.eoffender.beans.EoffenderSqlParameter;
import net.syscon.s4.eoffender.beans.EoffenderTemplate;
import net.syscon.s4.im.beans.IwpDocuments;
import net.syscon.s4.im.beans.IwpTemplates;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.iwp.base.EoffenderDocumentRepository;
import net.syscon.s4.iwp.beans.ManageDocumentRequest;
import net.syscon.s4.sa.admin.beans.IwpBookmarks;

@Repository
public class EoffenderDocumentRepositoryImpl extends RepositoryBase implements EoffenderDocumentRepository {

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static Logger logger = LogManager.getLogger(EoffenderDocumentRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> eOffenderTemplateMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TEMPLATE_ID", new FieldMapper("templateId"))
			.put("DESCRIPTION",  			new FieldMapper("description"))
			.put("TEMPLATE_NAME",  			new FieldMapper("code"))
			.put("IS_TEMPLATE",  			new FieldMapper("isGenTemplate"))
			.build();

  	private final Map<String, FieldMapper> eOffenderDBDocumentMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ID",  			new FieldMapper("idGenerated"))
			.put("OFF_BOOK_NO",  			new FieldMapper("offenderBookingNo"))
			.put("OFF_BOOK_ID",  			new FieldMapper("offenderBookId"))
			.put("TEMPLATE_NAME",  			new FieldMapper("templateName"))
			.put("DOCUMENT_NAME",  			new FieldMapper("documentName"))
			.put("DOCUMENT_TRIM_ID", 				new FieldMapper("documentId"))
			.put("FILE_DATA", 			new FieldMapper("fileData"))
			.put("DOB", 			new FieldMapper("offDOB"))
			.put("RECORD_CREATOR", 			new FieldMapper("documentAuthor"))
			.put("TRIM_USER", 			new FieldMapper("trimUser"))
			.put("OFF_NAME", 			new FieldMapper("offednerName"))
			.put("OFF_DISPLAY_ID", 			new FieldMapper("offenderDisplayId"))
			.put("EXT", 			new FieldMapper("fileExt"))
			.put("STATUS", 			new FieldMapper("status"))
			.build();
  	
  	private final Map<String, FieldMapper> IWPTemplateMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TEMPLATE_ID", 				new FieldMapper("templateId"))
			.put("TEMPLATE_NAME",  				new FieldMapper("templateName"))
			.put("DESCRIPTION",  				new FieldMapper("description"))
			.put("LOCATION",  					new FieldMapper("location"))
			.put("ACTIVE_FLAG",  				new FieldMapper("activeFlag"))
			.put("TEMPLATE_BODY",  				new FieldMapper("templateBody"))
			.put("DATE_CREATED", 				new FieldMapper("dateCreated"))
			.put("USER_CREATED",  				new FieldMapper("userCreated"))
			.put("LOCK_PASSWORD",  				new FieldMapper("lockPassword"))
			.put("OBJECT_TYPE",  				new FieldMapper("objectType"))
			.put("CREATE_DATETIME",  			new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID",  			new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME",  			new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID",  			new FieldMapper("modifyUserId"))
			.put("EDRMS_RECORD_NO",  			new FieldMapper("edrmsRecordNo"))
			.put("EDRMS_FOLDER",  				new FieldMapper("edrmsFolder"))
			.build();
  	private final Map<String, FieldMapper> iwpBookmarksMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("BOOKMARK_NAME", new FieldMapper("bookmarkName"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("BOOKMARK_TYPE", new FieldMapper("bookmarkType"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USERID", new FieldMapper("createUserId"))
			.put("DATE_CREATED", new FieldMapper("dateCreated"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USERID", new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("SQL_TEXT", new FieldMapper("sqlText"))
			.put("SQL_VERIFIED_FLAG", new FieldMapper("sqlVerifiedFlag"))
			.put("USER_CREATED", new FieldMapper("userCreated"))
			.build();
  	private final Map<String, FieldMapper> images = new ImmutableMap.Builder<String, FieldMapper>()
			.put("IMAGE_ID", 				new FieldMapper("imageId"))
			.put("CAPTURE_DATE", 			new FieldMapper("captureDate"))
			.put("IMAGE_OBJECT_TYPE", 		new FieldMapper("imageObjectType"))
			.put("IMAGE_OBJECT_ID", 		new FieldMapper("imageObjectId"))
			.put("IMAGE_VIEW_TYPE", 		new FieldMapper("imageViewType"))
			.put("IMAGE_OBJECT_SEQ", 		new FieldMapper("imageObjectSeq"))
			.put("IMAGE_THUMBNAIL", 		new FieldMapper("imageThumbnail"))
			.put("ACTIVE_FLAG", 			new FieldMapper("activeFlag"))
			.put("ORIENTATION_TYPE", 		new FieldMapper("orientationType"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("COU", 					new FieldMapper("cou")).build();

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
	@Cacheable("getBookmarkListEoffenderDocumentRequest")
	@Override
	public List<String> getBookmarkList(ManageDocumentRequest manageDocumentRequest) {
		/*String getBookmarkListSql = "SELECT IWP_BOOKMARKS_GROUPS.BOOKMARK_NAME FROM IWP_BOOKMARKS_GROUPS "
				+ "inner join IWP_TEMPLATES IWP_TEMPLATES ON "
				+ "IWP_TEMPLATES.TEMPLATE_ID = IWP_BOOKMARKS_GROUPS.TEMPLATE_ID "
				+ "WHERE IWP_TEMPLATES.TEMPLATE_ID = :EDRMS_RECORD_NO";*/
		String getBookmarkListSql = "SELECT IWP_BOOKMARKS.BOOKMARK_NAME FROM IWP_BOOKMARKS";
		List<String> bookmarkList = namedParameterJdbcTemplate.queryForList(getBookmarkListSql, createParams(), String.class);
		return bookmarkList;
	}


	@Cacheable("getBookmarkSqlMappingBookmarkList")
	@Override
	public Map<String, String> getBookmarkSqlMapping(List<String> bookmarkList) {
		String getBookmarkSqlMappingSql = "SELECT BOOKMARK_NAME, SQL_TEXT FROM IWP_BOOKMARKS WHERE BOOKMARK_NAME IN (:BOOKMARK_NAME)";
		Map<String, Object> namedParametersQuery = new HashMap<>();
		namedParametersQuery.put("BOOKMARK_NAME", bookmarkList);

		Map<String, String> bookmarkSqlMap = new HashMap<>();

		namedParameterJdbcTemplate.query(getBookmarkSqlMappingSql, namedParametersQuery, (ResultSetExtractor<Map<String,String>>) rs -> {
		    while(rs.next()){
		    		bookmarkSqlMap.put(rs.getString("BOOKMARK_NAME"), rs.getString("SQL_TEXT"));
		    }
		    return bookmarkSqlMap;
		});

		return bookmarkSqlMap;
	}


	@Cacheable("getBookmarkParameterMappingBookmarkList")
	@Override
	public Map<String, List<EoffenderSqlParameter>> getBookmarkParameterMapping(List<String> bookmarkList) {
		String getBookmarkParameterMappingSql = "SELECT BOOKMARK_NAME, PARAMETER_NAME, PARAMETER_TYPE, PARAMETER_DATA_TYPE,MODULE_BLOCK_CODE,FIELD FROM IWP_BOOKMARK_PARAMETERS WHERE BOOKMARK_NAME IN (:BOOKMARK_NAME)";
		Map<String, Object> namedParametersQuery = new HashMap<>();
		namedParametersQuery.put("BOOKMARK_NAME", bookmarkList);

		Map<String, List<EoffenderSqlParameter>> bookmarkParameterMap = new HashMap<>();

		namedParameterJdbcTemplate.query(getBookmarkParameterMappingSql, namedParametersQuery, (ResultSetExtractor<Map<String,List<EoffenderSqlParameter>>>) rs -> {
		    while(rs.next()){

		    	EoffenderSqlParameter eoffenderSqlParameter = new EoffenderSqlParameter();
		    	eoffenderSqlParameter.setParameterName(rs.getString("PARAMETER_NAME"));
		    	eoffenderSqlParameter.setParameterDataType(rs.getString("PARAMETER_DATA_TYPE"));
		    	eoffenderSqlParameter.setParameterType(rs.getString("PARAMETER_TYPE"));
		    	eoffenderSqlParameter.setModuleBlockCode(rs.getString("MODULE_BLOCK_CODE"));
		    	
		    	eoffenderSqlParameter.setField(rs.getString("FIELD"));
		    	
		    	if(bookmarkParameterMap.containsKey(rs.getString("BOOKMARK_NAME"))){
		    		bookmarkParameterMap.get(rs.getString("BOOKMARK_NAME")).add(eoffenderSqlParameter);
		    	} else {
		    		List<EoffenderSqlParameter> sqlParameterTempList = new ArrayList<>();
		    		sqlParameterTempList.add(eoffenderSqlParameter);
		    		bookmarkParameterMap.put(rs.getString("BOOKMARK_NAME"), sqlParameterTempList);
		    	}
		    }
		    return bookmarkParameterMap;
		});

		return bookmarkParameterMap;
	}


	@Cacheable("getBookmarkValueMapBookmarkResolvedSqlMap")
	@Override
	public Map<String, List<String>> getBookmarkValueMap(Map<String, String> bookmarkResolvedSqlMap, String userName) {
		//ExecutorService executor = Executors.newFixedThreadPool(bookmarkResolvedSqlMap.size());
		CountDownLatch latch = new CountDownLatch(bookmarkResolvedSqlMap.size());
//		List<Runnable> runnableTaskList = new ArrayList<>();
		Map<String, List<String>> bookmarkValueMap;
		try {
			bookmarkValueMap = new HashMap<>();
			List<IwpBookmarks> compBookMarks = getIWPBookmarksByType("COMP");
			List<IwpBookmarks> binaryBookMarks = getIWPBookmarksByType("BINARY");
			List<String> compBookMarkNames = compBookMarks.stream().map(obj -> obj.getBookmarkName()).collect(Collectors.toList());
			List<String> binaryBookMarkNames = binaryBookMarks.stream().map(obj -> obj.getBookmarkName()).collect(Collectors.toList());
			for (String bookmark : bookmarkResolvedSqlMap.keySet()) {
 
				String getbookmarkValueSql = bookmarkResolvedSqlMap.get(bookmark);
				
				/*if(getbookmarkValueSql.matches(".*?\\bUSER\\b.*?") ){
					getbookmarkValueSql = getbookmarkValueSql.substring(0, getbookmarkValueSql.length() - 4) + "'"+userName+"'";
				}*/
				
				final String bookMarkSql = getbookmarkValueSql;
				Runnable runnableTask = () -> {
					List<String> bookmarkValueList = null;
					try {
						
						if(binaryBookMarkNames.contains(bookmark)) {
							final RowMapper<Images> imagesRowMapper = Row2BeanRowMapper.makeMapping(bookMarkSql, Images.class, images);
							List<Images> imagesList = namedParameterJdbcTemplate.query(bookMarkSql, createParams(), imagesRowMapper);
							if(imagesList!=null && imagesList.size()>0) {
								String imageMeta = "\"imageString\":\"data:image/JPEG;base64,<base_64_data>\",\"isMetaFile\":false,\"isCompressed\":false,\"width\":100,\"height\":100,\"iscrop\":false";
								String base64Image = Base64.getEncoder().encodeToString(imagesList.get(0).getImageThumbnail());
								if(bookmarkValueList == null) {
									bookmarkValueList = new ArrayList<>();
								}
								bookmarkValueList.add(imageMeta.replace("<base_64_data>",base64Image));
							}
						}else if(compBookMarkNames.contains(bookmark)) {
							 List<Map<String,Object>> valueList = namedParameterJdbcTemplate.queryForList(bookMarkSql, createParams());
							 if(!valueList.isEmpty()) {
								 for(Map<String,Object> valueMap : valueList) {
									 if(!valueMap.keySet().isEmpty()) {
										 valueMap.keySet().forEach(key -> {
											 List<String> outList = new ArrayList<>();
											 String finalKey = String.join(".", bookmark, key).toUpperCase();
											 if(valueMap.get(key)!= null && !valueMap.get(key).equals("")) {
												 outList.add(String.valueOf(valueMap.get(key)));
												 if(bookmarkValueMap.containsKey(finalKey) && !bookmarkValueMap.get(finalKey).isEmpty()) {
													 bookmarkValueMap.get(finalKey).removeIf(String::isEmpty);
													 bookmarkValueMap.get(finalKey).addAll(outList);
												 } else {
													 bookmarkValueMap.put(finalKey, outList);
												 }
											 }else {
												 if(!bookmarkValueMap.containsKey(finalKey)){
													 outList.add("");
													 bookmarkValueMap.put(finalKey, outList);
												 }
												 
												
											 }
										 });
									 }
								 }
							 }
						} else {
							bookmarkValueList = namedParameterJdbcTemplate.queryForList(bookMarkSql, createParams(), String.class);
							
							
						}
					} catch (Exception e) {
							// e.printStackTrace();
							//
					}
					if(!compBookMarkNames.contains(bookmark)) {
						bookmarkValueMap.put(bookmark, bookmarkValueList);
					}
					latch.countDown();
				};
				taskExecutor.execute(runnableTask);
				}
			try {
				latch.await();
			} catch (InterruptedException E) {

			}
		} finally{
			//executor.shutdown();
		}

		return bookmarkValueMap;
	}

	@Cacheable("getTemplatesModuleNameStaffRoleList")
	@Override
	public List<EoffenderTemplate> getTemplates(String moduleName, List<String> staffRoleList, String activeFlag) {
		
		logger.info("getTemplatesModuleNameStaffRoleList moduleName :: "+moduleName);
		
		List<EoffenderTemplate> templateList =new ArrayList<>();
		// ROLE_ID in case of NSW DB , ROLE_CODE in case of Local Elite
		 String getTemplateList="";
		if(moduleName.equals("ALL_OFF_DOC")){
			getTemplateList = "SELECT DISTINCT TEMPLATE_NAME ,DESCRIPTION, IT.TEMPLATE_ID AS TEMPLATE_ID, "
					+ "CASE WHEN IT.TEMPLATE_BODY IS NOT NULL THEN 'TRUE' ELSE 'FALSE' END AS IS_TEMPLATE "
					+ "FROM "
					+ "IWP_TEMPLATE_MODULES ITM INNER JOIN IWP_TEMPLATES IT "
					+ "ON IT.TEMPLATE_ID = ITM.TEMPLATE_ID INNER JOIN IWP_TEMPLATE_ROLES ITR ON "
					+ "IT.TEMPLATE_ID = ITR.TEMPLATE_ID WHERE ITR.ROLE_CODE  IN (:ROLES) AND IT.ACTIVE_FLAG='Y' AND IT.OBJECT_TYPE <> 'EMAIL' AND ITM.ACTIVE_FLAG = :ACTIVE_FLAG";
		}else{
			 getTemplateList = "SELECT DISTINCT TEMPLATE_NAME ,DESCRIPTION, IT.TEMPLATE_ID AS TEMPLATE_ID, "
			 		+ "CASE WHEN IT.TEMPLATE_BODY IS NOT NULL THEN 'TRUE' ELSE 'FALSE' END AS IS_TEMPLATE "
					+ "FROM "
					+ "IWP_TEMPLATE_MODULES ITM INNER JOIN IWP_TEMPLATES IT "
					+ "ON IT.TEMPLATE_ID = ITM.TEMPLATE_ID INNER JOIN IWP_TEMPLATE_ROLES ITR ON "
					+ "IT.TEMPLATE_ID = ITR.TEMPLATE_ID WHERE ITM.MODULE_NAME = :MODULE_NAME AND ITR.ROLE_CODE  IN (:ROLES) AND IT.ACTIVE_FLAG='Y' AND IT.OBJECT_TYPE <> 'EMAIL' AND ITM.ACTIVE_FLAG = :ACTIVE_FLAG";
			 
			 /*if("Y".equalsIgnoreCase(activeFlag)) {
				 getTemplateList = getTemplateList + "AND ITM.ACTIVE_FLAG = :ACTIVE_FLAG";
			 }*/
					
			
		}
		
		final RowMapper<EoffenderTemplate> eoffenderRowMapper = Row2BeanRowMapper.makeMapping(getTemplateList, EoffenderTemplate.class, eOffenderTemplateMapping);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		Map<String, List<String>> namedParametersQuery = new HashMap<>();
		/*namedParametersQuery.put("ROLES", staffRoleList);
		parameters.addValues(namedParametersQuery);*/
		parameters.addValue("MODULE_NAME", moduleName);
		parameters.addValue("ROLES", staffRoleList);
		if("Y".equalsIgnoreCase(activeFlag)) {
			parameters.addValue("ACTIVE_FLAG", activeFlag);
		}

		try {
			templateList =  namedParameterJdbcTemplate.query(getTemplateList, parameters, eoffenderRowMapper);
			} catch (Exception e) {
				logger.error("getTemplates :: "+e.getMessage());
			}

		HashSet<EoffenderTemplate> eoffenderTemplateSet = new HashSet<>(templateList);

		return eoffenderTemplateSet.stream().collect(Collectors.toList());
	}


	@Cacheable("getStaffRoles")
	@Override
	public List<String> getStaffRoles(String userId) {
		logger.info("getStaffRoles userId :: "+userId);
		List<String> staffRoleList = new ArrayList<>();
		String getStaffRolesQuery = "SELECT DISTINCT(SMR.ROLE_ID) FROM STAFF_MEMBER_ROLES SMR inner join STAFF_MEMBERS SM ON SMR.STAFF_ID = SM.STAFF_ID WHERE SM.USER_ID = :USER_ID";           // ROLE_ID in case of NSW DB , ROLE_CODE in case of Local Elite
		try {
			staffRoleList =  namedParameterJdbcTemplate.queryForList(getStaffRolesQuery,createParams("USER_ID", userId), String.class);
			
			} catch (Exception e) {
				logger.error("getOffenderOffences"+e.getMessage());
			}

		return staffRoleList;
	}

	@Override
	public Optional<String> generateDocumentName(){
		String generateFileNameSql = "SELECT OMS_MISCELLANEOUS.GET_PROFILE_VALUE('CLIENT', 'TEMPLATE_DST')||'\\'||TO_CHAR(SYSDATE, 'DD_MM_YYYY_HH24_MI_SSSSS')|| '_IWP' FROM DUAL";
		String documentname =  namedParameterJdbcTemplate.queryForObject(generateFileNameSql, createParams(), String.class);
		Optional<String> optionalDocumentName = Optional.ofNullable(documentname).filter(s -> !s.isEmpty());

		return optionalDocumentName;
	}

	@Override
	public void saveFileAsBlobInDB(Document document, byte[] bytes) throws IOException {
		logger.info("saveFileAsBlobInDB offenderBookId :: "+document.getOffenderBookId());
		String saveBlobQuery = "INSERT INTO EOFFENDER_DOCUMENTS (ID_GENERATED, OFFENDER_BOOK_ID, DOCUMENT_ID, DOCUMENT_NAME, TEMPLATE_NAME, FILE_DATA, EXT) values "
				+ "(ID_GENERATED_SEQ.nextval, :OFFENDER_BOOK_ID, :DOCUMENT_ID, :DOCUMENT_NAME, :TEMPLATE_NAME, :FILE_DATA, :fileEXt)";
		MapSqlParameterSource saveBlobParams = new MapSqlParameterSource();
	      saveBlobParams.addValue("OFFENDER_BOOK_ID", document.getOffenderBookId());
	      saveBlobParams.addValue("DOCUMENT_ID", document.getDocumentId());
	      saveBlobParams.addValue("DOCUMENT_NAME", document.getDocumentName().concat(".docx"));
	      saveBlobParams.addValue("TEMPLATE_NAME", document.getDocumentType());
	      saveBlobParams.addValue("fileEXt", document.getFileExt());
//	      saveBlobParams.addValue("TRIM_USER", document.getTrimUser());
	      saveBlobParams.addValue("FILE_DATA",  new SqlLobValue(new ByteArrayInputStream(bytes), 
	    		   bytes.length, new DefaultLobHandler()), Types.BLOB);
//	      saveBlobParams.addValue("TIMESTAMP", System.currentTimeMillis());
	      KeyHolder keyHolder = new GeneratedKeyHolder();  
//	      		int result = namedParameterJdbcTemplate.update(saveBlobQuery, saveBlobParams);
		
		String getBlobFromDBQuery = "SELECT MAX(ID_GENERATED) FROM EOFFENDER_DOCUMENTS";
		
		int key = namedParameterJdbcTemplate.queryForObject(getBlobFromDBQuery, createParams(), Integer.class);
		
		
		logger.info("saveFileAsBlobInDB Save file in DB result :: "+ document.getOffenderBookId());
		//System.out.println("Save file in DB result :: "+result);
	}

	
	@Override
	public DBDocument getTrimDocumentAsBlobFromDB(String key) {
		BigDecimal id = new BigDecimal(key);
		String getBlobFromDBQuery = "SELECT * FROM OFFENDER_TEMP_DOC WHERE ID = :ID_GENERATED";
		logger.info("ID is "+key);
		RowMapper<DBDocument> eoffenderRowMapper = Row2BeanRowMapper.makeMapping(getBlobFromDBQuery, DBDocument.class, eOffenderDBDocumentMapping);
		DBDocument dbDocument = namedParameterJdbcTemplate.queryForObject(getBlobFromDBQuery, createParams("ID_GENERATED", id), eoffenderRowMapper);
		dbDocument.setDocumentName(dbDocument.getDocumentName().replace("_", "/"));
		return dbDocument;
	}
	
	@Override
	public boolean insertRecordforEditDocument(Document document) {
		
		BigDecimal tempID  = new BigDecimal(document.getTemproaryDocumentId());
		
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO OFFENDER_TEMP_DOC (ID, TRIM_USER, RECORD_CREATOR, OFF_BOOK_NO, OFF_DISPLAY_ID, DOB, DOCUMENT_NAME, DOCUMENT_TRIM_ID, EXT, STATUS, CREATE_DATE) VALUES (?, ?, ?,?,?,?,?,?,?,?,?)");
			ps.setBigDecimal(1, tempID);
			ps.setString(2, document.getTrimUser());
			ps.setString(3, document.getDocumentAuthor());
			ps.setString(4, document.getOffenderBookingNo());
			ps.setString(5, document.getOffenderDisplayId());
			ps.setString(6, document.getOffDOB());
			ps.setString(7, document.getDocumentName());
			ps.setString(8, document.getDocumentId());
			ps.setString(9, document.getFileExt());
			ps.setString(10, "D");
			ps.setDate(11, new java.sql.Date(System.currentTimeMillis()));
			return ps;
		});
		return true;
	}
	
	
	@Override
	public String verifyEditedDocCheckedIn(String docId) {
		String response = "";
		String getBlobFromDBQuery = "SELECT * FROM OFFENDER_TEMP_DOC WHERE DOCUMENT_TRIM_ID = :DOC_ID order by create_date desc";
		logger.info("docId is "+docId);
		RowMapper<DBDocument> eoffenderRowMapper = Row2BeanRowMapper.makeMapping(getBlobFromDBQuery, DBDocument.class, eOffenderDBDocumentMapping);
		List<DBDocument> dbDocuments = namedParameterJdbcTemplate.query(getBlobFromDBQuery, createParams("DOC_ID", docId), eoffenderRowMapper);
		logger.info("Response is :" +dbDocuments);
		if(dbDocuments!=null & dbDocuments.size()>=1) {
			DBDocument dbDocument = dbDocuments.get(0);
			if(dbDocument !=null && dbDocument.getStatus().equalsIgnoreCase("C")) {
				response = "CHECKEDIN";
			} else if (dbDocument !=null && dbDocument.getStatus().equalsIgnoreCase("D")) {
				response = "INPROCESS";
			} else if (dbDocument !=null && dbDocument.getStatus().equalsIgnoreCase("E")){
				response = "ERROR";
			}
		}
		logger.info("Return Response  is " + response);
		return response;
	}
	
	
	@Override
	public String verifyGeneratedDocCommited(String docId) {
		String response = "";
		String getBlobFromDBQuery = "SELECT * FROM OFFENDER_TEMP_DOC WHERE ID = :DOC_ID";
		logger.info("docId is "+docId);
		RowMapper<DBDocument> eoffenderRowMapper = Row2BeanRowMapper.makeMapping(getBlobFromDBQuery, DBDocument.class, eOffenderDBDocumentMapping);
		List<DBDocument> dbDocuments = namedParameterJdbcTemplate.query(getBlobFromDBQuery, createParams("DOC_ID", docId), eoffenderRowMapper);
		logger.info("Response is :" +dbDocuments);
		if(dbDocuments!=null & dbDocuments.size()>=1) {
			DBDocument dbDocument = dbDocuments.get(0);
			if (dbDocument !=null && dbDocument.getStatus().equalsIgnoreCase("D")) {
				response = "INPROCESS";
			} else {
				response = "NOT YET";
			}
		}
		logger.info("Return Response  is " + response);
		return response;
	}
   @Override
   public String updateDocStatus(BigDecimal id, String status) throws Exception {
	   jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection
					.prepareStatement("UPDATE EOFFENDER_TEMP_DOC SET STATUS = ? WHERE ID = ?");
			ps.setString(1, status);
			ps.setBigDecimal(2, id);
			return ps;
		});
	   return "SUCCESS";
   }

   	@Override
   	public List<IwpTemplates> getIWPTemplatesByType(String templateType) {
		List<IwpTemplates> returnObj= null;

		final String sql= "SELECT TO_CHAR(TEMPLATE_ID) CODE, TEMPLATE_NAME, DESCRIPTION, OBJECT_TYPE FROM IWP_TEMPLATES WHERE OBJECT_TYPE = :templateType AND ACTIVE_FLAG = 'Y'";

		final RowMapper<IwpTemplates> rowMapper = Row2BeanRowMapper.makeMapping(sql, IwpTemplates.class, IWPTemplateMapping);
		 try{
			 returnObj = namedParameterJdbcTemplate.query(sql,createParams("templateType",templateType), rowMapper);
			 }catch(Exception e){
				 returnObj = Collections.emptyList();
			 }
			return returnObj;
	}
   	private List<IwpBookmarks> getIWPBookmarksByType(String bokmarkType) {
		List<IwpBookmarks> returnObj= null;

		final String sql= "SELECT BOOKMARK_NAME, BOOKMARK_TYPE FROM IWP_BOOKMARKS WHERE BOOKMARK_TYPE = :bokmarkType AND ACTIVE_FLAG = 'Y'";

		final RowMapper<IwpBookmarks> rowMapper = Row2BeanRowMapper.makeMapping(sql, IwpBookmarks.class, iwpBookmarksMapping);
		 try{
			 returnObj = namedParameterJdbcTemplate.query(sql,createParams("bokmarkType", bokmarkType), rowMapper);
			 }catch(Exception e){
				 returnObj = Collections.emptyList();
			 }
			return returnObj;
	}


   	@Override
	public Integer updateDocumentName(List<IwpDocuments> documentList) {
		final String sql = getQuery("UPDATE_DOCUMENT_NAME");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final IwpDocuments list : documentList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("error in updateDocumentName"+e.getMessage() );
		}
		if (documentList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}


	@Override
	public List<String> getStaffRoleCodes(String staffId) {
		logger.info("getStaffRoleCodes userId :: "+staffId);
		List<String> staffRoleList = new ArrayList<>();
		String getStaffRoleCodesQuery = "SELECT DISTINCT(SMR.ROLE_CODE) FROM STAFF_MEMBER_ROLES SMR inner join STAFF_MEMBERS SM ON SMR.STAFF_ID = SM.STAFF_ID WHERE SM.USER_ID = :USER_ID";           // ROLE_ID in case of NSW DB , ROLE_CODE in case of Local Elite
		try {
			staffRoleList =  namedParameterJdbcTemplate.queryForList(getStaffRoleCodesQuery,createParams("USER_ID", staffId), String.class);
			
			} catch (Exception e) {
				logger.error("getStaffRoleCodes"+e.getMessage());
			}

		return staffRoleList;
	}


	@Override
	public List<ModulePrivileges> getUserModuleAccess(String moduleName, String userId) {
		final String sql = getQuery("GET_USER_MODULE_ACCESS");
		List<ModulePrivileges> userRoles=null;
			final RowMapper<ModulePrivileges> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ModulePrivileges.class,
					modulePrivilegesMapping);
			try {
				 userRoles = namedParameterJdbcTemplate.query(sql, createParams("USER_ID", userId, "MODULE_NAME",moduleName), columnRowMapper);
					
			}catch(Exception e) {
				logger.error("error in getUserModuleAccess"+e.getMessage());
			}
			return userRoles;
	}




	
	
}

package net.syscon.s4.globaloffenderrecords.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globaloffenderrecords.OiuimageRepository;
import net.syscon.s4.im.beans.ImageOriginals;
import net.syscon.s4.im.beans.ImageProperties;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.booking.beans.PersonIdentifiers;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.triggers.StaffMembersT2Repository;
import oracle.jdbc.OracleTypes;

/**
 * Class OiuimageRepositoryImpl
 */
@Repository
public class OiuimageRepositoryImpl extends RepositoryBase implements OiuimageRepository {

	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger log = LogManager.getLogger(OiuimageRepositoryImpl.class.getName());
	
	@Autowired
	private StaffMembersT2Repository staffMembersT2Repository;

	private final Map<String, FieldMapper> imagesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("IMAGE_OBJECT_SEQ", 		new FieldMapper("imageObjectSeq"))
			.put("IMAGE_ID", 				new FieldMapper("imageId"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("IMAGE_OBJECT_TYPE", 		new FieldMapper("imageObjectType"))
			.put("IMAGE_OBJECT_ID", 		new FieldMapper("imageObjectId"))
			.put("IMAGE_THUMBNAIL", 		new FieldMapper("imageThumbnail"))
			.put("ORIENTATION_TYPE", 		new FieldMapper("orientationType"))
			.put("IMAGE_VIEW_TYPE", 		new FieldMapper("imageViewType"))
			.build();
	private final Map<String, FieldMapper> vStgGangSetMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STG_DESC", 				new FieldMapper(" stgDesc "))
			.put("STG_ID", 					new FieldMapper("stgId"))
			.put("PROFILE_VALUE", 			new FieldMapper(" profileValue "))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 					new FieldMapper("code"))
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("LIST_SEQ", 			new FieldMapper("listSeq"))
			.build();
	private final Map<String, FieldMapper> vStgGroupMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STG_DESC", 				new FieldMapper("stgDesc"))
			.put("STG_ID", 					new FieldMapper("stgId"))
			.put("PROFILE_VALUE", 			new FieldMapper("profileValue"))
			.build();
	private final Map<String, FieldMapper> personsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", 			new FieldMapper("profileCode"))
			.put("CORONER_NUMBER", 			new FieldMapper("coronerNumber"))
			.put("ATTENTION", 				new FieldMapper("attention"))
			.put("LAST_NAME_SOUNDEX", 		new FieldMapper("lastNameSoundex"))
			.put("MIDDLE_NAME", 			new FieldMapper("middleName"))
			.put("PERSON_ID", 				new FieldMapper("personId"))
			.put("CITIZENSHIP", 			new FieldMapper("citizenship"))
			.put("CRIMINAL_HISTORY_TEXT", 	new FieldMapper("criminalHistoryText"))
			.put("LANGUAGE_CODE", 			new FieldMapper("languageCode"))
			.put("LAST_NAME", 				new FieldMapper("lastName"))
			.put("PRIMARY_LANGUAGE_CODE", 	new FieldMapper("primaryLanguageCode"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("EMPLOYER", 				new FieldMapper("employer"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("SUSPENDED_FLAG", 			new FieldMapper("suspendedFlag"))
			.put("OCCUPATION_CODE", 		new FieldMapper("occupationCode"))
			.put("NAME_TYPE", 				new FieldMapper("nameType"))
			.put("COMPREHEND_ENGLISH_FLAG", new FieldMapper("comprehendEnglishFlag"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("MEMO_TEXT", 				new FieldMapper("memoText"))
			.put("DECEASED_DATE", 			new FieldMapper("deceasedDate"))
			.put("SUSPENDED_DATE", 			new FieldMapper("suspendedDate"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("MIDDLE_NAME_KEY", 		new FieldMapper("middleNameKey"))
			.put("REMITTER_FLAG", 			new FieldMapper("remitterFlag"))
			.put("BIRTHDATE", 				new FieldMapper("birthdate"))
			.put("BIRTH_PLACE", 			new FieldMapper("birthPlace"))
			.put("LAST_NAME_KEY", 			new FieldMapper("lastNameKey"))
			.put("ALIAS_PERSON_ID", 		new FieldMapper("aliasPersonId"))
			.put("CARE_OF", 				new FieldMapper("careOf"))
			.put("STAFF_FLAG", 				new FieldMapper("staffFlag"))
			.put("ROOT_PERSON_ID", 			new FieldMapper("rootPersonId"))
			.put("FIRST_NAME_KEY", 			new FieldMapper("firstNameKey"))
			.put("MARITAL_STATUS", 			new FieldMapper("maritalStatus"))
			.put("SEX", 					new FieldMapper("sex"))
			.put("FIRST_NAME", 				new FieldMapper("firstName"))
			.put("INTERPRETER_REQUIRED", 	new FieldMapper("interpreterRequired"))
			.build();
	private final Map<String, FieldMapper> vStgSetMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STG_DESC", 				new FieldMapper("stgDesc"))
			.put("STG_ID", 					new FieldMapper("stgId"))
			.put("PROFILE_VALUE", 			new FieldMapper(" profileValue "))
			.build();
	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PERSONNEL_TYPE", 			new FieldMapper("personnelType"))
			.put("DEFAULT_PRINTER_ID", 		new FieldMapper("defaultPrinterId"))
			.put("ROLE", 					new FieldMapper("role"))
			.put("SUFFIX", 					new FieldMapper("suffix"))
			.put("TERMINATION_DATE", 		new FieldMapper("terminationDate"))
			.put("AS_OF_DATE", 				new FieldMapper("asOfDate"))
			.put("MIDDLE_NAME", 			new FieldMapper("middleName"))
			.put("COMM_RECEIPT_PRINTER_ID", new FieldMapper("commReceiptPrinterId"))
			.put("LAST_NAME", 				new FieldMapper("lastName"))
			.put("POSITION", 				new FieldMapper("position"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("FORCE_PASSWORD_CHANGE_FLAG", new FieldMapper("forcePasswordChangeFlag"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("WORKING_CASELOAD_ID", 	new FieldMapper("workingCaseloadId")).put("STATUS", new FieldMapper("status"))
			.put("SUSPENDED_FLAG", 			new FieldMapper("suspendedFlag"))
			.put("ASSIGNED_CASELOAD_ID", 	new FieldMapper("assignedCaseloadId"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("FIRST_LOGON_FLAG", 		new FieldMapper("firstLogonFlag"))
			.put("USER_ID", 				new FieldMapper("userId"))
			.put("LAST_PASSWORD_CHANGE_DATE", new FieldMapper("lastPasswordChangeDate"))
			.put("STAFF_ID", 				new FieldMapper("staffId"))
			.put("SUPERVISOR_STAFF_ID", 	new FieldMapper("supervisorStaffId"))
			.put("SUSPENSION_REASON", 		new FieldMapper("suspensionReason"))
			.put("NAME_SEQUENCE", 			new FieldMapper("nameSequence"))
			.put("ABBREVIATION", 			new FieldMapper("abbreviation"))
			.put("LICENSE_EXPIRY_DATE", 	new FieldMapper("licenseExpiryDate"))
			.put("BIRTHDATE", 				new FieldMapper("birthdate"))
			.put("TITLE", 					new FieldMapper("title"))
			.put("SEX_CODE", 				new FieldMapper("sexCode"))
			.put("SUSPENSION_DATE", 		new FieldMapper("suspensionDate"))
			.put("BADGE_ID", 				new FieldMapper("badgeId"))
			.put("WORKING_STOCK_LOC_ID", 	new FieldMapper("workingStockLocId"))
			.put("UPDATE_ALLOWED_FLAG", 	new FieldMapper("updateAllowedFlag"))
			.put("LICENSE_CODE", 			new FieldMapper("licenseCode"))
			.put("EMERGENCY_CONTACT", 		new FieldMapper("emergencyContact"))
			.put("QUEUE_CLUSTER_ID", 		new FieldMapper("queueClusterId"))
			.put("FIRST_NAME", 				new FieldMapper("firstName"))
			.build();
	private final Map<String, FieldMapper> imagePropertiesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("IMAGE_ID", 				new FieldMapper("imageId"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("IMAGE_PROPERTY_ID", 		new FieldMapper("imagePropertyId"))
			.put("PROPERTY", 				new FieldMapper("property"))
			.build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 			new FieldMapper(" description "))
			.put("PROPERTY", 				new FieldMapper("property"))
			.put("ORIENTATION_TYPE", 		new FieldMapper("orientationType"))
			.put("IMAGE_VIEW_TYPE", 		new FieldMapper("imageViewType"))
			.put("CODE", 					new FieldMapper(" code"))
			.build();
	private final Map<String, FieldMapper> imageOriginalsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", 			new FieldMapper("sealFlag"))
			.put("IMAGE_FULL", 			new FieldMapper("imageFull"))
			.put("CREATE_DATETIME", 	new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", 		new FieldMapper("modifyUserId"))
			.put("IMAGE_ID", 			new FieldMapper("imageId"))
			.put("IMAGE_ORIGINAL", 		new FieldMapper("imageOriginal"))
			.put("MODIFY_DATETIME", 	new FieldMapper("modifyDatetime")).build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LAN", 				new FieldMapper("lan"))
			.put("REPORT_CODE", 		new FieldMapper("reportCode"))
			.put("DECOD", 				new FieldMapper("decod"))
			.put("'PORTRAIT')", 		new FieldMapper("portrait"))
			.put("'LANDSCAPE'", 		new FieldMapper("landscape"))
			.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", 		new FieldMapper("profileCode"))
			.put("PROFILE_TYPE", 		new FieldMapper("profileType"))
			.put("CREATE_USER_ID", 		new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", 		new FieldMapper("modifyUserId"))
			.put("STG_DESC", 			new FieldMapper(" stgDesc "))
			.put("OLD_TABLE_NAME",		new FieldMapper("oldTableName"))
			.put("LTRIM(RTRIM(DESCRIPTION))", new FieldMapper(" ltrim(rtrim(description))"))
			.put("PROFILE_VALUE_2", 	new FieldMapper("profileValue2"))
			.put("SEAL_FLAG", 			new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 	new FieldMapper("createDatetime"))
			.put("STG_ID", 				new FieldMapper("stgId"))
			.put("MODIFY_DATETIME", 	new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE", 		new FieldMapper(" profileValue "))
			.put("DESCRIPTION", 		new FieldMapper("description"))
			.build();
	
	/**
	 * Creates new OiuimageRepositoryImpl class Object
	 */
	public OiuimageRepositoryImpl() {
		// OiuimageRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Images
	 *
	 * @return List<Images>
	 *
	 * @
	 */
	public List<Images> imagesExecuteQuery(final Images objSearchDao) {
		final String sql = getQuery("OIUIMAGE_IMAGES_FIND_IMAGES");
		final RowMapper<Images> imagesRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class, imagesMapping);
		String preparedSql = null;
		String preSqlQuery = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append("WHERE ");
			if ("OIDSTGID".equals(objSearchDao.getModelName())) {
				if (objSearchDao.getImageObjectType() != null) {
					sqlQuery.append(" IMAGE_OBJECT_TYPE= :IMAGE_OBJECT_TYPE " + " AND ");
					inParameterSource.addValue("IMAGE_OBJECT_TYPE", objSearchDao.getImageObjectType());
				}
				if (objSearchDao.getImageObjectId() != null) {
					sqlQuery.append(" IMAGE_OBJECT_ID = :IMAGE_OBJECT_ID "
							+ " AND   (:IMAGE_OBJECT_SEQ IS NULL OR IMAGE_OBJECT_SEQ =:IMAGE_OBJECT_SEQ) AND ");
					inParameterSource.addValue("IMAGE_OBJECT_ID", objSearchDao.getImageObjectId());
					inParameterSource.addValue("IMAGE_OBJECT_SEQ", objSearchDao.getImageObjectSeq());
				}
				if (objSearchDao.getImageViewType() != null) {
					sqlQuery.append(" IMAGE_VIEW_TYPE= :IMAGE_VIEW_TYPE " + " AND ");
					inParameterSource.addValue("IMAGE_VIEW_TYPE", objSearchDao.getImageViewType());
				}
				if (objSearchDao.getImageObjectSeq() != null) {
					sqlQuery.append(" IMAGE_OBJECT_SEQ= :IMAGE_OBJECT_SEQ ");
					inParameterSource.addValue("IMAGE_OBJECT_SEQ", objSearchDao.getImageObjectSeq());
				}
				preparedSql = sqlQuery.toString().trim();
				sqlQuery.delete(0, sqlQuery.length());
				sqlQuery.append(preparedSql);
				if (!"OFF_IDM".equals(objSearchDao.getImageObjectType().trim())
						&& !"PPTY".equals(objSearchDao.getImageObjectType().trim())) {
					sqlQuery.append(" AND (:IMAGE_OBJECT_TYPE IN ('OFF_BKG', 'PERSON', 'STAFF', 'OIC','STG') OR "
							+ " (:IMAGE_OBJECT_TYPE = 'OFF_IDM' AND IMAGE_OBJECT_SEQ = NULL) "
							+ " OR (:IMAGE_OBJECT_TYPE = 'PPTY' AND " + " IMAGE_OBJECT_SEQ = NULL)) ");
				}
				preSqlQuery = sqlQuery.toString().trim();
				preparedSql = preSqlQuery.concat(" ORDER BY ACTIVE_FLAG DESC,IMAGE_ID DESC ");
				if (preparedSql.endsWith("WHERE")) {
					preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
				}
				if (preparedSql.endsWith("AND")) {
					preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
				}
				preSqlQuery = preparedSql.concat(" ORDER BY ACTIVE_FLAG DESC,IMAGE_ID DESC ");
			} else {
				if (objSearchDao.getImageObjectType() != null) {
					sqlQuery.append("IMAGE_OBJECT_TYPE= :IMAGE_OBJECT_TYPE" + " AND ");
					inParameterSource.addValue("IMAGE_OBJECT_TYPE", objSearchDao.getImageObjectType());
				}
				if (objSearchDao.getImageObjectId() != null) {
					sqlQuery.append("IMAGE_OBJECT_ID = :IMAGE_OBJECT_ID"
							+ " AND   (IMAGE_OBJECT_SEQ IS NULL OR IMAGE_OBJECT_SEQ =:IMAGE_OBJECT_SEQ) AND ");
					inParameterSource.addValue("IMAGE_OBJECT_ID", objSearchDao.getImageObjectId());
					inParameterSource.addValue("IMAGE_OBJECT_SEQ", objSearchDao.getImageObjectSeq());
				}
				if (objSearchDao.getImageViewType() != null) {
					sqlQuery.append("IMAGE_VIEW_TYPE= :IMAGE_VIEW_TYPE" + " AND ");
					inParameterSource.addValue("IMAGE_VIEW_TYPE", objSearchDao.getImageViewType());
				}
				if (objSearchDao.getImageObjectSeq() != null) {
					sqlQuery.append(
							"IMAGE_OBJECT_SEQ= :IMAGE_OBJECT_SEQ");
					inParameterSource.addValue("IMAGE_OBJECT_SEQ", objSearchDao.getImageObjectSeq());
				}
				preparedSql = sqlQuery.toString().trim();
				if (preparedSql.endsWith("WHERE")) {
					preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
				}
				if (preparedSql.endsWith("AND")) {
					preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
				}
				sqlQuery.delete(0, sqlQuery.length());
				sqlQuery.append(preparedSql);
				if (objSearchDao.getImageObjectType()!=null &&  
						!"OFF_IDM".equals(objSearchDao.getImageObjectType().trim())
						&& !"PPTY".equals(objSearchDao.getImageObjectType().trim()) && !"PPTY_CONT".equals(objSearchDao.getImageObjectType().trim())) {
					sqlQuery.append("AND (IMAGE_OBJECT_TYPE IN ('OFF_BKG', 'PERSON', 'STAFF', 'OIC','STG') OR "
							+ "(IMAGE_OBJECT_TYPE = 'OFF_IDM' AND IMAGE_OBJECT_SEQ = NULL)"
							+ " OR (IMAGE_OBJECT_TYPE = 'PPTY' AND "
							+ "IMAGE_OBJECT_SEQ = NULL)) ORDER BY ACTIVE_FLAG DESC,IMAGE_ID DESC");
					preparedSql = sqlQuery.toString().trim();
				}
				else {
					sqlQuery.append(" ORDER BY ACTIVE_FLAG DESC,IMAGE_ID DESC ");
					preparedSql = sqlQuery.toString().trim();
				}
			}
		}
		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, imagesRowMapper);
	}


	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstImages
	 *            List<Images>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer imagesInsertImages(final List<Images> lstImages) {
		final String sql = getQuery("OIUIMAGE_IMAGES_INSERT_IMAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		final Map<String, Object>[] paramMap = (Map<String, Object>[]) new HashMap<?, ?>[lstImages.size()];
		for (final Images images : lstImages) {
			final int index = lstImages.indexOf(images);
			paramMap[index] = new HashMap<>();
			paramMap[index].put("imageId", images.getImageId());
			paramMap[index].put("imageObjectType", images.getImageObjectType());
			paramMap[index].put("imageObjectId", images.getImageObjectId());
			paramMap[index].put("imageObjectSeq", images.getImageObjectSeq());
			paramMap[index].put("imageViewType", images.getImageViewType());
			paramMap[index].put("imageThumbnail", images.getImageThumbnail());
			paramMap[index].put("activeFlag", images.getActiveFlag());
			paramMap[index].put("orientationType", images.getOrientationType());
			paramMap[index].put("sealFlag", images.getSealFlag());
			paramMap[index].put("createDatetime", images.getCreateDatetime());
			paramMap[index].put("createUserId", images.getCreateUserId());
			paramMap[index].put("modifyDatetime", images.getModifyDatetime());
			paramMap[index].put("modifyUserId", images.getModifyUserId());
		}


		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, paramMap);
		if (lstImages.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstImages
	 *            List<Images>
	 *
	 * @
	 */
	public Integer imagesUpdateImages(final List<Images> lstImages) {
		final String sql = getQuery("OIUIMAGE_IMAGES_UPDATE_IMAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Images images : lstImages) {
			parameters.add(new BeanPropertySqlParameterSource(images));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstImages.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstImages
	 *            List<Images>
	 *
	 * @
	 */
	public Integer imagesDeleteImages(final List<Images> lstImages) {
		final String sql = getQuery("OIUIMAGE_IMAGES_DELETE_IMAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Images images : lstImages) {
			parameters.add(new BeanPropertySqlParameterSource(images));
		}
		try {
			String tableName = "IMAGES";
			String whereClause = "IMAGE_ID = :imageId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			log.error("Exception occured in " + this.getClass().getName() + " in method imagesDeleteImages", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstImages.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            ImageOriginals
	 *
	 * @return List<ImageOriginals>
	 *
	 * @
	 */
	public List<ImageOriginals> imageOriginalsExecuteQuery(final ImageOriginals objSearchDao) {
		final String sql = getQuery("OIUIMAGE_IMAGEORIGINALS_FIND_IMAGE_ORIGINALS");
		final RowMapper<ImageOriginals> ImageOriginalsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ImageOriginals.class, imageOriginalsMapping);
		final List<ImageOriginals> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("imageId", objSearchDao.getImageId()), ImageOriginalsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstImageOriginals
	 *            List<ImageOriginals>
	 *
	 * @
	 */
	public Integer imageOriginalsDeleteImageOriginals(final List<ImageOriginals> lstImageOriginals) {
		final String sql = getQuery("OIUIMAGE_IMAGEORIGINALS_DELETE_IMAGE_ORIGINALS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ImageOriginals imageOriginals : lstImageOriginals) {
			parameters.add(new BeanPropertySqlParameterSource(imageOriginals));
		}
		try {
			String tableName = "IMAGE_ORIGINALS";
			String whereClause = "IMAGE_ID = :imageId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			log.error("Exception occured in " + this.getClass().getName() + " in method imageOriginalsDeleteImageOriginals", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		
		if (lstImageOriginals.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
	
	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstImageOriginals
	 *            List<ImageOriginals>
	 *
	 * @
	 */
	public Integer imageOriginalsUpdateImageOriginals(final List<ImageOriginals> lstImageOriginals) {
		final String sql = getQuery("OIUIMAGE_IMAGEORIGINALS_UPDATE_IMAGE_ORIGINALS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ImageOriginals imageOriginals : lstImageOriginals) {
			parameters.add(new BeanPropertySqlParameterSource(imageOriginals));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		
		if (lstImageOriginals.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            ImageProperties
	 *
	 * @return List<ImageProperties>
	 *
	 * @
	 */
	public List<ImageProperties> imagePropertiesExecuteQuery(final ImageProperties objSearchDao) {
		final String sql = getQuery("OIUIMAGE_IMAGEPROPERTIES_FIND_IMAGE_PROPERTIES");
		final RowMapper<ImageProperties> ImagePropertiesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ImageProperties.class, imagePropertiesMapping);
		List<ImageProperties> returnList=new ArrayList<ImageProperties>();
		 returnList =  namedParameterJdbcTemplate.query(sql,createParams("imageId",objSearchDao.getImageId()), ImagePropertiesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstImageProperties
	 *            List<ImageProperties>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer imagepropertiesInsertImageProperties(final List<ImageProperties> lstImagePrpties) {
		final String sql = getQuery("OIUIMAGE_IMAGEPROPERTIES_INSERT_IMAGE_PROPERTIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ImageProperties imageProperties : lstImagePrpties) {
			parameters.add(new BeanPropertySqlParameterSource(imageProperties));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstImagePrpties.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstImageProperties
	 *            List<ImageProperties>
	 *
	 * @
	 */
	public Integer imagePropertiesUpdateImageProperties(final List<ImageProperties> lstImageProperties) {
		final String sql = getQuery("OIUIMAGE_IMAGEPROPERTIES_UPDATE_IMAGE_PROPERTIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ImageProperties imageProperties : lstImageProperties) {
			parameters.add(new BeanPropertySqlParameterSource(imageProperties));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstImageProperties.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstImageProperties
	 *            List<ImageProperties>
	 *
	 * @
	 */
	public Integer imagePropertiesDeleteImageProperties(final List<ImageProperties> lstImageProperties) {
		final String sql = getQuery("OIUIMAGE_IMAGEPROPERTIES_DELETE_IMAGE_PROPERTIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ImageProperties imageProperties : lstImageProperties) {
			parameters.add(new BeanPropertySqlParameterSource(imageProperties));
		}
		try {
			String tableName = "IMAGE_PROPERTIES";
			String whereClause = "IMAGE_PROPERTY_ID  = :imagePropertyId  AND  IMAGE_ID  = :imageId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			log.error("Exception occured in " + this.getClass().getName() + " in method imagePropertiesDeleteImageProperties", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstImageProperties.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 * @
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OIUIMAGE_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), SystemProfilesRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            StaffMembers
	 *
	 * @return List<StaffMembers>
	 *
	 * @
	 */
	public List<StaffMembers> staffMembersExecuteQuery(final StaffMembers objSearchDao) {
		final String sql = getQuery("OIUIMAGE_STAFFMEMBERS_FIND_STAFF_MEMBERS");
		final RowMapper<StaffMembers> StaffMembersRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		final ArrayList<StaffMembers> returnList = (ArrayList<StaffMembers>) namedParameterJdbcTemplate.query(sql,
				createParams(), StaffMembersRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstStaffMembers
	 *            List<StaffMembers>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer staffMembersInsertStaffMembers(final List<StaffMembers> lstStaffMembers) {
		final String sql = getQuery("OIUIMAGE_STAFFMEMBERS_INSERT_STAFF_MEMBERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StaffMembers staffMembers : lstStaffMembers) {
			Integer profileVal=staffMembersT2Repository.getProfileValueFromSystemProfiles();
			staffMembers.setQueueClusterId(staffMembersT2Repository.getStaffIdProfileValueMod(staffMembers.getStaffId().longValue(),profileVal.longValue()));
			parameters.add(new BeanPropertySqlParameterSource(staffMembers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStaffMembers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstStaffMembers
	 *            List<StaffMembers>
	 *
	 * @
	 */
	public Integer staffMembersUpdateStaffMembers(final List<StaffMembers> lstStaffMembers) {
		final String sql = getQuery("OIUIMAGE_STAFFMEMBERS_UPDATE_STAFF_MEMBERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StaffMembers staffMembers : lstStaffMembers) {
			parameters.add(new BeanPropertySqlParameterSource(staffMembers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStaffMembers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstStaffMembers
	 *            List<StaffMembers>
	 *
	 * @
	 */
	public Integer staffMembersDeleteStaffMembers(final List<StaffMembers> lstStaffMembers) {
		int deleteCount = 0;
		final String sql = getQuery("OIUIMAGE_STAFFMEMBERS_DELETE_STAFF_MEMBERS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StaffMembers staffMembers : lstStaffMembers) {
			parameters.add(new BeanPropertySqlParameterSource(staffMembers));
		}
		try {
			String tableName = "STAFF_MEMBERS";
			String whereClause = null;
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			log.error("Exception occured in " + this.getClass().getName() + " in method staffMembersDeleteStaffMembers", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		for (int i = 0; i < returnArray.length; i++) {
			deleteCount = deleteCount++;
		}
		if (lstStaffMembers.size() == deleteCount) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Persons
	 *
	 * @return List<Persons>
	 *
	 * @
	 */
	public List<Persons> personsExecuteQuery(final Persons objSearchDao) {
		final String sql = getQuery("OIUIMAGE_PERSONS_FIND_PERSONS");
		final RowMapper<Persons> PersonsRowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, personsMapping);
		final ArrayList<Persons> returnList = (ArrayList<Persons>) namedParameterJdbcTemplate.query(sql, createParams(),
				PersonsRowMapper);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<OmsModules> rgReportRecordGroup() {
		final String sql = getQuery("OIUIMAGE_FIND_RGREPORT");
		final RowMapper<OmsModules> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mMapping);

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
	public List<ReferenceCodes> rgImageViewTypeRecordGroup() {
		final String sql = getQuery("OIUIMAGE_FIND_RGIMAGEVIEWTYPE");
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
	public List<ReferenceCodes> rgDummyImageViewTypeRecordGroup() {
		final String sql = getQuery("OIUIMAGE_FIND_RGDUMMYIMAGEVIEWTYPE");
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
	public List<ReferenceCodes> rgImagePropertiesRecordGroup() {
		final String sql = getQuery("OIUIMAGE_FIND_RGIMAGEPROPERTIES");
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
	 * imagesPostQuery
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes imagesPostQuery(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIUIMAGE_IMAGES_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * imagesKeyDelrec
	 *
	 * @param params
	 *
	 */
	public List<Images> imagesKeyDelrec(final Images paramBean) {
		final String sql = getQuery("OIUIMAGE_IMAGES_KEYDELREC");
		final RowMapper<Images> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class, imagesMapping);
		final ArrayList<Images> returnList = (ArrayList<Images>) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * imagesWhenNewRecordInstance
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes imagesWhenNewRecordInstance(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIUIMAGE_IMAGES_WHENNEWRECORDINSTANCE");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * imagesOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public ImageOriginals imagesOnCheckDeleteMaster(final ImageOriginals paramBean) {
		final String sql = getQuery("OIUIMAGE_IMAGES_ONCHECKDELETEMASTER");
		final RowMapper<ImageOriginals> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ImageOriginals.class,
				imageOriginalsMapping);
		final ImageOriginals returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * imagesOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<ImageProperties> imagesOnCheckDeleteMaster(final ImageProperties paramBean) {
		final String sql = getQuery("OIUIMAGE_IMAGES_ONCHECKDELETEMASTER");
		final RowMapper<ImageProperties> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ImageProperties.class,
				imagePropertiesMapping);
		final ArrayList<ImageProperties> returnList = (ArrayList<ImageProperties>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * imagePropertiesPostQuery
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes imagePropertiesPostQuery(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIUIMAGE_IMAGE_PROPERTIES_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * runReportOnTheWeb
	 *
	 * @param params
	 *
	 */
	public SystemProfiles runReportOnTheWeb(final SystemProfiles paramBean) {
		final String sql = getQuery("OIUIMAGE_RUN_REPORT_ON_THE_WEB");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		final SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * readProfileREAD_PROFILE
	 *
	 * @param params
	 *
	 */
	public List<SystemProfiles> readProfile(final SystemProfiles paramBean) {
		final String sql = getQuery("OIUIMAGE_READ_PROFILE_READ_PROFILE");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * controlOneDefaultImage
	 *
	 * @param params
	 *
	 */
	public List<Images> controlOneDefaultImage(Images paramBean) {
		final String sql = getQuery("OIUIMAGE_CONTROL_ONE_DEFAULT_IMAGE");
		final RowMapper<Images> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class, imagesMapping);
		final ArrayList<Images> returnList = (ArrayList<Images>) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * populateStgGroup
	 *
	 * @param params
	 *
	 */
	public SystemProfiles populateStgGroup(final SystemProfiles paramBean) {
		final String sql = getQuery("OIUIMAGE_POPULATE_STG_GROUP");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		final SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * disableButtons
	 *
	 * @param params
	 *
	 */
	public Images disableButtons(final Images paramBean) {
		final String sql = getQuery("OIUIMAGE_DISABLE_BUTTONS");
		final RowMapper<Images> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class, imagesMapping);
		final Images returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * initializeImageRecord
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes initializeImageRecord(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIUIMAGE_INITIALIZE_IMAGE_RECORD");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * @return Integer
	 */
	@Override
	public Long getNextImageId() {
		Long imageId = 0L;
		final String sql = getQuery("OIUIMAGE_GET_IMAGE_ID");
		imageId = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		return imageId;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstImages
	 *            List<Images>
	 *
	 * @return Integer
	 */
	public Integer updateActiveFlagInImages(final List<Images> lstImages) {
		final String sql = getQuery("OIUIMAGE_UPDATE_ACTIVE_FLAG");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Images images : lstImages) {
			parameters.add(new BeanPropertySqlParameterSource(images));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstImages.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	public Integer omsOwnerEliteImagingSaveBlob(final String table, final String column, final String where) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[24];
		sqlParameters = new SqlParameter[] { new SqlParameter("PC$TABLE", OracleTypes.VARCHAR),
				new SqlParameter("PC$COLUM", OracleTypes.VARCHAR), new SqlParameter("PC$WHERE", OracleTypes.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("ELITE_IMAGING").withFunctionName("SAVE_BLOB").declareParameters(sqlParameters);
		inParamMap.put("PC$TABLE", table);
		inParamMap.put("PC$COLUMN", column);
		inParamMap.put("PC$WHERE", where);
		Object result = simpleJDBCCall.executeFunction(Object.class, inParamMap);
		return 1;
	}

	@Override
	public String getOiiptranPptyCode(final String imageViewType) {
		final String sql = getQuery("OIUIMAGE_GET_CODE");
		String code= "";
		code = namedParameterJdbcTemplate.queryForObject(sql, createParams("description",imageViewType), String.class);
		return code;
	}

	@Override
	public String getOiiptranPptyDescription(final String imageViewType) {
		final String sql = getQuery("OIUIMAGE_GET_DERSCRIPTION");
		String code= "";
		code = namedParameterJdbcTemplate.queryForObject(sql, createParams("code",imageViewType), String.class);
		return code;
	}

	@Override
	public List<ReferenceCodes> getImageOicCodeDescription() {
		final String sql = getQuery("OIUIMAGE_GET_CODE_DERSCRIPTION_FROM_OIDOICUS");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, mMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("rgSearchTypeRecordGroup", e);
		}
		return refList;
	}

	/**
	 * @param property
	 * @return  List<ReferenceCodes>
	 */
	@Override
	public List<ReferenceCodes> imageGetPropertyDecription(final String property) {
		final String sql = getQuery("OIUIMAGE_GET_PROPERTY_DERSCRIPTION");
		List<ReferenceCodes> retunList= new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, mMapping);
			try {
				retunList =  namedParameterJdbcTemplate.query(sql, createParams("code",property),referenceCodeRowMapper);
		} catch (Exception e) {
			log.error("imageGetPropertyDecription", e);
		}
		return retunList;
	}

	@Override
	public Integer getImagePropertyId() {
		Integer propertyId = 0;
		final String sql = getQuery("OIUIMAGE_GET_IMAGE_PROPERTY_ID");
		try{
			propertyId =namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			log.error("getImagePropertyId", e);
		}
		return propertyId;
	}
	
	@Override
	public String rgGetCode(final String code) {
		final String sql = getQuery("OIUIMAGE_GET_PPTYCODE");
		String pptyCode= "";
		pptyCode = namedParameterJdbcTemplate.queryForObject(sql, createParams("code",code), String.class);
		return pptyCode;
	}

	@Override
	public Integer allowDelete(String userName) {
		final String sql = getQuery("OIUIMAGE_ALLOW_DELETE");
		Integer checkOne= 0;
		checkOne = namedParameterJdbcTemplate.queryForObject(sql, createParams("userName", userName), Integer.class);
		return checkOne;
	}


	@Override
	public Integer checkUserRole(final String moduleName, String userName) {
		final String sql = getQuery("OIUIMAGE_CHECK_USER_ROLE");
		Integer checkOne= 0;
		checkOne = namedParameterJdbcTemplate.queryForObject(sql, createParams("moduleName",moduleName, "userName", userName), Integer.class);
		return checkOne;
	}

}

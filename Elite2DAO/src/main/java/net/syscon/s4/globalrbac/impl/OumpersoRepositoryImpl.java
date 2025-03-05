package net.syscon.s4.globalrbac.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globalrbac.OumpersoRepository;
import net.syscon.s4.im.beans.TagImages;
import net.syscon.s4.im.beans.VStaffAddresses;
import net.syscon.s4.triggers.StaffMembersT2Repository;

/**
 * 
 * class OumpersoRepositoryImpl
 *
 */
@Repository
public class OumpersoRepositoryImpl extends RepositoryBase implements OumpersoRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumpersoRepositoryImpl.class.getName());
	@Autowired
	private StaffMembersT2Repository staffMembersT2Repository;

	
	private final Map<String, FieldMapper> vStaAddrMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STREET_NUMBER", new FieldMapper("streetNumber")).put("ADDRESS_ID", new FieldMapper("addressId"))
			.put("COUNTRY_CODE", new FieldMapper("countryCode")).put("FIRST_NAME", new FieldMapper("firstName"))
			.put("HOUSE", new FieldMapper("house"))
			.put("ADDRESS_LINE1", new FieldMapper("streetAddress")).build();
	
			private final Map<String, FieldMapper> intAddrMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
			.put("INTERNET_ADDRESS_ID", 		new FieldMapper("internetAddressId"))
			.put("INTERNET_ADDRESS_CLASS", 		new FieldMapper("internetAddressClass"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime"))
			.build();
			private final Map<String, FieldMapper> staffMemMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PERSONNEL_TYPE", 				new FieldMapper("personnelType"))
			.put("DEFAULT_PRINTER_ID", 			new FieldMapper("defaultPrinterId"))
			.put("ROLE", 						new FieldMapper("role"))
			.put("SUFFIX", 						new FieldMapper("suffix"))
			.put("TERMINATION_DATE", 			new FieldMapper("terminationDate"))
			.put("AS_OF_DATE", 					new FieldMapper("asOfDate"))
			.put("MIDDLE_NAME", 				new FieldMapper("middleName"))
			.put("COMM_RECEIPT_PRINTER_ID",		new FieldMapper("commReceiptPrinterId"))
			.put("LAST_NAME", 					new FieldMapper("lastName"))
			.put("POSITION", 					new FieldMapper("position"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",  			new FieldMapper("createDateTime"))
			.put("FORCE_PASSWORD_CHANGE_FLAG", 	new FieldMapper("forcePasswordChangeFlag"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDateTime"))
			.put("WORKING_CASELOAD_ID", 		new FieldMapper("workingCaseloadId"))
			.put("STATUS", 						new FieldMapper("status"))
			.put("SUSPENDED_FLAG", 				new FieldMapper("suspendedFlag"))
			.put("ASSIGNED_CASELOAD_ID", 		new FieldMapper("assignedCaseloadId"))
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("FIRST_LOGON_FLAG", 			new FieldMapper("firstLogonFlag"))
			.put("USER_ID", 					new FieldMapper("userId"))
			.put("LAST_PASSWORD_CHANGE_DATE", 	new FieldMapper("lastPasswordChangeDate"))
			.put("STAFF_ID", 					new FieldMapper("staffId"))
			.put("SUPERVISOR_STAFF_ID", 		new FieldMapper("supervisorStaffId"))
			.put("SUSPENSION_REASON", 			new FieldMapper("suspensionReason"))
			.put("NAME_SEQUENCE", 				new FieldMapper("nameSequence"))
			.put("ABBREVIATION", 				new FieldMapper("abbreviation"))
			.put("LICENSE_EXPIRY_DATE", 		new FieldMapper("licenseExpiryDate"))
			.put("BIRTHDATE", 					new FieldMapper("birthdate"))
			.put("TITLE", 						new FieldMapper("title"))
			.put("SEX_CODE", 					new FieldMapper("sexCode"))
			.put("SUSPENSION_DATE", 			new FieldMapper("suspensionDate"))
			.put("BADGE_ID", 					new FieldMapper("badgeId"))
			.put("WORKING_STOCK_LOC_ID", 		new FieldMapper("workingStockLocId"))
			.put("UPDATE_ALLOWED_FLAG", 		new FieldMapper("updateAllowedFlag"))
			.put("LICENSE_CODE", 				new FieldMapper("licenseCode"))
			.put("EMERGENCY_CONTACT", 			new FieldMapper("emergencyContact"))
			.put("QUEUE_CLUSTER_ID", 			new FieldMapper("queueClusterId"))
			.put("FIRST_NAME", 					new FieldMapper("firstName"))
			.put("USERIDVAL", 					new FieldMapper("userIdVal"))
			.build();
			private final Map<String, FieldMapper> imagesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("ACTIVE_FLAG", 				new FieldMapper("activeFlag"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("IMAGE_OBJECT_SEQ", 			new FieldMapper("imageObjectSeq"))
			.put("CAPTURE_DATE", 				new FieldMapper("captureDate"))
			.put("IMAGE_ID", 					new FieldMapper("imageId"))
			.put("IMAGE_VIEW_TYPE", 			new FieldMapper("imageViewType"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime"))
			.put("IMAGE_OBJECT_TYPE", 			new FieldMapper("imageObjectType"))
			.put("IMAGE_OBJECT_ID", 			new FieldMapper("imageObjectId"))
			.put("IMAGE_THUMBNAIL", 			new FieldMapper("imageThumbnail"))
			.put("ORIENTATION_TYPE", 			new FieldMapper("orientationType"))
			.build();
			private final Map<String, FieldMapper> refCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 						new FieldMapper("code"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.build();
			private final Map<String, FieldMapper> tagImagesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFF_ID", 					new FieldMapper("staffId"))
			.build();
			private final Map<String, FieldMapper> phonesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
			.put("PHONE_TYPE", 					new FieldMapper("phoneType"))
			.put("PHONE_NO", 					new FieldMapper("phoneNo"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDatetime"))
			.put("ADDRESS_ID", 					new FieldMapper("addressId"))
			.build();
			private final Map<String, FieldMapper> dualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE_2",              new FieldMapper("sysDate"))
			.put("PROFILE_VALUE",                new FieldMapper("user")).build();

	/**
	 * Creates new OumacaseRepositoryImpl class Object
	 */
	public OumpersoRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            StaffMembers
	 *
	 * @return List<StaffMembers>
	 *
	 * 
	 */
	public List<StaffMembers> staffExecuteQuery(final StaffMembers objSearchDao) {
		final String sql = getQuery("OUMPERSO_STAFF_FIND_STAFF_MEMBERS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");

			if (objSearchDao.getLastName() != null && !objSearchDao.getLastName().isEmpty()) {
				sqlQuery.append("LAST_NAME =  :lastName " + " and ");
				valuesList.addValue("lastName", objSearchDao.getLastName());
			}

			if (objSearchDao.getFirstName() != null && !objSearchDao.getFirstName().isEmpty()) {
				sqlQuery.append("FIRST_NAME =  :firstName " + " and ");
				valuesList.addValue("firstName", objSearchDao.getFirstName());
			}
			if (objSearchDao.getMiddleName() != null && !objSearchDao.getMiddleName().isEmpty()) {
				sqlQuery.append("MIDDLE_NAME =  :middleName " + " and ");
				valuesList.addValue("middleName", objSearchDao.getMiddleName());
			}
			
			if (objSearchDao.getSuffix() != null) {
				sqlQuery.append("SUFFIX =  :suffix " + " and ");
				valuesList.addValue("suffix", objSearchDao.getSuffix());
			}
			
			if (objSearchDao.getSexCode() != null) {
				sqlQuery.append("SEX_CODE =  :sexCode " + " and ");
				valuesList.addValue("sexCode", objSearchDao.getSexCode());
			}

			if (objSearchDao.getBirthdate() != null) {
				sqlQuery.append("BIRTHDATE =  :birthdate " + " and ");
				valuesList.addValue("birthdate", objSearchDao.getBirthdate());
			}
			
			if (objSearchDao.getPersonnelType() != null) {
				sqlQuery.append("PERSONNEL_TYPE =  :personnelType " + " and ");
				valuesList.addValue("personnelType", objSearchDao.getPersonnelType());
			}
			
			if (objSearchDao.getPosition() != null) {
				sqlQuery.append("POSITION =  :position " + " and ");
				valuesList.addValue("position", objSearchDao.getPosition());
			}
			
			if (objSearchDao.getStatus() != null) {
				sqlQuery.append("STATUS =  :status " + " and ");
				valuesList.addValue("status", objSearchDao.getStatus());
			}

			if (objSearchDao.getStaffId() != null) {
				sqlQuery.append("STAFF_ID =  :staffId " + " and ");
				valuesList.addValue("staffId", objSearchDao.getStaffId());
			}
			if (objSearchDao.getUserId() != null) {
				sqlQuery.append("USER_ID =  :userId " + " and ");
				valuesList.addValue("userId", objSearchDao.getUserId());
			}
			
			if (objSearchDao.getAsOfDate() != null ) {
				sqlQuery.append("AS_OF_DATE =  :asOfDate " + " and ");
				valuesList.addValue("asOfDate", objSearchDao.getAsOfDate());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		
		final RowMapper<StaffMembers> staMemRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMemMapping);
		return namedParameterJdbcTemplate.query(preparedSql,valuesList, staMemRowMapper);
	}

	/**
	 * @param
	 *
	 * 
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstStaffMembers
	 *            List<StaffMembers>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public String staffInsertStaffMembers(final List<StaffMembers> lstStaffMembers) {
		String staffId = null;
		final String sql = getQuery("OUMPERSO_STAFF_INSERT_STAFF_MEMBERS");
		int[] returnArray = new int[] {};
		try {
			final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
			for (final StaffMembers staffMembers : lstStaffMembers) {
				Integer profileValue=staffMembersT2Repository.getProfileValueFromSystemProfiles();
				staffMembers.setQueueClusterId(staffMembersT2Repository.getStaffIdProfileValueMod(staffMembers.getStaffId().longValue(), profileValue.longValue()));
				parameters.add(new BeanPropertySqlParameterSource(staffMembers));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (lstStaffMembers.size() == returnArray.length) {
				staffId = "1";
			} 
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " staffInsertStaffMembers and Exception is : {}", e.getMessage());
		}
		return staffId;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstStaffMembers
	 *            List<StaffMembers>
	 *@return Integer
	 * 
	 */
	public String staffUpdateStaffMembers(final List<StaffMembers> lstStaffMembers) {
		final String sql = getQuery("OUMPERSO_STAFF_UPDATE_STAFF_MEMBERS");
		String returnValue = null;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StaffMembers staffMembers : lstStaffMembers) {
			parameters.add(new BeanPropertySqlParameterSource(staffMembers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStaffMembers.size() == returnArray.length) {
			returnValue = "1";
		}
		return returnValue;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Images
	 *
	 * @return List<Images>
	 *
	 * 
	 */
	public List<Images> imageExecuteQuery(final Images objSearchDao) {
		final String sql = getQuery("OUMPERSO_IMAGE_FIND_IMAGES");
		final RowMapper<Images> imagesRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class, imagesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("imageObjectId",objSearchDao.getImageObjectId()),imagesRowMapper);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VStaffAddresses
	 *
	 * @return List<VStaffAddresses>
	 *
	 * 
	 */
	public List<VStaffAddresses> vStfAddrExecuteQuery(final VStaffAddresses objSearchDao) {
		final String sql = getQuery("OUMPERSO_VSTFADDR_FIND_V_STAFF_ADDRESSES");
		final RowMapper<VStaffAddresses> vStfAddRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VStaffAddresses.class, vStaAddrMapping);
		return namedParameterJdbcTemplate.query(sql,createParams("staffId",objSearchDao.getStaffId()), vStfAddRowMapper);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Phones
	 *
	 * @return List<Phones>
	 *
	 * 
	 */
	public List<Phones> addrPhonesExecuteQuery(final Phones objSearchDao) {
		final String sql = getQuery("OUMPERSO_ADDRPHONES_FIND_PHONES");
		final RowMapper<Phones> phonesRowMapper = Row2BeanRowMapper.makeMapping(sql, Phones.class, phonesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("ownerId",objSearchDao.getOwnerId(),"ownerClass",objSearchDao.getOwnerClass()),phonesRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstPhones
	 *            List<Phones>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer addrPhonesInsertPhones(final List<Phones> lstPhones) {
		int returnValue = 0;
		final String sql = getQuery("OUMPERSO_ADDRPHONES_INSERT_PHONES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Phones phones : lstPhones) {
			parameters.add(new BeanPropertySqlParameterSource(phones));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPhones.size() == returnArray.length) {
			returnValue = 1;
		} 
      return returnValue;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstPhones
	 *            List<Phones>
	 *@return Integer
	 * 
	 */
	public Integer addrPhonesUpdatePhones(final List<Phones> lstPhones) {
		final String sql = getQuery("OUMPERSO_ADDRPHONES_UPDATE_PHONES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Phones phones : lstPhones) {
			parameters.add(new BeanPropertySqlParameterSource(phones));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPhones.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstPhones
	 *            List<Phones>
	 **@return Integer
	 * 
	 */
	public Integer addrPhonesDeletePhones(final List<Phones> lstPhones) {
		int result = 0;
		final String sql = getQuery("OUMPERSO_ADDRPHONES_DELETE_PHONES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Phones phones : lstPhones) {
			parameters.add(new BeanPropertySqlParameterSource(phones));
		}
		try {
			String tableName = "PHONES";
			String whereClause = "PHONE_ID = :phoneId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method addrPhonesDeletePhones", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPhones.size() == returnArray.length) {
			result = 1;
		}
		return result;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            InternetAddresses
	 *
	 * @return List<InternetAddresses>
	 *
	 * 
	 */
	public List<InternetAddresses> emailAddrExecuteQuery(final InternetAddresses objSearchDao) {
		final String sql = getQuery("OUMPERSO_EMAILADDR_FIND_INTERNET_ADDRESSES");
		final RowMapper<InternetAddresses> internetAddRowMap = Row2BeanRowMapper.makeMapping(sql,
				InternetAddresses.class, intAddrMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("ownerId" , objSearchDao.getOwnerId()), internetAddRowMap);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstIntAddresses
	 *            List<InternetAddresses>
	 *
	 *@return Integer
	 *
	 * 
	 */
	public Integer emailAddrInsertInternetAddresses(final List<InternetAddresses> lstIntAddresses){
		int result = 0;
		final String sql = getQuery("OUMPERSO_EMAILADDR_INSERT_INTERNET_ADDRESSES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final InternetAddresses internetAddresses : lstIntAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(internetAddresses));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstIntAddresses.size() == returnArray.length) {
			result = 1;
		}
		return result;

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstIntAddresses
	 *            List<InternetAddresses>
	 **@return Integer
	 * 
	 */
	public Integer emailAddrUpdateInternetAddresses(final List<InternetAddresses> lstIntAddresses){
		final String sql = getQuery("OUMPERSO_EMAILADDR_UPDATE_INTERNET_ADDRESSES");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final InternetAddresses internetAddresses : lstIntAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(internetAddresses));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstIntAddresses.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstIntAddresses
	 *            List<InternetAddresses>
	 * @return Integer
	 *
	 * 
	 */
	public Integer emailAddrDeleteInternetAddresses(final List<InternetAddresses> lstIntAddresses){
		final String sql = getQuery("OUMPERSO_EMAILADDR_DELETE_INTERNET_ADDRESSES");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final InternetAddresses internetAddresses : lstIntAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(internetAddresses));
		}
		try {
			String tableName = "INTERNET_ADDRESSES";
			String whereClause = "INTERNET_ADDRESS_ID=:internetAddressId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method emailAddrDeleteInternetAddresses", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstIntAddresses.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgPhoneTypeRecordGroup() {
		final String sql = getQuery("OUMPERSO_FIND_RGPHONETYPE");
		final RowMapper<ReferenceCodes> refCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, refCodesMapping);
		 List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
			try {
				lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams(), refCodesRowMapper);
			} catch (Exception e) {
				logger.error(" In method rgPhoneTypeRecordGroup" + e);
			}
			return lstRefCodes;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgSuffixRecordGroup() {
		final String sql = getQuery("OUMPERSO_FIND_RGSUFFIX");
		final RowMapper<ReferenceCodes> refCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, refCodesMapping);
		 List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
			try {
				lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams(), refCodesRowMapper);
			} catch (Exception e) {
				logger.error(" In method rgSuffixRecordGroup" + e);
			}
			return lstRefCodes;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgSexCodeRecordGroup() {
		final String sql = getQuery("OUMPERSO_FIND_RGSEXCODE");
		final RowMapper<ReferenceCodes> refCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, refCodesMapping);
		 List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
			try {
				lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams(), refCodesRowMapper);
			} catch (Exception e) {
				logger.error(" In method rgSexCodeRecordGroup" + e);
			}
			return lstRefCodes;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgStatusRecordGroup() {
		final String mode = "NORMAL";
		final String sql = getQuery("OUMPERSO_FIND_RGSTATUS");
		final RowMapper<ReferenceCodes> refCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, refCodesMapping);
		 List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
			try {
				lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams("MODE", mode), refCodesRowMapper);
			} catch (Exception e) {
				logger.error(" In method rgStatusRecordGroup" + e);
			}
			return lstRefCodes;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgPersonnelTypeRecordGroup() {
		final String sql = getQuery("OUMPERSO_FIND_RGPERSONNELTYPE");
		final RowMapper<ReferenceCodes> refCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, refCodesMapping);
		 List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
			try {
				lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams(), refCodesRowMapper);
			} catch (Exception e) {
				logger.error(" In method rgPersonnelTypeRecordGroup" + e);
			}
			return lstRefCodes;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgPositionRecordGroup() {
		final String mode = "NORMAL";
		final String sql = getQuery("OUMPERSO_FIND_RGPOSITION");
		final RowMapper<ReferenceCodes> refCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, refCodesMapping);
        List<ReferenceCodes> lstRefCodes = new ArrayList<ReferenceCodes>();
		try {
			lstRefCodes = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams("MODE", mode), refCodesRowMapper);
		} catch (Exception e) {
			logger.error(" In method rgPositionRecordGroup " + e);
		}
		return lstRefCodes;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean TagImages
	 *
	 * @return  List<TagImages>
	 *
	 */
	public List<TagImages> staffOnCheckDeleteMaster(final TagImages paramBean) {
		final String sql = getQuery("OUMPERSO_STAFF_ONCHECKDELETEMASTER");
		final RowMapper<TagImages> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TagImages.class,
				tagImagesMapping);
		return namedParameterJdbcTemplate.query(sql,createParams(paramBean), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean VStaffAddresses
	 *
	 * @return  List<VStaffAddresses>
	 *
	 */
	public List<VStaffAddresses> staffOnCheckDeleteMaster(final VStaffAddresses paramBean) {
		final String sql = getQuery("OUMPERSO_STAFF_ONCHECKDELETEMASTER");
		final RowMapper<VStaffAddresses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VStaffAddresses.class,
				vStaAddrMapping);
		return namedParameterJdbcTemplate.query(sql,createParams(paramBean), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean Phones
	 *
	 * @return  List<Phones>
	 *
	 */
	public List<Phones> staffOnCheckDeleteMaster(final Phones paramBean) {
		final String sql = getQuery("OUMPERSO_STAFF_ONCHECKDELETEMASTER");
		final RowMapper<Phones> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Phones.class, phonesMapping);
		return namedParameterJdbcTemplate.query(sql,createParams(paramBean), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean InternetAddresses
	 *
	 * @return  List<InternetAddresses>
	 *
	 */
	public List<InternetAddresses> staffOnCheckDeleteMaster(final InternetAddresses paramBean) {
		final String sql = getQuery("OUMPERSO_STAFF_ONCHECKDELETEMASTER");
		final RowMapper<InternetAddresses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, InternetAddresses.class,
				intAddrMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(paramBean), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 *
	 * @return Object
	 *
	 */
	public Object staffPreInsert() {
		final String sql = getQuery("OUMPERSO_STAFF_PREINSERT_");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Object.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param paramBean Phones
	 *
	 * @return List<Phones>
	 *
	 */
	public List<Phones> vStfAddrOnCheckDeleteMaster(final Phones paramBean) {
		final String sql = getQuery("OUMPERSO_V_STF_ADDR_ONCHECKDELETEMASTER");
		final RowMapper<Phones> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Phones.class, phonesMapping);
		return namedParameterJdbcTemplate.query(sql,createParams(paramBean), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 *
	 * @return Object
	 *
	 */
	public Object addrPhonesPreInsert() {
		final String sql = getQuery("OUMPERSO_ADDR_PHONES_PREINSERT_");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Object.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 *
	 * @return Object
	 *
	 */
	public Object stfPhonesPreInsert() {
		final String sql = getQuery("OUMPERSO_STF_PHONES_PREINSERT_");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Object.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 *
	 * @return Object
	 *
	 */
	public Object emailAddrPreInsert() {
		final String sql = getQuery("OUMPERSO_EMAIL_ADDR_PREINSERT_");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Object.class);
	}
	
	public Phones getPhonesOldObject(Phones object) {
		final String sql = getQuery("PHONES_T2_GET_ADDRESS_OBJECT");
		return  namedParameterJdbcTemplate
				.queryForObject(sql, createParams("phoneId",object.getPhoneId()),new BeanPropertyRowMapper<Phones>(Phones.class));
		
	} 


	@Override
	public List<StaffMembers> getStaffDetails(Date fromDate) {
		final String sql = getQuery("OUMPERSO_GET_STAFF_DETAILS");
		final RowMapper<StaffMembers> staffMemRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMemMapping);
		final ArrayList<StaffMembers> returnList = (ArrayList<StaffMembers>) namedParameterJdbcTemplate.query(sql,
				createParams("fromDate", fromDate), staffMemRowMapper);
		return returnList;
	}
	
	@Override
	public String checkMailId(int staffId) {
		String mailId = "";
		final String sql = getQuery("OUMPERSO_CHECK_MAILID");
		try {
			mailId = namedParameterJdbcTemplate.queryForObject(sql, createParams("staffId", staffId), String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailId;
	}

}


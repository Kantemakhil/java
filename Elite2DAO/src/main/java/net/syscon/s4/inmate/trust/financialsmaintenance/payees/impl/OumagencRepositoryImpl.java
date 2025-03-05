package net.syscon.s4.inmate.trust.financialsmaintenance.payees.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Addresses;
import net.syscon.s4.im.beans.CorporateTypes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inmate.trust.financialsmaintenance.payees.OumagencRepository;
import net.syscon.s4.inst.schedules.bean.VCorporateAddresses;

/**
 * Class OumagencRepositoryImpl
 * 
 */
@Repository
public class OumagencRepositoryImpl extends RepositoryBase implements OumagencRepository {

	private static Logger logger = LogManager.getLogger(OumagencRepositoryImpl.class.getName());
	/**
	 * Creates new OumagencRepositoryImpl class Object
	 */

	private final Map<String, FieldMapper> addressesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ADDRESS_ID", new FieldMapper("addressId")).put("OWNER_CLASS", new FieldMapper("ownerClass"))
			.put("OWNER_ID", new FieldMapper("ownerId")).put("OWNER_SEQ", new FieldMapper("ownerSeq"))
			.put("OWNER_CODE", new FieldMapper("ownerCode")).put("ADDRESS_TYPE", new FieldMapper("addressType"))
			.put("CITY_CODE", new FieldMapper("cityCode")).put("COUNTRY_CODE", new FieldMapper("countryCode"))
			.put("VALIDATED_PAF_FLAG", new FieldMapper("validatedPafFlag"))
			.put("PRIMARY_FLAG", new FieldMapper("primaryFlag")).put("MAIL_FLAG", new FieldMapper("mailFlag"))
			.put("CAPACITY", new FieldMapper("capacity")).put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("NO_FIXED_ADDRESS_FLAG", new FieldMapper("noFixedAddressFlag"))
			.put("SERVICES_FLAG", new FieldMapper("servicesFlag"))
			.put("SPECIAL_NEEDS_CODE", new FieldMapper("specialNeedsCode"))
			.put("CONTACT_PERSON_NAME", new FieldMapper("contactPersonName"))
			.put("BUSINESS_HOUR", new FieldMapper("businessHour")).put("START_DATE", new FieldMapper("startDate"))
			.put("END_DATE", new FieldMapper("endDate")).put("CITY_NAME", new FieldMapper("cityName"))
			.put("PROV_STATE_CODE", new FieldMapper("provStateCode")).put("STREET", new FieldMapper("street"))
			.put("ZIP_POSTAL_CODE", new FieldMapper("zipPostalCode"))
			.put("SUITE_NUMBER", new FieldMapper("suiteNumber")).put("STREET_NUMBER", new FieldMapper("streetNumber"))
			.put("STREET_DIRECTION", new FieldMapper("streetDirection"))
			.put("MAIL_CARE_OF", new FieldMapper("mailCareOf")).put("SEAL_FLAG", new FieldMapper("sealFlag")).build();

	private final Map<String, FieldMapper> corporateTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_TYPE", new FieldMapper(" corporateType ")).build();
	private final Map<String, FieldMapper> phonesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PHONE_ID", new FieldMapper("phoneId")).put("OWNER_CLASS", new FieldMapper("ownerClass"))
			.put("OWNER_ID", new FieldMapper("ownerId")).put("OWNER_SEQ", new FieldMapper("ownerSeq"))
			.put("OWNER_CODE", new FieldMapper("ownerCode")).put("PHONE_TYPE", new FieldMapper("phoneType"))
			.put("PHONE_NO", new FieldMapper("phoneNo")).put("EXT_NO", new FieldMapper("extNo"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();

	private final Map<String, FieldMapper> caseloadsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper(" description ")).build();

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("CODE", new FieldMapper("code")).build();

	private final Map<String, FieldMapper> corporatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_ID", new FieldMapper("corporateId")).put("CORPORATE_NAME", new FieldMapper("corporateName"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("CONTACT_PERSON_NAME", new FieldMapper("contactPersonName"))
			.put("CREATED_DATE", new FieldMapper("createdDate")).put("UPDATED_DATE", new FieldMapper("updatedDate"))
			.put("USER_ID", new FieldMapper("userId")).put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("START_DATE", new FieldMapper("startDate"))
			.put("ACCOUNT_TERM_CODE", new FieldMapper("accountTermCode"))
			.put("SHIPPING_TERM_CODE", new FieldMapper("shippingTermCode"))
			.put("MINIMUM_PURCHASE_AMOUNT", new FieldMapper("minimumPurchaseAmount"))
			.put("MAXIMUM_PURCHASE_AMOUNT", new FieldMapper("maximumPurchaseAmount"))
			.put("MEMO_TEXT", new FieldMapper("memoText")).put("SUSPENDED_FLAG", new FieldMapper("suspendedFlag"))
			.put("SUSPENDED_DATE", new FieldMapper("suspendedDate")).put("FEI_NUMBER", new FieldMapper("feiNumber"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("TAX_NO", new FieldMapper("taxNo")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();

	private final Map<String, FieldMapper> vCorporateAddressesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ADDRESS_ID", new FieldMapper("addressId")).put("ADDRESS_TYPE", new FieldMapper("addressType"))
			.put("ADDRESS_TYPE_DESC", new FieldMapper("addressTypeDesc"))
			.put("CORPORATE_ID", new FieldMapper("corporateId")).put("ADDRESS_SEQ", new FieldMapper("addressSeq"))
			.put("START_DATE", new FieldMapper("startDate")).put("END_DATE", new FieldMapper("endDate"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("HOUSE", new FieldMapper("house"))
			.put("STREET", new FieldMapper("street")).put("AREA", new FieldMapper("area"))
			.put("COUNTRY", new FieldMapper("country")).put("SUITE_NUMBER", new FieldMapper("suiteNumber"))
			.put("STREET_NUMBER", new FieldMapper("streetNumber"))
			.put("STREET_INFORMATION", new FieldMapper("streetInformation"))
			.put("STREET_DIRECTION", new FieldMapper("streetDirection"))
			.put("STREET_DIRECTION_DESC", new FieldMapper("streetDirectionDesc"))
			.put("CITY_CODE", new FieldMapper("cityCode")).put("CITY_NAME", new FieldMapper("cityName"))
			.put("PROV_STATE_CODE", new FieldMapper("provStateCode"))
			.put("PROV_STATE_DESC", new FieldMapper("provStateDesc"))
			.put("ZIP_POSTAL_CODE", new FieldMapper("zipPostalCode"))
			.put("ADDRESS_LINE1", new FieldMapper("streetAddress"))
			.put("COUNTRY_CODE", new FieldMapper("countryCode")).put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("PRIMARY_FLAG", new FieldMapper("primaryFlag")).put("MAIL_FLAG", new FieldMapper("mailFlag"))
			.put("SERVICES_FLAG", new FieldMapper("servicesFlag")).put("SPECIAL_NEEDS", new FieldMapper("specialNeeds"))
			.put("VALIDATED_FLAG", new FieldMapper("validateFlag"))
			.put("CONTACT_PERSON_NAME", new FieldMapper("contactPersonName"))
			.put("BUSINESS_HOUR", new FieldMapper("businessHour")).build();

	private final Map<String, FieldMapper> internetAddressesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INTERNET_ADDRESS_ID", new FieldMapper("internetAddressId"))
			.put("OWNER_CLASS", new FieldMapper("ownerClass")).put("OWNER_ID", new FieldMapper("ownerId"))
			.put("OWNER_SEQ", new FieldMapper("ownerSeq")).put("OWNER_CODE", new FieldMapper("ownerCode"))
			.put("INTERNET_ADDRESS_CLASS", new FieldMapper("internetAddressClass"))
			.put("INTERNET_ADDRESS", new FieldMapper("internetAddress"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();

	public OumagencRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table corpExecuteQuery
	 * 
	 * @param objSearchDao
	 *            Corporates
	 * @return returnList
	 */
	public List<Corporates> corpExecuteQuery(final Corporates objSearchDao) {
		final String sql = getQuery("OUMAGENC_CORP_FIND_CORPORATES");
		final StringBuffer pSql = new StringBuffer(sql);
		String preparedSql = "";
		final MapSqlParameterSource param = new MapSqlParameterSource();
		if (objSearchDao != null) {
			pSql.append(" WHERE ");
			if (objSearchDao.getCorporateName() != null && !objSearchDao.getCorporateName().isEmpty()) {
				pSql.append(" CORPORATE_NAME LIKE :corporateName||'%'  AND ");
				param.addValue("corporateName", objSearchDao.getCorporateName());
			}
			if (objSearchDao.getCaseloadId() != null && !objSearchDao.getCaseloadId().isEmpty()) {
				pSql.append(" CASELOAD_ID LIKE :caseloadId  AND  ");
				param.addValue("caseloadId", objSearchDao.getCaseloadId());
			}
			if (objSearchDao.getFeiNumber() != null && !objSearchDao.getFeiNumber().isEmpty()) {
				pSql.append(" FEI_NUMBER LIKE :feiNumber  AND  ");
				param.addValue("feiNumber", objSearchDao.getFeiNumber());
			}
			if (objSearchDao.getTaxNo() != null && !objSearchDao.getTaxNo().isEmpty()) {
				pSql.append(" TAX_NO LIKE :taxNo::text  AND  ");
				param.addValue("taxNo", objSearchDao.getTaxNo());
			}
			if (objSearchDao.getCorporateId() != null) {
				pSql.append(" CORPORATE_ID::text LIKE :corporateId::text  AND  ");
				param.addValue("corporateId", objSearchDao.getCorporateId());
			}
			if (objSearchDao.getCommentText() != null && !objSearchDao.getCommentText().isEmpty()) {
				pSql.append(" COMMENT_TEXT LIKE :commentText  AND  ");
				param.addValue("commentText", objSearchDao.getCommentText());
			}
			if (objSearchDao.getExpiryDate() != null) {
				pSql.append(" TO_CHAR(EXPIRY_DATE, 'DD/MM/YYYY') = :expiryDate   AND  ");
				param.addValue("expiryDate", new SimpleDateFormat("dd/MM/yyyy").format(objSearchDao.getExpiryDate()));
			}

			preparedSql = pSql.toString().trim();
			if (preparedSql.endsWith("WHERE")) {
				preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 5);
			}
			if (preparedSql.endsWith("AND")) {
				preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 3);
			}

		}
		final RowMapper<Corporates> CorporatesRowMapper = Row2BeanRowMapper.makeMapping(preparedSql, Corporates.class,
				corporatesMapping);
		List<Corporates> returnList = new ArrayList<Corporates>();
		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql, param, CorporatesRowMapper);
		} catch (Exception e) {
			logger.error("corpExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on corpInsertCorporates
	 * 
	 * @param lstCorporates
	 *            List<Corporates>
	 * @return Integer
	 */
	public Integer corpInsertCorporates(final List<Corporates> lstCorporates) {
		String sql = getQuery("OUMAGENC_CORP_INSERT_CORPORATES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Corporates corporates : lstCorporates) {
			parameters.add(new BeanPropertySqlParameterSource(corporates));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCorporates.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 * corpUpdateCorporates
	 * 
	 * @param lstCorporates
	 * @return Integer
	 */
	public Integer corpUpdateCorporates(final List<Corporates> lstCorporates) {
		String sql = getQuery("OUMAGENC_CORP_UPDATE_CORPORATES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Corporates corporates : lstCorporates) {
			parameters.add(new BeanPropertySqlParameterSource(corporates));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCorporates.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 * corpDeleteCorporates
	 * 
	 * @param lstCorporates
	 *            List<Corporates>
	 * @return Integer
	 */
	public Integer corpDeleteCorporates(final List<Corporates> lstCorporates) {
		String sql = getQuery("OUMAGENC_CORP_DELETE_CORPORATES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Corporates corporates : lstCorporates) {
			parameters.add(new BeanPropertySqlParameterSource(corporates));
		}
		try {
			String tableName = "CORPORATES";
			String whereClause = "CORPORATE_ID = :corporateId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method corpDeleteCorporates", e);
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}
		catch (Exception e) {
			logger.error("corpDeleteCorporates : ", e);
			if (e.getMessage().contains("corporate_types_corporates_fk")) {
				return 2;
			}
			if (e.getMessage().contains("off_txn_corp_f1")) {
				return 3;
			}
		}
		if (lstCorporates.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table addrExecuteQuery
	 * 
	 * @param objSearchDao
	 *            VCorporateAddresses
	 * @return returnList
	 */
	public List<VCorporateAddresses> addrExecuteQuery(final VCorporateAddresses objSearchDao) {
		final String sql = getQuery("OUMAGENC_ADDR_FIND_V_CORPORATE_ADDRESSES");
		final RowMapper<VCorporateAddresses> VCorporateAddressesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VCorporateAddresses.class, vCorporateAddressesMapping);
		List<VCorporateAddresses> returnList = new ArrayList<VCorporateAddresses>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("corporateId", objSearchDao.getCorporateId()), VCorporateAddressesRowMapper);
		} catch (Exception e) {
			logger.error("addrExecuteQuery", e);
		}
		return returnList;
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
	public List<Phones> addPhExecuteQuery(final Phones objSearchDao) {
		final String sql = getQuery("OUMAGENC_ADDPH_FIND_PHONES");
		final RowMapper<Phones> PhonesRowMapper = Row2BeanRowMapper.makeMapping(sql, Phones.class, phonesMapping);
		List<Phones> returnList = new ArrayList<Phones>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("ownerId", objSearchDao.getOwnerId()),
					PhonesRowMapper);
		} catch (Exception e) {
			logger.error("addPhExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstPhones
	 *            List<Phones>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */

	public Integer addPhInsertPhones(final List<Phones> lstPhones) {
		String sql = getQuery("OUMAGENC_ADDPH_INSERT_PHONES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Phones phones : lstPhones) {
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
	 * This method is used to update the data base tables based on
	 *
	 * @param lstPhones
	 *            List<Phones>
	 *
	 * 
	 */
	public Integer addPhUpdatePhones(final List<Phones> lstPhones) {
		String sql = getQuery("OUMAGENC_ADDPH_UPDATE_PHONES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Phones phones : lstPhones) {
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
	 *
	 * 
	 */
	public Integer addPhDeletePhones(final List<Phones> lstPhones) {
		String sql = getQuery("OUMAGENC_ADDPH_DELETE_PHONES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Phones phones : lstPhones) {
			parameters.add(new BeanPropertySqlParameterSource(phones));
		}
		try {
			String tableName = "PHONES";
			String whereClause = "PHONE_ID = :phoneId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method addPhDeletePhones", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPhones.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

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
	public List<InternetAddresses> iAddExecuteQuery(final InternetAddresses objSearchDao) {
		final String sql = getQuery("OUMAGENC_IADD_FIND_INTERNET_ADDRESSES");
		final RowMapper<InternetAddresses> InternetAddressesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				InternetAddresses.class, internetAddressesMapping);
		List<InternetAddresses> returnList = new ArrayList<InternetAddresses>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("ownerId", objSearchDao.getOwnerId()),
					InternetAddressesRowMapper);
		} catch (Exception e) {
			logger.error("iAddExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstInternetAddresses
	 *            List<InternetAddresses> iaddInsertInternetAddresses
	 * @return Integer
	 *
	 * 
	 */

	public Integer iAddInsertInternetAddresses(final List<InternetAddresses> lstInternetAddresses) {
		String sql = getQuery("OUMAGENC_IADD_INSERT_INTERNET_ADDRESSES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (InternetAddresses internetAddresses : lstInternetAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(internetAddresses));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstInternetAddresses.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstInternetAddresses
	 *            List<InternetAddresses>
	 */
	public Integer iAddUpdateInternetAddresses(final List<InternetAddresses> lstInternetAddresses) {
		String sql = getQuery("OUMAGENC_IADD_UPDATE_INTERNET_ADDRESSES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (InternetAddresses internetAddresses : lstInternetAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(internetAddresses));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstInternetAddresses.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 * iAddDeleteInternetAddresses
	 * 
	 * @param lstInternetAddresses
	 * @return Integer
	 */
	public Integer iAddDeleteInternetAddresses(final List<InternetAddresses> lstInternetAddresses) {
		String sql = getQuery("OUMAGENC_IADD_DELETE_INTERNET_ADDRESSES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (InternetAddresses internetAddresses : lstInternetAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(internetAddresses));
		}
		try {
			String tableName = "INTERNET_ADDRESSES";
			String whereClause = "INTERNET_ADDRESS_ID = :internetAddressId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method iAddDeleteInternetAddresses", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstInternetAddresses.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table addressesExecuteQuery
	 * 
	 * @param objSearchDao
	 *            Addresses
	 * @return returnList
	 */
	public List<Addresses> addressesExecuteQuery(final Addresses objSearchDao) {
		final String sql = getQuery("OUMAGENC_ADDRESSES_FIND_ADDRESSES");
		final RowMapper<Addresses> AddressesRowMapper = Row2BeanRowMapper.makeMapping(sql, Addresses.class,
				addressesMapping);
		List<Addresses> returnList = new ArrayList<Addresses>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), AddressesRowMapper);
		} catch (Exception e) {
			logger.error("addressesExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAddresses
	 *            List<Addresses>
	 *
	 * 
	 */
	public Integer addressesUpdateAddresses(final List<Addresses> lstAddresses) {
		String sql = getQuery("OUMAGENC_ADDRESSES_UPDATE_ADDRESSES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Addresses addresses : lstAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(addresses));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAddresses.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAddresses
	 *            List<Addresses>
	 *
	 * 
	 */
	public Integer addressesDeleteAddresses(final List<Addresses> lstAddresses) {
		String sql = getQuery("OUMAGENC_ADDRESSES_DELETE_ADDRESSES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Addresses addresses : lstAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(addresses));
		}
		try {
			String tableName = "ADDRESSES";
			String whereClause = null;
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method addressesDeleteAddresses", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAddresses.size() == returnArray.length) {
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
	public List<Caseloads> rgCaseloadRecordGroup() {
		final String sql = getQuery("OUMAGENC_FIND_RGCASELOAD");
		final RowMapper<Caseloads> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class, mMapping);
		List<Caseloads> returnList = new ArrayList<Caseloads>();
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgCaseloadRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return returnList
	 */
	public List<ReferenceCodes> rgTypeRecordGroup() {
		final String sql = getQuery("OUMAGENC_FIND_RGTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgTypeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query rgIclassRecordGroup
	 * 
	 * @return returnList
	 */

	public List<ReferenceCodes> rgIclassRecordGroup() {
		final String sql = getQuery("OUMAGENC_FIND_RGICLASS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgIclassRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * corpOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<VCorporateAddresses> corpOnCheckDeleteMaster(final VCorporateAddresses paramBean) {
		final String sql = getQuery("OUMAGENC_CORP_ONCHECKDELETEMASTER_ADDRESS");
		final RowMapper<VCorporateAddresses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VCorporateAddresses.class, vCorporateAddressesMapping);
		List<VCorporateAddresses> returnList = new ArrayList<VCorporateAddresses>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("corporateId", paramBean.getCorporateId()),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("corpOnCheckDeleteMaster", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * corpOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<Phones> corpOnCheckDeleteMaster(final Phones paramBean) {
		final String sql = getQuery("OUMAGENC_CORP_ONCHECKDELETEMASTER_PHONE");
		final RowMapper<Phones> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Phones.class, phonesMapping);
		List<Phones> returnList = new ArrayList<Phones>();
		try {

			returnList = namedParameterJdbcTemplate.query(sql, createParams("corporateId", paramBean.getOwnerId()),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("corpOnCheckDeleteMaster", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * corpOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<InternetAddresses> corpOnCheckDeleteMaster(final InternetAddresses paramBean) {
		final String sql = getQuery("OUMAGENC_CORP_ONCHECKDELETEMASTER_INTERNET");
		final RowMapper<InternetAddresses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, InternetAddresses.class,
				internetAddressesMapping);
		List<InternetAddresses> returnList = new ArrayList<InternetAddresses>();
		try {

			returnList = namedParameterJdbcTemplate.query(sql, createParams("corporateId", paramBean.getOwnerId()),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("corpOnCheckDeleteMaster", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * corpPostQuery
	 * 
	 * @param params
	 */
	public List<Caseloads> corpPostQuery(final Caseloads paramBean) {
		final String sql = getQuery("OUMAGENC_CORP_POSTQUERY");
		final RowMapper<Caseloads> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class,
				caseloadsMapping);
		List<Caseloads> returnList = new ArrayList<Caseloads>();
		try {

			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("corpPostQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * addrOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<Phones> addrOnCheckDeleteMaster(final Phones paramBean) {
		final String sql = getQuery("OUMAGENC_ADDR_ONCHECKDELETEMASTER");

		final RowMapper<Phones> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Phones.class, phonesMapping);
		List<Phones> returnList = new ArrayList<Phones>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("addrOnCheckDeleteMaster", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * addrOnCheckDeleteMaster
	 * 
	 * @param params
	 * @return returnList
	 */
	public List<Addresses> addrOnCheckDeleteMaster(final Addresses paramBean) {
		final String sql = getQuery("OUMAGENC_ADDR_ONCHECKDELETEMASTER");
		final RowMapper<Addresses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Addresses.class,
				addressesMapping);
		List<Addresses> returnList = new ArrayList<Addresses>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("addrOnCheckDeleteMaster", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * oumagencKeyExit
	 * 
	 * @param params
	 * @return returnList
	 */
	public List<CorporateTypes> oumagencKeyExit(final CorporateTypes paramBean) {
		final String sql = getQuery("OUMAGENC_OUMAGENC_KEYEXIT");
		final RowMapper<CorporateTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, CorporateTypes.class,
				corporateTypesMapping);

		List<CorporateTypes> returnList = new ArrayList<CorporateTypes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("oumagencKeyExit", e);
		}
		return returnList;
	}

	public Integer corpInsertphones(final List<Phones> list) {
		String sql = getQuery("OUMAGENC_ADDPH_INSERT_PHONES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Phones listphone : list) {
			parameters.add(new BeanPropertySqlParameterSource(listphone));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<Phones> corPhoneExecuteQuery(final Phones searchRecord) {
		final String sql = getQuery("OUMAGENC_CRPH_FIND_PHONES");
		final RowMapper<Phones> PhonesRowMapper = Row2BeanRowMapper.makeMapping(sql, Phones.class, phonesMapping);
		List<Phones> returnList = new ArrayList<Phones>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("ownerId", searchRecord.getOwnerId()),
					PhonesRowMapper);
		} catch (Exception e) {
			logger.error("addPhExecuteQuery", e);
		}
		return returnList;
	}

	@Override
	public BigDecimal getCorporateChilds(final BigDecimal corporateId) {
		final String sql = getQuery("OUMAGENC_GET_CORPORATE_CHILDS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("corporateId", corporateId),
				BigDecimal.class);
	}

	@Override
	public BigDecimal getCorporateId() {
		final String sql = getQuery("OUMAGENC_GET_CORPORATE_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), BigDecimal.class);
	}
	
	
	@Override
	public Phones getphonesoldRecord(Long phoneId) {
		final String sql = getQuery("OUMAGENC_PHONES_OLD_RECORD");
		Phones phones=new Phones();
		try {
			phones= namedParameterJdbcTemplate.queryForObject(sql, createParams("phoneId",phoneId), new BeanPropertyRowMapper<Phones>(Phones.class));
		}catch(Exception e) {
			logger.error(this.getClass().getName()+" getphonesoldRecord"+e);
		}
		return phones;
	}
	
	@Override
	public Addresses getAddressoldRecord(Long addressId) {
		final String sql = getQuery("OUMAGENC_ADDRESSES_OLD_RECORD");
		Addresses addresses=new Addresses();
		try {
			addresses= namedParameterJdbcTemplate.queryForObject(sql, createParams("addressId",addressId), new BeanPropertyRowMapper<Addresses>(Addresses.class));
		}catch(Exception e) {
			logger.error(this.getClass().getName()+" getphonesoldRecord"+e);
		}
		return addresses;
	}
}

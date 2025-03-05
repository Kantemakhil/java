package net.syscon.s4.inst.demographicsbiometrics.impl;

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
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.OffenderCurfews;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VOffenderEducationAddresses;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AddressUsages;
//import net.syscon.s4.im.beans.Addresses;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.demographicsbiometrics.OcdaddreRepository;

/**
 * Class OcdaddreRepositoryImpl
 */
@Repository
public class OcdaddreRepositoryImpl extends RepositoryBase implements OcdaddreRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdaddreRepositoryImpl.class.getName());

	/**
	 * Creates new OcdaddreRepositoryImpl class Object
	 */
	public OcdaddreRepositoryImpl() {
	}
	private final Map<String, FieldMapper> internetAddressesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INTERNET_ADDRESS_ID", new FieldMapper("internetAddressId"))
			.put("OWNER_CLASS", new FieldMapper("ownerClass"))
			.put("OWNER_ID", new FieldMapper("ownerId"))
			.put("OWNER_SEQ", new FieldMapper("ownerSeq"))
			.put("OWNER_CODE", new FieldMapper("ownerCode"))
			.put("INTERNET_ADDRESS_CLASS", new FieldMapper("internetAddressClass"))
			.put("INTERNET_ADDRESS", new FieldMapper("internetAddress"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();
	private final Map<String, FieldMapper> vAddressesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ADDRESS_ID", new FieldMapper("addressId"))
			.put("ADDRESS_TYPE", new FieldMapper("addressType"))
			.put("ADDRESS_TYPE_DESC", new FieldMapper("addressTypeDesc"))
			.put("OWNER_CLASS", new FieldMapper("ownerClass"))
			.put("OWNER_ID", new FieldMapper("ownerId"))
			.put("OWNER_SEQ", new FieldMapper("ownerSeq"))
			.put("OWNER_CODE", new FieldMapper("ownerCode"))
			.put("START_DATE", new FieldMapper("startDate"))
			.put("END_DATE", new FieldMapper("endDate"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("FULL_ADDRESS", new FieldMapper("fullAddress"))
			.put("HOUSE", new FieldMapper("house"))
			.put("STREET", new FieldMapper("street"))
			.put("AREA", new FieldMapper("area"))
			.put("COUNTRY", new FieldMapper("country"))
			.put("SUITE_NUMBER", new FieldMapper("suiteNumber"))
			.put("STREET_DIRECTION", new FieldMapper("streetDirection"))
			.put("STREET_DIRECTION_DESC", new FieldMapper("streetDirectionDesc"))
			.put("STREET_INFORMATION", new FieldMapper("streetInformation"))
			.put("CITY_CODE", new FieldMapper("cityCode"))
			.put("CITY_NAME", new FieldMapper("cityName"))
			.put("PROV_STATE_CODE", new FieldMapper("provStateCode"))
			.put("PROV_STATE_DESC", new FieldMapper("provStateDesc"))
			.put("ZIP_POSTAL_CODE", new FieldMapper("zipPostalCode"))
			.put("COUNTRY_CODE", new FieldMapper("countryCode"))
			.put("COUNTRY_DESC", new FieldMapper("countryDesc"))
			.put("CAPACITY", new FieldMapper("capacity"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("PRIMARY_FLAG", new FieldMapper("primaryFlag"))
			.put("MAIL_FLAG", new FieldMapper("mailFlag"))
			.put("SERVICES_FLAG", new FieldMapper("servicesFlag"))
			.put("NO_FIXED_ADDRESS_FLAG", new FieldMapper("noFixedAddressFlag"))
			.put("SPECIAL_NEEDS", new FieldMapper("specialNeeds"))
			.put("VALIDATED_FLAG", new FieldMapper("validatedFlag"))
			.put("CONTACT_PERSON_NAME", new FieldMapper("contactPersonName"))
			.put("BUSINESS_HOUR", new FieldMapper("businessHour"))
			.put("MAIL_CARE_OF", new FieldMapper("mailCareOf"))
			.put("ADDRESS_LINE1", new FieldMapper("streetAddress"))
			
			.build();
	private final Map<String, FieldMapper> addressesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ADDRESS_ID", new FieldMapper("addressId"))
			.put("OWNER_CLASS", new FieldMapper("ownerClass"))
			.put("OWNER_ID", new FieldMapper("ownerId"))
			.put("OWNER_SEQ", new FieldMapper("ownerSeq"))
			.put("OWNER_CODE", new FieldMapper("ownerCode"))
			.put("CITY_CODE", new FieldMapper("cityCode"))
			.put("COUNTRY_CODE", new FieldMapper("countryCode"))
			.put("VALIDATED_PAF_FLAG", new FieldMapper("validatedPafFlag"))
			.put("PRIMARY_FLAG", new FieldMapper("primaryFlag"))
			.put("MAIL_FLAG", new FieldMapper("mailFlag"))
			.put("CAPACITY", new FieldMapper("capacity"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("NO_FIXED_ADDRESS_FLAG", new FieldMapper("noFixedAddressFlag"))
			.put("SERVICES_FLAG", new FieldMapper("servicesFlag"))
			.put("SPECIAL_NEEDS_CODE", new FieldMapper("specialNeedsCode"))
			.put("CONTACT_PERSON_NAME", new FieldMapper("contactPersonName"))
			.put("BUSINESS_HOUR", new FieldMapper("businessHour"))
			.put("START_DATE", new FieldMapper("startDate"))
			.put("END_DATE", new FieldMapper("endDate"))
			.put("CITY_NAME", new FieldMapper("cityName"))
			.put("PROV_STATE_CODE", new FieldMapper("provStateCode"))
			.put("STREET", new FieldMapper("street"))
			.put("ZIP_POSTAL_CODE", new FieldMapper("zipPostalCode"))
			.put("SUITE_NUMBER", new FieldMapper("suiteNumber"))
			.put("STREET_NUMBER", new FieldMapper("streetNumber"))
			.put("STREET_DIRECTION", new FieldMapper("streetDirection"))
			.put("MAIL_CARE_OF", new FieldMapper("mailCareOf"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("VALIDATED_PAYLOAD", new FieldMapper("verifiedPayload"))
			.build();
	private final Map<String, FieldMapper> offenderCurfewsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ADDRESS_ID", new FieldMapper("addressId"))
			.build();
	private final Map<String, FieldMapper> addressUsagesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("END_DATE", new FieldMapper("endDate"))
			.put("ADDRESS_TYPE_DESC", new FieldMapper("addressTypeDesc"))
			.put("OWNER_ID", new FieldMapper("ownerId"))
			.put("OWNER_SEQ", new FieldMapper("ownerSeq"))
			.put("AREA", new FieldMapper("area"))
			.put("NO_FIXED_ADDRESS_FLAG", new FieldMapper("noFixedAddressFlag"))
			.put("MAIL_FLAG", new FieldMapper("mailFlag"))
			.put("CITY_NAME", new FieldMapper("cityName"))
			.put("SPECIAL_NEEDS", new FieldMapper("specialNeeds"))
			.put("COUNTRY", new FieldMapper("country"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("FULL_ADDRESS", new FieldMapper("fullAddress"))
			.put("STREET_DIRECTION", new FieldMapper("streetDirection"))
			.put("COUNTRY_DESC", new FieldMapper("countryDesc"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("PHONE_ID", new FieldMapper("phoneId"))
			.put("STREET_INFORMATION", new FieldMapper("streetInformation"))
			.put("SERVICES_FLAG", new FieldMapper("servicesFlag"))
			.put("START_DATE", new FieldMapper("startDate"))
			.put("ADDRESS_USAGE", new FieldMapper("addressUsage"))
			.put("MAIL_CARE_OF", new FieldMapper("mailCareOf"))
			.put("ADDRESS_ID", new FieldMapper("addressId"))
			.put("COUNTRY_CODE", new FieldMapper("countryCode"))
			.put("INTERNET_ADDRESS_ID", new FieldMapper("internetAddressId"))
			.put("PHONE_TYPE", new FieldMapper("phoneType"))
			.put("INTERNET_ADDRESS_CLASS", new FieldMapper("internetAddressClass"))
			.put("VALIDATED_PAF_FLAG", new FieldMapper("validatedPafFlag"))
			.put("SPECIAL_NEEDS_CODE", new FieldMapper("specialNeedsCode"))
			.put("PROV_STATE_DESC", new FieldMapper("provStateDesc"))
			.put("ADDRESS_TYPE", new FieldMapper("addressType"))
			.put("VALIDATED_FLAG", new FieldMapper("validatedFlag"))
			.put("EXT_NO", new FieldMapper("extNo"))
			.put("SUITE_NUMBER", new FieldMapper("suiteNumber"))
			.put("ZIP_POSTAL_CODE", new FieldMapper("zipPostalCode"))
			.put("BUSINESS_HOUR", new FieldMapper("businessHour"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("PHONE_NO", new FieldMapper("phoneNo"))
			.put("PRIMARY_FLAG", new FieldMapper("primaryFlag"))
			.put("STREET", new FieldMapper("street"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("CAPACITY", new FieldMapper("capacity"))
			.put("OWNER_CLASS", new FieldMapper("ownerClass"))
			.put("OWNER_CODE", new FieldMapper("ownerCode"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("CITY_CODE", new FieldMapper("cityCode"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("STREET_DIRECTION_DESC", new FieldMapper("streetDirectionDesc"))
			.put("CONTACT_PERSON_NAME", new FieldMapper("contactPersonName"))
			.put("INTERNET_ADDRESS", new FieldMapper("internetAddress"))
			.put("PROV_STATE_CODE", new FieldMapper("provStateCode"))
			.put("STREET_NUMBER", new FieldMapper("streetNumber"))
			.put("HOUSE", new FieldMapper("house")).build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION",   new FieldMapper("description"))
			.put("LIST_SEQ", 	  new FieldMapper("listSeq"))
			.put("CODE",          new FieldMapper("code")).build();
	private final Map<String, FieldMapper> phonesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OWNER_ID", 						new FieldMapper("ownerId"))
			.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
			.put("PHONE_ID", 						new FieldMapper("phoneId"))
			.put("OWNER_CLASS", 					new FieldMapper("ownerClass"))
			.put("OWNER_CODE", 						new FieldMapper("ownerCode"))
			.put("EXT_NO", 							new FieldMapper("extNo"))
			.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
			.put("OWNER_SEQ", 						new FieldMapper("ownerSeq"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
			.put("PHONE_TYPE", 						new FieldMapper("phoneType"))
			.put("PHONE_NO", 						new FieldMapper("phoneNo"))
			.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
			.build();


	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgTownRecordGroup() {
		final String sql = getQuery("OCDADDRE_FIND_RGTOWN");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("In rgTownRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgCountryRecordGroup() {
		final String sql = getQuery("OCDADDRE_FIND_RGCOUNTRY");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("In rgCountryRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgSdirRecordGroup() {
		final String sql = getQuery("OCDADDRE_FIND_RGSDIR");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("In rgSdirRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgStateRecordGroup() {
		final String sql = getQuery("OCDADDRE_FIND_RGSTATE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("In rgSdirRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgAddressTypeRecordGroup() {
		final String sql = getQuery("OCDADDRE_FIND_RGADDRESSTYPE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("In rgAddressTypeRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgPhoneTypeRecordGroup() {
		final String sql = getQuery("OCDADDRE_FIND_RGPHONETYPE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("In rgPhoneTypeRecordGroup method : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param vAddresses
	 *
	 * @return List<VAddresses>
	 *
	 * @
	 */
	public List<VAddresses> vAddSearchVAddresses(final VAddresses vAddresses) {
		final String sql = getQuery("OCDADDRE_VADD_FIND_V_ADDRESSES");
		final RowMapper<VAddresses> VAddressesRowMapper = Row2BeanRowMapper.makeMapping(sql, VAddresses.class,
				vAddressesMapping);
		final ArrayList<VAddresses> returnList = (ArrayList<VAddresses>) namedParameterJdbcTemplate.query(sql,
				createParams("OWNER_ID", vAddresses.getOwnerId()), VAddressesRowMapper);
		if(returnList!=null && !returnList.isEmpty()) {
			for(VAddresses address:returnList) {
	              if(address.getProvStateDesc() == null) {
	            		address.setProvStateDesc(address.getProvStateCode());
	              }
				
	              if(address.getCityName() == null) {
	          		address.setCityName(address.getCityCode());
	            }
			}
			}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param addresses
	 *
	 * @return List<Addresses>
	 *
	 * @
	 */
	public Addresses searchAddresses(final Addresses addresses) {
		final String sql = getQuery("OCDADDRE_ADDR_FIND_ADDRESSES");
		Addresses returnList = new Addresses();
		final RowMapper<Addresses> addressesRowMapper = Row2BeanRowMapper.makeMapping(sql, Addresses.class,
				addressesMapping);
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("ADDRESS_ID", addresses.getAddressId()), addressesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("In method searchAddresses : ",e);
			returnList = new Addresses();
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstAddresses
	 *
	 * @return Integer
	 *
	 * @
	 */
	public Integer addrInsertAddresses(final List<Addresses> lstAddresses) {
		final String sql = getQuery("OCDADDRE_ADDR_INSERT_ADDRESSES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Addresses addresses : lstAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(addresses));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch(Exception e) {
			logger.error("In method addrInsertAddresses : "+e.getMessage());
		}
		
		if (lstAddresses.size() == returnArray.length) {
			final Long addreseId = Long.valueOf(lstAddresses.get(0).getAddressId());
			return addreseId.intValue();
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAddresses
	 * 
	 * @return Integer
	 *
	 * @
	 */
	public Integer addrUpdateAddresses(final List<Addresses> lstAddresses) {
		final String sql = getQuery("OCDADDRE_ADDR_UPDATE_ADDRESSES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Addresses addresses : lstAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(addresses));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			}catch(Exception e) {
				logger.error("In method addrUpdateAddresses : "+e.getMessage());
			}
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
	 * 
	 * @return Integer
	 *
	 * @
	 */
	public Integer addrDeleteAddresses(final List<Addresses> lstAddresses) {
		final String sql = getQuery("OCDADDRE_ADDR_DELETE_ADDRESSES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Addresses addresses : lstAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(addresses));
		}
		try {
			String tableName = "ADDRESSES";
			String whereClause = "ADDRESS_ID=:addressId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method addrDeleteAddresses", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAddresses.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param addressUsages
	 *
	 * @return List<AddressUsages>
	 *
	 * @
	 */
	public List<AddressUsages> addrUsageSearchAddressUsages(final AddressUsages addressUsages) {
		final String sql = getQuery("OCDADDRE_ADDRUSAGE_FIND_ADDRESS_USAGES");
		final RowMapper<AddressUsages> AddressUsagesRowMapper = Row2BeanRowMapper.makeMapping(sql, AddressUsages.class,
				addressUsagesMapping);
		final ArrayList<AddressUsages> returnList = (ArrayList<AddressUsages>) namedParameterJdbcTemplate.query(sql,
				createParams("ADDRESS_ID", addressUsages.getAddressId()), AddressUsagesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstAddressUsages
	 *
	 * @return Integer
	 *
	 * @
	 */
	public Integer addrUsageInsertAddressUsages(final List<AddressUsages> lstAddressUsages) {
		final String sql = getQuery("OCDADDRE_ADDRUSAGE_INSERT_ADDRESS_USAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AddressUsages addressUsages : lstAddressUsages) {
			parameters.add(new BeanPropertySqlParameterSource(addressUsages));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch(Exception e) {
			logger.error("addrUsageInsertAddressUsages"+e.getMessage());
		}
		
		if (lstAddressUsages.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAddressUsages
	 * 
	 * @return Integer
	 *
	 * @
	 */
	public Integer addrUsageUpdateAddressUsages(final List<AddressUsages> lstAddressUsages) {
		final String sql = getQuery("OCDADDRE_ADDRUSAGE_UPDATE_ADDRESS_USAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AddressUsages addressUsages : lstAddressUsages) {
			parameters.add(new BeanPropertySqlParameterSource(addressUsages));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAddressUsages.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAddressUsages
	 * 
	 * @return Integer
	 *
	 * @
	 */
	public Integer addrUsageDeleteAddressUsages(final List<AddressUsages> lstAddressUsages) {
		final String sql = getQuery("OCDADDRE_ADDRUSAGE_DELETE_ADDRESS_USAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AddressUsages addressUsages : lstAddressUsages) {
			parameters.add(new BeanPropertySqlParameterSource(addressUsages));
		}
		try {
			String tableName = "ADDRESS_USAGES";
			String whereClause = "ADDRESS_ID = :addressId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method addrUsageDeleteAddressUsages", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAddressUsages.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param phones
	 *
	 * @return List<Phones>
	 *
	 * @
	 */
	public List<Phones> phoneAddrSearchPhones(final Phones phones) {
		final String sql = getQuery("OCDADDRE_PHONEADDR_FIND_PHONES");
		final RowMapper<Phones> PhonesRowMapper = Row2BeanRowMapper.makeMapping(sql, Phones.class, phonesMapping);
		final ArrayList<Phones> returnList = (ArrayList<Phones>) namedParameterJdbcTemplate.query(sql,
				createParams("OWNER_ID", phones.getOwnerId(),"OWNER_CLASS",phones.getOwnerClass()), PhonesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstPhones
	 *
	 * @return Integer
	 *
	 * @
	 */
	public Integer phoneAddrInsertPhones(final List<Phones> lstPhones) {
		final String sql = getQuery("OCDADDRE_PHONEADDR_INSERT_PHONES");
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
	 * This method is used to update the data base tables based on
	 *
	 * @param lstPhones
	 * 
	 * @return Integer
	 *
	 * @
	 */
	public Integer phoneAddrUpdatePhones(final List<Phones> lstPhones) {
		final String sql = getQuery("OCDADDRE_PHONEADDR_UPDATE_PHONES");
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
	 * 
	 * @return Integer
	 *
	 * @
	 */
	public Integer phoneAddrDeletePhones(final List<Phones> lstPhones) {
		final String sql = getQuery("OCDADDRE_PHONEADDR_DELETE_PHONES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Phones phones : lstPhones) {
			parameters.add(new BeanPropertySqlParameterSource(phones));
		}
		try {
			String tableName = "PHONES";
			String whereClause = "PHONE_ID=:phoneId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method phoneAddrDeletePhones", e);
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
	 * @param internetAddresses
	 *
	 * @return List<InternetAddresses>
	 *
	 * @
	 */
	public List<InternetAddresses> emailSearchInternetAddresses(final InternetAddresses internetAddresses) {
		final String sql = getQuery("OCDADDRE_EMAIL_FIND_INTERNET_ADDRESSES");
		final RowMapper<InternetAddresses> InternetAddressesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				InternetAddresses.class, internetAddressesMapping);
		final ArrayList<InternetAddresses> returnList = (ArrayList<InternetAddresses>) namedParameterJdbcTemplate
				.query(sql, createParams("OWNER_ID", internetAddresses.getOwnerId()), InternetAddressesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstInternetAddresses
	 *
	 * @return Integer
	 *
	 * @
	 */
	public Integer emailInsertInternetAddresses(final List<InternetAddresses> lstInternetAddresses) {
		final String sql = getQuery("OCDADDRE_EMAIL_INSERT_INTERNET_ADDRESSES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final InternetAddresses internetAddresses : lstInternetAddresses) {
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
	 * 
	 * @return Integer
	 *
	 * @
	 */
	public Integer emailUpdateInternetAddresses(final List<InternetAddresses> lstInternetAddresses) {
		final String sql = getQuery("OCDADDRE_EMAIL_UPDATE_INTERNET_ADDRESSES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final InternetAddresses internetAddresses : lstInternetAddresses) {
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
	 *
	 * @param lstInternetAddresses
	 * 
	 * @return Integer
	 *
	 * @
	 */
	public Integer emailDeleteInternetAddresses(final List<InternetAddresses> lstInternetAddresses) {
		final String sql = getQuery("OCDADDRE_EMAIL_DELETE_INTERNET_ADDRESSES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final InternetAddresses internetAddresses : lstInternetAddresses) {
			parameters.add(new BeanPropertySqlParameterSource(internetAddresses));
		}
		try {
			String tableName = "INTERNET_ADDRESSES";
			String whereClause = "INTERNET_ADDRESS_ID=:internetAddressId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method emailDeleteInternetAddresses", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstInternetAddresses.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param vAddresses
	 * 
	 * @return List<Object>
	 * 
	 * @
	 *
	 */
	public List<Object> offbkgoncheckdeletemastervaddcur(final VAddresses vAddresses) {
		final String sql = getQuery("OCDADDRE_OFF_BKG_ONCHECKDELETEMASTER_V_ADD_CUR");
		final ArrayList<Object> returnList = (ArrayList<Object>) namedParameterJdbcTemplate.queryForList(sql,
				createParams("ROOTOFFENDERID", vAddresses.getOwnerId()), Object.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param phones
	 * 
	 * @return List<Object>
	 * 
	 * @
	 *
	 */
	public List<Object> offBkgOnCheckDeleteMasterphoneGlobalCur(final Phones phones) {
		final String sql = getQuery("OCDADDRE_OFF_BKG_ONCHECKDELETEMASTER_PHONE_GLOBAL_CUR");
		final ArrayList<Object> returnList = (ArrayList<Object>) namedParameterJdbcTemplate.queryForList(sql,
				createParams("ROOTOFFENDERID", phones.getOwnerId()), Object.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param internetAddresses
	 * 
	 * @return List<Object>
	 *
	 */
	public List<Object> offBkgOnCheckDeleteMasteremailCur(final InternetAddresses internetAddresses) {
		final String sql = getQuery("OCDADDRE_OFF_BKG_ONCHECKDELETEMASTER_EMAIL_CUR");
		final ArrayList<Object> returnList = (ArrayList<Object>) namedParameterJdbcTemplate.queryForList(sql,
				createParams("ROOTOFFENDERID", internetAddresses.getOwnerId()), Object.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param addresses
	 * 
	 * @return List<Object>
	 * 
	 * @
	 *
	 */
	public List<Object> offBkgOnCheckDeleteMasteraddrCur(final Addresses addresses) {
		final String sql = getQuery("OCDADDRE_OFF_BKG_ONCHECKDELETEMASTER_ADDR_CUR");
		final ArrayList<Object> returnList = (ArrayList<Object>) namedParameterJdbcTemplate.queryForList(sql,
				createParams("ROOTOFFENDERID", addresses.getOwnerId()), Object.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param addresses
	 * 
	 * @return Object
	 *
	 */
	public Object vAddOnCheckDeleteMasteraddrCur(final Addresses addresses) {
		final String sql = getQuery("OCDADDRE_V_ADD_ONCHECKDELETEMASTER_ADDR_CUR");
		Object returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("ADDRESSID", addresses.getAddressId()), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param referenceCodes
	 * 
	 * @return List<Object>
	 *
	 */
	public List<Object> nbtCityKeyListvalgetCityDescription(final ReferenceCodes referenceCodes) {
		final String sql = getQuery("OCDADDRE_NBT_CITY_KEYLISTVAL_GET_CITY_DESCRIPTION_C");
		List<Object> returnObj = namedParameterJdbcTemplate.queryForList(sql, createParams(), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param phones
	 * 
	 * @return List<Object>
	 *
	 */
	public List<Object> addrOnCheckDeleteMasterphoneAddrCur(final Phones phones) {
		final String sql = getQuery("OCDADDRE_ADDR_ONCHECKDELETEMASTER_PHONE_ADDR_CUR");
		final List<Object> returnList = (List<Object>) namedParameterJdbcTemplate.queryForList(sql,
				createParams("ADDRESSID", phones.getOwnerId()), Object.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param addressUsages
	 * 
	 * @return Object
	 *
	 */
	public Object addrOnCheckDeleteMasteraddrUsageCur(final AddressUsages addressUsages) {
		final String sql = getQuery("OCDADDRE_ADDR_ONCHECKDELETEMASTER_ADDR_USAGE_CUR");
		Object returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("ADDRESSID", addressUsages.getAddressId()), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param referenceCodes
	 *
	 * @return ReferenceCodes
	 */

	public List<ReferenceCodes> addrWhenCreateRecordgetCountryCur(final ReferenceCodes referenceCodes) {
		final String sql = getQuery("OCDADDRE_ADDR_WHENCREATERECORD_GET_COUNTRY_CUR");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnObj = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams("P_DOMAIN", referenceCodes.getDomain()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param omsModules
	 * 
	 * @return OmsModules
	 */
	public OmsModules createFormGlobals(final OmsModules omsModules) {
		final String sql = getQuery("OCDADDRE_CREATE_FORM_GLOBALS_");
	 namedParameterJdbcTemplate.queryForObject(sql,
		 createParams(omsModules), OmsModules.class);
		return null;// returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param addressUsages
	 *
	 * @return AddressUsages
	 */
	public Object addressTypecheckActiveTypeCur(final AddressUsages addressUsages) {
		final String sql = getQuery("OCDADDRE_ADDRESS_TYPE_CHECK_ACTIVE_TYPE_CUR");
		Object returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("ADDRESSID", addressUsages.getAddressId()), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param referenceCodes
	 *
	 * @return Object
	 */
	public Object validateCityInfogetCityDescription(final ReferenceCodes referenceCodes) {
		final String sql = getQuery("OCDADDRE_VALIDATE_CITY_INFO_GET_CITY_DESCRIPTION_C");
		Object returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @param referenceCodes
	 * 
	 * @return ReferenceCodes
	 */
	public List<ReferenceCodes> validateCityInfogetCityCode(final ReferenceCodes referenceCodes) {
		final String sql = getQuery("OCDADDRE_VALIDATE_CITY_INFO_GET_CITY_CODE_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final List<ReferenceCodes> returnObj = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param addressUsages
	 * 
	 * @return Object
	 */
	public Object checkHdcAddressActivecheckHdcAddressCur(final VAddresses addressUsages) {
		final String sql = getQuery("OCDADDRE_CHECK_HDC_ADDRESS_ACTIVE_CHECK_HDC_ADDRESS_CUR");
		Object returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OWNERID", addressUsages.getOwnerId()), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param offenderCurfews
	 * 
	 * @return List<OffenderCurfews>
	 */
	public List<OffenderCurfews> checkHdcAddressActive(final OffenderCurfews offenderCurfews) {
		final String sql = getQuery("OCDADDRE_CHECK_HDC_ADDRESS_ACTIVE_");
		final RowMapper<OffenderCurfews> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderCurfews.class,
				offenderCurfewsMapping);
		final ArrayList<OffenderCurfews> returnList = (ArrayList<OffenderCurfews>) namedParameterJdbcTemplate.query(sql,
				createParams(offenderCurfews), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param vAddresses
	 *
	 * @return Object
	 */
	public Object checkHdcAddressExistcheckHdcAddressCur(final VAddresses vAddresses) {
		final String sql = getQuery("OCDADDRE_CHECK_HDC_ADDRESS_EXIST_CHECK_HDC_ADDRESS_CUR");
		Object returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OWNERID", vAddresses.getOwnerId(), "ADDRESSID", vAddresses.getAddressId()), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return Object
	 */
	public Object preInsertgetAddrId() {
		final String sql = getQuery("OCDADDRE_ADDR_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Object.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return Object
	 */
	public Object preInsertgetPhoneId() {
		final String sql = getQuery("OCDADDRE_PHONES_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Object.class);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstPhones
	 *
	 * @return Integer
	 *
	 * @
	 */
	public Integer selectAddressUsageCount(final Long addressId, final String addressUsage) {
		final String sql = getQuery("OCDADDRE_ADDRESS_USAGE_COUNT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("ADDRESS_ID", addressId, "ADDRESS_USAGE", addressUsage), Integer.class);

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return Object
	 */
	public Object preInsertgetInternetAddrId() {
		final String sql = getQuery("OCDADDRE_INTERNET_ADDRESSES_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Object.class);
	}
	
	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAddresses
	 * 
	 * @return Integer
	 *
	 * @
	 */
	public Integer preUpdateAddresses(final Addresses bean) {
		final String sql = getQuery("OCDADDRE_ADDRESSES_PREUPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(bean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}
	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAddresses
	 * 
	 * @return Integer
	 *
	 * @
	 */
	public Integer preUpdateAddressesMailFlag(final Addresses bean) {
		final String sql = getQuery("OCDADDRE_ADDRESSES_PREUPDATE_MAILFLAG");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(bean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Addresses gettingOldRecord(long addressId) {
		final String sql = getQuery("OCDADDRE_ADDRESSES_GETTING_OLRECORD");
		Addresses returnObj=new Addresses();
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("addressId",addressId ),
				new BeanPropertyRowMapper<Addresses>(Addresses.class));
		return returnObj;
	}
		
}

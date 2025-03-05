package net.syscon.s4.inst.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.OcuoccupRepository;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.movementexternal.beans.RpOtherOccupants;
/**
 * Class OcuoccupRepositoryImpl
 */
@Repository
public class OcuoccupRepositoryImpl extends RepositoryBase implements OcuoccupRepository{

	private static Logger logger = LogManager.getLogger(OcuoccupRepositoryImpl.class.getName());
/**
 * Creates new OcuoccupRepositoryImpl class Object 
 */
public OcuoccupRepositoryImpl() {
}
//private final Map<String, FieldMapper> addressesMapping = new ImmutableMap.Builder<String, FieldMapper>()
//.put("NBT_PERSON_ID", 						new FieldMapper("nbtPersonId"))
//.put("'Y'", 						new FieldMapper(" 'y' "))
//.put("ADDRESS_ID", 						new FieldMapper("addressId"))
//.build();
private final Map<String, FieldMapper> rpOtherOccupantsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("EXT_NO", 						new FieldMapper("extNo"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("RP_OTHER_OCCUPANT_ID", 						new FieldMapper("rpOtherOccupantId"))
.put("CONTACTED_FLAG", 						new FieldMapper("contactedFlag"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
.put("RP_COMMENT", 						new FieldMapper("rpComment"))
.put("'Y'", 						new FieldMapper(" 'y' "))
.put("RELEASE_PLAN_ID", 						new FieldMapper("releasePlanId"))
.put("CONTACT_PHONE", 						new FieldMapper("contactPhone"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
.put("PRIMARY_FLAG", 						new FieldMapper("primaryFlag"))
.put("OFFENDER_CONTACT_PERSON_ID", 						new FieldMapper("offenderContactPersonId"))
.build();
private final Map<String, FieldMapper> moffenderContactPersonsreferenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("TRUNC(MONTHS_BETWEEN(SYSDATE", 						new FieldMapper("        trunc(monthsBetween(sysdate "))
.put("LAST_NAME||'", 						new FieldMapper("lastName||'"))
.put("CODE", 						new FieldMapper("code"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("PERSON_ID", 						new FieldMapper("personId"))
.put("BIRTHDATE", 						new FieldMapper("birthdate"))
.put("OFFENDER_CONTACT_PERSON_ID", 						new FieldMapper("offenderContactPersonId"))
.put("FIRST_NAME", 						new FieldMapper("firstName"))
.build();
private final Map<String, FieldMapper> allConstraintsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("TABLE_NAME", 						new FieldMapper(" tableName "))
.build();
private final Map<String, FieldMapper> phonesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("'N'", 						new FieldMapper("'n'"))
.put("OFFENDER_CONTACT_PERSON_ID", 						new FieldMapper("offenderContactPersonId"))
.put("EXT_NO", 						new FieldMapper("extNo"))
.put("NULL", 						new FieldMapper("null"))
.put("'YES'", 						new FieldMapper("'yes'"))
.put("LAST_NAME||", 						new FieldMapper("lastName||"))
.put("'NO'", 						new FieldMapper("'no'"))
.put("OFFENDER_BOOK_ID", 						new FieldMapper("offenderBookId"))
.put("PHONE_N", 						new FieldMapper("phoneN"))
.put("BIRTHDATE)/12", 						new FieldMapper("birthdate)/12"))
.put("NBT_PERSON_ID", 						new FieldMapper("nbtPersonId"))
.put("FIRST_NAM", 						new FieldMapper("firstNam"))
.put("'Y'", 						new FieldMapper("'y'"))
.put("COD", 						new FieldMapper("cod"))
.put("DESCRIPTIO", 						new FieldMapper("descriptio"))
.put("TRUNC(MONTHS_BETWEEN(SYSDATE", 						new FieldMapper(" trunc(monthsBetween(sysdate"))
.put("CONTACTED_FLA", 						new FieldMapper("contactedFla"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CODE", 						new FieldMapper("code"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> vAddressesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", new FieldMapper("description"))
.put("STREET_INFORMATION||'", new FieldMapper("streetInformation||'"))
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
.build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            RpOtherOccupants
	 *
	 * @return List<RpOtherOccupants>
	 *
	 * 
	 */
	public List<RpOtherOccupants> rpOtherOccupantsExecuteQuery(final RpOtherOccupants objSearchDao) {
		final String sql = getQuery("OCUOCCUP_RPOTHEROCCUPANTS_FIND_RP_OTHER_OCCUPANTS");
		final RowMapper<RpOtherOccupants> RpOtherOccupantsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				RpOtherOccupants.class, rpOtherOccupantsMapping);
		final ArrayList<RpOtherOccupants> returnList = (ArrayList<RpOtherOccupants>) namedParameterJdbcTemplate
				.query(sql, createParams("releasePlanId", objSearchDao.getReleasePlanId()), RpOtherOccupantsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstRpOtherOccupants
	 *            List<RpOtherOccupants>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer rpotheroccupantsInsertRpOtherOccupants(final List<RpOtherOccupants> lstRpOtherOccupants) {
		final String sql = getQuery("OCUOCCUP_RPOTHEROCCUPANTS_INSERT_RP_OTHER_OCCUPANTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final RpOtherOccupants list : lstRpOtherOccupants) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstRpOtherOccupants.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstRpOtherOccupants
	 *            List<RpOtherOccupants>
	 *
	 * 
	 */
	public Integer rpOtherOccupantsUpdateRpOtherOccupants(final List<RpOtherOccupants> lstRpOtherOccupants) {
		final String sql = getQuery("OCUOCCUP_RPOTHEROCCUPANTS_UPDATE_RP_OTHER_OCCUPANTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final RpOtherOccupants rpOtherOccupants : lstRpOtherOccupants) {
			parameters.add(new BeanPropertySqlParameterSource(rpOtherOccupants));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstRpOtherOccupants.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstRpOtherOccupants
	 *            List<RpOtherOccupants>
	 *
	 * 
	 */
	public Integer rpOtherOccupantsDeleteRpOtherOccupants(final List<RpOtherOccupants> lstRpOtherOccupants) {
		final String sql = getQuery("OCUOCCUP_RPOTHEROCCUPANTS_DELETE_RP_OTHER_OCCUPANTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final RpOtherOccupants rpOtherOccupants : lstRpOtherOccupants) {
			parameters.add(new BeanPropertySqlParameterSource(rpOtherOccupants));
		}
		try {
			String tableName = "RP_OTHER_OCCUPANTS";
			String whereCondition = "RP_OTHER_OCCUPANT_ID  = :rpOtherOccupantId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstRpOtherOccupants.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MOffenderContactPersonsReferenceCodes>
	 */
	public List<OffenderContactPersons> rgPersonNameRecordGroup(final String offenderBookId) {
		final String sql = getQuery("OCUOCCUP_FIND_RGPERSONNAME");
		final RowMapper<OffenderContactPersons> MOffenderContactPersonsReferenceCodesRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderContactPersons.class, moffenderContactPersonsreferenceCodesMapping);
		List<OffenderContactPersons> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId),
					MOffenderContactPersonsReferenceCodesRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 */
	public List<ReferenceCodes> rgContactTypesRecordGroup() {
		final String sql = getQuery("OCUOCCUP_FIND_RGCONTACTTYPES");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 */
	public List<ReferenceCodes> rgRelationshipsRecordGroup(final String contactCode) {
		final String sql = getQuery("OCUOCCUP_FIND_RGRELATIONSHIPS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("contactCode", contactCode), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * rpOtherOccupantsPostQuery
	 *
	 * @param params
	 *
	 */
	public List<Phones> rpOtherOccupantsPostQuery(final Phones paramBean) {
		final String sql = getQuery("OCUOCCUP_RP_OTHER_OCCUPANTS_POSTQUERY");
		final RowMapper<Phones> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Phones.class, phonesMapping);
		final ArrayList<Phones> returnList = (ArrayList<Phones>) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * ocuoccupKeyExit
	 *
	 * @param params
	 *
	 */
	public List<RpOtherOccupants> ocuoccupKeyExit(final RpOtherOccupants paramBean) {
		final String sql = getQuery("OCUOCCUP_OCUOCCUP_KEYEXIT");
		final RowMapper<RpOtherOccupants> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, RpOtherOccupants.class,
				rpOtherOccupantsMapping);
		final ArrayList<RpOtherOccupants> returnList = (ArrayList<RpOtherOccupants>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * ocuoccupPostFormsCommit
	 *
	 * @param params
	 *
	 */
	public List<RpOtherOccupants> ocuoccupPostFormsCommit(final RpOtherOccupants paramBean) {
		final String sql = getQuery("OCUOCCUP_OCUOCCUP_POSTFORMSCOMMIT");
		final RowMapper<RpOtherOccupants> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, RpOtherOccupants.class,
				rpOtherOccupantsMapping);
		final ArrayList<RpOtherOccupants> returnList = (ArrayList<RpOtherOccupants>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	public Addresses preFindAddressValues(final VAddresses vaddressGetVal) {
		Addresses returnObj = new Addresses();
		final String sql = getQuery("FINF_V_ADDRESSES");
		final RowMapper<Addresses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Addresses.class,
				addressesMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("addressId", vaddressGetVal.getAddressId()), columnRowMapper);
		} catch (Exception e) {
			returnObj = new Addresses();
		}
		return returnObj;
	}

	@Override
	public Integer preAddressInsertRpOtherOccupants(final List<Addresses> addressInsertList) {
		final String sql = getQuery("OCUOCCUP_RPOTHEROCCUPANTS_PRE_ADDRESSES_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Addresses list : addressInsertList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (addressInsertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public String preFindAddressFlag(final VAddresses vaddressGetVal) {
		final String sql = getQuery("PRE_INSERT_ADDRESS_FLAG");
		String returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("addressId", vaddressGetVal.getAddressId(), "ownerId", vaddressGetVal.getOwnerId()),
					String.class);
		} catch (Exception e) {
			returnObj = "N";
		}
		return returnObj;
	}

	@Override
	public Integer preOffenderContactsInsertRpOtherOccupants(final List<OffenderContactPersons> addressInsertList) {
		final String sql = getQuery("OCUOCCUP_OFFENDER_CONTACT_PERSONS_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderContactPersons list : addressInsertList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (addressInsertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Long findNextContactPersonId() {
		final String sql = getQuery("OCUOCCUP_OFFENDER_CONTACT_PERSONS_PRE_INSERT");
		Long returnObj = null;
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		return returnObj;
	}
}

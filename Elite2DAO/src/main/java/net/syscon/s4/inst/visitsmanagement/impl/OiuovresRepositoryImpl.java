package net.syscon.s4.inst.visitsmanagement.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.visitsmanagement.OiuovresRepository;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderPersonRestricts;

@Repository
public class OiuovresRepositoryImpl extends RepositoryBase implements OiuovresRepository{

private final Map<String, FieldMapper> personsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("PROFILE_CODE", 						new FieldMapper("profileCode"))
.put("CORONER_NUMBER", 						new FieldMapper("coronerNumber"))
.put("ATTENTION", 						new FieldMapper("attention"))
.put("LAST_NAME_SOUNDEX", 						new FieldMapper("lastNameSoundex"))
.put("MIDDLE_NAME", 						new FieldMapper("middleName"))
.put("PERSON_ID", 						new FieldMapper("personId"))
.put("CITIZENSHIP", 						new FieldMapper("citizenship"))
.put("CRIMINAL_HISTORY_TEXT", 						new FieldMapper("criminalHistoryText"))
.put("LANGUAGE_CODE", 						new FieldMapper("languageCode"))
.put("LAST_NAME", 						new FieldMapper("lastName"))
.put("PRIMARY_LANGUAGE_CODE", 						new FieldMapper("primaryLanguageCode"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
.put("EMPLOYER", 						new FieldMapper("employer"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
.put("SUSPENDED_FLAG", 						new FieldMapper("suspendedFlag"))
.put("OCCUPATION_CODE", 						new FieldMapper("occupationCode"))
.put("NAME_TYPE", 						new FieldMapper("nameType"))
.put("COMPREHEND_ENGLISH_FLAG", 						new FieldMapper("comprehendEnglishFlag"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("MEMO_TEXT", 						new FieldMapper("memoText"))
.put("DECEASED_DATE", 						new FieldMapper("deceasedDate"))
.put("SUSPENDED_DATE", 						new FieldMapper("suspendedDate"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("MIDDLE_NAME_KEY", 						new FieldMapper("middleNameKey"))
.put("REMITTER_FLAG", 						new FieldMapper("remitterFlag"))
.put("BIRTHDATE", 						new FieldMapper("birthdate"))
.put("BIRTH_PLACE", 						new FieldMapper("birthPlace"))
.put("LAST_NAME_KEY", 						new FieldMapper("lastNameKey"))
.put("ALIAS_PERSON_ID", 						new FieldMapper("aliasPersonId"))
.put("CARE_OF", 						new FieldMapper("careOf"))
.put("STAFF_FLAG", 						new FieldMapper("staffFlag"))
.put("ROOT_PERSON_ID", 						new FieldMapper("rootPersonId"))
.put("FIRST_NAME_KEY", 						new FieldMapper("firstNameKey"))
.put("MARITAL_STATUS", 						new FieldMapper("maritalStatus"))
.put("SEX", 						new FieldMapper("sex"))
.put("FIRST_NAME", 						new FieldMapper("firstName"))
.put("INTERPRETER_REQUIRED", 						new FieldMapper("interpreterRequired"))
.build();
private final Map<String, FieldMapper> offenderPersonRestrictsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("COMMENT_TEXT", 						new FieldMapper("commentText"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("ENTERED_STAFF_ID", 						new FieldMapper("enteredStaffId"))
.put("RESTRICTION_EXPIRY_DATE", 						new FieldMapper("restrictionExpiryDate"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("OFFENDER_PERSON_RESTRICT_ID", 						new FieldMapper("offenderPersonRestrictId"))
.put("AUTHORIZED_STAFF_ID", 						new FieldMapper("authorizedStaffId"))
.put("RESTRICTION_TYPE", 						new FieldMapper("restrictionType"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
.put("RESTRICTION_EFFECTIVE_DATE", 						new FieldMapper("restrictionEffectiveDate"))
.put("OFFENDER_CONTACT_PERSON_ID", 						new FieldMapper("offenderContactPersonId"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CODE", 						new FieldMapper("code"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();

	/**
	 * Creates new OiuovresRepositoryImpl class Object
	 */
	public OiuovresRepositoryImpl() {
		// OiuovresRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Persons
	 *
	 * @return List<Persons>
	 *
	 * 
	 */
	public List<Persons> perExecuteQuery(final Persons objSearchDao) {
		final String sql = getQuery("OIUOVRES_PER_FIND_PERSONS");
		final RowMapper<Persons> PersonsRowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, personsMapping);
		final List<Persons> returnList =  namedParameterJdbcTemplate.query(sql, createParams("personId", objSearchDao.getPersonId()),
				PersonsRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 *
	 * 
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstPersons
	 *            List<Persons>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer perInsertPersons(final List<Persons> lstPersons) {
		int insertCount = 0;
		String sql = getQuery("OIUOVRES_PER_INSERT_PERSONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		for (int i = 0; i < returnArray.length; i++) {
			insertCount = insertCount++;
		}
		if (lstPersons.size() == insertCount) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstPersons
	 *            List<Persons>
	 *
	 * 
	 */
	public Integer perUpdatePersons(final List<Persons> lstPersons) {
		String sql = getQuery("OIUOVRES_PER_UPDATE_PERSONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Persons personIdentifiers : lstPersons) {
			parameters.add(new BeanPropertySqlParameterSource(personIdentifiers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPersons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderPersonRestricts
	 *
	 * @return List<OffenderPersonRestricts>
	 *
	 * 
	 */
	public List<OffenderPersonRestricts> offConRestExecuteQuery(final OffenderPersonRestricts objSearchDao) {
		final String sql = getQuery("OIUOVRES_OFFCONREST_FIND_OFFENDER_PERSON_RESTRICTS");
		final RowMapper<OffenderPersonRestricts> OffenderPersonRestrictsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPersonRestricts.class, offenderPersonRestrictsMapping);
		final List<OffenderPersonRestricts> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("personId", objSearchDao.getPersonId(), "offenderBookId", objSearchDao.getOffenderBookId()),
				OffenderPersonRestrictsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderPersonRestricts
	 *            List<OffenderPersonRestricts>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer offConRestInsertOffenderPersonRestricts(
			final List<OffenderPersonRestricts> lstOffenderPersonRestricts) {
		String sql = getQuery("OIUOVRES_OFFCONREST_INSERT_OFFENDER_PERSON_RESTRICTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderPersonRestricts personIdentifiers : lstOffenderPersonRestricts) {
			parameters.add(new BeanPropertySqlParameterSource(personIdentifiers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderPersonRestricts.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderPersonRestricts
	 *            List<OffenderPersonRestricts>
	 *
	 * 
	 */
	public Integer offConRestUpdateOffenderPersonRestricts(
			final List<OffenderPersonRestricts> lstOffenderPersonRestricts) {
		String sql = getQuery("OIUOVRES_OFFCONREST_UPDATE_OFFENDER_PERSON_RESTRICTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderPersonRestricts personIdentifiers : lstOffenderPersonRestricts) {
			parameters.add(new BeanPropertySqlParameterSource(personIdentifiers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderPersonRestricts.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgOffRestrictionTypeRecordGroup() {
		final String sql = getQuery("OIUOVRES_FIND_RGOFFRESTRICTIONTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgStaffIdRecordGroup() {
		final String sql = getQuery("OIUOVRES_FIND_RGSTAFFID");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	@Override
	public String getStaffName(final Integer enteredStaffId) {
		final String sql = getQuery("GET_STAFF_NAME_FOR_ENTERED_STAFF_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("staffId",enteredStaffId), String.class);
	}
	
	@Override
	public Integer getStaffId(final String staffName) {
		final String sql = getQuery("OIUOVRES_OFFCONREST_GET_STAFF_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", staffName), Integer.class);
	}

}

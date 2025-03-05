package net.syscon.s4.inst.visitsmanagement.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import net.syscon.s4.inst.visitsmanagement.OcucnperRepository;

/**
 * Class OcucnperRepositoryImpl
 */
@Repository
public class OcucnperRepositoryImpl extends RepositoryBase implements OcucnperRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcucnperRepositoryImpl.class.getName());
	
private final Map<String, FieldMapper> personsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("PROFILE_CODE", 						new FieldMapper("profileCode"))
.put("CORONER_NUMBER", 						new FieldMapper("coronerNumber"))
.put("ATTENTION", 							new FieldMapper("attention"))
.put("LAST_NAME_SOUNDEX", 					new FieldMapper("lastNameSoundex"))
.put("MIDDLE_NAME", 						new FieldMapper("middleName"))
.put("PERSON_ID", 							new FieldMapper("personId"))
.put("CITIZENSHIP", 						new FieldMapper("citizenship"))
.put("CRIMINAL_HISTORY_TEXT", 				new FieldMapper("criminalHistoryText"))
.put("LANGUAGE_CODE", 						new FieldMapper("languageCode"))
.put("LAST_NAME", 							new FieldMapper("lastName"))
.put("PRIMARY_LANGUAGE_CODE", 				new FieldMapper("primaryLanguageCode"))
.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
.put("EMPLOYER", 							new FieldMapper("employer"))
.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
.put("SUSPENDED_FLAG", 						new FieldMapper("suspendedFlag"))
.put("OCCUPATION_CODE", 					new FieldMapper("occupationCode"))
.put("NAME_TYPE", 							new FieldMapper("nameType"))
.put("COMPREHEND_ENGLISH_FLAG", 			new FieldMapper("comprehendEnglishFlag"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("MEMO_TEXT", 							new FieldMapper("memoText"))
.put("DECEASED_DATE", 						new FieldMapper("deceasedDate"))
.put("SUSPENDED_DATE", 						new FieldMapper("suspendedDate"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("MIDDLE_NAME_KEY", 					new FieldMapper("middleNameKey"))
.put("REMITTER_FLAG", 						new FieldMapper("remitterFlag"))
.put("BIRTHDATE", 							new FieldMapper("birthdate"))
.put("BIRTH_PLACE", 						new FieldMapper("birthPlace"))
.put("LAST_NAME_KEY", 						new FieldMapper("lastNameKey"))
.put("ALIAS_PERSON_ID", 					new FieldMapper("aliasPersonId"))
.put("CARE_OF", 							new FieldMapper("careOf"))
.put("STAFF_FLAG", 							new FieldMapper("staffFlag"))
.put("ROOT_PERSON_ID", 						new FieldMapper("rootPersonId"))
.put("FIRST_NAME_KEY", 						new FieldMapper("firstNameKey"))
.put("MARITAL_STATUS", 						new FieldMapper("maritalStatus"))
.put("SEX", 								new FieldMapper("sex"))
.put("FIRST_NAME", 							new FieldMapper("firstName"))
.put("INTERPRETER_REQUIRED", 				new FieldMapper("interpreterRequired"))
.build();
private final Map<String, FieldMapper> referencecodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("CODE", 								new FieldMapper("code"))
.build();

	/**
	 * Creates new OcucnperRepositoryImpl class Object
	 */
	public OcucnperRepositoryImpl() {
		// OcucnperRepositoryImpl
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
		final String sql = getQuery("OCUCNPER_PERSONS_FIND_PERSONS");
		final RowMapper<Persons> PersonsRowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, personsMapping);
		final ArrayList<Persons> returnList = (ArrayList<Persons>) namedParameterJdbcTemplate.query(sql, createParams(),
				PersonsRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @
	 *
	 */
	public int preInsert() {
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
	 * @
	 */
	public Integer personsInsertPersons(final List<Persons> lstPersons) {
		final String sql = getQuery("OCUCNPER_PERSONS_INSERT_PERSONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Persons personObj : lstPersons) {
			parameters.add(new BeanPropertySqlParameterSource(personObj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstPersons.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgSexCodeRecordGroup() {
		final String sql = getQuery("OCUCNPER_FIND_RGSEXCODE");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referencecodeMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodesRowMapper);
		} catch (Exception e) {
			logger.error("In method rgSexCodeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * personsPreInsert
	 *
	 * @param params
	 *
	 */
	public Long personsPreInsert() {
		final String sql = getQuery("OCUCNPER_PERSONS_PREINSERT");
		final Long returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		return returnObj;
	}
}

package net.syscon.s4.inst.visitsmanagement.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.VisitorRestrictions;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.visitsmanagement.OmuvrestRepository;

/**
 * Class OmuvrestRepositoryImpl
 */
@Repository
public class OmuvrestRepositoryImpl extends RepositoryBase implements OmuvrestRepository {
	
private final Map<String, FieldMapper> personsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("PROFILE_CODE", 						new FieldMapper("profileCode"))
.put("CORONER_NUMBER", 						new FieldMapper("coronerNumber"))
.put("ATTENTION", 					    	new FieldMapper("attention"))
.put("LAST_NAME_SOUNDEX", 					new FieldMapper("lastNameSoundex"))
.put("MIDDLE_NAME", 						new FieldMapper("middleName"))
.put("PERSON_ID", 						    new FieldMapper("personId"))
.put("CITIZENSHIP", 						new FieldMapper("citizenship"))
.put("CRIMINAL_HISTORY_TEXT", 				new FieldMapper("criminalHistoryText"))
.put("LANGUAGE_CODE", 						new FieldMapper("languageCode"))
.put("LAST_NAME", 						    new FieldMapper("lastName"))
.put("PRIMARY_LANGUAGE_CODE", 				new FieldMapper("primaryLanguageCode"))
.put("SEAL_FLAG", 						    new FieldMapper("sealFlag"))
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
private final Map<String, FieldMapper> visitorRestrictionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("VISITOR_RESTRICTION_ID", 				new FieldMapper("visitorRestrictionId"))
.put("ENTERED_STAFF_ID", 					new FieldMapper("enteredStaffId"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("VISIT_RESTRICTION_TYPE", 				new FieldMapper("visitRestrictionType"))
.put("PERSON_ID", 							new FieldMapper("personId"))
.put("OFFENDER_ID", 						new FieldMapper("offenderId"))
.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
.put("EFFECTIVE_DATE", 						new FieldMapper("effectiveDate"))
.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
.put("EXPIRY_DATE", 						new FieldMapper("expiryDate"))
.put("COMMENT_TXT", 						new FieldMapper("commentTxt"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("LIST_SEQ", 							new FieldMapper("listSeq"))
.put("EXPIRED_DATE", 						new FieldMapper("expiredDate"))
.put("CODE", 								new FieldMapper("code"))
.put("DOMAIN", 								new FieldMapper("domain"))
.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("NEW_CODE", 							new FieldMapper("newCode"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.build();

	/**
	 * Creates new OmuvrestRepositoryImpl class Object
	 */
	public OmuvrestRepositoryImpl() {
		// OmuvrestRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Persons
	 *
	 * @return List<Persons>
	 *
	 * @throws SQLException
	 */
	public List<Persons> perExecuteQuery(final Persons objSearchDao) {
		final String sql = getQuery("OMUVREST_PER_FIND_PERSONS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao != null && objSearchDao.getPersonId() != null) {
				sqlQuery.append("PERSON_ID =  :personId " + " and ");
				valuesList.addValue("personId", objSearchDao.getPersonId());
			}
			if (objSearchDao != null && objSearchDao.getLastName() != null) {
				sqlQuery.append("LAST_NAME =  :lastName " + " and ");
				valuesList.addValue("lastName", objSearchDao.getLastName());
			}
			if (objSearchDao != null && objSearchDao.getMiddleName() != null) {
				sqlQuery.append("MIDDLE_NAME =  :middleName " + " and ");
				valuesList.addValue("middleName", objSearchDao.getMiddleName());
			}
			if (objSearchDao != null && objSearchDao.getFirstName() != null) {
				sqlQuery.append("FIRST_NAME =  :firstName " + " and ");
				valuesList.addValue("firstName", objSearchDao.getFirstName());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<Persons> PersonsRowMapper = Row2BeanRowMapper.makeMapping(preparedSql, Persons.class,
				personsMapping);
		List<Persons> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, valuesList, PersonsRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VisitorRestrictions
	 *
	 * @return List<VisitorRestrictions>
	 *
	 * @throws SQLException
	 */
	public List<VisitorRestrictions> visrRestExecuteQuery(final VisitorRestrictions objSearchDao) {
		final String sql = getQuery("OMUVREST_VISRREST_FIND_VISITOR_RESTRICTIONS");
		final RowMapper<VisitorRestrictions> VisitorRestrictionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VisitorRestrictions.class, visitorRestrictionsMapping);
		List<VisitorRestrictions> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("PERSONID", objSearchDao.getPersonId()),
				VisitorRestrictionsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstVisitorRestrictions
	 *            List<VisitorRestrictions>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer visrRestInsertVisitorRestrictions(final List<VisitorRestrictions> lstVisitorRestrictions) {
		String sql = getQuery("OMUVREST_VISRREST_INSERT_VISITOR_RESTRICTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (VisitorRestrictions visitorRestrictions : lstVisitorRestrictions) {
			parameters.add(new BeanPropertySqlParameterSource(visitorRestrictions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVisitorRestrictions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVisitorRestrictions
	 *            List<VisitorRestrictions>
	 *
	 * @throws SQLException
	 */
	public Integer visrRestUpdateVisitorRestrictions(final List<VisitorRestrictions> lstVisitorRestrictions) {
		String sql = getQuery("OMUVREST_VISRREST_UPDATE_VISITOR_RESTRICTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (VisitorRestrictions visitorRestrictions : lstVisitorRestrictions) {
			parameters.add(new BeanPropertySqlParameterSource(visitorRestrictions));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstVisitorRestrictions.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgVisrRestVisitRestrictiRecordGroup() {
		final String sql = getQuery("OMUVREST_FIND_RGVISRRESTVISITRESTRICTI");
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
	 * offenderGrievancesPreInsert
	 *
	 * @param params
	 *
	 */
	public Integer visitorRestrictionsPreInsert() {
		final String sql = getQuery("OMUVREST_VISITOR_RESTRICTION_ID");
		Integer obj = null;
		Integer returnval = null;
		obj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		if (obj != null) {
			returnval = obj;
		}
		return returnval;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievancesPreInsert
	 *
	 * @param params
	 *
	 */
	public Integer enteredStaffIdPreInsert(String userId) {
		final String sql = getQuery("OMUVREST_ENTERED_STAFF_ID");
		Integer obj = null;
		Integer returnval = null;
		obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("userId",userId), Integer.class);
		if (obj != null) {
			returnval = obj;
		}
		return returnval;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievancesPreInsert
	 *
	 * @param params
	 *
	 */
	public String desriptionPostInsert(final StaffMembers objVisitorRestrictions) {
		final String sql = getQuery("OMUVREST_DESRIPTION_POST_INSERT");
		final RowMapper<StaffMembers> VisitorRestrictionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				StaffMembers.class, visitorRestrictionsMapping);
		String returnval = null;
		StaffMembers returnArray = new StaffMembers();
		returnArray = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("STAFFID", objVisitorRestrictions.getStaffId()), VisitorRestrictionsRowMapper);
		if (returnArray != null) {
			returnval = returnArray.getDescription();
		}
		return returnval;
	}
}

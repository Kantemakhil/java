package net.syscon.s4.pkgs.tag_person_search.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.pkgs.tag_person_search.TagPersonSearchRepository;

@Repository
public class TagPersonSearchRepositoryImpl extends RepositoryBase implements TagPersonSearchRepository {

	private static final Logger logger = LogManager.getLogger(TagPersonSearchRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> personsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGG_SENTENCE_SEQ", new FieldMapper("aggSentenceSeq")).
			put("SECOND_MIDDLE_NAME", new FieldMapper("secondMiddleName")).
			put("SEX_DESCRIPTION", new FieldMapper("sexDescription")).build();

	@Override
	public Integer insertPersonProfileTypes(final Long personId, final String userName) {
		final String sql = getQuery("INSERT_PERSON_PROFILE_TYPES");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer retVal = 0;

		inParamMap.put("p_person_id", personId);
		inParamMap.put("createUserId", userName);
		try {
			retVal = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			retVal = 0;
			logger.error("insertPersonProfileTypes", e);
		}
		return retVal;
	}
	/* 
	 * This methos is used to get_partial_soundex_persons form database
	 * */
	@Override
	public List<Persons> pResultSet(final Persons persons) {
		final String sql = getQuery("P_RESULT_SET");
		List<Persons> retList = new ArrayList<Persons>();

		final RowMapper<Persons> rowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, personsMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql,
					createParams("p_last_name", persons.getLastName(), "p_first_name", persons.getFirstName(),
							"p_middle_name", persons.getMiddleName(), "v_from_date", persons.getLvFromDate(),
							"v_to_date", persons.getLvToDate(), "p_birth_date", persons.getBirthdate(), "p_sex",
							persons.getSex(), "p_second_middle_name", persons.getsecondMiddleName()),
					rowMapper);
		} catch (Exception e) {
			logger.error("pResultSet", e);
			retList = null;
		}
		return retList;

	}
	/* 
	 * This methos is used to get_partial_soundex_persons form database
	 * */
	@Override
	public List<Persons> pResultSetOne(final Persons persons) {
		final String sql = getQuery("P_RESULT_SET_ONE");
		List<Persons> retList = new ArrayList<Persons>();

		final RowMapper<Persons> rowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, personsMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql,
					createParams("p_last_name", persons.getLastName(), "p_first_name", persons.getFirstName(),
							"p_middle_name", persons.getMiddleName(), "v_from_date", persons.getLvFromDate(),
							"v_to_date", persons.getLvToDate(), "p_birth_date", persons.getBirthdate(), "p_sex",
							persons.getSex(),"p_second_middle_name",persons.getsecondMiddleName()),
					rowMapper);
		} catch (Exception e) {
			logger.error("pResultSetOne", e);
			retList = null;
		}
		return retList;
	}

	@Override
	public List<Persons> pResultSetOneFP(final Persons persons) {
		final String sql = getQuery("P_RESULT_SET_ONE_FP");
		List<Persons> retList = new ArrayList<Persons>();

		final RowMapper<Persons> rowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, personsMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql,
					createParams("p_last_name", persons.getLastName(), "p_first_name", persons.getFirstName(),
							"p_middle_name", persons.getMiddleName(), "v_from_date", persons.getLvFromDate(),
							"v_to_date", persons.getLvToDate(), "p_birth_date", persons.getBirthdate(), "p_sex",
							persons.getSex(), "p_second_middle_name", persons.getsecondMiddleName()),
					rowMapper);
		} catch (Exception e) {
			logger.error("pResultSetOne", e);
			retList = null;
		}
		return retList;
	}

	@Override
	public List<Persons> pResultSetTwo(final Long personId) {
		final String sql = getQuery("P_RESULT_SET_TWO");
		List<Persons> retList = new ArrayList<Persons>();

		final RowMapper<Persons> rowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, personsMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql, createParams("p_person_id", personId), rowMapper);
		} catch (Exception e) {
			logger.error("pResultSetTwo", e);
			retList = null;
		}
		return retList;
	}

	@Override
	public List<Persons> pResultSetThree(final String identifierType, final String identifier) {
		final String sql = getQuery("P_RESULT_SET_THREE");
		List<Persons> retList = new ArrayList<Persons>();

		final RowMapper<Persons> rowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, personsMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql,
					createParams("p_identifier_type", identifierType, "p_identifier_value", identifier), rowMapper);
		} catch (Exception e) {
			logger.error("pResultSetThree", e);
			retList = null;
		}
		return retList;
	}

	@Override
	public List<Persons> pResultSetFour(final String identifierType, final String identifier) {
		final String sql = getQuery("P_RESULT_SET_FOUR");
		List<Persons> retList = new ArrayList<Persons>();

		final RowMapper<Persons> rowMapper = Row2BeanRowMapper.makeMapping(sql, Persons.class, personsMapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql,
					createParams("p_identifier_type", identifierType, "p_identifier_value", identifier), rowMapper);
		} catch (Exception e) {
			logger.error("pResultSetFour", e);
			retList = null;
		}
		return retList;
	}

	@Override
	public Long getNextIdSeq(final Long pPersonId) {
		final String sql = getQuery("GET_NEXT_ID_SEQ");
		Long nextSeq = null;
		try {
			nextSeq = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_PERSON_ID", pPersonId),
					Long.class);
		} catch (Exception e) {
			logger.error("getNextIdSeq :" + e);
			nextSeq = 0l;
		}
		return nextSeq;
	}

	@Override
	public Long getNextEmpSeq(final Long pPersonId) {
		final String sql = getQuery("GET_NEXT_EMP_SEQ");
		Long nextEmpSeq = null;
		try {
			nextEmpSeq = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_PERSON_ID", pPersonId),
					Long.class);
		} catch (Exception e) {
			logger.error("getNextEmpSeq :" + e);
			nextEmpSeq = 0l;
		}
		return nextEmpSeq;
	}

}
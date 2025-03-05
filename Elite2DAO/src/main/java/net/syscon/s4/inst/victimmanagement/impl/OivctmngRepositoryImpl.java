package net.syscon.s4.inst.victimmanagement.impl;

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
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.victimmanagement.OivctmngRepository;
import net.syscon.s4.inst.victimmanagement.beans.VictimContactLogs;
import net.syscon.s4.inst.victimmanagement.beans.VictimContactPreferences;
import net.syscon.s4.inst.victimmanagement.beans.VictimLinkedOffenders;
import net.syscon.s4.inst.victimmanagement.beans.VictimRecords;

@Repository
public class OivctmngRepositoryImpl extends RepositoryBase implements OivctmngRepository {

	private static Logger logger = LogManager.getLogger(OivctmngRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> victamRowMapper = new ImmutableMap.Builder<String, FieldMapper>()
			.put("personName", new FieldMapper("personName")).put("gender", new FieldMapper("gender"))
			.put("personAge", new FieldMapper("age")).build();

	private final Map<String, FieldMapper> victamLinkedOffenderRowMapper = new ImmutableMap.Builder<String, FieldMapper>()
			.put("offenderName", new FieldMapper("offenderName"))
			.put("offenderBookId", new FieldMapper("offenderBookId"))
			.put("offenderIdDisplay", new FieldMapper("offenderIdDisplay")).put("agyLoc", new FieldMapper("agyLoc"))
			.put("associated_legal_case", new FieldMapper("jsonData"))
			.put("staffName", new FieldMapper("staffName")).build();

	@Override
	public Integer insertVictimRecords(List<VictimRecords> insertList) {

		String sql = getQuery("OIVCTMNG_INSERT_VICTIM_RECORDS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VictimRecords victimRecords : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(victimRecords));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in insertVictimRecords method :: " + e);
		}
		return (insertList.size() == returnArray.length) ? 1 : 0;
	}

	@Override
	public Integer updateVictimRecords(List<VictimRecords> updateList) {
		String sql = getQuery("OIVCTMNG_UPDATE_VICTIM_RECORDS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VictimRecords victimRecords : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(victimRecords));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in insertVictimRecords method :: " + e);
		}
		return (updateList.size() == returnArray.length) ? 1 : 0;
	}

	@Override
	public List<VictimRecords> getAllVictimRecords() {

		List<VictimRecords> returnList = new ArrayList<VictimRecords>();

		String sql = getQuery("OIVCTMNG_SELECT_VICTIM_RECORDS");
		final RowMapper<VictimRecords> rowMapper = Row2BeanRowMapper.makeMapping(sql, VictimRecords.class,
				victamRowMapper);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), rowMapper);
		} catch (Exception e) {
			returnList = Collections.emptyList();
			logger.error(getClass().getName() + " error in getAllVictimRecords method :: " + e);
		}

		return returnList;
	}

	@Override
	public Integer insertVictimLinkedOffenders(List<VictimLinkedOffenders> insertList) {
		String sql = getQuery("OIVCTMNG_INSERT_VICTIM_LINKED_OFFENDERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VictimLinkedOffenders victimLinkedOffenders : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(victimLinkedOffenders));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in insertVictimLinkedOffenders method :: " + e);
		}
		return (insertList.size() == returnArray.length) ? 1 : 0;
	}

	@Override
	public Integer updateVictimLinkedOffenders(List<VictimLinkedOffenders> updateList) {
		String sql = getQuery("OIVCTMNG_UPDATE_VICTIM_LINKED_OFFENDERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VictimLinkedOffenders victimLinkedOffenders : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(victimLinkedOffenders));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in updateVictimLinkedOffenders method :: " + e);
		}
		return (updateList.size() == returnArray.length) ? 1 : 0;
	}

	@Override
	public List<VictimLinkedOffenders> getAllVictimLinkedOffenders(Integer victimId) {
		List<VictimLinkedOffenders> returnList = new ArrayList<VictimLinkedOffenders>();

		String sql = getQuery("OIVCTMNG_SELECT_VICTIM_LINKED_OFFENDERS");
		final RowMapper<VictimLinkedOffenders> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				VictimLinkedOffenders.class, victamLinkedOffenderRowMapper);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("victimId", victimId), rowMapper);
		} catch (Exception e) {
			returnList = Collections.emptyList();
			logger.error(getClass().getName() + " error in getAllVictimLinkedOffenders method :: " + e);
		}

		return returnList;
	}

	@Override
	public Integer insertVictimContactLogs(List<VictimContactLogs> insertList) {
		String sql = getQuery("OIVCTMNG_INSERT_VICTIM_CONTACT_LOGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VictimContactLogs victimContactLogs : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(victimContactLogs));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in insertVictimRecords method :: " + e);
		}
		return (insertList.size() == returnArray.length) ? 1 : 0;
	}

	@Override
	public Integer updateVictimContactLogs(List<VictimContactLogs> updateList) {
		String sql = getQuery("OIVCTMNG_UPDATE_VICTIM_CONTACT_LOGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VictimContactLogs victimContactLogs : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(victimContactLogs));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in insertVictimRecords method :: " + e);
		}
		return (updateList.size() == returnArray.length) ? 1 : 0;
	}

	@Override
	public List<VictimContactLogs> getAllinsertVictimContactLogs(Integer victimId) {
		List<VictimContactLogs> returnList = new ArrayList<VictimContactLogs>();

		String sql = getQuery("OIVCTMNG_SELECT_VICTIM_CONTACT_LOGS");
		final RowMapper<VictimContactLogs> rowMapper = Row2BeanRowMapper.makeMapping(sql, VictimContactLogs.class,
				victamLinkedOffenderRowMapper);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("victimId", victimId), rowMapper);
		} catch (Exception e) {
			returnList = Collections.emptyList();
			logger.error(getClass().getName() + " error in getAllinsertVictimContactLogs method :: " + e);
		}

		return returnList;
	}

	@Override
	public Integer insertVictimContactPreferences(List<VictimContactPreferences> insertList) {

		String sql = getQuery("OIVCTMNG_INSERT_VICTIM_CONTACT_PREFERENCES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VictimContactPreferences victimContactPreferences : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(victimContactPreferences));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in insertVictimContactPreferences method :: " + e);
		}
		return (insertList.size() == returnArray.length) ? 1 : 0;
	}

	@Override
	public Integer updateVictimContactPreferences(List<VictimContactPreferences> updateList) {

		String sql = getQuery("OIVCTMNG_UPDATE_VICTIM_CONTACT_PREFERENCES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VictimContactPreferences victimContactPreferences : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(victimContactPreferences));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(getClass().getName() + " error in updateVictimContactPreferences method :: " + e);
		}
		return (updateList.size() == returnArray.length) ? 1 : 0;
	}

	@Override
	public List<VictimContactPreferences> getAllvictimContactPreferences(Integer victimId) {

		List<VictimContactPreferences> returnList = new ArrayList<VictimContactPreferences>();

		String sql = getQuery("OIVCTMNG_SELECT_VICTIM_CONTACT_PREFERENCES");
		final RowMapper<VictimContactPreferences> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				VictimContactPreferences.class, victamLinkedOffenderRowMapper);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("victimId", victimId), rowMapper);
		} catch (Exception e) {
			returnList = Collections.emptyList();
			logger.error(getClass().getName() + " error in getAllvictimContactPreferences method :: " + e);
		}

		return returnList;
	}

	@Override
	public Integer getVictimId() {
		String sql = getQuery("OIVCTMNG_GET_VICTIM_ID");
		Integer victimId = null;
		try {
			victimId = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			Collections.emptyList();
			logger.error(getClass().getName() + " error in getVictimId method :: " + e);
		}
		return victimId;
	}

}

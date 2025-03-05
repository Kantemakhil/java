package net.syscon.s4.inst.workflow.maintenance.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.teamsworkflow.beans.Work;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.workflow.maintenance.OcmcnperRepository;

@Repository
public class OcmcnperRepositoryImpl extends RepositoryBase implements OcmcnperRepository {

	private static Logger logger = LogManager.getLogger(OcmcnperRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> worksMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("TEMPLATE_NAME", new FieldMapper("templateName"))
			.put("CASENOTE_FLAG", new FieldMapper("casenoteFlag"))
			.put("templateCount", new FieldMapper("templateCount")).put("triggerCount", new FieldMapper("triggerCount"))
			.put("functionCount", new FieldMapper("functionCount"))
			.put("emailRecipientsCount", new FieldMapper("emailRecipientsCount"))
			.put("emailReturnCount", new FieldMapper("emailReturnCount"))
			.put("CASE_NOTE_TEXT", new FieldMapper("caseNoteText")).build();

	@Override
	public Integer gettingCountFromCaseNotePermission(long workId, Integer roleId) {
		Integer count = null;
		final String sql = getQuery("CASE_NOTE_PERMISSIONS_COUNT_RECORDS");
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("roleId", roleId, "workId", workId),
					Integer.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "In method gettingCountFromCaseNotePermission error", e);

		}
		return count;

	}

	@Override
	public Integer caseNotePermissionInseting(Work workRecords) {
		final String sql = getQuery("CASE_NOTE_PERMISSIONS_INSERTING");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		
			parameters.add(new BeanPropertySqlParameterSource(workRecords));
		
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "In method caseNotePermissionInseting error", e);
		}
		return returnArray.length;
		
	}

	@Override
	public Integer caseNotePermissionUpdating(Work workRecords) {
		final String sql = getQuery("CASE_NOTE_PERMISSIONS_UPDATING");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	
			parameters.add(new BeanPropertySqlParameterSource(workRecords));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "In method caseNotePermissionUpdating error", e);
		}
		return returnArray.length;
		
	}

	@Override
	public List<Work> caseNotePermissionExecuteQuery(Work searchBean) {
		final String sql = getQuery("OCMCNPER_CASE_NOTE_PERMISSIONS_EXECUTE_QUERRY");
		final RowMapper<Work> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Work.class, worksMapping);
		List<Work> returnList = new ArrayList<Work>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("roleId", searchBean.getRoleId()),
					mRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " caseNotePermissionExecuteQuery ");
		}
		return returnList;
	}

}

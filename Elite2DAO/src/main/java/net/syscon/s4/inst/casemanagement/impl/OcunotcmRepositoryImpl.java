package net.syscon.s4.inst.casemanagement.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffCaseNoteRecipients;
import net.syscon.s4.inst.casemanagement.OcunotcmRepository;

/**
 * Class OcunotcmRepositoryImpl
 */
@Repository
public class OcunotcmRepositoryImpl extends RepositoryBase implements OcunotcmRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger log = LogManager.getLogger(OcunotcmRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
            .put("ROLE",         new FieldMapper("role"))
            .put("DESCRIPTION",  new FieldMapper("description"))
            .put("STAFF_NAME",    new FieldMapper("lastName"))
            .put("FIRST_NAME",   new FieldMapper("firstName"))
            .put("TEAM_MEMBER_ID",   new FieldMapper("teamMemberId"))
            .put("FROM_DATE",   new FieldMapper("fromDate"))
            .put("STAFF_ID",   new FieldMapper("staffId"))
            .build();
	private final Map<String, FieldMapper> offCaseNoteRecipientsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COMMENT_TEXT",     new FieldMapper("commentText"))
			.put("CREATE_USER_ID",   new FieldMapper("createUserId"))
			.put("SEAL_FLAG",     	 new FieldMapper("sealFlag"))
			.put("CASE_NOTE_ID",     new FieldMapper("caseNoteId"))
			.put("CREATE_DATETIME",  new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID",   new FieldMapper("modifyUserId"))
			.put("TEAM_ID",          new FieldMapper("teamId"))
			.put("STAFF_ID",         new FieldMapper("staffId"))
			.put("MODIFY_DATETIME",  new FieldMapper("modifyDatetime"))
			.put("OFF_CASE_NOTE_RECIPIENT_ID", new FieldMapper("offCaseNoteRecipientId"))
			.build();
	private final Map<String, FieldMapper> dualMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFF_CASE_NOTE_RECIPIENT_ID", new FieldMapper("offCaseNoteRecipientId"))
			.build();
	
	/**
	 * Creates new OcunotcmRepositoryImpl class Object
	 */
	public OcunotcmRepositoryImpl() {
		// OcunotcmRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffCaseNoteRecipients
	 *
	 * @return List<OffCaseNoteRecipients>
	 *
	 * @
	 */
	public List<OffCaseNoteRecipients> offCaseNrExecuteQuery(final OffCaseNoteRecipients objSearchDao)  {
		final String sql = getQuery("OCUNOTCM_OFFCASENR_FIND_OFF_CASE_NOTE_RECIPIENTS");		
			
		List<OffCaseNoteRecipients> returnList= new ArrayList<OffCaseNoteRecipients>();
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append("WHERE ");
			
			if (objSearchDao.getCaseNoteId() != null) {
				sqlQuery.append("CASE_NOTE_ID = :CASE_NOTE_ID" + " AND ");
				inParameterSource.addValue("CASE_NOTE_ID", objSearchDao.getCaseNoteId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith(" WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		//preparedSql = preparedSql.concat(" ORDER BY TAG_WORKFLOW_ADM.SORT_BY_TEAM_NAME(TEAM_ID)");
		preparedSql = preparedSql.concat(" ORDER BY TAG_WORKFLOW_ADM_SORT_BY_TEAM_NAME(TEAM_ID)");
		try{
			final RowMapper<OffCaseNoteRecipients> OffCaseNoteRecipientsRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffCaseNoteRecipients.class, offCaseNoteRecipientsMapping);	
		 returnList =  namedParameterJdbcTemplate.query(preparedSql, inParameterSource, OffCaseNoteRecipientsRowMapper);
		}
		catch(Exception e)
		{
			log.error("offCaseNrExecuteQuery",e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffCaseNoteRecipients
	 *            List<OffCaseNoteRecipients>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer offCaseNrInsertOffCaseNoteRecipients(final List<OffCaseNoteRecipients> lstOffCaseNoteRecipients)
			 {
		final String sql = getQuery("OCUNOTCM_OFFCASENR_INSERT_OFF_CASE_NOTE_RECIPIENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffCaseNoteRecipients offenderCaseNotes : lstOffCaseNoteRecipients) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCaseNotes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffCaseNoteRecipients.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffCaseNoteRecipients
	 *            List<OffCaseNoteRecipients>
	 *
	 * @
	 */
	public Integer offCaseNrUpdateOffCaseNoteRecipients(final List<OffCaseNoteRecipients> lstOffCaseNoteRecipients)
			 {
		String sql = getQuery("OCUNOTCM_OFFCASENR_UPDATE_OFF_CASE_NOTE_RECIPIENTS");

		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffCaseNoteRecipients offCaseNoteRecipients : lstOffCaseNoteRecipients) {
			parameters.add(new BeanPropertySqlParameterSource(offCaseNoteRecipients));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffCaseNoteRecipients.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

/**
* Used to capture results from select query
* @return List<StaffMembers> 
*/
public List<StaffMembers> rgStaffDtlsRecordGroup(final String teamIdDesc)  {
 	final String sql = getQuery("OCUNOTCM_FIND_RGSTAFFDTLS");
 	final RowMapper<StaffMembers> staffdetailRowMapper = Row2BeanRowMapper.makeMapping(sql,StaffMembers.class, staffMembersMapping);
    List<StaffMembers> returnList= new ArrayList<StaffMembers>();
	try {
		returnList= namedParameterJdbcTemplate.query(sql,createParams("DESCRIPTION",teamIdDesc),staffdetailRowMapper);
  	} catch (Exception e) {
  		log.error("rgStaffDtlsRecordGroup",e);
	}
	return  returnList;
}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offCaseNrPreInsertPRE-INSERT
	 *
	 * @param params
	 *
	 */
	public Object offCaseNrPreInsert()  {
		final String sql = getQuery("OCUNOTCM_OFF_CASE_NR_PREINSERTPRE_INSERT");
		final RowMapper<Dual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,Dual.class,  dualMapping);
		final Dual returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	@Override
	public Integer getcaseRecipientId() {
		final String sql = getQuery("OIDCNOTE_GETCASE_NOTE_RECIPIENT__ID");
		Integer returnList = 0;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		return returnList;
	}

	@Override
	public String offCasegetteamIdDesc(final Integer teamId) {
		final String sql = getQuery("OIDCNOTE_GET_TEAM_ID_DESC");
		String returnList =null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("TEAM_ID",teamId), String.class);
		return returnList;
	}

	@Override
	public String offStaffNameDesc(final Integer staffId) {
		final String sql = getQuery("OIDCNOTE_GET_STAFF_NAME_DESC");
		String returnList =null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("STAFF_ID",staffId), String.class);
		return returnList;
	
	}

	@Override
	public Integer getteamId(final String teamIdDesc) {
		final String sql = getQuery("OIDCNOTE_GET_TEAM_ID");
		Integer returnList =0;
		try {
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("DESCRIPTION",teamIdDesc), Integer.class);
		
		}catch(Exception e){
			return returnList;	
		}
		return returnList;
	}
}



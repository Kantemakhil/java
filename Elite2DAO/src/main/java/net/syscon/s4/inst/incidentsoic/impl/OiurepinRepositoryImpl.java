package net.syscon.s4.inst.incidentsoic.impl;

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
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.incidentsoic.beans.ReportableIncedentDetails;
import net.syscon.s4.inst.incidentsoic.OiurepinRepository;

@Repository
public class OiurepinRepositoryImpl extends RepositoryBase implements OiurepinRepository{

	private static Logger logger = LogManager.getLogger(OiurepinRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> reportableIncidentTypeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INCIDENT_REPORTABLE_ID", 						new FieldMapper("incidentReportableId"))
			.put("AGENCY_INCIDENT_ID", 						new FieldMapper("agencyIncidentId"))		
			.put("PARTY_SEQ", 						new FieldMapper("partySeq"))			
			.put("REPORTABLE_INCIDENT_TYPE", 						new FieldMapper("reportableIncidentType"))
			.put("COMMENT_TEXT", 						new FieldMapper("commentText"))		
			.put("REPORTABLE_STAFF_ID", 						new FieldMapper("reportableStaffId"))
			.put("REPORTABLE_DATETIME", 						new FieldMapper("reportableDatetime"))
			.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
			.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
			.put("USER_NAME", 						new FieldMapper("userName"))
			.build();
	
	@Override
	public List<ReportableIncedentDetails> getReportDetailsExecuteQuery(ReportableIncedentDetails searchBean) {
		final String sql = getQuery("OIUREPIN_GET_REPORTABLE_INCIDENT_DETAILS_DATA");
		final RowMapper<ReportableIncedentDetails> sentRowMapper = Row2BeanRowMapper.makeMapping(sql, ReportableIncedentDetails.class,
				reportableIncidentTypeMapping);
		List<ReportableIncedentDetails> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("agencyIncedentId",
					searchBean.getAgencyIncidentId(), "partySeq", searchBean.getPartySeq()),
					sentRowMapper);
		} catch (Exception e) {
			logger.error("senTermsExecuteQuery", e);
		}

		return returnList;
	}

	@Override
	public Integer reportableIncedentDetailsInsertData(List<ReportableIncedentDetails> insertList) {
		final String sql = getQuery("OIUREPIN_INSERT_REPORTABLE_INCIDENT_DETAILS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ReportableIncedentDetails sentenceTerms : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Integer reportableIncedentDetailsUpdateData(List<ReportableIncedentDetails> updateList) {
		final String sql = getQuery("OIUREPIN_UPDATE_REPORTABLE_INCIDENT_DETAILS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ReportableIncedentDetails sentenceTerms : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}		
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer reportableIncedentDetailsDeleteData(List<ReportableIncedentDetails> deleteList) {
		final String sql = getQuery("OIUREPIN_DELETE_REPORTABLE_INCIDENT_DETAILS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ReportableIncedentDetails sentenceTerms : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
			String tableName = "INCIDENT_REPORTABLE_DETAILS";
			String whereClause = "INCIDENT_REPORTABLE_ID=:incidentReportableId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method reportableIncedentDetailsDeleteData", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));		
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public String getUserNameLog(String userNameLog) {
		final String sql = getQuery("OIUREPIN_GET_USERNAME_BASED_LOGGED_USER");
		String returnValue = null;
		returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("userNameLog",
				userNameLog), String.class);
		return returnValue;
	}

}

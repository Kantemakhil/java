package net.syscon.s4.inst.demographicsbiometrics.impl;

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
import net.syscon.s4.im.beans.HealthRecordDetails;
import net.syscon.s4.im.beans.OffHealthRecordsData;
import net.syscon.s4.inst.demographicsbiometrics.OcdhealtRepository;

@Repository
public class OcdhealtRepositoryImpl extends RepositoryBase implements OcdhealtRepository {

	private static Logger logger = LogManager.getLogger(OcdhealtRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> healthRecordsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("OFF_HEALTH_REC_ID", new FieldMapper("offHealthRecId"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("HEALTH_TYPE", new FieldMapper("healthType"))
			.put("HEALTH_SUB_TYPE", new FieldMapper("healthSubType"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("FROM_DATE", new FieldMapper("fromDate"))
			.put("TO_DATE", new FieldMapper("toDate"))
			.put("HEALTH_STATUS", new FieldMapper("healthStatus"))
			.put("HEALTH_TREAT_TYPE", new FieldMapper("healthTreatType"))
			.put("HEALTH_PROVIDER", new FieldMapper("healthProvider"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))	
			.put("OFF_HEALTH_REC_DTL_ID", new FieldMapper("offHealthRecDtlId"))
			
						.build();
	

	
	@Override
	public Integer getRoleId(String roleCode) {
		String sql = getQuery("OCDHEALT_GET_HEAL_USER_ROLE_ID");
		Integer roleId=0;
		try {
			roleId = namedParameterJdbcTemplate.queryForObject(sql, createParams("roleCode",roleCode), Integer.class);
		} catch (Exception e) {
			return roleId;
		}
		return roleId;
	}

	@Override
	public Integer offenderRowHealthDataInsertData(List<OffHealthRecordsData> insertList) {
		final String sql = getQuery("OCDHEALT_INSERT_OFF_HEALTH_RECORDS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffHealthRecordsData list : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}

		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {

			return 0;
		}
	}

	@Override
	public Integer offenderRowHealthDataUpdateData(List<OffHealthRecordsData> updateList) {
		final String sql = getQuery("OCDHEALT_UPDATE_OFF_HEALTH_RECORDS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffHealthRecordsData sentenceCalcTypes : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceCalcTypes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offenderRowHealthDataUpdateData: ", e);
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}


	}

	@Override
	public Integer offenderRowHealthDataDeleteData(List<OffHealthRecordsData> deleteList) {
		final String sql = getQuery("OCDHEALT_DELETE_OFF_HEALTH_RECORDS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffHealthRecordsData sentenceCalcTypes : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceCalcTypes));
		}
		try {
			String tableName = "OFF_HEALTH_RECORDS";
			String whereClause = "off_health_rec_id=:offHealthRecId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offenderRowHealthDataDeleteData", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offenderRowHealthDataDeleteData : ", e);
			if (e.getMessage().contains("off_health_record_dtls_fk1")) {
				return 2;
			}
		}
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}


	}

	@Override
	public List<OffHealthRecordsData> getOffenderRowHealthExecuteQuery(OffHealthRecordsData searchBean) {
		final String sql = getQuery("OCDHEALT_RETRIVE_OFF_HEALTH_RECORDS_DATA");
		final RowMapper<OffHealthRecordsData> sentRowMapper = Row2BeanRowMapper.makeMapping(sql, OffHealthRecordsData.class,
				healthRecordsMapping);
		List<OffHealthRecordsData> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId",
					searchBean.getOffenderBookId()),
					sentRowMapper);
		} catch (Exception e) {
			logger.error("getOffenderRowHealthExecuteQuery", e);
		}

		return returnList;

	}

	@Override
	public List<HealthRecordDetails> getHealthDetailExecuteQuery(HealthRecordDetails searchBean) {
		final String sql = getQuery("OCDHEALT_RETRIEVE_OFF_HEALTH_RECORD_DTLS_DATA");
		final RowMapper<HealthRecordDetails> sentRowMapper = Row2BeanRowMapper.makeMapping(sql, HealthRecordDetails.class,
				healthRecordsMapping);
		List<HealthRecordDetails> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offHealthRecId",
					searchBean.getOffHealthRecId()),
					sentRowMapper);
		} catch (Exception e) {
			
			logger.error("getOffenderRowHealthExecuteQuery", e);
		}

		return returnList;
	}

	@Override
	public Integer healthRecordDetailsDeleteData(List<HealthRecordDetails> deleteList) {
		final String sql = getQuery("OCDHEALT_DELETE_OFF_HEALTH_RECORD_DTLS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final HealthRecordDetails sentenceCalcTypes : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceCalcTypes));
		}
		try {
			String tableName = "OFF_HEALTH_RECORD_DTLS";
			String whereClause = "OFF_HEALTH_REC_DTL_ID =:offHealthRecDtlId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method healthRecordDetailsDeleteData", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("healthRecordDetailsDeleteData : ", e);
		}
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer healthRecordDetailsUpdateData(List<HealthRecordDetails> updateList) {
		final String sql = getQuery("OCDHEALT_UPDATE_OFF_HEALTH_RECORD_DTLS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final HealthRecordDetails sentenceCalcTypes : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceCalcTypes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("healthRecordDetailsUpdateData: ", e);
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}


	}

	@Override
	public Integer healthRecordDetailsInsertData(List<HealthRecordDetails> insertList) {
		final String sql = getQuery("OCDHEALT_INSERT_OFF_HEALTH_RECORD_DTLS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final HealthRecordDetails list : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}

		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {

			return 0;
		}
	}

}

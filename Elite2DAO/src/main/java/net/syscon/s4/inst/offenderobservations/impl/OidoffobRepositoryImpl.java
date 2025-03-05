package net.syscon.s4.inst.offenderobservations.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.careinplacement.beans.OffObsPeriodChecks;
import net.syscon.s4.inst.offenderobservations.OidoffobRepository;
import net.syscon.s4.inst.offenderobservations.beans.OffenderObservationPeriods;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsCharacteristics;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationTypes;
@Repository
public class OidoffobRepositoryImpl extends RepositoryBase implements OidoffobRepository {
	private static Logger logger = LogManager.getLogger(OidoffobRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> observationTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("observation_type", new FieldMapper("observationType"))
			.put("frequency", new FieldMapper("frequency"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("notification_timing", new FieldMapper("notificationTiming"))
			.put("link_assess_flag", new FieldMapper("linkAssessFlag"))
			.put("link_seg_di_flag", new FieldMapper("linkSegDiFlag"))
			.put("link_incident_flag", new FieldMapper("linkIncidentFlag"))
			.put("link_oic_flag", new FieldMapper("linkOicFlag"))	
			.put("seal_flag", new FieldMapper("sealFlag"))	
			.put("ZONE_CODE", new FieldMapper("zoneCode"))	
			.put("internal_location_id", new FieldMapper("internalLocationId"))
			.put("location_code", new FieldMapper("locationCode"))
			.put("sensor_id", new FieldMapper("sensorId"))				
			.put("notification_flag", new FieldMapper("notificationFlag"))		
			.put("characteristics_type", new FieldMapper("characteristicsType"))
			.put("characteristics_code", new FieldMapper("characteristicsCode"))	

			.put("obs_period_id", new FieldMapper("obsPeriodId"))
			.put("offender_book_id", new FieldMapper("offenderBookId"))				
			.put("start_datetime", new FieldMapper("startDatetime"))		
			.put("end_reason_code", new FieldMapper("endReasonCode"))
			.put("end_datetime", new FieldMapper("endDatetime"))
			.put("status_code", new FieldMapper("statusCode"))
			.put("check_id", new FieldMapper("checkId"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("check_record_count", new FieldMapper("checkRecordCount"))			
			.build();
	
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", 						new FieldMapper("profileCode"))
			.put("PROFILE_TYPE", 						new FieldMapper("profileType"))
			.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
			.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", 						new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", 						new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", 					new FieldMapper("profileValue2"))
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.build();
	
	@Override
	public List<OffenderObservationTypes> getObservatioTypeData(OffenderObservationTypes searchBean) {
		final String sql = getQuery("OIDOFFOB_GET_OBSERVATION_TYPE_DATA");
		ArrayList<OffenderObservationTypes> returnList =new ArrayList<OffenderObservationTypes>();
		try {		
			final RowMapper<OffenderObservationTypes> observationTypesRowMpr = Row2BeanRowMapper.makeMapping(sql,
					OffenderObservationTypes.class, observationTypesMapping);
			 returnList = (ArrayList<OffenderObservationTypes>) namedParameterJdbcTemplate
					.query(sql, createParams("obsTypeVersionId",searchBean.getObsTypeVersionId()), observationTypesRowMpr);
		} catch(Exception e) {
			logger.error("getZoneDetailsExecuteQuery", e);
		}
		return returnList;
	}

	@Override
	public List<OffenderObservationPeriods> getOffenderPeriodExecuteQuery(OffenderObservationPeriods searchBean) {
		final String sql = getQuery("OIDOFFOB_GET_OBSERVATION_PERIODS_DATA");
		ArrayList<OffenderObservationPeriods> returnList =new ArrayList<OffenderObservationPeriods>();
		try {		
			final RowMapper<OffenderObservationPeriods> observationTypesRowMpr = Row2BeanRowMapper.makeMapping(sql,
					OffenderObservationPeriods.class, observationTypesMapping);
			 returnList = (ArrayList<OffenderObservationPeriods>) namedParameterJdbcTemplate
					.query(sql, createParams("offenderBookId",searchBean.getOffenderBookId()), observationTypesRowMpr);
		} catch(Exception e) {
			logger.error("getOffenderPeriodExecuteQuery", e);
		}
		return returnList;
	}
	
	
	@Override
	public Integer offenderObservationPeriodInsertData(List<OffenderObservationPeriods> insertList) {
		final String sql = getQuery("OIDOFFOB_INSERT_OBSERVATION_PERIODS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderObservationPeriods sentenceTerms : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error("offenderObservationPeriodInsertData : ", e);
			if (e.getMessage().contains("off_observation_periods_pk")) {
				return 2;
			}
		}
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer offenderObservationPeriodUpdateData(List<OffenderObservationPeriods> updateList) {
		final String sql = getQuery("OIDOFFOB_UPDATE_OBSERVATION_PERIODS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderObservationPeriods sentenceTerms : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offenderObservationPeriodUpdateData : ", e);
			if (e.getMessage().contains("off_observation_periods_pk")) {
				return 2;
			}
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer offenderObservationPeriodDeleteData(List<OffenderObservationPeriods> deleteList) {
		final String sql = getQuery("OIDOFFOB_DELETE_OBSERVATION_PERIODS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderObservationPeriods sentenceTerms : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
			String tableName = "off_observation_periods";
			String whereCondition = "obs_period_id=:obsPeriodId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offenderObservationPeriodDeleteData : ", e);
			if (e.getMessage().contains("off_observation_periods_pk")) {
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
	public Integer offenderObservationPeriodCheckInsertData(List<OffObsPeriodChecks> insertList) {
		final String sql = getQuery("OIDOFFOB_INSERT_OBSERVATION_PERIODS_CHECKS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffObsPeriodChecks obsPeriods : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(obsPeriods));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error("offenderObservationPeriodCheckInsertData : ", e);
//			if (e.getMessage().contains("off_observation_periods_pk")) {
//				return 2;
//			}
		}
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public Long getSequence() {
		final String sql = getQuery("OIDOFFOB_INSERT_OBSERVATION_PERIODS_GET_SEQ");
		Long result=null;
		try {
			result=namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		}catch (Exception e) {
			logger.error("getSequence : ", e);
		}
		return result;
	}
	
	@Override
	public List<OffObsPeriodChecks> getOffenderPeriodCheckExecuteQuery(OffObsPeriodChecks searchBean) {
		final String sql = getQuery("OIDOFFOB_GET_OBSERVATION_PERIODS_CHECK_DATA");
		ArrayList<OffObsPeriodChecks> returnList = new ArrayList<OffObsPeriodChecks>();
		try {
			final RowMapper<OffObsPeriodChecks> observationTypesRowMpr = Row2BeanRowMapper.makeMapping(sql,
					OffObsPeriodChecks.class, observationTypesMapping);
			returnList = (ArrayList<OffObsPeriodChecks>) namedParameterJdbcTemplate.query(sql,
					createParams("obsTypeVersionId",searchBean.getObsTypeVersionId(),"OBSPERIODID",
							searchBean.getObsPeriodId()),
					observationTypesRowMpr);
		} catch (Exception e) {
			logger.error("getOffenderPeriodExecuteQuery", e);
		}
		return returnList;
	}

	@Override
	public Integer offenderObservationCheckUpdateData(List<OffObsPeriodChecks> updateList) {
		final String sql = getQuery("OIDOFFOB_UPDATE_OBSERVATION_PERIODS_CHECK_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffObsPeriodChecks sentenceTerms : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offenderObservationPeriodUpdateData : ", e);
			if (e.getMessage().contains("off_obs_period_checks_pk")) {
				return 2;
			}
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<OffObsCharacteristics> getAdditionalCherctersticsExecuteQuery(OffObsCharacteristics searchBean) {	
			final String sql = getQuery("OIDOFFOB_OBSERVATION_CHARECTERSTICS_ADDITIONAL_RETRIVE_DATA");
			ArrayList<OffObsCharacteristics> returnList =new ArrayList<OffObsCharacteristics>();
			try {		
				final RowMapper<OffObsCharacteristics> observationTypesRowMpr = Row2BeanRowMapper.makeMapping(sql,
						OffObsCharacteristics.class, observationTypesMapping);
				 returnList = (ArrayList<OffObsCharacteristics>) namedParameterJdbcTemplate
						.query(sql, createParams("checkId",searchBean.getCheckId()), observationTypesRowMpr);
			} catch(Exception e) {
				logger.error("observationTypesExecuteQuery", e);
			}
			return returnList;
		}

	
	
	@Override
	public Integer offObsDeleteAdditionalCharctData(long checkId,String modifyUserId) {		
			final String sql = getQuery("OIDOFFOB_DELETE_OBSERVATION_CHARECTERSTICS_ADDITIONAL_DATA");
			Integer returnVal = 0;
			try {
				Map<String, Object> inputMap = new HashMap<String, Object>();
				String tableName = "off_obs_add_check_details";
				String whereCondition = "check_id=:checkId";
				inputMap.put("checkId", checkId);
				inputMap.put("modifyUserId", modifyUserId);
				updatePreDeletedRow(tableName, whereCondition, inputMap);
			} catch (Exception e) {
				logger.error(this.getClass().getName() + " error in offObsDeleteAdditionalCharctData " + e.getMessage());
			}
			try {
				namedParameterJdbcTemplate.update(sql, createParams("checkId", checkId));
				returnVal = 1;
			} catch (Exception e) {
				returnVal = 0;
			}
			return returnVal;
		}

	@Override
	public Integer offObsCharacteristicsInsertCharctData(List<OffObsCharacteristics> insertList) {
		final String sql = getQuery("OIDOFFOB_INSERT_OBSERVATION_CHARECTERSTICS_ADDITIONAL_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffObsCharacteristics sentenceTerms : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error("offenderObservationInsertZoneHousingData : ", e);
			if (e.getMessage().contains("off_obs_characteristics_pk")) {
				return 2;
			}
			
		}
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer saveOffenderObservationCheckComment(List<OffObsPeriodChecks> updateList) {
		final String sql = getQuery("OIDOFFOB_UPDATE_OBSERVATION_PERIODS_CHECK_UPDATE_COMMENT_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffObsPeriodChecks sentenceTerms : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offenderObservationPeriodUpdateData : ", e);
			if (e.getMessage().contains("off_obs_period_checks_pk")) {
				return 2;
			}
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<OffObsPeriodChecks> getCommentExecuteQuery(OffObsPeriodChecks searchBean) {
		final String sql = getQuery("OIDOFFOB_GET_OBSERVATION_PERIODS_CHECK_COMMENT_DATA");
		ArrayList<OffObsPeriodChecks> returnList = new ArrayList<OffObsPeriodChecks>();
		try {
			final RowMapper<OffObsPeriodChecks> observationTypesRowMpr = Row2BeanRowMapper.makeMapping(sql,
					OffObsPeriodChecks.class, observationTypesMapping);
			returnList = (ArrayList<OffObsPeriodChecks>) namedParameterJdbcTemplate.query(sql,
					createParams("checkId", searchBean.getCheckId()),
					observationTypesRowMpr);
		} catch (Exception e) {
			logger.error("getOffenderPeriodExecuteQuery", e);
		}
		return returnList;
	}

	@Override
	public List<ReferenceCodes> cellCondiLinkDomainRecordGroup(String observationType) {
		String sql = null;
		if(observationType != null && "USE_DOMAIN".equals(observationType)) {
			sql = getQuery("OIDOFFOB_CELL_CONDITIONS_REFRENCE_RECORD_GROUP_DATA");
		}else {
			sql = getQuery("OIDOFFOB_CELL_CONDITIONS_RECORD_GROUP_DATA");
		}
		ArrayList<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			final RowMapper<ReferenceCodes> observationTypesRowMpr = Row2BeanRowMapper.makeMapping(sql,
					ReferenceCodes.class, observationTypesMapping);
			returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
					createParams("observationType", observationType),
					observationTypesRowMpr);
		} catch (Exception e) {
			logger.error("cellCondiLinkDomainRecordGroup", e);
	   }
		return returnList;
}
	
	@Override
	public List<ReferenceCodes> activityLinkDomainRecordGroup(String observationType) {
		String sql = null;
		if(observationType != null && "USE_DOMAIN".equals(observationType)) {
			sql = getQuery("OIDOFFOB_ACTIVITY_REFERENCE_RECORD_GROUP_DATA");
		}else {
			sql = getQuery("OIDOFFOB_ACTIVITY_RECORD_GROUP_DATA");
		}
		ArrayList<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			final RowMapper<ReferenceCodes> observationTypesRowMpr = Row2BeanRowMapper.makeMapping(sql,
					ReferenceCodes.class, observationTypesMapping);
			returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
					createParams("observationType", observationType),
					observationTypesRowMpr);
		} catch (Exception e) {
			logger.error("cellCondiLinkDomainRecordGroup", e);
	   }
		return returnList;
}
	
	@Override
	public List<ReferenceCodes> commDetailLinkDomainRecordGroup(String observationType) {
		String sql = null;
		if(observationType != null && "USE_DOMAIN".equals(observationType)) {
			sql = getQuery("OIDOFFOB_DEMEANOR_REFERNCE_RECORD_GROUP_DATA");
		}else {
			sql = getQuery("OIDOFFOB_DEMEANOR_RECORD_GROUP_DATA");
		}
		ArrayList<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			final RowMapper<ReferenceCodes> observationTypesRowMpr = Row2BeanRowMapper.makeMapping(sql,
					ReferenceCodes.class, observationTypesMapping);
			returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
					createParams("observationType", observationType),
					observationTypesRowMpr);
		} catch (Exception e) {
			logger.error("cellCondiLinkDomainRecordGroup", e);
	   }
		return returnList;
}
	
	@Override
	public List<ReferenceCodes> notInLinkDomainRecordGroup(String observationType) {
		String sql = null;
		if(observationType != null && "USE_DOMAIN".equals(observationType)) {
			sql = getQuery("OIDOFFOB_NOT_IN_CELL_REFERENCE_RECORD_GROUP_DATA");
		}else {
			sql = getQuery("OIDOFFOB_NOT_IN_CELL_RECORD_GROUP_DATA");
		}
		ArrayList<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			final RowMapper<ReferenceCodes> observationTypesRowMpr = Row2BeanRowMapper.makeMapping(sql,
					ReferenceCodes.class, observationTypesMapping);
			returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
					createParams("observationType", observationType),
					observationTypesRowMpr);
		} catch (Exception e) {
			logger.error("cellCondiLinkDomainRecordGroup", e);
	   }
		return returnList;
}
	@Override
	public Integer getOffenderLivningUnitIdCount(BigDecimal offenderBookId) {
		String sql = getQuery("OIDOFFOB_GET_LIVING_UNIT_MAINTAIN_SETUP");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID",offenderBookId), Integer.class);
		} catch (Exception e) {
			logger.error("getOffenderLivningUnitIdCount", e);
			return 0;
		}
	}

	
	@Override
	public List<OffenderObservationTypes> getObservationTypeRecordGroup() {
		final String sql = getQuery("OIDOFFOB_REFERENCE_CODE_OFFENDER_OBSERVATION");
		final RowMapper<OffenderObservationTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderObservationTypes.class, observationTypesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgSentRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	@Override
	public Integer offObsDeleteAddCharData(List<OffObsCharacteristics> deleteList) {
		final String sql = getQuery("OIDOFFOB_DELETE_OBSERVATION_ADD_CHAR_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffObsCharacteristics sentenceTerms : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
			String tableName = "off_obs_add_check_details";
			String whereCondition = "check_id=:checkId AND detail_type=:detailType AND detail_code=:detailCode";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offObsDeleteAddCharData : ", e);
			
		}
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	@Override
	public BigDecimal getMaximumObsPeriodSeqBookId(BigDecimal offenderBookId) {
		String sql = getQuery("OIDOFFOB_GET_OBS_PERIOD_SEQ_MAX_VALUE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId",offenderBookId), BigDecimal.class);
		} catch (Exception e) {
			logger.error("getMaximumObsPeriodSeqBookId", e);
			return BigDecimal.ZERO;
		}
	}

	
	@Override
	public Integer getOffenderLivningUnitIdCountNotInLocation(BigDecimal offenderBookId, String username,String agyLocId) {
		String sql = getQuery("OIDOFFOB_GET_LIVING_UNIT_MAINTAIN_SETUP_NOT_IN_LOCATION");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID",offenderBookId,"username",username,"agyLocId",agyLocId), Integer.class);
		} catch (Exception e) {
			logger.error("getOffenderLivningUnitIdCount", e);
			return 0;
		}
	}
	
	@Override
	public Integer offenderObservationCheckUpdateStartDate(List<OffObsPeriodChecks> updateList) {
		final String sql = getQuery("OIDOFFOB_UPDATE_OBSERVATION_PERIODS_CHECK_DATA_SCHEDULE_DATE_TIME");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffObsPeriodChecks sentenceTerms : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offenderObservationCheckUpdateStartDate : ", e);
			if (e.getMessage().contains("off_obs_period_checks_pk")) {
				return 2;
			}
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer getCurrentStaffId(String userName) {
		String sql = getQuery("OIDOFFOB_GET_CURRENT_STAFF_ID_LOGIN");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("currentUserId",userName), Integer.class);
		} catch (Exception e) {
			logger.error("getCurrentStaffId", e);
			return 0;
		}
	}

	@Override
	public List<SystemProfiles> sysPflExecuteQuery() {
		final String sql = getQuery("OIDOFFOB_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), SystemProfilesRowMapper);
		return returnList;
	}
}

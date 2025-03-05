package net.syscon.s4.inst.offenderobservations.maintenance.impl;

import java.math.BigDecimal;
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
import net.syscon.s4.common.ScheduleSeries;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.offenderobservations.maintenance.OimoffobRepository;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsCharacteristics;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsZoneDetails;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationTypes;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationZones;

@Repository
public class OimoffobRepositoryImpl extends RepositoryBase implements OimoffobRepository {
	private static Logger logger = LogManager.getLogger(OimoffobRepositoryImpl.class.getName());
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
			
			.put("detail_type", new FieldMapper("detailType"))
			.put("detail_code", new FieldMapper("detailCode"))
			.put("other_loc_exist", new FieldMapper("otherLocExist"))
			
			.build();
	
	
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description")).build();
	@Override
	public List<OffenderObservationTypes> observationTypesExecuteQuery() {
		final String sql = getQuery("OIMOFFOB_OBSERVATION_TYPES_RETRIVE_DATA");
		ArrayList<OffenderObservationTypes> returnList =new ArrayList<OffenderObservationTypes>();
		try {		
			final RowMapper<OffenderObservationTypes> observationTypesRowMpr = Row2BeanRowMapper.makeMapping(sql,
					OffenderObservationTypes.class, observationTypesMapping);
			 returnList = (ArrayList<OffenderObservationTypes>) namedParameterJdbcTemplate
					.query(sql, createParams(), observationTypesRowMpr);
		} catch(Exception e) {
			logger.error("observationTypesExecuteQuery", e);
		}
		return returnList;
	}

	@Override
	public Integer offenderObservationInsertData(List<OffenderObservationTypes> insertList) {
		final String sql = getQuery("OIMOFFOB_INSERT_OBSERVATION_TYPES_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderObservationTypes sentenceTerms : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error("offenderObservationInsertData : ", e);
			if (e.getMessage().contains("offender_observation_types_pk")) {
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
	public Integer offenderObservationUpdateData(List<OffenderObservationTypes> updateList) {
		final String sql = getQuery("OIMOFFOB_UPDATE_OBSERVATION_TYPES_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderObservationTypes sentenceTerms : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offenderObservationUpdateData : ", e);
			if (e.getMessage().contains("offender_observation_types_pk")) {
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
	public Integer offenderObservationDeleteData(List<OffenderObservationTypes> deleteList) {
		final String sql = getQuery("OIMOFFOB_DELETE_OBSERVATION_TYPES_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderObservationTypes sentenceTerms : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
			String tableName = " offender_observation_types ";
			String whereCondition = " observation_type = :observationType";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		//try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
//		} catch (Exception e) {
//			logger.error("offenderObservationDeleteData : ", e);
//			if (e.getMessage().contains("offender_observation_types_pk")) {
//				return 2;
//			}
//			if (e.getMessage().contains("off_obs_add_details_fk1")) {
//				return 4;
//			}
//			if (e.getMessage().contains("off_obs_periods_fk2")) {
//				return 3;
//			}
//			
//		}
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer offenderObservationInsertZoneData(List<OffenderObservationZones> insertList) {
		final String sql = getQuery("OIMOFFOB_INSERT_OBSERVATION_TYPES_ZONE_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderObservationZones sentenceTerms : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error("offenderObservationInsertZoneData : ", e);
			if (e.getMessage().contains("offender_obs_zones_pk")) {
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
	public Integer offenderObservationUpdateZoneData(List<OffenderObservationZones> updateList) {
		final String sql = getQuery("OIMOFFOB_UPDATE_OBSERVATION_TYPES_ZONE_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderObservationZones sentenceTerms : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offenderObservationUpdateZoneData : ", e);
			if (e.getMessage().contains("offender_obs_zones_pk")) {
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
	public Integer offenderObservationDeleteZoneData(List<OffenderObservationZones> deleteList) {
		final String sql = getQuery("OIMOFFOB_DELETE_OBSERVATION_TYPES_ZONE_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderObservationZones sentenceTerms : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
			String tableName = " offender_observation_zones ";
			String whereCondition = " agy_loc_id=:agyLocId and zone_code=:zoneCode";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offenderObservationDeleteZoneData : ", e);
			if (e.getMessage().contains("offender_obs_zones_pk")) {
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
	public List<OffenderObservationZones> getZoneDetailsExecuteQuery(OffenderObservationZones searchBean) {
		final String sql = getQuery("OIMOFFOB_FACILTY_BASED_ZONE_RETRIVE_DATA");
		ArrayList<OffenderObservationZones> returnList =new ArrayList<OffenderObservationZones>();
		try {		
			final RowMapper<OffenderObservationZones> observationTypesRowMpr = Row2BeanRowMapper.makeMapping(sql,
					OffenderObservationZones.class, observationTypesMapping);
			 returnList = (ArrayList<OffenderObservationZones>) namedParameterJdbcTemplate
					.query(sql, createParams("agyLocId",searchBean.getAgyLocId()), observationTypesRowMpr);
		} catch(Exception e) {
			logger.error("getZoneDetailsExecuteQuery", e);
		}
		return returnList;
	}

	@Override
	public List<OffObsZoneDetails> getZoneDetailsHousingExecuteQuery(OffObsZoneDetails searchBean) {
		final String sql = getQuery("OIMOFFOB_FACILTY_BASED_ZONE_HOUSING_RETRIVE_DATA");
		ArrayList<OffObsZoneDetails> returnList =new ArrayList<OffObsZoneDetails>();
		try {		
			final RowMapper<OffObsZoneDetails> observationTypesRowMpr = Row2BeanRowMapper.makeMapping(sql,
					OffObsZoneDetails.class, observationTypesMapping);
			 returnList = (ArrayList<OffObsZoneDetails>) namedParameterJdbcTemplate
					.query(sql, createParams("agyLocId",searchBean.getAgyLocId(),"zoneCode",searchBean.getZoneCode()), observationTypesRowMpr);
		} catch(Exception e) {
			logger.error("getZoneDetailsHousingExecuteQuery", e);
		}
		return returnList;
	}

	@Override
	public Integer offenderObservationInsertZoneHousingData(List<OffObsZoneDetails> insertList) {
		final String sql = getQuery("OIMOFFOB_INSERT_OBSERVATION_TYPES_ZONE_HOUSING_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffObsZoneDetails sentenceTerms : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error("offenderObservationInsertZoneHousingData : ", e);
			if (e.getMessage().contains("off_obs_zone_details_pk")) {
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
	public Integer offenderObservationUpdateZoneHousingData(List<OffObsZoneDetails> updateList) {
		final String sql = getQuery("OIMOFFOB_UPDATE_OBSERVATION_TYPES_ZONE_HOUSING_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffObsZoneDetails sentenceTerms : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offenderObservationUpdateZoneHousingData : ", e);
			if (e.getMessage().contains("off_obs_zone_details_pk")) {
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
	public Integer offenderObservationDeleteZoneHousingData(List<OffObsZoneDetails> deleteList) {
		final String sql = getQuery("OIMOFFOB_DELETE_OBSERVATION_TYPES_ZONE_HOUSING_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffObsZoneDetails sentenceTerms : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
			String tableName = "off_obs_zone_details";
			String whereCondition = "agy_loc_id=:agyLocId and zone_code=:zoneCode and internal_location_id=:internalLocationId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offenderObservationDeleteZoneData : ", e);
			if (e.getMessage().contains("off_obs_zone_details_pk")) {
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
	public Integer offObsCharacteristicsInsertCharctData(List<OffObsCharacteristics> insertList) {
		final String sql = getQuery("OIMOFFOB_INSERT_OBSERVATION_CHARECTERSTICS_DATA");
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
	public Integer offObsCharacteristicsUpdateCharctData(List<OffObsCharacteristics> updateList) {
		final String sql = getQuery("OIMOFFOB_UPDATE_OBSERVATION_CHARECTERSTICS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffObsCharacteristics sentenceTerms : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offObsCharacteristicsUpdateCharctData : ", e);
			if (e.getMessage().contains("off_obs_characteristics_pk")) {
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
	public Integer offObsCharacteristicsDeleteCharctData(List<OffObsCharacteristics> deleteList) {
		final String sql = getQuery("OIMOFFOB_DELETE_OBSERVATION_CHARECTERSTICS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffObsCharacteristics sentenceTerms : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		//try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
//		} catch (Exception e) {
//			logger.error("offObsCharacteristicsDeleteCharctData : ", e);
//			if (e.getMessage().contains("off_obs_characteristics_pk")) {
//				return 2;
//			}
//		}
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer offObsDeleteCharctData(String observationType, String characteristicsType) {		
			final String sql = getQuery("OIMOFFOB_DELETE_OBSERVATION_CHARECTERSTICS_DATA");
			Integer returnVal = 0;
			try {
				namedParameterJdbcTemplate.update(sql, createParams("observationType", observationType,"characteristicsType", characteristicsType));
				returnVal = 1;
			} catch (Exception e) {
				returnVal = 0;
			}
			return returnVal;
		}

	@Override
	public List<OffObsCharacteristics> observationCharectersticExecuteQuery(OffObsCharacteristics searchBean) {
		final String sql = getQuery("OIMOFFOB_OBSERVATION_CHARECTERSTICS_RETRIVE_DATA");
		ArrayList<OffObsCharacteristics> returnList =new ArrayList<OffObsCharacteristics>();
		try {		
			final RowMapper<OffObsCharacteristics> observationTypesRowMpr = Row2BeanRowMapper.makeMapping(sql,
					OffObsCharacteristics.class, observationTypesMapping);
			 returnList = (ArrayList<OffObsCharacteristics>) namedParameterJdbcTemplate
					.query(sql, createParams("obsTypeVersionId",searchBean.getObsTypeVersionId()), observationTypesRowMpr);
		} catch(Exception e) {
			logger.error("observationTypesExecuteQuery", e);
		}
		return returnList;
	}	
	
	/**
	 * Used to capture results from select query
	 * 
	 * @return ListAgencyLocations>
	 */
	public List<AgencyLocations> rgAgyLocRecordGroup(String userName) {
		final String sql = getQuery("OIMOFFOB_FIND_RGAGYLOC");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		List<AgencyLocations> returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("P_USER_ID", userName), mRowMapper);
		} catch (Exception e) {
			logger.error("rgAgyLocRecordGroup", e);
		}
		return returnList;
	}
		
	@Override
	public List<OffObsZoneDetails> getHousingLocDescription(BigDecimal internalLocationId) {
		final String sql = getQuery("OIMOFFOB_GET_HOUSING_LOCATION_DESCRIPTION");
		final RowMapper<OffObsZoneDetails> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OffObsZoneDetails.class,
				mMapping);
		List<OffObsZoneDetails> returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("livingUnitId", internalLocationId), mRowMapper);
		} catch (Exception e) {
			logger.error("rgAgyLocRecordGroup", e);
		}
		return returnList;
	}
	@Override
	public Integer updateCharecterFlagsData(OffenderObservationTypes observationCheckDetailTypeBean) {
		final String sql = getQuery("OIMOFFOB_UPDATE_FLAG_OFFENDER_OBSERVATION_TYPES_DETAILS");
		Integer returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(observationCheckDetailTypeBean));
		} catch (Exception e) {
			returnVal = 0;
			logger.error("Exception  in " + this.getClass().getName() + " updateCharecterFlagsData ", e);
		}
		return returnVal;
	}

	@Override
	public Integer getObservationPeriodDeleteCount(OffenderObservationTypes obj) {
		final String sql = getQuery("OIMOFFOB_GET_OBSERVATION_PERIOD_DELETE_COUNT");
		Integer deleteCount = 0;
		try {
			deleteCount = namedParameterJdbcTemplate.queryForObject(sql, createParams("observationType", obj.getObservationType()),
					Integer.class);
		} catch (Exception e) {
			logger.error("getZoneDetailsHousingExecuteQuery", e);
		}
		return deleteCount;
	}
	
	
	@Override
	public BigDecimal getObsTypeVersionIdSeq() {
		final String sql = getQuery("OIMOFFOB_GET_OBSERVATION_TYPE_VERSION_ID");
		BigDecimal obsTypeVesrionId = BigDecimal.ZERO;
		try {
			obsTypeVesrionId = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error("getZoneDetailsHousingExecuteQuery", e);
		}
		return obsTypeVesrionId;
	}

	@Override
	public Integer offenderObservationCommonInsertData(List<OffenderObservationTypes> insertList) {
		final String sql = getQuery("OIMOFFOB_INSERT_OBSERVATION_TYPES_COMMON_SAVE_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderObservationTypes sentenceTerms : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
	//	try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
//		}catch (Exception e) {
//			logger.error("offenderObservationCommonInsertData : ", e);
//			if (e.getMessage().contains("offender_obs_types_pk")) {
//				return 2;
//			}
//			if (e.getMessage().contains("off_obs_periods_fk2")) {
//				return 3;
//			}
//		}
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer offObsDetailsCommonInsertCharctData(List<OffObsCharacteristics> detailTypeCodeList) {
		final String sql = getQuery("OIMOFFOB_INSERT_OBSERVATION_DETAILS_COMMON_SAVE_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffObsCharacteristics sentenceTerms : detailTypeCodeList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
	//	try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
//		}catch (Exception e) {
//			logger.error("offObsDetailsCommonInsertCharctData : ", e);
//			if (e.getMessage().contains("off_obs_characteristics_pk")) {
//				return 2;
//			}
//			
//		}
		if (detailTypeCodeList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public Integer updateActiveFlag(List<OffenderObservationTypes> updateList) {
		final String sql = getQuery("OIMOFFOB_UPDATE_ACTIVE_FLAG");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderObservationTypes sentenceTerms : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error("offenderObservationCommonInsertData : ", e);
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<OffenderObservationTypes> getActiveObservationTypeList(OffenderObservationTypes obj) {
		final String sql = getQuery("OIMOFFOB_OBSERVATION_TYPES_ACTIVE_EXISTING_DATA");
		ArrayList<OffenderObservationTypes> returnList =new ArrayList<OffenderObservationTypes>();
		try {		
			final RowMapper<OffenderObservationTypes> observationTypesRowMpr = Row2BeanRowMapper.makeMapping(sql,
					OffenderObservationTypes.class, observationTypesMapping);
			 returnList = (ArrayList<OffenderObservationTypes>) namedParameterJdbcTemplate
					.query(sql, createParams("observationType",obj.getObservationType()), observationTypesRowMpr);
		} catch(Exception e) {
			logger.error("observationTypesExecuteQuery", e);
		}
		return returnList;
	}

	
}

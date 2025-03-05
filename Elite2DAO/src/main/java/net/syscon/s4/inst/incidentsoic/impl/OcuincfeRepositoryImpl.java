package net.syscon.s4.inst.incidentsoic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.incidentsoic.beans.OffenderWeapons;
import net.syscon.s4.im.incidentsoic.beans.StaffEquipment;
import net.syscon.s4.im.incidentsoic.beans.StaffForce;
import net.syscon.s4.inst.incidentsoic.OcuincfeRepositroy;

@Repository
public class OcuincfeRepositoryImpl extends RepositoryBase implements OcuincfeRepositroy {
//	
//	@Autowired
//	protected NamedParameterJdbcTemplate  namedParameterJdbcTemplate ;
//	
//	private static Logger logger = LogManager.getLogger(OcuincfeRepositoryImpl.class);
//	
//	private final Map<String, FieldMapper> incidentStaffForceMappping = new ImmutableMap.Builder<String, FieldMapper>()
//			.put("SEQUENCE_NUMBER", new FieldMapper("sequenceNumber"))
//			.put("AGENCY_INCIDENT_ID", new FieldMapper("agencyIncidentId"))
//			.put("PARTY_SEQ",  new FieldMapper("partySeq"))
//			.put("STAFF_ID",  new FieldMapper("staffId"))
//			.put("FORCE_USED", new FieldMapper("forceUsed"))
//			.put("FORCE_DETAIL", new FieldMapper("forceDetail"))
//			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
//			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
//			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
//			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
//			.put("SEAL_FLAG", new FieldMapper("sealFlag")).build();
//	
//	private final Map<String, FieldMapper> incidentStaffEquipementMappping = new ImmutableMap.Builder<String, FieldMapper>()
//			.put("AGENCY_INCIDENT_ID", new FieldMapper("agencyIncidentId"))
//			.put("PARTY_SEQ",  new FieldMapper("partySeq"))
//			.put("STAFF_ID",  new FieldMapper("staffId"))
//			.put("EQUIPMENT_USED", new FieldMapper("equipmentUsed"))
//			.put("EQUIPMENT_DETAIL", new FieldMapper("equipmentDetail"))
//			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
//			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
//			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
//			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
//			.put("SEAL_FLAG", new FieldMapper("sealFlag")).build();
//	
//	public OcuincfeRepositoryImpl() {
//			super();
//	}
//
//	@Override
//	public List<StaffForce> staffforceExecuteQuery(StaffForce objSearchDao) {
//		final String sql = queryBuilderFactory.getQueryBuilder(getQuery("OCUINCFE_STAFF_FORCE_SELECT_DATA"),incidentStaffForceMappping)
//				.build();
//		String preparedSql = null;
//		final StringBuffer sqlQuery = new StringBuffer();
//		final MapSqlParameterSource params = new MapSqlParameterSource();
//		sqlQuery.append(sql);
//		if(objSearchDao!=null) {
//			sqlQuery.append(" where ");
//			if (objSearchDao != null && objSearchDao.getAgencyIncidentId() != null) {
//				sqlQuery.append(" IWO.AGENCY_INCIDENT_ID =:agencyIncidentId"  );
//				params.addValue("agencyIncidentId", objSearchDao.getAgencyIncidentId());
//			}
//		}
//		preparedSql = sqlQuery.toString().trim();
//		final RowMapper<StaffForce> offenderRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
//				StaffForce.class, incidentStaffForceMappping);
//		List<StaffForce> returnList = new ArrayList<>();
//		try {
//			returnList = (namedParameterJdbcTemplate.query(preparedSql, params,offenderRowMapper));
//		} catch (EmptyResultDataAccessException e) {
//			logger.error(e);
//		}
//
//		return returnList;
//	}
//
//	@Override
//	public List<StaffEquipment> staffEquipementExecuteQuery(StaffEquipment objSearchDao) {
//		final String sql = queryBuilderFactory.getQueryBuilder(getQuery("OCUINCFE_STAFF_EQUIPMENT_SELECT_DATA"),incidentStaffEquipementMappping)
//				.build();
//		String preparedSql = null;
//		final StringBuffer sqlQuery = new StringBuffer();
//		final MapSqlParameterSource params = new MapSqlParameterSource();
//		sqlQuery.append(sql);
//		if(objSearchDao!=null) {
//			sqlQuery.append(" where ");
//			if (objSearchDao != null && objSearchDao.getAgencyIncidentId() != null) {
//				sqlQuery.append(" IWO.AGENCY_INCIDENT_ID =:agencyIncidentId"  );
//				params.addValue("agencyIncidentId", objSearchDao.getAgencyIncidentId());
//			}
//		}
//		preparedSql = sqlQuery.toString().trim();
//		final RowMapper<StaffEquipment> offenderRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
//				StaffEquipment.class, incidentStaffEquipementMappping);
//		List<StaffEquipment> returnList = new ArrayList<>();
//		try {
//			returnList = (namedParameterJdbcTemplate.query(preparedSql, params,offenderRowMapper));
//		} catch (EmptyResultDataAccessException e) {
//			logger.error(e);
//		}
//
//		return returnList;
//	}
//	@Override
//	public Integer StaffForceCommitBeanInsert(List<StaffForce> lInsert) {
//		final String sql = getQuery("OCUINCFE_STAFF_FORCE_INSERT_DATA");
//		int[] returnArray = new int[] {};
//		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
//		for (final StaffForce list : lInsert) {
//			parameters.add(new BeanPropertySqlParameterSource(list));
//		}
//		try {
//			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
//		} catch (Exception e) {
//			logger.error(e);
//		}
//		if (lInsert.size() == returnArray.length) {
//			return 1;
//		} else {
//			return 0;
//		}
//	}
//
//	@Override
//	public Integer StaffForceCommitBeanUpdate(List<StaffForce> lUpdate) {
//		final String sql = getQuery("OCUINCFE_STAFF_FORCE_UPDATE_DATA");
//		int[] returnArray = new int[] {};
//		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
//		for (final StaffForce list : lUpdate) {
//			parameters.add(new BeanPropertySqlParameterSource(list));
//		}
//		try {
//			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
//		} catch (Exception e) {
//			logger.error(e);
//		}
//		if (lUpdate.size() == returnArray.length) {
//			return 1;
//		} else {
//			return 0;
//		}
//	}
//
//	@Override
//	public Integer StaffForceCommitBeanDelete(List<StaffForce> lDelete) {
//		final String sql = getQuery("OCUINCFE_STAFF_FORCE_DELETE_DATA");
//		int[] returnArray = new int[] {};
//		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
//		for (final StaffForce list : lDelete) {
//			parameters.add(new BeanPropertySqlParameterSource(list));
//		}
//		try {
//			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
//		} catch (Exception e) {
//			logger.error(e);
//		}
//		if (lDelete.size() == returnArray.length) {
//			return 1;
//		} else {
//			return 0;
//		}
//	}
//
//	@Override
//	public Integer StaffEquipmentCommitBeanInsert(List<StaffEquipment> lInsert) {
//		final String sql = getQuery("OCUINCFE_STAFF_EQUPMENT_INSERT_DATA");
//		int[] returnArray = new int[] {};
//		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
//		for (final StaffEquipment list : lInsert) {
//			parameters.add(new BeanPropertySqlParameterSource(list));
//		}
//		try {
//			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
//		} catch (Exception e) {
//			logger.error(e);
//		}
//		if (lInsert.size() == returnArray.length) {
//			return 1;
//		} else {
//			return 0;
//		}
//	}
//
//	@Override
//	public Integer StaffEquipmentCommitBeanUpdate(List<StaffEquipment> lUpdate) {
//		final String sql = getQuery("OCUINCFE_STAFF_EQUIPMENT_UPDATE_DATA");
//		int[] returnArray = new int[] {};
//		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
//		for (final StaffEquipment list : lUpdate) {
//			parameters.add(new BeanPropertySqlParameterSource(list));
//		}
//		try {
//			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
//		} catch (Exception e) {
//			logger.error(e);
//		}
//		if (lUpdate.size() == returnArray.length) {
//			return 1;
//		} else {
//			return 0;
//		}
//	}
//
//	@Override
//	public Integer StaffEquipmentCommitBeanDelete(List<StaffEquipment> lDelete) {
//		final String sql = getQuery("OCUINCFE_STAFF_EQUIPMENT_DELETE_DATA");
//		int[] returnArray = new int[] {};
//		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
//		for (final StaffEquipment list : lDelete) {
//			parameters.add(new BeanPropertySqlParameterSource(list));
//		}
//		try {
//			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
//		} catch (Exception e) {
//			logger.error(e);
//		}
//		if (lDelete.size() == returnArray.length) {
//			return 1;
//		} else {
//			return 0;
//		}
//	}
//
}

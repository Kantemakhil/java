package net.syscon.s4.inst.incidentsoic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.core.EliteDateRepository;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentParties;
import net.syscon.s4.im.incidentsoic.beans.IncidentStaffReport;
import net.syscon.s4.im.incidentsoic.beans.StaffEquipment;
import net.syscon.s4.im.incidentsoic.beans.StaffForce;
import net.syscon.s4.inst.incidentsoic.OidstfrpRepository;

@Repository
public class OidstfrpRepositoryImpl extends RepositoryBase implements OidstfrpRepository{
	
	@Autowired
	protected NamedParameterJdbcTemplate  namedParameterJdbcTemplate ;
	
	@Autowired
	private EliteDateRepository dateRepository;
	
	private static Logger logger = LogManager.getLogger(OidstfrpRepositoryImpl.class);
	
	private final Map<String, FieldMapper> incidentStaffReportMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INCIDENT_REPORT_ID", new FieldMapper("incidentReportId"))
			.put("STAFF_ID", new FieldMapper("staffId"))
			.put("REPORT_DATE", new FieldMapper("reportDate"))
			.put("REPORT_TIME", new FieldMapper("reportTime"))
			.put("REPORT_TYPE", new FieldMapper("reportType"))
			.put("lOCK_FLAG", new FieldMapper("lockFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("AGENCY_INCIDENT_ID", new FieldMapper("agencyIncidentId"))
			.put("PARTY_SEQ", new FieldMapper("partySeq"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("INCIDENT_DETAILS", new FieldMapper("incidentDetails"))
			.put("LOCK_REFERENCE_TIME", new FieldMapper("lockReferenceTime"))
			.build();
	
	
	private final Map<String, FieldMapper> incidentStaffForceMappping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEQUENCE_NUMBER", new FieldMapper("sequenceNumber"))
			.put("AGENCY_INCIDENT_ID", new FieldMapper("agencyIncidentId"))
			.put("PARTY_SEQ",  new FieldMapper("partySeq"))
			.put("STAFF_ID",  new FieldMapper("staffId"))
			.put("FORCE_USED", new FieldMapper("forceUsed"))
			.put("FORCE_DETAIL", new FieldMapper("forceDetail"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("INCIDENT_REPORT_ID", new FieldMapper("incidentReportId"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).build();
	
	private final Map<String, FieldMapper> incidentStaffEquipementMappping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGENCY_INCIDENT_ID", new FieldMapper("agencyIncidentId"))
			.put("PARTY_SEQ",  new FieldMapper("partySeq"))
			.put("STAFF_ID",  new FieldMapper("staffId"))
			.put("EQUIPMENT_USED", new FieldMapper("equipmentUsed"))
			.put("EQUIPMENT_DETAIL", new FieldMapper("equipmentDetail"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).build();
	
	
	 public OidstfrpRepositoryImpl() {
	super();
	 }
	
	@Override
	public List<IncidentStaffReport> staffReportExecuteQuery(IncidentStaffReport commitBean) {
		String staffReportQuery = getQuery("OIDSTFRP_SELECT_STAFF_REPORT_ALL_DATA");
			final String sql = queryBuilderFactory.getQueryBuilder(staffReportQuery,incidentStaffReportMapping).build();
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if(commitBean!=null) {
				if (commitBean != null && commitBean.getIncidentReportId() != null) {
					sqlQuery.append(" ISR.INCIDENT_REPORT_ID =:incidentReportId" +" AND " );
					params.addValue("incidentReportId", commitBean.getIncidentReportId());
				}
				if (commitBean != null && commitBean.getAgencyIncidentId() != null) {
					sqlQuery.append(" ISR.AGENCY_INCIDENT_ID =:agencyIncidentId"+" AND "  );
					params.addValue("agencyIncidentId", commitBean.getAgencyIncidentId());
				
					}
				if(commitBean != null &&commitBean.getReporterStaffId() != null ) {
					sqlQuery.append(" ISR.REPORTER_STAFF_ID =:reportStaffId AND"  );
					params.addValue("reporterStaffId", commitBean.getReporterStaffId());
				}
				if( commitBean != null && commitBean.getPartySeq()!=null) {
					sqlQuery.append(" ISR.PARTY_SEQ =:partySeq"  );
					params.addValue("partySeq", commitBean.getPartySeq());
				}
		}
		logger.info("inside of staffreport @@@@@@@++++++"+commitBean.getAgencyIncidentId()+"staff ID:"+commitBean.getReporterStaffId());
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		logger.info("modified sql for staff detail @@@@@@@++++++"+preparedSql);
		final RowMapper<IncidentStaffReport> offenderRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				IncidentStaffReport.class, incidentStaffReportMapping);
		List<IncidentStaffReport> returnList = new ArrayList<>();
		try {
			returnList = (namedParameterJdbcTemplate.query(preparedSql, params,offenderRowMapper));
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			e.printStackTrace();
		} catch(Exception ex) {
			logger.error(ex);
			ex.printStackTrace();
		}
		logger.info("staff report deatils list ============"+returnList!=null?returnList.size():0);
		return returnList;
	}
	@Override
	public Integer staffReportCommitDataInsert(List<IncidentStaffReport> linsert) {
		for (IncidentStaffReport incidentStaffReport : linsert) {
			incidentStaffReport.setIncidentReportId(IncidentReportIdcDAO());
		}
		final String sql = getQuery("OIDSTFRP_INSERT_VALUES_STAFF_REPORT");
		
		int reportId=0;
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final IncidentStaffReport list : linsert) {
			reportId=list.getIncidentReportId();
			list.setCreateDateTime(dateRepository.getDBTime());
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("staffReportCommitDataInsert", e);
		}
		if (linsert.size() == returnArray.length) {
			return reportId;
		} else {
			return 0;
		}
	}
	
	public Integer IncidentReportIdcDAO() {
		
		final String sql = getQuery("NEXT_ID_GET");
		final Integer returnObj = namedParameterJdbcTemplate.queryForObject(sql,createParams(),Integer.class);
		return returnObj;
	}

	@Override
	public Integer staffReportCommitDataUpdate(List<IncidentStaffReport> linsert) {
		final String sql = getQuery("OIDSTFRP_UPDATE_VALUES_SATFF_REPORT");
		int[] returnArray = new int[] {};
		int reportId=0;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final IncidentStaffReport list : linsert) {
			reportId=list.getIncidentReportId();
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (linsert.size() == returnArray.length) {
			return reportId;
		} else {
			return 0;
		}
	}

	@Override
	public Integer staffReportCommitDataDelete(List<IncidentStaffReport> linsert) {
		final String sql = getQuery("OIDSTFRP_DELETE_VALUES_STAFF_REPORT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final IncidentStaffReport list : linsert) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			String tableName = "INCIDENT_STAFF_REPORTS";
			String whereClause = "INCIDENT_REPORT_ID=:incidentReportId AND AGENCY_INCIDENT_ID=:agencyIncidentId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method staffReportCommitDataDelete", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (linsert.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
	

	@Override
	public List<StaffForce> staffforceExecuteQuery(StaffForce objSearchDao) {
		final String sql = queryBuilderFactory.getQueryBuilder(getQuery("OCUINCFE_STAFF_FORCE_SELECT_DATA"),incidentStaffForceMappping).build();
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		final RowMapper<StaffForce> offenderRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				StaffForce.class, incidentStaffForceMappping);
		List<StaffForce> returnList = new ArrayList<>();
		try {
			returnList = (namedParameterJdbcTemplate.query(sql, createParams("incidentReportId",objSearchDao.getIncidentReportId()),offenderRowMapper));
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
		}

		return returnList;
	}

	@Override
	public List<StaffEquipment> staffEquipementExecuteQuery(StaffEquipment objSearchDao) {
		final String sql = queryBuilderFactory.getQueryBuilder(getQuery("OCUINCFE_STAFF_EQUIPMENT_SELECT_DATA"),incidentStaffEquipementMappping)
				.build();
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		final RowMapper<StaffEquipment> offenderRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				StaffEquipment.class, incidentStaffEquipementMappping);
		List<StaffEquipment> returnList = new ArrayList<>();
		try {
			returnList = (namedParameterJdbcTemplate.query(sql, createParams("incidentReportId",objSearchDao.getIncidentReportId()),offenderRowMapper));
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
		}

		return returnList;
	}
	@Override
	public Integer StaffForceCommitBeanInsert(List<StaffForce> lInsert) {
		final String sql = getQuery("OCUINCFE_STAFF_FORCE_INSERT_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StaffForce list : lInsert) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
			if(e.getMessage().contains("incident_staff_forces_uk")) {
				return 3;
			}
		}
		if (lInsert.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer StaffForceCommitBeanUpdate(List<StaffForce> lUpdate) {
		final String sql = getQuery("OCUINCFE_STAFF_FORCE_UPDATE_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StaffForce list : lUpdate) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
			if(e.getMessage().contains("incident_staff_forces_uk")|| e.getMessage().contains("SQL state [25P02]") ) {
				return 3;
			}
			if(e.getMessage().contains("DataIntegrityViolationException") ) {
				return 5;
			}
		}
		if (lUpdate.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer StaffForceCommitBeanDelete(List<StaffForce> lDelete) {
		final String sql = getQuery("OCUINCFE_STAFF_FORCE_DELETE_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StaffForce list : lDelete) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			String tableName = "INCIDENT_STAFF_FORCES";
			String whereClause = "INCIDENT_REPORT_ID=:incidentReportId and LIST_SEQ=:listSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method StaffForceCommitBeanDelete", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
			if(e.getMessage().contains("SQL state [25P02]")) {
				return 3;
			}
		}
		if (lDelete.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer StaffEquipmentCommitBeanInsert(List<StaffEquipment> lInsert) {
		final String sql = getQuery("OCUINCFE_STAFF_EQUIPMENT_INSERT_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StaffEquipment list : lInsert) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
			if(e.getMessage().contains("incident_staff_equipments_uk")) {
				return 4;
			}
			
		}
		if (lInsert.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	@Transactional
	public Integer StaffEquipmentCommitBeanUpdate(List<StaffEquipment> lUpdate) {
		final String sql = getQuery("OCUINCFE_STAFF_EQUIPMENT_UPDATE_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StaffEquipment list : lUpdate) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
			if(e.getMessage().contains("incident_staff_equipments_uk") || e.getMessage().contains("SQL state [25P02]" )) {
				return 4;
			}
		}
		if (lUpdate.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer StaffEquipmentCommitBeanDelete(List<StaffEquipment> lDelete) {
		final String sql = getQuery("OCUINCFE_STAFF_EQUIPMENT_DELETE_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StaffEquipment list : lDelete) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			String tableName = "INCIDENT_STAFF_EQUIPMENTS";
			String whereClause = "EQUIPMENT_USED=:equipmentUsed AND INCIDENT_REPORT_ID=:incidentReportId AND equipment_used = :equipmentUsed";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method StaffEquipmentCommitBeanDelete", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
			if(e.getMessage().contains("SQL state [25P02]")) {
				return 4;
			}
		}
		if (lDelete.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer getIncidentStaffForecCount(Integer agencyIncidentId) {
		Integer staffCount = 0;
		final String sql = getQuery("GET_INCIDENT_STAFF_FORCE_COUNT");
		try {
			staffCount = namedParameterJdbcTemplate.queryForObject(sql, createParams("incidentReportId", agencyIncidentId),
					Integer.class);
		} catch (final Exception e) {
			logger.error("getIncidentStaffForecCount", e);
		}
		return staffCount;
	}
	
	@Override
	public String getLockFlag(Integer incidentReportId) {
		String retVal ="";
		final String sql = getQuery("GET_INCIDENT_STAFF_REPORT_LOCK_FLAG");
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("incidentReportId", incidentReportId),
					String.class);
		} catch (final Exception e) {
			logger.error("getsfReportAgeIncParties", e);
		}
		return retVal;
	}
	
	@Override
	public String getsfReportAgeIncParties(Integer agencyIncidentId , Integer staffId) {
		String retVal ="";
		final String sql = getQuery("GET_AGENCY_INCIDENT_PARTIES_STAFF_REPORT_TYPE");
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("agencyIncidentId", agencyIncidentId,"partySeq",staffId),
					String.class);
		} catch (final Exception e) {
			logger.error("getsfReportAgeIncParties", e);
		}
		return retVal;
	}
	
	@Override
	public int updAgencyIncRepStaffReport(IncidentStaffReport incidentStaffReport) {
		String sql = null;
		int retVal = 0;
		sql = getQuery("UPDATE_AGENCY_INCIDENT_PARTIES_LOCK_REFERENCE_TIME");
		try {
			retVal = namedParameterJdbcTemplate.update(sql, createParams("agencyIncidentId",incidentStaffReport.getAgencyIncidentId(), "lockReferenceTime", incidentStaffReport.getLockReferenceTime(),"incidentReportId",incidentStaffReport.getIncidentReportId(),"modifyUserId",incidentStaffReport.getModifyUserId()));
		} catch (Exception e) {
			logger.error("updAgencyIncRepStaffReport :", e);
			return retVal;
		}
		return retVal;
	}
	
	
	@Override
    public ReferenceCodes getCountDownTime(String code) {
    	String sql=getQuery("OIDISTRFP_GET_COUNTDOWN_TIME");
    	try {
    		return namedParameterJdbcTemplate.queryForObject(sql, createParams("reportType",code), new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class));
    	}catch (Exception e) {
		logger.error("error in getCountDownTime", e);
		return new ReferenceCodes();
		}
    }
	
	@Override
	public AgencyIncidentParties getLockReferenceTime(AgencyIncidentParties agencyIncidentParties) {
		String sql = getQuery("OIDISTRFP_GET_LOCK_REFERENCE_TIME");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("agencyIncidentId",agencyIncidentParties.getAgencyIncidentId(),
					"partySeq",agencyIncidentParties.getPartySeq()), new BeanPropertyRowMapper<AgencyIncidentParties>(AgencyIncidentParties.class));
		}catch (Exception e) {
			logger.error("error in"+ this.getClass().getName() +" getLockReferenceTime", e);
			return new AgencyIncidentParties();
		}
	}

	@Override
	public Integer updateLockFlag(IncidentStaffReport incidentStaffReport) {
		String sql = getQuery("OIDISTRFP_UPDATE_LOCK_FlAG");
		int reVal=0;
		try {
			reVal= namedParameterJdbcTemplate.update(sql ,createParams("agencyIncidentId",incidentStaffReport.getAgencyIncidentId(),"incidentReportId",incidentStaffReport.getIncidentReportId(),"lockFlag",incidentStaffReport.getLockFlag(),"repCompletFlag",incidentStaffReport.getRepCompletFlag()));
		}catch (Exception e) {
			logger.error("error in"+ this.getClass().getName() +" updateLockFlag", e);
		}
		return reVal;
	}
	
	
}

package net.syscon.s4.inst.incidentsoic.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VAgencyIncidents;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;
import net.syscon.s4.inst.incidentsoic.OiiinlogRepository;
/**
 * Class OiiinlogRepositoryImpl
 */
@Repository
public class OiiinlogRepositoryImpl extends RepositoryBase implements OiiinlogRepository{
	private static Logger logger = LogManager.getLogger(OiiinlogRepositoryImpl.class);
private final Map<String, FieldMapper> modulePrivilegesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("ACCESS_PRIVILEGE", 						new FieldMapper("accessPrivilege"))
.put("MODULE_NAME", 						new FieldMapper("moduleName"))
.build();
private final Map<String, FieldMapper> vAgencyIncidentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("REPORTED_STAFF_ID", new FieldMapper("reportedStaffId"))
.put("AGENCY_INCIDENT_ID", new FieldMapper("agencyIncidentId"))
.put("STAFF_FIRST_NAME", new FieldMapper("staffFirstName"))
.put("REPORT_DATE", new FieldMapper("reportDate"))
.put("INCIDENT_STATUS", new FieldMapper("incidentStatus"))
.put("STAFF_LAST_NAME", new FieldMapper("staffLastName"))
.put("REPORT_TIME", new FieldMapper("reportTime"))
.put("REPAIR_FLAG", new FieldMapper("repairFlag"))
.put("INCIDENT_DATE", new FieldMapper("incidentDate"))
.put("INCIDENT_TYPE_DESC", new FieldMapper("incidentTypeDesc"))
.put("INT_LOC_DESCRIPTION", new FieldMapper("intLocDescription"))
.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
.put("INCIDENT_TYPE", new FieldMapper("incidentType"))
.put("INCIDENT_TIME", new FieldMapper("incidentTime"))
.put("description", new FieldMapper("description"))
.build();

private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
.put("CREATE_USER_ID", new FieldMapper("createUserId"))
.put("DSP_DESCRIPTION", new FieldMapper("description"))
.put("CODE", new FieldMapper("code"))
.put("DOMAIN", new FieldMapper("domain"))
.put("PARENT_DOMAIN", new FieldMapper("parentDomainId"))
.put("PARENT_CODE", new FieldMapper("parentCode"))
.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode"))
.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
.put("CASELOAD_ID", new FieldMapper("caseloadId"))
.put("LAST_NAME", new FieldMapper("lastName"))
.put("FIRST_NAME", new FieldMapper("firstName"))
.put("STAFF_ID", new FieldMapper("staffId"))
.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
.build();

private final Map<String, FieldMapper> agencyincidentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("REPORTED_STAFF_ID", new FieldMapper("reportedStaffId"))
.put("AGENCY_INCIDENT_ID", new FieldMapper("agencyIncidentId"))
.put("INCIDENT_DATE", new FieldMapper("incidentDate"))
.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
.put("INCIDENT_TIME", new FieldMapper("incidentTime"))
.put("INCIDENT_TYPE", new FieldMapper("incidentType"))
.put("INCIDENT_STATUS", new FieldMapper("incidentStatus"))
.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
.put("CREATE_USER_ID", new FieldMapper("createUserId"))
.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
.put("LOCK_FLAG", new FieldMapper("lockFlag"))
.put("INCIDENT_DETAILS", new FieldMapper("incidentDetails"))
.put("REPORT_DATE", new FieldMapper("reportDate"))
.put("REPORT_TIME", new FieldMapper("reportTime"))
.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
.put("LEVEL_CODE", new FieldMapper("levelCode"))
.put("LOG_NO", new FieldMapper("logNo"))
.put("INCIDENT_TEXT", new FieldMapper("incidentText"))
.put("SEAL_FLAG", new FieldMapper("sealFlag")).build();


private final Map<String, FieldMapper> referenceCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
.put("CREATE_USER_ID", new FieldMapper("createUserId"))
.put("CODE", new FieldMapper("code"))
.put("DOMAIN", new FieldMapper("domain"))
.put("PARENT_DOMAIN", new FieldMapper("parentDomainId"))
.put("PARENT_CODE", new FieldMapper("parentCode"))
.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode"))
.put("AGY_LOC_ID", new FieldMapper("agyLocId")).build();

	/**
	 * Creates new OiiinlogRepositoryImpl class Object
	 */
	public OiiinlogRepositoryImpl() {
		// OiiinlogRepositoryImpl
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgOccurTypeRecordGroup(){
		final String sql = getQuery("OIIINLOG_FIND_RGOCCURTYPE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @param searchBean
	 * @return List<CaseloadAgencyLocations>
	 */
	public List<CaseloadAgencyLocations> rgAgyLocRecordGroup(final String caseLoadId){
		final String sql = getQuery("OIIINLOG_FIND_RGAGYLOC");
		final RowMapper<CaseloadAgencyLocations> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAgencyLocations.class, referenceCodesMapping);
		ArrayList<CaseloadAgencyLocations> refList = new ArrayList<CaseloadAgencyLocations>();
		try {
			refList = (ArrayList<CaseloadAgencyLocations>) namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID",caseLoadId), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @param searchBean
	 * @return List<AgencyInternalLocations>
	 */
	public List<AgencyInternalLocations> rgLevelLocRecordGroup(final AgencyInternalLocations searchBean) {
		String param = "ENTER-QUERY";
		final String sql = getQuery("OIIINLOG_FIND_RGLEVELLOC");
		final RowMapper<AgencyInternalLocations> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("NBTAGYLOCID", searchBean.getAgyLocId(), "MODE", param), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @param caseloadId
	 * @return List<StaffMembers>
	 */
	public List<StaffMembers> rgStaffRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIIINLOG_FIND_RGSTAFF");
		final RowMapper<StaffMembers> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseloadId),
					referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VAgencyIncidents
	 *
	 * @return List<VAgencyIncidents>
	 *
	 * @throws SQLException
	 */
	public List<VAgencyIncidents> vAgyIncExecuteQuery(final VAgencyIncidents objSearchDao) {
		 String sql =null;
		 final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		if("INST".equals(objSearchDao.getCaseloadType())) {		
			sql = getQuery("OIIINLOG_VAGYINC_FIND_V_AGENCY_INCIDENTS");
		} else {
			sql = getQuery("OIINLOG_COMMUNITY_INCIDENTS_LOG_INQUIRY_GET_DATA");
			valuesList.addValue("caseLoadId", objSearchDao.getCaseloadId());
		}
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" and ");
		
		if (objSearchDao.getAgencyIncidentId() != null) {
			sqlQuery.append("VI.AGENCY_INCIDENT_ID =  :agencyIncidentId " + " and ");
			valuesList.addValue("agencyIncidentId", objSearchDao.getAgencyIncidentId());
		}
		if (objSearchDao.getReportedStaffId() != null) {
			sqlQuery.append("VI.REPORTED_STAFF_ID =  :reportedStaffId " + " and ");
			valuesList.addValue("reportedStaffId", objSearchDao.getReportedStaffId());
		}
//		if (objSearchDao.getIncidentDate() != null) {
//			sqlQuery.append("AGENCY_INCIDENT_ID =  :agencyIncidentId " + " and ");
//			valuesList.addValue("agencyIncidentId", objSearchDao.getIncidentDate());
//		}
//		if (objSearchDao.getAgencyIncidentId() != null) {
//			sqlQuery.append("AGENCY_INCIDENT_ID =  :agencyIncidentId " + " and ");
//			valuesList.addValue("agencyIncidentId", objSearchDao.getAgencyIncidentId());
//		}
//		if (objSearchDao.getAgencyIncidentId() != null) {
//			sqlQuery.append("AGENCY_INCIDENT_ID =  :agencyIncidentId " + " and ");
//			valuesList.addValue("agencyIncidentId", objSearchDao.getAgencyIncidentId());
//		}
		
		if (objSearchDao.getStaffFirstName() != null) {
			sqlQuery.append("VI.STAFF_FIRST_NAME = :staffFirstName"+" and ");
			valuesList.addValue("staffFirstName",objSearchDao.getStaffFirstName());
		}
		if (objSearchDao.getStaffLastName() != null) {
			sqlQuery.append("VI.STAFF_LAST_NAME = :staffLastName " + " and ");
			valuesList.addValue("staffLastName",objSearchDao.getStaffLastName());
		}
		if (objSearchDao.getIncidentDate() != null) {
			sqlQuery.append("VI.INCIDENT_DATE = to_date('" + new java.sql.Date(objSearchDao.getIncidentDate().getTime())+ "','yyyy/MM/dd')" + " and ");
		}
		if (objSearchDao.getFromDate() != null && objSearchDao.getToDate() != null) {
			sqlQuery.append("VI.INCIDENT_DATE between to_date('" + new java.sql.Date(objSearchDao.getFromDate().getTime())
					+ "','yyyy/MM/dd') and to_date('" + new java.sql.Date(objSearchDao.getToDate().getTime())
					+ "','yyyy/MM/dd') " + " and ");
		} else if (objSearchDao.getFromDate() != null && objSearchDao.getToDate() == null) {
			sqlQuery.append("VI.INCIDENT_DATE >= to_date('" + new java.sql.Date(objSearchDao.getFromDate().getTime())
					+ "','yyyy/MM/dd')" + " and ");
		} else if (objSearchDao.getFromDate() == null && objSearchDao.getToDate() != null) {
			sqlQuery.append("VI.INCIDENT_DATE <= to_date('" + new java.sql.Date(objSearchDao.getToDate().getTime())
					+ "','yyyy/MM/dd')" + " and ");
		}
		if (objSearchDao.getIncidentTime() != null) {
			sqlQuery.append("VI.INCIDENT_TIME = :IncidentTime " + " and ");
			valuesList.addValue("incidenttime",objSearchDao.getIncidentTime());
		}
		if (objSearchDao.getInternalLocationId() != null) {
			sqlQuery.append("VI.INTERNAL_LOCATION_ID = :InternalLocationId " + " and ");
			valuesList.addValue("InternalLocationId",objSearchDao.getInternalLocationId());
			
		}
		if (objSearchDao.getIncidentType() != null) {
			sqlQuery.append("VI.INCIDENT_TYPE = :IncidentType " + " and ");
			valuesList.addValue("IncidentType",objSearchDao.getIncidentType());
		}
		if (objSearchDao.getIncidentTypeDesc() != null) {
			sqlQuery.append("VI.INCIDENT_TYPE_DESC = :IncidentTypeDesc " + " and ");
			valuesList.addValue("IncidentTypeDesc",objSearchDao.getIncidentTypeDesc());
		}
		if (objSearchDao.getIncidentStatus() != null) {
			sqlQuery.append("VI.INCIDENT_STATUS = :IncidentStatus " + " and ");
			valuesList.addValue("IncidentStatus",objSearchDao.getIncidentStatus());
		}
		if (objSearchDao.getReportDate() != null) {
			sqlQuery.append("VI.REPORT_DATE = :ReportDate " + " and ");
			valuesList.addValue("ReportDate",objSearchDao.getReportDate());
		}
		if (objSearchDao.getReportTime() != null) {
			sqlQuery.append("VI.REPORT_TIME = :ReportTime " + " and ");
			valuesList.addValue("ReportTime",objSearchDao.getReportTime());
		}
		if (objSearchDao.getAgyLocId() != null) {
			sqlQuery.append("VI.AGY_LOC_ID = :AgyLocId " + " and ");
			valuesList.addValue("AgyLocId",objSearchDao.getAgyLocId());
		}
		if (objSearchDao.getIntLocDescription() != null) {
			sqlQuery.append("VI.INT_LOC_DESCRIPTION = :IntLocDescription " + " and ");
			valuesList.addValue("IntLocDescription",objSearchDao.getIntLocDescription());
		}
		if (objSearchDao.getRepairFlag() != null) {
			sqlQuery.append("VI.REPAIR_FLAG = :RepairFlag " + " and ");
			valuesList.addValue("RepairFlag",objSearchDao.getRepairFlag());
		}
		if (objSearchDao.getOffenderIdDisplay() != null) {
			sqlQuery.append(" (:offenderIdDisplay IS NULL OR VI.AGENCY_INCIDENT_ID IN (SELECT AIP.AGENCY_INCIDENT_ID FROM AGENCY_INCIDENT_PARTIES AIP " +
                            " WHERE AIP.OFFENDER_BOOK_ID IN (SELECT ob.offender_book_id FROM offenders o, offender_bookings ob " + 
                            " WHERE LTRIM(o.offender_id_display::text, '0') like LTRIM(:offenderIdDisplay::text,'0') AND o.root_offender_id = ob.root_offender_id )) )" + " and ");
			
			
			valuesList.addValue("offenderIdDisplay",objSearchDao.getOffenderIdDisplay());
		}
		    preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		}
		sqlQuery.delete(0, sqlQuery.length());
		sqlQuery.append(preparedSql);
		sqlQuery.append(" Order by VI.AGENCY_INCIDENT_ID desc");
		preparedSql = sqlQuery.toString().trim();
		final RowMapper<VAgencyIncidents> VAgencyIncidentsRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				VAgencyIncidents.class, vAgencyIncidentsMapping);
		final ArrayList<VAgencyIncidents> returnList = (ArrayList<VAgencyIncidents>) namedParameterJdbcTemplate
				.query(preparedSql, valuesList, VAgencyIncidentsRowMapper);
		return returnList;
	}
	
	
	/**
	 * method for query callings
	 * 
	 * @return List<AgencyIncidents>
	 * @param AgencyIncidents
	 *            searchBean
	 */
	@Override
	public List<AgencyIncidents> agencyIncidentsExecuteQuery(final AgencyIncidents searchBean) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIIINLOG_AGENCYINCIDENTS_FIND_AGENCY_INCIDENTS"), agencyincidentsMapping)
				.build();
		final RowMapper<AgencyIncidents> offenderRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyIncidents.class,
				agencyincidentsMapping);
		List<AgencyIncidents> returnList = null;
		try {
			returnList = (namedParameterJdbcTemplate.query(sql, createParams(),
					offenderRowMapper));
		} catch (Exception e) {

		}
		
		return returnList;
	}

	/**
	 * @param
	 *
	 *
	 */
	public int preInsert(){
		return 0;
	}
	
	@Override
	public List<String> findIncidentTypeDescList() {
		final String sql = getQuery("FIND_INCIDENT_TYPE_LIST");
		 return namedParameterJdbcTemplate.queryForList(sql,createParams(),String.class);
	}
	
	@Override
	public List<String> findIntLocationsList() {
		final String sql = getQuery("FIND_INCIDENT_LOC_LIST");
		 return namedParameterJdbcTemplate.queryForList(sql,createParams(),String.class);
	}
	
	@Override
	public List<AgencyInternalLocations> rgLevelInternalLocationIdsRecordGroup(final String agencyLocId) {
		final String sql = queryBuilderFactory
				.getQueryBuilder(getQuery("OIDINCDE_FIND_RGLEVELINTERNALLOCATIONIDS"), referenceCodeMapping).build();
		final RowMapper<AgencyInternalLocations> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, referenceCodeMapping);
		List<AgencyInternalLocations> returnList = new ArrayList<AgencyInternalLocations>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("agyLocId", agencyLocId),
					referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
		return returnList;
	}
	
	/**
	 * method for query callings
	 * 
	 * @return List<String>
	 */
	@Override
	public List<String> agencyIncidentsCallModuleOidincde() {
		final String sql = getQuery("OIIINLOG_AGENCYINCIDENTS_CALL_MODULE_OIDINCDE");
		List<String> returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForList(sql,createParams(), String.class);
		} catch (Exception e) {

		}
		return returnList;
	}
	
	/**
	 * method for query callings
	 * 
	 * @return List<String>
	 */
	@Override
	public List<String> agencyIncidentsCallModuleOiuincrp() {
		final String sql = getQuery("OIIINLOG_AGENCYINCIDENTS_CALL_MODULE_OIUINCRP");
		List<String> returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForList(sql,createParams(), String.class);
		} catch (Exception e) {

		}
		return returnList;
	}

}

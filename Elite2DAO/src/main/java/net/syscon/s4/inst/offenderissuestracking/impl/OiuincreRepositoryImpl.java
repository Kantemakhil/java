package net.syscon.s4.inst.offenderissuestracking.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;
import net.syscon.s4.inst.offenderissuestracking.OiuincreRepository;

/**
 * Class OiuincreRepositoryImpl
 */
@Repository
public class OiuincreRepositoryImpl extends RepositoryBase implements OiuincreRepository {

private final Map<String, FieldMapper> agencyIncidentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("REPORTED_STAFF_ID", 					new FieldMapper("reportedStaffId"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("AGENCY_INCIDENT_ID", 					new FieldMapper("agencyIncidentId"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("REPORT_DATE", 						new FieldMapper("reportDate"))
.put("LOCK_FLAG", 						    new FieldMapper("lockFlag"))
.put("INCIDENT_STATUS", 					new FieldMapper("incidentStatus"))
.put("REPORT_TIME", 						new FieldMapper("reportTime"))
.put("INCIDENT_DATE", 						new FieldMapper("incidentDate"))
.put("INCIDENT_TEXT", 						new FieldMapper("incidentText"))
.put("LEVEL_CODE", 						    new FieldMapper("levelCode"))
.put("SEAL_FLAG", 						    new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 					new FieldMapper("createDateTime"))
.put("AGY_LOC_ID", 						    new FieldMapper("agyLocId"))
.put("INTERNAL_LOCATION_ID", 				new FieldMapper("internalLocationId"))
.put("MODIFY_DATETIME", 					new FieldMapper("modifyDateTime"))
.put("INCIDENT_TYPE", 						new FieldMapper("incidentType"))
.put("LOG_NO", 						        new FieldMapper("logNo"))
.put("INCIDENT_TIME", 						new FieldMapper("incidentTime"))
.put("INCIDENT_DETAILS", 					new FieldMapper("incidentDetails"))
.build();
private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("MODULE_NAME", 						new FieldMapper("moduleName"))
.put("MODULE_TYPE", 						new FieldMapper("moduleType"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();

	/**
	 * Creates new OiuincreRepositoryImpl class Object
	 */
	public OiuincreRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            AgencyIncidents
	 *
	 * @return List<AgencyIncidents>
	 *
	 * @
	 */
	public List<AgencyIncidents> agencyIncidentsExecuteQuery(final Integer rootOffenderId) {
		final String sql = getQuery("OIUINCRE_AGENCYINCIDENTS_FIND_AGENCY_INCIDENTS");
		final RowMapper<AgencyIncidents> AgencyIncidentsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyIncidents.class, agencyIncidentsMapping);
		final ArrayList<AgencyIncidents> returnList = (ArrayList<AgencyIncidents>) namedParameterJdbcTemplate.query(sql,
				createParams("ROOT_OFFENDER_ID", rootOffenderId), AgencyIncidentsRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobalsCREATE_FORM_GLOBALS
	 *
	 * @param params
	 *
	 */
	public OmsModules createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OIUINCRE_CREATE_FORM_GLOBALS_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		final OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

}

package net.syscon.s4.inst.securitythreatgroups.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VStgGangSet;
import net.syscon.s4.common.beans.VStgGroup;
import net.syscon.s4.common.beans.VStgSet;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.SystemProfiles;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentParties;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;
import net.syscon.s4.inst.securitythreatgroups.OidstginRepository;

/**
 * Class OidstginRepositoryImpl
 */
@Repository
public class OidstginRepositoryImpl extends RepositoryBase implements OidstginRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidstginRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> vStgSetMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STG_DESC",                    new FieldMapper("stgDesc"))
			.put("PROFILE_VALUE",			    new FieldMapper("profileValue"))
			.build();
	private final Map<String, FieldMapper> vHeaderBlockMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID", 				new FieldMapper("offenderId "))
			.put("ROOT_OFFENDER_ID",			new FieldMapper("rootOffenderId"))
			.put("OFFENDER_BOOK_ID", 			new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACTION_CODE", 				new FieldMapper("actionCode"))
			.put("DESCRIPTION", 				new FieldMapper("description "))
			.put("INCIDENT_ROLE", 				new FieldMapper("incidentRole"))
			.put("STG_ID",						new FieldMapper("stgId"))
			.put("LAST_NAME||", 				new FieldMapper("lastName||"))
			.put("OFFENDER_ID_DISPLA", 			new FieldMapper("offenderIdDispla"))
			.put("FIRST_NAME",					new FieldMapper("firstName"))
			.put("OFFENDER_BOOK_ID", 			new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> securityThreatGroupsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACTION_CODE", 				new FieldMapper("actionCode"))
			.put("DESCRIPTION", 				new FieldMapper("description "))
			.put("INCIDENT_ROLE",				new FieldMapper("incidentRole"))
			.put("STG_ID", 						new FieldMapper("stgId"))
			.put("LAST_NAME||", 				new FieldMapper("lastName||"))
			.put("OFFENDER_ID_DISPLA",			new FieldMapper("offenderIdDispla"))
			.put("FIRST_NAME", 					new FieldMapper("firstName"))
			.put("OFFENDER_BOOK_ID", 			new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> vStgGangSetMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STG_DESC", 					new FieldMapper("stgDesc "))
			.put("PROFILE_VALUE",			    new FieldMapper("profileValue "))
			.build();
	private final Map<String, FieldMapper> offenderStgAffiliationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACTION_CODE", 				new FieldMapper("actionCode"))
			.put("DESCRIPTION", 				new FieldMapper("description "))
			.put("INCIDENT_ROLE", 				new FieldMapper("incidentRole"))	
			.put("STG_ID", 						new FieldMapper("stgId"))
			.put("LAST_NAME||",					new FieldMapper("lastName||"))
			.put("OFFENDER_ID_DISPLA",			new FieldMapper("offenderIdDispla"))
			.put("FIRST_NAME", 					new FieldMapper("firstName"))
			.put("OFFENDER_BOOK_ID",			new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> offendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACTION_CODE",					new FieldMapper("actionCode"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.put("INCIDENT_ROLE", 				new FieldMapper("incidentRole"))
			.put("STG_ID",						new FieldMapper("stgId"))
			.put("LAST_NAME",					new FieldMapper("lastName"))
			.put("OFFENDER_ID_DISPLAY", 		new FieldMapper("offenderIdDisplay"))
			.put("FIRST_NAME", 					new FieldMapper("firstName"))
			.put("OFFENDER_BOOK_ID", 			new FieldMapper("offenderBookId"))
			.put("INTERNAL_LOCATION_TYPE", 		new FieldMapper("internalLocationType"))
			.build();
	private final Map<String, FieldMapper> vStgGroupMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STG_DESC", 					new FieldMapper("stgDesc "))
			.put("PROFILE_VALUE", 				new FieldMapper("profileValue "))
			.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STG_DESC", 					new FieldMapper("stgDesc "))
			.put("PROFILE_VALUE",				new FieldMapper("profileValue "))
			.build();
	private final Map<String, FieldMapper> agencyIncidentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("REPORTED_STAFF_ID   ",		new FieldMapper("reportedStaffId  "))
			.put("AGENCY_INCIDENT_ID  ",		new FieldMapper("agencyIncidentId "))
			.put("INCIDENT_DATE", 				new FieldMapper("incidentDate"))
			.put("INTERNAL_LOCATION_ID",		new FieldMapper("internalLocationId"))
			.put("INCIDENT_TIME", 				new FieldMapper("incidentTime"))
			.put("INCIDENT_TYPE",			    new FieldMapper("incidentType"))
			.put("INCIDENT_STATUS",				new FieldMapper("incidentStatus"))
			.put("CREATE_DATETIME",				new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDateTime"))
			.put("LOCK_FLAG", 					new FieldMapper("lockFlag"))
			.put("INCIDENT_DETAILS",			new FieldMapper("incidentDetails"))
			.put("REPORT_DATE", 				new FieldMapper("reportDate"))
			.put("REPORT_TIME", 				new FieldMapper("reportTime"))
			.put("AGY_LOC_ID", 					new FieldMapper("agyLocId"))
			.put("LEVEL_CODE",					new FieldMapper("levelCode"))
			.put("LOG_NO", 						new FieldMapper("logNo"))
			.put("INCIDENT_TEXT", 				new FieldMapper("incidentText"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.build();

	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()

			.put("ASSIGNED_CASELOAD_ID", 	    new FieldMapper("assignedCaseloadId"))
			.put("WORKING_STOCK_LOC_ID", 		new FieldMapper("workingStockLocId"))
			.put("WORKING_CASELOAD_ID", 		new FieldMapper("workingCaseloadId"))
			.put("USER_ID", 					new FieldMapper("userId"))
			.put("BADGE_ID", 					new FieldMapper("badgeId"))
			.build();

	private final Map<String, FieldMapper> agencyIncidentPartiesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STG_ID",  					new FieldMapper("stgId"))
			.put("PARTY_SEQ", 					new FieldMapper("partySeq"))
			.put("INCIDENT_ROLE",				new FieldMapper("incidentRole"))
			.put("OFFENDER_BOOK_ID",			new FieldMapper("offenderBookId"))
			.put("STAFF_ID", 					new FieldMapper("staffId"))
			.put("PERSON_ID",					new FieldMapper("personId"))
			.put("DISPOSITION_TYPE", 			new FieldMapper("dispositionType"))
			.put("DISPOSITION_DATE", 			new FieldMapper("dispositionDate"))
			.put("OIC_INCIDENT_ID", 			new FieldMapper("oicIncidentId"))
			.put("COMMENT_TEXT", 				new FieldMapper("commentText"))
			.put("CREATE_DATETIME",				new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", 				new FieldMapper("createUserId"))
			.put("ACTION_CODE", 				new FieldMapper("actionCode"))
			.put("PARTY_ADDED_DATE", 			new FieldMapper("partyAddedDate"))
			.put("MODIFY_DATETIME", 			new FieldMapper("modifyDateTime"))
			.put("MODIFY_USER_ID", 				new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", 					new FieldMapper("sealFlag"))
			.put("REP_COMPLET_FLAG", 			new FieldMapper("repCompletFlag"))
			.put("DESCRIPTION",					new FieldMapper("description"))
			.build();

	private final Map<String, FieldMapper> agencyInternalLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("INTERNAL_LOCATION_TYPE", 		new FieldMapper("internalLocationType"))
			.put("INTERNAL_LOCATION_ID", 		new FieldMapper("internalLocationId"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.build();

	/**
	 * Creates new OidstginRepositoryImpl class Object
	 */
	public OidstginRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            AgencyIncidents
	 *
	 * @return List<AgencyIncidents>
	 *
	 * 
	 */
	public List<AgencyIncidents> agyIncExecuteQuery(final AgencyIncidents objSearchDao) {
		final String sql = getQuery("OIDSTGIN_AGYINC_FIND_AGENCY_INCIDENTS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		String preSqlQuery = null;
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if ("OIDMBRDT".equals(objSearchDao.getOriginatingForm())) {
				sqlQuery.append(" AGENCY_INCIDENT_ID IN (SELECT AGENCY_INCIDENT_ID FROM AGENCY_INCIDENT_PARTIES WHERE "
						+ " OFFENDER_BOOK_ID = :OFFID) ");
				params.addValue("OFFID", objSearchDao.getOffenderBookId());
			} else if ("OIITGDET".equals(objSearchDao.getOriginatingForm())) {
				sqlQuery.append(" (agency_incident_id in (select aip.agency_incident_id "
						+ " from agency_incident_parties aip, offender_stg_affiliations osa where aip.offender_book_id = osa.offender_book_id "
						+ " and osa.stg_id = :STGID)) OR (agency_incident_id in (select agency_incident_id from AGENCY_INCIDENT_ASSO_TOSTG "
						+ " where stg_id = :STGID)) ");
				params.addValue("STGID", objSearchDao.getAgencyIncidentId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY INCIDENT_DATE, INCIDENT_TIME DESC ");
		final RowMapper<AgencyIncidents> AgnyIncRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyIncidents.class,
				agencyIncidentsMapping);
		List<AgencyIncidents> returnList = new ArrayList<AgencyIncidents>();
		try {
			returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, AgnyIncRowMapper);
		} catch (Exception e) {
			logger.error("agyIncExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            AgencyIncidentParties
	 *
	 * @return List<AgencyIncidentParties>
	 *
	 * 
	 */
	public List<AgencyIncidentParties> agencyIncidentPartiesExecuteQuery(final AgencyIncidentParties searchRecord) {
		final String sql = getQuery("OIDSTGIN_AGENCYINCIDENTPARTIES_FIND_AGENCY_INCIDENT_PARTIES");
		final RowMapper<AgencyIncidentParties> AgcyIncPRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyIncidentParties.class, agencyIncidentPartiesMapping);
		List<AgencyIncidentParties> returnList = new ArrayList<AgencyIncidentParties>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("AGENCYINCIDENTID", searchRecord.getAgencyIncidentId()), AgcyIncPRowMapper);
		} catch (Exception e) {
			logger.error("agencyIncidentPartiesExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            AgencyIncidentParties
	 *
	 * @return List<AgencyIncidentParties>
	 *
	 * 
	 */
	public List<AgencyIncidentParties> agencyIncidentPartiesExecuteQuerySTGIDS(final Integer offenderBookid) {

		final String sql = getQuery("OIDSTGIN_AGENCYINCIDENTPARTIES_FIND_STG_IDS");
		final RowMapper<AgencyIncidentParties> AgcyIncPRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyIncidentParties.class, agencyIncidentPartiesMapping);
		List<AgencyIncidentParties> returnList = new ArrayList<AgencyIncidentParties>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("OFFENDERBOOKID", offenderBookid),
					AgcyIncPRowMapper);
		} catch (Exception e) {
			logger.error("agencyIncidentPartiesExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyIncidentPartiesPostQuery
	 *
	 * @param params
	 *
	 */
	public Offenders agencyIncidentPartiesPostQuery(final Integer offBooId) {
		final String sql = getQuery("OIDSTGIN_AGENCY_INCIDENT_PARTIES_POSTQUERY_OFFENDERS");
		final RowMapper<Offenders> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,
				offendersMapping);
		Offenders returnList = new Offenders();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", offBooId),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("agencyIncidentPartiesPostQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyIncidentPartiesPostQuery
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> agencyIncidentPartiesPostQuery(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDSTGIN_AGENCY_INCIDENT_PARTIES_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);

		} catch (Exception e) {
			logger.error("agencyIncidentPartiesPostQuery", e);

		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyIncidentPartiesPostQuery
	 *
	 * @param params
	 *
	 */
	public SecurityThreatGroups agencyIncidentPartiesPostQueryDescription(final Integer stgId) {
		final String sql = getQuery("OIDSTGIN_AGENCY_INCIDENT_PARTIES_POSTQUERY_DESCRIPTION");
		final RowMapper<SecurityThreatGroups> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SecurityThreatGroups.class, securityThreatGroupsMapping);
		SecurityThreatGroups returnList = new SecurityThreatGroups();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("STGID", stgId), columnRowMapper);
		} catch (Exception e) {
			logger.error("agencyIncidentPartiesPostQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * agencyIncidentPartiesPostQueryoff_cur
	 *
	 * @param paramBean
	 * @return List<OffenderStgAffiliations>
	 */
	public List<OffenderStgAffiliations> agencyIncidentPartiesPostQuery(final OffenderStgAffiliations paramBean) {
		final String sql = getQuery("OIDSTGIN_AGENCY_INCIDENT_PARTIES_POSTQUERY");
		final RowMapper<OffenderStgAffiliations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderStgAffiliations.class, offenderStgAffiliationsMapping);
		List<OffenderStgAffiliations> returnList = new ArrayList<OffenderStgAffiliations>();
		try {

			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("agencyIncidentPartiesPostQuery", e);

		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * populateIncidentLocations
	 *
	 * @param paramBean
	 * @return List<AgencyInternalLocations>
	 */
	public List<AgencyInternalLocations> populateIncidentLocations(final AgencyInternalLocations paramBean) {
		final String sql = getQuery("OIDSTGIN_POPULATE_INCIDENT_LOCATIONS_POPULATE_INCIDENT_LOCATIONS");
		final RowMapper<AgencyInternalLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agencyInternalLocationsMapping);
		List<AgencyInternalLocations> returnList = new ArrayList<AgencyInternalLocations>();
		try {

			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("populateIncidentLocations", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * populateLogNo
	 *
	 * @param paramBean
	 *
	 */
	public List<SystemProfiles> populateLogNo(final SystemProfiles paramBean) {
		final String sql = getQuery("OIDSTGIN_POPULATE_LOG_NO_POPULATE_LOG_NO");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<SystemProfiles>();
		try {

			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("populateLogNo", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * populateStgGroup
	 *
	 * @param paramBean
	 * @return List<VStgSet>
	 *
	 */
	public List<VStgSet> populateStgGroup(final VStgSet paramBean) {
		final String sql = getQuery("OIDSTGIN_POPULATE_STG_GROUP");
		final RowMapper<VStgSet> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VStgSet.class, vStgSetMapping);
		List<VStgSet> returnList = new ArrayList<VStgSet>();
		try {

			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("populateStgGroup", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * populateStgGroup
	 *
	 * @param params
	 *
	 */
	public List<VStgGangSet> populateStgGroup(final VStgGangSet paramBean) {
		final String sql = getQuery("OIDSTGIN_POPULATE_STG_GROUP");
		final RowMapper<VStgGangSet> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VStgGangSet.class,
				vStgGangSetMapping);
		List<VStgGangSet> returnList = new ArrayList<VStgGangSet>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("populateStgGroup", e);
		}

		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * populateStgGroup
	 *
	 * @param params
	 *
	 */
	public List<VStgGroup> populateStgGroup(final VStgGroup paramBean) {
		final String sql = getQuery("OIDSTGIN_POPULATE_STG_GROUP");
		final RowMapper<VStgGroup> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VStgGroup.class,
				vStgGroupMapping);
		List<VStgGroup> returnList = new ArrayList<VStgGroup>();
		try {

			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("populateStgGroup", e);
		}

		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * populateStgGroup
	 *
	 * @param params
	 *
	 */
	public List<SystemProfiles> populateStgGroup(final SystemProfiles paramBean) {
		final String sql = getQuery("OIDSTGIN_POPULATE_STG_GROUP");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<SystemProfiles>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("populateStgGroup", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * populateStaff
	 *
	 * @param params
	 *
	 */
	public List<StaffMembers> populateStaff(final StaffMembers paramBean) {
		final String sql = getQuery("OIDSTGIN_POPULATE_STAFF_POPULATE_STAFF");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		List<StaffMembers> returnList = new ArrayList<StaffMembers>();
		try {

			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("populateStaff", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * tagEstablishMent
	 *
	 * @param agyLocId
	 *
	 */
	public String tagEstablishMent(final String agyLocId) {
		final String sql = getQuery("OIDSTGIN_TAG_ESTABLISHMENT");
		String returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("AGYLOCID", agyLocId), String.class);
		if (returnList != null) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * staffMembers
	 *
	 * @param staffId
	 *
	 */
	public String staffMembers(final Integer staffId) {
		final String sql = getQuery("OIDSTGIN_STAFF_MEMBERS");
		String returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("STAFFID", staffId), String.class);
		if (returnList != null) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getDescription
	 *
	 * @param stgId
	 *
	 */
	public String getDescription(final BigDecimal stgId) {
		final String sql = getQuery("OIDSTGIN_GET_DESCRIPTION");
		String returnList = null;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("STGID", stgId), String.class);
		if (returnList != null) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getInternalLocations
	 *
	 * @param internalLocationId
	 *
	 */
	public AgencyInternalLocations getInternalLocations(final Integer intLocId) {
		final String sql = getQuery("OIDSTGIN_GET_LOCATION");
		final RowMapper<AgencyInternalLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, offendersMapping);
		AgencyInternalLocations returnList = new AgencyInternalLocations();
		try {

			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("INTERNALLOCATIONID", intLocId),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("populateStaff", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getDescriptionStgId
	 *
	 * @param offBookId
	 *
	 */
	public BigDecimal getDescriptionStgId(final BigDecimal stgId, final Integer offBookId) {
		final String sql = getQuery("OIDSTGIN_GET_STG_ID");
		BigDecimal returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("STGID", stgId, "OFFBOOKID", offBookId), BigDecimal.class);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderIdDisplay
	 *
	 * @param ofenderIdDisplay
	 *
	 */
	public String offenderIdDisplay(final BigDecimal ofenderIdDisplay,final String userId) {
		final String sql = getQuery("OIDSTGIN_GET_BOOKING_NO");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("ofenderIdDisplay", ofenderIdDisplay,"USERID",userId),
					String.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getDescriptionStgIdElseCondition
	 *
	 * @param offBookId
	 *
	 */
	public BigDecimal getDescriptionStgIdElseCondition(final Integer offBookId) {
		final String sql = getQuery("OIDSTGIN_GET_STG_ID_ELSE_CONDITION");
		BigDecimal returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID", offBookId),
					BigDecimal.class);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}
}

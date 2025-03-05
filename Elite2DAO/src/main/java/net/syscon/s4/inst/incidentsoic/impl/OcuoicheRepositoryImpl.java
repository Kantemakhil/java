package net.syscon.s4.inst.incidentsoic.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.core.EliteDateRepository;
import net.syscon.s4.im.beans.VOicIncidents;
import net.syscon.s4.im.incidentsoic.beans.OicHearingResults;
import net.syscon.s4.im.incidentsoic.beans.OicHearings;
import net.syscon.s4.inst.incidentsoic.OcuoicheRepository;

/**
 * Class OcuoicheRepositoryImpl
 */
@Repository
public class OcuoicheRepositoryImpl extends RepositoryBase implements OcuoicheRepository {
	
	@Autowired
	private EliteDateRepository dateRepository;
	
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuoicheRepositoryImpl.class.getName());

	/**
	 * Creates new OcuoicheRepositoryImpl class Object
	 */
	public OcuoicheRepositoryImpl() {
	}

	public static final String enterQuery = "ENTER-QUERY";
	private final Map<String, FieldMapper> oicHearingResultsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OIC_HEARING_ID", new FieldMapper("oicHearingId"))
			.put("OIC_HEARING_RESULTS", new FieldMapper("oicHearingResults"))
			.put("RESULT_SEQ", new FieldMapper("resultSeq"))
			.put("AGENCY_INCIDENT_ID", new FieldMapper("agencyIncidentId"))
			.put("CHARGE_SEQ", new FieldMapper("chargeSeq"))
			.put("PLEA_FINDING_CODE", new FieldMapper("pleaFindingCode"))
			.put("FINDING_CODE", new FieldMapper("findingCode"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OIC_OFFENCE_ID", new FieldMapper("oicOffenceId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("OIC_RESULT_OFFENCE_ID", new FieldMapper("oicResultOffenceId"))
			.build();
	private final Map<String, FieldMapper> oicHearingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("HEARING_DATE", new FieldMapper("hearingDate"))
			.put("REPRESENTATIVE_TEXT", new FieldMapper("representativeText"))
			.put("AGENCY_INCIDENT_ID", new FieldMapper("agencyIncidentId"))
			.put("TAPE_NUMBER", new FieldMapper("tapeNumber"))
			.put("OIC_OFFENCE_ID", new FieldMapper("oicOffenceId"))
			.put("SCHEDULE_TIME", new FieldMapper("scheduleTime"))
			.put("CHARGE_SEQ", new FieldMapper("chargeSeq"))
			.put("OIC_RESULT_OFFENCE_ID", new FieldMapper("oicResultOffenceId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("OIC_HEARING_TYPE", new FieldMapper("oicHearingType"))
			.put("EVENT_STATUS", new FieldMapper("eventStatus"))
			.put("OIC_INCIDENT_ID", new FieldMapper("oicIncidentId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("COMMENT_TEXT", new FieldMapper("commentText"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("SCHEDULE_DATE", new FieldMapper("scheduleDate"))
			.put("HEARING_TIME", new FieldMapper("hearingTime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("PLEA_FINDING_CODE", new FieldMapper("pleaFindingCode"))
			.put("EVENT_ID", new FieldMapper("eventId"))
			.put("FINDING_CODE", new FieldMapper("findingCode"))
			.put("RESULT_SEQ", new FieldMapper("resultSeq"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("VISIT_JUSTICE_TEXT", new FieldMapper("visitJusticeText"))
			.put("OIC_HEARING_ID", new FieldMapper("oicHearingId"))
			.put("HEARING_STAFF_ID", new FieldMapper("hearingStaffId"))
			.build();
	private final Map<String, FieldMapper> caseloadAgencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("CASELOAD_AGENCY_LOCATIONS", new FieldMapper("CaseloadAgencyLocations"))
			.build();

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("AGENCY_INCIDENT_ID", new FieldMapper("agencyIncidentId"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("PARTY_SEQ", new FieldMapper("partySeq"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code"))
			.put("DOMAIN", new FieldMapper("domain"))
			.put("PARENT_DOMAIN", new FieldMapper("parentDomainId"))
			.put("PARENT_CODE", new FieldMapper("parentCode"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("DSP_NAME", new FieldMapper("dspName"))
			.build();
	private final Map<String, FieldMapper> oicOffencesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OIC_OFFENCE_CODE", new FieldMapper("oicOffenceCode"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("UPDATE_ALLOWED_FLAG", new FieldMapper("updateAllowedFlag"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OIC_OFFENCE_CATEGORY", new FieldMapper("oicOffenceCategory"))
			.put("OIC_OFFENCE_TYPE", new FieldMapper("oicOffenceType"))
			.put("MAX_PENALTY_MONTHS", new FieldMapper("maxPenaltyMonths"))
			.put("MAX_PENALTY_DAYS", new FieldMapper("maxPenaltyDays"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("OIC_OFFENCE_ID", new FieldMapper("oicOffenceId"))
			.put("START_DATE", new FieldMapper("startDate"))
			.put("END_DATE", new FieldMapper("endDate"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CHARGE_SEQ", new FieldMapper("chargeSeq"))
			.build();

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<RecordGroup>
	 */
	public List<ReferenceCodes> rgOffenceCodeRecordGroup() {
		final String sql = getQuery("OCUOICHE_FIND_RGOFFENCECODE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("MODE", enterQuery), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @param caseloadId
	 * @return List<RecordGroup>
	 */
	public List<StaffMembers> rgAgyIncpStaffIdRecordGroup(final StaffMembers caseloadId) {
		final String sql = getQuery("OCUOICHE_FIND_RGAGYINCPSTAFFID");
		final RowMapper<StaffMembers> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				referenceCodesMapping);
		List<StaffMembers> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseloadId.getWorkingCaseloadId(), "MODE", enterQuery),
					referenceCodeRowMapper);
			return returnList;
		} catch (Exception e) {
			logger.error("", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<RecordGroup>
	 */
	public List<ReferenceCodes> rgHearingTypeRecordGroup() {
		final String sql = getQuery("OCUOICHE_FIND_RGHEARINGTYPE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("MODE", enterQuery), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @param caseloadId
	 * @return List<RecordGroup>
	 */
	public List<AgencyInternalLocations> rgInternalLocationsRecordGroup(final String caseloadId) {
		final String sql = getQuery("OCUOICHE_FIND_RGINTERNALLOCATIONS");
		final RowMapper<AgencyInternalLocations> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("MODE", enterQuery, "CASELOADID", caseloadId),
					referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @param partySeq
	 * @param agencyIncidentId,partySeq
	 * @return List<RecordGroup>
	 */
	public List<OicOffences> rgIncidentChargesRecordGroup(final VOicIncidents searchBean) {
		final String sql = getQuery("OCUOICHE_FIND_RGINCIDENTCHARGES");
		final RowMapper<OicOffences> oicOffencesRowMapper = Row2BeanRowMapper.makeMapping(sql, OicOffences.class,
				oicOffencesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("agencyIncidentId", searchBean.getAgencyIncidentId(), "partySeq",
							searchBean.getPartySeq(), "INCIDENTDATE", searchBean.getIncidentDate()),
					oicOffencesRowMapper);
		} catch (Exception e) {
			logger.error("", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<RecordGroup>
	 */
	public List<ReferenceCodes> rgFindingRecordGroup() {
		final String sql = getQuery("OCUOICHE_FIND_RGFINDING");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("MODE", enterQuery), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<RecordGroup>
	 */
	public List<ReferenceCodes> rgPleaRecordGroup() {
		final String sql = getQuery("OCUOICHE_FIND_RGPLEA");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("MODE", enterQuery), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param objSearchDao
	 * @return List<OicHearings>
	 * @throws SQLException
	 */
	public List<OicHearings> oicHearSearchOicHearings(final OicHearings objSearchDao) {
		final String sql = getQuery("OCUOICHE_OICHEAR_FIND_OIC_HEARINGS");
		final RowMapper<OicHearings> OicHearingsRowMapper = Row2BeanRowMapper.makeMapping(sql, OicHearings.class,
				oicHearingsMapping);
		List<OicHearings> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("OIC_INCIDENT_ID",
				objSearchDao.getOicIncidentId(), "HEARING_DATE", objSearchDao.getHearingDate()), OicHearingsRowMapper);
		return returnList;
	}

	/**
	 * @param
	 * @throws SQLException
	 */

	public Integer preInsert() {
		final String sql = getQuery("OIC_HEARING_ID_PREINSERTC_SQL");
		Integer returnList = 0;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 * 
	 * @param lstOicHearings
	 *            List<OicHearings>
	 * @return List<Integer>
	 * @throws SQLException
	 */
	public Integer oicHearInsertOicHearings(final List<OicHearings> lstOicHearings) {
		final String sql = getQuery("OCUOICHE_OICHEAR_INSERT_OIC_HEARINGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OicHearings oicHearings : lstOicHearings) {
			oicHearings.setInternalLocationId(Integer.valueOf(oicHearings.getInternalLocationIdDes()));
			parameters.add(new BeanPropertySqlParameterSource(oicHearings));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOicHearings.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 * 
	 * @param List<OicHearings>
	 *            lstOicHearings
	 * @throws SQLException
	 */
	public Integer oicHearUpdateOicHearings(final List<OicHearings> lstOicHearings) {
		final String sql = getQuery("OCUOICHE_OICHEAR_UPDATE_OIC_HEARINGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OicHearings oicHearings : lstOicHearings) {
			oicHearings.setCreateDateTime(dateRepository.getDBTime());
			oicHearings.setModifyDateTime(dateRepository.getDBTime());
			oicHearings.setInternalLocationId(Integer.valueOf(oicHearings.getInternalLocationIdDes()));
			parameters.add(new BeanPropertySqlParameterSource(oicHearings));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOicHearings.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 * 
	 * @param List<OicHearings>
	 *            lstOicHearings
	 * @throws SQLException
	 */
	public Integer oicHearDeleteOicHearings(final List<OicHearings> lstOicHearings) {
		final String sql = getQuery("OCUOICHE_OICHEAR_DELETE_OIC_HEARINGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OicHearings oicHearings : lstOicHearings) {
			parameters.add(new BeanPropertySqlParameterSource(oicHearings));
		}
		try {
			String tableName = "OIC_HEARINGS";
			String whereClause = "OIC_HEARING_ID = :OicHearingId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method oicHearDeleteOicHearings", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOicHearings.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param objSearchDao
	 *            OicHearingResults
	 * @return List<OicHearingResults>
	 * @throws SQLException
	 */
	public List<OicHearingResults> oicHearResSearchOicHearingResults(final OicHearingResults objSearchDao) {
		final String sql = getQuery("OCUOICHE_OICHEARRES_FIND_OIC_HEARING_RESULTS");
		List<OicHearingResults> refList = new ArrayList<>();
		final RowMapper<OicHearingResults> OicHearingResultsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OicHearingResults.class, oicHearingResultsMapping);
		refList = namedParameterJdbcTemplate.query(sql, createParams("OIC_HEARING_ID", objSearchDao.getOicHearingId()),
				OicHearingResultsRowMapper);
		return refList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * ocuoicchAgyInciChgPostQuery
	 *
	 * @param params
	 *
	 */
	public OicOffences agyInciChgPostQuery(final OicOffences paramBean) {
		final String sql = getQuery("OCUOICCH_AGY_INCI_CHG_POSTQUERY");
		final RowMapper<OicOffences> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OicOffences.class,
				oicOffencesMapping);
		OicOffences returnObj = new OicOffences();
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OIC_OFFENCE_ID", paramBean.getOicOffenceId()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 * 
	 * @param lstOicHearingResults
	 *            List<OicHearingResults>
	 * @return List<Integer>
	 * @throws SQLException
	 */
	public Integer oicHearResInsertOicHearingResults(final List<OicHearingResults> lstOicHearingResults) {
		Integer resultSeq = 0;
		for (Integer i = 0; i < lstOicHearingResults.size(); i++) {
			if (i == 0) {
				resultSeq = oicHaringResultsPreInsert(lstOicHearingResults.get(i).getOicHearingId());
			}
			lstOicHearingResults.get(i).setResultSeq(BigDecimal.valueOf(resultSeq));
			resultSeq = resultSeq + 1;
		}
		final String sql = getQuery("OCUOICHE_OICHEARRES_INSERT_OIC_HEARING_RESULTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OicHearingResults oicHearingResults : lstOicHearingResults) {
			parameters.add(new BeanPropertySqlParameterSource(oicHearingResults));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOicHearingResults.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 * 
	 * @param lstOicHearingResults
	 *            List<OicHearingResults>
	 * @throws SQLException
	 */
	public Integer oicHearResUpdateOicHearingResults(final List<OicHearingResults> lstOicHearingResults) {
		final String sql = getQuery("OCUOICHE_OICHEARRES_UPDATE_OIC_HEARING_RESULTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OicHearingResults oicHearingResults : lstOicHearingResults) {
			parameters.add(new BeanPropertySqlParameterSource(oicHearingResults));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOicHearingResults.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 * 
	 * @param lstOicHearingResults
	 *            List<OicHearingResults>
	 * @throws SQLException
	 */
	public Integer oicHearResDeleteOicHearingResults(final List<OicHearingResults> lstOicHearingResults) {
		final String sql = getQuery("OCUOICHE_OICHEARRES_DELETE_OIC_HEARING_RESULTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OicHearingResults oicHearingResults : lstOicHearingResults) {
			parameters.add(new BeanPropertySqlParameterSource(oicHearingResults));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOicHearingResults.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * ocuoicheOicHearPreQuery
	 *
	 * @param params
	 *
	 */
	public CaseloadAgencyLocations oicHearPreQuery(final CaseloadAgencyLocations paramBean) {
		final String sql = getQuery("OCUOICHE_OIC_HEAR_PREQUERY");
		final RowMapper<CaseloadAgencyLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadAgencyLocations.class, caseloadAgencyLocationsMapping);
		CaseloadAgencyLocations returnObj = new CaseloadAgencyLocations();
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("CASELOADID", paramBean.getCaseloadId()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param oichearingid
	 */
	public List<Object> oicHearOnCheckDeleteMasteroicHearResCur(final String oichearingid) {
		final String sql = getQuery("OCUOICHE_OIC_HEAR_ONCHECKDELETEMASTER_OIC_HEAR_RES_CUR");
		List<Object> returnList = new ArrayList<Object>();
		try {
			returnList = namedParameterJdbcTemplate.queryForList(sql, createParams("OICHEARINGID", oichearingid),
					Object.class);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param OicHearingId
	 * @param ResultSeq
	 */
	public List<Object> ocuoicheKeyDelrecoicSancCur(final String OicHearingId, final String ResultSeq) {
		final String sql = getQuery("OCUOICHE_OCUOICHE_KEYDELREC_OIC_SANC_CUR");
		List<Object> returnList = new ArrayList<Object>();
		try {
			returnList = namedParameterJdbcTemplate.queryForList(sql,
					createParams("OICHEARINGID", OicHearingId, "RESULTSEQ", ResultSeq), Object.class);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;

	}

	public Integer oicHaringResultsPreInsert(final BigDecimal oicHearingId) {
		final String sql = getQuery("OIC_HEARING_RESULTS_PREINSERTC_SQL");
		Integer returnList = 0;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("oicHearingId", oicHearingId),
				Integer.class);
		return returnList;
	}

	public StaffMembers getLastNameOfStaffId(final StaffMembers staffMember) {
		final String sql = getQuery("OCUOICHE_FIND_LASTNAME");
		final RowMapper<StaffMembers> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				referenceCodesMapping);
		StaffMembers returnList = new StaffMembers();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("STAFF_ID", staffMember.getStaffId()), referenceCodeRowMapper);

		} catch (Exception e) {
			logger.error("", e);
			returnList = new StaffMembers();
		}
		return returnList;
	}
}

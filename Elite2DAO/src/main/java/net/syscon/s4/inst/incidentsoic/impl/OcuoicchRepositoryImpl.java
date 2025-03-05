package net.syscon.s4.inst.incidentsoic.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentCharges;
import net.syscon.s4.im.incidentsoic.beans.OicHearingResults;
import net.syscon.s4.inst.incidentsoic.OcuoicchRepository;
/**
 * Class OcuoicchRepositoryImpl
 */
@Repository
public class OcuoicchRepositoryImpl extends RepositoryBase implements OcuoicchRepository{
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuoicchRepositoryImpl.class);
	
	private final Map<String, FieldMapper> agencyIncidentChargesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("AGENCY_INCIDENT_ID", new FieldMapper("agencyIncidentId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("FINDING_CODE", new FieldMapper("findingCode"))
			.put("GUILTY_EVIDENCE_TEXT", new FieldMapper("guiltyEvidenceText"))
			.put("EVIDENCE_DISPOSE_TEXT", new FieldMapper("evidenceDisposeText"))
			.put("LIDS_CHARGE_NUMBER", new FieldMapper("lidsChargeNumber"))
			.put("REPORT_TEXT", new FieldMapper("reportText"))
			.put("CHARGE_SEQ", new FieldMapper("chargeSeq"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("PARTY_SEQ", new FieldMapper("partySeq"))
			.put("CHARGED_OIC_OFFENCE_ID", new FieldMapper("chargedOicOffenceId"))
			.put("OIC_CHARGE_ID", new FieldMapper("oicChargeId"))
			.put("RESULT_OIC_OFFENCE_ID", new FieldMapper("resultOicOffenceId"))
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
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("OIC_OFFENCE_ID", new FieldMapper("oicOffenceId"))
			.put("START_DATE", new FieldMapper("startDate"))
			.put("END_DATE", new FieldMapper("endDate"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();
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
	/**
	 * Creates new OcuoicchRepositoryImpl class Object
	 */
	public OcuoicchRepositoryImpl() {
		
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<RecordGroup>
	 */
	public List<OicOffences> rgOffenceCodeRecordGroup(final String mode, final Date incidentDate) {
		final String sql = getQuery("OCUOICCH_FIND_RGOFFENCECODE");
		final RowMapper<OicOffences> oicOffencesCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, OicOffences.class,
				oicOffencesMapping);
		ArrayList<OicOffences> refList = new ArrayList<>();
		try {
			refList = (ArrayList<OicOffences>) namedParameterJdbcTemplate.query(sql,
					createParams("MODE", mode, "INCIDENTDATE", incidentDate), oicOffencesCodeRowMapper);
		} catch (Exception e) {
			logger.error("In this method rgOffenceCodeRecordGroup", e);
		}
		return refList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            AgencyIncidentCharges
	 *
	 * @return List<AgencyIncidentCharges>
	 *
	 * @
	 */
	public List<AgencyIncidentCharges> agyInciChgExecuteQuery(final AgencyIncidentCharges objSearchDao) {
		final String sql = getQuery("OCUOICCH_AGYINCICHG_FIND_AGENCY_INCIDENT_CHARGES");
		final RowMapper<AgencyIncidentCharges> AgencyIncidentChargesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyIncidentCharges.class, agencyIncidentChargesMapping);
		 ArrayList<AgencyIncidentCharges> returnList = (ArrayList<AgencyIncidentCharges>) namedParameterJdbcTemplate
				.query(sql, createParams("AGENCY_INCIDENT_ID", objSearchDao.getAgencyIncidentId(),"PARTY_SEQ",objSearchDao.getPartySeq()),
						AgencyIncidentChargesRowMapper);
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
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstAgencyIncidentCharges
	 *            List<AgencyIncidentCharges>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer agyInciChgInsertAgencyIncidentCharges(final List<AgencyIncidentCharges> lstAgencyIncidentCharges) {
		final String sql = getQuery("OCUOICCH_AGYINCICHG_INSERT_AGENCY_INCIDENT_CHARGES");
		int returnArray = 0;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyIncidentCharges agencyIncidentCharges : lstAgencyIncidentCharges) {
			parameters.add(new BeanPropertySqlParameterSource(agencyIncidentCharges));
			returnArray = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(agencyIncidentCharges));
		}
		
		if (returnArray == 1 ) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAgencyIncidentCharges
	 *            List<AgencyIncidentCharges>
	 *
	 * @
	 */
	public Integer agyInciChgUpdateAgencyIncidentCharges(final List<AgencyIncidentCharges> lstAgencyIncidentCharges) {
		final String sql = getQuery("OCUOICCH_AGYINCICHG_UPDATE_AGENCY_INCIDENT_CHARGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyIncidentCharges agencyIncidentCharges : lstAgencyIncidentCharges) {
			parameters.add(new BeanPropertySqlParameterSource(agencyIncidentCharges));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgencyIncidentCharges.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAgencyIncidentCharges
	 *            List<AgencyIncidentCharges>
	 *
	 * @
	 */
	public Integer agyInciChgDeleteAgencyIncidentCharges(final List<AgencyIncidentCharges> lstAgencyIncidentCharges) {
		final String sql = getQuery("OCUOICCH_AGYINCICHG_DELETE_AGENCY_INCIDENT_CHARGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgencyIncidentCharges agencyIncidentCharges : lstAgencyIncidentCharges) {
			parameters.add(new BeanPropertySqlParameterSource(agencyIncidentCharges));
		}
		try {
			String tableName = "AGENCY_INCIDENT_CHARGES";
			String whereClause = "AGENCY_INCIDENT_ID  = :agencyIncidentId and CHARGE_SEQ  = :chargeSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method agyInciChgDeleteAgencyIncidentCharges", e);
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			return 2;
		}
		if (lstAgencyIncidentCharges.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

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
		final String sql = getQuery("OCUOICCH_AGY_INCIDENT_CHG_POSTQUERY_NEW");
		final RowMapper<OicOffences> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OicOffences.class,
				oicOffencesMapping);
		OicOffences returnObj = (OicOffences) namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OIC_OFFENCE_ID", paramBean.getOicOffenceId()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return Object
	 */
	public Integer preInsertgetChargeSeqId(final AgencyIncidentCharges bean) {
		final String sql = getQuery("OCDADDRE_AGY_INCI_CHG_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("AGENCY_INCIDENT_ID", bean.getAgencyIncidentId()), Integer.class);
	}
	
	public List<OicHearingResults> oichearingSearchResults(final OicHearingResults objOicHearingResults) {
		final String sql = getQuery("OCUOICCH_DELETE_AGENCY_INCIDENT_CHARGES_PREQUERY");
		List<OicHearingResults> refList = new ArrayList<>();
		final RowMapper<OicHearingResults> OicHearingResultsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OicHearingResults.class, oicHearingResultsMapping);
		refList = namedParameterJdbcTemplate.query(sql, createParams("AGENCY_INCIDENT_ID", objOicHearingResults.getAgencyIncidentId(),
				"CHARGE_SEQ",objOicHearingResults.getChargeSeq()),
				OicHearingResultsRowMapper);
		return refList;
	}
}

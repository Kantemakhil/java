package net.syscon.s4.inst.incidentsoic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.SystemProfiles;
import net.syscon.s4.im.incidentsoic.beans.VOffenderOicSanctions;
import net.syscon.s4.im.incidentsoic.beans.VOicHearingResults;
import net.syscon.s4.im.incidentsoic.beans.VOicHearings;
import net.syscon.s4.im.incidentsoic.beans.VOicIncidents;
import net.syscon.s4.inst.incidentsoic.OiioicusRepository;

/**
 * class OiioicusRepositoryImpl
 */
@Repository
public class OiioicusRepositoryImpl extends RepositoryBase implements OiioicusRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiioicusRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> vOffenderOicSanctionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OIC_HEARING_ID", new FieldMapper("oicHearingId")).put("RESULT_SEQ", new FieldMapper("resultSeq"))
			.build();
	private final Map<String, FieldMapper> vOicHearingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OIC_INCIDENT_ID", new FieldMapper("oicIncidentId")).build();
	private final Map<String, FieldMapper> vOicIncidentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).build();
	private final Map<String, FieldMapper> vOicHearingResultsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("REPORTED_STAFF_ID", new FieldMapper("reportedStaffId"))
			.put("RESULT_OIC_OFFENCE_CODE", new FieldMapper("resultOicOffenceCode"))
			.put("HEARING_DATE", new FieldMapper("hearingDate"))
			.put("OIC_CHARGE_FLAG", new FieldMapper("oicChargeFlag"))
			.put("OIC_OFFENCE_CATEGORY", new FieldMapper("oicOffenceCategory"))
			.put("AGENCY_INCIDENT_ID", new FieldMapper("agencyIncidentId"))
			.put("REPRESENTATIVE_TEXT", new FieldMapper("representativeText"))
			.put("OIC_OFFENCE_CODE", new FieldMapper("oicOffenceCode"))
			.put("SANCTION_SEQ", new FieldMapper("sanctionSeq"))
			.put("RESULT_OIC_OFFENCE_CATEGORY", new FieldMapper("resultOicOffenceCategory"))
			.put("FINDING_DESCRIPTION", new FieldMapper("findingDescription"))
			.put("COMPENSATION_AMOUNT", new FieldMapper("compensationAmount"))
			.put("SANCTION_MONTHS", new FieldMapper("sanctionMonths"))
			.put("OIC_HEARING_TYPE", new FieldMapper("oicHearingType"))
			.put("OIC_INCIDENT_ID", new FieldMapper("oicIncidentId"))
			.put("EFFECTIVE_DATE", new FieldMapper("effectiveDate"))
			.put("INCIDENT_TYPE", new FieldMapper("incidentType")).put("PARTY_SEQ", new FieldMapper("partySeq"))
			.put("CONSECUTIVE_SANCTION_SEQ", new FieldMapper("consecutiveSanctionSeq"))
			.put("PLEA_DESCRIPTION", new FieldMapper("pleaDescription"))
			.put("OIC_CHARGE_ID", new FieldMapper("oicChargeId")).put("INCIDENT_TIME", new FieldMapper("incidentTime"))
			.put("INCIDENT_DETAILS", new FieldMapper("incidentDetails"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("HEARING_STAFF_NAME", new FieldMapper("hearingStaffName"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("SANCTION_DAYS", new FieldMapper("sanctionDays"))
			.put("HEARING_TIME", new FieldMapper("hearingTime")).put("REPORT_DATE", new FieldMapper("reportDate"))
			.put("OIC_SANCTION_CODE", new FieldMapper("oicSanctionCode"))
			.put("STATUS_DESCRIPTION", new FieldMapper("statusDescription"))
			.put("OIC_OFFENCE_DESCRIPTION", new FieldMapper("oicOffenceDescription"))
			.put("OIC_SANCTION_DESC", new FieldMapper("oicSanctionDesc"))
			.put("RESULT_OIC_OFFENCE_DESCRIPTION", new FieldMapper("resultOicOffenceDescription"))
			.put("RESULT_OIC_OFN_TYPE_DESC", new FieldMapper("resultOicOfnTypeDesc"))
			.put("INCIDENT_DATE", new FieldMapper("incidentDate")).put("RESULT_SEQ", new FieldMapper("resultSeq"))
			.put("INCIDENT_TYPE_DESC", new FieldMapper("incidentTypeDesc"))
			.put("INT_LOC_DESCRIPTION", new FieldMapper("intLocDescription"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("BOOKING_NO", new FieldMapper("bookingNo"))
			.put("OIC_OFN_TYPE_DESC", new FieldMapper("oicOfnTypeDesc"))
			.put("OIC_HEARING_TYPE_DESC", new FieldMapper("oicHearingTypeDesc"))
			.put("OIC_OFFENCE_TYPE", new FieldMapper("oicOffenceType"))
			.put("STATUS_DATE", new FieldMapper("statusDate")).put("OIC_HEARING_ID", new FieldMapper("oicHearingId"))
			.put("RESULT_OFFENCE_TYPE", new FieldMapper("resultOffenceType")).build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2")).build();
	public final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DOMAIN", new FieldMapper("domain")).put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description")).put("ACTIVE_FLAG", new FieldMapper("active_flag"))
			.put("LIST_SEQ", new FieldMapper("list_seq")).put("SYSTEM_DATA_FLAG", new FieldMapper("systemDataFlag"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("EXPIRED_DATE", new FieldMapper("expiredDate"))
			.put("NEW_CODE", new FieldMapper("newCode")).put("PARENT_CODE", new FieldMapper("parentCode"))
			.put("PARENT_DOMAIN", new FieldMapper("parentDomain"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("OIC_SANCTION_DESC", new FieldMapper("oicSanctionDesc"))
			.put("INCIDENT_TYPE_DESC", new FieldMapper("incidentTypeDesc"))
			.put("INCIDENT_TYPE", new FieldMapper("incidentType"))
			.put("OIC_HEARING_TYPE_DESC", new FieldMapper("oicHearingTypeDesc"))
			.put("OIC_OFFENCE_TYPE", new FieldMapper("oicOffenceType"))
			.put("OIC_OFFENCE_DESCRIPTION", new FieldMapper("oicOffenceDescription"))
			.put("OIC_HEARING_TYPE", new FieldMapper("oicHearingType")).build();

	/**
	 * Creates new OiioicusRepositoryImpl class Object
	 */
	public OiioicusRepositoryImpl() {
		
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgOicHearingTypeRecordGroup() {
		final String sql = getQuery("OIIOICUS_FIND_RGOICHEARINGTYPE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> listObj = new ArrayList<>();
		try {
			listObj = namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return listObj;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgIncidentTypeRecordGroup() {
		final String sql = getQuery("OIIOICUS_FIND_RGINCIDENTTYPE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> listObj = new ArrayList<>();
		try {
			listObj = namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error(e);
			;
		}
		return listObj;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 * @param oicOffenceObj
	 */
	public List<ReferenceCodes> rgOffenceTypeRecordGroup(final String date) {
		final String sql = getQuery("OIIOICUS_FIND_RGOFFENCETYPE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> listObj = new ArrayList<>();
		try {
			listObj = namedParameterJdbcTemplate.query(sql, createParams("INCIDENTDATE", date), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return listObj;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgSanctionCodeRecordGroup() {
		final String sql = getQuery("OIIOICUS_FIND_RGSANCTIONCODE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> listObj = new ArrayList<>();
		try {
			listObj = namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return listObj;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOicIncidents
	 *
	 * @return List<VOicIncidents>
	 */
	public List<VOicIncidents> vOicInciSearchVOicIncidents(final VOicIncidents objSearchDao) {
		final String sql = getQuery("OIIOICUS_VOICINCI_FIND_V_OIC_INCIDENTS");
		String preparedSql;
		final StringBuilder sqlQuery = new StringBuilder();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getOffenderBookId() != null) {
				sqlQuery.append("OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID" + " and ");
				params.addValue("OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId());
			}
			if (objSearchDao.getReportedStaffId() != null) {
				sqlQuery.append("REPORTED_STAFF_ID = :REPORTED_STAFF_ID" + " and ");
				params.addValue("REPORTED_STAFF_ID ", objSearchDao.getReportedStaffId());
			}
			if (objSearchDao.getBookingNo() != null) {
				sqlQuery.append("BOOKING_NO =:BOOKING_NO" + " and ");
				params.addValue("BOOKING_NO ", objSearchDao.getBookingNo());
			}
			if (objSearchDao.getAgencyIncidentId() != null) {
				sqlQuery.append("AGENCY_INCIDENT_ID = :AGENCY_INCIDENT_ID" + " and ");
				params.addValue("AGENCY_INCIDENT_ID", objSearchDao.getAgencyIncidentId());
			}
			if (objSearchDao.getPartySeq() != null) {
				sqlQuery.append("PARTY_SEQ = :PARTY_SEQ" + " and ");
				params.addValue("PARTY_SEQ", objSearchDao.getPartySeq());
			}
			if (objSearchDao.getIncidentDate() != null) {
				sqlQuery.append("INCIDENT_DATE = :INCIDENT_DATE" + " and ");
				params.addValue("INCIDENT_DATE", objSearchDao.getIncidentDate());
			}
			if (objSearchDao.getIntLocDescription() != null) {
				sqlQuery.append("INT_LOC_DESCRIPTION = :INT_LOC_DESCRIPTION" + " and ");
				params.addValue("INT_LOC_DESCRIPTION", objSearchDao.getIntLocDescription());
			}
			if (objSearchDao.getIncidentTime() != null) {
				sqlQuery.append("INCIDENT_TIME = :INCIDENT_TIME" + " and ");
				params.addValue("INCIDENT_TIME ", objSearchDao.getIncidentTime());
			}
			if (objSearchDao.getIncidentType() != null) {
				sqlQuery.append("INCIDENT_TYPE = :INCIDENT_TYPE" + " and ");
				params.addValue("INCIDENT_TYPE ", objSearchDao.getIncidentType());
			}
			if (objSearchDao.getIncidentTypeDesc() != null) {
				sqlQuery.append("INCIDENT_TYPE_DESC = :INCIDENT_TYPE_DESC" + " and ");
				params.addValue("INCIDENT_TYPE_DESC ", objSearchDao.getIncidentTypeDesc());
			}
			if (objSearchDao.getIncidentDetails() != null) {
				sqlQuery.append("INCIDENT_DETAILS = :INCIDENT_DETAILS" + " and ");
				params.addValue("INCIDENT_DETAILS", objSearchDao.getIncidentDetails());
			}
			if (objSearchDao.getReportDate() != null) {
				sqlQuery.append("REPORT_DATE = :REPORT_DATE" + " and ");
				params.addValue("REPORT_DATE ", objSearchDao.getReportDate());
			}
			if (objSearchDao.getAgyLocId() != null) {
				sqlQuery.append("AGY_LOC_ID = :AGY_LOC_ID" + " and ");
				params.addValue("AGY_LOC_ID ", objSearchDao.getAgyLocId());
			}
			if (objSearchDao.getOicIncidentId() != null) {
				sqlQuery.append("OIC_INCIDENT_ID = :OIC_INCIDENT_ID" + " and ");
				params.addValue("OIC_INCIDENT_ID ", objSearchDao.getOicIncidentId());
			}
			if (objSearchDao.getOicChargeFlag() != null) {
				sqlQuery.append("OIC_CHARGE_FLAG = :OIC_CHARGE_FLAG");
				params.addValue("OIC_CHARGE_FLAG ", objSearchDao.getOicChargeFlag());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" order by  AGENCY_INCIDENT_ID desc ");
		final RowMapper<VOicIncidents> VOicIncidentsRowMapper = Row2BeanRowMapper.makeMapping(sql, VOicIncidents.class,
				vOicIncidentsMapping);
		final ArrayList<VOicIncidents> returnList = (ArrayList<VOicIncidents>) namedParameterJdbcTemplate
				.query(preparedSql, params, VOicIncidentsRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOicHearings
	 *
	 * @return List<VOicHearings>
	 *
	 */
	public List<VOicHearings> vOicHearSearchVOicHearings(final VOicHearings objSearchDao) {
		final String sql = getQuery("OIIOICUS_VOICHEAR_FIND_V_OIC_HEARINGS");
		String preparedSql;
		final StringBuilder sqlQuery = new StringBuilder();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getOicHearingId() != null) {
				sqlQuery.append("OIC_HEARING_ID = :OIC_HEARING_ID" + " and ");
				params.addValue("OIC_HEARING_ID", objSearchDao.getOicHearingId());
			}
			if (objSearchDao.getOicHearingType() != null) {
				sqlQuery.append("OIC_HEARING_TYPE = :OIC_HEARING_TYPE" + " and ");
				params.addValue("OIC_HEARING_TYPE", objSearchDao.getOicHearingType());
			}
			if (objSearchDao.getOicHearingTypeDesc() != null) {
				sqlQuery.append("OIC_HEARING_TYPE_DESC = :OIC_HEARING_TYPE_DESC" + " and ");
				params.addValue("OIC_HEARING_TYPE_DESC", objSearchDao.getOicHearingTypeDesc());
			}
			if (objSearchDao.getHearingDate() != null) {
				sqlQuery.append("HEARING_DATE = :HEARING_DATE" + " and ");
				params.addValue("HEARING_DATE", objSearchDao.getHearingDate());
			}
			if (objSearchDao.getHearingTime() != null) {
				sqlQuery.append("HEARING_TIME = :HEARING_TIME" + " and ");
				params.addValue("HEARING_TIME", objSearchDao.getHearingTime());
			}
			if (objSearchDao.getHearingStaffName() != null) {
				sqlQuery.append("HEARING_STAFF_NAME = :HEARING_STAFF_NAME" + " and ");
				params.addValue("HEARING_STAFF_NAME", objSearchDao.getHearingStaffName());
			}
			if (objSearchDao.getCommentText() != null) {
				sqlQuery.append("COMMENT_TEXT = :COMMENT_TEXT" + " and ");
				params.addValue("COMMENT_TEXT", objSearchDao.getCommentText());
			}
			if (objSearchDao.getIntLocDescription() != null) {
				sqlQuery.append("INT_LOC_DESCRIPTION = :INT_LOC_DESCRIPTION" + " and ");
				params.addValue("INT_LOC_DESCRIPTION", objSearchDao.getIntLocDescription());
			}
			if (objSearchDao.getRepresentativeText() != null) {
				sqlQuery.append("REPRESENTATIVE_TEXT = :REPRESENTATIVE_TEXT" + " and ");
				params.addValue("REPRESENTATIVE_TEXT", objSearchDao.getRepresentativeText());
			}
			if (objSearchDao.getOicIncidentId() != null) {
				sqlQuery.append("OIC_INCIDENT_ID = :OIC_INCIDENT_ID");
				params.addValue("OIC_INCIDENT_ID", objSearchDao.getOicIncidentId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" order by  HEARING_DATE desc, HEARING_TIME desc ");
		final RowMapper<VOicHearings> VOicHearingsRowMapper = Row2BeanRowMapper.makeMapping(sql, VOicHearings.class,
				vOicHearingsMapping);
		final ArrayList<VOicHearings> returnList = (ArrayList<VOicHearings>) namedParameterJdbcTemplate
				.query(preparedSql, params, VOicHearingsRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOicHearingResults
	 *
	 * @return List<VOicHearingResults>
	 *
	 */
	public List<VOicHearingResults> vOicHearResSearchVOicHearingResults(final VOicHearingResults objSearchDao) {
		final String sql = getQuery("OIIOICUS_VOICHEARRES_FIND_V_OIC_HEARING_RESULTS");
		String preparedSql;
		final StringBuilder sqlQuery = new StringBuilder();
		final MapSqlParameterSource param = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getOicHearingId() != null) {
				sqlQuery.append("OIC_HEARING_ID = :OIC_HEARING_ID" + " and ");
				param.addValue("OIC_HEARING_ID", objSearchDao.getOicHearingId());
			}
			if (objSearchDao.getResultSeq() != null) {
				sqlQuery.append("RESULT_SEQ = :RESULT_SEQ" + " and ");
				param.addValue("RESULT_SEQ", objSearchDao.getResultSeq());
			}
			if (objSearchDao.getOicChargeId() != null) {
				sqlQuery.append("OIC_CHARGE_ID = :OIC_CHARGE_ID" + " and ");
				param.addValue("OIC_CHARGE_ID", objSearchDao.getOicChargeId());
			}
			if (objSearchDao.getOicOffenceCategory() != null) {
				sqlQuery.append("OIC_OFFENCE_CATEGORY = :OIC_OFFENCE_CATEGORY" + " and ");
				param.addValue("OIC_OFFENCE_CATEGORY", objSearchDao.getOicOffenceCategory());
			}
			if (objSearchDao.getOicOffenceCode() != null) {
				sqlQuery.append("OIC_OFFENCE_CODE = :OIC_OFFENCE_CODE" + " and ");
				param.addValue("OIC_OFFENCE_CODE", objSearchDao.getOicOffenceCode());
			}
			if (objSearchDao.getOicOffenceType() != null) {
				sqlQuery.append("OIC_OFFENCE_CODE = :OIC_OFFENCE_CODE" + " and ");
				param.addValue("OIC_OFFENCE_CODE", objSearchDao.getOicOffenceType());
			}
			if (objSearchDao.getOicOffenceDescription() != null) {
				sqlQuery.append("OIC_OFFENCE_DESCRIPTION = :OIC_OFFENCE_DESCRIPTION" + " and ");
				param.addValue("OIC_OFFENCE_DESCRIPTION", objSearchDao.getOicOffenceDescription());
			}
			if (objSearchDao.getPleaDescription() != null) {
				sqlQuery.append("PLEA_DESCRIPTION = :PLEA_DESCRIPTION" + " and ");
				param.addValue("PLEA_DESCRIPTION", objSearchDao.getPleaDescription());
			}
			if (objSearchDao.getFindingDescription() != null) {
				sqlQuery.append("FINDING_DESCRIPTION = :FINDING_DESCRIPTION" + " and ");
				param.addValue("FINDING_DESCRIPTION", objSearchDao.getFindingDescription());
			}
			if (objSearchDao.getOicOfnTypeDesc() != null) {
				sqlQuery.append("OIC_OFN_TYPE_DESC = :OIC_OFN_TYPE_DESC" + " and ");
				param.addValue("OIC_OFN_TYPE_DESC", objSearchDao.getOicOfnTypeDesc());
			}
			if (objSearchDao.getResultOicOffenceCategory() != null) {
				sqlQuery.append("RESULT_OIC_OFFENCE_CATEGORY = :RESULT_OIC_OFFENCE_CATEGORY" + " and ");
				param.addValue("RESULT_OIC_OFFENCE_CATEGORY", objSearchDao.getResultOicOffenceCategory());
			}
			if (objSearchDao.getResultOicOffenceCode() != null) {
				sqlQuery.append("RESULT_OIC_OFFENCE_CODE = :RESULT_OIC_OFFENCE_CODE" + " and ");
				param.addValue("RESULT_OIC_OFFENCE_CODE", objSearchDao.getResultOicOffenceCode());
			}
			if (objSearchDao.getResultOffenceType() != null) {
				sqlQuery.append("RESULT_OFFENCE_TYPE = :RESULT_OFFENCE_TYPE" + " and ");
				param.addValue("RESULT_OFFENCE_TYPE", objSearchDao.getResultOffenceType());
			}
			if (objSearchDao.getResultOicOffenceDescription() != null) {
				sqlQuery.append("RESULT_OIC_OFFENCE_DESCRIPTION = :RESULT_OIC_OFFENCE_DESCRIPTION" + " and ");
				param.addValue("RESULT_OIC_OFFENCE_DESCRIPTION", objSearchDao.getResultOicOffenceDescription());
			}
			if (objSearchDao.getResultOicOfnTypeDesc() != null) {
				sqlQuery.append("RESULT_OIC_OFN_TYPE_DESC = RESULT_OIC_OFN_TYPE_DESC");
				param.addValue("RESULT_OIC_OFN_TYPE_DESC", objSearchDao.getResultOicOfnTypeDesc());
			}
		}

		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" order by  RESULT_SEQ asc ");
		final RowMapper<VOicHearingResults> VOicHearingResultsRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				VOicHearingResults.class, vOicHearingResultsMapping);
		final ArrayList<VOicHearingResults> returnList = (ArrayList<VOicHearingResults>) namedParameterJdbcTemplate
				.query(preparedSql, param, VOicHearingResultsRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao VOffenderOicSanctions
	 *
	 * @return List<VOffenderOicSanctions>
	 *
	 */
	public List<VOffenderOicSanctions> vOffOicSanctSearchVOffenderOicSanctions(
			final VOffenderOicSanctions objSearchDao) {
		final String sql = getQuery("OIIOICUS_VOFFOICSANCT_FIND_V_OFFENDER_OIC_SANCTIONS");
		String preparedSql;
		final StringBuilder sqlQuery = new StringBuilder();
		final MapSqlParameterSource param = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getOffenderBookId() != null) {
				sqlQuery.append("OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID" + " and ");
				param.addValue("OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId());
			}
			if (objSearchDao.getSanctionSeq() != null) {
				sqlQuery.append("SANCTION_SEQ = :SANCTION_SEQ" + " and ");
				param.addValue("SANCTION_SEQ", objSearchDao.getSanctionSeq());
			}
			if (objSearchDao.getOicSanctionDesc() != null) {
				sqlQuery.append("OIC_SANCTION_DESC = :OIC_SANCTION_DESC" + " and ");
				param.addValue("OIC_SANCTION_DESC", objSearchDao.getOicSanctionDesc());
			}
			if (objSearchDao.getCompensationAmount() != null) {
				sqlQuery.append("COMPENSATION_AMOUNT = :COMPENSATION_AMOUNT" + " and ");
				param.addValue("COMPENSATION_AMOUNT", objSearchDao.getCompensationAmount());
			}
			if (objSearchDao.getSanctionMonths() != null) {
				sqlQuery.append("SANCTION_MONTHS = :SANCTION_MONTHS" + " and ");
				param.addValue("SANCTION_MONTHS", objSearchDao.getSanctionMonths());
			}
			if (objSearchDao.getSanctionDays() != null) {
				sqlQuery.append("SANCTION_DAYS = :SANCTION_DAYS" + " and ");
				param.addValue("SANCTION_DAYS", objSearchDao.getSanctionDays());
			}
			if (objSearchDao.getCommentText() != null) {
				sqlQuery.append("COMMENT_TEXT = :COMMENT_TEXT" + " and ");
				param.addValue("COMMENT_TEXT", objSearchDao.getCommentText());
			}
			if (objSearchDao.getEffectiveDate() != null) {
				sqlQuery.append("EFFECTIVE_DATE = :EFFECTIVE_DATE" + " and ");
				param.addValue("EFFECTIVE_DATE", objSearchDao.getEffectiveDate());
			}
			if (objSearchDao.getConsecutiveSanctionSeq() != null) {
				sqlQuery.append("CONSECUTIVE_SANCTION_SEQ = :CONSECUTIVE_SANCTION_SEQ" + " and ");
				param.addValue("CONSECUTIVE_SANCTION_SEQ", objSearchDao.getConsecutiveSanctionSeq());
			}
			if (objSearchDao.getOicHearingId() != null) {
				sqlQuery.append("OIC_HEARING_ID = :OIC_HEARING_ID" + " and ");
				param.addValue("OIC_HEARING_ID", objSearchDao.getOicHearingId());
			}
			if (objSearchDao.getOicSanctionCode() != null) {
				sqlQuery.append("OIC_SANCTION_CODE = :OIC_SANCTION_CODE" + " and ");
				param.addValue("OIC_SANCTION_CODE", objSearchDao.getOicSanctionCode());
			}
			if (objSearchDao.getStatusDescription() != null) {
				sqlQuery.append("STATUS_DESCRIPTION = :STATUS_DESCRIPTION" + " and ");
				param.addValue("STATUS_DESCRIPTION", objSearchDao.getStatusDescription());
			}
			if (objSearchDao.getResultSeq() != null) {
				sqlQuery.append("RESULT_SEQ = :RESULT_SEQ" + " and ");
				param.addValue("RESULT_SEQ", objSearchDao.getResultSeq());
			}
			if (objSearchDao.getStatusDate() != null) {
				sqlQuery.append("STATUS_DATE = :STATUS_DATE" + " and ");
				param.addValue("STATUS_DATE", objSearchDao.getStatusDate());
			}
			if (objSearchDao.getOicIncidentId() != null) {
				sqlQuery.append("OIC_INCIDENT_ID = :OIC_INCIDENT_ID");
				param.addValue("OIC_INCIDENT_ID", objSearchDao.getOicIncidentId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" order by EFFECTIVE_DATE desc ");
		final RowMapper<VOffenderOicSanctions> VOffenderOicSanctionsRowMapper = Row2BeanRowMapper
				.makeMapping(preparedSql, VOffenderOicSanctions.class, vOffenderOicSanctionsMapping);
		final ArrayList<VOffenderOicSanctions> returnList = (ArrayList<VOffenderOicSanctions>) namedParameterJdbcTemplate
				.query(preparedSql, param, VOffenderOicSanctionsRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMastervOicInciCur
	 *
	 * @param paramBean
	 *
	 */
	public List<String> offBkgOnCheckDeleteMastervOicInciCur(final VOicIncidents paramBean) {
		final String sql = getQuery("OIIOICUS_OFF_BKG_ONCHECKDELETEMASTER_V_OIC_INCI_CUR");
		List<Object> returnList = new ArrayList<>();
		returnList = (ArrayList<Object>) namedParameterJdbcTemplate.queryForList(sql,
				createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()), Object.class);
		final List<String> returnStringList = new ArrayList<>();
		for (final Object object : returnList) {
			returnStringList.add(object.toString());
		}
		return returnStringList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vOicInciOnCheckDeleteMastervOicHearCur
	 *
	 * @param paramBean
	 *
	 */
	public List<String> vOicInciOnCheckDeleteMastervOicHearCur(final VOicHearings paramBean) {
		final String sql = getQuery("OIIOICUS_V_OIC_INCI_ONCHECKDELETEMASTER_V_OIC_HEAR_CUR");
		List<Object> returnList = new ArrayList<>();
		returnList = (ArrayList<Object>) namedParameterJdbcTemplate.queryForList(sql,
				createParams("OICINCIDENTID", paramBean.getOicIncidentId()), Object.class);
		final List<String> returnStringList = new ArrayList<>();
		for (final Object object : returnList) {
			returnStringList.add(object.toString());
		}
		return returnStringList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vOicHearOnCheckDeleteMastervOicHearResCur
	 *
	 * @param paramBean
	 *
	 */
	public List<String> vOicHearOnCheckDeleteMastervOicHearResCur(final VOicHearingResults paramBean) {
		final String sql = getQuery("OIIOICUS_V_OIC_HEAR_ONCHECKDELETEMASTER_V_OIC_HEAR_RES_CUR");
		List<Object> returnList = new ArrayList<>();
		returnList = (ArrayList<Object>) namedParameterJdbcTemplate.queryForList(sql,
				createParams("OICHEARINGID", paramBean.getOicHearingId()), Object.class);
		final List<String> returnStringList = new ArrayList<>();
		for (final Object object : returnList) {
			returnStringList.add(object.toString());
		}
		return returnStringList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vOicHearResOnCheckDeleteMastervOffOicSanctCur
	 *
	 * @param paramBean
	 *
	 */
	public List<String> vOicHearResOnCheckDeleteMastervOffOicSanctCur(final VOffenderOicSanctions paramBean) {
		final String sql = getQuery("OIIOICUS_V_OIC_HEAR_RES_ONCHECKDELETEMASTER_V_OFF_OIC_SANCT_CUR");
		List<Object> returnList = new ArrayList<>();
		returnList = (ArrayList<Object>) namedParameterJdbcTemplate.queryForList(sql,
				createParams("OICHEARINGID", paramBean.getOicHearingId(), "RESULTSEQ", paramBean.getResultSeq()),
				Object.class);
		final List<String> returnStringList = new ArrayList<>();
		for (final Object object : returnList) {
			returnStringList.add(object.toString());
		}
		return returnStringList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getProfileValue2vsProfvalCur
	 *
	 * @param paramBean
	 *
	 */
	public SystemProfiles getProfileValuevsProfvalCur(final SystemProfiles paramBean) {
		final String sql = getQuery("OIIOICUS_GET_PROFILE_VALUE_2_VS_PROFVAL_CUR");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		final SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_PROFILE_TYPE",
				paramBean.getProfileType(), "P_PROFILE_CODE", paramBean.getProfileCode()), columnRowMapper);
		return returnObj;
	}

	@Override
	public List<String> getHearingStaffNameList() {
		final String sql = getQuery("OIIOICUS_GET_HEARING_STAFF_NAME_LIST");
		return namedParameterJdbcTemplate.queryForList(sql, createParams(), String.class);
	}

	@Override
	public List<String> getHearingResultsOicOffenceDes() {
		final String sql = getQuery("OIIOICUS_GET_HEARING_RESULTS_OIC_OFFENCE_DES");
		return namedParameterJdbcTemplate.queryForList(sql, createParams(), String.class);
	}

	@Override
	public List<String> getHearingResultsType() {
		final String sql = getQuery("OIIOICUS_GET_HEARING_RESULTS_TYPE");
		return namedParameterJdbcTemplate.queryForList(sql, createParams(), String.class);
	}

	@Override
	public List<String> getDiscOicSanctionDes() {
		final String sql = getQuery("OIIOICUS_GET_DISC_OIC_SANCTION_DESC");
		return namedParameterJdbcTemplate.queryForList(sql, createParams(), String.class);
	}

	@Override
	public List<String> getDiscStatusDes() {
		final String sql = getQuery("OIIOICUS_GET_DISC_STATUS_DESC");
		return namedParameterJdbcTemplate.queryForList(sql, createParams(), String.class);
	}

}

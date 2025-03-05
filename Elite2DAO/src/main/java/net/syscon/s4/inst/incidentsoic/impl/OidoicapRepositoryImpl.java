package net.syscon.s4.inst.incidentsoic.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderOicSanctions;
import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.incidentsoic.beans.OicHearingResults;
import net.syscon.s4.inst.incidentsoic.OidoicapRepository;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppealIncidents;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppealPenalties;
import net.syscon.s4.inst.incidentsoic.maintenance.OffenderOicAppeals;
import oracle.jdbc.internal.OracleTypes;

/**
 * Class OidoicapRepositoryImpl
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
@Repository
public class OidoicapRepositoryImpl extends RepositoryBase implements OidoicapRepository {

	public OidoicapRepositoryImpl() {
	}

	private static Logger logger = LogManager.getLogger(OidoicapRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> oicOffencesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MAX_PENALTY_DAYS", new FieldMapper("maxPenaltyDays"))
			.put("MAX_PENALTY_MONTHS", new FieldMapper("maxPenaltyMonths")).build();

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("HEARD_BY", new FieldMapper("heardBy")).put("STATUS", new FieldMapper("status"))
			.put("DESCRIPTION", new FieldMapper(" description "))
			.put("OIC_PENALTY_TYPE", new FieldMapper("oicPenaltyType"))
			.put("APRREAL_REASON_CODE", new FieldMapper("aprrealReasonCode"))
			.put("LAST_NAM", new FieldMapper("lastNam")).put("FIRST_NAME", new FieldMapper("firstName"))
			.put("HEARING_RESULT_CODE", new FieldMapper("hearingResultCode")).build();

	private final Map<String, FieldMapper> offenderOicAppealIncidentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OIC_APPREAL_ID", new FieldMapper("oicApprealId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("OIC_HEARING_ID", new FieldMapper("oicHearingId")).build();

	private final Map<String, FieldMapper> offenderOicAppealPenaltiesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OIC_COMMENT", new FieldMapper("oicComment")).put("DAYS", new FieldMapper("days"))
			.put("RESULT_SEQ", new FieldMapper("resultSeq")).put("OIC_HEARING_ID", new FieldMapper("oicHearingId"))
			.put("SEQ", new FieldMapper("seq")).put("COMPENSATION", new FieldMapper("compensation")).build();

	private final Map<String, FieldMapper> offenderOicAppealsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("APRREAL_REASON_CODE", new FieldMapper("aprrealReasonCode"))
			.put("HEARD_BY", new FieldMapper("heardBy"))
			.put("OFFENDER_BOOKING_ID", new FieldMapper("offenderBookingId"))
			.put("HEARING_RESULT_CODE", new FieldMapper("hearingResultCode")).build();

	private final Map<String, FieldMapper> mmoicHearingsoicOffencesagencyIncidentPartiesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CHARGE_SEQ", new FieldMapper("chargeSeq")).put("OIC_OFFENCE_ID", new FieldMapper("oicOffenceId"))
			.put("OIC_HEARING_ID", new FieldMapper("oicHearingId"))
			.put("OIC_RESULT_OFFENCE_ID", new FieldMapper("oicResultOffenceId"))
			.put("DESCRIPTION", new FieldMapper("description")).put("OIC_INCIDENT_ID", new FieldMapper("oicIncidentId"))
			.put("AGENCY_INCIDENT_ID", new FieldMapper("agencyIncidentId"))
			.put("RESULT_SEQ", new FieldMapper("resultSeq")).put("OIC_OFFENCE_CODE", new FieldMapper("oicOffenceCode"))
			.put("CODE", new FieldMapper("code")).build();

	private final Map<String, FieldMapper> offenderOicSanctionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SANCTION_SEQ", new FieldMapper(" sanctionSeq"))
			.put("EFFECTIVE_DATE", new FieldMapper(" effectiveDate"))
			.put("COMPENSATION_AMOUNT", new FieldMapper(" compensationAmount"))
			.put("SANCTION_MONTHS", new FieldMapper(" sanctionMonths")).put("STATUS", new FieldMapper(" status"))
			.put("DESCRIPTIO", new FieldMapper("descriptio"))
			.put("OIC_SANCTION_CODE", new FieldMapper(" oicSanctionCode"))
			.put("SANCTION_DAYS", new FieldMapper(" sanctionDays")).build();

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description"))
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName")).build();

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<StaffMembers> rgHeardByRecordGroup() {
		final String sql = getQuery("OIDOICAP_FIND_RGHEARDBY");
		final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMOicHearingsOicOffencesAgencyIncidentParties>
	 */
	public List<OicHearingResults> rgHearingOffencesRecordGroup(int offenderBookingId) {
		final String sql = getQuery("OIDOICAP_FIND_RGHEARINGOFFENCES");
		final RowMapper<OicHearingResults> OicHearingResultsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OicHearingResults.class, mmoicHearingsoicOffencesagencyIncidentPartiesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookingId),
					OicHearingResultsRowMapper);
		} catch (Exception e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	public List<OffenderOicAppealPenalties> rgOicSeqLogRecordGroup(OffenderOicAppealPenalties searchRecord) {
		final String sql = getQuery("OIDOICAP_FIND_RGOICSEQLOG");
		final RowMapper<OffenderOicAppealPenalties> mMRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderOicAppealPenalties.class, offenderOicAppealPenaltiesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", searchRecord.getOffenderBookingId(), "seq", searchRecord.getSeq(),
							"oicApprealId", searchRecord.getOicApprealId(), "oicHearingId",
							searchRecord.getOicHearingId(), "resultSeq", searchRecord.getResultSeq()),
					mMRowMapper);
		} catch (Exception e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderOicAppeals
	 *
	 * @return List<OffenderOicAppeals>
	 *
	 * @throws SQLException
	 */
	public List<OffenderOicAppeals> offOicaExecuteQuery(OffenderOicAppeals objSearchDao) {
		final String sql = getQuery("OIDOICAP_OFFOICA_FIND_OFFENDER_OIC_APPEALS");
		final RowMapper<OffenderOicAppeals> OffenderOicAppealsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderOicAppeals.class, offenderOicAppealsMapping);
		final List<OffenderOicAppeals> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("offenderBookId", objSearchDao.getOffenderBookingId()), OffenderOicAppealsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderOicAppeals List<OffenderOicAppeals>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offOicaInsertOffenderOicAppeals(final List<OffenderOicAppeals> lstOffenderOicAppeals) {
		String sql = getQuery("OIDOICAP_OFFOICA_INSERT_OFFENDER_OIC_APPEALS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderOicAppeals obj : lstOffenderOicAppeals) {
			obj.setOicApprealId(offOicaPreInsert());
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offoicaInsertOffenderOicAppeals: ", e);
		}
		if (lstOffenderOicAppeals.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	public Long offOicaPreInsert() {
		String sql = getQuery("OIDOICAP_OFFOICA_PRE_INSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderOicAppeals List<OffenderOicAppeals>
	 *
	 * @throws SQLException
	 */
	public Integer offOicaUpdateOffenderOicAppeals(final List<OffenderOicAppeals> lstOffenderOicAppeals) {
		String sql = getQuery("OIDOICAP_OFFOICA_UPDATE_OFFENDER_OIC_APPEALS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderOicAppeals offenderOicAppeals : lstOffenderOicAppeals) {
			parameters.add(new BeanPropertySqlParameterSource(offenderOicAppeals));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offOicaUpdateOffenderOicAppeals: ", e);
		}
		if (lstOffenderOicAppeals.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderOicAppeals List<OffenderOicAppeals>
	 *
	 * @throws SQLException
	 */
	public Integer offOicaDeleteOffenderOicAppeals(final List<OffenderOicAppeals> lstOffenderOicAppeals) {
		String sql = getQuery("OIDOICAP_OFFOICA_DELETE_OFFENDER_OIC_APPEALS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderOicAppeals offenderOicAppeals : lstOffenderOicAppeals) {
			parameters.add(new BeanPropertySqlParameterSource(offenderOicAppeals));
		}
		try {
			String tableName = "OFFENDER_OIC_APPEALS";
			String whereClause = "OIC_APPREAL_ID = :oicApprealId and OFFENDER_BOOKING_ID = :offenderBookingId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offOicaDeleteOffenderOicAppeals", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("offOicaDeleteOffenderOicAppeals: ", e);
		}
		if (lstOffenderOicAppeals.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */

	public int onAppealCheckDeleteMaster(Long oicApprealId) {
		final String sql = getQuery("OIDOICAP_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<String> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, String.class,
				offenderOicAppealsMapping);
		List<String> returnList = namedParameterJdbcTemplate.query(sql, createParams("oicApprealId", oicApprealId),
				columnRowMapper);
		return returnList.size() >= 1 ? 1 : 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderOicAppealIncidents
	 *
	 * @return List<OffenderOicAppealIncidents>
	 *
	 * @throws SQLException OffenderOicAppealIncidents
	 */
	public List<OffenderOicAppealIncidents> offOicaiExecuteQuery(OffenderOicAppealIncidents objSearchDao) {
		final String sql = getQuery("OIDOICAP_OFFOICAI_FIND_OFFENDER_OIC_APPEAL_INCIDENTS");
		final RowMapper<OffenderOicAppealIncidents> OffenderOicAppealIncidentsRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderOicAppealIncidents.class, offenderOicAppealIncidentsMapping);
		final List<OffenderOicAppealIncidents> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("OicApprealId", objSearchDao.getOicApprealId()), OffenderOicAppealIncidentsRowMapper);
		return returnList;
	}

	@Override
	public List<OicHearingResults> offOicaiPostQuery(OffenderOicAppealIncidents searchRecord) {
		final String sql = getQuery("OIDOICAP_OFFOICAI_FIND_OFFENDER_OIC_APPEAL_INCIDENTS_POST_QUERY");
		final RowMapper<OicHearingResults> OicHearingResultsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OicHearingResults.class, mmoicHearingsoicOffencesagencyIncidentPartiesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", searchRecord.getOffenderBookingId(), "oicHearingId",
							searchRecord.getOicHearingId(), "resultSeq", searchRecord.getResultSeq()),
					OicHearingResultsRowMapper);
		} catch (Exception e) {
			logger.error("offOicaiPostQuery: ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderOicAppealIncidents List<OffenderOicAppealIncidents>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offOicaiInsertoffOffenderOicAppealIncidents(
			final List<OffenderOicAppealIncidents> lstOffenderOicAppealIncidents) {
		String sql = getQuery("OIDOICAP_OFFOICAI_INSERT_OFFENDER_OIC_APPEAL_INCIDENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderOicAppealIncidents obj : lstOffenderOicAppealIncidents) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage().toUpperCase();
			if (error.contains("OFFENDER_OICAI_PK")) {
				return 3;
			}
		}
		if (lstOffenderOicAppealIncidents.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderOicAppealIncidents List<OffenderOicAppealIncidents>
	 *
	 * @throws SQLException
	 */
	public Integer offOicaiUpdateOffenderOicAppealIncidents(
			final List<OffenderOicAppealIncidents> lstOffenderOicAppealIncidents) {
		String sql = getQuery("OIDOICAP_OFFOICAI_UPDATE_OFFENDER_OIC_APPEAL_INCIDENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderOicAppealIncidents offenderOicAppealIncidents : lstOffenderOicAppealIncidents) {
			parameters.add(new BeanPropertySqlParameterSource(offenderOicAppealIncidents));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderOicAppealIncidents.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderOicAppealIncidents List<OffenderOicAppealIncidents>
	 *
	 * @throws SQLException
	 */
	public Integer offOicaiDeleteOffenderOicAppealIncidents(
			final List<OffenderOicAppealIncidents> lstOffenderOicAppealIncidents) {
		String sql = getQuery("OIDOICAP_OFFOICAI_DELETE_OFFENDER_OIC_APPEAL_INCIDENTS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderOicAppealIncidents offenderOicAppealIncidents : lstOffenderOicAppealIncidents) {
			parameters.add(new BeanPropertySqlParameterSource(offenderOicAppealIncidents));
		}
		try {
			String tableName = "OFFENDER_OIC_APPEAL_INCIDENTS";
			String whereClause = "OIC_APPREAL_ID  = :oicApprealId AND OIC_HEARING_ID  = :oicHearingId AND RESULT_SEQ  = :resultSeq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offOicaiDeleteOffenderOicAppealIncidents", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstOffenderOicAppealIncidents.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderOicAppealPenalties
	 *
	 * @return List<OffenderOicAppealPenalties>
	 *
	 * @throws SQLException
	 */
	public List<OffenderOicAppealPenalties> offOicapExecuteQuery(OffenderOicAppealPenalties objSearchDao) {
		final String sql = getQuery("OIDOICAP_OFFOICAP_FIND_OFFENDER_OIC_APPEAL_PENALTIES");
		final RowMapper<OffenderOicAppealPenalties> OffenderOicAppealPenaltiesRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OffenderOicAppealPenalties.class, offenderOicAppealPenaltiesMapping);
		final List<OffenderOicAppealPenalties> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("oicApprealId", objSearchDao.getOicApprealId(), "oicHearingId",
						objSearchDao.getOicHearingId(), "resultSeq", objSearchDao.getResultSeq()),
				OffenderOicAppealPenaltiesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderOicAppealPenalties List<OffenderOicAppealPenalties>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offOicapInsertOffenderOicAppealPenalties(final OffenderOicAppealPenalties obj) {
		String sql = getQuery("OIDOICAP_OFFOICAP_INSERT_OFFENDER_OIC_APPEAL_PENALTIES");
		Integer returnArray = 0;
		try {

			returnArray = namedParameterJdbcTemplate.update(sql,
					createParams("oicApprealId", obj.getOicApprealId(), "oicHearingId", obj.getOicHearingId(),
							"resultSeq", obj.getResultSeq(), "seq", obj.getSeq(), "oicPenaltyType",
							obj.getOicPenaltyType(), "effectDate", obj.getEffectDate(), "month", obj.getMonth(), "days",
							obj.getDays(), "compensation", obj.getCompensation(), "oicComment", obj.getOicComment(),
							"oicAppealIdLog", obj.getOicAppealIdLog(), "oicSeqLog", obj.getOicSeqLog(),
							"oicHearingIdLog", obj.getOicHearingIdLog(), "resultSeqLog", obj.getResultSeqLog(),
							"status", obj.getStatus(), "offenderAdjustId", obj.getOffenderAdjustId(), "createUserId",
							obj.getCreateUserId(), "sealFlag", obj.getSealFlag()));
		} catch (Exception e) {
			logger.error("offOicapInsertOffenderOicAppealPenalties: ", e);
			returnArray = 0;

		}
		return returnArray;

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderOicAppealPenalties List<OffenderOicAppealPenalties>
	 *
	 * @throws SQLException
	 */
	public Integer offOicapUpdateOffenderOicAppealPenalties(
			final List<OffenderOicAppealPenalties> lstOffenderOicAppealPenalties) {
		String sql = getQuery("OIDOICAP_OFFOICAP_UPDATE_OFFENDER_OIC_APPEAL_PENALTIES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderOicAppealPenalties offenderOicAppealPenalties : lstOffenderOicAppealPenalties) {
			parameters.add(new BeanPropertySqlParameterSource(offenderOicAppealPenalties));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderOicAppealPenalties.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderOicAppealPenalties List<OffenderOicAppealPenalties>
	 *
	 * @throws SQLException
	 */
	public Integer offOicapDeleteOffenderOicAppealPenalties(
			final List<OffenderOicAppealPenalties> lstOffenderOicAppealPenalties) {
		String sql = getQuery("OIDOICAP_OFFOICAP_DELETE_OFFENDER_OIC_APPEAL_PENALTIES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderOicAppealPenalties offenderOicAppealPenalties : lstOffenderOicAppealPenalties) {
			parameters.add(new BeanPropertySqlParameterSource(offenderOicAppealPenalties));
		}
		try {
			String tableName = "OFFENDER_OIC_APPEAL_PENALTIES";
			String whereClause = "OIC_APPREAL_ID  = :oicApprealId AND OIC_HEARING_ID  = :oicHearingId AND RESULT_SEQ  = :resultSeq AND SEQ  = :seq";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method offOicapDeleteOffenderOicAppealPenalties", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstOffenderOicAppealPenalties.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	public Integer offOicapPreInsertOffenderOicAppealPenalties(BigDecimal offenderBookingId) {
		String sql = getQuery("OIDOICAP_OFFOICAP_PRE_INSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookingId", offenderBookingId),
				Integer.class);
	}

	public int onOicAppealPenaltiesCheckDeleteMaster(OffenderOicAppealPenalties deleteRecord) {
		final String sql = getQuery("OIDOICAP_OFFENDER_OIC_APPEAL_PENALTIES_ONCHECKDELETEMASTER");
		final RowMapper<String> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, String.class,
				offenderOicAppealsMapping);
		List<String> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("oicApprealId", deleteRecord.getOicApprealId(), "oicHearingId",
						deleteRecord.getOicHearingId(), "resultSeq", deleteRecord.getResultSeq(), "seq",
						deleteRecord.getSeq()),
				columnRowMapper);
		return returnList.size() >= 1 ? 1 : 0;
	}

	public int offOicaiOnCheckDeleteMaster(OffenderOicAppealIncidents deleteRecord) {
		final String sql = getQuery("OIDOICAP_OICAI_ONCHECKDELETEMASTER");
		final RowMapper<String> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, String.class,
				offenderOicAppealsMapping);
		List<String> returnList = namedParameterJdbcTemplate
				.query(sql,
						createParams("oicApprealId", deleteRecord.getOicApprealId(), "oicHearingId",
								deleteRecord.getOicHearingId(), "resultSeq", deleteRecord.getResultSeq()),
						columnRowMapper);
		return returnList.size() >= 1 ? 1 : 0;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offOicapPostQuery
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes offOicapPostQuery(ReferenceCodes paramBean) {
		final String sql = getQuery("OIDOICAP_OFF_OICAP_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidoicapWhenValidateRecord
	 *
	 * @param params
	 *
	 */
	public OicOffences oidoicapWhenValidateRecord(OicOffences paramBean) {
		final String sql = getQuery("OIDOICAP_OIDOICAP_WHENVALIDATERECORD");
		final RowMapper<OicOffences> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OicOffences.class,
				oicOffencesMapping);
		OicOffences returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * populatePenalty
	 *
	 * @param params
	 *
	 */
	public List<OffenderOicSanctions> populatePenalty(OffenderOicSanctions paramBean) {
		final String sql = getQuery("OIDOICAP_POPULATE_PENALTY");
		final RowMapper<OffenderOicSanctions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderOicSanctions.class, offenderOicSanctionsMapping);
		final List<OffenderOicSanctions> returnList = namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	@Override
	public String butOriginalPenalty(OffenderOicAppealIncidents searchBean) {
		final String sql = getQuery("OIDOICAP_ORIGINAL_PENALTY");
		String returnValue = null;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("agencyIncidentId",
					searchBean.getAgencyIncidentId(), "chargeSeq", searchBean.getChargeSeq()), String.class);
		} catch (Exception e) {
			logger.error("butOriginalPenalty", e);
		}
		return returnValue;
	}

	@Override
	public String getoffencedetails(OffenderOicAppealIncidents searchBean) {
		String returnValue = null;
		Map<String, Object> returnObject = null;

		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];

		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENCE_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_OFFENCE_TYPE_DESC", Types.VARCHAR),
				new SqlOutParameter("P_DESCRIPTION", Types.VARCHAR),
				new SqlOutParameter("P_OFFENCE_CODE", Types.VARCHAR) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_ADJUDICATION").withProcedureName("GETOFFENCEDETAILS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENCE_ID", searchBean.getOicOffenceId());

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			returnValue = (String) returnObject.get("P_OFFENCE_TYPE_DESC");
		} catch (Exception e) {
			logger.error("getoffencedetails", e);
		}
		return returnValue;
	}

	@Override
	public OicOffences getOicOffenceCodeCur(Integer oicOffenceId) {
		final String sql = getQuery("OIDOICAP_OIDOICAP_WHENVALIDATERECORD");
		final RowMapper<OicOffences> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OicOffences.class,
				oicOffencesMapping);
		OicOffences returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("oicOffenceId", oicOffenceId), columnRowMapper);
		return returnObj;
	}

}

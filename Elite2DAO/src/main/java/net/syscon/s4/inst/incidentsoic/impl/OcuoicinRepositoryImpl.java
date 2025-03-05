package net.syscon.s4.inst.incidentsoic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgyIncInvStatements;
import net.syscon.s4.im.beans.AgyIncInvestigations;
import net.syscon.s4.inst.incidentsoic.OcuoicinRepository;

/**
 * Class OcuoicinRepositoryImpl
 */
@Repository
public class OcuoicinRepositoryImpl extends RepositoryBase implements OcuoicinRepository {

	public static final String enterQuery = "ENTER-QUERY";
	/**
	 * Logger object used to print the log in the file
	 */

	private static Logger logger = LogManager.getLogger(OcuoicinRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> agyIncInvStatementsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_INC_INVESTIGATION_ID",   new FieldMapper("agyIncInvestigationId")).build();
	private final Map<String, FieldMapper> agyIncInvestigationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COMMENT_TEXT",              new FieldMapper("commentText"))
			.put("CREATE_USER_ID",            new FieldMapper("createUserId"))
			.put("AGENCY_INCIDENT_ID",        new FieldMapper("agencyIncidentId"))
			.put("AGY_II_STATEMENT_ID",       new FieldMapper("agyIiStatementId"))
			.put("ASSIGNED_DATE",             new FieldMapper("assignedDate"))
			.put("STATEMENT_TYPE",            new FieldMapper("statementType"))
			.put("INVESTIGATOR_ID",           new FieldMapper("investigatorId"))
			.put("MODIFY_USER_ID",            new FieldMapper("modifyUserId"))
			.put("NAME_OF_STATEMENT_TAKER",   new FieldMapper("nameOfStatementTaker"))
			.put("STATEMENT_DETAIL",          new FieldMapper("statementDetail")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",           new FieldMapper("createDatetime"))
			.put("DATE_OF_STATEMENT_TAKEN",   new FieldMapper("dateOfStatementTaken"))
			.put("MODIFY_DATETIME",           new FieldMapper("modifyDatetime"))
			.put("AGY_INC_INVESTIGATION_ID",  new FieldMapper("agyIncInvestigationId"))
			.put("PARTY_SEQ",                 new FieldMapper("partySeq"))
			.build();

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_DATETIME",           new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID",            new FieldMapper("createUserId"))
			.put("DESCRIPTION",               new FieldMapper("description"))
			.put("CODE",                      new FieldMapper("code"))
			.put("DOMAIN",                    new FieldMapper("domain"))
			.put("PARENT_DOMAIN",             new FieldMapper("parentDomainId"))
			.put("PARENT_CODE",               new FieldMapper("parentCode"))
			.put("ACTIVE_FLAG",               new FieldMapper("activeFlag"))
			.put("DSP_NAME",                  new FieldMapper("dspName")).build();

	/**
	 * Creates new OcuoicinRepositoryImpl class Object
	 */
	public OcuoicinRepositoryImpl() {
		// OcuoicinRepositoryImpl
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgStatementTypeRecordGroup()  {
		final String sql = getQuery("OCUOICIN_FIND_RGSTATEMENTTYPE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		List<ReferenceCodes> refList = new ArrayList<>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, createParams("MODE", enterQuery), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return refList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @param caseloadId
	 * @return List<StaffMembers>
	 */
	public List<StaffMembers> rgAgyIncpStaffIdRecordGroup(final String caseloadId)  {
		final String sql = getQuery("OCUOICIN_FIND_RGAGYINCPSTAFFID");
		final RowMapper<StaffMembers> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				referenceCodesMapping);
		List<StaffMembers> refList = new ArrayList<StaffMembers>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseloadId, "MODE", enterQuery),
					referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error(e);
			return Collections.emptyList();
		}
		return refList;
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param AgyIncInvestigations
	 *            objSearchDao
	 * @return List<AgyIncInvestigations>
	 * @
	 */
	public List<AgyIncInvestigations> oicInvestSearchAgyIncInvestigations(final AgyIncInvestigations objSearchDao)
			 {
		final String sql = getQuery("OCUOICIN_OICINVEST_FIND_AGY_INC_INVESTIGATIONS");
		List<AgyIncInvestigations> refList = new ArrayList<AgyIncInvestigations>();
		final RowMapper<AgyIncInvestigations> AgyIncInvestigationsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgyIncInvestigations.class, agyIncInvestigationsMapping);
		refList = namedParameterJdbcTemplate.query(sql, createParams("AGENCY_INCIDENT_ID",objSearchDao.getAgencyIncidentId(),
				"PARTY_SEQ",objSearchDao.getPartySeq()), AgyIncInvestigationsRowMapper);
		return refList;
	}

	/**
	 * @
	 *
	 */
	public int preInsert()  {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 * 
	 * @param lstAgyIncInvestigations
	 *            List<AgyIncInvestigations>
	 * @return Integer
	 * @
	 */
	public Integer oicInvestInsertAgyIncInvestigations(final List<AgyIncInvestigations> lstAgyIncInvestigations)
			 {
		final String sql = getQuery("OCUOICIN_OICINVEST_INSERT_AGY_INC_INVESTIGATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgyIncInvestigations agyIncInvestigations : lstAgyIncInvestigations) {
			parameters.add(new BeanPropertySqlParameterSource(agyIncInvestigations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgyIncInvestigations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 * 
	 * @param lstAgyIncInvestigations
	 *            List<AgyIncInvestigations>
	 * @
	 * @return Integer
	 */
	public Integer oicInvestUpdateAgyIncInvestigations(final List<AgyIncInvestigations> lstAgyIncInvestigations)
			 {
		final String sql = getQuery("OCUOICIN_OICINVEST_UPDATE_AGY_INC_INVESTIGATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgyIncInvestigations agyIncInvestigations : lstAgyIncInvestigations) {
			parameters.add(new BeanPropertySqlParameterSource(agyIncInvestigations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstAgyIncInvestigations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 * 
	 * @param lstAgyIncInvestigations
	 *            List<AgyIncInvestigations>
	 * @
	 * @return Integer
	 */
	public Integer oicInvestDeleteAgyIncInvestigations(final List<AgyIncInvestigations> lstAgyIncInvestigations)
			 {
		final String sql = getQuery("OCUOICIN_OICINVEST_DELETE_AGY_INC_INVESTIGATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgyIncInvestigations agyIncInvestigations : lstAgyIncInvestigations) {
			parameters.add(new BeanPropertySqlParameterSource(agyIncInvestigations));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstAgyIncInvestigations.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param objSearchDao
	 *            AgyIncInvStatements
	 * @return List<AgyIncInvStatements>
	 * @
	 */
	public List<AgyIncInvStatements> oicInvestStaSearchAgyIncInvStatements(final AgyIncInvStatements objSearchDao)
			 {
		final String sql = getQuery("OCUOICIN_OICINVESTSTA_FIND_AGY_INC_INV_STATEMENTS");
		List<AgyIncInvStatements> refList = new ArrayList<AgyIncInvStatements>();
		final RowMapper<AgyIncInvStatements> AgyIncInvStatementsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgyIncInvStatements.class, agyIncInvStatementsMapping);
		refList = namedParameterJdbcTemplate.query(sql, createParams("AGY_INC_INVESTIGATION_ID",
				objSearchDao.getAgyIncInvestigationId()), AgyIncInvStatementsRowMapper);
		return refList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 * 
	 * @param lstAgyIncInvStatements
	 *            List<AgyIncInvStatements>
	 * @return Integer
	 * @
	 */
	public Integer oicInvestStaInsertAgyIncInvStatements(final List<AgyIncInvStatements> lstAgyIncInvStatements)
			 {
		final String sql = getQuery("OCUOICIN_OICINVESTSTA_INSERT_AGY_INC_INV_STATEMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgyIncInvStatements agyIncInvStatements : lstAgyIncInvStatements) {
			parameters.add(new BeanPropertySqlParameterSource(agyIncInvStatements));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAgyIncInvStatements.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 * 
	 * @param lstAgyIncInvStatements
	 *            List<AgyIncInvStatements>
	 * @
	 * @return Integer
	 */
	public Integer oicInvestStaUpdateAgyIncInvStatements(final List<AgyIncInvStatements> lstAgyIncInvStatements)
			 {
		final String sql = getQuery("OCUOICIN_OICINVESTSTA_UPDATE_AGY_INC_INV_STATEMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgyIncInvStatements agyIncInvStatements : lstAgyIncInvStatements) {
			parameters.add(new BeanPropertySqlParameterSource(agyIncInvStatements));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstAgyIncInvStatements.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 * 
	 * @param lstAgyIncInvStatements
	 *            List<AgyIncInvStatements>
	 * @
	 * @return Integer
	 */
	public Integer oicInvestStaDeleteAgyIncInvStatements(final List<AgyIncInvStatements> lstAgyIncInvStatements)
			 {
		final String sql = getQuery("OCUOICIN_OICINVESTSTA_DELETE_AGY_INC_INV_STATEMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AgyIncInvStatements agyIncInvStatements : lstAgyIncInvStatements) {
			parameters.add(new BeanPropertySqlParameterSource(agyIncInvStatements));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstAgyIncInvStatements.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return List<Object>
	 * @param investgation
	 */
	public List<Object> oicInvestOnCheckDeleteMasteroicInvestStaCur(final String investgation)  {

		final String sql = getQuery("OCUOICIN_OIC_INVEST_ONCHECKDELETEMASTER_OIC_INVEST_STA_CUR");
		List<Object> returnList = new ArrayList<Object>();
		try {
			returnList = (namedParameterJdbcTemplate.queryForList(sql,
					createParams("AgyIncInvestigationId", investgation), Object.class));
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return Object
	 */
	public Object agyIncInvestigationsPreInsertcDAO()  {
		final String sql = getQuery("OCUOICIN_AGY_INV_INVESTIGATIONSPREINSERTC_SQL");
		Object returnObj = null;
		 returnObj= namedParameterJdbcTemplate.queryForObject(sql, createParams(), Object.class);
		 return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return Object
	 */
	public Object agyIncInvestmentsPreInsertcDAO()  {
		final String sql = getQuery("OCUOICIN_AGY_INV_INVESTMENTSPREINSERTC_SQL");
		Object returnObj = null;
	    returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Object.class);
	    return returnObj;

	}

}

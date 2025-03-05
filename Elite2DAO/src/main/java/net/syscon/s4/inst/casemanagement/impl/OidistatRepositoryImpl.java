package net.syscon.s4.inst.casemanagement.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderBillingProfiles;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyBillingProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.casemanagement.OidistatRepository;
import net.syscon.s4.inst.casemanagement.beans.OffenderImprisonStatuses;

/**
 * Class OidistatRepositoryImpl
 * 
 */
@Repository
public class OidistatRepositoryImpl extends RepositoryBase implements OidistatRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidistatRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> offBiPrMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DELAY_DAYS", new FieldMapper("delayDays"))
			.put("AGENCY_ID", new FieldMapper("agencyID"))
			.put("EFFECTIVE_DATE_START", new FieldMapper("effectiveDateStart"))
			.build();
	private final Map<String, FieldMapper> agyBilPrMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DELAY_DAYS", new FieldMapper("delayDays"))
			.put("AGENCY_ID", new FieldMapper("agencyId"))
			.put("BILLING_TYPE", new FieldMapper("billingType"))
			.put("FREQUENCY", new FieldMapper("frequency"))
			.put("IMP_AGY_BILLING_DETAIL_ID", new FieldMapper("impAgyBillingDetailId"))
			.build();
	private final Map<String, FieldMapper> offExtMovMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TRUNC(EFFECTIVE_DATE)", new FieldMapper("trunc(effectiveDate)"))
			.put("IMPRISONMENT_STATUS", new FieldMapper("imprisonmentStatus"))
			.put("(EFFECTIVE_TIME", new FieldMapper("(effectiveTime"))
			.put("MOVEMENT_DATE", new FieldMapper("movementDate"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.build();
	private final Map<String, FieldMapper> offImpStMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("CREATE_DATE", new FieldMapper("createDate"))
			.put("EFFECTIVE_TIME", new FieldMapper("effectiveTime"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("IMPRISON_STATUS_SEQ", new FieldMapper("imprisonStatusSeq"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("IMPRISONMENT_STATUS", new FieldMapper("imprisonmentStatus"))
			.put("EFFECTIVE_DATE", new FieldMapper("effectiveDate"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("DOMAIN", new FieldMapper("domain"))
			.build();
	
	/**
	 * Creates new OidistatRepositoryImpl class Object
	 */
	public OidistatRepositoryImpl() {
		// OidistatRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderImprisonStatuses
	 *
	 * @return List<OffenderImprisonStatuses>
	 *
	 * @
	 */
	public List<OffenderImprisonStatuses> offImpsExecuteQuery(final OffenderImprisonStatuses objSearchDao) {
		final String sql = getQuery("OIDISTAT_OFFIMPS_FIND_OFFENDER_IMPRISON_STATUSES");
		final RowMapper<OffenderImprisonStatuses> offImpStatRM = Row2BeanRowMapper.makeMapping(sql,
				OffenderImprisonStatuses.class, offImpStMapping);
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getOffenderBookId() != null) {
				sqlQuery.append(
						"OFFENDER_BOOK_ID =  :offenderBookId ORDER BY EFFECTIVE_DATE DESC, TO_CHAR(EFFECTIVE_TIME, 'HH24:MI') DESC ");
				valuesList.addValue("offenderBookId", objSearchDao.getOffenderBookId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		return namedParameterJdbcTemplate.query(preparedSql, valuesList, offImpStatRM);
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
	 * @param lstOffImpStatuses
	 *            List<OffenderImprisonStatuses>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	public Integer offImpsInsertOffenderImprisonStatuses(final List<OffenderImprisonStatuses> lstOffImpStatuses) {
		int returnValue = 0;
		final String sql = getQuery("OIDISTAT_OFFIMPS_INSERT_OFFENDER_IMPRISON_STATUSES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderImprisonStatuses offImpStatus : lstOffImpStatuses) {
			parameters.add(new BeanPropertySqlParameterSource(offImpStatus));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffImpStatuses.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffImpStatuses
	 *            List<OffenderImprisonStatuses>
	 *
	 * @
	 */
	public Integer offImpsUpdateOffenderImprisonStatuses(final List<OffenderImprisonStatuses> lstOffImpStatuses) {
		int returnValue = 0;
		final String sql = getQuery("OIDISTAT_OFFIMPS_UPDATE_OFFENDER_IMPRISON_STATUSES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderImprisonStatuses offImpStat : lstOffImpStatuses) {
			parameters.add(new BeanPropertySqlParameterSource(offImpStat));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffImpStatuses.size() == returnArray.length) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgImprisonmentStaRecordGroup() {
		final String sql = getQuery("OIDISTAT_FIND_RGIMPRISONMENTSTA");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> lstRef = new ArrayList<>();
		try {
			lstRef = (List<ReferenceCodes>) namedParameterJdbcTemplate.query(sql, createParams("MODE", "ENTER-QUERY"), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(" In rgImprisonmentStaRecordGroup method :" + e);
		}
		return lstRef;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> rgAgyLocIdRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIDISTAT_FIND_RGAGYLOCID");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);
		List<AgencyLocations> returnList = new ArrayList<AgencyLocations>();
		try {
			returnList = (List<AgencyLocations>) namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseloadId), mRowMapper);
		} catch (Exception e) {
			logger.error("In rgAgyLocIdRecordGroup method : ", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public OffenderImprisonStatuses offBkgOnCheckDeleteMaster(final OffenderImprisonStatuses paramBean) {
		final String sql = getQuery("OIDISTAT_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderImprisonStatuses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderImprisonStatuses.class, offImpStMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offImpsPreInsert
	 *
	 * @param params
	 *
	 */
	public OffenderImprisonStatuses offImpsPreInsertStatus(final OffenderImprisonStatuses paramBean) {
		final String sql = getQuery("OIDISTAT_OFF_IMPS_PREINSERT_STATUS");
		final RowMapper<OffenderImprisonStatuses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderImprisonStatuses.class, offImpStMapping);
		List<OffenderImprisonStatuses> lstOffImp = (List<OffenderImprisonStatuses>) namedParameterJdbcTemplate
				.query(sql, createParams("offenderBookId", paramBean.getOffenderBookId()), columnRowMapper);
		OffenderImprisonStatuses offImp = null;
		if (lstOffImp != null && lstOffImp.size() > 0) {
			offImp = lstOffImp.get(0);
		}
		return offImp;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offImpsPreInsertPRE-INSERT
	 *
	 * @param params
	 *
	 */
	public Long offImpsPreInsert(final OffenderImprisonStatuses paramBean) {
		final String sql = getQuery("OIDISTAT_OFF_IMPS_PREINSERT_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDERBOOKID", paramBean.getOffenderBookId()), Long.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offImpsPostQuery
	 *
	 * @param params
	 *
	 */
	public OffenderImprisonStatuses offImpsPostQuery(final OffenderImprisonStatuses paramBean) {
		final String sql = getQuery("OIDISTAT_OFF_IMPS_POSTQUERY");
		final RowMapper<OffenderImprisonStatuses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderImprisonStatuses.class, offImpStMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * processBillProfile
	 *
	 * @param params
	 *
	 */
	public Integer processBillProfileDelayDays(final OffenderImprisonStatuses paramBean) {
		final String sql = getQuery("OIDISTAT_PROCESS_BILL_PROFILE_DELAY_DAYS");
		final RowMapper<AgencyBillingProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyBillingProfiles.class, agyBilPrMapping);
		List<AgencyBillingProfiles> returnObj = (List<AgencyBillingProfiles>) namedParameterJdbcTemplate.query(
				sql, createParams("P_CASE_LOAD_ID", paramBean.getGlobalCaseloadId(), "P_AGY_LOC_ID",
						paramBean.getAgyLocId(), "P_IMPRISONMENT_STATUS", paramBean.getImprisonmentStatus()),
				columnRowMapper);
		Integer delayDays = null;
		if (returnObj != null && returnObj.size() > 0) {
			final AgencyBillingProfiles agyBill = returnObj.get(0);
			delayDays = agyBill.getDELAY_DAYS();
		}
		return delayDays;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * processBillProfile
	 *
	 * @param params
	 *
	 */
	public Integer processBillProfileAgencyDelayDays(final AgencyBillingProfiles paramBean) {
		final String sql = getQuery("OIDISTAT_PROCESS_BILL_PROFILE_AGENCY_DELAY_DAYS");
		final RowMapper<AgencyBillingProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyBillingProfiles.class, agyBilPrMapping);
		List<AgencyBillingProfiles> returnObj = (List<AgencyBillingProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams("LV_AGENCY", paramBean.getAGENCY_ID(), "LV_BIL_TYPE", paramBean.getBILLING_TYPE(),
						"CASELOADID", paramBean.getCASELOAD_ID()),
				columnRowMapper);
		Integer delayDays = null;
		if (returnObj != null && returnObj.size() > 0) {
			final AgencyBillingProfiles agyBill = returnObj.get(0);
			delayDays = agyBill.getDELAY_DAYS();
		}
		return delayDays;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * processBillProfile
	 *
	 * @param params
	 *
	 */
	public Date processBillProfileMaxEffective(final OffenderImprisonStatuses paramBean) {
		final String sql = getQuery("OIDISTAT_PROCESS_BILL_PROFILE_MAX_EFFECTIVE");
		final RowMapper<OffenderBillingProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBillingProfiles.class, offBiPrMapping);
		List<OffenderBillingProfiles> lstOffBill = (List<OffenderBillingProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams("LV_CSLD_ID", paramBean.getGlobalCaseloadId(), "LV_OFF_BOOK_ID",
						paramBean.getOffenderBookId()),
				columnRowMapper);
		Date effectiveStDate = null;
		if (lstOffBill != null && lstOffBill.size() > 0) {
			final OffenderBillingProfiles offBillPrf = lstOffBill.get(0);
			effectiveStDate = offBillPrf.getEffectiveDateStart();
		}
		return effectiveStDate;

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * processBillProfile
	 *
	 * @param params
	 *
	 */
	public Date processBillProfileMaxEffectiveDateSys(final Date effectiveTime) {
		final String sql = getQuery("OIDISTAT_GET_EFFECTIVE_DATE_SYS");
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
	    String eftDate= formatter.format(effectiveTime);  
	    System.out.println("eftDate ========================================= " + eftDate);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("effectiveDate", eftDate), Date.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * processBillProfile
	 *
	 * @param params
	 *
	 */
	public String processBillProfileCaseLoads(final String agyLocId) {
		final String sql = getQuery("OIDISTAT_PROCESS_BILL_PROFILE_CASELOADS");
		String returnObject=null;
		 try {
			 returnObject=namedParameterJdbcTemplate.queryForObject(sql, createParams("P_LOC_ID", agyLocId), String.class);
		} catch (Exception e) {
			returnObject=null;
			logger.error("In processBillProfileCaseLoads method : ", e);
		}
		return returnObject;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * processBillProfile
	 *
	 * @param params
	 *
	 */
	public Integer processBillProfileCount(final OffenderBillingProfiles paramBean) {
		final String sql = getQuery("OIDISTAT_PROCESS_BILL_PROFILE_COUNT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("CASELOADID", paramBean.getCaseloadId(), "LV_AGENCY", paramBean.getAgencyId(),
						"LV_BIL_TYPE", paramBean.getBillingType(), "LV_OFF_BOOK_ID", paramBean.getOffenderBookingId()),
				Integer.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * processBillProfile
	 *
	 * @param params
	 *
	 */
	public OffenderBillingProfiles processBillProfile(final OffenderBillingProfiles paramBean) {
		final String sql = getQuery("OIDISTAT_PROCESS_BILL_PROFILE");
		final RowMapper<OffenderBillingProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBillingProfiles.class, offBiPrMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * processBillProfile
	 *
	 * @param params
	 *
	 */
	public AgencyBillingProfiles processBillProfileRecord(final OffenderImprisonStatuses paramBean) {
		final String sql = getQuery("OIDISTAT_PROCESS_BILL_PROFILE_RECORD");
		final RowMapper<AgencyBillingProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyBillingProfiles.class, agyBilPrMapping);
		List<AgencyBillingProfiles> returnObj = (List<AgencyBillingProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams("LV_CSLD_ID", paramBean.getGlobalCaseloadId(), "P_LOC_ID", paramBean.getAgyLocId(),
						"P_IMP_STS", paramBean.getImprisonmentStatus()),
				columnRowMapper);
		AgencyBillingProfiles agyBill = null;
		if (returnObj != null && returnObj.size() > 0) {
			agyBill = returnObj.get(0);
		}
		return agyBill;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * processBillProfilevs_get_count_cur
	 *
	 * @param params
	 *
	 */
	public String processBillProfilGetCountCur(final String caseloadId) {
		final String sql = getQuery("OIDISTAT_PROCESS_BILL_PROFILE_VS_GET_COUNT_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("LV_CSLD_ID", caseloadId), String.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * processBillProfilevs_get_count_cur
	 *
	 * @param params
	 *
	 */
	public String getProfileValueClient() {
		final String sql = getQuery("OIDISTAT_GET_PROFILE_VALUE_CLIENT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * chkImpDate
	 *
	 * @param params
	 *
	 */
	public Date chkImpDateMovement(final OffenderExternalMovements paramBean) {
		final String sql = getQuery("OIDISTAT_CHK_IMP_DATE_MOVEMENT");
		final RowMapper<OffenderExternalMovements> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderExternalMovements.class, offExtMovMapping);
		List<OffenderExternalMovements> lstOffImp = (List<OffenderExternalMovements>) namedParameterJdbcTemplate
				.query(sql, createParams("P_OFFENDER_BOOK_ID", paramBean.getOffenderBookId(), "P_CASELOAD_ID",
						paramBean.getToAgyLocId()), columnRowMapper);

		Date effectiveDate = null;
		if (lstOffImp != null && lstOffImp.size() > 0) {
			final OffenderExternalMovements offImp = lstOffImp.get(0);
			effectiveDate = offImp.getMovementDate();
		}
		return effectiveDate;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * chkImpDate
	 *
	 * @param params
	 *
	 */
	public Date chkImpDateEffective(final OffenderImprisonStatuses paramBean) {
		final String sql = getQuery("OIDISTAT_CHK_IMP_DATE_EFFECTIVE");
		final RowMapper<OffenderImprisonStatuses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderImprisonStatuses.class, offImpStMapping);
		List<OffenderImprisonStatuses> lstOffImp = (List<OffenderImprisonStatuses>) namedParameterJdbcTemplate.query(
				sql,
				createParams("OFFENDERBOOKID", paramBean.getOffenderBookId(), "CASELOADID", paramBean.getAgyLocId()),
				columnRowMapper);

		Date effectiveDate = null;
		if (lstOffImp != null && lstOffImp.size() > 0) {
			final OffenderImprisonStatuses offImp = lstOffImp.get(0);
			effectiveDate = offImp.getEffectiveDate();
		}
		return effectiveDate;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * chkImpDate
	 *
	 * @param params
	 *
	 */
	public OffenderImprisonStatuses chkImpDateEffectiveTime(final OffenderImprisonStatuses paramBean) {
		final String sql = getQuery("OIDISTAT_CHK_IMP_DATE_EFFECTIVE_TIME");
		final RowMapper<OffenderImprisonStatuses> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderImprisonStatuses.class, offImpStMapping);

		List<OffenderImprisonStatuses> lstOffImp = (List<OffenderImprisonStatuses>) namedParameterJdbcTemplate.query(
				sql,
				createParams("OFFENDERBOOKID", paramBean.getOffenderBookId(), "CASELOADID", paramBean.getAgyLocId()),
				columnRowMapper);
		OffenderImprisonStatuses offImp = null;
		if (lstOffImp != null && lstOffImp.size() > 0) {
			offImp = lstOffImp.get(0);
		}
		return offImp;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param OffenderBillingProfiles
	 *            paramBean
	 *
	 * @
	 */
	public Integer offenderBillingProfilesUpdateEqualOrLess(final OffenderBillingProfiles paramBean) {
		int returnValue = 0;
		final String sql = getQuery("OIDISTAT_UPDATE_OFFENDER_BILLING_PROFILES_EQUALORLESS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(paramBean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param OffenderBillingProfiles
	 *            paramBean
	 *
	 * @
	 */
	public Integer offenderBillingProfilesUpdateGreater(final OffenderBillingProfiles paramBean) {
		int returnValue = 0;
		final String sql = getQuery("OIDISTAT_UPDATE_OFFENDER_BILLING_PROFILES_GREATER");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(paramBean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param OffenderBillingProfiles
	 *            paramBean
	 *
	 * @
	 */
	public Integer offenderBillingProfilesUpdateNotEqualFrequency(final OffenderBillingProfiles paramBean) {
		int returnValue = 0;
		final String sql = getQuery("OIDISTAT_UPDATE_OFFENDER_BILLING_PROFILES_NOT_EQUAL_FREQUENCY");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(paramBean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param OffenderBillingProfiles
	 *            paramBean
	 *
	 * @
	 */
	public Integer offenderBillingProfilesInsertEqualFrequency(final OffenderBillingProfiles paramBean) {
		int returnValue = 0;
		final String sql = getQuery("OIDISTAT_INSERT_OFFENDER_BILLING_PROFILES_EQUAL_FREQUENCY");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(paramBean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			returnValue = 1;
		}
		return returnValue;

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param OffenderImprisonStatuses
	 *            paramBean
	 *
	 * @
	 */
	public Integer offenderUpdateImprisonStatuses(final OffenderImprisonStatuses paramBean) {
		int returnValue = 0;
		final String sql = getQuery("OIDISTAT_UPDATE_IMPRISON_STATUSES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(paramBean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			returnValue = 1;
		}
		return returnValue;

	}

}

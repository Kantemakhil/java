package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import net.syscon.s4.common.beans.CaseloadDedBeneficiaries;
import net.syscon.s4.common.beans.OtmtfproFmbBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.im.beans.SystemProfiles;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.OtmtfproRepository;

@Repository
public class OtmtfproRepositoryImpl extends RepositoryBase implements OtmtfproRepository{

private static Logger logger = LogManager.getLogger(OtmtfproRepositoryImpl.class.getName());


private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("RECEIPT_TXN_TYPE", 					new FieldMapper("receiptTxnType"))
.put("CASELOAD_ID", 						new FieldMapper("caseloadId"))
.put("DEDUCTION_TYPE", 						new FieldMapper("deductionType"))
.build();
private final Map<String, FieldMapper> clsdDpMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DELAY_RECAPTURE",					new FieldMapper("csldDpdelayRecapture"))
.put("CASELOAD_ID",						new FieldMapper("csldDpcaseloadId"))
.put("DEDUCTION_TYPE",					new FieldMapper("csldDpdeductionType"))
.put("EFFECTIVE_DATE",					new FieldMapper("csldDpeffectiveDate"))
.put("FIFO_FLAG",						new FieldMapper("csldDpfifoFlag"))
.put("FO_AL_ALL_OFFENDER_FLAG",			new FieldMapper("csldDpfoAlAllOffenderFlag"))
.put("PERCENTAGE",						new FieldMapper("csldDppercentage"))
.put("INTERNAL_PRIORITY_NO",			new FieldMapper("csldDpinternalPriorityNo"))
.put("EXTERNAL_PRIORITY_NO",			new FieldMapper("csldDpexternalPriorityNo"))
.put("ACCOUNT_CODE",					new FieldMapper("csldDpaccountCode"))
.put("CO_LIMIT_AMOUNT",					new FieldMapper("csldDpcoLimitAmount"))
.put("CO_CREDIT_WHEN_INDIGENT_FLAG",	new FieldMapper("csldDpcoCreditWhenIndigentFlag"))
.put("MAX_MONTHLY_AMOUNT",				new FieldMapper("csldDpmaxMonthlyAmount"))
.put("MAX_TOTAL_AMOUNT",				new FieldMapper("csldDpmaxTotalAmount"))
.put("EXPIRY_DATE",						new FieldMapper("csldDpexpiryDate"))
.put("PAYEE_PERSON_ID",					new FieldMapper("csldDppayeePersonId"))
.put("PAYEE_CORPORATE_ID",				new FieldMapper("csldDppayeeCorporateId"))
.put("MODIFY_USER_ID",					new FieldMapper("csldDpmodifyUserId"))
.put("ACTIVE_FLAG",						new FieldMapper("csldDpactiveFlag"))
.build();
private final Map<String, FieldMapper> deductionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DEDUCTION_TYPE", 						new FieldMapper("csldDddeductionType"))
.put("RECEIPT_TXN_TYPE", 					new FieldMapper("csldDdreceiptTxnType"))
.put("CASELOAD_ID", 						new FieldMapper("csldDdcaseloadId"))
.put("PERCENTAGE", 							new FieldMapper("csldDdpercentage"))
.put("FLAT_RATE", 							new FieldMapper("csldDdflatRate"))
.put("MODIFY_USER_ID", 						new FieldMapper("csldDdmodifyUserId"))
.put("MODIFY_DATE", 						new FieldMapper("csldDdmodifyDate"))
.put("LIST_SEQ", 							new FieldMapper("csldDdlistSeq"))
.build();
private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("ACCOUNT_NAME", 						new FieldMapper("accountName"))
.put("ACCOUNT_CODE", 						new FieldMapper("accountCode"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CODE", 								new FieldMapper("code"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("DEDUCTION_TYPE", 						new FieldMapper("deductionType"))
.put("ACCOUNT_CODE", 						new FieldMapper("accountCode"))
.put("TXN_TYPE", 							new FieldMapper("txnType"))
.build();
private final Map<String, FieldMapper> corporateloadIdMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CORPORATE_ID", 						new FieldMapper("corporateId"))
.put("CODE", 						new FieldMapper("code"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> corporatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CORPORATE_NAME", 						new FieldMapper("corporateName"))
.put("PAYEE_CORPORATE_ID", 					new FieldMapper("payeeCorporateId"))
.build();

	/**
	 * Creates new OtmtfproRepositoryImpl class Object
	 */
	public OtmtfproRepositoryImpl() {
//		OtmtfproRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CaseloadDeductionProfiles
	 *
	 * @return List<CaseloadDeductionProfiles>
	 *
	 * @throws SQLException
	 */
	public List<OtmtfproFmbBean> csldDpExecuteQuery(OtmtfproFmbBean objSearchDao) {
		String sql = getQuery("OTMTFPRO_CSLDDP_FIND_CASELOAD_DEDUCTION_PROFILES");
		final StringBuffer paramQuery = new StringBuffer();
		final MapSqlParameterSource param = new MapSqlParameterSource();
		if (objSearchDao != null) {
			if (objSearchDao.getCsldDpdeductionType() != null && !objSearchDao.getCsldDpdeductionType().isEmpty()) {
				paramQuery.append(" DEDUCTION_TYPE = :deductionType AND ");
				param.addValue("deductionType", objSearchDao.getCsldDpdeductionType());
			}
			if (objSearchDao.getCsldDpaccountCode() != null) {
				paramQuery.append(" ACCOUNT_CODE = :accountCode AND ");
				param.addValue("accountCode", objSearchDao.getCsldDpaccountCode());
			}
			if (objSearchDao.getCsldDppayeeCorporateId() != null) {
				paramQuery.append(" PAYEE_CORPORATE_ID = :payeeCorporateId AND ");
				param.addValue("payeeCorporateId", objSearchDao.getCsldDppayeeCorporateId());
			}
			param.addValue("caseloadId", objSearchDao.getCsldDpcaseloadId());
			if (paramQuery != null && paramQuery.toString() != null && !paramQuery.toString().isEmpty()) {
				sql = sql.replace("#paramQuery", paramQuery.toString());
			} else {
				sql = sql.replace("#paramQuery", "");
			}
		}
		final RowMapper<OtmtfproFmbBean> CaseloadDeductionProfilesRowMapper = Row2BeanRowMapper
				.makeMapping(sql, OtmtfproFmbBean.class, clsdDpMapping);
		final List<OtmtfproFmbBean> returnList = namedParameterJdbcTemplate
				.query(sql, param, CaseloadDeductionProfilesRowMapper);
		return returnList;
	}
	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	// public int PRE_INSERT() {
	// return 0;
	// }

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstCaseloadDeductionProfiles
	 *            List<CaseloadDeductionProfiles>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer csldDpInsertCaseloadDeductionProfiles(final OtmtfproFmbBean data) {
		String sql = getQuery("OTMTFPRO_CSLDDP_INSERT_CASELOAD_DEDUCTION_PROFILES");
		final Map<String, Object> batchValues = new HashMap<>();
		Integer returnValue = 0;
		batchValues.put("externalPriorityNo", data.getCsldDpexternalPriorityNo());
		batchValues.put("internalPriorityNo", data.getCsldDpinternalPriorityNo());
		batchValues.put("percentage", data.getCsldDppercentage());
		batchValues.put("fifoFlag", data.getCsldDpfifoFlag());
		batchValues.put("accountCode", data.getCsldDpaccountCode());
		batchValues.put("effectiveDate", data.getCsldDpeffectiveDate());
		batchValues.put("activeFlag", data.getCsldDpactiveFlag());
		batchValues.put("expiryDate", data.getCsldDpexpiryDate());
		batchValues.put("foAlAllOffenderFlag", data.getCsldDpfoAlAllOffenderFlag());
		batchValues.put("payeeCorporateId", data.getCsldDppayeeCorporateId());
		batchValues.put("deductionType", data.getCsldDpdeductionType());
		batchValues.put("caseloadId", data.getCsldDpcaseloadId());
		batchValues.put("createUserId", data.getCreateUserId());
		try {
			returnValue = namedParameterJdbcTemplate.update(sql, batchValues);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "In method rgTransportRecordGroup error", e);
			if (e.getMessage().contains("deduction_profiles_pk")) {
				returnValue = 2;
			}else {
				returnValue = 0;
			}
		}
		return returnValue;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCaseloadDeductionProfiles
	 *            List<CaseloadDeductionProfiles>
	 *
	 */
	public Integer csldDpUpdateCaseloadDeductionProfiles(
			final List<OtmtfproFmbBean> lstCaseloadDeductionProfiles) {
		String sql = getQuery("OTMTFPRO_CSLDDP_UPDATE_CASELOAD_DEDUCTION_PROFILES");
		final Map<String, Object>[] batchValues = new HashMap[lstCaseloadDeductionProfiles.size()];
		lstCaseloadDeductionProfiles.forEach(data -> {
			final int index = lstCaseloadDeductionProfiles.indexOf(data);
			batchValues[index] = new HashMap<String, Object>();
			batchValues[index].put("accountCode", data.getCsldDpaccountCode());
			batchValues[index].put("effectiveDate", data.getCsldDpeffectiveDate());
			batchValues[index].put("activeFlag", data.getCsldDpactiveFlag());
			batchValues[index].put("expiryDate", data.getCsldDpexpiryDate());
			batchValues[index].put("foAlAllOffenderFlag", data.getCsldDpfoAlAllOffenderFlag());
			batchValues[index].put("payeeCorporateId", data.getCsldDppayeeCorporateId());
			batchValues[index].put("deductionType", data.getCsldDpdeductionType());
			batchValues[index].put("caseloadId", data.getCsldDpcaseloadId());
			batchValues[index].put("modifyUserId", data.getModifyUserId());			
		});
		int[] returnArray = namedParameterJdbcTemplate.batchUpdate(sql, batchValues);
		if (returnArray != null && returnArray.length == batchValues.length) {
			return 1;
		} else {
			return 0;
		}

	}
	
	/**
	 * This method is used to Delete the data base tables based on
	 *
	 * @param lstCaseloadDeductionProfiles
	 *            List<CaseloadDeductionProfiles>
	 *
	 */
	public Integer csldDpDeleteCaseloadDeductionProfiles(
			final List<OtmtfproFmbBean> lstCaseloadDeductionProfiles) {
		String sql = getQuery("OTMTFPRO_CSLDDP_DELETE_CASELOAD_DEDUCTION_PROFILES");
		final Map<String, Object>[] batchValues = new HashMap[lstCaseloadDeductionProfiles.size()];
		lstCaseloadDeductionProfiles.forEach(data -> {
			final int index = lstCaseloadDeductionProfiles.indexOf(data);
			batchValues[index] = new HashMap<String, Object>();
			batchValues[index].put("deductionType", data.getCsldDpdeductionType());
			batchValues[index].put("caseloadId", data.getCsldDpcaseloadId());
			
		});
		try {
			List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
			for (OtmtfproFmbBean caseloadDeductionProfiles : lstCaseloadDeductionProfiles) {
				parameters.add(new BeanPropertySqlParameterSource(caseloadDeductionProfiles));
			}
			String tableName = "CASELOAD_DEDUCTION_PROFILES";
			String whereClause = "CASELOAD_ID = :caseloadId  AND DEDUCTION_TYPE = :deductionType";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method csldDpDeleteCaseloadDeductionProfiles", e);
		}
		int[] returnArray = namedParameterJdbcTemplate.batchUpdate(sql, batchValues);
		if (returnArray != null && returnArray.length == batchValues.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CaseloadDeductionDetails
	 *
	 * @return List<CaseloadDeductionDetails>
	 *
	 * @throws SQLException
	 */
	public List<OtmtfproFmbBean> csldDdExecuteQuery(OtmtfproFmbBean objSearchDao) {
		final String sql = getQuery("OTMTFPRO_CSLDDD_FIND_CASELOAD_DEDUCTION_DETAILS");
		final RowMapper<OtmtfproFmbBean> CaseloadDeductionDetailsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OtmtfproFmbBean.class, deductionTypesMapping);
		final List<OtmtfproFmbBean> returnList = namedParameterJdbcTemplate.query(sql, createParams("caseloadId", objSearchDao.getCsldDdcaseloadId(), "deductionType", objSearchDao.getCsldDddeductionType()), CaseloadDeductionDetailsRowMapper);
		return returnList;
	}
	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	// public int PRE_INSERT() {
	// return 0;
	// }

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstCaseloadDeductionDetails
	 *            List<CaseloadDeductionDetails>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer csldDdInsertCaseloadDeductionDetails(
			final List<OtmtfproFmbBean> lstCaseloadDeductionDetails) {
		String sql = getQuery("OTMTFPRO_CSLDDD_INSERT_CASELOAD_DEDUCTION_DETAILS");
		final Map<String, Object>[] batchValues = new HashMap[lstCaseloadDeductionDetails.size()];
		lstCaseloadDeductionDetails.forEach(data -> {
			final int index = lstCaseloadDeductionDetails.indexOf(data);
			batchValues[index] = new HashMap<String, Object>();
			batchValues[index].put("caseloadId", data.getCsldDdcaseloadId());
			batchValues[index].put("deductionType", data.getCsldDddeductionType());
			batchValues[index].put("receiptTxnType", data.getCsldDdreceiptTxnType());
			batchValues[index].put("percentage", data.getCsldDdpercentage());
			batchValues[index].put("flatRate", data.getCsldDdflatRate());
			batchValues[index].put("createUserId", data.getCreateUserId());
		});
		int[] returnArray = namedParameterJdbcTemplate.batchUpdate(sql, batchValues);
		if (returnArray != null && returnArray.length == batchValues.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCaseloadDeductionDetails
	 *            List<CaseloadDeductionDetails>
	 *
	 * @throws SQLException
	 */
	public Integer csldDdUpdateCaseloadDeductionDetails(
			final List<OtmtfproFmbBean> lstCaseloadDeductionDetails) {
		String sql = getQuery("OTMTFPRO_CSLDDD_UPDATE_CASELOAD_DEDUCTION_DETAILS");
		final Map<String, Object>[] batchValues = new HashMap[lstCaseloadDeductionDetails.size()];
		lstCaseloadDeductionDetails.forEach(data -> {
			final int index = lstCaseloadDeductionDetails.indexOf(data);
			batchValues[index] = new HashMap<String, Object>();
			batchValues[index].put("caseloadId", data.getCsldDdcaseloadId());
			batchValues[index].put("deductionType", data.getCsldDddeductionType());
			batchValues[index].put("receiptTxnType", data.getCsldDdreceiptTxnType());
			batchValues[index].put("percentage", data.getCsldDdpercentage());
			batchValues[index].put("flatRate", data.getCsldDdflatRate());
			batchValues[index].put("modifyUserId", data.getModifyUserId());
		});
		int[] returnArray = namedParameterJdbcTemplate.batchUpdate(sql, batchValues);
		if (returnArray != null && returnArray.length == batchValues.length) {
			return 1;
		} else {
			return 0;
		}

	}
	
	/**
	 * This method is used to delete the data base tables based on
	 *
	 * @param lstCaseloadDeductionDetails
	 *            List<CaseloadDeductionDetails>
	 *
	 * @throws SQLException
	 */
	public Integer csldDdDeleteCaseloadDeductionDetails(
			final List<OtmtfproFmbBean> lstCaseloadDeductionDetails) {
		String sql = getQuery("OTMTFPRO_CSLDDD_DELETE_CASELOAD_DEDUCTION_DETAILS");
		final Map<String, Object>[] batchValues = new HashMap[lstCaseloadDeductionDetails.size()];
		lstCaseloadDeductionDetails.forEach(data -> {
			final int index = lstCaseloadDeductionDetails.indexOf(data);
			batchValues[index] = new HashMap<String, Object>();
			batchValues[index].put("caseloadId", data.getCsldDdcaseloadId());
			batchValues[index].put("deductionType", data.getCsldDddeductionType());
			batchValues[index].put("receiptTxnType", data.getCsldDdreceiptTxnType());
		});
		try {
			List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
			for (OtmtfproFmbBean caseloadDeductionDetails : lstCaseloadDeductionDetails) {
				parameters.add(new BeanPropertySqlParameterSource(caseloadDeductionDetails));
			}
			String tableName = "CASELOAD_DEDUCTION_DETAILS";
			String whereClause = "CASELOAD_ID = :caseloadId AND DEDUCTION_TYPE = :deductionType AND RECEIPT_TXN_TYPE = :receiptTxnType";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method csldDdDeleteCaseloadDeductionDetails", e);
		}
		int[] returnArray = namedParameterJdbcTemplate.batchUpdate(sql, batchValues);
		if (returnArray != null && returnArray.length == batchValues.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 * @throws SQLException
	 */
	public List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSearchDao) {
		final String sql = getQuery("OTMTFPRO_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, mMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), SystemProfilesRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MCrp.corporateNameCrp.caseloadId>
	 */
	public List<Corporates> cgfkCsldDpPayeeCorporateIRecordGroup() {
		final String sql = getQuery("OTMTFPRO_FIND_CGFKCSLDDPPAYEECORPORATEI");
		final RowMapper<Corporates> caseloadIdRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,
				corporateloadIdMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), caseloadIdRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<TransactionTypes> cgfkCsldDdReceiptTxnTypeRecordGroup(final String caseloadType) {
		final String sql = getQuery("OTMTFPRO_FIND_CGFKCSLDDDRECEIPTTXNTYPE");
		final RowMapper<TransactionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadType", caseloadType), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(final String caseloadType) {
		final String sql = getQuery("OTMTFPRO_FIND_CGFKCSLDDPDEDUCTIONTYPE");
		final RowMapper<DeductionTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadType", caseloadType), mRowMapper);
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(final String caseloadType) {
		final String sql = getQuery("OTMTFPRO_FIND_CGFKCSLDDPACCOUNTCODE");
		final RowMapper<AccountCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("caseloadType", caseloadType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldDpDedprofCorp
	 *
	 * @param params
	 *
	 */
	public List<Corporates> cgfkchkCsldDpDedprofCorp(Corporates paramBean) {
		final String sql = getQuery("OTMTFPRO_CGFKCHK_CSLD_DP_DEDPROF_CORP");
		final RowMapper<Corporates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporates.class,
				corporatesMapping);
		final List<Corporates> returnList = namedParameterJdbcTemplate.query(sql,createParams("corporateId", paramBean.getCorporateId()), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldDpDedprofAcCo
	 *
	 * @param params
	 *
	 */
	public List<AccountCodes> cgfkchkCsldDpDedprofAcCo(AccountCodes paramBean) {
		final String sql = getQuery("OTMTFPRO_CGFKCHK_CSLD_DP_DEDPROF_AC_CO");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		final ArrayList<AccountCodes> returnList = (ArrayList<AccountCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldDpDedprofDedty
	 *
	 * @param params
	 *
	 */
	public List<DeductionTypes> cgfkchkCsldDpDedprofDedty(DeductionTypes paramBean) {
		final String sql = getQuery("OTMTFPRO_CGFKCHK_CSLD_DP_DEDPROF_DEDTY");
		final RowMapper<DeductionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, DeductionTypes.class,
				deductionTypesMapping);
		final ArrayList<DeductionTypes> returnList = (ArrayList<DeductionTypes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<Object> cgwhenNewFormInstance(SysDual paramBean) {
		final String sql = getQuery("OTMTFPRO_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<Object> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Object.class, mMapping);
		final ArrayList<Object> returnList = (ArrayList<Object>) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkCsldDdCsldDdTxnty
	 *
	 * @param params
	 *
	 */
	public List<TransactionTypes> cgfkchkCsldDdCsldDdTxnty(TransactionTypes paramBean) {
		final String sql = getQuery("OTMTFPRO_CGFKCHK_CSLD_DD_CSLD_DD_TXNTY");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, TransactionTypes.class,
				transactionTypesMapping);
		final ArrayList<TransactionTypes> returnList = (ArrayList<TransactionTypes>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	@Override
	public String omsUtilsDisplayUserMessage(BigDecimal msgNo, String applnCode) {
		final String sql = getQuery("OTMTFPRO_OMS_UTILS.DISPLAY_USER_MESSAGE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("msgNo", msgNo, "applnCode", applnCode), String.class);
	}
	
	@Override
	public List<CaseloadDeductionProfiles> chkDuplicateDedType (final String caseloadId, final String deductionType) {
		final String sql = getQuery("OTMTFPRO_CHK_DUPLICATE_DED_TYPE");
		final RowMapper<CaseloadDeductionProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, CaseloadDeductionProfiles.class,
				transactionTypesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("caseloadId",caseloadId,"deductionType",deductionType), columnRowMapper);
	}

	@Override
	public BigDecimal getExternalPriorityNo(String caseloadId) {
		final String sql = getQuery("GET_EXTERNAL_PRIORITY_NO");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", caseloadId), BigDecimal.class);
	}

	@Override
	public BigDecimal getCsldddMaxPercentage(String csldDdcaseloadId, String csldDddeductionType) {
		final String sql = getQuery("GET_CSLDDD_MAX_PERCENTAGE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", csldDdcaseloadId, "deductionType", csldDddeductionType), BigDecimal.class);
	}

	@Override
	public Integer updateCsldDdCaseloadDeductionProfiles(BigDecimal receiptPercentage, String csldDdcaseloadId,
			String csldDddeductionType, String userName) {
		final String sql = getQuery("UPDATE_CSLD_DD_CASELOAD_DEDUCTION_PROFILES");
		return namedParameterJdbcTemplate.update(sql, createParams("percentage", receiptPercentage, "caseloadId", csldDdcaseloadId, "deductionType", csldDddeductionType, "modifyUserId",userName));
	}

}

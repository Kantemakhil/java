package net.syscon.s4.inmate.trust.trustaccounts.impl;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.InsertGlTransNew;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OtrdreceReportBean;
import net.syscon.s4.common.beans.OtrreceiReportBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.ChkFreezeDisbursements;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.trust.trustaccounts.OtdrdtfuRepository;
import net.syscon.s4.inst.booking.beans.Persons;
import oracle.jdbc.internal.OracleTypes;
/**
 * Class OtdrdtfuRepositoryImpl
 */
@Repository
public class OtdrdtfuRepositoryImpl extends RepositoryBase implements OtdrdtfuRepository{
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdrdtfuRepositoryImpl.class.getName());

private final Map<String, FieldMapper> personsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("TXN_TYPE", 						new FieldMapper("txnType"))
.put("LAST_NAME", 						new FieldMapper("lastName"))
.put("FIRST_NAME", 						new FieldMapper("firstName"))
.put("ACTIVE_FLA", 						new FieldMapper("activeFla"))
.put("CORPORATE_NAM", 						new FieldMapper("corporateNam"))
.put("'Y'", 						new FieldMapper(" 'y' "))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("'X'", 						new FieldMapper(" 'x' "))
.put("PAYEE_CORPORATE_ID", 						new FieldMapper("payeeCorporateId"))
.put("ISSUE#18616", 						new FieldMapper(" issue#18616 "))
.put("CASELOAD_ID", 						new FieldMapper("caseloadId"))
.put("ISSUE#1861", 						new FieldMapper("issue#1861"))
.put("NVL(DAYS", 						new FieldMapper(" nvl(days"))
.build();
private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("TXN_TYPE", 						new FieldMapper("txnType"))
.put("LAST_NAME", 						new FieldMapper("lastName"))
.put("FIRST_NAME", 						new FieldMapper("firstName"))
.put("ACTIVE_FLA", 						new FieldMapper("activeFla"))
.put("CORPORATE_NAM", 						new FieldMapper("corporateNam"))
.put("'Y'", 						new FieldMapper(" 'y' "))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("CODE", 						new FieldMapper("code"))
.put("'X'", 						new FieldMapper(" 'x' "))
.put("PAYEE_CORPORATE_ID", 						new FieldMapper("payeeCorporateId"))
.put("ISSUE#18616", 						new FieldMapper(" issue#18616 "))
.put("CASELOAD_ID", 						new FieldMapper("caseloadId"))
.put("ISSUE#1861", 						new FieldMapper("issue#1861"))
.put("NVL(DAYS", 						new FieldMapper(" nvl(days"))
.build();
private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("PROFILE_CODE", 						new FieldMapper("profileCode"))
.put("PROFILE_TYPE", 						new FieldMapper("profileType"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("OLD_TABLE_NAME", 						new FieldMapper("oldTableName"))
.put("LTRIM(RTRIM(DESCRIPTION))", 						new FieldMapper(" ltrim(rtrim(description)) "))
.put("PROFILE_VALUE_2", 						new FieldMapper("profileValue2"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
.put("PROFILE_VALUE", 						new FieldMapper("profileValue"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> offenderBookingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("IMPRISONMENT_STATUS", 						new FieldMapper(" imprisonmentStatus "))
.put("OFFENDER_BOOK_ID", 						new FieldMapper(" offenderBookId "))
.build();
private final Map<String, FieldMapper> corporateNameMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CORPORATE_ID", 						new FieldMapper("corporateId"))
.put("CORPORATE_NAME", 						new FieldMapper("corporateName"))
.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
.build();
private final Map<String, FieldMapper> corporatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("TXN_TYPE", 						new FieldMapper("txnType"))
.put("LAST_NAME", 						new FieldMapper("lastName"))
.put("FIRST_NAME", 						new FieldMapper("firstName"))
.put("ACTIVE_FLA", 						new FieldMapper("activeFla"))
.put("CORPORATE_NAM", 						new FieldMapper("corporateNam"))
.put("'Y'", 						new FieldMapper(" 'y' "))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("'X'", 						new FieldMapper(" 'x' "))
.put("PAYEE_CORPORATE_ID", 						new FieldMapper("payeeCorporateId"))
.put("ISSUE#18616", 						new FieldMapper(" issue#18616 "))
.put("CASELOAD_ID", 						new FieldMapper("caseloadId"))
.put("ISSUE#1861", 						new FieldMapper("issue#1861"))
.put("NVL(DAYS", 						new FieldMapper(" nvl(days"))
.build();
private final Map<String, FieldMapper> offenderTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("TXN_TYPE", 						new FieldMapper("txnType"))
.put("LAST_NAME", 						new FieldMapper("lastName"))
.put("FIRST_NAME", 						new FieldMapper("firstName"))
.put("ACTIVE_FLA", 						new FieldMapper("activeFla"))
.put("CORPORATE_NAM", 						new FieldMapper("corporateNam"))
.put("'Y'", 						new FieldMapper(" 'y' "))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("'X'", 						new FieldMapper(" 'x' "))
.put("PAYEE_CORPORATE_ID", 						new FieldMapper("payeeCorporateId"))
.put("ISSUE#18616", 						new FieldMapper(" issue#18616 "))
.put("CASELOAD_ID", 						new FieldMapper("caseloadId"))
.put("ISSUE#1861", 						new FieldMapper("issue#1861"))
.put("NVL(DAYS", 						new FieldMapper(" nvl(days"))
.build();

private final Map<String, FieldMapper> txnOperationMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CHEQUE_PRODUCTION_FLAG", 						new FieldMapper("chequeProductionFlag"))
.put("RECEIPT_PRODUCTION_FLAG", 					new FieldMapper("receiptProductionFlag"))
.put("CHEQUE_PAYEE_TYPE", 						    new FieldMapper("chequePayeeType"))
.put("TXN_USAGE", 						            new FieldMapper("txnUsage"))
.build();

private final Map<String, FieldMapper> offenderDeductionMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DEDUCTION_TYPE", 						new FieldMapper("deductionType"))
.put("OFFENDER_DEDUCTION_ID", 						            new FieldMapper("offenderDeductionId"))
.build();

private final Map<String, FieldMapper> reportingMapping = new ImmutableMap.Builder<String, FieldMapper>()

.put("TXN_DATE", 						new FieldMapper("txnDate"))
.put("REC_NUM", 						            new FieldMapper("recNum"))
.put("T_CSLD", 						new FieldMapper("tCsld"))
.put("AMT", 						            new FieldMapper("amt"))
.put("OFF_NAME", 						new FieldMapper("offName"))
.put("OFF_ID", 						            new FieldMapper("offId"))
.put("TXN_DESC", 						new FieldMapper("txnDesc"))
.put("USER_ID", 						            new FieldMapper("userId"))
.put("PAYEE_NAME", 						new FieldMapper("payeeName"))
.put("CHECK_NUM", 						            new FieldMapper("checkNum"))
.put("LOCATION", 						new FieldMapper("location"))
.put("BOOK_ID", 						            new FieldMapper("bookId"))
.put("OFF_ID_DISPLAY", 						new FieldMapper("offId"))
.put("REMITTER_ID", 						            new FieldMapper("remitterId"))
.put("REMITTER_NAME", 						new FieldMapper("remitter"))
.put("TXN_ID", 						            new FieldMapper("txnId"))
.put("TXN_POSTING_TYPE", 						            new FieldMapper("txnPostingType"))
.put("ACCOUNT_CODE", 						            new FieldMapper("accountCode"))
.put("ACCOUNT_NAME", 						            new FieldMapper("accountName"))
.put("CLDESC", 						            new FieldMapper("clDesc"))
.put("CLIENT", 						            new FieldMapper("client"))
.build();

private final Map<String, FieldMapper> accountCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DR_ACCOUNT_CODE", 						new FieldMapper("drAccountCode"))
.put("A_TXN_POSTING_TYPE", 						new FieldMapper("aTxnPostingType"))
.put("NVL(B.POSTING_STATUS_FLAG,'N')", 						new FieldMapper("bPostingStatusFlag"))
.put("CR_ACCOUNT_CODE", 						new FieldMapper("crAccountCode"))
.put("B_TXN_POSTING_TYPE", 						new FieldMapper("bTxnPostingType"))
.put("NVL(C.POSTING_STATUS_FLAG,'N')", 						new FieldMapper("cPostingStatusFlag"))
.put("BANK_DR_ACCOUNT_CODE", 						new FieldMapper("bankDrAccountCode"))
.put("C_TXN_POSTING_TYPE", 						new FieldMapper("cTxnPostingType"))
.put("NVL(D.POSTING_STATUS_FLAG,'N')", 						new FieldMapper("dPostingStatusFlag"))
.put("BANK_CR_ACCOUNT_CODE", 						new FieldMapper("bankCrAccountCode"))
.put("D_TXN_POSTING_TYPE", 						new FieldMapper("dTxnPostingType"))
.put("NVL(E.POSTING_STATUS_FLAG,'N')", 						new FieldMapper("ePostingStatusFlag"))
.put("TXN_OPERATION_SEQ", 						new FieldMapper("txnOperationSeq"))
.build();

/**
 * Creates new OtdrdtfuRepositoryImpl class Object 
 */
public OtdrdtfuRepositoryImpl() {
	// OtdrdtfuRepositoryImpl
}

/**
* Fetch the records from database table
*
* @param objSearchDao SystemProfiles
*
* @return List<SystemProfiles>
*
* @
*/
 public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao)  {
		final String sql = getQuery("OTDRDTFU_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>)namedParameterJdbcTemplate.query(sql, createParams(), SystemProfilesRowMapper);
		return returnList;
} 
/**
* @param 
*
* @ 
*
*/
public int preInsert()  {
return 0;
}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<Persons> cgfkOffTxnPayeePersonIdRecordGroup()  {
 	final String sql = getQuery("OTDRDTFU_FIND_CGFKOFFTXNPAYEEPERSONID");
 	final RowMapper<Persons> personsRowMapper = Row2BeanRowMapper.makeMapping(sql,Persons.class, personsMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),personsRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		logger.error("In method cgfkOffTxnPayeePersonIdRecordGroup",e);
  		return Collections.emptyList();  
	}
}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<TransactionTypes> cgfkOffTxnTxnTypeRecordGroup(final String caseloadId, final String userName)  {
 	final String sql = getQuery("OTDRDTFU_FIND_CGFKOFFTXNTXNTYPE");
 	final RowMapper<TransactionTypes> trnRowMapper = Row2BeanRowMapper.makeMapping(sql,TransactionTypes.class, transactionTypesMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams("CASELOADID",caseloadId, "USER_NAME", userName),trnRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		logger.error("In method cgfkOffTxnTxnTypeRecordGroup",e);
  		return Collections.emptyList();  
	}
}
/**
* Used to capture results from select query
* @return List<MMCrp.corporateName> 
*/
public List<Corporates> cgfkOffTxnPayeeCorporateIRecordGroup()  {
 	final String sql = getQuery("OTDRDTFU_FIND_CGFKOFFTXNPAYEECORPORATEI");
 	final RowMapper<Corporates> corporateNameRowMapper = Row2BeanRowMapper.makeMapping(sql,Corporates.class, corporateNameMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),corporateNameRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		logger.error("In method cgfkOffTxnPayeeCorporateIRecordGroup",e);
  		return Collections.emptyList();  
	}
}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgPostQueryPOST-QUERY
	 *
	 * @param params
	 *
	 */
	public OffenderBookings offBkgPostQuery(final OffenderBookings paramBean)  {
		final String sql = getQuery("OTDRDTFU_OFF_BKG_POSTQUERY_POSTQUERY");
		final RowMapper<OffenderBookings> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,OffenderBookings.class,  offenderBookingsMapping);
		final OffenderBookings returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnPer
	 *
	 * @param params
	 *
	 */
	public Persons cgfkchkOffTxnOffTxnPer(final Persons paramBean)  {
		final String sql = getQuery("OTDRDTFU_CGFKCHK_OFF_TXN_OFF_TXN_PER_F");
		final RowMapper<Persons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,Persons.class,  personsMapping);
		final Persons returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnCorp
	 *
	 * @param params
	 *
	 */
	public Corporates cgfkchkOffTxnOffTxnCorp(final Corporates paramBean)  {
		final String sql = getQuery("OTDRDTFU_CGFKCHK_OFF_TXN_OFF_TXN_CORP_");
		final RowMapper<Corporates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,Corporates.class,  corporatesMapping);
		final Corporates returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffTxnOffTxnTxn
	 *
	 * @param params
	 *
	 */
	public TransactionTypes cgfkchkOffTxnOffTxnTxn(final TransactionTypes paramBean)  {
		final String sql = getQuery("OTDRDTFU_CGFKCHK_OFF_TXN_OFF_TXN_TXN_T");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,TransactionTypes.class,  transactionTypesMapping);
		final TransactionTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<Object> cgwhenNewFormInstance(final SysDual paramBean)  {
		final String sql = getQuery("OTDRDTFU_CGWHEN_NEW_FORM_INSTANCE");
		final List<Object> returnList = namedParameterJdbcTemplate.queryForList(sql, createParams(), Object.class);
		return returnList;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * mainProcess
	 *
	 * @param params
	 *
	 */
	public OffenderTransactions mainProcess(final OffenderTransactions paramBean)  {
		final String sql = getQuery("OTDRDTFU_MAIN_PROCESS");
		final RowMapper<OffenderTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,OffenderTransactions.class,  offenderTransactionsMapping);
		final OffenderTransactions returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * mainProcess
	 *
	 * @param params
	 *
	 */
	public TransactionTypes mainProcess(final TransactionTypes paramBean)  {
		final String sql = getQuery("OTDRDTFU_MAIN_PROCESS");
		final RowMapper<TransactionTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,TransactionTypes.class,  transactionTypesMapping);
		final TransactionTypes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * mainProcess
	 *
	 * @param params
	 *
	 */
	public Corporates mainProcess(final Corporates paramBean)  {
		final String sql = getQuery("OTDRDTFU_MAIN_PROCESS");
		final RowMapper<Corporates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,Corporates.class,  corporatesMapping);
		final Corporates returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * mainProcess
	 *
	 * @param params
	 *
	 */
	public Persons mainProcess(final Persons paramBean)  {
		final String sql = getQuery("OTDRDTFU_MAIN_PROCESS");
		final RowMapper<Persons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,Persons.class,  personsMapping);
		final Persons returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * runReport
	 *
	 * @param params
	 *
	 */
	public SystemProfiles runReport(final SystemProfiles paramBean)  {
		final String sql = getQuery("OTDRDTFU_RUN_REPORT");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,SystemProfiles.class,  systemProfilesMapping);
		SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * runReport
	 *
	 * @param params
	 *
	 */
	public TransactionOperation txnTypeValidation(final String txnType, final String caseloadId, final String userName)  {
		final String sql = getQuery("OTDRDTFU_TXN_TYPE_VALIDATION");
		final RowMapper<TransactionOperation> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,TransactionOperation.class,  txnOperationMapping);
		final TransactionOperation returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("TXN_TYPE",txnType,"CASELOAD_ID",caseloadId, "USER_NAME", userName), columnRowMapper);
		return returnObj;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * runReport
	 *
	 * @param params
	 *
	 */
	public Integer checkCaseloadValidation(final String caseloadId, final String agyLocId)  {
		final String sql = getQuery("OTDRDTFU_CASELOAD_VALIDATION");
		Integer returnObj = 0;
		try{
		 returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("CASELOAD_ID",caseloadId,"AGY_LOC_ID",agyLocId), Integer.class);
		}
		catch(EmptyResultDataAccessException e){
			return returnObj;
		}
		return returnObj;
	}

	@Override
	public SystemProfiles otdrdtfuModlibValidationHWhenValidateItemSystemProfileC() {
		final String sql = getQuery("OTDRDTFU_MODLIB_VALIDATION_H_WHEN_VALIDATE_ITEM_2_SYSTEM_PROFILE_C");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,SystemProfiles.class,  systemProfilesMapping);
		final SystemProfiles systemProfile =  namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper); 
		return systemProfile;
	}

	@Override
	public List<OffenderDeductions> otdrdtfuModlibValidationHWhenValidateItemGetTxnFeeType(final Long offenderId,
			final String caseloadId, final String transType) {
		final String sql = getQuery("OTDRDTFU_MODLIB_VALIDATION_H_WHEN_VALIDATE_ITEM_2_GET_TXN_FEE_TYPE");
		final RowMapper<OffenderDeductions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,OffenderDeductions.class,  offenderDeductionMapping);
		final List<OffenderDeductions> resultList =  namedParameterJdbcTemplate.query(sql, createParams("offenderId", offenderId, "caseloadId", caseloadId, "transType", transType),
				columnRowMapper);
		return resultList;
	}

	@Override
	public String otdrdtfuModlibValidationHWhenValidateTrustGetLowHighFlag() {
		SqlParameter[] sqlParameters = new SqlParameter[] {
				new SqlOutParameter("LOW_HIGH_FLAG", OracleTypes.VARCHAR)
		};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("GET_LOW_HIGH_FLAG").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("LOW_HIGH_FLAG", "");
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
		if (returnObject.get("LOW_HIGH_FLAG") != null) {
			return returnObject.get("LOW_HIGH_FLAG").toString();
		}	
		return null;
	}

	@Override
	public String otdrdtfuModlibValidationHWhenValidateTrustGetTransactionFee(final Long offenderId, final String caseloadId,
			final Long offenderDeductionId, final String transType, final Double txnamount, final String lowHighFlag) {
		SqlParameter[] sqlParameters = new SqlParameter[] {
				new SqlParameter("OFF_ID", OracleTypes.NUMBER),
				new SqlParameter("CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("DED_ID", OracleTypes.NUMBER),
				new SqlParameter("DISBU_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("TXN_AMOUNT", OracleTypes.NUMBER),
				new SqlParameter("LOW_HIGH_FLAG", OracleTypes.VARCHAR),
				new SqlOutParameter("TXN_FEE_AMT", OracleTypes.NUMBER)
		};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("GET_TRANSACTION_FEE").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
				inParamMap.put("OFF_ID", offenderId);
				inParamMap.put("CSLD_ID", caseloadId);
				inParamMap.put("DED_ID", offenderDeductionId);
				inParamMap.put("DISBU_TYPE", transType);
				inParamMap.put("TXN_AMOUNT", txnamount);
				inParamMap.put("LOW_HIGH_FLAG", lowHighFlag);
				inParamMap.put("TXN_FEE_AMT", 0);
				final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
				final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
				if(returnObject.get("TXN_FEE_AMT") != null) {
					return " \n txnFeeAmt : " + returnObject.get("TXN_FEE_AMT").toString();
				}
		return null;
	}

	@Override
	public String otdrdtfuModlibValidationHWhenValidateOffCreditLimit(final Long OffenderId, final String caseloadId,
			final String offenderBookId, final String transType, final Double creditamt, final String txnusg, final String orcreflg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReferenceCodes otdrdtfuOffCreditLimitTransactionTypesC(final String txnType) {
		final String sql = getQuery("OTDRDTFU_OFF_CREDIT_LIMIT_TRANSACTION_TYPES_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class,  transactionTypesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("txnType", txnType), columnRowMapper);
	}

	@Override
	public String otdrdtfuOffCreditLimitAmountWrittenOffC(final String caseloadId, final Long offenderId, final String deductionType, final Date fromDate, final Date toDate) {
		final String sql = getQuery("OTDRDTFU_OFF_CREDIT_LIMIT_AMOUNT_WRITTEN_OFF_C");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadid", caseloadId, "offenderid", offenderId, "obligationtype" , deductionType, "fromdate", fromDate,
				"todate", toDate), String.class);
	}

	@Override
	public Map<String, Object> otdrdtfuOffCreditLimitTrustGetOffenderSubBalance(String pCsldId, Long pOffId, String pSubActType,
			Double pAmount, Double pMinbal, Long pInddays, Date pInddate, Long pTrstcode, String pLockFlag,
			String txntype, String modName, String pSetupCsldId) {
		
			SqlParameter[] sqlParameters = new SqlParameter[] {
					new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
					new SqlParameter("P_OFF_ID", OracleTypes.NUMBER),
					new SqlParameter("P_SUB_ACT_TYPE", OracleTypes.VARCHAR),
					new SqlOutParameter("P_AMOUNT", OracleTypes.NUMBER),
					new SqlInOutParameter("P_MINBAL", OracleTypes.NUMBER),
					new SqlOutParameter("P_INDDAYS", OracleTypes.NUMBER),
					new SqlOutParameter("P_INDDATE", OracleTypes.DATE),
					new SqlInOutParameter("P_TRSTCODE", OracleTypes.NUMBER),
					new SqlParameter("P_LOCK_FLAG", OracleTypes.VARCHAR),
					new SqlParameter("TXNTYPE", OracleTypes.VARCHAR),
					new SqlParameter("MOD_NAME", OracleTypes.VARCHAR),
					new SqlParameter("P_SETUP_CSLD_ID", OracleTypes.VARCHAR),
			};
			SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
					.withCatalogName("TRUST").withProcedureName("GET_OFFENDER_SUB_BALANCE").declareParameters(sqlParameters);
			Map<String, Object> inParamMap = new HashMap<String, Object>();
					inParamMap.put("P_CSLD_ID", pCsldId);
					inParamMap.put("P_OFF_ID", pOffId);
					inParamMap.put("P_SUB_ACT_TYPE", null);
					inParamMap.put("P_MINBAL", pMinbal);
					inParamMap.put("P_TRSTCODE", pTrstcode);
					inParamMap.put("P_LOCK_FLAG", pLockFlag);
					inParamMap.put("TXNTYPE", txntype);
					inParamMap.put("MOD_NAME", modName);
					inParamMap.put("P_SETUP_CSLD_ID", pSetupCsldId);
					final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
					final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
					if(returnObject.get("P_AMOUNT") != null) {
						return returnObject;
					}
			return null;
//		
	}

	@Override
	public Long otdrdtfuGetDebitActCode(String txnType, String csldId) {
		final String sql = getQuery("OTDRDTFU_GET_DEBIT_ACT_CODE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("txnType", txnType, "csldId", csldId), Long.class);
	}

	@Override
	public String otdrdtfuOffCreditLimitFetchCrob(String obligationType) {
		final String sql = getQuery("OTDRDTFU_OFF_CREDIT_LIMIT_FETCH_CROB");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("obligationtype", obligationType), String.class);
	}

	@Override
	public String otdrdtfuOffCreditLimitFetchIndigentflag(String caseloadId, String obligationType) {
		final String sql = getQuery("OTDRDTFU_OFF_CREDIT_LIMIT_FETCH_INDIGENTFLAG");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadid", caseloadId, "obligationtype", obligationType), String.class);
	}

	@Override
	public String otdrdtfuOffCreditLimitFetchCtrWashSpecific() {
		final String sql = getQuery("OTDRDTFU_OFF_CREDIT_LIMIT_FETCH_CTR_WASH_SPECIFIC");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	@Override
	public ReferenceCodes otdrdtfuOffCreditLimitFetchMaxLimitPeriodtype(String caseloadId, Long offenderId,
			String ObligationType) {
		final String sql = getQuery("OTDRDTFU_OFF_CREDIT_LIMIT_FETCH_MAX_LIMIT_PERIODTYPE");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class,  transactionTypesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadid", caseloadId,"offenderid", offenderId, "obligationtype", ObligationType), columnRowMapper);
	}

	@Override
	public ReferenceCodes otdrdtfuOffCreditLimitFetchMaxLimitPeriodtypeWithoutOffenderId(String caseloadId,
			String ObligationType) {
		final String sql = getQuery("OTDRDTFU_OFF_CREDIT_LIMIT_FETCH_MAX_LIMIT_PERIODTYPE_WITHOUT_OFFENDER_ID");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class,  transactionTypesMapping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadid", caseloadId, "obligationtype", ObligationType), columnRowMapper);
	}

	@Override
	public String otdrdtfuOffCreditLimitFetchWeekday() {
		final String sql = getQuery("OTDRDTFU_OFF_CREDIT_LIMIT_FETCH_WEEKDAY");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	@Override
	public Date otdrdtfuOffCreditLimitFetchFromdate(String weekDay) {
		final String sql = getQuery("OTDRDTFU_OFF_CREDIT_LIMIT_FETCH_FROMDATE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("weekday", weekDay), Date.class);
	}

	@Override
	public Date otdrdtfuOffCreditLimitFetchTodate(String weekDay) {
		final String sql = getQuery("OTDRDTFU_OFF_CREDIT_LIMIT_FETCH_TODATE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("weekday", weekDay), Date.class);
	}

	@Override
	public Date otdrdtfuOffCreditLimitFetchFromdateOnMonth() {
		final String sql = getQuery("OTDRDTFU_OFF_CREDIT_LIMIT_FETCH_FROMDATE_ON_MONTH");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Date.class);
	}

	@Override
	public Date otdrdtfuOffCreditLimitFetchTodateOnMonth() {
		final String sql = getQuery("OTDRDTFU_OFF_CREDIT_LIMIT_FETCH_TODATE_ON_MONTH");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Date.class);
	}

	@Override
	public Double getPreviousLoanAmountTotalTakenC(Long offenderId, String deductionType, String caseloadId,
			Date fromDate, Date toDate) {
		final String sql = getQuery("OTDRDTFU_GET_PREVIOUS_LOAN_AMOUNT_TOTAL_TAKEN_C");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("pOffenderId", offenderId, "pDedType", deductionType, "caseloadid", caseloadId,  "pFromDate", fromDate, "pToDate", toDate), Double.class);
	}

	@Override
	public Double getPreviousLoanAmountTotalReversedC(Long offenderId, String deductionType, String caseloadId,
			Date fromDate, Date toDate) {
		final String sql = getQuery("OTDRDTFU_GET_PREVIOUS_LOAN_AMOUNT_TOTAL_REVERSED_C");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("pOffenderId", offenderId, "pDedType", deductionType, "caseloadid", caseloadId,  "pFromDate", fromDate, "pToDate", toDate), Double.class);
	}

	@Override
	public ChkFreezeDisbursements chkDisbursementFreeze(ChkFreezeDisbursements chkFreezeDisbursements) {
		SqlParameter[] sqlParameters = new SqlParameter[] {
				new SqlParameter("p_csld_id", OracleTypes.VARCHAR),
				new SqlParameter("p_off_id", OracleTypes.NUMBER),
				new SqlParameter("p_txn_type", OracleTypes.VARCHAR),
				new SqlParameter("p_acnt_code", OracleTypes.NUMBER),
				new SqlInOutParameter("p_csld_type", OracleTypes.VARCHAR),
				new SqlInOutParameter("P_MODULE_NAME", OracleTypes.VARCHAR),
				new SqlOutParameter("p_date", OracleTypes.DATE),
				new SqlOutParameter("frz_flag", OracleTypes.VARCHAR),
				
		};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("CHK_FREEZE_DISBURSEMENTS").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
				inParamMap.put("p_csld_id", chkFreezeDisbursements.getpCsldId());
				inParamMap.put("p_off_id", chkFreezeDisbursements.getpOffId());
				inParamMap.put("p_txn_type", chkFreezeDisbursements.getpTxnType());
				inParamMap.put("p_acnt_code", null);
				inParamMap.put("p_csld_type", chkFreezeDisbursements.getpCsldType());
				
				inParamMap.put("p_date", null);
				inParamMap.put("P_MODULE_NAME", chkFreezeDisbursements.getpModuleName());
				
				
				final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
				final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
				final ChkFreezeDisbursements returnObj = chkFreezeDisbursements;
				if (returnObject.get("frz_flag") != null) {
					returnObj.setFrzFlag(returnObject.get("frz_flag").toString());
				}
		return returnObj;
	}

	@Override
	public String chkAccountStatus(final String caseloadId, final Long offenderId) {
		SqlParameter[] sqlParameters = new SqlParameter[] {
				new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER)};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("CHK_ACCOUNT_STATUS").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
				inParamMap.put("P_CSLD_ID", caseloadId);
				inParamMap.put("P_OFFENDER_ID", offenderId);
				
				final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
				final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
				
				if (returnObject.get("P_OPEN_AN_ACCOUNT") != null) {
					return returnObject.get("P_OPEN_AN_ACCOUNT").toString();
				}
		return "";
	}

	@Override
	public Integer reopenOffenerTrustAccount(String caseloadId, Long offenderId, String userName) {
		final String sql = getQuery("OTDRDTFU_REOPEN_OFFENER_TRUST_ACCOUNT");
		Map<String,Object>paramMap = new HashMap<>();
		paramMap.put("offenderId", offenderId);
		paramMap.put("caseloadId", caseloadId);
		paramMap.put("modifyUserId", userName);
		
		return namedParameterJdbcTemplate.update(sql, paramMap);
	}

	@Override
	public List<OtrdreceReportBean> otdrdtfugenerateotrdrecereport(OtrdreceReportBean paramBean) {
		final String sql = getQuery("OTDRDTFU_OFF_CREDIT_LIMIT_TRANSACTION_TYPES_C");
		final RowMapper<OtrdreceReportBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,OtrdreceReportBean.class,  reportingMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	@Override
	public List<OtrreceiReportBean> otdrdtfugenerateOtrreceireport(OtrreceiReportBean paramBean) {
		final String sql = getQuery("OTDRDTFU_GENERATE_OTRRECEI_REPORT");
		final RowMapper<OtrreceiReportBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,OtrreceiReportBean.class,  reportingMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("receiptNumber", paramBean.getRecNum(), "caseloadId", paramBean.getCaseloadId(), "caseloadType", paramBean.getCaseloadType()), columnRowMapper);
	}
	
	@Override
	public List<OtrdreceReportBean> otdrdtfugenerateOtrdrecereport(OtrdreceReportBean paramBean) {
		final String sql = getQuery("OTDRDTFU_GENERATE_OTRDRECE_REPORT");
		final RowMapper<OtrdreceReportBean> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,OtrdreceReportBean.class,  reportingMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("receiptNumber", paramBean.getRecNum(), "caseloadId", paramBean.getCaseloadId(), "caseloadType", paramBean.getCaseloadType()), columnRowMapper);
	}

	@Override
	public Corporates getCorp(Long cropId) {
		final String sql = getQuery("OTDRDTFU_MAIN_PROCESS_GET_CROP");
		final RowMapper<Corporates> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,Corporates.class,  corporateNameMapping);
		try {
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("corpId", cropId), columnRowMapper);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Persons getPerson(final Integer payeePersonId) {
		final String sql = getQuery("OTDRDTFU_MAIN_PROCESS_GET_PERSON");
		final RowMapper<Persons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,Persons.class,  personsMapping);
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("personId", payeePersonId), columnRowMapper);
			} catch (Exception e) {
				return null;
			}
	}

	public Map<String, Object> getSubActType(String moduleName, String txnType, String txnUsage, String caseloadId) {
		
			
		String procedureQuery = "{CALL OMS_OWNER.TRUST.GET_SUB_ACT_TYPE(:P_MODULE_NAME, :P_TXN_TYPE, :P_TXN_USAGE, :P_TXN_POST_TYPE, :P_SUB_ACT_TYPE, :CSLD_ID)}";
		try(Connection connection = dataSource.getConnection()) {
		 CallableStatement callableStatement = connection.prepareCall(procedureQuery);
		 callableStatement.setString("P_MODULE_NAME", moduleName);
		 callableStatement.setString("P_TXN_TYPE", txnType);
		 callableStatement.setString("P_TXN_USAGE", txnUsage);
		 callableStatement.registerOutParameter("P_TXN_POST_TYPE", Types.VARCHAR);
		 callableStatement.registerOutParameter("P_SUB_ACT_TYPE", Types.VARCHAR);
		 callableStatement.setString("CSLD_ID", caseloadId);
		 callableStatement.execute();
		 Map<String,Object> returnMapData = new HashMap<>();
		 returnMapData.put("P_TXN_POST_TYPE", callableStatement.getString("P_TXN_POST_TYPE"));
		 returnMapData.put("P_SUB_ACT_TYPE", callableStatement.getString("P_SUB_ACT_TYPE"));
		 return returnMapData;
		} catch (Exception e) {
			logger.error("Error in OMS_OWNER.TRUST.GET_SUB_ACT_TYPE call", e);
		}
		return null;
	}

	@Override
	public String txnIdCur(final Integer txnId) {
		try {
			final String sql = getQuery("OTDRDTFU_MAIN_PROCESS_TXN_ID_CUR");
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("txnId", txnId), String.class);
		}catch (Exception e) {
			logger.error("OTDRDTFU_MAIN_PROCESS_TXN_ID_CUR", e);
		}
		return null;
	}

	@Override
	public Integer genTrustTrans(String seqId) {
		try {
			final String sql = getQuery("OTDRDTFU_GEN_TRUST_TRANS");
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("seq",seqId), Integer.class);
		}catch (Exception e) {
			logger.error("OTDRDTFU_MAIN_PROCESS_TXN_ID_CUR", e);
		}
		return null;
	}

	@Override
	public String creditObligExistsC(String txnType, String caseloadId) {
		try {
			final String sql = getQuery("OTDRDTFU_CREDIT_OBLIG_EXISTS_C");
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("txnType", txnType, "caseloadType", caseloadId), String.class);
		}catch (Exception e) {
			logger.error("CREDIT_OBLIG_EXISTS_C", e);
		}
		return null;
	}

	@Override
	public Map<String, Object> trustChkOverdrawn(String caseloadId, Long offenderId, String subAccountType, String txnType,
			Double txnEntryAmount, String modelName, Integer txnId, Long offenderBookId, Double totTxnFee, Integer txnEntrySeq) {

		
		SqlParameter[] sqlParameters = new SqlParameter[] {
				new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFF_ID", OracleTypes.NUMBER),
				new SqlParameter("P_SUB_ACT_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("TRANS_AMOUNT", OracleTypes.NUMBER),
				new SqlParameter("TXNTYPE", OracleTypes.VARCHAR),
				new SqlInOutParameter("SEQ_NO", OracleTypes.NUMBER),
				new SqlInOutParameter("CHECK_IND", OracleTypes.VARCHAR),
				new SqlParameter("MOD_NAME", OracleTypes.VARCHAR),
				new SqlParameter("P_TXN_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFF_BID", OracleTypes.NUMBER),
				new SqlParameter("P_TXN_FEE", OracleTypes.NUMBER),
				new SqlParameter("P_SETUP_CASELOAD", OracleTypes.VARCHAR),
				
		};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("CHK_OVERDRAWN").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("P_CSLD_ID", caseloadId);
			inParamMap.put("P_OFF_ID", offenderId);
			inParamMap.put("P_SUB_ACT_TYPE", subAccountType);
			inParamMap.put("TRANS_AMOUNT", txnEntryAmount);
			inParamMap.put("TXNTYPE", txnType);
			inParamMap.put("SEQ_NO", txnEntrySeq);
			inParamMap.put("CHECK_IND", "Y");
			inParamMap.put("MOD_NAME", modelName);
			inParamMap.put("P_TXN_ID", txnId);
			inParamMap.put("P_OFF_BID", offenderBookId);
			inParamMap.put("P_TXN_FEE", totTxnFee);
			inParamMap.put("P_SETUP_CASELOAD", null);
				
				final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
				final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
			
		return returnObject;
	}

	public Integer txnSeqCur(Integer txnId, Integer txnEntrySeq) {
		try {
			final String sql = getQuery("OTDRDTFU_TXN_SEQ_CUR");
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("txnId", txnId, "txnEntrySeq", txnEntrySeq), Integer.class);
		}catch (Exception e) {
			logger.error("OTDRDTFU_TXN_SEQ_CUR", e);
		}
		return null;
	}
	
	// OFFENDER_TRANSACTIONS_COMMIT
	
	public Integer offenderTransactionsCommit(final List<OffenderTransactions> offenderTransactions) {
		String sql = getQuery("OTDRDTFU_OFFENDER_TRANSACTIONS_COMMIT");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderTransactions personIdentifiers : offenderTransactions) {
			parameters.add(new BeanPropertySqlParameterSource(personIdentifiers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (offenderTransactions.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Map<String, Object> processGlTransNew(String caseloadId, String txnType, Object object,
			Double txnEntryAmount, Integer txnId, Date txnEntryDate, String txnEntryDesc, Integer txnEntrySeq,
			String string, Long offenderId, Long offenderBookId, Object object2, String subAccountType,
			Integer payeePersonId, Integer payeeCorporateId, String payeeNameText, String string2) {
		String query = getQuery("OTDRDTFU_TRUST_PROCESS_GL_TRANS_NEW");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();

			inParamMap.put("P_CSLD_ID", caseloadId);
			inParamMap.put("P_TRANS_TYPE", txnType);
			inParamMap.put("P_OPERATION_TYPE", null);
			if (txnEntryAmount != null) {
			inParamMap.put("P_TRANS_AMOUNT", txnEntryAmount);
			} else {
				inParamMap.put("p_trans_amount", 0);	
			}
			inParamMap.put("P_TRANS_NUMBER", txnId);
			inParamMap.put("P_TRANS_DATE", txnEntryDate); // new java.sql.Date(txnEntryDate.getTime()));
			inParamMap.put("P_TRANS_DESC", txnEntryDesc);
			inParamMap.put("P_TRANS_SEQ", txnEntrySeq);
			inParamMap.put("P_MODULE_NAME", string);
			inParamMap.put("P_OFF_ID", offenderId);
			inParamMap.put("P_OFF_BOOK_ID", offenderBookId);
			inParamMap.put("P_SUB_ACT_TYPE_DR", object2);
			inParamMap.put("P_SUB_ACT_TYPE_CR", subAccountType);
			inParamMap.put("P_PAYEE_PERS_ID", payeePersonId);
			inParamMap.put("P_PAYEE_CORP_ID", payeeCorporateId);
			inParamMap.put("P_PAYEE_NAME_TEXT", payeeNameText);
			inParamMap.put("P_GL_SQNC", 0);
			inParamMap.put("P_OFF_DED_ID", null);
			
			namedParameterJdbcTemplate.update(query, inParamMap);
				
				final Map<String, Object> returnObject = new HashMap<>(); 
				if (returnObject != null) {
					returnObject.put("P_GL_SQNC", 0);
				}
				return returnObject;
	}

	@Override
	public Map<String, Object> processTransactionFee(String string, String caseloadId, Long offenderId,
			Long offenderBookId, Integer txnId, Integer txnEntrySeq, String txnType, Double txnEntryAmount,
			Date txnEntryDate, String txnUsage) {
	
		SqlParameter[] sqlParameters = new SqlParameter[] {
				new SqlParameter("mod_name", OracleTypes.VARCHAR),
				new SqlParameter("csld_id", OracleTypes.VARCHAR),
				new SqlParameter("off_id", OracleTypes.NUMBER),
				new SqlParameter("off_bid", OracleTypes.NUMBER),
				new SqlParameter("trans_id", OracleTypes.NUMBER),
				new SqlInOutParameter("trans_seq", OracleTypes.NUMBER),
				new SqlParameter("disbu_type", OracleTypes.VARCHAR),
				new SqlParameter("txn_amount", OracleTypes.NUMBER),
				new SqlParameter("trans_date", OracleTypes.DATE),
				new SqlParameter("trans_usage", OracleTypes.VARCHAR),
				
				
		};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("PROCESS_TRANSACTION_FEE").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();

			inParamMap.put("mod_name", string);
			inParamMap.put("csld_id", caseloadId);
			inParamMap.put("off_id", offenderId);
			inParamMap.put("off_bid", offenderBookId);
			inParamMap.put("trans_id", txnId);
			inParamMap.put("trans_seq", txnEntrySeq);
			inParamMap.put("disbu_type", txnType);
			inParamMap.put("txn_amount", txnEntryAmount);
			inParamMap.put("trans_date", txnEntryDate);
			inParamMap.put("trans_usage", txnUsage);
			
				
				final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
				final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
				if (returnObject != null) {
					return returnObject;
				}
		return null;
	}

	@Override
	public String updateOffenderBalance(String caseloadId, Long offenderId, String txnPostingType, Date txnEntryDate,
			Integer txnId, String txnType, Double txnEntryAmount, String subAccountType, String string) {
		SqlParameter[] sqlParameters = new SqlParameter[] {
				
				new SqlParameter("p_csld_id", OracleTypes.VARCHAR),
				new SqlParameter("p_off_id", OracleTypes.NUMBER),
				new SqlParameter("p_trans_post_type", OracleTypes.VARCHAR),
				new SqlParameter("p_trans_date", OracleTypes.DATE),
				new SqlParameter("p_trans_number", OracleTypes.NUMBER),
				new SqlInOutParameter("p_trans_type", OracleTypes.VARCHAR),
				new SqlParameter("p_trans_amount", OracleTypes.NUMBER),
				new SqlParameter("p_sub_act_type", OracleTypes.VARCHAR),
				new SqlParameter("p_allow_overdrawn", OracleTypes.VARCHAR),
				
				
		};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("UPDATE_OFFENDER_BALANCE").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();

			inParamMap.put("p_csld_id", caseloadId);
			inParamMap.put("p_off_id", offenderId);
			inParamMap.put("p_trans_post_type", txnPostingType);
			inParamMap.put("p_trans_date", txnEntryDate);
			inParamMap.put("p_trans_number", txnId);
			inParamMap.put("p_trans_type", txnType);
			inParamMap.put("p_trans_amount", txnEntryAmount);
			inParamMap.put("p_sub_act_type", subAccountType);
			inParamMap.put("p_allow_overdrawn", string);
			
				
				final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
				final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
				if (returnObject != null) {
					return "";
				}
		return null;
	}

	@Override
	public Integer curDays(String txnType) {
		String sql = getQuery("OTDRDTFU_MAIN_PROCESS_DAYS_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("txnType", txnType), Integer.class);
		}catch (Exception e) {
			logger.error("OTDRDTFU_MAIN_PROCESS_DAYS_CUR ", e);
		}
		return null;
	}

	@Override
	public void processHold(Integer txnId, String caseloadId, Long offenderId, String txnType, Integer holdDays,
			String subAccountType, Double txnEntryAmount, String txnEntryDesc, String txnReferenceNumber, Integer txnnum,
			Integer holdNumbers) {
		
		final String sql = getQuery("OTDRDTFU_OMS_OWNER_PROCESS_HOLD");
		Map<String, Object> inParamMap = new HashMap<String, Object>();

			inParamMap.put("P_TXN_ID", txnId);
			inParamMap.put("P_CSLD_ID", caseloadId);
			inParamMap.put("P_OFF_ID", offenderId);
			inParamMap.put("P_ORG_TXN_TYPE", txnType);
			inParamMap.put("P_HOLD_DAYS", holdDays);
			inParamMap.put("P_SUB_ACCOUNT_TYPE", subAccountType);
			inParamMap.put("P_TOT_AMT", txnEntryAmount);
			inParamMap.put("P_TXN_DESC", txnEntryDesc);
			inParamMap.put("P_TXN_REF_NUM", txnReferenceNumber);
			inParamMap.put("P_TXN_NUM", txnnum);
			inParamMap.put("P_HOLD_NUMBER", holdNumbers);
			
			namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public Map<String, Object> financialDoDuctionsFinancial(String caseloadId, Long offenderId, Long offenderBookId,
			String txnType, Integer txnId, Date txnEntryDate, String subAccountType, String string,
			Double txnEntryAmount, int i, Double txnEntryAmount2, Integer txnEntrySeq, String string2) {
		try { 
		final String query = getQuery("OTDRDTFU_OMS_OWNER_FINANCIAL_DO_DEDUCTIONS_FINANCIAL");
		 Map<String, Object> paramMap = new HashMap<>();
		 paramMap.put("P_CSLD_ID", caseloadId);
		 paramMap.put("P_OFF_ID", offenderId);
		 paramMap.put("P_OFF_BOOK_ID", offenderBookId);
		 paramMap.put("P_TRANS_TYPE", txnType);
		 paramMap.put("P_TRANS_NUMBER", txnId);
		 paramMap.put("P_TRANS_DATE", txnEntryDate);
		 paramMap.put("P_SUB_ACT_TYPE", subAccountType);
		 paramMap.put("P_DED_FLAG", string);
		 paramMap.put("P_RECEIPT_AMOUNT", txnEntryAmount);
		 paramMap.put("P_SHADOW_ID", i);
		 paramMap.put("P_DED_AMOUNT", txnEntryAmount2);
		 paramMap.put("TXN_SEQUENCE", txnEntrySeq);
		 paramMap.put("P_INFO_NUMBER", "");
		 namedParameterJdbcTemplate.update(query, paramMap);
		 
		 
		} catch (Exception e) {
			logger.error("Error at FINANCIAL.DO_DUCTIONS_FINANCIAL", e);
		}
		return null;
	}

	@Override
	public void deductionGetAcAndSetIndDate(Long offenderId, String caseloadId) {
		final String sql = getQuery("OTDRDTFU_DEDUCTIONS_GET_AC_AND_SET_IND_DATE");
		namedParameterJdbcTemplate.update(sql, createParams("P_OFF_ID", offenderId, "P_CSLD_ID", caseloadId));

	}

	@Override
	public void insetIntoChequeData(String caseloadId, Integer txnId, Double txnEntryAmount, String string,
			Double txnEntryAmount2, Double txnEntryAmount3, Integer payeeId, Integer payeeCorporateId, String payeeName,
			String string2, String string3, String string4, String string5, String txnType) {
		
		
				SqlParameter[] sqlParameters = new SqlParameter[] {
				
				new SqlParameter("p_csld_id", OracleTypes.VARCHAR),
				new SqlParameter("p_trans_number", OracleTypes.NUMBER),
				new SqlParameter("p_trans_amount", OracleTypes.NUMBER),
				new SqlParameter("p_cheque_flag", OracleTypes.VARCHAR),
				new SqlParameter("p_start_txn_id", OracleTypes.NUMBER),
				new SqlParameter("p_end_txn_id", OracleTypes.NUMBER),
				new SqlParameter("p_pers_payee_id", OracleTypes.NUMBER),
				new SqlParameter("p_corp_payee_id", OracleTypes.NUMBER),
				new SqlParameter("p_payee_name", OracleTypes.VARCHAR),
				new SqlParameter("p_offender_payee", OracleTypes.NUMBER),
				new SqlParameter("p_single_entry", OracleTypes.VARCHAR),
				new SqlParameter("p_bank_act_code", OracleTypes.NUMBER),
				new SqlParameter("p_module_name", OracleTypes.VARCHAR),
				new SqlParameter("p_trans_type", OracleTypes.VARCHAR),
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("INSERT_INTO_CHEQUE_DATA").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();

			inParamMap.put("p_csld_id", caseloadId);
			inParamMap.put("p_trans_number", txnId);
			inParamMap.put("p_trans_amount", txnEntryAmount);
			inParamMap.put("p_cheque_flag", "O");
			inParamMap.put("p_start_txn_id", txnEntryAmount2);
			inParamMap.put("p_end_txn_id", txnEntryAmount3);
			inParamMap.put("p_pers_payee_id", payeeId);
			inParamMap.put("p_corp_payee_id", payeeCorporateId);
			inParamMap.put("p_payee_name", payeeName);
			inParamMap.put("p_offender_payee", 0);
			inParamMap.put("p_single_entry", "1");
			inParamMap.put("p_bank_act_code", null);
			inParamMap.put("p_module_name", string5);
			inParamMap.put("p_trans_type", txnType);
			
			
				
				final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
				 simpleJDBCCall.execute(inParameter);

		
	}

	@Override
	public String getTxnType(String moduleName, String caseloadId) {
		final String sql = getQuery("OTDRDTFU_GET_R_TXN_TYPE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("moduleName", moduleName, "caseloadId", caseloadId), String.class);
			
		} catch (Exception e) {
			logger.error("GET_R_TXN_TYPE", e);
		}
		return null;
	}

	@Override
	public void trustInsertIntoOffenderTrans(Integer txnNum, Integer txnEntrySeq, String caseloadId, Long offenderId,
			Long offenderBookId, String txnPostingType, String txnType, String txnDesc, Double txnAmount, Date txnData,
			String subAccountType, String pDeductionFlag, Double pPreDedAmount, String pDeductionType,
			Integer pPayeeCorpId, Integer pPayeePersonId, String pInfoNumber, String rSlipFlag,
			String pAllowOverdrawn) {
		Integer genSeq = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_trans_number", txnNum);
		inParamMap.put("p_trans_seq", txnEntrySeq);
		inParamMap.put("p_csld_id", caseloadId);
		inParamMap.put("p_off_id", offenderId);
		inParamMap.put("p_off_book_id", offenderBookId);
		inParamMap.put("p_trans_post_type", txnPostingType);
		inParamMap.put("p_trans_type", txnType);
		inParamMap.put("p_trans_desc", txnDesc);
		inParamMap.put("p_trans_amount", new BigDecimal(0.0));
		inParamMap.put("p_trans_date", txnData);
		inParamMap.put("p_sub_act_type", subAccountType);
		inParamMap.put("p_deduction_flag", pDeductionFlag);
		inParamMap.put("p_pre_ded_amount", pPreDedAmount);
		inParamMap.put("p_deduction_type", pDeductionType);
		inParamMap.put("p_payee_corp_id", pPayeeCorpId);
		inParamMap.put("p_payee_person_id", pPayeePersonId);
		inParamMap.put("p_info_number", pInfoNumber);
		inParamMap.put("p_slip_print_flag", rSlipFlag);
		inParamMap.put("p_allow_overdrawn", pAllowOverdrawn);
					namedParameterJdbcTemplate.update(
					" call OMS_OWNER.TRUST.INSERT_INTO_OFFENDER_TRANS(:p_trans_number, :p_trans_seq, :p_csld_id, :p_off_id, :p_off_book_id, :p_trans_post_type, "
							+ " :p_trans_type, :p_trans_desc, :p_trans_amount, :p_trans_date, :p_sub_act_type, :p_deduction_flag, :p_pre_ded_amount, :p_deduction_type, "
							+ " :p_payee_corp_id, :p_payee_person_id, :p_info_number, :p_slip_print_flag, :p_allow_overdrawn)",
					inParamMap);
			genSeq = 1;
		}
	
	
	public List<AccountCodes> processGlTransNewPostingC(String txnType, String modelName, String txnOperationType, String subAccountType) {
		final String sql = getQuery("OTDRDTFU_PROCESS_GL_TRANS_NEW_POSTING_C");
		try {
			final RowMapper<AccountCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,accountCodeMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("txnType", txnType, "modelName", modelName, "txnOperationType", txnOperationType,
				"subAccountType", subAccountType), rowMapper);
		}catch (Exception e) {
			return null;
		}
		
	}
	
	public void  trustInsertGltransNew(InsertGlTransNew paramBean) {
				SqlParameter[] sqlParameters = new SqlParameter[] {
		
				new SqlParameter("p_post_type", OracleTypes.VARCHAR),
				new SqlParameter("p_account_code", OracleTypes.NUMBER),
				new SqlParameter("p_acnt_posting", OracleTypes.VARCHAR),
				new SqlParameter("p_csld_id", OracleTypes.VARCHAR),
				new SqlParameter("p_trans_type", OracleTypes.VARCHAR),
				new SqlParameter("p_trans_amount", OracleTypes.NUMBER),
				new SqlParameter("p_trans_number", OracleTypes.NUMBER),
				new SqlParameter("p_trans_date", OracleTypes.DATE),
				new SqlParameter("p_trans_desc", OracleTypes.VARCHAR),
				new SqlParameter("p_trans_seq", OracleTypes.NUMBER),
				new SqlParameter("p_gl_sqnc", OracleTypes.NUMBER),
				new SqlParameter("p_off_id", OracleTypes.NUMBER),
				new SqlParameter("p_off_book_id", OracleTypes.NUMBER),
				new SqlParameter("p_info_number", OracleTypes.VARCHAR),
				new SqlParameter("p_payee_person_id", OracleTypes.NUMBER),
				new SqlParameter("p_payee_corporate_id", OracleTypes.NUMBER),
				new SqlParameter("p_payee_name_text", OracleTypes.VARCHAR),
				new SqlParameter("p_revr_txn_id", OracleTypes.NUMBER),
				new SqlParameter("p_revr_txn_entry_seq", OracleTypes.NUMBER),
				new SqlParameter("p_revr_gl_entry_seq", OracleTypes.NUMBER),
				new SqlParameter("p_txn_ref_number", OracleTypes.VARCHAR),
				new SqlParameter("p_off_ded_id", OracleTypes.NUMBER),
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("INSERT_GL_TRANS_NEW").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();

			inParamMap.put("p_post_type", paramBean.getpPostType());
			inParamMap.put("p_account_code", paramBean.getpAccountCode());
			inParamMap.put("p_acnt_posting", paramBean.getpAcntPosting());
			inParamMap.put("p_csld_id", paramBean.getpCsldId());
			inParamMap.put("p_trans_type", paramBean.getpTransType());
			inParamMap.put("p_trans_amount", paramBean.getpTransAmount());
			inParamMap.put("p_trans_number", paramBean.getpTransNumber());
			inParamMap.put("p_trans_date", paramBean.getpTransDate());
			inParamMap.put("p_trans_desc", paramBean.getpTransDesc());
			inParamMap.put("p_trans_seq", paramBean.getpTransSeq());
			inParamMap.put("p_gl_sqnc", paramBean.getpGlSqnc());
			inParamMap.put("p_off_id", paramBean.getpOffId());
			inParamMap.put("p_off_book_id", paramBean.getpOffBookId());
			inParamMap.put("p_info_number", paramBean.getpInfoNumber());
			inParamMap.put("p_payee_person_id", paramBean.getpPayeePersonId());
			inParamMap.put("p_payee_corporate_id", paramBean.getpPayeeCorporateId());
			inParamMap.put("p_payee_name_text", paramBean.getpPayeeNameText());
			inParamMap.put("p_revr_txn_id", paramBean.getpRevrTxnId());
			inParamMap.put("p_revr_txn_entry_seq", paramBean.getpRevrTxnEntrySeq());
			inParamMap.put("p_txn_ref_number", paramBean.getpTxnRefNumber());
			inParamMap.put("p_off_ded_id", paramBean.getpOffDedId());
			
				
				final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
				 simpleJDBCCall.execute(inParameter);
	}

	@Override
	public List<String> getAcAndSetIndDateChkCaseloadC(String caseloadId) {
		final String sql = getQuery("OTDRDTFU_GET_AC_AND_SET_IND_DATE_CHK_CASELOAD_C");
		try {
			return namedParameterJdbcTemplate.queryForList(sql, createParams("caseloadId",caseloadId), String.class);
		} catch (Exception e) {
			logger.error("GET_AC_AND_SET_IND_DATE_CHK_CASELOAD_C", e);
		}
		return null;
	}

	@Override
	public List<Integer> getAcAndSetIndDateChkIndAcC(Long offenderId) {
		final String sql = getQuery("OTDRDTFU_GET_AC_AND_SET_IND_DATE_CHK_IND_AC_C");
		try {
			return namedParameterJdbcTemplate.queryForList(sql, createParams("offenderId",offenderId), Integer.class);
		} catch (Exception e) {
			logger.error("GET_AC_AND_SET_IND_DATE_CHK_IND_AC_C", e);
		}
		return null;
	}

	@Override
	public void updateIndigentDate(Integer indAc, Long offenderId, String caseloadId) {
		SqlParameter[] sqlParameters = new SqlParameter[] {
				
				new SqlParameter("p_acnt_code", OracleTypes.NUMBER),
				new SqlParameter("p_off_id", OracleTypes.NUMBER),
				new SqlParameter("p_csld_id", OracleTypes.CHAR),				
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("DEDUCTIONS").withProcedureName("UPDATE_INDIGENT_DATE").declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();

			inParamMap.put("p_acnt_code", indAc);
			inParamMap.put("p_off_id", offenderId);
			inParamMap.put("p_csld_id", caseloadId);
									
			final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
			simpleJDBCCall.execute(inParameter);
		
	}

	@Override
	public SystemProfiles getAcAndSetIndDateSystemProfileC() {
		final String sql = getQuery("OTDRDTFU_GET_AC_AND_SET_IND_DATE_SYSTEM_PROFILE_C");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,SystemProfiles.class,  systemProfilesMapping);
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("GET_AC_AND_SET_IND_DATE_SYSTEM_PROFILE_C", e);
		}
		return null;
	}

	@Override
	public Integer getTrustAccountCodeC() {
		final String sql = getQuery("OTDRDTFU_GET_AC_AND_SET_IND_DATE_GET_TRUST_ACCOUNT_CODE_C");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error("GET_AC_AND_SET_IND_DATE_GET_TRUST_ACCOUNT_CODE_C", e);
		}
		return null;
	}

	@Override
	public Double sumOffSubActBalC(Long offenderId, Integer lvTrustAccountCode) {
		final String sql = getQuery("OTDRDTFU_GET_AC_AND_SET_IND_DATE_SUM_OFF_SUB_ACT_BAL_C");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", offenderId, "lvTrustAccountCode", lvTrustAccountCode), Double.class);
		} catch (Exception e) {
			logger.error("GET_AC_AND_SET_IND_DATE_SUM_OFF_SUB_ACT_BAL_C", e);
		}
		return null;
	}

	@Override
	public Date getAcAndSetIndDateMaxIndDateC(Long offenderId, Integer lvTrustAccountCode) {
		final String sql = getQuery("OTDRDTFU_GET_AC_AND_SET_IND_DATE_MAX_IND_DATE_C");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", offenderId, "lvTrustAccountCode", lvTrustAccountCode), Date.class);
		} catch (Exception e) {
			logger.error("GET_AC_AND_SET_IND_DATE_MAX_IND_DATE_C", e);
		}
		return null;
	}

	@Override
	public Integer getAcAndSetIndUpdateOffenderSubAccounts(Date lvIndigentDate, Integer lvIndigentDaysLimit, Long offenderId, Integer lvTrustAccountCode) {
		final String sql = getQuery("OTDRDTFU_GET_AC_AND_SET_IND_UPDATE_OFFENDER_SUB_ACCOUNTS");
		Integer isCommitted = 0;
		try {
			isCommitted = namedParameterJdbcTemplate.update(sql, createParams("indDate",lvIndigentDate, "indDays", lvIndigentDaysLimit,
					"offId", offenderId, "trustAccountcode", lvTrustAccountCode));
		} catch (Exception e) {
			logger.error("GET_AC_AND_SET_IND_UPDATE_OFFENDER_SUB_ACCOUNTS", e);
		}
		return isCommitted;
	}

	@Override
	public String mainProcessAutoSubmitting() {
		final String sql = getQuery("OTDRDTFU_MAIN_PROCESS_AUTO_SUBMITTING");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (Exception e) {
			logger.error("MAIN_PROCESS_AUTO_SUBMITTING", e);
		} 
		return null;
	}

	@Override
	public Integer reportRecievedUpdate(Integer txnId, String receiptNumber) {
		Integer result = 0;
		try {
			final String sql = getQuery("OTDRDTFU_REPORT_RECIEVED_UPDATE");
			result = namedParameterJdbcTemplate.update(sql, createParams("txnId", txnId, "receiptNumber", receiptNumber));
		} catch (Exception e) {
			logger.error("REPORT_RECIEVED_UPDATE", e);
		}
		return result;
	}

	@Override
	public String numberToWord(BigDecimal amount) {
		final String sql = getQuery("OTDRDTFU_NUMBER_TO_WORD"); 
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("amount", amount), String.class);
		}catch (Exception e) {
			logger.error("NUMBER_TO_WORD", e);
		}
		return null;
	}
	
	@Override
	public String getCurrencySymbol() {
		final String sql = getQuery("OTDRDTFU_GET_CURRENCY_SYMBOL"); 
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		}catch (Exception e) {
			logger.error("OTDRDTFU_GET_CURRENCY_SYMBOL", e);
		}
		return null;
	}
	@Override
	public String deductionsChkOffenderDeductions(final String caseloasdId, final Long offenderId, final String txnType,
			final Integer shadowId) {
		SqlParameter[] sqlParameters = new SqlParameter[] { new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFF_ID", OracleTypes.NUMBER), new SqlParameter("P_TRANS_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_SHADOW_ID", OracleTypes.NUMBER),
				new SqlInOutParameter("P_DED_FLAG", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("DEDUCTIONS").withProcedureName("CHK_OFFENDER_DEDUCTIONS")
				.declareParameters(sqlParameters);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_CSLD_ID", caseloasdId);
		inParamMap.put("P_OFF_ID", offenderId);
		inParamMap.put("P_TRANS_TYPE", txnType);
		inParamMap.put("P_SHADOW_ID", shadowId);
		inParamMap.put("P_DED_FLAG", "");
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		final Map<String, Object> returnObject = simpleJDBCCall.execute(inParameter);
		if (returnObject.get("P_DED_FLAG") != null) {
			return returnObject.get("P_DED_FLAG").toString();
		}

		return "";
	}
	
}


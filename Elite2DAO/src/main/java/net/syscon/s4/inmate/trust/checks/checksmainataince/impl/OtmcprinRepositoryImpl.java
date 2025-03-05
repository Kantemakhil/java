package net.syscon.s4.inmate.trust.checks.checksmainataince.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.BankChequeBooks;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inmate.beans.BankChequeRegisters;
import net.syscon.s4.inmate.trust.checks.checksmainataince.OtmcprinRepository;

/**
 * Class OtmcprinRepositoryImpl
 * 
 */
@Repository
public class OtmcprinRepositoryImpl extends RepositoryBase implements OtmcprinRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger log = LogManager.getLogger(OtmcprinRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("CHEQUE_STATUS", 			new FieldMapper("chequeStatus"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 					new FieldMapper("code")).build();
	private final Map<String, FieldMapper> bankChequeRegistersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CHECK_NUM", 				new FieldMapper("checkNum"))
			.put("CASELOAD_ID", 			new FieldMapper("caseLoadId"))
			.build();
	
	private final Map<String, FieldMapper> bankChequeBooksMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID", 			new FieldMapper("caseloadId"))
			.put("ACCOUNT_CODE", 			new FieldMapper("accountCode"))
			.put("ROW_ID", 					new FieldMapper("rowId"))
			
			.build();
	
	private final Map<String, FieldMapper> listMapping = new ImmutableMap.Builder<String, FieldMapper>()
	.put("VAL", 			new FieldMapper("val"))
	.build();
	
	/**
	 * Creates new OtmcprinRepositoryImpl class Object
	 */
	public OtmcprinRepositoryImpl() {
		 //OtmcprinRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            BankChequeBooks
	 *
	 * @return List<BankChequeBooks>
	 *
	 
	 */

	public List<BankChequeBooks> bankCbExecuteQuery(BankChequeBooks objSearchDao) {
		final String sql = getQuery("OTMCPRIN_BANKCB_FIND_BANK_CHEQUE_BOOKS");
		final RowMapper<BankChequeBooks> BankChequeBooksRowMapper = Row2BeanRowMapper.makeMapping(sql,
				BankChequeBooks.class, bankChequeBooksMapping);
		List<BankChequeBooks> returnList = new ArrayList<BankChequeBooks>();
		String preparedSql = null;
		String preSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao.getCaseloadId() != null) {
			sqlQuery.append(" CASELOAD_ID = :CASELOAD_ID" + " AND  ");
			inParameterSource.addValue("CASELOAD_ID", objSearchDao.getCaseloadId());
		}
		if (objSearchDao.getLastCheckNumber() != null) {
			sqlQuery.append("LAST_CHECK_NUMBER = :LAST_CHECK_NUMBER" + " AND  ");
			inParameterSource.addValue("LAST_CHECK_NUMBER", objSearchDao.getLastCheckNumber());
		}
		if (objSearchDao.getNextCheckNumber() != null) {
			sqlQuery.append("NEXT_CHECK_NUMBER = :NEXT_CHECK_NUMBER" + " AND  ");
			inParameterSource.addValue("NEXT_CHECK_NUMBER", objSearchDao.getNextCheckNumber());
		}
		preparedSql = sqlQuery.toString().trim();

		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSql = preparedSql.concat("  ORDER BY CASELOAD_ID ,ACCOUNT_CODE ,TXN_ENTRY_DATE DESC");
		try {
		 returnList =  namedParameterJdbcTemplate.query(preSql,inParameterSource, BankChequeBooksRowMapper);
		} catch (Exception e){
			log.error("bankCbExecuteQuery",e);
			
		}
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstBankChequeBooks
	 *            List<BankChequeBooks>
	 *
	 
	 */
	public Integer bankCbUpdateBankChequeBooks(final List<BankChequeBooks> lstBankChequeBooks) {
		String sql = getQuery("OTMCPRIN_BANKCB_UPDATE_BANK_CHEQUE_BOOKS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (BankChequeBooks bankChequeBooks : lstBankChequeBooks) {
			parameters.add(new BeanPropertySqlParameterSource(bankChequeBooks));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstBankChequeBooks.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            BankChequeRegisters
	 *
	 * @return List<BankChequeRegisters>
	 *
	 
	 */
	public List<BankChequeRegisters> bankCrExecuteQuery(BankChequeRegisters objSearchDao) {
		final String sql = getQuery("OTMCPRIN_BANKCR_FIND_BANK_CHEQUE_REGISTERS");
		final RowMapper<BankChequeRegisters> BankChequeRegistersRowMapper = Row2BeanRowMapper.makeMapping(sql,
				BankChequeRegisters.class, bankChequeRegistersMapping);
		final ArrayList<BankChequeRegisters> returnList = (ArrayList<BankChequeRegisters>) namedParameterJdbcTemplate
				.query(sql, createParams("caseloadId",objSearchDao.getCaseLoadId(),"accountCode",objSearchDao.getAccountCode(),
						"firstCheckNumber",objSearchDao.getFirstCheckNumber(),
						"lastCheckNumber",objSearchDao.getLastCheckNumber()), BankChequeRegistersRowMapper);
		return returnList;
	}


	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstBankChequeRegisters
	 *            List<BankChequeRegisters>
	 *
	 
	 */
	public Integer bankCrUpdateBankChequeRegisters(final List<BankChequeRegisters> lstBankChequeRegisters) {
		String sql = getQuery("OTMCPRIN_BANKCR_UPDATE_BANK_CHEQUE_REGISTERS");
		int returnValue = 0;
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (BankChequeRegisters bankChequeRegisters : lstBankChequeRegisters) {
			parameters.add(new BeanPropertySqlParameterSource(bankChequeRegisters));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstBankChequeRegisters.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkBankCrChequeStatusRecordGroup(final String chequeStatus) {
		final String sql = getQuery("OTMCPRIN_FIND_CGFKBANKCRCHEQUESTATUS");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			returnList= namedParameterJdbcTemplate.query(sql, createParams("bankChequeStatus",chequeStatus), mRowMapper);
		} catch (Exception e) {
			log.error("cgfkBankCrChequeStatusRecordGroup",e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkBankCrCheqStatusVoidRecordGroup() {
		final String sql = getQuery("OTMCPRIN_GETCODEDESC");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * bankCrPostDelete
	 *
	 * @param params
	 *
	 */
	public List<BankChequeRegisters> bankCrPostDelete(BankChequeRegisters paramBean) {
		final String sql = getQuery("OTMCPRIN_BANK_CR_POSTDELETE");
		final RowMapper<BankChequeRegisters> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				BankChequeRegisters.class, bankChequeRegistersMapping);
		final ArrayList<BankChequeRegisters> returnList = (ArrayList<BankChequeRegisters>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkBankCrBankCrRef
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkBankCrBankCrRef(ReferenceCodes paramBean) {
		final String sql = getQuery("OTMCPRIN_CGFKCHK_BANK_CR_BANK_CR_REF_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
		return returnList;
	}

	public Integer getMaxCheckNum(final String caseLoadId, final Long accountCode, final Integer firstCheckNumber,
		final Integer lastCheckNumber) {
		final String sql = getQuery("OTMCPRIN_GETMAXCHECKNUM");
		Integer maxNumb =0;
		maxNumb =namedParameterJdbcTemplate.queryForObject(sql, createParams("caseLoadId",caseLoadId,
				"accountCode",accountCode,"firstCheckNumber",firstCheckNumber,"lastCheckNumber",lastCheckNumber), Integer.class);
		return maxNumb;
	}
	
	public Integer getMaxCheckNum(String caseloadId, Long accountCode, BigDecimal firstCheckNumber,
			BigDecimal lastCheckNumber) {
		final String sql = getQuery("OTMCPRIN_GETMAXCHECKNUM");
		Integer maxNumb =0;
		maxNumb =namedParameterJdbcTemplate.queryForObject(sql, createParams("caseLoadId",caseloadId,
				"accountCode",accountCode,"firstCheckNumber",firstCheckNumber,"lastCheckNumber",lastCheckNumber), Integer.class);
		return maxNumb;
	}

	public Integer insertIntoRegister(BankChequeRegisters obj) {
		final String sql = getQuery("OTMCPRIN_INSERT_INTO_REGISTERS");
		Integer num = namedParameterJdbcTemplate.update(sql, createParams("caseloadId",obj.getCaseLoadId()
				,"checkNumber",obj.getChequeNumber(),"accountCode",obj.getAccountCode(),"createUserId",obj.getCreateUserId()));
		return num;
	}
	public Integer insertIntoRegister(BankChequeBooks obj) {
		final String sql = getQuery("OTMCPRIN_INSERT_INTO_REGISTERS");
		Integer num = namedParameterJdbcTemplate.update(sql, createParams("caseloadId",obj.getCaseloadId()
				,"checkNumber",obj.getCheckNumber(),"accountCode",obj.getAccountCode(),"createUserId",obj.getCreateUserId()));
		return num;
	}

	@Override
	public List<String> checkIfNewSeries(BankChequeBooks searchBean) {
		final String sql = getQuery("OTMCPRIN_CHECKIFNEWSERIES");
		 List<String> list =new ArrayList<String>();
		 final RowMapper<String> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, String.class, listMapping);
		 list=namedParameterJdbcTemplate.query(sql, createParams("lastCheckNumber",searchBean.getLastCheckNumber(),
				 "firstCheckNumber",searchBean.getFirstCheckNumber(),
				 "accountCode",searchBean.getAccountCode(),"caseloadId",searchBean.getCaseloadId(),
				 "rowId",searchBean.getRowId()),columnRowMapper);
		return list;
	}

	public List<String> checkIfOverOthSeries(BankChequeBooks searchBean) {
		final String sql = getQuery("OTMCPRIN_CHECKIFOVEROTHSERIES");
		 List<String> list =new ArrayList<String>();
		 final RowMapper<String> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, String.class, listMapping);
		 list=namedParameterJdbcTemplate.query(sql, createParams("firstCheckNumber",searchBean.getFirstCheckNumber(),
				"lastCheckNumber",searchBean.getLastCheckNumber(), "accountCode",searchBean.getAccountCode(),"caseloadId",searchBean.getCaseloadId(),
				"rowId",searchBean.getRowId()),columnRowMapper);
		return list;
	}

	@Override
	public Long bcRowMaxChecqueNumber(final String caseloadId, final Long accountCode, final String firstCheckNumber,
			final String lastCheckNumber) {
		final String sql = getQuery("OTMCPRIN_BCROWMAXCHECQUENUMBER");
		Long checkNum = null;
		checkNum =namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId",caseloadId,
				"accountCode",accountCode,"firstCheckNumber",firstCheckNumber,"lastCheckNumber",lastCheckNumber), Long.class);
		return checkNum;
	}

	public String  isTransactionReversed(final Long txnId, final Long accountCode) {
		final String sql = getQuery("OTMCPRIN_ISTRANSACTIONREVERSED");
		String lvTxnReversedFlg =null;
		try{
			// final RowMapper<String> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, String.class, listMapping);
			lvTxnReversedFlg=namedParameterJdbcTemplate.queryForObject(sql, createParams("txnId",txnId),String.class);
		} catch (EmptyResultDataAccessException e) {
			lvTxnReversedFlg = "Y" ;
		} catch (NullPointerException e) {
			lvTxnReversedFlg ="Y" ;
		}
		return lvTxnReversedFlg;
	}

	public String getDesc(final String chequeStatus) {
		final String sql = getQuery("OTMCPRIN_GETDESC");
		String desc =null;
		desc =namedParameterJdbcTemplate.queryForObject(sql, createParams("chequeStatus",chequeStatus), String.class);
		return desc;
	}


}

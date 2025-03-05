package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globaloffenderrecords.impl.OsiosearRepositoryImpl;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.OtmdprioRepository;
/**
 * Class OtmdprioRepositoryImpl
 * @author Arkin Software Technologies 
 * @version 1.0 
 */
@Repository
public class OtmdprioRepositoryImpl extends RepositoryBase implements OtmdprioRepository{

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OsiosearRepositoryImpl.class.getName());

private final Map<String, FieldMapper> transactionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("CASELOAD_TYPE", 						new FieldMapper("caseloadType"))
.put("RECEIPT_TXN_TYPE", 						new FieldMapper("receiptTxnType"))
.build();
private final Map<String, FieldMapper> deductionTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DEDUCTION_TYPE", 						new FieldMapper("deductionType"))
.put("DEDUCTION_DESC", 						new FieldMapper("deductionTypeDesc"))
.put("CASELOAD_TYPE", 						new FieldMapper("caseloadType"))
.put("FROM_BALANCE_TYPE", 					new FieldMapper("fromBalType"))
.build();
private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("ACCOUNT_NAME", 						new FieldMapper("accountName"))
.put("CASELOAD_TYPE", 						new FieldMapper("caseloadType"))
.put("ACCOUNT_CODE", 						new FieldMapper("accountCode"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DEDUCTION_TYPE", 						new FieldMapper("deductionType"))
.put("ACCOUNT_CODE", 						new FieldMapper("accountCode"))
.put("TXN_TYPE", 						new FieldMapper("txnType"))
.build();
private final Map<String, FieldMapper> caseloadDeductionProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DEDUCTION_TYPE", 						new FieldMapper("deductionType"))
.put("ACCOUNT_CODE", 						new FieldMapper("accountCode"))
.put("TXN_TYPE", 							new FieldMapper("txnType"))
.put("CREATE_DATETIME", 					new FieldMapper("createDateTime"))
.put("MODIFY_DATETIME", 					new FieldMapper("modifyDateTime"))
.build();

private final Map<String, FieldMapper> caseloadDeductionDetailsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DEDUCTION_TYPE", 						new FieldMapper("deductionType"))
.put("ACCOUNT_CODE", 						new FieldMapper("accountCode"))
.put("TXN_TYPE", 						new FieldMapper("txnType"))
.build();

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
	public List<CaseloadDeductionProfiles> csldDpExecuteQuery(CaseloadDeductionProfiles objSearchDao) {
		final String sql = getQuery("OTMDPRIO_CSLDDP_FIND_CASELOAD_DEDUCTION_PROFILES");
		final RowMapper<CaseloadDeductionProfiles> CaseloadDeductionProfilesRowMapper = Row2BeanRowMapper
				.makeMapping(sql, CaseloadDeductionProfiles.class, caseloadDeductionProfilesMapping);
		List<CaseloadDeductionProfiles> returnList = new ArrayList<CaseloadDeductionProfiles>();
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao.getCaseloadId() != null) {
			sqlQuery.append("CASELOAD_ID = :CASELOAD_ID" + " AND  ");
			inParameterSource.addValue("CASELOAD_ID", objSearchDao.getCaseloadId());
		}
		if (objSearchDao.getExternalPriorityNo() != null) {
			sqlQuery.append("EXTERNAL_PRIORITY_NO = :EXTERNAL_PRIORITY_NO" + " AND ");
			inParameterSource.addValue("EXTERNAL_PRIORITY_NO", objSearchDao.getExternalPriorityNo());
		}
		if (objSearchDao.getInternalPriorityNo() != null) {
			sqlQuery.append("INTERNAL_PRIORITY_NO = :INTERNAL_PRIORITY_NO" + " AND ");
			inParameterSource.addValue("INTERNAL_PRIORITY_NO", objSearchDao.getInternalPriorityNo());
		}
		if (objSearchDao.getFifoFlag() != null) {
			sqlQuery.append("FIFO_FLAG = :FIFO_FLAG" + " AND ");
			inParameterSource.addValue("FIFO_FLAG", objSearchDao.getFifoFlag());
		}
		if (objSearchDao.getIndigentMandatoryFlag() != null) {
			sqlQuery.append("INDIGENT_MANDATORY_FLAG = :INDIGENT_MANDATORY_FLAG" + " AND ");
			inParameterSource.addValue("INDIGENT_MANDATORY_FLAG", objSearchDao.getIndigentMandatoryFlag());
		}
		preparedSql = sqlQuery.toString().trim();
		
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat("ORDER BY EXTERNAL_PRIORITY_NO,INTERNAL_PRIORITY_NO");
		try{
		 returnList =  namedParameterJdbcTemplate.query(preparedSql,inParameterSource, CaseloadDeductionProfilesRowMapper);
		}catch (Exception e) {
			logger.error("csldDpExecuteQuery",e);
		}
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCaseloadDeductionProfiles
	 *            List<CaseloadDeductionProfiles>
	 *
	 * @throws SQLException
	 */
	public Integer csldDpUpdateCaseloadDeductionProfiles(
			final List<CaseloadDeductionProfiles> lstCaseloadDeductionProfiles) {
		String sql = getQuery("OTMDPRIO_CSLDDP_UPDATE_CASELOAD_DEDUCTION_PROFILES");
		int[] returnArray = new int[] {};
		 List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		 for (CaseloadDeductionProfiles caseloadDeductionProfiles : lstCaseloadDeductionProfiles) {
		  parameters.add(new BeanPropertySqlParameterSource(caseloadDeductionProfiles));
		 }
		 returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		 if (lstCaseloadDeductionProfiles.size() == returnArray.length) {
		  return 1;
		 } else {
		  return 0;
		 }



	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<Object> cgwhenNewFormInstance(final SysDual paramBean) {
		final String sql = getQuery("OTMDPRIO_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<Object> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Object.class, mMapping);
		final ArrayList<Object> returnList = (ArrayList<Object>) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	public List<CaseloadDeductionProfiles> getBalTypeDesc(final String deductionType) {
		final String sql = getQuery("OTMDPRIO_GETBALTYPEDESC");
		final RowMapper<CaseloadDeductionProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, CaseloadDeductionProfiles.class, deductionTypesMapping);
		List<CaseloadDeductionProfiles> beanDataList= new ArrayList<CaseloadDeductionProfiles>();
		try{
		beanDataList =namedParameterJdbcTemplate.query(sql, createParams("deductionType",deductionType),columnRowMapper);
		}catch (Exception e) {
			logger.error("getBalTypeDesc",e);
		}
		return beanDataList;
	}

}

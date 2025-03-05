package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.MinimumPayableBalances;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.trust.deductions.deductionsmaintenance.OcmmpbalRepository;

@Repository
public class OcmmpbalRepositoryImpl extends RepositoryBase implements OcmmpbalRepository{


private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("ACCOUNT_NAME", 						new FieldMapper("accountName"))
.put("CASELOAD_TYPE", 						new FieldMapper("caseloadType"))
.put("ACCOUNT_CODE", 						new FieldMapper("accountCode"))
.put("description",                        new FieldMapper("dspAccountName"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("ACCOUNT_CODE", 						new FieldMapper("accountCode"))
.put("CODE", 								new FieldMapper("code"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();

/**
 * Creates new OcmmpbalRepositoryImpl class Object 
 */
public OcmmpbalRepositoryImpl() {
//	OcmmpbalRepositoryImpl
}

/**
* Fetch the records from database table
*
* @param objSearchDao MinimumPayableBalances
*
* @return List<MinimumPayableBalances>
*
* @throws SQLException
*/
 public List<MinimumPayableBalances> minPbExecuteQuery(final MinimumPayableBalances objMinimumPayableBalances)  {
		final String sql = getQuery("OCMMPBAL_MINPB_FIND_MINIMUM_PAYABLE_BALANCES");
		final RowMapper<MinimumPayableBalances> MinimumPayableBalancesRowMapper = Row2BeanRowMapper.makeMapping(sql, MinimumPayableBalances.class, accountCodesMapping);
		final List<MinimumPayableBalances> returnList = namedParameterJdbcTemplate.query(sql, createParams("accountCode", objMinimumPayableBalances.getAccountCode(),
				"minPayAmount", objMinimumPayableBalances.getMinPayAmount(),"caseloadType",objMinimumPayableBalances.getCaseloadType()), MinimumPayableBalancesRowMapper);
		return returnList;
} 
/**
* @param 
*
* @throws SQLException 
*
*/
public int PRE_INSERT()  {
return 0;
}

/**
*  This method is used to insert the records in the data base tables based on
*
* @param lstMinimumPayableBalances List<MinimumPayableBalances>
*
* @return List<Integer>
*
* @throws SQLException
*/
 public Integer minPbInsertMinimumPayableBalances(final List<MinimumPayableBalances> lstMinimumPayableBalances)  {
	String sql = getQuery("OCMMPBAL_MINPB_INSERT_MINIMUM_PAYABLE_BALANCES");
	int[] returnArray = new int[] {};
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (MinimumPayableBalances minimumPayableBalances : lstMinimumPayableBalances) {
		parameters.add(new BeanPropertySqlParameterSource(minimumPayableBalances));
	}
	returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	if (lstMinimumPayableBalances.size() == returnArray.length) {
		return 1;
	} else {
		return 0;
	}

}

/**
* This method is used to update the data base tables based on
*
* @param lstMinimumPayableBalances List<MinimumPayableBalances>
*
* @throws SQLException
*/
 public Integer minPbUpdateMinimumPayableBalances(final List<MinimumPayableBalances> lstMinimumPayableBalances)  {
	String sql = getQuery("OCMMPBAL_MINPB_UPDATE_MINIMUM_PAYABLE_BALANCES");
	int[] returnArray = new int[] {};
	List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	for (MinimumPayableBalances minimumPayableBalances : lstMinimumPayableBalances) {
		parameters.add(new BeanPropertySqlParameterSource(minimumPayableBalances));
	}
	returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	if (lstMinimumPayableBalances.size() == returnArray.length) {
		return 1;
	} else {
		return 0;
	}

}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<AccountCodes> cgfkMinPbAccountCodeRecordGroup(final String caseloadType)  {
 	final String sql = getQuery("OCMMPBAL_FIND_CGFKMINPBACCOUNTCODE");
 	final RowMapper<AccountCodes>mRowMapper = Row2BeanRowMapper.makeMapping(sql,AccountCodes.class, mMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams("caseloadType", caseloadType),mRowMapper);
  	} catch (Exception e) {
  		return Collections.emptyList();  
	}
}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkMinPbMinPbAcCode
	 *
	 * @param params
	 *
	 */
	public List<AccountCodes> cgfkchkMinPbMinPbAcCode(AccountCodes paramBean)  {
		final String sql = getQuery("OCMMPBAL_CGFKCHK_MIN_PB_MIN_PB_AC_CODE");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,AccountCodes.class,  accountCodesMapping);
		final ArrayList<AccountCodes> returnList = (ArrayList<AccountCodes>)namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
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
	public List<SysDual> cgwhenNewFormInstance(SysDual paramBean)  {
		final String sql = getQuery("OCMMPBAL_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<SysDual> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,SysDual.class,  mMapping);
		final List<SysDual> returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnList;
	}

	

}

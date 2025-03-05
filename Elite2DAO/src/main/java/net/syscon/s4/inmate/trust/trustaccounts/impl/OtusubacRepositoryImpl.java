package net.syscon.s4.inmate.trust.trustaccounts.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.trust.trustaccounts.OtusubacRepository;
/**
 * Class OtusubacRepositoryImpl
 */
@Repository
public class OtusubacRepositoryImpl extends RepositoryBase implements OtusubacRepository {

/**
 * Creates new OtusubacRepositoryImpl class Object 
 */
	private static Logger logger = LogManager.getLogger(OtusubacRepositoryImpl.class.getName());
public OtusubacRepositoryImpl() {
}
private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("SUB_ACCOUNT_TYPE", 						new FieldMapper("subAccountType"))
.build();
private final Map<String, FieldMapper> offenderSubAccountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("TRUST_ACCOUNT_CODE", 						new FieldMapper("trustAccountCode"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("LIST_SEQ", 						new FieldMapper("listSeq"))
.put("HOLD_BALANCE", 						new FieldMapper("holdBalance"))
.put("LAST_TXN_ID", 						new FieldMapper("lastTxnId"))
.put("IND_DAYS", 						new FieldMapper("indDays"))
.put("OFFENDER_ID", 						new FieldMapper("offenderId"))
.put("BALANCE", 						new FieldMapper("balance"))
.put("CASELOAD_ID", 						new FieldMapper("caseloadId"))
.put("MODIFY_DATE", 						new FieldMapper("modifyDate"))
.put("IND_DATE", 						new FieldMapper("indDate"))
.put("MINIMUM_BALANCE", 						new FieldMapper("minimumBalance"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
.build();
private final Map<String, FieldMapper> caseloadsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("'Y'", 						new FieldMapper(" 'y' "))
.put("ACTIVE_FLAG",                new FieldMapper("activeFlag"))
.build();
private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("SUB_ACCOUNT_TYPE", 						new FieldMapper("subAccountType"))
.build();
private final Map<String, FieldMapper> offendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("OFFENDER_ID_DISPLAY", 						new FieldMapper("offenderIdDisplay"))
.put("PARENT_OFFENDER_ID", 						new FieldMapper("parentOffenderId"))
.put("SUFFIX", 						new FieldMapper("suffix"))
.put("OFFENDER_NAME_SEQ", 						new FieldMapper("offenderNameSeq"))
.put("LAST_NAME_SOUNDEX", 						new FieldMapper("lastNameSoundex"))
.put("ROOT_OFFENDER_ID", 						new FieldMapper("rootOffenderId"))
.put("ADD_INFO_CODE", 						new FieldMapper("addInfoCode"))
.put("MIDDLE_NAME_2", 						new FieldMapper("middleName2"))
.put("LAST_NAME_ALPHA_KEY", 						new FieldMapper("lastNameAlphaKey"))
.put("MIDDLE_NAME", 						new FieldMapper("middleName"))
.put("OFFENDER_ID", 						new FieldMapper("offenderId"))
.put("BIRTH_STATE", 						new FieldMapper("birthState"))
.put("LAST_NAME", 						new FieldMapper("lastName"))
.put("BIRTH_COUNTY", 						new FieldMapper("birthCounty"))
.put("CASELOAD_TYPE", 						new FieldMapper("caseloadType"))
.put("UNIQUE_OBLIGATION_FLAG", 						new FieldMapper("uniqueObligationFlag"))
.put("ID_SOURCE_CODE", 						new FieldMapper("idSourceCode"))
.put("REMARK_CODE", 						new FieldMapper("remarkCode"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
.put("SUSPENDED_FLAG", 						new FieldMapper("suspendedFlag"))
.put("NAME_TYPE", 						new FieldMapper("nameType"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("SUSPENDED_DATE", 						new FieldMapper("suspendedDate"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("BIRTH_COUNTRY_CODE", 						new FieldMapper("birthCountryCode"))
.put("MIDDLE_NAME_KEY", 						new FieldMapper("middleNameKey"))
.put("NAME_SEQUENCE", 						new FieldMapper("nameSequence"))
.put("TITLE", 						new FieldMapper("title"))
.put("SEX_CODE", 						new FieldMapper("sexCode"))
.put("BIRTH_PLACE", 						new FieldMapper("birthPlace"))
.put("LAST_NAME_KEY", 						new FieldMapper("lastNameKey"))
.put("ALIAS_OFFENDER_ID", 						new FieldMapper("aliasOffenderId"))
.put("RACE_CODE", 						new FieldMapper("raceCode"))
.put("ALIAS_NAME_TYPE", 						new FieldMapper("aliasNameType"))
.put("CREATE_DATE", 						new FieldMapper("createDate"))
.put("FIRST_NAME_KEY", 						new FieldMapper("firstNameKey"))
.put("BIRTH_DATE", 						new FieldMapper("birthDate"))
.put("FIRST_NAME", 						new FieldMapper("firstName"))
.put("AGE", 						new FieldMapper("age"))
.build();
private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("PROFILE_CODE", 						new FieldMapper("profileCode"))
.put("PROFILE_TYPE", 						new FieldMapper("profileType"))
.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
.put("CREATE_DATETIME", 						new FieldMapper("createDatetime"))
.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
.put("OLD_TABLE_NAME", 						new FieldMapper("oldTableName"))
.put("PROFILE_VALUE", 						new FieldMapper("profileValue"))
.put("MODIFY_DATETIME", 						new FieldMapper("modifyDatetime"))
.put("PROFILE_VALUE_2", 						new FieldMapper("profileValue2"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();

/**
* Fetch the records from database table
*
* @param objSearchDao Offenders
*
* @return List<Offenders>
*
* @throws SQLException
*/
 public List<Offenders> offNameExecuteQuery(final Offenders objSearchDao) {
		final String sql = getQuery("OTUSUBAC_OFFNAME_FIND_OFFENDERS");
		final RowMapper<Offenders> OffendersRowMapper = Row2BeanRowMapper.makeMapping(sql, Offenders.class,offendersMapping);
		List<Offenders> returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderId", objSearchDao.getOffenderId()), OffendersRowMapper);
		} catch (Exception e) {
		logger.error(this.getClass().getName()+"In method offNameExecuteQuery error", e);
		}
		return returnList;
} 

/**
* Fetch the records from database table
*
* @param objSearchDao OffenderSubAccounts
*
* @return List<OffenderSubAccounts>
*
* @throws SQLException
*/
 public List<OffenderSubAccounts> offSubaExecuteQuery(final OffenderSubAccounts objSearchDao) {
		final String sql = getQuery("OTUSUBAC_OFFSUBA_FIND_OFFENDER_SUB_ACCOUNTS");
		final StringBuffer prepredSql = new StringBuffer(sql);
		final MapSqlParameterSource params = new MapSqlParameterSource();
		/*check_caseload cursor implemented*/
		Caseloads caseloads = new Caseloads();
		caseloads.setCaseloadId(objSearchDao.getCaseloadId());
		
		final Caseloads resultCaseLoad = offSubaPreQuery(caseloads);
		if(resultCaseLoad != null && resultCaseLoad.getActiveFlag() != null && resultCaseLoad.getActiveFlag().equalsIgnoreCase("Y")) {
			prepredSql.append(" OFFENDER_ID = :offenderId");
			params.addValue("offenderId", objSearchDao.getOffenderId());
			prepredSql.append(" and CASELOAD_ID in (select cs.caseload_id  ");
			prepredSql.append("           from caseloads cs, caseload_agency_locations ca ");
			prepredSql.append("          where cs.trust_commissary_caseload = :caseloadId");
			params.addValue("caseloadId", objSearchDao.getCaseloadId());
			prepredSql.append("            and ca.caseload_id = cs.caseload_id ");
			prepredSql.append("            and ca.caseload_id = offender_sub_accounts.caseload_id ");
			prepredSql.append("            AND EXISTS (SELECT 1 FROM OFFENDER_EXTERNAL_MOVEMENTS OEM ");
			prepredSql.append("                                 WHERE DECODE(OEM.DIRECTION_CODE, 'OUT', oem.from_agy_loc_id ");
			prepredSql.append("                                             ,OEM.TO_AGY_LOC_ID) = CA.AGY_LOC_ID ");
			prepredSql.append("                                  AND OEM.OFFENDER_BOOK_ID = :offenderBookId  ");
			params.addValue("offenderBookId", objSearchDao.getOffenderBookId());
			prepredSql.append("                                   AND (OEM.MOVEMENT_SEQ = (SELECT MAX(OEM1.MOVEMENT_SEQ) ");
			prepredSql.append("                                                              FROM OFFENDER_EXTERNAL_MOVEMENTS OEM1 ");
			prepredSql.append("                                                             WHERE OEM1.OFFENDER_BOOK_ID = :offenderBookId)))) ");
			
			
		} else {
			prepredSql.append("  OFFENDER_ID = :offenderId   ");
			params.addValue("offenderId", objSearchDao.getOffenderId());
			prepredSql.append("   AND CASELOAD_ID = :caseloadId   ");
			params.addValue("caseloadId", objSearchDao.getCaseloadId());
		}
		/* Un-Understandable code */
		
//		IF (:OFF_SUBA.DSP_SUB_ACCOUNT_TYPE IS NOT NULL) THEN
//	      sub_where := add_and( sub_where ) || '(SUB_ACCOUNT_TYPE LIKE '''||:OFF_SUBA.DSP_SUB_ACCOUNT_TYPE || ''')';
//	    END IF;
//	 
//	    IF (sub_where IS NOT NULL) THEN
//	      def_where := add_and( def_where ) || ' ( TRUST_ACCOUNT_CODE IN  '||'(SELECT ACCOUNT_CODE '||'FROM ACCOUNT_CODES WHERE '||
//	      sub_where || '))';
//	    END IF;
		final String preparedSqlQuery = prepredSql.toString();
		final RowMapper<OffenderSubAccounts> OffenderSubAccountsRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderSubAccounts.class,offenderSubAccountsMapping);
		List<OffenderSubAccounts> returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.query(preparedSqlQuery, params, OffenderSubAccountsRowMapper);
		} catch (DataAccessException e) {
		logger.error(this.getClass().getName()+"In method offSubaExecuteQuery error", e);
		}
		return returnList;
} 

/**
* Fetch the records from database table
*
* @param objSearchDao SystemProfiles
*
* @return List<SystemProfiles>
*
* @throws SQLException
*/
 public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OTUSUBAC_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>)namedParameterJdbcTemplate.query(sql, createParams(), SystemProfilesRowMapper);
		return returnList;
} 
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offSubaPreQuery
	 *
	 * @param params
	 *
	 */
	public Caseloads offSubaPreQuery(final Caseloads paramBean) {
		final String sql = getQuery("OTUSUBAC_OFF_SUBA_PREQUERY");
		final RowMapper<Caseloads> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,Caseloads.class,  caseloadsMapping);
		Caseloads returnObj = new Caseloads();
		try {
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", paramBean.getCaseloadId()), columnRowMapper);
		} catch(Exception e) {
		logger.error(this.getClass().getName()+"In method offSubaPreQuery error", e);
			returnObj.setActiveFlag("N");
			return returnObj;
		}
		return returnObj;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffSubaOffSubaAc
	 *
	 * @param params
	 *
	 */
	public AccountCodes cgfkchkOffSubaOffSubaAc(final AccountCodes paramBean) {
		final String sql = getQuery("OTUSUBAC_CGFKCHK_OFF_SUBA_OFF_SUBA_AC_CODE");
		final RowMapper<AccountCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,AccountCodes.class,  accountCodesMapping);
		try {
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("accountCode", paramBean.getAccountCode()), columnRowMapper);
		} catch(Exception e) {
		logger.error(this.getClass().getName()+"In method cgfkchkOffSubaOffSubaAc error", e);
			return null;
		}
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffSubaOffSubaAc
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffSubaOffSubaAc(final ReferenceCodes paramBean) {
		final String sql = getQuery("OTUSUBAC_CGFKCHK_OFF_SUBA_OFF_SUBA_AC");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class,  referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("code", paramBean.getCode()), columnRowMapper);
		} catch (Exception e) {
		logger.error(this.getClass().getName()+"In method cgfkchkOffSubaOffSubaAc error", e);
			return null;
			}
	}

}

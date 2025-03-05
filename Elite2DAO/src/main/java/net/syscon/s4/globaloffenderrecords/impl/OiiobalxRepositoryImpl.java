package net.syscon.s4.globaloffenderrecords.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.globaloffenderrecords.OiiobalxRepository;
import net.syscon.s4.inmate.beans.OffExternalAccountBalances;

@Repository
public class OiiobalxRepositoryImpl extends RepositoryBase implements OiiobalxRepository {
	
	private static Logger logger = LogManager.getLogger(OiiobalxRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> offExtActBalMapping = new ImmutableMap.Builder<String, FieldMapper>()
	.put("ACCOUNT_BALANCE_ID",          new FieldMapper("accountBalanceId"))
	.put("ACCOUNT_ID",            new FieldMapper("accountId"))      
	.put("ROOT_OFFENDER_ID",           	new FieldMapper("rootOffenderId"))  
	.put("ACCOUNT_TYPE",              	new FieldMapper("accountType"))     
	.put("BALANCE",                 	new FieldMapper("balance"))         
	.put("LAST_CHANGED",              	new FieldMapper("lastChanged"))     
	.put("ACCOUNT_DETAILS",           	new FieldMapper("accountDetails"))  
	.put("CREATE_DATETIME",           	new FieldMapper("createDatetime"))  
	.put("CREATE_USER_ID",             	new FieldMapper("createUserId"))    
	.put("MODIFY_DATETIME",           	new FieldMapper("modifyDatetime"))  
	.put("MODIFY_USER_ID",             	new FieldMapper("modifyUserId")).build();    
	
	private final Map<String, FieldMapper> offenderMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY",     new FieldMapper("offenderIdDisplay"))      
			.put("ROOT_OFFENDER_ID",        new FieldMapper("rootOffenderId"))  
			.build(); 

	@Override
	public List<OffExternalAccountBalances> getOffExternalAccountBalances(OffExternalAccountBalances searchBean) {
		final String sql = getQuery("OIIOBALX_EXTERNAL_ACCOUNT_BALANCES");
		final RowMapper<OffExternalAccountBalances> offExtActBalRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffExternalAccountBalances.class, offExtActBalMapping);
		List<OffExternalAccountBalances> refList = new ArrayList<OffExternalAccountBalances>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, createParams("rootOffenderId", searchBean.getRootOffenderId()), offExtActBalRowMapper);
		} catch (Exception e) {
			logger.error("error in getOffExternalAccountBalances"+ e);
		}
		return refList;
	}

	

	@Override
	public OffExternalAccountBalances getOffenderAccountDetails(long rootOffenderId, String accountType) {
		final String sql = getQuery("OFFENDER_EXTERNAL_ACCOUNT_BALANCE");
		OffExternalAccountBalances accountBalance = null;
		final RowMapper<OffExternalAccountBalances> vHdBlockRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffExternalAccountBalances.class, offExtActBalMapping);
		try {
			accountBalance = namedParameterJdbcTemplate.queryForObject(sql, createParams("rootOffenderId", rootOffenderId,"accountType",accountType),
					vHdBlockRowMapper);
		} catch (Exception e) {
			
			logger.error("OiiobalxRepositoryImpl::  error in getOffenderAccountDetails  ", e.getMessage());
		}
		return accountBalance;
	}

	@Override
	public Integer updateOffenderExternalBalance(OffExternalAccountBalances accBalObj) {
		 final String sql = getQuery("UPDATE_OFFENDER_EXTERNAL_ACCOUNT_BALANCE");
			int returnArray = 0 ;
			SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(accBalObj);
			try {
				returnArray = namedParameterJdbcTemplate.update(sql, sqlParam);
			} catch (Exception e) {
				logger.error("OiiobalxRepositoryImpl::  error in updateOffenderExternalBalance  "+ e.getMessage());
				return 0;
			}
			
			if (returnArray > 0) {
				return 1;
			} else {
				return 0;
			}
	}

	@Override
	public Integer InsertOffenderExternalBalance(OffExternalAccountBalances accBalObj) {
		 final String sql = getQuery("INSERT_OFFENDER_EXTERNAL_BALANCES");
			int returnArray = 0 ;
			SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(accBalObj);
			try {
				returnArray = namedParameterJdbcTemplate.update(sql, sqlParam);
			} catch (Exception e) {
				logger.error("OiiobalxRepositoryImpl::  error in InsertOffenderExternalBalance  "+ e.getMessage());
				return 0;
			}
			
			if (returnArray > 0) {
				return 1;
			} else {
				return 0;
			}
	}
	
	@Override
	public List<VHeaderBlock> getRootOffenderId(Set<String> offenderIdDisplay) {
		final String sql = getQuery("GET_ROOT_OFFENDER_IDS");
		final RowMapper<VHeaderBlock> offenderRowMapping = Row2BeanRowMapper.makeMapping(sql,
				VHeaderBlock.class, offenderMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("offenderIdDisplay",offenderIdDisplay),offenderRowMapping);
		} catch (Exception e) {
			logger.error("OiiobalxRepositoryImpl:: error in getRootOffenderId"+ e.getMessage());
		}
		return null;
	}

	@Override
	public Date getLastUpdatedDate() {
		final String sql = getQuery("GET_LATEST_MODIFIED_DATE");
		Date returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams(), Date.class);
		} catch (Exception e) {
			logger.error("OiiobalxRepositoryImpl:: error in getLastUpdatedDate"+ e.getMessage());
			returnVal = null;
		}
		return returnVal;
	}

}

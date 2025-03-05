package net.syscon.s4.cm.intakeclosure.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cm.intakeclosure.OcdsupstRepository;
import net.syscon.s4.common.beans.OffSupervisionStsHty;
import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import oracle.jdbc.OracleTypes;

/**
 * Class OcdsupstRepositoryImpl
 * 
 */
@Repository
public class OcdsupstRepositoryImpl extends RepositoryBase implements OcdsupstRepository {

	private final Map<String, FieldMapper> agencyLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("DSP_DESCRIPTION22", new FieldMapper("dspDescription22"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> refCodesMapping = new ImmutableMap.Builder<String, FieldMapper>().build();
	private final Map<String, FieldMapper> supHstyMapping = new ImmutableMap.Builder<String, FieldMapper>().build();
	private final Map<String, FieldMapper> feeAccMapping = new ImmutableMap.Builder<String, FieldMapper>().build();
	private final Map<String, FieldMapper> cdpMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EXTERNAL_PRIORITY_NO",			new FieldMapper("externalPriorityNo"))
			.put("ACCOUNT_CODE",					new FieldMapper("accountCode"))
			.put("CO_LIMIT_AMOUNT",					new FieldMapper("coLimitAmount"))
			.put("CO_CREDIT_WHEN_INDIGENT_FLAG",	new FieldMapper("coCreditWhenIndigentFlag"))
			.put("MAX_MONTHLY_AMOUNT",				new FieldMapper("maxMonthlyAmount"))
			.put("MAX_TOTAL_AMOUNT",				new FieldMapper("maxTotalAmount"))
			.put("EXPIRY_DATE",						new FieldMapper("expiryDate"))
			.put("PAYEE_PERSON_ID",					new FieldMapper("payeePersonId"))
			.put("PAYEE_CORPORATE_ID",				new FieldMapper("payeeCorporateId"))
			.put("MODIFY_USER_ID",					new FieldMapper("modifyUserId"))
			.put("MODIFY_DATE",						new FieldMapper("modifyDate"))
			.put("LIST_SEQ",						new FieldMapper("listSeq"))
			.put("FLAT_RATE",						new FieldMapper("flatRate"))
			.put("MINIMUM_TRUST_BALANCE",			new FieldMapper("minimumTrustBalance"))
			.put("INDIGENT_MANDATORY_FLAG",			new FieldMapper("indigentMandatoryFlag"))
			.put("COMM_CONDITION_TYPE",				new FieldMapper("commConditionType"))
			.put("COMM_CONDITION_CODE",				new FieldMapper("commConditionCode"))
			.put("MAX_RECURSIVE_AMOUNT",			new FieldMapper("maxRecursiveAmount"))
			.put("CREATE_DATETIME",					new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID",					new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME",					new FieldMapper("modifyDateTime"))
			.put("CATEGORY_TYPE",					new FieldMapper("categoryType"))
			.put("SEAL_FLAG",						new FieldMapper("sealFlag"))
			.put("LOCATION",						new FieldMapper("location"))
			.put("AMOUNT",						new FieldMapper("amount"))
			.put("FREQUENCY",						new FieldMapper("frequency"))
			.put("DAY_OF_MONTH",						new FieldMapper("dayOfMonth"))
			.put("BACK_BILL",						new FieldMapper("backBill"))
			.put("CODE",						new FieldMapper("code"))
			.put("FREQUENCY_TYPE",						new FieldMapper("frequencyType"))
			.put("FREQUENCY_CODE",						new FieldMapper("frequencyCode"))
			.put("NON_BILLABLE_STATUS",						new FieldMapper("nonBillableStatus"))
			.build();
	@Override
	public List<OffenderBookingAgyLocs> offBkgAgyLocExecuteQuery(final OffenderBookingAgyLocs searchBean) {
		final String sql = getQuery("OCDSUPST_OFFENDER_BOOKING_AGY_LOCS");
		final RowMapper<OffenderBookingAgyLocs> StaffLocationRolesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderBookingAgyLocs.class, agencyLocationsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", searchBean.getOffenderBookId()),
				StaffLocationRolesRowMapper);

	}

	@Override
	public String getProfileValue() {
		final String sql = getQuery("OCDSUPST_GET_SYS_PROFILES");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public ReferenceCodes getBillableFlag(final String code) {
		final String sql = getQuery("OCDSUPST_GET_BILLABLE_FLAG_VALUE");
		final RowMapper<ReferenceCodes> refsRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodesMapping);
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("CODE", code), refsRowMapper);
		}catch (Exception e) {
			return null;
		}

	}

	@Transactional
	public Integer suphstyInsertQuery(final List<OffSupervisionStsHty> insertList) {
		final String sql = getQuery("OCDSUPST_SUPERVISION_HISTORY_INSERT_QUERY");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffSupervisionStsHty bean : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		try{
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch(Exception e) {
			String str = e.getMessage();
			if(str.contains("OFF_SUPERVISION_STS_HTY_UK1") || str.contains("OFF_SUPERVISION_STS_HTY_UK2")){
				return 2;
			}
		}
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Integer suphstyUpdateQuery(final List<OffSupervisionStsHty> insertList) {
		final String sql = getQuery("OCDSUPST_SUPERVISION_HISTORY_UPDATE_QUERY");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffSupervisionStsHty bean : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		try{
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			} catch(Exception e) {
				String str = e.getMessage();
				if(str.contains("OFF_SUPERVISION_STS_HTY_UK1") || str.contains("OFF_SUPERVISION_STS_HTY_UK2")){
					return 2;
				}
			}
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public List<OffSupervisionStsHty> supHistoryExecuteQuery(final OffSupervisionStsHty searchBean) {
		final String sql = getQuery("OCDSUPST_SUPERVISION_HSTY_EXECUTE_QUERY");
		final RowMapper<OffSupervisionStsHty> SupHstyRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffSupervisionStsHty.class, supHstyMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", searchBean.getOffenderBookId()),
				SupHstyRowMapper);
	}

	@Override
	public FeeAccountProfiles getsupStartDate(final BigDecimal offenderBookId) {
		final String sql = getQuery("OCDSUPST_GET_FEE_ACCOUNT_SUP_START_DATE");
		final RowMapper<FeeAccountProfiles> feeAccRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FeeAccountProfiles.class, feeAccMapping);
		try {
			return  namedParameterJdbcTemplate.queryForObject(sql,
					createParams("OFFENDER_BOOK_ID", offenderBookId), feeAccRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return new FeeAccountProfiles();
		}
	}

	public Integer updateFeeAccounts(final List<FeeAccountProfiles> updateList) {
		final String sql = getQuery("OCDSUPST_UPDATE_FEE_ACCOUNTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final FeeAccountProfiles bean : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<FeeAccountProfiles> getCurrentSupData(final BigDecimal offenderBookId) {
		final String sql = getQuery("OCDSUPST_GET_FEE_ACCOUNT_SUP_DATA");
		final RowMapper<FeeAccountProfiles> feeAccRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FeeAccountProfiles.class, feeAccMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_BOOK_ID", offenderBookId), feeAccRowMapper);
	}
	@Override
	public List<OffSupervisionStsHty> supHisExecuteQuery(final OffSupervisionStsHty searchBean) {
		final String sql = getQuery("OCDSUPST_SUPV_HSTY_EXECUTE_QUERY");
		final RowMapper<OffSupervisionStsHty> SupHstyRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffSupervisionStsHty.class, supHstyMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", searchBean.getOffenderBookId()),
				SupHstyRowMapper);
	}

	@Override
	public Integer getIntakeRevCount(final VHeaderBlock bean) {
		final String sql = getQuery("OCDSUPST_GET_INTAKE_REVIEW_COUNT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDER_BOOK_ID", bean.getOffenderBookId()), Integer.class);
	}

	@Override
	public Integer validateStartEndDates(final OffSupervisionStsHty bean) {
		String sql = getQuery("OCDSUPST_VALIDATE_START_END_DATES");
		if (bean.getOffenderSupId() != null) {
			sql = sql + " AND OFFENDER_SUP_ID != " + bean.getOffenderSupId();
		}
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_BOOK_ID", bean.getOffenderBookId(),
				"START_DATETIME", bean.getStartDatetime(), "END_DATETIME", bean.getEndDatetime()), Integer.class);
	}

	@Override
	public Integer validateStartDate(final OffSupervisionStsHty bean) {
		 String sql = getQuery("OCDSUPST_VALIDATE_START_DATE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDER_BOOK_ID", bean.getOffenderBookId(), "START_DATETIME", bean.getStartDatetime()),
				Integer.class);
	}

	@Override
	public Integer getActiveCount(final OffSupervisionStsHty bean) {
		String sql = getQuery("OCDSUPST_GET_ACTIVE_COUNT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDER_BOOK_ID", bean.getOffenderBookId()), Integer.class);
	}
	public Integer preUpdateSupvAccount(final OffSupervisionStsHty bean) {
		final String sql = getQuery("OCDSUPST_UPDATE_ACTIVE_RECORD");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
			parameters.add(new BeanPropertySqlParameterSource(bean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}
	@Override
	public List<CaseloadDeductionProfiles> caseloadDedProfExecuteQuery(final String  caseloadId) {
		final String sql = getQuery("OCDSUPST_FIND_CASELOAD_DEDUCTION_PROFILES");
		final RowMapper<CaseloadDeductionProfiles> OffencesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CaseloadDeductionProfiles.class, cdpMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("caseloadId", caseloadId),
				OffencesRowMapper);
	}
	@Override
	public BigDecimal getMaxOdp(final Integer offenderBookId, final String feeId, final String caseloadId) {
		final String sql = getQuery("OCDSUPST_GET_MAX_ODP_VALUE");
		BigDecimal returnObj=null;
		try {
			returnObj= namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId,"FEE_CODE",feeId, "caseloadId",caseloadId),
						BigDecimal.class);
		}catch (Exception e) {
			return returnObj;
		}
		return returnObj;
	}
	@Override
	public Integer getFeeBillSeq(String caseloadseq) {
		final String sql = getQuery("OCDSUPST_GET_FEE_BILL_CASELOAD_SEQ").replace("%SEQNAME%", caseloadseq);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}
//	@Override
//	public Integer offFeeBillsInsertQuery(OffFeeBills ofFeebillObj) {
//		final String sql = getQuery("OCDSUPST_OFF_FEE_BILLS__INSERT");
//		int[] returnArray = new int[] {};
//		List<SqlParameterSource> parameters = new ArrayList<>();
//		parameters.add(new BeanPropertySqlParameterSource(ofFeebillObj));
//		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
//
//		if (returnArray.length > 0) {
//			return 1;
//		} else {
//			return 0;
//		}
//	}
	@Override
	public Integer updFeeAccounts(FeeAccountProfiles feeAccBean) {
		final String sql = getQuery("OCDSUPST_UPDATE_FEE_ACCOUNT_STATUS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
			parameters.add(new BeanPropertySqlParameterSource(feeAccBean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer getstaffId(String userId) {
		final String sql = getQuery("OCDSUPST_GET_CURRENT_STAFFDETAIL");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("userId",userId), Integer.class);
	}
	
//	@Override
//	public Integer offFeeBillTransInsertQuery(OffFeeBillTransactions ofFeebillTranObj) {
//		final String sql = getQuery("OCDSUPST_OFF_FEE_BILLS_TRANSACTIONS_INSERT");
//		int[] returnArray = new int[] {};
//		List<SqlParameterSource> parameters = new ArrayList<>();
//		parameters.add(new BeanPropertySqlParameterSource(ofFeebillTranObj));
//		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
//
//		if (returnArray.length > 0) {
//			return 1;
//		} else {
//			return 0;
//		}
//	}
	public Integer getBillTranId(final String billId) {
		final String sql = getQuery("OCDSUPST_OFF_FEE_BILLS_TRANSACTIONS_PRE_INSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("BILL_ID",billId), Integer.class);
	}

	@Override
	public Integer getFeeBillCount(final Integer offenderBookId) {
		final String sql = getQuery("OCDSUPST_OFF_FEE_BILL_COUNT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_BOOK_ID",offenderBookId), Integer.class);
	}
	@Override
	public Integer getSupvRecCount(final Integer offenderBookId, final String caseloadId) {
		final String sql = getQuery("OCDSUPST_GET_SUPV_REC_COUNT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offender_book_id",offenderBookId,"caseloadId", caseloadId), Integer.class);
	}
	@Override
	public List<FeeAccountProfiles> getCurrentSupRecordsData(final BigDecimal offenderBookId) {
		final String sql = getQuery("OCDSUPST_GET_FEE_ACCOUNT_SUPERVISION_RECORDS_DATA");
		final RowMapper<FeeAccountProfiles> feeAccRowMapper = Row2BeanRowMapper.makeMapping(sql,
				FeeAccountProfiles.class, feeAccMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_BOOK_ID", offenderBookId), feeAccRowMapper);
	}

	@Override
	public List<OffSupervisionStsHty> getActiveSUpvStHstryRecord(FeeAccountProfiles feeBean) {
		final String sql = getQuery("OCDSUPST_SUPERVISION_HSTY_CURRENT_EXECUTE_QUERY_RECORD");
		final RowMapper<OffSupervisionStsHty> SupHstyRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffSupervisionStsHty.class, supHstyMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", feeBean.getOffenderBookId()),
				SupHstyRowMapper);
	}
	@Override
	public BigDecimal feeOverrodeExists(final BigDecimal offenderFeeId) {
		final String sql = getQuery("OCDSUPST_ACTIVE_FEE_OVERRIDE_EXISTS");
		BigDecimal returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERFEEID",offenderFeeId), BigDecimal.class);
		} catch(EmptyResultDataAccessException e) {
			returnVal = null;
		}
		return returnVal;
	}
	@Override
	public Integer feeOverrodeExists(final String feeCode) {
		final String sql = getQuery("OCDSUPST_CHECK_PREPAID_ACCOUNT");
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("feeCode",feeCode), Integer.class);
	}
	/**
	 * This method will update the transaction in the table
	 * 
	 * @param offTrans
	 * @return String
	 */
	public String checkAccountSatus(final OffSupervisionStsHty bean) {
		String openAnAccount = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_OPEN_AN_ACCOUNT", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("CHK_ACCOUNT_STATUS").declareParameters(sqlParameters);
		inParamMap.put("P_CSLD_ID", bean.getCaseloadId());
		inParamMap.put("P_OFFENDER_ID", bean.getOffenderId());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			openAnAccount = String.valueOf(returnObject.get("P_OPEN_AN_ACCOUNT"));
		} catch (Exception e) {
//			logger.error("checkAccountSatus :" + e);
		}
		return openAnAccount;
	}

	

}

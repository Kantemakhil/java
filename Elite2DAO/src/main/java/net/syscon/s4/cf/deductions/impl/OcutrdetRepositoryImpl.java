package net.syscon.s4.cf.deductions.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.deductions.OcutrdetRepository;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.GlTransactions;

@Repository
public class OcutrdetRepositoryImpl extends RepositoryBase implements OcutrdetRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcutrdetRepositoryImpl.class);

	private final Map<String, FieldMapper> refMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> billDetMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("BILL_ID", new FieldMapper("billId")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("OFFENDER_FEE_ID", new FieldMapper("offenderFeeId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("BILL_DATE", new FieldMapper("billDate"))
			.put("BILL_GENERATE_DATETIME", new FieldMapper("billGenerateDatetime"))
			.put("BILL_GENERATE_AMOUNT", new FieldMapper("billGenerateAmount"))
			.put("BILL_GENERATE_STATUS", new FieldMapper("billGenerateStatus"))
			.put("BILL_GENERATE_STAFF_ID", new FieldMapper("billGenerateStaffId"))
			.put("BILL_GENERATE_USER", new FieldMapper("billGenerateUser"))
			.put("BILL_EXPECTED_STATEMENT_DATE", new FieldMapper("billExpectedStatementDate"))
			.put("BILL_EXPECTED_AR_DUE_DATE", new FieldMapper("billExpectedArDueDate"))
			.put("BILL_EXPECTED_LDPP_START_DATE", new FieldMapper("billExpectedLdppStartDate"))
			.put("BILL_EXPECTED_LDPP_END_DATE", new FieldMapper("billExpectedLdppEndDate"))
			.put("STATEMENT_GENERATED_FLAG", new FieldMapper("statementGeneratedFlag"))
			.put("BILLING_STATEMENT_ID", new FieldMapper("billingStatementId"))
			.put("BI", new FieldMapper("billOverrideIncreaseAmount"))
			.put("BD", new FieldMapper("billOverrideDecreaseAmount"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();

	private final Map<String, FieldMapper> billTransDetMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("BILL_ID", new FieldMapper("billId")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("OFFENDER_FEE_ID", new FieldMapper("offenderFeeId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))

			.put("BILL_TXN_NO", new FieldMapper("billTxnNo"))
			.put("BILL_TXN_DATETIME", new FieldMapper("billTxnDatetime"))
			.put("BILL_TXN_STAFF_ID", new FieldMapper("billTxnStaffId"))
			.put("BILL_TXN_USER", new FieldMapper("billTxnUser"))
			.put("BILL_TXN_CATEGORY", new FieldMapper("billTxnCategory"))
			.put("BILL_TXN_TYPE", new FieldMapper("billTxnType"))
			.put("BILL_TXN_AMOUNT", new FieldMapper("billTxnAmount")).put("TRUST_TXN_ID", new FieldMapper("trustTxnId"))
			.put("TRUST_TXN_ENTRY_SEQ", new FieldMapper("trustTxnEntrySeq"))
			.put("OFF_ADJ_CANC_RSN", new FieldMapper("offAdjCancRsn"))
			.put("OFF_ADJ_SUB_RSN", new FieldMapper("offAdjSubRsn"))
			.put("OFF_ADJ_TXN_ID", new FieldMapper("offAdjTxnId"))
			.put("OFF_ADJ_REV_RSN", new FieldMapper("offAdjRevRsn")).put("BILL_STATUS", new FieldMapper("billStatus"))
			.put("BILL_AGING_START_DATE", new FieldMapper("billAgingStartDate"))
			.put("BILL_AGING_END_DATE", new FieldMapper("billAgingEndDate"))
			.put("BILL_TXN_COMMENT", new FieldMapper("billTxnComment"))
			.put("ORIGINAL_BILL_ID", new FieldMapper("originalBillId"))
			.put("ORIGINAL_BILL_TXN_NO", new FieldMapper("originalBillTxnNo"))
			.put("ORIGINAL_OFF_ADJ_TXN_ID", new FieldMapper("originalOffAdjTxnId"))
			.put("STATEMENT_GENERATED_FLAG", new FieldMapper("statementGeneratedFlag"))
			.put("BILLING_STATEMENT_ID", new FieldMapper("billingStatementId"))
			.put("adjustment_amount", new FieldMapper("adjustmentAmount"))
			.put("description", new FieldMapper("description"))
			.put("bill_status_description", new FieldMapper("billStatusDescription"))
			.put("PAYMENTAMOUNT", new FieldMapper("paymentAmount"))
			.put("OWINGAMOUNT", new FieldMapper("balanceOwingAmount"))
			.put("txnUsage", new FieldMapper("txnUsage"))
			.build();
	
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", new FieldMapper("profileCode")).put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName"))
			.put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2")).put("DESCRIPTION", new FieldMapper("description"))
			.build();

	private final Map<String, FieldMapper> roleIdMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ROLE_ID", new FieldMapper("roleId")).build();

	/**
	 * Creates new OtdocfeeRepositoryImpl class Object
	 */
	public OcutrdetRepositoryImpl() {
		// OcufovdtRepositoryImpl
	}

	@Override
	public List<ReferenceCodes> overrideTypeRecordGroup() {
		final String sql = getQuery("OCUTRDET_OVERRIDE_TYPE_RG");
		List<ReferenceCodes> returnList = new ArrayList<>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refMapping);

		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("overrideTypeRecordGroup ", e);
			return Collections.emptyList();
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchBean
	 *            FeeOverrideDetails
	 * @return List<FeeOverrideDetails>
	 */
	@Override
	public List<OffFeeBills> billDetailsExecuteQuery(OffFeeBills searchBean) {
		final String sql = getQuery("OCUTRDET_FIND_BILL_OVERRIDE_DETAILS");
		final RowMapper<OffFeeBills> mapper = Row2BeanRowMapper.makeMapping(sql, OffFeeBills.class, billDetMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("offenderFeeId", searchBean.getOffenderFeeId()),
				mapper);
	}

	@Override
	public List<OffFeeBillTransactions> billTransDetailsExecuteQuery(OffFeeBillTransactions searchBean) {
		final String sql = getQuery("OCUTRDET_FIND_BILL_TRANS_HIST_DETAILS");
		final RowMapper<OffFeeBillTransactions> mapper = Row2BeanRowMapper.makeMapping(sql,
				OffFeeBillTransactions.class, billTransDetMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("billId", searchBean.getBillId(),"BILL_GENERATE_AMOUNT",searchBean.getAdjustmentAmountTot()), mapper);
	}

	@Override
	public String getDeductionDesc(final String deductionType) {
		final String sql = getQuery("OCUTRDET_GET_DEDUCTION_DESC");
		String result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("deductionType", deductionType),
					String.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return result;
	}

	@Override
	public String getFrequency(final String code) {
		final String sql = getQuery("OCUTRDET_GET_FREQUENCY");
		String result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("code", code), String.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
			result = null;
		}
		return result;
	}

	@Override
	public String getLocation(final String location) {
		final String sql = getQuery("OCUTRDET_GET_LOCATION");
		String result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("location", location), String.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return result;
	}

	@Override
	public String getTxnTypeDescription(String billTxnType) {
		final String sql = getQuery("OCUTRDET_GET_DESCRIPTION_TXN_TYPE");
		String returnValue;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("billTxnType", billTxnType),
					String.class);
		} catch (Exception e) {
			returnValue = null;
		}
		return returnValue;
	}

	@Override
	public String getFrequencyType(String code) {
		final String sql = getQuery("OCUTRDET_GET_FREQUENCYTYPE");
		String result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("code", code), String.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
			result = null;
		}
		return result;
	}

	@Override
	public String feeActStatusDescription(String feeActStatus) {
		final String sql = getQuery("OCUTRDET_GET_FEEACCOUNT_STATUS_DESC");
		String result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("code", feeActStatus), String.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
			result = null;
		}
		return result;
	}

	@Override
	public String getBillStatusForBillId(final String billId) {
		final String sql = getQuery("OCUTRDET_GET_BILL_STATUS_FOR_BILL_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("billId", billId), String.class);
	}

	@Override
	public List<OffFeeBillTransactions> getBillTransDetails(String billId) {
		final String sql = getQuery("OCUTRDET_GET_BILL_STATUS_FOR_BILL_ID_TRANSDETAILS_SAVE");
		final RowMapper<OffFeeBillTransactions> mapper = Row2BeanRowMapper.makeMapping(sql, OffFeeBillTransactions.class, billTransDetMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("billId", billId),
				mapper);
		
	}

	@Override
	public Integer offFeeBillTransInsertQuery(List<OffFeeBillTransactions> ofFeebillTranObj) {	
		final String sql = getQuery("OCUTRDET_OFF_FEE_BILLS_TRANSACTIONS_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffFeeBillTransactions list : ofFeebillTranObj) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (ofFeebillTranObj.size() == returnArray.length) {
			return 1;
		} else {
			
			return 0;
		}	
		
	}
	
	@Override
    public String getBillStatusDesc(final String code) {
        final String sql = getQuery("OCUTRDET_OFF_FFE_BILL_STATUS_DESCRIPTION");
        String result = null;
        try{
            result = namedParameterJdbcTemplate.queryForObject(sql,createParams("code",code),String.class);
        } catch(Exception e) {
            logger.error("Exception :", e);
            result = null;
        }
        return result;
    }
	@Override
	public GlTransactions getGlTransData(Integer trustTxnId,Integer trustTxnEntrySeq) {
		final String sql = getQuery("OCUTRDET_OFF_FFE_BILL_GL_TRANS_DETAILS");
		GlTransactions returnData=new GlTransactions();
		 try{
		returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("trustTxnId",trustTxnId,"trustTxnEntrySeq",trustTxnEntrySeq), GlTransactions.class);
		 } catch(Exception e) {
	            logger.error("Exception :", e);
	            return returnData;
	        }
		return returnData;
	}
	
	@Override
	public OffenderTransactions getOffTransData(Integer trustTxnId,Integer trustTxnEntrySeq) {
		final String sql = getQuery("OCUTRDET_OFF_FFE_BILL_OFFENDER_TRANS_DETAILS");
		OffenderTransactions returnData=new OffenderTransactions();
		 try{
		returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("trustTxnId",trustTxnId,"trustTxnEntrySeq",trustTxnEntrySeq), new BeanPropertyRowMapper<OffenderTransactions>(OffenderTransactions.class));
		 } catch(Exception e) {
	            logger.error("Exception :", e);
	            return returnData;
	        }
		return returnData;
	}
	@Override
	public List<SystemProfiles> sysPflBillAdjusExecuteQuery() {
		final String sql = getQuery("OCUTRDET_SYSPFL_BILL_ADJUS_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), SystemProfilesRowMapper);
		return returnList;
	}
	
	@Override
	public List<SystemProfiles> sysPflBillStatusExecuteQuery() {
		final String sql = getQuery("OCUTRDET_SYSPFL_BILL_STATUS_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), SystemProfilesRowMapper);
		return returnList;
	}
	@Override
	public Date getBillDateForBillId(final String billId) {
		final String sql = getQuery("OCUTRDET_GET_BILL_STATUS_FOR_BILL_ID_DATE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("billId", billId), Date.class);
	}

	@Override
	public String getBillDetailsCommentValue(final String billId) {
		final String sql = getQuery("OCUTRDET_GET_BILL_TRANACTION_COMMENT_RECENT");
		try {
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("billId", billId), String.class);
		}catch(Exception e) {
			return null;
		}
	}
	
}

package net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inmate.beans.BeneficiaryTransactions;
import net.syscon.s4.inmate.beans.VBankChequeBeneficiaries;
import net.syscon.s4.inmate.trust.deductions.beneficiaryinquiry.OcutrahiRepository;

/**
 * Class OcutrahiRepositoryImpl
 */
@Repository
public class OcutrahiRepositoryImpl extends RepositoryBase implements OcutrahiRepository {

	private final Map<String, FieldMapper> vBankChequeBeneficiariesMapping = new ImmutableMap.Builder<String, FieldMapper>()
		.put("TXN_ENTRY_DATE", 						new FieldMapper("txnEntryDate"))
		.put("TXN_ENTRY_TIME", 						new FieldMapper("txnEntryTime"))
		.put("CHEQUE_TXN_ID", 						new FieldMapper("chequeTxnId"))
		.put("CHEQUE_AMOUNT", 						new FieldMapper("chequeAmount"))
		.put("CREATE_DATE", 						new FieldMapper("createDate"))
		.put("CHEQUE_PAID_DATE", 					new FieldMapper("chequePaidDate"))
		.put("PERSON_ID", 							new FieldMapper("personId"))
		.put("CHEQUE_NUMBER", 						new FieldMapper("chequeNumber"))
		.put("TXN_POST_USAGE", 						new FieldMapper("txnPostUsage"))
		.put("TXN_ENTRY_DESC", 						new FieldMapper("txnEntryDesc"))
		.put("CHEQUE_STATUS", 						new FieldMapper("chequeStatus"))
		.put("CORPORATE_ID", 						new FieldMapper("corporateId"))
		.build();
	private final Map<String, FieldMapper> beneficiaryTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
		.put("TXN_ENTRY_TIME", 						new FieldMapper("txnEntryTime"))
		.put("UNKNOWN_BEN_ID", 						new FieldMapper("unknownBenId"))
		.put("REV_TXN_FLAG", 						new FieldMapper("revTxnFlag"))
		.put("PERSON_ID", 							new FieldMapper("personId"))
		.put("RECEIPT_TXN_TYPE", 					new FieldMapper("receiptTxnType"))
		.put("GL_ENTRY_SEQ", 						new FieldMapper("glEntrySeq"))
		.put("CHECK_CLEARED_DATE", 					new FieldMapper("checkClearedDate"))
		.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
		.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
		.put("BENEFICIARY_CLEARED_FLAG", 			new FieldMapper("beneficiaryClearedFlag"))
		.put("TXN_TYPE", 							new FieldMapper("txnType"))
		.put("REV_TXN_ENTRY_SEQ", 					new FieldMapper("revTxnEntrySeq"))
		.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
		.put("REV_GL_ENTRY_SEQ", 					new FieldMapper("revGlEntrySeq"))
		.put("TXN_ID", 								new FieldMapper("txnId"))
		.put("BENEFICIARY_ID", 						new FieldMapper("beneficiaryId"))
		.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
		.put("OFFENDER_DEDUCTION_ID", 				new FieldMapper("offenderDeductionId"))
		.put("TXN_ENTRY_DATE", 						new FieldMapper("txnEntryDate"))
		.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
		.put("TXN_ENTRY_AMOUNT", 					new FieldMapper("txnEntryAmount"))
		.put("BEN_ENTRY_SEQ", 						new FieldMapper("benEntrySeq"))
		.put("REV_BEN_ENTRY_SEQ", 					new FieldMapper("revBenEntrySeq"))
		.put("CASELOAD_ID", 						new FieldMapper("caseloadId"))
		.put("MODIFY_DATE", 						new FieldMapper("modifyDate"))
		.put("REV_TXN_ID", 							new FieldMapper("revTxnId"))
		.put("TXN_ENTRY_SEQ", 						new FieldMapper("txnEntrySeq"))
		.put("ACCOUNT_CODE", 						new FieldMapper("accountCode"))
		.put("TXN_POST_USAGE", 						new FieldMapper("txnPostUsage"))
		.put("TXN_ENTRY_DESC", 						new FieldMapper("txnEntryDesc"))
		.put("CORPORATE_ID", 						new FieldMapper("corporateId"))
		.build();
	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
		.put("PROFILE_CODE", 						new FieldMapper("profileCode"))
		.put("PROFILE_TYPE", 						new FieldMapper("profileType"))
		.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
		.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
		.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
		.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
		.put("OLD_TABLE_NAME", 						new FieldMapper("oldTableName"))
		.put("PROFILE_VALUE", 						new FieldMapper("profileValue"))
		.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
		.put("PROFILE_VALUE_2", 					new FieldMapper("profileValue2"))
		.put("DESCRIPTION", 						new FieldMapper("description"))
		.build();

	/**
	 * Creates new OcutrahiRepositoryImpl class Object
	 */
	public OcutrahiRepositoryImpl() {
		// OcutrahiRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            BeneficiaryTransactions
	 *
	 * @return List<BeneficiaryTransactions>
	 *
	 * 
	 */
	public List<BeneficiaryTransactions> benTxnExecuteQuery(final BeneficiaryTransactions objSearchDao) {
		List<BeneficiaryTransactions> returnList = new ArrayList<>();
		final String sql = getQuery("OCUTRAHI_BENTXN_FIND_BENEFICIARY_TRANSACTIONS");
		final RowMapper<BeneficiaryTransactions> BeneficiaryTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				BeneficiaryTransactions.class, beneficiaryTransactionsMapping);
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("offenderDeductionId", objSearchDao.getOffenderDeductionId(), "personId",
						objSearchDao.getPersonId(), "corporateId", objSearchDao.getCorporateId()),
				BeneficiaryTransactionsRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VBankChequeBeneficiaries
	 *
	 * @return List<VBankChequeBeneficiaries>
	 *
	 * 
	 */
	public List<VBankChequeBeneficiaries> vBcBenExecuteQuery(final VBankChequeBeneficiaries objSearchDao) {
		final String sql = getQuery("OCUTRAHI_VBCBEN_FIND_V_BANK_CHEQUE_BENEFICIARIES");
		final RowMapper<VBankChequeBeneficiaries> VBankChequeBeneficiariesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VBankChequeBeneficiaries.class, vBankChequeBeneficiariesMapping);
		List<VBankChequeBeneficiaries> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("personId", objSearchDao.getPersonId(), "corporateId", objSearchDao.getCorporateId()),
				VBankChequeBeneficiariesRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 * 
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OCUTRAHI_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams(), SystemProfilesRowMapper);
		return returnList;
	}

}

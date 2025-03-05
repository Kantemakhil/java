package net.syscon.s4.cf.offendertransactions.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.offendertransactions.OcudpdisRepository;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderDeductions;

/**
 * Class OcudpdisRepositoryImpl
 */
@Repository
public class OcudpdisRepositoryImpl extends RepositoryBase implements OcudpdisRepository {
	private static Logger logger = LogManager.getLogger(OcudpdisRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> tbdMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("BALANCE", new FieldMapper("code"))
			.put("TXN_TYPE", new FieldMapper("txnType"))
			.put("DESCRIPTION", new FieldMapper("txnDescription")).
			 put("CASELOAD_ID", new FieldMapper("caseloadId"))
			.put("OFFENDER_ID", new FieldMapper("offenderId")).build();

	private final Map<String, FieldMapper> transMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CURRENT_BALANCE", new FieldMapper("currentBalance"))
			.put("SUB_ACCOUNT_TYPE", new FieldMapper("subAccountType")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param paramBean
	 *            OffenderDeductions
	 *
	 * @return List<OffenderDeductions>
	 *
	 * @throws SQLException
	 */
	@Override
	public List<OffenderDeductions> offTransTypeExecuteQuery(final OffenderDeductions paramBean) {
		final String sql = getQuery("OCUDPDIS_FIND_TRANSACTIONDETAILS");
		final RowMapper<OffenderDeductions> offDedRowMaper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, tbdMapping);
		final ArrayList<OffenderDeductions> returnList = (ArrayList<OffenderDeductions>) namedParameterJdbcTemplate
				.query(sql, createParams("caseloadId", paramBean.getCaseloadId()), offDedRowMaper);
		return returnList;
	}

	/**
	 * This method is used to get the offender sub account balance based on
	 *
	 * @param paramBean
	 *            OffenderDeductions
	 *
	 * @throws SQLException
	 */
	@Override
	public BigDecimal offAccBalExecuteQuery(final OffenderDeductions paramBean) {
		final String sql = getQuery("OCUDPDIS_FIND_BALANCEDETAILS");
		BigDecimal amount = null;
		try {
			
			amount = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", paramBean.getOffenderId(),
					"caseloadId", paramBean.getCaseloadId(), "txnType", paramBean.getTxnType()), BigDecimal.class);
		} catch (Exception e) {
			e.getMessage();
		}
		return amount;

	}

	/**
	 * This method will get the sequence
	 * 
	 * @return Integer
	 * 
	 */
	public Integer preInsert() {
		final String sql = getQuery("OCUDPDIS_TXN_ID_SEQUENCE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}

	/**
	 * This method is used to get the offenderBookId based on
	 *
	 * @param paramBean
	 *            OffenderDeductions
	 *
	 * @throws SQLException
	 */

	@Override
	public Long getOffenderBookID(final OffenderDeductions paramBean) {
		final String sql = getQuery("OCUDPDIS_OFFENDER_BOOK_ID");
		Long offenderBookID = null;
		try {
			
			offenderBookID = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderId", paramBean.getOffenderId(), "caseloadId", paramBean.getCaseloadId()),
					Long.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return offenderBookID;
	}

	/**
	 * Insert the records into database table
	 *
	 * @param paramBean
	 *            OffenderTransactions
	 * @throws SQLExceptionssssssssssss
	 */
	@Override
	public Integer tbdDetailsComitt(final OffenderTransactions paramBean) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer genSeq = 0;
		inParamMap.put("P_CSLD_ID", paramBean.getCaseloadId());
		inParamMap.put("P_OFF_ID", paramBean.getOffenderId());
		inParamMap.put("P_OFF_BOOK_ID", paramBean.getOffenderBookId());
		inParamMap.put("P_TRANS_DATE", paramBean.getTxnEntryDate());
		inParamMap.put("P_TRANS_NUMBER", paramBean.getTxnId());
		inParamMap.put("P_TRANS_TYPE", paramBean.getTxnType());
		inParamMap.put("P_SUB_ACT_TYPE", paramBean.getSubAccountType());
		inParamMap.put("P_DED_FLAG", "Y");
		inParamMap.put("P_RECEIPT_AMOUNT", paramBean.getAmount());
		inParamMap.put("P_SHADOW_ID", null);
		inParamMap.put("P_DED_AMOUNT", paramBean.getCurrentBalance());
		inParamMap.put("TXN_SEQUENCE", paramBean.getTxnEntrySeq());
		inParamMap.put("P_INFO_NUMBER", null);
		try {
			namedParameterJdbcTemplate.update(
					"call OMS_OWNER.FINANCIAL.DO_DEDUCTIONS_FINANCIAL(:P_CSLD_ID, :P_OFF_ID, :P_OFF_BOOK_ID, :P_TRANS_TYPE, "
							+ " :P_TRANS_NUMBER, :P_TRANS_DATE, :P_SUB_ACT_TYPE, :P_DED_FLAG, :P_RECEIPT_AMOUNT, :P_SHADOW_ID, :P_DED_AMOUNT, "
							+ " :TXN_SEQUENCE, :P_INFO_NUMBER)",
					inParamMap);
			genSeq = 1;
		} catch (final Exception e) {
			logger.error("In updateOffenderBalance :" + e);

			genSeq = 0;
			return genSeq;
		}
		return genSeq;

	}

	/**
	 * This method is used to get the offender SubAccountType based on
	 *
	 * @param paramBean
	 *            OffenderDeductions
	 *
	 * @throws SQLException
	 */

	@Override
	public OffenderTransactions getSubAccTypeDetails(final OffenderDeductions paramBean) {
		final String sql = getQuery("OCUDPDIS_FIND_BAL_SUB_ACC_DETAILS");
		final RowMapper<OffenderTransactions> offTraRowmaper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, transMapping);
		final OffenderTransactions returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId",
				paramBean.getOffenderId(), "txnType", paramBean.getTxnType(), "caseloadId", paramBean.getCaseloadId()),
				offTraRowmaper);
		return returnObj;
	}
}

package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.TagExceptions;
import net.syscon.s4.pkgs.tag_exceptions.TagExceptionsRepository;
import net.syscon.s4.triggers.CaseloadCurrentAccountsTxns;
import net.syscon.s4.triggers.GlTransactionsT2Repository;

@Repository
public class GlTransactionsT2RepositoryImpl extends RepositoryBase implements GlTransactionsT2Repository {
	Logger logger = LogManager.getLogger(GlTransactionsT2RepositoryImpl.class);
	@Autowired
	TagExceptionsRepository tagExceptionsRepository;

	final DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	@SuppressWarnings("PMD.AvoidDuplicateLiterals")
	public Integer acCount(final String caseloadId, final BigDecimal accountCode, final Date txnEntryDate)
			throws Exception {
		final String sql = getQuery("GL_TRANSACTIONS_T2_AC_COUNT");
		Integer result = 0;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("caseloadId", caseloadId,
					"accountCode", accountCode, "txnEntryDate", sdf.format(txnEntryDate)), Integer.class);
		} catch (final Exception e) {
			final TagExceptions tagExceptions = new TagExceptions();
			result = 0;
			logger.info("v_Error_Msg:= SQLERRM");
			logger.info("v_Trigger_Name:= 'GL_TRANSACTIONS_T2'");
			logger.info("v_Error_Location:= 'GL_TRANSACTIONS_T2 TRIGGER INNER EXCEPTION..1'");
			logger.info("TAG_EXCEPTIONS(v_Trigger_Name, v_Error_Msg, v_Error_Location)");
			tagExceptions.setProcedureName("GL_TRANSACTIONS_T2");
			tagExceptions.setErrorMessage(e.getMessage());
			tagExceptions.setErrorLocation("GL_TRANSACTIONS_T2 TRIGGER INNER EXCEPTION..1");
			tagExceptions(tagExceptions);
			logger.error("Account Code not found in Caseload Account Summaries for account " + accountCode
					+ "  and caseload " + caseloadId, e);
			throw new Exception("Account Code not found in Caseload Account Summaries for account " + accountCode
					+ "  and caseload " + caseloadId, e);
		}
		return result;
	}

	@Override
	@SuppressWarnings("PMD.AvoidDuplicateLiterals")
	public String pAcntPosting(final Integer accountCode) throws Exception {
		final String sql = getQuery("GL_TRANSACTIONS_T2_P_ACNT_POSTING");
		String result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("accountCode", accountCode),
					String.class);
		} catch (final EmptyResultDataAccessException e) {
			final TagExceptions tagExceptions = new TagExceptions();
			logger.error("pAcntPosting", e);
			result = null;
			logger.info("v_Error_Msg:= SQLERRM");
			logger.info("v_Trigger_Name:= GL_TRANSACTIONS_T2");
			logger.info("v_Error_Location:= GL_TRANSACTIONS_T2 TRIGGER NO_DATA_FOUND EXCEPTION..1");
			logger.info("TAG_EXCEPTIONS(v_Trigger_Name, v_Error_Msg, v_Error_Location)");
			tagExceptions.setProcedureName("GL_TRANSACTIONS_T2");
			tagExceptions.setErrorMessage(e.getMessage());
			tagExceptions.setErrorLocation("GL_TRANSACTIONS_T2 TRIGGER INNER EXCEPTION..1");
			tagExceptions(tagExceptions);
			logger.error("Account code " + accountCode + " does not exist." + accountCode, e);
			throw new Exception("Account code " + accountCode + " does not exist.", e);
		} catch (final IncorrectResultSizeDataAccessException e) {
			final TagExceptions tagExceptions = new TagExceptions();
			result = null;
			logger.info("v_Error_Msg:= SQLERRM");
			logger.info("v_Trigger_Name:= GL_TRANSACTIONS_T2");
			logger.info("v_Error_Location:= GL_TRANSACTIONS_T2 TRIGGER TOO_MANY_ROWS EXCEPTION");
			logger.info("TAG_EXCEPTIONS(v_Trigger_Name, v_Error_Msg, v_Error_Location)");
			tagExceptions.setProcedureName("GL_TRANSACTIONS_T2");
			tagExceptions.setErrorMessage(e.getMessage());
			tagExceptions.setErrorLocation("GL_TRANSACTIONS_T2 TRIGGER INNER EXCEPTION..1");
			tagExceptions(tagExceptions);
			logger.error("Error: More than one  TXN_POSTING_TYPE exists for Account code " + accountCode, e);
			throw new Exception("Error: More than one  TXN_POSTING_TYPE exists for Account code " + accountCode, e);
		} catch (final Exception e) {
			final TagExceptions tagExceptions = new TagExceptions();
			logger.error("pAcntPosting", e);
			result = null;
			logger.info("v_Error_Msg:= SQLERRM");
			logger.info("v_Trigger_Name:= GL_TRANSACTIONS_T2");
			logger.info("v_Error_Location:= GL_TRANSACTIONS_T2 TRIGGER INNER EXCEPTION..2");
			logger.info("TAG_EXCEPTIONS(v_Trigger_Name, v_Error_Msg, v_Error_Location)");
			tagExceptions.setProcedureName("GL_TRANSACTIONS_T2");
			tagExceptions.setErrorMessage(e.getMessage());
			tagExceptions.setErrorLocation("GL_TRANSACTIONS_T2 TRIGGER INNER EXCEPTION..1");
			tagExceptions(tagExceptions);
			logger.error("Error: GL_TRANSACTIONS_T2. " + e.getMessage());
			throw new Exception("Error: GL_TRANSACTIONS_T2.  " + e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Integer acPdId(final Date txnEntryDate) throws Exception {
		final String sql = getQuery("GL_TRANSACTIONS_T2_AC_PD_ID");
		Integer result = 0;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("txnEntryDate", sdf.format(txnEntryDate)), Integer.class);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("pAcntPosting", e);
			result = null;
			logger.info("v_Trigger_Name:= GL_TRANSACTIONS_T2");
			logger.info("v_Error_Location:= GL_TRANSACTIONS_T2 TRIGGER NO_DATA_FOUND EXCEPTION..2");
			logger.info("TAG_EXCEPTIONS(v_Trigger_Name, v_Error_Msg, v_Error_Location)");
			throw new Exception("Error: Could not find account Period Id in Account_Periods table.", e);
		} catch (final Exception e) {
			final TagExceptions tagExceptions = new TagExceptions();
			logger.error("acPdId", e);
			result = null;
			logger.info("v_Error_Msg:= SQLERRM");
			logger.info("v_Trigger_Name:= GL_TRANSACTIONS_T2");
			logger.info("v_Error_Location:= GL_TRANSACTIONS_T2 TRIGGER INNER EXCEPTION..3");
			logger.info("TAG_EXCEPTIONS(v_Trigger_Name, v_Error_Msg, v_Error_Location)");
			tagExceptions.setProcedureName("GL_TRANSACTIONS_T2");
			tagExceptions.setErrorMessage(e.getMessage());
			tagExceptions.setErrorLocation("GL_TRANSACTIONS_T2 TRIGGER INNER EXCEPTION..1");
			tagExceptions(tagExceptions);
			logger.error("RAISE_APPLICATION_ERROR(-20009, 'Error: GL_TRANSACTIONS_T2. '||SQLERRM)" + e.getMessage());
			throw new Exception("Error: GL_TRANSACTIONS_T2.  " + e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Integer acntId() throws Exception {
		final String sql = getQuery("GL_TRANSACTIONS_T2_ACNT_ID");
		Integer result = 0;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (final EmptyResultDataAccessException e) {
			final TagExceptions tagExceptions = new TagExceptions();
			logger.error("pAcntPosting", e);
			result = null;
			logger.info("v_Error_Msg:= SQLERRM");
			logger.info("v_Trigger_Name:= GL_TRANSACTIONS_T2");
			logger.info("v_Error_Location:= GL_TRANSACTIONS_T2 TRIGGER NO_DATA_FOUND EXCEPTION..3");
			logger.info("TAG_EXCEPTIONS(v_Trigger_Name, v_Error_Msg, v_Error_Location)");
			tagExceptions.setProcedureName("GL_TRANSACTIONS_T2");
			tagExceptions.setErrorMessage(e.getMessage());
			tagExceptions.setErrorLocation("GL_TRANSACTIONS_T2 TRIGGER INNER EXCEPTION..1");
			tagExceptions(tagExceptions);
			logger.error("Error: Caseload Current Account ID Sequence Not Found.", e);
			throw new Exception("Error: Caseload Current Account ID Sequence Not Found.", e);
		} catch (final Exception e) {
			final TagExceptions tagExceptions = new TagExceptions();
			logger.error("acntId", e);
			result = null;
			logger.info("v_Error_Msg:= SQLERRM");
			logger.info("v_Trigger_Name:= GL_TRANSACTIONS_T2");
			logger.info("v_Error_Location:= GL_TRANSACTIONS_T2 INNER EXCEPTIONS..4");
			logger.info("TAG_EXCEPTIONS(v_Trigger_Name, v_Error_Msg, v_Error_Location)");
			tagExceptions.setProcedureName("GL_TRANSACTIONS_T2");
			tagExceptions.setErrorMessage(e.getMessage());
			tagExceptions.setErrorLocation("GL_TRANSACTIONS_T2 TRIGGER INNER EXCEPTION..1");
			tagExceptions(tagExceptions);
			logger.error("'Error: GL_TRANSACTIONS_T2. " + e.getMessage());
			throw new Exception("'Error: GL_TRANSACTIONS_T2. " + e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Integer integer(final List<CaseloadCurrentAccountsTxns> caseCurAccTxList) throws Exception {
		Integer returnValue = 0;
		final String sql = getQuery("GL_TRANSACTIONS_T2_CASELOAD_CURRENT_ACCOUNTS_TXNS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CaseloadCurrentAccountsTxns obj : caseCurAccTxList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (caseCurAccTxList.size() == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			logger.error("acntId", e);
			returnValue = 0;
			logger.info("v_Error_Msg:= SQLERRM");
			logger.info("v_Trigger_Name:= GL_TRANSACTIONS_T2");
			logger.info("v_Error_Location:= GL_TRANSACTIONS_T2 INNER EXCEPTIONS..5");
			logger.error("Error: Cannot insert into caseload current account txns for account" + e.getMessage());
			throw new Exception("Error: Cannot insert into caseload current account txns for account" + e.getMessage(),
					e);
		}
		return returnValue;

	}

	@Transactional
	@Override
	public Integer tagExceptions(final TagExceptions tagExceptions) {
		List<TagExceptions> tagExceptionsList = new ArrayList<TagExceptions>();
		final TagExceptions targetObj = new TagExceptions();
		BeanUtils.copyProperties(tagExceptions, targetObj);
		Integer resultFlag = 0;
		final Long sidCur = tagExceptionsRepository.sidCur();
		String moduleNameCur = null;
		if (Optional.ofNullable(sidCur).isPresent()) {
			moduleNameCur = tagExceptionsRepository.moduleNameCur(sidCur);
		}
		final String systemProfilesCur = tagExceptionsRepository.systemProfilesCur();
		if (Optional.ofNullable(systemProfilesCur).isPresent() && "Y".equals(systemProfilesCur)) {
			targetObj.setTagErrorId(tagExceptionsRepository.tagErrorId());
			targetObj.setSid(sidCur);
			targetObj.setModuleName(moduleNameCur);
			targetObj.setModifyDatetime(new Date());
			tagExceptionsList.add(targetObj);
			try {
				resultFlag = tagExceptionsRepository.tagExceptionsInsert(tagExceptionsList);
			} catch (final Exception e) {
				logger.error("tagExceptionsTrigger", e);
				targetObj.setTagErrorId(tagExceptionsRepository.tagErrorId());
				targetObj.setProcedureName("TAG_EXCEPTIONS");
				targetObj.setErrorMessage(e.getMessage());
				targetObj.setErrorLocation("TAG_EXCEPTIONS PROCEDURE");
				tagExceptionsList = new ArrayList<TagExceptions>();
				tagExceptionsList.add(targetObj);
				resultFlag = tagExceptionsRepository.tagExceptionsInsert(tagExceptionsList);
			}
		}
		return resultFlag;
	}
}

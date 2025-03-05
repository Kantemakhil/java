package net.syscon.s4.inmate.trust.deductions.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.beans.OffenderAdjustmentTxns;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.trust.deductions.OtucobwoRepository;

/**
 * Class OtucobwoRepositoryImpl
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
@Repository
public class OtucobwoRepositoryImpl extends RepositoryBase implements OtucobwoRepository {

	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtucobwoRepositoryImpl.class.getName());
	

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TXN_TYPE", new FieldMapper("txnType"))
			.put("DESCRIPTION",new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> offenderAdjustmentTxnsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DR_ACCOUNT_CODE", 	new FieldMapper("drAccountCode"))
			.put("TXN_POSTING_TYPE",	new FieldMapper("txnPostingType"))
			.put("ACCOUNT_CODE",		new FieldMapper("accountCode"))
			.put("CR",					new FieldMapper("cr"))
			.put("TXN_TYPE",			new FieldMapper("txnType"))			
			.build();
	
	

	/**
	 * Creates new OtucobwoRepositoryImpl class Object
	 */
	public OtucobwoRepositoryImpl() {
	}
	/**
	 * Used to capture results from select query
	 * 
	 * @return List<TransactionOperation>
	 */
	public List<TransactionOperation> cgfkCobwoAdjustmentReasoRecordGroup(String caseloadId) {
		final String sql = getQuery("OTUCOBWO_FIND_CGFKCOBWOADJUSTMENTREASO");
		final RowMapper<TransactionOperation> MRowMapper = Row2BeanRowMapper.makeMapping(sql,
				TransactionOperation.class, mMapping);
		List<TransactionOperation> returnList= new ArrayList<TransactionOperation>();

		try {
			returnList= namedParameterJdbcTemplate.query(sql, createParams("caseloadId",caseloadId), MRowMapper);
		} catch (Exception e) {
			logger.error("cgfkCobwoAdjustmentReasoRecordGroup",e);;
		}
		return returnList;
	}
	@Override
	public Long getTxnId() {
		final String sql = getQuery("OTUCOBWO_GET_TXN_ID");
		Long txnId=null;
		txnId= namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		return txnId;
	}
	@Override
	public List<OffenderAdjustmentTxns> writeOffGl(OffenderAdjustmentTxns bean) {
		final String sql = getQuery("OTUCOBWO_WRITEOFFGL");
		final RowMapper<OffenderAdjustmentTxns> MRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderAdjustmentTxns.class, offenderAdjustmentTxnsMapping);
		List<OffenderAdjustmentTxns> returnList= new ArrayList<OffenderAdjustmentTxns>();

		try {
			returnList= namedParameterJdbcTemplate.query(sql, createParams("deductionType",bean.getDeductionType(),"caseloadId",bean.getCaseloadId(),
					"caseloadType",bean.getCaseloadType(),"txnType",bean.getAdjustmentReasonCode()), MRowMapper);
		} catch (Exception e) {
			logger.error("writeOffGl",e);;
		}
		return returnList;
	}
	
	@Override
	public Integer insertintoGltransactionsDr(OffenderAdjustmentTxns bean) {
		final String sql = getQuery("OTUCOBWO_INSERT_INTO_GL_TRANSACTIONS_DR");
		Map<String, Object> paramMap= new HashMap<>();
		paramMap.put("txnId", bean.getTxnId());
		paramMap.put("txnEntrySeq", 1);	
		paramMap.put("glSeq", 1);
		paramMap.put("accountCode", bean.getDrAccountCode());
		
		//paramMap.put("txnEntryDate", transDate);
		paramMap.put("txnType", bean.getTxnType());
		// paramMap.put("txnPostUsage", bean.getTxnPostingType());
		paramMap.put("caseloadId", bean.getCaseloadId());
		paramMap.put("offenderId", bean.getOffenderId());
		paramMap.put("offenderBookId", bean.getOffenderBookId());
		paramMap.put("txnEntryAmount", bean.getAdjustmentAmount());
		paramMap.put("txnEntryDesc", bean.getAdjustmentText());
//		paramMap.put("reconClearFlag", "N");
//		paramMap.put("txnReversedFlag", "N");
		// paramMap.put("infoNumber", null);
		paramMap.put("deductionId", bean.getOffenderDeductionId());
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
		if (returnArray != 0) {
			return returnArray;
		}
		return returnArray;
	}
	
	
	
	
		public Integer insertintoGltransactionsCr(OffenderAdjustmentTxns bean) {
			final String sql = getQuery("OTUCOBWO_INSERT_INTO_GL_TRANSACTIONS_CR");
			Map<String, Object> paramMap= new HashMap<>();
			paramMap.put("txnId", bean.getTxnId());
//			paramMap.put("txnEntrySeq", 1);	
//			paramMap.put("glSeq", 1);
			paramMap.put("accountCode", bean.getAccountCode());
			
			//paramMap.put("txnEntryDate", transDate);
			paramMap.put("txnType", bean.getTxnType());
			// paramMap.put("txnPostUsage", bean.getTxnPostingType());
			paramMap.put("caseloadId", bean.getCaseloadId());
			paramMap.put("offenderId", bean.getOffenderId());
			paramMap.put("offenderBookId", bean.getOffenderBookId());
			paramMap.put("txnEntryAmount", bean.getAdjustmentAmount());
			paramMap.put("txnEntryDesc", bean.getAdjustmentText());
//			paramMap.put("reconClearFlag", "N");
//			paramMap.put("txnReversedFlag", "N");
			// paramMap.put("infoNumber", null);
			paramMap.put("deductionId", bean.getOffenderDeductionId());
			Integer returnArray = null;
			returnArray = namedParameterJdbcTemplate.update(sql, paramMap);
			if (returnArray != 0) {
				return returnArray;
			}
			return returnArray;
		}
		@Override
		public Integer insertintoOffnederAdjustmentTxns(OffenderAdjustmentTxns bean) {
			final String sql = getQuery("OTUCOBWO_INSERT_INTO_OFFENDER_ADJUSTMENT_TXNS");
			Map<String, Object> paramMap= new HashMap<>();
			paramMap.put("txnId", bean.getTxnId());
			paramMap.put("offenderId", bean.getOffenderId());
			paramMap.put("offenderDeductionId", bean.getOffenderDeductionId());
			paramMap.put("txnAmount", bean.getAdjustmentAmount());
			paramMap.put("reason", bean.getAdjustmentReasonCode());
			paramMap.put("commentText", bean.getAdjustmentText());
			Integer count= namedParameterJdbcTemplate.update(sql, paramMap);
			return count;
		}

		@Override
		public Integer updateOffenderdeductions(OffenderAdjustmentTxns bean) {
			final String sql = getQuery("OTUCOBWO_UPDATE_OFFENDER_DEDUCTIONS");
			Map<String, Object> paramMap= new HashMap<>();
			paramMap.put("writeOffamount", bean.getLvWriteOffAmount());
			paramMap.put("reason", bean.getAdjustmentReasonCode());
			paramMap.put("offenderDeductionId", bean.getOffenderDeductionId());			
			Integer count= namedParameterJdbcTemplate.update(sql, paramMap);
			return count;
		}
		@Override
		public List<OffenderDeductions> offenderDeductionCur(Long deductionId) {
			final String sql = getQuery("OTUCOBWO_OFFENDER_DEDUCTION_C");
			final RowMapper<OffenderDeductions> MRowMapper = Row2BeanRowMapper.makeMapping(sql,
					OffenderDeductions.class, offenderAdjustmentTxnsMapping);
			List<OffenderDeductions> returnList= new ArrayList<OffenderDeductions>();

			try {
				returnList= namedParameterJdbcTemplate.query(sql, createParams("deductionId",deductionId), MRowMapper);
			} catch (Exception e) {
				logger.error("offenderDeductionCur",e);;
			}
			return returnList;
		}
}

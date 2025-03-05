package net.syscon.s4.pkgs.tag_delete_legals.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.Orders;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legals.beans.Sentences;
import net.syscon.s4.pkgs.tag_delete_legals.TagdeleteLegalsRepository;

@Repository
public class TagdeleteLegalsRepositoryImpl extends RepositoryBase implements TagdeleteLegalsRepository {

	private final static Logger logger = LogManager.getLogger(TagdeleteLegalsRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("code", new FieldMapper("coe")).build();

	@Override
	public Integer deleteOrder(final BigDecimal offenderBookId, final Long caseId, final Long eventid,String modifyUserId) {
		final String sql = getQuery("DELETE_ORDERS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "orders";
			String whereCondition = "case_id =:p_case_id AND offender_book_id =:p_offender_book_id AND event_id =:p_event_id";
			inputMap.put("p_case_id", caseId);
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_event_id", eventid);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOrder " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql,
				createParams("p_case_id", caseId, "p_offender_book_id", offenderBookId, "p_event_id", eventid));
	}

	@Override
	public List<Orders> orderCur(final BigDecimal offenderBookId, final Long caseId, final Long eventid) {
		final String sql = getQuery("SELECT_ORDERS_CUR");
		final RowMapper<Orders> rowMapper = Row2BeanRowMapper.makeMapping(sql, Orders.class, mapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("p_case_id", caseId, "p_offender_book_id", offenderBookId, "p_event_id", eventid),
				rowMapper);
	}

	@Override
	public Integer deleteOrderCondActivities(final BigDecimal orderId,String modifyUserId) {
		final String sql = getQuery("DELETE_ORDER_PPSL_COND_ACTIVITIES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "order_ppsl_cond_activities";
			String whereCondition = "order_proposal_condition_id IN ( SELECT order_proposal_condition_id FROM order_proposal_conditions WHERE order_id =:order_id)";
			inputMap.put("order_id", orderId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOrderCondActivities " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("order_id", orderId));
	}

	@Override
	public Integer deleteOrderPurposes(final BigDecimal orderid,String modifyUserId) {
		final String sql = getQuery("ORDER_PURPOSES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "order_purposes";
			String whereCondition = "order_id =:order_id";
			inputMap.put("order_id", orderid);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOrderPurposes " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("order_id", orderid));
	}

	@Override
	public Integer deleteOrderProposalCond(final BigDecimal orderid,String modifyUserId) {
		final String sql = getQuery("ORDER_PROPOSAL_COND");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "order_proposal_conditions";
			String whereCondition = "order_id = :order_id";
			inputMap.put("order_id", orderid);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOrderProposalCond " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("order_id", orderid));
	}

	@Override
	public Integer deleteorderProposals(final BigDecimal orderid,String modifyUserId) {
		final String sql = getQuery("ORDER_PROPOSALS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "order_proposals";
			String whereCondition = "order_id =:order_id";
			inputMap.put("order_id", orderid);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteorderProposals " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("order_id", orderid));
	}

	@Override
	public Integer deleteOffSentterms(final BigDecimal offBookId, final Long sentseq,String modifyUserId) {
		final String sql = getQuery("DELETE_OFFENDER_SENTENCE_TERMS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_sentence_terms";
			String whereCondition = "offender_book_id =:p_offender_book_id AND sentence_seq =:p_sentence_seq";
			inputMap.put("p_offender_book_id", offBookId);
			inputMap.put("p_sentence_seq", sentseq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOffSentterms " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql,
				createParams("p_offender_book_id", offBookId, "p_sentence_seq", sentseq));
	}

	@Override
	public Integer deleteOffSentAdjust(final BigDecimal offBookId, final Long sentseq,String modifyUserId) {
		final String sql = getQuery("DELETE_OFFENDER_SENTENCE_ADJUSTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_sentence_adjusts";
			String whereCondition = "offender_book_id =:p_offender_book_id  AND sentence_seq =:p_sentence_seq";
			inputMap.put("p_offender_book_id", offBookId);
			inputMap.put("p_sentence_seq", sentseq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOffSentAdjust " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql,
				createParams("p_offender_book_id", offBookId, "p_sentence_seq", sentseq));
	}

	@Override
	public Integer deleteOffSentStatus(final BigDecimal offBookId, final Long sentseq,String modifyUserId) {
		final String sql = getQuery("DELETE_OFFENDER_SENTENCE_STATUSES");
		Integer count = null;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_sentence_statuses";
			String whereCondition = "offender_book_id =:p_offender_book_id AND sentence_seq =:p_sentence_seq";
			inputMap.put("p_offender_book_id", offBookId);
			inputMap.put("p_sentence_seq", sentseq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOffSentStatus " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql,
				createParams("p_offender_book_id", offBookId, "p_sentence_seq", sentseq));
	}

	@Override
	public Integer deleteOffSentHty(final BigDecimal offBookId, final Long sentseq,String modifyUserId) {
		final String sql = getQuery("DELETE_OFFENDER_SENTENCE_HTY");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_sentence_hty";
			String whereCondition = "offender_book_id =:p_offender_book_id AND sentence_seq =:p_sentence_seq";
			inputMap.put("p_offender_book_id", offBookId);
			inputMap.put("p_sentence_seq", sentseq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOffSentHty " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql,
				createParams("p_offender_book_id", offBookId, "p_sentence_seq", sentseq));
	}

	@Override
	public Integer deleteOffLiceSentence(final BigDecimal offBookId, final Long sentseq,String modifyUserId) {
		final String sql = getQuery("DELETE_OFFENDER_LICENCE_SENTENCES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_licence_sentences";
			String whereCondition = "offender_book_id =:p_offender_book_id AND sentence_seq =:p_sentence_seq";
			inputMap.put("p_offender_book_id", offBookId);
			inputMap.put("p_sentence_seq", sentseq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOffLiceSentence " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql,
				createParams("p_offender_book_id", offBookId, "p_sentence_seq", sentseq));
	}

	@Override
	public Integer deleteCaseAssociatedPersons(final BigDecimal offBookId, final Long caseId,String modifyUserId) {
		final String sql = getQuery("DELETE_CASE_ASSOCIATED_PERSONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "case_associated_persons";
			String whereCondition = "offender_book_id =:p_offender_book_id AND case_id =:p_case_id";
			inputMap.put("p_offender_book_id", offBookId);
			inputMap.put("p_case_id", caseId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteCaseAssociatedPersons " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql,
				createParams("p_offender_book_id", offBookId, "p_case_id", caseId));
	}

	@Override
	public Integer deleteOffcaseAssociatin(final BigDecimal offBookId, final Long caseId,String modifyUserId) {
		final String sql = getQuery("DELETE_OFFENDER_CASE_ASSOCIATIONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_case_associations";
			String whereCondition = "offender_book_id =:p_offender_book_id  AND case_id =:p_case_id";
			inputMap.put("p_offender_book_id", offBookId);
			inputMap.put("p_case_id", caseId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOffcaseAssociatin " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql,
				createParams("p_offender_book_id", offBookId, "p_case_id", caseId));
	}

	@Override
	public Integer deleteOffenderCaseIdentifier(final Long caseId,String modifyUserId) {
		final String sql = getQuery("DELETE_OFFENDER_CASE_IDENTIFIERS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_case_identifiers";
			String whereCondition = "case_id =:p_case_id";
			inputMap.put("p_case_id", caseId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOffenderCaseIdentifier " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_case_id", caseId));
	}

	@Override
	public Integer deleteOffenderCaseStatus(final Long caseId,String modifyUserId) {
		final String sql = getQuery("DELETE_OFFENDER_CASE_STATUSES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_case_statuses";
			String whereCondition = "case_id =:p_case_id";
			inputMap.put("p_case_id", caseId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOffenderCaseStatus " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_case_id", caseId));
	}

	@Override
	public Integer deleteOffendersentCondStatus(final Long sentCondId,String modifyUserId) {
		final String sql = getQuery("DELETE_OFFENDER_SENT_COND_STATUSES");
		Integer count = null;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_sent_cond_statuses";
			String whereCondition = "offender_sent_condition_id =:p_sent_condition_id";
			inputMap.put("p_sent_condition_id", sentCondId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOffendersentCondStatus " + e.getMessage());
		}
		try {
			count = namedParameterJdbcTemplate.update(sql, createParams("p_sent_condition_id", sentCondId));
		} catch (DataAccessException e) {
			logger.error("deleteOffendersentCondStatus :" + e);
			return 0;
		}
		return count;
	}

	@Override
	public Integer deleteOffenderSentConditions(final BigDecimal offBookId, final Long sentseq, final Long sentCondId,String modifyUserId) {
		final String sql = getQuery("DELETE_OFFENDER_SENT_CONDITIONS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_sent_conditions";
			String whereCondition = "offender_book_id =:p_offender_book_id  AND sentence_seq =:p_sentence_seq AND offender_sent_condition_id =:p_sent_condition_id";
			inputMap.put("p_offender_book_id", offBookId);
			inputMap.put("p_sentence_seq", sentseq);
			inputMap.put("p_sent_condition_id", sentCondId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOffenderSentConditions " + e.getMessage());
		}
		return namedParameterJdbcTemplate.update(sql, createParams("p_sent_condition_id", sentCondId,
				"p_offender_book_id", offBookId, "p_sentence_seq", sentseq));
	}

	@Override
	public Integer deleteSentCharges(final BigDecimal offBookId, final Long OffSentenSeq,
			final BigDecimal offChargeId,String modifyUserId) {
		final String sql = getQuery("DELETE_SENTENCE_CHARGES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_sentence_charges";
			String whereCondition = "offender_book_id =:p_offender_book_id AND offender_charge_id =:p_offender_charge_id  AND sentence_seq =:p_sentence_seq";
			inputMap.put("p_offender_book_id", offBookId);
			inputMap.put("p_offender_charge_id", offChargeId);
			inputMap.put("p_sentence_seq", OffSentenSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteSentCharges " + e.getMessage());
		}
		try {
			return namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id", offBookId,
					"p_offender_charge_id", offChargeId, "p_sentence_seq"));
		} catch (Exception e) {
			logger.error("deleteSentCharges", e);
			return 0;
		}
	}

	@Override
	public void deleteOffenderSentences(final Long offenderBookId, final Long sentenceSeq,String modifyUserId) {
		final String sql = getQuery("DELETE_OFFENDER_SENTENCES");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("p_offender_book_id", offenderBookId);
		inParamMap.put("p_sentence_seq", sentenceSeq);
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_sentences";
			String whereCondition = "offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq";
			inputMap.put("p_offender_book_id", offenderBookId);
			inputMap.put("p_sentence_seq", sentenceSeq);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in deleteOffenderSentences " + e.getMessage());
		}
		namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public Integer getCount(final Integer charge) {
		final String sql = getQuery("GET_COUNT_SELECT");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_charge_id", charge),
					Integer.class);
		} catch (Exception e) {
			logger.error("getCount", e);

		}
		return count;
	}

	@Override
	public String existCur(final Long offBookId, final Long offChargeId) {
		final String sql = getQuery("EXIST_CUR");
		String count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_offender_book_id", offBookId, "p_offender_charge_id", offChargeId), String.class);
		} catch (Exception e) {
			logger.error("existCur", e);

		}
		return count;
	}

	@Override
	public Integer existFineSent(final Long offBookId) {
		final String sql = getQuery("EXIST_FINE_SENT");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_book_id", offBookId),
					Integer.class);
		} catch (Exception e) {
			logger.error("getCount", e);

		}
		return count;
	}

	@Override
	public void offenderFinePayments(final Long offBookId,String modifyUserId) {
		final String sql = getQuery("OFFENDER_FINE_PAYMENTS");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			String tableName = "offender_fine_payments";
			String whereCondition = "offender_book_id = :p_offender_book_id";
			inputMap.put("p_offender_book_id", offBookId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in offenderFinePayments " + e.getMessage());
		}
		try {
			namedParameterJdbcTemplate.update(sql, createParams("p_offender_book_id", offBookId));
		} catch (Exception e) {
			logger.error("offenderFinePayments :" + e);
		}

	}

	@Override
	public List<OffensesOutcome> pRefCurs(final Long OffenderBookId, final Long SetenceSeq) {
		final String sql = getQuery("SENTENCE_SEQ_QUERY");
		List<OffensesOutcome> returnList = new ArrayList<OffensesOutcome>();
		final RowMapper<OffensesOutcome> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OffensesOutcome.class,
				mapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("P_OFFENDER_BOOK_ID", OffenderBookId, "P_SENTENCE_SEQ", SetenceSeq), mRowMapper);
		} catch (Exception e) {
			logger.error("PREFCURS :", e);
		}
		return returnList;
	}

	@Override
	public List<Object[]> tagReferenceCodesGetdesccode(final Sentences sentence) {
		final String sql = getQuery("TAG_REFERENCE_CODES_GETDESCCODE");
		final RowMapper<Object[]> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Object[].class, mapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_offender_book_id", sentence.getOffenderBookId(),
				"p_sentence_seq", sentence.getSentenceSeq()), mRowMapper);
	}
}

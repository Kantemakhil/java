package net.syscon.s4.pkgs.tag_sentence_calc.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.pkgs.tag_sentence_calc.TagSentenceCalcRepository;

@Repository
public class TagSentenceCalcRepositoryImpl extends RepositoryBase implements TagSentenceCalcRepository {
	final private static Logger logger = LogManager.getLogger(TagSentenceCalcRepositoryImpl.class.getName());

	@Override
	public Integer deleteOffSentenceAdju(final BigDecimal offBookId, final Long offKeyDateAdjId,String modifyUserId) {
		final String sql = getQuery("DELETE_OFFENDER_SENTENCE_ADJUSTS");
		try {
			String tableName = "offender_sentence_adjusts offadj";
			String whereCondition = "offender_book_id =:p_offbook_id AND offender_key_date_adjust_id =:p_key_date_id AND sentence_seq = (SELECT sentence_seq FROM offender_sentences WHERE offender_book_id =:p_offbook_id AND sentence_seq = offadj.sentence_seq AND sentence_status = 'A')";
			Map<String, Object> inputMap = new HashMap<String, Object>();
			inputMap.put("p_offbook_id", offBookId);
			inputMap.put("p_key_date_id", offKeyDateAdjId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			return namedParameterJdbcTemplate.update(sql,
					createParams("p_offbook_id", offBookId, "p_key_date_id", offKeyDateAdjId));
		} catch (DataAccessException e) {
			logger.error("deleteOffSentenceAdju :" + e);
			return 0;
		}

	}

	@Override
	public Integer insertOffSentenceAdj(final BigDecimal offBookId, final Long offKeyDateAdjId, final String userName) {
		final String sql = getQuery("INSERT_OFFENDER_SENTENCE_ADJUSTS");
		try {
			return namedParameterJdbcTemplate.update(sql, createParams("p_offbook_id", offBookId, "p_key_date_id",
					offKeyDateAdjId, "createUserId", userName));
		} catch (DataAccessException e) {
			logger.error("insertOffSentenceAdj :" + e);
			return 0;
		}
	}

	private final Map<String, FieldMapper> referenceCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SENTENCE_SEQ", new FieldMapper("sentenceSeq")).put("START_DATE", new FieldMapper("startDate"))
			.put("END_DATE", new FieldMapper("endDate"))
			.put("CONSEC_TO_SENTENCE_SEQ", new FieldMapper("consecutiveToLine"))
			.put("SENTENCE_CATEGORY", new FieldMapper("category"))
			.put("SENTENCE_CALC_TYPE", new FieldMapper("sentenceCalcType"))
			.put("AGG_SENTENCE_SEQ", new FieldMapper("aggSentenceSeq")).build();

	@Override
	public List<OffenderSentences> getSentencesCurser(final Long offenderBookId) {
		final String sql = getQuery("GET_SENTENCE_CURSER");
		List<OffenderSentences> returnList = new ArrayList<OffenderSentences>();

		final RowMapper<OffenderSentences> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderSentences.class,
				referenceCodeMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("pOffBookId", offenderBookId), rowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("oseCurSelectOperation", e);
		}
		return returnList;
	}

	@Override
	public Date getEndDate(final Long offenderBookId, final Long sentenceSeq, final Date vStartDate, final int i) {
		final String sql = getQuery("GET_END_DATE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("pStartDate", vStartDate, "pOffBookId",
				offenderBookId, "pSentSeq", sentenceSeq, "pTermSeq", i), Date.class);
	}

	@Override
	public Date calculateMonths(final Date dateinput, final Long months) {
		final String sql = getQuery("CALCULATE_MONTHS_VALUE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("dateinput", dateinput, "months", months),
				Date.class);
	}

	@Override
	public Long getDaysBetween(final Date endDAte, final Date startDAte) {
		final String sql = getQuery("GET_DAYS_BETWEEN");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("pEndDate", endDAte, "pStartDAte", startDAte), Long.class);
	}

	@Override
	public Date calculateHdcedDate(final Date pStartDate, final Date pEndDate) {
		final String sql = getQuery("CALCULATE_HDCEDDATE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("pEndDate", pEndDate, "pStartDate", pStartDate), Date.class);
	}

	@Override
	public List<OffenderSentences> getSedcalculatedDate(final Long offenderBookId,
			final BigDecimal getConsecToSentenceSeq) {
		final String sql = getQuery("GET_SED_CALCULATED_DATE");
		List<OffenderSentences> returnList = new ArrayList<OffenderSentences>();

		final RowMapper<OffenderSentences> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderSentences.class,
				referenceCodeMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("pOffBookId", offenderBookId, "pConsecToSent", getConsecToSentenceSeq), rowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("oseCurSelectOperation", e);
		}
		return returnList;
	}

	@Override
	public Date getAggCurser(final Long offenderBookId, final String category) {
		final String sql = getQuery("GET_AGG_CURSER");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("pOffBookId", offenderBookId, "SentCategory", category), Date.class);
	}

	@Override
	public Long getAdjustDays(final Long offenderBookId, final long sentenceSeq, final String string) {
		final String sql = getQuery("GET_ADJUST_DAYS_CURSER");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("pOffBookId", offenderBookId, "pAdjCode", string, "pSentSeq", sentenceSeq), Long.class);
	}

	@Override
	public Integer getExtCurser(final Long offenderBookId, final Long sentenceSeq) {
		final String sql = getQuery("GET_EXT_TERM_CURSER");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_OFFBOOK_ID", offenderBookId, "P_SENTENCE_SEQ", sentenceSeq), Integer.class);
	}

	@Override
	public void updateSentTerm(final Long offenderBookId, final long sentenceSeq, final Date inputDate,
			final Date endDate, final int i) {
		final String sql = getQuery("UPDATE_OFFENDER_SENTENCE_TERMS_REC");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_START_DATE", inputDate);
		inParamMap.put("P_END_DATE", endDate);
		inParamMap.put("P_OFFBOOK_ID", offenderBookId);
		inParamMap.put("P_SENT_SEQ", sentenceSeq);
		inParamMap.put("P_TERM_SEQ", i);
		namedParameterJdbcTemplate.update(sql, inParamMap);

	}

	@Override
	public Object[] getLtdMtdEtdDates(final Date pStartDate, final Date pEndDate) {
		final String sql = getQuery("GET_LTD_MTD_DTD_DATES");
		Object[] date = null;

		try {
			date = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pStartDate", pStartDate, "pEndDate", pEndDate), Object[].class);
		} catch (Exception e) {
			logger.error("getParentSedCur", e);
			date = null;
		}
		return date;
	}

	@Override
	public void updateOffenderSentences(final Date vStartDate, final Date vEndDateOne, final Date vArdDate,
			final Date vCrdDate, final Date vPedDate, final Date vNpdDate, final Date vledDateOne,
			final Date vSedDateOne, final Long aggregateAdjustDays, final Date vHdcedDate, final Integer vExtDays,
			final Object object, final String string, final Date vMtdDate, final Date vEtdDate, final Date vLtdDate,
			final Long offenderBookId, final long sentenceSeq, final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_SENTENCES_VALUES");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("vStartDate", vStartDate);
		inParamMap.put("vEndDateOne", vEndDateOne);
		inParamMap.put("vArdDate", vArdDate);
		inParamMap.put("vCrdDate", vCrdDate);
		inParamMap.put("vPedDate", vPedDate);

		inParamMap.put("vNpdDate", vNpdDate);
		inParamMap.put("vledDateOne", vledDateOne);
		inParamMap.put("vSedDateOne", vSedDateOne);
		inParamMap.put("aggregateAdjustDays", aggregateAdjustDays);
		inParamMap.put("vHdcedDate", vHdcedDate);

		inParamMap.put("vExtDays", vExtDays);
		inParamMap.put("string", string);
		inParamMap.put("vMtdDate", vMtdDate);
		inParamMap.put("vEtdDate", vEtdDate);
		inParamMap.put("vLtdDate", vLtdDate);
		inParamMap.put("modifyUserId", userName);

		inParamMap.put("offenderBookId", offenderBookId);
		inParamMap.put("sentenceSeq", sentenceSeq);
		namedParameterJdbcTemplate.update(sql, inParamMap);

	}

	@Override
	public void deleteOffenderSentences(final Long offenderBookId,String modifyUserId) {
		final String sql = getQuery("DELETE_OFFENDER_SENTENCES_VALUES");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("offenderBookId", offenderBookId);
		inParamMap.put("modifyUserId", modifyUserId);
		try {
			String tableName = "OFFENDER_SENTENCES";
			String whereCondition = " OFFENDER_BOOK_ID = :offenderBookId AND SENTENCE_LEVEL = 'AGG' AND SENTENCE_STATUS = 'A' AND SENTENCE_CALC_TYPE = 'AGG-IND'";
			updatePreDeletedRow(tableName, whereCondition, inParamMap);
		} catch (Exception e) {
			logger.error(e);
		}
		namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public List<OffenderSentences> getSingleTermSentencesCurser(final Long offenderBookId) {
		final String sql = getQuery("GET_SINGLE_TERM_SENT_CURSER");
		List<OffenderSentences> returnList = new ArrayList<OffenderSentences>();

		final RowMapper<OffenderSentences> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderSentences.class,
				referenceCodeMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("P_OFFBOOK_ID", offenderBookId), rowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("oseCurSelectOperation", e);
		}
		return returnList;
	}

	@Override
	public List<OffenderSentences> getAggSentSeq(final Long offenderBookId, final BigDecimal getConsecToSentenceSeq) {
		final String sql = getQuery("GET_CONSEQ_TO_AGG_SENT_SEQ");
		List<OffenderSentences> returnList = new ArrayList<OffenderSentences>();

		final RowMapper<OffenderSentences> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderSentences.class,
				referenceCodeMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("pOffBookId", offenderBookId, "pConsecToSent", getConsecToSentenceSeq), rowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("oseCurSelectOperation", e);
		}
		return returnList;
	}

	@Override
	public BigDecimal getSequenceNumberCurser(final Long offenderBookId) {
		final String sql = getQuery("GET_MAX_SENTENCE_SEQ_VALUE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFBOOK_ID", offenderBookId),
				BigDecimal.class);
	}

	@Override
	public void insertIntoOffenderSentences(final String string, final BigDecimal seqNO, final Long offenderBookId,
			final BigDecimal consecToSentenceSeq) {
		final String sql = getQuery("INSERT_OFFENDER_SENTENCE_AGGCOUNT_VALUE");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("V_AGG_SENT_SEQ", seqNO);
		inParamMap.put("P_CALC_TYPE", string);
		inParamMap.put("P_OFFBOOK_ID", offenderBookId);
		inParamMap.put("P_SENTENCE_SEQ", consecToSentenceSeq);
		namedParameterJdbcTemplate.update(sql, inParamMap);

	}

	@Override
	public void updateAggSentenceOffenderSentence(final BigDecimal seqNO, final Long offenderBookId,
			final BigDecimal consecToSentenceSeq, final String userName) {
		final String sql = getQuery("UPDATE_AGGSENTENCE_OFFENDER_SENTENCES");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_AGG_SENT_SEQ", seqNO);
		inParamMap.put("P_OFFBOOK_ID", offenderBookId);
		inParamMap.put("P_SENTENCE_SEQ", consecToSentenceSeq);
		inParamMap.put("modifyUserId", userName);
		namedParameterJdbcTemplate.update(sql, inParamMap);

	}

	@Override
	public List<OffenderSentences> getSingleTermStartDateCurser(final Date startDate, final BigDecimal vAggSentSeq,
			final Long offenderBookId) {
		final String sql = getQuery("GET_SINGLE_TERM_SENT_EXT_CURSER");
		List<OffenderSentences> returnList = new ArrayList<OffenderSentences>();

		final RowMapper<OffenderSentences> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderSentences.class,
				referenceCodeMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("P_OFFBOOK_ID", offenderBookId, "P_AGG_SEQ", vAggSentSeq, "P_START_DATE", startDate),
					rowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("oseCurSelectOperation", e);
		}
		return returnList;
	}

	@Override
	public Long getRemandAdjustDays(final BigDecimal vAggSentSeq, final Long offenderBookId) {
		final String sql = getQuery("GET_REMAND_ADJUST_DAYS");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_OFFBOOK_ID", offenderBookId, "V_AGG_SENT_SEQ", vAggSentSeq), Long.class);
	}

	@Override
	public Long getAddedDaysCurser(final BigDecimal vAggSentSeq, final Long offenderBookId) {
		final String sql = getQuery("GET_ADDED_DAYS_CURSER");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_OFFBOOK_ID", offenderBookId, "V_AGG_SENT_SEQ", vAggSentSeq), Long.class);
	}

	@Override
	public void updateOffenderSentencesSingleTerm(final Date vArdDate, final Date vCrdDate, final Date vPedDate,
			final Date vNpdDate, final Date ledCalculatedDate, final Date vHdcedDate, final Date sedCalculatedDate,
			final String string, final Date endDate, final Long aggrigateAdjustValue, final int i,
			final Long extendedDays, final Long offenderBookId, final BigDecimal vAggSentSeq, final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_SENTENCES_SINGLE_TERM");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("V_ARD_DATE", vArdDate);
		inParamMap.put("V_CRD_DATE", vCrdDate);
		inParamMap.put("V_PED_DATE", vPedDate);
		inParamMap.put("V_NPD_DATE", vNpdDate);
		inParamMap.put("LedCalDate", ledCalculatedDate);

		inParamMap.put("V_HDCED_DATE", vHdcedDate);
		inParamMap.put("SedCalcDate", sedCalculatedDate);
		inParamMap.put("EndDate", endDate);
		inParamMap.put("AggreAdjustDates", aggrigateAdjustValue);
		inParamMap.put("Counts", i);

		inParamMap.put("ExtendDays", extendedDays);
		inParamMap.put("P_OFFBOOK_ID", offenderBookId);
		inParamMap.put("V_AGG_SENT_SEQ", vAggSentSeq);
		inParamMap.put("modifyUserId", userName);
		namedParameterJdbcTemplate.update(sql, inParamMap);

	}

	@Override
	public void updateOffnderSentenceSetAgSentNull(final Long offenderBookId, final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_SENTENCES_SET_NULL_VALUE");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("sed_calculated_date", offenderBookId);
		inParamMap.put("modifyUserId", userName);
		namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public void deleteOffnderSentenceSingleTerm(final Long offenderBookId,String modifyUserId) {
		final String sql = getQuery("DELETE_FROM_OFFEDNER_SENTENCES_SINGLE_TERM");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_OFFBOOK_ID", offenderBookId);
		inParamMap.put("modifyUserId", modifyUserId);
		try {
			String tableName = "OFFENDER_SENTENCES";
			String whereCondition = "OFFENDER_BOOK_ID = :P_OFFBOOK_ID AND SENTENCE_LEVEL = 'AGG' AND SENTENCE_CALC_TYPE = 'AGG-IND' AND COUNTS = 1 ";
			updatePreDeletedRow(tableName, whereCondition, inParamMap);
		} catch (Exception e) {
			logger.error(e);
		}
		namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public void deleteOffenderSentencesAggFine(final Long offenderBookId,String modifyUserId) {
		final String sql = getQuery("DELETE_OFFENDER_SENTENCES_AGGFINE_VALUES");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("offenderBookId", offenderBookId);
		inParamMap.put("modifyUserId", modifyUserId);
		try {
			String tableName = "OFFENDER_SENTENCES";
			String whereCondition = " OFFENDER_BOOK_ID = :offenderBookId AND SENTENCE_LEVEL = 'AGG' AND SENTENCE_STATUS = 'A' AND SENTENCE_CALC_TYPE = 'AGG-FINE' ";
			updatePreDeletedRow(tableName, whereCondition, inParamMap);
		} catch (Exception e) {
			logger.error(e);
		}
		namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public List<OffenderSentences> getCalculteFineSentenceCurser(final Long offenderBookId) {
		final String sql = getQuery("CALCULATE_FINE_SENTENCE_CURSER");
		List<OffenderSentences> returnList = new ArrayList<OffenderSentences>();

		final RowMapper<OffenderSentences> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderSentences.class,
				referenceCodeMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("P_OFFBOOK_ID", offenderBookId), rowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("oseCurSelectOperation", e);
		}
		return returnList;
	}

	@Override
	public BigDecimal getFineAmount(final Long offenderBookId) {
		final String sql = getQuery("GET_FINE_AMOUNT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFBOOK_ID", offenderBookId),
				BigDecimal.class);
	}

	@Override
	public Date getSedDateCur(final Long offenderBookId, final long sentenceSeq, final Date sedCalculatedDate) {
		final String sql = getQuery("GET_SED_DATE_CUR");
		Date endDate = null;

		try {
			endDate = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId,
					"sentenceSeq", sentenceSeq, "sedCalculatedDate", sedCalculatedDate), Date.class);
		} catch (Exception e) {
			logger.error("getSedDateCur", e);
			endDate = null;
		}
		return endDate;
	}

	@Override
	public void updateOffSen(final Date vAggSed, final Long offenderBookId, final long sentenceSeq,
			final String userName) {
		final String sql = getQuery("UPDATE_OFF_SEN");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("SED_CALCULATED_DATE", vAggSed);
		inParamMap.put("ARD_CALCULATED_DATE", vAggSed);
		inParamMap.put("OFFENDER_BOOK_ID", offenderBookId);
		inParamMap.put("SENTENCE_SEQ", sentenceSeq);
		inParamMap.put("modifyUserId", userName);
		namedParameterJdbcTemplate.update(sql, inParamMap);

	}

	@Override
	public void updateAggSentSeq(final Long offenderBookId, final long sentenceSeq, final BigDecimal vAggSentSeq,
			final String userName) {
		final String sql = getQuery("UPDATE_AGG_SENT_SEQ");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("AGG_SENTENCE_SEQ", vAggSentSeq);
		inParamMap.put("OFFENDER_BOOK_ID", offenderBookId);
		inParamMap.put("SENTENCE_SEQ", sentenceSeq);
		inParamMap.put("modifyUserId", userName);
		namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public void updateOffenSenTwo(final Long offenderBookId, final Date vAggSed, final Date vAggSedTwo,
			final String userName) {
		final String sql = getQuery("UPDATE_OFFEN_SEN_TWO");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("V_AGG_SED", vAggSed);
		inParamMap.put("vAggSedTwo", vAggSedTwo);
		inParamMap.put("OFFENDER_BOOK_ID", offenderBookId);
		inParamMap.put("modifyUserId", userName);
		namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	/*
	 * @Override public void updateSentTerm(Long offenderBookId, long sentenceSeq,
	 * Date inputDate, Date endDate, int i) { final String sql =
	 * getQuery("INSERT_OFFENDER_SENTENCE_AGGCOUNT_VALUE"); Map<String, Object>
	 * inParamMap = new HashMap<String, Object>(); inParamMap.put("P_START_DATE",
	 * inputDate); inParamMap.put("P_END_DATE", endDate);
	 * inParamMap.put("P_OFFBOOK_ID", offenderBookId); inParamMap.put("P_SENT_SEQ",
	 * sentenceSeq); inParamMap.put("P_TERM_SEQ", i);
	 * namedParameterJdbcTemplate.update(sql, inParamMap);
	 * 
	 * }
	 */
	@Override
	public void updateOffenSenThree(final Date vAggStartDate, final Date vAggSed, final Date vAggArd,
			final Date vAggCrd, final Date vAggPed, final Long offenderBookId, final BigDecimal vAggSentSeq,
			final String userName) {
		final String sql = getQuery("UPDATE_OFFEN_SEN_THREE");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("V_AGG_START_DATE", vAggStartDate);
		inParamMap.put("V_AGG_SED", vAggSed);
		inParamMap.put("V_AGG_ARD", vAggArd);
		inParamMap.put("V_AGG_CRD", vAggCrd);
		inParamMap.put("V_AGG_PED", vAggPed);
		inParamMap.put("OFFENDER_BOOK_ID", offenderBookId);
		inParamMap.put("V_AGG_SENT_SEQ", vAggSentSeq);
		inParamMap.put("modifyUserId", userName);
		namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public List<OffenderSentences> getMaxDatesCurser(final Long offenderBookId) {
		final String sql = getQuery("GET_MAX_DATES_CURSER");
		List<OffenderSentences> returnList = new ArrayList<OffenderSentences>();

		final RowMapper<OffenderSentences> rowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderSentences.class,
				referenceCodeMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("P_OFFBOOK_ID", offenderBookId), rowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("oseCurSelectOperation", e);
		}
		return returnList;
	}

	@Override
	public void insertSentenceCalculations(final Long offenderBookId, final Date hdcedCalculatedDate,
			final Date ardCalculatedDate, final Date crdCalculatedDate, final Date pedCalculatedDate,
			final Date npdCalculatedDate, final Date ledCalculatedDate, final Date sedCalculatedDate,
			final Date mtdCalculatedDate, final Date ltdCalculatedDate, final Date etdCalculatedDate,
			final String calculationReason, final String comment, final String userName) {
		final String sql = getQuery("INSERT_OFFENDER_SENTENCES_CALCULATION_VALUES");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("hdcedCalculatedDate", hdcedCalculatedDate);
		inParamMap.put("ardCalculatedDate", ardCalculatedDate);
		inParamMap.put("crdCalculatedDate", crdCalculatedDate);
		inParamMap.put("pedCalculatedDate", pedCalculatedDate);
		inParamMap.put("npdCalculatedDate", npdCalculatedDate);

		inParamMap.put("ledCalculatedDate", ledCalculatedDate);
		inParamMap.put("sedCalculatedDate", sedCalculatedDate);
		inParamMap.put("mtdCalculatedDate", mtdCalculatedDate);
		inParamMap.put("ltdCalculatedDate", ltdCalculatedDate);
		inParamMap.put("etdCalculatedDate", etdCalculatedDate);

		inParamMap.put("calculationReason", calculationReason);
		inParamMap.put("commentText", comment);
		inParamMap.put("P_OFFBOOK_ID", offenderBookId);
		inParamMap.put("createUserId", userName);

		namedParameterJdbcTemplate.update(sql, inParamMap);

	}

	@Override
	public Date getStartDate(final Long offenderBookId, final Date vAggSed) {
		final String sql = getQuery("GET_STA_RT_DATE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("vAggSed", vAggSed, "offenderBookId", offenderBookId), Date.class);
	}

	@Override
	public Date getVAggArdFromUpdate() {
		final String sql = getQuery("GET_V_AGG_ARD_FROM_UPDATE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Date.class);
	}

}
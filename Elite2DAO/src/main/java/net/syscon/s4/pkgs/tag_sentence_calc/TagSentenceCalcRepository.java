package net.syscon.s4.pkgs.tag_sentence_calc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.syscon.s4.inst.legals.beans.OffenderSentences;

@Repository
public interface TagSentenceCalcRepository {
	Integer deleteOffSentenceAdju(final BigDecimal offBookId, final Long offKeyDateAdjId,String modifyUserId);

	Integer insertOffSentenceAdj(final BigDecimal offBookId, final Long offKeyDateAdjId, final String userName);

	public List<OffenderSentences> getSentencesCurser(final Long offenderBookId);

	public Date getEndDate(final Long offenderBookId, final Long sentenceSeq, final Date vStartDate, final int i);

	List<OffenderSentences> getSedcalculatedDate(final Long offenderBookId, final BigDecimal getConsecToSentenceSeq);
	// public Date getReCalcDate(Long offenderBookId, BigDecimal
	// consecToSentenceSeq, String sentenceCategory);

	public Date getAggCurser(final Long offenderBookId, final String string);

	public Date calculateMonths(final Date dateinput, final Long months);

	Object[] getLtdMtdEtdDates(final Date pStartDate, final Date pEndDate);

	public Long getAdjustDays(final Long offenderBookId, final long sentenceSeq, final String string);

	Long getDaysBetween(final Date endDAte, final Date startDAte);

	Date calculateHdcedDate(final Date pEndDate, final Date pStartDate);

	Integer getExtCurser(final Long offenderBookId, final Long sentenceSeq);

	public void updateSentTerm(final Long offenderBookId, final long sentenceSeq, final Date inputDate,
			final Date endDate, final int i);

	public void updateOffenderSentences(final Date vStartDate, final Date vEndDateOne, final Date vArdDate,
			final Date vCrdDate, final Date vPedDate, final Date vNpdDate, final Date vledDateOne,
			final Date vSedDateOne, final Long aggregateAdjustDays, final Date vHdcedDate, final Integer vExtDays,
			final Object object, final String string, final Date vMtdDate, final Date vEtdDate, final Date vLtdDate,
			Long offenderBookId, long sentenceSeq, final String userName);

	public void deleteOffenderSentences(final Long offenderBookId,String modifyUserId);

	List<OffenderSentences> getSingleTermSentencesCurser(final Long offenderBookId);

	List<OffenderSentences> getAggSentSeq(final Long offenderBookId, final BigDecimal getConsecToSentenceSeq);

	public BigDecimal getSequenceNumberCurser(final Long offenderBookId);

	public void insertIntoOffenderSentences(final String string, final BigDecimal seqNO, final Long offenderBookId,
			final BigDecimal consecToSentenceSeq);

	public void updateAggSentenceOffenderSentence(final BigDecimal seqNO, final Long offenderBookId,
			final BigDecimal consecToSentenceSeq, final String userName);

	public List<OffenderSentences> getSingleTermStartDateCurser(final Date startDate, final BigDecimal vAggSentSeq,
			final Long offenderBookId);

	Long getRemandAdjustDays(final BigDecimal vAggSentSeq, final Long offenderBookId);

	Long getAddedDaysCurser(final BigDecimal vAggSentSeq, final Long offenderBookId);

	public void updateOffenderSentencesSingleTerm(final Date vArdDate, final Date vCrdDate, final Date vPedDate,
			final Date vNpdDate, final Date ledCalculatedDate, final Date vHdcedDate, final Date sedCalculatedDate,
			final String string, final Date endDate, final Long aggrigateAdjustValue, final int i,
			final Long extendedDays, final Long offenderBookId, final BigDecimal vAggSentSeq, final String userName);

	public void updateOffnderSentenceSetAgSentNull(final Long offenderBookId, final String userName);

	void deleteOffnderSentenceSingleTerm(final Long offenderBookId,String modifyUserId);

	void deleteOffenderSentencesAggFine(final Long offenderBookId,String modifyUserId);

	List<OffenderSentences> getCalculteFineSentenceCurser(final Long offenderBookId);

	public BigDecimal getFineAmount(final Long offenderBookId);

	public Date getSedDateCur(final Long offenderBookId, final long sentenceSeq, final Date sedCalculatedDate);

	public void updateOffSen(final Date vAggSed, final Long offenderBookId, final long sentenceSeq,
			final String userName);

	public void updateAggSentSeq(final Long offenderBookId, final long sentenceSeq, final BigDecimal vAggSentSeq,
			final String userName);

	public void updateOffenSenTwo(final Long offenderBookId, final Date vAggSed, final Date vAggSedTwo,
			final String userName);

	public void updateOffenSenThree(final Date vAggStartDate, final Date vAggSed, final Date vAggArd,
			final Date vAggCrd, final Date vAggPed, final Long offenderBookId, final BigDecimal vAggSentSeq,
			final String userName);

	List<OffenderSentences> getMaxDatesCurser(Long offenderBookId);

	public void insertSentenceCalculations(final Long offenderBookId, final Date hdcedCalculatedDate,
			final Date ardCalculatedDate, Date crdCalculatedDate, Date pedCalculatedDate, final Date npdCalculatedDate,
			final Date ledCalculatedDate, final Date sedCalculatedDate, final Date mtdCalculatedDate,
			final Date ltdCalculatedDate, final Date etdCalculatedDate, final String calculationReason,
			final String comment, final String userName);

	public Date getStartDate(final Long offenderBookId, final Date vAggSed);

	public Date getVAggArdFromUpdate();
}
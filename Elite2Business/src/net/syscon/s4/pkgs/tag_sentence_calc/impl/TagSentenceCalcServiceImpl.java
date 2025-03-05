package net.syscon.s4.pkgs.tag_sentence_calc.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.legals.beans.Adjustments;
import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.inst.legals.beans.SentenceCalculation;
import net.syscon.s4.pkgs.tag_sentence_calc.TagSentenceCalcRepository;
import net.syscon.s4.pkgs.tag_sentence_calc.TagSentenceCalcService;

@Service
public class TagSentenceCalcServiceImpl extends BaseBusiness implements TagSentenceCalcService {
	@Autowired
	private TagSentenceCalcRepository TagSentenceCalcRepository;
	private static final String F = "F";
	private static final String T = "T";
	private static final String Y = "Y";

	@Override
	public Integer insertAdjustDays(final List<Adjustments> recordList, final String userName) {
		Integer retVal = null;
		for (Adjustments adjustment : recordList) {
			retVal = TagSentenceCalcRepository.deleteOffSentenceAdju(BigDecimal.valueOf(adjustment.getOffenderBookId()),
					adjustment.getKeyDatesAdjustId(),userName);
			retVal = TagSentenceCalcRepository.insertOffSentenceAdj(BigDecimal.valueOf(adjustment.getOffenderBookId()),
					adjustment.getKeyDatesAdjustId(), userName);
		}
		return retVal;
	}

	@Override
	public String calculateSentence(final SentenceCalculation sentenceCalc) {
		String pSuccessFlag = F;
		final List<OffenderSentences> curserList = TagSentenceCalcRepository
				.getSentencesCurser(sentenceCalc.getOffenderBookId());
		String vFineFlag = null;
		for (final OffenderSentences sentences : curserList) {
			Long vUnCrdRendDays = null;
			Long vSentDays = null;
			Long vAdaDays = null;
			Long vUalDays = null;
			Long vRemandDays = null;
			Integer vExtDays = 0;
			Date vArdDate = null;
			Date vCrdDate = null;
			Date vPedDate = null;
			Date vNpdDate = null;
			Date vLedDate = null;
			Date vHdcedDate = null;
			Date vLtdDate = null;
			Date vEtdDate = null;
			Date vMtdDate = null;
			final Date vStartDate = sentences.getStartDate();
			Date vEndDate;

			vEndDate = TagSentenceCalcRepository.getEndDate(sentenceCalc.getOffenderBookId(),
					sentences.getSentenceSeq(), vStartDate, 1);

			if (sentences.getConsecToSentenceSeq() != null) {
				final Date pStartDate = getReCalcDate(sentenceCalc.getOffenderBookId(),
						sentences.getConsecToSentenceSeq(), sentences.getSentenceCategory());
				// TagSentenceCalcRepository.getReCalcDate(sentenceCalc.getOffenderBookId(),sentences.getConsecToSentenceSeq(),sentences.getSentenceCategory());

				vEndDate = TagSentenceCalcRepository.getEndDate(sentenceCalc.getOffenderBookId(),
						sentences.getSentenceSeq(), pStartDate, 1);

				vSentDays = TagSentenceCalcRepository.getDaysBetween(vEndDate, pStartDate);
				// vEndDate.getTime() - pStartDate.getTime();
				// vSentDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

				vAdaDays = TagSentenceCalcRepository.getAdjustDays(sentenceCalc.getOffenderBookId(),
						sentences.getSentenceSeq(), "ADA");
				final Long vPadadays = TagSentenceCalcRepository.getAdjustDays(sentenceCalc.getOffenderBookId(),
						sentences.getSentenceSeq(), "PADA");
				vUalDays = TagSentenceCalcRepository.getAdjustDays(sentenceCalc.getOffenderBookId(),
						sentences.getSentenceSeq(), "UAL");
				vRemandDays = TagSentenceCalcRepository.getAdjustDays(sentenceCalc.getOffenderBookId(),
						sentences.getSentenceSeq(), "RX");
				final Long sentDaysValu = (long) Math.floor(vSentDays / 2);
				if (vRemandDays > sentDaysValu) {
					vUnCrdRendDays = vRemandDays - sentDaysValu;
					vRemandDays = vRemandDays - vUnCrdRendDays;
				}
				final Long vAdjdays = vAdaDays + vPadadays + vUalDays;

				// Date input = new Date();
				final LocalDate date = vEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				final LocalDate VSedDate = date.minusDays(vRemandDays);
				Map<String, Object> returnMap = new HashMap<>();
				returnMap = calculateScheme(sentences.getSentenceCategory(), pStartDate, vSentDays, VSedDate, vAdjdays,
						vArdDate, vCrdDate, vPedDate, vNpdDate, vLedDate, vHdcedDate);
				vArdDate = (Date) returnMap.get("vArdDate");
				vCrdDate = (Date) returnMap.get("vCrdDate");
				vPedDate = (Date) returnMap.get("vPedDate");
				vNpdDate = (Date) returnMap.get("vNpdDate");
				vLedDate = (Date) returnMap.get("vLedDate");
				vHdcedDate = (Date) returnMap.get("vHdcedDate");
				if ("A/FINE".equals(sentences.getSentCalcType()) || "CIVIL".equals(sentences.getSentCalcType())) {
					vNpdDate = null;
					vLedDate = null;
					vHdcedDate = null;
					vFineFlag = Y;
				}
				final ZoneId defaultZoneId = ZoneId.systemDefault();
				if ("EXT".equals(sentences.getSentCalcType()) || "EPP".equals(sentences.getSentCalcType())) {
					vExtDays = TagSentenceCalcRepository.getExtCurser(sentenceCalc.getOffenderBookId(),
							sentences.getSentenceSeq());
					date.plusDays(1);

					// local date + atStartOfDay() + default time zone + toInstant() = Date
					final Date inputDate = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
					TagSentenceCalcRepository.updateSentTerm(sentenceCalc.getOffenderBookId(),
							sentences.getSentenceSeq(), inputDate, TagSentenceCalcRepository.getEndDate(
									sentenceCalc.getOffenderBookId(), sentences.getSentenceSeq(), inputDate, 2),
							2);
				}
				if ("2003".equals(sentences.getSentCalcType())) {
					vNpdDate = Date.from(VSedDate.atStartOfDay(defaultZoneId).toInstant());
					if (vCrdDate != null) {
						vPedDate = vCrdDate;
					} else {
						vPedDate = vArdDate;
					}
					vCrdDate = null;
					vArdDate = null;

				}
				final Date VSedDateDate = Date.from(VSedDate.atStartOfDay(defaultZoneId).toInstant());
				if ("DTO".equals(sentences.getSentCalcType()) && pStartDate != null && VSedDateDate != null) {
					Map<String, Object> returnMapData = new HashMap<>();
					returnMapData = getDtoMtdValues(pStartDate, VSedDateDate, vMtdDate, vLtdDate, vEtdDate);
					vMtdDate = (Date) returnMapData.get("vMtdDate");
					vLtdDate = (Date) returnMapData.get("vLtdDate");
					vEtdDate = (Date) returnMapData.get("vEtdDate");
				}
				final LocalDate dateOne = vEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				dateOne.plusDays(vExtDays);

				// local date + atStartOfDay() + default time zone + toInstant() = Date
				final Date vEndDateOne = Date.from(dateOne.atStartOfDay(defaultZoneId).toInstant());

				final LocalDate datetwo = vLedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				datetwo.plusDays(vExtDays);

				// local date + atStartOfDay() + default time zone + toInstant() = Date
				final Date vledDateOne = Date.from(datetwo.atStartOfDay(defaultZoneId).toInstant());

				VSedDate.plusDays(vExtDays).plusDays(vUalDays);

				// local date + atStartOfDay() + default time zone + toInstant() = Date
				final Date vSedDateOne = Date.from(VSedDate.atStartOfDay(defaultZoneId).toInstant());

				final Long aggregateAdjustDays = vAdjdays + vRemandDays;

				TagSentenceCalcRepository.updateOffenderSentences(vStartDate, vEndDateOne, vArdDate, vCrdDate, vPedDate,
						vNpdDate, vledDateOne, vSedDateOne, aggregateAdjustDays, vHdcedDate, vExtDays, null, "A",
						vMtdDate, vEtdDate, vLtdDate, sentenceCalc.getOffenderBookId(), sentences.getSentenceSeq(),
						sentenceCalc.getModifyUserId());
				TagSentenceCalcRepository.updateSentTerm(sentenceCalc.getOffenderBookId(), sentences.getSentenceSeq(),
						vStartDate, vEndDateOne, 1);
			}
		}

		singleTermSentences(sentenceCalc.getOffenderBookId(), sentenceCalc.getModifyUserId());
		if ("Y".equals(vFineFlag)) {
			calculateFineSentencses(sentenceCalc.getOffenderBookId(), sentenceCalc.getModifyUserId());
		}
		generateKeyDates(sentenceCalc.getOffenderBookId(), sentenceCalc.getCalculationReason(),
				sentenceCalc.getComment(), sentenceCalc.getCreateUserId());
		pSuccessFlag = T;
		return null;
	}

	private void generateKeyDates(final Long offenderBookId, final String calculationReason, final String comment,
			final String userName) {
		final List<OffenderSentences> returnList = TagSentenceCalcRepository.getMaxDatesCurser(offenderBookId);
		for (final OffenderSentences offenderSentences : returnList) {
			TagSentenceCalcRepository.insertSentenceCalculations(offenderBookId,
					offenderSentences.getHdcedCalculatedDate(), offenderSentences.getArdCalculatedDate(),
					offenderSentences.getCrdCalculatedDate(), offenderSentences.getPedCalculatedDate(),
					offenderSentences.getNpdCalculatedDate(), offenderSentences.getLedCalculatedDate(),
					offenderSentences.getSedCalculatedDate(), offenderSentences.getMtdCalculatedDate(),
					offenderSentences.getLtdCalculatedDate(), offenderSentences.getEtdCalculatedDate(),
					calculationReason, comment, userName);
		}

	}

	private void calculateFineSentencses(final Long offenderBookId, final String userName) {
		TagSentenceCalcRepository.deleteOffenderSentencesAggFine(offenderBookId,userName);
		final BigDecimal fineAmount = TagSentenceCalcRepository.getFineAmount(offenderBookId);
		BigDecimal vAggSentSeq = BigDecimal.ZERO;
		BigDecimal vPaytBal = BigDecimal.ZERO;
		BigDecimal vCompDmr = BigDecimal.ZERO;
		Long vSentDays = 0L;
		Long vDiffDays = 0L;
		Long vNetSentDays = 0L;
		Date vAggStartDate = null;
		Date vAggEndDate = null;
		Date vAggSed = null;
		Date vAggArd = null;
		Date vAggCrd = null;
		Date vAggPed = null;
		String vLoopFlag = "T";
		BigDecimal mulResult = null;
		final Calendar calendar = Calendar.getInstance();
		final Calendar calendar1 = Calendar.getInstance();
		final Calendar calendar2 = Calendar.getInstance();
		final Calendar calendar3 = Calendar.getInstance();

		final List<OffenderSentences> responseList = TagSentenceCalcRepository
				.getCalculteFineSentenceCurser(offenderBookId);
		for (final OffenderSentences offenderSentences : responseList) {
			if (vAggSed == null) {
				vAggStartDate = offenderSentences.getStartDate();
				vAggEndDate = offenderSentences.getEndDate();
				vAggSed = offenderSentences.getSedCalculatedDate();
				vAggSentSeq = insertAggSentences(offenderBookId, BigDecimal.valueOf(offenderSentences.getSentenceSeq()),
						"AGG-FINE", userName);
			}
			if (vAggStartDate.compareTo(offenderSentences.getStartDate()) > 0) {
				vAggStartDate = offenderSentences.getStartDate();
			}
			vSentDays = TagSentenceCalcRepository.getDaysBetween(offenderSentences.getEndDate(),
					offenderSentences.getStartDate());
			BigDecimal valueAdd = BigDecimal.valueOf(Math.round(offenderSentences.getFineAmount()
					.divide(BigDecimal.valueOf(vSentDays).subtract(BigDecimal.ONE)).doubleValue()));
			vCompDmr = vCompDmr.add(valueAdd);

			if (vCompDmr.compareTo(vPaytBal) != 1 && "T".equals(vLoopFlag)) {
				// get_next_sed_days Funcation cursor get_sed_date_cur 1530
				vDiffDays = getNextSedDays(offenderBookId, offenderSentences.getSentenceSeq(),
						offenderSentences.getSedCalculatedDate());

				vNetSentDays = TagSentenceCalcRepository.getDaysBetween(offenderSentences.getStartDate(),
						offenderSentences.getSedCalculatedDate());

				if (vNetSentDays <= vDiffDays) {
					final int minusDays = (int) (vNetSentDays - 1);
					calendar.setTime(vAggSed);
					calendar.add(Calendar.DATE, -minusDays);
					vAggSed = calendar.getTime();

					mulResult = vCompDmr.multiply(BigDecimal.valueOf(vNetSentDays));
					vPaytBal = vPaytBal.subtract(mulResult);

					// Update offender_sentences
					TagSentenceCalcRepository.updateOffSen(vAggSed, offenderBookId, offenderSentences.getSentenceSeq(),
							userName);
					vDiffDays = vDiffDays - (vNetSentDays - 1);

					double rouVal = Math.round(offenderSentences.getFineAmount()
							.divide(BigDecimal.valueOf(vSentDays).subtract(BigDecimal.ONE)).doubleValue() * 100.0)
							/ 100.0;
					vCompDmr = vCompDmr.subtract(BigDecimal.valueOf(rouVal));
				}

				final BigDecimal mul1rest = vCompDmr.multiply(BigDecimal.valueOf(vDiffDays));
				if (vDiffDays != 0 && mul1rest.compareTo(vPaytBal) != 1) {
					calendar1.setTime(vAggSed);
					calendar1.add(Calendar.DATE, -vDiffDays.intValue());
					vAggSed = calendar1.getTime();
					vPaytBal = vPaytBal.subtract((BigDecimal.valueOf(vDiffDays).multiply(vCompDmr)));
				} else {
					vDiffDays = vSentDays - (long) Math.floor(vPaytBal.divide(vCompDmr).doubleValue());
					if ((vDiffDays % 2) != 0) {
						vDiffDays = vDiffDays + 1;
					}
					final Long sdDays = vSentDays + vDiffDays;
					calendar2.setTime(vAggSed);
					calendar2.add(Calendar.DATE, -sdDays.intValue());
					vAggSed = calendar2.getTime();

					final BigDecimal senDiff = vCompDmr.multiply(BigDecimal.valueOf((vSentDays - vDiffDays)));
					vPaytBal = vPaytBal.subtract(senDiff);

					vLoopFlag = "F";
				}
			}
//			update_agg_sent_seq 
			TagSentenceCalcRepository.updateAggSentSeq(offenderBookId, offenderSentences.getSentenceSeq(), vAggSentSeq,
					userName);
		}
		// update offender_sentences 1582
		final Date staDate = TagSentenceCalcRepository.getStartDate(offenderBookId, vAggSed);
		final Long forFlr = TagSentenceCalcRepository.getDaysBetween(staDate, vAggSed) / 2;
		final int floorNo = (int) Math.floor(forFlr);
		calendar3.setTime(vAggSed);
		calendar3.add(Calendar.DATE, -floorNo);
		final Date vAggSedTwo = calendar3.getTime();
		TagSentenceCalcRepository.updateOffenSenTwo(offenderBookId, vAggSed, vAggSedTwo, userName);
		vAggArd = TagSentenceCalcRepository.getVAggArdFromUpdate();

		vSentDays = TagSentenceCalcRepository.getDaysBetween(vAggStartDate, vAggEndDate);
		final Long timePeriod = getDaysBetween(vAggStartDate, 0, 12, 0, 0);
		final Long timePeriodOne = getDaysBetween(vAggStartDate, 4, 0, 0, 0);
		if (vSentDays >= timePeriod && vSentDays < timePeriodOne) {
			vAggCrd = vAggArd;
			vAggArd = null;
		} else if (vSentDays >= timePeriodOne) {
			vAggPed = vAggArd;
			vAggArd = null;
		}
//		1608  update offender_sentences
		TagSentenceCalcRepository.updateOffenSenThree(vAggStartDate, vAggSed, vAggArd, vAggCrd, vAggPed, offenderBookId,
				vAggSentSeq, userName);
	}

	private Long getNextSedDays(final Long offenderBookId, final long sentenceSeq, final Date sedCalculatedDate) {
		Date eDate = null;
		Long totDif;
		Long diffDays = null;
		Long vDiffDays = null;

		eDate = TagSentenceCalcRepository.getSedDateCur(offenderBookId, sentenceSeq, sedCalculatedDate);
		if (eDate != null) {
			totDif = sedCalculatedDate.getTime() - eDate.getTime();
			diffDays = TimeUnit.DAYS.convert(totDif, TimeUnit.MILLISECONDS);
			if (diffDays == null) {
				diffDays = 0l;
			}
			vDiffDays = (long) Math.floor(diffDays);
		}
		return vDiffDays;
	}

	private void singleTermSentences(final Long offenderBookId, final String userName) {
		TagSentenceCalcRepository.deleteOffenderSentences(offenderBookId,userName);
		final List<OffenderSentences> singleTermCurse = TagSentenceCalcRepository
				.getSingleTermSentencesCurser(offenderBookId);
		for (final OffenderSentences offenderSentences : singleTermCurse) {
			BigDecimal vAggSentSeq = null;
			final Date vArdDate = null;
			final Date vCrdDate = null;
			final Date vPedDate = null;
			final Date vNpdDate = null;
			final Date vLedDate = null;
			final Date vHdcedDate = null;
			String vParentSentCatg = null;
			Long vPrvAdaDays = null;
			Long vAdaDays = null;
			Long vPadaDays = null;
			BigDecimal vAggExtDays = BigDecimal.ZERO;
			Date vStartDate = null;
			Date vEndDate = null;
			Date vSedDate = null;
			String vSentCatg = null;
			if (offenderSentences.getConsecToSentenceSeq() != null) {
				final List<OffenderSentences> getAggSentSeqObj = TagSentenceCalcRepository.getAggSentSeq(offenderBookId,
						offenderSentences.getConsecToSentenceSeq());
				if (!getAggSentSeqObj.isEmpty()) {
					vAggSentSeq = getAggSentSeqObj.get(0).getAggSentenceSeq();
					vParentSentCatg = getAggSentSeqObj.get(0).getSentenceCategory();
				}
				if (offenderSentences.getSentenceCategory().equals(vParentSentCatg) && vAggSentSeq == null
						&& "2003".equals(offenderSentences.getSentenceCategory())) {
					BigDecimal returnValue = insertAggSentences(offenderBookId,
							offenderSentences.getConsecToSentenceSeq(), "AGG-IND", userName);
				} else {
					vAggSentSeq = null;
				}
			}

			final List<OffenderSentences> resultList = TagSentenceCalcRepository
					.getSingleTermStartDateCurser(offenderSentences.getStartDate(), vAggSentSeq, offenderBookId);
			if (!resultList.isEmpty()) {
				vAggSentSeq = BigDecimal.valueOf(resultList.get(0).getSentenceSeq());
				vStartDate = resultList.get(0).getStartDate();
				vEndDate = resultList.get(0).getEndDate();
				vSedDate = resultList.get(0).getSedCalculatedDate();
				vSentCatg = resultList.get(0).getSentenceCategory();
				vAggExtDays = resultList.get(0).getExtendedDays();

			}
			if (vAggSentSeq != null) {
				final LocalDate datetwo = vSedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				final ZoneId defaultZoneId = ZoneId.systemDefault();
				datetwo.minusDays(vAggExtDays.longValue());

				// local date + atStartOfDay() + default time zone + toInstant() = Date
				Date vAggSed = Date.from(datetwo.atStartOfDay(defaultZoneId).toInstant());

				final Long vSentDaysBetweenDays = TagSentenceCalcRepository.getDaysBetween(vEndDate, vStartDate);
				Long vSentDays = vSentDaysBetweenDays - vAggExtDays.longValue();
				if (vSedDate.compareTo(offenderSentences.getSedCalculatedDate()) < 0
						|| offenderSentences.getConsecToSentenceSeq() != null) {
					if (offenderSentences.getConsecToSentenceSeq() == null) {
						LocalDate sentCurseSedCalDate = offenderSentences.getSedCalculatedDate().toInstant()
								.atZone(ZoneId.systemDefault()).toLocalDate();
						// local date + atStartOfDay() + default time zone + toInstant() = Date

						Long vSentDaysconseqNull = TagSentenceCalcRepository.getDaysBetween(vStartDate,
								offenderSentences.getEndDate());
						vSentDays = vSentDaysconseqNull
								- (vAggExtDays.longValue() + offenderSentences.getExtendedDays().longValue());
						Long differSedDate = TagSentenceCalcRepository.getDaysBetween(vSedDate, vEndDate) - 1
								- vAggExtDays.longValue() - offenderSentences.getExtendedDays().longValue();
						sentCurseSedCalDate.minusDays(differSedDate);
						vAggSed = Date.from(sentCurseSedCalDate.atStartOfDay(defaultZoneId).toInstant());

					} else {

						Long vAggRemDays = null;
						vAggRemDays = TagSentenceCalcRepository.getRemandAdjustDays(vAggSentSeq, offenderBookId);
						if (vEndDate.compareTo(offenderSentences.getEndDate()) < 0) {
							vSentDays = TagSentenceCalcRepository.getDaysBetween(vStartDate,
									offenderSentences.getEndDate()) - vAggExtDays.longValue()
									- offenderSentences.getExtendedDays().longValue() + vAggRemDays;
							LocalDate sentCurSedCalDateone = offenderSentences.getSedCalculatedDate().toInstant()
									.atZone(ZoneId.systemDefault()).toLocalDate();
							sentCurSedCalDateone.minusDays(vAggExtDays.longValue())
									.minusDays(offenderSentences.getExtendedDays().longValue());
							vAggSed = Date.from(sentCurSedCalDateone.atStartOfDay(defaultZoneId).toInstant());
						} else {
							Long differSedDateValesAgg = TagSentenceCalcRepository.getDaysBetween(vSedDate, vEndDate)
									- 1 - offenderSentences.getExtendedDays().longValue();
							LocalDate vAggLocalDateValue = vAggSed.toInstant().atZone(ZoneId.systemDefault())
									.toLocalDate();
							vAggLocalDateValue.minusDays(differSedDateValesAgg);
							vAggSed = Date.from(vAggLocalDateValue.atStartOfDay(defaultZoneId).toInstant());
						}
					}

				}
				vPrvAdaDays = TagSentenceCalcRepository.getAddedDaysCurser(vAggSentSeq, offenderBookId);
				vAdaDays = TagSentenceCalcRepository.getAdjustDays(offenderBookId, offenderSentences.getSentenceSeq(),
						"ADA");
				vPadaDays = TagSentenceCalcRepository.getAdjustDays(offenderBookId, offenderSentences.getSentenceSeq(),
						"PADA");
				final LocalDate vAggLocalDateValue = vAggSed.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				calculateScheme(vSentCatg, vStartDate, vSentDays, vAggLocalDateValue,
						vPrvAdaDays + vAdaDays + vPadaDays, vArdDate, vCrdDate, vPedDate, vNpdDate, vLedDate,
						vHdcedDate);

				LocalDate ledCalculatedLocalDate = vLedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
						.plusDays(vAggExtDays.longValue()).plusDays(offenderSentences.getExtendedDays().longValue());
				final Date ledCalculatedDate = Date
						.from(ledCalculatedLocalDate.atStartOfDay(defaultZoneId).toInstant());
				LocalDate sedCalculatedLocalDate = vAggSed.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
						.plusDays(vAggExtDays.longValue()).plusDays(offenderSentences.getExtendedDays().longValue());
				final Date sedCalculatedDate = Date
						.from(sedCalculatedLocalDate.atStartOfDay(defaultZoneId).toInstant());
				Long endDateDifferencedValue = vSentDays - 1 + vAggExtDays.longValue()
						+ offenderSentences.getExtendedDays().longValue();
				LocalDate endDateLocalValue = vStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
						.plusDays(endDateDifferencedValue);
				final Date endDate = Date.from(endDateLocalValue.atStartOfDay(defaultZoneId).toInstant());
				Long aggrigateAdjustValue = 0L;
				aggrigateAdjustValue = aggrigateAdjustValue + offenderSentences.getAggregateAdjustDays().longValue();
				final Integer counts = 0;
				final Long extendedDays = vAggExtDays.longValue() + offenderSentences.getExtendedDays().longValue();
				TagSentenceCalcRepository.updateOffenderSentencesSingleTerm(vArdDate, vCrdDate, vPedDate, vNpdDate,
						ledCalculatedDate, vHdcedDate, sedCalculatedDate, "AGG-IND", endDate, aggrigateAdjustValue,
						counts + 1, extendedDays, offenderBookId, vAggSentSeq, userName);
				TagSentenceCalcRepository.updateAggSentenceOffenderSentence(vAggSentSeq, offenderBookId,
						BigDecimal.valueOf(offenderSentences.getSentenceSeq()), userName);
			} else {
				insertAggSentences(offenderBookId, BigDecimal.valueOf(offenderSentences.getSentenceSeq()), "AGG-IND",
						userName);
			}
		}

		TagSentenceCalcRepository.updateOffnderSentenceSetAgSentNull(offenderBookId, userName);
		TagSentenceCalcRepository.deleteOffnderSentenceSingleTerm(offenderBookId,userName);

	}

	private BigDecimal insertAggSentences(final Long offenderBookId, final BigDecimal consecToSentenceSeq,
			final String string, final String userName) {
		final BigDecimal seqNO = TagSentenceCalcRepository.getSequenceNumberCurser(offenderBookId);
		TagSentenceCalcRepository.insertIntoOffenderSentences(string, seqNO, offenderBookId, consecToSentenceSeq);
		TagSentenceCalcRepository.updateAggSentenceOffenderSentence(seqNO, offenderBookId, consecToSentenceSeq,
				userName);
		return seqNO;
	}

	private Map<String, Object> getDtoMtdValues(final Date pStartDate, final Date vSedDateDate, final Date vMtdDate,
			final Date vLtdDate, final Date vEtdDate) {
		final Object[] returnData = TagSentenceCalcRepository.getLtdMtdEtdDates(pStartDate, vSedDateDate);
		final Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("VMTDDATE", returnData[0]);
		returnMap.put("VLTDDATE", returnData[1]);
		returnMap.put("VETDDATE", returnData[2]);

		return returnMap;
	}

	private Map<String, Object> calculateScheme(final String sentenceCategory, final Date pStartDate,
			final Long vSentDays, final LocalDate vSedDate, final Long vAdjdays, Date vArdDate, Date vCrdDate,
			Date vPedDate, Date vNpdDate, Date vLedDate, Date vHdcedDate) {
		final Map<String, Object> returnMap = new HashMap<>();
		final Long timePeriod = getDaysBetween(pStartDate, 0, 12, 0, 0);
		final Long timePeriodOne = getDaysBetween(pStartDate, 0, 4, 0, 0);

		if (vSentDays < timePeriod) {
			final Long sentDaysValu = (long) Math.floor(vSentDays / 2);
			final LocalDate vArDateValue = vSedDate.minusDays(sentDaysValu).plusDays(vAdjdays);
			final ZoneId defaultZoneId = ZoneId.systemDefault();

			// local date + atStartOfDay() + default time zone + toInstant() = Date
			vArdDate = Date.from(vArDateValue.atStartOfDay(defaultZoneId).toInstant());

			vHdcedDate = TagSentenceCalcRepository.calculateHdcedDate(pStartDate, vArdDate);
		} else if (vSentDays >= timePeriod && vSentDays < timePeriodOne) {
			final Long firstFloor = (long) Math.floor(vSentDays / 2);
			final Long secondFloor = (long) Math.floor(vSentDays / 4);
			final LocalDate vcrdDateValue = vSedDate.minusDays(firstFloor).plusDays(vAdjdays);
			final ZoneId defaultZoneId = ZoneId.systemDefault();

			vCrdDate = Date.from(vcrdDateValue.atStartOfDay(defaultZoneId).toInstant());

			final LocalDate vLeddDateValue = vSedDate.minusDays(secondFloor).plusDays(vAdjdays);

			vLedDate = Date.from(vLeddDateValue.atStartOfDay(defaultZoneId).toInstant());
			vHdcedDate = TagSentenceCalcRepository.calculateHdcedDate(pStartDate, vCrdDate);
		} else if (vSentDays >= timePeriodOne) {
			final Long firstFloor = (long) Math.floor(vSentDays / 2);
			final Long secondFloor = (long) Math.floor(vSentDays / 4);
			final Long thirdFloor = (long) Math.floor(vSentDays / 3);

			final LocalDate vpedDateValue = vSedDate.minusDays(firstFloor).plusDays(vAdjdays);
			final ZoneId defaultZoneId = ZoneId.systemDefault();

			vPedDate = Date.from(vpedDateValue.atStartOfDay(defaultZoneId).toInstant());

			final LocalDate vLeddDateValue = vSedDate.minusDays(secondFloor).plusDays(vAdjdays);

			vLedDate = Date.from(vLeddDateValue.atStartOfDay(defaultZoneId).toInstant());

			final LocalDate vnpdDateValue = vSedDate.minusDays(thirdFloor).plusDays(vAdjdays);

			vNpdDate = Date.from(vnpdDateValue.atStartOfDay(defaultZoneId).toInstant());

			vHdcedDate = TagSentenceCalcRepository.calculateHdcedDate(pStartDate, vPedDate);
		}
		if ("2003".equals(sentenceCategory)) {
			vLedDate = null;
			if (vPedDate != null) {
				vCrdDate = vPedDate;
			}
		}

		returnMap.put("vArdDate", vArdDate);
		returnMap.put("vCrdDate", vCrdDate);
		returnMap.put("vPedDate", vPedDate);
		returnMap.put("vNpdDate", vNpdDate);
		returnMap.put("vLedDate", vLedDate);
		returnMap.put("vHdcedDate", vHdcedDate);
		return returnMap;
	}

	private Long getDaysBetween(final Date pStartDate, final int years, final int months, final int weeks,
			final int days) {

		final Date vEndDate = getSentenceEndDate(pStartDate, years, months, weeks, days, null);
		return TagSentenceCalcRepository.getDaysBetween(vEndDate, pStartDate);

	}

	private Date getSentenceEndDate(final Date pStartDate, final int years, final int months, final int weeks,
			final int days, final String object) {
		if ("Y".equals(object)) {
			return null;
		}

		final Long result = (long) (12 * years + months);

		return TagSentenceCalcRepository.calculateMonths(pStartDate, result);

	}

	private Date getReCalcDate(final Long offenderBookId, final BigDecimal consecToSentenceSeq,
			final String sentenceCategory) {
		Date pStartDate = null;
		List<OffenderSentences> getSedCurser = TagSentenceCalcRepository.getSedcalculatedDate(offenderBookId,
				consecToSentenceSeq);
		OffenderSentences getSedObject = new OffenderSentences();
		if (getSedCurser.size() > 0) {
			getSedObject = getSedCurser.get(0);
		}
		final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		final Calendar calendar = Calendar.getInstance();
		if (sentenceCategory.equals(getSedObject.getSentenceCategory())) {
			calendar.setTime(getSedObject.getSedCalculatedDate());
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			try {
				pStartDate = new SimpleDateFormat("dd/MM/yyyy").parse(format.format(calendar.getTime()).toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}

			final Date release = TagSentenceCalcRepository.getAggCurser(offenderBookId,
					getSedObject.getSentenceCategory());
			final Calendar calendarOne = Calendar.getInstance();
			calendarOne.setTime(release);
			calendarOne.add(Calendar.DAY_OF_YEAR, 1);
			try {
				pStartDate = new SimpleDateFormat("dd/MM/yyyy").parse(format.format(calendarOne.getTime()).toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return pStartDate;
	}

	@Override
	public Date getEndDate(final Long offenderBookId, final Long sentenceSeq, final Date vStartDate, int i) {
		return TagSentenceCalcRepository.getEndDate(offenderBookId, sentenceSeq, vStartDate, i);
	}

	@Override
	public Long getDaysBetween(final Date endDAte, final Date startDAte) {
		return TagSentenceCalcRepository.getDaysBetween(endDAte, startDAte);
	}

	@Override
	public Long getAdjustDays(final Long offenderBookId, final long sentenceSeq, final String string) {
		return TagSentenceCalcRepository.getAdjustDays(offenderBookId, sentenceSeq, string);
	}
}
package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.sql.SQLXML;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.pkgs.tag_wfmsg.TagWfmsgService;
import net.syscon.s4.pkgs.tag_workflow.TagWorkflowService;
import net.syscon.s4.triggers.OffenderSentencesTwfRepository;
import net.syscon.s4.triggers.OffenderSentencesTwfService;

@Service
public class OffenderSentencesTwfServiceImpl implements OffenderSentencesTwfService {
	Logger logger = LogManager.getLogger(OffenderSentencesTwfServiceImpl.class);
	@Autowired
	OffenderSentencesTwfRepository offenderSentencesTwfRepository;
	@Autowired
	TagWfmsgService tagWfmsgService;
	@Autowired
	TagWorkflowService tagWorkflowService;

	@Override
	public Integer offenderSentencesTwfTgr(final OffenderSentences offenderSentences, final String sqlOperation) {
		final OffenderSentences old = offenderSentencesTwfRepository
				.getOffenderSentences(offenderSentences.getOffenderBookId(), offenderSentences.getSentenceSeq());
		try {
			if ("UPDATING".equalsIgnoreCase(sqlOperation)) {
				if ("I".equals(offenderSentences.getSentenceStatus()) && !"I".equals(old.getSentenceStatus())) {
//				SQLXML lvXml = tagWfmsgService.createXml();  function not yet  implemented
					final SQLXML lvXml = null;
					tagWfmsgService.append("sentence_category", offenderSentences.getSentCalcType(), null);
					tagWfmsgService.append("status_update_reason", offenderSentences.getStatusUpdateReason(), null);
					tagWfmsgService.append("sentence_start_date",
							new SimpleDateFormat("dd-MMM-yyyy").format(offenderSentences.getStartDate()), null);
					tagWorkflowService.createCaseNote(new BigDecimal(offenderSentences.getOffenderBookId()),
							"SENT_TERMIND", lvXml, null, null, "AUTO", offenderSentences.getCreateUserId());
				}
				final BigDecimal noOfUnexcusedAbsenceNew = Optional.ofNullable(offenderSentences).isPresent()
						&& Optional.ofNullable(offenderSentences.getNoOfUnexcusedAbsence()).isPresent()
								? offenderSentences.getNoOfUnexcusedAbsence()
								: new BigDecimal(0);
				final BigDecimal noOfUnexcusedAbsenceOld = Optional.ofNullable(old).isPresent()
						&& Optional.ofNullable(old.getNoOfUnexcusedAbsence()).isPresent()
								? old.getNoOfUnexcusedAbsence()
								: new BigDecimal(0);

				if (noOfUnexcusedAbsenceNew.compareTo(noOfUnexcusedAbsenceOld) > 0
						&& noOfUnexcusedAbsenceOld.compareTo(new BigDecimal(0)) > 0) {
					final String lSentenceCalcType = offenderSentencesTwfRepository.lSentenceCalcType(
							offenderSentences.getSentenceCategory(), offenderSentences.getSentenceCalcType());
					if ((("1991".equals(offenderSentences.getSentenceCategory())
							|| "2003".equals(offenderSentences.getSentenceCategory()))
							&& "COMM".equals(lSentenceCalcType))
							|| ("LICENCE".equals(offenderSentences.getSentenceCategory())
									&& "ALL".equals(lSentenceCalcType)
									&& ("YO".equals(offenderSentences.getSentenceCalcType())
											|| "YN".equals(offenderSentences.getSentenceCalcType())))
							|| (("1991".equals(offenderSentences.getSentenceCategory())
									|| "2003".equals(offenderSentences.getSentenceCategory()))
									&& "ALL".equals(lSentenceCalcType)
									&& "YOI".equals(offenderSentences.getSentenceCalcType()))) {
						if (noOfUnexcusedAbsenceNew.compareTo(new BigDecimal(1)) > 0
								&& noOfUnexcusedAbsenceNew.compareTo(new BigDecimal(0)) == 0) {
//						SQLXML lvXml = tagWfmsgService.createXml();  function not yet  implemented
							final SQLXML lvXml = null;
							final String pKey = new StringBuffer("OFFENDER_BOOK_ID=>")
									.append(String.valueOf(offenderSentences.getOffenderBookId()))
									.append(":SENTENCE_SEQ=>")
									.append(String.valueOf(offenderSentences.getSentenceSeq())).toString();
							tagWorkflowService.createWorkflow(new BigDecimal(offenderSentences.getOffenderBookId()),
									null, lvXml, new BigDecimal(offenderSentences.getOffenderBookId()), null, null,
									null, offenderSentences.getCreateUserId(), "BREACH");
						}

					}

					if (("LICENCE".equals(offenderSentences.getSentenceCategory()) && "ALL".equals(lSentenceCalcType))
							&& ("YO".equals(offenderSentences.getSentenceCalcType())
									|| "YN".equals(offenderSentences.getSentenceCalcType()))) {
						if (noOfUnexcusedAbsenceNew.compareTo(new BigDecimal(2)) > 0
								&& noOfUnexcusedAbsenceNew.compareTo(new BigDecimal(2)) == 0) {
//						SQLXML lvXml = tagWfmsgService.createXml();  function not yet  implemented
							final SQLXML lvXml = null;
							final String pKey = new StringBuffer("OFFENDER_BOOK_ID=>")
									.append(String.valueOf(offenderSentences.getOffenderBookId()))
									.append(":SENTENCE_SEQ=>")
									.append(String.valueOf(offenderSentences.getSentenceSeq())).toString();
							tagWorkflowService.createWorkflow(new BigDecimal(offenderSentences.getOffenderBookId()),
									null, lvXml, new BigDecimal(offenderSentences.getOffenderBookId()), null, null,
									null, offenderSentences.getCreateUserId(), "RECALL");
						}
					}
				}
			}
		} catch (final Exception e) {
			logger.error("offenderSentencesTwfTgr", e);
			return 0;
		}
		return 1;
	}

}

package net.syscon.s4.pkgs.tag_termination.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.legalscreens.bean.UpdateCase;
import net.syscon.s4.pkgs.tag_termination.TagTerminationRepository;

@Repository
public class TagTerminationRepositoryImpl extends RepositoryBase implements TagTerminationRepository {

	private static Logger logger = LogManager.getLogger(TagTerminationRepositoryImpl.class.getName());

	@Override
	public String chkActiveLicences(final UpdateCase updateCase) {
		final String sql = getQuery("CHK_ACTIVE_LICENCES");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_OFFENDER_BOOK_ID",
					updateCase.getOffenderBookId(), "P_CASE_ID", updateCase.getCaseId()), String.class);
		} catch (Exception e) {
			logger.error("checkActiveLicences :" + e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public Long getCaseId(final String pRowid) {
		final String sql = getQuery("GET_CASE_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_ROWID", pRowid), Long.class);
	}

	@Override
	public String chkActiveSentences(final Long pCaseId) {
		final String sql = getQuery("CHK_ACTIVE_SENTENCES");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CASE_ID", pCaseId), String.class);
	}

	@Override
	public Long getSentenceSeq(final String pRowid) {
		final String sql = getQuery("GET_SENTENCE_SEQ");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_ROWID", pRowid), Long.class);
	}

	@Override
	public String chkActiveConditions(final Long pOffenderBookId, final Long pSentenceSeq) {
		final String sql = getQuery("CHK_ACTIVE_CONDITIONS");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_OFFENDER_BOOK_ID", pOffenderBookId, "P_SENTENCE_SEQ", pSentenceSeq), String.class);
	}

	// This method is used for Prevention of Termination if active cases still
	// exists.
	@Override
	public String chkActiveCases(final OffenderExternalMovements searchDao) {
		final String sql = getQuery("CHK_ACTIVE_CASES");
		String retVal = "";
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_OFFENDER_BOOK_ID", searchDao.getOffenderBookId()), String.class);
		} catch (Exception e) {
			
			logger.error("chkActiveCases :" + e);
			retVal="N";
		}
		return retVal;
	}

}
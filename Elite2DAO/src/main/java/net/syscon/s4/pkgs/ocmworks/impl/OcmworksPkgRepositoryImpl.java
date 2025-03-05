package net.syscon.s4.pkgs.ocmworks.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.ocmworks.OcmworksPkgRepository;

@Repository
public class OcmworksPkgRepositoryImpl extends RepositoryBase implements OcmworksPkgRepository {

	private static Logger logger = LogManager.getLogger(OcmworksPkgRepositoryImpl.class.getName());

	@Override
	public Integer getPrevDays(final String pTriggerName) {
		final String sql = getQuery("GET_L_DAYS");
		Integer lDays = 0;
		try {
			lDays = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_TRIGGER_NAME", pTriggerName),
					Integer.class);
		} catch (Exception e) {
			logger.error("getPrevDays :" + e);
			lDays = null;
		}
		return lDays;
	}

	@Override
	public Integer getCountIwpDocsCur(final BigDecimal pTemplateId) {
		final String sql = getQuery("GET_COUNT_IWP_DOCS_CUR");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_TEMPLATE_ID", pTemplateId),
					Integer.class);
		} catch (Exception e) {
			logger.error("getCountIwpDocsCur :" + e);
		}
		return count;
	}

	@Override
	public Integer getCountOffAssociated(final String pCaseNoteType, final String pCaseNoteSubType) {
		final String sql = getQuery("GET_COUNT_OFF_CASENOTE_CUR");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_CASE_NOTE_TYPE", pCaseNoteType, "P_CASE_NOTE_SUB_TYPE", pCaseNoteSubType),
					Integer.class);
		} catch (Exception e) {
			logger.error("getCountOffAssociated :" + e);
		}
		return count;
	}
}
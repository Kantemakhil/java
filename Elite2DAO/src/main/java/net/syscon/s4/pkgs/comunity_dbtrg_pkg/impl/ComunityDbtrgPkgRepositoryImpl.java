package net.syscon.s4.pkgs.comunity_dbtrg_pkg.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.comunity_dbtrg_pkg.ComunityDbtrgPkgRepository;
import net.syscon.s4.triggers.CaseNotes;

@Repository
public class ComunityDbtrgPkgRepositoryImpl extends RepositoryBase implements ComunityDbtrgPkgRepository {
	private static Logger logger = LogManager.getLogger(ComunityDbtrgPkgRepositoryImpl.class);

	@Override
	public StaffMembers vsEmailcur(final Long pOffenderBookId) {
		final String sqlname = getQuery("COMUNITY_DBTRG_PKG_VS_EMAILCUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams(":offenderBookId", pOffenderBookId),
					StaffMembers.class);
		} catch (final Exception e) {
			logger.error("vsEmailcur", e);
			return null;
		}

	}

	@Override
	public String vsCaseNoteCur(final String casNotType, final String reason, final String triggerEvent) {
		final String sqlname = getQuery("COMUNITY_DBTRG_PKG_VS_CASE_NOTE_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname,
					createParams("casNotType", casNotType, "reason", reason, "triggerEvent", triggerEvent),
					String.class);
		} catch (final Exception e) {
			logger.error("vsCaseNoteCur", e);
			return null;
		}

	}

	@Override
	public Long vsNseqCur(final Long OffenderBookId) {
		final String sqlname = getQuery("COMUNITY_DBTRG_PKG_VS_NSEQ_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams(":offenderBookId", OffenderBookId),
					Long.class);
		} catch (final Exception e) {
			logger.error("vsNseqCur", e);
			return null;
		}

	}

	@Override
	public Integer insertCaseNotes(final CaseNotes caseNotes) {
		final String sql = getQuery("COMUNITY_DBTRG_PKG_INSERT_CASE_NOTES_INSERT");
		try {
			return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(caseNotes));
		} catch (final Exception e) {
			logger.error("insertCaseNotes", e);
			return null;
		}
	}

	@Override
	public StaffMembers vsStaffCur(final String cUser) {
		final String sqlname = getQuery("COMUNITY_DBTRG_PKG_VS_STAFF_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams(":userId", cUser),
					StaffMembers.class);
		} catch (final Exception e) {
			logger.error("vsStaffCur", e);
			return null;
		}

	}

	/*
	 * This procedure is used to get the Email Parameters used for constructing the
	 * Email Data.
	 * 
	 */
	@Override
	public VHeaderBlock vsOffInfoCur(final Long offenderBookId,String userId) {
		final String sqlname = getQuery("COMUNITY_DBTRG_PKG_VS_OFF_INFO_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams("offenderBookId", offenderBookId,"USERID",userId),
					VHeaderBlock.class);
		} catch (final Exception e) {
			logger.error("vsOffInfoCur", e);
			return null;
		}

	}

	@Override
	public String curEmailPboard() {
		final String sqlname = getQuery("COMUNITY_DBTRG_PKG_CUR_EMAIL_PBOARD");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams(), String.class);
		} catch (final Exception e) {
			logger.error("curEmailPboard", e);
			return null;
		}

	}

	/*
	 * This procedure will check if the reason,case note type exists with an active
	 * flag for that given trigger event ( overloaded function )
	 * 
	 */
	@Override
	public String vsCaseNotesCur(final String caseType, final String reason) {
		final String sqlname = getQuery("COMUNITY_DBTRG_PKG_VS_CASE_NOTES_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname,
					createParams(":caseType", caseType, ":reason", reason), String.class);
		} catch (final Exception e) {
			logger.error("vsCaseNotesCur", e);
			return null;
		}

	}

	@Override
	public String staffCur(final Integer staffId) {
		final String sqlname = getQuery("COMUNITY_DBTRG_PKG_STAFF_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams(":staffId", staffId), String.class);
		} catch (final Exception e) {
			logger.error("staffCur", e);
			return null;
		}

	}

	@Override
	public String agyLocCur(final String pAgyLocId) {
		final String sqlname = getQuery("COMUNITY_DBTRG_PKG_GET_MISC_VALUES_AGY_LOC_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams(":pAgyLocId", pAgyLocId),
					String.class);
		} catch (final Exception e) {
			logger.error("agyLocCur", e);
			return null;
		}

	}

	@Override
	public String eventDspCur(final String pEventType) {
		final String sqlname = getQuery("COMUNITY_DBTRG_PKG_GET_MISC_VALUES_EVENT_DSP_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname, createParams(":pEventType", pEventType),
					String.class);
		} catch (final Exception e) {
			logger.error("eventDspCur", e);
			return null;
		}

	}

	@Override
	public String evtSubDspCur(final String pEventSubType, final String pEventType) {
		final String sqlname = getQuery("COMUNITY_DBTRG_PKG_GET_MISC_VALUES_EVT_SUB_DSP_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sqlname,
					createParams(":pEventSubType", pEventSubType, ":pEventType", pEventType), String.class);
		} catch (final Exception e) {
			logger.error("evtSubDspCur", e);
			return null;
		}
	}

}

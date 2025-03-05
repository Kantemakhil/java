package net.syscon.s4.pkgs.oidshlog.impl;

import java.math.BigDecimal;
import java.sql.Clob;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.shiftlogs.bean.AgencyShiftLogs;
import net.syscon.s4.pkgs.oidshlog.OidshlogPkgRepository;

@Repository
public class OidshlogPkgRepositoryImpl extends RepositoryBase implements OidshlogPkgRepository {
	private static Logger logger = LogManager.getLogger(OidshlogPkgRepositoryImpl.class.getName());

	@Override
	public AgencyShiftLogs checkClobState(final Long shiftLogSeq) {
		final String sql = getQuery("SELECT_CLOB_QUERY");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("SHIFT_LOG_SEQ", shiftLogSeq),
				AgencyShiftLogs.class);

	}

	@Override
	public Integer updateAencyShiftLogs(final Long shiftLogSeq, final String userName) {
		final String sql = getQuery("UPDATE_AGENCY_SHIFT_LOGS");
		return namedParameterJdbcTemplate.update(sql,
				createParams("SHIFT_LOG_SEQ", shiftLogSeq, "modifyUserId", userName));
	}

	@Override
	public AgencyShiftLogs selectAgyShiftLogs(final Long shiftLogSeq) {
		final String sql = getQuery("SELECT_AGENCY_SHIFTLOGS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("SHIFT_LOG_SEQ", shiftLogSeq),
				AgencyShiftLogs.class);
	}

	@Override
	public Clob getObservText(final BigDecimal pShiftLogSeq) {
		final String sql = getQuery("GET_OBSERVATION_TEXT");
		Clob retObsvText = null;
		try {
			retObsvText = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_SHIFT_LOG_SEQ", pShiftLogSeq),
					Clob.class);
		} catch (Exception e) {
			logger.error("getObservText :", e);
		}
		return retObsvText;
	}

}
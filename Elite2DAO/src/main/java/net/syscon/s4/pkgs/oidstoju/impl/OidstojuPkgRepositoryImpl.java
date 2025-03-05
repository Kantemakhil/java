package net.syscon.s4.pkgs.oidstoju.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.movementexternal.beans.OffenderPendNotifications;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.pkgs.oidstoju.OidstojuPkgRepository;

@Repository
public class OidstojuPkgRepositoryImpl extends RepositoryBase implements OidstojuPkgRepository {

	@Override
	public String recUpdCur(final OffenderIndSchedules offIndSch) {
		final String sql = getQuery("REC_UPD_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("v_sch_id", offIndSch.getEventId(), "v_mvt_date",
						"to_date(' " + offIndSch.getEventDate() + " ','DD-MON-YYYY HH24:MI:SS') ", "v_mvt_reason",
						offIndSch.getEventSubType()),
				String.class);
	}

	@Override
	public OffenderPendNotifications notiExisCur(final Integer eventId) {
		final String sql = getQuery("NOTI_EXIS_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("v_sch_id", eventId),
				OffenderPendNotifications.class);
	}

	@Override
	public void deleteOffenderNotCompletions(final Long notiSeq, final Long notiMoveSeq, final Integer vOffBookId) {
		final String sql = getQuery("DELETE_OFFENDER_NOT_COMPLETIONS");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("v_not_seq", notiSeq);
		inParamMap.put("v_not_m_seq", notiMoveSeq);
		inParamMap.put("v_off_book_id", vOffBookId);
		namedParameterJdbcTemplate.update(sql, inParamMap);
	}

	@Override
	public Integer chNotCur(final Long notiSeq, final Long notiMoveSeq, final Integer vOffBookId) {
		final String sql = getQuery("CH_NOT_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("v_not_seq", notiSeq, "v_not_m_seq", notiMoveSeq, "v_off_book_id", vOffBookId),
				Integer.class);
	}

	@Override
	public void updateOffenderNotCompletions(final Long notiSeq, final Long notiMoveSeq, final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_NOT_COMPLETIONS");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("v_not_seq", notiSeq);
		inParamMap.put("v_not_m_seq", notiMoveSeq);
		inParamMap.put("modifyUserId", userName);
		namedParameterJdbcTemplate.update(sql, inParamMap);
	}

}

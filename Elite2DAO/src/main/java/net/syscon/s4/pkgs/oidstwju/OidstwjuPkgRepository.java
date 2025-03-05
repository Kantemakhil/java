package net.syscon.s4.pkgs.oidstwju;

import java.sql.Timestamp;
import java.util.List;

import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;

public interface OidstwjuPkgRepository {

	Integer firstSelect(final String pAgyLocId, final Integer pOffenderBookId);

	Integer secondSelect(final String pAgyLocId, final Integer pOffenderBookId);

	String cancelCur(final String pAgyLocId, final Integer pOffenderBookId);

	Integer scheduleNaCur(final String pAgyLocId, final Integer pOffenderBookId);

	Timestamp dateConversion();

	List<Object[]> notReqCur(final String vMvtType, final String vMvtReason);

	Integer getOffPCntCur(final Integer integer, final String vMvtType, final String vMvtReason);

	Long getNotRecCur(final Integer integer, final String vMvtType, final String vMvtReason);

	List<Object[]> getRelNotRecCur(final Integer integer, final String vMvtType, final String vMvtReason);

	Integer getNextmoveSeqCur(final Integer pOffenderBookId, final Integer notiSeq);

	Integer offenderPendNotifications(final OffenderIndSchedules sch);

	Integer offenderNotCompletions(final OffenderIndSchedules sch);
}
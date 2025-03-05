package net.syscon.s4.pkgs.oidcount;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.automatedcounts.beans.LockedModules;
import net.syscon.s4.inst.automatedcounts.beans.TempOidcount;

public interface OidcountPkgRepository {
	List<LockedModules> checkdeadJobes(final String sessionId);

	TempOidcount tempOidCount(final String sessionId);

	List<ReferenceCodes> deadJobe(final String SessionId);

	Integer tempOidCountDelete(final String sessioId,String modifyUserId);

	Integer deleteAgyLocation(final Long reportingLocId,String modifyUserId);

	Integer deleteAgencyLocatCount(final Long reportingLocId,String modifyUserId);

	Integer deleteLockModules(final String sessionId);

	Integer cancelCount(final Long pSessionId, final String userName);

	Integer isCountExist(final Integer sessionId);

	void insertTempOidcount(final Integer sessionId, final String agyLocId, final Integer countTypeId,
			final String scheduledTime, final String userName);

	Integer getreportingLocId(final Integer sessionId);

	List<TempOidcount> lTempCountCur(final Integer sessionId);

	Integer lCancelFlag(final Integer sessionId);

	Integer deleteTempOidcount(final Integer sessionId,String modifyUserId);

	Integer lTerminatedSessionFlag(final Integer sessionId);

	Integer deleteAgencyLocationCounts(final Integer lReportingLocId,String modifyUserId);

	Integer deleteAgencyCounts(final Integer lReportingLocId,String modifyUserId);

	Integer lCountInitCur(final String agyLocId, final Integer lowestLocationId);

	Integer lCountLivUnitCur(final String agyLocId, final Integer lowestLocationId);

	Integer updateTempOidcountActualCount(final Integer lActualCount, final Long rowId, final String userName);

	Integer lCountInitMaleCur(final String agyLocId, final Integer lowestLocationId);

	Integer lCountLivUnitMaleCur(final String agyLocId, final Integer lowestLocationId);

	Integer updateTempOidcountTempMale(final Integer lTotalMale, final Long rowId, final String userName);

	Integer lCountInitFemaleCur(final String agyLocId, final Integer lowestLocationId);

	Integer lCountLivUnitFemaleCur(final String agyLocId, final Integer lowestLocationId);

	Integer updateTempOidcountTempFemale(final Integer lTotalFemale, final Long rowId, final String userName);

	Integer lCountInitOtherCur(final String agyLocId, final Integer lowestLocationId);

	Integer lCountLivUnitOtherCur(final String agyLocId, final Integer lowestLocationId);

	Integer updateTempOidcountTotalOther(final Integer lTotalOther, final Long rowId, final String userName);

	Integer lOutInitCur(final String agyLocId, final Integer lowestLocationId);

	Integer lOutLivUnitCur(final String agyLocId, final Integer lowestLocationId);

	Integer updateTempOidcountOutTotal(final Integer lOutTotal, final Long rowId, final String userName);

	Integer lTotalMaleOutInitCur(final String agyLocId, final Integer lowestLocationId);

	Integer lTotalMaleOutLivUnitCur(final String agyLocId, final Integer lowestLocationId);

	Integer updateTempOidcountTotalMaleOut(final Integer lTotalMaleOut, final Long rowId, final String userName);

	Integer lTotalFemaleOutInitCur(final String agyLocId, final Integer lowestLocationId);

	Integer lTotFemaleOutLivUnitCur(final String agyLocId, final Integer lowestLocationId);

	Integer updateTempOidcountTotalFemaleOut(final Integer lTotalFemaleOut, final Long rowId, final String userName);

	Integer lTotalOtherOutInitCur(final String agyLocId, final Integer lowestLocationId);

	Integer lTotOtherOutLivUnitCur(final String agyLocId, final Integer lowestLocationId);

	Integer updateTempOidcountTotalOtherOut(final Integer lTotalOtherOut, final Long rowId, final String userName);

	Integer getJob(final Integer sessionId);

	void removeOidcountJob(final Integer lJob);

	Integer cancelCount(final Long pSessionId);

}
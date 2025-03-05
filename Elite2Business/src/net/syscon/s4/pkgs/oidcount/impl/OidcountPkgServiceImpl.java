package net.syscon.s4.pkgs.oidcount.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.LockedModules;
import net.syscon.s4.inst.automatedcounts.beans.TempOidcount;
import net.syscon.s4.pkgs.oidcount.OidcountPkgRepository;
import net.syscon.s4.pkgs.oidcount.OidcountPkgService;

/*
 * Below comments are copied from package oidcount

|| Purpose: Package created for module OIDCOUNT
||
|| MODIFICATION HISTORY (Please put version history in a reverse-chronological order below)
||
|| ------------------------------------------------------------------------------------
|| Person        Date          Version    Comments
|| ------------  -----------   ---------  ---------------------------------------------
|| Nasir         28-NOV-2012   1.23       HPQC#18552  Modify set_temp_oidcount procedure.
|| Aditya	 25-SEP-2012   1.22	  Commented the condition "Outcome is null" to fix defect# 20191.
|| Himanshu      17-Jul-2012   1.21       HPQC#3728 Added function Show version and commented procedure show version
|| NIKO          08-SEP-2008   1.20       Modified the  PROCEDURE set_temp_oidcount as per 10G data model
|| EDWARD        27-FEB-2008   1.19       Added total_male_out, total_female_out, total_other_out to set_temp_oidcount.
|| EDWARD        28-Jan-2008   1.18       Added total_male, total_female, total_other to set_temp_oidcount.
|| Abu           13-Jan-2008   1.17       removed check on V$session for dead sessions.
||                                           Count Job will be terminated only if User wishes to Cancel the job
||                                           or Admin deletes record in Locked_Modules table.
|| Jason         15-Aug-2007   1.16       added logic for check_all_dead_jobs
|| Dragan        24-May-2007   1.15       added new column out_total and perform calculations for it
|| Abu           03-May-2007   1.14       performance fix
|| Abu           29-Mar-2007   1.12       performance fix
|| Abu           10-Dec-2006   1.10       modified to not retrieve zero counts
|| JASON         18-SEP-2006   1.7        Created Module specific Package
|| ====================================================================================

*/
@Service
public class OidcountPkgServiceImpl implements OidcountPkgService {
	@Autowired
	private OidcountPkgRepository oidcountRepository;

	private static final String N = "N";
	private static final String Y = "Y";
	private static final String CASE_LOAD = "INST";
	/*
	 * This procedure is migrated from oracle oidcount
	 * 
	 * @Procedure CHECK_REMOVE_DEAD_JOBS to be used for Deleting locked_modules
	 * search results.
	 */
	@Override
	public Integer checkRemoveDeadJobs(final String sessionId,String modifyUserId) {
        // Fetching records on locked_modules table
		final List<LockedModules> list = oidcountRepository.checkdeadJobes(sessionId);
		for (final LockedModules lockModu : list) {
			// Fetching record on temp_oidcount table
			final TempOidcount bean = oidcountRepository.tempOidCount(sessionId);
			// Deleting from temp_oidcount table
			oidcountRepository.tempOidCountDelete(lockModu.getSessionId().toString(),modifyUserId);
			// Deleting from agency_location_counts table
			oidcountRepository.deleteAgyLocation(bean.getReportingLocId().longValue(),modifyUserId);
			// Deleting from AGENCY_COUNTS table
			oidcountRepository.deleteAgencyLocatCount(bean.getReportingLocId().longValue(),modifyUserId);
		}
		// Deleting locked_modules data
		oidcountRepository.deleteLockModules(sessionId);
		return 0;
	}
	/*
	 * This procedure is migrated from oracle oidcount
	 * 
	 * @Procedure CANCEL_COUNT to be used for inserting into TEMP_OIDCOUNT Table
	 * search results.
	 */
	@Override
	public Integer cancelCount(final Long pSessionId, final String userName) {
		// inserting into TEMP_OIDCOUNT Table
		oidcountRepository.cancelCount(pSessionId, userName);
		return 1;
	}

	// This PROCEDURE submit_count_job  is migrated from oracle 
	@Override
	@Transactional
	public Integer submitCountJob(final AgencyCountTypes paramBean) {
		try {
			setTempOidcount(paramBean);
		} catch (Exception e) {
			return 1;
		}
		return 0;
	}

	public void setTempOidcount(final AgencyCountTypes paramBean) {
		Integer lCountExisting;
		Integer lReportingLocId;
		Integer lTerminatedSessionFlag;
		Integer lActualCount;
		Integer lTotalMale;
		Integer lTotalFemale;
		Integer lTotalOther;
		Integer lOutTotal;
		Integer lTotalMaleOut;
		Integer lTotalFemaleOut;
		String lAllCompleteFlag;
		Integer lTotalOtherOut;
		Integer lCancelFlag;

		lCountExisting = oidcountRepository.isCountExist(paramBean.getSessionId().intValue());
		List<TempOidcount> lTempCountCur = null;
		if (lCountExisting != null && lCountExisting > 0) {
			oidcountRepository.cancelCount(paramBean.getSessionId(), paramBean.getCreateUserId());
		}
		oidcountRepository.insertTempOidcount(paramBean.getSessionId().intValue(), paramBean.getAgyLocId(),
				paramBean.getReportingLocId(), paramBean.getScheduledTime(), paramBean.getCreateUserId());
		lReportingLocId = oidcountRepository.getreportingLocId(paramBean.getSessionId().intValue());
		lAllCompleteFlag = N;
		lTempCountCur = oidcountRepository.lTempCountCur(paramBean.getSessionId().intValue());
		for (TempOidcount data : lTempCountCur) {
			lCancelFlag = null;
			lTerminatedSessionFlag = null;

			lCancelFlag = oidcountRepository.lCancelFlag(paramBean.getSessionId().intValue());
			if (lCancelFlag != null && lCancelFlag > 0) {
				oidcountRepository.deleteTempOidcount(paramBean.getSessionId().intValue(),paramBean.getModifyUserId());
				lAllCompleteFlag = Y;
				continue;
			}

			lTerminatedSessionFlag = oidcountRepository.lTerminatedSessionFlag(paramBean.getSessionId().intValue());
			if (lTerminatedSessionFlag != null && lTerminatedSessionFlag == 0) {
				oidcountRepository.deleteTempOidcount(paramBean.getSessionId().intValue(),paramBean.getModifyUserId());
				oidcountRepository.deleteAgencyLocationCounts(lReportingLocId,paramBean.getModifyUserId());
				oidcountRepository.deleteAgencyCounts(lReportingLocId,paramBean.getModifyUserId());
				lAllCompleteFlag = Y;
				continue;
			}

			if (data.getActualCount() == null) {
				if (data.getLocationType() != null && data.getLocationType().equals(CASE_LOAD)) {
					lActualCount = 0;
					lActualCount = oidcountRepository.lCountInitCur(data.getAgyLocId(), data.getLowestLocationId());
				} else {
					lActualCount = 0;
					lActualCount = oidcountRepository.lCountLivUnitCur(data.getAgyLocId(), data.getLowestLocationId());
				}
				oidcountRepository.updateTempOidcountActualCount(lActualCount, data.getRowId(),
						paramBean.getCreateUserId());

			}

			if (data.getTotalMale() == null) {
				if (data.getLocationType() != null && data.getLocationType().equals(CASE_LOAD)) {
					lTotalMale = 0;
					lTotalMale = oidcountRepository.lCountInitMaleCur(data.getAgyLocId(), data.getLowestLocationId());
				} else {
					lTotalMale = 0;
					lTotalMale = oidcountRepository.lCountLivUnitMaleCur(data.getAgyLocId(),
							data.getLowestLocationId());
				}

				oidcountRepository.updateTempOidcountTempMale(lTotalMale, data.getRowId(), paramBean.getCreateUserId());

			}

			if (data.getTotalFemale() == null) {
				if (data.getLocationType() != null && data.getLocationType().equals(CASE_LOAD)) {
					lTotalFemale = 0;
					lTotalFemale = oidcountRepository.lCountInitFemaleCur(data.getAgyLocId(),
							data.getLowestLocationId());

				} else {
					lTotalFemale = 0;
					lTotalFemale = oidcountRepository.lCountLivUnitFemaleCur(data.getAgyLocId(),
							data.getLowestLocationId());
				}
				oidcountRepository.updateTempOidcountTempFemale(lTotalFemale, data.getRowId(),
						paramBean.getCreateUserId());
			}

			if (data.getTotalOther() == null) {
				if (data.getLocationType() != null && data.getLocationType().equals(CASE_LOAD)) {
					lTotalOther = 0;
					lTotalOther = oidcountRepository.lCountInitOtherCur(data.getAgyLocId(), data.getLowestLocationId());
				} else {
					lTotalOther = 0;
					lTotalOther = oidcountRepository.lCountLivUnitOtherCur(data.getAgyLocId(),
							data.getLowestLocationId());
				}
				oidcountRepository.updateTempOidcountTotalOther(lTotalOther, data.getRowId(),
						paramBean.getCreateUserId());
			}

			if (data.getOutTotal() == null) {
				if (data.getLocationType() != null && data.getLocationType().equals(CASE_LOAD)) {
					lOutTotal = 0;
					lOutTotal = oidcountRepository.lOutInitCur(data.getAgyLocId(), data.getLowestLocationId());
				} else {
					lOutTotal = 0;
					lOutTotal = oidcountRepository.lOutLivUnitCur(data.getAgyLocId(), data.getLowestLocationId());
				}
				oidcountRepository.updateTempOidcountOutTotal(lOutTotal, data.getRowId(), paramBean.getCreateUserId());
			}

			if (data.getTotalMaleOut() == null) {
				if (data.getLocationType() != null && data.getLocationType().equals(CASE_LOAD)) {
					lTotalMaleOut = 0;
					lTotalMaleOut = oidcountRepository.lTotalMaleOutInitCur(data.getAgyLocId(),
							data.getLowestLocationId());
				} else {
					lTotalMaleOut = 0;
					lTotalMaleOut = oidcountRepository.lTotalMaleOutLivUnitCur(data.getAgyLocId(),
							data.getLowestLocationId());
				}
				oidcountRepository.updateTempOidcountTotalMaleOut(lTotalMaleOut, data.getRowId(),
						paramBean.getCreateUserId());
			}

			if (data.getTotalFemaleOut() == null) {
				if (data.getLocationType() != null && data.getLocationType().equals(CASE_LOAD)) {
					lTotalFemaleOut = 0;
					lTotalFemaleOut = oidcountRepository.lTotalFemaleOutInitCur(data.getAgyLocId(),
							data.getLowestLocationId());
				} else {
					lTotalFemaleOut = 0;
					lTotalFemaleOut = oidcountRepository.lTotFemaleOutLivUnitCur(data.getAgyLocId(),
							data.getLowestLocationId());
				}
				oidcountRepository.updateTempOidcountTotalFemaleOut(lTotalFemaleOut, data.getRowId(),
						paramBean.getCreateUserId());
			}

			if (data.getTotalOtherOut() == null) {
				if (data.getLocationType() != null && data.getLocationType().equals(CASE_LOAD)) {
					lTotalOtherOut = 0;
					lTotalOtherOut = oidcountRepository.lTotalOtherOutInitCur(data.getAgyLocId(),
							data.getLowestLocationId());

				} else {
					lTotalOtherOut = 0;
					lTotalOtherOut = oidcountRepository.lTotOtherOutLivUnitCur(data.getAgyLocId(),
							data.getLowestLocationId());
				}
				oidcountRepository.updateTempOidcountTotalOtherOut(lTotalOtherOut, data.getRowId(),
						paramBean.getCreateUserId());
			}
		}

		if (lAllCompleteFlag.equals(N)) {
			lCancelFlag = null;
			lCancelFlag = oidcountRepository.lCancelFlag(paramBean.getSessionId().intValue());
			if (lCancelFlag > 0) {
				oidcountRepository.deleteTempOidcount(paramBean.getSessionId().intValue(),paramBean.getModifyUserId());
			}
			lTerminatedSessionFlag = null;
			lTerminatedSessionFlag = oidcountRepository.lTerminatedSessionFlag(paramBean.getSessionId().intValue());
			if (lTerminatedSessionFlag != null && lTerminatedSessionFlag == 0) {
				oidcountRepository.deleteTempOidcount(paramBean.getSessionId().intValue(),paramBean.getModifyUserId());
				oidcountRepository.deleteAgencyLocationCounts(lReportingLocId,paramBean.getModifyUserId());
				oidcountRepository.deleteAgencyCounts(lReportingLocId,paramBean.getModifyUserId());
			}

		}

		final Integer lJob = oidcountRepository.getJob(paramBean.getSessionId().intValue());
		oidcountRepository.removeOidcountJob(lJob);

	}

}

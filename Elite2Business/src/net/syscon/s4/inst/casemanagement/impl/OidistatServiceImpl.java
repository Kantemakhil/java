package net.syscon.s4.inst.casemanagement.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderBillingProfiles;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyBillingProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.casemanagement.OidistatRepository;
import net.syscon.s4.inst.casemanagement.OidistatService;
import net.syscon.s4.inst.casemanagement.beans.OffenderImprisonStatuses;
import net.syscon.s4.inst.casemanagement.beans.OffenderImprisonStatusesCommitBean;

/**
 * Class OidistatServiceImpl
 */
@Service
public class OidistatServiceImpl extends BaseBusiness implements OidistatService {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidistatServiceImpl.class.getName());

	@Autowired
	private OidistatRepository oidistatRepo;

	@Autowired
	private EliteDateService eliteDateService;

	/**
	 * Creates new OidistatServiceImpl class Object
	 */
	public OidistatServiceImpl() {
		// OidistatServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public OffenderImprisonStatuses offBkgOnCheckDeleteMaster(final OffenderImprisonStatuses paramBean) {
		return oidistatRepo.offBkgOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	@Transactional(rollbackFor=Exception.class)
	public Integer offImpsPreInsert(final OffenderImprisonStatuses paramBean) {
		final OffenderImprisonStatuses offImpSts = oidistatRepo.offImpsPreInsertStatus(paramBean);
		Integer inResult = 0;
			Integer inCheckDt = 0;
			Integer inCheckTime = 0;
			final OffenderExternalMovements objExt = new OffenderExternalMovements();
			objExt.setToAgyLocId(paramBean.getGlobalCaseloadId());
			objExt.setOffenderBookId(paramBean.getOffenderBookId());
			objExt.setMovementDate(paramBean.getEffectiveDate());
			inCheckDt = chkImpDate(objExt);
			if (inCheckDt == 0) {
				objExt.setMovementTime(paramBean.getEffectiveTime());
				inCheckTime = chkImpDateEffectiveTime(objExt);
				if (inCheckTime > 0) {
					inResult = 102;
					return inResult;
				}
			} else {
				if (inCheckDt == 1) {
					inResult = 100;
				} else {
					inResult = 101;
					throw new RuntimeErrorException(new Error(), "101");
				}

				return inResult;
			}
			final DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
			if (offImpSts != null && offImpSts.getEffectiveDate() != null
					&& offImpSts.getImprisonmentStatus() != null) {
				try {
				final String lvEffDate = outputFormat.format(paramBean.getEffectiveDate());
				final String effectiveDate = outputFormat.format(offImpSts.getEffectiveDate());
				final Date lvEffDt = new SimpleDateFormat("dd/MM/yyyy").parse(lvEffDate);
				final Date effDt = new SimpleDateFormat("dd/MM/yyyy").parse(effectiveDate);
				if (offImpSts.getImprisonmentStatus().equals(paramBean.getImprisonmentStatus())
						&& (effDt.compareTo(lvEffDt) == 0)) {
					inResult = 103;
					return inResult;
				}
				} catch(Exception e) {
					logger.error("In offImpsCommit method : ", e);
				} 
			}
			final String lvBillingFlag = oidistatRepo.processBillProfilGetCountCur(paramBean.getGlobalCaseloadId());

			String lvCaseId = null;
			if (lvBillingFlag != null && lvBillingFlag.equals("Y")) {
				lvCaseId = paramBean.getGlobalCaseloadId();
			} else {
				final String lvCaseloadId = oidistatRepo.processBillProfileCaseLoads(paramBean.getAgyLocId());
				if (lvCaseloadId != null) {
					lvCaseId = lvCaseloadId;
				}
			}
			if (lvCaseId != null) {
				final String lvBosFlag = oidistatRepo.getProfileValueClient();
				if (lvBosFlag != null && lvBosFlag.equals("Y")) {
					final Date lvChkStartDate = oidistatRepo.processBillProfileMaxEffective(paramBean);
					final Integer lImpDelayDays = oidistatRepo.processBillProfileDelayDays(paramBean);
					OffenderBillingProfiles offBillProf = null;
					final Date effectiveTime = oidistatRepo
							.processBillProfileMaxEffectiveDateSys(paramBean.getEffectiveTime());
					if (lvChkStartDate != null && effectiveTime != null && lImpDelayDays != null) {
						try{
						final String strLvChkStartDate = outputFormat.format(lvChkStartDate);
						final String strEffectiveTime = outputFormat.format(effectiveTime);
						final Date lvChkStDt = new SimpleDateFormat("dd/MM/yyyy").parse(strLvChkStartDate);
						final Date lvChkEffDt = new SimpleDateFormat("dd/MM/yyyy").parse(strEffectiveTime);
						offBillProf = new OffenderBillingProfiles();
						offBillProf.setEffectiveEndTime(paramBean.getEffectiveTime());
						offBillProf.setCaseloadId(paramBean.getGlobalCaseloadId());
						offBillProf.setOffenderBookingId(paramBean.getOffenderBookId());
						offBillProf.setModifyUserId(paramBean.getModifyUserId());
						if (lvChkEffDt.compareTo(lvChkStDt) <= 0) {
							offBillProf.setEffectiveDateEnd(lvChkStartDate);
							oidistatRepo.offenderBillingProfilesUpdateEqualOrLess(offBillProf);
						} else {
							offBillProf.setEffectiveDateEnd(paramBean.getEffectiveTime());
							offBillProf.setDelayDays(lImpDelayDays);
							oidistatRepo.offenderBillingProfilesUpdateGreater(offBillProf);
						}
						} catch (Exception e) {
							logger.error("In offImpsCommit method : ", e);
						}
					}
					Integer lvDelayDay = 0;
					final AgencyBillingProfiles agyBillProf = oidistatRepo.processBillProfileRecord(paramBean);

					if (agyBillProf != null && agyBillProf.getAGENCY_ID() != null
							&& agyBillProf.getBILLING_TYPE() != null) {
						agyBillProf.setCASELOAD_ID(paramBean.getGlobalCaseloadId());
						lvDelayDay = oidistatRepo.processBillProfileAgencyDelayDays(agyBillProf);
						offBillProf = new OffenderBillingProfiles();
						offBillProf.setAgencyId(agyBillProf.getAGENCY_ID());
						offBillProf.setBillingType(agyBillProf.getBILLING_TYPE());
						offBillProf.setCaseloadId(paramBean.getGlobalCaseloadId());
						offBillProf.setOffenderBookingId(paramBean.getOffenderBookId());
						final Integer lvCount = oidistatRepo.processBillProfileCount(offBillProf);

						if (lvCount > 0) {
							if (agyBillProf.getFREQUENCY() != null
									&& !agyBillProf.getFREQUENCY().equals("ONE TIME FEE")) {
								offBillProf = new OffenderBillingProfiles();
								offBillProf.setEffectiveDateStart(paramBean.getEffectiveTime());
								offBillProf.setImpAgyBillingDetailId(agyBillProf.getImpAgyBillingDetailId());
								offBillProf.setCaseloadId(paramBean.getGlobalCaseloadId());
								offBillProf.setOffenderBookingId(paramBean.getOffenderBookId());
								offBillProf.setAgencyId(agyBillProf.getAGENCY_ID());
								offBillProf.setBillingType(agyBillProf.getBILLING_TYPE());
								offBillProf.setDelayDays(lvDelayDay);
								offBillProf.setModifyUserId(paramBean.getModifyUserId());
								oidistatRepo.offenderBillingProfilesUpdateNotEqualFrequency(offBillProf);
							} else {
								Date lvEffDateEnd = null;
								if (agyBillProf.getFREQUENCY() != null
										&& agyBillProf.getFREQUENCY().equals("ONE TIME FEE")) {
									lvEffDateEnd = paramBean.getEffectiveTime();
								}

								offBillProf = new OffenderBillingProfiles();
								offBillProf.setEffectiveDateStart(paramBean.getEffectiveTime());
								offBillProf.setImpAgyBillingDetailId(agyBillProf.getImpAgyBillingDetailId());
								offBillProf.setOffenderBookingId(paramBean.getOffenderBookId());
								
								offBillProf.setAgencyId(agyBillProf.getAGENCY_ID());
								offBillProf.setBillingType(agyBillProf.getBILLING_TYPE());
								offBillProf.setEffectiveDateEnd(lvEffDateEnd);
								offBillProf.setCreateDate(eliteDateService.getDBTime());
								offBillProf.setRate(agyBillProf.getRATE());
								offBillProf.setEffectiveStartTime(paramBean.getEffectiveTime());
								offBillProf.setEffectiveEndTime(lvEffDateEnd);
								offBillProf.setEligibleDate(paramBean.getEffectiveTime());
								if (lvCaseId != null) {
									offBillProf.setCaseloadId(lvCaseId);
								}
								offBillProf.setDelayDays(lvDelayDay);
								offBillProf.setCreateUserId(paramBean.getModifyUserId());
								oidistatRepo.offenderBillingProfilesInsertEqualFrequency(offBillProf);
							}
						}
					}
				}
			}
		return 0;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public OffenderImprisonStatuses offImpsPostQuery(final OffenderImprisonStatuses paramBean) {
		return oidistatRepo.offImpsPostQuery(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public OffenderBillingProfiles processBillProfile(final OffenderBillingProfiles paramBean) {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public Integer chkImpDate(final OffenderExternalMovements paramBean) {

		final Date lvAdmitDate = oidistatRepo.chkImpDateMovement(paramBean);
		int inResult = 0;
		final DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if (lvAdmitDate != null) {
				final String admitDate = outputFormat.format(lvAdmitDate);
				final String effectiveDate = outputFormat.format(paramBean.getMovementDate());
				final Date admDt = new SimpleDateFormat("dd/MM/yyyy").parse(admitDate);
				final Date effDt = new SimpleDateFormat("dd/MM/yyyy").parse(effectiveDate);
				if (effDt.compareTo(admDt) < 0) {
					inResult = 1;
					return inResult;
				}
			}
			final OffenderImprisonStatuses offImpStatus = new OffenderImprisonStatuses();
			offImpStatus.setOffenderBookId(paramBean.getOffenderBookId());
			offImpStatus.setAgyLocId(paramBean.getToAgyLocId());
			final Date lvEffectiveDate = oidistatRepo.chkImpDateEffective(offImpStatus);

			if (lvEffectiveDate != null) {
				final String lvEffDate = outputFormat.format(lvEffectiveDate);
				final String effectiveDate = outputFormat.format(paramBean.getMovementDate());
				final Date lvEffDt = new SimpleDateFormat("dd/MM/yyyy").parse(lvEffDate);
				final Date effDt = new SimpleDateFormat("dd/MM/yyyy").parse(effectiveDate);
				if (effDt.compareTo(lvEffDt) < 0) {
					inResult = 2;
					return inResult;
				}
			}
		} catch (Exception e) {
			logger.error("In chkImpDate method : ", e);
		}

		return inResult;
	}

	public Integer chkImpDateEffectiveTime(final OffenderExternalMovements paramBean) {
		int inResult = 0;
		try {
			final DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
			final OffenderImprisonStatuses offImpStatus = new OffenderImprisonStatuses();
			offImpStatus.setOffenderBookId(paramBean.getOffenderBookId());
			offImpStatus.setAgyLocId(paramBean.getToAgyLocId());
			final OffenderImprisonStatuses offImplResult = oidistatRepo.chkImpDateEffectiveTime(offImpStatus);
			if (offImplResult != null && offImplResult.getEffectiveDate() != null
					&& offImplResult.getEffectiveTime() != null) {

				final String admitDate = outputFormat.format(offImplResult.getEffectiveDate());
				final String effectiveDate = outputFormat.format(paramBean.getMovementDate());
				final Date admDt = new SimpleDateFormat("dd/MM/yyyy").parse(admitDate);
				final Date effDt = new SimpleDateFormat("dd/MM/yyyy").parse(effectiveDate);
				final DateFormat timeFormat = new SimpleDateFormat("HH:mm");
				final String resultEffTime = admitDate + " " + timeFormat.format(offImplResult.getEffectiveTime());
				final String resultMovTime = effectiveDate + " " + timeFormat.format(paramBean.getMovementTime());

				final Date resultAdDt = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(resultEffTime);
				final Date resultMovDt = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(resultMovTime);
				if ((resultMovDt.before(resultAdDt) ) && effDt.compareTo(admDt) == 0) {
					inResult = 1;
				}
			}
		} catch (Exception e) {
			logger.error("In chkImpDate method : ", e);
		}
		return inResult;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderImprisonStatuses> offImpsExecuteQuery(final OffenderImprisonStatuses searchRecord) {
		return oidistatRepo.offImpsExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_IMPS
	 *
	 */
	@Transactional
	public Integer offImpsCommit(final OffenderImprisonStatusesCommitBean commitBean) {
		int liReturn = 0;

		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final OffenderImprisonStatuses offImpSt : commitBean.getUpdateList()) {
				offImpSt.setModifyUserId(commitBean.getCreateUserId());
				offImpSt.setCreateUserId(commitBean.getCreateUserId());
				if (offImpSt.getErrorFlag() != null && offImpSt.getErrorFlag().equals("true")) {
					offImpSt.setErrorFlag("Y");
				} else {
					offImpSt.setErrorFlag("N");
				}
			}
			liReturn = oidistatRepo.offImpsUpdateOffenderImprisonStatuses(commitBean.getUpdateList());
		}
		// insertRecords
		List<OffenderImprisonStatuses> lstOffImpSts = new ArrayList<OffenderImprisonStatuses>();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final OffenderImprisonStatuses offImpSt : commitBean.getInsertList()) {
				lstOffImpSts = new ArrayList<OffenderImprisonStatuses>();
				offImpSt.setModifyUserId(commitBean.getCreateUserId());
				offImpSt.setCreateUserId(commitBean.getCreateUserId());
				final Integer inValue = offImpsPreInsert(offImpSt);
				if (inValue == 0) {
					final OffenderImprisonStatuses updateOffImp = new OffenderImprisonStatuses();
					updateOffImp.setExpiryDate(offImpSt.getEffectiveDate());
					updateOffImp.setOffenderBookId(offImpSt.getOffenderBookId());
					updateOffImp.setModifyUserId(offImpSt.getModifyUserId());
					oidistatRepo.offenderUpdateImprisonStatuses(updateOffImp);
					final Long lnSeq = oidistatRepo.offImpsPreInsert(offImpSt);
					offImpSt.setImprisonStatusSeq(lnSeq);
					if (offImpSt.getErrorFlag() != null && offImpSt.getErrorFlag().equals("true")) {
						offImpSt.setErrorFlag("Y");
					} else {
						offImpSt.setErrorFlag("N");
					}
					lstOffImpSts.add(offImpSt);
					liReturn = oidistatRepo.offImpsInsertOffenderImprisonStatuses(lstOffImpSts);
					if (liReturn == 0) {
						return liReturn;
					}
				} else {
					liReturn = inValue;
					return liReturn;
				}
			}
		}

		return liReturn;
	}

	/**
	 * Insert the records from database table
	 *
	 *
	 */
	@Transactional
	public Integer offImpsUpdateCommit(final OffenderImprisonStatusesCommitBean commitBean) {
		int liReturn = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderImprisonStatuses object : commitBean.getUpdateList()) {
				object.setModifyUserId(commitBean.getCreateUserId());
				
			}
			liReturn = oidistatRepo.offImpsUpdateOffenderImprisonStatuses(commitBean.getUpdateList());
		}

		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgImprisonmentStaRecordGroup() {
		return oidistatRepo.rgImprisonmentStaRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<AgencyLocations> rgAgyLocIdRecordGroup(final String caseloadId) {
		List<AgencyLocations> returnList = oidistatRepo.rgAgyLocIdRecordGroup(caseloadId);
		returnList.forEach(element -> {
			if ("N".equals(element.getActiveFlag())) {
				element.setCanDisplay(false);
			}
		});
		return returnList;
	}

}
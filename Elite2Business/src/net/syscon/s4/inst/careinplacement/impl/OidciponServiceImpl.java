package net.syscon.s4.inst.careinplacement.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.careinplacement.OidciponRepository;
import net.syscon.s4.inst.careinplacement.OidciponService;
import net.syscon.s4.inst.careinplacement.beans.OffenderCipDetails;
import net.syscon.s4.inst.careinplacement.beans.OffenderCipDetailsCommitBean;
import net.syscon.s4.inst.careinplacement.beans.PlacementDurations;

/**
 * Class OidciponServiceImpl
 */
@Service
public class OidciponServiceImpl extends BaseBusiness implements OidciponService {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidciponServiceImpl.class.getName());

	@Autowired
	private OidciponRepository oidciponRepo;

	@Autowired
	private EliteDateService eliteDateService;

	/**
	 * 
	 * /** Creates new OidciponServiceImpl class Object
	 */
	public OidciponServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public OffenderCipDetails offBkgOnCheckDeleteMaster(final OffenderCipDetails paramBean) {
		return oidciponRepo.offBkgOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public Integer offCipDetailsPreInsert(final OffenderCipDetails paramBean) {
		return oidciponRepo.offCipDetailsPreInsert(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public AgencyLocations offCipDetailsPostQuery(final AgencyLocations paramBean) {
		return oidciponRepo.offCipDetailsPostQuery(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public OmsModules createFormGlobals(final OmsModules paramBean) {
		return oidciponRepo.createFormGlobals(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<Object> defaultAgyLocId(final CaseloadAgencyLocations paramBean) {
		return oidciponRepo.defaultAgyLocId(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<PlacementDurations> defaultDurationType(final PlacementDurations paramBean) {
		return oidciponRepo.defaultDurationType(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public String dtValidationForInactiveOff(final OffenderExternalMovements paramBean) {
		String strMsg = "NONE";
		if (paramBean != null) {
			final List<OffenderExternalMovements> lstObj = oidciponRepo.dtValidationForInactiveOff(paramBean);
			boolean activeFlag = false;
			if (lstObj != null && lstObj.size() > 0) {
				final OffenderExternalMovements extMov = lstObj.get(0);

				if (extMov != null) {
					final String strDspDes = paramBean.getDspDescription();
					if (paramBean.getDspDescription() != null
							&& ("effectiveDate".equals(strDspDes) || "releaseDate".equals(strDspDes)
									|| "effectiveTime".equals(strDspDes) || "releaseTime".equals(strDspDes))) {
						try {
							final DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
							final String eventDate = outputFormat.format(paramBean.getMovementDate());
							final String peventDate = outputFormat.format(extMov.getMovementTime());

							final Date movementDt = new SimpleDateFormat("dd/MM/yyyy").parse(eventDate);
							final Date prevMovementDt = new SimpleDateFormat("dd/MM/yyyy").parse(peventDate);

							if (movementDt.compareTo(prevMovementDt) > 0) {
								activeFlag = true;
							}
						} catch (Exception e) {
							logger.error("In dtValidationForInactiveOff method : ", e);
						}

					}
				}

				if (activeFlag) {
					strMsg = extMov.getDspDescription() + " : " + extMov.getTimeString();
				}
			}

		}
		return strMsg;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<OffenderCipDetails> checkForActiveCpRec(final OffenderCipDetails paramBean) {
		return oidciponRepo.checkForActiveCpRec(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public Integer checkDate(final VHeaderBlock paramBean) {

		Integer checkReleaseDate = 0;
		OffenderCipDetails offCipDetails = null;
		final List<OffenderCipDetails> lstObj = oidciponRepo.checkDate(paramBean);
		final int releaseDateCount = oidciponRepo.checkDateCount(paramBean);
		try {
			if (paramBean.getOffenderBookId() != null && (releaseDateCount > 0)) {
				if (lstObj != null && lstObj.size() > 0) {
					offCipDetails = lstObj.get(0);
					final DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
					final String expiryDate = outputFormat.format(offCipDetails.getExpiryTime());
					final String sysDate = outputFormat.format(eliteDateService.getDBTime());
					final Date expDt = new SimpleDateFormat("dd/MM/yyyy").parse(expiryDate);
					final Date sysDt = new SimpleDateFormat("dd/MM/yyyy").parse(sysDate);

					if (offCipDetails.getReleaseTime() != null) {
						checkReleaseDate = 1;
					} else if (expDt.compareTo(sysDt) < 0) {
						checkReleaseDate = 1;
					} else {
						checkReleaseDate = 0;
					}
				}
			} else {
				checkReleaseDate = 1;
			}

		} catch (Exception e) {
			logger.error("In checkDate method : ", e);
		}
		return checkReleaseDate;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderCipDetails> offCipDetailsExecuteQuery(final OffenderCipDetails searchRecord) {
		final List<OffenderCipDetails> lstOffDetails = (List<OffenderCipDetails>) oidciponRepo
				.offCipDetailsExecuteQuery(searchRecord);
		Date lvReleaseTime = null;
		if (lstOffDetails != null && lstOffDetails.size() > 0) {
			for (final OffenderCipDetails offCipDetails : lstOffDetails) {
				if (offCipDetails.getReleaseTime() == null) {
					lvReleaseTime = eliteDateService.getDBTime();
				} else if (offCipDetails.getReleaseTime() != null) {
					if (offCipDetails.getReleaseTime().compareTo(eliteDateService.getDBTime()) > 0) {
						lvReleaseTime = eliteDateService.getDBTime();
					} else {
						lvReleaseTime = offCipDetails.getReleaseTime();
					}
				}
				final Double lvTtInHours = oidciponRepo.computeForHours(offCipDetails.getEffectiveTime(),
						lvReleaseTime);
				final Integer lvTtInDays = oidciponRepo.computeForFloorHours(lvTtInHours);
				final Double lvRemTime = oidciponRepo.computeForTime(lvTtInHours);
				final String lvTsInHr = oidciponRepo.computeForHoursFormat(lvRemTime);

				if (lvTtInDays > 0) {
					offCipDetails.setNbtDaysServed(lvTtInDays);
				}
				offCipDetails.setNbtHoursServed(lvTsInHr);
			}
		}
		return lstOffDetails;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 */
	@Transactional
	public Integer offCipDetailsCommit(final OffenderCipDetailsCommitBean commitBean) {
		int liReturn = 0;
		Integer placementSeq = null;
		List<OffenderCipDetails> lstOffCip = new ArrayList<OffenderCipDetails>();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {

			for (final OffenderCipDetails offCipDetails : commitBean.getInsertList()) {
				lstOffCip = new ArrayList<OffenderCipDetails>();
				placementSeq = oidciponRepo.offCipDetailsPreInsert(offCipDetails);
				if (offCipDetails.getReleaseTime() == null) {
					offCipDetails.setReleaseTime(offCipDetails.getReleaseDate());
				}
				offCipDetails.setCreateUserId(commitBean.getCreateUserId());
				offCipDetails.setPlacementSeq(placementSeq.longValue());
				lstOffCip.add(offCipDetails);
				liReturn = oidciponRepo.offCipDetailsInsertOffenderCipDetails(lstOffCip);
			}

		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final OffenderCipDetails offCipDetails : commitBean.getUpdateList()) {
				offCipDetails.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oidciponRepo.offCipDetailsUpdateOffenderCipDetails(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oidciponRepo.offCipDetailsDeleteOffenderCipDetails(commitBean.getDeleteList());
		}

		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return oidciponRepo.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean commitBean) {
		return 0;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgPlacementReasonRecordGroup(final String placementType) {
		return oidciponRepo.rgPlacementReasonRecordGroup(placementType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgPlacementTypeRecordGroup() {
		return oidciponRepo.rgPlacementTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<AgencyLocations> rgAgyLocsRecordGroup(final String caseloadId) {
		return oidciponRepo.rgAgyLocsRecordGroup(caseloadId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgRequestedByRecordGroup() {
		return oidciponRepo.rgRequestedByRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgAuthorizedByRecordGroup() {
		return oidciponRepo.rgAuthorizedByRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<PlacementDurations> rgDurationTypeRecordGroup(final String placementType) {
		return oidciponRepo.rgDurationTypeRecordGroup(placementType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgReleasedByRecordGroup() {
		return oidciponRepo.rgReleasedByRecordGroup();

	}

}
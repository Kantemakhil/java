package net.syscon.s4.inst.movements.housingchanges.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.movementexternal.beans.VHeaderBlockCommitBean;
import net.syscon.s4.inst.movements.housingchanges.OidehlocRepository;
import net.syscon.s4.inst.movements.housingchanges.OidehlocService;
import net.syscon.s4.pkgs.non_association.NonAssociationRepository;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.triggers.OffenderBookingsBkadmTrgService;
import net.syscon.s4.triggers.OffenderBookingsT2Service;
import net.syscon.s4.triggers.OffenderBookingsT7Service;
import net.syscon.s4.triggers.OffendersBookVineIntfTrgService;

/**
 * Class OidehlocServiceImpl
 */
@Service
public class OidehlocServiceImpl extends BaseBusiness implements OidehlocService {

	@Autowired
	private OidehlocRepository oidehlocRepo;
	@Autowired
	private NonAssociationService nonAssociationService;
	@Autowired
	private OffenderBookingsT2Service offenderBookingsT2Service;
	@Autowired
	private OffenderBookingsBkadmTrgService offenderBookingsBkadmTrgService;
	@Autowired
	private OffendersBookVineIntfTrgService offendersBookVineIntfTrgService;
	@Autowired
	private OffenderBookingsT7Service offenderBookingsT7Service;
	@Autowired
	private NonAssociationRepository nonAssociationRepository;

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidehlocServiceImpl.class.getName());

	/**
	 * Creates new OidehlocServiceImpl class Object
	 */
	public OidehlocServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> cgwhenNewFormInstance(final SysDual paramBean) {
		return oidehlocRepo.cgwhenNewFormInstance(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VHeaderBlock> vOffBkgExecuteQuery(final VHeaderBlock searchRecord) {
		return oidehlocRepo.vOffBkgExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_OFF_BKG
	 *
	 */
	@Transactional
	public Integer vOffBkgCommit(final VHeaderBlockCommitBean commitBean) {
		Integer liReturn = 0;
		VHeaderBlock objBkgHeader = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final VHeaderBlock objHeaderBlock : commitBean.getInsertList()) {
				objHeaderBlock.setCreateUserId(commitBean.getCreateUserId());
				objBkgHeader = objHeaderBlock;
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			try {
				for (final VHeaderBlock objVOff : commitBean.getUpdateList()) {
					objVOff.setModifyUserId(commitBean.getCreateUserId());
					objBkgHeader.setModifyUserId(commitBean.getCreateUserId());
					String activeFlag = oidehlocRepo.checkOffStatus(objBkgHeader.getOffenderBookId());
					if (activeFlag != null && "Y".equals(activeFlag)) {
						activeFlag = oidehlocRepo.checkOffStatus(objVOff.getOffenderBookId());
						if (activeFlag != null && !"Y".equals(activeFlag)) {
							liReturn = 101;
							return liReturn;
						}
					} else {
						liReturn = 100;
						return liReturn;
					}				 
					 OffenderBookings olddta = oidehlocRepo.gettingOldData(objVOff.getOffenderBookId());
						OffenderBookings offenderBooking = new OffenderBookings();
						offenderBooking.setAgyLocId(objVOff.getAgyLocId());
						offenderBooking.setCreateAgyLocId(objVOff.getCreateAgyLocId());
						offenderBooking.setLivingUnitId(objVOff.getLivingUnitId());
						offenderBooking.setOffenderBookId(objVOff.getOffenderBookId().longValue());
						offenderBooking.setOffenderId(objVOff.getOffenderId());
						offenderBooking.setActiveFlag(objVOff.getActiveFlag());
						offenderBooking.setCreateUserId(commitBean.getCreateUserId());
						offenderBooking.setModifyUserId(commitBean.getCreateUserId());
						OffenderBookings retObj = offenderBookingsT2Service.offenderBookingsT2(offenderBooking, olddta);
						objVOff.setCreateAgyLocId(retObj.getCreateAgyLocId());
						objVOff.setCreateUserId(commitBean.getCreateUserId());
						final int insResult = oidehlocRepo.updOffBook(objVOff, objBkgHeader);
						 String operation = "UPDATING";
						offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(olddta, offenderBooking,
								operation);

						offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(offenderBooking,ApplicationConstants.UPDATING);
						offenderBookingsT7Service.offenderBookingsT7Trigger(offenderBooking);

						if (insResult > 0) {
							liReturn = 1;
						} else {
							logger.error("update offender bookings insert fail : ");
							throw new RuntimeException("update offender bookings insert fail : ");
						}
					 
				}
			} catch (Exception e) {
				logger.error("Main method : ", e);
				throw new RuntimeException("Main method : ", e);
			}

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
		return oidehlocRepo.sysPflExecuteQuery(searchRecord);

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
	 */
	public List<ReferenceCodes> rgAssignmentReasonRecordGroup() {
		return oidehlocRepo.rgAssignmentReasonRecordGroup();

	}

	@Override
	public VHeaderBlockCommitBean nonAssocationOffendersList(VHeaderBlockCommitBean commitBean) {
		List<BigDecimal> offenderList = new ArrayList<BigDecimal>();

		final boolean blOverrideFlag = oidehlocRepo.checkOverrideLocation(commitBean.getCreateUserId());

		if ((commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0)
				&& commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				for (VHeaderBlock vHeaderBlock : commitBean.getInsertList()) {
					List<String> offenderNonAssDetails = nonAssociationRepository.getOffendersDetailsForLocationExhange(
							vHeaderBlock.getOffenderId(),
							commitBean.getUpdateList().get(0).getLivingUnitId().intValueExact(),
							commitBean.getUpdateList().get(0).getOffenderId());
					String data = "";
					if (offenderNonAssDetails != null && offenderNonAssDetails.size() > 0) {
						for (String str : offenderNonAssDetails) {
							if(str != null) {
							data += str + "\n";
							}
						}
					}
					vHeaderBlock.setNonAssocationData(data.length() > 0 ? data : null);
					vHeaderBlock.setNbtNonAssBProceed(blOverrideFlag);
					
					List<String> offenderGangConflitDetails = nonAssociationRepository.getGangConflitDataForExchange(
							vHeaderBlock.getOffenderBookId(),
							commitBean.getUpdateList().get(0).getLivingUnitId().intValueExact(),
							commitBean.getUpdateList().get(0).getOffenderId());
					String gangData = "";
					if (offenderGangConflitDetails != null && offenderGangConflitDetails.size() > 0) {
						for (String str : offenderGangConflitDetails) 
							gangData += str + "\n";
					}
					vHeaderBlock.setGangConflitData(gangData.length() > 0 ? gangData : null);

				}
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				for (VHeaderBlock vHeaderBlock : commitBean.getUpdateList()) {
					List<String> offenderNonAssDetails = nonAssociationRepository.getOffendersDetailsForLocationExhange(
							vHeaderBlock.getOffenderId(),
							commitBean.getInsertList().get(0).getLivingUnitId().intValueExact(),
							commitBean.getInsertList().get(0).getOffenderId());
					String data = "";
					if (offenderNonAssDetails != null && offenderNonAssDetails.size() > 0) {
						for (String str : offenderNonAssDetails) {
							if(str != null) {
							data += str + "\n";
							}
						}
					}
					vHeaderBlock.setNonAssocationData(data.length() > 0 ? data : null);
					vHeaderBlock.setNbtNonAssBProceed(blOverrideFlag);
					
					List<String> offenderGangConflitDetails = nonAssociationRepository.getGangConflitDataForExchange(
							vHeaderBlock.getOffenderBookId(),
							commitBean.getInsertList().get(0).getLivingUnitId().intValueExact(),
							commitBean.getInsertList().get(0).getOffenderId());
					String gangData = "";
					if (offenderGangConflitDetails != null && offenderGangConflitDetails.size() > 0) {
						for (String str : offenderGangConflitDetails)
							gangData += str + "\n";
					}
					vHeaderBlock.setGangConflitData(gangData.length() > 0 ? gangData : null);
				}
			}
		}
		return commitBean;
	}

}
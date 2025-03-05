package net.syscon.s4.inst.incidentsoic.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderDetail;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.incidentsoic.beans.OicHearingNotices;
import net.syscon.s4.im.incidentsoic.beans.OicHearingNoticesCommitBean;
import net.syscon.s4.im.incidentsoic.beans.OicHearings;
import net.syscon.s4.im.incidentsoic.beans.OicHearingsCommitBean;
import net.syscon.s4.inst.incidentsoic.OcuoichnRepository;
import net.syscon.s4.inst.incidentsoic.OcuoichnService;
import net.syscon.s4.pkgs.non_association.NonAssociationService;

/**
 * Class OcuoichnServiceImpl
 * 
 */
@Service
public class OcuoichnServiceImpl extends BaseBusiness implements OcuoichnService {

	@Autowired
	private OcuoichnRepository ocuoichnRepository;

	@Autowired
	private NonAssociationService nonAssociationService;

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuoichnServiceImpl.class.getName());
	
	/**
	 * Creates new OcuoichnServiceImpl class Object
	 */
	public OcuoichnServiceImpl() {
		
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<Object> oicHearOnCheckDeleteMasteroicHearNotiCur(final OicHearingNotices paramBean) {
		return ocuoichnRepository.oicHearOnCheckDeleteMasteroicHearNotiCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public Object oicHearPreInsertgetEventIdCur(final Dual paramBean) {
		return ocuoichnRepository.oicHearPreInsertgetEventIdCur(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OicHearings> oicHearSearchOicHearings(final OicHearings searchRecord) {
		List<OicHearings> returnList = new ArrayList<>();
		returnList = ocuoichnRepository.oicHearSearchOicHearings(searchRecord);
		for (final OicHearings obj : returnList) {
			if (obj.getInternalLocationId() != null) {
				obj.setInternalLocationIdDes(obj.getInternalLocationId().toString());
			} else {
				obj.setInternalLocationIdDes(null);
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOicHearings
	 *
	 */
	@Transactional
	public Integer oicHearInsertOicHearings(final List<OicHearings> lstOicHearings) {
		for (final OicHearings obj : lstOicHearings) {
			obj.setInternalLocationId(Integer.parseInt(obj.getInternalLocationIdDes()));
		}
		return ocuoichnRepository.oicHearInsertOicHearings(lstOicHearings);
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstOicHearings
	 *
	 */
	@Transactional
	public Integer oicHearUpdateOicHearings(final List<OicHearings> lstOicHearings) {
		return ocuoichnRepository.oicHearUpdateOicHearings(lstOicHearings);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOicHearings
	 *
	 */
	@Transactional
	public Integer oicHearDeleteOicHearings(final List<OicHearings> lstOicHearings) {
		return ocuoichnRepository.oicHearDeleteOicHearings(lstOicHearings);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OicHearingNotices> oicHearNotiSearchOicHearingNotices(final OicHearingNotices searchRecord) {
		List<OicHearingNotices> returnList = new ArrayList<>();
		returnList = ocuoichnRepository.oicHearNotiSearchOicHearingNotices(searchRecord);
		for (final OicHearingNotices obj : returnList) {
			obj.setDeliveryStaffIdDes(obj.getDeliveryStaffId().toString());
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOicHearingNotices
	 *
	 */
	@Transactional
	public Integer oicHearNotiInsertOicHearingNotices(final List<OicHearingNotices> lstOicHearingNotices) {
		return ocuoichnRepository.oicHearNotiInsertOicHearingNotices(lstOicHearingNotices);
	}

	/**
	 * Update the records from database table
	 *
	 * @param lstOicHearingNotices
	 *
	 */
	@Transactional
	public Integer oicHearNotiUpdateOicHearingNotices(final List<OicHearingNotices> lstOicHearingNotices) {
		return ocuoichnRepository.oicHearNotiUpdateOicHearingNotices(lstOicHearingNotices);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOicHearingNotices
	 *
	 */
	@Transactional
	public Integer oicHearNotiDeleteOicHearingNotices(final List<OicHearingNotices> lstOicHearingNotices) {
		return ocuoichnRepository.oicHearNotiDeleteOicHearingNotices(lstOicHearingNotices);
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<StaffMembers> rgAgyIncpStaffIdRecordGroup(final String caseLoadId) {
		List<StaffMembers> returnList = new ArrayList<>();
		returnList = ocuoichnRepository.rgAgyIncpStaffIdRecordGroup(caseLoadId);
		for (final StaffMembers obj : returnList) {
			if (obj.getStaffId() != null) {
				obj.setCode(obj.getStaffId());
			}
		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgHearingTypeRecordGroup() {
		return ocuoichnRepository.rgHearingTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<AgencyInternalLocations> rgInternalLocationsRecordGroup(final String caseloadId) {
		List<AgencyInternalLocations> returnList = new ArrayList<>();
		returnList = ocuoichnRepository.rgInternalLocationsRecordGroup(caseloadId);
		for (final AgencyInternalLocations obj : returnList) {
			obj.setCode(obj.getInternalLocationId().toString());
		}
		return returnList;

	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update in the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@Transactional
	public Integer oicHearCommit(final OicHearingsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OicHearings bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oicHearInsertOicHearings(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OicHearings bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oicHearUpdateOicHearings(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oicHearDeleteOicHearings(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update in the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@Transactional
	public Integer oicHearNotiCommit(final OicHearingNoticesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OicHearingNotices bean : commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = oicHearNotiInsertOicHearingNotices(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OicHearingNotices bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oicHearNotiUpdateOicHearingNotices(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oicHearNotiDeleteOicHearingNotices(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param obj
	 *
	 * @
	 */
	public Integer oicHearCheckScheduleConflict(final OicHearings searchBean) {
		return ocuoichnRepository.oicHearCheckScheduleConflict(searchBean);
	}

	@Override
	public String checkNonAssociations(OicHearingsCommitBean commitBean) {
		logger.info(this.getClass().getName() + commitBean.toString());
		String individualOffenderDetails = "";
		String gangOffenderDetails = "";
		String returnString = "";
		List<OicHearings> oicHearingsList = new ArrayList<OicHearings>();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OicHearings oicHearings : commitBean.getInsertList()) {
				Integer offenderBookid = ocuoichnRepository.getOffenderBookId(oicHearings.getOicIncidentId());
				oicHearings.setOffenderBookId(offenderBookid);
				oicHearingsList.add(oicHearings);
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OicHearings oicHearings : commitBean.getUpdateList()) {
				Integer offenderBookid = ocuoichnRepository.getOffenderBookId(oicHearings.getOicIncidentId());
				oicHearings.setOffenderBookId(offenderBookid);
				oicHearingsList.add(oicHearings);
			}
		}
		if (oicHearingsList != null && oicHearingsList.size() > 0) {
			for (OicHearings oicHearings : oicHearingsList) {
				List<OffenderNaDetails> offenderNaDetailsList = new ArrayList<OffenderNaDetails>();
				try {
					offenderNaDetailsList = ocuoichnRepository.getNonAssocationOffenderList(
							oicHearings.getOffenderBookId(), Integer.parseInt(oicHearings.getInternalLocationIdDes()));

				} catch (Exception e) {
					offenderNaDetailsList = null;
					logger.error(this.getClass().getName() + "error in checkNonAssociations" + e.getMessage());
				}
				if (offenderNaDetailsList != null && offenderNaDetailsList.size() > 0) {
					for (OffenderNaDetails offenderNaDetails : offenderNaDetailsList) {
						Integer oidIncidentId = ocuoichnRepository
								.getIncidentId(Integer.valueOf(offenderNaDetails.getNsOffenderBookId().intValue()));
						if (oidIncidentId != null) {
							List<OicHearings> oicHearingsDetailsList = new ArrayList<>();
							try {
								oicHearingsDetailsList = ocuoichnRepository.getNonAssocationVisitSchedule(oidIncidentId,
										oicHearings);
							} catch (Exception e) {
								oicHearingsDetailsList = null;
								logger.error(
										this.getClass().getName() + "error in checkNonAssociations" + e.getMessage());
							}
							if (oicHearingsDetailsList != null && oicHearingsDetailsList.size() > 0) {
								for (OicHearings oicHearingsDetails : oicHearingsDetailsList) {
									List<OffenderDetail> offenderDetaildata = new ArrayList<OffenderDetail>();
									try {
										offenderDetaildata = ocuoichnRepository
												.getNonAssocationVisitScheduleOffenderDetails(
														oicHearingsDetails.getOicIncidentId());
									} catch (Exception e) {
										offenderDetaildata = null;
										logger.error(this.getClass().getName() + "error in checkNonAssociations"
												+ e.getMessage());
									}
									if (offenderDetaildata != null)
										individualOffenderDetails = individualOffenderDetails
												+ nonAssocationOffenderDetails(offenderDetaildata, oicHearingsDetails);
								}
							}
						}
					}
				}
				List<OffenderStgAffiliations> offenderStgAffiliationsList = new ArrayList<OffenderStgAffiliations>();
				try {
					offenderStgAffiliationsList = nonAssociationService
							.getOffendersOfNonAssociationGroup(BigDecimal.valueOf(oicHearings.getOffenderBookId()));
				} catch (Exception e) {
					offenderStgAffiliationsList = null;
					logger.error(this.getClass().getName() + "error in checkNonAssociations" + e.getMessage());
				}
				if (offenderStgAffiliationsList != null && offenderStgAffiliationsList.size() > 0) {
					for (OffenderStgAffiliations offenderStgAffiliations : offenderStgAffiliationsList) {
						Integer oidIncidentId = ocuoichnRepository
								.getIncidentId(Integer.valueOf(offenderStgAffiliations.getOffenderBookId().intValue()));
						if (oidIncidentId != null) {
							List<OicHearings> oicHearingsDetailsList = new ArrayList<OicHearings>();
							try {
								oicHearingsDetailsList = ocuoichnRepository.getNonAssocationVisitSchedule(oidIncidentId,
										oicHearings);
							} catch (Exception e) {
								logger.error(
										this.getClass().getName() + "error in checkNonAssociations" + e.getMessage());
							}
							if (oicHearingsDetailsList != null && oicHearingsDetailsList.size() > 0) {
								for (OicHearings oicHearingsDetails : oicHearingsDetailsList) {
									List<OffenderDetail> offenderDetaildata = new ArrayList<OffenderDetail>();
									try {
										offenderDetaildata = ocuoichnRepository
												.getNonAssocationVisitScheduleOffenderDetails(
														oicHearingsDetails.getOicIncidentId());
									} catch (Exception e) {
										offenderDetaildata = null;
										logger.error(this.getClass().getName() + "error in checkNonAssociations"
												+ e.getMessage());
									}
									gangOffenderDetails = gangOffenderDetails
											+ nonAssocationOffenderDetails(offenderDetaildata, oicHearingsDetails);
								}
							}
						}
					}
				}

			}

		}
		returnString = (individualOffenderDetails.length() > 0 && gangOffenderDetails.length() > 0)
				? "\n ocuoichn.indinonassocconflict:\n\n" + individualOffenderDetails
						+ "\n\n ocuoichn.gangnonassocconflict:\n\n" + gangOffenderDetails
				: (individualOffenderDetails.length() > 0)
						? " ocuoichn.indinonassocconflict:\n\n" + individualOffenderDetails
						: (gangOffenderDetails.length() > 0)
								? " ocuoichn.gangnonassocconflict:\n\n" + gangOffenderDetails
								: "";
		return (returnString.length() > 0) ? "ocuoichn.hearingSchedule \n\n" + returnString : ApplicationConstants.EMPTYDATA;
	}

	public String nonAssocationOffenderDetails(List<OffenderDetail> offenderDetaildata,
			OicHearings oicHearingsDetails) {
		String result = "";
		if (offenderDetaildata != null && offenderDetaildata.size() > 0) {
			for (OffenderDetail offenderDetail : offenderDetaildata) {
				String offenderName = offenderDetail.getLastName() + " " + offenderDetail.getFirstName();
				String offenderId =Long.toString(offenderDetail.getOffenderId()); 
					while(offenderId.length()<10) {
						offenderId="0"+offenderId;
					}
				java.util.Date hearingDate = oicHearingsDetails.getHearingDate();
				java.util.Date hearingStartDate = oicHearingsDetails.getHearingTime();
				SimpleDateFormat sdf1 = new SimpleDateFormat(("dd/MM/yyyy"));
				SimpleDateFormat sdf2 = new SimpleDateFormat(("HH:mm"));
				String startTime = sdf1 != null ? sdf2.format(hearingStartDate) : "";
				String scheduleDate = sdf1 != null ? sdf1.format(hearingDate) : "";
				result = result + offenderName + "," + offenderId + "," + scheduleDate + "," + startTime + "\n";
			}
		}

		return result;
	}

}
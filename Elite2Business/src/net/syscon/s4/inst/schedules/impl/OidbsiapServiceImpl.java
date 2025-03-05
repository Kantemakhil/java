package net.syscon.s4.inst.schedules.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.OcdprogrRepository;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.institutionalactivities.OciscataRepository;
import net.syscon.s4.inst.schedules.OidbsiapRepository;
import net.syscon.s4.inst.schedules.OidbsiapService;
import net.syscon.s4.inst.schedules.OidsiappRepository;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedulesCommitBean;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules2;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.pkgs.oidbsiap.OidbsiapPkgService;
import net.syscon.s4.pkgs.osinames.OsinamesPkgService;
import net.syscon.s4.triggers.OffenderIndSchedulesT1Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT2Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT3Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT4Service;
import net.syscon.s4.triggers.OffenderIndSchedulesTwfService;


/**
 * Class OidbsiapServiceImpl
 */
@Service
public class OidbsiapServiceImpl extends BaseBusiness implements OidbsiapService {

	@Autowired
	private OidbsiapRepository oidbsiapRepository;

	@Autowired
	private OsinamesPkgService osinamesPkgService;

	@Autowired
	private OidbsiapPkgService oidbsiapPkgService;

	private static Logger logger = LogManager.getLogger(OidbsiapServiceImpl.class.getName());

	@Autowired
	private OffenderIndSchedulesT1Service offenderIndSchedulesT1Service;

	@Autowired
	private OffenderIndSchedulesT2Service offenderIndSchedulesT2Service;

	@Autowired
	private OffenderIndSchedulesTwfService offenderIndSchedulesTwfService;

	@Autowired
	private OffenderIndSchedulesT3Service offenderIndSchedulesT3Service;
	@Autowired
	private OffenderIndSchedulesT4Service offenderIndSchedulesT4Service;

	@Autowired
	private OcdprogrRepository ocdprogrRepository;
	@Autowired
	private NonAssociationService nonAssociationsService;

	private List<OffenderIndSchedules> commitBeanList;

	public static final String EMPTYDATA = "EMPTYDATA";
	@Autowired
	private OidsiappRepository oidsiappRepository; 

	@Autowired
	private NonAssociationService nonAssociationService;
	@Autowired
	private OciscataRepository ociscataRepository;

	/**
	 * Creates new OidbsiapServiceImpl class Object
	 */
	public OidbsiapServiceImpl() {
		// OidbsiapServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<VHeaderBlock> checkNa(final VHeaderBlock paramBean) {
		final List<VHeaderBlock> vHeaderBlockList = oidbsiapRepository.checkNa(paramBean);
		return vHeaderBlockList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<VOffenderAllSchedules2> offSchExecuteQuery(final VOffenderAllSchedules2 searchRecord) {
		final List<VOffenderAllSchedules2> returnList = oidbsiapRepository.offSchExecuteQuery(searchRecord);
		for (final VOffenderAllSchedules2 obj : returnList) {
			final VHeaderBlock beanObj = new VHeaderBlock();
			beanObj.setOffenderBookId(obj.getOffenderBookId());
			beanObj.setCreateuserId(obj.getCreateUserId());
			final Map<String, Object> returnObj = osinamesPkgService
					.getOffDetailsByBookId(beanObj.getOffenderBookId().longValue());
			VHeaderBlock returnObjValue = oidbsiapPkgService.getLivingUnitDesc(beanObj);
			returnObj.forEach((k, v) -> {
				if (k.equals("P_OFFENDER_ID_DISPLAY"))
					obj.setOffIdDisplay(v + "");
				if (k.equals("P_FIRST_NAME"))
					obj.setFirstName(v + "");
				if (k.equals("P_LAST_NAME"))
					obj.setLastName(v + "");
				obj.setFacility(returnObjValue.getLivingUnitDescription());
			});
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_SCH
	 *
	 * @
	 */
	@Transactional(rollbackFor = Exception.class)
	public Integer offSchCommit(final OffenderIndSchedulesCommitBean commitBean) {
		List<OffenderIndSchedules> listObj = new ArrayList<>();
		final VOffenderAllSchedules2 voffBean = new VOffenderAllSchedules2();
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bean -> bean.setCreateUserId(commitBean.getCreateUserId()));
			if (commitBean.getInsertList().size() > 0) {
				for (final OffenderIndSchedules obj : commitBean.getInsertList()) {
					voffBean.setOffenderBookId(BigDecimal.valueOf(obj.getOffenderBookId()));
					voffBean.setEventDate(obj.getEventDate());
					final Integer returnValue = oidbsiapRepository.offSchCheckScheduleConflict(voffBean);
					if (returnValue > 0 && !obj.getConflictFlag()) {
						throw new RuntimeException("Conflict Occured");
					} else {
						listObj = new ArrayList<>();
						listObj.add(obj);
						try {
							listObj = offenderIndSchedulesT1Service.offenderIndSchedulesT1Tgn(listObj);
							listObj = offenderIndSchedulesT2Service.offenderIndSchedulesT2Tgr(listObj);
							offenderIndSchedulesT3Service.getVnumRows(obj);
							offenderIndSchedulesTwfService.offenderIndSchedulesTwfTgr(obj, "INSERTING");
							liReturn = oidbsiapRepository.offSchInsertVOffenderAllSchedules2(listObj);
						} catch (Exception e) {
							logger.error(e);
						}
					}
				}
			} else {
				liReturn = oidbsiapRepository.offSchInsertVOffenderAllSchedules2(listObj);
			}

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bean -> bean.setModifyUserId(commitBean.getCreateUserId()));
			try {
				for (final OffenderIndSchedules offIndSch : commitBean.getUpdateList()) {
					if(ApplicationConstants.YES.equalsIgnoreCase(offIndSch.getCancelFlag())) {
						offIndSch.setEventStatus("CANC");
				
					}else if(ApplicationConstants.NO.equalsIgnoreCase(offIndSch.getCancelFlag())) {
						offIndSch.setEventStatus("SCH");
					}
				}
				listObj = offenderIndSchedulesT2Service.offenderIndSchedulesT2Tgr(listObj);
				listObj = offenderIndSchedulesT1Service.offenderIndSchedulesT1Tgn(listObj);
				commitBean.getUpdateList()
				.forEach(bean -> offenderIndSchedulesTwfService.offenderIndSchedulesTwfTgr(bean, "UPDATING"));
				liReturn = oidbsiapRepository.offSchUpdateVOffenderAllSchedules2(commitBean.getUpdateList());
				commitBean.getUpdateList()
				.forEach(bean -> offenderIndSchedulesT4Service.offenderIndSchedulesT4(null, bean, null));
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<InternalScheduleReasons> rgSchInternalScheduleRecordGroup() {
		return oidbsiapRepository.rgSchInternalScheduleRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<AgencyLocations> rgAgyLocRecordGroup(final String caseloadId) {
		return oidbsiapRepository.rgAgyLocRecordGroup(caseloadId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<AgencyInternalLocations> rgInternalMoveLocationsRecordGroup(final String agyLocId) {
		return oidbsiapRepository.rgInternalMoveLocationsRecordGroup(agyLocId);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param obj
	 *
	 * @
	 */
	public Integer offSchCheckScheduleConflict(final VOffenderAllSchedules2 searchBean) {
		final Integer returnValue = oidbsiapRepository.offSchCheckScheduleConflict(searchBean);
		return returnValue;
	}

	public List<OffenderNaDetails> checkNonAssociationOffendersWithLivingUnit(BigDecimal offenderBookId,
			BigDecimal livingUnitId) {
		List<AgyIntLocProfiles> profileCodeList = new ArrayList<>();
		List<OffenderNaDetails> offenderNaDetailsList = null;
		if (offenderBookId != null && livingUnitId != null) {
			List<AgyIntLocProfiles> agyIntLocProfilesList = oidbsiapRepository
					.getNonAssociationConfigForLocation(livingUnitId);
			if (profileCodeList != null) {
				offenderNaDetailsList = oidbsiapRepository.getNonAssociationOffenders(offenderBookId, agyIntLocProfilesList);
			}
		}
		return offenderNaDetailsList;
	}
	@Override
	public List<String> checkNonAssociations(OffenderIndSchedulesCommitBean commitBean) {
		String returnMessage = "";
		commitBeanList=new ArrayList<>();
		String gangConflictsMessage="";
		List<String> returnList=new ArrayList<>();
		String individualConflictsMessage="";
		if(commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBeanList.addAll(commitBean.getInsertList());
		}
		if(commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBeanList.addAll(commitBean.getUpdateList());
		}
		try {
			if(commitBeanList != null && commitBeanList.size()> 0) {
				for(OffenderIndSchedules vOffenderAllSchedule : commitBeanList) {
					List<OffenderNaDetails>  offenderNaDetailsList = oidbsiapRepository.checkNonAssociationOffendersWithLivingUnit(new BigDecimal(vOffenderAllSchedule.getOffenderBookId()), new BigDecimal(vOffenderAllSchedule.getToInternalLocationId()),vOffenderAllSchedule.getToInternalLocationCode());
					if(offenderNaDetailsList != null && offenderNaDetailsList.size() > 0) {
						for(OffenderNaDetails offenderNaDetails : offenderNaDetailsList) {
							List<OffenderIndSchedules> offenderIndSchedulesList = oidbsiapRepository.getNonAssociationScheduleConflicts(offenderNaDetails.getNsOffenderBookId(), vOffenderAllSchedule.getEventDate(), vOffenderAllSchedule.getToInternalLocationId()!=null?new BigDecimal(vOffenderAllSchedule.getToInternalLocationId()):null);
							if (offenderIndSchedulesList != null && offenderIndSchedulesList.size() > 0) {
								for (OffenderIndSchedules offenderIndSchedule : offenderIndSchedulesList) {
									String message = "";
									VHeaderBlock bean = ocdprogrRepository
											.ocdprogrGetOffenderNames(offenderNaDetails.getNsOffenderBookId(),commitBean.getCreateUserId());
									message = bean.getLastName() + " " + bean.getFirstName() + ", "
											+ bean.getOffenderIdDisplay() + "\n";
									java.util.Date stDate = offenderIndSchedule.getStartTime();
									java.util.Date enDate = offenderIndSchedule.getEndTime();
									java.util.Date apDate = offenderIndSchedule.getEventDate();
									SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
									String startTime = stDate != null ? sdf.format(stDate) : "";
									String endTime = enDate != null ? sdf.format(enDate) : null;
									SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
									String appDate = sdf1 != null ? sdf1.format(apDate) : "";
									message = message + appDate + ", " + startTime + (endTime != null ? ", " + endTime : "")
											+ "\n";
									if(!individualConflictsMessage.contains(message)) {
										individualConflictsMessage = individualConflictsMessage + message + "\n";
									}
								}
							}
						}
					}
					if(individualConflictsMessage != null && !individualConflictsMessage.equalsIgnoreCase("")) {
						if(!individualConflictsMessage.contains("oidbsiap.indinonassocconflict:\n\n")) {
						individualConflictsMessage = "oidbsiap.indinonassocconflict:\n\n" + individualConflictsMessage;
						}
					}

					logger.info(this.getClass().getName() +"getOffendersOfNonAssociationGroup method call");
					List<OffenderStgAffiliations> offenderStgAffiliationsList = nonAssociationsService.getOffendersOfNonAssociationGroup(new BigDecimal(vOffenderAllSchedule.getOffenderBookId()));
					if(offenderStgAffiliationsList != null && offenderStgAffiliationsList.size()>0) {
						for(OffenderStgAffiliations offenderStgAffiliations : offenderStgAffiliationsList) {
							logger.info(this.getClass().getName() +"ocdprogrGetOffenderNames method call");
							List<OffenderIndSchedules> offenderIndSchedulesList = oidsiappRepository.getNonAssociationScheduleConflicts(offenderStgAffiliations.getOffenderBookId(), vOffenderAllSchedule.getEventDate(), new BigDecimal(vOffenderAllSchedule.getToInternalLocationId()));
							if (offenderIndSchedulesList != null && offenderIndSchedulesList.size() > 0) {
								logger.info(this.getClass().getName() +"ocdprogrGetOffenderNames method call");
								VHeaderBlock bean = ocdprogrRepository.ocdprogrGetOffenderNames(offenderStgAffiliations.getOffenderBookId(),commitBean.getCreateUserId());
								String message = "";
								message = bean.getLastName() + " " + bean.getFirstName() + ", " + bean.getOffenderIdDisplay() + "\n";							
								for (OffenderIndSchedules offenderIndSchedule : offenderIndSchedulesList) {
									java.util.Date stDate = offenderIndSchedule.getStartTime();
									java.util.Date enDate = offenderIndSchedule.getEndTime();
									java.util.Date apDate = offenderIndSchedule.getEventDate();
									SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
									String startTime = stDate != null ? sdf.format(stDate) : "";
									String endTime = enDate != null ? sdf.format(enDate) : null;
									SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
									String appDate = sdf1 != null ? sdf1.format(apDate) : "";
									message = message + appDate + ", " + startTime + (endTime != null ? ", " + endTime : "") + "\n";
								}
								gangConflictsMessage = gangConflictsMessage + message + "\n";
							}
						}

					}

					if(gangConflictsMessage != null && !gangConflictsMessage.equalsIgnoreCase("")) {
						if(!gangConflictsMessage.contains("oidbsiap.gangnonassocconflict:\n\n")) {
							gangConflictsMessage = "oidbsiap.gangnonassocconflict:\n\n" + gangConflictsMessage;
						}
					}
					String offenderName=oidbsiapRepository.getOffenderName(vOffenderAllSchedule.getOffenderBookId()!=null?vOffenderAllSchedule.getOffenderBookId().longValue():null);
					if((individualConflictsMessage != null && !individualConflictsMessage.equalsIgnoreCase("")) && (gangConflictsMessage != null && !gangConflictsMessage.equalsIgnoreCase(""))) {
						returnMessage = offenderName+" "+ getFinalMessageString(individualConflictsMessage + "\n" + gangConflictsMessage);
					} else if(individualConflictsMessage != null && !individualConflictsMessage.equalsIgnoreCase("")){
						returnMessage = offenderName+" "+getFinalMessageString(individualConflictsMessage);
					} else if(gangConflictsMessage != null && !gangConflictsMessage.equalsIgnoreCase("")){
						returnMessage = offenderName+" "+getFinalMessageString(gangConflictsMessage);
					} 
					if(!returnMessage.equals("")) {
						returnList.add(returnMessage);
					}
				}
			}



		}catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"checkNonAssociations : ", e);
		}
		if(returnList.isEmpty())
			returnList .add( ApplicationConstants.EMPTYDATA);
		

		return returnList;


	}

	public String getFinalMessageString(final String messageData) {
		return "oidbsiap.nonassociationconflictmsg \n\n"
				+ messageData + " \n oidbsiap.doyouwanttocontinue ";
	}
	@Override
	public List<OffenderNonAssociations> getNsOffenderBookId(OffenderIndSchedulesCommitBean commitBean){
		List<OffenderNonAssociations> nsoffenderbookids=new ArrayList<>();
		try {
			for(OffenderIndSchedules sch: commitBean.getInsertList()){
				nsoffenderbookids= oidbsiapRepository.getNsOffenderBookId(new BigDecimal(sch.getOffenderBookId()));
			}
		}catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() +"getNsOffenderBookId : ", e);
		}
		return nsoffenderbookids;
	}

	@Override
	public List<OffenderIndSchedules> getNonAssociationWarnings(List<OffenderIndSchedules> courseActivitiesList) {
		List<Integer> offenderBookIdList = new ArrayList<Integer>();
		for (OffenderIndSchedules offebderBookId : courseActivitiesList) {
			offenderBookIdList.add(offebderBookId.getOffenderBookId().intValue());
		}
		for (int i = 0; i < courseActivitiesList.size(); i++) {
			List<Offenders> offenderInd = new ArrayList<Offenders>();
			List<Offenders> offenderGang = new ArrayList<Offenders>();
			List<OffenderNaDetails> nonAssList = nonAssociationService
					.getNonAssociationOffenderList(courseActivitiesList.get(i).getOffenderBookId().intValue());
			List<OffenderStgAffiliations> nonAssListGang = nonAssociationService
					.getOffendersOfNonAssociationGroup(courseActivitiesList.get(i).getOffenderBookId()!=null?new BigDecimal(courseActivitiesList.get(i).getOffenderBookId()):null);
			if (nonAssList != null && nonAssList.size() > 0) {
				for (OffenderNaDetails offenderNaDetails : nonAssList) {
					if (offenderBookIdList.contains(offenderNaDetails.getNsOffenderBookId().intValue())) {

						List<Offenders> offender = ociscataRepository
								.getOffenderDetails(offenderNaDetails.getNsOffenderBookId());
						offender.get(0).setOffenderBookId(offenderNaDetails.getNsOffenderBookId().longValue());
						offenderInd.addAll(offender);
					}
				}
			}

			if (nonAssListGang != null && nonAssListGang.size() > 0) {
				for (OffenderStgAffiliations offenderStgAffiliations : nonAssListGang) {
					if (offenderBookIdList.contains(offenderStgAffiliations.getOffenderBookId().intValue())) {
						List<Offenders> offender = ociscataRepository
								.getOffenderDetails(offenderStgAffiliations.getOffenderBookId());
						offender.get(0).setOffenderBookId(offenderStgAffiliations.getOffenderBookId().longValue());
						offenderGang.addAll(offender);
					}

				}
			}
			if (offenderGang != null && offenderGang.size() > 0)
				courseActivitiesList.get(i).setOffenderNonAssociationsByGang(offenderGang);
			else
				courseActivitiesList.get(i).setOffenderNonAssociationsByGang(null);
			if (offenderInd != null && offenderInd.size() > 0)
				courseActivitiesList.get(i).setOffenderNonAssociationsByInd(offenderInd);
			else
				courseActivitiesList.get(i).setOffenderNonAssociationsByInd(null);
		}

		return courseActivitiesList;
	}


	public String getOffenderDetails(List<Offenders> offendersListInd, List<Offenders> offendersListGang) {
		String offenderDetails = "";
		String offenderDetailsInd = "";
		String offenderDetailsGang = "";
		if (offendersListInd != null && offendersListInd.size() > 0) {
			for (Offenders offenders : offendersListInd) {
				offenderDetailsInd = offenderDetailsInd+offenders.getLastName()+" "+offenders.getFirstName() + "," + offenders.getOffenderIdDisplay() + "\n";
			}
		}
		if (offendersListGang != null && offendersListGang.size() > 0) {
			for (Offenders offenders : offendersListGang) {
				offenderDetailsGang = offenderDetailsGang+offenders.getLastName()+" "+offenders.getFirstName() + "," + offenders.getOffenderIdDisplay() + "\n";
			}
		}
		if (offenderDetailsInd.length() > 0 && offenderDetailsGang.length() > 0) {
			offenderDetails = "ociscata.indinonassocconflict:\n\n" + offenderDetailsInd+"\n\n"
					+ "ociscata.gangnonassocconflict:\n\n" + offenderDetailsGang;
		} else if (offenderDetailsInd.length() > 0) {
			offenderDetails = "ociscata.indinonassocconflict:\n\n" + offenderDetailsInd;
		} else if (offenderDetailsGang.length() > 0) {
			offenderDetails = "ociscata.gangnonassocconflict:\n\n" + offenderDetailsGang;
		} 
		if(offenderDetails.length()>0)
			return "ociscata.program \n\n"+ offenderDetails;
		else
			return ApplicationConstants.EMPTYDATA;
	}
}
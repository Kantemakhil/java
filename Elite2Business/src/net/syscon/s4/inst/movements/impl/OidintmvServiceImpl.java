package net.syscon.s4.inst.movements.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.demographicsbiometrics.OmuavbedRepository;
import net.syscon.s4.inst.movements.OidintmvRepository;
import net.syscon.s4.inst.movements.OidintmvService;
import net.syscon.s4.inst.movements.beans.OffenderInterMvmtLocations;
import net.syscon.s4.inst.movements.beans.VIntLocSummaries;
import net.syscon.s4.inst.movements.beans.VOffenderBookings;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.pkgs.tag_establishment.impl.TagEstablishmentServiceImpl;
import net.syscon.s4.triggers.OffenderInterMvmtLocT1Service;
import net.syscon.s4.triggers.VOffenderAllSchedules2TuService;

/**
 * Class OidintmvServiceImpl
 */
@Service
public class OidintmvServiceImpl extends BaseBusiness implements OidintmvService {
	private List<VOffenderAllSchedules> tempdata;
	@Autowired
	private OidintmvRepository oidintmvRepository;
	@Autowired
	private TagEstablishmentServiceImpl tagEstablishmentServiceImpl;
	@Autowired
	private NonAssociationService nonAssociationService;
	@Autowired
	private VOffenderAllSchedules2TuService vOffenderAllSchedules2TuService;
	@Autowired
	private OffenderInterMvmtLocT1Service offenderInterMvmtLocT1Service;
	@Autowired
	private OmuavbedRepository omuavbedRepository;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidintmvServiceImpl.class.getName());

	/**
	 * Creates new OidintmvServiceImpl class Object
	 */
	public OidintmvServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<SystemProfiles> GetProfileValue(final SystemProfiles paramBean) {
		final List<SystemProfiles> systemProfilesList = new ArrayList<>();
		return systemProfilesList;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public VHeaderBlock getOffenderDetails(final VHeaderBlock paramBean) {
		VHeaderBlock agencyInternalLocations = new VHeaderBlock();
		agencyInternalLocations = oidintmvRepository.getOffenderDetails(paramBean);
		if (agencyInternalLocations.getAgencyImlId() != null) {
			agencyInternalLocations.setLivingUnitDescription(
					oidintmvRepository.getInternalLocDesc(agencyInternalLocations.getAgencyImlId().toString()));
		}
		return agencyInternalLocations;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<VOffenderAllSchedules> offBlkExecuteQuery(final VOffenderAllSchedules searchRecord) {
		List<VOffenderAllSchedules> returnList = new ArrayList<VOffenderAllSchedules>();
		List<VOffenderBookings> unSchList = new ArrayList<VOffenderBookings>();
		VOffenderAllSchedules bean;
		if (searchRecord.getEventType().equals("SCHEDULED")) {
			returnList = oidintmvRepository.getSchedules(searchRecord);

			for (final VOffenderAllSchedules object : returnList) {
				if (object.getLivingUnitId() != null) {
					object.setLivingUnitDesc(
							oidintmvRepository.getHousingLocationDesc(object.getLivingUnitId().toString()));
				}
			}
			
			if (returnList != null && returnList.size() > 0) {
				for (VOffenderAllSchedules courtDetails : returnList) {
					if (courtDetails.getEventType().equals("CRT")) {
						ReferenceCodes referenceCodes = new ReferenceCodes();
						referenceCodes = oidintmvRepository.courtEventsLocation(searchRecord.getAgyLocId(),
								courtDetails.getAppearanceLocation());
						if (referenceCodes != null) {
							courtDetails.setToIntLocUserDesc(referenceCodes.getDescription());
							courtDetails.setToInternalLocationId(BigDecimal.valueOf(referenceCodes.getStaffId()));
						}
					}
				}
			}

		} else {
			unSchList = oidintmvRepository.getUnSchedules(searchRecord);
			for (final VOffenderBookings object : unSchList) {
				bean = new VOffenderAllSchedules();
				bean.setOffenderIdDisplay(object.getOffenderIdDisplay());
				bean.setOffenderLastName(object.getLastName());
				bean.setOffenderFirstName(object.getFirstName());
				bean.setOffenderId(object.getOffenderId());
				bean.setOffenderBookId(object.getOffenderBookId());
				bean.setLivingUnitId(object.getLivingUnitId());
				bean.setLivingUnitDesc(object.getLivingUnitDesc());
				bean.setAgyLocDesc(object.getAgencyImlDesc());
				returnList.add(bean);
			}
		}
		if (returnList != null && returnList.size() > 0) {
			Integer rowId = 0;
			for (VOffenderAllSchedules data : returnList) {
				data.setRowId(++rowId);
			}
			tempdata = returnList;
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_BLK
	 * 
	 */
	@Transactional
	public List<VOffenderAllSchedules> offBlkCommit(final VOffenderAllSchedulesCommitBean commitBean) {
		BigDecimal eventId = null;
		int liReturn = 0;
		String conflict;
		Boolean lvNaConf;
		Boolean lvStgConf;
		String lvRealTimeConf;
		String offenderIdDisplay = null;
		String firstname = null;
		String lastName = null;

		final List<OffenderInterMvmtLocations> offenderInterList = new ArrayList<>();
		final List<VOffenderAllSchedules> returnList = new ArrayList<>();
		OffenderInterMvmtLocations offIntMVMTBean = new OffenderInterMvmtLocations();
		final VOffenderAllSchedules returnBean = new VOffenderAllSchedules();
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final VOffenderAllSchedules insertBean : commitBean.getInsertList()) {
				commitBean.getUpdateList().add(insertBean);
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final VOffenderAllSchedules pSchTab : commitBean.getUpdateList()) {
				if ("Y".equals(pSchTab.getConfirmMove())) {
					offIntMVMTBean = new OffenderInterMvmtLocations();
					offIntMVMTBean.setAgencyLocationId((pSchTab.getAgyLocId() == null) ? null : pSchTab.getAgyLocId());
					offIntMVMTBean.setEventId((pSchTab.getEventId() == null) ? null : pSchTab.getEventId());
					offIntMVMTBean.setOffenderId(pSchTab.getOffenderId());
					offIntMVMTBean.setOffenderBookId(pSchTab.getOffenderBookId());
					offIntMVMTBean.setEventDate(pSchTab.getEventDate());
					offIntMVMTBean.setMovementDate(pSchTab.getEventDate());
					offIntMVMTBean.setReferenceId(pSchTab.getReferenceId());
					offIntMVMTBean.setMovementTime(pSchTab.getScheduleMovementTime());
					offIntMVMTBean.setOffenderIdDisplay(pSchTab.getOffenderIdDisplay());
					offIntMVMTBean.setToInternalLocationIdOne(pSchTab.getToInternalLocationIdOne());
					offIntMVMTBean.setOffenderIdOne(pSchTab.getOffenderIdOne());
					offIntMVMTBean.setOffenderBookIdOne(pSchTab.getOffenderBookIdOne());
					offIntMVMTBean.setInternalLocationId(pSchTab.getToInternalLocationId());
					offIntMVMTBean.setCreateUserId(commitBean.getCreateUserId());
					offIntMVMTBean.setModifyUserId(commitBean.getCreateUserId());
					offIntMVMTBean.setAgencyImlId(
							(pSchTab.getToInternalLocationId() == null) ? null : pSchTab.getToInternalLocationId());
					offIntMVMTBean.setMovementType((pSchTab.getEventType() == null) ? null : pSchTab.getEventType());
					offIntMVMTBean.setMovementReasonCode(
							(pSchTab.getEventSubType() == null) ? null : pSchTab.getEventSubType());
					if (pSchTab.getEventId() == null && "SCH".equals(pSchTab.getEventStatus())) {
						eventId = oidintmvRepository.getEventIdCur(pSchTab);
						offIntMVMTBean.setOffenderImlId(BigDecimal.valueOf(oidintmvRepository.getNxtOffenderIml()));
					} else {
						offIntMVMTBean.setOffenderImlId(BigDecimal.valueOf(oidintmvRepository.getNxtOffenderIml()));
					}
					offenderInterList.add(offIntMVMTBean);
				}
			}
			for (OffenderInterMvmtLocations offenderInterMvmtLocations : offenderInterList) {
				String nsType = oidintmvRepository.getNsType(offenderInterMvmtLocations.getOffenderId(),
						offenderInterMvmtLocations.getOffenderIdOne());
				if (nsType != null && nsType != "") {
					Boolean restrictLocation = checkRestrictLocation(offenderInterMvmtLocations.getInternalLocationId(),
							nsType, "N", String.valueOf(offenderInterMvmtLocations.getInternalLocationId()));
					Boolean restrictLocation2 = checkRestrictLocation(
							offenderInterMvmtLocations.getToInternalLocationIdOne(), nsType, "N",
							String.valueOf(offenderInterMvmtLocations.getToInternalLocationIdOne()));
					if (restrictLocation && restrictLocation2
							&& (String.valueOf(offenderInterMvmtLocations.getInternalLocationId())
									.equals(String.valueOf(offenderInterMvmtLocations.getToInternalLocationIdOne())))) {
						lvNaConf = true;
					} else {
						lvNaConf = false;
					}
				} else {
					lvNaConf = false;
				}
				String stgType = oidintmvRepository.getStgType(offenderInterMvmtLocations.getOffenderBookId(),
						offenderInterMvmtLocations.getOffenderBookIdOne());
				if (stgType != null && stgType != "") {
					Boolean checkRestrictParentLocation = checkRestrictLocation(
							offenderInterMvmtLocations.getInternalLocationId(), "STG", "Y",
							String.valueOf(offenderInterMvmtLocations.getInternalLocationId()));
					Boolean checkRestrictParentLocation1 = checkRestrictLocation(
							offenderInterMvmtLocations.getToInternalLocationIdOne(), "STG", "Y",
							String.valueOf(offenderInterMvmtLocations.getToInternalLocationIdOne()));
					if (checkRestrictParentLocation && checkRestrictParentLocation1
							&& (String.valueOf(offenderInterMvmtLocations.getInternalLocationId())
									.equals(String.valueOf(offenderInterMvmtLocations.getToInternalLocationIdOne())))) {
						lvStgConf = true;
					} else {
						lvStgConf = false;
					}
				} else {
					lvStgConf = false;
				}

				if (!lvNaConf && !lvStgConf) {
					lvRealTimeConf = "N";
				} else {
					lvRealTimeConf = "Y";
					List<Offenders> returnListTwo = oidintmvRepository
							.getOffDetails(offenderInterMvmtLocations.getOffenderId());
					if (returnListTwo.size() > 0) {
						for (final Offenders offenders : returnListTwo) {
							offenderIdDisplay = offenders.getOffenderIdDisplay();
							firstname = offenders.getFirstName();
							lastName = offenders.getLastName();

						}
					}
					List<Offenders> returnListOne = oidintmvRepository
							.getOffDetails(offenderInterMvmtLocations.getOffenderIdOne());
					if (returnListOne.size() > 0) {
						for (final Offenders offenders : returnListOne) {
							offenderInterMvmtLocations.setOffenderIdDisplay(offenders.getOffenderIdDisplay());
							offenderInterMvmtLocations.setFirstName(offenders.getFirstName());
							offenderInterMvmtLocations.setLastName(offenders.getLastName());
						}
					}
				}

				if ("Y".equals(lvRealTimeConf)) {
					returnBean.setWarningMsg("A non-association linkage exists between " + lastName + "," + firstname
							+ "," + offenderIdDisplay + "," + offenderInterMvmtLocations.getLastName() + ","
							+ offenderInterMvmtLocations.getFirstName() + " "
							+ offenderInterMvmtLocations.getOffenderIdDisplay());
					returnBean.setWarningMsg(returnBean.getWarningMsg() + " in this location.");
					returnBean.setWarningPrompt(
							"Before proceeding with location change investigate possible risk. Only proceed if you are satisfied with the risk. Do you wish to proceed ?");

					returnList.add(returnBean);
					return returnList;
				}
			}

		}

		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final VOffenderAllSchedules pSchTab : commitBean.getUpdateList()) {
				OffenderIndSchedules objOffIndSch = new OffenderIndSchedules();
				objOffIndSch.setAgyLocId(pSchTab.getAgyLocId());
				objOffIndSch.setEscortCode(pSchTab.getEscortCode());
				objOffIndSch.setEventStatus(pSchTab.getEventStatus());
				pSchTab.setModifyUserId(commitBean.getCreateUserId());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date dateWithoutTime = null;
				try {
					dateWithoutTime = sdf.parse(sdf.format(pSchTab.getEventDate()));
				} catch (ParseException e) {
					logger.error("Exception :", e);
				}
				objOffIndSch.setEventDate(dateWithoutTime);
				objOffIndSch.setToAgyLocId(pSchTab.getToAgyLocId());
				objOffIndSch.setEventSubType(pSchTab.getEventSubType());
				objOffIndSch.setStartTime(pSchTab.getStartTime());
				objOffIndSch.setCommentText(pSchTab.getCommentText());
				objOffIndSch.setHiddenCommentText(pSchTab.getHiddenCommentText());
				objOffIndSch.setModifyUserId(commitBean.getCreateUserId());
				objOffIndSch.setOffenderBookId((pSchTab.getOffenderBookId().intValue()));
				objOffIndSch.setEventClass("INT_MOV");
				objOffIndSch.setEventType(pSchTab.getEventType());
				objOffIndSch.setCreateUserId(commitBean.getCreateUserId());
				objOffIndSch.setEventId(pSchTab.getEventId()!=null? pSchTab.getEventId().intValue():null);
				objOffIndSch.setEventOutcome(pSchTab.getEventOutcome());
				objOffIndSch.setCancelFlag(pSchTab.getCancelFlag());
				objOffIndSch.setOffPrgrefId(pSchTab.getOffPrgrefId() !=null? pSchTab.getOffPrgrefId().intValue():null);
				objOffIndSch.setReferenceId(pSchTab.getReferenceId() !=null?pSchTab.getReferenceId().intValue():null);
				objOffIndSch.setToInternalLocationId(pSchTab.getToInternalLocationId() !=null?pSchTab.getToInternalLocationId().intValue():null);
				if ("Y".equals(pSchTab.getConfirmMove())) {
					chkNaIntLocConflict(pSchTab);
					chkNaStgLocationCount(pSchTab);
					if ("N".equals(pSchTab.getLvNaFlag()) && "N".equals(pSchTab.getLvNaFlagOne())) {
						conflict = "N";
					} else {
						conflict = "Y";
					}
					if (conflict.equals("N")) {
						offIntMVMTBean = new OffenderInterMvmtLocations();
						offIntMVMTBean.setOffenderBookId(pSchTab.getOffenderBookId());
						offIntMVMTBean.setEventId((pSchTab.getEventId() == null) ? null : pSchTab.getEventId());
						offIntMVMTBean
								.setAgencyLocationId((pSchTab.getAgyLocId() == null) ? null : pSchTab.getAgyLocId());
						offIntMVMTBean.setAgencyImlId(
								(pSchTab.getToInternalLocationId() == null) ? null : pSchTab.getToInternalLocationId());
						offIntMVMTBean.setEventDate(pSchTab.getEventDate());
						offIntMVMTBean.setMovementDate(pSchTab.getEventDate());
						offIntMVMTBean.setReferenceId(pSchTab.getReferenceId());
						offIntMVMTBean.setMovementTime(pSchTab.getScheduleMovementTime());
						offIntMVMTBean.setFromAgencyImlId(
								(pSchTab.getAgencyImlId() == null) ? null : pSchTab.getAgencyImlId());
						offIntMVMTBean
								.setMovementType((pSchTab.getEventType() == null) ? null : pSchTab.getEventType());
						offIntMVMTBean.setMovementReasonCode(
								(pSchTab.getEventSubType() == null) ? null : pSchTab.getEventSubType());
						offIntMVMTBean.setCreateUserId(commitBean.getCreateUserId());
						if (pSchTab.getEventId() == null && "SCH".equals(pSchTab.getEventStatus())) {
							eventId = oidintmvRepository.getEventIdCur(pSchTab);
							if (eventId != null) {
								offIntMVMTBean.setEventId(eventId);
							}
						}
						if (pSchTab.getEventId() == null && "SCH".equals(pSchTab.getEventStatus())) {
							eventId = oidintmvRepository.getEventIdCur(pSchTab);
							offIntMVMTBean.setOffenderImlId(BigDecimal.valueOf(oidintmvRepository.getNxtOffenderIml()));
						} else {
							offIntMVMTBean.setOffenderImlId(BigDecimal.valueOf(oidintmvRepository.getNxtOffenderIml()));
						}
						offenderInterList.clear();
						offenderInterList.add(offIntMVMTBean);
					} else {
						returnBean.setWarningMsg("A non-association linkage exists between " + pSchTab.getLastName()
								+ "," + pSchTab.getFirstName() + " " + pSchTab.getOffenderIdDisplay());
						returnBean.setWarningMsg(returnBean.getWarningMsg() + " in this location.");
						returnBean.setWarningPrompt("You do not have authorization to overridemovement");
						returnList.add(returnBean);
						return returnList;
					}
					Integer rowId = pSchTab.getRowId();
					OffenderIndSchedules oldObj = null;
					if (tempdata != null) {
						for (VOffenderAllSchedules data : tempdata) {
							if (rowId == data.getRowId()) {
								oldObj = new OffenderIndSchedules();
								oldObj.setAgyLocId(data.getAgyLocId());
								oldObj.setEscortCode(data.getEscortCode());
								oldObj.setEventSubType(data.getEventSubType());
								oldObj.setEventStatus(data.getEventStatus());
								oldObj.setEventDate(data.getEventDate());
								oldObj.setToAgyLocId(data.getToAgyLocId());
								oldObj.setStartTime(data.getStartTime());
								oldObj.setCommentText(data.getCommentText());
								oldObj.setHiddenCommentText(data.getHiddenCommentText());
								oldObj.setOffenderBookId(data.getOffenderBookId().intValue());
								oldObj.setEventClass("INT_MOV");
								oldObj.setEventType(data.getEventType());
								oldObj.setReferenceId(data.getReferenceId()!=null?data.getReferenceId().intValue():null);
								oldObj.setOffPrgrefId(data.getOffPrgrefId()!=null?data.getOffPrgrefId().intValue():null);
								 data.getRecordSource();
								if (data.getEventId() != null) {
									final String strEventId = String.valueOf(data.getEventId());
									oldObj.setEventId(Integer.parseInt(strEventId));
								}
								if (data.getReferenceId() != null) {
									oldObj.setReferenceId(data.getReferenceId().intValue());
								}
								if (data.getOffenderBookId() != null) {
									oldObj.setOffenderBookId(data.getOffenderBookId().intValue());
								}
								break;
							}
						}
					}

					if (pSchTab.getEventId() == null && "SCH".equals(pSchTab.getEventStatus())) {
						 try {
							 objOffIndSch.setEventStatus("COMP");
							 if(objOffIndSch.getCancelFlag().equals(ApplicationConstants.YES)) {
									objOffIndSch.setEventStatus("CANC"); 
							 }
						liReturn=vOffenderAllSchedules2TuService.vOffenderAllSchedules2TuTrigger(objOffIndSch,oldObj, pSchTab.getRecordSource(), null);
						} catch (Exception e) {
							logger.error("");
						}
						returnBean.setReturnValue(liReturn);
					} else {
                      if(pSchTab.getEventId() != null) {
						try {
				objOffIndSch.setEventStatus("COMP");	
				if(objOffIndSch.getCancelFlag().equals(ApplicationConstants.YES)) {
					objOffIndSch.setEventStatus("CANC");
					
				}
		 liReturn=vOffenderAllSchedules2TuService.vOffenderAllSchedules2TuTrigger(objOffIndSch,oldObj, pSchTab.getRecordSource(),null);
						} catch (Exception e) {
							logger.error(e);
						}

						returnBean.setReturnValue(liReturn);
					}
					}
					liReturn = oidintmvRepository.offBlkInsertOidintmvGetSchedules(offenderInterList);
					returnBean.setReturnValue(liReturn);
					offenderInterMvmtLocT1Service.offenderInterMvmtLocT1Tgr(offenderInterList);

				}
			}
		}
		returnList.add(returnBean);
		return returnList;
	}

	private VOffenderAllSchedules chkNaIntLocConflict(VOffenderAllSchedules pSchTab) {
		List<BigDecimal> parentLocationCurserList = oidintmvRepository
				.getparentLocationSchedList(pSchTab.getToInternalLocationId(), "N");
		List<OffenderInterMvmtLocations> offenderInterList = new ArrayList<>();
		List<OffenderInterMvmtLocations> profileCodeIntLocList = new ArrayList<>();
		Boolean lvNaFlag = false;
		List<Offenders> returnList = new ArrayList<>();
		OffenderInterMvmtLocations object = new OffenderInterMvmtLocations();
		for (BigDecimal bigDecimal : parentLocationCurserList) {
			offenderInterList = oidintmvRepository.getParentLocScheduleCusrerList(bigDecimal, "N");
			for (OffenderInterMvmtLocations offenderInterMvmtLocations : offenderInterList) {
				object.setIntLocProfileCode(offenderInterMvmtLocations.getIntLocProfileCode());
				object.setInternalLocationId(offenderInterMvmtLocations.getInternalLocationId());
				profileCodeIntLocList.add(object);
			}
		}
		for (OffenderInterMvmtLocations objectOne : profileCodeIntLocList) {
			returnList = oidintmvRepository.getOffDetailsNaIntLocList(objectOne.getInternalLocationId(),
					objectOne.getIntLocProfileCode(), pSchTab.getOffenderId());
			if (returnList.size() > 0) {
				pSchTab.setOffenderIdDisplay(returnList.get(0).getOffenderIdDisplay());
				pSchTab.setFirstName(returnList.get(0).getFirstName());
				pSchTab.setLastName(returnList.get(0).getLastName());
			}
		}
		if (returnList.size() > 0) {
			lvNaFlag = true;
		}
		if (lvNaFlag) {
			pSchTab.setLvNaFlag("Y");
			return pSchTab;
		} else {
			pSchTab.setLvNaFlag("N");
			return pSchTab;
		}

	}

	private VOffenderAllSchedules chkNaStgLocationCount(VOffenderAllSchedules pSchTab) {
		List<BigDecimal> parentLocationCurserList = oidintmvRepository
				.getparentLocationSchedList(pSchTab.getToInternalLocationId(), "Y");
		List<OffenderInterMvmtLocations> offenderInterList = new ArrayList<>();
		List<OffenderInterMvmtLocations> profileCodeIntLocList = new ArrayList<>();
		Boolean lvNaFlagOne = false;
		List<Offenders> returnListOne = new ArrayList<>();
		OffenderInterMvmtLocations object = new OffenderInterMvmtLocations();
		for (BigDecimal bigDecimal : parentLocationCurserList) {
			offenderInterList = oidintmvRepository.getParentLocScheduleCusrerList(bigDecimal, "Y");
			for (OffenderInterMvmtLocations offenderInterMvmtLocations : offenderInterList) {
				object.setIntLocProfileCode(offenderInterMvmtLocations.getIntLocProfileCode());
				object.setInternalLocationId(offenderInterMvmtLocations.getInternalLocationId());
				offenderInterMvmtLocations.setModifyUserId(pSchTab.getModifyUserId());
				profileCodeIntLocList.add(object);
			}
		}
		for (OffenderInterMvmtLocations objectOne : profileCodeIntLocList) {
			returnListOne = oidintmvRepository.getOffDetailsNaIntLocList(objectOne.getInternalLocationId(), "STG",
					pSchTab.getOffenderBookId());
			if (returnListOne.size() > 0) {
				pSchTab.setOffenderIdDisplayStg(returnListOne.get(0).getOffenderIdDisplay());
				pSchTab.setFirstNameStg(returnListOne.get(0).getFirstName());
				pSchTab.setLastNameStg(returnListOne.get(0).getLastName());
			}
		}
		if (returnListOne.size() > 0) {
			lvNaFlagOne = true;
		}
		if (lvNaFlagOne) {
			pSchTab.setLvNaFlagOne("Y");
			return pSchTab;
		} else {
			pSchTab.setLvNaFlagOne("N");
			return pSchTab;
		}
	}

	public Boolean checkRestrictLocation(BigDecimal valueOf, String nsType, String string, String valueOf2) {
		BigDecimal lvParentLocationId = null;
		List<BigDecimal> parentLocationCurserList = oidintmvRepository.getparentLocationCurserList(valueOf);
		for (BigDecimal boolean1 : parentLocationCurserList) {
			List<OffenderInterMvmtLocations> offenderInterList = new ArrayList<>();
			offenderInterList = oidintmvRepository.getRestricationCusrerList(boolean1, string);
			for (OffenderInterMvmtLocations offenderInterMvmtLocations : offenderInterList) {
				if (offenderInterMvmtLocations.getIntLocProfileCode().equals(nsType)) {
					lvParentLocationId = offenderInterMvmtLocations.getInternalLocationId();
				}

			}
		}
		if (lvParentLocationId != null) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * This method is used to execute a record group
	 * 
	 */
	public List<AgencyLocations> rgEstablishmentRecordGroup(final String caseLoadId) {
		return oidintmvRepository.rgEstablishmentRecordGroup(caseLoadId);

	}

	/**
	 * This method is used to execute a record group
	 * 
	 */
	public List<LivingUnits> rgFromHlocLevelOneRecordGroup(final String agyLocId) {
		List<LivingUnits> livingUnitList = new ArrayList<>();
		livingUnitList = oidintmvRepository.rgFromHlocLevelOneRecordGroup(agyLocId);
		for (LivingUnits bean : livingUnitList) {
			if (bean.getLivingUnitId() != null) {
				bean.setCode(bean.getLivingUnitId().toString());
			}
		}
		return filterLovOnActiveFlag(livingUnitList);
	}

	/**
	 * This method is used to execute a record group
	 * 
	 */
	public List<LivingUnits> rgFromHlocLevelTwoRecordGroup(final String agyLocId, final String fromLocLevelOne) {
		List<LivingUnits> livingUnitList = new ArrayList<>();
		livingUnitList = oidintmvRepository.rgFromHlocLevelTwoRecordGroup(agyLocId, fromLocLevelOne);
		for (final LivingUnits bean : livingUnitList) {
			if (bean.getLivingUnitId() != null) {
				bean.setCode(bean.getLivingUnitId().toString());
			}
		}
		return filterLovOnActiveFlag(livingUnitList);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<LivingUnits> rgFromHlocLevelThreeRecordGroup(final String agyLocId, final String fromLocLevelTwo) {
		List<LivingUnits> livingUnitList = new ArrayList<>();
		livingUnitList = oidintmvRepository.rgFromHlocLevelThreeRecordGroup(agyLocId, fromLocLevelTwo);
		for (final LivingUnits bean : livingUnitList) {
			if (bean.getLivingUnitId() != null) {
				bean.setCode(bean.getLivingUnitId().toString());
			}
		}
		return filterLovOnActiveFlag(livingUnitList);
	}

	private List<LivingUnits> filterLovOnActiveFlag(List<LivingUnits> livingUnitList) {
		if(Optional.ofNullable(livingUnitList).isPresent()) {
			livingUnitList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return livingUnitList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<VIntLocSummaries> rgFromIlocLevelOneRecordGroup(final String agyLocId) {
		 List<VIntLocSummaries> returnList = oidintmvRepository.rgFromIlocLevelOneRecordGroup(agyLocId);
		return filterLocSummariesOnActiveFlag(returnList);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<VIntLocSummaries> rgFromIlocLevelTwoRecordGroup(final String agyLocId,
			final String fromILocLevelOneId) {
		List<VIntLocSummaries> returnList = oidintmvRepository.rgFromIlocLevelTwoRecordGroup(agyLocId, fromILocLevelOneId);
		return filterLocSummariesOnActiveFlag(returnList);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<VIntLocSummaries> rgFromIlocLevelThreeRecordGroup(final String agyLocId,
			final String fromILocLevelTwoId) {
		List<VIntLocSummaries> returnList = oidintmvRepository.rgFromIlocLevelThreeRecordGroup(agyLocId, fromILocLevelTwoId);
		return filterLocSummariesOnActiveFlag(returnList);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<VIntLocSummaries> rgToIlocLevelOneRecordGroup(final String agyLocId, final String fromILocLevelOneId,
			final String fromHLocLevelOne) {
		List<VIntLocSummaries> returnList =  oidintmvRepository.rgToIlocLevelOneRecordGroup(agyLocId, fromILocLevelOneId, fromHLocLevelOne);
		return filterLocSummariesOnActiveFlag(returnList);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<VIntLocSummaries> rgToIlocLevelTwoRecordGroup(final String agyLocId, final String toILocLevelOneId) {
		List<VIntLocSummaries> returnList =  oidintmvRepository.rgToIlocLevelTwoRecordGroup(agyLocId, toILocLevelOneId);
		return filterLocSummariesOnActiveFlag(returnList);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<VIntLocSummaries> rgToIlocLevelThreeRecordGroup(final String agyLocId, final String toILocLevelTwoId) {
		List<VIntLocSummaries> returnList = oidintmvRepository.rgToIlocLevelThreeRecordGroup(agyLocId, toILocLevelTwoId);
		return filterLocSummariesOnActiveFlag(returnList);
	}

	private List<VIntLocSummaries> filterLocSummariesOnActiveFlag(List<VIntLocSummaries> returnList) {
		if(Optional.ofNullable(returnList).isPresent()) {
			returnList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgSchTypeRecordGroup() {
		return oidintmvRepository.rgSchTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgSchReasonRecordGroup() {
		return oidintmvRepository.rgSchReasonRecordGroup();

	}

	public LivingUnits getLabels(final String agyLocId) {
		LivingUnits obj = new LivingUnits();
		AgencyLocations data = tagEstablishmentServiceImpl.getHousingLabels(agyLocId);

		try {
			BeanUtils.copyProperties(obj, data);
			if (data.getHousingLev1Code() != null) {
				obj.setLevel1Code(omuavbedRepository.getLabelDescription(data.getHousingLev1Code()));
			}
			if (data.getHousingLev2Code() != null) {
				obj.setLevel2Code(omuavbedRepository.getLabelDescription(data.getHousingLev2Code()));
			}
			if (data.getHousingLev3Code() != null) {
				obj.setLevel3Code(omuavbedRepository.getLabelDescription(data.getHousingLev3Code()));
			}
			if (data.getHousingLev4Code() != null) {
				obj.setLevel4Code(omuavbedRepository.getLabelDescription(data.getHousingLev4Code()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Integer isOffenderExists(final String offIdDisplay) {
		return nonAssociationService.isOffenderExists(offIdDisplay);
	}

	@Override
	public List<ReferenceCodes> rgMovmentTypeRecordGroup() {
		List<ReferenceCodes> list = new ArrayList<>();
		ReferenceCodes obj = new ReferenceCodes();
		obj.setCode("SCHEDULED");
		obj.setDescription("Scheduled");
		list.add(obj);
		ReferenceCodes object = new ReferenceCodes();
		object.setCode("UNSCHEDULED");
		object.setDescription("Unscheduled");
		list.add(object);
		return list;
	}

}

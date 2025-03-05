package net.syscon.s4.inst.schedules.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.OcdprogrRepository;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.inst.schedules.OidstwjuRepository;
import net.syscon.s4.inst.schedules.OidstwjuService;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchWaitLists;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchWaitListsCommitBean;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules2;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.pkgs.oidstwju.OidstwjuPkgService;
import net.syscon.s4.pkgs.oms_utils.OmsUtilsService;
import net.syscon.s4.triggers.OffenderIndSchedulesT1Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT2Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT3Service;
import net.syscon.s4.triggers.OffenderIndSchedulesTwfService;
import net.syscon.s4.triggers.VOffenderAllSchedules2TdService;
import net.syscon.s4.triggers.VOffenderAllSchedules2TuService;

/**
 * Class OidstwjuServiceImpl
 */
@Service
public class OidstwjuServiceImpl extends BaseBusiness implements OidstwjuService {
	
	private static Logger logger = LogManager.getLogger(OidstwjuServiceImpl.class.getName());
	
	private  List<VOffenderAllSchedules> offSchTemp;
	@Autowired
	private OidstwjuRepository oidstwjuRepo;

	@Autowired
	private EliteDateService dateService;
	
	@Autowired
	private  OffenderIndSchedulesTwfService  offenderIndSchedulesTwfService;
	
	@Autowired
	private OffenderIndSchedulesT2Service offenderIndSchedulesT2Service; 
	
	@Autowired
	private OffenderIndSchedulesT3Service offenderIndSchedulesT3Service; 
	
	@Autowired
	private OffenderIndSchedulesT1Service offenderIndSchedulesT1Service;
	
	@Autowired
	private VOffenderAllSchedules2TuService vOffenderAllSchedules2TuService;
	
	@Autowired
	private VOffenderAllSchedules2TdService vOffenderAllSchedules2TdService;
	
	@Autowired
	private OidstwjuPkgService oidstwjuPkgService;
	
	@Autowired
	private OmsUtilsService omsUtilsService;
	
	@Autowired
	private OcdprogrRepository ocdprogrRepository;
	
	@Autowired
	private NonAssociationService nonAssociationService;
 
	/**
	 * Creates new OidstwjuServiceImpl class Object
	 */
	public OidstwjuServiceImpl() {
		// OidstwjuServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 *
	 */
	public List<OffenderIndSchedules> offBkgOnCheckDeleteMaster(final OffenderIndSchedules paramBean) {
		return oidstwjuRepo.offBkgOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 *
	 */
	public List<Object> offSchOnCheckDeleteMaster(final String eventId) {
		return oidstwjuRepo.offSchOnCheckDeleteMaster(eventId);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 *
	 */
	public StaffMembers showApprovedDetails(final StaffMembers paramBean) {
		return oidstwjuRepo.showApprovedDetails(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 *
	 */
	public ReferenceCodes getParentCode(final ReferenceCodes paramBean) {
		return oidstwjuRepo.getParentCode(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VOffenderAllSchedules> offSchExecuteQuery(final VOffenderAllSchedules searchRecord) {
		List<VOffenderAllSchedules> list=null;
		Integer rowId=0;
		list= oidstwjuRepo.offSchExecuteQuery(searchRecord);
		logger.info(logger.getName().getClass()+" offSchExecuteQuery");
		if(list != null && list.size()>0) {
			for(VOffenderAllSchedules data:list) {
				data.setRowId(++rowId);
			}
			offSchTemp=list;
		}
		return list;

	}

	@Transactional
	public Integer chkNonAssociation(final VOffenderAllSchedulesCommitBean commitBean) {
		Integer liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			for (final VOffenderAllSchedules vOffAllSch : commitBean.getInsertList()) {
				liReturn = nonAssociation(vOffAllSch);
				if (liReturn > 0) {
					return liReturn;
				}
			}
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (final VOffenderAllSchedules vOffAllSch : commitBean.getUpdateList()) {
				liReturn = nonAssociation(vOffAllSch);
				if (liReturn > 0) {
					return liReturn;
				}
			}
		}
		return liReturn;
	}
	public Integer nonAssociation(final VOffenderAllSchedules bean) {
		Integer liReturn = 0;
		OffenderIndSchedules objOffIndSch = new OffenderIndSchedules();
		if (bean.getOffenderBookId() != null) {
			final String strOffenderId = String.valueOf(bean.getOffenderBookId());
			objOffIndSch.setOffenderBookId(Integer.parseInt(strOffenderId));
		}
		objOffIndSch.setToAgyLocId(bean.getToAgyLocId());
		if (objOffIndSch.getOffenderBookId() != null && objOffIndSch.getToAgyLocId() != null) {
			liReturn =oidstwjuPkgService.chkNonAssociation(objOffIndSch);
		}
		return liReturn;
	}
	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *            VOffenderAllSchedulesCommitBean
	 * @throws CustomException 
	 *
	 */
	@Transactional
	public Integer offSchCommit(final VOffenderAllSchedulesCommitBean commitBean) throws CustomException {
		int liReturn = 0;
		Integer eventId = null;
		OffenderIndSchedules objOffIndSch = null;
		OffenderIndSchWaitLists objOffIndSchWait = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			List<OffenderIndSchedules> lstInsertOff = new ArrayList<OffenderIndSchedules>();
			List<OffenderIndSchWaitLists> lstInsertOffWait = new ArrayList<OffenderIndSchWaitLists>();

			for (final VOffenderAllSchedules vOffAllSch : commitBean.getInsertList()) {
				lstInsertOff = new ArrayList<OffenderIndSchedules>();
				eventId = oidstwjuRepo.offSchPreInsert();
				objOffIndSch = new OffenderIndSchedules();
				objOffIndSch.setAgyLocId(vOffAllSch.getAgyLocId());
				objOffIndSch.setDirectionCode(vOffAllSch.getDirectionCode());
				objOffIndSch.setEscortCode(vOffAllSch.getEscortCode());
				objOffIndSch.setEventSubType(vOffAllSch.getEventSubType());
				objOffIndSch.setEventStatus(vOffAllSch.getEventStatus());
				objOffIndSch.setEventDate(vOffAllSch.getEventDate());
				if ( vOffAllSch.getOffenderBookId() != null) {
					final String strOffenderId = String.valueOf(vOffAllSch.getOffenderBookId());
					objOffIndSch.setOffenderBookId(Integer.parseInt(strOffenderId));
				}
				objOffIndSch.setToAgyLocId(vOffAllSch.getToAgyLocId());
				objOffIndSch.setStartTime(vOffAllSch.getStartTime());
				objOffIndSch.setCommentText(vOffAllSch.getCommentText());
				objOffIndSch.setEventClass("EXT_MOV");
				objOffIndSch.setEventType("TRN");
				objOffIndSch.setHiddenCommentText(vOffAllSch.getHiddenCommentText());
				objOffIndSch.setEventId(eventId);
				objOffIndSch.setCreateUserId(commitBean.getCreateUserId());
				liReturn=oidstwjuPkgService.insNotification(objOffIndSch, commitBean.getCreateUserId());
				logger.info(logger.getName().getClass()+" insNotification");
				if (liReturn > 0) {
					liReturn = 5;
					return liReturn;

				}


				if (objOffIndSch.getOffenderBookId() != null && objOffIndSch.getToAgyLocId() != null) {

					liReturn = oidstwjuRepo.chkClassification(objOffIndSch);
					logger.info(logger.getName().getClass()+" chkClassification");

				}

				if (liReturn > 0) {
					liReturn = 8;
				}

				lstInsertOffWait = new ArrayList<OffenderIndSchWaitLists>();
				if (vOffAllSch.getNbtWaitListStatus() != null) {
					objOffIndSchWait = new OffenderIndSchWaitLists();
					objOffIndSchWait.setRequestDate(vOffAllSch.getNbtRequestDate());
					objOffIndSchWait.setApprovedFlag(vOffAllSch.getNbtApprovedFlag());
					objOffIndSchWait.setNbtLastName(vOffAllSch.getNbtLastName());
					objOffIndSchWait.setNbtFirstName(vOffAllSch.getNbtFirstName());
					objOffIndSchWait.setWaitListStatus(vOffAllSch.getNbtWaitListStatus());
					objOffIndSchWait.setStatusDate(vOffAllSch.getNbtStatusDate());
					objOffIndSchWait.setTransferPriority(vOffAllSch.getNbtTransferPriority());
					objOffIndSchWait.setOutcomeReasonCode(vOffAllSch.getNbtOutcomeReasonCode());
					objOffIndSchWait.setCommentText1(vOffAllSch.getNbtCommentText1());
					objOffIndSchWait.setCreateUserId(commitBean.getCreateUserId());
					if (vOffAllSch.getNbtApprovedStaffId() != null) {
						objOffIndSchWait.setApprovedStaffId(vOffAllSch.getNbtApprovedStaffId());
					}
					objOffIndSchWait.setEventId(eventId);
					lstInsertOffWait.add(objOffIndSchWait);
				}
				lstInsertOff.add(objOffIndSch);
				offenderIndSchedulesTwfService.offenderIndSchedulesTwfTgr(objOffIndSch, "INSERTING");
				logger.info(logger.getName().getClass()+" offenderIndSchedulesTwfTgr");
				lstInsertOff=offenderIndSchedulesT2Service.offenderIndSchedulesT2Tgr(lstInsertOff);
				logger.info(logger.getName().getClass()+" offenderIndSchedulesT2Tgr");
				lstInsertOff=offenderIndSchedulesT1Service.offenderIndSchedulesT1Tgn(lstInsertOff);
				logger.info(logger.getName().getClass()+" offenderIndSchedulesT1Tgn");
				offenderIndSchedulesT3Service.getVnumRows(objOffIndSch);
				logger.info(logger.getName().getClass()+" getVnumRows");

				liReturn = oidstwjuRepo.offSchInsertVOffenderAllSchedules(lstInsertOff);
				logger.info(logger.getName().getClass()+" offSchInsertVOffenderAllSchedules");

				if (liReturn > 0) {
					if (lstInsertOffWait != null && lstInsertOffWait.size() > 0) {
						liReturn = oidstwjuRepo.offSwlInsertOffenderIndSchWaitLists(lstInsertOffWait);
						logger.info(logger.getName().getClass()+" offSwlInsertOffenderIndSchWaitLists");
					}
				}
			}

		}

		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			final List<OffenderIndSchWaitLists> lstUpdateOffWait = new ArrayList<OffenderIndSchWaitLists>();
			final List<OffenderIndSchWaitLists> lsInsertOffWait = new ArrayList<OffenderIndSchWaitLists>();
			final List<OffenderIndSchedules> lstUpdateOff = new ArrayList<OffenderIndSchedules>();
			for (final VOffenderAllSchedules vOffAllSch : commitBean.getUpdateList()) {
				objOffIndSch = new OffenderIndSchedules();
				objOffIndSch.setAgyLocId(vOffAllSch.getAgyLocId());
				objOffIndSch.setEscortCode(vOffAllSch.getEscortCode());
				objOffIndSch.setEventStatus(vOffAllSch.getEventStatus());
				objOffIndSch.setEventDate(vOffAllSch.getEventDate());
				objOffIndSch.setToAgyLocId(vOffAllSch.getToAgyLocId());
				objOffIndSch.setEventSubType(vOffAllSch.getEventSubType());
				objOffIndSch.setStartTime(vOffAllSch.getStartTime());
				objOffIndSch.setCommentText(vOffAllSch.getCommentText());
				objOffIndSch.setHiddenCommentText(vOffAllSch.getHiddenCommentText());
				objOffIndSch.setModifyUserId(commitBean.getCreateUserId());
				if (vOffAllSch.getEventId() != null) {
					final String strEventId = String.valueOf(vOffAllSch.getEventId());
					objOffIndSch.setEventId(Integer.parseInt(strEventId));
				}
				if (vOffAllSch.getReferenceId() != null) {
					objOffIndSch.setReferenceId(vOffAllSch.getReferenceId().intValue());
				}
				if (vOffAllSch.getOffenderBookId() != null) {
					objOffIndSch.setOffenderBookId(vOffAllSch.getOffenderBookId().intValue());
				}
				if (objOffIndSch.getOffenderBookId() != null && objOffIndSch.getToAgyLocId() != null) {
					liReturn = oidstwjuRepo.chkClassification(objOffIndSch);
					if (liReturn > 0) {
						liReturn = 8;
						return liReturn;
					}
				}

				if (vOffAllSch.getNbtEventId() > 0) {
					if (vOffAllSch.getInsertedFlag()) {
						objOffIndSchWait = new OffenderIndSchWaitLists();
						objOffIndSchWait.setRequestDate(vOffAllSch.getNbtRequestDate());
						objOffIndSchWait.setApprovedFlag(vOffAllSch.getNbtApprovedFlag());
						objOffIndSchWait.setNbtLastName(vOffAllSch.getNbtLastName());
						objOffIndSchWait.setNbtFirstName(vOffAllSch.getNbtFirstName());
						objOffIndSchWait.setWaitListStatus(vOffAllSch.getNbtWaitListStatus());
						objOffIndSchWait.setStatusDate(vOffAllSch.getNbtStatusDate());
						objOffIndSchWait.setTransferPriority(vOffAllSch.getNbtTransferPriority());
						objOffIndSchWait.setOutcomeReasonCode(vOffAllSch.getNbtOutcomeReasonCode());
						objOffIndSchWait.setCommentText1(vOffAllSch.getNbtCommentText1());
						if (vOffAllSch.getNbtApprovedStaffId() != null) {
							objOffIndSchWait.setApprovedStaffId(vOffAllSch.getNbtApprovedStaffId());
						}
						objOffIndSchWait.setEventId(vOffAllSch.getNbtEventId());
						lsInsertOffWait.add(objOffIndSchWait);
					} else {
						objOffIndSchWait = new OffenderIndSchWaitLists();
						objOffIndSchWait.setRequestDate(vOffAllSch.getNbtRequestDate());
						objOffIndSchWait.setApprovedFlag(vOffAllSch.getNbtApprovedFlag());
						objOffIndSchWait.setNbtLastName(vOffAllSch.getNbtLastName());
						objOffIndSchWait.setNbtFirstName(vOffAllSch.getNbtFirstName());
						objOffIndSchWait.setWaitListStatus(vOffAllSch.getNbtWaitListStatus());
						objOffIndSchWait.setStatusDate(vOffAllSch.getNbtStatusDate());
						objOffIndSchWait.setTransferPriority(vOffAllSch.getNbtTransferPriority());
						objOffIndSchWait.setOutcomeReasonCode(vOffAllSch.getNbtOutcomeReasonCode());
						objOffIndSchWait.setCommentText1(vOffAllSch.getNbtCommentText1());
						objOffIndSchWait.setEventId(vOffAllSch.getNbtEventId());
						if (vOffAllSch.getNbtApprovedStaffId() != null) {
							objOffIndSchWait.setApprovedStaffId(vOffAllSch.getNbtApprovedStaffId());
						}
						objOffIndSchWait.setModifyDatetime(dateService.getDBTime());
						objOffIndSchWait.setModifyUserId(commitBean.getCreateUserId());
						lstUpdateOffWait.add(objOffIndSchWait);
					}

				}
				lstUpdateOff.add(objOffIndSch);
				Integer rowId=vOffAllSch.getRowId();
				String recordSource = null;
				OffenderIndSchedules oldObj=null;
				for(VOffenderAllSchedules data:offSchTemp) {
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
						recordSource=data.getRecordSource();
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
				try {
					vOffenderAllSchedules2TuService.vOffenderAllSchedules2TuTrigger(objOffIndSch,oldObj,recordSource, null);
					liReturn=1;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (liReturn > 0) {
				if (lstUpdateOffWait != null && lstUpdateOffWait.size() > 0) {
					liReturn = oidstwjuRepo.offSwlUpdateOffenderIndSchWaitLists(lstUpdateOffWait);
				} else if (lsInsertOffWait != null && lsInsertOffWait.size() > 0) {
					liReturn = oidstwjuRepo.offSwlInsertOffenderIndSchWaitLists(lsInsertOffWait);
				}
			}

		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {

			final List<OffenderIndSchedules> lstDeleteOff = new ArrayList<OffenderIndSchedules>();
			for (final VOffenderAllSchedules vOffAllSch : commitBean.getDeleteList()) {
				vOffAllSch.setModifyUserId(commitBean.getCreateUserId());
				VOffenderAllSchedules oldObj=null;
				int rowId=vOffAllSch.getRowId();
				for(final VOffenderAllSchedules data : offSchTemp) {
					if(data.getRowId() == rowId) {
						oldObj=data;
						break;
					}
				}
				objOffIndSch = new OffenderIndSchedules();
				if ( vOffAllSch.getEventId() != null) {
					final String strEventId = String.valueOf(vOffAllSch.getEventId());
					objOffIndSch.setEventId(Integer.parseInt(strEventId));
				}
				objOffIndSch.setEventSubType(vOffAllSch.getEventSubType());
				objOffIndSch.setEventDate(vOffAllSch.getEventDate());
				if ( vOffAllSch.getOffenderBookId() != null) {
					final String strOffenderId = String.valueOf(vOffAllSch.getOffenderBookId());
					objOffIndSch.setOffenderBookId(Integer.parseInt(strOffenderId));
				}
				liReturn = oidstwjuRepo.delNotification(objOffIndSch);
				if (liReturn == 1) {
					lstDeleteOff.add(objOffIndSch);
					VOffenderAllSchedules2 newRef=new VOffenderAllSchedules2();
					VOffenderAllSchedules2 oldRef=new VOffenderAllSchedules2();
					try {
						BeanUtils.copyProperties(newRef, vOffAllSch);
						BeanUtils.copyProperties(oldRef, oldObj);
					} catch (Exception e) {
						e.printStackTrace();
					}
					liReturn = vOffenderAllSchedules2TdService.vOffenderAllSchedules2TdTgr(newRef, oldRef, null);
				} else {
					liReturn = 7;
					return liReturn;
				}

			}
		}
		return liReturn;
	}
	
	@Override
	public String checkNonAssociations(VOffenderAllSchedulesCommitBean commitBean) {
		String returnMessage = "";
		try {
			List<VOffenderAllSchedules>  VOffenderAllSchedulesFinalList = new ArrayList<VOffenderAllSchedules>();
			if (commitBean != null && commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			 VOffenderAllSchedulesFinalList.addAll(commitBean.getInsertList());
			}
			if (commitBean != null && commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				 VOffenderAllSchedulesFinalList.addAll(commitBean.getUpdateList());
			}
			if (commitBean != null && commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				return	returnMessage=ApplicationConstants.EMPTYDATA;
			}
	returnMessage=getNonAssociationsData(VOffenderAllSchedulesFinalList, commitBean.getCreateUserId());
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in checkNonAssociations ", e);
		}
	
	return returnMessage;
}
	public String getNonAssociationsData(List<VOffenderAllSchedules> offenderAllSchedules, String createUserId) {
		String returnMessage = "";
		String individualNonAssociationMessages = "";
		String gandNonAssociationMessages = "";
		try {
			if (offenderAllSchedules != null && offenderAllSchedules.size()>0 && offenderAllSchedules.get(0)!= null && offenderAllSchedules.get(0).getOffenderBookId() != null) {
				
					List<OffenderNaDetails> nonAssociationsListDifferentLocation = nonAssociationService.getNonAssociationOffenderList(offenderAllSchedules.get(0).getOffenderBookId().intValue());
					if(nonAssociationsListDifferentLocation != null  && nonAssociationsListDifferentLocation.size()>0  ) {
						for(VOffenderAllSchedules vOffSch : offenderAllSchedules) {	
							for (OffenderNaDetails obj : nonAssociationsListDifferentLocation) {
								List<VOffenderAllSchedules> offenderIndSchedulesList  = oidstwjuRepo.checkOffenderScheduleConflictsSameFacility(obj.getNsOffenderBookId(), vOffSch);
								if (offenderIndSchedulesList != null && offenderIndSchedulesList.size() > 0) {
									for (VOffenderAllSchedules offenderIndSchedule : offenderIndSchedulesList) {
										String message = "";
										VHeaderBlock bean = ocdprogrRepository
												.ocdprogrGetOffenderNames(obj.getNsOffenderBookId(), createUserId);
										message = bean.getLastName() + " " + bean.getFirstName() + ", "
												+ bean.getOffenderIdDisplay() + "\n";

										java.util.Date stDate = offenderIndSchedule.getStartTime();
										java.util.Date releaseDate = offenderIndSchedule.getEventDate();
										SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
										String startTime = stDate != null ? sdf.format(stDate) : "";
										SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
										String appDate = sdf1 != null ? sdf1.format(releaseDate) : "";
										message = message + appDate + ", " + startTime+ "\n";
										individualNonAssociationMessages = individualNonAssociationMessages + message;
									}
									individualNonAssociationMessages = individualNonAssociationMessages + "\n";

								}
							}
						}
					}
					if(individualNonAssociationMessages != null && !individualNonAssociationMessages.equalsIgnoreCase("")) {
						individualNonAssociationMessages = "oidstwju.indinonassocconflict:\n\n" + individualNonAssociationMessages;
					}

					List<OffenderStgAffiliations> offenderStgAffiliationsList = nonAssociationService.getOffendersOfNonAssociationGroup(BigDecimal.valueOf(offenderAllSchedules.get(0).getOffenderBookId().intValue()));

					if(offenderStgAffiliationsList != null && offenderStgAffiliationsList.size()>0) {
						for(OffenderStgAffiliations offenderStgAffiliations : offenderStgAffiliationsList) {
							for(VOffenderAllSchedules vOffSch : offenderAllSchedules) {
								List<VOffenderAllSchedules> offenderIndNonAssocaiotnSchedulesList  = oidstwjuRepo.checkOffenderScheduleConflictsSameFacility(offenderStgAffiliations.getOffenderBookId(), vOffSch);
								if (offenderIndNonAssocaiotnSchedulesList != null && offenderIndNonAssocaiotnSchedulesList.size() > 0) {
									VHeaderBlock bean = ocdprogrRepository.ocdprogrGetOffenderNames(offenderStgAffiliations.getOffenderBookId(), createUserId);
									String message = "";
									message = bean.getLastName() + " " + bean.getFirstName() + ", " + bean.getOffenderIdDisplay() + "\n";							
									for (VOffenderAllSchedules offenderIndNonAssocaiotnSchedule : offenderIndNonAssocaiotnSchedulesList) {


										java.util.Date stDate = offenderIndNonAssocaiotnSchedule.getStartTime();
										java.util.Date releaseDate = offenderIndNonAssocaiotnSchedule.getEventDate();
										SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
										String startTime = stDate != null ? sdf.format(stDate) : "";
										SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
										String appDate = sdf1 != null ? sdf1.format(releaseDate) : "";
										message = message + appDate + ", " + startTime + "\n";
									}
									gandNonAssociationMessages = gandNonAssociationMessages + message + "\n";
								}
							}

						}
					}

					if(gandNonAssociationMessages != null && !gandNonAssociationMessages.equalsIgnoreCase("")) {
						gandNonAssociationMessages = "oidstwju.gangnonassocconflict:\n\n" + gandNonAssociationMessages;
					}

					if((individualNonAssociationMessages != null && !individualNonAssociationMessages.equalsIgnoreCase("")) && (gandNonAssociationMessages != null && !gandNonAssociationMessages.equalsIgnoreCase(""))) {
						returnMessage = getFinalMessageString(individualNonAssociationMessages + "\n" + gandNonAssociationMessages);
					} else if(individualNonAssociationMessages != null && !individualNonAssociationMessages.equalsIgnoreCase("")){
						returnMessage = getFinalMessageString(individualNonAssociationMessages);
					} else if(gandNonAssociationMessages != null && !gandNonAssociationMessages.equalsIgnoreCase("")){
						returnMessage = getFinalMessageString(gandNonAssociationMessages);
					} else {
						returnMessage = ApplicationConstants.EMPTYDATA;
					}
				
			} else {
				returnMessage = ApplicationConstants.EMPTYDATA;
			}

		} catch(Exception e){
			logger.error(this.getClass().getName()+"Error in getNonAssociationsData ", e);
		}
		return returnMessage;

}

	public String getFinalMessageString(final String messageData) {
		return "oidstwju.nonassociationconflictmsg \n\n"
				+ messageData + " \n oidstwju.doyouwanttocontinue ";
	}
	
	

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderIndSchWaitLists> offSwlExecuteQuery(final OffenderIndSchWaitLists searchRecord) {

		final StaffMembers objSearchDao = new StaffMembers();
		StaffMembers staffMem = new StaffMembers();
		final List<OffenderIndSchWaitLists> lstOffSchWait = (List<OffenderIndSchWaitLists>) oidstwjuRepo
				.offSwlExecuteQuery(searchRecord);

		if (lstOffSchWait != null && lstOffSchWait.size() > 0) {
			for (final OffenderIndSchWaitLists objOffSchWait : lstOffSchWait) {
				if (objOffSchWait != null) {
					if (objOffSchWait.getApprovedStaffId() != null) {
						objSearchDao.setStaffId(objOffSchWait.getApprovedStaffId());
						staffMem = oidstwjuRepo.showApprovedDetails(objSearchDao);
						if (staffMem != null) {
							objOffSchWait.setNbtLastName(staffMem.getLastName());
							objOffSchWait.setNbtFirstName(staffMem.getFirstName());
						}
					}
				}
			}
		}
		return lstOffSchWait;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *            OffenderIndSchWaitListsCommitBean
	 */
	@Transactional
	public Integer offSwlCommit(final OffenderIndSchWaitListsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().stream().forEach(data->{
				data.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = oidstwjuRepo.offSwlInsertOffenderIndSchWaitLists(commitBean.getInsertList());

		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().stream().forEach(data->{
				data.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = oidstwjuRepo.offSwlUpdateOffenderIndSchWaitLists(commitBean.getUpdateList());
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oidstwjuRepo.offSwlDeleteOffenderIndSchWaitLists(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgEscortRecordGroup() {
		return oidstwjuRepo.rgEscortRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<AgencyLocations> rgAgencyLocationRecordGroup(final String agyLocId) {
		return oidstwjuRepo.rgAgencyLocationRecordGroup(agyLocId);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<MovementReasons> rgMoveReasonRecordGroup() {
		return oidstwjuRepo.rgMoveReasonRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgStatusRecordGroup() {
		return oidstwjuRepo.rgStatusRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgPriorityRecordGroup() {
		return oidstwjuRepo.rgPriorityRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgCancelReasonRecordGroup() {
		return oidstwjuRepo.rgCancelReasonRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<StaffMembers> rgApprovedByRecordGroup(final String caseLoadId) {
		return oidstwjuRepo.rgApprovedByRecordGroup(caseLoadId);

	}

	/**
	 * Gets SysDate
	 * 
	 * @return Date
	 */
	public Date getCurrentDate() {
		return oidstwjuRepo.getCurrentDate();
	}

	/**
	 * Retrives Staff ID
	 * 
	 * @return Integer
	 */
	public Integer getStaffId(String userName) {
 		return omsUtilsService.getStaffId(userName);
	}

	/**
	 * It checks whether offender has any conflict
	 * 
	 * @return Integer
	 */
	public Integer checkScheduleConflict(final VOffenderAllSchedulesCommitBean offSch) {
		return oidstwjuRepo.checkScheduleConflict(offSch);
	}
	

}
package net.syscon.s4.cm.programsservices.maintenance.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.OcdprogrRepository;
import net.syscon.s4.cm.programsservices.OcduprojRepository;
import net.syscon.s4.cm.programsservices.maintenance.OidowrelRepository;
import net.syscon.s4.cm.programsservices.maintenance.OidowrelService;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderCaseNotesCommitBean;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.im.beans.OffenderPrgObligationsCommitBean;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.im.beans.OffenderProgramProfilesCommitBean;
import net.syscon.s4.im.beans.VCourseActivities;
import net.syscon.s4.im.beans.VOffenderProgramProfiles;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.OciscataRepository;
import net.syscon.s4.inst.institutionalactivities.OcupaoffRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEventsCommitBean;
import net.syscon.s4.inst.programswithoutschedules.OcdpnoteService;
import net.syscon.s4.inst.programswithoutschedules.OcdxprogRepository;
import net.syscon.s4.pkgs.oms_miscellaneous.OmsMiscellaneousService;
import net.syscon.s4.pkgs.oms_utils.OmsUtilsService;
import net.syscon.s4.pkgs.tag_service.TagServiceService;
import net.syscon.s4.triggers.OffenderPrgObligationsT1Service;
import net.syscon.s4.triggers.OffenderPrgObligationsT2Service;
import net.syscon.s4.triggers.OffenderProgramProfilesTrService;
import net.syscon.s4.triggers.VOffenderCourseEventsTuService;

@Service
public class OidowrelServiceImpl extends BaseBusiness implements OidowrelService {

	private static Logger logger = LogManager.getLogger(OidowrelServiceImpl.class.getName());

	@Autowired
	private OidowrelRepository oidowrelRepository;

	@Autowired
	private TagServiceService tagServiceService;

	@Autowired
	private OmsMiscellaneousService omsMiscellaneousService;

	@Autowired
	private OffenderPrgObligationsT2Service offenderPrgObligationsT2Service;

	@Autowired
	private OffenderPrgObligationsT1Service offenderPrgObligationsT1Service;

	@Autowired
	private OffenderProgramProfilesTrService offenderProgramProfilesTrService;

	@Autowired
	private OmsUtilsService omsUtilsService;

	@Autowired
	private OcdpnoteService ocdpnoteService;

	@Autowired
	private VOffenderCourseEventsTuService vOffenderCourseEventsTuService;

	@Autowired
	private OcduprojRepository ocduprojRepository;
	
	@Autowired
	private OcupaoffRepository ocupaoffRepository;

	@Autowired
	private OcdxprogRepository ocdxprogRepository;
	
	@Autowired
	private OcdprogrRepository ocdprogrRepository;
	
	@Autowired
	private OciscataRepository ociscataRepository;

	/**
	 * Logger object used to print the log in the file
	 */

	/**
	 * Creates new OidowrelServiceImpl class Object
	 */
	public OidowrelServiceImpl() {
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgPriorityRecordGroup() {
		return oidowrelRepository.rgPriorityRecordGroup();
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderPrgObligations> offPrgObligationsExecuteQuery(OffenderPrgObligations searchRecord) {
		logger.info(this.getClass().getName()+" offPrgObligationsExecuteQuery method call");
		List<OffenderPrgObligations> list = oidowrelRepository.offPrgObligationsExecuteQuery(searchRecord);
		if (list != null && list.size() > 0 && !list.isEmpty()) {
			list.forEach(bo -> {
				if (bo.getProgramId() != null) {
					logger.info(this.getClass().getName()+" getPrgSrvDetails method call");
					ProgramServices bean = tagServiceService.getPrgSrvDetails(bo.getProgramId());
					bo.setProgramDesc(bean.getDescription());
				}
				if (bo.getStatus() != null) {
					logger.info(this.getClass().getName()+" getDescCode method call");
					String nbtStatus = omsMiscellaneousService.getDescCode("PS_PRG_STAT", bo.getStatus());// OPO_WR_STAT
					bo.setStatus(nbtStatus);
				}
			});
		}
		return list;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_PRG_OBLIGATIONS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offPrgObligationsCommit(OffenderPrgObligationsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bo -> {
				bo.setCreateUserId(commitBean.getCreateUserId());
				logger.info(this.getClass().getName()+" getseqPrgRefIdSeq method call");
				bo.setOffenderPrgObligationId(oidowrelRepository.getseqPrgRefIdSeq());
			});
			logger.info(this.getClass().getName()+" insertOffenderPrgObligations method call");
			liReturn = oidowrelRepository.insertOffenderPrgObligations(commitBean.getInsertList());
			commitBean.getInsertList().forEach(bo -> {
				logger.info(this.getClass().getName()+" offenderPrgObligationsT2 method call");
				offenderPrgObligationsT2Service.offenderPrgObligationsT2(bo);
				logger.info(this.getClass().getName()+" offenderPrgObligationsT1 method call");
			});
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			logger.info(this.getClass().getName()+" updateOffenderPrgObligations method call");
			liReturn = oidowrelRepository.updateOffenderPrgObligations(commitBean.getUpdateList());
			commitBean.getUpdateList().forEach(bo -> {
				OffenderPrgObligations oldBean = new OffenderPrgObligations();
				logger.info(this.getClass().getName()+" getOldStatus method call");
				String Status = oidowrelRepository.getOldStatus(bo.getOffenderPrgObligationId());
				oldBean.setStatus(Status);
				logger.info(this.getClass().getName()+" offenderPrgObligationsT1 method call");
				offenderPrgObligationsT1Service.offenderPrgObligationsT1(bo, oldBean);
			});
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = oidowrelRepository.deleteOffenderPrgObligations(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderProgramProfiles> offProgramProfilesExecuteQuery(OffenderProgramProfiles searchRecord) {
		logger.info(this.getClass().getName()+" offProgramProfilesExecuteQuery method call");
		List<OffenderProgramProfiles> list = oidowrelRepository.offProgramProfilesExecuteQuery(searchRecord);
		if (list.size() > 0 && !list.isEmpty()) {
			list.forEach(bo -> {
				logger.info(this.getClass().getName()+" getCourseActivityData method call");
				VCourseActivities obj = oidowrelRepository.getCourseActivityData(bo.getCrsActyId());
				if (obj != null) {
					bo.setProviderName(obj.getProviderName());
					bo.setProjectCode(obj.getCourseActivityCode());
				}
			});
		}
		return list;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_PROGRAM_PROFILES
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offProgramProfilesCommit(OffenderProgramProfilesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bo -> {
				bo.setCreateUserId(commitBean.getCreateUserId());
				logger.info(this.getClass().getName()+" offenderProgramProfilesTr method call");
				offenderProgramProfilesTrService.offenderProgramProfilesTr(bo);
			});
			logger.info(this.getClass().getName()+" insertOffenderProgramProfiles method call");
			liReturn = oidowrelRepository.insertOffenderProgramProfiles(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
				logger.info(this.getClass().getName()+" offenderProgramProfilesTr method call");
				offenderProgramProfilesTrService.offenderProgramProfilesTr(bo);
			});
			logger.info(this.getClass().getName()+" updateOffenderProgramProfiles method call");
			liReturn = oidowrelRepository.updateOffenderProgramProfiles(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<VOffenderCourseEvents> vOffenderCourseEventsExecuteQuery(VOffenderCourseEvents searchRecord) {
		logger.info(this.getClass().getName()+" vOffenderCourseEventsExecuteQuery method call");
		List<VOffenderCourseEvents> list = oidowrelRepository.vOffenderCourseEventsExecuteQuery(searchRecord);
		if (list.size() > 0 && !list.isEmpty()) {
			list.forEach(bo -> {
				logger.info(this.getClass().getName()+" getDescCode method call");
				bo.setEventType(omsMiscellaneousService.getDescCode("EVENTS", bo.getEventType()));
				logger.info(this.getClass().getName()+" getNbtProviderName method call");
				bo.setProviderName(oidowrelRepository.getNbtProviderName(bo.getCrsActyId()!= null ?bo.getCrsActyId().longValue():null));
			});
		}
		return list;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_OFFENDER_COURSE_EVENTS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer vOffenderCourseEventsCommit(VOffenderCourseEventsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (VOffenderCourseEvents bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				bean.setCreateUserId(commitBean.getCreateUserId());
				logger.info(this.getClass().getName()+" getOldRecvOffenderCourseEvents method call");
				List<VOffenderCourseEvents> oldObjList = ocduprojRepository.getOldRecvOffenderCourseEvents(
						bean.getEventId() != null ? bean.getEventId().longValue() : null);
				VOffenderCourseEvents oldObj = new VOffenderCourseEvents();
				if (oldObjList != null && !oldObjList.isEmpty()) {
					oldObj = oldObjList.get(0);
				}
				logger.info(this.getClass().getName()+" vOffenderCourseEventsTuTgr method call");
				liReturn = vOffenderCourseEventsTuService.vOffenderCourseEventsTuTgr(oldObj, bean);
			}
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderCaseNotes> offenderCaseNotesExecuteQuery(OffenderCaseNotes searchRecord) {
		logger.info(this.getClass().getName()+" offenderCaseNotesExecuteQuery method call");
		List<OffenderCaseNotes> list = oidowrelRepository.offenderCaseNotesExecuteQuery(searchRecord);
		if (list.size() > 0 && !list.isEmpty()) {
			list.forEach(bo -> {
				bo.setStaffName(getStaffName(searchRecord.getCreateUserId()));
			});
		}
		return list;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFFENDER_CASE_NOTES
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offenderCaseNotesCommit(OffenderCaseNotesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bo -> {
				bo.setCreateUserId(commitBean.getCreateUserId());
				ReferenceCodes obj = new ReferenceCodes();
				if (bo.getCaseLoadType() != null) {
					logger.info(this.getClass().getName()+" getReferenceCode method call");
					obj = oidowrelRepository.getReferenceCode(bo.getCaseLoadType());
					if (bo.getCaseLoadType().equalsIgnoreCase("COMM")) {
						bo.setNoteSourceCode(obj.getCode());
						bo.setpNbtNoteSourceCodeDesc(obj.getDescription());
					} else {
						bo.setNoteSourceCode(obj.getCode());
						bo.setpNbtNoteSourceCodeDesc(obj.getDescription());
					}
				}
			});
			logger.info(this.getClass().getName()+" insertOffenderCaseNotes method call");
			liReturn = oidowrelRepository.insertOffenderCaseNotes(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
				ReferenceCodes obj = new ReferenceCodes();
				if (bo.getCaseLoadType() != null) {
					logger.info(this.getClass().getName()+" getReferenceCode method call");
					obj = oidowrelRepository.getReferenceCode(bo.getCaseLoadType());
					if (bo.getCaseLoadType().equalsIgnoreCase("COMM")) {
						bo.setNoteSourceCode(obj.getCode());
						bo.setpNbtNoteSourceCodeDesc(obj.getDescription());
					} else {
						bo.setNoteSourceCode(obj.getCode());
						bo.setpNbtNoteSourceCodeDesc(obj.getDescription());
					}
				}
			});
			logger.info(this.getClass().getName()+" offenderCaseNotesUpdateOffenderCaseNotes method call");
			liReturn = oidowrelRepository.offenderCaseNotesUpdateOffenderCaseNotes(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			logger.info(this.getClass().getName()+" offenderCaseNotesDeleteOffenderCaseNotes method call");
			commitBean.getDeleteList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = oidowrelRepository.offenderCaseNotesDeleteOffenderCaseNotes(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ProgramServices> rgProgramRecordGroup() {
		List<ProgramServices> list = oidowrelRepository.rgProgramRecordGroup();
		if (list != null && list.size() > 0 && !list.isEmpty()) {
			list.forEach(bo -> {
				if (bo.getActiveFlag().equalsIgnoreCase("Y")) {
					bo.setCanDisplay(true);
				} else {
					bo.setCanDisplay(false);
				}
			});
		}
		return list;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgEndReasonRecordGroup() {
		return oidowrelRepository.rgEndReasonRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> rgCancelReasonRecordGroup() {
		return oidowrelRepository.rgCancelReasonRecordGroup();

	}

	@Override
	public String getStaffName(String userName) {
		String result = null;
		logger.info(this.getClass().getName()+" getStaffid method call");
		BigDecimal staffId = omsUtilsService.getStaffid(userName);
		if (staffId != null) {
			logger.info(this.getClass().getName()+" getStaffName method call");
			result = omsUtilsService.getStaffName(staffId);
		}
		return result;
	}

	@Override
	public OffenderCaseNotes getModuleName(OffenderCaseNotes objOffenderCaseNotes) {
		return ocdpnoteService.getModuleName(objOffenderCaseNotes);
	}
	
	@Override
	public String checkNonAssociations(OffenderProgramProfilesCommitBean commitBean) {
		String returnMessage = "";
		try {
			List<OffenderProgramProfiles> offenderProgramProfilesFinalList = new ArrayList<OffenderProgramProfiles>();
			if (commitBean != null && commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				offenderProgramProfilesFinalList.addAll(commitBean.getInsertList());
			}
			if (commitBean != null && commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				offenderProgramProfilesFinalList.addAll(commitBean.getUpdateList());
			}
			returnMessage = getNonAssociationsData(offenderProgramProfilesFinalList, commitBean.getCreateUserId());
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in checkNonAssociations", e);
		}
		return returnMessage;
	}
	
	public String getFinalMessageString(final String messageData) {
		String returnMessage = "";
		try {
			returnMessage =  messageData + " \n ociscata.doyouwanttoproceed ";
		} catch(Exception e){
			logger.error(this.getClass().getName()+"Error in getFinalMessageString ", e);
		}
		return returnMessage;
	}
	
	public String getNonAssociationsData(List<OffenderProgramProfiles> offenderProgramProfiles,String createUserId) {
		String  returnMessage = "";
		String individualNonAssociationMessages = "";
		String gangNonAssociationMessages = "";
		try {
			List<VOffenderProgramProfiles> listOfAllocOffenders = new ArrayList<VOffenderProgramProfiles>();
			if (offenderProgramProfiles != null && offenderProgramProfiles.size() > 0
					&& offenderProgramProfiles.get(0) != null
					&& offenderProgramProfiles.get(0).getOffenderBookId() != null) {
				for (OffenderProgramProfiles vOffPrgm : offenderProgramProfiles) {
					VOffenderProgramProfiles objSearchDao = new VOffenderProgramProfiles();
					objSearchDao.setCrsActyId(vOffPrgm.getCrsActyId());
					List<VOffenderProgramProfiles> listOfAllocOffendersTwo = ocupaoffRepository.vOffPrgProfilesExecuteQuery(objSearchDao);
					listOfAllocOffenders.addAll(listOfAllocOffendersTwo);
				}
			}
			if(!listOfAllocOffenders.isEmpty()) {
				if(offenderProgramProfiles != null && offenderProgramProfiles.size() > 0 && offenderProgramProfiles.get(0) != null && offenderProgramProfiles.get(0).getOffenderBookId() != null) {
					for (OffenderProgramProfiles vOffPrgm : offenderProgramProfiles) {
						VHeaderBlock bean = ocdprogrRepository.ocdprogrGetOffenderNames(new BigDecimal(vOffPrgm.getOffenderBookId()), createUserId);
						int offenderCount = 1;
						for(VOffenderProgramProfiles activeOffenderObj : listOfAllocOffenders) {
						Integer count = ociscataRepository.checkNonAssociationConflict(new BigDecimal(vOffPrgm.getOffenderBookId()),new BigDecimal(activeOffenderObj.getOffenderBookId()));
						if(count > 0) {
							String Msg = null;
							Msg = "ociscata.offender " + bean.getLastName() + "," + bean.getFirstName() + " , " + bean.getOffenderId() + " ociscata.hasNonAssociation " + " \n"
							      + " " + offenderCount + ") " + activeOffenderObj.getOffenderName() + " , " + activeOffenderObj.getOffenderId()  + " \n";
							offenderCount++;
							individualNonAssociationMessages = individualNonAssociationMessages + Msg + "\n";
						}
					}
					if (individualNonAssociationMessages != null
							&& !individualNonAssociationMessages.equalsIgnoreCase("")) {
						individualNonAssociationMessages = "INDIVIDUAL NON-ASSOCIATION CONFLICTS:\n\n"
								+ individualNonAssociationMessages;
					}
					for (VOffenderProgramProfiles activeOffenderObj : listOfAllocOffenders) {
						Integer count = ocdxprogRepository.checkNonAssociationGroupConflict(new BigDecimal(activeOffenderObj.getOffenderBookId()), new BigDecimal(vOffPrgm.getOffenderBookId()));
						if (count > 0) {
							String Msg = null;
							Msg = "ociscata.offender " + bean.getLastName() + "," + bean.getFirstName() + " , " + bean.getOffenderId() + " ociscata.hasNonAssociation " + " \n" + " "
									+ offenderCount + ") " + activeOffenderObj.getOffenderName() + " , " + activeOffenderObj.getOffenderId() + " \n";
							offenderCount++;
							gangNonAssociationMessages = gangNonAssociationMessages + Msg + "\n";
						}
					}
					if (gangNonAssociationMessages != null && !gangNonAssociationMessages.equalsIgnoreCase("")) {
						gangNonAssociationMessages = "GANG NON-ASSOCIATION CONFLICTS:\n\n" + gangNonAssociationMessages;
					}
				}
			}
			if ((individualNonAssociationMessages != null && !individualNonAssociationMessages.equalsIgnoreCase("")) && (gangNonAssociationMessages != null && !gangNonAssociationMessages.equalsIgnoreCase(""))) {
				returnMessage = getFinalMessageString(individualNonAssociationMessages + "\n" + gangNonAssociationMessages);
			} else if (individualNonAssociationMessages != null && !individualNonAssociationMessages.equalsIgnoreCase("")) {
				returnMessage = getFinalMessageString(individualNonAssociationMessages);
			} else if (gangNonAssociationMessages != null && !gangNonAssociationMessages.equalsIgnoreCase("")) {
				returnMessage = getFinalMessageString(gangNonAssociationMessages);
			} else {
				returnMessage = ApplicationConstants.EMPTYDATA;
			}

		} else {
			returnMessage = ApplicationConstants.EMPTYDATA;
		}
	} catch (Exception e) {
			logger.error(this.getClass().getName() + "Error in getNonAssociationsData", e);
		}
		return returnMessage;
	}
}
package net.syscon.s4.inst.schedules.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.inst.schedules.OidstojuRepository;
import net.syscon.s4.inst.schedules.OidstojuService;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules2;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;
import net.syscon.s4.triggers.OffenderIndSchedulesT1Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT2Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT3Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT4Service;
import net.syscon.s4.triggers.OffenderIndSchedulesTwfService;
import net.syscon.s4.triggers.VOffenderAllSchedules2TdService;
import net.syscon.s4.triggers.VOffenderAllSchedules2TuService;

/**
 * Class OidstojuServiceImpl
 */
@Service
public class OidstojuServiceImpl extends BaseBusiness implements OidstojuService {

	private static Logger logger = LogManager.getLogger(OidstojuServiceImpl.class.getName());

	private final String UPDATING = "UPDATING";
	@Autowired
	private OidstojuRepository oidstojuRepo;
	@Autowired
	private OffenderIndSchedulesT1Service offenderIndSchedulesT1Service;
	@Autowired
	private OffenderIndSchedulesT2Service offenderIndSchedulesT2Service;
	@Autowired
	private OffenderIndSchedulesT3Service offenderIndSchedulesT3Service;
	@Autowired
	private OffenderIndSchedulesT4Service offenderIndSchedulesT4Service;
	@Autowired
	private OffenderIndSchedulesTwfService offenderIndSchedulesTwfService;
	@Autowired
	private VOffenderAllSchedules2TdService offenderAllSchedules2TdService;
	@Autowired
	private VOffenderAllSchedules2TuService offenderAllSchedules2TuService;

	/**
	 * Creates new OidstojuServiceImpl class Object
	 */
	public OidstojuServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 *
	 */
	public List<OffenderIndSchedules> offBkgOnCheckDeleteMaster(final OffenderIndSchedules paramBean) {
		return oidstojuRepo.offBkgOnCheckDeleteMaster(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VOffenderAllSchedules> offSchExecuteQuery(final VOffenderAllSchedules searchRecord) {
		return oidstojuRepo.offSchExecuteQuery(searchRecord);

	}

	/**
	 * This method is used to insert,update in the db table & delete records from
	 * the data base tables
	 *
	 * @param commitBean <VOffenderAllSchedulesCommitBean>
	 * 
	 * @return VOffenderAllSchedules
	 *
	 */
	@Transactional
	public VOffenderAllSchedules offSchCommit(final VOffenderAllSchedulesCommitBean commitBean) {
		int liReturn = 0;
		VOffenderAllSchedules returnValue = null;
		OffenderIndSchedules objOffIndSch = null;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for(VOffenderAllSchedules bean:commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			List<OffenderIndSchedules> lstUpdateOff = new ArrayList<OffenderIndSchedules>();
			for (final VOffenderAllSchedules vOffAllSch : commitBean.getUpdateList()) {
				vOffAllSch.setModifyUserId(commitBean.getCreateUserId());
				if (!vOffAllSch.getConflictFlag()) {
					liReturn = offSchCheckScheduleConflictBeforeSave(vOffAllSch);
				}
				if (liReturn == 0) {
					objOffIndSch = new OffenderIndSchedules();
					objOffIndSch.setEventDate(vOffAllSch.getEventDate());
					objOffIndSch.setStartTime(vOffAllSch.getStartTime());
					objOffIndSch.setCommentText(vOffAllSch.getCommentText());
					objOffIndSch.setAgyLocId(vOffAllSch.getAgyLocId());
					objOffIndSch.setEscortCode(vOffAllSch.getEscortCode());
					objOffIndSch.setProvStateCode(vOffAllSch.getProvStateCode());
					objOffIndSch.setEventClass(vOffAllSch.getEventClass());
					objOffIndSch.setEventStatus(vOffAllSch.getEventStatus());
					objOffIndSch.setEventType(vOffAllSch.getEventType());
					objOffIndSch.setEventSubType(vOffAllSch.getEventSubType());
					objOffIndSch.setToAgyLocId(vOffAllSch.getToAgyLocId());
					objOffIndSch.setCreateUserId(commitBean.getCreateUserId());
					objOffIndSch.setModifyUserId(commitBean.getCreateUserId());
					if (vOffAllSch.getEventId() != null) {
						final String strEventId = String.valueOf(vOffAllSch.getEventId());
						objOffIndSch.setEventId(Integer.parseInt(strEventId));
					}
					objOffIndSch.setEventSubType(vOffAllSch.getEventSubType());
					if (vOffAllSch.getOffenderBookId() != null) {
						final String strOffenderId = String.valueOf(vOffAllSch.getOffenderBookId());
						objOffIndSch.setOffenderBookId(Integer.parseInt(strOffenderId));
					}
					lstUpdateOff.add(objOffIndSch);
				} else {
					return vOffAllSch;
				}
			}
			lstUpdateOff = offenderIndSchedulesT1Service.offenderIndSchedulesT1Tgn(lstUpdateOff);
			try {
				lstUpdateOff = offenderIndSchedulesT2Service.offenderIndSchedulesT2Tgr(lstUpdateOff);
			} catch (CustomException e) {
				logger.error("offenderIndSchedulesT2 :" + e);
			}
			OffenderCaseNotes offCaseNotes = null;
			for (int i = 0; i < lstUpdateOff.size(); i++) {
				offCaseNotes = new OffenderCaseNotes();
				try {
					offenderIndSchedulesT4Service.offenderIndSchedulesT4(offCaseNotes, lstUpdateOff.get(i), null);
				} catch (Exception e) {
					logger.error("offenderIndSchedulesT4 :" + e);
				}
			}
			for (int i = 0; i < lstUpdateOff.size(); i++) {
				offCaseNotes = new OffenderCaseNotes();
				offenderIndSchedulesTwfService.offenderIndSchedulesTwfTgr(lstUpdateOff.get(i), UPDATING);
				try {
					liReturn = offenderAllSchedules2TuService.vOffenderAllSchedules2TuTrigger(lstUpdateOff.get(i),
							lstUpdateOff.get(i), commitBean.getUpdateList().get(i).getRecordSource(), null);
				} catch (CustomException e) {
					logger.error("vOffenderAllSchedules2TuTrigger :" + e);
				}
			}

			if (liReturn > 0) {
				returnValue = new VOffenderAllSchedules();
			}

		}

		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for(VOffenderAllSchedules bean:commitBean.getInsertList()) {
				bean.setCreateUserId(commitBean.getCreateUserId());
			}

			final VOffenderAllSchedules objSchedules = offSchCheckScheduleConflict(commitBean);
			if (objSchedules != null && objSchedules.getOffenderBookId() == null) {
				Integer eventId = null;
				List<OffenderIndSchedules> lstInsertOff = new ArrayList<OffenderIndSchedules>();

				for (final VOffenderAllSchedules vOffAllSch : commitBean.getInsertList()) {
					lstInsertOff = new ArrayList<OffenderIndSchedules>();
					eventId = oidstojuRepo.offSchPreInsert();
					objOffIndSch = new OffenderIndSchedules();
					objOffIndSch.setEventDate(vOffAllSch.getEventDate());
					objOffIndSch.setStartTime(vOffAllSch.getStartTime());
					objOffIndSch.setEscortCode(vOffAllSch.getEscortCode());
					objOffIndSch.setCommentText(vOffAllSch.getCommentText());
					objOffIndSch.setEventType("TRN");
					objOffIndSch.setEventSubType("OJ");
					objOffIndSch.setEventClass("EXT_MOV");
					objOffIndSch.setEventStatus("SCH");
					objOffIndSch.setEventId(eventId);
					objOffIndSch.setDirectionCode("OUT");
					if (vOffAllSch.getOffenderBookId() != null) {
						final String strOffenderId = String.valueOf(vOffAllSch.getOffenderBookId());
						objOffIndSch.setOffenderBookId(Integer.parseInt(strOffenderId));
					}
					objOffIndSch.setAgyLocId(vOffAllSch.getAgyLocId());
					objOffIndSch.setToAgyLocId("OUT");
					objOffIndSch.setProvStateCode(vOffAllSch.getProvStateCode());
					objOffIndSch.setCreateUserId(commitBean.getCreateUserId());
					lstInsertOff.add(objOffIndSch);
					lstInsertOff = offenderIndSchedulesT1Service.offenderIndSchedulesT1Tgn(lstInsertOff);
					try {
						lstInsertOff = offenderIndSchedulesT2Service.offenderIndSchedulesT2Tgr(lstInsertOff);
					} catch (CustomException e) {
						logger.error("offenderIndSchedulesT2 :" + e);
					}
					if (lstInsertOff != null && lstInsertOff.size() > 0) {
						try {
							for (int i = 0; i < lstInsertOff.size(); i++)
								offenderIndSchedulesT3Service.getVnumRows(lstInsertOff.get(i));
						} catch (CustomException e) {
							logger.error("offenderIndSchedulesT3 :" + e);
						}
					}
					for (int i = 0; i < lstInsertOff.size(); i++) {
						offenderIndSchedulesTwfService.offenderIndSchedulesTwfTgr(lstInsertOff.get(i),"INSERTING");
					}
					liReturn = oidstojuRepo.offSchInsertVOffenderAllSchedules(lstInsertOff);
					if (liReturn > 0) {
						returnValue = new VOffenderAllSchedules();
					} else {
						returnValue = null;
					}
				}
			} else {
				return objSchedules;
			}
		}

		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (final VOffenderAllSchedules vOffAllSch : commitBean.getDeleteList()) {
				objOffIndSch = new OffenderIndSchedules();
				if (vOffAllSch.getEventId() != null) {
					final String strEventId = String.valueOf(vOffAllSch.getEventId());
					objOffIndSch.setEventId(Integer.parseInt(strEventId));
					VOffenderAllSchedules2 bean = new VOffenderAllSchedules2();
					bean.setRecordSource(vOffAllSch.getRecordSource());
					bean.setAgyLocId(vOffAllSch.getAgyLocId());
					bean.setEscortCode(vOffAllSch.getEscortCode());
					bean.setEventClass(vOffAllSch.getEventClass());
					bean.setEventDate(vOffAllSch.getEventDate());
					bean.setEventId(vOffAllSch.getEventId());
					bean.setEventStatus(vOffAllSch.getEventStatus());
					bean.setEventType(vOffAllSch.getEventType());
					bean.setEventSubType(vOffAllSch.getEventSubType());
					bean.setOffenderBookId(vOffAllSch.getOffenderBookId());
					bean.setOffenderIdDisplay(vOffAllSch.getOffenderIdDisplay());
					bean.setFirstName(vOffAllSch.getFirstName());
					bean.setToAgyLocId(vOffAllSch.getToAgyLocId());
					bean.setModifyUserId(commitBean.getCreateUserId());
					try {
						liReturn = offenderAllSchedules2TdService.vOffenderAllSchedules2TdTgr(bean, bean, null);
					} catch (CustomException e) {
						logger.error("vOffenderAllSchedules2TdTgr :" + e);
					}
				}
			}

		}
		if (liReturn > 0) {
			returnValue = new VOffenderAllSchedules();
		} else {
			returnValue = null;
		}
		return returnValue;

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgLocationRecordGroup() {
		return oidstojuRepo.rgLocationRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgEscortRecordGroup() {
		return oidstojuRepo.rgEscortRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgEventTypeSubTypeGroup() {
		return oidstojuRepo.rgEventTypeSubTypeGroup();

	}

	/**
	 * 
	 * @return Date
	 */
	public Date getCurrentDate() {
		return oidstojuRepo.getCurrentDate();
	}

	/**
	 * Before inserting the record it verifying whether any other schedules are
	 * assigned to the offender
	 * 
	 * @param vOffAllSch
	 * @return Integer <VOffenderAllSchedules>
	 */
	public Integer offSchCheckScheduleConflictBeforeSave(final VOffenderAllSchedules vOffAllSch) {
		int scheckConflit = 0;
		scheckConflit = oidstojuRepo.offSchCheckScheduleConflict(vOffAllSch);
		if (scheckConflit > 0) {
			return scheckConflit;
		}
		return scheckConflit;
	}

	/**
	 * Before inserting the record it verifying whether any other schedules are
	 * assigned to the offender
	 * 
	 * @param commitBean <VOffenderAllSchedulesCommitBean>
	 * @return VOffenderAllSchedules
	 */
	public VOffenderAllSchedules offSchCheckScheduleConflict(final VOffenderAllSchedulesCommitBean commitBean) {
		int scheckConflit = 0;

		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final VOffenderAllSchedules vOffAllSch : commitBean.getInsertList()) {

				if (!vOffAllSch.getConflictFlag()) {
					scheckConflit = oidstojuRepo.offSchCheckScheduleConflict(vOffAllSch);
					if (scheckConflit > 0) {
						return vOffAllSch;
					}
				}
			}
		}

		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			int inCount = 0;
			for (final VOffenderAllSchedules vOffAllSch1 : commitBean.getInsertList()) {
				inCount = 0;
				for (final VOffenderAllSchedules vOffAllSch2 : commitBean.getInsertList()) {
					if (!vOffAllSch1.getConflictFlag()
							&& vOffAllSch1.getEventDate().compareTo(vOffAllSch2.getEventDate()) == 0) {
						inCount = inCount + 1;
						if (inCount > 1) {
							return vOffAllSch1;
						}
					}
				}
			}
		}

		return new VOffenderAllSchedules();
	}

}

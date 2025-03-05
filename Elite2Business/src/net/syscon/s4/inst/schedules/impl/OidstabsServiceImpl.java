package net.syscon.s4.inst.schedules.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cm.programsservices.OcdprogrRepository;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.inst.schedules.OidstabsRepository;
import net.syscon.s4.inst.schedules.OidstabsService;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedulesCommitBean;
import net.syscon.s4.inst.schedules.bean.ScheduleMovementSetting;
import net.syscon.s4.inst.schedules.bean.VAddressUsages;
import net.syscon.s4.inst.schedules.bean.VAddressUsagesCommitBean;
import net.syscon.s4.inst.schedules.bean.VAgencyAddresses;
import net.syscon.s4.inst.schedules.bean.VAgencyAddressesCommitBean;
import net.syscon.s4.inst.schedules.bean.VCorporateAddresses;
import net.syscon.s4.inst.schedules.bean.VCorporateAddressesCommitBean;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules2;
import net.syscon.s4.inst.schedules.bean.VPhones;
import net.syscon.s4.inst.schedules.bean.VPhonesCommitBean;
import net.syscon.s4.inst.schedules.maintenance.OidsmsetService;
import net.syscon.s4.pkgs.non_association.NonAssociationService;
import net.syscon.s4.pkgs.tag_schedule.TagScheduleService;
import net.syscon.s4.triggers.OffenderIndSchedulesT1Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT2Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT3Service;
import net.syscon.s4.triggers.OffenderIndSchedulesT4Service;
import net.syscon.s4.triggers.OffenderIndSchedulesTwfService;
import net.syscon.s4.triggers.VOffenderAllSchedules2TdService;

/**
 * Class OidstabsServiceImpl
 */
@Service
public class OidstabsServiceImpl extends BaseBusiness implements OidstabsService {

	@Autowired
	private OidstabsRepository oidstabsRepository;

	@Autowired
	private TagScheduleService tagScheduleService;

	@Autowired
	private OffenderIndSchedulesT2Service offenderIndSchedulesT2Service;

	@Autowired
	private OffenderIndSchedulesT1Service offenderIndSchedulesT1Service;

	@Autowired
	private OffenderIndSchedulesTwfService offenderIndSchedulesTwfService;

	@Autowired
	private OffenderIndSchedulesT3Service offenderIndSchedulesT3Service;

	@Autowired
	private OffenderIndSchedulesT4Service offenderIndSchedulesT4Service;

	@Autowired
	private VOffenderAllSchedules2TdService vOffenderAllSchedules2TdService;

	@Autowired
	private OcdprogrRepository ocdprogrRepository;

	@Autowired
	private NonAssociationService nonAssociationService;
	
	@Autowired
	private OidsmsetService oidsmsetService;

	private static Logger logger = LogManager.getLogger(OidstabsServiceImpl.class.getName());

	/**
	 * Creates new OidstabsServiceImpl class Object
	 */
	public OidstabsServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public VOffenderAllSchedules offBkgOnCheckDeleteMaster(final VOffenderAllSchedules paramBean) {
		final VOffenderAllSchedules vOffenderAllSchedules = null;
		return vOffenderAllSchedules;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<VAgencyAddresses> offSchedulesOnCheckDeleteMaster(final VAgencyAddresses paramBean) {
		List<VAgencyAddresses> vAgencyAddressesList = null;
		try {
			vAgencyAddressesList = oidstabsRepository
					.offSchedulesOnCheckDeleteMaster(paramBean);
		} catch(Exception e){
			logger.error(this.getClass().getName()+"Error in offSchedulesOnCheckDeleteMaster ", e);
		}
		return vAgencyAddressesList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<VOffenderAllSchedules> offSchedulesExecuteQuery(final VOffenderAllSchedules searchRecord) {
		logger.info(logger.getName().getClass()+" offSchedulesExecuteQuery exeution method start");
		final DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
		final DateFormat timeFormat = new SimpleDateFormat("HH:mm");
		List<VOffenderAllSchedules> returnList = null;
		try {
			returnList = oidstabsRepository.offSchedulesExecuteQuery(searchRecord);
			logger.info(logger.getName().getClass()+" offSchedulesExecuteQuery");
			for (final VOffenderAllSchedules obj : returnList) {
				final String eventDate = outputFormat.format(obj.getEventDate());
				final String returnDate = outputFormat.format(obj.getReturnDate());
				final Integer returnValue = calculateDays(eventDate, returnDate);
				obj.setDaysOut(String.valueOf(returnValue));
				if (obj.getReturnTime() == null) {
					obj.setHoursOut(String.valueOf(0));
				} else {
					final String startTime = eventDate + " " + timeFormat.format(obj.getStartTime());
					final String returnTime = returnDate + " " + timeFormat.format(obj.getReturnTime());
					final Object returnVal = calculateHours(startTime, returnTime);
					obj.setHoursOut(String.valueOf(returnVal));
				}

			}
		} catch(Exception e){
			logger.error(this.getClass().getName()+"Error in offSchedulesExecuteQuery error ", e);
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_SCHEDULES
	 *
	 * @
	 */
	@Transactional
	public Integer offSchedulesCommit(final OffenderIndSchedulesCommitBean commitBean) {
		logger.info(logger.getName().getClass()+" offSchedulesCommit method start");
		int liReturn = 0;
		try {
			Integer conflictValue = 0;
			Integer seq = null;
			List<OffenderIndSchedules> listObject = new ArrayList<>();
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				for (final OffenderIndSchedules obj : commitBean.getInsertList()) {
					obj.setCreateUserId(commitBean.getCreateUserId());
					listObject = new ArrayList<>();
					seq = oidstabsRepository.getMaxEventid();
					obj.setEventId(seq);
					listObject.add(obj);
					try {
						listObject = offenderIndSchedulesT2Service.offenderIndSchedulesT2Tgr(listObject);
						logger.info(logger.getName().getClass()+" offenderIndSchedulesT2Tgr");
						listObject = offenderIndSchedulesT1Service.offenderIndSchedulesT1Tgn(listObject);
						logger.info(logger.getName().getClass()+" offenderIndSchedulesT1Tgn");
						offenderIndSchedulesTwfService.offenderIndSchedulesTwfTgr(obj, "INSERTING");
						logger.info(logger.getName().getClass()+" offenderIndSchedulesTwfTgr");
						offenderIndSchedulesT3Service.getVnumRows(obj);
						logger.info(logger.getName().getClass()+" getVnumRows");
					} catch (Exception e) {
						logger.error(this.getClass().getName()+"In method offSchedulesCommit error",e);
					}
					logger.info(logger.getName().getClass()+" offSchedulesInsertVOffenderAllSchedules exeution method start");
					liReturn = oidstabsRepository.offSchedulesInsertVOffenderAllSchedules(listObject);
					logger.info(logger.getName().getClass()+" offSchedulesInsertVOffenderAllSchedules exeution method end");

				}
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				for (final OffenderIndSchedules obj : commitBean.getUpdateList()) {
					obj.setModifyUserId(commitBean.getCreateUserId());
					List<OffenderIndSchedules> list = new ArrayList<OffenderIndSchedules>();
					list.add(obj);
					List<ScheduleMovementSetting> statuslist = oidsmsetService.tapScheduleSettingExecuteQuery();
					String statusValue = statuslist.isEmpty() ? null : statuslist.get(0).getSettingValue();
					if (obj.getEventDate() != null && statusValue != null &&  statusValue.contains(obj.getEventStatus()) && !obj.getConflictFlag()) {
						conflictValue = oidstabsRepository.offSchCheckScheduleConflict(obj);
						logger.info(logger.getName().getClass()+" offSchCheckScheduleConflict");
						if (conflictValue > 0) {
							return obj.getEventId();
						}
						if (conflictValue == 0) {
							return 0;
						}
					}

					try {
						OffenderCaseNotes notes = new OffenderCaseNotes();
						BeanUtils.copyProperties(obj, notes);
						OffenderIndSchedules schedules = new OffenderIndSchedules();
						BeanUtils.copyProperties(obj, schedules);
						schedules.setEventId(obj.getEventId());
						schedules.setCommentText(obj.getCommentText());
						schedules.setEventOutcome(obj.getEventOutcome());
						schedules.setInChargeStaffId(obj.getInChargeStaffId());
						schedules.setAgyLocId(obj.getAgyLocId());
						schedules.setEndTime(obj.getEndTime());
						schedules.setStartTime(obj.getStartTime());
						schedules.setEventDate(obj.getEventDate());
						list = offenderIndSchedulesT2Service.offenderIndSchedulesT2Tgr(list);
						logger.info(logger.getName().getClass()+" offenderIndSchedulesT2Tgr");
						list = offenderIndSchedulesT1Service.offenderIndSchedulesT1Tgn(list);
						logger.info(logger.getName().getClass()+" offenderIndSchedulesT1Tgn");
						offenderIndSchedulesTwfService.offenderIndSchedulesTwfTgr(obj, "UPDATING");
						logger.info(logger.getName().getClass()+" offenderIndSchedulesTwfTgr");
						liReturn = oidstabsRepository.offSchedulesUpdateVOffenderAllSchedules(list);
						logger.info(logger.getName().getClass()+" offSchedulesUpdateVOffenderAllSchedules");
						offenderIndSchedulesT4Service.offenderIndSchedulesT4(notes, schedules, null);
						logger.info(logger.getName().getClass()+" offenderIndSchedulesT4");
					} catch (Exception e) {
						logger.error(e);
					}
				}
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				for (OffenderIndSchedules bean : commitBean.getDeleteList()) {
					bean.setModifyUserId(commitBean.getCreateUserId());
					try {
						VOffenderAllSchedules2 schedule = new VOffenderAllSchedules2();
						BeanUtils.copyProperties(bean, schedule);
						schedule.setOutcomeReasonCode(bean.getOutcomeReasonCode());
						schedule.setOffenderBookId(new BigDecimal(bean.getOffenderBookId()));
						schedule.setRecordSource("SCH");
						schedule.setEventClass(bean.getEventClass());
						schedule.setEventId(new BigDecimal(bean.getEventId()));
						liReturn = vOffenderAllSchedules2TdService.vOffenderAllSchedules2TdTgr(schedule, schedule,null);
						logger.info(logger.getName().getClass()+" vOffenderAllSchedules2TdTgr");

					} catch (Exception e) {
						logger.error("in delete:" + e);
					}
				}

			}
			
			if (commitBean.getAgyUpdateList() != null && commitBean.getAgyUpdateList().size() > 0
					&& !commitBean.getAgyUpdateList().isEmpty()) {
				OffenderIndSchedulesCommitBean commitBeanTemp = new OffenderIndSchedulesCommitBean();
				commitBeanTemp.setUpdateList(commitBean.getAgyUpdateList());
				commitBeanTemp.setCreateUserId(commitBean.getCreateUserId());
				liReturn = adressLocationsCommit(commitBeanTemp);
			}
			
		} catch(Exception e) {
			logger.error(this.getClass().getName()+"Exception in offSchedulesCommit :" + e);
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<VAgencyAddresses> agyAdrExecuteQuery(final VAgencyAddresses searchRecord) {
		List<VAgencyAddresses> returnList=oidstabsRepository.agyAdrExecuteQuery(searchRecord);
		if(returnList!=null && !returnList.isEmpty()) {
			for(VAgencyAddresses address:returnList) {
				if(address.getProvStateDesc() == null) {
					address.setProvStateDesc(address.getProvStateCode());
				}

				if(address.getCityName() == null) {
					address.setCityName(address.getCityCode());
				}
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAGY_ADR
	 *
	 * @
	 */
	@Transactional
	public Integer agyAdrCommit(final VAgencyAddressesCommitBean commitBean) {
		logger.info(logger.getName().getClass()+" agyAdrCommit method start");
		int liReturn = 0;
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				for(VAgencyAddresses bean:commitBean.getInsertList()) {
					bean.setCreateUserId(commitBean.getCreateUserId());
				}
				liReturn = oidstabsRepository.agyAdrInsertVAgencyAddresses(commitBean.getInsertList());
				logger.info(logger.getName().getClass()+" agyAdrInsertVAgencyAddresses");
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				for(VAgencyAddresses bean:commitBean.getUpdateList()) {
					bean.setModifyUserId(commitBean.getCreateUserId());
				}
				liReturn = oidstabsRepository.agyAdrUpdateVAgencyAddresses(commitBean.getUpdateList());
				logger.info(logger.getName().getClass()+" agyAdrUpdateVAgencyAddresses");
			}
		} catch(Exception e){
			logger.error(this.getClass().getName()+"Error in agyAdrCommit ", e);
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<VCorporateAddresses> busAdrExecuteQuery(final VCorporateAddresses searchRecord) {
		List<VCorporateAddresses> returnList=oidstabsRepository.busAdrExecuteQuery(searchRecord);
		if(returnList!=null && !returnList.isEmpty()) {
			for(VCorporateAddresses address:returnList) {
				if(address.getProvStateDesc() == null) {
					address.setProvStateDesc(address.getProvStateCode());
				}

				if(address.getCityName() == null) {
					address.setCityName(address.getCityCode());
				}
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstBUS_ADR
	 *
	 * @
	 */
	@Transactional
	public Integer busAdrCommit(final VCorporateAddressesCommitBean commitBean) {
		logger.info(logger.getName().getClass()+" busAdrCommit method start");
		int liReturn = 0;
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				liReturn = oidstabsRepository.busAdrInsertVCorporateAddresses(commitBean.getInsertList());
				logger.info(logger.getName().getClass()+" busAdrInsertVCorporateAddresses");
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				liReturn = oidstabsRepository.busAdrUpdateVCorporateAddresses(commitBean.getUpdateList());
				logger.info(logger.getName().getClass()+" busAdrUpdateVCorporateAddresses");
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
				liReturn = oidstabsRepository.busAdrDeleteVCorporateAddresses(commitBean.getDeleteList());
				logger.info(logger.getName().getClass()+" busAdrDeleteVCorporateAddresses");
			}
		} catch(Exception e){
			logger.error(this.getClass().getName()+"Error in busAdrCommit ", e);
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<VAddressUsages> othAdrExecuteQuery(final VAddressUsages searchRecord) {
		List<VAddressUsages> returnList=null;
		try {
			returnList=oidstabsRepository.othAdrExecuteQuery(searchRecord);
			if(returnList!=null && !returnList.isEmpty()) {
				for(VAddressUsages address:returnList) {
					if(address.getProvStateDesc() == null) {
						address.setProvStateDesc(address.getProvStateCode());
					}

					if(address.getCityName() == null) {
						address.setCityName(address.getCityCode());
					}
				}
			}
		} catch(Exception e){
			logger.error(this.getClass().getName()+"Error in othAdrExecuteQuery ", e);
			return null;
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOTH_ADR
	 *
	 * @
	 */
	@Transactional
	public Integer othAdrCommit(final VAddressUsagesCommitBean commitBean) {
		logger.info(logger.getName().getClass()+" othAdrCommit method start");
		int liReturn = 0;
		try {
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				for(VAddressUsages bean:commitBean.getInsertList()) {
					bean.setCreateUserId(commitBean.getCreateUserId());
				}
				liReturn = oidstabsRepository.othAdrInsertVAddressUsages(commitBean.getInsertList());
				logger.info(logger.getName().getClass()+" othAdrInsertVAddressUsages ");
			}
			if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				for(VAddressUsages bean:commitBean.getUpdateList()) {
					bean.setModifyUserId(commitBean.getCreateUserId());
				}
				liReturn = oidstabsRepository.othAdrUpdateVAddressUsages(commitBean.getUpdateList());
				logger.info(logger.getName().getClass()+" othAdrUpdateVAddressUsages ");
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
				liReturn = oidstabsRepository.othAdrDeleteVAddressUsages(commitBean.getDeleteList());
				logger.info(logger.getName().getClass()+" othAdrDeleteVAddressUsages ");
			}
		} catch(Exception e){
			logger.error(this.getClass().getName()+"Error in othAdrCommit ", e);
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<VPhones> agyPhonesExecuteQuery(final VPhones searchRecord) {
		try {
			return oidstabsRepository.agyPhonesExecuteQuery(searchRecord);
		} catch(Exception e){
			logger.error(this.getClass().getName()+"Error in agyPhonesExecuteQuery ", e);
		}
		return null;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAGY_PHONES
	 *
	 * @
	 */
	@Transactional
	public Integer agyPhonesCommit(final VPhonesCommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<VPhones> busPhonesExecuteQuery(final VPhones searchRecord) {
		return oidstabsRepository.busPhonesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstBUS_PHONES
	 *
	 * @
	 */
	@Transactional
	public Integer busPhonesCommit(final VPhonesCommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<VPhones> othPhonesExecuteQuery(final VPhones searchRecord) {
		return oidstabsRepository.othPhonesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOTH_PHONES
	 *
	 * @
	 */
	@Transactional
	public Integer othPhonesCommit(final VPhonesCommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<MovementReasons> rgSubTypeRecordGroup(String type) {
		List<MovementReasons> list = oidstabsRepository.rgSubTypeRecordGroup(type);
		if (list != null && !list.isEmpty() && list.size() > 0) {
			list.forEach(bo -> {
				if (ApplicationConstants.NFLAG.equalsIgnoreCase(bo.getActiveFlag())) {
					bo.setCanDisplay(false);
				} else {
					bo.setCanDisplay(true);
				}
			});
		}
		return list;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgEscortRecordGroup() {
		return oidstabsRepository.rgEscortRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgTransportRecordGroup() {
		return oidstabsRepository.rgTransportRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgStatusRecordGroup() {
		return oidstabsRepository.rgStatusRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<VCorporateAddresses> rgCorpLocRecordGroup() {
		return oidstabsRepository.rgCorpLocRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<VAgencyAddresses> rgAgyLocRecordGroup() {
		return oidstabsRepository.rgAgyLocRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<VAddressUsages> rgOthLocRecordGroup(final String rootOffenderId) {
		return oidstabsRepository.rgOthLocRecordGroup(rootOffenderId);

	}

	public Integer calculateDays(final String eventDate, final String returnDate) {
		return oidstabsRepository.calculateDays(eventDate, returnDate);
	}

	public Object calculateHours(final String startTime, final String returnTime) {
		return oidstabsRepository.calculateHours(startTime, returnTime);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_SCHEDULES
	 *
	 * @
	 */
	@Transactional
	public Integer adressLocationsCommit(final OffenderIndSchedulesCommitBean commitBean) {
		logger.info(logger.getName().getClass()+" adressLocationsCommit method start");
		int liReturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for(OffenderIndSchedules bean:commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			OffenderCaseNotes notes = new OffenderCaseNotes();
			OffenderIndSchedules schedules = new OffenderIndSchedules();
			List<OffenderIndSchedules> list = new ArrayList<OffenderIndSchedules>();
			try {
				list = offenderIndSchedulesT2Service.offenderIndSchedulesT2Tgr(commitBean.getUpdateList());
				logger.info(this.getClass().getName()+ "offenderIndSchedulesT2Tgr method call");
				list = offenderIndSchedulesT1Service.offenderIndSchedulesT1Tgn(list);
				logger.info(this.getClass().getName()+ "offenderIndSchedulesT1Tgn method call");
				for (OffenderIndSchedules bean : list) {
					bean.setModifyUserId(commitBean.getCreateUserId());
					offenderIndSchedulesTwfService.offenderIndSchedulesTwfTgr(bean, "UPDATING");
					logger.info(this.getClass().getName()+ "offenderIndSchedulesTwfTgr method call");
					BeanUtils.copyProperties(bean, notes);
					BeanUtils.copyProperties(bean, schedules);
				}

				liReturn = oidstabsRepository.adressLocationsUpdateQuery(list);
				logger.info(this.getClass().getName()+ "adressLocationsUpdateQuery method call");
				try {
					offenderIndSchedulesT4Service.offenderIndSchedulesT4(notes, schedules, null);
					logger.info(this.getClass().getName()+ "offenderIndSchedulesT4 method call");
				} catch (Exception e) {
					logger.error("Exception in adressLocationsCommit in trigger offenderIndSchedulesT4Service" + e);
				}
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return liReturn;
	}

	public Integer offSchCheckScheduleConflict(final OffenderIndSchedules obj) {
		return oidstabsRepository.offSchCheckScheduleConflict(obj);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 * @
	 */
	@Transactional
	public Integer offenderSchedulesCommit(final OffenderIndSchedulesCommitBean commitBean) {
		logger.info(logger.getName().getClass()+" offenderSchedulesCommit method start");
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (final OffenderIndSchedules obj : commitBean.getInsertList()) {
				liReturn = tagScheduleService.createSchedule(obj);
				logger.info(logger.getName().getClass()+" createSchedule");

			}
		}
		return liReturn;
	}

	@Override
	public String checkNonAssociations(OffenderIndSchedulesCommitBean commitBean) {
		logger.info(logger.getName().getClass()+" checkNonAssociations method start");
		String returnMessage = "";
		try {
			List<OffenderIndSchedules>  OffenderIndSchedulesFinalList = new ArrayList<OffenderIndSchedules>();
			if (commitBean != null && commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				OffenderIndSchedulesFinalList.addAll(commitBean.getInsertList());
			}
			if (commitBean != null && commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
				OffenderIndSchedulesFinalList.addAll(commitBean.getUpdateList());
			}
			returnMessage=getNonAssociationsData(OffenderIndSchedulesFinalList, commitBean.getCreateUserId());
			logger.info(logger.getName().getClass()+" getNonAssociationsData");
		} catch(Exception e){
			logger.error(this.getClass().getName()+"Error in checkNonAssociations ", e);
		}
		return returnMessage;
	}



	public String getFinalMessageString(final String messageData) {
		String returnMessage = "";
		try {
			returnMessage = "oidstabs.nonassociationconflictmsg \n\n"
					+ messageData + " \n oidstabs.doyouwanttocontinue ";
		} catch(Exception e){
			logger.error(this.getClass().getName()+"Error in getFinalMessageString ", e);
		}
		return returnMessage;
	}

	public String getNonAssociationsData(List<OffenderIndSchedules> offenderIndSchedules, String createUserId) {
		logger.info(logger.getName().getClass()+" getNonAssociationsData method start");
		String returnMessage = "";
		String individualNonAssociationMessages = "";
		String gandNonAssociationMessages = "";
		try {
			if (offenderIndSchedules != null && offenderIndSchedules.size()>0 && offenderIndSchedules.get(0)!= null && offenderIndSchedules.get(0).getOffenderBookId() != null) {
				List<OffenderNaDetails> nonAssociationsList = nonAssociationService.getNonAssociationOffenderList(offenderIndSchedules.get(0).getOffenderBookId());
				logger.info(logger.getName().getClass()+" getNonAssociationOffenderList");
				if(nonAssociationsList != null && nonAssociationsList.size()>0) {
					for(OffenderIndSchedules vOffSch : offenderIndSchedules) {
						for (OffenderNaDetails obj : nonAssociationsList) {
							List<OffenderIndSchedules> offenderIndNonAssocaiotnSchedulesList=new ArrayList<>();
							if("SCH".equals(vOffSch.getEventStatus())|| "PEN".equals(vOffSch.getEventStatus())) {
								offenderIndNonAssocaiotnSchedulesList = oidstabsRepository.checkOffenderScheduleConflicts(obj.getNsOffenderBookId(), vOffSch);
							}
							logger.info(logger.getName().getClass()+" checkOffenderScheduleConflicts");
							if (offenderIndNonAssocaiotnSchedulesList != null && offenderIndNonAssocaiotnSchedulesList.size() > 0) {
								String message = "";
								VHeaderBlock bean = ocdprogrRepository.ocdprogrGetOffenderNames(obj.getNsOffenderBookId(), createUserId);
								logger.info(logger.getName().getClass()+" ocdprogrGetOffenderNames");
								message = bean.getLastName() + " " + bean.getFirstName() + ", " + bean.getOffenderIdDisplay() + "\n";
								for (OffenderIndSchedules offenderIndNonAssocaiotnSchedule : offenderIndNonAssocaiotnSchedulesList) {
									Date returnDate = offenderIndNonAssocaiotnSchedule.getReturnDate();
									Date returnTime = offenderIndNonAssocaiotnSchedule.getReturnTime();
									java.util.Date stDate = offenderIndNonAssocaiotnSchedule.getStartTime();
									java.util.Date releaseDate = offenderIndNonAssocaiotnSchedule.getEventDate();
									SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
									String startTime = stDate != null ? sdf.format(stDate) : "";
									String returnTim = returnTime != null ? sdf.format(returnTime) : "";
									SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
									String appDate = releaseDate != null ? sdf1.format(releaseDate) : "";
									String returnDat = returnDate != null ? sdf1.format(returnDate) : "";
									message = message + appDate + ", " + startTime+ ", " +  returnDat +", " +returnTim + "\n";
									individualNonAssociationMessages = individualNonAssociationMessages + message;
								}
								individualNonAssociationMessages = individualNonAssociationMessages + "\n";
							}
						}
					}
				}
				if(individualNonAssociationMessages != null && !individualNonAssociationMessages.equalsIgnoreCase("")) {
					individualNonAssociationMessages = "oidstabs.indinonassocconflict:\n\n" + individualNonAssociationMessages;
				}
				
				List<OffenderStgAffiliations> offenderStgAffiliationsList = nonAssociationService.getOffendersOfNonAssociationGroup(BigDecimal.valueOf(offenderIndSchedules.get(0).getOffenderBookId()));
				logger.info(logger.getName().getClass()+" getOffendersOfNonAssociationGroup");				
				if(offenderStgAffiliationsList != null && offenderStgAffiliationsList.size()>0) {
					for(OffenderStgAffiliations offenderStgAffiliations : offenderStgAffiliationsList) {
						for(OffenderIndSchedules offenderIndSchedule : offenderIndSchedules) {
							List<OffenderIndSchedules> offenderIndNonAssocaiotnSchedulesList  =new ArrayList<OffenderIndSchedules>();
							if("SCH".equals(offenderIndSchedule.getEventStatus()) || "PEN".equals(offenderIndSchedule.getEventStatus())) {								
								offenderIndNonAssocaiotnSchedulesList  = oidstabsRepository.checkOffenderScheduleConflicts(offenderStgAffiliations.getOffenderBookId(), offenderIndSchedule);
							}
							logger.info(logger.getName().getClass()+" checkOffenderScheduleConflicts");				
							if (offenderIndNonAssocaiotnSchedulesList != null && offenderIndNonAssocaiotnSchedulesList.size() > 0) {
								VHeaderBlock bean = ocdprogrRepository.ocdprogrGetOffenderNames(offenderStgAffiliations.getOffenderBookId(), createUserId);
								logger.info(logger.getName().getClass()+" ocdprogrGetOffenderNames");				
								String message = "";
								message = bean.getLastName() + " " + bean.getFirstName() + ", " + bean.getOffenderIdDisplay() + "\n";							
								for (OffenderIndSchedules offenderIndNonAssocaiotnSchedule : offenderIndNonAssocaiotnSchedulesList) {
									Date returnDate = offenderIndNonAssocaiotnSchedule.getReturnDate();
									Date returnTime = offenderIndNonAssocaiotnSchedule.getReturnTime();
									java.util.Date stDate = offenderIndNonAssocaiotnSchedule.getStartTime();
									java.util.Date releaseDate = offenderIndNonAssocaiotnSchedule.getEventDate();
									SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
									String startTime = stDate != null ? sdf.format(stDate) : "";
									String returnTim = returnTime != null ? sdf.format(returnTime) : "";
									SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
									String appDate = releaseDate != null ? sdf1.format(releaseDate) : "";
									String returnDat = returnDate != null ? sdf1.format(returnDate) : "";
									message = message + appDate + ", " + startTime+ ", " +  returnDat +", " +returnTim + "\n";
								}
								gandNonAssociationMessages = gandNonAssociationMessages + message + "\n";
							}
						}

					}
				}

				if(gandNonAssociationMessages != null && !gandNonAssociationMessages.equalsIgnoreCase("")) {
					gandNonAssociationMessages = "oidstabs.gangnonassocconflict:\n\n" + gandNonAssociationMessages;
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

	@Override
	public List<ReferenceCodes> rgPurposeRecordGroup(String reason) {
			List<ReferenceCodes> list = oidstabsRepository.rgPurposeRecordGroup(reason);
			if (list != null && !list.isEmpty() && list.size() > 0) {
				list.forEach(bo -> {
					if ("N".equalsIgnoreCase(bo.getActiveFlag())) {
						bo.setCanDisplay(false);
					} else {
						bo.setCanDisplay(true);
					}
				});
			}
			return list;
		}
}
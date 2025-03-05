package net.syscon.s4.inst.movementexternal.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.intake.beans.MovementReasons;
import net.syscon.s4.inst.movementexternal.OidtrwjuRepository;
import net.syscon.s4.inst.movementexternal.OidtrwjuService;
import net.syscon.s4.pkgs.tag_prison_activities.TagPrisonActivitiesService;
import net.syscon.s4.triggers.OffenderExtMovementsTwfService;
import net.syscon.s4.triggers.OffenderExternalMovementT13Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT1Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT3Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT5Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT6Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT8Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT9Service;
import net.syscon.s4.triggers.impl.OffExtMvVineIntfTrgServiceImpl;


/**
 * Class OidtrwjuServiceImpl
 */
@Service
public class OidtrwjuServiceImpl extends BaseBusiness implements OidtrwjuService {

	@Autowired
	private OidtrwjuRepository oidtrwjuRepository;
	
	@Autowired
	private OffenderExternalMovementsT9Service offenderExternalMovementsT9Service;
	
	@Autowired
	private OffenderExternalMovementsT6Service offenderExternalMovementsT6Service;
	
	@Autowired
	private OffExtMvVineIntfTrgServiceImpl offExtMvVineIntfTrgServiceImpl;
	
	
	@Autowired
	private OffenderExtMovementsTwfService offenderExtMovementsTwfService;
	
	@Autowired
	private OffenderExternalMovementsT5Service offenderExtMovementsT5Service;
	
	@Autowired
	private OffenderExternalMovementsT8Service offenderExtMovementsT8Service;
	
	@Autowired
	private OffenderExternalMovementsT3Service offenderExternalMovementsT3Service;
	
	@Autowired
	private OffenderExternalMovementsT1Service offenderExternalMovementsT1Service;
	
	@Autowired
	private OffenderExternalMovementT13Service offenderExternalMovementT13Service;
	
	@Autowired
	private TagPrisonActivitiesService tagPrisonActivitiesService;
	
	private static Logger logger = LogManager.getLogger(OidtrwjuServiceImpl.class.getName());

	/**
	 * Creates new OidtrwjuBusiness class Object
	 */
	public OidtrwjuServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public OffenderExternalMovements offempreinsertc(final OffenderExternalMovements paramBean) {
		return oidtrwjuRepository.offempreinsertc(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> cgfkchkoffemoffemrefmovc(final ReferenceCodes paramBean) {
		return oidtrwjuRepository.cgfkchkoffemoffemrefmovc(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> cgfkchkoffemoffemmoversc(final MovementReasons paramBean) {
		return oidtrwjuRepository.cgfkchkoffemoffemmoversc(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> cgfkchkoffemoffemmovetoc(final MovementReasons paramBean) {
		return oidtrwjuRepository.cgfkchkoffemoffemmovetoc(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> cgfkchkoffemoffemagylocc(final AgencyLocations paramBean) {
		return oidtrwjuRepository.cgfkchkoffemoffemagylocc(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Dual> cgwhennewforminstancec(final Dual paramBean) {
		return oidtrwjuRepository.cgwhennewforminstancec(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffenderExternalMovements> offEmSearchOffenderExternalMovements(
			final OffenderExternalMovements searchRecord) {
		return oidtrwjuRepository.offEmSearchOffenderExternalMovements(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderExternalMovements
	 *
	 * @
	 */public Integer offEmCommit(final OffenderExternalMovementsCommitBean commitBean)
	 {
		 int liReturn = 0;
	if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
		for(OffenderExternalMovements bean:commitBean.getInsertList()) {
			bean.setCreateUserId(commitBean.getCreateUserId());
		}
		liReturn =  offEmInsertOffenderExternalMovements(commitBean.getInsertList());
	}
	if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
		for(OffenderExternalMovements bean:commitBean.getUpdateList()) {
			bean.setModifyUserId(commitBean.getCreateUserId());
		}
		liReturn =  offEmUpdateOffenderExternalMovements(commitBean.getUpdateList());
	}
	return liReturn;
	 }
	private Integer offEmUpdateOffenderExternalMovements(final List<OffenderExternalMovements> updateList) {
		Integer retVal=null;
		for(OffenderExternalMovements data:updateList){
			if(data.getMovementSeq()!=null) {				
				List<OffenderExternalMovements> list=new ArrayList<OffenderExternalMovements>();
				list.add(data);
				offenderExternalMovementsT9Service.offenderExternalMovementsT9(data);
				retVal=oidtrwjuRepository.offEmUpdateOffenderExternalMovements(updateList);
				offenderExtMovementsT5Service.offenderExternalMovementsT5(data);
				OffenderExternalMovements old=oidtrwjuRepository.getOffenderExternalMovements(data.getOffenderBookId(),data.getMovementSeq());
				if (old != null) {
					net.syscon.s4.im.beans.MovementReasons oldRef = new net.syscon.s4.im.beans.MovementReasons();
					BeanUtils.copyProperties(old, oldRef);
					net.syscon.s4.im.beans.MovementReasons newRef = new net.syscon.s4.im.beans.MovementReasons();
					BeanUtils.copyProperties(data, newRef);
					offenderExternalMovementsT1Service.OffenderExternalMovementsT1Trigger(oldRef, newRef,
							old.getOffenderBookId(), "UPDATING");
				}
				offenderExternalMovementT13Service.OffenderExternalMovementT1(data);
			}
		}
		return retVal;
	}

	@Transactional
	public Integer offEmInsertOffenderExternalMovements(final List<OffenderExternalMovements> lstOffenderExternalMovements) {
		Integer returnVal=null;
		for(final OffenderExternalMovements obj:lstOffenderExternalMovements){
			Integer seq = oidtrwjuRepository.offExternalMovmentssgetMaxBookIdMovmentSeq(obj.getOffenderBookId());
			for (int i = 0; i < lstOffenderExternalMovements.size(); i++) {
				obj.setMovementSeq(Long.valueOf(seq));
				seq = seq + 1;
				List<OffenderExternalMovements> list=new ArrayList<OffenderExternalMovements>();
				list.add(obj);
				offenderExternalMovementsT9Service.offenderExternalMovementsT9(obj);
				returnVal=oidtrwjuRepository.offEmInsertOffenderExternalMovements(list);
				offenderExternalMovementsT6Service.offenderExternalMovementsT6(obj);
				offenderExternalMovementsT3Service.offenderExternalMovementsT3Trigger(obj, null);
				offenderExtMovementsTwfService.offenderExternalMovementsTrigger(obj);
				offenderExtMovementsT5Service.offenderExternalMovementsT5(obj);
				offenderExtMovementsT8Service.updateObligationWR(obj.getOffenderBookId(), obj.getMovementType(),obj.getCreateUserId());
				offExtMvVineIntfTrgServiceImpl.offExtMvVineIntfTrg(obj);
				net.syscon.s4.im.beans.MovementReasons newRef=new net.syscon.s4.im.beans.MovementReasons();
				BeanUtils.copyProperties(obj, newRef);
				newRef.setModifyUserId(obj.getCreateUserId());
				offenderExternalMovementsT1Service.OffenderExternalMovementsT1Trigger(null, newRef, obj.getOffenderBookId(), "INSERTING");
				offenderExternalMovementT13Service.OffenderExternalMovementT1(obj);
			
			}
			}
		return returnVal;
	}
	

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<SystemProfiles> sysPflSearchSystemProfiles(final SystemProfiles searchRecord) {
		return oidtrwjuRepository.sysPflSearchSystemProfiles(searchRecord);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<MovementReasons> cgfkOffEmMovementReasonCoRecordGroup() {
		List<MovementReasons> returnList=null;
		returnList= oidtrwjuRepository.cgfkOffEmMovementReasonCoRecordGroup();
		for (final MovementReasons obj:returnList) {
			if (obj.getMovementReasonCode() != null) {
				obj.setCode(obj.getMovementReasonCode());
			}
		}
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
	 * @
	 */
	public List<AgencyLocations> cgfkOffEmToAgyLocIdRecordGroup(final String agyLocId) {
		List<AgencyLocations> returnList = null;
		returnList = oidtrwjuRepository.cgfkOffEmToAgyLocIdRecordGroup(agyLocId);
		for (final AgencyLocations obj:returnList) {
			if (obj.getAgyLocId() != null) {
				obj.setCode(obj.getAgyLocId());
			}
		}
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
	
	public Integer checkWaitListAndLocation(final OffenderExternalMovements bean){
			return oidtrwjuRepository.checkWaitListAndLocation(bean);
			
		}
	public Integer suspendAllocations(final OffenderExternalMovements bean){
		Integer returnValue=null;
		try {
			tagPrisonActivitiesService.suspendAllocations(BigDecimal.valueOf(bean.getOffenderBookId()),bean.getMovementDate(),bean.getCreateUserId());
			returnValue=1;
		} catch (Exception e) {
			logger.error("Exception in suspendAllocations: ", e);
			returnValue = 0;
		}
		return returnValue;
		
	}

	public Integer endWaitlistAndAllocations(final OffenderExternalMovements bean){
		Integer returnValue=null;
		try {
			tagPrisonActivitiesService.endWaitlistAndAllocations(BigDecimal.valueOf(bean.getOffenderBookId()),
					bean.getMovementDate(), "TRF", bean.getCreateUserId());
			returnValue=1;
		} catch (Exception e) {
			logger.error("Exception in endWaitlistAndAllocations: ", e);
			returnValue = 0;
		}
		return returnValue;
	}

}
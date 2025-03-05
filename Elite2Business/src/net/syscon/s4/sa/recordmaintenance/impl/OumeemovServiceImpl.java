package net.syscon.s4.sa.recordmaintenance.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.sa.recordmaintenance.OumeemovRepository;
import net.syscon.s4.sa.recordmaintenance.OumeemovService;
import net.syscon.s4.triggers.OffExtMvVineIntfTrgService;
import net.syscon.s4.triggers.OffenderExtMovementsTwfService;
import net.syscon.s4.triggers.OffenderExternalMovementT13Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT1Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT3Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT5Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT6Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT8Service;
import net.syscon.s4.triggers.OffenderExternalMovementsT9Service;

/**
 * Class OumeemovServiceImpl
 */
@Service
public class OumeemovServiceImpl extends BaseBusiness implements OumeemovService {

	@Autowired
	private OumeemovRepository oumeemovRepository;
	
	@Autowired
	private OffenderExternalMovementsT1Service offenderExternalMovementsT1Service;
	
	@Autowired
	private OffenderExternalMovementsT6Service offenderExternalMovementsT6Service;
	
	@Autowired
	private OffenderExternalMovementsT3Service offenderExternalMovementsT3Service;
	
	@Autowired
	private OffenderExternalMovementsT5Service offenderExternalMovementsT5Service;
	
	@Autowired
	private OffenderExternalMovementsT8Service offenderExternalMovementsT8Service;
	
	@Autowired
	private OffenderExternalMovementsT9Service offenderExternalMovementsT9Service;
	
	@Autowired
	private OffExtMvVineIntfTrgService offExtMvVineIntfTrgService;
	
	@Autowired
	private OffenderExternalMovementT13Service offenderExternalMovementT13Service;
	
	@Autowired
	private OffenderExtMovementsTwfService offenderExtMovementsTwfService;
	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public String ValidateSeqNum(OffenderExternalMovements paramBean) {
		return oumeemovRepository.validateSeqNum(paramBean);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderExternalMovements> offEmExecuteQuery(OffenderExternalMovements searchRecord) {
		return oumeemovRepository.offEmExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_EM
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offEmCommit(OffenderExternalMovementsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for(OffenderExternalMovements obj:commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				offenderExternalMovementsT9Service.offenderExternalMovementsT9(obj);
				liReturn = oumeemovRepository.offEmInsertOffenderExternalMovements(obj);
				if(liReturn != 0) {
					MovementReasons newRef = new MovementReasons();
					newRef.setMovementType(obj.getMovementType());
					newRef.setMovementReasonCode(obj.getMovementReasonCode());
					newRef.setModifyUserId(obj.getCreateUserId());
					offenderExternalMovementsT1Service.OffenderExternalMovementsT1Trigger(null,newRef,obj.getOffenderBookId(),"INSERTING");
					offenderExternalMovementsT6Service.offenderExternalMovementsT6(obj);
					offenderExternalMovementsT3Service.offenderExternalMovementsT3Trigger(obj, null);
					/*
					 * This method does'nt work as it has some XML functions and DBMS_AQ.deque
					 */
//					offenderExtMovementsTwfService.offenderExternalMovementsTrigger(obj);
					offenderExternalMovementsT5Service.offenderExternalMovementsT5(obj);
					offenderExternalMovementsT8Service.updateObligationWR(obj.getOffenderBookId(), obj.getMovementType(),commitBean.getCreateUserId());
					offExtMvVineIntfTrgService.offExtMvVineIntfTrg(obj);
					offenderExternalMovementT13Service.OffenderExternalMovementT1(obj);
				}
			}
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for(OffenderExternalMovements obj:commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				offenderExternalMovementsT9Service.offenderExternalMovementsT9(obj);
			}
			liReturn = oumeemovRepository.offEmUpdateOffenderExternalMovements(commitBean.getUpdateList());
			for(OffenderExternalMovements obj:commitBean.getUpdateList()) {
				OffenderExternalMovements oldMovType = oumeemovRepository.getMovementType(obj.getOffenderBookId(), obj.getMovementSeq());
				MovementReasons oldRef = new MovementReasons();
				oldRef.setMovementType(oldMovType.getMovementType());
				oldRef.setMovementReasonCode(oldMovType.getMovementReasonCode());
				MovementReasons newRef = new MovementReasons();
				newRef.setMovementType(obj.getMovementType());
				newRef.setMovementReasonCode(obj.getMovementReasonCode());
				newRef.setModifyUserId(obj.getModifyUserId());
				try {
				offenderExternalMovementsT1Service.OffenderExternalMovementsT1Trigger(oldRef,newRef,obj.getOffenderBookId(),"UPDATING");
				offenderExternalMovementsT5Service.offenderExternalMovementsT5(obj);// to be checked
				offenderExternalMovementT13Service.OffenderExternalMovementT1(obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
		// deleteRecords
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oumeemovRepository.offEmDeleteOffenderExternalMovements(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkOffEmFromCityRecordGroup() {
		return oumeemovRepository.cgfkOffEmFromCityRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> cgfkOffEmFromAgyLocIdRecordGroup() {
		return oumeemovRepository.cgfkOffEmFromAgyLocIdRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<AgencyLocations> cgfkOffEmToAgyLocIdRecordGroup() {
		return oumeemovRepository.cgfkOffEmToAgyLocIdRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkOffEmMovementTypeRecordGroup() {
		return oumeemovRepository.cgfkOffEmMovementTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkOffEmDirectionCodeRecordGroup() {
		return oumeemovRepository.cgfkOffEmDirectionCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<MovementReasons> cgfkOffEmMovementReasonCoRecordGroup(String movementType) {
		return oumeemovRepository.cgfkOffEmMovementReasonCoRecordGroup(movementType);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkOffEmToCityRecordGroup() {
		return oumeemovRepository.cgfkOffEmToCityRecordGroup();

	}

}
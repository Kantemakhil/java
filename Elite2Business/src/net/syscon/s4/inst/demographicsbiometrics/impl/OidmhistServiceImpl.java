package net.syscon.s4.inst.demographicsbiometrics.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderMilitaryDiscActions;
import net.syscon.s4.im.beans.OffenderMilitaryDiscActionsCommitBean;
import net.syscon.s4.im.beans.OffenderMilitaryRecords;
import net.syscon.s4.im.beans.OffenderMilitaryRecordsCommitBean;
import net.syscon.s4.im.beans.OffenderMilitaryTechSpecs;
import net.syscon.s4.im.beans.OffenderMilitaryTechSpecsCommitBean;
import net.syscon.s4.im.beans.OffenderMilitaryWarZones;
import net.syscon.s4.im.beans.OffenderMilitaryWarZonesCommitBean;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.demographicsbiometrics.OidmhistRepository;
import net.syscon.s4.inst.demographicsbiometrics.OidmhistService;

/**
 * Class OidmhistServiceImpl
 */
@Service
public class OidmhistServiceImpl extends BaseBusiness implements OidmhistService {

	@Autowired
	private OidmhistRepository oidmhistRepository;

	/**
	 * Creates new OidmhistServiceImpl class Object
	 */
	public OidmhistServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 *
	 * @return List<OffenderMilitaryRecords>
	 */
	public List<OffenderMilitaryRecords> offBkgOnCheckDeleteMaster(final OffenderMilitaryRecords paramBean) {
		return oidmhistRepository.offBkgOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 *
	 * @return ReferenceCodes
	 */
	public ReferenceCodes offMrPostQuery(final ReferenceCodes paramBean) {
		return oidmhistRepository.offMrPostQuery(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 *
	 * @return ReferenceCodes
	 */
	public OffenderMilitaryWarZones offMrOnCheckDeleteMaster(final OffenderMilitaryWarZones paramBean) {
		return oidmhistRepository.offMrOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 *
	 * @return ReferenceCodes
	 */
	public ReferenceCodes disActPostQuery(final ReferenceCodes paramBean) {
		return oidmhistRepository.disActPostQuery(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 *
	 * @return ReferenceCodes
	 */
	public ReferenceCodes techSpecPostQuery(final ReferenceCodes paramBean) {
		return oidmhistRepository.techSpecPostQuery(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 *
	 * @return ReferenceCodes
	 */
	public ReferenceCodes warZonesPostQuery(final ReferenceCodes paramBean) {
		return oidmhistRepository.warZonesPostQuery(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 *
	 * @return ReferenceCodes
	 */
	public ReferenceCodes cgfkchkOffMrOffMrRef(final ReferenceCodes paramBean) {
		return oidmhistRepository.cgfkchkOffMrOffMrRef(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 *
	 * @return List<Dual>
	 */
	public List<Dual> cgwhenNewFormInstance(final SysDual paramBean) {
		return oidmhistRepository.cgwhenNewFormInstance(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @return List<OffenderMilitaryRecords>
	 */
	public List<OffenderMilitaryRecords> offMrExecuteQuery(final OffenderMilitaryRecords searchRecord) {
		return oidmhistRepository.offMrExecuteQuery(searchRecord);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 * @return Integer
	 */
	@Transactional
	public Integer offMrCommit(final OffenderMilitaryRecordsCommitBean commitBean) {
		int liReturn = 0;
		Long offBookid = null;
		Integer militarySeq = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			List<OffenderMilitaryRecords> recordSavingObject = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (int i = 0; i < commitBean.getInsertList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final OffenderMilitaryRecords offenderPropertyItemObj = commitBean.getInsertList().get(i);
					offBookid = offenderPropertyItemObj.getOffenderBookId();
					militarySeq = oidmhistRepository.offmrPreInsertc(offBookid);
					offenderPropertyItemObj.setMilitarySeq(Long.valueOf(militarySeq));
					offenderPropertyItemObj.setCreateUserId(commitBean.getCreateUserId());
					recordSavingObject.add(offenderPropertyItemObj);
					liReturn = oidmhistRepository.offMrInsertOffenderMilitaryRecords(recordSavingObject);
				}
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderMilitaryRecords bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oidmhistRepository.offMrUpdateOffenderMilitaryRecords(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oidmhistRepository.offMrDeleteOffenderMilitaryRecords(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @return List<OffenderMilitaryDiscActions>
	 */
	public List<OffenderMilitaryDiscActions> disActExecuteQuery(final OffenderMilitaryDiscActions searchRecord) {
		return oidmhistRepository.disActExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 * @return Integer
	 */
	@Transactional
	public Integer disActCommit(final OffenderMilitaryDiscActionsCommitBean commitBean) {
		int liReturn = 0;
		Long offBookid = null;
		Integer mltyDiscpSeq = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			List<OffenderMilitaryDiscActions> recordSavingObject = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (int i = 0; i < commitBean.getInsertList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final OffenderMilitaryDiscActions offenderPropertyItemObj = commitBean.getInsertList().get(i);
					offBookid = offenderPropertyItemObj.getOffenderBookId();
					mltyDiscpSeq = oidmhistRepository.offdisActPreInsertc(offBookid);
					offenderPropertyItemObj.setMltyDiscpSeq(Long.valueOf(mltyDiscpSeq));
					offenderPropertyItemObj.setCreateUserId(commitBean.getCreateUserId());
					recordSavingObject.add(offenderPropertyItemObj);
					liReturn = oidmhistRepository.disActInsertOffenderMilitaryDiscActions(recordSavingObject);
				}
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = oidmhistRepository.disActUpdateOffenderMilitaryDiscActions(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oidmhistRepository.disActDeleteOffenderMilitaryDiscActions(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @return List<OffenderMilitaryTechSpecs>
	 */
	public List<OffenderMilitaryTechSpecs> techSpecExecuteQuery(final OffenderMilitaryTechSpecs searchRecord) {
		return oidmhistRepository.techSpecExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 * @return Integer
	 */
	@Transactional
	public Integer techSpecCommit(final OffenderMilitaryTechSpecsCommitBean commitBean) {
		int liReturn = 0;
		Long offBookid = null;
		Integer mltyTechSeq = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			List<OffenderMilitaryTechSpecs> recordSavingObject = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (int i = 0; i < commitBean.getInsertList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final OffenderMilitaryTechSpecs offenderPropertyItemObj = commitBean.getInsertList().get(i);
					offBookid = offenderPropertyItemObj.getOffenderBookId();
					mltyTechSeq = oidmhistRepository.offtechSpecPreInsertc(offBookid);
					offenderPropertyItemObj.setMltyTechSeq(Long.valueOf(mltyTechSeq));
					offenderPropertyItemObj.setCreateUserId(commitBean.getCreateUserId());
					recordSavingObject.add(offenderPropertyItemObj);
					liReturn = oidmhistRepository.techSpecInsertOffenderMilitaryTechSpecs(recordSavingObject);
				}
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = oidmhistRepository.techSpecUpdateOffenderMilitaryTechSpecs(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oidmhistRepository.techSpecDeleteOffenderMilitaryTechSpecs(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @return List<OffenderMilitaryWarZones>
	 */
	public List<OffenderMilitaryWarZones> warZonesExecuteQuery(final OffenderMilitaryWarZones searchRecord) {
		return oidmhistRepository.warZonesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 * @return Integer
	 */
	@Transactional
	public Integer warZonesCommit(final OffenderMilitaryWarZonesCommitBean commitBean) {
		int liReturn = 0;
		Long offBookid = null;
		Integer warSeq = null;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			List<OffenderMilitaryWarZones> recordSavingObject = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (int i = 0; i < commitBean.getInsertList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final OffenderMilitaryWarZones offenderPropertyItemObj = commitBean.getInsertList().get(i);
					offBookid = offenderPropertyItemObj.getOffenderBookId();
					warSeq = oidmhistRepository.offwarZonesPreInsertc(offBookid);
					offenderPropertyItemObj.setWarSeq(Long.valueOf(warSeq));
					offenderPropertyItemObj.setCreateUserId(commitBean.getCreateUserId());
					recordSavingObject.add(offenderPropertyItemObj);
					liReturn = oidmhistRepository.warZonesInsertOffenderMilitaryWarZones(recordSavingObject);
				}
			}
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(bo -> {
				bo.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = oidmhistRepository.warZonesUpdateOffenderMilitaryWarZones(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			liReturn = oidmhistRepository.warZonesDeleteOffenderMilitaryWarZones(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @return List<SystemProfiles>
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return oidmhistRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param CommitBean
	 *
	 * @return Integer
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean CommitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgWarZoneRecordGroup() {
		return oidmhistRepository.rgWarZoneRecordGroup();

	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgMltyTechRecordGroup() {
		return oidmhistRepository.rgMltyTechRecordGroup();

	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgMilitaryRankRecordGroup() {
		 List<ReferenceCodes> refList = oidmhistRepository.rgMilitaryRankRecordGroup();
		if(Optional.ofNullable(refList).isPresent()) {
			refList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return refList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgMilitaryDischargeRecordGroup() {
		return oidmhistRepository.rgMilitaryDischargeRecordGroup();

	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgMilitaryBranchRecordGroup() {
		return oidmhistRepository.rgMilitaryBranchRecordGroup();

	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgDisciplinaryActionRecordGroup() {
		return oidmhistRepository.rgDisciplinaryActionRecordGroup();

	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgHighstRankRecordGroup() {
		return oidmhistRepository.rgHighstRankRecordGroup();

	}

}
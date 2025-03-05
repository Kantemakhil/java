package net.syscon.s4.inst.securitythreatgroups.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.StgRelationships;
import net.syscon.s4.im.beans.StgRelationshipsCommitBean;
import net.syscon.s4.inst.securitythreatgroups.OidstgaeRepository;
import net.syscon.s4.inst.securitythreatgroups.OidstgaeService;
import net.syscon.s4.pkgs.oms_stg.OmsStgService;

/**
 * Class OidstgaeServiceImpl
 */
@Service
public class OidstgaeServiceImpl extends BaseBusiness implements OidstgaeService {

	@Autowired
	private OidstgaeRepository oidstgaeRepository;
	
	@Autowired
	private OmsStgService omsStgService;

	private final String alice = "ALIANCE";
	private final String enmy = "ENEMY";
	private final Integer successCode = 1;

	OidstgaeServiceImpl() {
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public Long stgRltPreInsert(final Long paramBean) {
		return oidstgaeRepository.stgRltPreInsertPreInsert(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public Long stgRelationshipsPreInsert(final StgRelationships paramBean) {
		return oidstgaeRepository.stgRelationshipsPreInsert(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public Integer oidstgaeOnCommit(final StgRelationships paramBean) {
		return oidstgaeRepository.oidstgaeOnCommitOnCommit(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public String cgwhenNewFormInstance() {
		return oidstgaeRepository.cgwhennewforminstancestgLovLvlCur();
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public ReferenceCodes cgpostQueryRltReason(final ReferenceCodes paramBean) {
		return oidstgaeRepository.cgpostQueryRlt2Reason(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public SecurityThreatGroups cgpostQueryRltGroup(final SecurityThreatGroups paramBean) {
		return oidstgaeRepository.cgpostQueryRlt2Group(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public SecurityThreatGroups cgpostQueryRlt2Group(final SecurityThreatGroups paramBean) {
		return oidstgaeRepository.cgpostQueryRlt2Group(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public ReferenceCodes cgpostQueryRlt2Reason(final ReferenceCodes paramBean) {
		return oidstgaeRepository.cgpostQueryRlt2Reason(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * 
	 */
	public StgRelationships assAlianceStg(final StgRelationships paramBean) {
		return oidstgaeRepository.assaliancestgassAlianceStg(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<StgRelationships> stgRltExecuteQuery(final StgRelationships searchRecord) {
		searchRecord.setRelationshipType(alice);
		return oidstgaeRepository.stgRltExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTG_RLT
	 *
	 * 
	 */
	@Transactional(rollbackFor = Exception.class)
	public String stgRltCommit(final StgRelationshipsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			commitBean.getInsertList().forEach(data -> {
				data.setCreateUserId(commitBean.getCreateUserId());
				data.setRelationshipType(alice);
				final Integer count = oidstgaeOnCommit(data);
				if (count.compareTo(0) > 0) {
					throw new RuntimeException("oidstgae.rowalreadyexistwithsamecode");
				}
				final Long relationshipSeq = stgRltPreInsert(data.getStgId());
				if (relationshipSeq != null) {
					data.setRelationshipSeq(relationshipSeq);
					oidstgaeRepository.stgRltInsertStgRelationships(data);
					if ("Y".equals(data.getActiveFlag())) {
						assStgAliance(data);
					} else {
						deActivateAliance(data);
					}
				}
			});
			liReturn = successCode;
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			commitBean.getUpdateList().forEach(data -> {
				data.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = oidstgaeRepository.stgRltUpdateStgRelationships(commitBean.getUpdateList());
			commitBean.getUpdateList().forEach(data -> {
				if ("N".equals(data.getActiveFlag())) {
					deActivateAliance(data);
				} else {
					final Long rStgId = data.getRelatedStgId().longValue();
					data.setRelatedStgId(BigDecimal.valueOf(data.getStgId()));
					data.setStgId(rStgId);
					final Integer count = oidstgaeOnCommit(data);
					data.setStgId(data.getRelatedStgId().longValue());
					data.setRelatedStgId(BigDecimal.valueOf(rStgId));
					if (count.compareTo(0) == 0) {
						final Long relationshipSeq = stgRltPreInsert(data.getStgId());
						data.setRelationshipSeq(relationshipSeq);
						assStgAliance(data);
					} else {
						reActiveAliance(data);
					}
				}
			});
		}
		return String.valueOf(liReturn);
	}

	private void reActiveAliance(final StgRelationships data) {
		final Long relationShipId = data.getStgId();
		data.setStgId(data.getRelatedStgId().longValue());
		data.setRelatedStgId(BigDecimal.valueOf(relationShipId));
		oidstgaeRepository.reActivateAlianceUpdate(data);

	}

	private void deActivateAliance(final StgRelationships data) {
		final Long relationShipId = data.getStgId();
		data.setStgId(data.getRelatedStgId().longValue());
		data.setRelatedStgId(BigDecimal.valueOf(relationShipId));
		oidstgaeRepository.deActivateAlianceUpdate(data);

	}

	private void assStgAliance(final StgRelationships data) {
		final Long stgRelationshipSeq = oidstgaeRepository.assAlianceStgassAlianceStg(data.getRelatedStgId().longValue(),
				data.getStgId());
		data.setRelationshipSeq(stgRelationshipSeq);
		final Long relationId = data.getStgId();
		data.setStgId(data.getRelatedStgId().longValue());
		data.setRelatedStgId(BigDecimal.valueOf(relationId));
		oidstgaeRepository.stgRltInsertStgRelationships(data);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<StgRelationships> stgRelationshipsExecuteQuery(final StgRelationships searchRecord) {
		searchRecord.setRelationshipType(enmy);
		return oidstgaeRepository.stgRltExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTG_RELATIONSHIPS
	 *
	 * 
	 */
	@Transactional(rollbackFor = Exception.class)
	public String stgRelationshipsCommit(final StgRelationshipsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && !commitBean.getInsertList().isEmpty()) {
			commitBean.getInsertList().forEach(data -> {
				data.setCreateUserId(commitBean.getCreateUserId());
				data.setRelationshipType(enmy);
				final Integer count = oidstgaeOnCommit(data);
				if (count.compareTo(0) > 0) {
					throw new RuntimeException("oidstgae.rowalreadyexistwithsamecode");
				}
				final Long relationshipSeq = stgRelationshipsPreInsert(data);
				data.setRelationshipSeq(relationshipSeq);
				oidstgaeRepository.stgRltInsertStgRelationships(data);
				if ("Y".equals(data.getActiveFlag())) {
					omsStgService.nonaEnemychildToStg(data);
				}
			});
			liReturn = successCode;
		}
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			commitBean.getUpdateList().forEach(data->data.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = oidstgaeRepository.stgRltUpdateStgRelationships(commitBean.getUpdateList());
			commitBean.getUpdateList().forEach(data -> {
				if ("Y".equals(data.getActiveFlag())) {
					oidstgaeRepository.reActiveEnemy(data);
				} else {
					oidstgaeRepository.deActiveEnemy(data);
				}
			});
		}
		return String.valueOf(liReturn);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<SecurityThreatGroups> rgStg2RecordGroup(final BigDecimal stgId) {
		final List<SecurityThreatGroups> returnList = oidstgaeRepository.rgStg2RecordGroup(stgId);
		returnList.stream().forEach(data -> {
			if (!"Y".equals(data.getActiveFlag())) {
				data.setCanDisplay(false);
			}
			else {
				data.setCanDisplay(true);
			}
		});
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<SecurityThreatGroups> rgStg1RecordGroup(final BigDecimal stgId) {
		final List<SecurityThreatGroups> returnList = oidstgaeRepository.rgStg1RecordGroup(stgId);
		returnList.stream().forEach(data -> {
			if (!"Y".equals(data.getActiveFlag())) {
				data.setCanDisplay(false);
			}else {
				data.setCanDisplay(true);
			}
		});
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<SecurityThreatGroups> rgStg3RecordGroup(final BigDecimal stgId) {
		final List<SecurityThreatGroups> returnList = oidstgaeRepository.rgStg3RecordGroup(stgId);
		returnList.stream().forEach(data -> {
			if (!"Y".equals(data.getActiveFlag())) {
				data.setCanDisplay(false);
			}
		});
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> recReason2RecordGroup() {
		return oidstgaeRepository.recReason2RecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<SecurityThreatGroups> recStg2RecordGroup() {
		return oidstgaeRepository.recStg2RecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<SecurityThreatGroups> recStgRecordGroup() {
		return oidstgaeRepository.recStgRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> recReasonRecordGroup() {
		return oidstgaeRepository.recReasonRecordGroup();

	}

	public String groupLovRecordGroupNumber() {
		return oidstgaeRepository.cgwhenNewFormInstance();
	}

	@Override
	public BigDecimal stgRltGroupPostChange(final BigDecimal stgId, final BigDecimal relatedStgId) {
		return oidstgaeRepository.stgRltGroupPostChange(stgId, relatedStgId);
	}

	@Override
	public BigDecimal stgRltCheckBoxChange(final BigDecimal stgId, final BigDecimal relatedStgId) {
		return oidstgaeRepository.stgRltCheckBoxChange(stgId, relatedStgId);
	}

	@Override
	public BigDecimal stgRelationshipsCheckBoxChange(final BigDecimal stgId, final BigDecimal relatedStgId) {
		return oidstgaeRepository.stgRelationshipsCheckBoxChange(stgId, relatedStgId);
	}

}
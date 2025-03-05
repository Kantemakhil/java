package net.syscon.s4.globaloffenderrecords.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globaloffenderrecords.OidononaRepository;
import net.syscon.s4.globaloffenderrecords.OidononaService;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNaDetailsCommitBean;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.im.beans.OffenderNonAssociationsCommitBean;
import net.syscon.s4.im.beans.StgRelationships;
import net.syscon.s4.im.beans.StgRelationshipsCommitBean;

/**
 * Class OidononaServiceImpl
 */
@Service
public class OidononaServiceImpl extends BaseBusiness implements OidononaService {

	@Autowired
	private OidononaRepository oidononaRepository;

	/**
	 * 
	 * /**Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderNonAssociations> offNaExecuteQuery(final OffenderNonAssociations searchRecord) {
		final List<OffenderNonAssociations> returnList = oidononaRepository.offNaExecuteQuery(searchRecord);
		for (final OffenderNonAssociations obj : returnList) {
			final List<VHeaderBlock> vNamesList = oidononaRepository.getlastFirstName(obj.getNsOffenderId(),
					obj.getOffenderId(),searchRecord.getCreateUserId());
			final Integer count = oidononaRepository.getNbtActiveFlg(obj.getOffenderId(), obj.getNsOffenderId());
			if (count >= 1) {
				obj.setActiveFlag("Y");
			}
			if (count == 0) {
				obj.setActiveFlag("N");
			}
			for (final VHeaderBlock vObj : vNamesList) {
				obj.setOffenderIdDisplay(vObj.getOffenderIdDisplay());
				obj.setLastName(vObj.getLastName());
				obj.setFirstName(vObj.getFirstName());
				obj.setPrisionLocation(vObj.getPrisonLocation());

			}
			List<OffenderNaDetails> naDetailsList=new ArrayList<OffenderNaDetails>();
			OffenderNaDetails naObject=new OffenderNaDetails();
			naObject.setOffenderId(obj.getOffenderId());
			naObject.setNsOffenderBookId(obj.getNsOffenderBookId());
			naDetailsList=oidononaRepository.offNadExecuteQuery(naObject);
			obj.setNaDetailsList(naDetailsList);
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_NA
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offNaCommit(final OffenderNonAssociationsCommitBean commitBean) {
		Integer liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(data ->{
				data.setCreateUserId(commitBean.getCreateUserId());
			});
			liReturn = offNaInsertOffenderNonAssociations(commitBean.getInsertList());
			return liReturn;
		}
		if ((commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) && 
				(commitBean.getOffNadUdateList().isEmpty())) {
			commitBean.getUpdateList().forEach(data ->{
				data.setModifyUserId(commitBean.getCreateUserId());
			});
			liReturn = offNaUpdateOffenderNonAssciations(commitBean.getUpdateList(),commitBean.getOffNadUdateList());
			return liReturn;
		}
		if ((commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) && 
				(commitBean.getOffNadUdateList() != null && commitBean.getOffNadUdateList().size() > 0)) {
			commitBean.getUpdateList().forEach(data ->{
				data.setModifyUserId(commitBean.getCreateUserId());
			});
			commitBean.getOffNadUdateList().forEach(ele->ele.setModifyUserId(commitBean.getCreateUserId()));
			liReturn = offNaUpdateOffenderNonAssciations(commitBean.getUpdateList(),commitBean.getOffNadUdateList());
			return liReturn;
		}
		return liReturn;
	}

	private Integer offNaUpdateOffenderNonAssciations(final List<OffenderNonAssociations> updateList,final List<OffenderNaDetails> offNadupdateList) {
		Integer updateNadetails = null;
		for (final OffenderNonAssociations obj : updateList) {
			final Integer count = oidononaRepository.getNbtActiveFlg(obj.getOffenderId(), obj.getNsOffenderId());
			if (count == 1) {
				obj.setActiveFlag("Y");
			} else if (count == 0) {
				obj.setActiveFlag("N");
			} else if (count > 1) {
				return 2;
			}
			if ("Y".equals(obj.getUpdateNonAssociation())) {
				final Integer updateNonAss = oidononaRepository.updateOffenderNonAssociationfromWeb(obj);
				if (updateNonAss == 1) {
					for (final OffenderNaDetails obj1 : offNadupdateList) {
						updateNadetails = oidononaRepository.offNadUpdateOffenderNaDetails(obj1);
					}
				}
			}
			if ("N".equals(obj.getUpdateNonAssociation())) {
				final Integer updateNonAss = oidononaRepository.updateOffenderNonAssociationfromWeb(obj);
				if (updateNonAss == 1) {
					updateNadetails = updateNonAss;
				}
			}
			OffenderNaDetails newObj=new OffenderNaDetails();
			newObj.setNsReasonCode(obj.getRecipNsReasonCode());
			newObj.setOffenderId(obj.getOffenderId());
			newObj.setNsOffenderId(obj.getNsOffenderId());
			newObj.setModifyUserId(obj.getModifyUserId());
			oidononaRepository.updateOffenderNonAssociationfromWebReciprocalReason(newObj);

		}
		return updateNadetails;
	}

	private Integer offNaInsertOffenderNonAssociations(final List<OffenderNonAssociations> insertList) {
		Integer val = 0;
		Integer updateCount = 0;
		Integer nadVal = 0;
		for (final OffenderNonAssociations obj : insertList) {
			final List<OffenderNonAssociations> objOne = new ArrayList<>();
			final List<OffenderNonAssociations> objTwo = new ArrayList<>();
			final List<OffenderNonAssociations> objThree = new ArrayList<>();
			objOne.clear();
			if ("N".equals(obj.getUpdateNonAssociation())) {
				final Integer seq = oidononaRepository.perInsert(obj.getOffenderId(), obj.getNsOffenderId());
				obj.setTypeSeq(seq.longValue());
				objOne.clear();
				objOne.add(obj);
				nadVal = oidononaRepository.offNadInsertList(objOne);

				if (nadVal == 1) {
					obj.setModifyUserId(obj.getCreateUserId());
					updateCount = oidononaRepository.updateOffenderNonAssociation(obj);
					return updateCount;
				}
			} else {
				objOne.clear();
				objOne.add(obj);
				String recipNsReasonCode = null;
				recipNsReasonCode = obj.getRecipNsReasonCode();
				obj.setRecipNsReasonCode(obj.getNsReasonCode());
				val = oidononaRepository.offNaInsertOffenderNonAssociations(objOne);
				if  (val == 1) {
					final Integer seq = oidononaRepository.perInsert(obj.getOffenderId(),
							obj.getNsOffenderId());
					obj.setNsReasonCode(recipNsReasonCode);
					obj.setTypeSeq(seq.longValue());
					objThree.clear();
					objThree.add(obj);
					nadVal = oidononaRepository.offNadInsertList(objThree);
					obj.setNsReasonCode(obj.getRecipNsReasonCode());
					obj.setRecipNsReasonCode(recipNsReasonCode);
				}
				objTwo.clear();
				OffenderNonAssociations objData = new OffenderNonAssociations();
				objData.setOffenderId(obj.getNsOffenderId());
				objData.setNsOffenderId(obj.getOffenderId());
				objData.setOffenderBookId(obj.getNsOffenderBookId());
				objData.setNsOffenderBookId(obj.getOffenderBookId());
				objData.setNsReasonCode(obj.getNsReasonCode());
				objData.setNsLevelCode(obj.getNsLevelCode());
				objData.setInternalLocationFlag(obj.getInternalLocationFlag());
				objData.setTransportFlag(obj.getTransportFlag());
				objData.setRecipNsReasonCode(obj.getNsLevelCode());
				objData.setNsType(obj.getNsType());
				objData.setNsEffectiveDate(obj.getNsEffectiveDate());
				objData.setNsExpiryDate(obj.getNsExpiryDate());
				objData.setAuthorizedStaff(obj.getAuthorizedStaff());
				objData.setCommentText(obj.getCommentText());
				objData.setCreateUserId(obj.getCreateUserId());
				objData.setModifyUserId(obj.getCreateUserId());
				objTwo.add(objData);
				val = oidononaRepository.offNaInsertOffenderNonAssociations(objTwo);
				if (val == 1) {
					final Integer seq = oidononaRepository.perInsert(objData.getOffenderId(),
							objData.getNsOffenderId());
					objData.setRecipNsReasonCode(obj.getRecipNsReasonCode());
					objData.setTypeSeq(seq.longValue());
					objTwo.clear();
					objTwo.add(objData);
					nadVal = oidononaRepository.offNadInsertList(objTwo);

					if (nadVal == 1) {
						objData.setRecipNsReasonCode(obj.getRecipNsReasonCode());
						
						updateCount = oidononaRepository.updateOffenderNonAssociation(objData);

					}
					if (updateCount == 1) {
						return updateCount;
					}

				}
			}
		}
		return updateCount;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderNaDetails> offNadExecuteQuery(final OffenderNaDetails searchRecord) {
		return oidononaRepository.offNadExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_NAD
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offNadCommit(final OffenderNaDetailsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(data ->{
				data.setCreateUserId(commitBean.getCreateUserId());
			});
			for (final OffenderNaDetails obj : commitBean.getInsertList()) {
					final Integer seq = oidononaRepository.perInsert(obj.getOffenderId(), obj.getNsOffenderId());
					obj.setTypeSeq(seq.longValue());
					}
			liReturn = oidononaRepository.offNadInsertOffenderNaDetails(commitBean.getInsertList());
			return liReturn;
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			commitBean.getUpdateList().forEach(data ->{
				data.setModifyUserId(commitBean.getCreateUserId());
			});
			for (final OffenderNaDetails obj : commitBean.getUpdateList()) {
				commitBean.getUpdateList().forEach(data ->{
					data.setModifyUserId(commitBean.getCreateUserId());
				});
				final Integer count = oidononaRepository.getNbtActiveFlg(obj.getOffenderId(), obj.getNsOffenderId());
				if (count == 1) {
					obj.setActiveFlag("Y");
				} else if (count == 0) {
					obj.setActiveFlag("N");
				}
				liReturn = oidononaRepository.offNadUpdateOffenderNaDetails(obj);
				liReturn = oidononaRepository.offNadUpdateOffenderNaDetailsDouble(obj);
				OffenderNonAssociations data = new OffenderNonAssociations();
				data.setModifyUserId(commitBean.getCreateUserId());
				data.setNsReasonCode(obj.getNsReasonCode());
				data.setOffenderId(obj.getOffenderId());
				data.setNsOffenderId(obj.getNsOffenderId());
				liReturn=oidononaRepository.updateOffenderNonAssociationfromWebDouble(data);
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
	public List<StgRelationships> stgRelationshipsExecuteQuery(final StgRelationships searchRecord) {
		final List<StgRelationships> returnList = oidononaRepository.stgRelationshipsExecuteQuery(searchRecord);
		returnList.forEach(data -> {
			if (data.getRelatedStgId() != null) {
				final String description = oidononaRepository.getstgDesc(data.getRelatedStgId());
				data.setDescription(description);

			}
		});
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSTG_RELATIONSHIPS
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer stgRelationshipsCommit(final StgRelationshipsCommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return oidononaRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<VHeaderBlock> cgfkOffNaDspOffenderIdDiRecordGroup() {
		return oidononaRepository.cgfkOffNaDspOffenderIdDiRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkOffNadDspDescription3RecordGroup() {
		return oidononaRepository.cgfkOffNadDspDescription3RecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> offNaDspRecipRsnRecordGroup() {
		return oidononaRepository.offNaDspRecipRsnRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> cgfkOffNadDspDescriptionRecordGroup() {
		return oidononaRepository.cgfkOffNadDspDescriptionRecordGroup();

	}

	/**
	 * This method is used to compareEffectiveDatec
	 *
	 */
	public Integer compareEffectiveDatec(final String effectiveDate) {
		return oidononaRepository.compareEffectiveDatec(effectiveDate);
	}

	/**
	 * This method is used to get Max value
	 *
	 */
	public Integer getMaxVal(final Long rootOffenderId, final Long nsOffenderId) {
		return oidononaRepository.getMaxVal(rootOffenderId, nsOffenderId);
	}

	public List<VHeaderBlock> getlastFirstName(final String nsOffenderId, final Long offenderId,String userName) {
		final Long rootId = oidononaRepository.getRootOffenderId(nsOffenderId,userName);
		return oidononaRepository.getlastFirstName(rootId, offenderId,userName);
	}

	@Override
	public List<ReferenceCodes> getActiveStaffMembers() {
		return oidononaRepository.getActiveStaffMembers();
	}

}
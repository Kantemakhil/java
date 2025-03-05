package net.syscon.s4.inst.property.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderPptyConTxnsCommitBean;
import net.syscon.s4.im.beans.Statutes;
import net.syscon.s4.inst.property.OidvcontRepository;
import net.syscon.s4.inst.property.OidvcontService;
import net.syscon.s4.inst.property.bean.OffenderPptyConTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxnsCommitBean;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.triggers.OmtocontService;

/**
 * Class OidvcontServiceImpl
 */
@Service
public class OidvcontServiceImpl extends BaseBusiness implements OidvcontService {

	@Autowired
	private OidvcontRepository oidvcontRepository;
    
	@Autowired
	private OmtocontService omtocontService;
	/**
	 * Creates new OidvcontServiceImpl class Object
	 */
	public OidvcontServiceImpl() {
		// OidvcontServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffenderPptyContainers> vPheadOnCheckDeleteMaster(final OffenderPptyContainers paramBean) {
		return oidvcontRepository.vPheadOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public AgencyInternalLocations cgfkchkOffConOffConPpty(final AgencyInternalLocations paramBean) {
		return oidvcontRepository.cgfkchkOffConOffConPpty(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes cgfkchkOffConOffConRef(final ReferenceCodes paramBean) {
		return oidvcontRepository.cgfkchkOffConOffConRef(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public ReferenceCodes cgfkchkConTxConTxnRefCo(final ReferenceCodes paramBean) {
		return oidvcontRepository.cgfkchkConTxConTxnRefCo(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> CgwhenNewFormInstance() {
		return null;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderPptyContainers> offConExecuteQuery(final OffenderPptyContainers searchRecord) {
		List<OffenderPptyContainers> returnList = new ArrayList<>();
		returnList = oidvcontRepository.offConExecuteQuery(searchRecord);
		for (final OffenderPptyContainers obj : returnList) {
			AgencyInternalLocations internalLocId = new AgencyInternalLocations();
			internalLocId.setInternalLocationId(obj.getInternalLocationId());
			if (obj.getInternalLocationId() != null) {
				internalLocId = oidvcontRepository.cgfkchkOffConOffConPpty(internalLocId);
				obj.setDescription(internalLocId.getDescription());
			}
			if (obj.getContainerCode() != null) {
				ReferenceCodes refObj = new ReferenceCodes();
				refObj.setCode(obj.getContainerCode().toString());
				refObj = oidvcontRepository.cgfkchkOffConOffConRef(refObj);
				obj.setDspDescription(refObj.getDescription());
			}
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderPptyConTxns> conTxExecuteQuery(final OffenderPptyConTxns searchRecord) {
		return oidvcontRepository.conTxExecuteQuery(searchRecord);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCON_TX
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer conTxCommit(final OffenderPptyConTxnsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			commitBean.getInsertList().forEach(bean->bean.setCreateUserId(commitBean.getCreateUserId()));
			liReturn = offPptyConInsertOffenderAlerts(commitBean.getInsertList());
		}
		return liReturn;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderAlerts
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offPptyConInsertOffenderAlerts(final List<OffenderPptyConTxns> lstOffenderPptyConTxns) {
		OffenderPptyContainers bean = new OffenderPptyContainers();
		for (final OffenderPptyConTxns obj : lstOffenderPptyConTxns) {
			if (obj.getSealMark() != null && obj.getActionCode().equalsIgnoreCase("VC")) {
				bean.setPropertyContainerId(obj.getPropertyContainerId());
				bean.setModifyUserId(obj.getCreateUserId());
				bean.setSealMark(obj.getSealMark());
				bean.setAgyLocId(obj.getAgyLocId());
				bean.setCommentText(obj.getCommentText());
				bean.setInternalLocationId(obj.getInternalLocationId());
				bean.setCreateUserId(obj.getCreateUserId());
				bean.setTrnFromAgyLocId(obj.getTrnFromAgyLocId());
				omtocontService.omtocontTrg(bean, "update"); 	
				Integer returnValue = oidvcontRepository.updateOffenderPptyContainers(bean);			
				if (returnValue != null) {
				}
			}
		}
		return oidvcontRepository.offPptyConInsertOffenderAlerts(lstOffenderPptyConTxns);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderPptyItemTxns> itmTxExecuteQuery(final OffenderPptyItemTxns searchRecord) {
		int index = 0;
		List<OffenderPptyItemTxns> returnList = new ArrayList<>();
		OffenderPptyItems offenderPptyItems = new OffenderPptyItems();
		OffenderPptyItems offenderPpty = new OffenderPptyItems();
		returnList = oidvcontRepository.itmTxExecuteQuery(searchRecord);
		for (OffenderPptyItemTxns offenderPptyItemTxns : returnList) {
			if (index == 0) {
				offenderPptyItems.setOffenderBookId(offenderPptyItemTxns.getOffenderBookId());
				offenderPptyItems.setPropertyItemSeq(offenderPptyItemTxns.getPropertyItemSeq());
				offenderPpty = oidvcontRepository.offPiExecuteQuery(offenderPptyItems);
			}
			offenderPptyItemTxns.setPropertyType(offenderPpty.getPropertyType());
			offenderPptyItemTxns.setPropertyDescription(offenderPpty.getPropertyDescription());
			offenderPptyItemTxns.setConditionCode(offenderPpty.getConditionCode());
			offenderPptyItemTxns.setQuantity(offenderPpty.getQuantity());
		}
		for (final OffenderPptyItemTxns obj : returnList) {
			if (obj.getColor() != null) {
				final ReferenceCodes toStatus = oidvcontRepository.itmTxPostQuery(obj.getColor());
				if (toStatus != null) {
					obj.setColor(toStatus.getDescription());
				}
			}
			if (obj.getConditionCode() != null) {
				final ReferenceCodes toStatus = oidvcontRepository
						.getDescriptionOfConditionCode(obj.getConditionCode());
				if (toStatus != null) {
					obj.setConditionCode(toStatus.getDescription());
				}
			}
			if (obj.getPropertyType() != null) {
				final ReferenceCodes toStatus = oidvcontRepository.getDescriptionOfPropertyCode(obj.getPropertyType());
				if (toStatus != null) {
					obj.setPropertyType(toStatus.getDescription());
				}
			}
		}
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstITM_TX
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer itmTxCommit(final OffenderPptyItemTxnsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (OffenderPptyItemTxns obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
			}
			liReturn = itmTxInsertOffenderPptyItemTxns(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderPptyItemTxns obj : commitBean.getInsertList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oidvcontRepository.itmTxUpdateOffenderPptyItemTxns(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderPptyItemTxns
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer itmTxInsertOffenderPptyItemTxns(final List<OffenderPptyItemTxns> lstOffenderPptyItemTxns) {
		Integer returnValue = null;
		for (OffenderPptyItemTxns offenderPptyItemTxns : lstOffenderPptyItemTxns) {
			if (offenderPptyItemTxns.getColor() != null) {
				final ReferenceCodes toStatus = oidvcontRepository.itmTxPostQueryColor(offenderPptyItemTxns.getColor());
				if (toStatus != null) {
					offenderPptyItemTxns.setColor(toStatus.getCode());
				}
			}
			if (offenderPptyItemTxns.getVerifyFlag() == null || offenderPptyItemTxns.getVerifyFlag().trim().isEmpty()
					|| !offenderPptyItemTxns.getVerifyFlag().trim().equals("Y")) {
				offenderPptyItemTxns.setVerifyFlag("N");
			}
		}
		returnValue = oidvcontRepository.itmTxInsertOffenderPptyItemTxns(lstOffenderPptyItemTxns);

		return returnValue;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkConTxActionCodeRecordGroup(final String propertyContainerId) {
		List<ReferenceCodes> resultList = oidvcontRepository.cgfkConTxActionCodeRecordGroup(propertyContainerId);
		for (ReferenceCodes result : resultList) {
			if (result.getSeqValue() == 0) {
				result.setCanDisplay(false);
			}
		}
		if(Optional.ofNullable(resultList).isPresent()) {
			resultList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return resultList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<OffenderPptyItems> offPItemExecuteQuery(final OffenderPptyItemTxns searchRecord) {
		List<OffenderPptyItems> offenderPpty = new ArrayList<>();
		OffenderPptyItems offPptyItemsObj = new OffenderPptyItems();
		offPptyItemsObj.setPropertyContainerId(searchRecord.getPropertyContainerId());
		offenderPpty = oidvcontRepository.offPItemExecuteQuery(offPptyItemsObj);
		for (final OffenderPptyItems obj : offenderPpty) {
			if (obj.getColor() != null) {
				final ReferenceCodes toStatus = oidvcontRepository.itmTxPostQuery(obj.getColor());
				if (toStatus != null) {
					obj.setColor(toStatus.getDescription());
				}
			}
			if (obj.getConditionCode() != null) {
				final ReferenceCodes toStatus = oidvcontRepository
						.getDescriptionOfConditionCode(obj.getConditionCode());
				if (toStatus != null) {
					obj.setConditionCode(toStatus.getDescription());
				}
			}
			if (obj.getPropertyType() != null) {
				final ReferenceCodes toStatus = oidvcontRepository.getDescriptionOfPropertyCode(obj.getPropertyType());
				if (toStatus != null) {
					obj.setPropertyType(toStatus.getDescription());
				}
			}
		}
		return offenderPpty;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOffenderPptyItemTxns
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer updateOffenderPptyContainers(final OffenderPptyContainers commitBean) {
		int liReturn = 0;
		if (commitBean != null) {
			omtocontService.omtocontTrg(commitBean, "update"); 	
			liReturn = oidvcontRepository.updateOffenderPptyContainers(commitBean);
		}
		return liReturn;
	}
}
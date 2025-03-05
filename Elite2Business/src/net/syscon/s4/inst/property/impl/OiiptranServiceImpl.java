package net.syscon.s4.inst.property.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.cf.deductions.OcdofaccRepository;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.inst.demographicsbiometrics.OcuverifRepository;
import net.syscon.s4.inst.property.OiiptranRepository;
import net.syscon.s4.inst.property.OiiptranService;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.inst.property.bean.OffenderPptyItemsCommitBean;

/**
 * Class OiiptranServiceImpl
 */
@Service
public class OiiptranServiceImpl extends BaseBusiness implements OiiptranService {

	@Autowired
	private OiiptranRepository oiiptranRepository;

	@Autowired
	private OcdofaccRepository ocdofaccRepository;
	
	@Autowired
	private OcuverifRepository ocuverifRepo;
	/**
	 * Creates new OiiptranServiceImpl class Object
	 */
	public OiiptranServiceImpl() {
		// OiiptranServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<OffenderPptyItems> vPheadOnCheckDeleteMaster(final OffenderPptyItems paramBean) {
		return oiiptranRepository.vPheadOnCheckDeleteMaster(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderPptyItems> offPiExecuteQuery(final OffenderPptyItems searchRecord) {
		OffenderPptyContainers offPptyIms = new OffenderPptyContainers();
		List<OffenderPptyItems> returnList = new ArrayList<>();
		returnList = oiiptranRepository.offPiExecuteQuery(searchRecord);
		for (final OffenderPptyItems obj : returnList) {
			if(obj.getOffenderBookId()!=null ){
				final List<String> imageFlags= oiiptranRepository.getImageFlag(obj.getOffenderBookId(),obj.getPropertyItemSeq());
				for(final String objforImageflag:imageFlags){
					obj.setImageFlag(objforImageflag);					
				}
				
			}
			
			if (obj.getReceivedFrom() != null) {
				final ReferenceCodes toStatus = oiiptranRepository.getDescriptionOfReceivedForm(obj.getReceivedFrom());
				if (toStatus != null) {
					obj.setReceivedFrom(toStatus.getDescription());
				}
			}
			if (obj.getPropertyType() != null) {
				final ReferenceCodes toStatus = oiiptranRepository.getDescriptionOfPropertyCode(obj.getPropertyType());
				if (toStatus != null) {
					obj.setPropertyType(toStatus.getDescription());
				}
			}
			if (obj.getColor() != null) {
				final ReferenceCodes toStatus = oiiptranRepository.getDescriptionOfColor(obj.getColor());
				if (toStatus != null) {
					obj.setColor(toStatus.getDescription());
				}
			}
			if (obj.getConditionCode() != null) {
				final ReferenceCodes toStatus = oiiptranRepository
						.getDescriptionOfConditionCode(obj.getConditionCode());
				if (toStatus != null) {
					obj.setConditionCode(toStatus.getDescription());
				}
			}
			if (obj.getAgyLocId() != null) {
				final ReferenceCodes toStatus = oiiptranRepository.getDescriptionOfAgyLocId(obj.getAgyLocId());
				if (toStatus != null) {
					obj.setAgyLocId(toStatus.getDescription());
				}
			}
		}
		for (final OffenderPptyItems obj : returnList) {
			OffenderPptyContainers offConId = new OffenderPptyContainers();
			offConId.setOffenderBookId(obj.getOffenderBookId());
			offConId.setPropertyContainerId(obj.getPropertyContainerId());
			offConId.setPropertyItemSeq(obj.getPropertyItemSeq());
			if (offConId.getPropertyContainerId() != null && offConId.getOffenderBookId() != null
					&& offConId.getPropertyItemSeq() != null) {
				offPptyIms = oiiptranRepository.cgfkchkOffPiOffPiOffCon(offConId);
				if(offPptyIms!=null && offPptyIms.getInternalLocationId() !=null ) {
				obj.setInternalLocationId(offPptyIms.getInternalLocationId());
				}
				AgencyInternalLocations internalLocId = new AgencyInternalLocations();
				internalLocId.setInternalLocationId(obj.getInternalLocationId());
				if (obj.getInternalLocationId() != null) {
					internalLocId = oiiptranRepository.cgfkchkOffConOffConPpty(internalLocId);
					if(internalLocId!=null && internalLocId.getDescription() !=null ) {
					obj.setDspDescription(internalLocId.getDescription());
					}
				}
			}
		}
		
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_PI
	 *
	 * @throws SQLException
	 */
	@Transactional
	public Integer offPiCommit(final OffenderPptyItemsCommitBean CommitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @throws SQLException
	 */
	public List<OffenderPptyItemTxns> itmTxExecuteQuery(final OffenderPptyItemTxns searchRecord) {
		List<OffenderPptyItemTxns> returnList = new ArrayList<>();
		returnList = oiiptranRepository.itmTxExecuteQuery(searchRecord);
		for (final OffenderPptyItemTxns obj : returnList) {
			if (obj.getCreateDate() != null) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				obj.setMake(simpleDateFormat.format(obj.getCreateDate()));

			}
			if (obj.getToStatusCode() != null) {
				final ReferenceCodes toStatus = oiiptranRepository.getDescriptionOfToStatus(obj.getToStatusCode());
				if (toStatus != null) {
					obj.setToStatusCode(toStatus.getDescription());
				}
			}
			if (obj.getAgyLocId() != null) {
				final ReferenceCodes toStatus = oiiptranRepository.getDescriptionOfAgyLocId(obj.getAgyLocId());
				if (toStatus != null) {
					obj.setAgyLocId(toStatus.getDescription());
				}
			}
			if(obj.getActionCode()!=null) {
				if(obj.getActionCode().equals("REJECT")) {
					obj.setActionReason(ocdofaccRepository.getDescription(obj.getActionReason(), "P_TRN_REJ"));
				}
				if(obj.getActionCode().equals("CANCEL")) {
					obj.setActionReason(ocdofaccRepository.getDescription(obj.getActionReason(), "P_TRN_CAN"));
				}
			}
		}
		
		String userId = null;
		String userName = null;
		for (OffenderPptyItemTxns list : returnList) {
			userId = list.getCreateUserId();
			userName = ocuverifRepo.getUserName(userId);
			list.setCreateUserId(userName);
		}
		
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 */
	public List<String> findReceivedList() {
		return oiiptranRepository.findReceivedList();
	}

	/**
	 * Fetch the records from database table
	 */
	public List<String> findTypeList() {
		return oiiptranRepository.findTypeList();
	}

	/**
	 * Fetch the records from database table
	 */
	public List<String> findColorList() {
		return oiiptranRepository.findColorList();
	}

	/**
	 * Fetch the records from database table
	 */
	public List<String> findConditionList() {
		return oiiptranRepository.findConditionList();
	}

	/**
	 * Fetch the records from database table
	 */
	public List<String> findFacilityList() {
		return oiiptranRepository.findFacilityList();
	}

	/**
	 * Fetch the records from database table
	 */
	public List<String> findagyLocationList(String caseLoadId) {
		return oiiptranRepository.findagyLocationList(caseLoadId);
	}

	@Override
	public List<Images> offPiImagesExecuteQuery(final Images searchBean) {
		return oiiptranRepository.offPiImagesExecuteQuery(searchBean);
	}
}
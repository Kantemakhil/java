package net.syscon.s4.pkgs.oms_property.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.OffenderPptyConTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.pkgs.oms_property.OmsPropertyRepository;
import net.syscon.s4.pkgs.oms_property.OmsPropertyService;
import net.syscon.s4.pkgs.oms_trigger_objects.OmsTriggerObjectsService;
import net.syscon.s4.triggers.OffenderPptyItemsT1Repository;
import net.syscon.s4.triggers.OffenderPptyItemsT1Service;
import net.syscon.s4.triggers.impl.OmtocontServiceImpl;

/*
 * Below comments are copied from package OMS_PROPERTY
 * MODIFICATION HISTORY
-- Person        DATE         Version   Comments
-- -----------   ----------   --------  ------------------------------------------------
-- NIKO          19-FEB-2009  2.4       Modified procedure - get_tran_room_storage_id
--                                      by rewriting CURSOR lv_ppty_id to retrieve internal location id as per new data model
-- NIKO          20-MAR-2008  2.3       Modified procedure - get_tran_room_storage_id3 and get_tran_room_storage_id2
-- GJC           14-Oct-2006  2.2       SHOW_VERSION changed from procedure to function
-- Surya         13-Dec-2005  2.1       Modified the Complete package for the merging of
--                                      Property Storages with the Internal Locations.
--                                      Added the get_location_id_description procedure as well.
-- Venu          11-Mar-05    1.1       Modified following program units, when getting storage ID one has to look
--                                      at the storage type and storage code, Tr+ 384.
-- SURYA         09-AUG-00    4.9.0.0   Added the Procedure SHOW_VERSION and Added the
--  
*/
@Service
public class OmsPropertyServiceImpl extends BaseBusiness implements OmsPropertyService {

	@Autowired
	private OmsPropertyRepository omsPropertyRepository;

	@Autowired
	private OffenderPptyItemsT1Service offenderPptyItemsT1Service;
	@Autowired
	OmsTriggerObjectsService omsTriggerObjectsService;
	@Autowired
	private OffenderPptyItemsT1Repository offenderPptyItemsT1Repository;

	
	private final Logger logger = LogManager.getLogger(OmsPropertyServiceImpl.class);
	@Override
	public Integer checkStorageCapacity(final String internalLocId) {
		Integer returnVal;
		try {
			returnVal = omsPropertyRepository.checkStorageCapacity(internalLocId);
		} catch (Exception e) {
			returnVal = 0;
		}
		return returnVal;
	}

	@Override
	public Integer getTranRoomStorageId(final String trnToAgyLocId) {
		Integer returnVal = 0;
		try {
			returnVal = omsPropertyRepository.getTranRoomStorageId(trnToAgyLocId);
		} catch (Exception e) {
			returnVal = 0;
		}
		return returnVal;
	}

	/*
	 * This PROCEDURE update_transferred_ is migrated from oracle -- To update the
	 * status and agency location of transferred items.
	 */
	@Override
	public Integer updateTransferredItems(final OffenderPptyContainers paramBean, final String operation) {
		try {
			// This method is used to get old data from OFFENDER_PPTY_ITEMS table
			List<OffenderPptyItems> oldDataList = omsPropertyRepository.getOldDataOffenderPptyItems(paramBean.getPropertyContainerId());
			paramBean.setModifyUserId(paramBean.getModifyUserId());
			// -- To update the status and agency location of transferred items.
			omsPropertyRepository.offenderPptyItemsUpdate(paramBean);
			OffenderPptyItems container = new OffenderPptyItems();
			BeanUtils.copyProperties(paramBean, container);
			for (int i = 0; i < oldDataList.size(); i++) {
				OffenderPptyItems newdata = container;
				// Copying PropertyItemSeq
				newdata.setPropertyItemSeq(oldDataList.get(i).getPropertyItemSeq());
				// This trigger offenderPptyItemsT1 is migrated from oracle
				offenderPptyItemsT1Service.offenderPptyItemsT1(oldDataList.get(i), newdata, operation);
			}
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	// This PROCEDURE unseal_container is migrated from oracle
	// -- To Unseal a container if it is sealed.
	@Override
	public void unsealContainer(final Integer propertyContainerId) {
		// -- To Unseal a container if it is sealed.
		// This method is used to update offender_ppty_containers
		omsPropertyRepository.unsealContainerUpdate(propertyContainerId);
	}
    //This method is used for retrieving for agy_loc_id from database
	@Override
	public String getUsrAgyLoc() {
		return omsPropertyRepository.getUsrAgyLocSelect();
	}

	@Override
	public String getConAgyLoc(Integer pPropertyContainerId) {
		return omsPropertyRepository.getConAgyLocSelect(pPropertyContainerId);
	}

	@Override
	public Integer updatePropertyItemDetails(OffenderPptyContainers paramBean) {
		try {
			String userId=null;
			// This method is used to get old data from OFFENDER_PPTY_ITEMS table
			List<OffenderPptyItems> oldDataList = omsPropertyRepository.getOldDataOffenderPptyItems(paramBean.getPropertyContainerId());
			paramBean.setModifyUserId(paramBean.getModifyUserId());
			// -- To update the status and agency location of transferred items.
			omsPropertyRepository.offenderPptyItemsUpdate(paramBean);
			OffenderPptyItems container = new OffenderPptyItems();
			BeanUtils.copyProperties(paramBean, container);
			for (int i = 0; i < oldDataList.size(); i++) {
				OffenderPptyItems newData = container;
				// Copying PropertyItemSeq
				newData.setPropertyItemSeq(oldDataList.get(i).getPropertyItemSeq());
				if(newData.getActionCode().equals("TR-IN")) {
					newData.setActionCode("STORED");
					
				}
				if(newData.getModifyUserId()!=null) {
					userId=newData.getModifyUserId();
				}else {
					userId=newData.getCreateUserId();
				}
				offenderPptyItemsT1Repository.insert(oldDataList.get(i), newData, newData.getPropertyContainerId(), newData.getAgyLocId(), null,userId);
				//offenderPptyItemsT1Service.offenderPptyItemsT1(oldDataList.get(i), newdata, operation);
			}
		} catch (Exception e) {
			logger.error("error in updatePropertyItemDetails ", e);
			return 0;
		}
		return 1;
	}
	
	@Override
	public Integer InsertContainerTransaction( final OffenderPptyContainers newbean,String actionCode) {
		final OffenderPptyConTxns offenderPptyConTxns = new OffenderPptyConTxns();
		offenderPptyConTxns.setPropertyContainerId(newbean.getPropertyContainerId());
		if(actionCode!=null && actionCode.equals("CANCEL")) {
			offenderPptyConTxns.setActionReason(newbean.getCancelReason());
		}else if(actionCode!=null && actionCode.equals("REJECT")){
			offenderPptyConTxns.setActionReason(newbean.getRejectReason());
		}
		offenderPptyConTxns.setActionCode(actionCode);
		offenderPptyConTxns.setAgyLocId(newbean.getAgyLocId());
		offenderPptyConTxns.setCommentText(newbean.getCommentText());
		offenderPptyConTxns.setInternalLocationId(newbean.getInternalLocationId());
		offenderPptyConTxns.setSealMark(newbean.getSealMark());
		offenderPptyConTxns.setTrnFromAgyLocId(null);
		offenderPptyConTxns.setTrnToAgyLocId(null);
		offenderPptyConTxns.setCreateUserId(newbean.getCreateUserId());
		// inserting data into OFFENDER_PPTY_CON_TXNS table
		return omsTriggerObjectsService.createContainerTransaction(offenderPptyConTxns);
		
		
	}

}
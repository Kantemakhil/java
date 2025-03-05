package net.syscon.s4.triggers.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.triggers.AgencyInternalLocationsT1Repository;
import net.syscon.s4.triggers.AgencyInternalLocationsT1Service;
import net.syscon.s4.triggers.AgyIntLocAmendments;

@Service
public class AgencyInternalLocationsT1ServiceImpl implements AgencyInternalLocationsT1Service {
	@Autowired
	private AgencyInternalLocationsT1Repository agencyInternalLocationsT1Repository;
	
	private String actionCode;


	
	@Override
	public Boolean isChanged(final String pOldVal, final String pNewVal, String pActionCode) {
		if (pOldVal==null && pNewVal!=null) {
			pActionCode = "A";
			actionCode=pActionCode;
			return true;
		}
		if (pOldVal!=null && pNewVal==null) {
			pActionCode = "D";
			actionCode=pActionCode;
			return true;
		}

		if(pOldVal==null && pNewVal!=null) {
			pActionCode = "U";
			actionCode=pActionCode;
			return true;
		}else if(pOldVal!=null && pNewVal==null) {
			pActionCode = "U";
			actionCode=pActionCode;
			return true;
		}else if(!pOldVal.equals(pNewVal)) {
			pActionCode = "U";
			actionCode=pActionCode;
			return true;
		}
		return false;
	}
	
	
	

	@Override
	public Integer AgencyInternalLocationsT1( AgencyInternalLocations oldRef, final AgencyInternalLocations newRef,
			 String lvActionCode) {
		AgyIntLocAmendments agyIntLocAmendments = new AgyIntLocAmendments();
		final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Integer result = 0;
		if (isChanged(oldRef.getInternalLocationCode(), newRef.getInternalLocationCode(), lvActionCode)) {
			agyIntLocAmendments = new AgyIntLocAmendments();
			agyIntLocAmendments.setInternalLocationId(Long.valueOf(newRef.getInternalLocationId()));
			agyIntLocAmendments.setActionCode(actionCode);
			agyIntLocAmendments.setColumnName("Living Unit Id");
			agyIntLocAmendments.setOldValue(oldRef.getInternalLocationCode());
			agyIntLocAmendments.setNewValue(newRef.getInternalLocationCode());
			agyIntLocAmendments.setAmendUserId(newRef.getCreateUserId()!=null?newRef.getCreateUserId():newRef.getModifyUserId());
			agyIntLocAmendments.setCreateUserId(newRef.getCreateUserId());
			result = agencyInternalLocationsT1Repository.insertAgyIntLocAmendments(agyIntLocAmendments);
		}
		if (isChanged(oldRef.getAgyLocId(), newRef.getAgyLocId(), actionCode)) {
			agyIntLocAmendments = new AgyIntLocAmendments();
			agyIntLocAmendments.setInternalLocationId(Long.valueOf(newRef.getInternalLocationId()));
			agyIntLocAmendments.setActionCode(actionCode);
			agyIntLocAmendments.setColumnName("Location");
			agyIntLocAmendments.setOldValue(oldRef.getAgyLocId());
			agyIntLocAmendments.setNewValue(newRef.getAgyLocId());
			agyIntLocAmendments.setAmendUserId(newRef.getCreateUserId()!=null?newRef.getCreateUserId():newRef.getModifyUserId());
			agyIntLocAmendments.setCreateUserId(newRef.getCreateUserId());
			result = agencyInternalLocationsT1Repository.insertAgyIntLocAmendments(agyIntLocAmendments);
		}
		if (isChanged(oldRef.getInternalLocationType(), newRef.getInternalLocationType(), actionCode)) {
			agyIntLocAmendments = new AgyIntLocAmendments();
			agyIntLocAmendments.setInternalLocationId(Long.valueOf(newRef.getInternalLocationId()));
			agyIntLocAmendments.setActionCode(actionCode);
			agyIntLocAmendments.setColumnName("Accommodation Type");
			agyIntLocAmendments.setOldValue(oldRef.getInternalLocationType());
			agyIntLocAmendments.setNewValue(newRef.getInternalLocationType());
			agyIntLocAmendments.setAmendUserId(newRef.getCreateUserId()!=null?newRef.getCreateUserId():newRef.getModifyUserId());
			agyIntLocAmendments.setCreateUserId(newRef.getCreateUserId());
			result = agencyInternalLocationsT1Repository.insertAgyIntLocAmendments(agyIntLocAmendments);
		}
		if (isChanged(oldRef.getDescription(), newRef.getDescription(), lvActionCode)) {
			agyIntLocAmendments = new AgyIntLocAmendments();
			agyIntLocAmendments.setInternalLocationId(Long.valueOf(newRef.getInternalLocationId()));
			agyIntLocAmendments.setActionCode(actionCode);
			agyIntLocAmendments.setColumnName("Description");
			agyIntLocAmendments.setOldValue(oldRef.getDescription());
			agyIntLocAmendments.setNewValue(newRef.getDescription());
			agyIntLocAmendments.setAmendUserId(newRef.getCreateUserId()!=null?newRef.getCreateUserId():newRef.getModifyUserId());
			agyIntLocAmendments.setCreateUserId(newRef.getCreateUserId());
			result = agencyInternalLocationsT1Repository.insertAgyIntLocAmendments(agyIntLocAmendments);
		}
		if ((oldRef.getCapacity() == null && newRef.getCapacity() != null) 
				|| (oldRef.getCapacity() != null && newRef.getCapacity() == null) 
				|| (oldRef.getCapacity() != null && newRef.getCapacity() != null)) {
			if (isChanged(String.valueOf(oldRef.getCapacity()), String.valueOf(newRef.getCapacity()), lvActionCode)) {
				agyIntLocAmendments = new AgyIntLocAmendments();
				agyIntLocAmendments.setInternalLocationId(Long.valueOf(newRef.getInternalLocationId()));
				agyIntLocAmendments.setActionCode(actionCode);
				agyIntLocAmendments.setColumnName("Maximum Capacity");
				agyIntLocAmendments.setOldValue(oldRef.getCapacity()!=null?String.valueOf(oldRef.getCapacity()):null);
				agyIntLocAmendments.setNewValue(newRef.getCapacity()!=null?String.valueOf(newRef.getCapacity()):null);
				agyIntLocAmendments.setAmendUserId(newRef.getCreateUserId()!=null?newRef.getCreateUserId():newRef.getModifyUserId());
				agyIntLocAmendments.setCreateUserId(newRef.getCreateUserId());
				result = agencyInternalLocationsT1Repository.insertAgyIntLocAmendments(agyIntLocAmendments);
			}
		}
		if (isChanged(oldRef.getActiveFlag(), newRef.getActiveFlag(), lvActionCode)) {
			agyIntLocAmendments = new AgyIntLocAmendments();
			if ("N".equals(newRef.getActiveFlag())) {
				agyIntLocAmendments.setDeactivateReasonCode(newRef.getDeactiveReasonCode());
			} else {
				agyIntLocAmendments.setDeactivateReasonCode(null);
			}
			agyIntLocAmendments.setInternalLocationId(Long.valueOf(newRef.getInternalLocationId()));
			agyIntLocAmendments.setActionCode(actionCode);
			agyIntLocAmendments.setColumnName("Active");
			agyIntLocAmendments.setOldValue(oldRef.getActiveFlag());
			agyIntLocAmendments.setNewValue(newRef.getActiveFlag());
			agyIntLocAmendments.setAmendUserId(newRef.getCreateUserId()!=null?newRef.getCreateUserId():newRef.getModifyUserId());
			agyIntLocAmendments.setCreateUserId(newRef.getCreateUserId());
			result = agencyInternalLocationsT1Repository.insertAgyIntLocAmendments(agyIntLocAmendments);
		}
		if (oldRef.getDeactiveReasonCode() != null && newRef.getDeactiveReasonCode() != null) {
			if (isChanged(oldRef.getDeactiveReasonCode(), newRef.getDeactiveReasonCode(), lvActionCode)) {
				agyIntLocAmendments = new AgyIntLocAmendments();
				agyIntLocAmendments.setInternalLocationId(Long.valueOf(newRef.getInternalLocationId()));
				agyIntLocAmendments.setActionCode(actionCode);
				agyIntLocAmendments.setColumnName("Deactivate Reason");
				agyIntLocAmendments.setOldValue(oldRef.getDeactiveReasonCode());
				agyIntLocAmendments.setNewValue(newRef.getDeactiveReasonCode());
				agyIntLocAmendments.setAmendUserId(newRef.getCreateUserId()!=null?newRef.getCreateUserId():newRef.getModifyUserId());
				agyIntLocAmendments.setCreateUserId(newRef.getCreateUserId());
				result = agencyInternalLocationsT1Repository.insertAgyIntLocAmendments(agyIntLocAmendments);
			}
		}
		if (oldRef.getDeactivateDate() != null && newRef.getDeactivateDate() != null) {
			if (isChanged(dateFormat.format(oldRef.getDeactivateDate()), dateFormat.format(newRef.getDeactivateDate()),
					lvActionCode)) {
				agyIntLocAmendments = new AgyIntLocAmendments();
				agyIntLocAmendments.setInternalLocationId(Long.valueOf(newRef.getInternalLocationId()));
				agyIntLocAmendments.setActionCode(actionCode);
				agyIntLocAmendments.setColumnName("Deactivate Date");
				agyIntLocAmendments.setOldValue(dateFormat.format(oldRef.getDeactivateDate()));
				agyIntLocAmendments.setNewValue(dateFormat.format(newRef.getDeactivateDate()));
				agyIntLocAmendments.setAmendUserId(newRef.getCreateUserId()!=null?newRef.getCreateUserId():newRef.getModifyUserId());
				agyIntLocAmendments.setCreateUserId(newRef.getCreateUserId());
				result = agencyInternalLocationsT1Repository.insertAgyIntLocAmendments(agyIntLocAmendments);
			}
		}
		if (oldRef.getReactivateDate() != null && newRef.getReactivateDate() != null) {

			if (isChanged(dateFormat.format(oldRef.getReactivateDate()), dateFormat.format(newRef.getReactivateDate()),
					lvActionCode)) {
				agyIntLocAmendments = new AgyIntLocAmendments();
				agyIntLocAmendments.setInternalLocationId(Long.valueOf(newRef.getInternalLocationId()));
				agyIntLocAmendments.setActionCode(actionCode);
				agyIntLocAmendments.setColumnName("Proposed Reactivate Date");
				agyIntLocAmendments.setOldValue(dateFormat.format(oldRef.getReactivateDate()));
				agyIntLocAmendments.setNewValue(dateFormat.format(newRef.getReactivateDate()));
				agyIntLocAmendments.setAmendUserId(newRef.getCreateUserId()!=null?newRef.getCreateUserId():newRef.getModifyUserId());
				agyIntLocAmendments.setCreateUserId(newRef.getCreateUserId());
				result = agencyInternalLocationsT1Repository.insertAgyIntLocAmendments(agyIntLocAmendments);
			}
		}

		if (isChanged(String.valueOf(oldRef.getListSeq()), String.valueOf(newRef.getListSeq()), lvActionCode)) {
			agyIntLocAmendments = new AgyIntLocAmendments();
			agyIntLocAmendments.setInternalLocationId(Long.valueOf(newRef.getInternalLocationId()));
			agyIntLocAmendments.setActionCode(actionCode);
			agyIntLocAmendments.setColumnName("Sequence");
			agyIntLocAmendments.setAmendUserId(newRef.getCreateUserId()!=null?newRef.getCreateUserId():newRef.getModifyUserId());
			agyIntLocAmendments.setOldValue(oldRef.getListSeq()!=null?String.valueOf(oldRef.getListSeq()):null);
			agyIntLocAmendments.setNewValue(newRef.getListSeq()!=null?String.valueOf(newRef.getListSeq()):null);
			agyIntLocAmendments.setCreateUserId(newRef.getCreateUserId());
			result = agencyInternalLocationsT1Repository.insertAgyIntLocAmendments(agyIntLocAmendments);
		}
		if (isChanged(String.valueOf(oldRef.getCnaNo()), String.valueOf(newRef.getCnaNo()), lvActionCode)) {
			agyIntLocAmendments = new AgyIntLocAmendments();
			agyIntLocAmendments.setInternalLocationId(Long.valueOf(newRef.getInternalLocationId()));
			agyIntLocAmendments.setActionCode(actionCode);
			agyIntLocAmendments.setColumnName("Baseline CNA");
			agyIntLocAmendments.setOldValue(oldRef.getCnaNo()!=null?String.valueOf(oldRef.getCnaNo()):null);
			agyIntLocAmendments.setNewValue(newRef.getCnaNo()!=null?String.valueOf(newRef.getCnaNo()):null);
			agyIntLocAmendments.setAmendUserId(newRef.getCreateUserId()!=null?newRef.getCreateUserId():newRef.getModifyUserId());
			agyIntLocAmendments.setCreateUserId(newRef.getCreateUserId());
			result = agencyInternalLocationsT1Repository.insertAgyIntLocAmendments(agyIntLocAmendments);
		}
		if (isChanged(oldRef.getCertifiedFlag(), newRef.getCertifiedFlag(), lvActionCode)) {
			agyIntLocAmendments = new AgyIntLocAmendments();
			agyIntLocAmendments.setInternalLocationId(Long.valueOf(newRef.getInternalLocationId()));
			agyIntLocAmendments.setActionCode(actionCode);
			agyIntLocAmendments.setColumnName("Certified");
			agyIntLocAmendments.setOldValue(oldRef.getCertifiedFlag());
			agyIntLocAmendments.setNewValue(newRef.getCertifiedFlag());
			agyIntLocAmendments.setAmendUserId(newRef.getCreateUserId()!=null?newRef.getCreateUserId():newRef.getModifyUserId());
			agyIntLocAmendments.setCreateUserId(newRef.getCreateUserId());
			result = agencyInternalLocationsT1Repository.insertAgyIntLocAmendments(agyIntLocAmendments);
		}
		if (oldRef.getCommentText() != null && newRef.getCommentText() != null) {
			if (isChanged(oldRef.getCommentText(), newRef.getCommentText(), lvActionCode)) {
				agyIntLocAmendments = new AgyIntLocAmendments();
				agyIntLocAmendments.setInternalLocationId(Long.valueOf(newRef.getInternalLocationId()));
				agyIntLocAmendments.setActionCode(actionCode);
				agyIntLocAmendments.setColumnName("Comments");
				agyIntLocAmendments.setOldValue(oldRef.getCertifiedFlag());
				agyIntLocAmendments.setNewValue(newRef.getCertifiedFlag());
				agyIntLocAmendments.setAmendUserId(newRef.getCreateUserId()!=null?newRef.getCreateUserId():newRef.getModifyUserId());
				agyIntLocAmendments.setCreateUserId(newRef.getCreateUserId());
				result = agencyInternalLocationsT1Repository.insertAgyIntLocAmendments(agyIntLocAmendments);
			}
		}
		if (oldRef.getUnitType() != null && newRef.getUnitType() != null) {
			if (isChanged(oldRef.getUnitType(), newRef.getUnitType(), lvActionCode)) {
				agyIntLocAmendments = new AgyIntLocAmendments();
				agyIntLocAmendments.setInternalLocationId(Long.valueOf(newRef.getInternalLocationId()));
				agyIntLocAmendments.setActionCode(actionCode);
				agyIntLocAmendments.setColumnName("Unit Type");
				agyIntLocAmendments.setOldValue(oldRef.getUnitType());
				agyIntLocAmendments.setNewValue(newRef.getUnitType());
				agyIntLocAmendments.setAmendUserId(newRef.getCreateUserId()!=null?newRef.getCreateUserId():newRef.getModifyUserId());
				agyIntLocAmendments.setCreateUserId(newRef.getCreateUserId());
				result = agencyInternalLocationsT1Repository.insertAgyIntLocAmendments(agyIntLocAmendments);
			}
		}
		if ((oldRef.getOperationCapacity() == null && newRef.getOperationCapacity() != null) 
				|| (oldRef.getOperationCapacity() != null && newRef.getOperationCapacity() == null) 
				|| (oldRef.getOperationCapacity() != null && newRef.getOperationCapacity() != null)) {
			if (isChanged(String.valueOf(oldRef.getOperationCapacity()), String.valueOf(newRef.getOperationCapacity()),
					lvActionCode)) {
				agyIntLocAmendments = new AgyIntLocAmendments();
				agyIntLocAmendments.setInternalLocationId(Long.valueOf(newRef.getInternalLocationId()));
				agyIntLocAmendments.setActionCode(actionCode);
				agyIntLocAmendments.setColumnName("Operational Capacity");
				agyIntLocAmendments.setOldValue(oldRef.getOperationCapacity()!=null?String.valueOf(oldRef.getOperationCapacity()):null);
				agyIntLocAmendments.setNewValue(newRef.getOperationCapacity()!=null?String.valueOf(newRef.getOperationCapacity()):null);
				agyIntLocAmendments.setAmendUserId(newRef.getCreateUserId()!=null?newRef.getCreateUserId():newRef.getModifyUserId());
				agyIntLocAmendments.setCreateUserId(newRef.getCreateUserId());
				result = agencyInternalLocationsT1Repository.insertAgyIntLocAmendments(agyIntLocAmendments);
			}
		}

		return result;
	}

}

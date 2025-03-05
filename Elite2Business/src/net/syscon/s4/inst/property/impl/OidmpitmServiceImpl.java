package net.syscon.s4.inst.property.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.property.OidmpitmRepository;
import net.syscon.s4.inst.property.OidmpitmService;
import net.syscon.s4.inst.property.OidrpitmRepository;
import net.syscon.s4.inst.property.OidtpritRepository;
import net.syscon.s4.inst.property.bean.Group;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItemEvents;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.triggers.OffenderPptyItemsT1Service;
import net.syscon.s4.triggers.OmtocontService;

/**
 * Class OidmpitmServiceImpl
 * 
 * 
 */
@Service
public class OidmpitmServiceImpl extends BaseBusiness implements OidmpitmService {

	private static final String UPDATING = "UPDATING";

	@Autowired
	private OidtpritRepository oidtpritDao;

	@Autowired
	private OidmpitmRepository oidmpitmRepository;

	@Autowired
	private OidrpitmRepository oidrpitmRepository;

	@Autowired
	private OffenderPptyItemsT1Service offenderPptyItemsT1Service;

	@Autowired
	private OmtocontService omtocontService;

	/**
	 * Creates new OidrpitmServiceImpl class Object
	 */
	public OidmpitmServiceImpl() {
		super();
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffenderPptyItems> offPiExecuteQuery(final OffenderPptyItems searchRecord) {
		List<OffenderPptyItems> returnList = new ArrayList<OffenderPptyItems>();

		returnList = oidmpitmRepository.offPiExecuteQuery(searchRecord);
		for (final OffenderPptyItems offpptm : returnList) {
			if (offpptm.getPropertyType() != null) {
				final String description = oidrpitmRepository.getDescrption(offpptm.getPropertyType());
				offpptm.setPptyDescription(description);
			}
		}
		return returnList;

	}

	@Override
	public List<Group> fetchGroupNames(String caseloadId) {
		return oidmpitmRepository.fetchGroupNames(caseloadId);
	}

	@Override
	public List<OffenderPptyItems> getDefaultValuesForSelecteGroup(String groupId) {
		return oidmpitmRepository.getDefaultValuesForSelecteGroup(groupId);
	}

	@Override
	public List<OffenderPptyItems> setpropDescForPropertyAttr(List<OffenderPptyItems> propertyItems) {
		for (final OffenderPptyItems obj : propertyItems) {
			if (obj.getPropertyType() != null) {
				final ReferenceCodes toStatus = oidtpritDao.getDescriptionOfPropertyCode(obj.getPropertyType());
				if (toStatus != null) {
					obj.setPropertyTypeDesc(toStatus.getDescription());
				}
			}
			/*if (obj.getConditionCode() != null) {
				final ReferenceCodes toStatus = oidtpritDao.getDescriptionOfConditionCode(obj.getConditionCode());
				if (toStatus != null) {
					obj.setConditionCode(toStatus.getDescription());
				}
			}*/
		}
		return propertyItems;
	}

	@Override
	public List<OffenderPptyContainers> offPiSearchOffenderPptyItemsForcontainer(
			List<OffenderPptyContainers> containersList) {
		List<OffenderPptyItems> returnList = new ArrayList<>();
		for (OffenderPptyContainers container : containersList) {
			if (container.getPropertyContainerId() != null || container.getStatusCode() != null) {
				returnList = oidmpitmRepository.offPiSearchOffenderPptyItemsForcontainer(container);
				container.setItemsForContainer(returnList);
			}
		}

		return containersList;
	}

	@Override
	public boolean isRegisterProOrContainerExist(Integer offenderBookId) {
		return oidmpitmRepository.isRegisterProOrContainerExist(offenderBookId);
	}

	@Transactional
	@Override
	public Integer deactivateContainer(OffenderPptyItems property) {
		final List<OffenderPptyContainers> updateCon = new ArrayList<>();
		final List<OffenderPptyItemEvents> insertEventList = new ArrayList<>();
		final OffenderPptyItemEvents eventBean = new OffenderPptyItemEvents();
		// Get props based on containerID and bookId
		List<OffenderPptyItems> propList = oidmpitmRepository.getContainerProps(property);
		// Old List data
		List<OffenderPptyItems> propListOld = new ArrayList<>();
		if (propList != null && propList.size() > 0 && !propList.isEmpty()) {
			propList.forEach(bo -> {
				// Old data getting
				OffenderPptyItems offeOldData = oidmpitmRepository.updatePropertiesOldData(bo);
				propListOld.add(offeOldData);
			});
		}
		if (!propList.isEmpty()) {
			Integer eventSeq = oidmpitmRepository.getEventSeq();
			if (eventSeq == null) {
				eventSeq = 0;
			}
			eventSeq = eventSeq + 1;
			eventBean.setOffenderBookId(property.getOffenderBookId());
			eventBean.setEventSeq(eventSeq);
			eventBean.setModifyUserId(property.getCreateUserId());
			eventBean.setCreateUserId(property.getCreateUserId());
			insertEventList.add(eventBean);
			oidmpitmRepository.saveEvent(insertEventList);
			for (final OffenderPptyItems obj : propList) {
				obj.setStatusCode("DISPOSED");
				obj.setConfirmFlag("N");
				obj.setDisposedToPerson(property.getDisposedToPerson());
				obj.setDisposedToPersonId(property.getDisposedToPersonId());
				obj.setDisposedToOffenderFlag(property.getDisposedToOffenderFlag());
				obj.setCommentText(property.getCommentText());
				obj.setDisposedToCorpId(property.getDisposedToCorpId());
				obj.setModifyUserId(property.getCreateUserId());
			}
			oidmpitmRepository.updateProperties(propList);
			if (propList != null) {
				for (int i = 0; i < propList.size(); i++) {
					// Trigger call
					offenderPptyItemsT1Service.offenderPptyItemsT1(propListOld.get(i), propList.get(i), UPDATING);
				}
			}
		}
		// Run Deactivate logic
		OffenderPptyContainers contBean = new OffenderPptyContainers();
		contBean.setOffenderBookId(property.getOffenderBookId());
		contBean.setPropertyContainerId(property.getPropertyContainerId());
		contBean.setCommentText(property.getCommentText());
		contBean.setModifyUserId(property.getCreateUserId());
		contBean.setCreateUserId(property.getCreateUserId());
		contBean.setSealMark(property.getSealMark());
		updateCon.add(contBean);
		if (updateCon != null) {
			updateCon.forEach(bo -> {
				// Trigger call
				omtocontService.omtocontTrg(contBean, UPDATING);
			});
		}
		Integer returnValue = oidmpitmRepository.deactivateContainer(updateCon);
		return returnValue;
	}

}
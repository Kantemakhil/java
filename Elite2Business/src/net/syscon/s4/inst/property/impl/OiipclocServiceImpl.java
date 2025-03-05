package net.syscon.s4.inst.property.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.PropertyStorages;
import net.syscon.s4.inst.property.OiipclocRepository;
import net.syscon.s4.inst.property.OiipclocService;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyContainersCommitBean;

/**
 * Class OiipclocServiceImpl
 */
@Service
public class OiipclocServiceImpl extends BaseBusiness implements OiipclocService {

	@Autowired
	private OiipclocRepository oiipclocRepository;

	/**
	 * Creates new OiipclocServiceImpl class Object
	 */
	public OiipclocServiceImpl() {
		// OiipclocServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public VHeaderBlock cgfkchkOffConOffConVPhe(final VHeaderBlock paramBean) {
		return oiipclocRepository.cgfkchkOffConOffConVPhe(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<PropertyStorages> cgfklkpOffConOffConPpty(final PropertyStorages paramBean) {
		return oiipclocRepository.cgfklkpOffConOffConPpty(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<Object> cgwhenNewFormInstance() {
		return null;
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<AgencyInternalLocations> cgfkchkOffConOffConPpty(final AgencyInternalLocations paramBean) {
		return oiipclocRepository.cgfkchkOffConOffConPpty(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<OffenderPptyContainers> offConExecuteQuery(final OffenderPptyContainers searchRecord) {
		final VHeaderBlock headerBlockObj = new VHeaderBlock();
		if ("true".equals(searchRecord.getActiveFlag())) {
			searchRecord.setActiveFlag("Y");
		} else {
			searchRecord.setActiveFlag("N");
		}
		final List<OffenderPptyContainers> returnList = oiipclocRepository.offConExecuteQuery(searchRecord);
		for (final OffenderPptyContainers obj : returnList) {
			if (obj.getInternalLocationId() != null) {
				obj.setCode(obj.getInternalLocationId().toString());
			}
			headerBlockObj.setOffenderBookId(BigDecimal.valueOf(obj.getOffenderBookId()));
			headerBlockObj.setCreateUserId(searchRecord.getCreateUserId());
			final VHeaderBlock beanObj = cgfkchkOffConOffConVPhe(headerBlockObj);
			obj.setAgyLocId(beanObj.getAgyLocId());
			obj.setFirstName(beanObj.getFirstName());
			obj.setLastName(beanObj.getLastName());
			obj.setOffenderIdDisplay(beanObj.getOffenderIdDisplay());
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_CON
	 *
	 * @
	 */
	@Transactional
	public Integer offConCommit(final OffenderPptyContainersCommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgContainerCodeRecordGroup() {
		return oiipclocRepository.rgContainerCodeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<AgencyInternalLocations> rgDescriptionRecordGroup(final String caseloadId) {
		final List<AgencyInternalLocations> returnList = oiipclocRepository.rgDescriptionRecordGroup(caseloadId);
		
		return returnList;
	}

}
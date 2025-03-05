package net.syscon.s4.inst.property.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.inst.property.OidtpconRepository;
import net.syscon.s4.inst.property.OidtpconService;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyContainersCommitBean;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.inst.property.bean.PropertyStorages;
import net.syscon.s4.pkgs.oms_property.OmsPropertyService;
import net.syscon.s4.triggers.OmtocontService;

/**
 * Class OidtpconServiceImpl
 */
@Service
public class OidtpconServiceImpl extends BaseBusiness implements OidtpconService {

	private final static String UPDATING = "UPDATING";
	@Autowired
	private OidtpconRepository oidtpRepository;
	@Autowired
	private OmsPropertyService omsPropertyService;
	@Autowired
	private OmtocontService omtocontService;

	/**
	 * Creates new OidtpconServiceImpl class Object
	 */
	public OidtpconServiceImpl() {
		// OidtpconServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<Object> vPheadOnCheckDeleteMaster(final OffenderPptyContainers paramBean) {
		return oidtpRepository.vPheadOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public AgencyInternalLocations offConPostQuery(final AgencyInternalLocations paramBean) {
		return oidtpRepository.offConPostQuery(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public String offConWhenNewRecordInstance(final Integer paramBean) {
		return oidtpRepository.offConWhenNewRecordInstance(paramBean);

	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public AgencyLocations cgfkchkOffConOffConAgy(final AgencyLocations paramBean) {
		return oidtpRepository.cgfkchkOffConOffConAgy(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public PropertyStorages cgfkchkOffConOffConPpty(final PropertyStorages paramBean) {
		return oidtpRepository.cgfkchkOffConOffConPpty(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public Dual cgwhenNewFormInstance(String user) {
		return oidtpRepository.cgwhenNewFormInstance(user);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderPptyContainers> offConExecuteQuery(final OffenderPptyContainers searchRecord,String opertaion) {
		List<OffenderPptyContainers> returnList = new ArrayList<>();
		final ReferenceCodes refObj = new ReferenceCodes();
		final AgencyInternalLocations agencyObj = new AgencyInternalLocations();
		if(opertaion!=null && opertaion.equals("PENDING")) {
			returnList = oidtpRepository.offPenPropConExecuteQuery(searchRecord);
		}else {
			returnList = oidtpRepository.offConExecuteQuery(searchRecord);
		}
			
		
		for (final OffenderPptyContainers obj : returnList) {
			if (obj != null && obj.getContainerCode() != null) {
				refObj.setCode(obj.getContainerCode());
				final ReferenceCodes returnRefObj = oidtpRepository.cgfkchkOffConOffConRef(refObj);
				obj.setContainerCode(returnRefObj.getDescription());

			}
			if (obj != null && obj.getInternalLocationId() != null) {
				agencyObj.setInternalLocationId(obj.getInternalLocationId());
				final AgencyInternalLocations returnAgencyObj = oidtpRepository.offConPostQuery(agencyObj);
				obj.setDescription(returnAgencyObj.getDescription());
				final String gvAgyLocId = oidtpRepository.offConWhenNewRecordInstance(obj.getInternalLocationId());
				obj.setGvAgyLocId(gvAgyLocId);
			}

		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_CON
	 *
	 */
	@Transactional
	public Integer offConCommit(final OffenderPptyContainersCommitBean commitBean) {
		Integer liReturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final OffenderPptyContainers bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				OffenderPptyContainers bean1 = new OffenderPptyContainers();
				BeanUtils.copyProperties(bean, bean1);
				bean1.setAgyLocId("OUT");
				bean1.setStatusCode("TRANSIT");
				omsPropertyService.updateTransferredItems(bean1, "UPDATING");
			}
		}
		liReturn = offConUpdateOffenderPptyContainers(commitBean.getUpdateList());

		return liReturn;
	}

	@Transactional
	public Integer offConUpdateOffenderPptyContainers(final List<OffenderPptyContainers> offderPptyCon) {
		for (final OffenderPptyContainers obj : offderPptyCon) {
			if (obj.getInternalLocationId() != null && obj.getInternalLocationId() != 0) {
				obj.setInternalLocationId(null);
			}
			obj.setTrnFromAgyLocId(obj.getAgyLocId());
			obj.setAgyLocId("OUT");
			// Trigger call
			omtocontService.omtocontTrg(obj, UPDATING);
		}
		return oidtpRepository.offConUpdateOffenderPptyContainers(offderPptyCon);

	}
	
	
	


	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles searchRecord) {
		return oidtpRepository.sysPflExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstSYS_PFL
	 *
	 */
	@Transactional
	public Integer sysPflCommit(final SystemProfilesCommitBean commitBean) {
		final Integer liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<AgencyLocations> cgfkOffConTrnToAgyLocIdRecordGroup(String agyLocId) {

		if (agyLocId == null || agyLocId.isEmpty() || "undefined".equalsIgnoreCase(agyLocId)
				|| "null".equalsIgnoreCase(agyLocId)) {
			agyLocId = null;
		}
		List<AgencyLocations> returnList = new ArrayList<>();
		returnList = oidtpRepository.cgfkOffConTrnToAgyLocIdRecordGroup(agyLocId);
		for (final AgencyLocations obj : returnList) {
			if (obj.getAgyLocId() != null) {
				obj.setCode(obj.getAgyLocId());
			}
		}
		if(Optional.ofNullable(returnList).isPresent()) {
			returnList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<AgencyLocations> rgSelectAllRecordGroup() {
		return oidtpRepository.rgSelectAllRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public ReferenceCodes cgfkchkOffConOffConRef(final ReferenceCodes paramBean) {
		return oidtpRepository.cgfkchkOffConOffConRef(paramBean);

	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 *
	 */
	public List<OffenderPptyItems> getItemStatus(final OffenderPptyItems paramBean) {
		return oidtpRepository.getItemStatus(paramBean);
	}

	/**
	 * * Method is used to update the offender_ppty_items table by using procedure.
	 * 
	 * @param offderPptyCon
	 * @return Integer
	 */
	@Transactional
	public Integer postUpdateOfOffenderPptyItems(final List<OffenderPptyContainers> offderPptyCon) {
		for (final OffenderPptyContainers obj : offderPptyCon) {
			omsPropertyService.updateTransferredItems(obj, obj.getCreateUserId());
		}
		return 1;
	}

	@Override
	public Integer offPendPropCommit(OffenderPptyContainersCommitBean commitBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer offPenPropConCommit(OffenderPptyContainersCommitBean commitBean) {
		Integer liReturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (final OffenderPptyContainers obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
				obj.setStatusCode("STORED");
				obj.setActionCode("CANCEL");
				obj.setActionReason(obj.getCancelReason());
				omsPropertyService.updatePropertyItemDetails(obj);
			}
		}
		liReturn = updateOffenderPropertyContainer(commitBean.getUpdateList());

		return liReturn;
	}
	
	@Transactional
	public Integer updateOffenderPropertyContainer(final List<OffenderPptyContainers> offderPptyCon) {
		for (final OffenderPptyContainers obj : offderPptyCon) {
			obj.setTrnFromAgyLocId(null);
			obj.setTrnToAgyLocId(null);
			omsPropertyService.InsertContainerTransaction(obj,"CANCEL");
		}
		return oidtpRepository.offConUpdateOffenderPptyContainers(offderPptyCon);

	}

}
package net.syscon.s4.inst.property.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.property.OidrtconRepository;
import net.syscon.s4.inst.property.OidrtconService;
import net.syscon.s4.inst.property.OidtpconRepository;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyContainersCommitBean;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.pkgs.oms_property.OmsPropertyService;
import net.syscon.s4.triggers.OmtocontService;

/**
 * Class OidrtconServiceImpl
 * 
 */
@Service
public class OidrtconServiceImpl extends BaseBusiness implements OidrtconService {

	private static final String UPDATING = "UPDATING";

	@Autowired
	private OidrtconRepository oidrtconRepository;

	@Autowired
	private OmsPropertyService omsPropertyService;

	@Autowired
	private OmtocontService omtocontService;
	
	@Autowired
	private OidtpconRepository oidtpRepository;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidrtconServiceImpl.class.getName());

	/**
	 * Creates new OidrtconServiceImpl class Object
	 */
	public OidrtconServiceImpl() {
		// OidrtconServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkOffConOffConRef(final ReferenceCodes paramBean) {
		return oidrtconRepository.cgfkchkOffConOffConRef(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfklkpOffConOffConRef(final ReferenceCodes paramBean) {
		return oidrtconRepository.cgfklkpOffConOffConRef(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public Map<String, Object> cgfkchkOffConOffConOff(final OffenderBookings paramBean) {
		return oidrtconRepository.cgfkchkOffConOffConOff(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<SysDual> cgwhenNewFormInstance() {
		List<SysDual> result = null;
		try {
			result = oidrtconRepository.cgwhenNewFormInstance(null);
		} catch (Exception e) {
			logger.error("CgwhenNewFormInstance", e);
		}
		return result;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<OffenderPptyContainers> offConExecuteQuery(final OffenderPptyContainers searchRecord) {
		Map<String, Object> offBkngs = new HashMap<>();
		final OffenderBookings offBkg = new OffenderBookings();
		List<OffenderPptyContainers> resultList = null;
		try {
			resultList = oidrtconRepository.offConExecuteQuery(searchRecord);
			for (final OffenderPptyContainers result : resultList) {

				offBkg.setOffenderBookId(Long.valueOf(result.getOffenderBookId()));
				offBkngs = oidrtconRepository.cgfkchkOffConOffConOff(offBkg);
				if (offBkngs.get("OFFENDER_NAME") != null) {
					result.setOffenderName(offBkngs.get("OFFENDER_NAME").toString());
				}
				if (offBkngs.get("OFFENDER_ID_DISPLAY") != null) {
					result.setOffenderIdDisplay(offBkngs.get("OFFENDER_ID_DISPLAY").toString());
				}
			}
			Collections.sort(resultList, (o1, o2) -> Integer.parseInt(o1.getOffenderIdDisplay())
					- Integer.parseInt(o2.getOffenderIdDisplay()));
		} catch (Exception e) {
			logger.error("offConExecuteQuery", e);
		}
		return resultList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_CON
	 *
	 */
	@Transactional
	public OffenderPptyContainers offConCommit(final OffenderPptyContainersCommitBean commitBean) {
		int liReturn = 0;
		OffenderPptyContainers returnObj=new OffenderPptyContainers();
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (OffenderPptyContainers bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				bean.setActiveFlag("Y");
				if(bean.getRejectFlag() !=null && bean.getRejectFlag().equals("Y")) {
					final Integer locationId = omsPropertyService.getTranRoomStorageId(bean.getTrnFromAgyLocId());
					if (locationId == 0) {
						returnObj.setSealFlag(bean.getTrnFromAgyLocId());
						return returnObj;
					}
					bean.setAgyLocId(bean.getTrnFromAgyLocId());
					bean.setInternalLocationId(locationId);
					bean.setStatusCode("STORED");
					bean.setActionCode("REJECT");
					bean.setActionReason(bean.getRejectReason());
				}else {
					bean.setAgyLocId(bean.getTrnToAgyLocId());
					bean.setStatusCode("STORED");
					bean.setActionCode("TR-IN");
				}
				
				bean.setTrnFromAgyLocId(null);
				bean.setTrnToAgyLocId(null);
				
				
				omsPropertyService.updatePropertyItemDetails(bean);
				
			}
			liReturn=updateOffenderPropertyContainer(commitBean.getUpdateList());
			returnObj.setSealFlag(String.valueOf(liReturn));
			
		}
		return returnObj;
	}
	
	
	@Transactional
	public Integer updateOffenderPropertyContainer(final List<OffenderPptyContainers> offderPptyCon) {
		for (final OffenderPptyContainers obj : offderPptyCon) {
			obj.setTrnFromAgyLocId(null);
			obj.setTrnToAgyLocId(null);
			omsPropertyService.InsertContainerTransaction(obj,obj.getActionCode());
		}
		return oidtpRepository.offConUpdateOffenderPptyContainers(offderPptyCon);

	}

	@Override
	public List<AgencyLocations> oidrtconRecievedFromLov() {
		final List<AgencyLocations> resultList = oidrtconRepository.oidrtconRecievedFromLov();
		for (final AgencyLocations result : resultList) {
			if (result.getAgyLocId() != null) {
				result.setCode(result.getAgyLocId());
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
	 * * Method is used to update the offender_ppty_items table by using procedure.
	 * 
	 * @param offderPptyCon
	 * @return Integer
	 */
	@Transactional
	public Integer postUpdateOfOffenderPptyItems(final List<OffenderPptyContainers> offderPptyCon) {
		Integer result = 0;
		for (final OffenderPptyContainers obj : offderPptyCon) {
			obj.setStatusCode("STORED");
			result = omsPropertyService.updateTransferredItems(obj, UPDATING);
		}
		return result;
	}

}
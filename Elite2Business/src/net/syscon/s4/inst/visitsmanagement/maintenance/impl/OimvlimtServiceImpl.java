package net.syscon.s4.inst.visitsmanagement.maintenance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;
import net.syscon.s4.inst.visitsmanagement.beans.VisitCycleLimits;
import net.syscon.s4.inst.visitsmanagement.beans.VisitCycleLimitsCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.VisitTypeLimits;
import net.syscon.s4.inst.visitsmanagement.beans.VisitTypeLimitsCommitBean;
import net.syscon.s4.inst.visitsmanagement.maintenance.OimvlimtRepository;
import net.syscon.s4.inst.visitsmanagement.maintenance.OimvlimtService;

/**
 * Class OimvlimtServiceImpl
 */
@Service
public class OimvlimtServiceImpl extends BaseBusiness implements OimvlimtService {

	@Autowired
	private OimvlimtRepository oimvlimtRepository;

	private static Logger logger = LogManager.getLogger(OimvlimtServiceImpl.class.getName());

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public Long visCycPreInsert(final VisitCycleLimits paramBean) {
		return oimvlimtRepository.visCycPreInsert(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 *               n
	 */
	public List<VisitTypeLimits> visCycOnCheckDeleteMaster(final VisitTypeLimits paramBean) {
		return oimvlimtRepository.visCycOnCheckDeleteMaster(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public VisitCycleLimits visCycPreUpdate(final VisitCycleLimits paramBean) {
		return oimvlimtRepository.visCycPreUpdate(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<VisitCycleLimits> visCycExecuteQuery(final VisitCycleLimits searchRecord) {
		final List<VisitCycleLimits> returnList = oimvlimtRepository.visCycExecuteQuery(searchRecord);
		returnList.forEach(action -> {
			
			
			if (action.getTotHrs() != null) {
				final Double tHrs = action.getTotHrs().doubleValue();
				final Double data = Math.floor(tHrs);
				final BigDecimal dataObj = action.getTotHrs().subtract(BigDecimal.valueOf(data));
				final BigDecimal finalData = dataObj.multiply(BigDecimal.valueOf(60));
				final long tMin = Math.round(finalData.floatValue());
				action.setTmin(tMin);
				action.setTotHrs(BigDecimal.valueOf(data));
			}
		});
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstVIS_CYC
	 *
	 * 
	 */
	@Transactional
	public Integer visCycCommit(final VisitCycleLimitsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (VisitCycleLimits obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			List<VisitCycleLimits> recordSavingObject = new ArrayList<>();
			if (commitBean.getInsertList().size() > 0) {
				for (int i = 0; i < commitBean.getInsertList().size(); i++) {
					recordSavingObject = new ArrayList<>();
					final VisitCycleLimits offPropItemObj = commitBean.getInsertList().get(i);
					final Long visitCycleId = oimvlimtRepository.visitCycleLimitId();
					offPropItemObj.setVisitCycleLimitId(visitCycleId);
					recordSavingObject.add(offPropItemObj);
					liReturn = oimvlimtRepository.visCycInsertVisitCycleLimits(recordSavingObject);
				}
			}
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (VisitCycleLimits obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimvlimtRepository.visCycUpdateVisitCycleLimits(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<VisitTypeLimits> visTypExecuteQuery(final VisitTypeLimits searchRecord) {
		final List<VisitTypeLimits> returnList = oimvlimtRepository.visTypExecuteQuery(searchRecord);
		returnList.forEach(action -> {
			if (action.getMaxHrsType() != null) {
				final Double tHrs = action.getMaxHrsType().doubleValue();
				final Double data = Math.floor(tHrs);
				final BigDecimal dataObj = action.getMaxHrsType().subtract(BigDecimal.valueOf(data));
				final BigDecimal finalData = dataObj.multiply(BigDecimal.valueOf(60));
				final long MMin = Math.round(finalData.floatValue());
				action.setMaxHrsType(BigDecimal.valueOf(data));
				action.setMmin(MMin);
				action.setMaxHrsTypeTemp(BigDecimal.valueOf(tHrs));
			}
		});
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstVIS_TYP
	 *
	 * 
	 */
	@Transactional
	public Integer visTypCommit(final VisitTypeLimitsCommitBean commitBean) {
		int liReturn = 0;
		// insertRecords
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {

			for (VisitTypeLimits obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimvlimtRepository.visTypInsertVisitTypeLimits(commitBean.getInsertList());
		}
		// updateRecords
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {

			for (VisitTypeLimits obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oimvlimtRepository.visTypUpdateVisitTypeLimits(commitBean.getUpdateList());
		}
		return liReturn;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgSecLvlRecordGroup() {
		return oimvlimtRepository.rgSecLvlRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgCycTypRecordGroup() {
		return oimvlimtRepository.rgCycTypRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgVisTypRecordGroup() {
		return oimvlimtRepository.rgVisTypRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> rgStrDayRecordGroup() {
		return oimvlimtRepository.rgStrDayRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<AgencyLocations> rgAgyIntLocRecordGroup(String userName) {
		final List<AgencyLocations> returnList = oimvlimtRepository.rgAgyIntLocRecordGroup(userName);
		returnList.forEach(ele -> {
			if (ele.getAgyLocId() != null) {
				ele.setCode(ele.getAgyLocId().toString());
			}
		});
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

	@Override
	public List<IepLevelBean> rgIepLevelRecordGroup() {
		List<IepLevelBean> returnList = new ArrayList<>();
		returnList = oimvlimtRepository.rgIepLevelRecordGroup();
		if(returnList != null && !returnList.isEmpty()) {
			returnList.forEach(e -> {
				if(e.getActiveFlag() != null && e.getActiveFlag().equalsIgnoreCase(ApplicationConstants.YES)) {
					e.setCanDisplay(true);
				}else {
					e.setCanDisplay(false);
				}
			});
		}
		return returnList;
	}

	@Override
	public List<VisitCycleLimits> iepLevelExecuteQuery(VisitCycleLimits searchBean) {
		final List<VisitCycleLimits> returnList = oimvlimtRepository.iepLevelExcuteQuery(searchBean);
		returnList.forEach(action -> {
			if (action.getTotHrs() != null) {
				final Double tHrs = action.getTotHrs().doubleValue();
				final Double data = Math.floor(tHrs);
				final BigDecimal dataObj = action.getTotHrs().subtract(BigDecimal.valueOf(data));
				final BigDecimal finalData = dataObj.multiply(BigDecimal.valueOf(60));
				final long tMin = Math.round(finalData.floatValue());
				action.setTmin(tMin);
				action.setTotHrs(BigDecimal.valueOf(data));

			}
		});
		return returnList;

	}

	@Override
	public List<IepLevelBean> getIEPDetails(Long offenderBookId) {
		try {
			return oimvlimtRepository.getIEPDetails(offenderBookId);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + "In getIepVisitLimis method : ", e);
			return new ArrayList<IepLevelBean>();
		}

	}

	@Override
	public Integer saveIepSecData(String facility, String iepSecLevel, String user, String operaion) {
		try {
			VisitCycleLimits limits=getIepVisitLimis(facility);
			if(limits!=null && limits.getAgyLocId()!=null) {
				operaion=ApplicationConstants.UPDATE;
			}else {
				operaion=ApplicationConstants.INSERT;
			}
			return oimvlimtRepository.saveIepSecData(facility, iepSecLevel, user, operaion);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + "In saveIepSecData method : ", e);
			return 0;
		}
	}

	@Override
	public VisitCycleLimits getIepVisitLimis(String agylocid) {
		try {
			return oimvlimtRepository.getIepVisitLimis(agylocid);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + "In getIepVisitLimis method : ", e);
			return new VisitCycleLimits();
		}
	}
	@Override
	public List<ReferenceCodes> getIepSecLov(){
		try {
			return oimvlimtRepository.getIepSecLov();
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + "In getIepSecLov method : ", e);
			return Arrays.asList();
		}
	}

}
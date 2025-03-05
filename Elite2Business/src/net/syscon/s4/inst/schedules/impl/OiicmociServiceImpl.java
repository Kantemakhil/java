package net.syscon.s4.inst.schedules.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.legalscreens.bean.VCourtEvents;
import net.syscon.s4.inst.legalscreens.bean.VCourtEventscommitBean;
import net.syscon.s4.inst.schedules.OiicmociRepository;
import net.syscon.s4.inst.schedules.OiicmociService;

/**
 * Class OiicmociServiceImpl
 */
@Service
public class OiicmociServiceImpl extends BaseBusiness implements OiicmociService {

	@Autowired
	private OiicmociRepository oiicmociRepository;

	/**
	 * Creates new OiicmociServiceImpl class Object
	 */
	public OiicmociServiceImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<VCourtEvents> offSchExecuteQuery(VCourtEvents searchRecord) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(searchRecord.getEventDate());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		searchRecord.setEventDate(calendar.getTime());
		return oiicmociRepository.offSchExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstOFF_SCH
	 *
	 * 
	 */
	@Transactional
	public Integer offSchCommit(VCourtEventscommitBean commitBean) {
		int liReturn = 0;
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<AgencyLocations> rgAgyLocIdRecordGroup(String caseLoadId) {
		List<AgencyLocations> refList = oiicmociRepository.rgAgyLocIdRecordGroup(caseLoadId);
		if(Optional.ofNullable(refList).isPresent()) {
			refList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return refList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 *
	 */
	public List<LivingUnits> rgLu1RecordGroup(final String agyLocId) {
		List<LivingUnits> resultList = oiicmociRepository.rgLu1RecordGroup(agyLocId);
		for (LivingUnits result : resultList) {
			result.setDescription(result.getLivingUnitCode());
			result.setCode(result.getLivingUnitId().toString());
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
	 *
	 */
	public List<LivingUnits> rgLu2RecordGroup(final String agyLocId, final Integer livingUnit) {
		List<LivingUnits> resultList = oiicmociRepository.rgLu2RecordGroup(agyLocId, livingUnit);
		for (LivingUnits result : resultList) {
			result.setDescription(result.getLivingUnitCode());
			result.setCode(result.getLivingUnitId().toString());
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
	 *
	 */
	public List<LivingUnits> rgLu3RecordGroup(final String agyLocId, final Integer livingUnit) {
		List<LivingUnits> resultList = oiicmociRepository.rgLu3RecordGroup(agyLocId, livingUnit);
		for (LivingUnits result : resultList) {
			result.setDescription(result.getLivingUnitCode());
			result.setCode(result.getLivingUnitId().toString());
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

}
package net.syscon.s4.inst.movements.maintenance.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgyIntLocAmendQuery;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.movements.maintenance.OumhlhisRepository;
import net.syscon.s4.inst.movements.maintenance.OumhlhisService;
import net.syscon.s4.pkgs.oumhlhis.OumhlhisPkgService;

/**
 * Class OumhlhisServiceImpl
 */
@Service
public class OumhlhisServiceImpl extends BaseBusiness implements OumhlhisService {

	@Autowired
	private OumhlhisRepository oumhlhisRepository;

	@Autowired
	private OumhlhisPkgService oumhlhisService;

	/**
	 * This method is used to execute a record group
	 */
	public List<LivingUnits> livingUnitOneRgRecordGroup(final String agyLocId) {
		final List<LivingUnits> returnList = oumhlhisRepository.livingUnitOneRgRecordGroup(agyLocId);
		returnList.forEach(ele -> {
			if (ele.getLivingUnitId() != null) {
				ele.setCode(ele.getLivingUnitId().toString());
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

	/**
	 * This method is used to execute a record group
	 */
	public List<LivingUnits> livingUnitTwoRgRecordGroup(final Long livingUnitId, final String level1Code) {
		final List<LivingUnits> returnList = oumhlhisRepository.livingUnitTwoRgRecordGroup(livingUnitId, level1Code);
		returnList.forEach(ele -> {
			if (ele.getLivingUnitId() != null) {
				ele.setCode(ele.getLivingUnitId().toString());
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

	/**
	 * This method is used to execute a record group
	 */
	public List<LivingUnits> livingUnitThreeRgRecordGroup(final Long livingUnitId, final String level2Code) {
		final List<LivingUnits> returnList = oumhlhisRepository.livingUnitThreeRgRecordGroup(livingUnitId, level2Code);
		returnList.forEach(ele -> {
			if (ele.getLivingUnitId() != null) {
				ele.setCode(ele.getLivingUnitId().toString());
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

	/**
	 * This method is used to execute a record group
	 */
	public List<LivingUnits> livingUnitFourRgRecordGroup(final Long livingUnitId, final String level3Code) {
		final List<LivingUnits> returnList = oumhlhisRepository.livingUnitFourRgRecordGroup(livingUnitId, level3Code);
		returnList.forEach(ele -> {
			if (ele.getLivingUnitId() != null) {
				ele.setCode(ele.getLivingUnitId().toString());

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

	/**
	 * This method is used to execute a AgyLocId
	 */
	public String getAgyLocIdDescReturn(final String userId) {
		return oumhlhisRepository.getAgyLocIdDescReturn(userId);
	}

	/**
	 * This method is used to execute a AgyIntLocAmendQuery
	 */
	public List<AgyIntLocAmendQuery> vAgyIntLocAmendExecuteQuery(final AgyIntLocAmendQuery objSearchDao) {
		return oumhlhisService.agyIntLocAmendQuery(objSearchDao);
	}

	/**
	 * This method is used to execute a AgyIntLocAmendQuery
	 */
	public List<LivingUnits> getLivingunitId(final String agyLocId) {
		return oumhlhisRepository.getLivingunitId(agyLocId);
	}

}
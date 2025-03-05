package net.syscon.s4.pkgs.oimmholo.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.pkgs.oimmholo.OimmholoPkgRepository;
import net.syscon.s4.pkgs.oimmholo.OimmholoPkgService;
import net.syscon.s4.triggers.AgyIntLocAmendments;
import net.syscon.s4.triggers.AgyIntLocProfilesT1Service;

/*
 * --Below comments are copied from package oimmholo
   -- To modify this template, edit file PKGBODY.TXT in TEMPLATE
   -- directory of SQL Navigator
   --
   -- Purpose: This package contains the procedures for the form OIMMHOLO.
   --
   -- MODIFICATION HISTORY
   -- Person      Date          Version      Comments
   -- ---------   ------        ----------   ------------------------------------------ 
   -- Nasir       19-JUL-2016   2.15         HPQC#27004 Modified Procedure get_all_attributes and increase the size of variable from 1024 to 2000.
   -- Nasir       27-APR-2015   2.14         #25504 Modified PROCEDURE update_parent_cap_and_cna.
   -- TBriggs     02-APR-2015   2.13         #25417 Modified procedure update_parent_cap_and_cna.
   -- Nasir       27-SEP-2013   2.12         #20778 Modified PROCEDURE update_parent_cap_and_cna.
   -- Rose        28-MAY-2008   2.11         #6142: Modified PROCEDURE update_parent_cap_and_cna that only 'Active' child records are counted.
   -- Rose        27-MAY-2008   2.10         #6142: Modified PROCEDURE update_parent_cap_and_cna that only 'Active' child records are counted.
   -- Rose        06-MAY-2008   2.9          #6142: Modified PROCEDURE update_parent_cap_and_cna to remove the reference to certified cells.
   --                                         The ability to set a location as 'certified' has been removed from the screen and the database column is set to   DEFAULT TO 'N' .
   -- Niko        19-Nov-2007   2.8          Modified FUNCTION child_lu_offender_assigned to check the child records
   -- GJC         14-Oct-2006   2.7          SHOW_VERSION changed from procedure to function
   -- Patrick     22-SEP-2005   2.5          Added function to check whether the living unit is occupied including its lower levels
   --                                        Added child_lu_offender_assigned
   -- Patrick     22-SEP-2005   2.4          Modified cursor in update_parent_cap_and_cna to include only records with certified_flag
   -- Patrick     05-SEP-2005   2.3          T+ 676. Change the way of the closure of the cursor in update_parent_cap_and_cna procedure
   -- Patrick     25-AUG-2005   2.2          Fixed bugs after peer review.
   -- Patrick     18-AUG-2005   2.1         Changed living_unit_offender_assigned for lowest level only.
   -- Patrick     11-AUG-2005   2.0         Initial version.

*/
@Service
public class OimmholoPkgServiceImpl implements OimmholoPkgService {
	private static final String N = "N";
	@Autowired
	private OimmholoPkgRepository oimmholoRepository;
	private static Logger logger = LogManager.getLogger(OimmholoPkgServiceImpl.class.getName());

	@Autowired
	private AgyIntLocProfilesT1Service agyIntLocProfilesT1Service;
	
	@Override
	public String getNewLuType(final LivingUnits bean) {
		return oimmholoRepository.getNLuType(bean.getCapacity().intValue(), bean.getAgyLocId());
	}
	@Override
	public LivingUnits defaultLivingUnitDesc(final LivingUnits objSearchDao) {
		final LivingUnits livUnits = new LivingUnits();
		String lvDesc = null;
		try {
			if (objSearchDao.getLivingUnitId() != null) {
				lvDesc = oimmholoRepository.getDefaultLuDesc(objSearchDao.getLivingUnitId(),
						objSearchDao.getLivingUnitCode());
			} else if (objSearchDao.getAgyLocId() != null) {
				lvDesc = objSearchDao.getAgyLocId() + '-' + objSearchDao.getLivingUnitCode();
			}
			livUnits.setDescription(lvDesc);
		} catch (Exception e) {
			logger.error("defaultLivingUnitDesc :" + e);
		}

		return livUnits;
	}

	
	@Override
	public Integer updateParentCapAndCna(final BigDecimal livingUnitId,String userName) {
		BigDecimal lvCapacity;
		BigDecimal lvCna;
		BigDecimal operationCapacity;
		Integer retVal = 0;
		try {
			final List<LivingUnits> retList = oimmholoRepository.getLivingUnitsDetails(livingUnitId);
			for (final LivingUnits lu : retList) {
				final LivingUnits obj = oimmholoRepository.getChildTotals(lu.getLivingUnitId());
				lvCapacity = obj.getCapacity();
				lvCna = obj.getCnaNo();
				operationCapacity=obj.getOperationCapacity();
				final List<LivingUnits> returnLivingUId = oimmholoRepository.getLivUnitUpdCur(lu.getLivingUnitId());
				for (final LivingUnits bean : returnLivingUId) {
					retVal = oimmholoRepository.updateLivingUnits(lvCapacity, lvCna,operationCapacity, bean.getLivingUnitId(),userName);
				}
			}
			retVal = 1;
		} catch (Exception e) {
			logger.error("updateParentCapAndCna :" + e);
			retVal = 0;
		}
		return retVal;
	}

	@Override
	public Integer insertChildAllLuProfiles(final LivingUnits object) {
		try {
			oimmholoRepository.getAllAttributesCur(object.getParentLivingUnitId()).forEach(obj -> {
				oimmholoRepository.agyIntLocProfiles(object.getLivingUnitId());
				AgyIntLocAmendments agy = new AgyIntLocAmendments();
				BeanUtils.copyProperties(object, agy);
				agyIntLocProfilesT1Service.agyIntLocProfilesT1Trigger("INSERTING", null, agy);
			});
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
	
	@Override
	public Integer actDeactChildLu(final LivingUnits object) {
		try {
			if (object.getActiveFlag().equals(N))
				oimmholoRepository.livingUnitsUpdate(object);
			else
				oimmholoRepository.livingUnitsUpdateElse(object);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

}

package net.syscon.s4.pkgs.ocmsuwpj.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.schedules.bean.VCorporateAddresses;
import net.syscon.s4.pkgs.ocmsuwpj.OcmsuwpjPkgRepository;
import net.syscon.s4.pkgs.ocmsuwpj.OcmsuwpjPkgService;

/*
 * Below comments are copied from package Ocmsuwpj
-- To modify this template, edit file PKGBODY.TXT in TEMPLATE
-- directory of SQL Navigator
--
-- Purpose: Briefly explain the functionality of the package body
--
-- MODIFICATION HISTORY
-- Person       Date                    Version Comments
-- ---------         ------                       -------       ------------------------------------------
-- NIKO         22 Feb 2007         2.3.1.1    Modified the columns of address table  in copyoffaddr function 
-- NIKO         22 Feb 2007         2.3.1.0    Peter checks into PVCS
-- GJC          14 Oct 2006           2.3          SHOW_VERSION changed from procedure to function
-- GJC          25-Jan-2006          1.2         Defect 325
-- Krishna      09-Jan-2006        1.1         Added function get_next_crs_acty_id
-- Krishna      19-Dec-2005       1.0         Initial Creation
--
   -- Enter procedure, function bodies as shown below

   v_version CONSTANT VARCHAR2(60) := '1.0    22-Febt-2007';
*/
@Service
public class OcmsuwpjPkgServiceImpl implements OcmsuwpjPkgService {

	@Autowired
	private OcmsuwpjPkgRepository ocmsuwpjRepository;

	private final Logger logger = LogManager.getLogger(OcmsuwpjPkgServiceImpl.class.getName());

	// This procedure is used to get_placement_details
	@Override
	public CourseActivities getPlacementDetails(final CourseActivities couActy) {
		final CourseActivities dataAvail = new CourseActivities();
		try {
			// This procedure is used to get_placement_det_cur
			String corporateName = null;
			if (couActy.getPlacementCorporateId() != null) {
				try {
					corporateName = ocmsuwpjRepository
							.getPlacementCur(BigDecimal.valueOf(couActy.getPlacementCorporateId()));
				} catch (Exception e) {
					logger.error("getPlacementDetails", e);
					corporateName = null;
				}
			}
			VCorporateAddresses bean = null;

			if (couActy.getProviderType().equals("EXT") && couActy.getServicesAddressId().intValue() > 0) {
				// This procedure is used to get_placement_det_cur
				bean = ocmsuwpjRepository.getPlacementAddressCur(BigDecimal.valueOf(couActy.getPlacementCorporateId()),
						BigDecimal.valueOf(couActy.getServicesAddressId()));
				dataAvail.setName(corporateName);
				
				if(bean.getProvStateDesc() == null) {
					dataAvail.setProvStateDesc(bean.getProvStateCode());
	          }else {
	        	  dataAvail.setProvStateDesc(bean.getProvStateDesc());
	          }
				
	          if(bean.getCityName() == null) {
	        	  dataAvail.setCityName(bean.getCityCode());
	          }else {
	        	  dataAvail.setCityName(bean.getCityName());
	          }
	          dataAvail.setStreetAddress(bean.getStreetAddress());
	          dataAvail.setSuiteNumber(bean.getSuiteNumber());
				dataAvail.setPostalCode(bean.getZipPostalCode());
				dataAvail.setCountry(bean.getCountry());
			}
			if (couActy.getProviderType().equals("INT") && couActy.getServicesAddressId().intValue() > 0) {
				bean = ocmsuwpjRepository.getPlacementAddressCurAylocId(couActy.getAgyLocId(),
						BigDecimal.valueOf(couActy.getServicesAddressId()));
				
				if(bean.getProvStateDesc() == null) {
					dataAvail.setProvStateDesc(bean.getProvStateCode());
	          }else {
	        	  dataAvail.setProvStateDesc(bean.getProvStateDesc());
	          }
				
	          if(bean.getCityName() == null) {
	        	  dataAvail.setCityName(bean.getCityCode());
	          }else {
	        	  dataAvail.setCityName(bean.getCityName());
	          }
	          dataAvail.setStreetAddress(bean.getStreetAddress());
	          dataAvail.setSuiteNumber(bean.getSuiteNumber());
				
				dataAvail.setPostalCode(bean.getZipPostalCode());
				dataAvail.setCountry(bean.getCountry());
			}

		} catch (Exception e) {
			logger.error("getPlacementDetails", e);
			return dataAvail;
		}
		return dataAvail;
	}

}

package net.syscon.s4.pkgs.tag_booking.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.im.beans.VLivingUnitOffenders;
import net.syscon.s4.pkgs.ocdoapop.OcdoapopPkgService;
import net.syscon.s4.pkgs.tag_booking.TagBookingRepository;
import net.syscon.s4.pkgs.tag_booking.TagBookingService;
import net.syscon.s4.pkgs.tag_establishment.TagEstablishmentService;
import net.syscon.s4.pkgs.tag_reference_codes.TagReferenceCodesService;

@Service
public class TagBookingServiceImpl implements TagBookingService {

	@Autowired
	private TagBookingRepository tagBookingRepository;
	@Autowired
	private OcdoapopPkgService ocdoapopService;
	@Autowired
	private TagReferenceCodesService tagReferenceCodesService;
	@Autowired
	private TagEstablishmentService tagEstablishmentService;

	private static Logger logger = LogManager.getLogger(TagBookingRepositoryImpl.class.getName());

	@Override
	public String getExtLocation(final VLivingUnitOffenders searchBean) {
		String lToAgyLocId = null;
		String lToCity = null;
		String lToCountryCode = null;
		BigDecimal lToAddressId = null;
		String lDesc = null;
		VAddresses lAdd = null;

		try {
		    OffenderExternalMovements obj = tagBookingRepository.getOffExtMovementsdetails(searchBean.getOffenderBookId());
		    if (obj != null) {
		    	lToAgyLocId = obj.getToAgyLocId();
				lToCity = obj.getToCity();
				lToCountryCode = obj.getToCountryCode();
				lToAddressId = obj.getToAddressId();
				if (lToAddressId != null) {
		            lAdd = ocdoapopService.fetchVAddressRecord(lToAddressId.longValue());
		            if (lAdd != null && lAdd.getFullAddress() != null) {
		                lDesc = lAdd.getFullAddress().substring(0, Math.min(lAdd.getFullAddress().length(), 40));
		            }
		            else if(lAdd != null && lAdd.getCorporateName()!= null) {
		            	lDesc = lAdd.getCorporateName();
		            }
		        }	
				else if (lToCity != null) {
				String cityDesc = tagReferenceCodesService.getDescCode("CITY", lToCity);
	            lDesc = cityDesc != null ? cityDesc.substring(0, Math.min(cityDesc.length(), 40)) : null;
	        } else if (lToCountryCode != null) {
	            String countryDesc = tagReferenceCodesService.getDescCode("COUNTRY", lToCountryCode);
	            lDesc = countryDesc != null ? countryDesc.substring(0, Math.min(countryDesc.length(), 40)) : null;
	        } else if (lToAgyLocId != null) {
	            String agyLocDesc = tagEstablishmentService.getAgyLocDesc(lToAgyLocId);
	            lDesc = agyLocDesc != null ? agyLocDesc.substring(0, Math.min(agyLocDesc.length(), 40)) : null;
	        }
		    }
		}		
		catch (Exception e) {
		    logger.error("In method getExtLocation", e);
			return lDesc;

		}
		return lDesc;
	}
}
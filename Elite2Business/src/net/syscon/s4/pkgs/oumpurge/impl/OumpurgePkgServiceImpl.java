package net.syscon.s4.pkgs.oumpurge.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.oumpurge.OumpurgePkgRepository;
import net.syscon.s4.pkgs.oumpurge.OumpurgePkgService;
import net.syscon.s4.pkgs.purge_offender.PurgeOffenderService;
import net.syscon.s4.pkgs.purge_offender_bookings.PurgeOffenderBookingsService;
import net.syscon.s4.pkgs.tag_sealer.TagSealerService;

@Service
public class OumpurgePkgServiceImpl implements OumpurgePkgService {
	@Autowired
	private OumpurgePkgRepository oumpurgeRepository;
	@Autowired
	private PurgeOffenderBookingsService purgeOffenderBookingsService;
	@Autowired
	private PurgeOffenderService purgeOffenderService;
	@Autowired
	private TagSealerService tagSealerService;

	private static Logger logger = LogManager.getLogger(OumpurgePkgServiceImpl.class.getName());
	private static final String ERROR = "ERROR";
	private static final String OFFENDER = "OFFENDER";
	private static final String BOOKING = "BOOKING"; 
	private static final String N = "N";
	@Override
	public String chkValueExists(final VHeaderBlock bean) {
		Integer lvDummy = 0;
		String lvReturn = null;
		String lvString;

		try {
			final String pWhere = bean.getStatus1();
			final String pTableName = "SOR_CONTROL_TABLES";
			lvString = "SELECT COUNT(1) FROM " + pTableName + " WHERE " + pWhere;
			lvDummy = oumpurgeRepository.getCount(lvString);
			if (lvDummy >= 1) {
				lvReturn = ERROR;
			}
		} catch (Exception e) {
			logger.error("chkValueExists", e);
			lvReturn = null;
		}
		return lvReturn;
	}

	@Override
	public String purgeOffenders(Long pRootOffenderId, Long pOffBookId, String pDelType,String modifyUserId) {
		try {
		List<Offenders> offenderId=new ArrayList<Offenders>();
			List<OffenderBookings> offenderBookId=null;
		if(pDelType!=null && pDelType.equals(OFFENDER)) { 
		 offenderId = oumpurgeRepository.getOffenderId(pRootOffenderId);
			 for(Offenders bean:offenderId) {
				 offenderBookId=oumpurgeRepository.getOffBookId(bean.getOffenderId()); 
			 }//for
			 offenderBookId.forEach(off->purgeOffenderBookingsService.prOffenderBookings(off.getOffenderBookId(),modifyUserId));
			offenderId.forEach(bean->purgeOffenderService.prOffenders(bean.getOffenderId()!=null?new BigDecimal(bean.getOffenderId()):null,modifyUserId));
		} else if (pDelType!=null && pDelType.equals(BOOKING)) {
			purgeOffenderBookingsService.prOffenderBookings(pOffBookId,modifyUserId);
		}//else if
		}
		catch (Exception e) {
			logger.error("purgeOffenders "+ e);
			return e.getMessage();
		}
		return "";
	}//method

	@Override
	public String sealingOffenders(Long offenderId, Long offenderBookId, String pSealType, String pSealFlag,String userName) throws Exception{
		String returnValue="";
		if(OFFENDER.equals(pSealType)) {
			try {
			if(N.equals(pSealFlag) || pSealFlag==null ) {				
				tagSealerService.sealOffender(offenderId!=null?new BigDecimal(offenderId):null,userName);
				returnValue ="4";
			}
			else {				
				tagSealerService.unsealOffender(offenderId!=null?new BigDecimal(offenderId):null,userName);
				returnValue ="5";
			}
			}catch (Exception e) {
				return " ORA-20998: Could not seal specified record. Error: ORA-20111:offender record has been used by another session!Try again later";
			}
		}//if
		else if(BOOKING.equals(pSealType)) {
			try {
			if(N.equals(pSealFlag) || pSealFlag==null) {				
				tagSealerService.sealOffenderBooking(offenderBookId!=null?new BigDecimal(offenderBookId):null,userName);
				returnValue ="4";
			}
			else {			
				tagSealerService.unsealOffenderBooking(offenderBookId!=null?new BigDecimal(offenderBookId):null,userName);
				returnValue ="5";
			}
			}catch(Exception e) {
				return " ORA-20998: Could not seal specified record. Error: ORA-00904: "+"SEAL_FLAG"+" invalid identifier ";
			}
		}//else if	
		return returnValue;

	}//method

}//class
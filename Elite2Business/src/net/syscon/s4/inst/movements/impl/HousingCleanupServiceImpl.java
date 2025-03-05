package net.syscon.s4.inst.movements.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globaloffenderrecords.impl.OcucoffeServiceImpl;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OffendersCommitBean;
import net.syscon.s4.im.beans.VHeaderBlock2;
import net.syscon.s4.inst.demographicsbiometrics.OidadmisService;
import net.syscon.s4.inst.movements.HousingCleanupRepository;
import net.syscon.s4.inst.movements.HousingCleanupService;
import net.syscon.s4.pkgs.common.CommonService;
@Service
public class HousingCleanupServiceImpl extends BaseBusiness implements HousingCleanupService {
	@Autowired
	HousingCleanupRepository housingCleanUpRepository;
	
	@Autowired
	private OcucoffeServiceImpl ocucoffeService;
	
	@Autowired
	private OidadmisService oidadmisService;
	
	@Autowired
	private EliteDateService eliteDateService;
	@Autowired
	private CommonService commonService; 
	
	private static Logger log = LogManager.getLogger(HousingCleanupServiceImpl.class.getName());
	@Override
	public Integer cleanUpHousingData(String userName) {
		Integer isDataCleanUp=0;
		isDataCleanUp =  insertData(userName);
		if(isDataCleanUp==1) {
			offInsertOffenders(userName);
		}
		return isDataCleanUp;
	}
	
	private Long offInsertOffenders(String userName) {
		Long liReturn = 0L;
		OffendersCommitBean commitBean = new OffendersCommitBean();
		List<Offenders> offenderInsertList =new ArrayList<>();
		offenderInsertList=getOffendersList();
		commitBean.setInsertList(offenderInsertList);
		liReturn = ocucoffeService.offInsertOffenders(commitBean);
		if(liReturn!=null) {
			List<Offenders> offenderDetailsList = getOffenderDetails(offenderInsertList);
			if(offenderDetailsList.size()>0) {
				OffenderBookings offBooking = insertExternalMovemementDetails(offenderDetailsList);
				if(offBooking!=null) {
					List<Offenders> offenderList = getOffenderDet(offenderInsertList,userName);
					Integer admitResult = admitOffenders(offenderList);
					if(admitResult!=null) {
						liReturn = 1L;
						log.info("Offerder Creation and admission Successful");
					}
				}
				
			}
		}
		return liReturn;
	}
	
	private List<Offenders> getOffendersList() {
		Offenders offenderDetails = new Offenders();
		Offenders secondOffenderDetails = new Offenders();
		Offenders thirdOffenderDetails = new Offenders();
		Offenders fourthOffenderDetails = new Offenders();
		List<Offenders> offInsert =new ArrayList<>();
			offenderDetails.setLastNameKey("HOMES");
	        offenderDetails.setOffenderIdDisplay(null);
	        offenderDetails.setIdSourceCode("SEQ");
	        offenderDetails.setRootOffenderId(null);
	        offenderDetails.setAliasNameType("WORKING");
	        offenderDetails.setAliasOffenderId(null);
	        offenderDetails.setOffenderNameSeq(new BigDecimal(1));
	        offenderDetails.setAge(new BigDecimal(40));
	        offenderDetails.setFirstName("SHERLOCK");
	        offenderDetails.setLastName("HOMES");
	        offenderDetails.setSexCode("M");
	        final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
			Date date;
			try {
				date = format.parse(String.valueOf("15-08-1983 00:00:00.0"));
				 offenderDetails.setBirthDate(date);
			}catch (Exception e) {
				log.error("offInsertOffenders", e);
			}
			secondOffenderDetails.setLastNameKey("HOMES");
	        secondOffenderDetails.setOffenderIdDisplay(null);
	        secondOffenderDetails.setIdSourceCode("SEQ");
	        secondOffenderDetails.setRootOffenderId(null);
	        secondOffenderDetails.setAliasNameType("WORKING");
	        secondOffenderDetails.setAliasOffenderId(null);
	        secondOffenderDetails.setOffenderNameSeq(new BigDecimal(1));
	        secondOffenderDetails.setAge(new BigDecimal(40));
	        secondOffenderDetails.setFirstName("MICROFT");
	        secondOffenderDetails.setLastName("HOMES");
	        secondOffenderDetails.setSexCode("M");
			Date birthdate;
			try {
				birthdate = format.parse(String.valueOf("15-08-1983 00:00:00.0"));
				 offenderDetails.setBirthDate(birthdate);
			}catch (Exception e) {
				log.error("offInsertOffenders", e);
			}
			thirdOffenderDetails.setLastNameKey("WATSON");
	        thirdOffenderDetails.setOffenderIdDisplay(null);
	        thirdOffenderDetails.setIdSourceCode("SEQ");
	        thirdOffenderDetails.setRootOffenderId(null);
	        thirdOffenderDetails.setAliasNameType("WORKING");
	        thirdOffenderDetails.setAliasOffenderId(null);
	        thirdOffenderDetails.setOffenderNameSeq(new BigDecimal(1));
	        thirdOffenderDetails.setAge(new BigDecimal(40));
	        thirdOffenderDetails.setFirstName("JOHN");
	        thirdOffenderDetails.setLastName("WATSON");
	        thirdOffenderDetails.setSexCode("M");
			Date bDate;
			try {
				bDate = format.parse(String.valueOf("15-08-1983 00:00:00.0"));
				 offenderDetails.setBirthDate(bDate);
			}catch (Exception e) {
				log.error("offInsertOffenders", e);
			}
			fourthOffenderDetails.setLastNameKey("MORIARTY");
	        fourthOffenderDetails.setOffenderIdDisplay(null);
	        fourthOffenderDetails.setIdSourceCode("SEQ");
	        fourthOffenderDetails.setRootOffenderId(null);
	        fourthOffenderDetails.setAliasNameType("WORKING");
	        fourthOffenderDetails.setAliasOffenderId(null);
	        fourthOffenderDetails.setOffenderNameSeq(new BigDecimal(1));
	        fourthOffenderDetails.setAge(new BigDecimal(40));
	        fourthOffenderDetails.setFirstName("JIM");
	        fourthOffenderDetails.setLastName("MORIARTY");
	        fourthOffenderDetails.setSexCode("M");
			Date dob;
			try {
				dob = format.parse(String.valueOf("15-08-1983 00:00:00.0"));
				 offenderDetails.setBirthDate(dob);
			}catch (Exception e) {
				log.error("offInsertOffenders", e);
			}
		offInsert.add(offenderDetails);
		offInsert.add(secondOffenderDetails);
		offInsert.add(thirdOffenderDetails);
		offInsert.add(fourthOffenderDetails);
		return offInsert;
	}
	
	private List<Offenders> getOffenderDetails(List<Offenders> offenderInsertList) {
		List<Offenders> offenderDetailsList = new ArrayList<>();
		offenderDetailsList = housingCleanUpRepository.getOffenderDetails(offenderInsertList);
		return offenderDetailsList;
	}
	
	private List<Offenders> getOffenderDet(List<Offenders> offenderInsertList,String userName) {
		List<Offenders> offenderDetailsList = new ArrayList<>();
		offenderDetailsList = housingCleanUpRepository.getOffenderDet(offenderInsertList,userName);
		return offenderDetailsList;
	}
	
	private Integer admitOffenders(List<Offenders> offenderDetailsList) {
		Integer result=null;
		for(Offenders offender : offenderDetailsList) {
			VHeaderBlock2 admissionDetail = new VHeaderBlock2();
			admissionDetail.setActiveFlag("A");
			admissionDetail.setAge(new BigDecimal(40));
			admissionDetail.setAgyLocId("STAG");
			admissionDetail.setBirthDate(offender.getBirthDate());
			admissionDetail.setBookingStatus("O");
			admissionDetail.setFirstName(offender.getFirstName());
			admissionDetail.setLastName(offender.getLastName());
			admissionDetail.setLivingUnitDescription(";:");
			admissionDetail.setOffenderBookId(new BigDecimal(offender.getOffenderBookId()));
			admissionDetail.setOffenderId(new BigDecimal(offender.getOffenderId()));
			admissionDetail.setOffenderIdDisplay(offender.getOffenderIdDisplay());
			admissionDetail.setPrisonLocation("STAG-FLOOR1-UNIT A-CELL6-BED A");
			admissionDetail.setRootOffenderId(offender.getRootOffenderId());
			admissionDetail.setStatusDisplay("ACTIVE");
			admissionDetail.setStatusReason("ADM-NEW");
			admissionDetail.setBookingNo(null);
			result = oidadmisService.offBookingUpdateOffenderExternalMovements(admissionDetail);
		}
		return result;
	}
	
	private OffenderBookings insertExternalMovemementDetails(List<Offenders> offenderDetailsList) {
		List<OffenderExternalMovements> offExtMovList = new ArrayList<>();
		OffenderBookings offBooking=new OffenderBookings();
		if(offenderDetailsList.size()>0) {
			for(Offenders offender : offenderDetailsList) {
				if(offender.getFirstName().equalsIgnoreCase("SHERLOCK") && offender.getLastName().equalsIgnoreCase("HOLMES")) {
					OffenderExternalMovements exMovDetails = new OffenderExternalMovements();
					exMovDetails.setActiveFlag("Y");
					exMovDetails.setArrestAgencyLocId("POL");
					exMovDetails.setCreateDatetime(eliteDateService.getDBTime());
					exMovDetails.setModifyDatetime(eliteDateService.getDBTime());
					exMovDetails.setMovementTime(eliteDateService.getDBTime());
					exMovDetails.setMovementDate(eliteDateService.getDBTime());
					exMovDetails.setCreateUserId("OMS_OWNER");
					exMovDetails.setDirectionCode("IN");
					exMovDetails.setEscortCode("U");
					exMovDetails.setFromAgyLocId("OUT");
					exMovDetails.setLivUnitDesc("STAG-FLOOR1-UNIT A-CELL6-BED A");
					exMovDetails.setModifyUserId("OMS_OWNER");
					exMovDetails.setMovementReasonCode("NEW");
					exMovDetails.setMovementSeq(1L);
					exMovDetails.setMovementType("ADM");
					exMovDetails.setOffenderId(new BigDecimal(offender.getOffenderId()));
					exMovDetails.setRootOffenderId(offender.getRootOffenderId());
					exMovDetails.setToAgyLocId("STAG");
				offExtMovList.add(exMovDetails);
				}
				if(offender.getFirstName().equalsIgnoreCase("MICROFT") && offender.getLastName().equalsIgnoreCase("HOLMES")) {
					OffenderExternalMovements exMovDetails = new OffenderExternalMovements();
					exMovDetails.setActiveFlag("Y");
					exMovDetails.setArrestAgencyLocId("POL");
					exMovDetails.setCreateDatetime(eliteDateService.getDBTime());
					exMovDetails.setModifyDatetime(eliteDateService.getDBTime());
					exMovDetails.setMovementTime(eliteDateService.getDBTime());
					exMovDetails.setMovementDate(eliteDateService.getDBTime());
					exMovDetails.setCreateUserId("OMS_OWNER");
					exMovDetails.setDirectionCode("IN");
					exMovDetails.setEscortCode("U");
					exMovDetails.setFromAgyLocId("OUT");
					exMovDetails.setLivUnitDesc("STAG-FLOOR1-UNIT B-CELL6-BED A");
					exMovDetails.setModifyUserId("OMS_OWNER");
					exMovDetails.setMovementReasonCode("NEW");
					exMovDetails.setMovementSeq(1L);
					exMovDetails.setMovementType("ADM");
					exMovDetails.setOffenderId(new BigDecimal(offender.getOffenderId()));
					exMovDetails.setRootOffenderId(offender.getRootOffenderId());
					exMovDetails.setToAgyLocId("STAG");
				offExtMovList.add(exMovDetails);
				}
				if(offender.getFirstName().equalsIgnoreCase("JIM") && offender.getLastName().equalsIgnoreCase("MORIARITY") ) {
					OffenderExternalMovements exMovDetails = new OffenderExternalMovements();
					exMovDetails.setActiveFlag("Y");
					exMovDetails.setArrestAgencyLocId("POL");
					exMovDetails.setCreateDatetime(eliteDateService.getDBTime());
					exMovDetails.setModifyDatetime(eliteDateService.getDBTime());
					exMovDetails.setMovementTime(eliteDateService.getDBTime());
					exMovDetails.setMovementDate(eliteDateService.getDBTime());
					exMovDetails.setCreateUserId("OMS_OWNER");
					exMovDetails.setDirectionCode("IN");
					exMovDetails.setEscortCode("U");
					exMovDetails.setFromAgyLocId("OUT");
					exMovDetails.setLivUnitDesc("STAG-FLOOR1-UNIT C-CELL6-BED A");
					exMovDetails.setModifyUserId("OMS_OWNER");
					exMovDetails.setMovementReasonCode("NEW");
					exMovDetails.setMovementSeq(1L);
					exMovDetails.setMovementType("ADM");
					exMovDetails.setOffenderId(new BigDecimal(offender.getOffenderId()));
					exMovDetails.setRootOffenderId(offender.getRootOffenderId());
					exMovDetails.setToAgyLocId("STAG");
				offExtMovList.add(exMovDetails);
				}
				
				if(offender.getFirstName().equalsIgnoreCase("JOHN") && offender.getLastName().equalsIgnoreCase("WATSON") ) {
					OffenderExternalMovements exMovDetails = new OffenderExternalMovements();
					exMovDetails.setActiveFlag("Y");
					exMovDetails.setArrestAgencyLocId("POL");
					exMovDetails.setCreateDatetime(eliteDateService.getDBTime());
					exMovDetails.setModifyDatetime(eliteDateService.getDBTime());
					exMovDetails.setMovementTime(eliteDateService.getDBTime());
					exMovDetails.setMovementDate(eliteDateService.getDBTime());
					exMovDetails.setCreateUserId("OMS_OWNER");
					exMovDetails.setDirectionCode("IN");
					exMovDetails.setEscortCode("U");
					exMovDetails.setFromAgyLocId("OUT");
					exMovDetails.setLivUnitDesc("STAG-FLOOR 2-UNIT D-CELL6-BED A");
					exMovDetails.setModifyUserId("OMS_OWNER");
					exMovDetails.setMovementReasonCode("NEW");
					exMovDetails.setMovementSeq(1L);
					exMovDetails.setMovementType("ADM");
					exMovDetails.setOffenderId(new BigDecimal(offender.getOffenderId()));
					exMovDetails.setRootOffenderId(offender.getRootOffenderId());
					exMovDetails.setToAgyLocId("STAG");
				offExtMovList.add(exMovDetails);
				}
				
			}
			OffenderExternalMovementsCommitBean offExtMovCommitBean = new OffenderExternalMovementsCommitBean();
			offExtMovCommitBean.setInsertList(offExtMovList);
			offBooking = oidadmisService.offemCommit(offExtMovCommitBean);
		}
		return offBooking;
	}

	@Override
	public Boolean checkAgyLocationExist() {
		return housingCleanUpRepository.checkAgyLocationExist();
	}

	@Override
	public Integer assignDefaultLocation(String userName) {
		return housingCleanUpRepository.assignDefaultLocation(userName);
	}

	@Override
	public Long createAndAdmitOffender(String userName) {
		return offInsertOffenders(userName);
	}
	
	private Integer insertData(String userName) {
		Integer result=commonService.createAgencyTab();
		if(result==1) {
			commonService.insertAgencyData();
		}
		return housingCleanUpRepository.insertData(userName);
		
	}

}

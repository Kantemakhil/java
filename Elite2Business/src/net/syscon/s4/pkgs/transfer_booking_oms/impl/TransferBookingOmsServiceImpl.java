package net.syscon.s4.pkgs.transfer_booking_oms.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.im.beans.AddressUsages;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.legalorders.OffenderCommunityFiles;
import net.syscon.s4.pkgs.merge_offender_oms.MergeOffenderOmsRepository;
import net.syscon.s4.pkgs.transfer_booking_core.TransferBookingCoreService;
import net.syscon.s4.pkgs.transfer_booking_oms.TransferBookingOmsRepository;
import net.syscon.s4.pkgs.transfer_booking_oms.TransferBookingOmsService;
import net.syscon.s4.triggers.AddressesT1Service;
import net.syscon.s4.triggers.AddressesT2Service;
import net.syscon.s4.triggers.AddressesT3Service;
import net.syscon.s4.triggers.AddressesT4Service;
import net.syscon.s4.triggers.AddressesTjnService;
import net.syscon.s4.triggers.AddressesTwfService;
import net.syscon.s4.triggers.InternetAddressesT1Service;
import net.syscon.s4.triggers.InternetAddressesT2Service;
import net.syscon.s4.triggers.OffIdentVineIntfTrgService;
import net.syscon.s4.triggers.OffenderBookingsBkadmTrgService;
import net.syscon.s4.triggers.OffenderBookingsT2Service;
import net.syscon.s4.triggers.OffenderBookingsT7Service;
import net.syscon.s4.triggers.OffendersBookVineIntfTrgService;
import net.syscon.s4.triggers.OffendersTjnService;
import net.syscon.s4.triggers.OffendersVineIntfTrgService;
import net.syscon.s4.triggers.OmtoffirService;
import net.syscon.s4.triggers.OmtoffsrcService;
import net.syscon.s4.triggers.OmtofsbService;
import net.syscon.s4.triggers.PhonesT1Service;
import net.syscon.s4.triggers.PhonesT2Service;

@Service
public class TransferBookingOmsServiceImpl implements TransferBookingOmsService{
	
	@Autowired
	private TransferBookingOmsRepository transferBookingOmsRepository;
	
	@Autowired
	private TransferBookingCoreService transferBookingsCoreService;
	
	@Autowired
	private OffIdentVineIntfTrgService offIdentVineIntfTrgService;
	
	@Autowired
	private MergeOffenderOmsRepository mergeOffenderOmsRepository;
	
	@Autowired
	private PhonesT1Service phonesT1Service;
	
	@Autowired
	private PhonesT2Service phonesT2Service;
	
	@Autowired
	private InternetAddressesT1Service internetAddressesT1Service;
	
	@Autowired
	private InternetAddressesT2Service internetAddressesT2Service;
	
	@Autowired
	private AddressesT1Service addressesT1Service;
	
	@Autowired
	private AddressesT2Service addressesT2Service;
	
	@Autowired
	private AddressesT3Service addressesT3Service;
	
	@Autowired
	private AddressesT4Service addressesT4Service;
	
	@Autowired
	private AddressesTjnService addressesTjnService;
	
	@Autowired
	private AddressesTwfService addressesTwfService;
	
	@Autowired
	private OmtofsbService omtofsbService;
	
	@Autowired
	private OmtoffirService omtoffirService;
	
	@Autowired
	private OffendersTjnService offendersTjnService;
	
	@Autowired
	private OmtoffsrcService omtoffsrcService;
	
	@Autowired
	private OffendersVineIntfTrgService offendersVineIntfTrgService;
	
	@Autowired
	private OffenderBookingsT2Service offenderBookingsT2Service;
	
	@Autowired
	private OffenderBookingsBkadmTrgService offenderBookingsBkadmTrgService;
	
	@Autowired
	private OffendersBookVineIntfTrgService offendersBookVineIntfTrgService;
	
	@Autowired
	private OffenderBookingsT7Service offenderBookingsT7Service;
		

	private static Logger logger = LogManager.getLogger(TransferBookingOmsServiceImpl.class.getName());

	@Override
	public void transferBookingRecords(Long pTxnId, Offenders fromOffenders, Offenders toOffenders, String user) {
		Long lvTxnId = null;
		Long lvToRootOffId = toOffenders.getRootOffenderId().longValue();
		Long lvToOffId = toOffenders.getOffenderId();
		Long lvFromRootOffId = fromOffenders.getRootOffenderId().longValue();
		Long lvFromOffBookId = fromOffenders.getOffenderBookId();
		Long lvFromOffId = fromOffenders.getOffenderId();
		String lvFromOffIdDisplay = fromOffenders.getOffenderIdDisplay();
		String lvToOffIdDisplay = toOffenders.getOffenderIdDisplay();

		//addOffIdentifier(lvToOffId, lvToRootOffId, lvFromOffIdDisplay, user);

		transferNonAssociations(lvFromOffBookId, lvFromRootOffId, lvToRootOffId, user);
		transferPhones(lvFromOffBookId, lvFromRootOffId, lvToRootOffId, user);
		transferAddresses(lvFromOffBookId, lvFromRootOffId, lvToRootOffId, user);
		transferInternetAddresses(lvFromOffBookId, lvFromRootOffId, lvToRootOffId, user);
		transferOffenderFiles(lvFromOffBookId, lvFromRootOffId, lvToRootOffId, user);
		transferBookingTable(lvFromOffBookId, lvFromOffId, lvToRootOffId, lvToOffIdDisplay, lvToOffId, user);
		transferOffIdentifiers(lvFromOffBookId, lvFromOffId, lvToRootOffId, lvToOffId, user);

		if (pTxnId != null) {
			lvTxnId = pTxnId;
		}

		transferOmsRecords(lvTxnId,user);

	}
	
	public void addOffIdentifier(Long pOffenderIdTo, Long pRootOffenderIdTo, String pOffenderIdDisp, String user) {
		String lvIdentType = null;
		Integer lvOffIdSeq = 0;
		String lvCaseloadType = null;
	
		lvIdentType = transferBookingOmsRepository.getOffIdenTypeCur();
		if(lvIdentType != null) {
			lvOffIdSeq = transferBookingOmsRepository.getNewSeqCur(pOffenderIdTo);
			if(lvOffIdSeq == null) {
				lvOffIdSeq = 1;
			}else {
				lvOffIdSeq = lvOffIdSeq+1;
			}
			lvCaseloadType = transferBookingOmsRepository.getCaseloadTypeCur(user);
			
			List<OffenderIdentifier> oldOffIdentList = mergeOffenderOmsRepository.getOldOffenderIdentifierDet(pRootOffenderIdTo);
		
			List<OffenderIdentifier> insertList=new ArrayList<OffenderIdentifier>();
			OffenderIdentifier object=new OffenderIdentifier();
			object.setOffenderId(pOffenderIdTo);
			object.setOffenderIdSeq(String.valueOf(lvOffIdSeq));
			object.setIdentifierType(lvIdentType);
			object.setIdentifier(pOffenderIdDisp);
			object.setRootOffenderId(BigDecimal.valueOf(pRootOffenderIdTo));
			object.setCaseloadType(lvCaseloadType);
			object.setCreateUserId(user);
			insertList.add(object);
			
			try{
			transferBookingOmsRepository.insertOffIdentRecord(insertList);
			//Trigger calling
			offIdentVineIntfTrgService.offIdentVineIntfTrg(insertList, oldOffIdentList, "INSERT");
			}catch(Exception e) {
			transferBookingOmsRepository.deleteOffenderIdentifier(lvIdentType, pRootOffenderIdTo,user);
			//Trigger calling
			offIdentVineIntfTrgService.offIdentVineIntfTrg(insertList, oldOffIdentList, "DELETE");
			
			transferBookingOmsRepository.insertOffIdentRecord(insertList);
			//Trigger calling
			offIdentVineIntfTrgService.offIdentVineIntfTrg(insertList, oldOffIdentList, "INSERT");
			}
		}
	}


	public void transferNonAssociations(Long pFromOffenderBookId, Long pFromOffenderId, Long pToOffenderId, String user) {

		Long lvLatestBookingId;

		List<OffenderNonAssociations> txnferNonAssoc1 = transferBookingOmsRepository.txnferNonAssoc1(pFromOffenderBookId);
		List<OffenderNonAssociations> txnferNonAssoc2 = transferBookingOmsRepository.txnferNonAssoc2(pFromOffenderBookId);
		transferBookingOmsRepository.txnferNaDetails1(pFromOffenderBookId);
		transferBookingOmsRepository.txnferNaDetails2(pFromOffenderBookId);
		lvLatestBookingId = transferBookingOmsRepository.getLatestBooking(pToOffenderId, pFromOffenderBookId);

		Long offenderId = null;
		Long nsOffenderId = null;

		for (OffenderNonAssociations eachNonAssoc : txnferNonAssoc1) {
			offenderId = eachNonAssoc.getOffenderId();
			nsOffenderId = eachNonAssoc.getNsOffenderId();

			List<OffenderNonAssociations> offNonAssData = transferBookingOmsRepository.getOffNonAssData(offenderId);
			for (OffenderNonAssociations offenderNonAssociations : offNonAssData) {
				BigDecimal lvLatestBkngId = new BigDecimal(lvLatestBookingId);
				offenderNonAssociations.setOffenderId(pToOffenderId);
				offenderNonAssociations.setOffenderBookId(lvLatestBkngId);
			}

			List<OffenderNaDetails> offNaData = transferBookingOmsRepository.getOffNaData(offenderId);
			for (OffenderNaDetails OffenderNaDetails : offNaData) {
				BigDecimal lvLatestBkngId = new BigDecimal(lvLatestBookingId);
				OffenderNaDetails.setOffenderId(pToOffenderId);
				OffenderNaDetails.setOffenderBookId(lvLatestBkngId);
			}

			Integer updateOffNonAsso = transferBookingOmsRepository.updateOffenderNonAssociations(pToOffenderId,offenderId, nsOffenderId, user);
			Integer updateOffNaDet = transferBookingOmsRepository.updateOffenderNaDetails(pToOffenderId, offenderId,nsOffenderId, user);
			if (updateOffNonAsso == 0 || updateOffNaDet == 0) {
				Integer deleteOffenderNaDetails = transferBookingOmsRepository.deleteOffenderNaDetails(offenderId,nsOffenderId,user);
				Integer deleteOffenderNonAssociations = transferBookingOmsRepository.deleteOffenderNonAssociations(offenderId, nsOffenderId,user);
				Integer insertOffNonAssData = transferBookingOmsRepository.insertOffNonAssData(offNonAssData);
				Integer insertOffNaData = transferBookingOmsRepository.insertOffNaData(offNaData);
			}
		}

		for (OffenderNonAssociations eachNonAssoc : txnferNonAssoc2) {
			offenderId = eachNonAssoc.getOffenderId();
			nsOffenderId = eachNonAssoc.getNsOffenderId();

			List<OffenderNonAssociations> offNonAssData = transferBookingOmsRepository.getOffNonAssData(nsOffenderId);
			for (OffenderNonAssociations offenderNonAssociations : offNonAssData) {
				BigDecimal lvLatestBkngId = new BigDecimal(lvLatestBookingId);
				offenderNonAssociations.setNsOffenderId(pToOffenderId);
				offenderNonAssociations.setOffenderBookId(lvLatestBkngId);
			}

			List<OffenderNaDetails> offNaData = transferBookingOmsRepository.getOffNaData(nsOffenderId);
			for (OffenderNaDetails OffenderNaDetails : offNaData) {
				BigDecimal lvLatestBkngId = new BigDecimal(lvLatestBookingId);
				OffenderNaDetails.setNsOffenderId(pToOffenderId);
				OffenderNaDetails.setOffenderBookId(lvLatestBkngId);
			}

			Integer updateOffNonAsso2 = transferBookingOmsRepository.updateOffenderNonAssociations2(pToOffenderId,offenderId, nsOffenderId, user);
			Integer updateOffNaDet2 = transferBookingOmsRepository.updateOffenderNaDetails2(pToOffenderId, offenderId,nsOffenderId, user);
			if (updateOffNonAsso2 == 0 || updateOffNaDet2 == 0) {
				Integer deleteOffenderNaDetails2 = transferBookingOmsRepository.deleteOffenderNaDetails2(offenderId, nsOffenderId,user);
				Integer deleteOffenderNonAssociations2 = transferBookingOmsRepository.deleteOffenderNonAssociations2(offenderId, nsOffenderId,user);
				Integer insertOffNonAssData = transferBookingOmsRepository.insertOffNonAssData(offNonAssData);
				Integer insertOffNaData = transferBookingOmsRepository.insertOffNaData(offNaData);
			}
		}
	}

	public void transferPhones(Long pFromOffenderBookId, Long pFromOffenderId, Long pToOffenderId, String user) {
		Date lvStartDate;
		Date lvEndDate;
		Long phoneId;
		Integer lvCount;
		lvStartDate = transferBookingsCoreService.getBookingStartDate(pFromOffenderBookId);
		lvEndDate = transferBookingsCoreService.getBookingEndDate(pFromOffenderBookId);
		lockPhoneRec("OFF", pFromOffenderId);
		
		List<Phones> transPhones = transferBookingOmsRepository.transPhones(lvStartDate, lvEndDate, pFromOffenderId);
		for (Phones eachPhone : transPhones) {
			 phoneId = eachPhone.getPhoneId();
			 
			 Phones oldPhoneData = transferBookingOmsRepository.getPhoneData(phoneId);
			 String ownerClass = oldPhoneData.getOwnerClass();
			 Long ownerId = oldPhoneData.getOwnerId().longValue();
			 Long ownerSeq = oldPhoneData.getOwnerSeq().longValue();
			 String ownerCode = oldPhoneData.getOwnerCode();
			 
			 lvCount = transferBookingOmsRepository.phoneNumExists(phoneId, pFromOffenderId, pToOffenderId);
			 if(lvCount > 0) {
				 transferBookingOmsRepository.deletePhones(phoneId,user);
				 Phones newPhoneData = transferBookingOmsRepository.getPhoneData(phoneId); 
				 // Trigger Calling
				 phonesT2Service.phonesT2Trigger(oldPhoneData, newPhoneData);// old new 
			 }else {
				 // Trigger phonesT1Service Calling
				 try {
					phonesT1Service.phonesT1Trigger(ownerClass, ownerId, ownerSeq, ownerCode);
				} catch (CustomException e) {
					logger.error("Exception occured in " + this.getClass().getName() + " in method phonesT1Trigger", e);
				}
				 transferBookingOmsRepository.updatePhones(pToOffenderId, phoneId, user);
				 Phones newPhoneData1 = transferBookingOmsRepository.getPhoneData(phoneId); 
				 // Trigger phonesT2Service Calling
				 phonesT2Service.phonesT2Trigger(oldPhoneData, newPhoneData1);// old new  
			 }
		}	
	}
	
	public void lockPhoneRec(String pOwnerClass, Long pOwnerId) {
		List<Phones> list = transferBookingOmsRepository.lockPhone(pOwnerClass, pOwnerId);
		for (Phones locRec : list) {
			if(locRec == null) {	
			}
		}
	}
	
	
	public void transferAddresses(Long pFromOffenderBookId ,Long pFromOffenderId, Long pToOffenderId, String user){
		Date lvStartDate;
		Date lvEndDate;
		Integer lvCount;
		Long addressId;
		
		lvStartDate = transferBookingsCoreService.getBookingStartDate(pFromOffenderBookId);
		lvEndDate = transferBookingsCoreService.getBookingEndDate(pFromOffenderBookId);
		lockPhoneRec("OFF", pFromOffenderId);
		
		List<Addresses> transAddresses = transferBookingOmsRepository.transAddresses(lvStartDate, lvEndDate, pFromOffenderId);
		
		for (Addresses eachAddress : transAddresses) {
			addressId = eachAddress.getAddressId();
			
			lvCount = transferBookingOmsRepository.addressExists(addressId, pFromOffenderId, pToOffenderId);
			
			if(lvCount > 0) {
				lockPhoneRec("ADDR", addressId);
				lockInternetAddrRec("ADDR", addressId);
				lockAddressUsageRec(addressId);
				
				Phones oldPhoneData = transferBookingOmsRepository.getPhnDataByAddId(addressId);
				
				transferBookingOmsRepository.deletePhonesByAddId(addressId,user);
				Phones newPhoneData1 = transferBookingOmsRepository.getPhnDataByAddId(addressId); 
				 // Trigger phonesT2Service Calling
				 phonesT2Service.phonesT2Trigger(oldPhoneData, newPhoneData1);// old new 
				
				 InternetAddresses oldIntAddrData = transferBookingOmsRepository.getIntAddrDataByAddrId(addressId);
				 String ownerClass = oldIntAddrData.getOwnerClass();
				 Long ownerId = oldIntAddrData.getOwnerId().longValue();
				 Long ownerSeq = oldIntAddrData.getOwnerSeq().longValue();
				 String ownerCode = oldIntAddrData.getOwnerCode();
				 
				transferBookingOmsRepository.deleteInternetAddresses(addressId,user);
				try {
					// Trigger internetAddressesT1Trigger Calling
					internetAddressesT1Service.internetAddressesT1Trigger(ownerClass, ownerId, ownerSeq, ownerCode);
				} catch (CustomException e) {
					logger.error("Exception occured in " + this.getClass().getName() + " in method internetAddressesT1Service", e);
				}
				
				transferBookingOmsRepository.deleteAddressUsages(addressId,user);
				
				
				Addresses oldAddrData = transferBookingOmsRepository.getAddressesByAddrId(addressId);
				String ownerClassA = oldAddrData.getOwnerClass();
				Long ownerIdA = oldAddrData.getOwnerId().longValue();
				BigDecimal ownerSeqA = oldAddrData.getOwnerSeq();
				String ownerCodeA = oldAddrData.getOwnerCode();
				
				transferBookingOmsRepository.deleteAddresses(addressId,user);
				Addresses newAddrData = transferBookingOmsRepository.getAddressesByAddrId(addressId);
					//Trigger addressesTjn calling
				//addressesTjnService.addressesTjn(newAddrData, oldAddrData, "DELETE");
				try {
					//Trigger addresesT2Trigger calling
					addressesT2Service.addresesT2Trigger(addressId);
				} catch (CustomException e1) {
					logger.error("Exception occured in " + this.getClass().getName() + " in method transferAddresses addresesT2Trigger", e1);
				}
					//Trigger AddresesT3Trigger calling
				addressesT3Service.AddresesT3Trigger(oldAddrData, newAddrData);
				
				
				try {
					//Trigger addressesT1Service calling
					addressesT1Service.addresesT1Trigger(ownerClassA, ownerIdA, ownerSeqA, ownerCodeA);//String ownerClass,Long ownerId,BigDecimal ownerSeq,String ownerCode
				} catch (CustomException e) {
					logger.error("Exception occured in " + this.getClass().getName() + " in method transferAddresses addresesT1Trigger", e);
				}
				transferBookingOmsRepository.updateAddresses(pToOffenderId, addressId, user);
				Addresses newAddrData1 = transferBookingOmsRepository.getAddressesByAddrId(addressId);
				//Triggers calling
				addressesTwfService.addressesTwf(newAddrData1);
				//addressesTjnService.addressesTjn(newAddrData1, oldAddrData, "UPDATE");
				addressesT4Service.AddresesT4Trigger(newAddrData1);
				addressesT3Service.AddresesT3Trigger(oldAddrData, eachAddress);
			}
		}
	}
	
	
	public void lockInternetAddrRec(String pOwnerClass, Long pOwnerId) {
		
		List<Phones> list = transferBookingOmsRepository.lockPhone(pOwnerClass, pOwnerId);
		for (Phones locRec : list) {
			if(locRec == null) {
				
			}
		}
	}
	
	public void lockAddressUsageRec(Long addressId) {
		List<AddressUsages> list = transferBookingOmsRepository.lockAddressUsage(addressId);
		for (AddressUsages locRec : list) {
			if(locRec == null) {
				
			}
		}
	}
	
	public void transferInternetAddresses(Long pFromOffenderBookId ,Long pFromOffenderId, Long pToOffenderId, String user) {
		
		Date lvStartDate;
		Date lvEndDate;
		Integer lvCount;
		Long internetAddressId = null;
		lvStartDate = transferBookingsCoreService.getBookingStartDate(pFromOffenderBookId);
		lvEndDate = transferBookingsCoreService.getBookingEndDate(pFromOffenderBookId);
		lockInternetAddrRec("OFF", internetAddressId);
		
		List<InternetAddresses> transIntAddr = transferBookingOmsRepository.transIntAddr(lvStartDate, lvEndDate, pFromOffenderId);
		
		for (InternetAddresses eachAddress : transIntAddr) {
			internetAddressId =  eachAddress.getInternetAddressId();
			
			lvCount = transferBookingOmsRepository.internetAddressExists(internetAddressId, pFromOffenderId, pToOffenderId);
			InternetAddresses oldIntAddrData = transferBookingOmsRepository.getIntAddrByIntAddrDet(internetAddressId);
			String ownerClass = oldIntAddrData.getOwnerClass();
			 Long ownerId = oldIntAddrData.getOwnerId().longValue();
			 Long ownerSeq = oldIntAddrData.getOwnerSeq().longValue();
			 String ownerCode = oldIntAddrData.getOwnerCode();
			 
			if(lvCount > 0) {
				transferBookingOmsRepository.deleteIntAddresses(internetAddressId,user);
				try {
					// Trigger internetAddressesT1Trigger Calling
					internetAddressesT1Service.internetAddressesT1Trigger(ownerClass, ownerId, ownerSeq, ownerCode);
				} catch (CustomException e) {
					logger.error("Exception occured in " + this.getClass().getName() + " in method transferInternetAddresses in internetAddressesT1Service", e);
				}
				
			}else {
				// Trigger internetAddressesT2Trigger Calling
				internetAddressesT2Service.internetAddressesT2Trigger(oldIntAddrData);
				transferBookingOmsRepository.updateIntAddresses(pToOffenderId, internetAddressId, user);
				try {
					// Trigger internetAddressesT1Trigger Calling
					internetAddressesT1Service.internetAddressesT1Trigger(ownerClass, ownerId, ownerSeq, ownerCode);
				} catch (CustomException e) {
					logger.error("Exception occured in " + this.getClass().getName() + " in method transferInternetAddresses in internetAddressesT1Service", e);
				}
			}
		}
	}
	
	
	public void transferOffenderFiles(Long pFromOffenderBookId, Long pFromRootOffId, Long pToRootOffId, String user) {

		Integer lvNextOfs;
		Long lvNextOfn;
		Date lvStartDate;     
		Date lvEndDate;         
		String lvDml;         
	    Integer offenderFileSeq; 
	    Long offenderFileNum;
		
		lvDml = "SELECT * FROM offender_file_deliveries WHERE  offender_id = ".concat(String.valueOf(pFromRootOffId));		
		transferBookingsCoreService.runMergeSql(lvDml);
		
		lvDml = "SELECT * FROM offender_file_transactions WHERE  offender_id = ".concat(String.valueOf(pFromRootOffId));
		transferBookingsCoreService.runMergeSql(lvDml);
		
		lvDml = "SELECT * FROM offender_community_files WHERE  offender_id = ".concat(String.valueOf(pFromRootOffId));
		transferBookingsCoreService.runMergeSql(lvDml);
	
		lvStartDate = transferBookingsCoreService.getBookingStartDate(pFromOffenderBookId);
		lvEndDate = transferBookingsCoreService.getBookingEndDate(pFromOffenderBookId);
		
		OffenderCommunityFiles offCommFileVals = transferBookingOmsRepository.getOffCommFileVals(pToRootOffId);
		
		lvNextOfs = offCommFileVals.getOffenderFileSeq();
		lvNextOfn = offCommFileVals.getOffenderFileNum();
		offenderFileSeq = offCommFileVals.getOffenderFileSeq();
		offenderFileNum = offCommFileVals.getOffenderFileNum();
		offenderFileSeq = offenderFileSeq + lvNextOfs;
		offenderFileNum = offenderFileNum + lvNextOfn;
		
		 transferBookingOmsRepository.updateOffFileDelv(pToRootOffId, offenderFileSeq, pFromRootOffId, lvStartDate, lvEndDate, user);
		 transferBookingOmsRepository.updateOffFileTrans(pToRootOffId, offenderFileSeq, pFromRootOffId, lvStartDate, lvEndDate, user);
		
		List<OffenderCommunityFiles> offCommFilelist = transferBookingOmsRepository.getOffCommFileObj(pToRootOffId);
		
		//Trigger deleteOffenderFileTrigger calling
		omtofsbService.deleteOffenderFileTrigger(user);
		 transferBookingOmsRepository.updateOffCommFiles(pToRootOffId, offenderFileSeq, offenderFileNum, pFromRootOffId, lvStartDate, lvEndDate, user);
		//Trigger omtoffirTgr calling
		omtoffirService.omtoffirTgr(offCommFilelist);
		
	}
	
	
	public void transferBookingTable(Long pFromOffBookId, Long pFromOffId, Long pToRootOffId, String pToOffIdDisplay, Long pToOffId, String user) {
		     
		Long lvCount;
		Long lvOffenderId = null;
		String lvDml;
		 
		lvDml = "SELECT * FROM   offender_bookings WHERE  offender_book_id = ".concat(String.valueOf(pFromOffBookId).concat(" FOR UPDATE NOWAIT "));
		transferBookingsCoreService.runMergeSql(lvDml);

		Offenders offCount = transferBookingOmsRepository.getOffCount(pToRootOffId, pFromOffId);
		lvCount = Long.parseLong(offCount.getLastNameKey());
		if(offCount.getFirstNameKey() != null) {
		lvOffenderId =Long.parseLong(offCount.getFirstNameKey());
		}
		
		OffenderBookings oldoffBooksData = transferBookingOmsRepository.getOffBooksData(pFromOffBookId);
		
		if(lvCount == 0) {
			lvOffenderId = transferBookingOmsRepository.getNxtVal();
			
			List<Offenders> offendersData = transferBookingOmsRepository.getOffendersData(pFromOffId);
			BigDecimal pToRootOffeId = new BigDecimal(pToRootOffId);
			for (Offenders fromOffData : offendersData) {
				fromOffData.setOffenderId(lvOffenderId);
				fromOffData.setRootOffenderId(pToRootOffeId);
				fromOffData.setOffenderIdDisplay(pToOffIdDisplay);
				fromOffData.setAliasOffenderId(pToRootOffId);	
			}
			
			//Trigger omtoffsrc calling
			omtoffsrcService.omtoffsrc(offendersData, "INSERT");
			transferBookingOmsRepository.insertTrOffVals(offendersData);
			List<Offenders> newOffenderdata = transferBookingOmsRepository.getOffenderdata(pFromOffId);
			//Trigger offendersTjn calling
			//offendersTjnService.offendersTjn(newOffenderdata, offendersData, "INSERT");
			//Trigger offendersVineIntfTrg calling
			offendersVineIntfTrgService.offendersVineIntfTrg(newOffenderdata, "INSERT");
		}
		OffenderBookings newOffBookingData = transferBookingOmsRepository.getOffBooksData(pFromOffBookId);
		//Trigger offenderBookingsT2 calling
		offenderBookingsT2Service.offenderBookingsT2(newOffBookingData, oldoffBooksData);
		transferBookingOmsRepository.updateOffenderBookings(lvOffenderId, pToRootOffId, pFromOffBookId, user);
		OffenderBookings newOffBooksData = transferBookingOmsRepository.getOffBooksData(pFromOffBookId);
		newOffBooksData.setModifyUserId(user);
		//Trigger offenderBookingsBkadmTrg calling
		offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(oldoffBooksData, newOffBooksData, "UPDATING");
		//Trigger OffendersBookVineIntfTrgTrigger calling
		offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(newOffBooksData,ApplicationConstants.UPDATING);
		//Trigger offenderBookingsT7Trigger calling
		offenderBookingsT7Service.offenderBookingsT7Trigger(newOffBooksData);
		
	}
	
	
	public void transferOffIdentifiers(Long pFromOffBookId, Long pFromOffId, Long pToRootOffId, Long pToOffId, String user) {
		
		Integer lvOffIdSeq;
		Date lvStartDate;     
		Date lvEndDate;
		Long lvToOffId = pToOffId;
		String lvDml;
		Integer lvDelRecords = 0;
		Integer lvOffenderIdSeqP = null;
		Integer lCount1;
	
		lvDml = "SELECT * FROM offender_identifiers WHERE  offender_id = ".concat(String.valueOf(pFromOffId));
		transferBookingsCoreService.runMergeSql(lvDml);
		lvStartDate = transferBookingsCoreService.getBookingStartDate(pFromOffBookId);
		lvEndDate = transferBookingsCoreService.getBookingEndDate(pFromOffBookId);
		
		lvOffIdSeq = transferBookingOmsRepository.getNewSeqCurs(pToOffId);
		
		List<OffenderIdentifier> oldOffIdentList = mergeOffenderOmsRepository.getOldOffenderIdentifierDet(pToRootOffId);
		
		if(lvDelRecords ==  0) {
			lvDelRecords = transferBookingOmsRepository.deleteOffIdentf(pFromOffId, pToRootOffId, lvStartDate, lvEndDate);
			List<OffenderIdentifier> newOffIdentList = mergeOffenderOmsRepository.getOldOffenderIdentifierDet(pToRootOffId);
			//Trigger offIdentVineIntfTrg calling
			offIdentVineIntfTrgService.offIdentVineIntfTrg(newOffIdentList, oldOffIdentList, "DELETE");
		}
		if(lvDelRecords > 0) {
			lCount1 = 0;
		 List<OffenderIdentifier> offIdSeqCur = transferBookingOmsRepository.getOffIdSeqCur(pFromOffId);
		 
		 List<OffenderIdentifier> oldOffIdentList1 = mergeOffenderOmsRepository.getOldOffenderIdentifierDet(pFromOffId);
		 
		 for (OffenderIdentifier offenderIdentifier : offIdSeqCur) {
			 lCount1 = lCount1 +1;
			 lvOffenderIdSeqP = Integer.parseInt(offenderIdentifier.getOffenderIdSeq());
			 if(lvOffenderIdSeqP != null) {
				 
				 transferBookingOmsRepository.updateOffIdentfiers(lCount1, pFromOffId, lvOffenderIdSeqP, user);
				 
				 List<OffenderIdentifier> newOffIdentList1 = mergeOffenderOmsRepository.getOldOffenderIdentifierDet(pFromOffId);
				 //Trigger offIdentVineIntfTrg Calling
				 offIdentVineIntfTrgService.offIdentVineIntfTrg(newOffIdentList1, oldOffIdentList1, "UPDATE");
			 }
		 	}
		 	lvOffenderIdSeqP = lvOffenderIdSeqP + lvOffIdSeq;
		 	
		 		transferBookingOmsRepository.updateFinalOffIdentfiers(lvToOffId, pToRootOffId, pFromOffId, lvOffenderIdSeqP, lvStartDate, lvEndDate, user);
		 		List<OffenderIdentifier> newOffIdentList2 = mergeOffenderOmsRepository.getOldOffenderIdentifierDet(pFromOffId);
		 		 //Trigger offIdentVineIntfTrg Calling
				 offIdentVineIntfTrgService.offIdentVineIntfTrg(newOffIdentList2, oldOffIdentList1, "UPDATE");
		}
	}
	
	
	public void transferOmsRecords(Long pTxnId, String user) {
		try {
		transferBookingsCoreService.transferApplnRecords(pTxnId, "OMS",user);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method transferOmsRecords", e);
		}
	}
	
	
}

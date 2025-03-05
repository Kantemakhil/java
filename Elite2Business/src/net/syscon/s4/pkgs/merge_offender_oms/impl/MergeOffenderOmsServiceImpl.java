package net.syscon.s4.pkgs.merge_offender_oms.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.globalconfiguration.OumsypflRepository;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.legalorders.OffenderCommunityFiles;
import net.syscon.s4.legalorders.OffenderFileTransactions;
import net.syscon.s4.pkgs.merge_context.MergeContextService;
import net.syscon.s4.pkgs.merge_log.MergeLogService;
import net.syscon.s4.pkgs.merge_offender_core.MergeOffenderCoreService;
import net.syscon.s4.pkgs.merge_offender_oms.MergeOffenderOmsRepository;
import net.syscon.s4.pkgs.merge_offender_oms.MergeOffenderOmsService;
import net.syscon.s4.pkgs.merge_offender_trust.MergeOffenderTrustRepository;
import net.syscon.s4.pkgs.merge_process.impl.MergeProcessRepositoryImpl;
import net.syscon.s4.triggers.AddressesT1Service;
import net.syscon.s4.triggers.AddressesT3Service;
import net.syscon.s4.triggers.AddressesT4Service;
import net.syscon.s4.triggers.AddressesTjnService;
import net.syscon.s4.triggers.AddressesTwfService;
import net.syscon.s4.triggers.OffIdentVineIntfTrgService;
import net.syscon.s4.triggers.OffcomfiService;
import net.syscon.s4.triggers.OffendersTjnService;
import net.syscon.s4.triggers.OffendersVineIntfTrgService;
import net.syscon.s4.triggers.OmtoffsrcService;

@Service
public class MergeOffenderOmsServiceImpl implements MergeOffenderOmsService {
	
	@Autowired
	private MergeContextService mergeContextService;
	
	@Autowired
	private MergeOffenderOmsRepository mergeOffenderOmsRepository;
	
	@Autowired
	private MergeOffenderCoreService mergeOffenderCoreService;

	@Autowired
	private MergeOffenderTrustRepository mergeOffenderTrustRepository;
	
	@Autowired
	private OffcomfiService offcomfiService;
	
	@Autowired
	private OffIdentVineIntfTrgService offIdentVineIntfTrgService;
	
	@Autowired
	private AddressesT1Service addressesT1Service;
	
	@Autowired
	private AddressesT3Service addressesT3Service;
	
	@Autowired
	private AddressesT4Service addressesT4Service;
	
	@Autowired
	private AddressesTjnService addressesTjnService;
	
	@Autowired
	private AddressesTwfService addressesTwfService;
	
	@Autowired
	private OffendersVineIntfTrgService offendersVineIntfTrgService;
	
	@Autowired
	private OmtoffsrcService omtoffsrcService;
	
	@Autowired
	private OffendersTjnService offendersTjnService;
	
	@Autowired
	private MergeLogService mergeLogService;
	
	@Autowired
	private OumsypflRepository oumsypflrepository;
	
	private static Logger logger = LogManager.getLogger(MergeProcessRepositoryImpl.class.getName());

	@Override
	public void mergeOffenderRecords(Offenders fromOffenders, Offenders toOffenders, String user, Long mergeTransactionId) {
		 
		String cFunc = "merge_offender_oms";
		Long lvRootOffIdTo = toOffenders.getRootOffenderId().longValue();
		Long lvOffBookIdTo = toOffenders.getOffenderBookId();
		Long lvOffIdTo = toOffenders.getOffenderId();
		Long lvRootOffIdFrom = fromOffenders.getRootOffenderId().longValue();
		Long lvOffBookIdFrom = fromOffenders.getOffenderBookId();
		Long lvOffIdFrom = fromOffenders.getOffenderId();
		String lvOffIdDisplayFrom = fromOffenders.getOffenderIdDisplay();

		mergeLogService.info("Merging OMS records.", mergeTransactionId, user);
		mergeLogService.traceIn(cFunc, mergeTransactionId, user);
	
		lockCustomRuleRecords(lvRootOffIdFrom);
		mergeNonAssociations(lvRootOffIdFrom, lvRootOffIdTo, mergeTransactionId, user);
		mergeOffPaperFiles(lvRootOffIdFrom, lvRootOffIdTo, mergeTransactionId, user);
		mergeOffIdentifiers(lvOffIdTo, lvRootOffIdTo, lvOffIdDisplayFrom, user, mergeTransactionId, lvOffIdFrom, lvRootOffIdFrom);
		mergeOffAddPriFlag(lvRootOffIdFrom, lvRootOffIdTo, mergeTransactionId, user);
		mergeOmsRecords(lvRootOffIdFrom, lvRootOffIdTo, mergeTransactionId, user);
		mergeOffendersTable(lvRootOffIdFrom, lvRootOffIdTo, mergeTransactionId, user);
	}

	public void lockCustomRuleRecords(Long lvRootOffIdFrom) {
		List<OffenderNonAssociations> lockOffNonAssociations = mergeOffenderOmsRepository.lockOffNonAssociations(lvRootOffIdFrom);
		List<OffenderNaDetails> lockOffenderNaDetails = mergeOffenderOmsRepository.lockOffenderNaDetails(lvRootOffIdFrom);
		List<OffenderNaDetails> lockOffNaDetailsNs = mergeOffenderOmsRepository.lockOffNaDetailsNs(lvRootOffIdFrom);
		List<OffenderNonAssociations> lockOffNonAssociationsNs = mergeOffenderOmsRepository.lockOffNonAssociationsNs(lvRootOffIdFrom);
		List<OffenderFileTransactions> lockOffFileTransactions = mergeOffenderOmsRepository.lockOffFileTransactions(lvRootOffIdFrom);
		List<OffenderNonAssociations> lockOffFileDeliveries = mergeOffenderOmsRepository.lockOffFileDeliveries(lvRootOffIdFrom);
		List<Offenders> lockOffenders = mergeOffenderOmsRepository.lockOffenders(lvRootOffIdFrom);
		
		for (OffenderNonAssociations offenders : lockOffNonAssociations) {
			
		}
		
		for (OffenderNaDetails offenders : lockOffenderNaDetails) {
			
		}
		
		for (OffenderNaDetails offenders : lockOffNaDetailsNs) {
			
		}
		
		for (OffenderNonAssociations offenders : lockOffNonAssociationsNs) {
			
		}
		
		for (OffenderFileTransactions offenders : lockOffFileTransactions) {
			
		}
		
		for (OffenderNonAssociations offenders : lockOffFileDeliveries) {
			
		}
		
		for (Offenders offenders : lockOffenders) {
			
		}
		
	}
	
	
	public void mergeNonAssociations(Long lvRootOffIdFrom, Long lvRootOffIdTo, Long mergeTransactionId, String user) {
		
		Long lvLatestBookingId;
		String cFunc = "merge_non_associations";
		List<OffenderNonAssociations> mergeNonAssoc1 = mergeOffenderOmsRepository.mergeNonAssoc1(lvRootOffIdFrom);
		List<OffenderNonAssociations> mergeNonAssoc2 = mergeOffenderOmsRepository.mergeNonAssoc2(lvRootOffIdFrom);
		mergeLogService.traceIn(cFunc, mergeTransactionId, user);
		lvLatestBookingId = mergeOffenderOmsRepository.getLatestBooking(lvRootOffIdFrom, lvRootOffIdTo);

		Long offenderId = null;
		Long nsOffenderId = null;
		for (OffenderNonAssociations eachNonAssoc : mergeNonAssoc1) {
			offenderId = eachNonAssoc.getOffenderId();
			nsOffenderId = eachNonAssoc.getNsOffenderId();
			
			List<OffenderNonAssociations> offNonAssData = mergeOffenderOmsRepository.getOffNonAssData(offenderId);
			for (OffenderNonAssociations offenderNonAssociations : offNonAssData) {
				BigDecimal lvLatestBkngId = new BigDecimal(lvLatestBookingId);
				offenderNonAssociations.setOffenderId(lvRootOffIdTo);
				offenderNonAssociations.setOffenderBookId(lvLatestBkngId);	
			}
			
			List<OffenderNaDetails> offNaData = mergeOffenderOmsRepository.getOffNaData(offenderId);
			for (OffenderNaDetails OffenderNaDetails : offNaData) {
				BigDecimal lvLatestBkngId = new BigDecimal(lvLatestBookingId);
				OffenderNaDetails.setOffenderId(lvRootOffIdTo);
				OffenderNaDetails.setOffenderBookId(lvLatestBkngId);
			}
				Integer updateOffNonAsso = mergeOffenderOmsRepository.updateOffenderNonAssociations(lvRootOffIdTo, offenderId, nsOffenderId, user);
				Integer updateOffNaDetails = mergeOffenderOmsRepository.updateOffenderNaDetails(lvRootOffIdTo, offenderId, nsOffenderId, user);
				if(updateOffNonAsso == 0 || updateOffNaDetails == 0) {
					Integer deleteOffNaDetails = mergeOffenderOmsRepository.deleteOffenderNaDetails(offenderId, nsOffenderId,user);
					Integer deleteOffNonAsso = mergeOffenderOmsRepository.deleteOffenderNonAssociations(offenderId, nsOffenderId,user);
					Integer insertOffNonAssData = mergeOffenderOmsRepository.insertOffNonAssData(offNonAssData);
					Integer insertOffNaData = mergeOffenderOmsRepository.insertOffNaData(offNaData);
					
				}
		}
		
		for (OffenderNonAssociations eachNonAssoc2 : mergeNonAssoc2) {
			offenderId = eachNonAssoc2.getOffenderId();
			nsOffenderId = eachNonAssoc2.getNsOffenderId();
			
			List<OffenderNonAssociations> offNonAssData = mergeOffenderOmsRepository.getOffNonAssData(nsOffenderId);
			for (OffenderNonAssociations offenderNonAssociations : offNonAssData) {
				BigDecimal lvLatestBkngId = new BigDecimal(lvLatestBookingId);
				offenderNonAssociations.setNsOffenderId(lvRootOffIdTo);
				offenderNonAssociations.setOffenderBookId(lvLatestBkngId);	
			}
			
			List<OffenderNaDetails> offNaData = mergeOffenderOmsRepository.getOffNaData(nsOffenderId);
			for (OffenderNaDetails OffenderNaDetails : offNaData) {
				BigDecimal lvLatestBkngId = new BigDecimal(lvLatestBookingId);
				OffenderNaDetails.setNsOffenderId(lvRootOffIdTo);
				OffenderNaDetails.setOffenderBookId(lvLatestBkngId);
			}
				Integer updateOffNonAsso2 = mergeOffenderOmsRepository.updateOffenderNonAssociations2(lvRootOffIdTo, offenderId, nsOffenderId, user);
				Integer updateOffNaDetails2 = mergeOffenderOmsRepository.updateOffenderNaDetails2(lvRootOffIdTo, offenderId, nsOffenderId, user);
				if(updateOffNonAsso2 == 0 || updateOffNaDetails2 == 0) {
					Integer deleteOffNaDetails2 = mergeOffenderOmsRepository.deleteOffenderNaDetails2(offenderId, nsOffenderId,user);
					Integer deleteOffNonAsso2 = mergeOffenderOmsRepository.deleteOffenderNonAssociations2(offenderId, nsOffenderId,user);
					Integer insertOffNonAssData = mergeOffenderOmsRepository.insertOffNonAssData(offNonAssData);
					Integer insertOffNaData = mergeOffenderOmsRepository.insertOffNaData(offNaData);
				
				}
		}		
		
	}
	
	
	public void mergeOffPaperFiles(Long lvRootOffIdFrom, Long lvRootOffIdTo, Long mergeTransactionId, String user) {	
		
		String cFunc = "merge_off_paper_files";
		mergeLogService.traceIn(cFunc, mergeTransactionId, user);
		BigDecimal nextFileOfs = mergeOffenderOmsRepository.getNextFileSeqCurser(lvRootOffIdTo);
		List<OffenderCommunityFiles> oldOffFilesCurserData=new ArrayList();
		List<OffenderCommunityFiles> insertList=new ArrayList();
		oldOffFilesCurserData = mergeOffenderOmsRepository.getOldOffFilesCurserData(lvRootOffIdFrom);
		if(!oldOffFilesCurserData.isEmpty()) {		
			for (OffenderCommunityFiles offenderCommunityFile : oldOffFilesCurserData) {
				BigDecimal lvNxtOfn=BigDecimal.ZERO;
				BigDecimal lvNxtVsq=BigDecimal.ZERO;
				BigDecimal getFileNumCu=BigDecimal.ZERO;
				getFileNumCu = mergeOffenderOmsRepository.getNextFileNo(lvRootOffIdTo,offenderCommunityFile.getFileType(),
						offenderCommunityFile.getFileSubType());
				if(getFileNumCu == null || getFileNumCu.compareTo(BigDecimal.ZERO) == 0) {					
					BigDecimal lvNextFileNumCur = mergeOffenderOmsRepository.getNextFileNoCurser(lvRootOffIdTo);
					lvNxtOfn= lvNextFileNumCur;
				} else {
					lvNxtOfn= getFileNumCu;
				}			
				lvNxtVsq = 	mergeOffenderOmsRepository.getNextVolumeSeqCurser(lvNxtOfn,lvRootOffIdTo);
				offenderCommunityFile.setOffenderFileSeq(nextFileOfs.intValue());    //(nextFileOfs.longValue());
				offenderCommunityFile.setOffenderId(lvRootOffIdTo);
				offenderCommunityFile.setOffenderFileNum(lvNxtOfn.longValue()); 
				offenderCommunityFile.setVolumeSeq(lvNxtVsq.intValue()); 
				insertList=new ArrayList();
				insertList.add(offenderCommunityFile);
				
				mergeOffenderOmsRepository.insertOffenderCommunityFile(insertList);	
				//Trigger offcomfiTrigger calling
				offcomfiService.offcomfiTrigger(offenderCommunityFile);
				
				Integer getOffenderFileSeq = offenderCommunityFile.getOffenderFileSeq();
				mergeOffenderOmsRepository.updateOffenderFileTransaction(lvRootOffIdTo,nextFileOfs.longValue(),lvRootOffIdFrom, getOffenderFileSeq.longValue(), user);
				mergeOffenderOmsRepository.updateOffenderFileDeliveries(lvRootOffIdTo,nextFileOfs.longValue(),lvRootOffIdFrom,offenderCommunityFile.getOffenderFileSeq(), user);
				offenderCommunityFile.setModifyUserId(user);
				mergeOffenderOmsRepository.deleteOffenderCommunityFiles(offenderCommunityFile);
				nextFileOfs = nextFileOfs.add(BigDecimal.ONE);
			}
		}
	}
	
	public void mergeOffIdentifiers (Long offenderIdTo,Long lvRootOffIdTo,String offenderIdDisplayFrom, String user, Long mergeTransactionId, Long offenderIdFrom, Long lvRootOffIdFrom ) {
		String cFunc = "merge_off_identifiers";
		mergeLogService.traceIn(cFunc, mergeTransactionId, user);
		String lvIdentType= mergeOffenderOmsRepository.getOffIdenTypeCur();
		List<OffenderIdentifier> identiFiList = new ArrayList<>();
		if(lvIdentType!=null) {
			Long lvOffIdSeq=mergeOffenderOmsRepository.getlvOffIdSeq(offenderIdTo);
			String caseloadType = mergeOffenderOmsRepository.getCaseloadType(user);
			List<OffenderIdentifier> oldUpdateList = mergeOffenderOmsRepository.getOldOffenderIdentifierDet(offenderIdTo);
			
			List<OffenderIdentifier> insertList=new ArrayList<OffenderIdentifier>();
			OffenderIdentifier object=new OffenderIdentifier();
			object.setOffenderId(offenderIdTo);
			object.setOffenderIdSeq(String.valueOf(lvOffIdSeq));
			object.setIdentifierType(lvIdentType);
			object.setIdentifier(offenderIdDisplayFrom);
			object.setRootOffenderId(BigDecimal.valueOf(lvRootOffIdTo));
			object.setCaseloadType(caseloadType);
			object.setCreateUserId(user);
			insertList.add(object);	
			SystemProfiles sysProf = new SystemProfiles();
			sysProf.setProfileCode("PIN");
			sysProf.setProfileType("CLIENT");
			List<SystemProfiles> systemProfile = oumsypflrepository.getSystemProfileRecords(sysProf);
			if (systemProfile!=null && systemProfile.size()>0 && "Y".equalsIgnoreCase(systemProfile.get(0).getProfileValue())) {
				identiFiList = mergeOffenderOmsRepository.getOldOffenderIdentifierDet(offenderIdFrom);
				if (identiFiList != null && identiFiList.size() > 0 && !identiFiList.isEmpty()) {
					for (OffenderIdentifier bean : identiFiList) {
						if ("PIN".equalsIgnoreCase(bean.getIdentifierType())) {												
							mergeOffenderOmsRepository.updateFromOffenderIdentifiers("MPIN", user, bean.getOffenderId(), bean.getOffenderIdSeq());//S4-23596
						}
					}
				}
				if (oldUpdateList != null && oldUpdateList.size() > 0 && !oldUpdateList.isEmpty()) {
					Long fromOffIdSeq = mergeOffenderOmsRepository.getlvOffIdSeq(offenderIdFrom);
					for (OffenderIdentifier bean : oldUpdateList) {
						if ("PIN".equalsIgnoreCase(bean.getIdentifierType())) {
							OffenderIdentifier objOne = new OffenderIdentifier();
							objOne.setIdentifierType("PIN");
							objOne.setIdentifier(bean.getIdentifier());
							lvOffIdSeq = lvOffIdSeq + 1;
							objOne.setOffenderIdSeq(String.valueOf(fromOffIdSeq));
							objOne.setOffenderId(offenderIdFrom);
							objOne.setRootOffenderId(new BigDecimal(lvRootOffIdFrom));
							objOne.setCreateUserId(user);
							objOne.setIssuedAuthorityText(bean.getIssuedAuthorityText());
							objOne.setVerifiedFlag(bean.getVerifiedFlag());
							insertList.add(objOne);
						}
					}
				}
			}
			try {
				Integer insertOffIdentifiers = mergeOffenderOmsRepository.insertOffenderIdentifiers(insertList);
			} catch (Exception e) {
				if (e.getMessage().contains("offender_identifiers_uk1")) {
					if (insertList != null && insertList.size() > 0 && identiFiList != null && identiFiList.size() >0) {
						for (OffenderIdentifier obj : identiFiList) {
							Integer deleteOffIdentifier = mergeOffenderOmsRepository
									.deleteOffenderIdentifier(obj.getIdentifierType(), lvRootOffIdTo,user);
							// offIdentVineIntfTrg trigger calling
							offIdentVineIntfTrgService.offIdentVineIntfTrg(insertList, identiFiList, "DELETE");
						}
						Integer insertOffIdntifiers = mergeOffenderOmsRepository.insertOffenderIdentifiers(insertList);
						// offIdentVineIntfTrg trigger calling
						offIdentVineIntfTrgService.offIdentVineIntfTrg(insertList, oldUpdateList, "INSERT");
					}
				}
			}
		}
	}
	
	public void mergeOffAddPriFlag(Long lvRootOffIdFrom, Long lvRootOffIdTo, Long mergeTransactionId, String user) {
		
		String ownerClass = null;
		BigDecimal ownerSeq = null;
		String ownerCode = null;
		String cFunc = "merge_off_add_pri_flag";
		mergeLogService.traceIn(cFunc, mergeTransactionId, user);
		List<Addresses> fromAddressData = mergeOffenderOmsRepository.getAddressesData(lvRootOffIdFrom);
		for (Addresses addresses : fromAddressData) {	
			System.out.println("addresses"+addresses);
		}
		List<Addresses> toAddressData = mergeOffenderOmsRepository.getAddressesData(lvRootOffIdTo);
		for (Addresses addresses : toAddressData) {
			System.out.println("addresses"+addresses);
			ownerClass = addresses.getOwnerClass();
			ownerSeq = addresses.getOwnerSeq();
			ownerCode = addresses.getOwnerCode();
		}
		
		Integer fromCountOffStatus=mergeOffenderOmsRepository.checkOffStatusCur(lvRootOffIdFrom);
		Integer toCountOffStatus=mergeOffenderOmsRepository.checkOffStatusCur(lvRootOffIdTo);
		
		Integer fromCountOffPreFlagCur=mergeOffenderOmsRepository.checkOffPreFlagCur(lvRootOffIdFrom);
		Integer toCountOffPreFlagCur=mergeOffenderOmsRepository.checkOffPreFlagCur(lvRootOffIdTo);
		
		if(fromCountOffStatus > 0 && fromCountOffPreFlagCur > 0) {
			if(toCountOffPreFlagCur > 0) {
				
	
				Addresses oldAdressesData = mergeOffenderOmsRepository.getAddrData(lvRootOffIdTo);
				
				// Trigger addresesT1Trigger calling
				try {
					addressesT1Service.addresesT1Trigger(ownerClass, lvRootOffIdTo, ownerSeq, ownerCode);
					
				} catch (CustomException e) {
					logger.error("Exception occured in " + this.getClass().getName() + " in Trigger addresesT1Trigger", e);
				}
				
				mergeOffenderOmsRepository.updateAdreessData(lvRootOffIdTo, user);
				
				Addresses newAddrData = mergeOffenderOmsRepository.getAddrData(lvRootOffIdTo);
				
				//Triggers calling
				if(oldAdressesData != null && newAddrData != null) {
				addressesT3Service.AddresesT3Trigger(oldAdressesData, newAddrData); 
				addressesT4Service.AddresesT4Trigger(newAddrData );
				//addressesTjnService.addressesTjn(newAddrData, oldAdressesData, "UPDATE"); 
				addressesTwfService.addressesTwf(newAddrData); 
				
				}
				
			}
		} else if(toCountOffStatus > 0 && toCountOffPreFlagCur > 0) {
			if(fromCountOffPreFlagCur > 0) {
				
				// Trigger addresesT1Trigger calling
				Addresses oldAdressesData1 = mergeOffenderOmsRepository.getAddrData(lvRootOffIdFrom);
				try {
					addressesT1Service.addresesT1Trigger(ownerClass, lvRootOffIdTo, ownerSeq, ownerCode); 
					
				} catch (CustomException e) {
					logger.error("Exception occured in " + this.getClass().getName() + " in Trigger addresesT1Trigger", e);
				}
				
				mergeOffenderOmsRepository.updateAdreessData(lvRootOffIdFrom, user);
				
				Addresses newAddrData1 = mergeOffenderOmsRepository.getAddrData(lvRootOffIdFrom);
				//Triggers calling
				if(oldAdressesData1 != null && newAddrData1 != null) {
				addressesT3Service.AddresesT3Trigger(oldAdressesData1, newAddrData1); 
				addressesT4Service.AddresesT4Trigger(newAddrData1 );
				//addressesTjnService.addressesTjn(newAddrData1, oldAdressesData1, "UPDATE"); 
				addressesTwfService.addressesTwf(newAddrData1);
				}
			}
		}
		
		if(fromCountOffStatus == 0 && toCountOffStatus == 0 && fromCountOffPreFlagCur > 0 && toCountOffPreFlagCur > 0) {
			
			Addresses oldAdressesData2 = mergeOffenderOmsRepository.getAddrData(lvRootOffIdFrom);

			// Trigger addresesT1Trigger calling
			try {
				addressesT1Service.addresesT1Trigger(ownerClass, lvRootOffIdTo, ownerSeq, ownerCode);
				
			} catch (CustomException e) {
				logger.error("Exception occured in " + this.getClass().getName() + " in Trigger addresesT1Trigger", e);
			}
			
			mergeOffenderOmsRepository.updateAdreessData(lvRootOffIdFrom, user);
			
			Addresses newAddrData2 = mergeOffenderOmsRepository.getAddrData(lvRootOffIdFrom);
			
			if(oldAdressesData2 != null && newAddrData2 != null) {
			//Triggers calling
			addressesT3Service.AddresesT3Trigger(oldAdressesData2, newAddrData2);
			addressesT4Service.AddresesT4Trigger(newAddrData2 );
			//addressesTjnService.addressesTjn(newAddrData2, oldAdressesData2, "UPDATE"); 
			addressesTwfService.addressesTwf(newAddrData2);
			}
		}
		
	}
	
	public void mergeOmsRecords(Long lvRootOffIdFrom, Long lvRootOffIdTo, Long mergeTransactionId, String user) {
		
		String cFunc = "merge_oms_records";
		mergeLogService.traceIn(cFunc, mergeTransactionId, user);
		mergeOffenderCoreService.mergeApplnRecords("OMS", lvRootOffIdFrom, lvRootOffIdTo);
		mergeOffenderCoreService.mergeOffenderGeneric("ADDRESSES", "OFF", lvRootOffIdFrom, lvRootOffIdTo, 0l);
		mergeOffenderCoreService.mergeOffenderGeneric("INTERNET_ADDRESSES", "OFF", lvRootOffIdFrom, lvRootOffIdTo, 0l);
		mergeOffenderCoreService.mergeOffenderGeneric("PHONES", "OFF", lvRootOffIdFrom, lvRootOffIdTo, 0l);

	}
	
	
	
	
	public void mergeOffendersTable(Long lvRootOffIdFrom, Long lvRootOffIdTo, Long mergeTransactionId, String user) {
		
		String cFunc = "merge_offenders_table";
		mergeLogService.traceIn(cFunc, mergeTransactionId, user);
		String offIdDisplayCur = mergeOffenderOmsRepository.getOffIdDisplayCur(lvRootOffIdTo);
		
		List<Offenders> oldOffendersData = mergeOffenderOmsRepository.getOffenders(lvRootOffIdTo);
		
		//Trigger omtoffsrc call
		omtoffsrcService.omtoffsrc(oldOffendersData, "UPDATE");
		
		Integer updateOffs = mergeOffenderOmsRepository.updateOffenders(lvRootOffIdTo, offIdDisplayCur, lvRootOffIdFrom, user);
		
		List<Offenders> offendersData = mergeOffenderOmsRepository.getOffenders(lvRootOffIdTo);
		//Triggers call
		offendersVineIntfTrgService.offendersVineIntfTrg(offendersData, "UPDATE");
		//offendersTjnService.offendersTjn(offendersData, oldOffendersData, "UPDATE");
	}
	
}

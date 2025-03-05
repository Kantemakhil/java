package net.syscon.s4.pkgs.transfer_booking_oms;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.InternetAddresses;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.im.beans.AddressUsages;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.legalorders.OffenderCommunityFiles;


public interface TransferBookingOmsRepository {

	public String getOffIdenTypeCur();
	
	public Integer getNewSeqCur(Long pOffenderIdTo);
	
	public String getCaseloadTypeCur(String user);
	
	public Integer insertOffIdentRecord(List<OffenderIdentifier> insertList);
	
	public Integer deleteOffenderIdentifier(String lvIdentType, Long pRootOffenderIdTo,String modifyUserId);
	
	public List<OffenderNonAssociations> txnferNonAssoc1(Long pFromOffenderBookId);
	
	public List<OffenderNonAssociations> txnferNonAssoc2(Long pFromOffenderBookId);
	
	public List<OffenderNaDetails> txnferNaDetails1(Long pFromOffenderBookId);
	
	public List<OffenderNaDetails> txnferNaDetails2(Long pFromOffenderBookId);
	
	public Long getLatestBooking(Long pToOffenderId, Long pFromOffenderBookId);
	
	
	public Integer updateOffenderNonAssociations(Long pToOffenderId, Long offenderId, Long nsOffenderId, String user);
	
	public Integer updateOffenderNaDetails(Long pToOffenderId, Long offenderId, Long nsOffenderId, String user);
	
	public Integer deleteOffenderNaDetails(Long offenderId, Long nsOffenderId,String modifyUserId);

	public Integer deleteOffenderNonAssociations(Long offenderId, Long nsOffenderId,String modifyUserId);
	
	public Integer updateOffenderNaDetails1(Long lvLatestBookingId, Long pToOffenderId, Long nsOffenderId);
	
	public Integer updateOffenderNonAssociations1(Long lvLatestBookingId, Long pToOffenderId, Long nsOffenderId);
	

	public Integer updateOffenderNonAssociations2(Long pToOffenderId, Long offenderId, Long nsOffenderId, String user);
	
	public Integer updateOffenderNaDetails2(Long pToOffenderId, Long offenderId, Long nsOffenderId, String user);
	
	public Integer deleteOffenderNaDetails2(Long offenderId, Long nsOffenderId,String modifyUserId);

	public Integer deleteOffenderNonAssociations2(Long offenderId, Long nsOffenderId,String modifyUserId);
	
	public Integer updateOffenderNaDetails21(Long lvLatestBookingId , Long offenderId, Long pToOffenderId);

	public Integer updateOffenderNonAssociations21(Long lvLatestBookingId,  Long offenderId, Long pToOffenderId);
	
	
	public Integer phoneNumExists(Long pPhoneId, Long pFromOffenderId, Long pToOffenderId); 
	
	public List<Phones> transPhones(Date pStartDate, Date pEndDate, Long pFromOffenderId); 
	
	public List<Phones> lockPhone(String pOwnerClass, Long pOwnerId);
	
	public Integer deletePhones(Long pPhoneId,String modifyUserId);
	
	public Integer updatePhones(Long pOwnerId, Long pPhoneId, String user);
	
	public Phones getPhoneData(Long pPhoneId);
	
	
	public Integer addressExists(Long pAddressId, Long pFromOffenderId, Long pToOffenderId);
	
	public List<Addresses> transAddresses(Date pStartDate, Date pEndDate, Long pFromOffenderId);
	
	public List<InternetAddresses> lockInternetAddr(String pOwnerClass, Long pOwnerId);
	
	public List<AddressUsages> lockAddressUsage(Long pAddressId);
	
	public Integer deletePhonesByAddId(Long pAddressId,String modifyUserId);
	
	public Phones getPhnDataByAddId(Long addressId);
	
	public Integer deleteInternetAddresses(Long pAddressId,String modifyUserId);

	public InternetAddresses getIntAddrDataByAddrId(Long addressId);
	
	public Integer deleteAddressUsages(Long pAddressId,String modifyUserId);
	
	public Integer deleteAddresses(Long pAddressId,String modifyUserId);
	
	public Addresses getAddressesByAddrId(Long pAddressId);
	
	public Integer updateAddresses(Long pToOffenderId, Long pAddressId, String user);
	
	
	
	public Integer internetAddressExists(Long pIntAddressId, Long pFromOffenderId, Long pToOffenderId);
	
	public List<InternetAddresses> transIntAddr(Date pStartDate, Date pEndDate, Long pFromOffenderId);
	
	public Integer deleteIntAddresses(Long pInternetAddressId,String modifyUserId);
	
	public InternetAddresses getIntAddrByIntAddrDet(Long pInternetAddressId);
	
	public Integer updateIntAddresses(Long pToOffenderId, Long pInternetAddressId, String user);
	
	
	public OffenderCommunityFiles getOffCommFileVals(Long pToRootOffenderId);
	
	public Integer updateOffFileDelv(Long pToRootOffenderId, Integer offenderFileSeq,Long pFromRootOffenderId, Date lvStartDate, Date lvEndDate, String user );
	
	public Integer updateOffFileTrans(Long pToRootOffenderId, Integer offenderFileSeq,Long pFromRootOffenderId, Date lvStartDate, Date lvEndDate, String user);
	
	public Integer updateOffCommFiles(Long pToRootOffenderId, Integer offenderFileSeq, Long offenderFileNum, Long pFromRootOffenderId, Date lvStartDate, Date lvEndDate, String user );

	public List<OffenderCommunityFiles> getOffCommFileObj(Long pToRootOffenderId);
	
	
	public Offenders getOffCount( Long pToRootOffId, Long pFromOffId);
	
	public Long getNxtVal();
	
	public Integer insertTrOffVals(List<Offenders> insertList);
	
	public List<Offenders> getOffenderdata(Long offenderId);
	
	public Integer updateOffenderBookings(Long lvOffenderId, Long pToRootOffId, Long pFromOffBookId, String user);
	
	public OffenderBookings getOffBooksData(Long pFromOffBookId);
	
	
	
	
	public Integer getNewSeqCurs(Long pToOffId);
	
	public List<OffenderIdentifier> getOffIdSeqCur(Long pFromOffId);
	
	public Integer deleteOffIdentf(Long pFromOffId, Long pToRootOffId, Date lvStartDate, Date lvEndDate);
	
	public Integer updateOffIdentfiers(Integer lCount1,Long pFromOffId, Integer lvOffenderIdSeqP, String user);
	
	public Integer updateFinalOffIdentfiers(Long lvToOffenderId, Long pToRootOffenderId, Long pFromOffenderId, Integer lvOffenderIdSeqP, Date lvStartDate, Date lvEndDate, String user);
	
	
	public List<OffenderNonAssociations> getOffNonAssData(Long nsOffenderId);
	public List<OffenderNaDetails> getOffNaData(Long nsOffenderId);
	public Integer insertOffNonAssData(List<OffenderNonAssociations> insertList);
	public Integer insertOffNaData(List<OffenderNaDetails> insertList);
	
	public List<Offenders> getOffendersData(Long offenderId);
	
}

package net.syscon.s4.pkgs.merge_offender_oms;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.legalorders.OffenderCommunityFiles;
import net.syscon.s4.legalorders.OffenderFileTransactions;



public interface MergeOffenderOmsRepository {
	
	public List<OffenderNonAssociations> lockOffNonAssociations(Long offenderId);
	
	public List<OffenderNaDetails> lockOffenderNaDetails(Long offenderId);
	
	public List<OffenderNaDetails> lockOffNaDetailsNs(Long rootOffenderId);
	
	public List<OffenderNonAssociations> lockOffNonAssociationsNs(Long rootOffenderId);
	
	public List<OffenderFileTransactions> lockOffFileTransactions(Long offenderId);
	
	public List<OffenderNonAssociations> lockOffFileDeliveries(Long rootOffenderId);
	
	public List<Offenders> lockOffenders(Long rootOffenderId);
	
	
	public List<OffenderNonAssociations> mergeNonAssoc1(Long rootOffenderIdFrom);
	
	public List<OffenderNonAssociations> mergeNonAssoc2(Long rootOffenderIdFrom);

	public Long getLatestBooking(Long FromRootOffenderId, Long toRootOffenderId); //(---- CHECK ----)
	
	
	public Integer updateOffenderNonAssociations(Long pRootOffenderIdTo, Long offenderId, Long nsOffenderId, String user);
	
	public Integer updateOffenderNaDetails(Long pRootOffenderIdTo, Long offenderId, Long nsOffenderId, String user);
	
	public Integer deleteOffenderNaDetails(Long offenderId, Long nsOffenderId,String modifyUserId);

	public Integer deleteOffenderNonAssociations(Long offenderId, Long nsOffenderId,String modifyUserId);
	
	public Integer updateOffenderNonAssociations1(Long lvLatestBookingId, Long pRootOffenderIdTo, Long nsOffenderId);
	
	public Integer updateOffenderNaDetails1(Long lvLatestBookingId, Long pRootOffenderIdTo, Long nsOffenderId);

	
	public Integer updateOffenderNonAssociations2(Long pRootOffenderIdTo, Long offenderId, Long nsOffenderId, String user);
	
	public Integer updateOffenderNaDetails2(Long pRootOffenderIdTo, Long offenderId, Long nsOffenderId, String user);
	
	public Integer deleteOffenderNaDetails2(Long offenderId, Long nsOffenderId,String modifyUserId);

	public Integer deleteOffenderNonAssociations2(Long offenderId, Long nsOffenderId,String modifyUserId);
	
	public Integer updateOffenderNaDetails21(Long lvLatestBookingId , Long offenderId, Long pRootOffenderIdTo);

	public Integer updateOffenderNonAssociations21(Long lvLatestBookingId,  Long offenderId, Long pRootOffenderIdTo);

	
	public BigDecimal getNextFileSeqCurser(Long lvRootOffIdTo);

	public List<OffenderCommunityFiles> getOldOffFilesCurserData(Long lvRootOffIdFrom);

	public BigDecimal getNextFileNo(Long lvRootOffIdTo, String fileType, String fileSubType);

	public BigDecimal getNextFileNoCurser(Long lvRootOffIdTo);

	public BigDecimal getNextVolumeSeqCurser(BigDecimal lvNxtOfn, Long lvRootOffIdTo);

	public Integer insertOffenderCommunityFile(List<OffenderCommunityFiles> insertList);

	public Integer updateOffenderFileTransaction(Long lvRootOffIdTo, Long longValue, Long lvRootOffIdFrom,
			Long offenderFileSeq, String user);

	public Integer updateOffenderFileDeliveries(Long lvRootOffIdTo, long longValue, Long lvRootOffIdFrom,
			long offenderFileSeq, String user);

	public Integer deleteOffenderCommunityFiles(OffenderCommunityFiles offenderCommunityFile);

	public String getOffIdenTypeCur();

	public Long getlvOffIdSeq(Long offenderIdTo);

	public String getCaseloadType(String user);

	public Integer insertOffenderIdentifiers(List<OffenderIdentifier> insertList);

	public Integer deleteOffenderIdentifier(String lvIdentType, Long lvRootOffIdTo,String modifyUserId);

	public List<Addresses> getAddressesData(Long lvRootOffIdFrom);

	public Integer checkOffStatusCur(Long lvRootOffIdFrom);

	public Integer checkOffPreFlagCur(Long lvRootOffIdFrom);

	public Integer updateAdreessData(Long lvRootOffIdTo, String user);

	
	public String getOffIdDisplayCur(Long lvRootOffIdTo);
	
	public Integer updateOffenders(Long lvRootOffIdTo, String lvOffIdDisplay, Long lvRootOffIdFrom, String user);
	
	
	public List<OffenderIdentifier> getOldOffenderIdentifierDet(Long offenderIdTo);
	
	public Addresses getAddrData(Long offenderIdTo);
	
	public List<Offenders> getOffenders(Long offenderIdTo);
	
	
	public List<OffenderNonAssociations> getOffNonAssData(Long nsOffenderId);
	public List<OffenderNaDetails> getOffNaData(Long nsOffenderId);
	public Integer insertOffNonAssData(List<OffenderNonAssociations> insertList);
	public Integer insertOffNaData(List<OffenderNaDetails> insertList);

	public Integer updateFromOffenderIdentifiers(String identifierType, String modifyUserId, Long offenderId,String offenderIdSeq);
}

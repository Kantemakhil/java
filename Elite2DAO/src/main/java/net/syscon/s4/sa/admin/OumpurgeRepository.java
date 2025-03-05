package net.syscon.s4.sa.admin;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.OffenderTrustAccounts;

/**
 * Interface OumpurgeRepository
 */
public interface OumpurgeRepository {

	List<OffenderBookings> offBkgExecuteQuery(OffenderBookings objOffenderBookings);

	List<OffenderBookings> offOnCheckDeleteMaster(OffenderBookings paramBean);

	List<VHeaderBlock> offExecuteQuery(VHeaderBlock objVHeaderBlock,String count);

	Integer offBkgUpdateOffenderBookings(List<OffenderBookings> lstOffenderBookings);

	Integer offUpdateVHeaderBlock(List<VHeaderBlock> lstVHeaderBlock);

	String BookingsOne(String bookingStatus);

	Integer getLvCountSealBookings(BigDecimal rootOffenderId);
	
	List<OffenderTrustAccounts> showCaseloadAcct(VHeaderBlock object);
	
	Integer checkActiveBooking(VHeaderBlock objVHeaderBlock);
	
	Integer processRecord(VHeaderBlock objVHeaderBlock);

	String getProfileValue();

	String chkValueExists(VHeaderBlock bean);

	Integer getBeneficiaryAcc(BigDecimal paramBean);

	String getCaseloadSubStr(String lvCaseloadStr);

	String defWhereCondition();

	Integer getTrustAccountCode(VHeaderBlock ele);

	String getSealedFlag(VHeaderBlock ele);

	Map<String, Object> getPurgeOffenders(BigDecimal offenderId, String statusReason, BigDecimal offenderBookId);

	String getSubString(String data, String data2);

	Map<String, Object> getSealOffenders(BigDecimal rootOffenderId, String statusReason, BigDecimal object,
			String sealFlag);
	
	public OffenderBookings getOldData(Long offenderBookId);
	
	List<SystemProfiles> sysPflSearchSystemProfiles(final SystemProfiles objSearchDao);

	Integer getAccessStaffCount(Integer staffIdValue, String userName);

	Integer getLvCountSealBookings(String rootOffenderId);
}

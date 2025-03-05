package net.syscon.s4.globaloffenderrecords;

import java.util.Date;
import java.util.List;
import java.util.Set;

import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.inmate.beans.OffExternalAccountBalances;

public interface OiiobalxRepository {
	List<OffExternalAccountBalances> getOffExternalAccountBalances(OffExternalAccountBalances searchBean);
	
	OffExternalAccountBalances getOffenderAccountDetails(long offenderIdDisplay,String accountType);
	
	Integer updateOffenderExternalBalance(OffExternalAccountBalances accBalObj);
	
	Integer InsertOffenderExternalBalance(OffExternalAccountBalances accBalObj);

	List<VHeaderBlock> getRootOffenderId(Set<String> offenderIdDisplay);
	

	Date getLastUpdatedDate();
}

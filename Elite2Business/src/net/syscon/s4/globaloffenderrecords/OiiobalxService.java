package net.syscon.s4.globaloffenderrecords;

import java.util.Date;
import java.util.List;

import net.syscon.s4.inmate.beans.OffExternalAccountBalances;

public interface OiiobalxService {
	List<OffExternalAccountBalances> getOffExternalAccountBalances(OffExternalAccountBalances searchBean);
	
	String saveOffExternalAccountBalances(String data,String userId);
	
  Date getLastUpdatedDate();
	
}

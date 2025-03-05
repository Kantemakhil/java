package net.syscon.s4.sa.recordmaintenance;

import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;

public interface SendmailRepository {
	List<SystemProfiles> getClicksendConfDet();
	
	String getMailType();
}

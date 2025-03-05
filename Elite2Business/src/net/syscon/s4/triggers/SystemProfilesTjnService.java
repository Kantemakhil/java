package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.SystemProfiles;

public interface SystemProfilesTjnService {
	void systemProfilesTjn(final SystemProfiles old,final SystemProfiles newprofile,final String operation);
}

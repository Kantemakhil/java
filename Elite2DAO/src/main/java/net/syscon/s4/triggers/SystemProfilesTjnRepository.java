package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.SystemProfiles;

public interface SystemProfilesTjnRepository {
	Integer insert(final SystemProfiles newprofile);
	Integer update(final SystemProfiles newprofile);
	Integer delete(final SystemProfiles old);

}

package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;

public interface SystemPofilesTjnRepository {

	

	Integer insertingSystemProfile(List<SystemProfiles> lstSystemProfiles);

	Integer updatingSystemProfile(List<SystemProfiles> lstSystemProfiles);

	Integer deletingSystemProfile(List<SystemProfiles> lstSystemProfiles);
}

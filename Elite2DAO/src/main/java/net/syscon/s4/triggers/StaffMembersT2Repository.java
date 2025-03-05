package net.syscon.s4.triggers;

public interface StaffMembersT2Repository {
	Integer getProfileValueFromSystemProfiles();

	Integer getStaffIdProfileValueMod(final Long staffId, final Long profileVal);
}

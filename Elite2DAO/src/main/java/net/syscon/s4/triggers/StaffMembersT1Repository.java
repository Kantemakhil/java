package net.syscon.s4.triggers;

public interface StaffMembersT1Repository {
	Integer getCountAddresses(final Integer staffId);

	Integer getCountPhones(Integer staffId);

	Integer getCountInternetAddresses(Integer staffId);
}

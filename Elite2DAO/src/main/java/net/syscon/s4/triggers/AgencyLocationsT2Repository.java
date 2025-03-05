package net.syscon.s4.triggers;

public interface AgencyLocationsT2Repository {
	Integer addressRecords(String agyLocId);

	Integer phoneRecords(String agyLocId);

	Integer internetAddresses(String agyLocId);
}

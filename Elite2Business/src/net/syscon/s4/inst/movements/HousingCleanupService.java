package net.syscon.s4.inst.movements;

public interface HousingCleanupService {
	Integer cleanUpHousingData(String userName);

	Boolean checkAgyLocationExist();

	Integer assignDefaultLocation(String userName);

	Long createAndAdmitOffender(String userName);
}

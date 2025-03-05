package net.syscon.s4.triggers;

public interface WorkRatesTjnRepository {
	Integer insertOrUpdateOrDelete(WorkRates workRates);
	WorkRates getWorkRates(WorkRates workRates);
}

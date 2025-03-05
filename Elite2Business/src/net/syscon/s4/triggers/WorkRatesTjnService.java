package net.syscon.s4.triggers;

import java.util.List;

public interface WorkRatesTjnService {
	Integer workRatesTjnTgr(List<WorkRates> workRatesList, String sqlOperation);
}

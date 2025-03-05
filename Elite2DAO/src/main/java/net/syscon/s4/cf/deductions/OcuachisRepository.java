package net.syscon.s4.cf.deductions;

import java.util.List;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;

public interface OcuachisRepository {

	List<FeeAccountProfiles> accountHistoryQuery(FeeAccountProfiles searchObject);
	String getDescription(FeeAccountProfiles searchObject);
	String getUserName(FeeAccountProfiles feeAccountProfiles);
}

package net.syscon.s4.cf.deductions;

import java.util.List;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;

public interface OcuachisService {

	List<FeeAccountProfiles> accountHistoryQuery(FeeAccountProfiles searchObject);
	String getDescription(FeeAccountProfiles searchObject);
}

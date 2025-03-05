package net.syscon.s4.core;

import java.sql.SQLException;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;

public interface LovService {

	List<ReferenceCodes> getReferenceDomainCodes(String domain, String parent,String userId,String moduleName) throws SQLException;

}

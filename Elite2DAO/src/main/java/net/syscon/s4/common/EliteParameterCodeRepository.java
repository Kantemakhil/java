package net.syscon.s4.common;

import java.sql.SQLException;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;

/**
 * Class EliteParameterCodeRepository
 */
public interface EliteParameterCodeRepository {

	List<ReferenceCodes> searchEvidenceType(ReferenceCodes refCodeBean) throws SQLException;
}

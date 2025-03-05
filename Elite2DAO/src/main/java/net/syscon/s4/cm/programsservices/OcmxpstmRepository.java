package net.syscon.s4.cm.programsservices;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;

public interface OcmxpstmRepository {

	ReferenceCodes refCodeCondExecuteQuery(final String code);

	Integer refCodeCondInsertReference(List<ReferenceCodes> lstReferenceCodes);

	Integer refCodeCondUpdateReference(final List<ReferenceCodes> lstReferenceCodes);
}

package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.OffStatusesPkgSpec;

public interface AfterUpdateStatementReqRepository {

	Integer insertOffenderInternalStatuses(OffStatusesPkgSpec offStatusesPkgSpec);
}

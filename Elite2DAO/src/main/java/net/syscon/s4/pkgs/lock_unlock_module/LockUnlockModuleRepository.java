package net.syscon.s4.pkgs.lock_unlock_module;

import java.math.BigDecimal;

public interface LockUnlockModuleRepository {

	String getLSessionLockCur(final String pModuleName, final String pCaseload, final Integer pSessionId,
			final Integer pAccountCode);

	Integer insertLockedModules(final String pModuleName, final String pCaseload, final String pUser,
			final BigDecimal pRootOffenderId, final Integer pSessionId, final Integer pAccountCode,
			final String userName);
}

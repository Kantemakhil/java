package net.syscon.s4.pkgs.lock_unlock_module.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.pkgs.lock_unlock_module.LockUnlockModuleRepository;
import net.syscon.s4.pkgs.lock_unlock_module.LockUnlockModuleService;

@Service
public class LockUnlockModuleServiceImpl implements LockUnlockModuleService {

	@Autowired
	private LockUnlockModuleRepository lockUnlockModuleRepository;

	final private static String N = "N";

	@Override
	public void lockFormModule(final String pModuleName, final String pCaseload, final String pUser,
			final BigDecimal pRootOffenderId, final Integer pSessionId, final String userName) {
		lockFormModule(pModuleName, pCaseload, pUser, pRootOffenderId, pSessionId, null, userName);
	}

	@Override
	@Transactional
	public void lockFormModule(final String pModuleName, final String pCaseload, final String pUser,
			final BigDecimal pRootOffenderId, final Integer pSessionId, final Integer pAccountCode,
			final String userName) {
		String sessionlExi = N;
		if (pSessionId != null) {
			sessionlExi = lockUnlockModuleRepository.getLSessionLockCur(pModuleName, pCaseload, pSessionId,
					pAccountCode);
		}

		if ((sessionlExi != null ? sessionlExi : N) == N) {
			lockUnlockModuleRepository.insertLockedModules(pModuleName, pCaseload, pUser, pRootOffenderId, pSessionId,
					pAccountCode, userName);
		}
	}

}

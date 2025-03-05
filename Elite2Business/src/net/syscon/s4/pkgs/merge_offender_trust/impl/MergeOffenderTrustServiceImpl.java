package net.syscon.s4.pkgs.merge_offender_trust.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.merge_offender_trust.MergeOffenderTrustRepository;
import net.syscon.s4.pkgs.merge_offender_trust.MergeOffenderTrustService;

@Service
public class MergeOffenderTrustServiceImpl implements MergeOffenderTrustService {
	
	@Autowired
	private MergeOffenderTrustRepository mergeOffenderTrustRepository;

	@Override
	public boolean mergeFromHasTrustAccount(Long fromRootOffenderId) {
		Integer lvCount = 0;
		lvCount = mergeOffenderTrustRepository.checkForTrustAccountCur(fromRootOffenderId);
		if(lvCount > 0) {
			return true;
		}else {
			return false;
		}
	}

	
}

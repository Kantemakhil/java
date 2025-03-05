package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.triggers.OffFeeAccountProfileT1AndT2Repository;
import net.syscon.s4.triggers.OffFeeAccountProfileT1AndT2Service;
@Service
public class OffFeeAccountProfileT1AndT2ServiceImpl implements OffFeeAccountProfileT1AndT2Service {
	@Autowired
	private OffFeeAccountProfileT1AndT2Repository offFeeAccountProfileT1Repository;
	
	@Override
	public void offFeeAccountProfileT1AndT2(FeeAccountProfiles profiles) {
       if(profiles.getFeeActStatus()!=null && profiles.getFeeActStatus().equals(ApplicationConstants.A)) 
    	   offFeeAccountProfileT1Repository.insertAccountProfiles(profiles);
       else
    	   offFeeAccountProfileT1Repository.insertAccountProfiles(profiles);
	}
}

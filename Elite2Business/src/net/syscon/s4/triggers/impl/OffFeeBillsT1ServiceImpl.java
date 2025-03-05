package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.triggers.OffFeeBillsT1Repository;
import net.syscon.s4.triggers.OffFeeBillsT1Service;

/**
 * Class OffFeeBillsT1ServiceImpl
 */
@Service
public class OffFeeBillsT1ServiceImpl implements OffFeeBillsT1Service {

	@Autowired
	private OffFeeBillsT1Repository offFeeBillsT1Repository;

	@Override
	public String offFeeBillsT1(OffFeeBills bean) throws Exception {
		String raiseApplicationError;
		raiseApplicationError = offFeeBillsT1Repository.offFeeBillsT1(bean);
		if(raiseApplicationError.equals("Backdated")) {
			throw new Exception(
					"This bill is backdated and has to be marked accordingly");
		}else
			if(raiseApplicationError.equals("NotBackdated")) {
				throw new Exception(
						"This bill is not a backdated bill, please mark it accordingly");
			}else 
				return null;
	}
}

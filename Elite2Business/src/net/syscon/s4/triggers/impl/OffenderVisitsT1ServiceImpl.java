package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.OffenderVisitsT1Repository;
import net.syscon.s4.triggers.OffenderVisitsT1Service;

@Service("OffenderVisitsT1ServiceImpl")
public class OffenderVisitsT1ServiceImpl implements OffenderVisitsT1Service {

	@Autowired
	@Qualifier("OffenderVisitsT1RepositoryImpl")
	private OffenderVisitsT1Repository offenderVisitsT1Repository;
	
	@Override
	public void offenderVisitsT1(final String visitStatus) {
		if (visitStatus != null) {
			final Integer vNumrows = offenderVisitsT1Repository.getvNumrows(visitStatus);
			if (vNumrows == 0) {
				/*
				 *  raise_application_error(
                       -20001,
                    'Invalid Offender Visit Status.'
                      );
				 */
			}
		} 
	}

}

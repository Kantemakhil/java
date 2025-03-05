package net.syscon.s4.core.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.core.EliteDateRepository;
import net.syscon.s4.core.EliteDateService;

@Service
public class EliteDateServiceImpl implements EliteDateService {

	@Autowired
	private EliteDateRepository dateRepository;

	@Override
	public Date getDBTime() {
		return dateRepository.getDBTime();
	}

	@Override
	public String getFormateDBTime() {
		return dateRepository.getFormateDBTime();
	}

	@Override
	public String getDBTimeZoneName() {
		return dateRepository.getDBTimeZoneName();
	}

	@Override
	public long calculateAge(Date dob) {
		long age = 0;
		if (dob != null) {
			Calendar calCurrent = Calendar.getInstance();
			calCurrent.setTime(this.getDBTime());
			Calendar calDob = Calendar.getInstance();
			calDob.setTime(dob);
			age = calCurrent.get(Calendar.YEAR) - calDob.get(Calendar.YEAR);
			if (calCurrent.get(Calendar.DAY_OF_YEAR) < calDob.get(Calendar.DAY_OF_YEAR)) {
				age--;
			}
		}
		return age;
	}

}

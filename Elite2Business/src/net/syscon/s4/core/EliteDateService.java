package net.syscon.s4.core;

import java.util.Date;

public interface EliteDateService {
	
	public Date getDBTime();
	
	public String getFormateDBTime();
	
	public String getDBTimeZoneName();
	
	public long calculateAge(Date dob);

}

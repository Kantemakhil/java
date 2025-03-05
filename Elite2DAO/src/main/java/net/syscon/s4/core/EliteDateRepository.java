package net.syscon.s4.core;

import java.util.Date;

public interface EliteDateRepository {
	
	public Date getDBTime();
	
	public String getFormateDBTime();
	
	public String getDBTimeZoneName();

}

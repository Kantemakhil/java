package net.syscon.s4.pkgs.oms_date_time;

public interface OmsDateTimeService {
	
	Integer compareDateTime(final String pDate1,final String pTime1,final String pDate2,final String pTime2); 
	
	Integer compareDateTime(final String pDate1,final String pTime1);
}
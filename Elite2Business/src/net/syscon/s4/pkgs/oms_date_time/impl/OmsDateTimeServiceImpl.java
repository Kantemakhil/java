package net.syscon.s4.pkgs.oms_date_time.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.oms_date_time.OmsDateTimeService;

@Service
public class OmsDateTimeServiceImpl implements OmsDateTimeService {

	@Override
	public Integer compareDateTime(String pDate1, String pTime1, String pDate2, String pTime2) {
		LocalDateTime vdate1;
		LocalDateTime vdate2;
		LocalTime vTime1;
		LocalTime vTime2;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		if (pDate1 != null && pDate2 != null) {

			vdate1 = LocalDateTime.parse(pDate1, formatter);
			vdate1 = vdate1.truncatedTo(ChronoUnit.MINUTES);
			vdate2 = LocalDateTime.parse(pDate2, formatter);
			vdate2 = vdate2.truncatedTo(ChronoUnit.MINUTES);

		} else {
			String dfltDate = "01-01-1980";
			vdate1 = LocalDateTime.parse(dfltDate, formatter);
			vdate2 = LocalDateTime.parse(dfltDate, formatter);
		}
		if (pTime1 != null && pTime2 != null) {
			vTime1 = LocalTime.parse(pTime1.substring(pTime1.indexOf(" ") + 1, 8));
			vTime2 = LocalTime.parse(pTime2.substring(pTime2.indexOf(" ") + 1, 8));
		} else {
			LocalTime dfltTime = LocalTime.of(00, 00, 00);
			vTime1 = dfltTime;
			vTime2 = dfltTime;
		}
		if (vdate2.isAfter(vdate1)) {
			return -1;
		} else if (vdate1.equals(vdate2)) {
			if (vTime1.compareTo(vTime2) < 0) {
				return -1;
			} else if (vTime1.equals(vTime2)) {
				return 0;
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}

	@Override
	public Integer compareDateTime(String pDate1, String pTime1) {
		String vDate;
		String vTime;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("DD-MM-YYYY HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		vDate = dtf.format(now);
		vTime = dtf.format(now);
		return compareDateTime(pDate1, pTime1, vDate, vTime);
	}

}
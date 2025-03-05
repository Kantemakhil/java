package net.syscon.s4.core.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import net.syscon.s4.core.EliteDateRepository;
import net.syscon.s4.global.Omss40Repository;

@Repository
public class EliteDateRepositoryImpl implements EliteDateRepository{

	private static Logger logger = LogManager.getLogger(EliteDateRepositoryImpl.class.getName());

	@Autowired
	private Omss40Repository omss40Repository;

	private static final ThreadLocal<SimpleDateFormat> sdf = ThreadLocal.<SimpleDateFormat>withInitial(() -> {
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	});

	private long timeDiff;

	private String dbTZName;

	@Scheduled(fixedDelay = 900000)
	public void updateDate() {
		try {
			long start = new Date().getTime();
			String dbTimeStr = omss40Repository.getServerTime();
			logger.info("dbTimeStr : {}", dbTimeStr);
			String[] details = dbTimeStr.split(" ");
			Date dbTime = EliteDateRepositoryImpl.sdf.get().parse(details[0] + "T" + details[1]);
			long end = new Date().getTime();
			this.timeDiff = new Date().getTime() - (dbTime.getTime() + (end - start) / 2);
			logger.info("Date Details : {}", details);
			logger.info("Date Details length: {}", details.length);
			String[] zoneInfo = details[2].split(":");
			String[] zoneIds = TimeZone.getAvailableIDs(
					((Integer.parseInt(zoneInfo[0]) * 60) + Integer.parseInt(zoneInfo[1])) * 60 * 1000);
			logger.info("ZoneIds : {}", zoneIds);
			if(zoneIds != null && zoneIds.length > 0) {
				this.dbTZName = zoneIds[0];
			}
		} catch (Exception e) {
			logger.error("Exception in retriving the db time", e);
		}
	}

	@Override
	public Date getDBTime() {
		Date current = new Date();
		current.setTime(new Date().getTime() - this.timeDiff);
		return current;
	}
	
	@Override
	public String getFormateDBTime(){
		Date current = new Date();
		current.setTime(new Date().getTime() - this.timeDiff);
		return EliteDateRepositoryImpl.sdf.get().format(current);
	}
	
	@Override
	public String getDBTimeZoneName() {
		return this.dbTZName;
	}

}

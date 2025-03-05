package net.syscon.s4.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.cm.programsservices.OcduprojController;
import net.syscon.s4.pkgs.tag_schedule.TagScheduleService;

@EliteController
public class JobsController {
	
	@Autowired
	private TagScheduleService tagScheduelService;
	
	private static Logger logger = LogManager.getLogger(OcduprojController.class.getName());
	
	
	/*
	 * TAG_SCHEDULE_JOB
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "jobs/tagschedulejob", method = RequestMethod.POST)
	public void flushSchedulesJob() {
		try {
			tagScheduelService.flushSchedules();
		} catch (Exception e) {
			logger.error("Error in "+this.getClass().getName()+" flushSchedulesJob method "+ e.getMessage());
		}
	}

}

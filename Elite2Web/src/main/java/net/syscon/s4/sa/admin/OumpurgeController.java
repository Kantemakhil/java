package net.syscon.s4.sa.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.AgencyLocations;

@EliteController
public class OumpurgeController {
	@Autowired
	private OumpurgeService oumpurgeService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumpurgeController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumpurge/offExecuteQuery", method = RequestMethod.POST)
	public List<VHeaderBlock> offExecuteQuery(@RequestBody final VHeaderBlock searchBean) {
		List<VHeaderBlock> searchResult = new ArrayList<>();
		try {
			if (searchBean != null) {
				searchBean.setAgyLocId(searchBean.getCreateuserId());
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = oumpurgeService.offExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumpurge/offBkgExecuteQuery", method = RequestMethod.POST)
	public List<OffenderBookings> offBkgExecuteQuery(@RequestBody final OffenderBookings searchBean) {
		List<OffenderBookings> searchResult = new ArrayList<>();
		try {
			searchResult = oumpurgeService.offBkgExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumpurge/offBkgCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offBkgCommit(@RequestBody final OffenderBookingsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumpurgeService.offBkgCommit(commitBean);
		} catch (final Exception e) {
			logger.error("Exception : Oumpurge", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumpurge/purgeOffenderCommit", method = RequestMethod.POST)
	public @ResponseBody VHeaderBlock purgeOffenderCommit(@RequestBody final VHeaderBlock object) {
		VHeaderBlock liReturn = new VHeaderBlock();
		try {
			liReturn = oumpurgeService.purgeOffenderCommit(object);
		} catch (final Exception e) {
			logger.error("Exception : Oumpurge", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumpurge/whenTimerExpired", method = RequestMethod.POST)
	public @ResponseBody VHeaderBlock whenTimerExpired(@RequestBody final VHeaderBlock object) {
		VHeaderBlock liReturn = new VHeaderBlock();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			liReturn = oumpurgeService.whenTimerExpired(object,userName);
		} catch (final Exception e) {
			logger.error("Exception : Oumpurge", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumpurge/getSealButtonAccessPermission", method = RequestMethod.GET)
	public @ResponseBody Integer getSealButtonAccessPermission() {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			liReturn = oumpurgeService.getSealButtonAccessPermission(userName);
		} catch (final Exception e) {
			logger.error("Exception : Oumpurge", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumpurge/getLvCountSealBookings", method = RequestMethod.GET)
	public Integer getLvCountSealBookings(@RequestParam("rootOffenderId") final String rootOffenderId) {
		Integer recordCount=0;
		try {
			recordCount = oumpurgeService.getLvCountSealBookings(rootOffenderId);
		} catch (Exception e) {
			logger.error("Exception : oumpurge: getLvCountSealBookings", e);
		}
		return recordCount;
	}
}
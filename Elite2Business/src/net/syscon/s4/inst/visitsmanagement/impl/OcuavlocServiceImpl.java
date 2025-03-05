package net.syscon.s4.inst.visitsmanagement.impl;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.visitsmanagement.OcuavlocRepository;
import net.syscon.s4.inst.visitsmanagement.OcuavlocService;
import net.syscon.s4.inst.visitsmanagement.beans.VOcuavlocAvailable;
import net.syscon.s4.inst.visitsmanagement.beans.VOcuavlocUnavailable;
import net.syscon.s4.pkgs.tag_visits.TagVisitsService;

/**
 * Class OcuavlocServiceImpl
 */
@Service
public class OcuavlocServiceImpl extends BaseBusiness implements OcuavlocService {

	@Autowired
	private OcuavlocRepository ocuavlocRepository;

	@Autowired
	private TagVisitsService TagVisitsService;

	/**
	 * Creates new OcuavlocServiceImpl class Object
	 */
	public OcuavlocServiceImpl() {
		// OcuavlocServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 */
	public List<VOcuavlocUnavailable> avlLocPreQuery(final VOcuavlocUnavailable paramBean) {
		return ocuavlocRepository.avlLocPreQuery(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VOcuavlocAvailable> avlLocExecuteQuery(final VOcuavlocAvailable searchRecord) {
		return ocuavlocRepository.avlLocExecuteQuery(searchRecord);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VOcuavlocUnavailable> fboLocExecuteQuery(final VOcuavlocUnavailable searchRecord) {
		return ocuavlocRepository.fboLocExecuteQuery(searchRecord);

	}

	/**
	 * used to execute GET_OCUAVLOC_AVAILABLE procedure.
	 * 
	 * @Param objSearchDao
	 * @return List<VOcuavlocAvailable>
	 */
	public List<VOcuavlocAvailable> getOcuavlocAvailable(final VOcuavlocAvailable objSearchDao) {
		final String weekDay = new SimpleDateFormat("EEEE").format(objSearchDao.getVisitDate());
		objSearchDao.setWeekDay(weekDay.substring(0, 3).toUpperCase());
		// Procedure call
		final List<VOcuavlocAvailable> returnList = TagVisitsService.getOcuavlocAvailable(objSearchDao);
		Collections.sort(returnList, (o1, o2) -> o1.getInternalLocationId() - o2.getInternalLocationId());
		return returnList;

	}

	/**
	 * used to execute GET_OCUAVLOC_UNAVAILABLE procedure.
	 * 
	 * @Param objSearchDao
	 * @return List<VOcuavlocUnavailable>
	 */
	public List<VOcuavlocUnavailable> getOcuavlocUnAvailable(final VOcuavlocUnavailable objSearchDao) {
		final String weekDay = new SimpleDateFormat("EEEE").format(objSearchDao.getVisitDate());
		objSearchDao.setWeekDay(weekDay.substring(0, 3).toUpperCase());
		// Procedure call
		List<VOcuavlocUnavailable> returnList = TagVisitsService.getOcuavlocUnavailable(objSearchDao);
		Collections.sort(returnList, (o1, o2) -> o1.getInternalLocationId() - o2.getInternalLocationId());
		return returnList;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public Integer reCheckTimeSlot(final VOcuavlocAvailable searchRecord) {
		Integer returnValue = 0;
		final VOcuavlocAvailable maxGroupsList = ocuavlocRepository.reCheckTimeSlot(searchRecord);
		final VOcuavlocAvailable curVisList = ocuavlocRepository.reCheckTimeSlotCursorcVis(searchRecord);
		final VOcuavlocAvailable curAllList = ocuavlocRepository.reCheckTimeSlotCursorcAll(searchRecord);
		maxGroupsList.setMaxGroups(maxGroupsList.getMaxGroups() != null ? maxGroupsList.getMaxGroups() : 999);
		maxGroupsList.setMaxAdults(maxGroupsList.getMaxAdults() != null ? maxGroupsList.getMaxAdults() : 999);
		maxGroupsList.setCapacity(maxGroupsList.getCapacity() != null ? maxGroupsList.getCapacity() : 999);
		curVisList.setGroupsBooked(curVisList.getGroupsBooked() != null ? curVisList.getGroupsBooked() : 0);
		curVisList.setTotalBooked(curVisList.getTotalBooked() != null ? curVisList.getTotalBooked() : 0);
		curVisList.setAdultsBooked(curVisList.getAdultsBooked() != null ? curVisList.getAdultsBooked() : 0);
		curAllList.setGroupsBooked(curAllList.getGroupsBooked() != null ? curAllList.getGroupsBooked() : 0);
		curAllList.setTotalBooked(curAllList.getTotalBooked() != null ? curAllList.getTotalBooked() : 0);
		curAllList.setAdultsBooked(curAllList.getAdultsBooked() != null ? curAllList.getAdultsBooked() : 0);
		if (curVisList.getGroupsBooked() + curAllList.getGroupsBooked() >= maxGroupsList.getMaxGroups()
				|| curVisList.getAdultsBooked() + curAllList.getAdultsBooked() >= maxGroupsList.getMaxAdults()
				|| curVisList.getTotalBooked() + curAllList.getTotalBooked() >= maxGroupsList.getCapacity()) {
			returnValue = 0;
		} else {
			returnValue = 1;
		}
		return returnValue;

	}

}
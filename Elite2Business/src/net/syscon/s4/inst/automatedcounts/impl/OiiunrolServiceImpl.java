package net.syscon.s4.inst.automatedcounts.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.VLivingUnitOffenders;
import net.syscon.s4.im.beans.VLivingUnitOffendersCommitBean;
import net.syscon.s4.inst.automatedcounts.OiiunrolRepository;
import net.syscon.s4.inst.automatedcounts.OiiunrolService;
import net.syscon.s4.pkgs.tag_booking.impl.TagBookingServiceImpl;


/**
 * Class OiiunrolServiceImpl
 */
@Service
public class OiiunrolServiceImpl extends BaseBusiness implements OiiunrolService {

	@Autowired
	private OiiunrolRepository oiiunrolRepository;

	@Autowired
	private TagBookingServiceImpl TagBookingService;
	/**
	 * Creates new OiiunrolServiceImpl class Object
	 */
	public OiiunrolServiceImpl() {
		
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * @
	 */
	public List<VLivingUnitOffenders> rollListExecuteQuery(final VLivingUnitOffenders searchRecord) {
		final List<VLivingUnitOffenders> returnList = oiiunrolRepository.rollListExecuteQuery(searchRecord);
		for (final VLivingUnitOffenders obj : returnList) {
			if (obj.getAgencyImlDesc() == null) {
				final String currentLocation = TagBookingService.getExtLocation(obj);
				if (currentLocation != null) {
					obj.setAgencyImlDesc(currentLocation.toUpperCase());
				}
			}
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstROLL_LIST
	 *
	 * @
	 */
	@Transactional
	public Integer rollListCommit(final VLivingUnitOffendersCommitBean commitBean) {
		final int liReturn = 0;
		return liReturn;
	}

}
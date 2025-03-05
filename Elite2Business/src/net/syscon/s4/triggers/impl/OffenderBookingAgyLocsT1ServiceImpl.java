package net.syscon.s4.triggers.impl;

import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.pkgs.tag_core.TagCoreService;
import net.syscon.s4.triggers.OffenderBookingAgyLocsT1Repository;
import net.syscon.s4.triggers.OffenderBookingAgyLocsT1Service;
import net.syscon.s4.triggers.OffenderBookingsBkadmTrgService;
import net.syscon.s4.triggers.OffenderBookingsT2Service;
import net.syscon.s4.triggers.OffenderBookingsT7Service;
import net.syscon.s4.triggers.OffendersBookVineIntfTrgService;
/* =========================================================
Below comments are copied from OFFENDER_BOOKING_AGY_LOCS_T1 Trigger
========================================================= */

/* MODIFICATION HISTORY
Person        Date           Version             Comments
---------    ---------    ------------    -----------------------------------------------
Girish        01/03/2001   4.11.0.0          Modified Trigger :- does not  update
                                             community_agy_loc_id to MULTI when
                                             offender_status is updated.
*/
@Service
public class OffenderBookingAgyLocsT1ServiceImpl implements OffenderBookingAgyLocsT1Service {
	private final Logger logger = LogManager.getLogger(OffenderBookingAgyLocsT1ServiceImpl.class);
	@Autowired
	OffenderBookingAgyLocsT1Repository offenderBookingAgyLocsT1Repository;
	@Autowired
	TagCoreService tagCoreService;
	@Autowired
	OffenderBookingsBkadmTrgService offenderBookingsBkadmTrgService;

	@Autowired
	OffendersBookVineIntfTrgService offendersBookVineIntfTrgService;

	@Autowired
	OffenderBookingsT7Service offenderBookingsT7Service;

	@Autowired
	OffenderBookingsT2Service offenderBookingsT2Service;

	// Below method call after insert or update on OFFENDER_BOOKING_AGY_LOCS table
	@Override
	public Integer offenderBookingAgyLocsT1Triger(final OffenderBookingAgyLocs offenderBookingAgyLocs,
			final String sqlOperation) {
		final Pattern LTRIM = Pattern.compile("^\\s+");
		final Pattern RTRIM = Pattern.compile("\\s+$");
		String vAgyLocIdList = null;
		String vCommAgy = null;
		final OffenderBookings targetObject = new OffenderBookings();
		Integer result;

		try {
			// retrieving details from offender_bookings based on offender_book_id
			final OffenderBookings offenderBookings = offenderBookingAgyLocsT1Repository
					.getOffenderBookings(offenderBookingAgyLocs.getOffenderBookId());
			if ("INSERTING".equals(sqlOperation)) {
				if (null != offenderBookings && offenderBookings.getAgyLocIdList() == null) {

					vAgyLocIdList = offenderBookingAgyLocs.getAgyLocId();
				} else {
					// below method call is used to adding string
					vAgyLocIdList = offenderBookings.getAgyLocIdList() +
							";" + offenderBookingAgyLocs.getAgyLocId();
				}
			} else if ("UPDATING".equals(sqlOperation)) {
				if (offenderBookingAgyLocs.getRemovedDate() != null && offenderBookings.getAgyLocIdList()!=null) {
					// below method call is used to removing string
					vAgyLocIdList=offenderBookings.getAgyLocIdList().replaceAll(offenderBookingAgyLocs.getAgyLocId()+";","");
					/*vAgyLocIdList = tagCoreService.removeString(offenderBookings.getAgyLocIdList(),
							 offenderBookingAgyLocs.getAgyLocId());
					if (vAgyLocIdList != null) {
						vAgyLocIdList = LTRIM.matcher(vAgyLocIdList).replaceAll(";");
						vAgyLocIdList = RTRIM.matcher(vAgyLocIdList).replaceAll(";");
					}*/

				}
			}
			if (vAgyLocIdList != null) {
					vCommAgy = vAgyLocIdList.indexOf(';') == -1 ? vAgyLocIdList : "MULTI";
			}
			targetObject.setAgyLocIdList(vAgyLocIdList);
			targetObject.setCommunityAgyLocId(vCommAgy);
			targetObject.setModifyDatetime(offenderBookingAgyLocs.getCreateDatetime());
			targetObject.setModifyUserId(offenderBookingAgyLocs.getCreateUserId());
			targetObject.setOffenderBookId(offenderBookingAgyLocs.getOffenderBookId());
			// adding trigger
			// below trigger method call executes before updating data in to Offender_bookings table
			offenderBookingsT2Service.offenderBookingsT2(offenderBookings, targetObject);
			// below method is used to update data into Offender_bookings table
			result = offenderBookingAgyLocsT1Repository.update(targetObject);
			// adding trigger
			String operation = sqlOperation;
			// below trigger method call executes after updation data in to Offender_bookings table
			offenderBookingsBkadmTrgService.offenderBookingsBkadmTrg(targetObject, offenderBookings, operation);
			// below trigger method call executes after updation data in to Offender_bookings table
			offendersBookVineIntfTrgService.OffendersBookVineIntfTrgTrigger(targetObject,operation);
			// below trigger method call executes after updation data in to Offender_bookings table
			offenderBookingsT7Service.offenderBookingsT7Trigger(targetObject);
		} catch (final Exception e) {
			result = 0;
			logger.error("offenderBookingAgyLocsT1Triger", e);
		}
		return result;
	}
}

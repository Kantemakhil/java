package net.syscon.s4.pkgs.tag_internal_locations.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.pkgs.tag_internal_locations.TagInternalLocationsRepository;
import net.syscon.s4.pkgs.tag_internal_locations.TagInternalLocationsService;
import net.syscon.s4.triggers.AgencyInternalLocationsT1Service;

/*
 * Below comments are copied from package TAG_INTERNAL_LOCATIONS
 - ======================================================================================
   v_version   CONSTANT VARCHAR2 (30) := '2.8   26-MAR-2008';
-- ======================================================================================

  MODIFICATION HISTORY
   --------------------------------------------------------------------------------------
   Person      Date         Version   Comments
   ---------   -----------  --------- ---------------------------------------------------
   Surya       09-Nov-2005  2.0       Initial Draft.
   Surya       22-Nov-2005  2.1       Modified get_internal_location_record for the
                                      usage description as per the design change.
   Surya       24-Nov-2005  2.2       Added the get_internal_location_desc sub-routine
                                      to retrieve the description for Incidents Screen.
                                      Removed the get_table subroutine as it is moved to
                                      tag_utils package.
   GJC         11-Jan-2006  2.3       Add procedure update_related_tracking_flags
   Krishna     11-Apr-2006  2.4       Add new parameter Unit_type to record g_location_rec
                                      Change in query_internal_location proc. to return unit_type
                                      Add new parameter p_show_living_unit to query_internal_location proc.
   GJC         16-Oct-2006  2.5       Change SHOW_VERION to a function
   Krishna     18-Jan-2007	2.6       TD 5937:Added check_internal_mvmt_locations function.
   Krishna     19-Jan-2007  2.7       Added Connect By to SQL in check_internal_mvmt_locations function.
   Niko        26-MAR-2008  2.8       Added procedure - act_deact_int_child_location   
                                      activates/deactivates the all the child records of an agency internal location.

*/

@Service
@Transactional
public class TagInternalLocationsServiceImpl implements TagInternalLocationsService {
	@Autowired
	private TagInternalLocationsRepository tagInternalLocationsRepository;
	@Autowired
	private AgencyInternalLocationsT1Service agencyInternalLocationsT1Service;
	private static final String Y = "Y";
	private static Logger logger = LogManager.getLogger(TagInternalLocationsServiceImpl.class.getName());

	@Override
	public List<AgencyInternalLocations> queryInternalLocations(final AgencyInternalLocations bean) {
		List<AgencyInternalLocations> list = null;
		if (bean.getInternalLocationId() == null) {
			if (bean.getInternalLocationCode() != null || bean.getDescription() != null || bean.getpLevel() != null) {
				list = tagInternalLocationsRepository.queryOne(bean);
			} else {
				list = tagInternalLocationsRepository.queryTwo(bean.getAgyLocId(), bean.getTrackingFlag());

			}
		} else {
			list = tagInternalLocationsRepository.queryThree(bean);
		}
		return list;
	}

	@Override
	public AgencyInternalLocations defaultLocationDescription(final AgencyInternalLocations agyIntLoc) {
		String vDescription = null;
		final AgencyInternalLocations lu = new AgencyInternalLocations();
		try {
			if (agyIntLoc.getParentInternalLocationId() == null) {
				vDescription = agyIntLoc.getAgyLocId() + '-' + agyIntLoc.getInternalLocationCode();
			} else {
				vDescription = tagInternalLocationsRepository.getDefaultLocationDesc(
						agyIntLoc.getInternalLocationCode(), agyIntLoc.getParentInternalLocationId());

			}
			lu.setSealFlag(vDescription);
		} catch (Exception e) {
			logger.error("defaultLocationDescription :" + e);
		}
		return lu;
	}
	@Override
	public Integer getInternalLocationId() {
		return tagInternalLocationsRepository.getInternalLocationId();

	}
	@Override
	public AgencyInternalLocations getInternalLocationRecord(final Integer internalLocId) {
		return tagInternalLocationsRepository.getInternalLocationRecord(internalLocId);
	}

	@Override
	public Integer actDeactIntChildLocation(final AgencyInternalLocations off) {
		final String flag = off.getActiveFlag();
		Integer genSeq = 0;
		try {
			if (flag.equals(Y)) {
				final AgencyInternalLocations oldOff = tagInternalLocationsRepository
						.getOldRecordAgencyIntLoc(off.getInternalLocationId());
				tagInternalLocationsRepository.agencyInternalLocations(off);
				agencyInternalLocationsT1Service.AgencyInternalLocationsT1(oldOff, off, flag);
			} else {
				final AgencyInternalLocations oldOff = tagInternalLocationsRepository
						.getOldRecordAgencyIntLoc(off.getInternalLocationId());
				tagInternalLocationsRepository.agencyInternalLocationsUpdate(off);
				agencyInternalLocationsT1Service.AgencyInternalLocationsT1(oldOff, off, flag);
			}
			genSeq = 1;
		} catch (Exception e) {
			logger.error("actDeactIntChildLocation :", e);
			return genSeq;
		}
		return genSeq;
	}

	@Override
	public Integer updateRelatedTrackingFlags(final AgencyInternalLocations off) {
		Integer genSeq = 0;
		try {
			final AgencyInternalLocations oldOff = tagInternalLocationsRepository
					.getOldRecordAgencyIntLoc(off.getInternalLocationId());
			tagInternalLocationsRepository.updateRelatedTrackingFlagsUpdate(off);
			agencyInternalLocationsT1Service.AgencyInternalLocationsT1(oldOff, off, null);
			genSeq = 1;
		} catch (Exception e) {
			logger.error("updateRelatedTrackingFlags :", e);
			return genSeq;
		}
		return genSeq;
	}

}
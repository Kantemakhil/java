package net.syscon.s4.inst.inquiries.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.inquiries.OiischedRepository;
import net.syscon.s4.inst.inquiries.OiischedService;
import net.syscon.s4.inst.movements.impl.OidintmvRepositoryImpl;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

/**
 * Class OiischedServiceImpl
 */
@Service
public class OiischedServiceImpl extends BaseBusiness implements OiischedService {

	@Autowired
	private OiischedRepository oiischedRepository;
	
	
	@Autowired
	private OidintmvRepositoryImpl oidintmvRepositoryImpl;
	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 */
	public List<VOffenderAllSchedules> offSchExecuteQuery(final VOffenderAllSchedules searchRecord) {
		List<VOffenderAllSchedules> returnList = oiischedRepository.offSchExecuteQuery(searchRecord);
		if (returnList != null && returnList.size() > 0) {
			returnList.forEach(ele -> {
				if (ele.getAppearanceLocation() != null) {
					ReferenceCodes referenceCodes = oidintmvRepositoryImpl.courtEventsLocation(ele.getAgyLocId(),
							ele.getAppearanceLocation());
					if (referenceCodes != null && referenceCodes.getDescription() != null)
						ele.setAppearanceLocation(referenceCodes.getDescription());
					else
						ele.setAppearanceLocation(null);
				}
			});
		}
		return returnList;
	}

	/**
	 * This method is used to execute a record group
	 */
	public List<ReferenceCodes> rgTypeRecordGroup(final String scheduleFilter) {
		final List<ReferenceCodes> referenceCodesList = oiischedRepository.rgTypeRecordGroup(scheduleFilter);
		if (!referenceCodesList.isEmpty()) {
			Integer count = 0;
			for (final ReferenceCodes referenceCodes : referenceCodesList) {
				count = count + 1;
				referenceCodes.setListSeq(new BigDecimal(count));
			}
		}
		return referenceCodesList;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<InternalScheduleReasons> rgSubtypeRecordGroup(final String scheduleFilter, final String scheduleType) {
		final List<InternalScheduleReasons> internalScheduleReasonsList = oiischedRepository
				.rgSubtypeRecordGroup(scheduleFilter, scheduleType);
		if (!internalScheduleReasonsList.isEmpty()) {
			Integer count = 0;
			for (final InternalScheduleReasons teams : internalScheduleReasonsList) {
				count = count + 1;
				teams.setListSeq(new BigDecimal(count));
			}
		}
		return internalScheduleReasonsList;
	}

	@Override
	public List<ReferenceCodes> rgSchFilterRecordGroup() {
		final List<ReferenceCodes> schFilter = new ArrayList<>();
		final ReferenceCodes all = new ReferenceCodes();
		final ReferenceCodes internal = new ReferenceCodes();
		final ReferenceCodes external = new ReferenceCodes();
		all.setCode("ALL");
		all.setListSeq(new BigDecimal(1));
		all.setDescription("All");
		schFilter.add(all);
		internal.setListSeq(new BigDecimal(2));
		internal.setCode("INTERNAL");
		internal.setDescription("Internal");
		schFilter.add(internal);
		external.setListSeq(new BigDecimal(3));
		external.setCode("EXTERNAL");
		external.setDescription("External");
		schFilter.add(external);
		return schFilter;
	}

}
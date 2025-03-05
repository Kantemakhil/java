package net.syscon.s4.inst.automatedcounts.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocationCounts;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.automatedcounts.OiihiscoRepository;
import net.syscon.s4.inst.automatedcounts.OiihiscoService;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCounts;
import net.syscon.s4.inst.demographicsbiometrics.OcuverifRepository;

/**
 * Class OiihiscoServiceImpl
 */
@Service
public class OiihiscoServiceImpl extends BaseBusiness implements OiihiscoService {

	@Autowired
	private OiihiscoRepository oiihiscoRepository;
	
	@Autowired
	private OcuverifRepository ocuverifRepo;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<AgencyCounts> agencyCountsExecuteQuery(final AgencyCounts searchBean) {

		final List<AgencyCounts> returnList = oiihiscoRepository.agencyCountsExecuteQuery(searchBean);
		for (final AgencyCounts agencyCounts : returnList) {
			agencyCounts.setDiscrep((agencyCounts.getTotalReported() - agencyCounts.getTotalActual()));

			final List<AgencyCountTypes> countIdTime = oiihiscoRepository
					.getCountIdAndTime(agencyCounts.getCountTypeId());
			for (final AgencyCountTypes aCountTypes : countIdTime) {
				agencyCounts.setCountTypeCode(aCountTypes.getCountTypeCode());
				agencyCounts.setSchTime(aCountTypes.getScheduledTime());
			}
			
		}
		
		String userId = null;
		String userName = null;
		for (AgencyCounts list : returnList) {
			userId = list.getConductedByUserid();
			userName = ocuverifRepo.getUserName(userId);
			list.setConductedByUserid(userName);
		}
		return returnList;

	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAGENCY_COUNTS
	 *
	 * 
	 */
	public List<AgencyLocationCounts> agencyLocationCountsExecuteQuery(final AgencyLocationCounts searchRecord) {
		final List<AgencyLocationCounts> returnList = oiihiscoRepository.agencyLocationCountsExecuteQuery(searchRecord);
		if (returnList != null) {
			for (final AgencyLocationCounts agencyLocationCounts : returnList) {
				if (agencyLocationCounts.getRecountTotal() != null && agencyLocationCounts.getActualCount() != null) {
					agencyLocationCounts
							.setDiscrep(agencyLocationCounts.getRecountTotal() - agencyLocationCounts.getActualCount());
				}
				if (agencyLocationCounts.getReportedCount() != null && agencyLocationCounts.getActualCount() != null) {
					agencyLocationCounts.setDiscrepTemp(
							agencyLocationCounts.getReportedCount() - agencyLocationCounts.getActualCount());
				} else {
					agencyLocationCounts.setDiscrepTemp(0);
				}
				final String location = oiihiscoRepository.getLocationDesc(agencyLocationCounts.getReportingLocId(),
						agencyLocationCounts.getAgySeq());
				if (location != null) {
					agencyLocationCounts.setLocation(location);
				}
				if(agencyLocationCounts.getReportingLocId() != null && agencyLocationCounts.getReportingLocId() != 0) {
					String recountComment = oiihiscoRepository.getRecountComment(agencyLocationCounts.getReportingLocId());
					agencyLocationCounts.setCommentText(recountComment);
				}
			}
		}
		String userId = null;
		String userName = null;
		String userId1 = null;
		String userName1 = null;
		for (AgencyLocationCounts list : returnList) {
			userId = list.getConductedByUserid();
			userId1= list.getEnteredByUserid();
			userName = ocuverifRepo.getUserName(userId);
			userName1 = ocuverifRepo.getUserName(userId1);
			list.setConductedByUserid(userName);
			list.setEnteredByUserid(userName1);
		}
		
		return returnList;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstAGENCY_LOCATION_COUNTS
	 *
	 * 
	 */
	public List<AgencyLocations> cgfkAgyLocIdRecordGroup(final String caseloadId) {
		final List<AgencyLocations> returnList = oiihiscoRepository.cgfkAgyLocIdRecordGroup(caseloadId);
		returnList.forEach(result -> {
			result.setCode(result.getAgyLocId());
			result.setDescription(result.getDescription());
		});
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<ReferenceCodes> cgfkCountTypesRecordGroup(final String location) {
		final List<ReferenceCodes> returnList = oiihiscoRepository.cgfkCountTypesRecordGroup(location);
		returnList.forEach(result -> {
			result.setCode(result.getCode());
			result.setDescription(result.getDescription());

		});
		return returnList;

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<AgencyCountTypes> cgfkSchTimeRecordGroup(final String countTypeCode, final String agylocId) {
		final List<AgencyCountTypes> returnList = oiihiscoRepository.cgfkSchTimeRecordGroup(countTypeCode, agylocId);
		returnList.forEach(result -> {
			result.setCode(result.getCountTypeCode());
			if (result.getScheduledTime() == null) {
				result.setDescription("");
			} else {
				result.setDescription(result.getScheduledTime());
			}
		});
		return returnList;
	}

}
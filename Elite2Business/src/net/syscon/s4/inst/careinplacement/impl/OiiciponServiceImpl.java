package net.syscon.s4.inst.careinplacement.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.careinplacement.OiiciponRepository;
import net.syscon.s4.inst.careinplacement.OiiciponService;
import net.syscon.s4.inst.careinplacement.beans.OffenderCipDetails;

@Service
public class OiiciponServiceImpl extends BaseBusiness implements OiiciponService {

	private static Logger logger = LogManager.getLogger(OiiciponServiceImpl.class.getName());

	@Autowired
	private OiiciponRepository oiiciponRepository;

	public List<AgencyLocations> rgAgyLocsRecordGroup(final String caseloadId) {
		return oiiciponRepository.rgAgyLocsRecordGroup(caseloadId);
	}

	public List<OffenderCipDetails> offCipDetailsExecuteQuery(final OffenderCipDetails searchRecord) {
		List<OffenderCipDetails> lstOffDetails = (List<OffenderCipDetails>) oiiciponRepository
				.offCipDetailsExecuteQuery(searchRecord);

		return lstOffDetails;
	}

}

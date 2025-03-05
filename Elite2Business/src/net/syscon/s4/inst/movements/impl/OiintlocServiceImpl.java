package net.syscon.s4.inst.movements.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.movements.OiintlocRepository;
import net.syscon.s4.inst.movements.OiintlocService;
import net.syscon.s4.inst.movements.beans.VIntLocUsageLocations;

/**
 * Class OiintlocServiceImpl
 */
@Service
public class OiintlocServiceImpl extends BaseBusiness implements OiintlocService {

	@Autowired
	private OiintlocRepository oiintlocRepository;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiintlocServiceImpl.class.getName());

	/**
	 * Creates new OiintlocServiceImpl class Object
	 */
	public OiintlocServiceImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VIntLocUsageLocations> intLocExecuteQuery(final VIntLocUsageLocations searchRecord) {
		return oiintlocRepository.intLocExecuteQuery(searchRecord);

	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ReferenceCodes> rgUsagesRecordGroup() {
		 List<ReferenceCodes> refList = oiintlocRepository.rgUsagesRecordGroup();
		if(Optional.ofNullable(refList).isPresent()) {
			refList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return refList;

	}

}
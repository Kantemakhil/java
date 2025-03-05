package net.syscon.s4.inst.casemanagement.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.casemanagement.OcutasatRepository;
import net.syscon.s4.inst.casemanagement.OcutasatService;

/**
 * Class OcutasatServiceImpl
 */
@Service
public class OcutasatServiceImpl extends BaseBusiness implements OcutasatService {

	@Autowired
	private OcutasatRepository ocutasatRepository;

	public List<Teams> teamsExecuteQuery(final Teams searchRecord) {
		return ocutasatRepository.teamsExecuteQuery(searchRecord);

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<ReferenceCodes> rgAreaTypeRecordGroup() {
		return ocutasatRepository.rgAreaTypeRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * @
	 */
	public List<Areas> rgAreaRecordGroup(final String areaType) {
		return ocutasatRepository.rgAreaRecordGroup(areaType);

	}

}
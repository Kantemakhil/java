package net.syscon.s4.inst.institutionalactivities.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.VOffenderProgramProfiles;
import net.syscon.s4.im.beans.VOffenderProgramProfilesCommitBean;
import net.syscon.s4.inst.institutionalactivities.OcupaoffRepository;
import net.syscon.s4.inst.institutionalactivities.OcupaoffService;

/**
 * Class OcupaoffServiceImpl
 */
@Service
public class OcupaoffServiceImpl extends BaseBusiness implements OcupaoffService {

	@Autowired
	private OcupaoffRepository ocupaoffRepository;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 */
	public List<VOffenderProgramProfiles> vOffPrgProfilesExecuteQuery(VOffenderProgramProfiles searchRecord) {
		return ocupaoffRepository.vOffPrgProfilesExecuteQuery(searchRecord);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstV_OFF_PRG_PROFILES
	 *
	 */
	@Transactional
	public Integer vOffPrgProfilesCommit(VOffenderProgramProfilesCommitBean commitBean) {
		int liReturn = 0;
		return liReturn;
	}

}
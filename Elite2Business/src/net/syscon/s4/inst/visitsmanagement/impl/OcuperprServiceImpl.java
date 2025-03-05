package net.syscon.s4.inst.visitsmanagement.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceDomains;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.PersonProfiles;
import net.syscon.s4.im.beans.PersonProfilesCommitBean;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.inst.visitsmanagement.OcuperprRepository;
import net.syscon.s4.inst.visitsmanagement.OcuperprService;
import net.syscon.s4.pkgs.tag_person_search.TagPersonSearchService;

/**
 * Class OcuperprServiceImpl
 */
@Service
public class OcuperprServiceImpl extends BaseBusiness implements OcuperprService {

	@Autowired
	private OcuperprRepository ocuperprRepository;
	@Autowired
	private TagPersonSearchService tagPersonSearchService;

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 
	 */
	public List<PersonProfiles> profilesExecuteQuery(final PersonProfiles searchRecord) {

		final List<PersonProfiles> returnList = ocuperprRepository.profilesExecuteQuery(searchRecord);
		for (final PersonProfiles personProfiles : returnList) {
			final ProfileTypes profileTypes = getProfileTypeDesc(personProfiles);
			personProfiles.setNbtProfileType(profileTypes.getDescription());
			if (personProfiles.getProfileCode() != null && personProfiles.getProfileType() != null) {
				final ProfileCodes profileCodes = getProfileCodeDesc(personProfiles);
				if(personProfiles.getNbtProfileType().equals("Age")){
					personProfiles.setNbtProfileCode(personProfiles.getProfileCode().toString());
				}else{
				personProfiles.setNbtProfileCode(profileCodes.getDescription());
				}
			}
		}
		return returnList;
	}

	private ProfileCodes getProfileCodeDesc(final PersonProfiles personProfiles) {
		return ocuperprRepository.getProfileCodeDesc( personProfiles);
	}

	private ProfileTypes getProfileTypeDesc(final PersonProfiles personProfiles) {
		return ocuperprRepository.getProfileTypeDesc( personProfiles);
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstPROFILES
	 *
	 
	 */
	@Transactional
	public Integer profilesCommit(final PersonProfilesCommitBean commitBean) {
		int liReturn = 0;
		// updateRecords
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (PersonProfiles obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = profilesUpdatePersonProfiles(commitBean.getUpdateList());
		}
		return liReturn;
	}
	public Integer profilesUpdatePersonProfiles(final List<PersonProfiles> personProfiles){
		Integer listreturn=0;
		listreturn= ocuperprRepository.profilesUpdatePersonProfiles(personProfiles);
		return listreturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 */
	public List<ProfileCodes> rgProfileCodeRecordGroup(final String profileType) {
		final List<ProfileCodes> resultList = ocuperprRepository.rgProfileCodeRecordGroup(profileType);
		resultList.forEach(result -> {
			result.setCode(String.valueOf(result.getProfileCode()));
			result.setDescription(result.getDescription().toString());
		});
		return resultList;

	}

	@Override
	public List<PersonProfiles> insertProfilesTypes(final Integer personId,String userName) {
		return tagPersonSearchService.insertPersonProfileTypes(personId!=null?personId.longValue():null, userName);
	}

}
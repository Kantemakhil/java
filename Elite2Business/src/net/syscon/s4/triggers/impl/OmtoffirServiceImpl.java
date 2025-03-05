package net.syscon.s4.triggers.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.legalorders.OffenderCommunityFiles;
import net.syscon.s4.triggers.OmtoffirRepository;
import net.syscon.s4.triggers.OmtoffirService;

@Service
public class OmtoffirServiceImpl implements OmtoffirService {
	private final Logger logger = LogManager.getLogger(OmtoffirServiceImpl.class);
	@Autowired
	OmtoffirRepository omtoffirRepository;

	@Override
	public Integer omtoffirTgr(final List<OffenderCommunityFiles> offenderCommunityFilesList) {
		Integer result = 0;
		try {
			if (!offenderCommunityFilesList.isEmpty()) {
				OffenderCommunityFiles targetObj = new OffenderCommunityFiles();
				for (final OffenderCommunityFiles newObj : offenderCommunityFilesList) {
					// getting old record from OFFENDER_COMMUNITY_FILES
					final OffenderCommunityFiles old = omtoffirRepository.getOffenderCommunityFiles(newObj);
					if ((Optional.ofNullable(newObj).isPresent() && Optional.ofNullable(old).isPresent()
							&& (!newObj.getHoldingAgyLocId().equals(old.getHoldingAgyLocId())))
							&& ((newObj == null
									|| (Optional.ofNullable(newObj).isPresent() && newObj.getSealFlag() == null))
									|| (newObj.getSealFlag().equals(old.getSealFlag())))) {
						//Inserting record into offender_files_trig table
						result = omtoffirRepository.insertOffenderFilesTrig(newObj);
						targetObj = new OffenderCommunityFiles();
						BeanUtils.copyProperties(old, targetObj);
						targetObj.setNonOfficerStatus(newObj.getNonOfficerStatus());
						targetObj.setHoldingStaffId(newObj.getHoldingStaffId());
						targetObj.setHoldingAgyLocId(newObj.getHoldingAgyLocId());
						targetObj.setCreateUserId(newObj.getCreateUserId());
						targetObj.setCreationDate(newObj.getCreationDate());
						//Inserting record into offender_files_trig1 table
						result = omtoffirRepository.insertOffenderFilesTrig1(targetObj);
					}
				}
			}
		} catch (final Exception e) {
			result = 0;
			logger.error("omtoffirTgr", e);
		}
		return result;
	}
}

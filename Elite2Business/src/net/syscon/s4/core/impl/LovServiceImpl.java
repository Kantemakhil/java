package net.syscon.s4.core.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.core.LovRepository;
import net.syscon.s4.core.LovService;

@Service
public class LovServiceImpl implements LovService {

	@Autowired
	private LovRepository lovDao;

	@Override
	public List<ReferenceCodes> getReferenceDomainCodes(final String domain, String parent,String userId,String moduleName) throws SQLException {
		List<ReferenceCodes> refList = lovDao.getReferenceDomainCodes(domain, parent,userId,moduleName);
		if(Optional.ofNullable(refList).isPresent()) {
			refList.forEach(refcode->{
				if (ApplicationConstants.YFLAG.equals(refcode.getActiveFlag()) && ApplicationConstants.YFLAG.equals(refcode.getDomainAccess())) {
					refcode.setCanDisplay(true);
				} else {
					refcode.setCanDisplay(false);
				}
			});
		}
		return refList;
	}

}

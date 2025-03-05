package net.syscon.s4.pkgs.tag_security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.tag_security.TagSecurityRepository;
import net.syscon.s4.pkgs.tag_security.TagSecurityService;

@Service
public class TagSecurityServiceImpl implements TagSecurityService {
	@Autowired
	private TagSecurityRepository tagSecurityRepository;

	@Override
	public String getGroupPrivilege(final String moduleName) {
		return tagSecurityRepository.getGroupPrivilege(moduleName);
	}

	@Override
	public String getCaseloadId(final String userName) {
		return tagSecurityRepository.getCaseloadId(userName);
	}

	@Override
	public Boolean checkPrivilegeExists(final String roleCode,final String userName) {
		return tagSecurityRepository.checkPrivilegeExists(roleCode, userName);
	}

	@Override
	public String getGroupPrivilege(final String moduleName, final String userName) {
		return tagSecurityRepository.getGroupPrivilege(moduleName,userName);
	}
	@Override
	public String checkPrivilege(final String roleCode, final String userName) {
		return tagSecurityRepository.checkPrivilege(roleCode,userName);
	}
}
package net.syscon.s4.pkgs.oms_ir.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.oms_ir.OmsIrRepository;
import net.syscon.s4.pkgs.oms_ir.OmsIrService;

@Service
public class OmsIrServiceImpl implements OmsIrService {

	@Autowired
	private OmsIrRepository omsIrRepository;

	@Override
	public Boolean changePassword(String userId, String oldPassword, String newPassword,String loggedUserName) {
		return omsIrRepository.ChangeUserPassword(userId, oldPassword, newPassword, loggedUserName);

	}

}
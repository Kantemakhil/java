package net.syscon.s4.triggers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.triggers.AddresesTJNRepository;
import net.syscon.s4.triggers.AddresesTJNService;
@Service
public class AddresesTJNServiceImpl implements AddresesTJNService {
	@Autowired
	private AddresesTJNRepository addresesTJNRepository;
	@Override
	public Integer addressTJNTrigger(List<Addresses> lstAddresses) {
		return addresesTJNRepository.addressTJNTrigger(lstAddresses);
	}

}

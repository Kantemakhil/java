package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.Addresses;
import net.syscon.s4.triggers.AddressesTjnRepository;
import net.syscon.s4.triggers.AddressesTjnService;

@Service
public class AddressesTjnServiceImpl implements AddressesTjnService {

	@Autowired
	private AddressesTjnRepository addressesTjnRepo;

	private static final String INSERT = "INSERT";
	private static final String UPDATE = "UPDATE";
	private static final String DELETE = "DELETE";
	private static final String INS = "INS";
	private static final String UPD = "UPD";
	private static final String DEL = "DEL";

	@Override
	public void addressesTjn(final Addresses newBean, final Addresses oldBean, final String operation) {
		if (operation.equalsIgnoreCase(INSERT)) {
			newBean.setJnOperation(INS);
			newBean.setJnOracleUser(newBean.getCreateUserId());
			addressesTjnRepo.addressesTjnInsertOperation(newBean);
		} else if (operation.equalsIgnoreCase(UPDATE)) {
			oldBean.setJnOperation(UPD);
			oldBean.setJnOracleUser(oldBean.getCreateUserId());
			oldBean.setPrimaryFlag(oldBean.getPrimaryFlag().equalsIgnoreCase("true")? "Y": "N");
			oldBean.setMailFlag(oldBean.getMailFlag().equalsIgnoreCase("true")? "Y": "N");
			oldBean.setServicesFlag(oldBean.getServicesFlag().equalsIgnoreCase("true")? "Y": "N");
			addressesTjnRepo.addressesTjnInsertOperation(oldBean);
		} else if (operation.equalsIgnoreCase(DELETE)) {
			oldBean.setJnOracleUser(oldBean.getCreateUserId());
			oldBean.setJnOperation(DEL);
			addressesTjnRepo.addressesTjnInsertOperation(oldBean);
		}
	}
}

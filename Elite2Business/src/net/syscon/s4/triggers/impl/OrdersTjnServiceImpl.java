package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.Orders;
import net.syscon.s4.triggers.OrdersTjnRepository;
import net.syscon.s4.triggers.OrdersTjnService;

@Service
public class OrdersTjnServiceImpl implements OrdersTjnService {

	@Autowired
	private OrdersTjnRepository ordersTjnRepository;

	@Override
	public void ordersTjn(final Orders newRecord, final Orders oldRecord, final String operation) {
		if (operation.equalsIgnoreCase("INSERT")) {
			ordersTjnRepository.insertOrdersTjn(newRecord);
		}
		else if (operation.equalsIgnoreCase("UPDATE")) {
			ordersTjnRepository.updateOrdersTjn(oldRecord);
		}
		else if (operation.equalsIgnoreCase("DELETE")) {
			ordersTjnRepository.deleteOrdersTjn(oldRecord);
		}
	}

}

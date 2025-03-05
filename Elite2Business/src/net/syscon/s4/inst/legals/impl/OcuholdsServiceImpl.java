package net.syscon.s4.inst.legals.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.HoldsCommitBean;
import net.syscon.s4.inst.legals.OcuholdsRepository;
import net.syscon.s4.inst.legals.OcuholdsService;
import net.syscon.s4.inst.legals.beans.Court;
import net.syscon.s4.inst.legals.beans.HoldStatus;
import net.syscon.s4.inst.legals.beans.Holds;
import net.syscon.s4.inst.legals.beans.OrderType;


@Service
public class OcuholdsServiceImpl implements OcuholdsService {
	
	@Autowired
	OcuholdsRepository ocuholdsRepository;

	@Override
	public List<Holds> populateHoldsData(Integer eventId) {
		List<Holds> resultList = new ArrayList<Holds>();
		resultList = ocuholdsRepository.populateHoldsData(eventId);
		return resultList;
	}
	@Override
	public List<OrderType> orderType() {
		List<OrderType> resultList = new ArrayList<OrderType>();
		resultList = ocuholdsRepository.orderType();
		return resultList;
	}
	
	@Override
	public List<Court> populateCourtData() {
		List<Court> resultList = new ArrayList<Court>();
		resultList = ocuholdsRepository.populateCourtData();
		return resultList;
	}
	
	@Override
	public List<HoldStatus> populateHoldStatus() {
		List<HoldStatus> resultList = new ArrayList<HoldStatus>();
		resultList = ocuholdsRepository.populateHoldStatus();
		return resultList;
	}
	
	@Transactional
	public Integer updateHoldData(String userId,HoldsCommitBean holdsBeanCommit) {
		
		Integer returnRows = 0; 
		if(null!=holdsBeanCommit.getInsertList() && holdsBeanCommit.getInsertList().size()>0) {
			insertHoldData(holdsBeanCommit.getInsertList(), userId);
		}
		if(null!=holdsBeanCommit.getUpdateList() && holdsBeanCommit.getUpdateList().size()>0) {
			updateHoldData(holdsBeanCommit.getUpdateList());
		}
		if(null!=holdsBeanCommit.getDeleteList() && holdsBeanCommit.getDeleteList().size()>0) {
			for (Holds object : holdsBeanCommit.getDeleteList()) {
				object.setModifyUserId(userId);
			}
			deleteHoldData(holdsBeanCommit.getDeleteList());
		}
		int liReturn = 0;
		return  returnRows;
	}
	
	@Transactional
	private Integer insertHoldData(List<Holds> insertList, String userId) {
		Integer returnRows = 0;
		Integer calcExpDays = 0;
		Date calcExpDate = null;
		List<Holds> recordSavingObject = new ArrayList<>();
		for ( Holds newHoldsData :insertList) {
			recordSavingObject = new ArrayList<>();
			final Integer valueOrderId = getPreInsertOrderId();
			newHoldsData.setOrderId(Integer.valueOf(valueOrderId));
			newHoldsData.setCreateUserId(userId);
			newHoldsData.setModifyUserId(userId);
			calcExpDays = calcExpiryDate(newHoldsData.getOrderType());
			if (calcExpDays == null) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar c = Calendar.getInstance();
				c.setTime(newHoldsData.getOrderDate());
				c.add(Calendar.DATE, 0);
				newHoldsData.setExpiryDate(c.getTime());
			} else {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar c = Calendar.getInstance();
				c.setTime(newHoldsData.getOrderDate());
				c.add(Calendar.DATE, calcExpDays);
				newHoldsData.setExpiryDate(c.getTime());
			}
			recordSavingObject.add(newHoldsData);
		returnRows = ocuholdsRepository.insertHoldData(recordSavingObject);
		}
		return returnRows;
	} 
	
	@Transactional
	private Integer updateHoldData(List<Holds> updatetList) {
		Integer liReturn = 0;
		List<Holds> recordSavingObject = new ArrayList<>();
		for ( Holds newHoldsData :updatetList) {
			recordSavingObject = new ArrayList<>();
			//newHoldsData.setModifyUserId(userId);
			recordSavingObject.add(newHoldsData);
		liReturn = ocuholdsRepository.updateHoldData(updatetList);
		}
		return liReturn;
	} 
	
	public Integer getPreInsertOrderId(){
		return ocuholdsRepository.getPreInsertOrderId();
	}
	
	private Integer calcExpiryDate(String orderType) {
		Integer result=0;
		result = ocuholdsRepository.calcexpdate(orderType);
		return result;
	}
	
	@Transactional
	public Integer deleteHoldData(final List<Holds> deleteList) {
		return ocuholdsRepository.deleteHoldData(deleteList);
	}
	
	
	
}

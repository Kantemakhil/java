package net.syscon.s4.inst.institutionalactivities.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.inst.institutionalactivities.OcdcgpayRepository;
import net.syscon.s4.inst.institutionalactivities.OcupdetaRepository;
import net.syscon.s4.inst.institutionalactivities.OcupdetaService;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmpssetRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OffAllowPayDetails;

@Service
public class OcupdetaServiceImpl extends BaseBusiness implements OcupdetaService {

	@Autowired
	private OcupdetaRepository ocupdetaRepository;

	@Autowired
	private OcmpssetRepository ocmpssetRepository;
	
	@Autowired
	private OcdcgpayRepository ocdcgpayRepository;

	@Override
	public List<VOffenderCourseAttendances> unpaidAttendanceExecuteQuery(VOffenderCourseAttendances obj) {
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal totalAmountAllowance = BigDecimal.ZERO;
		BigDecimal singleTotalAmnt = BigDecimal.ZERO;
		List<VOffenderCourseAttendances> returnlist = new ArrayList<VOffenderCourseAttendances>();
		List<VOffenderCourseAttendances> returnlistNew =new ArrayList<VOffenderCourseAttendances>();
		if(obj.getOffAllowanceId()==null) {
			returnlist = ocupdetaRepository.unpaidAttendanceExecuteQuery(obj);	
		}
		if (returnlist.size() > 0 && obj.getOffAllowanceId()==null) {
			for (VOffenderCourseAttendances bean : returnlist) {
				List<VOffenderCourseAttendances> object = ocmpssetRepository.getSchedulePayRate(
						BigDecimal.valueOf(bean.getOffenderBookId()), Integer.valueOf(bean.getEventId().toString()),
						bean.getEventType());
				if (object.size() > 0) {
					bean.setPaySystemRate(object.get(0).getPaySystemRate());
				}
				if (bean.getAmount() != null) {
					totalAmount = totalAmount.add(bean.getPayActualAmount());
				}
			}
			returnlist.get(0).setTotalAmount(totalAmount);
		}
		if(obj.getOffAllowanceId()!=null) {	
			List<OffAllowPayDetails> allowUnpaidList=new ArrayList<OffAllowPayDetails>();			
			allowUnpaidList = ocupdetaRepository.getUnpaidAllowanceDetailsChildQry(obj);				
			for (OffAllowPayDetails offAllowPayDetails : allowUnpaidList) {
				VOffenderCourseAttendances newObj = new VOffenderCourseAttendances();
				newObj=setDataObject(offAllowPayDetails);
				if(offAllowPayDetails.getPayActualRate() != null) {
					singleTotalAmnt = BigDecimal.valueOf((offAllowPayDetails.getAllowanceVersionMaxUnit())*(offAllowPayDetails.getAllowanceVersionRate()));
					totalAmountAllowance = totalAmountAllowance.add(singleTotalAmnt);
					newObj.setPayActualAmount(singleTotalAmnt);
				}else {
					singleTotalAmnt = BigDecimal.valueOf((offAllowPayDetails.getAllowanceVersionMaxUnit())*(offAllowPayDetails.getAllowanceVersionRate()));
					totalAmountAllowance = totalAmountAllowance.add(singleTotalAmnt);
					newObj.setPayActualAmount(singleTotalAmnt);
				}	
				returnlistNew.add(newObj);
			}
			if(!returnlist.isEmpty() && !returnlistNew.isEmpty()) {
				returnlist.addAll(returnlistNew);
				if(returnlist.get(0)!=null && returnlist.get(0).getTotalAmount()!=null && totalAmountAllowance!=null) {
					returnlist.get(0).setTotalAmount(returnlist.get(0).getTotalAmount().add(totalAmountAllowance));
				} 
			} else if(!returnlistNew.isEmpty()) {
				returnlist.addAll(returnlistNew);
				returnlist.get(0).setTotalAmount(totalAmountAllowance);
			}
		}
		return returnlist;
	}

	@Override
	public Integer generatePay(List<VOffenderCourseAttendances> updateList) {
		Integer result =0;
		List<VOffenderCourseAttendances> vOffCourseAttendList = new ArrayList<VOffenderCourseAttendances>();
		List<VOffenderCourseAttendances> vOffAllowanceList = new ArrayList<VOffenderCourseAttendances>();
		for (VOffenderCourseAttendances list : updateList) {
			if(list.getType()!= null && "ALLOWANCE".equals(list.getType())) {
				vOffAllowanceList.add(list);
			}else {
				vOffCourseAttendList.add(list);
			}
		}
		if(vOffAllowanceList!= null && vOffAllowanceList.size() > 0) {
		result = ocupdetaRepository.generatePayAllow(vOffAllowanceList);
		}else {
		result = ocupdetaRepository.generatePay(vOffCourseAttendList);
		}
		return result;
	}
	
	private VOffenderCourseAttendances setDataObject(OffAllowPayDetails offAllowPayDetails) {
		VOffenderCourseAttendances newObj = new VOffenderCourseAttendances();
		newObj.setOffenderBookId(offAllowPayDetails.getOffenderBookId().longValue());
		newObj.setOffenderIdDisplay(offAllowPayDetails.getOffenderIdDisplay());
		newObj.setLastName(offAllowPayDetails.getLastName());
		newObj.setFirstName(offAllowPayDetails.getFirstName());
		newObj.setPaySystemRate(BigDecimal.valueOf(offAllowPayDetails.getAllowanceVersionRate()));
		newObj.setPayActualAmount(BigDecimal.valueOf(offAllowPayDetails.getPayActualAmount()));
		newObj.setPayActualRate(BigDecimal.valueOf(offAllowPayDetails.getAllowanceVersionRate()));
		newObj.setActivityDescription(offAllowPayDetails.getAllowanceType());
		newObj.setEventDate(offAllowPayDetails.getOffAllowanceDay());
		newObj.setPayHours(BigDecimal.valueOf(offAllowPayDetails.getAllowanceVersionMaxUnit()));
		newObj.setDetailId(offAllowPayDetails.getDetailId());
		newObj.setOffAllowanceId(offAllowPayDetails.getOffAllowanceId());
		newObj.setHiddenCommentText(offAllowPayDetails.getCommentText());
		newObj.setType("ALLOWANCE");
		return newObj;
		
	}
	
	
	@Override
	public List<VOffenderCourseAttendances> postUpdateExecuteQuery(VOffenderCourseAttendances obj) {
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal totalAmountAllowance = BigDecimal.ZERO;
		List<VOffenderCourseAttendances> returnlist = ocupdetaRepository.unpaidAttendanceExecuteQuery(obj);
		List<VOffenderCourseAttendances> returnlistNew =new ArrayList<VOffenderCourseAttendances>();
		if (returnlist.size() > 0) {
			for (VOffenderCourseAttendances bean : returnlist) {
				List<VOffenderCourseAttendances> object = ocmpssetRepository.getSchedulePayRate(
						BigDecimal.valueOf(bean.getOffenderBookId()), Integer.valueOf(bean.getEventId().toString()),
						bean.getEventType());
				if (object.size() > 0) {
					bean.setPaySystemRate(object.get(0).getPaySystemRate());
				}
				if (bean.getAmount() != null) {
					totalAmount = totalAmount.add(bean.getPayActualAmount());
				}
			}
			returnlist.get(0).setTotalAmount(totalAmount);
		}
			List<OffAllowPayDetails> allowUnpaidList=new ArrayList<OffAllowPayDetails>();			
			allowUnpaidList = ocupdetaRepository.getUnpaidGenerateDetailsPayData(obj);				
			for (OffAllowPayDetails offAllowPayDetails : allowUnpaidList) {
				VOffenderCourseAttendances newObj = new VOffenderCourseAttendances();
				newObj=setDataObject(offAllowPayDetails);
				if (offAllowPayDetails.getAllowanceVersionRate() != null) {
					totalAmountAllowance = totalAmountAllowance.add(BigDecimal.valueOf(offAllowPayDetails.getPayActualAmount()));
				}	
				returnlistNew.add(newObj);
			}
			if(!returnlist.isEmpty() && !returnlistNew.isEmpty()) {
				returnlist.addAll(returnlistNew);
				if(returnlist.get(0)!=null && returnlist.get(0).getTotalAmount()!=null && totalAmountAllowance!=null) {
					returnlist.get(0).setTotalAmount(returnlist.get(0).getTotalAmount().add(totalAmountAllowance));
				} 
			} else if(!returnlistNew.isEmpty()) {
				returnlist.addAll(returnlistNew);
				returnlist.get(0).setTotalAmount(totalAmountAllowance);
			}
		return returnlist;
	}
}

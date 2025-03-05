package net.syscon.s4.triggers.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Service;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.triggers.OffFeeBillsT2Service;
@Service
public class OffFeeBillsT2ServiceImpl implements OffFeeBillsT2Service {

	

	@Override
	public String offFeeBillsT2(OffFeeBillTransactions newBean, OffFeeBills oldBean) throws Exception {
		Date startDate = newBean.getBillArStartDate();
		
		if(startDate!= null) {
			startDate.setHours(0);
			startDate.setMinutes(0);
			startDate.setSeconds(0);
			
		}
       Date dueDate = newBean.getBillArDueDate();
 
		if(dueDate!= null) {
			dueDate.setHours(0);
			dueDate.setMinutes(0);
			dueDate.setSeconds(0);
			
		}
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		if(newBean!=null && oldBean!=null && newBean.getBillArStartDate()!=null && oldBean.getBillArStartDate()!=null && formatter.format(newBean.getBillArStartDate()).compareTo(oldBean.getBillArStartDate().toString())!=0) {
			throw new Exception(
					"Bill payment start date cannot be updated");
		}
		else if(newBean!=null && oldBean!=null && newBean.getBillArDueDate()!=null && oldBean.getBillArDueDate()!=null && formatter.format(dueDate).compareTo(oldBean.getBillArDueDate().toString())!=0) {
			 throw new Exception(
					  "Bill balance payment due date cannot be updated");	
		}

		return null;
		
	}
		
	}

	



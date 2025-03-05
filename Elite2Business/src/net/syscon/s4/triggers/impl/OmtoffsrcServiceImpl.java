package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.triggers.OmtoffsrcRepository;
import net.syscon.s4.triggers.OmtoffsrcService;
/* =========================================================
 Below comments are copied from OMTOFFSRC Trigger
 ========================================================= */
/*
   MODIFICATION HISTORY (Please put version history in a reverse-chronological order below)
   --------------------
   Person      Date         Comments
   ---------   -----------  -------------------------------------------------------------------
   Vipul       21-DEC-2004  Modified the trigger to populate the last_name_alpha_key column
   Simanta     30-JUN-2003  The trigger code has be changed to remove the redundant conditions
   Jagdeep     31-OCT-2001  Commented out the code for LPADing zeroes
*/
@Service
public class OmtoffsrcServiceImpl extends BaseBusiness implements OmtoffsrcService{
	
	@Autowired
	private OmtoffsrcRepository repository;  
	
	@Override
	public List<Offenders> omtoffsrc(List<Offenders> offenderList, String operation) {
		for(Offenders data:offenderList){
			Offenders old=repository.offendersExecuteQuery(data.getOffenderId());

			if((data != null && data.getSealFlag() == null) || (equals(data.getSealFlag(),old.getSealFlag()))) {
				if(operation.equalsIgnoreCase("INSERT") || (operation.equalsIgnoreCase("UPDATE") && !equals(data.getLastName(),old.getLastName()))) {
					data.setLastNameSoundex(data.getLastName() != null ? getSoundex(data.getLastName()):null);
					data.setLastNameKey(data.getLastName() != null ? returnKey(data.getLastName()):null);
					data.setLastNameAlphaKey(data.getLastName() != null ? data.getLastName().substring(0, 1):null);
				}			
				if(operation.equalsIgnoreCase("INSERT") || (operation.equalsIgnoreCase("UPDATE") && !equals(data.getFirstName(),old.getFirstName()))) {
					data.setFirstNameKey(data.getFirstName() != null ? returnKey(data.getFirstName()):null);
				}
	
				if(data.getMiddleName() != null && (operation.equalsIgnoreCase("INSERT") || (operation.equalsIgnoreCase("UPDATE") && !equals(data.getMiddleName(),old.getMiddleName())))) {
					data.setMiddleNameKey(data.getMiddleName() != null ?returnKey(data.getMiddleName()):null);
				}
		
				if((operation.equalsIgnoreCase("INSERT") && data.getRootOffenderId() == null) || (operation.equalsIgnoreCase("UPDATE") && old.getRootOffenderId() == null)) {
					data.setRootOffenderId(data.getAliasOffenderId() != null ?BigDecimal.valueOf(data.getAliasOffenderId()) :BigDecimal.valueOf(data.getOffenderId()));
				}
			}
	
		}
		
		return offenderList;
	}
	
	private boolean equals(String oldValue,String newValue) {
		if(oldValue == null && newValue == null) 
			return true;
		else if((oldValue != null && newValue == null) || (oldValue == null && newValue != null)) 
			return false;
		else {
			return newValue.equalsIgnoreCase(oldValue);
		}
	}
	
	private String returnKey(String pText) {
		String lText=null;
		lText=pText.replace("?ABCDEFGHIJKLMNOPQRSTUVWXYZ", "?");
		if(lText == null) {
			return pText;
		}
		lText="A"+lText;
		return pText.replace(lText, "A");
	}
	
	 private  String getSoundex(String s) {
	        char[] x = s.toUpperCase().toCharArray();
	         
	         
	        char firstLetter = x[0];
	 
	        for (int i = 0; i < x.length; i++) {
	            switch (x[i]) {
	            case 'B':
	            case 'F':
	            case 'P':
	            case 'V': {
	                x[i] = '1';
	                break;
	            }
	 
	            case 'C':
	            case 'G':
	            case 'J':
	            case 'K':
	            case 'Q':
	            case 'S':
	            case 'X':
	            case 'Z': {
	                x[i] = '2';
	                break;
	            }
	 
	            case 'D':
	            case 'T': {
	                x[i] = '3';
	                break;
	            }
	 
	            case 'L': {
	                x[i] = '4';
	                break;
	            }
	 
	            case 'M':
	            case 'N': {
	                x[i] = '5';
	                break;
	            }
	 
	            case 'R': {
	                x[i] = '6';
	                break;
	            }
	 
	            default: {
	                x[i] = '0';
	                break;
	            }
	            }
	        }
	 
	        String output = "" + firstLetter;
	         
	        for (int i = 1; i < x.length; i++)
	            if (x[i] != x[i - 1] && x[i] != '0')
	                output += x[i];
	 
	        output = output + "0000";
	        return output.substring(0, 4);
	    }
	
	
}

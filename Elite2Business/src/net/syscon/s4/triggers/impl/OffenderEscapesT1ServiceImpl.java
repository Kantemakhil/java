package net.syscon.s4.triggers.impl;

import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderEscape;
import net.syscon.s4.triggers.OffenderEscapesT1Service;
@Service
public class OffenderEscapesT1ServiceImpl implements OffenderEscapesT1Service {
	
	@Override
	public OffenderEscape offenderEscapesT1(final OffenderEscape escape) {
		if(escape.getEscapeDate()!=null && escape.getEscapeTime()!=null) {
		escape.getEscapeTime().setDate(escape.getEscapeDate().getDate());
//		escape.getEscapeTime().setTime(escape.getEscapeDate().getTime());
		}
		return escape;
		
	}

}

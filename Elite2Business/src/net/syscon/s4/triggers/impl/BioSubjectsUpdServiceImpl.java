package net.syscon.s4.triggers.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import net.syscon.s4.common.beans.BioSubjectsUpd;
import net.syscon.s4.triggers.BioSubjectsUpdService;

public class BioSubjectsUpdServiceImpl implements BioSubjectsUpdService {

	public void bioSubjectsUpd(BioSubjectsUpd oldBioSubjectsUpd,BioSubjectsUpd newBioSubjectsUpd) {
		if(Objects.nonNull(newBioSubjectsUpd.getSealFlag()) && StringUtils.equals(oldBioSubjectsUpd.getSealFlag(), newBioSubjectsUpd.getSealFlag())) {
			newBioSubjectsUpd.setClusterHash(new Random().nextInt(999999));
		}
		if(Objects.nonNull(newBioSubjectsUpd.getNtemplate())) {
			final DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			newBioSubjectsUpd.setNtemplateUpdate(formatter.format(new Date()));
		}
	}
}

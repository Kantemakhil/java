package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.TeamsT1Repository;
import net.syscon.s4.triggers.TeamsT1Service;

@Service
public class TeamsT1ServiceImpl implements TeamsT1Service {

	@Autowired
	private TeamsT1Repository teamsT1Repository;

	@Override
	public Integer teamsT1() {
		return teamsT1Repository.teamsT1();
	}

}

package net.syscon.s4.triggers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.TeamsT2Repository;
import net.syscon.s4.triggers.Teams_T2Service;

@Service
public class TeamsT2ServiceImpl implements Teams_T2Service {
	@Autowired
	private TeamsT2Repository eamsT2Repository;

	@Override
	public Integer teamsT2() {
		return eamsT2Repository.teamsT2();
	}

}

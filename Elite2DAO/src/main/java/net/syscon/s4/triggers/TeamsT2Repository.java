package net.syscon.s4.triggers;

public interface TeamsT2Repository {

	Integer teamsT2(); 
	
	Integer teamsMod(final Long teamId,final Integer lvProfileValue);
}

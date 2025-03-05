package net.syscon.s4.pkgs.api_off_observastion;

import net.syscon.s4.pkgs.ApiOffObsStaging;

public interface ApiOffObservastionRepository {
	Long lvSeq();

	Integer logging(ApiOffObsStaging apiOffObsStaging);

}

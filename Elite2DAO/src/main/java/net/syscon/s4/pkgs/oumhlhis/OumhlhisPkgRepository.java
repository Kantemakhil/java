package net.syscon.s4.pkgs.oumhlhis;

import java.util.List;

import net.syscon.s4.im.beans.AgyIntLocAmendQuery;

public interface OumhlhisPkgRepository {
	String getDescCode(String domain, String prefCode);

	List<AgyIntLocAmendQuery> agyIntLocAmendQuery(AgyIntLocAmendQuery bean, String getOrderBy);

}
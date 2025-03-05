package net.syscon.s4.pkgs.ocdclist;

import java.util.List;

import net.syscon.s4.inst.legalscreens.bean.OcdclistCourtListQuery;

public interface OcdclistPkgService {

	List<OcdclistCourtListQuery> courtListQuery(final OcdclistCourtListQuery courListQuery);

}

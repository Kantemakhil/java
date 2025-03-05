package net.syscon.s4.pkgs.oidincde;

import java.util.List;

import net.syscon.s4.im.beans.Offenders;

public interface OidincdePkgService {

	List<Offenders> getOffDetailsByBookId(final Long offenderBookId);
}

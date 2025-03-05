package net.syscon.s4.triggers;

import net.syscon.s4.inst.legals.beans.VOffenderProceedingSents;

public interface VOffenderProceedingSentsTuRepository {

	Integer delete(VOffenderProceedingSents newObj);

	Integer insert(VOffenderProceedingSents newObj);

}

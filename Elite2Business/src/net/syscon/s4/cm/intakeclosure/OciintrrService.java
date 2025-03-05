package net.syscon.s4.cm.intakeclosure;

import java.util.List;

import net.syscon.s4.common.beans.OffIntakeReviewQueue;

public interface OciintrrService {

	List<OffIntakeReviewQueue> offIntakeReiewQuExecuteQuery(final OffIntakeReviewQueue searchBean);

	Integer offIntakeRevAccept(final OffIntakeReviewQueue bean);

}

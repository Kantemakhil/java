package net.syscon.s4.pkgs.tag_exceptions;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.pkgs.TagExceptions;

public interface TagExceptionsRepository {
	Long sidCur();

	String moduleNameCur(Long sid);

	BigDecimal tagErrorId();

	String systemProfilesCur();

	Integer tagExceptionsInsert(List<TagExceptions> tagExceptionsList);

	Integer tagExceptionsInsertException(List<TagExceptions> tagExceptionsList);

}

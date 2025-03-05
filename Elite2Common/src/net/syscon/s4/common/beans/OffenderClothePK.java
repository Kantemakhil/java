package net.syscon.s4.common.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OffenderClothePK extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("offenderBookId")
	private long offenderBookId;

	@JsonProperty("clothesSeq")
	private long clothesSeq;

	/**
	 *
	 * @return
	 */
	public long getOffenderBookId() {
		return offenderBookId;
	}

	/**
	 *
	 * @param offenderBookId
	 */
	public void setOffenderBookId(long offenderBookId) {
		this.offenderBookId = offenderBookId;
	}

	/**
	 *
	 * @return
	 */
	public long getClothesSeq() {
		return clothesSeq;
	}

	/**
	 *
	 * @param clothesSeq
	 */
	public void setClothesSeq(long clothesSeq) {
		this.clothesSeq = clothesSeq;
	}

}
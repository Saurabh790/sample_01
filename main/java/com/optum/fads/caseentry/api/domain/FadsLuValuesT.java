package com.optum.fads.caseentry.api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * FADS_LU_VALUES_T entity. @author awagh
 */
@Getter
@Setter
@Data
@ToString
@NoArgsConstructor
@Entity
@Table(name = "FADS_LU_VALUES_T")
public class FadsLuValuesT implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private FadsLuValuesTPK id;
	
	@Column(name = "LU_CODE", length = 50)
	private String luCode;
	
	@Column(name = "LU_LABEL", length = 255)
	private String luLabel;
	
	@Column(name = "LU_VIEW", length = 30)
	private String luView;
	
	@Column(name = "OBJ_ID", precision = 5)
	private Integer objId;
	
	@Column(name = "LU_SEQ", precision = 5)
	private Integer luSeq;
	
	@Column(name = "LU_PARENT", length = 3)
	private String luParent;

}

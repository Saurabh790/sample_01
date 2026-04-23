package com.optum.fads.caseentry.api.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * CaLuPrmNodeT entity.  @author Anil Wagh
 */
@Getter
@Setter
@Data
@ToString
@NoArgsConstructor
@Entity
@Table(name = "CA_LU_PRM_NODE_T")
public class CaLuPrmNodeT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "NODE_CD", unique = true, nullable = false, length = 5)
	private String nodeCd;

	@Column(name = "NODE_DESC", length = 50)
	private String nodeDesc;
	
	@Column(name = "NODE_VIEW", length = 1)
	private String nodeView;
	
	@Column(name = "NODE_SEQ", precision = 5)
	private Integer nodeSeq;
	
	@Column(name = "NODE_FILTER", length = 5)
	private String nodeFilter;

}

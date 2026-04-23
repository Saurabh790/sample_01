package com.optum.fads.caseentry.api.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Provides the base persistence definition of the
 * CaLuScndNodeT entity. @author Anil Wagh
 */
@Getter
@Setter
@Data
@ToString
@NoArgsConstructor
@Entity
@Table(name = "CA_LU_SCND_NODE_T")
public class CaLuScndNodeT implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "NODE_CD", unique = true, nullable = false, length = 5)
	private String nodeCd;
	
	@Column(name = "NODE_DESC", length = 50)
	private String nodeDesc;
	
	@Column(name = "NODE_VIEW", length = 1)
	private String nodeView;
	
	@Column(name = "NODE_SEQ", nullable = false, precision = 5, scale = 0)
	private Integer nodeSeq;
	
	@Column(name = "NODE_FILTER", length = 5)
	private String nodeFilter;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "caLuScndNodeT")
	private Set<CaIntelligentKeyT> caIntelligentKeyTs = new HashSet<CaIntelligentKeyT>(0);

}
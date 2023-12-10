package com.book.book_project.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity(name="address")
@Table(name="tbl_addr")
public class AddressEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addrseq")
	@SequenceGenerator(name = "addrseq", sequenceName = "tbl_addrseq", initialValue = 1, allocationSize = 1)
	private int seqno;
	
	@Column(name="zipcode", length=10, nullable=true)
	private String zipcode;
	
	@Column(name="province", length=50, nullable=true)
	private String province;
	
	@Column(name="road", length=200, nullable=true)
	private String road;
	
	@Column(name="building", length=200, nullable=true)
	private String building;
	
	@Column(name="oldaddr", length=200, nullable=true)
	private String oldaddr;
	

}

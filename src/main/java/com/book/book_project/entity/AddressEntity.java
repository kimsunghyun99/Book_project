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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seqno;
	
	@Column(name="zipcode", length=10, nullable=false)
	private String zipcode;
	
	@Column(name="province", length=200, nullable=false)
	private String province;
	
	@Column(name="road", length=200, nullable=false)
	private String road;
	
	@Column(name="building", length=200, nullable=false)
	private String building;
	
	@Column(name="oldaddr", length=200, nullable=false)
	private String oldaddr;
	

}

package com.iu.b1.member;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "members")
public class MemberVO {
	@Id
	@NotEmpty
	private String id;
	//@Column(name = "password")
	private String pw;
	
	@Transient
	private String pw2;
	
	private String name;
	private String email;
	
	//@OneToOne(mappedBy = Join하는 Entity에 선언된 자기자신의 멤버변수명")
	@OneToOne(mappedBy = "memberVO", cascade = CascadeType.ALL)
	private MemberFilesVO memberFilesVO;

	
	
	

}

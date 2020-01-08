package com.iu.b1.member;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTest {
	
	@Autowired
	private MemberRepository memberRepository;

	@Test
	void test() {
		
		//long count = memberRepository.count();
		//boolean check = memberRepository.existsById("iu");
//		List<MemberVO> ar = memberRepository.findAll();
//		for(MemberVO memberVO:ar) {
//			System.out.println(memberVO.getId());
//		}
		
//		 Optional<MemberVO> opt = memberRepository.findById("iu5555");
//		
//		 if(opt.isPresent()) {
//		 	MemberVO memberVO= opt.get();
//		 	System.out.println(memberVO.getEmail());
//		 }else {
//			 System.out.println("No Data");
//		 }
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId("iu8");
		memberVO.setPw("iu8");
		memberVO.setName("Rename");
		
		MemberVO ar = memberRepository.findByIdAndPw("admin", "admin");
		System.out.println(ar.getName());
		
		
	}

}

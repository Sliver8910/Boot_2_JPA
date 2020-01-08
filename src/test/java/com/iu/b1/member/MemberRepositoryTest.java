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
	@Autowired
	private MemberFilesRepository memberFilesRepository;
	
	@Test
	void updateTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("ruda10");
		memberVO.setPw("ruda10");
		memberVO.setEmail("ruda10@ruda10");
		memberVO.setName("ruda10");
		
		MemberFilesVO memberFilesVO = new MemberFilesVO();
		memberFilesVO.setFnum(5);
		memberFilesVO.setFname("ruda10ReImage.jsp");
		memberFilesVO.setOname("ruda10ReOname.jsp");
		
		memberVO.setMemberFilesVO(memberFilesVO);
		memberFilesVO.setMemberVO(memberVO);
		
		memberRepository.save(memberVO);
		
	}
	
	
	//@Test
	void DeleteTest() {
		MemberVO memberVO = new MemberVO();
		
		memberRepository.deleteById("iu9");
	}
	
	//@Test
	void InsertTest() {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("ruda11");
		memberVO.setPw("ruda11");
		memberVO.setName("ruda11");
		memberVO.setEmail("ruda11@ruda11");
		MemberFilesVO memberFilesVO = new MemberFilesVO();
		memberFilesVO.setMemberVO(memberVO);
		memberFilesVO.setFname("ruda11Fname.jpg");
		memberFilesVO.setOname("ruda11Oname.jpg");
		
		memberVO.setMemberFilesVO(memberFilesVO);
		
		//memberRepository.save(memberVO);
		memberFilesRepository.save(memberFilesVO);
		
	}
	
	//@Test
	void SelectText() {
		Optional<MemberVO> opt = memberRepository.findById("iu9");
		MemberVO memberVO = opt.get();  //opt에서 memberVO를 꺼낸다
		System.out.println(memberVO.getName());
		System.out.println(memberVO.getEmail());
		System.out.println(memberVO.getMemberFilesVO().getFname());
	}

	//@Test
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
		//memberVO.setId("iu8");
		//memberVO.setPw("iu8");
	//	memberVO.setName("Rename");
		
		MemberVO ar = memberRepository.findByIdAndPw("admin", "admin");
		//System.out.println(ar.getName());
		
		
	}

}

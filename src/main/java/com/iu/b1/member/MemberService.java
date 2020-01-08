package com.iu.b1.member;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.iu.b1.util.FilePathGenerator;
import com.iu.b1.util.FileSaver;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private FilePathGenerator filePathGenerator;
	@Autowired
	private FileSaver fileSaver;
	
	
	public boolean memberIdCheck(String id)throws Exception{
		return memberRepository.existsById(id);
		
	}
	
	public MemberVO memberLogin(MemberVO memberVO)throws Exception{
		return memberRepository.findByIdAndPw(memberVO.getId(), memberVO.getPw());
	}
	
	public void memberJoin(MemberVO memberVO, MultipartFile files)throws Exception{
		File file = filePathGenerator.getUseClasspathResource("upload");
		
		String fileName = fileSaver.save(file, files);
		System.out.println(fileName);
		
		
		
		MemberFilesVO memberFilesVO = new MemberFilesVO();
		
		memberFilesVO.setFname(fileName);
		memberFilesVO.setOname(files.getOriginalFilename());
		memberVO.setMemberFilesVO(memberFilesVO);
		memberFilesVO.setMemberVO(memberVO);
		
		memberRepository.save(memberVO);
		
		
	}
	
	public boolean memberJoinValidate(MemberVO memberVO, BindingResult bindingResult)throws Exception{
		boolean check=false;//true -> error, false-> ok
		
		//annotation  검증 결과
		if(bindingResult.hasErrors()) {
			check=true;
		}
		
		//pw가 일치 하는지 검증
		if(!memberVO.getPw().equals(memberVO.getPw2())) {
			check=true;
			bindingResult.rejectValue("pw2", "memberVO.pw.notEqual");
									 //form의 path명, properties의 키 	
		}
		
		
		Boolean ch = memberRepository.existsById(memberVO.getId());
		
		if(ch) {
			check = true;
			bindingResult.rejectValue("id", "Duplicate Id", "Duplicate Id");
		}
		
	
		
		return check;
	}
	
	public int	memberDelete(MemberVO memberVO)throws Exception{
		memberRepository.deleteById(memberVO.getId());
		
		int result = memberDelete(memberVO);
		
		return result;
	}
	
	public void memberUpdate(MemberVO memberVO, MultipartFile files)throws Exception{
		MemberFilesVO memberFilesVO = null;
		if(files.getSize()>0) {
			
			File file = filePathGenerator.getUseClasspathResource("upload");
			String fileName = fileSaver.save(file, files);
			memberFilesVO = memberVO.getMemberFilesVO();
			memberFilesVO.setFname(fileName);
			memberFilesVO.setOname(files.getOriginalFilename());
			memberVO.setMemberFilesVO(memberFilesVO);
			memberFilesVO.setMemberVO(memberVO);
		}else {
			memberVO.setMemberFilesVO(null);
		}
		
		memberRepository.save(memberVO);
	}

}

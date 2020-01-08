package com.iu.b1.util;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class FilePathGenerator {
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private ResourceLoader resourceLoader;
	
	public File getUseClasspathResource(String filePath) throws Exception{
	String path = "/static";
	ClassPathResource classPathResource = new ClassPathResource(path);
	File file = new File(classPathResource.getFile(), filePath);
	
	if(!file.exists()) {
		file.mkdirs();
	}
	
	System.out.println(file.getAbsolutePath());
	return file;
	
	}
	
	public String getUseResourceLoader(String filePath) throws Exception{
		String path = "classpath:/static/";
							//classes:/static                           , upload
		File file = new File(resourceLoader.getResource(path).getFile(), filePath);
		
		//rescourceLoader는 classpath 경로를 받아오기 위해 사용
		// 생성하려는 폴더가 없으면 에러 발생
		//static 경로를 이용해서 file 객체 생성하고
		//하위 폴더를 Child를 사용해서 폴더 생성
		
		if(!file.exists()) {
			file.mkdirs(); //파일이 존재하지 않으면 경로 생성
		}
		System.out.println(file.getAbsolutePath()+"/upload");
		return "";
	}
	
	public File getUseServletContext(String filePath) {
		String files= servletContext.getRealPath(filePath);
		File file = new File(files);
		if(!file.exists()) {
			file.mkdirs(); //파일이 존재하지 않으면 경로 생성
		}
		return file;
	}

}

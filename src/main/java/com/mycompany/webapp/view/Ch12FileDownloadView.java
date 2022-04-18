package com.mycompany.webapp.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class Ch12FileDownloadView extends AbstractView{
	
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		log.info("실행");
		String fileName = (String) model.get("fileName");
		String userAgent = (String) model.get("userAgent");
		
		//DB에서 가져올 정보
		String contentType = request.getServletContext().getMimeType(fileName);//파일 확장자명 알아냄
		String originalFilename = fileName;
		String saveFiename = fileName;
		
		//응답 내용의 데이터 타입을 응답 헤더에 추가
		response.setContentType(contentType);
		//response.setHeader("Content-Type", contentType);//두가지 다 동일
		
		
		//다운로드할 파일명을 헤더에 추가 [Trident : 익스플로어 11 / MSIE : 익스플로어 10 이하 ]
		if(userAgent.contains("Trident") ||userAgent.contains("MSIE")) {
			//IE 브라우저일 경우
			//한글이 있을경우 UTF-8로 변경하여 바이트로 전송해야함
			originalFilename = URLEncoder.encode(originalFilename,"UTF-8");
		}else {
			//크롬, 엣지, 사파리
			//파일을  UTF-8 형식으로 바이트 배열로 만들어서 다시 ISO-8859-1 문자셋으로 표현
			originalFilename = new String(originalFilename.getBytes("UTF-8"),"ISO-8859-1");
		}
		
		//파일을 첨부해 다운로드 하도록 설정
		response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\"");
		
		//파일 데이터를 응답 본문에 실기
		File file = new File("C:/Temp/uploadfiles/" + saveFiename);
		if(file.exists()) {
			//파일 또는 바이너리 데이터를 응답 본문에 싣기
//			InputStream is = new FileInputStream(file);
//			OutputStream os = new FileOutputStream(file);
//			FileCopyUtils.copy( is, os);
//			os.flush();
//			is.close();
//			os.close();
			FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
		}
		
	}

}

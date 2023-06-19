package com.myweb.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.FileVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;

// @Component : 작성한 Class를 Bean으로 등록하기 위한 Annotatino
@Component
@Slf4j
@AllArgsConstructor
public class FileHandler {
	
	private final String UP_DIR = "E:\\_spring_workspace\\_myweb\\_java\\fileUpload";
	
	public List<FileVO> uploadFiles(MultipartFile[] files) {
		LocalDate date = LocalDate.now();
		String today = date.toString();		// Date 객체를 String으로 변환
		log.info("date : " + date);
		log.info("today : " + today);
		
		today = today.replace("-", File.separator);
		log.info("today.replace : " + today);
		
		File folders = new File(UP_DIR, today);
		
		if (!folders.exists()) {
			folders.mkdirs();		// 폴더 생성 명령어
		}
		
		List<FileVO> listFvo = new ArrayList<FileVO>();
		
		for (MultipartFile file :  files) {
			FileVO fvo = new FileVO();
			
			// 경로가 포함되어있을 수도 있는 파일명
			String originalFilename = file.getOriginalFilename();		
			String onlyFileName = originalFilename.substring(
					originalFilename.lastIndexOf(File.separator) + 1);
			log.info("originalFilename : " + originalFilename);
			log.info("onlyFileName : " + onlyFileName);

			UUID uuid = UUID.randomUUID();
			
			fvo.setSave_dir(today);
			fvo.setFile_size(file.getSize());
			fvo.setFile_name(onlyFileName);
			fvo.setUuid(uuid.toString());
			
			String fullFileName = uuid.toString() + "_" + onlyFileName;
			File storeFile = new File(folders, fullFileName);
			
			try {
				file.transferTo(storeFile);		// 원본 객체를 저장을 위한 형태의 객체로 복사
				
				// 이미지 파일 이라면 썸네일 생성
				if (isImageFile(storeFile)) {
					fvo.setFile_type(1);
					File thumbNail = new File(folders, uuid.toString() + "_th_" + onlyFileName);
					Thumbnails.of(storeFile).size(75, 75).toFile(thumbNail);
				}
			} catch (Exception e) {
				log.info("uploadFiles() : 파일 생성 오류");
				e.printStackTrace();
			}
			
			listFvo.add(fvo);
		}
		 
		return listFvo;
	}

	private boolean isImageFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile);
		
		return mimeType.startsWith("image") ? true : false;
	}
	
}

package com.myweb.www.handler;
import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.myweb.www.domain.FileVO;
import com.myweb.www.repository.FileDAO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class FileSweeper {
	private final String BASE_PATH = "C:\\_javaweb\\_java\\fileUpload\\";
	
	@Inject
	private FileDAO fdao;
	
	// 초 분 시 일 월 요일 년도(생략가능)
	@Scheduled(cron = "1 16 21 * * *")
	public void fileSweeper() throws Exception {
		log.info(">>> FileSweeper Running Start : {}", LocalDateTime.now());
		
		// DB에 등록된 파일 목록 가져오기
		List<FileVO> dbFiles= fdao.selectAllFileList();
		
		// 저장소를 검색할 때 필요한 파일 경로 리스트 (실제 존재해야 될 리스트)
		List<String> currFiles = new ArrayList<String>();
		
		for (FileVO fvo : dbFiles) {
			String filePath = fvo.getSave_dir() + "\\" + fvo.getUuid();
			String fileName = fvo.getFile_name();
			currFiles.add(BASE_PATH + filePath + "_" + fileName);
			
			// 이미지 파일일 경우 썸네일 파일 경로도 추가해줘야 함
			if(fvo.getFile_type() > 0) {
				currFiles.add(BASE_PATH + filePath + "_th_" + fileName);
			}
		}
		
		// 날짜를 반영한 폴더 구조 경로 만들기
		LocalDate now = LocalDate.now();
		String today = now.toString();
		today = today.replace("-", File.separator);
		
		// 경로를 기반으로 저장되어 있는 파일 검색
		File dir = Paths.get(BASE_PATH + today).toFile();
		File[] allFileObjects = dir.listFiles();
		
		// 실제 저장되어 있는 파일과 DB에 기반하여 존재해야되는 파일을 비교하여 없으면 삭제 진행
		for (File file : allFileObjects) {
			String storedFileName = file.toPath().toString();
			if(!currFiles.contains(storedFileName)) {
				file.delete();
				log.info(">>> delete > {}", storedFileName);
			}
		}
		log.info(">>> FileSweeper Running Finish : {}", LocalDateTime.now());
	}
}
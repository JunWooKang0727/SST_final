package org.sst.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.sst.domain.StudyDataVO;
import org.sst.service.StudyDataService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/studydata/*")
@Log4j
public class StudyDataController {
	
	@Setter(onMethod_=@Autowired)
	private StudyDataService service;
	
	
	@GetMapping("/list2")
	public void goList(){
		log.info("r");
	}
	
	
	@PostMapping("/uploadAjax")
	public void uploadAjaxPost(MultipartFile[] uploadFile){
		
		log.info("upate ajax post...........................");
		
		String uploadFolder = "E:\\upload\\1";
		
		for(MultipartFile multipartFile : uploadFile){
			
			StudyDataVO studyData = new StudyDataVO();
			
			log.info("=====================================");
			log.info("upload File Name : "+multipartFile.getOriginalFilename());
			
			String uploadFileName = multipartFile.getOriginalFilename();
			log.info(uploadFileName+"수정전 =========");
			//iE
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			
			log.info(uploadFileName+"IE수정후 =========");
			File saveFile = new File(uploadFolder, uploadFileName);
			
			//uuid create
			UUID uuid = UUID.randomUUID();
			
			studyData.setFileName(uploadFileName);
			studyData.setG_num("1");
			studyData.setUploader("solkang");
			studyData.setUploadPath("1");
			studyData.setFileType(true);
			studyData.setUuid(uuid.toString());
			
			service.upload(studyData);
			
			try {
				multipartFile.transferTo(saveFile);
			} catch(Exception e){
				log.error(e.getMessage());
			}
			
			
			
		}// end for : multipartFile
	}// end uploadAjaxPost
	
	
	@GetMapping(value="/list",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<StudyDataVO>> getFileList(String g_num){
		
		service.getList(g_num, "");
		
		return null;
	}

}

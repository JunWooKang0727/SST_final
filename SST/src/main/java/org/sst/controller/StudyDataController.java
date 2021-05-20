package org.sst.controller;


import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.sst.domain.StudyDataListVO;
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
		log.info("go list");
	}
	
	@PostMapping("/create")
	public String makeDir(String curDir, String dirName){
		
		String uploadFolder = "E:\\upload\\1";
		
//		if(dirName.equals("")){
//			return "redirect:/studydata/list2";
//		}
		
		UUID uuid = UUID.randomUUID();
		String uuidFile =uuid.toString()+"_"+dirName;
		File dir = new File(uploadFolder, uuidFile);
		dir.mkdir();
		StudyDataVO vo = new StudyDataVO();
		
		vo.setFileName(dirName);
		vo.setFileType(false);
		vo.setG_num("1"); //여기에 그룹 넘버
		vo.setUploader("solkang");
		vo.setUploadPath("1"); //여기에 현재 경로
		vo.setUuid(uuid.toString());
		
		service.upload(vo);
		
		return "redirect:/studydata/list2";
	};
	
	
	
	@PostMapping("/uploadAjax")
	public ResponseEntity<String> uploadAjaxPost(MultipartFile[] uploadFile){
		
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
			
			
			
			studyData.setFileName(uploadFileName);
			//uuid create
			UUID uuid = UUID.randomUUID();

			uploadFileName = uuid.toString() + "_" + uploadFileName;
			File saveFile = new File(uploadFolder, uploadFileName);
			
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
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}// end uploadAjaxPost
	
	
	@GetMapping(value="/list",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<StudyDataVO>> getFileList(StudyDataListVO vo){
		log.info(vo.getCurPath());
		return new ResponseEntity<List<StudyDataVO>>(service.getList(vo), HttpStatus.OK);

	}//end getFileList 123
	
	
	@GetMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent")String userAgent,String fileName){
		
		Resource resource = new FileSystemResource("e:\\upload\\"+fileName);
		
		log.info(resource);
		
		String resourceName = resource.getFilename();
		
		if(resource.exists()==false){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		//uuid 제거
		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_")+1);
		
		HttpHeaders headers = new HttpHeaders();
		try {
			
			String downloadName = null;
			
			if(userAgent.contains("Trident")){
				log.info("IE browser");
				downloadName = URLEncoder.encode(resourceOriginalName,"UTF-8").replaceAll("\\+", " ");
			}else if(userAgent.contains("Edge")){
				log.info("Edge browser");
				downloadName = URLEncoder.encode(resourceOriginalName,"UTF-8");
			}else{
				log.info("Chrome browser");
				downloadName = new String(resourceOriginalName.getBytes("UTF-8"),
						"ISO-8859-1");
			}
			
			
			headers.add("Content-Disposition", "attachment; filename="+downloadName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers,HttpStatus.OK);
	}
	
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String uuid){
		
		log.info("deleteFile: "+fileName);
		
		File file;
		
		try {
			file = new File("e:\\upload\\"+URLDecoder.decode(fileName,"UTF-8"));
			
			file.delete();
			service.delete(uuid);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	


}

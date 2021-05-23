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
import org.springframework.ui.Model;
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
	public void goList(StudyDataListVO studyDataList, Model model){
		log.info("go list");
		
		studyDataList.setG_num("1"); //임의로 1로 지정 //그룹 번호를 그룹페이지 들어갈 때 가져와야함
		studyDataList.setCurPath("\\"+studyDataList.getG_num()); // 임의로 1로 지정 //그룹번호로 되어야함
		log.info("file group number : "+studyDataList.getG_num());
		log.info(studyDataList);
		model.addAttribute("studyDataList",	 studyDataList);
		
	}
	
	//새 폴더 생성
	@PostMapping("/create")
	public String makeDir(StudyDataListVO listvo, String dirName){
		
		String uploadFolder = "D:\\upload";
		
//		if(dirName.equals("")){
//			return "redirect:/studydata/list2";
//		}

		if(listvo.getCurPath() == null){
			listvo.setCurPath("\\"+listvo.getG_num());
		}
		
		String curPath = listvo.getCurPath();
		uploadFolder = uploadFolder + curPath;
		UUID uuid = UUID.randomUUID();
		String uuidFile =uuid.toString()+"_"+dirName;
		File dir = new File(uploadFolder, uuidFile);
		dir.mkdir();
		
		StudyDataVO vo = new StudyDataVO();
		log.info("new folder in "+curPath);
		vo.setFileName(dirName);
		vo.setFileType(false);
		vo.setG_num(listvo.getG_num()); //여기에 그룹 넘버
		vo.setUploader("solkang");
		vo.setUploadPath(uploadFolder.replace("D:\\upload", ""));
		vo.setUuid(uuid.toString());
		//log.info(vo);
		service.upload(vo);
		
		return "redirect:/studydata/list2";
	};
	
	
	
	@PostMapping("/uploadAjax")
	public ResponseEntity<String> uploadAjaxPost(MultipartFile[] uploadFile,StudyDataListVO vo){
		
		log.info("upate ajax post...........................");
		
		String uploadFolder = "D:\\upload\\1";
		
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
	//	StudyDataListVO vo2 = new StudyDataListVO();
		//vo2.setCurPath("1\\new"); // DB에 있는 문자열
		
		//(서비스에서 처리해줘야 할 것) 다시 정규표현식이용 가능

//		if(vo.getCurPath() == null){
//			vo.setCurPath("1");
//		}
		
		String regPath = vo.getCurPath().replace("\\", "\\\\");
		regPath = regPath + "$";
		//vo.setCurPath("^1");
		vo.setCurPath(regPath);
		//vo2.setG_num("1");
		log.info("새로만든거"+vo.getCurPath());
		return new ResponseEntity<List<StudyDataVO>>(service.getList(vo), HttpStatus.OK);

	}//end getFileList 123
	
	
	@GetMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent")String userAgent,String fileName){
		
		Resource resource = new FileSystemResource("D:\\upload\\"+fileName);
		
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
			file = new File("D:\\upload\\"+URLDecoder.decode(fileName,"UTF-8"));
			
			file.delete();
			service.delete(uuid);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	


}

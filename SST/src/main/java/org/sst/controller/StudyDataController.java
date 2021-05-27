package org.sst.controller;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Principal;
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
		
		//studyDataList.setG_num("1"); //임의로 1로 지정 //그룹 번호를 그룹페이지 들어갈 때 가져와야함
		studyDataList.setCurPath("\\"+studyDataList.getG_num()); // 임의로 1로 지정 //그룹번호로 되어야함
		log.info("file group number : "+studyDataList.getG_num());
		log.info(studyDataList);
		model.addAttribute("studyDataList",	 studyDataList);
		
	}
	
	//새 폴더 생성
	@PostMapping("/create")
	@ResponseBody
	public String makeDir(StudyDataListVO listvo, String dirName){
		
		String uploadFolder = "E:\\upload";
		log.info("create directory");
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
		File dir = new File(uploadFolder, dirName);
		dir.mkdirs();
		
		StudyDataVO vo = new StudyDataVO();
		log.info("create folder "+dirName);
		vo.setFileName(dirName);
		vo.setFileType(false);
		vo.setG_num(listvo.getG_num()); //여기에 그룹 넘버
		vo.setUploader("solkang");
		vo.setUploadPath(uploadFolder.replace("E:\\upload", ""));
		vo.setUuid(uuid.toString());
		//log.info(vo);1
		service.upload(vo);
		
		return "redirect:/studydata/list2";
	};
	
	
	
	@PostMapping("/upload")
	@ResponseBody
	public ResponseEntity<String> uploadAjaxPost(MultipartFile[] uploadFile,StudyDataListVO vo,Principal principal){
		
		log.info("upate ajax post...........................");
		
		String uploadFolder = "E:\\upload";
		
		String curPath = vo.getCurPath();
		String id = principal.getName();
		
	
		uploadFolder=uploadFolder + curPath;
		
		
		
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
			
			studyData.setG_num(vo.getG_num());
			studyData.setUploader(id);
			studyData.setUploadPath(curPath);
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
		//log.info("새로만든거"+vo.getCurPath());
		return new ResponseEntity<List<StudyDataVO>>(service.getList(vo), HttpStatus.OK);

	}//end getFileList 123
	
	
	@GetMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent")String userAgent,String fileName){
		
		Resource resource = new FileSystemResource("E:\\upload\\"+fileName);
		
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
	public ResponseEntity<String> deleteFile(String fileCallPath, StudyDataVO vo){
		
		log.info("deleteFile: "+vo);
		File file;
		
		if(vo.isFileType()){
			try {
				log.info("파일 타입 : ");
				file = new File("E:\\upload\\"+URLDecoder.decode(fileCallPath,"UTF-8"));
				if(file.isDirectory()){
					log.info("================ 디렉토리 입니다 =================");
				}else{
					
				}
				file.delete();
				service.delete(vo.getUuid());
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}else if(!vo.isFileType()){
			try {
				log.info("디렉토리 타입 : ");
				file = new File("E:\\upload"+vo.getUploadPath()+"\\"+vo.getFileName());
				if(file.isDirectory()){
					
					StudyDataListVO listvo = new StudyDataListVO();
					listvo.setG_num(vo.getG_num());
					String regExp = vo.getUploadPath()+"\\"+vo.getFileName();
					regExp = regExp.replace("\\", "\\\\");
					listvo.setCurPath(regExp);
					
					List<StudyDataVO> childFiles = service.getList(listvo);
					
					//경로안에 파일 모두 삭제
					deleteFilesRecursively(file);
					
					//DB상의 로우 모두 삭제
					for(StudyDataVO stddata : childFiles){
						service.delete(stddata.getUuid());
					}
					
				}else{
					log.info("=======아닙니다");
				}
				
				//file.delete();
				service.delete(vo.getUuid());
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
		
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	
    public boolean deleteFilesRecursively(File rootFile) {
        File[] allFiles = rootFile.listFiles();
        if (allFiles != null) {
            for (File file : allFiles) {
                deleteFilesRecursively(file);
            }
        }
        System.out.println("Remove file: " + rootFile.getPath());
        return rootFile.delete();

    }

}

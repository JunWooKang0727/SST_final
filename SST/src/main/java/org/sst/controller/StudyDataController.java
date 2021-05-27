package org.sst.controller;


import java.io.File;
import java.net.URLDecoder;
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
		dir.mkdir();
		
		StudyDataVO vo = new StudyDataVO();
		log.info("create folder "+dirName);
		vo.setFileName(dirName);
		vo.setFileType(false);
		vo.setG_num(listvo.getG_num()); //여기에 그룹 넘버
		vo.setUploader("solkang");
		vo.setUploadPath(uploadFolder.replace("E:\\upload", ""));
		vo.setUuid(uuid.toString());
		//log.info(vo);
		service.upload(vo);
		
		return "redirect:/studydata/list2";
	};
	

	
	@ResponseBody
	public ResponseEntity<String> uploadAjaxPost(MultipartFile[] uploadFile,StudyDataListVO vo){

		
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
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}// end uploadAjaxPost
	
	
	@GetMapping(value="/list",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<StudyDataVO>> getFileList(String g_num){


		return new ResponseEntity<List<StudyDataVO>>(service.getList(g_num, ""), HttpStatus.OK);

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

	}//end getFileList
	
	
	@GetMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String fileName){
		
		Resource resource = new FileSystemResource("e:\\upload\\1\\"+fileName);
		
		log.info(resource);
		
		String resourceName = resource.getFilename();
		
		HttpHeaders headers = new HttpHeaders();
		try {
			headers.add("Content-Disposition", "attachment; filename="+new String(resourceName.getBytes("UTF-8"),
					"ISO-8859-1"));
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

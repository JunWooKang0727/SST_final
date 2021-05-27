package org.sst.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.sst.domain.WanoteAttachVO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/wanoteAttach/*")
public class WanoteUploadController {
	@GetMapping("/uploadForm")
	public void uploadForm(){
		log.info("uploadForm");
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax(){
		log.info("uploadAjax");
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model){
		for(MultipartFile multipartFile: uploadFile){
			String uploadFolder="D:\\upload";
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+uploadFolder+ multipartFile.getOriginalFilename());
			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	//오늘 날짜 폴더 생성
	private String getFolder(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}
	//이미지 파일인지 검사
	private boolean checkImageType(File file){
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@PostMapping(value="/uploadAjaxAction",
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<WanoteAttachVO>> uploadAjaxPost(MultipartFile[] uploadFile, Model model){
		List<WanoteAttachVO> list = new ArrayList<>();
		String uploadFolder="D:\\upload";
		String uploadFolderPath = getFolder();
		File uploadPath = new File(uploadFolder,uploadFolderPath);
		
		//make Folder
		if(uploadPath.exists() == false){
			uploadPath.mkdirs();
		}
	
		for(MultipartFile multipartFile: uploadFile){
			WanoteAttachVO attachDTO = new WanoteAttachVO();
			String uploadFileName = multipartFile.getOriginalFilename();
			
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			
			attachDTO.setFileName(uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString()+"_"+uploadFileName;
			
			
			try {
				//File saveFile = new File(uploadFolder, uploadFileName);
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				attachDTO.setUploadPath(uploadFolderPath);
				attachDTO.setUuid(uuid.toString());
				
				//이미지 파일인지 검사해서 이미지 파일이면 섬네일 생성
				if(checkImageType(saveFile)){
					attachDTO.setImage(true);;
				}
				
				list.add(attachDTO);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} //end try-catch
		} //end for
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName) {
		File file = new File("d:\\upload\\" + fileName);
		ResponseEntity<byte[]> result = null;

		try {
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName) {

		Resource resource = new FileSystemResource("d:\\upload\\" + fileName);

		if (resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		String resourceName = resource.getFilename();

		// UUID 제거
		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);

		HttpHeaders headers = new HttpHeaders();
		try {

			boolean checkIE = (userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1);

			String downloadName = null;

			if (checkIE) {
				downloadName = URLEncoder.encode(resourceOriginalName, "UTF8")
				                         .replaceAll("\\+", "");
			} else {
				downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
			}
       
			headers.add("Content-Disposition", "attachment; filename=" + downloadName);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	@PostMapping(value="/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName){
		
		File file;
		try {
			file = new File("d:\\upload\\" +URLDecoder.decode(fileName, "UTF8"));
			log.info("------------------------------------------------------------뭐가 문제야 제발..."+file);
			log.info(file.delete());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("deleted",HttpStatus.OK);
	}
}

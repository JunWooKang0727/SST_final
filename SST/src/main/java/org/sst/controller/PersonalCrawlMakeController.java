package org.sst.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

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
import org.sst.domain.PersonalCrawlerVO;
import org.sst.domain.PersonalMakeVO;
import org.sst.domain.StudyDataVO;
import org.sst.service.PersonalCrawlMakeService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/personalcrawlmake/*")
@AllArgsConstructor
public class PersonalCrawlMakeController {
	
	private PersonalCrawlMakeService service;
	
	@GetMapping(value="/create",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<String>> create(PersonalCrawlerVO pcvo,PersonalMakeVO pmvo) throws IOException{
		List<String> list = service.makePdf(pcvo, pmvo);
		System.out.println(pcvo.toString()+"받아온 크롤값입니다.");
		System.out.println(pmvo.toString()+"받아온 문제값입니다.");
		return new ResponseEntity<List<String>>(list, HttpStatus.OK);
	}
	@GetMapping(value="/createImage",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<String>> createImage(PersonalCrawlerVO pcvo,PersonalMakeVO pmvo) throws Exception{
		List<String> list = service.makePdfForImage(pcvo, pmvo);
		return new ResponseEntity<List<String>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent")String userAgent,String fileName){
		log.info("+++++++++++++++++++++++"+fileName);
		Resource resource = new FileSystemResource("C:\\upload\\new\\"+fileName);
		
		log.info(resource);
		
		String resourceName = resource.getFilename();
		
		if(resource.exists()==false){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		//uuid 제거
		//String resourceOriginalName = resourceName.substring(resourceName.indexOf("_")+1);
		String resourceOriginalName = resourceName;
		
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
	
}

package org.sst.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sst.service.ReportCardService;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/scoreanalysis/")
@RestController
@Log4j
@AllArgsConstructor
public class ScoreAnalysisController {
	private ReportCardService service;
	
//	@GetMapping(value = "/pages/{bno}/{page}", produces = { MediaType.APPLICATION_XML_VALUE,
//			MediaType.APPLICATION_JSON_UTF8_VALUE })
//	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page, @PathVariable("bno") Long bno) {
//		
//		return new ResponseEntity<>(service.getListPage(cri, bno), HttpStatus.OK);
//	}

}

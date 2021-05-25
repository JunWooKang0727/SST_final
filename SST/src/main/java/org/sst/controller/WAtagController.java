package org.sst.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sst.domain.WAtagVO;
import org.sst.service.WANoteService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/watag/")
@RestController
@Log4j
@AllArgsConstructor
public class WAtagController {
	private WANoteService service;
	
	@GetMapping(value = "/listAllTag/{keyword}", produces = { 
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<List<WAtagVO>> listAllTag(@PathVariable("keyword") String keyword) {
		return new ResponseEntity<>(service.listAllTag(keyword), HttpStatus.OK);
	}

}

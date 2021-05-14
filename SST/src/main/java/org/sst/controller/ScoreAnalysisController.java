package org.sst.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sst.service.ReportCardService;
import org.sst.service.ScoreAnalysisService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/scoreanalysis/")
@RestController
@Log4j
@AllArgsConstructor
public class ScoreAnalysisController {
	private ScoreAnalysisService service;
	
	@GetMapping(value = "/schoolallscore/{rc_num}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<HashMap> getSchoolAllScore(@PathVariable("rc_num") String rc_num) {
		return new ResponseEntity<>(service.allSubjectScoreSchoolTest(rc_num), HttpStatus.OK);
	}
	
	@GetMapping(value = "/schoolaverage/{rc_num}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<HashMap> getSchoolAverage(@PathVariable("rc_num") String rc_num) {
		
		return new ResponseEntity<>(service.averageSchoolTest(rc_num), HttpStatus.OK);
	}
	
	@GetMapping(value = "/licenseallscore/{rc_num}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<HashMap> getLicenseAllScore(@PathVariable("rc_num") String rc_num) {
		return new ResponseEntity<>(service.allSubjectScoreLicenseTest(rc_num), HttpStatus.OK);
	}

}

package org.sst.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.sst.domain.Criteria;
import org.sst.domain.ReplyPageDTO;
import org.sst.domain.WANoteReplyVO;
import org.sst.service.WANoteReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/wanotereply/")
@RestController
@Log4j
@AllArgsConstructor
public class WANoteReplyController {

	private WANoteReplyService service;
	
	@GetMapping(value = "/pages/{w_num}/{page}", produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ReplyPageDTO> getList(@PathVariable("page") int page, @PathVariable("w_num") String w_num) {
		Criteria cri = new Criteria(page, 10);
		log.info("111111111111111111"+service.listWANoteReply(cri, w_num));
		ReplyPageDTO dto = new ReplyPageDTO(service.countWANoteReply(w_num), service.listWANoteReply(cri, w_num));
		return new ResponseEntity<ReplyPageDTO>(dto, HttpStatus.OK);
	}
	
	@PostMapping(value = "/create", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> create(@RequestBody WANoteReplyVO vo) {
		return service.createWANoteReply(vo) ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value = "/{wr_num}", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> remove(@PathVariable("wr_num") String wr_num) {
		return service.deleteWANoteReply(wr_num) ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(method = { RequestMethod.PUT,
			RequestMethod.PATCH }, value = "/{wr_num}", consumes = "application/json", produces = {
					MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> modify(@RequestBody WANoteReplyVO vo, @PathVariable("wr_num") String wr_num) {
		vo.setWr_num(wr_num);;
		return service.updateWANoteReply(vo) ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

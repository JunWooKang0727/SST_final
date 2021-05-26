package org.sst.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.sst.domain.Criteria;
import org.sst.domain.QuestionReplyPageVO;
import org.sst.domain.QuestionReplyVO;
import org.sst.service.QuestionReplyService;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies/")
@RestController
@Log4j
@AllArgsConstructor
public class QuestionReplyController {
	private QuestionReplyService service;
	
	@PostMapping(value = "/new",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody QuestionReplyVO vo){
		log.info("ReplyVO: "+vo);
		
		int insertCount = service.register(vo);
		
		log.info("Reply INSERT COUNT: " +insertCount);
		
		return insertCount ==1
				? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/pages/{bno}/{page}",
			produces = {
					MediaType.APPLICATION_JSON_UTF8_VALUE,
					MediaType.APPLICATION_XML_VALUE
			})
	public ResponseEntity<QuestionReplyPageVO> getList(
			@PathVariable("page") int page,
			@PathVariable("bno") String q_num){
		Criteria cri = new Criteria(page,10);

		log.info("getList bno :"+ q_num);
		log.info(cri);
		
		return new ResponseEntity<>(service.getListPage(cri, q_num),HttpStatus.OK);
	}
	
	@GetMapping(value = "/{rno}",
			produces = {
					MediaType.APPLICATION_JSON_UTF8_VALUE,
					MediaType.APPLICATION_XML_VALUE
			})
	public ResponseEntity<QuestionReplyVO> get(@PathVariable("rno") String rno){
		log.info("get:" + rno);
		
		return new ResponseEntity<>(service.get(rno) , HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{rno}",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") String rno){
		log.info("remove"+rno);
		
		return service.remove(rno)==1
				? new ResponseEntity<>("success",HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
			value = "/{rno}",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(
			@PathVariable("rno") String rno,
			@RequestBody QuestionReplyVO vo){
				vo.setRno(rno);
				log.info("rno: "+rno);
				log.info("modify: "+vo);
				
				return service.modify(vo)==1
						? new ResponseEntity<>("success",HttpStatus.OK)
						: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
 	//댓ㄹ!!!!!!!!!!!!!!글추천!!!!!!!!!!!
	
	@RequestMapping(value = "/likeCnt/{rno}", method = RequestMethod.POST)
	@ResponseBody
	public String likeCnt(
	        @RequestBody String filterJSON,
	        HttpServletResponse response,
	        HttpServletRequest request,
	        ModelMap model ) throws Exception {
	    
	    //LoginVO loginVO = loginService.getLoginInfo();
	    
	    JsonObject resMap = new JsonObject();
	        
	    try{
	        ObjectMapper mapper = new ObjectMapper();
	        
	        QuestionReplyVO searchVO = (QuestionReplyVO)mapper.readValue(filterJSON,new TypeReference<QuestionReplyVO>(){ });
	            
	        service.updateLike(searchVO.getRno());
	        
	        }catch(Exception e){
	        System.out.println(e.toString());
	        
	    }
	    
	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.print(resMap);
	    
	    return null;
	}
	 
	
	@RequestMapping(value = "/hateCnt/{rno}", method = RequestMethod.POST)
	@ResponseBody
	public String hateCnt(
	        @RequestBody String filterJSON,
	        HttpServletResponse response,
	        ModelMap model ) throws Exception {
	    
	   // LoginVO loginVO = loginService.getLoginInfo();
	    
	    JsonObject resMap = new JsonObject();
	    
	    try{
	        ObjectMapper mapper = new ObjectMapper();
	        QuestionReplyVO searchVO = (QuestionReplyVO)mapper.readValue(filterJSON,new TypeReference<QuestionReplyVO>(){ });
	        
	        service.updateHate(searchVO.getRno());
	        
	    }catch(Exception e){
	        System.out.println(e.toString());
	        
	    }
	    
	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.print(resMap);
	    
	    return null;
	}

	
	
	
	
	
	
	
}

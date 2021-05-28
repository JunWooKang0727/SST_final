package org.sst.mapper;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sst.domain.PersonalCrawlerVO;
import org.sst.domain.PersonalMakeVO;
import org.sst.service.PersonalCrawlMakeService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@Log4j
public class PersonalCrawlMakeTests {
	
	@Setter(onMethod_=@Autowired)
	private PersonalCrawlMakeService service;
	@Test
	public void get() throws IOException{
		String [] a = {"03"};
		PersonalCrawlerVO pcvo = new PersonalCrawlerVO("1", a, "2018", "2021");
		PersonalMakeVO pmvo = new PersonalMakeVO("serviceTest", "표현상 특징을 파악한다");
		service.makePdf(pcvo, pmvo);
	}
}

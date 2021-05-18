package org.sst.domain;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;


import org.jsoup.Connection;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	static String exUrl = "https://wdown.ebsi.co.kr/W61001/01exam";
	
	public static void getEx(String downP,String downH, String path) throws IOException{
		//파라미터 == path
		int fileNameIndex = exUrl.length();
		System.out.println(downP.substring(fileNameIndex+14)+"문제파일이름입니다.");
		InputStream in = new URL(downP).openStream();
		//파일 카피해버리기.
		//String path = "C:/upload/new/";
		Files.copy(in, Paths.get(path+downP.substring(fileNameIndex+14)), StandardCopyOption.REPLACE_EXISTING);
		in.close();
		System.out.println(downH.substring(fileNameIndex+14)+"해설파일이름입니다.");
		InputStream in2 = new URL(downH).openStream();
		//파일 카피해버리기.
		Files.copy(in2, Paths.get(path+downH.substring(fileNameIndex+14)), StandardCopyOption.REPLACE_EXISTING);
		in2.close();
		
		System.out.println("성공입니다");
	}
	
	public Object[] urlFromEbs() throws IOException {
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36";
		Document doc = Jsoup.connect("https://www.ebsi.co.kr/ebs/xip/xipc/previousPaperListAjax.ajax")
				.method(Connection.Method.GET)
				.userAgent(userAgent)
				.header("Referer", "https://www.ebsi.co.kr/ebs/xip/xipc/previousPaperList.ebs")
				.header("Origin","https://www.ebsi.co.kr")
				.header("Accept", "text/html")
				.header("Accept-Encoding", "gzip, deflate, br") 
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.data("targetCd", "D300")//이아래부터 우리가 만져줘야함
				.data("monthList", "03")//여기에 월 추가
				.data("subjList", "1")//국어1 수학2 영어3 한국사4 사회탐구5 과학탐구6 직업탐구7 제2외국어8
				.data("sort", "recent")
				.data("beginYear", "2018")
				.data("endYear", "2021")
				.ignoreContentType(false)
				.get();
		
		/*System.out.println(doc.html());*/
		Elements buttonElements = doc.select("button[class=btn_L_col2 has_down]");
		List<String> buttons = new ArrayList<>();
		for(Element buttonElement : buttonElements){
			buttons.add(buttonElement.attr("onclick"));
		}
		ArrayList<String> downP = new ArrayList<String>();
		ArrayList<String> downH = new ArrayList<String>();
		
		for(int i = 0 ; i<buttons.size();i++){
			if(buttons.get(i).contains("goDownLoadP")){
				String[] downPSp = null;
				downPSp = buttons.get(i).replace("goDownLoadP(","").replace(")","").replace("\'", "").split(",");
				downP.add(exUrl + downPSp[0]);
			}else if(buttons.get(i).contains("goDownLoadH")){
				String[] downHSp = null;
				downHSp = buttons.get(i).replace("goDownLoadH(","").replace(")","").replace("\'", "").split(",");
				downH.add(exUrl + downHSp[0]);
			}
			
		}
		for(int i = 0 ; i<downH.size();i++){
			System.out.println(downP.get(i)+"문제 경로입니다");
			System.out.println(downH.get(i)+"해설 경로입니다");
		}
		/*for(int i = 0 ; i<downH.size();i++){
			getEx(downP.get(i));
		}*/
		Object[] exMak = new Object[2];
		exMak[0] = downP;
		exMak[1] = downH;
		return exMak;
	}
}



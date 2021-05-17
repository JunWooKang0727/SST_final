package org.sst.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	public static void getEx(String exUrl) throws IOException{
		Connection.Response response = Jsoup.connect("exUrl")
                .method(Connection.Method.GET)
                .execute();
		Document googleDocument = response.parse();
		Element btnK = googleDocument.getElementsByTag("cr-icon-button").first();
		String btnKValue = btnK.attr("title");

		System.out.println(btnKValue); 

	}
	public static void main(String[] args) throws IOException {
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
				.data("monthList", "03,04,05,06,07,09,10,11,12")
				.data("subjList", "1,6,7,8")
				.data("sort", "recent")
				.data("beginYear", "2018")
				.data("endYear", "2021")
				.data("monthAll", "")
				.data("month", "on")
				.data("month", "03")
				.data("month", "04")
				.data("month", "05")
				.data("month", "06")
				.data("month", "07")
				.data("month", "09")
				.data("month", "10")
				.data("month", "11")
				.data("month", "12")
				.data("subjAll", "on")
				.data("subjAll", "1")
				.data("subjAll", "6")
				.data("subjAll", "7")
				.data("subjAll", "8")
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
		String exUrl = "https://wdown.ebsi.co.kr/W61001/01exam";
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
			System.out.println(downP.get(i));
			System.out.println(downH.get(i));
		}
		
	}//main end

}

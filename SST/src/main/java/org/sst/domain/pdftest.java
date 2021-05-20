package org.sst.domain;

import java.util.ArrayList;


public class pdftest {

	public static void main(String[] args) throws Exception {
		Crawler cr = new Crawler();//1
		pdftestM pm = new pdftestM();//1
		PersonalCrawlerVO pcvo  = new PersonalCrawlerVO(null, args, null, null, null);//1
		
		int urlLeng = pcvo.getExUrl().length();
		
		System.out.println("1."+urlLeng+"파일이 저장될 경로입니다.");
		//폼으로 입력받아야 하는값.
		String[] monthList = {"03"};
		pcvo.setMonthList(monthList);
		String subject = "1";
		pcvo.setSubject(subject);
		String StartYear = "2018";
		pcvo.setStartYear(StartYear);
		String EndYear = "2021";
		pcvo.setEndYear(EndYear);
		
		Object[] ur = cr.urlFromEbs(pcvo);//트랜잭션 처리 할것 // array 리스트를 리턴
		
		System.out.println("EBS사이트에서 경로를 받아왔습니다.");
		
		ArrayList<String> downPList  = (ArrayList<String>)ur[0];
		
		System.out.println(downPList.size()+"가지고있는 url개수");
		
		System.out.println("문제 URL리스트입니다.");
		
		ArrayList<String> downHList  = (ArrayList<String>)ur[1];
		
		System.out.println("해설 URL리스트입니다.");
		//폼으로 입력받아야 하는값
		pcvo.setPath("C:/upload/new/");
		for(int i = 0 ; i<downPList.size();i++){
			try {
				cr.getEx(downPList.get(i), downHList.get(i),pcvo.getPath());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("문제:"+downPList.get(i)+"\nURL을 통해 각 지정된 경로에 문제를 넣습니다.");
			System.out.println("해설:"+downHList.get(i)+"\nURL을 통해 각 지정된 경로에 해설을 넣습니다.");
			System.out.println(i+"번째입니다.");
			
			//path, 
			PersonalMakeVO pmvo = new PersonalMakeVO(EndYear, EndYear, EndYear);
			pmvo.setExFileName("newexample");
			pmvo.setPath("C:/upload/new/");
			pmvo.setSearchT("표현상 특징을 파악한다");
			Object[] r = pm.findEx(
					downPList.get(i).substring(urlLeng+14),
					downHList.get(i).substring(urlLeng+14),
					pmvo);
			ArrayList<String> exRealMultiList =(ArrayList<String>) r[0] ;
			ArrayList<String> exRealTextList = (ArrayList<String>) r[1];
			ArrayList<String> solRealTextList = (ArrayList<String>) r[2];
			pmvo.setExFileName("sample");
				System.out.println("순서대로 (문제, 경로, 해설, 찾을 검색어) 를 넣습니다. 그러면 OBJCET LIST를 반환하는데 이둘은 각각 문제를만듭니다.");
				try {
					pm.pdfCreate(exRealMultiList, exRealTextList,solRealTextList,pmvo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				System.out.println("실행해!");
				
				pm.pdfDelete(downPList, downHList, pmvo);
				
			
		}
		
	}
}

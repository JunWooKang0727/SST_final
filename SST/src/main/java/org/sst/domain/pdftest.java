package org.sst.domain;

import java.io.File;
import java.util.ArrayList;


public class pdftest {

	public static void main(String[] args) throws Exception {
		Crawler cr = new Crawler();
		pdftestM pm = new pdftestM();
		int urlLeng = cr.exUrl.length();
		System.out.println("1."+urlLeng+"파일이 저장될 경로입니다.");
		Object[] ur = cr.urlFromEbs();
		System.out.println("EBS사이트에서 경로를 받아왔습니다.");
		ArrayList<String> downPList  = (ArrayList<String>)ur[0];
		System.out.println(downPList.size()+"가지고있는 url개수");
		System.out.println("문제 URL리스트입니다.");
		ArrayList<String> downHList  = (ArrayList<String>)ur[1];
		System.out.println("해설 URL리스트입니다.");
		
		
		for(int i = 0 ; i<downPList.size();i++){
			try {
				cr.getEx(downPList.get(i), downHList.get(i));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("문제:"+downPList.get(i)+"\nURL을 통해 각 지정된 경로에 문제를 넣습니다.");
			System.out.println("해설:"+downHList.get(i)+"\nURL을 통해 각 지정된 경로에 해설을 넣습니다.");
			System.out.println(i+"번째입니다.");
			Object[] r = pm.findEx(
					downPList.get(i).substring(urlLeng+14),
					"C:/upload/new/",
					downHList.get(i).substring(urlLeng+14),
					"표현상 특징을 파악한다");
			
				System.out.println("순서대로 (문제, 경로, 해설, 찾을 검색어) 를 넣습니다. 그러면 OBJCET LIST를 반환하는데 이둘은 각각 문제를만듭니다.");
				try {
					pm.pdfCreate((ArrayList<String>) r[0], (ArrayList<String>) r[1],"sample");
				} catch (Exception e) {
					e.printStackTrace();
				}

			
			
		}
		
		
	}
}

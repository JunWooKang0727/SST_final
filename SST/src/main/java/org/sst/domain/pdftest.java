package org.sst.domain;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class pdftest {
	public static void pdfCreate(ArrayList<String> exRealMultiList,ArrayList<String> exRealTextList) throws Exception {
	    String fileName="";
	    String dir="C:/Users/ki11e/upload";
	    fileName = "simple_table3.pdf";
	    BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/malgun.ttf", BaseFont.IDENTITY_H,
                BaseFont.EMBEDDED);

	    Font font = new Font(baseFont, 12);
	    
	    File directory = new File(dir);
	    if(!directory.exists()) directory.mkdirs(); //파일경로 없으면 생성
	     
	     Document document = new Document();
	     PdfWriter.getInstance(document, new FileOutputStream(dir+"/"+fileName));
	         
	      document.open();
	      
	 
	        for(int i = 0; i < exRealMultiList.size(); i++){
	        	PdfPTable pdfTable = new PdfPTable(1);
	        	Chunk chunk = new Chunk(exRealMultiList.get(i), font);
	        	Chunk chunk2 = new Chunk(exRealTextList.get(i), font);
	    	    Paragraph ph = new Paragraph(chunk);
	    	    Paragraph ph2 = new Paragraph(chunk2);
	        	pdfTable.addCell(ph);
	        	pdfTable.addCell(ph2);
	        	document.add(pdfTable);
	        	System.out.println("완료입니다.");
	        }
	        document.close();
	  }
	public static void main(String[] args) throws Exception {

		ArrayList<String> exNumList = new ArrayList<String>();
		ArrayList<String> exMultiStartNumList = new ArrayList<String>();
		ArrayList<String> exMultiEndNumList = new ArrayList<String>();
		
		ArrayList<String> exMultiList = new ArrayList<String>();
		ArrayList<String> exTextList = new ArrayList<String>();
		ArrayList<String> exRealMultiList = new ArrayList<String>();
		ArrayList<String> exRealTextList = new ArrayList<String>();
		/*System.out.println("=============================================================");*/
		String fileName2 = "C:/Users/ki11e/upload/example/korB_hsj_V4AQ713J.pdf";
		File source2 = new File(fileName2);
		PDDocument pdfDoc2 = PDDocument.load(source2);
		String text2 = new PDFTextStripper().getText(pdfDoc2);
		
		String ahn = text2;

		String reg = "(\\[출제의도\\]){1}\\p{Space}*(매체)";

		String[] result = ahn.split(reg);

		for (int i = 0; i < result.length; i++) {
			/*System.out.println("====");
			System.out.println(result[i]);*/
			if (i > 0) {
				String num = result[i - 1].substring(result[i - 1].length() - 4, result[i - 1].length() - 2);
				exNumList.add(num);
				/*System.out.println(result[i - 1].substring(result[i - 1].length() - 4).length() + "길이이이이이ㅣㅇ");
				System.out.println("문제번호입니다 :" + num);*/
			}
			/*System.out.println("====");
			System.out.println(result.length);*/
		}
		String fileName = "C:/Users/ki11e/upload/example/korB_mun_8S782INA.pdf";
		File source = new File(fileName);
		PDDocument pdfDoc = PDDocument.load(source);
		String text = new PDFTextStripper().getText(pdfDoc);
		Pattern pattern = Pattern.compile("\\[{1}\\d{1,2}(\\p{Space}~\\p{Space}){1}\\d{1,2}\\]{1}");
		Matcher matcher = pattern.matcher(text);
		int count = 0;
		while (matcher.find()) {
			count++;
			System.out.println("Match number " + count);
			System.out.println("group(): " + matcher.group());
			//다중지문 시작 인덱스
			System.out.println("start(): " + matcher.start());
		
			System.out.println("end(): " + matcher.end());
			String trans = matcher.group();
			/* trans.replace("]", "").replace(" ","").split("~"); */
			String BefSplit = trans.replace("[", "").replace("]", "").replace(" ", "");
			String[] exRange = BefSplit.split("~");
			/*
			 * for(int i = 0 ; i<arr2.length;i++){ System.out.println(arr2[i]);
			 * }
			 */
			System.out.println(text.indexOf(exRange[0]+".")+"지문시작 부터 지문까지");
			exMultiStartNumList.add(exRange[0]);
			exMultiEndNumList.add(exRange[1]);
			
			int seSNum = Integer.parseInt(exRange[0]);
			int seENum = Integer.parseInt(exRange[1]);
			for (int i = seSNum; i <= seENum; i++) {
				for (int j = 0; j < exNumList.size(); j++) {
					System.out.println("도는거"+i+" : "+j);
					if (i == Integer.parseInt(exNumList.get(j))) {
						System.out.println("일치하는거"+i+" : "+j);
						exMultiList.add(text.substring(matcher.start() , text.indexOf(exRange[0]+".")-1));
						break;
					}
				}
			}
			System.out.println("멍청한녀석"+exMultiList.size());
		}
		for (int i = 0; i < exNumList.size(); i++) {
			if (!exNumList.get(i).equals("45")) {
				//문제번호 = exNumList.get(i)".");
				int exNumBefore = text.indexOf(exNumList.get(i) + ".");
				//문제번호 인덱스 = exNumBefore);
				//문제 다음번호 Integer.parseInt(exNumList.get(i)) + 1) + '.'
				//문제 다음번호 인덱스 exNumAfter
				int exNumAfter = text.indexOf((Integer.parseInt(exNumList.get(i)) + 1) + ".");
				/*System.out.println("문제다음번호 인덱스다" + exNumAfter);
				System.out.println("===========문제===========");*/
				exTextList.add(text.substring(exNumBefore, exNumAfter));
				/*System.out.println(text.substring(exNumBefore, exNumAfter));
				System.out.println("===========문제끝===========");*/
			} else {
				int exNumBefore = text.indexOf(exNumList.get(i) + ".");
				exTextList.add(text.substring(exNumBefore, text.length()));
				/*System.out.println("문제번호다" + exNumList.get(i) + ".");*/
			}
		}

		for(int i = 0 ; i<exTextList.size();i++){
			exRealMultiList.add("연관된 지문: \n"+exMultiList.get(i));
			exRealTextList.add("찾으시는 문제:\n"+exTextList.get(i));
			
		}
		System.out.println("바보녀석!"+exTextList.size());
		pdfCreate(exRealMultiList, exRealTextList);
	}
	
}

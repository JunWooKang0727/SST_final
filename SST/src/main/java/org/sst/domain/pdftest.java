package org.sst.domain;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class pdftest {

	public static void main(String[] args) throws IOException {
		/*
		 * String fileName =
		 * "C:/Users/ki11e/upload/example/korB_mun_8S782INA.pdf"; File source =
		 * new File(fileName); PDDocument pdfDoc = PDDocument.load(source);
		 * String text = new PDFTextStripper().getText(pdfDoc);
		 * System.out.printf(text );
		 */

		System.out.println("=============================================================");
		String fileName2 = "C:/Users/ki11e/upload/example/korB_hsj_V4AQ713J.pdf";
		File source2 = new File(fileName2);
		PDDocument pdfDoc2 = PDDocument.load(source2);
		String text2 = new PDFTextStripper().getText(pdfDoc2);
		
		String ahn = text2;

		String reg = "(\\[출제의도\\]){1}\\p{Space}*(매체)";

        String[] result = ahn.split(reg);
        
        for(int i=0; i<result.length;i++){
            System.out.println("====");
            System.out.println(result[i]);
            if(i>0){
            	String num = result[i-1].substring(result[i-1].length()-4,result[i-1].length()-2);
            	System.out.println(result[i-1].substring(result[i-1].length()-4).length()+"길이이이이이ㅣㅇ");
                System.out.println("문제번호입니다 :"+num);
            }
            System.out.println("====");
            System.out.println(result.length);
        }

        System.out.println();
	}
}

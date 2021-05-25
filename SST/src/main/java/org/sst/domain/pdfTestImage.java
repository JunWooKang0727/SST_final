package org.sst.domain;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.pdfbox.pdmodel.PDDocument;

public class pdfTestImage {
	public static void main(String[] args) throws Exception {
		pdfFindIndex pfi = new pdfFindIndex();
		File source = new File("C:/upload/new/mathC_mun_8RSUO5A1.pdf");
		
		PDDocument document = PDDocument.load(source);
	
		
		String searchTerm = "1.";//리스트에 들어가야되는 값.
		pfi.printSubwords(document, searchTerm);
		pfi.printSubwords(document, (Integer.parseInt(searchTerm.substring(0,searchTerm.length()-1))+1)+".");
		
		File f = new File("C:/upload/new/1 (5).png");
		

		EasyImage easyImage = new EasyImage(f);
		System.out.println("높이 입니다. : "+easyImage.getHeight()+"넓이 입니다. : "+easyImage.getWidth());
		 
		if (!easyImage.isSuppoprtedImageFormat()) {

		    System.out.println("not supported image type");
		    return;

		}
	
		
		/*
		int fontSize = 12;
		int lineDistance = 30;
		EasyImage cropedImage = easyImage.crop((int)(60.087433*4),//찾는숫자 x인덱스*4, 변동값 final
												(int)(204.37665*4-fontSize),//변동값 찾는숫자 y인덱스 *2 - 글자 폰트크기(글자 좌측중앙의 위치를찾기때문)
												(int)((427.98-85.02)*4+lineDistance),//변동값 x축의 문제사이의간격*4-문제사이의줄간격 
												(int)((447.23123-204.37665)*4));//변동값 y축의 문제사이의 간격*4
		*/
		//==========================================
		int fontSize = 40;//폰트는 10인데 4배차이나니깐.
		int lineDistance = 30;
		EasyImage cropedImage = easyImage.crop((int)(60.087433*4),//찾는숫자 x인덱스*4, 변동값 final
												(int)(204.37665*4-fontSize),//변동값 찾는숫자 y인덱스 *2 - 글자 폰트크기(글자 좌측중앙의 위치를찾기때문)
												(int)((302.35614-60.087433)*4+lineDistance),//변동값 x축의 문제사이의간격*4-문제사이의줄간격 
												(int)((447.23123-204.37665)*4));//변동값 y축의 문제사이의 간격*4
		
		//세로비율이 4배 가로비율 5배에 줄간격 절댓값 30
		FileOutputStream out = new FileOutputStream(f);
		cropedImage.writeTo(out, "png"); //cropedImage.writeTo(out, "jpg");
	}
}

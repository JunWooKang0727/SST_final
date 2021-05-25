package org.sst.domain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
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

public class pdftestM {
	static int count = 0;
	public void pdfDelete(ArrayList<String> downPList, ArrayList<String> downHList,PersonalMakeVO pmvo){
		
		for(int i = 0 ; i <downPList.size();i++){
			System.out.println(pmvo.getPath()+"경로!");
			System.out.println(downPList.get(i).substring(52)+"다운!");
			File file = new File(pmvo.getPath() +downPList.get(i).substring(52));
			if (file.exists()) {
				System.out.println("존재하는 파일");
			}
			System.out.println(file.getAbsolutePath()+"문제파일을 지웁니다.");
			boolean bool = file.delete();
			
			File file2 = new File(pmvo.getPath() +downHList.get(i).substring(52));
			if (file2.exists()) {
				System.out.println("존재하는 파일");
			}
			System.out.println(file2.getAbsolutePath()+"해설파일을 지웁니다.");
			boolean bool2 = file2.delete();
		}
		
		
	}
	public void pdfCreate(ArrayList<String> exRealMultiList, ArrayList<String> exRealTextList,ArrayList<String> solRealTextList, PersonalMakeVO pmvo) throws Exception {
		// 파라미터 경로, 파일이름
		String fileName = "";
		//path = "C:/upload/new/";
		count++;
		fileName = pmvo.getExFileName() + count + ".pdf";
		String fileNameSol = pmvo.getExFileName()+"sol" + count + ".pdf";
		BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/malgun.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

		Font font = new Font(baseFont, 12);

		File directory = new File(pmvo.getPath());
		if (!directory.exists())
			directory.mkdirs(); // 파일경로 없으면 생성
		Document document = new Document();
		FileOutputStream fos = new FileOutputStream(pmvo.getPath() + fileName);
		
		PdfWriter.getInstance(document, fos);
		
		if (exRealMultiList.size() > 0) {

			document.open();
			for (int i = 0; i < exRealMultiList.size(); i++) {
				//PdfPTable pdfTable = new PdfPTable(1);
				PdfPTable pdfTable = new PdfPTable(2);
				//이거를 나중에 바꺼버릴
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

		} else {
			
			fos.close();
			File file = new File(pmvo.getPath() + fileName);
			if (file.exists()) {
				System.out.println("존재하는 파일");
			}
			System.out.println(file.getAbsolutePath());
			boolean bool = file.delete();
			System.out.println(bool);
			System.out.println("지워버려려어엉");
		}
		fos.close();
		
		Document documentSol = new Document();
		FileOutputStream fosSol = new FileOutputStream(pmvo.getPath() + fileNameSol);
		PdfWriter.getInstance(documentSol, fosSol);
		if (exRealMultiList.size() > 0) {

			documentSol.open();
			for (int i = 0; i < solRealTextList.size(); i++) {
				//PdfPTable pdfTable2 = new PdfPTable(1);
				PdfPTable pdfTable2 = new PdfPTable(1);
				Chunk chunk3 = new Chunk(solRealTextList.get(i), font);
				Paragraph ph3 = new Paragraph(chunk3);
				pdfTable2.addCell(ph3);
				documentSol.add(pdfTable2);
				System.out.println("완료입니다.");
			}
			documentSol.close();

		} else {
			
			fosSol.close();
			File file = new File(pmvo.getPath() + fileNameSol);
			if (file.exists()) {
				System.out.println("존재하는 파일");
			}
			System.out.println(file.getAbsolutePath());
			boolean bool = file.delete();
			System.out.println(bool);
			System.out.println("지워버려려어엉");
		}
		fosSol.close();
		
	}

	public Object[] findEx(String example, String answer,PersonalMakeVO pmvo) throws IOException {
		// 해설의 카테고리를 통해서 원하는 문제찾기.
		// 매개변수example(파일이름[문제])
		// path(경로),answer(파일이름[해설]),searchT(검색카테고리)
		// String path = "C:/upload/new";
		// String answer = "korB_hsj_V4AQ713J.pdf";//해설임 이거받아와야댐
		// 경로지정+파일이름(구함)
		File source2 = new File(pmvo.getPath() + answer);
		PDDocument pdfDoc2 = PDDocument.load(source2);
		String text2 = new PDFTextStripper().getText(pdfDoc2);
		pdfDoc2.close();
		String ahn = text2;
		// String searchT = "매체";
		String reg = "(\\[출제의도\\]){1}\\p{Space}*(" + pmvo.getSearchT()+ ")";// 원하는 문제를 위해
																	// 매체.. 이부분을
																	// 받아와야댐

		String[] result = ahn.split(reg);
		if (result.length != 0) {
			ArrayList<String> exNumList = new ArrayList<String>();
			for (int i = 0; i < result.length; i++) {
				/*
				 * System.out.println("===="); System.out.println(result[i]);
				 */
				if (i > 0) {
					String num = result[i - 1].substring(result[i - 1].length() - 4, result[i - 1].length() - 2);
					exNumList.add(num);
					/*
					 * System.out.println(result[i - 1].substring(result[i -
					 * 1].length() - 4).length() + "길이이이이이ㅣㅇ");
					 * System.out.println("문제번호입니다 :" + num);
					 */
				}
				/*
				 * System.out.println("====");
				 * System.out.println(result.length);
				 */
			}
			// String fileName = "korB_mun_8S782INA.pdf";//문제임
			File source = new File(pmvo.getPath() + example);
			PDDocument pdfDoc = PDDocument.load(source);
			String text = new PDFTextStripper().getText(pdfDoc);
			pdfDoc.close();
			Pattern pattern = Pattern.compile("\\[{1}\\d{1,2}(\\p{Space}~\\p{Space}){1}\\d{1,2}\\]{1}");
			Matcher matcher = pattern.matcher(text);
			int count = 0;
			ArrayList<String> exMultiList = new ArrayList<String>();
			ArrayList<String> exTextList = new ArrayList<String>();
			ArrayList<String> solTextList = new ArrayList<String>();
			while (matcher.find()) {
				count++;
				System.out.println("Match number " + count);
				System.out.println("group(): " + matcher.group());
				// 다중지문 시작 인덱스
				System.out.println("start(): " + matcher.start());

				System.out.println("end(): " + matcher.end());
				String trans = matcher.group();
				/* trans.replace("]", "").replace(" ","").split("~"); */
				String BefSplit = trans.replace("[", "").replace("]", "").replace(" ", "");
				String[] exRange = BefSplit.split("~");
				/*
				 * for(int i = 0 ; i<arr2.length;i++){
				 * System.out.println(arr2[i]); }
				 */
				ArrayList<String> exMultiStartNumList = new ArrayList<String>();
				ArrayList<String> exMultiEndNumList = new ArrayList<String>();
				System.out.println(text.indexOf(exRange[0] + ".") + "지문시작 부터 지문까지");
								
				exMultiStartNumList.add(exRange[0]);
				exMultiEndNumList.add(exRange[1]);

				int seSNum = Integer.parseInt(exRange[0]);
				int seENum = Integer.parseInt(exRange[1]);

				for (int i = seSNum; i <= seENum; i++) {
					for (int j = 0; j < exNumList.size(); j++) {
						System.out.println("도는거" + i + " : " + j);
						// 뭐야?
						if (i == Integer.parseInt(exNumList.get(j).trim())) {
							System.out.println("일치하는거" + i + " : " + j);
							exMultiList.add(text.substring(matcher.start(), text.indexOf(exRange[0] + ".") - 1));
							break;
						}
					}
				}
				System.out.println("멍청한녀석" + exMultiList.size());
			}
			for (int i = 0; i < exNumList.size(); i++) {
				// 뭐야? 공백도 아니고
				if (!exNumList.get(i).equals("45")) {
					// 문제번호 = exNumList.get(i)".");
					int exNumBefore = text.indexOf(exNumList.get(i) + ".");
					//해설만들기
					int solNumBefore = text2.indexOf(exNumList.get(i) + ".");
					// 문제번호 인덱스 = exNumBefore);
					// 문제 다음번호 Integer.parseInt(exNumList.get(i)) + 1) + '.'
					// 문제 다음번호 인덱스 exNumAfter
					int exNumAfter = text.indexOf((Integer.parseInt(exNumList.get(i).trim()) + 1) + ".");
					int solNumAfter = text2.indexOf((Integer.parseInt(exNumList.get(i).trim()) + 1) + ".");
					/*
					 * System.out.println("문제다음번호 인덱스다" + exNumAfter);
					 * System.out.println("===========문제===========");
					 */
					exTextList.add(text.substring(exNumBefore, exNumAfter));
					solTextList.add(text2.substring(solNumBefore, solNumAfter));
					/*
					 * System.out.println(text.substring(exNumBefore,
					 * exNumAfter));
					 * System.out.println("===========문제끝===========");
					 */
				} else {
					int exNumBefore = text.indexOf(exNumList.get(i) + ".");
					exTextList.add(text.substring(exNumBefore, text.length()));
					int solNumBefore = text2.indexOf(exNumList.get(i) + ".");
					solTextList.add(text.substring(solNumBefore, text.length()));
					/* System.out.println("문제번호다" + exNumList.get(i) + "."); */
				}

			}
			ArrayList<String> exRealMultiList = new ArrayList<String>();
			ArrayList<String> exRealTextList = new ArrayList<String>();
			ArrayList<String> solRealTextList = new ArrayList<String>();
			for (int i = 0; i < exTextList.size(); i++) {
				exRealMultiList.add("연관된 지문: \n" + exMultiList.get(i));
				exRealTextList.add("찾으시는 문제:\n" + exTextList.get(i));
				solRealTextList.add("찾으시는 문제의 해설:\n"+solTextList.get(i));

			}
			System.out.println("바보녀석!" + exTextList.size());
			Object[] exPan = new Object[3];
			exPan[0] = exRealMultiList;
			exPan[1] = exRealTextList;
			exPan[2] = solRealTextList;
			return exPan;
		} else {
			return null;
		}

	}
}

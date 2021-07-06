package org.sst.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.sst.domain.Crawler;
import org.sst.domain.EasyImage;
import org.sst.domain.PdfIndexInImage;
import org.sst.domain.PersonalCrawlerVO;
import org.sst.domain.PersonalMakeVO;
import org.sst.domain.pdfFindIndex;
import org.sst.domain.pdfImageTest;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PersonalCrawlMakeServiceImpl implements PersonalCrawlMakeService {
	private static String exUrl = "https://wdown.ebsi.co.kr/W61001/01exam";
	private static int count = 0;
	@Override
	public Object[] urlFromEbs(PersonalCrawlerVO pcvo) throws IOException {//pdf주소 크롤링
		String monthToString = "";
		for (int i = 0; i < pcvo.getMonthList().length; i++) {
			monthToString += pcvo.getMonthList()[i];
			monthToString += ",";
		}
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36";
		Document doc = Jsoup.connect("https://www.ebsi.co.kr/ebs/xip/xipc/previousPaperListAjax.ajax")
				.method(Connection.Method.GET).userAgent(userAgent)
				.header("Referer", "https://www.ebsi.co.kr/ebs/xip/xipc/previousPaperList.ebs")
				.header("Origin", "https://www.ebsi.co.kr")
				.header("Accept", "text/html")
				.header("Accept-Encoding", "gzip, deflate, br")
				.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
				.data("targetCd", "D300")// 이아래부터
				.data("monthList", monthToString.substring(0, monthToString.length() - 1))											// ex)03,04,05
				.data("subjList", pcvo.getSubject())
				.data("sort", "recent")
				.data("beginYear", pcvo.getStartYear())
				.data("endYear", pcvo.getEndYear())
				.ignoreContentType(false)
				.get();
		Elements buttonElements = doc.select("button[class=btn_L_col2 has_down]");
		List<String> buttons = new ArrayList<>();
		for (Element buttonElement : buttonElements) {
			buttons.add(buttonElement.attr("onclick"));
		}
		ArrayList<String> downP = new ArrayList<String>();
		ArrayList<String> downH = new ArrayList<String>();
		for (int i = 0; i < buttons.size(); i++) {
			if (buttons.get(i).contains("goDownLoadP")) {
				String[] downPSp = null;
				downPSp = buttons.get(i).replace("goDownLoadP(", "").replace(")", "").replace("\'", "").split(",");
				downP.add(exUrl + downPSp[0]);
			} else if (buttons.get(i).contains("goDownLoadH")) {
				String[] downHSp = null;
				downHSp = buttons.get(i).replace("goDownLoadH(", "").replace(")", "").replace("\'", "").split(",");
				downH.add(exUrl + downHSp[0]);
			}

		}
		Object[] exMak = new Object[2];
		exMak[0] = downP;
		exMak[1] = downH;
		return exMak;
	}

	@Override
	public void getEx(String downP, String downH, String path) throws IOException {//크롤링한 경로 copy해서 pdf파일가져오기
		int fileNameIndex = exUrl.length();
		InputStream in = new URL(downP).openStream();
		Files.copy(in, Paths.get(path + downP.substring(fileNameIndex + 14)), StandardCopyOption.REPLACE_EXISTING);
		in.close();
		InputStream in2 = new URL(downH).openStream();
		Files.copy(in2, Paths.get(path + downH.substring(fileNameIndex + 14)), StandardCopyOption.REPLACE_EXISTING);
		in2.close();
	}

	@Override
	public Object[] findEx(String example, String answer,PersonalMakeVO pmvo) throws IOException {//해설에서 카테고리별 원하는 문제 찾기
		File source2 = new File(pmvo.getPath() + answer);
		PDDocument pdfDoc2 = PDDocument.load(source2);
		String text2 = new PDFTextStripper().getText(pdfDoc2);
		pdfDoc2.close();
		String ahn = text2;
		String reg = "(\\[출제의도\\]){1}\\p{Space}*(" + pmvo.getSearchT()+ ")";
		String[] result = ahn.split(reg);
		if (result.length != 0) {
			ArrayList<String> exNumList = new ArrayList<String>();
			for (int i = 0; i < result.length; i++) {
				if (i > 0) {
					String num = result[i - 1].substring(result[i - 1].length() - 4, result[i - 1].length() - 2);
					exNumList.add(num);
				}
			}
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
				String trans = matcher.group();
				String BefSplit = trans.replace("[", "").replace("]", "").replace(" ", "");
				String[] exRange = BefSplit.split("~");
				ArrayList<String> exMultiStartNumList = new ArrayList<String>();
				ArrayList<String> exMultiEndNumList = new ArrayList<String>();
								
				exMultiStartNumList.add(exRange[0]);
				exMultiEndNumList.add(exRange[1]);

				int seSNum = Integer.parseInt(exRange[0]);
				int seENum = Integer.parseInt(exRange[1]);

				for (int i = seSNum; i <= seENum; i++) {
					for (int j = 0; j < exNumList.size(); j++) {
						if (i == Integer.parseInt(exNumList.get(j).trim())) {
							exMultiList.add(text.substring(matcher.start(), text.indexOf(exRange[0] + ".") - 1));
							break;
						}
					}
				}
			}
			for (int i = 0; i < exNumList.size(); i++) {
				if (!exNumList.get(i).equals("45")) {
					int exNumBefore = text.indexOf(exNumList.get(i) + ".");
					int solNumBefore = text2.indexOf(exNumList.get(i) + ".");
					int exNumAfter = text.indexOf((Integer.parseInt(exNumList.get(i).trim()) + 1) + ".");
					int solNumAfter = text2.indexOf((Integer.parseInt(exNumList.get(i).trim()) + 1) + ".");
					exTextList.add(text.substring(exNumBefore, exNumAfter));
					solTextList.add(text2.substring(solNumBefore, solNumAfter));
				} else {
					int exNumBefore = text.indexOf(exNumList.get(i) + ".");
					exTextList.add(text.substring(exNumBefore, text.length()));
					int solNumBefore = text2.indexOf(exNumList.get(i) + ".");
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
			Object[] exPan = new Object[3];
			exPan[0] = exRealMultiList;
			exPan[1] = exRealTextList;
			exPan[2] = solRealTextList;
			return exPan;
		} else {
			return null;
		}
	}

	@Override
	public void pdfCreate(ArrayList<String> exRealMultiList, ArrayList<String> exRealTextList,ArrayList<String> solRealTextList, PersonalMakeVO pmvo) throws Exception {
		//해당 하는 문제를 text로 뽑아서 pdfbox에 넣어서 pdf로 만들어주는 메서드
		String fileName = "";
		count++;
		fileName = pmvo.getExFileName() + count + ".pdf";
		String fileNameSol = pmvo.getExFileName()+"sol" + count + ".pdf";
		BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/malgun.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font = new Font(baseFont, 12);
		File directory = new File(pmvo.getPath());
		if (!directory.exists())
			directory.mkdirs(); // 파일경로 없으면 생성
		com.itextpdf.text.Document document = new com.itextpdf.text.Document();
		FileOutputStream fos = new FileOutputStream(pmvo.getPath() + fileName);
		PdfWriter.getInstance(document, fos);
		if (exRealMultiList.size() > 0) {
			document.open();
			for (int i = 0; i < exRealMultiList.size(); i++) {
				PdfPTable pdfTable = new PdfPTable(1);
				Chunk chunk = new Chunk(exRealMultiList.get(i), font);
				Chunk chunk2 = new Chunk(exRealTextList.get(i), font);
				Paragraph ph = new Paragraph(chunk);
				Paragraph ph2 = new Paragraph(chunk2);
				pdfTable.addCell(ph);
				pdfTable.addCell(ph2);
				document.add(pdfTable);
			}
			document.close();
		} else {
			fos.close();
			File file = new File(pmvo.getPath() + fileName);
			if (file.exists()) System.out.println("존재하는 파일");
			boolean bool = file.delete();
		}
		fos.close();
		com.itextpdf.text.Document documentSol = new com.itextpdf.text.Document();
		FileOutputStream fosSol = new FileOutputStream(pmvo.getPath() + fileNameSol);
		PdfWriter.getInstance(documentSol, fosSol);
		if (exRealMultiList.size() > 0) {
			documentSol.open();
			for (int i = 0; i < solRealTextList.size(); i++) {
				PdfPTable pdfTable2 = new PdfPTable(1);
				Chunk chunk3 = new Chunk(solRealTextList.get(i), font);
				Paragraph ph3 = new Paragraph(chunk3);
				pdfTable2.addCell(ph3);
				documentSol.add(pdfTable2);
			}
			documentSol.close();
		} else {
			fosSol.close();
			File file = new File(pmvo.getPath() + fileNameSol);
			if (file.exists()) System.out.println("존재하는 파일");

			System.out.println(file.getAbsolutePath());
			boolean bool = file.delete();
			System.out.println(bool);
			System.out.println("지워버려려어엉");
		}
		fosSol.close();
	}
	
	@Override
	public void pdfDelete(ArrayList<String> downPList, ArrayList<String> downHList, PersonalMakeVO pmvo) {
		for (int i = 0; i < downPList.size(); i++) {//pdf생성하느라 썻던 파일을 다시 지워줌
			File file = new File(pmvo.getPath() + downPList.get(i).substring(52));
			if (file.exists()) System.out.println("존재하는 파일");
			File file2 = new File(pmvo.getPath() + downHList.get(i).substring(52));
			if (file2.exists()) System.out.println("존재하는 파일");
		}
	}

	@Override
	public List<String> makePdf(PersonalCrawlerVO pcvo, PersonalMakeVO pmvo) throws IOException { 
		File directory = new File(pmvo.getPath()); // 위 해당 메서드를 다 모아서 실행시켜줌
		if (!directory.exists()) directory.mkdirs();
		File[] filearr = directory.listFiles();
		for(File f : filearr){
			System.out.println("★★★★★★★"+f.getName()); //해당 문제를 fileupload를 위해 만들어줌
			f.delete();
		}
		int urlLeng = pcvo.getExUrl().length();
		Object[] ur = urlFromEbs(pcvo);
		ArrayList<String> downPList = (ArrayList<String>) ur[0];
		ArrayList<String> downHList = (ArrayList<String>) ur[1];
		for (int i = 0; i < downPList.size(); i++) {
			try {
				getEx(downPList.get(i), downHList.get(i), pcvo.getPath());
			} catch (Exception e) {
				e.printStackTrace();
			}
			Object[] r = findEx(downPList.get(i).substring(urlLeng + 14), downHList.get(i).substring(urlLeng + 14),
					pmvo);
			ArrayList<String> exRealMultiList = (ArrayList<String>) r[0];
			ArrayList<String> exRealTextList = (ArrayList<String>) r[1];
			ArrayList<String> solRealTextList = (ArrayList<String>) r[2];
			try {
				pdfCreate(exRealMultiList, exRealTextList,solRealTextList, pmvo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			pdfDelete(downPList, downHList, pmvo);
		}
		File[] resultFiles = directory.listFiles();
		List<String> result = new ArrayList<>();
		for(File f : resultFiles){
			result.add(f.getName());
		}
		return result;
	}

	@Override
	public ArrayList<String> findExForImage(String example, String answer, PersonalMakeVO pmvo) throws IOException {
		File source2 = new File(pmvo.getPath() + answer); //해당 파일에서 원하는 문제를 image번호에서 찾음
		PDDocument pdfDoc2 = PDDocument.load(source2);
		String text2 = new PDFTextStripper().getText(pdfDoc2);
		pdfDoc2.close();
		String ahn = text2;
		String reg = "(\\[출제의도\\]){1}\\p{Space}*[가-힣]*(" + pmvo.getSearchT()+ ")";// 원하는 문제를 위해
		String[] result = ahn.split(reg);
		ArrayList<String> exNumList = null;
		int resultLength = result.length;
		if (resultLength != 0) {
			exNumList = new ArrayList<String>();
			for (int i = 0; i < resultLength; i++) {
				if (i > 0) {
					String num = result[i - 1].substring(result[i - 1].length() - 4, result[i - 1].length() - 2);
					exNumList.add(num);
				}
			}
		}else{
			System.out.println("리스트를 반환하지 못했습니다.");
		}
		return exNumList;
	}

	@Override
	public File copyingFileForImage(String oriFilePath, String storepath, String filename, String rename, int count,
			String form){//이미지로 만든 원본을 카피해줍니다. 그이유는 원본을 잘라내면 해당페이지의 같은 문제를 찾지 못합니다.
		String copyFilePath = storepath+filename+rename+count+form;
        File oriFile = new File(oriFilePath);//파일객체생성
        File copyFile = new File(copyFilePath);//복사파일객체생성
        try { 
            FileInputStream fis = new FileInputStream(oriFile); //읽을파일
            FileOutputStream fos = new FileOutputStream(copyFile); //복사할파일       
            int fileByte = 0; 
            while((fileByte = fis.read()) != -1) {
                fos.write(fileByte);
            }
            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return copyFile;
	}
	@Override
	public List<String> pdfCreateForImage(ArrayList<String> remakeFilePathList, PersonalMakeVO pmvo)
			throws Exception { //원하는 이미지를 자른후 pdf파일로 만들어준다.
		List<String> makedpdf = new ArrayList<String>();
		String pdfPath = pmvo.getPath()+pmvo.getExFileName()+".pdf";
		File directory = new File(pmvo.getPath());
		if (!directory.exists())
			directory.mkdirs(); // 파일경로 없으면 생성
		PDDocument doc = new PDDocument();
		try {
			for (int i = 0; i < remakeFilePathList.size(); i++) {
				PDPage page = new PDPage();
				doc.addPage(page);
				PDImageXObject pdImage = PDImageXObject.createFromFile(remakeFilePathList.get(i), doc);
				PDPageContentStream contents = new PDPageContentStream(doc, page);
				contents.drawImage(pdImage, 0, 0, 500, 750); //만들어줄 파이의 크기
				contents.close();
				doc.save(pdfPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			doc.close();
		}
			for (int i = 0; i < remakeFilePathList.size(); i++) {
			File file = new File(remakeFilePathList.get(i));
			if (file.exists()) System.out.println("존재하는 파일");
			System.out.println(file.getAbsolutePath());
			file.delete();
			}
			makedpdf.add(pdfPath.substring(pmvo.getPath().length(),pdfPath.length()));
			return makedpdf;
	}

	@Override
	public List<String> makePdfForImage(PersonalCrawlerVO pcvo, PersonalMakeVO pmvo) throws Exception {
		Crawler cr = new Crawler();
		ArrayList<String> remakeFilePathList = new ArrayList<String>();
		Object[] ur = cr.urlFromEbs(pcvo);//문제 url https://wdown.ebsi.co.kr/W61001/01exam/.....pdf
		ArrayList<String> downPList  = (ArrayList<String>)ur[0];
		ArrayList<String> downHList  = (ArrayList<String>)ur[1];
		int downPListSize =  downPList.size();
		System.out.println();
		for(int i = 0 ; i<downPListSize;i++){
			
			try {
				cr.getEx(downPList.get(i), downHList.get(i),pcvo.getPath());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int count = 0;
		int exCount = 0;
		int downHListSize = downHList.size();
		for(int i = 0 ; i<downHListSize;i++){
			count++;
			ArrayList<String> exListForImage = findExForImage(downPList.get(i).substring(52), downHList.get(i).substring(52), pmvo);
			pdfImageTest pit = new pdfImageTest();
			File file = new File(pmvo.getPath()+downPList.get(i).substring(52));
			FileInputStream in = new FileInputStream(file);
			ArrayList<String> chagedExImage = (ArrayList<String>) pit.conversionPdf2Img(in, downPList.get(i).substring(52,downPList.get(i).length()-4));
			in.close();
			pdfFindIndex pfi = new pdfFindIndex();
			File source = new File(pmvo.getPath()+downPList.get(i).substring(52));
			PDDocument document = PDDocument.load(source);
			for(int j = 0 ; j<exListForImage.size();j++){
			String searchTerm = exListForImage.get(j)+".";//리스트에 들어가야되는 값.
			ArrayList<PdfIndexInImage> firstP3i = pfi.printSubwords(document, searchTerm.trim());
			ArrayList<PdfIndexInImage> secondP3i = pfi.printSubwords(document, (Integer.parseInt(searchTerm.substring(0,searchTerm.length()-1).trim())+1)+".");
			//45번이 아닌경우 처리
			int secondP3iSize= secondP3i.size();
			int chagedExImageSize = chagedExImage.size();
			if(secondP3iSize!=0){
				
			for(int k = 0 ; k <chagedExImageSize;k++){//가져온pdf와 이미지의 비율 1:4 줄간격 절댓값 30
				File f = new File(chagedExImage.get(k));
				EasyImage easyImage = new EasyImage(f);
				if (!easyImage.isSuppoprtedImageFormat()) {
					System.out.println("not supported image type");
					return null;
				}
				int imagePath = pcvo.getPath().length()+downPList.get(i).substring(52,downPList.get(i).length()-4).length();
				if(firstP3i.get(0).getPage()==Integer.parseInt(chagedExImage.get(k).substring(imagePath,chagedExImage.get(k).length()-4))){
					 	exCount++;
						String oriFilePath = chagedExImage.get(k);
				        String copyFilePath = pcvo.getPath()+chagedExImage.get(k).substring(pcvo.getPath().length(),chagedExImage.get(k).length()-4)+"remake"+exCount+".png";
				  
				        File copyFile = copyingFileForImage(oriFilePath, 
				        		pcvo.getPath(),//파일경로 
				        		chagedExImage.get(k).substring(pcvo.getPath().length(),chagedExImage.get(k).length()-4),
				        		"remake",//새로붙일이름
				        		exCount,//숫자
				        		".png");//형식
				    EasyImage easyImageMachted = new EasyImage(copyFile);
					int fontSize = 40;//폰트는 10인데 4배차이나니깐.
					int lineDistance = 30;
					int xindex = (int)(firstP3i.get(0).getxIndex()*4);
					int xwidth = 0;
					if(firstP3i.get(0).getxIndex()<(int)((easyImage.getWidth()/4)/2-lineDistance)){
						xwidth = (int)(easyImage.getWidth()-xindex*2)/2+lineDistance;
					}else{
						xwidth = (int)(easyImage.getWidth()-xindex-lineDistance);
					}
					int yindex = (int)(firstP3i.get(0).getyIndex()*4-fontSize);
					int yheight = 0;
					if((firstP3i.get(0).getPage()==secondP3i.get(0).getPage())&&
							firstP3i.get(0).getyIndex()<secondP3i.get(0).getyIndex()){
						yheight = (int)(secondP3i.get(0).getyIndex()*4-firstP3i.get(0).getyIndex()*4+fontSize);
					}else if((firstP3i.get(0).getPage()==secondP3i.get(0).getPage())&&
							firstP3i.get(0).getyIndex()>secondP3i.get(0).getyIndex()){
						yheight = (int)(easyImage.getHeight()-firstP3i.get(0).getyIndex()*4+fontSize);
					}else if(firstP3i.get(0).getPage()!=secondP3i.get(0).getPage()){
						yheight = (int)(easyImage.getHeight()-firstP3i.get(0).getyIndex()*4+fontSize);
					}else{
						yheight = (int)((secondP3i.get(0).getyIndex()-firstP3i.get(0).getyIndex())*4+fontSize);
					}
					EasyImage cropedImage = easyImageMachted.crop(xindex,//찾는숫자 x인덱스*4, 변동값 final
														   yindex,//변동값 찾는숫자 y인덱스 *2 - 글자 폰트크기(글자 좌측중앙의 위치를찾기때문)
														   xwidth,//변동값 x축의 문제사이의간격*4-문제사이의줄간격 
														   yheight);//변동값 y축의 문제사이의 간격*4
					FileOutputStream out = new FileOutputStream(copyFile);
					cropedImage.writeTo(out, "png"); //cropedImage.writeTo(out, "jpg");
					remakeFilePathList.add(copyFilePath);
					out.close();
				}
				
			}
			
			}else{//45번인경우
				for(int k = 0 ; k <chagedExImageSize;k++){
					File f = new File(chagedExImage.get(k));
					EasyImage easyImage = new EasyImage(f);
					if (!easyImage.isSuppoprtedImageFormat()) {
						System.out.println("not supported image type");
						return null;
					}
					int imagePath = pcvo.getPath().length()+downPList.get(i).substring(52,downPList.get(i).length()-4).length();
					if(firstP3i.get(0).getPage()==Integer.parseInt(chagedExImage.get(k).substring(imagePath,chagedExImage.get(k).length()-4))){
						 	exCount++;
							String oriFilePath = chagedExImage.get(k);
					        String copyFilePath = pcvo.getPath()+chagedExImage.get(k).substring(pcvo.getPath().length(),chagedExImage.get(k).length()-4)+"remake"+exCount+".png";
					        File copyFile = copyingFileForImage(oriFilePath, 
					        		pcvo.getPath(),//파일경로 
					        		chagedExImage.get(k).substring(pcvo.getPath().length(),chagedExImage.get(k).length()-4),
					        		"remake",//새로붙일이름
					        		exCount,//숫자
					        		".png");//형식
					      //해당 파일 카피 완료    
					    EasyImage easyImageMachted = new EasyImage(copyFile);  
						int fontSize = 40;//폰트는 10인데 4배차이나니깐.
						int lineDistance = 30;
						int xindex = (int)(firstP3i.get(0).getxIndex()*4);
						int xwidth = 0;
						if(firstP3i.get(0).getxIndex()<(int)((easyImage.getWidth()/4)/2)-lineDistance){
							xwidth = (int)(easyImage.getWidth()-xindex*2)/2+lineDistance;
						}else{
							xwidth = (int)(easyImage.getWidth()-xindex-lineDistance);
						}
						int yindex = (int)(firstP3i.get(0).getyIndex()*4-fontSize);
						int yheight = easyImage.getHeight()-yindex;

						EasyImage cropedImage = easyImageMachted.crop(xindex,//찾는숫자 x인덱스*4, 변동값 final
															   yindex,//변동값 찾는숫자 y인덱스 *2 - 글자 폰트크기(글자 좌측중앙의 위치를찾기때문)
															   xwidth,//변동값 x축의 문제사이의간격*4-문제사이의줄간격 
															   yheight);//변동값 y축의 문제사이의 간격*4
						FileOutputStream out = new FileOutputStream(copyFile);
						cropedImage.writeTo(out, "png"); //cropedImage.writeTo(out, "jpg");
						remakeFilePathList.add(copyFilePath);
						out.close();
						}
					}
				}
			}
			document.close();
		}
		List<String> createImagePath = pdfCreateForImage(remakeFilePathList, pmvo);
		return createImagePath;
	}
}
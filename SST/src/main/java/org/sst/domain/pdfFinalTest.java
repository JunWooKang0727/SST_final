package org.sst.domain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocListener;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class pdfFinalTest {
	public static ArrayList<String> findExForImage(String example, String answer,PersonalMakeVO pmvo) throws IOException {

		File source2 = new File(pmvo.getPath() + answer);
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

	public static File copyingFileForImage(String oriFilePath,String storepath,String filename,String rename,int count ,String form ){
		String copyFilePath = storepath+filename+rename+count+form;
		System.out.println(copyFilePath+"해당 경로로 복사를 진행합니다.");
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
	public static void pdfCreateForImage(ArrayList<String> remakeFilePathList, String makeFileName,PersonalMakeVO pmvo) throws Exception {
		// 파라미터 경로, 파일이름
		String pdfPath = pmvo.getPath()+makeFileName+".pdf";
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
				contents.drawImage(pdImage, 0, 0, 500, 750
						);
				contents.close();
				doc.save(pdfPath);
				System.out.println("완료입니다.");
			}
		} catch (Exception e) {
			doc.close();
		}
			for (int i = 0; i < remakeFilePathList.size(); i++) {
			File file = new File(remakeFilePathList.get(i));
			if (file.exists()) {
				System.out.println("존재하는 파일");
			}
			System.out.println(file.getAbsolutePath());
			boolean bool = file.delete();
			System.out.println(bool);
			System.out.println("지우기 성공하였습니다.");
			}
	}
	
	
	public static void main(String[] args) throws Exception {
		Crawler cr = new Crawler();
		ArrayList<String> remakeFilePathList = new ArrayList<String>();
		String [] a = {"03"};
		PersonalCrawlerVO pcvo = new PersonalCrawlerVO("2", a, "2018", "2018");
		PersonalMakeVO pmvo = new PersonalMakeVO("newexample", "수열");
		String reMakeName = "exam";
		Object[] ur = cr.urlFromEbs(pcvo);//문제 url https://wdown.ebsi.co.kr/W61001/01exam/.....pdf
		
		ArrayList<String> downPList  = (ArrayList<String>)ur[0];
		ArrayList<String> downHList  = (ArrayList<String>)ur[1];
		int downPListSize =  downPList.size();
		System.out.println();
		for(int i = 0 ; i<downPListSize;i++){
			
			try {
				cr.getEx(downPList.get(i), downHList.get(i),pcvo.getPath());
				//문제를 pcvo에 있는 값에 따라 pdf를 만들어준다.C:/upload/new/m_mun...pdf
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		
		//파라미터
		int count = 0;
		int exCount = 0;
		int downHListSize = downHList.size();
		for(int i = 0 ; i<downHListSize;i++){
			count++;
			System.out.println(count);
			ArrayList<String> exListForImage = findExForImage(downPList.get(i).substring(52), downHList.get(i).substring(52), pmvo);
			//pcvo에 따른 searchT의 값에 따라 문제 번호값을 가지고 있는 ArrayList값을 리턴
			pdfImageTest pit = new pdfImageTest();
			File file = new File(pmvo.getPath()+downPList.get(i).substring(52));
			FileInputStream in = new FileInputStream(file);
			ArrayList<String> chagedExImage = (ArrayList<String>) pit.conversionPdf2Img(in, downPList.get(i).substring(52,downPList.get(i).length()-4));
			in.close();
			//pdf파일을 하나 넣어주고 그에 따라 이미지경로 리스트를 반환한다.
			//이름은 C:/upload/new/m_mun...1.png 여기서 1은 페이지 수이다.
			pdfFindIndex pfi = new pdfFindIndex();
			File source = new File(pmvo.getPath()+downPList.get(i).substring(52));
				
			PDDocument document = PDDocument.load(source);
			for(int j = 0 ; j<exListForImage.size();j++){
	
			String searchTerm = exListForImage.get(j)+".";//리스트에 들어가야되는 값.
			
			ArrayList<PdfIndexInImage> firstP3i = pfi.printSubwords(document, searchTerm.trim());
			ArrayList<PdfIndexInImage> secondP3i = pfi.printSubwords(document, (Integer.parseInt(searchTerm.substring(0,searchTerm.length()-1).trim())+1)+".");
			
			//45번이 아닌경우
			int secondP3iSize= secondP3i.size();
			int chagedExImageSize = chagedExImage.size();
			if(secondP3iSize!=0){
				
			
			for(int k = 0 ; k <chagedExImageSize;k++){
				System.out.println(chagedExImage.get(k)+"해당이미지 검색중입니다.");
				File f = new File(chagedExImage.get(k));
				EasyImage easyImage = new EasyImage(f);
				System.out.println("높이 입니다. : "+easyImage.getHeight()+"넓이 입니다. : "+easyImage.getWidth());
					
				if (!easyImage.isSuppoprtedImageFormat()) {

					System.out.println("not supported image type");
					return;

				}
				int imagePath = pcvo.getPath().length()+downPList.get(i).substring(52,downPList.get(i).length()-4).length();
				if(firstP3i.get(0).getPage()==Integer.parseInt(chagedExImage.get(k).substring(imagePath,chagedExImage.get(k).length()-4))){
					 	exCount++;
					 	//문제를 구분한다.
					 	
					 	
						String oriFilePath = chagedExImage.get(k);
						
				        //복사될 파일경로//chagedExImage.get(k)
				        String copyFilePath = pcvo.getPath()+chagedExImage.get(k).substring(pcvo.getPath().length(),chagedExImage.get(k).length()-4)+"remake"+exCount+".png";
				        System.out.println(copyFilePath+"dlrasrosadsgklsa이게모야야야ㅏㅇㅇ");
				        
				        File copyFile = copyingFileForImage(oriFilePath, 
				        		pcvo.getPath(),//파일경로 
				        		chagedExImage.get(k).substring(pcvo.getPath().length(),chagedExImage.get(k).length()-4),
				        		//파일이름
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
					System.out.println("뽑는xindex :"+xindex+"뽑는yindex :"+yindex+"뽑는xwidth :"+xwidth+"뽑는yheight :"+yheight);
					EasyImage cropedImage = easyImageMachted.crop(xindex,//찾는숫자 x인덱스*4, 변동값 final
														   yindex,//변동값 찾는숫자 y인덱스 *2 - 글자 폰트크기(글자 좌측중앙의 위치를찾기때문)
														   xwidth,//변동값 x축의 문제사이의간격*4-문제사이의줄간격 
														   yheight);//변동값 y축의 문제사이의 간격*4
						//세로비율이 4배 가로비율 5배에 줄간격 절댓값 30
					FileOutputStream out = new FileOutputStream(copyFile);
					System.out.println("성공입니다.");
					cropedImage.writeTo(out, "png"); //cropedImage.writeTo(out, "jpg");
					remakeFilePathList.add(copyFilePath);
					out.close();
				}
				
			}
			
			}else{//45번인경우
				for(int k = 0 ; k <chagedExImageSize;k++){
					System.out.println(chagedExImage.get(k)+"해당이미지 검색중입니다.");
					File f = new File(chagedExImage.get(k));
					EasyImage easyImage = new EasyImage(f);
					System.out.println("높이 입니다. : "+easyImage.getHeight()+"넓이 입니다. : "+easyImage.getWidth());
						
					if (!easyImage.isSuppoprtedImageFormat()) {

						System.out.println("not supported image type");
						return;

					}
					int imagePath = pcvo.getPath().length()+downPList.get(i).substring(52,downPList.get(i).length()-4).length();
					if(firstP3i.get(0).getPage()==Integer.parseInt(chagedExImage.get(k).substring(imagePath,chagedExImage.get(k).length()-4))){
						 	exCount++;
						 	//문제를 구분한다.
							String oriFilePath = chagedExImage.get(k);
							
					        //복사될 파일경로//chagedExImage.get(k)
					        String copyFilePath = pcvo.getPath()+chagedExImage.get(k).substring(pcvo.getPath().length(),chagedExImage.get(k).length()-4)+"remake"+exCount+".png";
					        System.out.println(copyFilePath+"dlrasrosadsgklsa이게모야야야ㅏㅇㅇ");
					        //파일객체생성
					        File copyFile = copyingFileForImage(oriFilePath, 
					        		pcvo.getPath(),//파일경로 
					        		chagedExImage.get(k).substring(pcvo.getPath().length(),chagedExImage.get(k).length()-4),
					        		//파일이름
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
							//세로비율이 4배 가로비율 5배에 줄간격 절댓값 30
						FileOutputStream out = new FileOutputStream(copyFile);
						System.out.println("성공입니다.");
						cropedImage.writeTo(out, "png"); //cropedImage.writeTo(out, "jpg");
						
						remakeFilePathList.add(copyFilePath);
						out.close();
						}
					
					}
				
				}
			//아래 코드는 문제 숫자를 넣으면 해당 위치를 가지고 있는 객체를 반환합니다.

			}
			document.close();
			
		}
		pdfCreateForImage(remakeFilePathList, reMakeName, pmvo);
	}
}


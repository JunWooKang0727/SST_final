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
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class pdfFinalTest {
	public static ArrayList<String> findEx(String example, String answer,PersonalMakeVO pmvo) throws IOException {
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
		ArrayList<String> exNumList = null;
		if (result.length != 0) {
			exNumList = new ArrayList<String>();
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
			
		}else{
			System.out.println("리스트를 반환하지 못했습니다.");
		}
		return exNumList;
	}
	public static List<String> conversionPdf2Img(InputStream is, String uniqueId,String resultImgPath) {
        List<String> savedImgList = new ArrayList<>(); //저장된 이미지 경로를 저장하는 List 객체
        try {
            PDDocument pdfDoc = PDDocument.load(is); //Document 생성
            PDFRenderer pdfRenderer = new PDFRenderer(pdfDoc);

            //String resultImgPath = "C:/upload/new"; //이미지가 저장될 경로
            Files.createDirectories(Paths.get(resultImgPath)); //PDF 2 Img에서는 경로가 없는 경우 이미지 파일이 생성이 안되기 때문에 디렉토리를 만들어준다.

            //순회하며 이미지로 변환 처리
            for (int i=0; i<pdfDoc.getPages().getCount(); i++) {
                String imgFileName = resultImgPath + "/" +uniqueId + (i+1) +".png";
					
		//DPI 설정
                BufferedImage bim = pdfRenderer.renderImageWithDPI(i, 300, ImageType.RGB);

                // 이미지로 만든다.
                ImageIOUtil.writeImage(bim, imgFileName , 300);

                //저장 완료된 이미지를 list에 추가한다.
                savedImgList.add(imgFileName);
            }
            pdfDoc.close(); //모두 사용한 PDF 문서는 닫는다.
        }catch (Exception e) {
			e.printStackTrace();
		}
        return savedImgList;
    }

	
	public static void main(String[] args) throws IOException {
		Crawler cr = new Crawler();
		/*public class PersonalCrawlerVO {
			private String Subject;
			private String[] MonthList;
			private String StartYear;
			private String EndYear;
			private String Path;
			
			private final String exUrl = "https://wdown.ebsi.co.kr/W61001/01exam";
			
		}*/
		String [] a = {"03"};
		PersonalCrawlerVO pcvo = new PersonalCrawlerVO("1", a, "2018", "2021", "C:/upload/new/");
		Object[] ur = cr.urlFromEbs(pcvo);//문제 url https://wdown.ebsi.co.kr/W61001/01exam/.....pdf
		
		ArrayList<String> downPList  = (ArrayList<String>)ur[0];
		ArrayList<String> downHList  = (ArrayList<String>)ur[1];
		for(int i = 0 ; i<downPList.size();i++){
			try {
				cr.getEx(downPList.get(i), downHList.get(i),pcvo.getPath());
				//문제를 pcvo에 있는 값에 따라 pdf를 만들어준다.C:/upload/new/m_mun...pdf
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		PersonalMakeVO pmvo = new PersonalMakeVO("C:/upload/new/", "newexample", "매체");
		int count = 0;
		System.out.println();
		int exCount = 0;
		for(int i = 0 ; i<downHList.size();i++){
			count++;
			System.out.println(count);
			ArrayList<String> exListForImage = findEx(downPList.get(i).substring(52), downHList.get(i).substring(52), pmvo);
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
			if(secondP3i.size()!=0){
				
			
			for(int k = 0 ; k <chagedExImage.size();k++){
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
				        File oriFile = new File(oriFilePath);
				        //복사파일객체생성
				        File copyFile = new File(copyFilePath);
				        System.out.println();
				        
				        try {
				            
				            FileInputStream fis = new FileInputStream(oriFile); //읽을파일
				            FileOutputStream fos = new FileOutputStream(copyFile); //복사할파일
				            
				            int fileByte = 0; 
				            // fis.read()가 -1 이면 파일을 다 읽은것
				            while((fileByte = fis.read()) != -1) {
				                fos.write(fileByte);
				            }
				            //자원사용종료
				            fis.close();
				            fos.close();
				            
				        } catch (FileNotFoundException e) {
				            // TODO Auto-generated catch block
				            e.printStackTrace();
				        } catch (IOException e) {
				            // TODO Auto-generated catch block
				            e.printStackTrace();
				        }
				      //해당 파일 카피 완료    
				    EasyImage easyImageMachted = new EasyImage(copyFile);
				    
					int fontSize = 40;//폰트는 10인데 4배차이나니깐.
					int lineDistance = 30;
					int xindex = (int)(firstP3i.get(0).getxIndex()*4);
					int xwidth = 0;
					if(firstP3i.get(0).getxIndex()<(int)((easyImage.getWidth()/4)/2)){
						xwidth = (int)(easyImage.getWidth()-xindex*2)/2+lineDistance;
					}else{
						xwidth = (int)(easyImage.getWidth()-xindex-lineDistance);
					}
					int yindex = (int)(firstP3i.get(0).getyIndex()*4-fontSize);
					int yheight = 0;
					if(firstP3i.get(0).getPage()!=secondP3i.get(0).getPage()){
						yheight = (int)(easyImage.getHeight()-firstP3i.get(0).getyIndex()*4-fontSize);
					}else if((firstP3i.get(0).getPage()==secondP3i.get(0).getPage())&&
							firstP3i.get(0).getyIndex()>secondP3i.get(0).getyIndex()){
						yheight = (int)(easyImage.getHeight()-firstP3i.get(0).getyIndex()*4-fontSize);
					}else{
						yheight = (int)((secondP3i.get(0).getyIndex()-firstP3i.get(0).getyIndex())*4);
					}

					EasyImage cropedImage = easyImageMachted.crop(xindex,//찾는숫자 x인덱스*4, 변동값 final
														   yindex,//변동값 찾는숫자 y인덱스 *2 - 글자 폰트크기(글자 좌측중앙의 위치를찾기때문)
														   xwidth,//변동값 x축의 문제사이의간격*4-문제사이의줄간격 
														   yheight);//변동값 y축의 문제사이의 간격*4
						//세로비율이 4배 가로비율 5배에 줄간격 절댓값 30
					FileOutputStream out = new FileOutputStream(copyFile);
					System.out.println("성공입니다.");
					cropedImage.writeTo(out, "png"); //cropedImage.writeTo(out, "jpg");
				
					
					out.close();
				}
				
			}
			
			}else{//45번인경우
				for(int k = 0 ; k <chagedExImage.size();k++){
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
					        File oriFile = new File(oriFilePath);
					        //복사파일객체생성
					        File copyFile = new File(copyFilePath);
					        System.out.println();
					        
					        try {
					            
					            FileInputStream fis = new FileInputStream(oriFile); //읽을파일
					            FileOutputStream fos = new FileOutputStream(copyFile); //복사할파일
					            
					            int fileByte = 0; 
					            // fis.read()가 -1 이면 파일을 다 읽은것
					            while((fileByte = fis.read()) != -1) {
					                fos.write(fileByte);
					            }
					            //자원사용종료
					            fis.close();
					            fos.close();
					            
					        } catch (FileNotFoundException e) {
					            // TODO Auto-generated catch block
					            e.printStackTrace();
					        } catch (IOException e) {
					            // TODO Auto-generated catch block
					            e.printStackTrace();
					        }
					      //해당 파일 카피 완료    
					    EasyImage easyImageMachted = new EasyImage(copyFile);
					    
						int fontSize = 40;//폰트는 10인데 4배차이나니깐.
						int lineDistance = 30;
						int xindex = (int)(firstP3i.get(0).getxIndex()*4);
						int xwidth = 0;
						if(firstP3i.get(0).getxIndex()<(int)((easyImage.getWidth()/4)/2)){
							xwidth = (int)(easyImage.getWidth()-xindex*2)/2+lineDistance;
						}else{
							xwidth = (int)(easyImage.getWidth()-xindex-lineDistance);
						}
						int yindex = (int)(firstP3i.get(0).getyIndex()*4-fontSize);
						int yheight = 0;
						if(firstP3i.get(0).getPage()!=secondP3i.get(0).getPage()){
							yheight = (int)(easyImage.getHeight()-firstP3i.get(0).getyIndex()*4-fontSize);
						}else if((firstP3i.get(0).getPage()==secondP3i.get(0).getPage())&&
								firstP3i.get(0).getyIndex()>secondP3i.get(0).getyIndex()){
							yheight = (int)(easyImage.getHeight()-firstP3i.get(0).getyIndex()*4-fontSize);
						}else{
							yheight = (int)((secondP3i.get(0).getyIndex()-firstP3i.get(0).getyIndex())*4);
						}

						EasyImage cropedImage = easyImageMachted.crop(xindex,//찾는숫자 x인덱스*4, 변동값 final
															   yindex,//변동값 찾는숫자 y인덱스 *2 - 글자 폰트크기(글자 좌측중앙의 위치를찾기때문)
															   xwidth,//변동값 x축의 문제사이의간격*4-문제사이의줄간격 
															   yheight);//변동값 y축의 문제사이의 간격*4
							//세로비율이 4배 가로비율 5배에 줄간격 절댓값 30
						FileOutputStream out = new FileOutputStream(copyFile);
						System.out.println("성공입니다.");
						cropedImage.writeTo(out, "png"); //cropedImage.writeTo(out, "jpg");
					
						
						out.close();
					}
					
				}
				
			}
			//아래 코드는 문제 숫자를 넣으면 해당 위치를 가지고 있는 객체를 반환합니다.

			
		}
			document.close();
		}
	}
}


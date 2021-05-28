package org.sst.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class pdfMultiFinalTest {
	public static Object[] findExMulti(String example, String answer,PersonalMakeVO pmvo) throws IOException {

		File source2 = new File(pmvo.getPath() + answer);
		PDDocument pdfDoc2 = PDDocument.load(source2);
		String text2 = new PDFTextStripper().getText(pdfDoc2);
		pdfDoc2.close();
		String ahn = text2;
		String reg = "(\\[출제의도\\]){1}\\p{Space}*(" + pmvo.getSearchT()+ ")";

		String[] result = ahn.split(reg);
		Object[] forMulti = new Object[2]; 
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
			while (matcher.find()) {
				count++;
				
				String trans = matcher.group();

				String BefSplit = trans.replace("[", "").replace("]", "").replace(" ", "");
				String[] exRange = BefSplit.split("~");

				ArrayList<String> exMultiStartNumList = new ArrayList<String>();
				ArrayList<String> exMultiEndNumList = new ArrayList<String>();
				System.out.println(text.indexOf(exRange[0] + ".") + "지문시작 부터 지문까지");
								
				exMultiStartNumList.add(exRange[0]);
				exMultiEndNumList.add(exRange[1]);

				int seSNum = Integer.parseInt(exRange[0]);
				int seENum = Integer.parseInt(exRange[1]);

				for (int i = seSNum; i <= seENum; i++) {
					for (int j = 0; j < exNumList.size(); j++) {
						if (i == Integer.parseInt(exNumList.get(j).trim())) {
							System.out.println("일치합니다" + i + " : " + j);
							PDDocument document = PDDocument.load(source);
							String searchMultiTerm = "["+seSNum;//리스트에 들어가야되는 값.
							String searchFirstTerm = seSNum+".";
							pdfFindIndex pfi = new pdfFindIndex();
							ArrayList<PdfIndexInImage> multiP3i = pfi.printSubwords(document, searchMultiTerm);
							forMulti[0] = multiP3i.get(0);//다중지문 인덱스
							ArrayList<PdfIndexInImage> multiFisrtP3i = pfi.printSubwords(document, searchFirstTerm.trim());
							forMulti[1] = multiFisrtP3i.get(0);//다중지문 첫번째 인덱스
							document.close();
							break;
					}
				}
				//System.out.println("지문개수 : "+multiP3i.size());
				}
			}
		return forMulti;
	}
		return forMulti;//null값임
	}
	public static File copyingFile(String oriFilePath,String storepath,String filename,String rename,int count ,String form ){
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
	public static void main(String[] args) throws IOException {
		Crawler cr = new Crawler();
		
		String [] a = {"03"};
		PersonalCrawlerVO pcvo = new PersonalCrawlerVO("1", a, "2018", "2021");
		Object[] ur = cr.urlFromEbs(pcvo);//문제 url https://wdown.ebsi.co.kr/W61001/01exam/.....pdf
		
		ArrayList<String> downPList  = (ArrayList<String>)ur[0];
		ArrayList<String> downHList  = (ArrayList<String>)ur[1];
		int downPListSize =  downPList.size();
		for(int i = 0 ; i<downPListSize;i++){
			try {
				cr.getEx(downPList.get(i), downHList.get(i),pcvo.getPath());//문제를 pcvo에 있는 값에 따라 pdf를 만들어준다.
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		PersonalMakeVO pmvo = new PersonalMakeVO("newexample", "매체");
		int multiCount = 0;//이미지를 위한 것
		int exMultiCount = 0;//매칭된이미지를 위한것.
		
		int downHListSize = downHList.size();
		for(int i = 0 ; i<downHListSize;i++){
			multiCount++;
			System.out.println(multiCount+"번째 다중지문 가져옵니다.");
			if(findExMulti(downPList.get(i).substring(52), downHList.get(i).substring(52), pmvo)!=null){

			Object[] exMultiForImage = findExMulti(downPList.get(i).substring(52), downHList.get(i).substring(52), pmvo);
			
			PdfIndexInImage matchedMulti = (PdfIndexInImage) exMultiForImage[0];
			
			PdfIndexInImage matchedMultiFirst = (PdfIndexInImage) exMultiForImage[1];
		//	System.out.println("다중지문의 x인덱스는 :"+matchedMulti.getxIndex()+"입니다.");
		//	System.out.println("다중지문의 첫번째문제의 x인덱스는 :"+matchedMulti.getxIndex()+"입니다.");

			if(matchedMulti!=null){
			pdfImageTest pit = new pdfImageTest();
			
			File file = new File(pmvo.getPath()+downPList.get(i).substring(52));
			FileInputStream in = new FileInputStream(file);
			ArrayList<String> chagedExImage = (ArrayList<String>) pit.conversionPdf2Img(in, downPList.get(i).substring(52,downPList.get(i).length()-4));
			
			in.close();
			int chagedExImageSize = chagedExImage.size();
			
			int exMultiCountIn = 0;
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
				System.out.println(matchedMulti.getPage()+"페이지이징지잊이지");
				System.out.println(Integer.parseInt(chagedExImage.get(k).substring(imagePath,chagedExImage.get(k).length()-4))+"얻어온페이지인밍밍미");
				if(matchedMulti.getPage()==Integer.parseInt(chagedExImage.get(k).substring(imagePath,chagedExImage.get(k).length()-4))){
						exMultiCount++;//문제를 구분한다.
					 	
						String oriFilePath = chagedExImage.get(k);//복사될 파일경로//chagedExImage.get(k)
				        //파일객체생성
				        File copyFile =  copyingFile(oriFilePath, 
				        		pcvo.getPath(), 
				        		chagedExImage.get(k).
				        		substring(pcvo.getPath().length(),chagedExImage.get(k).length()-4), 
				        		"multiremake", 
				        		exMultiCount, 
				        		".png");
				      //해당 파일 카피 완료    
				    EasyImage easyImageMachted = new EasyImage(copyFile);
				    int ytop = 127;
					int fontSize = 40;//폰트는 10인데 4배차이나니깐.
					int lineDistance = 30;
					int xindex = (int)(matchedMulti.getxIndex()*4);
					
					int xwidth = 0;
					if(matchedMulti.getxIndex()<(int)((easyImage.getWidth()/4)/2-lineDistance)){
						xwidth = (int)(easyImage.getWidth()-xindex*2)/2+lineDistance;
					}else{
						xwidth = (int)(easyImage.getWidth()-xindex-lineDistance);
					}
					int yindex = (int)(matchedMulti.getyIndex()*4-fontSize);
					int yheight = 0;
					System.out.println("결정된xindex :"+xindex+"결정된yindex :"+yindex+"결정된xwidth :"+xwidth);
					if(matchedMulti.getPage()==matchedMultiFirst.getPage()&&
						matchedMulti.getxIndex()==matchedMultiFirst.getxIndex()){
						yheight = (int)((matchedMultiFirst.getyIndex()*4-matchedMulti.getyIndex())*4-fontSize);
						
					}else if((matchedMulti.getPage()==matchedMultiFirst.getPage())&&
							matchedMulti.getxIndex()!=matchedMultiFirst.getxIndex()){

						yheight = (int)(easyImage.getHeight()-matchedMulti.getyIndex()*4+fontSize);
						System.out.println("멀티이페이지같을경우뽑는xindex :"+xindex+"뽑는yindex :"+yindex+"뽑는xwidth :"+xwidth+"뽑는yheight :"+yheight);
						
						EasyImage cropedImage = easyImageMachted.crop(xindex,//찾는숫자 x인덱스*4, 변동값 final
								   yindex,//변동값 찾는숫자 y인덱스 *2 - 글자 폰트크기(글자 좌측중앙의 위치를찾기때문)
								   xwidth,//변동값 x축의 문제사이의간격*4-문제사이의줄간격 
								   yheight);//변동값 y축의 문제사이의 간격*4
						
						FileOutputStream out = new FileOutputStream(copyFile);
						cropedImage.writeTo(out, "png");
						
						exMultiCountIn++;//지문이 길어질경우 사용하는 숫자
						File copyFile2 = copyingFile(oriFilePath,
									pcvo.getPath(),
									chagedExImage.get(k).substring(pcvo.getPath().length(),chagedExImage.get(k).length()-4),
									"multiremake_",
									exMultiCountIn,//그다음 x축처음값부터 y축에서 인덱스숫자까지(파일하나)를 위한
									".png");//파일 한개 더생성
				        easyImageMachted = new EasyImage(copyFile2);
				        xindex = (int)(matchedMultiFirst.getxIndex()*4);//x값 재조정
				        yindex = (int)(ytop*4-fontSize); //y값재조정
				        yheight = (int)(matchedMultiFirst.getyIndex()*4-ytop*4-fontSize);//height값재조정
				        xwidth = (int)(easyImage.getWidth()-xindex-lineDistance);//whidth값 재조정
				        
					}else if((matchedMulti.getPage()!=matchedMultiFirst.getPage())&&
							matchedMulti.getxIndex()>200 && matchedMultiFirst.getxIndex()<200){
						yheight = (int)(easyImage.getHeight()-matchedMulti.getyIndex()*4-fontSize);
						System.out.println("멀티이페이지다를경우뽑는xindex :"+xindex+"뽑는yindex :"+yindex+"뽑는xwidth :"+xwidth+"뽑는yheight :"+yheight);
						EasyImage cropedImage = easyImageMachted.crop(xindex,//찾는숫자 x인덱스*4, 변동값 final
								   yindex,//변동값 찾는숫자 y인덱스 *2 - 글자 폰트크기(글자 좌측중앙의 위치를찾기때문)
								   xwidth,//변동값 x축의 문제사이의간격*4-문제사이의줄간격 
								   yheight);//변동값 y축의 문제사이의 간격*4
						FileOutputStream out = new FileOutputStream(copyFile);
						cropedImage.writeTo(out, "png");
						
						exMultiCountIn++;//지문이 길어질경우 사용하는 숫자
						
						//chagedExImage = "C:/upload/new/pdf이름_숫자.pdf"
						String pdfMakedNum = chagedExImage.get(k).substring(pcvo.getPath().length()+downPList.get(i).substring(52,downPList.get(i).length()-4).length(),
								chagedExImage.get(k).length()-4);//현재이미지파일 숫자
						String oriFilePathLong = pcvo.getPath()//경로 = C:/upload/new/
								+downPList.get(i).substring(52,downPList.get(i).length()-4)//pdf이름
								+((Integer.parseInt(pdfMakedNum)+1)) //다음이미지파일 숫자
								+".png";
						System.out.println("oriFilePathLong :"+oriFilePathLong);
						File copyFile2 = copyingFile(oriFilePathLong,
								pcvo.getPath(),
								downPList.get(i).substring(52,downPList.get(i).length()-4)//pdf이름
								+((Integer.parseInt(pdfMakedNum)+1)),
								"multiremake_",
								exMultiCountIn,//그다음 x축처음값부터 y축에서 인덱스숫자까지(파일하나)를 위한
								".png");//파일 한개 더생성
						System.out.println("pdf이름 : "+"");
				        easyImageMachted = new EasyImage(copyFile2);
				        xindex = (int)(matchedMultiFirst.getxIndex()*4);//x값 재조정
				        yindex = (int)(ytop*4-fontSize); //y값재조정
				        yheight = (int)(easyImage.getHeight()-matchedMultiFirst.getyIndex()*4-fontSize);//height값재조정
				        xwidth = (int)(easyImage.getWidth()-xindex-lineDistance);//whidth값 재조정
						//여기까지 했어요.
					}else if((matchedMulti.getPage()!=matchedMultiFirst.getPage())&&
							matchedMulti.getxIndex()>200 && matchedMultiFirst.getxIndex()<200){
						
					}
					System.out.println("뽑는xindex :"+xindex+"뽑는yindex :"+yindex+"뽑는xwidth :"+xwidth+"뽑는yheight :"+yheight);
					EasyImage cropedImage = easyImageMachted.crop(xindex,//찾는숫자 x인덱스*4, 변동값 final
														   yindex,//변동값 찾는숫자 y인덱스 *2 - 글자 폰트크기(글자 좌측중앙의 위치를찾기때문)
														   xwidth,//변동값 x축의 문제사이의간격*4-문제사이의줄간격 
														   yheight);//변동값 y축의 문제사이의 간격*4

					FileOutputStream out = new FileOutputStream(copyFile);
					System.out.println("성공입니다.");
					cropedImage.writeTo(out, "png");
					out.close();
						}
					}
				}
			}
		}
	}
}

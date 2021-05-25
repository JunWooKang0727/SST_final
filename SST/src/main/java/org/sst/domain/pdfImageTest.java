package org.sst.domain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class pdfImageTest {
	public static List<String> conversionPdf2Img(InputStream is, String uniqueId) {
        List<String> savedImgList = new ArrayList<>(); //저장된 이미지 경로를 저장하는 List 객체
        try {
            PDDocument pdfDoc = PDDocument.load(is); //Document 생성
            PDFRenderer pdfRenderer = new PDFRenderer(pdfDoc);

            String resultImgPath = "C:/upload/new"; //이미지가 저장될 경로
            Files.createDirectories(Paths.get(resultImgPath)); //PDF 2 Img에서는 경로가 없는 경우 이미지 파일이 생성이 안되기 때문에 디렉토리를 만들어준다.

            //순회하며 이미지로 변환 처리
            for (int i=0; i<pdfDoc.getPages().getCount(); i++) {
                String imgFileName = resultImgPath + "/" +uniqueId + (i+1)+ ".png";
					
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
	
	public static void main(String[] args) throws Exception {
		
		//testPDFBoxExtractImages();
		File file = new File("C:/upload/new/mathC_mun_8RSUO5A1.pdf");
		FileInputStream in = new FileInputStream(file);
		ArrayList<String> a = (ArrayList<String>) conversionPdf2Img(in, "mathC_mun_8RSUO5A1.pdf");
		System.out.println(a.size());
		for(int i = 0 ; i <a.size();i++){
			System.out.println(a.get(i));
		}
	}

}

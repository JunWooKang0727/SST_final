package org.sst.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class pdfFindIndex {
	List<TextPositionSequence> findSubwords(PDDocument document, int page, String searchTerm) throws IOException
	{
	    final List<TextPositionSequence> hits = new ArrayList<TextPositionSequence>();
	    PDFTextStripper stripper = new PDFTextStripper()
	    {
	        @Override
	        protected void writeString(String text, List<TextPosition> textPositions) throws IOException
	        {
	            TextPositionSequence word = new TextPositionSequence(textPositions);
	            String string = word.toString();

	            int fromIndex = 0;
	            int index;
	            while ((index = string.indexOf(searchTerm, fromIndex)) > -1)
	            {
	                hits.add(word.subSequence(index, index + searchTerm.length()));
	                fromIndex = index + 1;
	            }
	            super.writeString(text, textPositions);
	        }
	    };

	    stripper.setSortByPosition(true);
	    stripper.setStartPage(page);
	    stripper.setEndPage(page);
	    stripper.getText(document);
	    return hits;
	}
	ArrayList<PdfIndexInImage> printSubwords(PDDocument document, String searchTerm) throws IOException
	{
	    System.out.printf("* Looking for '%s'\n", searchTerm);
	    ArrayList<PdfIndexInImage> p3i = new ArrayList<PdfIndexInImage>();
	    for (int page = 1; page <= document.getNumberOfPages(); page++)
	    {
	        List<TextPositionSequence> hits = findSubwords(document, page, searchTerm);
	        int i = 0;
	        for (TextPositionSequence hit : hits)
	        	
	        {
	        	i++;
	            //TextPosition lastPosition = hit.textPositionAt(hit.length() - 1);
	            System.out.printf("  Page %s at x : %s,y : %s",
	                    page, hit.getX(), hit.getY()
	                    );
	            PdfIndexInImage p3ij = new PdfIndexInImage();
	            p3ij.setPage(page);
	            p3ij.setxIndex(hit.getX());
	            p3ij.setyIndex(hit.getY());
	            p3i.add(p3ij);
	            
	            //lastPosition.getUnicode(), lastPosition.getXDirAdj(), lastPosition.getYDirAdj()
	        }
	    }
		return p3i;
	}
}

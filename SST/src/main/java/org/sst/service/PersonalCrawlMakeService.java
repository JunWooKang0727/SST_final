package org.sst.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.sst.domain.PersonalCrawlerVO;
import org.sst.domain.PersonalMakeVO;

public interface PersonalCrawlMakeService {
	public Object[] urlFromEbs(PersonalCrawlerVO pcvo) throws IOException;
	public void getEx(String downP,String downH, String path) throws IOException;
	public Object[] findEx(String example, String answer,PersonalMakeVO pmvo) throws IOException;
	public void pdfCreate(ArrayList<String> exRealMultiList, ArrayList<String> exRealTextList,ArrayList<String> solRealTextList, PersonalMakeVO pmvo) throws Exception;
	public void pdfDelete(ArrayList<String> downPList, ArrayList<String> downHList,PersonalMakeVO pmvo);
	public List<String> makePdf(PersonalCrawlerVO pcvo, PersonalMakeVO pmvo) throws IOException;
	public ArrayList<String> findExForImage(String example, String answer,PersonalMakeVO pmvo) throws IOException;
	public File copyingFileForImage(String oriFilePath,String storepath,String filename,String rename,int count ,String form);
	public void pdfCreateForImage(ArrayList<String> remakeFilePathList, String makeFileName,PersonalMakeVO pmvo) throws Exception;
	public void makePdfForImage(PersonalCrawlerVO pcvo, PersonalMakeVO pmvo,String reMakeName) throws Exception;
}

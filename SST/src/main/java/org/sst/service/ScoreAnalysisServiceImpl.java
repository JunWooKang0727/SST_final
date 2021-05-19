package org.sst.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sst.mapper.ReportCardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class ScoreAnalysisServiceImpl implements ScoreAnalysisService {
	@Setter(onMethod_ = @Autowired)
	ReportCardMapper mapper;

	@Override
	public HashMap allSubjectScoreSchoolTest(String rc_num) {
		HashMap map = new HashMap();
		map.put("rc_num", rc_num);
		List<String> subjectlist = mapper.schoolTestSubjects(rc_num);
		map.put("list", subjectlist);

		List<HashMap> scorelist = mapper.allSubjectScoreSchoolTest(map);

		HashMap data = new HashMap();

		List<HashMap> title = new ArrayList<HashMap>();

		HashMap col1 = new HashMap();
		col1.put("label", "INFO");
		col1.put("type", "string");
		title.add(col1);

		subjectlist.forEach(subject -> {
			HashMap col2 = new HashMap();
			col2.put("label", subject);
			col2.put("type", "number");
			title.add(col2);
		});

		data.put("cols", title);

		List<HashMap> body = new ArrayList<HashMap>();
		scorelist.forEach(score -> {
			List<HashMap> row = new ArrayList<HashMap>();
			HashMap info = new HashMap();
			info.put("v", score.get("INFO"));
			row.add(info);

			subjectlist.forEach(subject -> {
				HashMap value = new HashMap();
				value.put("v", score.get(subject));
				row.add(value);
			});
			HashMap c = new HashMap();
			c.put("c", row);
			body.add(c);
		});
		data.put("rows", body);

		log.info(data);
		return data;
	}

	@Override
	public HashMap averageSchoolTest(String rc_num) {
		List<HashMap> avglist = mapper.averageSchoolTest(rc_num);

		HashMap data = new HashMap();

		List<HashMap> title = new ArrayList<HashMap>();
		
		HashMap col1 = new HashMap();
		col1.put("label", "INFO");
		col1.put("type", "string");
		title.add(col1);

		HashMap col2 = new HashMap();
		col2.put("label", "avg");
		col2.put("type", "number");
		title.add(col2);

		data.put("cols", title);

		List<HashMap> body = new ArrayList<HashMap>();
		avglist.forEach(score -> {
			List<HashMap> row = new ArrayList<HashMap>();
			HashMap info = new HashMap();
			info.put("v", score.get("ST_TEST"));
			row.add(info);

			HashMap value = new HashMap();
			value.put("v", score.get("AVG"));
			row.add(value);

			HashMap c = new HashMap();
			c.put("c", row);
			body.add(c);
		});

		data.put("rows", body);
		log.info(data);
		return data;
	}

	@Override
	public HashMap allSubjectScoreLicenseTest(String rc_num) {
		HashMap map = new HashMap();
		map.put("rc_num", rc_num);
		List<String> subjectlist = mapper.licenseTestSubjects(rc_num);
		map.put("list", subjectlist);

		List<HashMap> scorelist = mapper.allSubjectScoreLicenseTest(map);

		HashMap data = new HashMap();

		List<HashMap> title = new ArrayList<HashMap>();

		HashMap col1 = new HashMap();
		col1.put("label", "INFO");
		col1.put("type", "string");
		title.add(col1);

		subjectlist.forEach(subject -> {
			HashMap col2 = new HashMap();
			col2.put("label", subject);
			col2.put("type", "number");
			title.add(col2);
		});

		data.put("cols", title);

		List<HashMap> body = new ArrayList<HashMap>();
		scorelist.forEach(score -> {
			List<HashMap> row = new ArrayList<HashMap>();
			HashMap info = new HashMap();
			info.put("v", score.get("INFO"));
			row.add(info);

			subjectlist.forEach(subject -> {
				HashMap value = new HashMap();
				value.put("v", score.get(subject));
				row.add(value);
			});
			HashMap c = new HashMap();
			c.put("c", row);
			body.add(c);
		});
		data.put("rows", body);

		log.info(data);
		return data;
	}

}

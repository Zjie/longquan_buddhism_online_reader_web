package org.longquanzs.olreader.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.longquanzs.olreader.web.model.DocModel;
import org.longquanzs.olreader.web.model.PageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
	@Autowired
	private IndexSearcher indexSearcher;
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	@RequestMapping("/search")
	@ResponseBody
	public PageModel search(@RequestParam(name = "query", required = true) String query,
												@RequestParam(name = "hits", required = false, defaultValue = "10") int hits,
												@RequestParam(name = "page", required = false, defaultValue = "1") int page) throws IOException, ParseException {
		String searchField = "roll_text";
		String decodedQuery = URLDecoder.decode(query, "utf-8");
		QueryParser qp = new QueryParser(searchField, new SmartChineseAnalyzer());
		Query parsedQuery = qp.parse(decodedQuery);
		TopDocs topDocs = indexSearcher.search(parsedQuery, hits * page);
		List<DocModel> hitsDoc = new ArrayList<DocModel>();
		PageModel result = new PageModel();
		result.setResult(hitsDoc);
		result.setTotalNum(topDocs.totalHits);
		result.setPageSize(hits);
		result.setCurPage(page);
		if ((int)topDocs.totalHits % hits == 0) {
			result.setTotalPage((int)topDocs.totalHits/hits);
		} else {
			result.setTotalPage((int)topDocs.totalHits/hits + 1);
		}
		
		int start = hits * (page - 1);
		int end = hits * page > topDocs.totalHits ? (int)topDocs.totalHits : hits * page;
		for (int i = start; i < end; i++) {
			int docId = topDocs.scoreDocs[i].doc;
			Document d = indexSearcher.doc(docId);
			DocModel model = new DocModel(docId, d, false, false);
			hitsDoc.add(model);
		}
		return result;
	}
	
	@RequestMapping("/get")
	@ResponseBody
	public DocModel get(@RequestParam(name = "id", required = true) int id) throws IOException {
		Document d = indexSearcher.doc(id);
		return new DocModel(id, d, true, true); 
	}

}
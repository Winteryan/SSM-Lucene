package com.huawei.lucene;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import com.alibaba.druid.util.StringUtils;
import com.huawei.model.Person;

public class LuceneIndex {
	 private Directory dir = null;
	 /**
	  * 获取IndexWriter实例
	  * @return
	  * @throws Exception
	  */
	 private IndexWriter getWriter()throws Exception {
		 dir = FSDirectory.open(Paths.get("C://lucene"));
		 SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
		 IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		 IndexWriter writer = new IndexWriter(dir, iwc );
		 return writer;
	}
	 /**
	  * 添加索引
	  * @param person
	  * @throws Exception
	  */
	 public void addIndex(Person person)throws Exception{
		 IndexWriter writer = getWriter();
		 Document doc = new Document();
		 doc.add(new StringField("id", String.valueOf(person.getId()), Field.Store.YES));
		 doc.add(new TextField("name",person.getName(),Field.Store.YES));
		 doc.add(new TextField("age",person.getAge(),Field.Store.YES));
		 writer.addDocument(doc);
		 writer.close();
		
	}
	 /**
	  * 更新用户索引
	  * @param person
	  * @throws Exception
	  */
	 public void updateIndex(Person person)throws Exception{
		 IndexWriter writer = getWriter();
		 Document doc = new Document();
		 doc.add(new StringField("id", String.valueOf(person.getId()), Field.Store.YES));
		 doc.add(new TextField("name",person.getName(),Field.Store.YES));
		 doc.add(new TextField("age",person.getAge(),Field.Store.YES));
		 writer.updateDocument(new Term("id",String.valueOf(person.getId())), doc);
		 writer.close();
	 }
	 /**
	  * 删除指定人的索引
	  * @param personId
	  * @throws Exception
	  */
	 public void deleteIndex(String personId)throws Exception{
		 IndexWriter writer = getWriter();
		 writer.deleteDocuments(new Term("id",personId));
		 writer.forceMergeDeletes();
		 writer.commit();
		 writer.close();
	 }
	 /**
	  * 查询用户，q是关键字
	  * @param q
	  * @return
	  * @throws Exception
	  */
	 public List<Person> searchPerson(String q)throws Exception {
		dir = FSDirectory.open(Paths.get("C://lucene"));
		IndexReader reader = DirectoryReader.open(dir);
		IndexSearcher is = new IndexSearcher(reader);
		BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
		QueryParser parser = new QueryParser("name", analyzer);
		Query query = parser.parse(q);
		QueryParser parser2 = new QueryParser("age", analyzer);
		Query query2 = parser2.parse(q);
		booleanQuery.add(query,BooleanClause.Occur.SHOULD);
		booleanQuery.add(query2,BooleanClause.Occur.SHOULD);
		TopDocs hits = is.search(booleanQuery.build(), 100);
		QueryScorer scorer = new QueryScorer(query);
		Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
		SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
		Highlighter highlighter = new Highlighter(simpleHTMLFormatter,scorer);
		highlighter.setTextFragmenter(fragmenter);
		List<Person> personList = new LinkedList<Person>();
		for(ScoreDoc scoreDoc : hits.scoreDocs){
			Document doc = is.doc(scoreDoc.doc);
			Person person = new Person();
			person.setId(Integer.parseInt(doc.get("id")));
			person.setAge(doc.get("age"));
			String name = doc.get("name");
			String age = doc.get("age");
			if(name !=null){
				TokenStream tokenStream = analyzer.tokenStream("name", new StringReader(name));
				String hname = highlighter.getBestFragment(tokenStream, name);
				if(StringUtils.isEmpty(hname)){
					person.setName(name);
				}else {
					person.setName(hname);
				}
			}
			if(age !=null){
				TokenStream tokenStream = analyzer.tokenStream("age", new StringReader(age));
				String hage = highlighter.getBestFragment(tokenStream, age);
				if(StringUtils.isEmpty(hage)){
					if(age.length()<=200){
						person.setAge(age);
					}else {
						person.setAge(age.substring(0,200));
					}
					
				}else {
					person.setName(hage);
				}
			}
			personList.add(person);
		}
		return personList;
	}
	 
}

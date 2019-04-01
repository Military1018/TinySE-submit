package edu.hanyang.submit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.hanyang.indexer.Tokenizer;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.tartarus.snowball.ext.PorterStemmer;

public class TinySETokenizer implements Tokenizer {

	private SimpleAnalyzer analyzer;
	private PorterStemmer stemmer;
	
	public void setup() {
		analyzer = new SimpleAnalyzer();
//		stemmer = new PorterStemmer();
	}

	public List<String> split(String text) {
		
		
		TokenStream stream = analyzer.tokenStream(null, text);
		stream = new PorterStemFilter(stream);
		
		
		CharTermAttribute streamAttr = stream.addAttribute(CharTermAttribute.class);
		try {
			stream.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		List<String> tokens = new ArrayList<>();
		try {
			while (stream.incrementToken()) {
				tokens.add(streamAttr.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tokens;
	}

	public void clean() {
	}

}	
package net.begincode.lucene.analyzer;

import java.io.Reader;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.util.IOUtils;

/**
 * Created by Stay on 2016/9/28  13:22.
 */
public class MyIkAnalyzer extends Analyzer {
    @Override
    protected TokenStreamComponents createComponents(String arg0) {
        Reader reader = null;
        try {
            reader = new StringReader(arg0);
            MyIKTokenizer it = new MyIKTokenizer(reader);
            return new Analyzer.TokenStreamComponents(it);
        } finally {
            IOUtils.closeWhileHandlingException(reader);
        }
    }
}

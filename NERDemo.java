import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.*;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.sequences.DocumentReaderAndWriter;
import edu.stanford.nlp.util.Triple;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/** This is a demo of calling CRFClassifier programmatically.
 *  <p>
 *  Usage: {@code java -mx400m -cp "*" NERDemo [serializedClassifier [fileName]] }
 *  <p>
 *  If arguments aren't specified, they default to
 *  classifiers/english.all.3class.distsim.crf.ser.gz and some hardcoded sample text.
 *  If run with arguments, it shows some of the ways to get k-best labelings and
 *  probabilities out with CRFClassifier. If run without arguments, it shows some of
 *  the alternative output formats that you can get.
 *  <p>
 *  To use CRFClassifier from the command line:
 *  </p><blockquote>
 *  {@code java -mx400m edu.stanford.nlp.ie.crf.CRFClassifier -loadClassifier [classifier] -textFile [file] }
 *  </blockquote><p>
 *  Or if the file is already tokenized and one word per line, perhaps in
 *  a tab-separated value format with extra columns for part-of-speech tag,
 *  etc., use the version below (note the 's' instead of the 'x'):
 *  </p><blockquote>
 *  {@code java -mx400m edu.stanford.nlp.ie.crf.CRFClassifier -loadClassifier [classifier] -testFile [file] }
 *  </blockquote>
 *
 *  @author Jenny Finkel
 *  @author Christopher Manning
 */

public class NERDemo {

	public static String read(String filePath)
    {
        // 读取txt内容为字符串
        StringBuffer txtContent = new StringBuffer();
        // 每次读取的byte数
        byte[] b = new byte[520 * 1024];
        InputStream in = null;
        try
        {
            // 文件输入流
            in = new FileInputStream(filePath);
            while (in.read(b) != -1)
            {
                // 字符串拼接
                txtContent.append(new String(b));
            }
            // 关闭流
            in.close();
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return txtContent.toString();
    }
	

  public static void main(String[] args) throws Exception {
	  String serializedClassifier = "ner-model.ser.gz";
  	  AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
      String s1 = read("F:/Users/Desktop/segment.txt");
      List<List<CoreLabel>> out = classifier.classify(s1);      
      
      int count=0;
      String[] filename =new String[50];
      for (List<CoreLabel> sentence : out) {
        for (CoreLabel word : sentence) {
        	if(word.get(CoreAnnotations.AnswerAnnotation.class).equals("f"))
        	{
        		filename[count]=word.word();
        		if(count!=0)
        		{
        			System.out.print("</"+ filename[count-1] +'>'+'\n');
        		}
        		System.out.print('<'+filename[count]+'>'+'\n');
        		count++;
        	}
        	
        	if(word.get(CoreAnnotations.AnswerAnnotation.class).equals("t"))
        	{
        		System.out.print("<time>"+word.word() +"</time>"+'\n');
        	}
        	
        	if(word.get(CoreAnnotations.AnswerAnnotation.class).equals("l"))
        	{
        		System.out.print("<location>"+word.word() +"</location>"+'\n');
        	}
        	if(word.get(CoreAnnotations.AnswerAnnotation.class).equals("d"))
        	{
        		System.out.print("<defendant>"+word.word() +"</defendant>"+'\n');
        	}
        	if(word.get(CoreAnnotations.AnswerAnnotation.class).equals("c"))
        	{
        		System.out.print("<court>"+word.word() +"</court>"+'\n');
        	}
        	if(word.get(CoreAnnotations.AnswerAnnotation.class).equals("p"))
        	{
        		System.out.print("<procuratorate>"+word.word() +"</procuratorate>"+'\n');
        	}
        	
        }
      }
      System.out.print("</"+ filename[49] +'>'+'\n');
      //System.out.print(count);
      //System.out.println(classifier.classifyToString(s1, "tabbedEntities", false));      
  }
}

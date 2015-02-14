import java.util.List;

import org.snu.ids.ha.index.Keyword;
import org.snu.ids.ha.index.KeywordExtractor;
import org.snu.ids.ha.index.KeywordList;
import org.snu.ids.ha.ma.MExpression;
import org.snu.ids.ha.ma.MorphemeAnalyzer;
import org.snu.ids.ha.ma.Sentence;
import org.snu.ids.ha.util.Timer;


public class Exp
{

	public static void main(String[] args)
	{
//		maTest();
		keTest();
	}


	public static void maTest()
	{
		String string = "형태소 분석할 문자열";
		try {
			MorphemeAnalyzer ma = new MorphemeAnalyzer();
			ma.createLogger(null);
			Timer timer = new Timer();
			timer.start();
			List<MExpression> ret = ma.analyze(string);
			timer.stop();
			timer.printMsg("Time");

			ret = ma.postProcess(ret);

			ret = ma.leaveJustBest(ret);

			List<Sentence> stl = ma.divideToSentences(ret);
			for( int i = 0; i < stl.size(); i++ ) {
				Sentence st = stl.get(i);
				System.out.println("=============================================  " + st.getSentence());
				for( int j = 0; j < st.size(); j++ ) {
					System.out.println(st.get(j));
				}
			}

			ma.closeLogger();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void keTest()
	{
		String strToExtrtKwrd = "형태소 분석할 문자열형태소 분석할 문자열형태소 분석할 문자열형태소 분석할 문자열형태소 분석할 문자열형태소 분석할 문자열형태소 분석할 문자열형태소 분석할 문자열형태소 분석할 문자열형태소 분석할 문자열형태소 분석할 문자열";
		KeywordExtractor ke = new KeywordExtractor();
		KeywordList kl = ke.extractKeyword(strToExtrtKwrd, true);
		for( int i = 0; i < kl.size(); i++ ) {
			Keyword kwrd = kl.get(i);
			System.out.println(kwrd.getString() + "\t" + kwrd.getCnt());
		}
	}

}
//NOT BEING USED
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.lang.StringBuilder;

import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.hcoref.CorefCoreAnnotations;
import edu.stanford.nlp.io.EncodingPrintWriter.out;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

public class QANLPConnector {
	public static String analyze(String storytext) {
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		// Initialize an Annotation with some text to be annotated. The text is
		// the argument to the constructor.
		Annotation annotation = new Annotation(storytext);

		// run all the selected Annotators on this text
		pipeline.annotate(annotation);
		String result = "";
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		List<CorefChainNode> master_coref_list = new ArrayList<CorefChainNode>();
		for (edu.stanford.nlp.hcoref.data.CorefChain cc : annotation
				.get(CorefCoreAnnotations.CorefChainAnnotation.class).values()) {
			System.out.println(cc.toString());
			Iterator<edu.stanford.nlp.hcoref.data.CorefChain.CorefMention> iter = cc.getMentionsInTextualOrder()
					.iterator();
			String root_word = "";
			while (iter.hasNext()) {
				edu.stanford.nlp.hcoref.data.CorefChain.CorefMention cm = iter.next();
				if (root_word.equals(""))
					root_word = cm.mentionSpan;
				if (root_word.indexOf(" ") > -1)
					root_word = root_word.substring(root_word.lastIndexOf(" "));
				System.out.println(cm.mentionSpan + " " + cm.sentNum);
				master_coref_list.add(new CorefChainNode(cm.mentionSpan, root_word, cm.sentNum));
			}

		}
//		try {

//			 Files.write(Paths.get(fileURI), "".getBytes());
			for (int i = 0; i < sentences.size(); ++i) {
				CoreMap test_sentence = sentences.get(i);
				String tmp = test_sentence.get(SemanticGraphCoreAnnotations.BasicDependenciesAnnotation.class)
						.toString(SemanticGraph.OutputFormat.LIST);
				for (CorefChainNode ccn : master_coref_list) {
					if (ccn.line_no - 1 == i) {
						System.out.println(ccn.word + " replaced by " + ccn.root_word);
						tmp = tmp.replaceAll(ccn.word, ccn.root_word);
					}
				}
//				Files.write(Paths.get(fileURI), tmp.getBytes(), StandardOpenOption.APPEND);
				result += tmp;

			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return result;
	}

	public static String convertNLPToOAO(String nlptext) {
		String result = "";
		System.out.println(nlptext);
		// split per root tag
		String[] sentences = nlptext.split("root");
		List<String[]> conjunctions = new ArrayList<String[]>();
		List<String[]> nsub = new ArrayList<String[]>();
		List<String[]> dobj = new ArrayList<String[]>();
		List<String[]> cop = new ArrayList<String[]>();
		List<String[]> compoundprt = new ArrayList<String[]>();

		for (int i = 0; i < sentences.length; ++i) {
			String sentence = sentences[i];
			result += addAmodIfPresent(sentences[i]);
			conjunctions.clear();
			nsub.clear();
			dobj.clear();
			cop.clear();
			compoundprt.clear();
			conjunctions.addAll(getCCConjIfPresent(sentence));
			nsub.addAll(getNsubIfPresent(sentence));
			dobj.addAll(getDobjIfPresent(sentence));
			cop.addAll(getCopIfPresent(sentence));
			compoundprt.addAll(getCompundPrtIfPresent(sentence));

			// look for common connections
			for (String[] nsub_pair : nsub) {
				String afforder = nsub_pair[0].trim();
				String affordance = nsub_pair[1].trim();
				int flag = 0;

				for (String[] dobj_pair : dobj) {
					String affordance_dobj = dobj_pair[0].trim();
					String affordee = dobj_pair[1].trim();
					if (affordance_dobj.equals(affordance)) {
						// match found: sequence of afforder affordance affordee
						result += removeHyphen(afforder) + " " + removeHyphen(affordance) + " " + removeHyphen(affordee)
								+ "\n";
						flag = 1;
					}

					for (String[] cc_result : conjunctions) {
						String conj1 = cc_result[0].trim();
						String conj2 = cc_result[1].trim();
						if (afforder.equals(conj1) && affordance_dobj.equals(affordance)) {
							// we have two statements with different afforders
							result += removeHyphen(conj2) + " " + removeHyphen(affordance) + " "
									+ removeHyphen(affordee) + "\n";

						}
						if (affordance.equals(conj1) && affordance_dobj.equals(conj2)) {
							// we have two statements with the same affordance
							result += removeHyphen(afforder) + " " + removeHyphen(conj2) + " " + removeHyphen(affordee)
									+ "\n";
						}
						if (affordee.equals(conj1) && affordance_dobj.equals(affordance)) {
							// we have two statements with different affordees
							result += removeHyphen(afforder) + " " + removeHyphen(affordance) + " "
									+ removeHyphen(conj2) + "\n";
						}
					}

				}
				
				for (String[] compoundprt_pair : compoundprt) {
					String affordance_compoundprt = compoundprt_pair[0].trim();
					String affordee = compoundprt_pair[1].trim();
					if (affordance_compoundprt.equals(affordance)) {
						// match found: sequence of afforder affordance affordee
						result += removeHyphen(afforder) + " " + removeHyphen(affordance) + " " + removeHyphen(affordee)
								+ "\n";
						flag = 1;
					}

					for (String[] cc_result : conjunctions) {
						String conj1 = cc_result[0].trim();
						String conj2 = cc_result[1].trim();
						if (afforder.equals(conj1) && affordance_compoundprt.equals(affordance)) {
							// we have two statements with different afforders
							result += removeHyphen(conj2) + " " + removeHyphen(affordance) + " "
									+ removeHyphen(affordee) + "\n";

						}
						if (affordance.equals(conj1) && affordance_compoundprt.equals(conj2)) {
							// we have two statements with the same affordance
							result += removeHyphen(afforder) + " " + removeHyphen(conj2) + " " + removeHyphen(affordee)
									+ "\n";
						}
						if (affordee.equals(conj1) && affordance_compoundprt.equals(affordance)) {
							// we have two statements with different affordees
							result += removeHyphen(afforder) + " " + removeHyphen(affordance) + " "
									+ removeHyphen(conj2) + "\n";
						}
					}

				}

				for (String[] cop_pair : cop) {
					String affordance_cop = cop_pair[0].trim();
					String affordee = cop_pair[1].trim();
					if (affordee.equals(affordance)) {
						// match found: sequence of afforder affordance affordee
						result += removeHyphen(afforder) + " " + removeHyphen(affordance_cop) + " "
								+ removeHyphen(affordee) + "\n";
						flag = 1;
					}

					for (String[] cc_result : conjunctions) {
						String conj1 = cc_result[0].trim();
						String conj2 = cc_result[1].trim();
						if (afforder.equals(conj1) && affordee.equals(affordance)) {
							// we have two statements with different afforders
							result += removeHyphen(conj2) + " " + removeHyphen(affordance_cop) + " "
									+ removeHyphen(affordee) + "\n";

						}
						if (affordance.equals(conj1) && affordee.equals(conj2)) {
							// we have two statements with the same affordance
							result += removeHyphen(afforder) + " " + removeHyphen(conj2) + " " + removeHyphen(affordee)
									+ "\n";
						}
						if (affordee.equals(conj1) && affordee.equals(affordance)) {
							// we have two statements with different affordees
							result += removeHyphen(afforder) + " " + removeHyphen(affordance_cop) + " "
									+ removeHyphen(conj2) + "\n";
						}
					}

				}
			}
		}
		out.println(result);
		return result;
	}

	public static String convertOAOToProlog(String oaotext, String outputFile) {
		final String ACTION = "action(%s, %s, %s).\n";
		StringBuilder result = new StringBuilder();
		for (String oao : oaotext.split("\\n")) {
			String[] args = oao.split(" ");
			for (int i = 0; i < args.length; i++) {
				args[i] = args[i].toLowerCase();
			}
			result.append(String.format(ACTION, args[0], args[1], args[2]));
		}
		try {
			Files.write(Paths.get(outputFile), result.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	private static String addAmodIfPresent(String NLPCode) {
		String result = "";
		if (NLPCode.contains("amod(")) {
			String s = NLPCode;
			while (s.indexOf("amod(") > 0) {
				// chop s by the amod tag
				s = s.substring(s.indexOf("amod("));
				String afforder = s.substring(5, s.indexOf(","));
				String attribute = s.substring(s.indexOf(",") + 1, s.indexOf(")"));
				result += afforder.substring(0, afforder.lastIndexOf('-')) + " is "
						+ attribute.substring(0, attribute.lastIndexOf('-')) + "\n";

			}
		}
		return result;
	}

	private static List<String[]> getCCConjIfPresent(String NLPCode) {
		List<String[]> result = new ArrayList<String[]>();

		// check if cc has and
		if (NLPCode.indexOf("cc(") > 0 && NLPCode
				.substring(NLPCode.indexOf("cc("), NLPCode.indexOf(")", NLPCode.indexOf("cc("))).contains(", and")) {
			// and clause exists!
			String s = NLPCode;
			while (s.indexOf("cc(") > 0) {
				// chop s by the cc tag
				s = s.substring(s.indexOf("cc("));
				String conj1 = s.substring(s.indexOf("conj(") + 5, s.indexOf(")", s.indexOf("conj(")));
				String conj2 = conj1.substring(conj1.indexOf(",") + 1);
				conj1 = conj1.substring(0, conj1.indexOf(conj2) - 1);
				String[] tuple = { conj1, conj2 };
				result.add(tuple);
			}

		}
		return result;
	}

	private static List<String[]> getNsubIfPresent(String sentence) {
		List<String[]> result = new ArrayList<String[]>();

		if (sentence.indexOf("nsub") > 0) {
			// it does have at least 1 nsub tag
			int index = sentence.indexOf("nsub");
			while (index >= 0) {
				// using string handling to extract afforder and affordee
				String affordance = sentence.substring(sentence.indexOf('(', sentence.indexOf("nsub", index)) + 1,
						sentence.indexOf(',', sentence.indexOf("nsub", index)));
				String afforder = sentence.substring(sentence.indexOf(',', sentence.indexOf("nsub", index)) + 2,
						sentence.indexOf(')', sentence.indexOf("nsub", index)));
				index = sentence.indexOf("nsub", index + 1);
				if (afforder.equalsIgnoreCase(affordance))
					continue;
				result.add(new String[] { afforder, affordance });
				out.println(afforder + "!!@!!" + affordance);
			}
		}
		return result;
	}

	private static List<String[]> getDobjIfPresent(String sentence) {
		List<String[]> result = new ArrayList<String[]>();
		while (sentence.indexOf("dobj(") >= 0) {
			String affordee;
			String affordance;
			sentence = sentence.substring(sentence.indexOf("dobj("));
			// look for dobj with affordance

			affordance = sentence.substring(sentence.indexOf("dobj(") + 5,
					sentence.indexOf(",", sentence.indexOf("dobj(")));
			affordee = sentence.substring(sentence.indexOf(',', sentence.indexOf("dobj(") + 2) + 2,
					sentence.indexOf(')', sentence.indexOf("dobj(")));
			sentence = sentence.substring(sentence.indexOf("dobj(") + 1);

			if (affordance.equalsIgnoreCase(affordee))
				continue;
			String[] args = { affordance, affordee };
			result.add(args);
		}
		return result;
	}

	private static List<String[]> getCopIfPresent(String sentence) {
		List<String[]> result = new ArrayList<String[]>();
		while (sentence.indexOf("cop(") >= 0) {
			String affordee;
			String affordance;
			sentence = sentence.substring(sentence.indexOf("cop("));
			affordee = sentence.substring(sentence.indexOf(',', sentence.indexOf("cop(")) + 2,
					sentence.indexOf(')', sentence.indexOf("cop(")));
			affordance = sentence.substring(sentence.indexOf("cop(") + 4,
					sentence.indexOf(",", sentence.indexOf("cop(")));
			sentence = sentence.substring(sentence.indexOf("cop(") + 1);
			String tmp = null;
			tmp = affordance;
			affordance = affordee;
			affordee = tmp;

			if (affordance.equalsIgnoreCase(affordee))
				continue;
			String[] args = { affordance, affordee };
			result.add(args);
		}
		return result;
	}

	//compound:prt works the same as nsub
	private static List<String[]> getCompundPrtIfPresent(String sentence) {
		List<String[]> result = new ArrayList<String[]>();
		while (sentence.indexOf("compound:prt(") >= 0) {
			String affordee;
			String affordance;
			sentence = sentence.substring(sentence.indexOf("compound:prt("));
			// look for dobj with affordance

			affordance = sentence.substring(sentence.indexOf("compound:prt(") + 13,
					sentence.indexOf(",", sentence.indexOf("compound:prt(")));
			affordee = sentence.substring(sentence.indexOf(',', sentence.indexOf("compound:prt(") + 2) + 2,
					sentence.indexOf(')', sentence.indexOf("compound:prt(")));
			sentence = sentence.substring(sentence.indexOf("compound:prt(") + 1);

			if (affordance.equalsIgnoreCase(affordee))
				continue;
			String[] args = { affordance, affordee };
			result.add(args);
		}
		return result;
	}
	
	public static int getMinOfTwo(int a, int b) {
		if (a < 0 && b < 0)
			return -1;
		if (a < 0)
			return b;
		if (b < 0)
			return a;
		return Math.min(a, b);

	}

	private static String removeHyphen(String s) {
		return s.substring(0, s.lastIndexOf('-'));
	}
}

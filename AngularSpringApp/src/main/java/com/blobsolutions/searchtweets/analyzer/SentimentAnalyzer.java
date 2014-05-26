package com.blobsolutions.searchtweets.analyzer;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class SentimentAnalyzer {

	private StanfordCoreNLP stanfordCoreNLP;
	//private List<Tree> trees;

	public SentimentAnalyzer(StanfordCoreNLP stanfordCoreNLP) {
		super();
		//trees = new ArrayList<Tree>();
		this.stanfordCoreNLP = stanfordCoreNLP;
	}

	public Tweet findSentiment(String line) {

		/*int mainSentiment = 0;
		String sentimentAsString = "";
		if (line != null && line.length() > 0) {
			int longest = 0;
			Annotation annotation = getStanfordCoreNLP().process(line);
			for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
				Tree tree = sentence.get(SentimentCoreAnnotations.AnnotatedTree.class);

				//List<CoreLabel> list = sentence.get(TokensAnnotation.class);
				//computeRole(list);
				
				int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
				
				String partText = sentence.toString();
				if (partText.length() > longest) {
					sentimentAsString = sentence.get(SentimentCoreAnnotations.ClassName.class);
					mainSentiment = sentiment;
					longest = partText.length();
					trees.add(tree);
				}

			}
		}
		if (mainSentiment > 4 || mainSentiment < 0) {
			return null;
		}

		TweetWithSentiment tweetWithSentiment = new TweetWithSentiment(line, mainSentiment, sentimentAsString);*/
		Tweet tweetWithSentiment = new Tweet(line, 1, "Neutral");
		return tweetWithSentiment;

	}

	/*private void computeRole(List<CoreLabel> list) {
		if(list != null) {
			for (CoreLabel coreLabel : list) {
				String value = coreLabel.get(CoreAnnotations.ValueAnnotation.class);
				String pos = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
				
				System.out.println("Word: " + value + " POS: " + pos);
			}
		}
		
	}*/

	public StanfordCoreNLP getStanfordCoreNLP() {
		return stanfordCoreNLP;
	}

	public void setStanfordCoreNLP(StanfordCoreNLP stanfordCoreNLP) {
		this.stanfordCoreNLP = stanfordCoreNLP;
	}

	public void evaluate() {
		//Evaluate evaluate = new Evaluate(new SentimentModel(new RNNOptions(),new ArrayList<Tree>()));
		//evaluate.eval(trees);

		//evaluate.printSummary();
	}
}
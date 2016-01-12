import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;

import java.io.IOException;
import java.util.ArrayList;

public class NLPHelper {

	 public static final String PATHCAT3 = "english.all.3class.distsim.crf.ser.gz";
	    public static final String PATHCAT4 = "english.conll.4class.distsim.crf.ser.gz";
	    public static final String PATHCAT7 = "english.muc.7class.distsim.crf.ser";
	   
		
	public static String NERTagging(String sentence) throws IOException
    {
      String serializedClassifier = PATHCAT7;
      AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
      return classifier.classifyToString(sentence);
    }
	
	public Tweet UpdateTweetText(Tweet tweet) throws IOException
	{
		String originalTweetText = tweet.GetTweetText();
		String updatedTweetText = NERTagging(originalTweetText);
		
		System.out.println(updatedTweetText);
		
		tweet = UpdateTweetObject(tweet,updatedTweetText);
		
		return tweet;
	}
	/*
	The/O Marine/ORGANIZATION National/ORGANIZATION Park/ORGANIZATION is/O situated/O in/O Pune/LOCATION district/O which/O Putin/PERSON wants/O to/O visit/O even/O if/O it/O's/O at/O midnight/TIME on/O a/O Sunday/DATE and/O the/O tickets/O are/O priced/O $/MONEY100/MONEY
	*/
	
	private Tweet UpdateTweetObject(Tweet objTweet, String text)
	{
		String [] arrWords = text.split(" ");
		String tmpWord = "";
		String [] arrLocations = new String[2];
		ArrayList arrTweetLocations = new ArrayList();
		String [] arrOrganizations = new String[2];
		ArrayList arrTweetOrganizations = new ArrayList();
		String [] arrPeople = new String[2];
		ArrayList arrTweetPeople = new ArrayList();
		
		//update with Location details
		if(arrWords!=null && arrWords.length>0)
		{
			for(int i=0;i<arrWords.length;i++)
			{
				tmpWord = arrWords[i];
				if(tmpWord.contains("/LOCATION"))
				{
					arrLocations = tmpWord.split("/");
					arrTweetLocations.add(arrLocations[0]);
				}
			}
		}
		
		//update with ORGANIZATION details
		if(arrWords!=null && arrWords.length>0)
		{
			for(int i=0;i<arrWords.length;i++)
			{
				tmpWord = arrWords[i];
				if(tmpWord.contains("/ORGANIZATION"))
				{
					arrOrganizations = tmpWord.split("/");
					arrTweetOrganizations.add(arrOrganizations[0]);
				}
			}
		}
		
		
		//update with PERSON details
		if(arrWords!=null && arrWords.length>0)
		{
			for(int i=0;i<arrWords.length;i++)
			{
				tmpWord = arrWords[i];
				if(tmpWord.contains("/PERSON"))
				{
					arrPeople = tmpWord.split("/");
					arrTweetPeople.add(arrPeople[0]);
				}
			}
		}
		
		objTweet.SetTweetLocation(arrTweetLocations);
		objTweet.SetTweetOrganization(arrTweetOrganizations);
		objTweet.SetTweetPeople(arrTweetPeople);
		
		return objTweet;
	}
}

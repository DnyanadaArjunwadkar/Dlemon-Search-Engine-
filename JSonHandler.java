import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSonHandler 
{
	
	JSONParser parser = new JSONParser();
	TweetsList currentList = null;
	ArrayList<Tweet> currentTweetList = null;
	
	private static final String  str_text_en = "text_en";
	private static final String str_text_de = "text_de";
	private static final String str_created_at = "created_at";
	private static final String str_lang = "lang";
	private static final String str_tweet_urls = "tweet_urls";
	private static final String str_tweet_hashtags = "tweet_hashtags";
	private static final String str_id = "id";
	private static final String str_locations = "locations";
	private static final String str_organizations = "organizations";
	private static final String str_people = "person";
	
	public void ReadJSonFile(File file)
	{
		try {

			Object obj = parser.parse(new FileReader(file));
			Tweet newTweet = new Tweet();
		
			JSONArray jarray = (JSONArray)obj;
			NLPHelper helper= new NLPHelper();
			
			currentList = new TweetsList();
			currentTweetList = new ArrayList<Tweet>();
			
			if(jarray!=null && jarray.size()>0)
			{
				currentList.SetAllTweets(currentTweetList);
				for(int i=0;i<jarray.size();i++)
				{
					Object jsonOb = jarray.get(i);
					JSONObject jsonObject= (JSONObject)jsonOb;

					Tweet objTweet = polpulateTweetObject(jsonObject);
					newTweet = helper.UpdateTweetText(objTweet);
					currentList.getAllTweets().add(newTweet);
				}
			}
			System.out.println("File :"+file.getName()+"  Total tweets: "+currentTweetList.size());
			//String fileName = getFilesFirstName(file.getName());
			WriteJSonFile(file.getName(),currentList.getAllTweets());
				

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
		
	private Tweet polpulateTweetObject(JSONObject jobj)
	{
		Tweet objTweet = new Tweet();
		
		String twText = (String) jobj.get(str_text_en);
		if(twText == null)
			twText = (String) jobj.get(str_text_de);
		
		objTweet.SetTweetText(twText);
	
		objTweet.SetTweetID((String) jobj.get(str_id));
		
		objTweet.SetTweetLang((String) jobj.get(str_lang));
		
		objTweet.SetTweetTime((String) jobj.get(str_created_at));
		
	
	JSONArray arrURLs = (JSONArray) jobj.get(str_tweet_urls);
		ArrayList tweetURLList = new ArrayList();
		Iterator<String> iterator = arrURLs.iterator();
		while (iterator.hasNext()) {
			tweetURLList.add(iterator.next());
		}
		objTweet.SetTweetURLs(tweetURLList);
		
		JSONArray arrHashTags = (JSONArray) jobj.get(str_tweet_hashtags);
		ArrayList tweetHTList = new ArrayList();
		iterator = arrHashTags.iterator();
		while (iterator.hasNext()) {
			tweetHTList.add(iterator.next());
		}
		objTweet.SetTweetHashTags(tweetHTList);
		
		return objTweet;
	}
	
	
	public void WriteJSonFile(String fileName, ArrayList<Tweet> allTweets)
		{
		FileWriter file = null;
		String updatedFileName = "New"+fileName;
			try {
				file = new FileWriter(updatedFileName,true);
				file.write("[\n");
				Tweet tweet = new Tweet();
				int noOfTweets = allTweets.size();
				if(allTweets !=null && noOfTweets>0)
				{
					for(int i=0;i<noOfTweets;i++)
					{
						tweet = allTweets.get(i);
						JSONObject objJason = new JSONObject();
						
						
						objJason.put(str_text_en, tweet.GetTweetText());
						objJason.put(str_created_at, tweet.GetTweetTime());
						objJason.put(str_lang, tweet.GetTweetLang());
						
						
						ArrayList hashTags = tweet.GetTweetHashTags();
						JSONArray listHashTags = new JSONArray();
						if(listHashTags!=null && hashTags.size()>0)
						{
							
							for(int j=0;j< hashTags.size();j++)
							{
								listHashTags.add(hashTags.get(j));
							}
						}
						objJason.put(str_tweet_hashtags, listHashTags);
						objJason.put(str_id, tweet.GetTweetID());

						ArrayList locations = tweet.GetTweetLocation();
						JSONArray listLocations = new JSONArray();
						if(locations!=null && locations.size()>0)
						{
							
							for(int j=0;j< locations.size();j++)
							{
								listLocations.add(locations.get(j));
							}
						}
						objJason.put(str_locations, listLocations);
						
						
						ArrayList people = tweet.GetTweetPeople();
						JSONArray listPeople = new JSONArray();
						if(people!=null && people.size()>0)
						{
							
							for(int j=0;j< people.size();j++)
							{
								listPeople.add(people.get(j));
							}
						}
						objJason.put(str_people, listPeople);
						
						ArrayList orgnizations = tweet.GetTweetOrganization();
						JSONArray listOrgaization = new JSONArray();
						if(orgnizations!=null && orgnizations.size()>0)
						{
							
							for(int j=0;j< orgnizations.size();j++)
							{
								listOrgaization.add(orgnizations.get(j));
							}
						}
						objJason.put(str_organizations, listOrgaization);
						
						file.write(objJason.toJSONString());
						if(i<noOfTweets-1)
							file.write(",");//separate tweets by comma
						file.write("\n");
						
					}
					//after writting all tweets, close the file
					
					file.write("]");
					file.flush();
					file.close();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
}

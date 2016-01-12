import java.util.ArrayList;

public class TweetsList {
	private ArrayList<Tweet> tweetList;
	
	public ArrayList<Tweet> getAllTweets()
	{
		return this.tweetList;
	}
	
	public void SetAllTweets(ArrayList<Tweet> list)
	{
		this.tweetList = list;
	}
}

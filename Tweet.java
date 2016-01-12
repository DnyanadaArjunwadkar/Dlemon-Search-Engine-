import java.util.ArrayList;

public class Tweet {
	
	private String TweetText;
	private String TweetID;
	private ArrayList TweetURLs;
	private ArrayList TweetHashtags;
	private String TweetLang;
	private String Tweettime;
	
	private ArrayList TweetLocation;
	private ArrayList TweetPeople;
	private ArrayList TweetOrganization;
	
	
	public void SetTweetText(String msg)
	{
		this.TweetText=msg;
	}
	
	public String GetTweetText()
	{
		return this.TweetText;
	}
	public void SetTweetTime(String time)
	{
		this.Tweettime=time;
	}
	
	public String GetTweetTime()
	{
		return this.Tweettime;
	}
	public void SetTweetLang(String lang)
	{
		this.TweetLang=lang;
	}
	
	public String GetTweetLang()
	{
		return this.TweetLang;
	}
	public void SetTweetID(String id)
	{
		this.TweetID=id;
	}
	
	public String GetTweetID()
	{
		return this.TweetID;
	}
	public void SetTweetLocation(ArrayList loc)
	{
		this.TweetLocation=loc	;
	}
	
	public ArrayList GetTweetLocation()
	{
		return this.TweetLocation;
	}
	
	public void SetTweetPeople(ArrayList ppl)
	{
		this.TweetPeople = ppl	;
	}
	
	public ArrayList GetTweetPeople()
	{
		return this.TweetPeople;
	}
	
	public void SetTweetOrganization(ArrayList org)
	{
		this.TweetOrganization = org	;
	}
	
	public ArrayList GetTweetOrganization()
	{
		return this.TweetOrganization;
	}
	
	public void SetTweetURLs(ArrayList urls)
	{
		this.TweetURLs=urls	;
	}
	
	public ArrayList GetTweetURLs()
	{
		return this.TweetURLs;
	}
	public void SetTweetHashTags(ArrayList hashtags)
	{
		this.TweetHashtags=hashtags	;
	}
	
	public ArrayList GetTweetHashTags()
	{
		return this.TweetHashtags;
	}
}

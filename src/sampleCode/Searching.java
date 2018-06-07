package sampleCode;

import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Searching {

	//Se puede configurar desde un .properties o desde las propiedades del sistema.
	public static ConfigurationBuilder getConfigurationBuilder(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("3LYLJuZqB0K8s1OLQTUt0i6Rn")
		  .setOAuthConsumerSecret("Sh1RV0KrgSUMDAZUs40E9rE72qT1IMYBDlxTzwZvemSQAXnEx3")
		  .setOAuthAccessToken("1001264914546286594-nJJH4B7IevCYXO7A44ITCmGHD9TmQ2")
		  .setOAuthAccessTokenSecret("LgrZ4hjlVFg2ryUnGntHoMSTDXbTi8LENq41aUewBHjJl");
		return cb;
	}
	
	public static ConfigurationBuilder getConfigurationBuilderFromFile(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		
		return cb;
	}

	// Busqueda de tweets a partir de un query.
	public static void main(String[] args) throws TwitterException {
		
		String qq = "macri";
		
        Twitter twitter = new TwitterFactory(getConfigurationBuilder().build()).getInstance();

            Query query = new Query(qq);
            QueryResult result = twitter.search(query);
           
            while ((query = result.nextQuery()) != null){
                 List<Status> tweets = result.getTweets();
             
                 for (Status tweet : tweets) {
                     System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                 }
                 result = twitter.search(query);
            }
          
        }
}

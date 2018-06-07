package sampleCode;

import java.io.IOException;
import java.util.List;

import cmu.arktweetnlp.Tagger;
import cmu.arktweetnlp.Tagger.TaggedToken;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class ConsumingAndProcessing {

	public static Tagger tagger = new Tagger();
	static String whatTag = "N";
	
	private static int countTags(String text){
		List<TaggedToken> taggedTokens = tagger.tokenizeAndTag(text);
		int count = 0;
		for(TaggedToken t : taggedTokens)
			if(t.tag.equals(whatTag))
				count++;
		return count;
	}
	
	static class CustomListenerProcess implements StatusListener{
		
		public void onStatus(Status status) {
			
//            System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            System.out.println("@" + status.getUser().getScreenName() + " - " + whatTag+" "+countTags(status.getText()));
        }

        @Override
        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
//            System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
        }

        @Override
        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
//            System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
        }

        @Override
        public void onScrubGeo(long userId, long upToStatusId) {
//            System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
        }

        @Override
        public void onStallWarning(StallWarning warning) {
//            System.out.println("Got stall warning:" + warning);
        }

        @Override
        public void onException(Exception ex) {
            ex.printStackTrace();
        }
	}
		
	public static void main(String[] args) throws IOException {
		
		System.out.println("Hello Streaming Twitter!");
		tagger.loadModel("model.20120919");
		
		TwitterStream twitterStream = new TwitterStreamFactory(Searching.getConfigurationBuilder().build()).getInstance();
		twitterStream.addListener(new CustomListenerProcess());
        twitterStream.sample();
	}
	
}

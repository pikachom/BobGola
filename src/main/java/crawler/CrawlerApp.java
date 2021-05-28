package crawler;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.*;
import java.util.Properties;

public class CrawlerApp {
    ConfigReader cr;
    ConfigurationBuilder cb;
    TwitterFactory tf;
    Twitter twitter;
    public CrawlerApp() {
        this.cb = new ConfigurationBuilder();
        try {
            this.cr = new ConfigReader();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String consumerKey = cr.getPropertyValue("CONSUMER_KEY");
        String consumerSecret = cr.getPropertyValue("CONSUMER_SECRET");
        String accessToken = cr.getPropertyValue("ACCESS_TOKEN");
        String accesstokenSecret = cr.getPropertyValue("ACCESS_TOKEN_SECRET");

        cb.setDebugEnabled(true)
            .setOAuthConsumerKey(consumerKey)
            .setOAuthConsumerSecret(consumerSecret)
            .setOAuthAccessToken(accessToken)
            .setOAuthAccessTokenSecret(accesstokenSecret);

        this.tf = new TwitterFactory(cb.build());
        this.twitter = tf.getInstance();
    }
    public void doCrawl() throws IOException, TwitterException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("tweets.txt"));
        Query query = new Query("메뉴");
        QueryResult result = twitter.search(query);
        for(Status status : result.getTweets()){
            bw.write("@" + status.getUser().getScreenName() + ":"
                        + status.getText() + "|||"
                        + status.getRetweetCount() + "\r\n");
        }
        bw.close();

    }

    public static void main(String[] args) {

        CrawlerApp app = new CrawlerApp();
        try {
            app.doCrawl();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TwitterException e) {
            e.printStackTrace();
        }


    }



}

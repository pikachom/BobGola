package crawler;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import static java.time.LocalTime.now;

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
    public Query buildQuery(String keyword){
        Query query = new Query(keyword);

        Date yesterday = new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L);
        String yesterdayStr = yesterday.toInstant()
                .atOffset(ZoneOffset.UTC)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        query.setSince(yesterdayStr);
        query.setCount(100);
        return query;

    }
    public void doCrawl() throws IOException, TwitterException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("tweets.txt"));
        Query query = buildQuery("점심");
        QueryResult result = twitter.search(query);
        for(Status status : result.getTweets()){
            bw.write("@" + status.getUser().getScreenName() + ":"
                        + status.getText() + "\r\n");
        }
//        System.out.println(result.getTweets().size());
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

package crawler;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.*;
import java.util.Properties;

public class CrawlerApp {
    ConfigurationBuilder cb;
    TwitterFactory tf;
    Twitter twitter;
    public CrawlerApp() {
        this.cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("cwEwGKMytce9EghqsV4CJApXs")
                .setOAuthConsumerSecret("QglxPmMEqTnOIZTbhBGoLJ874enXY7ky4dq9Rydk5p0rWQGbGc")
                .setOAuthAccessToken("245304374-jc9nEywA1ftVVbo8j83mKcQPfjFVyxaOJxpaWTZG")
                .setOAuthAccessTokenSecret("MeIVBtor9pqf0Bew24XL46LlK7LqDPe9dfCmqwCbl5y6X");
//        String resource = "main/resources/twitter.properties";
//        Properties properties = new Properties();
//        try{
//            InputStream reader = getClass().getClassLoader().getResourceAsStream(resource);
////            Reader reader = Resources.getResourceAsReader(resource);
//            properties.load(reader);
//            String consumerKey = properties.getProperty("consumerkey");
//            String consumerSecret = properties.getProperty("consumersecret");
//            String accessToken = properties.getProperty("accesstoken");
//            String accesstokenSecret = properties.getProperty("accesstokensecret");
//            cb.setDebugEnabled(true)
//                    .setOAuthConsumerKey(consumerKey)
//                    .setOAuthConsumerSecret(consumerSecret)
//                    .setOAuthAccessToken(accessToken)
//                    .setOAuthAccessTokenSecret(accesstokenSecret);
//
//        } catch (IOException e){
//            System.out.println("config file not found");
////            e.printStackTrace();
//        }

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

package ranking;

import java.io.*;
import java.util.*;

public class WordCounter {
    private final String resource = "src/main/resources/menu.list";
    private BufferedReader br;
    private HashSet<String> menuSet;

    public WordCounter(){
        this.menuSet = menuSetBuilder(resource);

    }
    public HashMap<String, Integer> doCount(){

        /*
        * TODO
        *  menuSet은 만들어뒀으니 Tweets를 가지고 wordcount 수행...
        *  https://118k.tistory.com/790 참고하는중
        *  tweets.split("@").flatmap(x -> x.split(" "))
        *  -> menuset의 원소와 같은 것이 있으면 대체하고, 없으면 "_" 따위로 치환
        *  (예시)
        *   "나는 제육덮밥을아주 많이 먹어버렸다"
        *   -> {_, 제육덮밥, _, _}
        * */



        return null;
    }
    private HashSet<String> menuSetBuilder(String resource){
        if(this.menuSet!=null){
            return this.menuSet;
        }else{
            HashSet<String> set = new HashSet();
            String oneLine;
            try {
                br = new BufferedReader(new FileReader(resource));
                while((oneLine = br.readLine()) != null){
                    set.add(oneLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return set;
        }
    }

    public static void main(String[] args) {
        WordCounter wc = new WordCounter();
        for(String menu : wc.menuSet){
            System.out.println(menu);
        }
    }

}

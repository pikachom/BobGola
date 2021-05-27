# BobGola
* 지금 트위터에서 가장 핫한 점심 메뉴를 골라주는 봇


## 구성요소

------

* CrawlerApp
  * Twitter4j API를 사용해서 search 수행    
    * Input
      * 메뉴 Dictionary
      * 검색 시간대
      * 그 외 변수화 하면 좋을 것들
    * Output
      * 수집된 트윗 데이터(파일 저장? 메모리에서 Ranking App으로 데이터 직접 전달?)

    * (ASIS) 정해진 query keyword에 해당하는 트윗을 가져와서 파일로 저장
    * (TODO)
      * 메뉴 Dictionary 설정
      * Dictionary에 해당하는 tweet들 수집
      * 쿼리 조건 상세 설정, 변수화하기

----------
* RankingApp(TODO)
  * 수집된 tweet에서 가장 핫한 메뉴명 도출(wordcount)
  * telegram bot으로 전송 
    
    


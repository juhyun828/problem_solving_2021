# Algorithm

-------------------

> - since 210119~
>
> - 1일 1 commit  목표
> - 문제 사이트별로, 날짜 순으로 기록합니다.
> - README에 기록하지 않으며 풀던 문제들은 복습하며 추가 중 입니다.



## 백준

| 문제번호                                                     | 풀이                                                         | 문제명         | 분류                | 날짜                      | memo                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ | -------------- | ------------------- | ------------------------- | ------------------------------------------------------------ |
| [9663]([www.acmicpc.net/problem/9663](https://www.acmicpc.net/problem/9663)) | [9663](https://github.com/jhk828/Algorithm/blob/master/BJ/Main_BJ_9663_NQueen.java) | N-Queen        | `백트랙킹`          | 21/03/02                  | [1행/ 1열/ 1대각선 -> 1퀸](https://jhk0307.tistory.com/328)  |
| [17135]([www.acmicpc.net/problem/17135](https://www.acmicpc.net/problem/17135)) | [17135](https://github.com/jhk828/Algorithm/blob/master/BJ/Main_BJ_17135_%EC%BA%90%EC%8A%AC%EB%94%94%ED%8E%9C%EC%8A%A4.java) | 캐슬 디펜스    | `시뮬레이션`        | 21/03/04                  | [1) 궁수들이 동시에 여러 적을 노릴 수 있음<br />2) Class의 `compareTo()` 재정의하여 사용하기](https://jhk0307.tistory.com/337) |
| [17406]([www.acmicpc.net/problem/17406](https://www.acmicpc.net/problem/17406)) | [17406](https://github.com/jhk828/Algorithm/blob/master/BJ/Main_BJ_17406_%EB%B0%B0%EC%97%B4%EB%8F%8C%EB%A6%AC%EA%B8%B04_2.java) | 배열 돌리기4   | `시뮬레이션` `순열` | 21/02/10<br />21/03/05    | [1) 배열 복사해서 rotate()해야 다음 경우에 영향 x<br />2) `map[or][oc]` <- `map[nr][nc]` 순서 / 회전 순서 유의](https://jhk0307.tistory.com/300) |
| [3109](https://www.acmicpc.net/problem/3109)                 | [3109](https://github.com/jhk828/Algorithm/blob/master/BJ/Main_BJ_3109_%EB%B9%B5%EC%A7%91.java) | 빵집           | `dfs` `백트랙킹`    | 21/02/20<br />21/03/06    | [가지치기 2가지<br />1) 성공하여 return true를 한 경우 다음 방향으로 탐색x<br />2) 3방 모두 탐색 불가능해도 `visited[r][c]`값 초기화x](https://jhk0307.tistory.com/327) |
| [17070](https://www.acmicpc.net/problem/3109)                | [17070](https://github.com/jhk828/Algorithm/blob/master/BJ/Main_BJ_17070_%ED%8C%8C%EC%9D%B4%ED%94%84%EC%98%AE%EA%B8%B0%EA%B8%B01.java) | 파이프 옮기기1 | `시뮬레이션` `bfs`  | 21/03/06                  | 방향(가로 / 세로 / 대각선)마다 다른 이동조건 확인            |
| [17281](https://www.acmicpc.net/problem/17281)               | [17281](https://github.com/jhk828/Algorithm/blob/master/BJ/Main_BJ_17281_%EC%95%BC%EA%B5%AC_fail.java) | ⚾              |                     | 21/03/09 ~ <br />21/03/10 | **미해결** - 순열/ nextPermutation으로 푸는 중               |



## SWEA

| 문제번호                                                     | 풀이                                                         | 문제명            | 분류                        | 날짜                   | memo                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ----------------- | --------------------------- | ---------------------- | ------------------------------------------------------------ |
| [1767](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV4suNtaXFEDFAUf&categoryId=AV4suNtaXFEDFAUf&categoryType=CODE&problemTitle=1767&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1&&&&&&&&&) | [1767](https://github.com/jhk828/Algorithm/blob/master/SW%20Expert/Solution_D_1767_%ED%94%84%EB%A1%9C%EC%84%B8%EC%84%9C%EC%97%B0%EA%B2%B0%ED%95%98%EA%B8%B0_2.java) | 프로세서 연결하기 | `부분집합` `백트래킹` `DFS` | 21/02/25<br />21/03/01 | [1) 전선 교차x <br />2) 4방향 모두 탐색할 필요 x](https://jhk0307.tistory.com/335) |
| [1749](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PoOKKAPIDFAUq&categoryId=AV5PoOKKAPIDFAUq&categoryType=CODE&problemTitle=1949&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1&&&&&&&&&) | [1749](https://github.com/jhk828/Algorithm/blob/master/SW%20Expert/Solution_%EB%AA%A8%EC%9D%98_1949_%EB%93%B1%EC%82%B0%EB%A1%9C%EC%A1%B0%EC%84%B1.java) | 등산로 조성       | `DFS`                       | 21/03/11               | 새 위치의 등산로를 깎을 때, 현재값-1로 깎기 X                |
| [1952](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpFQaAQMDFAUq&categoryId=AV5PpFQaAQMDFAUq&categoryType=CODE&problemTitle=%EC%88%98%EC%98%81%EC%9E%A5&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1) | [1952](https://github.com/jhk828/Algorithm/blob/master/SW%20Expert/Solution_%EB%AA%A8%EC%9D%98_1052_%EC%88%98%EC%98%81%EC%9E%A5.java) | 수영장            | `DFS`                       | 21/03/11               | `DP`로도 풀어보기                                            |



## Code Up

| 문제번호                                                  | 풀이                                                         | 문제명 | 분류          | 날짜                | memo             |
| --------------------------------------------------------- | ------------------------------------------------------------ | ------ | ------------- | ------------------- | ---------------- |
| [기초 100제](https://codeup.kr/problemsetsol.php?psid=23) | [1~49번](https://github.com/jhk828/Algorithm/tree/master/CodeUp/CodeUp100%EC%A0%9C/1~49)<br />[50~100번](https://github.com/jhk828/Algorithm/tree/master/CodeUp/CodeUp100%EC%A0%9C/50~100) |        | `Java 사용법` | 21/01/19 ~ 21/01/16 | 입출력 방법 주의 |



## JUNGOL




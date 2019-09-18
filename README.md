# 눈사람 게임

* 프로젝트명 : The Snowman
* 개발기간 : 2017.10.27~2017.12.15 (6주/4명)
* 개발환경
  * OS : Window
  * IDE : Eclipse
  * Language : JAVA
*  프로젝트 목적 
  * 이전에 개발했었던 C언어 게임과 다르게 그래픽과 창작성에 크게 초점을 맞춰 퍼즐게임을 제작한다. 특히, 캐릭터가 맵을 이동하면서 얻게 되는 이벤트와 까다로운 조건들을 처리하는 능력을 향상시킨다. 또한 libGDX라는 오픈소스 JAVA 게임개발엔진을 통하여 보다 넓은 개발환경을 접한다.

<br>

### 세부 기능

---

1. 캐릭터 이동, 점프, 눈 쌓기, 눈 던지기
2. 가시 블록에 맞으면 게임 종료
3. 움직이는 블록은 타이밍에 맞춰 탑승
4. 시간제한 맵에서는 정해진 시간 내에 맵 통과해야 다음 스테이지로 이동
5. 발판을 만들기 위해 눈사람은 눈을 쌓아 이동 가능
6. 마지막 스테이지는 8단계

<br>

### 중요 자료구조

---

1. 캐릭터

   * enum State{FALLING, JUMPING, STANDING, RUNNING, DEAD}; //캐릭터 상태
   * TextureRegion snowmanStand;  //게임 시작 시 캐릭터 모습
   * Animation snowmanRun;       //캐릭터가 움직일 때 애니메이션처럼 보이기 위함
   * State currentState;       //캐릭터의 현재상태
   * State previousState;      //캐릭터의 이전 상태

2. 움직이는 블록

   * int x = 0, int y = 0;        // 현재 블록의 위치
   * float speed = 2;            // 블록 이동 속도
   * int direction;                // 블록 이동 상태 (0 : 정지 , 1 : 오른쪽으로 이동, 2 : 왼쪽으로 이동)

   - int minPositionX, minPostionY; // 블록이 움직일 수 있는 최소 위치 값
   - int maxPositionX, maxPositionY; // 블록이 움직일 수 있는 최대 위치 값

3. 눈 출력

   * float playerX, playerY;   //캐릭터 앞에 눈을 쌓을 것이기 때문에 캐릭터 좌표를 통해 눈 생성위치 결정

   - float timecount;       //게임 카운트다운
   - Integer score;          //점수 출력

<br>

### UI

---

1. 메인화면 및 게임설명 화면

   ![img1](/img/01.png)

   ![img2](/img/02.png)

2. 스테이지 화면

   * 전체 스테이지 (stage1 ~ stage8)

   ![img3](/img/03.png)

   * stage 3

   ![img4](/img/04.png)

   * stage 8

   ![img5](/img/05.png)

3. 게임종료 및 클리어화면

   ![img6](/img/06.png)

   ![img7](/img/07.png)

<br>
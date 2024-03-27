# 기능 요구사항

## Board
- [x] 초기의 체스판에 기물을 적절한 위치와 컬러에 맞게 세팅한다.
- [x] 민들어진 체스판을 보여준다.
- [x] 플레이어의 색을 받아서 해당 색의 기물을 움직인다.
  - ⛔️️[x] 플레이어의 색과 움직이는 기물의 색이 다를 경우 예외가 발생한다.
- [x] 이동위치를 받아 기물을 움직이다.
- [x] 상대방의 기물이 있는 위치에 이동하면, 현재 기물로 덮어쓴다.
- [x] 남아있는 기물 컬러별로 점수를 계산한다.
  - [x] 폰의 경우에는 같은 세로줄에 같은 색의 폰이 있으면 0.5점을 준다.

## Piece
- [x] 폰, 킹, 나이트, 퀸 등등으로 이루어져 있다.
- [x] 점수를 가지고 있다.
  - [x] 퀸은 9점, 룩은 5점, 비숍은 3점, 나이트는 2.5점, 킹은 0점, 폰은 1점이다.
- [x] 해당 위치로 이동할 수 있는지 판별한다.
  - [x] true면 해당 위치로 이동할 수 있다.

## Game
- [x] 사용자에게 Start를 전달받으면 게임을 시작한다.
- [x] 사용자에게 End를 전달받으면 게임을 종료한다.
- [x] 사용자에게 {move source위치 target위치}를 입력받는다. 
  - [x] WHITE와 BLACK 순으로 돌아가면서 입력받는다.
  - ⛔️️[x] 입력형식은 `move b2 b3`와 같은 형식으로 입력되지 않으면 예외가 발생한다.
- [] 사용자에게 status를 전달 받으면 색상별 점수를 보여준다.
  - [] WHITE와 BLACK 순으로 돌아가면서 입력받는다.
- [] 킹을 잡은 플레이어가 우승하고 게임을 종료시킨다.

## Square
- [x] Rank를 가지고 있다.
- [x] File을 가지고 있다.
  - ⛔️️[x] 이동 위치가 유효하지 않으면 예외가 발생한다.

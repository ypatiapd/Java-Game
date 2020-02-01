package fidaki;


public class Player {
int playerId;
String name;
int score;
Board board;
//����� constructor ������ Player
public Player() {
	playerId=0;
	name=" ";
	score=0;
	board=new Board();
}
//constructor �� �������� ������ Player
//������� ����� ��� ��� ���������� ��� �� ����������� �� ��������
//�������� � 3�� constructor ��� ������ Board .
public Player(int pId,String na,int sc,Board BO) {
	setPlayer(pId,na,sc);
	board=new Board(BO);
}
public void setPlayerId(int ID) {
	playerId=ID;
}
public void setName(String na) {
	name=na;
}
public void setScore(int sc) {
	score=sc;
}

public int getPlayerId() {
	return playerId;
}
public String getName() {
	return name;
}
public int getScore() {
	return score;
}
public Board getBoard() {
	return board;
}
public void setPlayer(int ID, String NA,int SC) {
	playerId=ID;
	name=NA;
	score=SC;
}
//� ������� move ������������� ��� ������ ��� ������ ������� �� ��� ���� ��� ������ (die).
//�� ���������� sn,la,apr,apb, ID ����� �� ����� ��� ������ ��� �� ���������� ��� ��� ������.
//� ��������� done ��������������� ���� ����� while ��� ������� ���� true ���� ���� ��������� � ������ ��� ������ ���
//��������� �� ��������� ��� ������ ��� ��� �������� ������ ������ � ���� ������.
//��� ���� ��� while, �������� ����� ������ for,��� � ������� ������� �� ��� �D ��� ��������� � ������� ������� ����, ����� � ���� .
//�� ���������� sna ��� lad �������� ���� true �� � ������� ��������� ��� ���� � ������ �����.�� ��� ����� ��� while
//��� ��� ����� ��� ����� ����� true,� ������ ��������������� ��� �� ������� �� ��� �D ��� ������� � �������.
//�����,������������� ��� ������������ � ������� pin �� ��� ����� ��� �����.

	public int[] move(int id,int die ) {
	int sn=0,la=0,apr=0,apb=0,done=0;
	int ID=id+die;
	
	while(done==0) {
    boolean lad=false;
    boolean sna=false;
	for(int i=0;i<board.getSnakesLength();i++) {
	     
		if( (ID)== (board.getSnakes(i).getHeadId())) {
		   System.out.println("oups!se dagose fidi");
		   sna=true;
		   ID=board.getSnakes(i).getTaleId();
		   sn++;break;
		}
	}
	for(int i=0;i<board.getLaddersLength();i++) {
		if(((ID)==((board.getLadders(i)).getDownStep())&&((board.getLadders(i)).getBroken()!=true))) {
			ID=board.getLadders(i).getUpStep();
			la++;
			System.out.println("anevikes skala");
			
		}    
	}
	
	for(int i=0;i<board.getApplesLength();i++) {
		if((ID)==((board.getApples(i)).getAppleTileId())) {
			if ((board.getApples(i)).getColor()=="red") {
				if((board.getApples(i)).getPoints()==5) {
					setScore(score+=5);
					(board.getApples(i)).setPoints(0);
					apr++;
					System.out.println("efages kokkino milo 5 vathmwn");
                    break;
				}
				else {setScore(score+=10);
				System.out.println("efages kokkino milo 10 vathmwn");
				apr++;
				break;
				}

			}
			else {
				if(( board.getApples(i)).getPoints()==-5) {
				    setScore(score-=5);
				    (board.getApples(i)).setPoints(0);
				    apb++;
				    System.out.println("efages mavro milo -5 vathmwn");
				    break;
				}
				else {setScore(score-=10);
				   apb++;
				   System.out.println("efages mavro milo -10 vathmwn");break;
				   
				}
				
			 }
		  }
	  }
	if(lad==true)continue;
	if(sna==true)continue;
	done=1;
	}
	int[] pin = {0,0,0,0,0};
	pin[0]=ID;
	pin[1]=sn;
	pin[2]=la;
	pin[3]=apr;
	pin[4]=apb;

	return pin;
}
		
}


package fidaki;
import java.util.Random;
public class Board {
int N=0,M=0;
int[][] tiles;
snake[] snakes;
Ladder[] ladders;
Apple[]  apples;
//����� constructor ������ Board
public Board() {
	N=0;
	M=0;	
}
//constructor ������ Board �� �������� 
//��������������� �� ���������� �,� ��� �������������� �� ������ ��� ������� ������� �� �� ��������
public Board(int n, int m,int sn,int la,int ap) {
	 N=n;
	 M=m;
	 tiles=new int[N][M];
	 snakes=new snake[sn];
	 ladders=new Ladder[la];
	 apples=new Apple[ap];   
}
//constructor �� ������ ����������� Board
//�� ���������� ��� ���� ������������ ����� Board �������� ��� ����� ��� ����������� board
//��� �� ���������� ��� ������� snakes,tiles,ladders ��� apples ,�� 4 for loops ����������� ��� ���� ���
//�� ��� ������������ bo.
// �� ������� ��� for �������� ������� ���� �� ������� ��� �������.
public Board(Board bo) {
	
	N=bo.getN();
	M=bo.getM();
	 tiles=new int[N][M];
	 for(int i=0;i<N;i++) {
		 for(int j=0;j<M;j++)
			 tiles[i][j]=bo.getTiles(i,j);
	 }
	 snakes=new snake[bo.getSnakesLength()];
	 for(int i=0;i<bo.getSnakesLength();i++)
		setSnakes(i,bo.getSnakes(i));
	 ladders=new Ladder[bo.getLaddersLength()];
	 for(int i=0;i<bo.getLaddersLength();i++)
			setLadders(i,bo.getLadders(i));
	 apples=new Apple[bo.getApplesLength()];
	 for(int i=0;i<bo.getApplesLength();i++)
			setApples(i,bo.getApples(i));
}
int getN() {
	return N;
}
int getM() {
	return M;
}
//getter ��� ��� ������ ������������ ��� ������ snakes
int getSnakesLength() {
	return snakes.length;
}
//getter ��� ��� ������ ������������ ��� ������ ladders

int getLaddersLength() {
	return ladders.length;
}
//getter ��� ��� ������ ������������ ��� ������ apples
int getApplesLength() {
	return apples.length;
}
public void setN(int n) {
	N=n;
}
public void setM(int m) {
	M=m;
}
public void setTiles(int n,int m,int val) {
	tiles[n][m]= val;
}
public int  getTiles(int n,int m) {
	return tiles[n][m];
}
public void setSnakes(int num,snake val) {
	snakes[num]=val;
}
public void setLadders(int num,Ladder val) {
	ladders[num]=val;
}
public void setApples(int num,Apple val) {
	apples[num]=val;
}

//getter ��� ���������� ����������� snake ��� ��� ������ snakes
	public snake getSnakes(int num) {
	return snakes[num];
}
	//getter ��� ���������� ����������� Ladder ��� ��� ������ Ladders
public Ladder getLadders(int num) {
	return ladders[num];
}
//getter ��� ���������� ����������� apple ��� ��� ������ apples

public Apple getApples(int num) {
	return apples[num];
}
//getter ��� ���������� ��� ��������� ��� ������ tiles ��� ��� ���� �� ���� val

public int getTilesX(int val) {
	for(int i=0;i<N;i++) {
		for(int j=0;j<M;j++) {
			if(tiles[i][j]==val)
				return i;
		}
	}
	return 0;
}
//getter ��� ���������� ��� ��������� ��� ������ tiles ��� ��� ���� �� ���� val

public int getTilesY(int val) {
	for(int i=0;i<N;i++) {
		for(int j=0;j<M;j++) {
			if(tiles[i][j]==val)
				return j;
		}
	}
	return 0;
}
//� ��������� createBoard ���������� ����� ������� ����� ���� ��������������� ������ ��� ������� tiles,
//snakes,ladders ��� apples.������ �� ���� if ������, ����� ����� �� ������� ����� ���� ������� ��� ������
//tiles �� ���� id,��� ���� ����� �� �������� �����, ������� �� �� ������ ��� ���������.
//������ �������� ������� ����� ���� ���������� headId kai taleId ��� ������ snakes ��� upStep,downStep ��� ������ 
//ladders �� ��� for loop ��� ��� ���� ������ �� ������� ���� ������ �� ������� ��� ������.H ��������� temp ���������������
//��� �� ����������� ��� ����� ������ ���� (taleId ��� snakes ��� downStep ��� ladders.)�� boolean ���������� sameS ,sameL
//�������� ���� ��������� ���� ������ �� ����� �� �������� ��� ������� ������ �� ��� taleId , ��� �� ��� ��������� � ������� ������
//���� 1 ��� �� ����������� �� ������������ ����.�� ���� ������� ��� ����� ��� ��� ������ ladders �� �������� ��� downStep �� ��� taleId.
//�� ���� ��� for ��� ��� ��������� temp ��������� �� �������� ����� ��� ������ apples. �������������� ������ ��� ������� ���������� 
//check1,check2 ��� �������� ������ ���� 0 � 1, ��� ��� ������� �������� �����(��������� int col) ��� ������(��������� int po).������ � ��������� check3 ������� �� ������� 
//��� ������ ������ ��� id ��� ��������� �� ����������� �� ���� ��� �� ���, �� ���� ��������������� ���������� ��� ������ ���� 1.
void createBoard() {
	for(int z=0;z<N;z++) {
		   if(z%2==0)
		   {
			   for(int j=0;j<M;j++)
				   tiles[z][j]=j+1+((N-z-1)*M);
		   }
		   else {
			   for(int j=0;j<M;j++)
				   tiles[z][j]=(N-z-1)*M +M-j;
		   }	
	}
	Random num=new Random();
	int temp=0;
	boolean sameS=false;
	boolean sameL=false;
	for (int i=0;i<snakes.length;i++) {
		temp=1+num.nextInt(tiles[0][M-1]);
		for(int j=0;j<i;j++) {
			if(temp+num.nextInt(tiles[0][M-1]-temp)==snakes[j].getHeadId())
				sameS=true;
		}
		if(sameS==false)
		snakes[i]=new snake(i,temp+num.nextInt(tiles[0][M-1]-temp),temp);	
		else i--;
	}
	temp=0;
	sameS=false;
	for (int i=0;i<ladders.length;i++) {
		temp=1+num.nextInt(tiles[0][M-1]);
		for(int j=0;j<i;j++) {
			if(temp==ladders[j].getDownStep())
				sameL=true;
		}
		for(int z=0;z<snakes.length;z++) {
			if(temp==snakes[z].getHeadId())
				sameS=true;
		}
		if((sameL==false)&&(sameS==false))
		ladders[i]=new Ladder(i,temp+num.nextInt(tiles[0][M-1]-temp),temp,false);	
		else i--;
	}
	for(int i=0;i<apples.length;i++) {
		temp=1+num.nextInt(tiles[0][M-1]);
		int check1=num.nextInt(1);
		int check2=num.nextInt(1);
		String col;
		int po;
		if (check1==0) {
			col="red";
		    if(check2==0)
			po=5;
		    else po=10;
		}
		else {
			col="black";
		    if(check2==0)
			    po=-5;
		    else po=-10;
		}		
		boolean check3=false;
		
		    for(int z=0;z<snakes.length;z++) {
			     if(temp==snakes[z].getHeadId())
				    check3= true;
		     }
		    if(check3==false)
			   apples[i]=new Apple(i,temp,col,po);
		    else i--;
		 
	}
}
//� ������� createElementBoard() ���������� ����� ������� ��� ������������� ��� ����������
// �� �����, �� ������ ��� �� ���� ��� ������ ��� ���� �������.
//������ �� ������� ��������������� ������ �� ��� ���� "_"
//������ �� ��� for �� ���� �� ������� ��� ������ snakes, ��������� �� headId ��� �� taleId ��� ���� snake
//��� ������� ��� ���������� id ��� ������ ElementBoardSnakes[][] ��� ���������� ����������.
// �������� ���������� ������������ ��� ���� ������� �lementBoardLadders[][] ,ElementBoardApples[][]
//����� ������������ �� ����� �������
void createElementBoard() {
	String[][] ElementBoardSnakes= new String[N][M];
	String[][] ElementBoardLadders= new String[N][M];
	String[][] ElementBoardApples= new String[N][M];
	for(int i=0;i<N;i++) {
		for(int j=0;j<M;j++) {
			ElementBoardSnakes[i][j]=" _ ";
			ElementBoardLadders[i][j]=" _ ";
			ElementBoardApples[i][j]=" _ ";
		}
	}
	
	for(int i=0;i<snakes.length;i++) {
		ElementBoardSnakes[getTilesX(snakes[i].getHeadId())][getTilesY(snakes[i].getHeadId())]="SH"+i;
		ElementBoardSnakes[getTilesX(snakes[i].getTaleId())][getTilesY(snakes[i].getTaleId())]="ST"+i;	
	}
	for(int i=0;i<ladders.length;i++) {
		ElementBoardLadders[getTilesX(ladders[i].getUpStep())][getTilesY(ladders[i].getUpStep())]="LU"+i;
		ElementBoardLadders[getTilesX(ladders[i].getDownStep())][getTilesY(ladders[i].getDownStep())]="LD"+i;
	}
	for(int i=0;i<apples.length;i++ ) {
		if(apples[i].getColor()=="red")
		ElementBoardApples[getTilesX(apples[i].getAppleTileId())][getTilesY(apples[i].getAppleTileId())]="AR"+i;
		else
			ElementBoardApples[getTilesX(apples[i].getAppleTileId())][getTilesY(apples[i].getAppleTileId())]="AB"+i;
	}
	System.out.println("       elementBoardSnakes");
	for (int i=0;i<N;i++) { 
		for(int j=0;j<M;j++) {
            System.out.print(ElementBoardSnakes[i][j]+" ");			
		}
		System.out.println(" ");
	}
	System.out.println(" ");
	System.out.println("        elementBoardLadders");
	for (int i=0;i<N;i++) { 
		for(int j=0;j<M;j++) {
            System.out.print(ElementBoardLadders[i][j]+" ");			
		}
		System.out.println(" ");
	}
	System.out.println(" ");
	System.out.println("         elementBoardApples");
	for (int i=0;i<N;i++) { 
		for(int j=0;j<M;j++) {
            System.out.print(ElementBoardApples[i][j]+" ");			
		}
		System.out.println(" ");
	}		
}
}

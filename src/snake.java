package fidaki;

public class snake {
	int snakeId;
	int headId;
	int taleId;
//����� constructor ������ snake	
public snake(){	
    this(0,0,0);
    }
//Constructor ������ snake �� �������� 
public	snake(int s,int h,int t){
	snakeId=s;
	headId=h;
	taleId=t;

}
//Constructor �� ������ ����������� snake
public snake(snake sn) {
	snakeId=sn.getSnakeId();
	headId=sn.getHeadId();
    taleId=sn.getTaleId();
}

public void setSnakeId(int sId) {
    snakeId=sId;
}
public void setHeadId(int hId) {
	headId=hId;
}
public void setTaleId(int tId) {
	taleId=tId;
}
public int getSnakeId() {
	return snakeId;
}
public int getHeadId() {
	return headId;
}
public int getTaleId() {
	return taleId;
}

}
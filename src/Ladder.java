package fidaki;

public class Ladder {
int ladderId;
int upStep;
int downStep;
boolean broken;

//κενός constructor κλάσης Ladder
	public Ladder(){	
    this(0,0,0,false);
    }
//Constructor  κλάσης Ladder με ορίσματα
public	Ladder(int l,int u,int d,boolean b){
	setLadder(l,u,d,b);
	}
public void setLadder(int lId,int uS,int dS,boolean br) {
	setLadderId(lId);
	setUpStep(uS);
	setDownStep(dS);
	setBroken(br);
}
//constructor με όρισμα αντικείμενο Ladder
public Ladder(Ladder La) {
	this(La.getLadderId(),La.getUpStep(),La.getDownStep(),La.getBroken());
}
public void setLadderId(int lId) {
    ladderId=lId;
}
public void setUpStep(int uS) {
	upStep=uS;
}
public void setDownStep(int dS) {
	downStep=dS;
}
public void setBroken(boolean br) {
	broken=br;
}
public int getLadderId() {
	return ladderId;
}
public int getUpStep() {
	return upStep;
}
public int getDownStep() {
	return downStep;
}
public boolean getBroken() {
	return broken;
}

}


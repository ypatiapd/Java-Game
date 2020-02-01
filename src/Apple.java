package fidaki;

public class Apple {
	int appleId;
	int appleTileId;
	String color;
	int points;
	//κενός  κλάσης apple constructor 
	public Apple(){	
	    this(0,0," ",0);
	    }
	//constructor με ορίσματα κλάσης Apple
	public	Apple(int aI,int atI,String c,int p){
		setApple(aI,atI,c,p);
		}
	public void setApple(int aI,int atI,String c,int p) {
		setAppleId(aI);
		setAppleTileId(atI);
		setColor(c);
		setPoints(p);
	}
	// constructor με όρισμα αντικείμενο Apple
	public Apple(Apple ap) {
		this(ap.getAppleId(),ap.getAppleId(),ap.getColor(),ap.getPoints());
	}
	public void setAppleId(int aI) {
	    appleId=aI;
	}
	public void setAppleTileId(int atI) {
		appleTileId=atI;
	}
	public void setColor(String c) {
		color=c;
	}
	public void setPoints(int p) {
		points=p;
	}
	public int getAppleId() {
		return appleId;
	}
	public int getAppleTileId() {
		return appleTileId;
	}
	public String getColor() {
		return color;
	}
    public int getPoints() {
    	return points;
    }
}


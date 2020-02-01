package fidaki;
import java.util.ArrayList;
public class Node {
	
	Node parent;
	ArrayList<Node> children;
	int  nodeDepth;
	int[] nodeMove;
	Board nodeBoard;
	double nodeEvaluation;
	
	public Node() {
		parent=null;
		children = new ArrayList<Node>();
		nodeDepth=0;
		nodeMove=new int[2];
		nodeBoard=new Board();
		nodeEvaluation=0;
	}
	//asto peiramatiko
	public Node(Board bo) {  //methodos domisis gia komvous -rizes tou dentrou
		parent= null;
		children = new ArrayList<Node>();
		nodeDepth=0; 
		nodeMove=new int[2];
		nodeBoard=new Board(bo);
		nodeEvaluation=0;
	}
	
	public Node(Node par,  int id, int dice,Board bo) { //methodos domisis gia komvous tou dentrou /oxi riza
		parent=new Node();
		parent=par;
		children = new ArrayList<Node>();
		nodeDepth=par.getNodeDepth()+1;
		nodeMove=new int[2];
		nodeMove[0]=id;
		nodeMove[1]=dice;
		nodeBoard=new Board(bo); 
		nodeEvaluation=0;  //diorthwsi
	}
	
	Node getParentNode() {
		return parent;
	}

	Node getChildNode(int i) {
		return children.get(i);
		
	}
	int getNodeDepth() {
		return nodeDepth;
	}
	int getNodeMove(int i) {
		return nodeMove[i];
	}
	Board getNoadBoard() {
		return nodeBoard;
	}
	double getNodeEvaluation() {
		return nodeEvaluation;
	}
	
	void setParentNode(Node par) {
		parent=par;
	}
	void setNodeDepth(int de) {
		nodeDepth=de;
	}
	void setNodeMove(int val,int i) {
		nodeMove[i]=val;
	}
	void setNoadBoard(Board bo) {
		nodeBoard=bo;
	}
	void setNodeEvaluation(double ev) {
		nodeEvaluation=ev;
	}
	void addChildren(Node Child) {
		children.add(Child);
	}
	int getNumberOfChildren() {
		return children.size();
	}
	
}

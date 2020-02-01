package fidaki;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MinMaxPlayer extends Player {

	ArrayList<int[]> path;// lista pou apothikevei enan pinaka me plirofories se kathe gyro
	int[] roundInfo;// pinakas pou krataei tis plirofories tou kathe girou
	int steps = 0;// vimata se ena giro
	int bestDice = 0;// i kaliteri zaria me vasi tin sinartisi stoxou
	int gainPoints = 0;// synolikoi pontoi se ena giro
	// methodos domisis xoris orismata

	public MinMaxPlayer() {
		super();// klisi methodou domisis tis klasis player
		path = new ArrayList<int[]>();
		roundInfo = new int[8];
	}

	// methodos domisis me orismata
	public MinMaxPlayer(int pId, String na, int sc, Board BO) {
		super(pId, na, sc, BO);
		path = new ArrayList<int[]>();

	}

	// H sinartisi evaluate ektimaei tin kinisi tou paikti gia kathe pithani zaria ,
	// metrwntas ta vimata
	// pou tha kanei kai tous pontous pou tha kerdisei, kai epistrefei tin sinartisi
	// stoxou me aytes tis times
	// H metavliti newPos apothikevei ti nea thesi prosthetwntas stin palia thesi
	// tin zaria .Epeita
	// se ena vroxo while elegxetai ksexwrista gia ola ta fidia, tis skales, kai ta
	// mila tou tablo,
	// an i nea thesi tou paikti periexei kefali fidiou, katw meros skalas i milo,me
	// treis vroxous for.
	// se periptwsi yparxei kati apo ayta i metavlites steps kai gainPoints
	// ayksanontai i meiwnontai analogws
	// pou katelikse o paiktis .Oi metavlites SNA kai lAD xrisimopoiountai etsi wste
	// an sti nea thesi tou
	// paikti yparxei kefali fidiou i skala,pairnoun timi true, etsi wste o vroxos
	// while na synexisei
	// na trexei kai na ginei neos elegxos gia ti nea thesi tou paikti. se periptwsi
	// pou oi dyo aytes meavlites
	// exoun sto telos tou vroxou timi false, tote h roi vgainei ap to vroxo kai
	// epistrefei tin sinartisi
	// stoxou me ta vimata kai tous pontous pou metrise i evaluate .
	public double evaluate(int currentPos, int dice, Board bo) {
		int newPos = 0;
		newPos = currentPos + dice;
		steps = dice; // arithmos vimatwn an o paixtis den tyxei pouthena
		gainPoints = 0;
		boolean done = false;
		while (done == false) {
			boolean LAD = false;
			boolean SNA = false;
			for (int i = 0; i < bo.getSnakesLength(); i++) {

				if (newPos == bo.getSnakes(i).getHeadId()) {
					steps += bo.getSnakes(i).getTaleId() - newPos; // arnitikos arithmos vimatwn giati tsibithike apo
					SNA = true; // yparxei kefali fidiou sti sigekrimeni thesi
					newPos = bo.getSnakes(i).getTaleId();// nea thesi stin oura tou fidiou
				}
			}
			for (int i = 0; i < bo.getLaddersLength(); i++) {
				if (newPos == bo.getLadders(i).getDownStep()) {
					steps += bo.getLadders(i).getUpStep() - newPos;
					newPos += bo.getLadders(i).getUpStep();// nea thesi sto panw meros tis skalas
					LAD = true;// o paitkis anevike skala ara o vroxos while epanalamvanetai
				}
			}
			for (int i = 0; i < bo.getApplesLength(); i++) {
				if (newPos == bo.getApples(i).getAppleId()) {
					gainPoints += bo.getApples(i).getPoints();
				}
			}
			if (SNA == true)
				continue;
			if (LAD == true)
				continue;
			done = true;
		}
		return targetFunction(steps, gainPoints);

	}

	//H sinartisi getNextMove() arxika dimiourgei ton komvo root me orisma to tablo tou trexontos paixnidiou. 
	//epeita dimiourgei to dentro vathous dyo kinisewn kai apothikevei stin metavliti bestDice tin kaliteri zaria 
	// tis sinartisis chooseMinMaxMove().
	//kaloume tin move gia ton paikti me orisma tin bestDice kai enimerwnoume tis metavlites STEPS kai GAINPOINTS.
	//dimiourgoume ton pinaka roundInfo[] gia na prosthesoume tis plirofories tou girou.
	//stis theseis 5,6,7 apothikevontai oi metavlites STEPS,BestDice,GAINPOINTS pou enimerwthikan proigoumenws 
	// enw stis prwtes theseis oi times tou pinaka pou epestrepse i move().Prosthetoume ton pinaka roundInfo sto path
	//kai epistrefoume tin kainouria thesi tou paikti (roundInfo[0]).
	//Epeidi sto sigekrimeno paixnidi paizei enas MinMaxPlayer kai enas koinos Player, den epireazei pote
	// o enas tin kinisi tou allou logw grigoris diaforas sto tablo. Epomenws gia tin orthi leitourgia tou programmatos
	// se periptwsi pou i chooseMinMaxMove epistrepsei -1, diladi oi ektimiseis tis evaluate itan oi idies gia
	//oles tis zaries, tote o paikteis kalei tin evaluate kai epilegei tin kaliteri zaria.
	int getNextMove(int currentPos, int oppCurrentPos) {
		bestDice = 0;
		int STEPS = 0;
		int GAINPOINTS = 0;
		int[] info = {0,0,0,0,0}; 
		double eval ;
		double maxEval=0;
		Node root = new Node(board);
		createMySubtree(root, 1, currentPos, oppCurrentPos);
		bestDice = chooseMinMaxMove(root);
		if(bestDice==-1) {
			for (int i = 0; i < 6; i++) {
				eval = evaluate(currentPos, i + 1,board);
				if (eval > maxEval) {
					maxEval = eval;
					bestDice = i + 1;
					STEPS=steps;// apothikeysi stin metavliti STEPS tin timi tis metavlitis steps gia tin kaliteri zaria 
					GAINPOINTS=gainPoints;//apothikeysi stin metavliti GAINPOINTS tis metavlitis gainPoints gia tin kaliteri zaria 

				}
			}
		}
		System.out.println("bestdice" + bestDice);
		info = move(currentPos, bestDice);
		System.out.println(info[2]+"skalaaaaaaaaaaaaaaa ");
		int[] roundInfo = new int[8];// dimiourgia pinaka roundinfo kai prosthiki twn pliroforiwn tou girou
		roundInfo[5] = STEPS;
		roundInfo[6] = bestDice;
		roundInfo[7] = GAINPOINTS;
		for (int j = 0; j < 5; j++) {
			roundInfo[j] = info[j]; // stis prwtes theseis tou roundinfo bainoun oi plirofories tou pinaka pou
									// epistrefei i move()
			
		}
		path.add(roundInfo);// prosthiki sto path tou pinaka roundInfo me tis plirofories tou girou
		return roundInfo[0]; // epistrofi tis neas thesis tou paikti;

	}
   //H sinartisi createMySubTree() dimiourgei to prwto epipedo tou dentrou mas, diladi tous komvous pou antiproswpevoun
	//tis kiniseis tou MinMaxPlayer kai sto telos kalei tin createOpponentSubTree() h opoia dimiourgei to deytero 
	//epipedo tou dentrou pou afora tis kiniseis tou antipalou.Se kathe epanalipsi tou vroxou for(gia kathe zaria)
	//dimiourgeitai i metavliti simBoard opou arxika exei timi to tablo tou komvou parent(diladi ayto tou trexontos paixnidiou).
	//dimiourgoume enan simPlayer me orisma to simBoars kai ton kinoume simfwna me ti zaria i gia na ananewthei i 
	//timi tou simBoard simfwna me ti sigekrimeni kinisi.Dimiourgoume enan komvo child pou antiprosopevei tin kinisi i
	//me orisma tin ananewmeni timi tou simboard kai sti sinexeia
	//prosthetoume ton komvo-kinisi ston komvo parent kai gia ton sigekrimeno komvo child dimiourgoume to deytero epipedo tou dentrou.
	 
	void createMySubtree(Node parent, int depth, int currentPos, int opponentCurrentPos) {

		for (int i = 1; i < 7; i++) {
			if (currentPos + i <= 200) {
				Board simBoard = new Board(parent.getNoadBoard());
				MinMaxPlayer simPlayer = new MinMaxPlayer(10, "simulator", 0, simBoard);
				simPlayer.move(currentPos, i);
				Node child = new Node(parent, currentPos, i, simBoard);
				parent.addChildren(child);
				createOpponentSubtree(child, depth + 1, currentPos, opponentCurrentPos);

			}
		}

	}
    //H sinartisi createOpponentSubtree() dimiourgei to deytero epipedo tou dentrou
	//gia kathe kinisi mas dexetai ws paidia tis pithanes kiniseis tou antipalou.Dimiourgeitai ena simBoard me tin 
	//timi tou simBoard tou komvou pou afora tin kinisi mas kai enan paikti simPlayer .Mesa se vroxo me 6 epanalipseis
	//dimiourgoume ton komvo child pou antiproswpevei tin kinisi tou antipalou gia tin sigekrimeni zaria gia tin 
	//opoia klithike i createOpponentSubtree apo tin createMySubtree.
	// Kaleitai i evaluate apo ton simPlayer gia ton komvo ayton,me orisma ti thesi tou antipalou  kai to ananewmeno simBoard.
	//Pairnoume tin arnitiki timi tis evaluate, epeidi ektimoume tis kiniseis tou antipalou kai theloume tin mikroteri timi tis
	//Telos ,prosthetoume ton komvo-kinisi antipalou ws paidi ston komvo-kinisi mas.Kathe komvos-kinisi mas apokta
	//ews 6 komvous-kiniseis antipalou.
	void createOpponentSubtree(Node parent, int depth, int currentPos, int opponentCurrentPos) {
		for (int i = 1; i < 7; i++) {
			if (opponentCurrentPos + i <= 200) {
				Board simBoard = new Board(parent.getNoadBoard());
				MinMaxPlayer simPlayer = new MinMaxPlayer(10, "simulator", 0, simBoard);
				Node child = new Node(parent, opponentCurrentPos, i, simBoard);
				child.setNodeEvaluation(-(simPlayer.evaluate(opponentCurrentPos, i, simBoard)));
				parent.addChildren(child);
			}
		}
	}
    //H sinartisi chooseMinMaxMove() epilegei tin katalliloteri kinisi gia ton MinMaxPlayer xrisimopoiontas
	//tin ektimisi tis evaluate gia tin kinisi tou antipalou sto deytero epipedo tou dentrou pou dimiourgisame
	//Ston ekswteriko vroxo for, thetoume enan metriti i me arithmo ews ton arithmo twn diathesimwn kinisewn mas.
	//gia kathe mia apo tis dinates mas kiniseis kai ston eswteriko vroxo for thetoume enan metriti j me arithmo ews
	//ton arithmo twn diathesimwn kinisewn tou antipalou, dedomenou oti emeis paiksame tin kinisi i
	//sti metavliti eval apothikevetai h timi i pou epestrepse h evaluation() gia tin kinisi tou antipalou, kai
	//xrisimopoiwntas tin metavliti worstEval apothikevoume sto telos tou eswterikou vroxou for kathe i epanalipsis tin 
	//xeiroteri ektimisi tis evaluate,h opoia antiproswpevei tin xeiroteri kinisi pou tha kanei o antipalos eis varos mas
	//epeita sto telos tis epanalipsis tou ekswterikou for elegxoume me ti metavliti bestEval kai apothikevoume tin megaliteri
	//timi apo aytes pou apokta i worstEval(dedomenou oti kalesame tin evaluate me -), diladi dialegoume tin kaliteri gia mas kinisi. Taytoxrona apothikevoume stin 
	//bestDice tin timi tis sigekrimenis zarias kai epistrefoume sto telos tin veltisti zaria bestDice.
	int chooseMinMaxMove(Node root) {
		double eval = 0, bestEval = Double.NEGATIVE_INFINITY, worstEval = Double.POSITIVE_INFINITY;
		int bestDice = 0;
		int counter=0;
		for (int i = 0; i < root.getNumberOfChildren(); i++) {
			for (int j = 0; j < root.getChildNode(i).getNumberOfChildren(); j++) {
				eval = root.getChildNode(i).getChildNode(j).getNodeEvaluation();
				if (eval <= worstEval) {
					worstEval = eval;
					
				}
			}
			if (bestEval <= worstEval) {
				if(bestEval==worstEval) {
					counter++;
				}
			bestEval = worstEval;
			bestDice = i + 1;
			}
		}
		
		if(counter==5) {
			return -1;
		}
		return bestDice;

	}

	// H sinartisi statistics ektipwnei tis plirofories tou kathe gyrou , exontas
	// prosvasi stous pinakes
	// tou path. Episis, me tis metavlites subSnakes, subLadders,subRedApples kai
	// subBlackApples
	// ektipwnei ton sinoliko arithmo fidiwn pou tsibisan ton paikti, skalwn pou
	// anevike kai milwn pou efage sto paixnidi.
	void statistics() {

		int subSnakes = 0;
		int subLadders = 0;
		int subRedApples = 0;
		int subBlackApples = 0;

		for (int i = 0; i < path.size(); i++) {
			subSnakes += path.get(i)[1];
			subLadders += path.get(i)[2];
			subRedApples += path.get(i)[3];
			subBlackApples += path.get(i)[4];
		}
		for (int i = 0; i < path.size(); i++) {

			System.out.println("O paiktis ston gyro " + i + " tsibithike apo " + path.get(i)[1] + " fidia");
			System.out.println("O paiktis ston gyro " + i + " anevike " + path.get(i)[2] + " skales");
			System.out.println("O paiktis ston gyro " + i + " efage " + path.get(i)[3] + " kokkina mila");
			System.out.println("O paiktis ston gyro " + i + " efage " + path.get(i)[4] + " mavra mila");
			System.out.println("O paiktis ston gyro " + i + " ekane " + path.get(i)[5] + " vimata");
			System.out.println("O paiktis ston gyro " + i + " efere " + path.get(i)[6] + " sto zari");
			System.out.println("O paiktis ston gyro " + i + " sygedrwse " + path.get(i)[7] + " pontous");
		}
		System.out.println(" O paiktis tsibithike synolika apo " + subSnakes + " fidia");
		System.out.println(" O paiktis anevike synolika  " + subLadders + " skales");
		System.out.println(" O paiktis efage synolika  " + subRedApples + " kokkina mila");
		System.out.println(" O paiktis efage synolika  " + subBlackApples + " mavra mila");
		return;
	}

	public double targetFunction(int Steps, int GainPoints) {

			return (Steps * (0.60) + GainPoints * (0.40));

	}

}

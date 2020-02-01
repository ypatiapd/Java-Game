package fidaki;

import java.util.ArrayList;
import java.util.Collections;

public class HeuristicPlayer extends Player {

	ArrayList<int[]> path;// lista pou apothikevei enan pinaka me plirofories se kathe gyro
	int[] roundInfo;// pinakas pou krataei tis plirofories tou kathe girou
	int steps = 0;// vimata se ena giro
	int bestDice = 0;// i kaliteri zaria me vasi tin sinartisi stoxou
	int gainPoints = 0;// synolikoi pontoi se ena giro

	// methodos domisis xoris orismata
	public HeuristicPlayer() {
		super();// klisi methodou domisis tis klasis player
		path = new ArrayList<int[]>();
		roundInfo = new int[8];
	}

	// methodos domisis me orismata
	public HeuristicPlayer(int pId, String na, int sc, Board BO) {
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
	public double evaluate(int currentPos, int dice) {
		int newPos = currentPos + dice;
		steps = dice; // arithmos vimatwn an o paixtis den tyxei pouthena
		gainPoints = 0;
		boolean done = false;
		while (done == false) {
			boolean LAD = false;
			boolean SNA = false;
			for (int i = 0; i < board.getSnakesLength(); i++) {

				if (newPos == board.getSnakes(i).getHeadId()) {
					steps += board.getSnakes(i).getTaleId() - newPos; // arnitikos arithmos vimatwn giati tsibithike apo
					SNA = true; // yparxei kefali fidiou sti sigekrimeni thesi
					newPos = board.getSnakes(i).getTaleId();// nea thesi stin oura tou fidiou
				}
			}
			for (int i = 0; i < board.getLaddersLength(); i++) {
				if (newPos == board.getLadders(i).getDownStep()) {
					steps += board.getLadders(i).getUpStep() - newPos;
					newPos += board.getLadders(i).getUpStep();// nea thesi sto panw meros tis skalas
					LAD = true;// o paitkis anevike skala ara o vroxos while epanalamvanetai
				}
			}
			for (int i = 0; i < board.getApplesLength(); i++) {
				if (newPos == board.getApples(i).getAppleId()) {
					gainPoints += board.getApples(i).getPoints();
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

	// H sinartisi getNextMove sigrinei ta apotelesmata pou edwse i evaluate gia
	// kathe zaria kai epilegei ti veltisti
	// kinisi. Ston pinaka choices apothikevontai oi times gia kathe pithani zaria
	// tis sinartisis stoxou
	// kai me enan if elegxo elegxetai poia timi einai megalyteri;H timi tis zarias
	// gia tin opoia
	// h evaluate epestrepse megalyteri timi apothikevetai stin metavliti bestDice
	// .Episis kathe fora pou kaleitai i evaluate dimiourgeitai o pinakas
	// roundInfo .Stis teleytaies theseis apothikevei ta vimata, tin zaria kai
	// tous pontous.Epeita kaleitai i sinartisi move
	// me orisma tin thesi tou paikti kai tin veltisti zaria, kai oi times tou
	// pinaka pou epistrefei
	// apothikevontai stis prwtes theseis tou pinaka roundInfo.Epeita 
	// prostithetai sto Path (arraylist).
	int getNextMove(int currentPos) {
		bestDice = 0;
		int STEPS=0;
		int GAINPOINTS=0;
		int[] info = new int[5];
		double maxEvaluate = 0;
		double[] choices;
		choices = new double[6];
		for (int i = 0; i < 6; i++) {
			choices[i] = evaluate(currentPos, i + 1);
			if (choices[i] > maxEvaluate) {
				maxEvaluate = choices[i];
				bestDice = i + 1;
				STEPS=steps;// apothikeysi stin metavliti STEPS tin timi tis metavlitis steps gia tin kaliteri zaria 
				GAINPOINTS=gainPoints;//apothikeysi stin metavliti GAINPOINTS tis metavlitis gainPoints gia tin kaliteri zaria 

			}
		}
		info = move(currentPos, bestDice);
		int[] roundInfo = new int[8];//dimiourgia pinaka roundinfo kai prosthiki twn pliroforiwn tou girou
		roundInfo[5] = STEPS;
		roundInfo[6] = bestDice;
		roundInfo[7] = GAINPOINTS;
		for (int i = 0; i < 5; i++) {
			roundInfo[i] = info[i];  //stis prwtes theseis tou roundinfo bainoun oi plirofories tou pinaka pou epistrefei i move()
		}
		path.add(roundInfo);//prosthiki sto path tou pinaka roundInfo me tis plirofories tou girou

		setPlayerId(roundInfo[0]);
		return roundInfo[0]; // epistrofi tis neas thesis tou paikti;

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
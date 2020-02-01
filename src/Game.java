//Yпатиа далг
//8606
//6977960438
//ypatiapd@gmail.com
//дглгтягс йоутфиалпасгс
//8989
//dkoutzia@ece.auth.gr
//6979023896
package fidaki;

import java.util.Random;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Game {
	static int round = 0;//metavliti pou apothikevei tin timi tou girou

	public static void main(String[] args) {
		boolean first1 = false;// metavliti pou pairnei timi true an paizei prwtos o paiktis 1 simfwna me tin turnMap
		boolean first2 = false;//metavliti pou pairnei timi true an paizei prwtos o paiktis 1 simfwna me tin turnMap

		Player player1;//dimiourgia antikeimenou Player
		MinMaxPlayer player2;//dimiourgia antikeimenou MinMaxPlayer
		ArrayList<Player> Players = new ArrayList<Player>();//dimiourgia Arraylist pou apothikevei ta 2 antikeimena kai pernaei ws orisma stin turnMap
		Map<Integer, Integer> Turn = new HashMap<Integer, Integer>();//dimiourgia xarti Turn gia na apothikefsei to apotelesma tis turnMap
		int[] pl1 = { 0, 0, 0, 0, 0 };//pinakas pou apothikevei tis times pou epistrefei i move()
		int pl2 = 0; // apothikefsi boardid gia ton heuristic player
		Board BOARD = new Board(20, 10, 3, 3, 6);
		BOARD.createBoard();
		BOARD.createElementBoard();
		player1 = new Player(1, "takis", 0, BOARD);
		player2 = new MinMaxPlayer(2, "anna", 0, BOARD);
		Players.add(player1);
		Players.add(player2);
		Turn = new Game().setTurns(Players);
		for (int i = 0; i < 6; i++) {
			if (Turn.containsKey(i)) {//elegxos an o xartis periexei tin zaria "i"
				if (Turn.get(i) == player1.getPlayerId()) {// an antistoixei i timi tou xarti sto id tou paikti 1
					first1 = true;// h metavliti first1 pairnei timi true kai paizei o paiktis 1 prwtos
					System.out.println("paizei prwtos o paiktis 1");
					break;
				} else if (Turn.get(i) == player2.getPlayerId()) {
					first2 = true;
					System.out.println("paizei prwtos o paiktis 2");
					break;
				}
			}
		}
		System.out.println("to paixnidi ksekinise!");
		while ((round < 100)&&(pl1[0]<200)&&(pl2<200)) {
			Random dice = new Random();
			int num1 = 0;//metavliti pou apothikevei tin zaria tou antikeimenou Player
			num1 = 1 + dice.nextInt(6);
			if (first1) {
				pl1 = player1.move(pl1[0], num1);
				System.out.println("paiktis 1");
				for (int i = 0; i < 5; i++)
					System.out.println(pl1[i]);
				pl2=player2.getNextMove(pl2,pl1[0]);

				round++;
			} else {
				pl2 = player2.getNextMove(pl2,pl1[0]);
				System.out.println("id"+player2.getPlayerId());
				pl1 = player1.move(pl1[0], num1);
				System.out.println("paiktis 1");
				for (int i = 0; i < 5; i++)
					System.out.println(pl1[i]);
			}
		}

		player2.statistics();

		System.out.println("arithmos girwn:" + round);
		System.out.println("scor paikti 1: " + player1.getScore());
		System.out.println("scor paikti 2: " + player2.getScore());
		System.out.println("id paikti 1: " + pl1[0]);
		System.out.println("id paikti 2: " + pl2);

		if (player2.targetFunction(pl2, player2.getScore()) > player2.targetFunction(pl1[0], player1.getScore())) {
			System.out.println("nikitis tou paixnidiou einai o paiktis 2 !!");
		}
		else if (player2.targetFunction(pl2, player2.getScore()) == player2.targetFunction(pl1[0],
				player1.getScore())) {
			if (pl2 > pl1[0])
				System.out.println("nikitis tou paixnidiou einai o paiktis 2 !! ");
			else
				System.out.println("nikitis tou paixnidiou einai o paiktis 1 !! ");
		} else
			System.out.println("nikitis tou paixnidiou einai o paiktis 1 !! ");
		
	}
	
	
    //H sinartisi setTurns orizei tin seira me tin opoia tha paiksoun oi paiktes, symfwna me ti zaria pou
	//tha feroun. Dimiourgeitai enas xartis pou dexetai ws kleidia tis zaries kai ws times ta ID twn paiktwn pou feran tin 
	//kathe zaria. Me mia for pou trexei toses fores osoi einai oi paiktes, stin metavliti temp apothikevetai me 
	// tyxaia metavliti i zaria pou antistixei ston paikti me deikti i ston pinaka players. Elegxetai me tin methodo 
	// containsKey an yparxei idi i zaria ston map gia na min yparxoyn diples zaries kai an oxi, apothikevetai i zaria ws kleidi
	// kai ws timi to antistoixo ID tou paikti. An nai o deiktis meiwnetai kata 1 kai epanalamvanetai h for gia to sygekrimeno paikti 
	//H synartisi epistrefei enan xarti me tis zaries kai ta ID twn paiktwn.
	Map<Integer, Integer> setTurns(ArrayList<Player> players) {
		int temp = 0;
		Map<Integer, Integer> turnMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < players.size(); i++) {
			Random num = new Random();
			temp = 1 + num.nextInt(6);
			if (turnMap.containsKey(temp)) {
				i--;
				continue;
			}
			turnMap.put(temp, players.get(i).getPlayerId());

		}

		return turnMap;

	}

	public void setRound(int ro) {
		round = ro;
	}

	public int getRound() {
		return round;
	}

	public Game() {
		round = 0;
	}

	public Game(int Ro) {
		setRound(Ro);
	}

}
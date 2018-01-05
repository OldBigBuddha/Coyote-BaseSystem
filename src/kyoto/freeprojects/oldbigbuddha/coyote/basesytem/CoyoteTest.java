package kyoto.freeprojects.oldbigbuddha.coyote.basesytem;

import kyoto.freeprojects.oldbigbuddha.coyote.basesytem.Player.HumanPlayer;
import kyoto.freeprojects.oldbigbuddha.coyote.basesytem.Player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static kyoto.freeprojects.oldbigbuddha.coyote.basesytem.Utils.print;
import static kyoto.freeprojects.oldbigbuddha.coyote.basesytem.Utils.println;

public class CoyoteTest {

    public static void main(String[] args) {

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            players.add( new HumanPlayer("Player" + (i + 1)) );
        }

        Coyote coyote = new Coyote();
        coyote.setPlayers(players);
        coyote.init();

        String currentPlayerName;
        while (true) {
            currentPlayerName = coyote.getCurrentPlayerName();
            println( currentPlayerName + "のターン");
            for (CardStatus status: coyote.getOtherCardStatus()) {
                println( status.toString() );
            }
            print("数を入力してください：");
            if (!coyote.turn( new Scanner(System.in).next() )) {
                continue;
            } else {
                List<Integer> statuses = coyote.judge();
                if (statuses.get(0) == Coyote.WIN ) {
                    println( currentPlayerName + "勝利");
                } else {
                    println( currentPlayerName + "敗北" );
                }
            }

        }
    }

}

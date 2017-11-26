package kyoto.freeprojects.oldbigbuddha.coyote.basesytem.Player;

import java.util.Scanner;

import static kyoto.freeprojects.oldbigbuddha.coyote.basesytem.Utils.*;

public class HumanPlayer extends Player {
    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public int turn() {

        print( "あなたが思う合計（コヨーテは-1）：" );
        while (true) {
            try {
                return Integer.parseInt(new Scanner(System.in).next());
            } catch (NumberFormatException e) {
                println("数値を入力して下さい");
            }
        }
    }


}

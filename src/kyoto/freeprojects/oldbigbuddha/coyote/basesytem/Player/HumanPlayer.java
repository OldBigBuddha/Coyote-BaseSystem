package kyoto.freeprojects.oldbigbuddha.coyote.basesytem.Player;

import java.util.Scanner;

import static kyoto.freeprojects.oldbigbuddha.coyote.basesytem.Utils.*;

public class HumanPlayer extends Player {
    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public String turn() {

        print( "あなたが思う合計（コヨーテは-1）：" );
        while (true) {
            return new Scanner(System.in).next();
        }
    }

    @Override
    public void die() {
        println( mName + "は死んだ" );
    }
}

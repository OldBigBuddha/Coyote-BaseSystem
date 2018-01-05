package kyoto.freeprojects.oldbigbuddha.coyote.basesytem;

import kyoto.freeprojects.oldbigbuddha.coyote.basesytem.Player.HumanPlayer;
import kyoto.freeprojects.oldbigbuddha.coyote.basesytem.Player.Player;

import java.util.ArrayList;
import java.util.List;

import static kyoto.freeprojects.oldbigbuddha.coyote.basesytem.Utils.*;

/**
 * Coyoteのベースシステム
 * @author OldBigBuddha
 * */

public class Main {

    /**
    * テスト用のプレイヤー人数
    * */
    public static final int NUMBER_OF_MEMBERS = 5;

    /**
     * Coyoteのデッキ
     * */
    static Deck mDeck;
    /**
     * プレイヤーを管理するList
     * */
    static List<Player>     players = new ArrayList<>();
    /**
     * ターン数
     * 初期値は０
     * */
    static int turnCount = 0;
    /**
     * ユーザが出した現在の数
     * */
    static int nowNumber = 0;
    /**
     * ユーザが持っているカードの中で最も大きい数
     * */
    static int maxNumber = 0;
    /**
     * ユーザが持っているカードの数字の合計
     * */
    static int answer    = 0;

    public static void main(String[] args) {
        init();
        // １ターン分の処理
        // Coyoteと言われるまでループ
        while (true) {
            turnCount++;
            // ターン数に応じて、プレイヤーを選択
            Player player = players.get( turnCount % NUMBER_OF_MEMBERS );
            // プレイヤーが答えた数字
            // -1だとCoyote
            // TODO: マイナスがプラスを上回ったときの回答と被る
//            int playerNumber = player.turn();
//            if (playerNumber == -1) {
//                break;
//            } else {
//                // マイナスカードがプラスカードの枚数を上回る可能性がある
//                nowNumber = playerNumber;
//            }
            println("Now : "    + nowNumber);
            println("Answer : " + answer);
        }
//        judge();
    }

    /**
     * ゲームの初期化
     * デッキの生成やプレイヤーの生成をおこなう
     * */
    public static void init() {
        mDeck = new Deck();
        println( mDeck.size() );
        for (int i = 0; i < NUMBER_OF_MEMBERS; i++) {
            Player player = new HumanPlayer( "Player" + (i + 1) );
            mDeck = player.draw(mDeck);

            int number = player.getCardNumber();
            maxNumber = (number < 1000 && maxNumber < number) ? number: maxNumber;

            players.add(player);
        }

//        for (Player player : players) {
//            System.out.println( player.toString() );
//        }
        calcAnswer();
    }

    /**
     * 変数answerを計算する
     * */
    public static void calcAnswer() {

        boolean isThereDouble = false;  // 酋長（しゅちょう）カードがあるかどうか
        boolean isInvalidMax  = false;  // 狐カードがあるかどうか

        for (Player player : players) {
            CardStatus status = player.getStatus();

            if (status.getStatus() < 1000) {
                answer += status.getStatus();
            } else {
                switch (status) {
                    case INVALIDMAX:
                        isInvalidMax = true;
                        break;
                    case ADDITIONAL:
                        // ほらあなカードは一枚ドロー
                        answer += mDeck.drawn().getStatus();
                        break;
                    case DOUBLEUP:
                        isThereDouble = true;
                        break;
                    case NEGATIVEFIVE:
                        answer -= 5;
                        break;
                    case NEGATIVETEN:
                        answer -= 10;
                        break;
                }
            }
        }
        if (isInvalidMax)  answer -= maxNumber; // 最大値を削除
        if (isThereDouble) answer *= 2;         // 合計値を二倍
    }

    /**
     * 勝敗を判定する
     * Coyote宣言後に実行
     * */
    public static void judge() {
        // 合計の数（answer）がプレイヤーの回答(nowNumber)より
        //      大きい場合：Coyote宣言者勝利
        //      小さい場合：Coyote宣言者敗北
        if (nowNumber <= answer ) {
            println("loss");
        } else {
            println("win");
        }
    }
}

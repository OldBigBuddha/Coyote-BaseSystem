package kyoto.freeprojects.oldbigbuddha.coyote.basesytem;

import kyoto.freeprojects.oldbigbuddha.coyote.basesytem.Player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Coyote {

    public static final int WIN  = 0x0;
    public static final int LOSS = 0x1;

    /**
     * テスト用のプレイヤー人数
     * */
    public static final int NUMBER_OF_MEMBERS = 5;

    /**
     * Coyoteのデッキ
     * */
    private Deck mDeck;
    /**
     * プレイヤーを管理するList
     * */
    private List<Player> mPlayers = new ArrayList<>();

    public List<Player> getPlayers() {
        return mPlayers;
    }

    /**
     * ターン数
     * 初期値は０
     * */
    private int mTurnCount = 0;
    /**
     * ユーザが出した現在の数
     * */
    private int mNowAnswer = 0;
    /**
     * ユーザが持っているカードの中で最も大きい数
     * */
    private int maxNumber = 0;

    /**
     * ユーザが持っているカードの数字の合計
     * */
    private int mCurrentAnswer = 0;

    public int getCurrentAnswer() {
        return mCurrentAnswer;
    }

    private int mLastPlayerNumber;

    public Coyote() {}

    public void init() {

        if (mPlayers.size() < 1) {
            new RuntimeException("Not exist player");
        }

        mDeck = new Deck();
        for (Player player: mPlayers) {
            mDeck = player.draw(mDeck);

            int number = player.getCardNumber();
            maxNumber = (number < 1000 && maxNumber < number) ? number: maxNumber;
        }
        calcAnswer();
        Collections.shuffle(mPlayers);
    }

    public boolean turn(String playerAnswer) throws NumberFormatException {
        mTurnCount++;
        mLastPlayerNumber =  mTurnCount % NUMBER_OF_MEMBERS - 1;
        if (playerAnswer.equals("coyote")) {
            return true;
        } else {
            // マイナスカードがプラスカードの枚数を上回る可能性がある
            mNowAnswer = Integer.parseInt(playerAnswer);
        }
        return false;
    }

    /**
     * 勝敗を判定する
     * Coyote宣言後に実行
     * */
    public List<Integer> judge() {
        // 合計の数（mCurrentAnswer）がプレイヤーの回答( mNowAnswer )より
        //      大きい場合：Coyote宣言者勝利
        //      小さい場合：Coyote宣言者敗北
        List<Integer> result = new ArrayList<>();
        if (mCurrentAnswer < mNowAnswer) {
            int beforePlayerNumber = (mLastPlayerNumber - 1 < 0) ? 0 : mLastPlayerNumber - 1;
            mPlayers.get( beforePlayerNumber ).damaged();
            result.add(WIN);
        } else {
            mPlayers.get(mLastPlayerNumber).damaged();
            result.add(LOSS);
        }
        for (Player player: mPlayers) {
            result.add( player.getLife() );
        }
        return result;
    }

    public String getCurrentPlayerName() {
        return mPlayers.get(mTurnCount % mPlayers.size()).getName();
    }

    public List<CardStatus> getOtherCardStatus() {
        List<CardStatus> status = new ArrayList<>();
        for (Player player: mPlayers) {
            if (!getCurrentPlayerName().equals(player.getName())) {
                status.add( player.getStatus() );
            }
        }
        return status;
    }

    private void calcAnswer() {

        boolean isThereDouble = false;  // 酋長（しゅちょう）カードがあるかどうか
        boolean isInvalidMax  = false;  // 狐カードがあるかどうか

        for (Player player : mPlayers) {
            CardStatus status = player.getStatus();

            if (status.getStatus() < 1000) {
                mCurrentAnswer += status.getStatus();
            } else {
                switch (status) {
                    case INVALIDMAX:
                        isInvalidMax = true;
                        break;
                    case ADDITIONAL:
                        // ほらあなカードは一枚ドロー
                        mCurrentAnswer += mDeck.drawn().getStatus();
                        break;
                    case DOUBLEUP:
                        isThereDouble = true;
                        break;
                    case NEGATIVEFIVE:
                        mCurrentAnswer -= 5;
                        break;
                    case NEGATIVETEN:
                        mCurrentAnswer -= 10;
                        break;
                }
            }
        }
        if (isInvalidMax)  mCurrentAnswer -= maxNumber; // 最大値を削除
        if (isThereDouble) mCurrentAnswer *= 2;         // 合計値を二倍
    }


    public void setPlayers(List<Player> players) {
        mPlayers = players;
    }

    public int getTurnNumber() {
        return mTurnCount;
    }
}

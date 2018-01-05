package kyoto.freeprojects.oldbigbuddha.coyote.basesytem.Player;

import kyoto.freeprojects.oldbigbuddha.coyote.basesytem.CardStatus;
import kyoto.freeprojects.oldbigbuddha.coyote.basesytem.Deck;

public abstract class Player {

    protected String mName;
    protected CardStatus mStatus;
    private int mLife;

    public Player(String name) {
        mName = name;
        mLife = 3;
    }

    public abstract String turn();

    public Deck draw(Deck deck) {
        mStatus = deck.drawn();
        return deck;
    }

    public void damaged() {
        mLife--;
    }

    public abstract void die();

    public CardStatus getStatus() {
        return mStatus;
    }
    public int getCardNumber() {return  mStatus.getStatus();}

    public String getName() {
        return mName;
    }

    public int getLife() {
        return mLife;
    }

    @Override
    public String toString() {
        return mName + " : " + mStatus.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        if (!mName.equals(player.mName)) return false;
        return mStatus == player.mStatus;
    }

    @Override
    public int hashCode() {
        int result = mName.hashCode();
        result = 31 * result + mStatus.hashCode();
        return result;
    }
}

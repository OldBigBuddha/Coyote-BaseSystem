package kyoto.freeprojects.oldbigbuddha.coyote.basesytem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final CardStatus[] TEMPLATE_DECK = {
            CardStatus.ZERO,
            CardStatus.ZERO,
            CardStatus.ZERO,
            CardStatus.ZERO,

            CardStatus.ONE,
            CardStatus.ONE,
            CardStatus.ONE,
            CardStatus.ONE,

            CardStatus.TWO,
            CardStatus.TWO,
            CardStatus.TWO,
            CardStatus.TWO,

            CardStatus.THREE,
            CardStatus.THREE,
            CardStatus.THREE,
            CardStatus.THREE,

            CardStatus.FOUR,
            CardStatus.FOUR,
            CardStatus.FOUR,
            CardStatus.FOUR,

            CardStatus.FIVE,
            CardStatus.FIVE,
            CardStatus.FIVE,
            CardStatus.FIVE,

            CardStatus.TEN,
            CardStatus.TEN,
            CardStatus.TEN,

            CardStatus.FIFTEEN,
            CardStatus.FIFTEEN,

            CardStatus.TWENTY,

            CardStatus.NEGATIVEFIVE,
            CardStatus.NEGATIVEFIVE,

            CardStatus.NEGATIVETEN,
            CardStatus.DOUBLEUP,
            CardStatus.INVALIDMAX,
            CardStatus.ADDITIONAL,
    };


    private List<CardStatus> mCards = new ArrayList<>();

    public Deck() {
        mCards.addAll( Arrays.asList( TEMPLATE_DECK ));
        Collections.shuffle(mCards);
    }

    public int size() {
        return mCards.size();
    }

    public CardStatus drawn() {
        CardStatus status = mCards.get(0);
        mCards.remove(0);
        return status;
    }
}

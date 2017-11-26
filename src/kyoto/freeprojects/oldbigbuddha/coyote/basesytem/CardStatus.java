package kyoto.freeprojects.oldbigbuddha.coyote.basesytem;

public enum CardStatus {
    // Number Status
    ZERO(0),      // 0
    ONE(1),       // 1
    TWO(2),       // 2
    THREE(3),     // 3
    FOUR(4),      // 4
    FIVE(5),      // 5
    TEN(10),      // 10
    FIFTEEN(15),  // 15
    TWENTY(20),   // 20

    // Special Status
    NEGATIVEFIVE(1005), // マイナス５
    NEGATIVETEN(1010),  // マイナス１０
    INVALIDMAX(1000),   // 最大値を消去
    ADDITIONAL(1001),   // 最後にデッキから一枚引く
    DOUBLEUP(1002);     // 合計を二倍する

    private int mStatus;

    CardStatus(int status) {
        mStatus = status;
    }

    public int getStatus() {
        return mStatus;
    }

    @Override
    public String toString() {
        switch (mStatus) {
            case 1000: return "Max -> 0";
            case 1001: return "?";
            case 1002: return "x2";
            case 1005: return "-5";
            case 1010: return "-10";
        }
        return mStatus + "";
    }

}

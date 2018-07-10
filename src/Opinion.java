public enum Opinion {
    Positive(1),
    Negative(-1);

    // フィールド定義
    private int opi;

    // コンストラクタ定義
    private Opinion(int opi){
        this.opi = opi;
    }

    public int getValue(){
        return this.opi;
    }
}

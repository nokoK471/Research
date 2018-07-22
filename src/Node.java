import java.util.ArrayList;

public class Node {

    int number, layer;
    Opinion opi;
    double Q_pos, Q_neg;
    boolean isExpress;
    double x, y;

    ArrayList<Node> linkList;

    public Node(int layer, int number, double x, double y){
        this.layer = layer;
        this.number = number;

        // opinion
        Q_pos = Constants.sfmt.NextUnif() - 0.5;
        Q_neg = Constants.sfmt.NextUnif() - 0.5;
        updateOpinion();

        isExpress = true;

        // position
        this.x = x;
        this.y = y;

        linkList = new ArrayList<>();
    }

    public double updateQ(Node j){
        int r_i = opi.getValue()*j.opi.getValue();

        if(opi==Opinion.Positive) {
            Q_pos = (1 - Constants.alpha) * Q_pos + Constants.alpha * r_i;
            return Q_pos;

        }else if(opi==Opinion.Negative) {
            Q_neg = (1 - Constants.alpha) * Q_neg + Constants.alpha * r_i;
            return Q_neg;
        }

        // Error
        return 0;
    }

    public Opinion updateOpinion(){
        if(Q_pos > Q_neg) opi = Opinion.Positive;
        else if(Q_pos < Q_neg) opi = Opinion.Negative;

        // when exploration
        if(Constants.sfmt.NextUnif() < Constants.epsilon){
            if(opi==Opinion.Positive) opi = Opinion.Negative;
            else if(opi==Opinion.Negative) opi = Opinion.Positive;
        }

        return opi;
    }

}

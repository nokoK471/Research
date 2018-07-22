import java.util.ArrayList;

public class Agent {
    int number;
    ArrayList<Node> linkList;
    ArrayList<Node> myselfList;

    public Agent(int number){
        this.number = number;

        linkList = new ArrayList<>();
        myselfList = new ArrayList<>();
    }


    // ネットワーク間での自己の意見が異なるために沈黙する
    public boolean silence_becauseofInconsistency(Agent j){
        for(Node i : myselfList){

        }
        return true;
    }
}

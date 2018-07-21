public class SpatialRandomGraph {
    int layer;
    Agent[] agents;

    SpatialRandomGraph(int l, double r){
        layer = l;

        // agentsの初期化
        agents = new Agent[Constants.N];
        for(int i=0; i<Constants.N; i++){
            agents[i] = new Agent(i, Constants.sfmt.NextUnif(), Constants.sfmt.NextUnif());
        }

        // linkをつなぐ
        for(int i=0; i<Constants.N; i++){
            for(int j=i; j<Constants.N; j++){
                double distance = Math.sqrt(Math.pow(agents[i].x - agents[j].x, 2) +
                        Math.pow(agents[i].y - agents[j].y, 2));
                if(distance < r){
                    agents[i].linkList.add(agents[j]);
                    agents[j].linkList.add(agents[i]);
                }
            }
        }
    }

    public void outputNetworkCSV(String filename){
        FileOperator fo = new FileOperator();
        String str = "a1 number,a2 number,r\n";
        for(Agent a1 : agents){
            for(Agent a2 : a1.linkList){
                if(a1.number < a2.number){
                    double r = Math.sqrt(Math.pow(a1.x - a2.x, 2) + Math.pow(a1.y - a2.y, 2));
                    str += a1.number + "," + a2.number + "," + r + "\n";
                }
            }
        }
        fo.write(filename + ".csv", false, str);
    }

    public void outputAgentCSV(String filename){
        FileOperator fo = new FileOperator();
        String str = "number,x,y,opinion,Q_pos,Q_neg,Q\n";
        for(Agent a : agents){
            double Q = Math.max(a.Q_pos, a.Q_neg);
            str += a.number + "," + a.x + "," + a.y + "," + a.opi + "," + a.Q_pos + "," + a.Q_neg + "," + Q + "\n";
        }
        fo.writeLine(filename + ".csv", false, str);
    }
}

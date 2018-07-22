public class SpatialRandomGraph {
    int layer;
    Node[] nodes;

    SpatialRandomGraph(int l, double r){
        layer = l;

        // nodesの初期化
        nodes = new Node[Constants.N];
        for(int i=0; i<Constants.N; i++){
            nodes[i] = new Node(l, i, Constants.sfmt.NextUnif(), Constants.sfmt.NextUnif());
        }

        // linkをつなぐ
        for(int i=0; i<Constants.N; i++){
            for(int j=i; j<Constants.N; j++){
                double distance = Math.sqrt(Math.pow(nodes[i].x - nodes[j].x, 2) +
                        Math.pow(nodes[i].y - nodes[j].y, 2));
                if(distance < r){
                    nodes[i].linkList.add(nodes[j]);
                    nodes[j].linkList.add(nodes[i]);
                }
            }
        }
    }

    public void outputNetworkCSV(String filename){
        FileOperator fo = new FileOperator();
        String str = "a1 number,a2 number,r\n";
        for(Node a1 : nodes){
            for(Node a2 : a1.linkList){
                if(a1.number < a2.number){
                    double r = Math.sqrt(Math.pow(a1.x - a2.x, 2) + Math.pow(a1.y - a2.y, 2));
                    str += a1.number + "," + a2.number + "," + r + "\n";
                }
            }
        }
        fo.write(filename + ".csv", false, str);
    }

    public void outputNodeCSV(String filename){
        FileOperator fo = new FileOperator();
        String str = "number,x,y,opinion,Q_pos,Q_neg,Q\n";
        for(Node a : nodes){
            double Q = Math.max(a.Q_pos, a.Q_neg);
            str += a.number + "," + a.x + "," + a.y + "," + a.opi + "," + a.Q_pos + "," + a.Q_neg + "," + Q + "\n";
        }
        fo.writeLine(filename + ".csv", false, str);
    }
}

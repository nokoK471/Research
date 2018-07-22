public class Test {

    public static void main(String args[]){
        // 乱数の準備
        Constants.readRandomSeed("RandomSeed.csv");
        Constants.changeSfmtSeed(Integer.parseInt(Constants.randomSeed[0]));

        // グラフの準備
        SpatialRandomGraph[] srg = new SpatialRandomGraph[Constants.LN];
        for(int l=0; l<Constants.LN; l++){
            srg[l] = new SpatialRandomGraph(l, Constants.r);
        }

        // ノードの準備
        // ノードは最初全てのレイヤーで同じQ値を持つ(＝同じ意見を持つ)が、
        // ネットワークごとに異なるネットワーク構成を持つのでネットワーク内の場所(x,y)は異なる。
        // 場所は異なったままに、Q値と意見だけネットワーク1と同じに調整する
        for(int l=0; l<Constants.LN; l++){
            for(int i=0; i<Constants.N; i++){
                srg[l].nodes[i].Q_pos = srg[0].nodes[i].Q_pos;
                srg[l].nodes[i].Q_neg = srg[0].nodes[i].Q_neg;
                srg[l].nodes[i].opi = srg[0].nodes[i].opi;
            }
        }

        // 各エージェントにノードを入れる
        Agent[] agents = new Agent[Constants.N];
        for(int i=0; i<Constants.N; i++){
            agents[i] = new Agent(i);
            for(int l=0; l<Constants.LN; l++){
                agents[i].myselfList.add(srg[l].nodes[i]);
            }
        }

        // ファイルパス
        String filepath = "result/20180722/";

        for(int l=0; l<Constants.LN; l++){
            srg[l].outputNodeCSV(filepath + "l" + l + "_node_step0");
            srg[l].outputNetworkCSV(filepath + "l" + l + "_network");
        }


        // メイン
        for(int step=1; step<=Constants.steps; step++){
            // 各ネットワークごとに異なるエージェント(番号)を選んでQ値をアップデートする
            for(int l=0; l<Constants.LN; l++){
                // 1つ目のエージェント
                int a1 = Constants.sfmt.NextInt(Constants.N);
                srg[l].nodes[a1].updateOpinion();
                // 2つ目のエージェント
                int a2 = Constants.sfmt.NextInt(srg[l].nodes[a1].linkList.size());
                srg[l].nodes[a1].updateQ(srg[l].nodes[a1].linkList.get(a2));
            }

            if(step%2500==0){
                for(int l=0; l<Constants.LN; l++) {
                    srg[l].outputNodeCSV(filepath + "l" + l + "_node_step" + step);
                    System.out.println("output step" + step);
                }
            }
        }
    }
}

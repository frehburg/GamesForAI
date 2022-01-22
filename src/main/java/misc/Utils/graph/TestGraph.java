package misc.Utils.graph;

public class TestGraph {
    public static void main(String[] args) {
        iGraph<Integer> g = new GraphAL<>();
        for(int i = 0; i < 8; i++) {
            g.addVertex(i);
        }
        //neighbors of 0
        g.addEdge(0,2);
        g.addEdge(0,3);
        g.addEdge(0,4);
        //neighbors of 1
        g.addEdge(1,5);
        g.addEdge(1,6);
        //neighbors of 2
        g.addEdge(2, 0);
        //neighbors of 3
        g.addEdge(3,0);
        g.addEdge(3,5);
        g.addEdge(3,7);
        //neighbors of 4
        g.addEdge(4,0);
        g.addEdge(4,7);
        //neighbors of 5
        g.addEdge(5,3);
        g.addEdge(5,1);
        g.addEdge(5,6);
        //neighbors of 6
        g.addEdge(6,1);
        g.addEdge(6,5);
        //neighbors of 7
        g.addEdge(7,3);
        g.addEdge(7,4);
        for(int i = 0; i < 8; i++) {
            System.out.println("Neighbors of " + i + " = " + g.getNeighbors(i));
        }

        System.out.println(g.getShortestPaths(2,6));
    }
}

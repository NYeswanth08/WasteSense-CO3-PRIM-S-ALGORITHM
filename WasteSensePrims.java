public class WasteSensePrims {

    private static final int V = 5;

    int minKey(int key[], boolean mstSet[]) {

        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    void printMST(int parent[], int graph[][]) {

        int totalCost = 0;

        System.out.println("\nOptimal Waste Collection Network (MST):");

        System.out.println("Route\tCost");

        for (int i = 1; i < V; i++) {

            char source = (char) ('A' + parent[i]);
            char destination = (char) ('A' + i);

            System.out.println(
                    source + " - " + destination
                            + "\t" + graph[i][parent[i]]
            );

            totalCost += graph[i][parent[i]];
        }

        System.out.println("\nTotal Network Cost = " + totalCost);
    }

    void primMST(int graph[][]) {

        int parent[] = new int[V];

        int key[] = new int[V];

        boolean mstSet[] = new boolean[V];

        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;

        parent[0] = -1;

        for (int count = 0; count < V - 1; count++) {

            int u = minKey(key, mstSet);

            mstSet[u] = true;

            for (int v = 0; v < V; v++) {

                if (graph[u][v] != 0
                        && !mstSet[v]
                        && graph[u][v] < key[v]) {

                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent, graph);
    }

    public static void main(String[] args) {

        System.out.println("===== WasteSense Route Optimization =====");

        System.out.println("\nCollection Centers:");
        System.out.println("A - Central Station");
        System.out.println("B - North Zone");
        System.out.println("C - South Zone");
        System.out.println("D - East Zone");
        System.out.println("E - West Zone");

        int graph[][] = {
                {0, 4, 2, 0, 0},
                {4, 0, 1, 5, 0},
                {2, 1, 0, 8, 10},
                {0, 5, 8, 0, 2},
                {0, 0, 10, 2, 0}
        };

        WasteSensePrims tree = new WasteSensePrims();

        tree.primMST(graph);

        System.out.println("\nRoute Optimization Completed Successfully");

        System.out.println("\nTime Complexity:");
        System.out.println("Prim's Algorithm = O(V²)");
    }
}
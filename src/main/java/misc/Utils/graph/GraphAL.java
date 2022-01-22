package misc.Utils.graph;


import java.util.*;

/**
 * Implements the graph interface with an adjacency list and as an undirected graph.
 * @param <E> content of vertices
 * @author filreh
 */
public class GraphAL<E> implements iGraph<E>{
    private int curID;
    private HashMap<Integer, ArrayList<Integer>> adjacencyList;
    private HashMap<Integer, E> vertices;
    public GraphAL() {
        adjacencyList = new HashMap<>();
        vertices = new HashMap<>();
        this.curID = 0;
    }
    /**
     * If there exists a vertex with the given id, true is returned
     *
     * @param id vertex
     * @return whether vertex exists
     */
    @Override
    public boolean existsVertex(int id) {
        return vertices.containsKey(id);
    }

    /**
     * Returns the content of the vertex id, returns null if the vertex does not exist
     *
     * @param id vertex
     * @return content of vertex
     */
    @Override
    public E getContent(int id) {
        return vertices.getOrDefault(id, null);
    }

    /**
     * If there exists an edge indcident to the vertices with the given ids, true is returned.
     * Else false is returned.
     *
     * @param id1 first vertex
     * @param id2 second vertex
     * @return whether vertex exists
     */
    @Override
    public boolean existsEdge(int id1, int id2) {
        ArrayList<Integer> neighbours = adjacencyList.getOrDefault(id1, new ArrayList<>());
        if(neighbours.contains(id2)) {
            return true;
        }
        return false;
    }

    /**
     * Creates a vertex with content e and returns the id of the created vertex.
     * If the vertex already exists, then it returns its id.
     *
     * @param e content
     * @return id of created vertex
     */
    @Override
    public int addVertex(E e) {
        vertices.put(curID,e);
        adjacencyList.put(curID, new ArrayList<>());
        return curID++;
    }

    /**
     * If the vertex with the given id exists, it is removed and so are all its incoming edges.
     * And it returns true.
     * Else nothing happens and it returns false.
     *
     * @param id vertex
     * @return whether the vertex was deleted
     */
    @Override
    public boolean removeVertex(int id) {
        if(existsVertex(id)) {
            vertices.remove(id);
            adjacencyList.remove(id);
            //remove all incoming edges
            Set<Map.Entry<Integer, ArrayList<Integer>>> entrySet = adjacencyList.entrySet();
            for(Map.Entry<Integer, ArrayList<Integer>> entry : entrySet) {
                ArrayList<Integer> neighbors = entry.getValue();
                if(neighbors.contains(id)) {
                    neighbors.remove(neighbors.indexOf(id));
                }
            }
            return true;
        }
        return false;
    }

    /**
     * If both vertices id1 and id2 exist, then an edge between them is added and true is returned.
     * If the edge already exists true is returned. If at least one of the vertices does not exist,
     * false is returned
     *
     * @param id1 first vertex
     * @param id2 second vertex
     * @return whether the edge exists now
     */
    @Override
    public boolean addEdge(int id1, int id2) {
        if(existsVertex(id1) && existsVertex(id2)) {
            ArrayList<Integer> neighbors1 = adjacencyList.get(id1);
            if(!neighbors1.contains(id2))
                neighbors1.add(id2);
            ArrayList<Integer> neighbors2 = adjacencyList.get(id2);
            if(!neighbors2.contains(id1))
                neighbors2.add(id1);
            return true;
        }
        return false;
    }

    /**
     * If the edge exists, the edge is removed and true is returned.
     * Else, false is returned.
     *
     * @param id1 first vertex
     * @param id2 second vertex
     * @return whether the edge was removed
     */
    @Override
    public boolean removeEdge(int id1, int id2) {
        if(existsVertex(id1) && existsVertex(id2)) {
            ArrayList<Integer> neighbors1 = adjacencyList.get(id1);
            neighbors1.remove(neighbors1.indexOf(id2));
            ArrayList<Integer> neighbors2 = adjacencyList.get(id2);
            neighbors2.remove(neighbors1.indexOf(id1));
            return true;
        }
        return false;
    }

    /**
     * Returns true if the vertices exist and are adjacent,
     * false if not
     *
     * @param id1 vertex
     * @param id2 vertex
     * @return whether the vertices are adjacent
     */
    @Override
    public boolean adjacent(int id1, int id2) {
        if(existsVertex(id1) && existsVertex(id2)) {
            return adjacencyList.get(id1).contains(id2);
        }
        return false;
    }

    /**
     * Returns a list of the neighbors of the vertex.
     * If the vertex does not exist, an empty list is returned.
     *
     * @param id vertex
     * @return list of neighbors
     */
    @Override
    public List<Integer> getNeighbors(int id) {
        if(existsVertex(id)) {
            return adjacencyList.get(id);
        }
        return new ArrayList<>();
    }

    /**
     * Returns a list of adjacent vertices, that in the given
     * order form a shortest path from the vertex id1 to vertex id2
     *
     * Using Dijkstra's algorithm
     * @param id1 first vertex
     * @param id2 second vertex
     * @return shortest path from id1 to id2
     */
    @Override
    public List<Integer> getShortestPaths(final int id1, final int id2) {
        //TODO: fix!!!!!!!!!!!!
        if(existsVertex(id1) && existsVertex(id2)) {
            //distances
            HashMap<Integer, Integer> d = new HashMap<>();
            //predecessors
            HashMap<Integer, Integer> p = new HashMap<>();
            //already visited
            ArrayList<Integer> a = new ArrayList<>();
            //not yet visited
            ArrayList<Integer> b = new ArrayList<>();
            //TODO: use priority queue for efficiency
            for(int i : vertices.keySet()) {
                d.put(i, Integer.MAX_VALUE);
                System.out.println("Distance of: "+ i +" = " + d.get(i));
                p.put(i, i);
                System.out.println("Predecessor of: "+ i +" = " + p.get(i));
                b.add(i);
            }
            d.put(id1, 0);
            int n = getAmountOfVertices();
            while(a.size() < n) {
                int v;
                //minimum out of b (list of not visited yet
                System.out.println(d);
                b.sort(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return d.get(o1) - d.get(o2);
                    }
                });
                v = b.remove(0);
                a.add(v);
                System.out.println(v);
                System.out.println("----------------------------------");
                List<Integer> neighbors = getNeighbors(v);
                for(int w : neighbors) {
                    if(d.get(v) + 1 < d.get(w)) {
                        d.put(w, d.get(v) + 1);
                        System.out.println("Distance of: "+ w +" = " + d.get(w));
                        p.put(w,v);
                        System.out.println("Predecessor of: "+ w +" = " + p.get(v));
                    }
                }
            }
            System.out.println(d);
            System.out.println(p);
            //put into list
            ArrayList<Integer> path = new ArrayList<>();
            int v = id2;
            while(v!= id1) {
                path.add(id2);
                v = p.get(v);
            }
            path.add(id1);
            return path;
        }
        return null;
    }

    /**
     * Returns |V| or n
     *
     * @return
     */
    @Override
    public int getAmountOfVertices() {
        return vertices.size();
    }

    /**
     * Returns |E| or m
     *
     * @return
     */
    @Override
    public int getAmountOfEdges() {
        //TODO
        throw new RuntimeException("Not implemented yet");
    }
}

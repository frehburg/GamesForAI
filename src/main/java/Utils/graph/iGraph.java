package Utils.graph;

import java.util.List;

public interface iGraph<E> {
    /**
     * If there exists a vertex with the given id, true is returned
     * @param id vertex
     * @return whether vertex exists
     */
    boolean existsVertex(int id);

    /**
     * Returns the content of the vertex id, returns null if the vertex does not exist
     * @param id vertex
     * @return content of vertex
     */
    E getContent(int id);

    /**
     * If there exists an edge indcident to the vertices with the given ids, true is returned.
     * Else false is returned.
     * @param id1 first vertex
     * @param id2 second vertex
     * @return whether vertex exists
     */
    boolean existsEdge(int id1, int id2);

    /**
     * Creates a vertex with content e and returns the id of the created vertex.
     * If the vertex already exists, then it returns its id.
     * @param e content
     * @return id of created vertex
     */
    int addVertex(E e);

    /**
     * If the vertex with the given id exists, it is removed and so are all its incoming edges.
     * And it returns true.
     * Else nothing happens and it returns false.
     * @param id vertex
     * @return whether the vertex was deleted
     */
    boolean removeVertex(int id);

    /**
     * If both vertices id1 and id2 exist, then an edge between them is added and true is returned.
     * If the edge already exists true is returned. If at least one of the vertices does not exist,
     * false is returned
     * @param id1 first vertex
     * @param id2 second vertex
     * @return whether the edge exists now
     */
    boolean addEdge(int id1, int id2);

    /**
     * If the edge exists, the edge is removed and true is returned.
     * Else, false is returned.
     * @param id1 first vertex
     * @param id2 second vertex
     * @return whether the edge was removed
     */
    boolean removeEdge(int id1, int id2);

    /**
     * Returns true if the vertices exist and are adjacent,
     * false if not
     * @param id1 vertex
     * @param id2 vertex
     * @return whether the vertices are adjacent
     */
    boolean adjacent(int id1, int id2);

    /**
     * Returns a list of the neighbors of the vertex.
     * If the vertex does not exist, an empty list is returned.
     * @param id vertex
     * @return list of neighbors
     */
    List<Integer> getNeighbors(int id);

    /**
     * Returns a list of adjacent vertices, that in the given
     * order form a shortest path from the vertex id1 to vertex id2
     * @param id1 first vertex
     * @param id2 second vertex
     * @return shortest path from id1 to id2
     */
    List<Integer> getShortestPaths(int id1, int id2);

    /**
     * Returns |V| or n
     * @return
     */
    int getAmountOfVertices();

    /**
     * Returns |E| or m
     * @return
     */
    int getAmountOfEdges();

}

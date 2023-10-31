package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        List<Neighbour> favoriteNeighbour = new ArrayList<>();

        for (Neighbour n : neighbours) {
            if (n.isFavorite()) {
                favoriteNeighbour.add(n);
            }
        }

        return favoriteNeighbour;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public Neighbour getNeighbourById(long id) {
        for (Neighbour n : neighbours) {
            if (n.getId() == id) return n;
        }
        return null;
    }

    @Override
    public void deleteFavoriteNeighbour(Neighbour neighbour) {
        for (Neighbour n : neighbours) {
            if (n.getId() == neighbour.getId()) n.setFavorite(false);
        }
    }

    @Override
    public void addFavoriteNeighbour(Neighbour neighbour) {
        for (Neighbour n : neighbours) {
            if (n.getId() == neighbour.getId()) n.setFavorite(true);
        }
    }
}

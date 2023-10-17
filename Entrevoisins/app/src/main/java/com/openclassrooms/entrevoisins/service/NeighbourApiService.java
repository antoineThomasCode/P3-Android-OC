package com.openclassrooms.entrevoisins.service;

import androidx.annotation.Nullable;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Get all my favorite Neighbours
     * @return {@link List}
     */
    List<Neighbour> getFavoriteNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);
    /**
     * Deletes a Favorite neighbour
     * @param neighbour
     */
    void deleteFavoriteNeighbour(Neighbour neighbour);
    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Deletes a Favorite neighbour
     * @param neighbour
     */
    void addFavoriteNeighbour(Neighbour neighbour);
    @Nullable
    Neighbour getNeighbourById(long id);
}

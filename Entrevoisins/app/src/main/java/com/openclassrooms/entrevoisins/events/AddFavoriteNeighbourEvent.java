package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class AddFavoriteNeighbourEvent {

    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public AddFavoriteNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}

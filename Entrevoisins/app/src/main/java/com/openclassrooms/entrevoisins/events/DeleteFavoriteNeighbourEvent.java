package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class DeleteFavoriteNeighbourEvent {

    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public DeleteFavoriteNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }

}

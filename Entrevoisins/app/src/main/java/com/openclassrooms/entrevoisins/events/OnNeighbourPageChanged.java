package com.openclassrooms.entrevoisins.events;

public class OnNeighbourPageChanged {
    public boolean isFavoritePage = false;

    public OnNeighbourPageChanged(boolean isFavoritePage) {
        this.isFavoritePage = isFavoritePage;
    }
}

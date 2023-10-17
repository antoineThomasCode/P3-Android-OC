package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.AddFavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.OnNeighbourPageChanged;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class NeighbourFragment extends Fragment {

    private final static String IS_FAVORITE_KEY = "isFavorite";
    private boolean isFavoriteList = false;
    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;


    /**
     * Create and return a new instance
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance( boolean isFavoriteList) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_FAVORITE_KEY, isFavoriteList);
        NeighbourFragment fragment = new NeighbourFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.isFavoriteList = getArguments().getBoolean(IS_FAVORITE_KEY);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
        mNeighbours = isFavoriteList? mApiService.getFavoriteNeighbours() :  mApiService.getNeighbours();
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours, isFavoriteList));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        EventBus.getDefault().post(new OnNeighbourPageChanged(this.isFavoriteList));
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mApiService.deleteNeighbour(event.neighbour);
        initList();
        // test
    }
    @Subscribe
    public void onDeleteFavoriteNeighbour(DeleteFavoriteNeighbourEvent event) {
        mApiService.deleteFavoriteNeighbour(event.neighbour);
        initList();
    }
    @Subscribe
    public void addFavoriteNeighbour(AddFavoriteNeighbourEvent event) {
        mApiService.addFavoriteNeighbour(event.neighbour);
        initList();
    }
}

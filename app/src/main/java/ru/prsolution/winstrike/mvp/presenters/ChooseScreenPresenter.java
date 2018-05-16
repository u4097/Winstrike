package ru.prsolution.winstrike.mvp.presenters;


import java.util.Map;

import ru.prsolution.winstrike.mvp.apimodels.RoomLayoutFactory;
import ru.prsolution.winstrike.mvp.apimodels.Rooms;
import ru.prsolution.winstrike.networking.NetworkError;
import ru.prsolution.winstrike.networking.Service;
import ru.prsolution.winstrike.ui.main.ChooseScreenFragment;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by terrakok 26.11.16
 */

public class ChooseScreenPresenter {
    private CompositeSubscription subscriptions;
    private final Service service;
    private ChooseScreenFragment fragment;


    public ChooseScreenPresenter(Service service, ChooseScreenFragment fragment) {
        this.subscriptions = new CompositeSubscription();
        this.service = service;
        this.fragment = fragment;
    }


    public void getActivePid() {
        fragment.showWait();

        Subscription subscription = service.getActivePid(new Service.RoomsCallback() {
            @Override
            public void onSuccess(Rooms authResponse) {
                fragment.removeWait();

                fragment.onGetActivePidResponseSuccess(authResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                fragment.removeWait();
                fragment.onGetAcitivePidFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }



    public void getArenaByTimeRange(String activeLayoutPid, Map<String,String> time) {
        fragment.showWait();

        Subscription subscription = service.getArenaByTimeRange(new Service.RoomLayoutByTimeCallback() {
            @Override
            public void onSuccess(RoomLayoutFactory authResponse) {
                fragment.removeWait();
                fragment.onGetArenaByTimeResponseSuccess(authResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                fragment.removeWait();
                fragment.onGetArenaByTimeFailure(networkError.getAppErrorMessage());
            }

        },activeLayoutPid,time);

        subscriptions.add(subscription);
    }


    public void onStop() {
        subscriptions.unsubscribe();
    }

}

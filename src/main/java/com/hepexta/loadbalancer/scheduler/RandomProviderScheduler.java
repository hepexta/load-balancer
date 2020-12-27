package com.hepexta.loadbalancer.scheduler;

import com.hepexta.loadbalancer.provider.Provider;

import java.util.List;
import java.util.Random;

public class RandomProviderScheduler<T> implements ProviderScheduler<T>{
    @Override
    public Provider<T> getNextProvider(List<Provider> providers) {
        validateProviders(providers);
        return providers.get(getRandomIndex(providers));
    }

    private int getRandomIndex(List<Provider> providers) {
        return new Random().nextInt(providers.size());
    }
}

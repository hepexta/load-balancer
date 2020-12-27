package com.hepexta.loadbalancer.scheduler;

import com.hepexta.loadbalancer.provider.Provider;

import java.util.List;

public class RoundRobinProviderScheduler<T> implements ProviderScheduler<T>{

    private volatile Provider<T> prev = null;

    @Override
    public Provider<T> getNextProvider(List<Provider> providers) {
        validateProviders(providers);
        Provider next = getNext(providers);
        prev = next;
        return next;
    }

    private Provider getNext(List<Provider> providers) {
        int indexOfLastElement = providers.indexOf(prev);

        return indexOfLastElement+1 >= providers.size() ? providers.get(0) : providers.get(indexOfLastElement+1);
    }

}

package com.hepexta.loadbalancer;

import com.hepexta.loadbalancer.ex.MaxLimitProviderReachedException;
import com.hepexta.loadbalancer.provider.Provider;
import com.hepexta.loadbalancer.scheduler.ProviderScheduler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LoadBalancerImpl<T> implements LoadBalancer<T> {

    private ProviderScheduler providerScheduler;
    private int maxRegisteredProvidersLimit;
    private List<Provider> registeredProviders = new CopyOnWriteArrayList<>();

    public LoadBalancerImpl(ProviderScheduler providerScheduler, int maxRegisteredProvidersLimit) {
        this.providerScheduler = providerScheduler;
        this.maxRegisteredProvidersLimit = maxRegisteredProvidersLimit;
    }

    @Override
    public Provider<T> get() {
        return providerScheduler.getNextProvider(registeredProviders);
    }

    @Override
    public void registerProvider(Provider<T> provider) throws MaxLimitProviderReachedException {
        if (registeredProviders.size() == maxRegisteredProvidersLimit) {
            throw new MaxLimitProviderReachedException();
        }
        registeredProviders.add(provider);
    }
}

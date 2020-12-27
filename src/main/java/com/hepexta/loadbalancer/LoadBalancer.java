package com.hepexta.loadbalancer;

import com.hepexta.loadbalancer.ex.MaxLimitProviderReachedException;
import com.hepexta.loadbalancer.provider.Provider;

public interface LoadBalancer<T> {
    Provider<T> get();

    void registerProvider(Provider<T> provider) throws MaxLimitProviderReachedException;
}

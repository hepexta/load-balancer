package com.hepexta.loadbalancer.scheduler;

import com.hepexta.loadbalancer.ex.ProvidersEmptyException;
import com.hepexta.loadbalancer.provider.Provider;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public interface ProviderScheduler<T> {
    Provider<T> getNextProvider(List<Provider> providers);

    default void validateProviders(List<Provider> providers) {
        if (CollectionUtils.isEmpty(providers)){
            throw new ProvidersEmptyException();
        }
    }
}

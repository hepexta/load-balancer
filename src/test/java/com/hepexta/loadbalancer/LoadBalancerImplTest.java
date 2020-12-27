package com.hepexta.loadbalancer;


import com.hepexta.loadbalancer.ex.MaxLimitProviderReachedException;
import com.hepexta.loadbalancer.provider.URIProvider;
import com.hepexta.loadbalancer.scheduler.RandomProviderScheduler;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoadBalancerImplTest {

    private static final int MAX_PROVIDER_COUNT = 10;
    private LoadBalancer lb;

    @Test
    public void test_registerOneProvider() {
        lb = new LoadBalancerImpl(new RandomProviderScheduler(), MAX_PROVIDER_COUNT);
        URIProvider provider = new URIProvider();
        lb.registerProvider(provider);
        assertEquals(provider, lb.get());
    }

    @Test
    public void test_registerMaxProvider() {
        lb = new LoadBalancerImpl(new RandomProviderScheduler(), MAX_PROVIDER_COUNT);
        for (int i = 0; i < MAX_PROVIDER_COUNT; i++) {
            lb.registerProvider(new URIProvider());
        }
        assertNotNull(lb.get());
    }

    @Test(expected = MaxLimitProviderReachedException.class)
    public void registerProvider_MaxLimitProviderReachedException() {
        lb = new LoadBalancerImpl(new RandomProviderScheduler(), MAX_PROVIDER_COUNT);
        for (int i = 0; i < MAX_PROVIDER_COUNT; i++) {
            lb.registerProvider(new URIProvider());
        }
        lb.registerProvider(new URIProvider());
    }
}
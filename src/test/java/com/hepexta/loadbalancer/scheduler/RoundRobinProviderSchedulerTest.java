package com.hepexta.loadbalancer.scheduler;

import com.hepexta.loadbalancer.ex.ProvidersEmptyException;
import com.hepexta.loadbalancer.provider.URIProvider;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class RoundRobinProviderSchedulerTest {

    ProviderScheduler<String> ps = new RoundRobinProviderScheduler<>();

    @Test
    public void getNextProvider_singletonList() {
        URIProvider one = new URIProvider();
        Assert.assertEquals(one, ps.getNextProvider(Collections.singletonList(one)));
    }

    @Test
    public void getNextProvider() {
        URIProvider one = new URIProvider();
        URIProvider two = new URIProvider();
        URIProvider thr = new URIProvider();
        Assert.assertEquals(one, ps.getNextProvider(Arrays.asList(one, two, thr)));
        Assert.assertEquals(two, ps.getNextProvider(Arrays.asList(one, two, thr)));
        Assert.assertEquals(thr, ps.getNextProvider(Arrays.asList(one, two, thr)));
        Assert.assertEquals(one, ps.getNextProvider(Arrays.asList(one, two, thr)));
    }

    @Test(expected = ProvidersEmptyException.class)
    public void getNextProvider_emptyList() {
        Assert.assertNotNull(ps.getNextProvider(Collections.emptyList()));
    }
}
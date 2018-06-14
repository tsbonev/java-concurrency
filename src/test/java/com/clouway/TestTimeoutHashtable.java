package com.clouway;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.nullValue;

public class TestTimeoutHashtable {

    TimeoutHashtable<Integer, String> table = new TimeoutHashtable<>(20);

    @Test
    public void willTimeoutAfterPeriod() throws InterruptedException {

        table.put(1, "value");
        Thread.currentThread().sleep(25);

        assertThat(table.get(1), is(nullValue()));

    }

    @Test
    public void willRefreshAfterPeriod() throws InterruptedException {

        table.put(1, "value");

        Thread.currentThread().sleep(5);

        assertThat(table.get(1), is("value"));

        Thread.currentThread().sleep(15);

        assertThat(table.get(1), is("value"));

    }

    @Test
    public void putWillRefreshTimeout() throws InterruptedException {

        table.put(1, "value");

        Thread.currentThread().sleep(10);

        table.put(1, "new value");

        Thread.currentThread().sleep(15);

        assertThat(table.get(1), is("new value"));

    }

    @Test
    public void willRefreshAndDie() throws InterruptedException {

        table.put(1, "value");
        table.put(2, "other value");

        Thread.currentThread().sleep(5);

        assertThat(table.get(1), is("value"));
        table.put(2, "new other value");

        Thread.currentThread().sleep(15);

        assertThat(table.get(1), is("value"));
        assertThat(table.get(2), is("new other value"));

        Thread.currentThread().sleep(30);

        assertThat(table.get(1), is(nullValue()));
        assertThat(table.get(2), is(nullValue()));

    }

}

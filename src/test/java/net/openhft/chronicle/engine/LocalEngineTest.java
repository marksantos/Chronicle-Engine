/*
 * Copyright 2014 Higher Frequency Trading
 *
 * http://www.higherfrequencytrading.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.openhft.chronicle.engine;

import net.openhft.chronicle.engine.client.internal.ChronicleEngine;
import net.openhft.chronicle.queue.ChronicleQueue;
import net.openhft.chronicle.set.ChronicleSet;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class LocalEngineTest extends ThreadMonitoringTest {
    final ChronicleContext context = new ChronicleEngine();
    final ChronicleQueue mockedQueue = mock(ChronicleQueue.class);
    final ChronicleSet<String> mockedSet = mock(ChronicleSet.class);

    @Before
    public void setUp() throws IOException {
        ((ChronicleEngine) context).setQueue("queue1", mockedQueue);
        ((ChronicleEngine) context).setSet("set1", mockedSet);
    }

    @Test
    public void testGetMap() throws IOException {
        {
            Map<String, String> map1 = context.getMap("map1", String.class, String.class);
            map1.put("Hello", "World");
        }

        {
            Map<String, String> map1 = context.getMap("map1", String.class, String.class);
            assertEquals("World", map1.get("Hello"));
        }
    }

    @Test
    public void testGetSet() {
        ChronicleSet<String> set1 = context.getSet("set1", String.class);
        set1.add("Hello");
    }

    @Test
    public void testGetQueue() {
        ChronicleQueue chronicle = context.getQueue("queue1");
        assertNotNull(chronicle);
    }
}
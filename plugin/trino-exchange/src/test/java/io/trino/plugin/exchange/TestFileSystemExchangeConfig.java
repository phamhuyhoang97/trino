/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.trino.plugin.exchange;

import com.google.common.collect.ImmutableMap;
import org.testng.annotations.Test;

import java.util.Map;

import static io.airlift.configuration.testing.ConfigAssertions.assertFullMapping;
import static io.airlift.configuration.testing.ConfigAssertions.assertRecordedDefaults;
import static io.airlift.configuration.testing.ConfigAssertions.recordDefaults;

public class TestFileSystemExchangeConfig
{
    @Test
    public void testDefaults()
    {
        assertRecordedDefaults(recordDefaults(FileSystemExchangeConfig.class)
                .setBaseDirectory(null)
                .setExchangeEncryptionEnabled(false)
                .setExchangeSinkBufferPoolMinSize(0));
    }

    @Test
    public void testExplicitPropertyMappings()
    {
        Map<String, String> properties = ImmutableMap.<String, String>builder()
                .put("exchange.base-directory", "s3n://exchange-spooling-test/")
                .put("exchange.encryption-enabled", "true")
                .put("exchange.sink-buffer-pool-min-size", "10")
                .buildOrThrow();

        FileSystemExchangeConfig expected = new FileSystemExchangeConfig()
                .setBaseDirectory("s3n://exchange-spooling-test/")
                .setExchangeEncryptionEnabled(true)
                .setExchangeSinkBufferPoolMinSize(10);

        assertFullMapping(properties, expected);
    }
}

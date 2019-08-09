/*
 * Copyright 2019 TWO SIGMA OPEN SOURCE, LLC
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

package com.twosigma.znai.server.landing;

import com.twosigma.znai.utils.ServiceLoaderUtils;

import java.util.Set;
import java.util.stream.Stream;

public class LandingDocEntriesProviders {
    private static final Set<LandingDocEntriesProvider> providers =
            ServiceLoaderUtils.load(LandingDocEntriesProvider.class);

    public static void add(LandingDocEntriesProvider provider) {
        providers.add(provider);
    }

    public static Stream<LandingDocEntry> provide() {
        return providers.stream().flatMap(LandingDocEntriesProvider::provide);
    }
}

/*
 * Copyright 2023 znai maintainers
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

package org.testingisdocumenting.znai.website;

import org.testingisdocumenting.znai.html.HtmlPageAndPageProps;
import org.testingisdocumenting.znai.structure.TableOfContents;
import org.testingisdocumenting.znai.structure.TocItem;

import java.util.List;

public record TocAddedAndRemovedPages(TableOfContents tableOfContents, List<HtmlPageAndPageProps> addedPagesProps,
                                      List<TocItem> removedTocItems) {
}

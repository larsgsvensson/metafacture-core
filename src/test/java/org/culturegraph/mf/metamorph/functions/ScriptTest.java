/*
 * Copyright 2016 Christoph Böhme
 *
 * Licensed under the Apache License, Version 2.0 the "License";
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
package org.culturegraph.mf.metamorph.functions;

import static org.mockito.Mockito.inOrder;

import org.culturegraph.mf.framework.StreamReceiver;
import org.culturegraph.mf.metamorph.InlineMorph;
import org.culturegraph.mf.metamorph.Metamorph;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * Tests for class {@link Script}.
 *
 * @author Markus Geipel (metamorph-test xml)
 * @author Christoph Böhme (conversion to Java)
 */
public final class ScriptTest {

  @Rule
  public final MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  private StreamReceiver receiver;

  private Metamorph metamorph;

  @Test
  public void shouldExecuteJavascriptFunctions() {
    metamorph = InlineMorph.in(this)
        .with("<rules>")
        .with("  <data source='data1'>")
        .with("    <script file='org/culturegraph/mf/metamorph/functions/script-test.js' invoke='process' />")
        .with("  </data>")
        .with("  <data source='data2'>")
        .with("    <script file='org/culturegraph/mf/metamorph/functions/script-test.js' invoke='process2' />")
        .with("  </data>")
        .with("</rules>")
        .createConnectedTo(receiver);

    metamorph.startRecord("1");
    metamorph.literal("data1", "ABC");
    metamorph.literal("data2", "ABC");
    metamorph.endRecord();

    final InOrder ordered = inOrder(receiver);
    ordered.verify(receiver).startRecord("1");
    ordered.verify(receiver).literal("data1", "ABC!");
    ordered.verify(receiver).literal("data2", "ABCABC");
    ordered.verify(receiver).endRecord();
    ordered.verifyNoMoreInteractions();
  }

}

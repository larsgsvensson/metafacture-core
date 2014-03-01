/*
 *  Copyright 2013, 2014 Deutsche Nationalbibliothek
 *
 *  Licensed under the Apache License, Version 2.0 the "License";
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.culturegraph.mf.stream.pipe.sort;

import org.culturegraph.mf.formeta.Formeta;
import org.culturegraph.mf.framework.StreamReceiver;
import org.culturegraph.mf.types.Triple;
import org.culturegraph.mf.types.Triple.ObjectType;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

/**
 * Tests {@link TripleCollect}
 * 
 * @author Markus Geipel
 * 
 */
public final class TripleCollectTest {
	private static final String VALUE = "value";
	private static final String VALUE1 = "value1";
	private static final String VALUE2 = "value2";
	private static final String NAME = "name";
	private static final String ENTITY_NAME = "ename";
	private static final String REC_ID = "id";
	private static final String REC_ALT_ID = "altid";

	@Test
	public void testShouldBuildRecords() {
		final StreamReceiver receiver = Mockito.mock(StreamReceiver.class);
		final TripleCollect collect = new TripleCollect();
		collect.setReceiver(receiver);
		
		collect.process(new Triple(REC_ID, NAME, VALUE1));
		collect.process(new Triple(REC_ID, NAME, VALUE2));
		collect.process(new Triple(REC_ALT_ID, NAME, VALUE1));
		
		final InOrder ordered = Mockito.inOrder(receiver);
		
		ordered.verify(receiver).startRecord(REC_ID);
		ordered.verify(receiver).literal(NAME, VALUE1);
		ordered.verify(receiver).literal(NAME, VALUE2);
		ordered.verify(receiver).endRecord();
		ordered.verify(receiver).startRecord(REC_ALT_ID);
		ordered.verify(receiver).literal(NAME, VALUE1);
	}
	
	@Test
	public void testShouldDecodeEntities() {
		final StreamReceiver receiver = Mockito.mock(StreamReceiver.class);
		final TripleCollect collect = new TripleCollect();
		collect.setReceiver(receiver);
		
		collect.process(new Triple(REC_ID,  ENTITY_NAME, Formeta.GROUP_START +NAME + Formeta.NAME_VALUE_SEPARATOR + VALUE
				+ Formeta.ITEM_SEPARATOR + ENTITY_NAME + Formeta.GROUP_START + NAME
				+ Formeta.NAME_VALUE_SEPARATOR + VALUE + Formeta.GROUP_END + Formeta.GROUP_END,
				ObjectType.ENTITY));
		
		final InOrder ordered = Mockito.inOrder(receiver);
		
		ordered.verify(receiver).startRecord(REC_ID);
		ordered.verify(receiver).startEntity(ENTITY_NAME);
		ordered.verify(receiver).literal(NAME, VALUE);
		ordered.verify(receiver).startEntity(ENTITY_NAME);
		ordered.verify(receiver).literal(NAME, VALUE);
		ordered.verify(receiver, Mockito.times(2)).endEntity();

	}
}

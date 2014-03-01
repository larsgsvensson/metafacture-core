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
package org.culturegraph.mf.framework;

/**
 * Empty implementation of {@link ObjectReceiver} which
 * simply does nothing. Do not use this class as a base class
 * for modules which are implement {@link ObjectPipe}; use 
 * {@link DefaultObjectPipe} for that.
 * 
 * @param <T> object type
 * 
 * @see DefaultObjectPipe
 * 
 * @author Christoph Böhme
 *
 */
public class DefaultObjectReceiver<T> extends DefaultLifeCycle implements ObjectReceiver<T> {

	// CHECKSTYLE OFF: StrictDuplicateCode
	// Code duplication in DefaultObjectPipe and DefaultObjectReceiver
	// cannot be avoided. DefaultObjectPipe combines the logic 
	// from DefaultSender and DefaultObjectReceiver but can only
	// have one of these classes as its base class. Since 
	// DefaultSender was chosen as the base class, the logic
	// from DefaultObjectReceiver needs to be duplicated.
	
	@Override
	public void process(final T obj) {
		// Default implementation does nothing
	}
	
	// CHECKSTYLE ON: StrictDuplicateCode
	
}

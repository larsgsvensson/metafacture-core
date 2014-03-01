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
package org.culturegraph.mf.exceptions;


/**
 * Thrown if data processing fails due to a format error in the 
 * data. Use {@link WellformednessException} or {@link ValidationException}
 * to provide a more detailed explanation of the type of format error.
 * 
 * @author Markus Michael Geipel
 * 
 */
public class FormatException extends MetafactureException {

	private static final long serialVersionUID = -5767420416327213311L;

	public FormatException(final String message) {
		super(message);
	}

	public FormatException(final Throwable cause) {
		super(cause);
	}

	public FormatException(final String message, final Throwable cause) {
		super(message, cause);
	}

}

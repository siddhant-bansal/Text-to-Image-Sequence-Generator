/* GNU Prolog for Java
 * Copyright (C) 1997-1999  Constantine Plotnikov
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307, USA. The text of license can be also found
 * at http://www.gnu.org/copyleft/lgpl.html
 */
package gnu.prolog.vm;

import gnu.prolog.term.CompoundTermTag;

/**
 * Event that indicates changes in prolog code.
 */
public class PrologCodeUpdatedEvent extends java.util.EventObject implements HasEnvironment
{
	private static final long serialVersionUID = 3547991978575619876L;
	/** tag for predicate */
	CompoundTermTag tag;

	/**
	 * a constructor
	 * 
	 * @param environment
	 * @param tag
	 */
	public PrologCodeUpdatedEvent(Environment environment, CompoundTermTag tag)
	{
		super(environment);
		this.tag = tag;
	}

	/** get environment */
	public Environment getEnvironment()
	{
		return (Environment) getSource();
	}

	/**
	 * get tag of predicate
	 * 
	 * @return get the CompoundTermTag for this event
	 */
	public CompoundTermTag getPredicateTag()
	{
		return tag;
	}
}

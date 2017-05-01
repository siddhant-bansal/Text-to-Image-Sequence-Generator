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
package gnu.prolog.term;

/** term clone context
 * @author Constantine Plotnikov
 * @version 0.0.1
 */
import java.util.HashMap;
import java.util.Map;

public class TermCloneContext
{
	/** term to cloned term map */
	private Map<Term, Term> term2clone = new HashMap<Term, Term>();

	/**
	 * get cloned term if it is alrady added to context
	 *
	 * @param term
	 *          source term
	 * @return cloned term or null if it was not added
	 */
	public Term getTerm(Term term)
	{
		return term2clone.get(term);
	}

	/**
	 * put cloned term to context
	 *
	 * @param term
	 *          source term
	 * @param cloned
	 *          cloned term
	 */
	public void putTerm(Term term, Term cloned)
	{
		term2clone.put(term, cloned);
	}
}

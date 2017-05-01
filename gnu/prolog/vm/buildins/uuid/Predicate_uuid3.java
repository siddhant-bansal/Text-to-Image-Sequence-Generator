/* GNU Prolog for Java
 * Copyright (C) 1997-1999  Constantine Plotnikov
 * Copyright (C) 2009       Michiel Hendriks
 *
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
package gnu.prolog.vm.buildins.uuid;

import gnu.prolog.term.AtomTerm;
import gnu.prolog.term.Term;
import gnu.prolog.term.VariableTerm;
import gnu.prolog.vm.Interpreter;
import gnu.prolog.vm.PrologException;
import gnu.prolog.vm.TermConstants;

import java.util.UUID;

/**
 * 
 * @author Michiel Hendriks
 */
public class Predicate_uuid3 extends Predicate_uuid
{
	public Predicate_uuid3()
	{}

	@Override
	public int execute(Interpreter interpreter, boolean backtrackMode, Term[] args) throws PrologException
	{
		if (!(args[0] instanceof VariableTerm))
		{
			PrologException.instantiationError();
		}
		if (!(args[1] instanceof AtomTerm))
		{
			PrologException.typeError(TermConstants.atomAtom, args[1]);
		}
		Term uuidTerm = AtomTerm.get(UUID.nameUUIDFromBytes(((AtomTerm) args[1]).value.getBytes()).toString());
		return interpreter.unify(args[0], uuidTerm);
	}

}

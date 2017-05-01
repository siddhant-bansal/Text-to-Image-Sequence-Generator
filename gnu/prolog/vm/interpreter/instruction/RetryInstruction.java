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
package gnu.prolog.vm.interpreter.instruction;

/**
 * base class to try_me_else and retry_me_else instructions. it exists only for
 * purposes of code generation.
 */
public abstract class RetryInstruction extends Instruction
{
	/**
	 * position to go to on retry, it always points to either retry_me_else or to
	 * trust_me instructions
	 */
	public int retryPosition;

	/**
	 * a constructor
	 * 
	 * @param retryPosition
	 */
	public RetryInstruction(int retryPosition)
	{
		this.retryPosition = retryPosition;
	}

}

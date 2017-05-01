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
package gnu.prolog.vm.interpreter;

/**
 * this class contains information about exception handling. Exception handling
 * is modeled after Java exceptions
 */
public class ExceptionHandlerInfo
{
	/** start position of code segment */
	protected int startPosition;
	/** end position of code segment */
	protected int endPosition;
	/** position where to go for exception handling */
	protected int handlerPosition;

	/** convert handler to string */
	@Override
	public String toString()
	{
		return "from " + startPosition + " to " + endPosition + " go to " + handlerPosition;
	}
}

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

/** back track information base class */
public class BacktrackInfo
{
	/**
	 * a constructor
	 * 
	 * @param undoPosition
	 *          {@link #undoPosition}
	 * @param codePosition
	 *          {@link #codePosition}
	 * */
	public BacktrackInfo(int undoPosition, int codePosition)
	{
		this.undoPosition = undoPosition;
		this.codePosition = codePosition;
	}

	/**
	 * undo changes up to creation of this backtrack info
	 * 
	 * @param i
	 */
	public void undo(Interpreter i)
	{
		i.undo(undoPosition);
	}

	/**
	 * A pointer to code position. This pointer is interpreted by function that
	 * created backtrack information. TODO: what is the meaning of -1 here?
	 */
	public int codePosition;

	/**
	 * A position in undo stack. all actions will be undone up to this position.
	 * TODO: what is the meaning of -1 here?
	 */
	public int undoPosition;
}

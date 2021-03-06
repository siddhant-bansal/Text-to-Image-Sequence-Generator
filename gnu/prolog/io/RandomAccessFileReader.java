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
package gnu.prolog.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;

/**
 * Reads a {@link RandomAccessFile}
 * 
 * @author Michiel Hendriks
 */
public class RandomAccessFileReader extends Reader
{
	RandomAccessFile raf;

	InputStreamReader rd;

	/**
	 * @param randomAccess
	 */
	public RandomAccessFileReader(RandomAccessFile randomAccess)
	{
		super();
		raf = randomAccess;
		createReader();
	}

	protected void createReader()
	{
		rd = new InputStreamReader(new InputStream()
		{
			@Override
			public int read() throws IOException
			{
				return raf.read();
			}
		});
	}

	public void seek(long pos) throws IOException
	{
		raf.seek(pos);
		createReader();
	}

	public long getPosition() throws IOException
	{
		return raf.getFilePointer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Reader#close()
	 */
	@Override
	public void close() throws IOException
	{
		rd.close();
		raf.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Reader#read(char[], int, int)
	 */
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException
	{
		return rd.read(cbuf, off, len);
	}

	/**
	 * @return the size of the RandomAccessFile
	 * @throws IOException
	 */
	public long size() throws IOException
	{
		return raf.length();
	}
}

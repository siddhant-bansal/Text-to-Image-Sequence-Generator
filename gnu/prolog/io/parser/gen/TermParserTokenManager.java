/* Generated By:JavaCC: Do not edit this line. TermParserTokenManager.java */
package gnu.prolog.io.parser.gen;

public class TermParserTokenManager implements TermParserConstants
{
	private final int jjMoveStringLiteralDfa0_0()
	{
		return jjMoveNfa_0(16, 0);
	}

	private final void jjCheckNAdd(int state)
	{
		if (jjrounds[state] != jjround)
		{
			jjstateSet[jjnewStateCnt++] = state;
			jjrounds[state] = jjround;
		}
	}

	private final void jjAddStates(int start, int end)
	{
		do
		{
			jjstateSet[jjnewStateCnt++] = jjnextStates[start];
		} while (start++ != end);
	}

	private final void jjCheckNAddTwoStates(int state1, int state2)
	{
		jjCheckNAdd(state1);
		jjCheckNAdd(state2);
	}

	private final void jjCheckNAddStates(int start, int end)
	{
		do
		{
			jjCheckNAdd(jjnextStates[start]);
		} while (start++ != end);
	}

	private final void jjCheckNAddStates(int start)
	{
		jjCheckNAdd(jjnextStates[start]);
		jjCheckNAdd(jjnextStates[start + 1]);
	}

	static final long[] jjbitVec0 = { 0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL };

	private final int jjMoveNfa_0(int startState, int curPos)
	{
		int[] nextStates;
		int startsAt = 0;
		jjnewStateCnt = 94;
		int i = 1;
		jjstateSet[0] = startState;
		int j, kind = 0x7fffffff;
		for (;;)
		{
			if (++jjround == 0x7fffffff)
			{
				ReInitRounds();
			}
			if (curChar < 64)
			{
				long l = 1L << curChar;
				MatchLoop: do
				{
					switch (jjstateSet[--i])
					{
						case 16:
							if ((0xf400ac5800000000L & l) != 0L)
							{
								if (kind > 6)
								{
									kind = 6;
								}
								jjCheckNAdd(36);
							}
							else if ((0x3ff000000000000L & l) != 0L)
							{
								if (kind > 36)
								{
									kind = 36;
								}
								jjCheckNAddStates(0, 2);
							}
							else if ((0x100002600L & l) != 0L)
							{
								if (kind > 1)
								{
									kind = 1;
								}
								jjCheckNAddStates(3, 7);
							}
							else if (curChar == 46)
							{
								if (kind > 57)
								{
									kind = 57;
								}
								jjCheckNAdd(84);
							}
							else if (curChar == 44)
							{
								if (kind > 56)
								{
									kind = 56;
								}
							}
							else if (curChar == 41)
							{
								if (kind > 50)
								{
									kind = 50;
								}
							}
							else if (curChar == 40)
							{
								if (kind > 49)
								{
									kind = 49;
								}
							}
							else if (curChar == 34)
							{
								jjCheckNAddStates(8, 10);
							}
							else if (curChar == 33)
							{
								if (kind > 6)
								{
									kind = 6;
								}
							}
							else if (curChar == 59)
							{
								if (kind > 6)
								{
									kind = 6;
								}
							}
							else if (curChar == 39)
							{
								jjCheckNAddStates(11, 13);
							}
							else if (curChar == 37)
							{
								jjCheckNAddStates(14, 16);
							}
							if (curChar == 45)
							{
								jjCheckNAddStates(17, 20);
							}
							else if (curChar == 48)
							{
								jjAddStates(21, 23);
							}
							else if (curChar == 13)
							{
								jjCheckNAdd(13);
							}
							else if (curChar == 10)
							{
								jjstateSet[jjnewStateCnt++] = 11;
							}
							else if (curChar == 47)
							{
								jjstateSet[jjnewStateCnt++] = 0;
							}
							if (curChar == 48)
							{
								jjstateSet[jjnewStateCnt++] = 40;
							}
							break;
						case 0:
							if (curChar == 42)
							{
								jjCheckNAddTwoStates(1, 2);
							}
							break;
						case 1:
							if ((0xfffffbffffffffffL & l) != 0L)
							{
								jjCheckNAddTwoStates(1, 2);
							}
							break;
						case 2:
							if (curChar == 42)
							{
								jjCheckNAddStates(24, 26);
							}
							break;
						case 3:
							if ((0xffff7bffffffffffL & l) != 0L)
							{
								jjCheckNAddTwoStates(4, 2);
							}
							break;
						case 4:
							if ((0xfffffbffffffffffL & l) != 0L)
							{
								jjCheckNAddTwoStates(4, 2);
							}
							break;
						case 5:
							if (curChar != 47)
							{
								break;
							}
							if (kind > 1)
							{
								kind = 1;
							}
							jjCheckNAddStates(3, 7);
							break;
						case 6:
							if (curChar == 47)
							{
								jjstateSet[jjnewStateCnt++] = 0;
							}
							break;
						case 7:
							if (curChar == 37)
							{
								jjCheckNAddStates(14, 16);
							}
							break;
						case 8:
							if ((0xffffffffffffdbffL & l) != 0L)
							{
								jjCheckNAddStates(14, 16);
							}
							break;
						case 9:
							if ((0x2400L & l) == 0L)
							{
								break;
							}
							if (kind > 1)
							{
								kind = 1;
							}
							jjCheckNAddStates(3, 7);
							break;
						case 10:
							if ((0x100002600L & l) == 0L)
							{
								break;
							}
							if (kind > 1)
							{
								kind = 1;
							}
							jjCheckNAddStates(3, 7);
							break;
						case 11:
							if (curChar != 13)
							{
								break;
							}
							if (kind > 1)
							{
								kind = 1;
							}
							jjCheckNAddStates(3, 7);
							break;
						case 12:
							if (curChar == 10)
							{
								jjstateSet[jjnewStateCnt++] = 11;
							}
							break;
						case 13:
							if (curChar != 10)
							{
								break;
							}
							if (kind > 1)
							{
								kind = 1;
							}
							jjCheckNAddStates(3, 7);
							break;
						case 14:
						case 15:
							if (curChar == 13)
							{
								jjCheckNAdd(13);
							}
							break;
						case 17:
							if ((0x3ff000000000000L & l) == 0L)
							{
								break;
							}
							if (kind > 6)
							{
								kind = 6;
							}
							jjstateSet[jjnewStateCnt++] = 17;
							break;
						case 18:
							if (curChar == 39)
							{
								jjCheckNAddStates(11, 13);
							}
							break;
						case 19:
							if ((0xffffff7f00000200L & l) != 0L)
							{
								jjCheckNAddStates(11, 13);
							}
							break;
						case 21:
							if (curChar == 10)
							{
								jjCheckNAddStates(11, 13);
							}
							break;
						case 22:
							if (curChar != 39)
							{
								break;
							}
							if (kind > 6)
							{
								kind = 6;
							}
							jjstateSet[jjnewStateCnt++] = 18;
							break;
						case 23:
							if (curChar == 13)
							{
								jjstateSet[jjnewStateCnt++] = 21;
							}
							break;
						case 24:
							if (curChar == 13)
							{
								jjCheckNAddStates(11, 13);
							}
							break;
						case 25:
							if (curChar == 10)
							{
								jjstateSet[jjnewStateCnt++] = 24;
							}
							break;
						case 26:
							if ((0x2400L & l) != 0L)
							{
								jjCheckNAddStates(11, 13);
							}
							break;
						case 28:
							if ((0x3ff000000000000L & l) != 0L)
							{
								jjCheckNAddTwoStates(28, 29);
							}
							break;
						case 30:
							if ((0xff000000000000L & l) != 0L)
							{
								jjCheckNAddTwoStates(30, 29);
							}
							break;
						case 32:
							if ((0x8400000000L & l) != 0L)
							{
								jjCheckNAddStates(11, 13);
							}
							break;
						case 33:
							if (curChar == 59 && kind > 6)
							{
								kind = 6;
							}
							break;
						case 34:
							if (curChar == 33 && kind > 6)
							{
								kind = 6;
							}
							break;
						case 35:
							if ((0xf400ac5800000000L & l) == 0L)
							{
								break;
							}
							if (kind > 6)
							{
								kind = 6;
							}
							jjCheckNAdd(36);
							break;
						case 36:
							if ((0xf400ec5800000000L & l) == 0L)
							{
								break;
							}
							if (kind > 6)
							{
								kind = 6;
							}
							jjCheckNAdd(36);
							break;
						case 38:
							if ((0x3ff000000000000L & l) == 0L)
							{
								break;
							}
							if (kind > 32)
							{
								kind = 32;
							}
							jjstateSet[jjnewStateCnt++] = 38;
							break;
						case 39:
							if (curChar == 48)
							{
								jjstateSet[jjnewStateCnt++] = 40;
							}
							break;
						case 40:
							if (curChar == 39)
							{
								jjAddStates(27, 29);
							}
							break;
						case 41:
							if (curChar == 39)
							{
								jjstateSet[jjnewStateCnt++] = 42;
							}
							break;
						case 42:
							if (curChar == 39 && kind > 36)
							{
								kind = 36;
							}
							break;
						case 43:
							if ((0xffffff7f00000200L & l) != 0L && kind > 36)
							{
								kind = 36;
							}
							break;
						case 46:
							if ((0x3ff000000000000L & l) != 0L)
							{
								jjCheckNAddTwoStates(46, 47);
							}
							break;
						case 48:
							if ((0xff000000000000L & l) != 0L)
							{
								jjCheckNAddTwoStates(48, 47);
							}
							break;
						case 50:
							if ((0x8400000000L & l) != 0L && kind > 36)
							{
								kind = 36;
							}
							break;
						case 51:
							if (curChar == 34)
							{
								jjCheckNAddStates(8, 10);
							}
							break;
						case 52:
							if ((0xfffffffb00000200L & l) != 0L)
							{
								jjCheckNAddStates(8, 10);
							}
							break;
						case 54:
							if (curChar == 10)
							{
								jjCheckNAddStates(8, 10);
							}
							break;
						case 55:
							if (curChar != 34)
							{
								break;
							}
							if (kind > 45)
							{
								kind = 45;
							}
							jjstateSet[jjnewStateCnt++] = 51;
							break;
						case 56:
							if (curChar == 13)
							{
								jjstateSet[jjnewStateCnt++] = 54;
							}
							break;
						case 57:
							if (curChar == 13)
							{
								jjCheckNAddStates(8, 10);
							}
							break;
						case 58:
							if (curChar == 10)
							{
								jjstateSet[jjnewStateCnt++] = 57;
							}
							break;
						case 59:
							if ((0x2400L & l) != 0L)
							{
								jjCheckNAddStates(8, 10);
							}
							break;
						case 61:
							if ((0x3ff000000000000L & l) != 0L)
							{
								jjCheckNAddTwoStates(61, 62);
							}
							break;
						case 63:
							if ((0xff000000000000L & l) != 0L)
							{
								jjCheckNAddTwoStates(63, 62);
							}
							break;
						case 65:
							if ((0x8400000000L & l) != 0L)
							{
								jjCheckNAddStates(8, 10);
							}
							break;
						case 66:
							if (curChar == 40 && kind > 49)
							{
								kind = 49;
							}
							break;
						case 67:
							if (curChar == 41 && kind > 50)
							{
								kind = 50;
							}
							break;
						case 73:
							if (curChar == 44 && kind > 56)
							{
								kind = 56;
							}
							break;
						case 74:
							if (curChar == 48)
							{
								jjAddStates(21, 23);
							}
							break;
						case 76:
							if ((0x3000000000000L & l) == 0L)
							{
								break;
							}
							if (kind > 36)
							{
								kind = 36;
							}
							jjstateSet[jjnewStateCnt++] = 76;
							break;
						case 78:
							if ((0xff000000000000L & l) == 0L)
							{
								break;
							}
							if (kind > 36)
							{
								kind = 36;
							}
							jjstateSet[jjnewStateCnt++] = 78;
							break;
						case 80:
							if ((0x3ff000000000000L & l) == 0L)
							{
								break;
							}
							if (kind > 36)
							{
								kind = 36;
							}
							jjstateSet[jjnewStateCnt++] = 80;
							break;
						case 82:
							if ((0x3ff000000000000L & l) == 0L)
							{
								break;
							}
							if (kind > 32)
							{
								kind = 32;
							}
							jjstateSet[jjnewStateCnt++] = 82;
							break;
						case 83:
							if (curChar != 46)
							{
								break;
							}
							if (kind > 57)
							{
								kind = 57;
							}
							jjCheckNAdd(84);
							break;
						case 84:
							if ((0xf400ac5800000000L & l) == 0L)
							{
								break;
							}
							if (kind > 6)
							{
								kind = 6;
							}
							jjCheckNAdd(84);
							break;
						case 85:
							if (curChar == 45)
							{
								jjCheckNAddStates(17, 20);
							}
							break;
						case 86:
							if ((0x3ff000000000000L & l) == 0L)
							{
								break;
							}
							if (kind > 36)
							{
								kind = 36;
							}
							jjCheckNAdd(86);
							break;
						case 87:
							if ((0x3ff000000000000L & l) != 0L)
							{
								jjCheckNAddTwoStates(87, 88);
							}
							break;
						case 88:
							if (curChar == 46)
							{
								jjCheckNAdd(89);
							}
							break;
						case 89:
							if ((0x3ff000000000000L & l) == 0L)
							{
								break;
							}
							if (kind > 42)
							{
								kind = 42;
							}
							jjCheckNAddTwoStates(89, 90);
							break;
						case 91:
							if ((0x280000000000L & l) != 0L)
							{
								jjCheckNAdd(92);
							}
							break;
						case 92:
							if ((0x3ff000000000000L & l) == 0L)
							{
								break;
							}
							if (kind > 42)
							{
								kind = 42;
							}
							jjCheckNAdd(92);
							break;
						case 93:
							if ((0x3ff000000000000L & l) == 0L)
							{
								break;
							}
							if (kind > 36)
							{
								kind = 36;
							}
							jjCheckNAddStates(0, 2);
							break;
						default:
							break;
					}
				} while (i != startsAt);
			}
			else if (curChar < 128)
			{
				long l = 1L << (curChar & 077);
				MatchLoop: do
				{
					switch (jjstateSet[--i])
					{
						case 16:
							if ((0x7fffffeL & l) != 0L)
							{
								if (kind > 32)
								{
									kind = 32;
								}
								jjCheckNAdd(38);
							}
							else if ((0x7fffffe00000000L & l) != 0L)
							{
								if (kind > 6)
								{
									kind = 6;
								}
								jjCheckNAdd(17);
							}
							else if ((0x4000000050000001L & l) != 0L)
							{
								if (kind > 6)
								{
									kind = 6;
								}
								jjCheckNAdd(36);
							}
							else if (curChar == 95)
							{
								if (kind > 32)
								{
									kind = 32;
								}
								jjCheckNAdd(82);
							}
							else if (curChar == 124)
							{
								if (kind > 55)
								{
									kind = 55;
								}
							}
							else if (curChar == 125)
							{
								if (kind > 54)
								{
									kind = 54;
								}
							}
							else if (curChar == 123)
							{
								if (kind > 53)
								{
									kind = 53;
								}
							}
							else if (curChar == 93)
							{
								if (kind > 52)
								{
									kind = 52;
								}
							}
							else if (curChar == 91)
							{
								if (kind > 51)
								{
									kind = 51;
								}
							}
							break;
						case 1:
							jjCheckNAddTwoStates(1, 2);
							break;
						case 3:
						case 4:
							jjCheckNAddTwoStates(4, 2);
							break;
						case 8:
							jjAddStates(14, 16);
							break;
						case 17:
							if ((0x7fffffe87fffffeL & l) == 0L)
							{
								break;
							}
							if (kind > 6)
							{
								kind = 6;
							}
							jjCheckNAdd(17);
							break;
						case 19:
							if ((0x7fffffffefffffffL & l) != 0L)
							{
								jjCheckNAddStates(11, 13);
							}
							break;
						case 20:
							if (curChar == 92)
							{
								jjAddStates(30, 36);
							}
							break;
						case 27:
							if (curChar == 120)
							{
								jjCheckNAdd(28);
							}
							break;
						case 28:
							if ((0x7e0000007eL & l) != 0L)
							{
								jjCheckNAddTwoStates(28, 29);
							}
							break;
						case 29:
							if (curChar == 92)
							{
								jjCheckNAddStates(11, 13);
							}
							break;
						case 31:
							if ((0x54404600000000L & l) != 0L)
							{
								jjCheckNAddStates(11, 13);
							}
							break;
						case 32:
							if ((0x110000000L & l) != 0L)
							{
								jjCheckNAddStates(11, 13);
							}
							break;
						case 35:
						case 36:
							if ((0x4000000050000001L & l) == 0L)
							{
								break;
							}
							if (kind > 6)
							{
								kind = 6;
							}
							jjCheckNAdd(36);
							break;
						case 37:
							if ((0x7fffffeL & l) == 0L)
							{
								break;
							}
							if (kind > 32)
							{
								kind = 32;
							}
							jjCheckNAdd(38);
							break;
						case 38:
							if ((0x7fffffe87fffffeL & l) == 0L)
							{
								break;
							}
							if (kind > 32)
							{
								kind = 32;
							}
							jjCheckNAdd(38);
							break;
						case 43:
							if ((0x7fffffffefffffffL & l) != 0L && kind > 36)
							{
								kind = 36;
							}
							break;
						case 44:
							if (curChar == 92)
							{
								jjAddStates(37, 40);
							}
							break;
						case 45:
							if (curChar == 120)
							{
								jjCheckNAdd(46);
							}
							break;
						case 46:
							if ((0x7e0000007eL & l) != 0L)
							{
								jjCheckNAddTwoStates(46, 47);
							}
							break;
						case 47:
							if (curChar == 92 && kind > 36)
							{
								kind = 36;
							}
							break;
						case 49:
							if ((0x54404600000000L & l) != 0L && kind > 36)
							{
								kind = 36;
							}
							break;
						case 50:
							if ((0x110000000L & l) != 0L && kind > 36)
							{
								kind = 36;
							}
							break;
						case 52:
							if ((0x7fffffffefffffffL & l) != 0L)
							{
								jjCheckNAddStates(8, 10);
							}
							break;
						case 53:
							if (curChar == 92)
							{
								jjAddStates(41, 47);
							}
							break;
						case 60:
							if (curChar == 120)
							{
								jjCheckNAdd(61);
							}
							break;
						case 61:
							if ((0x7e0000007eL & l) != 0L)
							{
								jjCheckNAddTwoStates(61, 62);
							}
							break;
						case 62:
							if (curChar == 92)
							{
								jjCheckNAddStates(8, 10);
							}
							break;
						case 64:
							if ((0x54404600000000L & l) != 0L)
							{
								jjCheckNAddStates(8, 10);
							}
							break;
						case 65:
							if ((0x110000000L & l) != 0L)
							{
								jjCheckNAddStates(8, 10);
							}
							break;
						case 68:
							if (curChar == 91 && kind > 51)
							{
								kind = 51;
							}
							break;
						case 69:
							if (curChar == 93 && kind > 52)
							{
								kind = 52;
							}
							break;
						case 70:
							if (curChar == 123 && kind > 53)
							{
								kind = 53;
							}
							break;
						case 71:
							if (curChar == 125 && kind > 54)
							{
								kind = 54;
							}
							break;
						case 72:
							if (curChar == 124 && kind > 55)
							{
								kind = 55;
							}
							break;
						case 75:
							if (curChar == 98)
							{
								jjstateSet[jjnewStateCnt++] = 76;
							}
							break;
						case 77:
							if (curChar == 111)
							{
								jjstateSet[jjnewStateCnt++] = 78;
							}
							break;
						case 79:
							if (curChar == 120)
							{
								jjCheckNAdd(80);
							}
							break;
						case 80:
							if ((0x7e0000007eL & l) == 0L)
							{
								break;
							}
							if (kind > 36)
							{
								kind = 36;
							}
							jjCheckNAdd(80);
							break;
						case 81:
							if (curChar != 95)
							{
								break;
							}
							if (kind > 32)
							{
								kind = 32;
							}
							jjCheckNAdd(82);
							break;
						case 82:
							if ((0x7fffffe87fffffeL & l) == 0L)
							{
								break;
							}
							if (kind > 32)
							{
								kind = 32;
							}
							jjCheckNAdd(82);
							break;
						case 84:
							if ((0x4000000050000001L & l) == 0L)
							{
								break;
							}
							if (kind > 6)
							{
								kind = 6;
							}
							jjstateSet[jjnewStateCnt++] = 84;
							break;
						case 90:
							if ((0x2000000020L & l) != 0L)
							{
								jjAddStates(48, 49);
							}
							break;
						default:
							break;
					}
				} while (i != startsAt);
			}
			else
			{
				int i2 = (curChar & 0xff) >> 6;
				long l2 = 1L << (curChar & 077);
				MatchLoop: do
				{
					switch (jjstateSet[--i])
					{
						case 1:
							if ((jjbitVec0[i2] & l2) != 0L)
							{
								jjCheckNAddTwoStates(1, 2);
							}
							break;
						case 3:
						case 4:
							if ((jjbitVec0[i2] & l2) != 0L)
							{
								jjCheckNAddTwoStates(4, 2);
							}
							break;
						case 8:
							if ((jjbitVec0[i2] & l2) != 0L)
							{
								jjAddStates(14, 16);
							}
							break;
						default:
							break;
					}
				} while (i != startsAt);
			}
			if (kind != 0x7fffffff)
			{
				jjmatchedKind = kind;
				jjmatchedPos = curPos;
				kind = 0x7fffffff;
			}
			++curPos;
			if ((i = jjnewStateCnt) == (startsAt = 94 - (jjnewStateCnt = startsAt)))
			{
				return curPos;
			}
			try
			{
				curChar = input_stream.readChar();
			}
			catch (java.io.IOException e)
			{
				return curPos;
			}
		}
	}

	static final int[] jjnextStates = { 86, 87, 88, 6, 7, 10, 12, 14, 52, 53, 55, 19, 20, 22, 8, 9, 15, 86, 39, 87, 74,
			75, 77, 79, 2, 3, 5, 41, 43, 44, 23, 25, 26, 27, 30, 31, 32, 45, 48, 49, 50, 56, 58, 59, 60, 63, 64, 65, 91, 92, };
	public static final String[] jjstrLiteralImages = { "", null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, };
	public static final String[] lexStateNames = { "DEFAULT", };
	static final long[] jjtoToken = { 0x3fe241100000041L, 0x0L, };
	static final long[] jjtoSkip = { 0x2L, 0x0L, };
	static final long[] jjtoSpecial = { 0x2L, 0x0L, };
	private CharStream input_stream;
	private final int[] jjrounds = new int[94];
	private final int[] jjstateSet = new int[188];
	protected char curChar;

	public TermParserTokenManager(CharStream stream)
	{
		input_stream = stream;
	}

	public TermParserTokenManager(CharStream stream, int lexState)
	{
		this(stream);
		SwitchTo(lexState);
	}

	public void ReInit(CharStream stream)
	{
		jjmatchedPos = jjnewStateCnt = 0;
		curLexState = defaultLexState;
		input_stream = stream;
		ReInitRounds();
	}

	private final void ReInitRounds()
	{
		int i;
		jjround = 0x80000001;
		for (i = 94; i-- > 0;)
		{
			jjrounds[i] = 0x80000000;
		}
	}

	public void ReInit(CharStream stream, int lexState)
	{
		ReInit(stream);
		SwitchTo(lexState);
	}

	public void SwitchTo(int lexState)
	{
		if (lexState >= 1 || lexState < 0)
		{
			throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState
					+ ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
		}
		else
		{
			curLexState = lexState;
		}
	}

	private final Token jjFillToken()
	{
		Token t = Token.newToken(jjmatchedKind);
		t.kind = jjmatchedKind;
		String im = jjstrLiteralImages[jjmatchedKind];
		t.image = im == null ? input_stream.GetImage() : im;
		t.beginLine = input_stream.getBeginLine();
		t.beginColumn = input_stream.getBeginColumn();
		t.endLine = input_stream.getEndLine();
		t.endColumn = input_stream.getEndColumn();
		return t;
	}

	int curLexState = 0;
	int defaultLexState = 0;
	int jjnewStateCnt;
	int jjround;
	int jjmatchedPos;
	int jjmatchedKind;

	public final Token getNextToken()
	{
		int kind;
		Token specialToken = null;
		Token matchedToken;
		int curPos = 0;

		EOFLoop: for (;;)
		{
			try
			{
				curChar = input_stream.BeginToken();
			}
			catch (java.io.IOException e)
			{
				jjmatchedKind = 0;
				matchedToken = jjFillToken();
				matchedToken.specialToken = specialToken;
				return matchedToken;
			}

			jjmatchedKind = 0x7fffffff;
			jjmatchedPos = 0;
			curPos = jjMoveStringLiteralDfa0_0();
			if (jjmatchedKind != 0x7fffffff)
			{
				if (jjmatchedPos + 1 < curPos)
				{
					input_stream.backup(curPos - jjmatchedPos - 1);
				}
				if ((jjtoToken[jjmatchedKind >> 6] & 1L << (jjmatchedKind & 077)) != 0L)
				{
					matchedToken = jjFillToken();
					matchedToken.specialToken = specialToken;
					return matchedToken;
				}
				else
				{
					if ((jjtoSpecial[jjmatchedKind >> 6] & 1L << (jjmatchedKind & 077)) != 0L)
					{
						matchedToken = jjFillToken();
						if (specialToken == null)
						{
							specialToken = matchedToken;
						}
						else
						{
							matchedToken.specialToken = specialToken;
							specialToken = specialToken.next = matchedToken;
						}
					}
					continue EOFLoop;
				}
			}
			int error_line = input_stream.getEndLine();
			int error_column = input_stream.getEndColumn();
			String error_after = null;
			boolean EOFSeen = false;
			try
			{
				input_stream.readChar();
				input_stream.backup(1);
			}
			catch (java.io.IOException e1)
			{
				EOFSeen = true;
				error_after = curPos <= 1 ? "" : input_stream.GetImage();
				if (curChar == '\n' || curChar == '\r')
				{
					error_line++;
					error_column = 0;
				}
				else
				{
					error_column++;
				}
			}
			if (!EOFSeen)
			{
				input_stream.backup(1);
				error_after = curPos <= 1 ? "" : input_stream.GetImage();
			}
			throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar,
					TokenMgrError.LEXICAL_ERROR);
		}
	}

}
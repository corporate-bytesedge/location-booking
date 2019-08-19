package com.bytesedge.bookvenue.util;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringFormat {

	private static Logger logger = LoggerFactory.getLogger(StringFormat.class);

	private static final String NL = "\n";

	/**
	 * Return the stack trace of an Throwable as a String
	 * 
	 * @param throwable
	 *            The Throwable containing the stack trace.
	 * @return The stack trace that would go to stderr with
	 *         throwable.printStackTrace ().
	 */
	public static String getStackTrace(Throwable throwable) {
		StackTraceElement[] stackTraceElements = throwable.getStackTrace();
		StringBuffer result = new StringBuffer();
		for (StackTraceElement se : stackTraceElements) {
			result.append("  ");
			result.append(se.toString());
			result.append("\n");
		}
		return result.toString();
	}

	/**
	 * Prepend a string to every line of text in a String
	 * 
	 * @param prepend
	 *            String to be prepended
	 * @param lines
	 *            Lines separated by newline character, from
	 *            System.getProperty("line.separator");
	 * @return Modified lines
	 */
	public static String prependToLines(String prepend, String lines) {
		if (lines == null)
			return null;
		if (prepend == null)
			return lines;
		StringBuffer result = new StringBuffer();
		boolean hasFinalNL = lines.endsWith(NL);
		StringTokenizer divided = new StringTokenizer(lines, NL);
		while (divided.hasMoreTokens()) {
			result.append(prepend + divided.nextToken());
			if (divided.hasMoreTokens() || hasFinalNL)
				result.append(NL);
		}
		return result.toString();
	}

	/**
	 * Replaces all occurences in string s of string x with string y. Stole from
	 * com.lgc.gpr.util.XmlUtil.replaceAll.
	 * 
	 * @param x
	 *            string to be replaced.
	 * @param y
	 *            string replacing each occurence of x.
	 * @param s
	 *            string containing zero or more x to be replaced.
	 * @return string s with all x replaced with y.
	 */
	public static String replaceAll(String x, String y, String s) {
		if (s == null)
			return null;
		int from = 0;
		int to = s.indexOf(x, from);
		if (to < 0)
			return s;
		StringBuffer d = new StringBuffer(s.length() + 32);
		while (to >= 0) {
			d.append(s.substring(from, to));
			d.append(y);
			from = to + x.length();
			to = s.indexOf(x, from);
		}
		return d.append(s.substring(from)).toString();
	}

	/**
	 * Break lines at convenient locations and indent if lines are too long. Try
	 * to break at word boundaries, but resort to breaking in the middle of a
	 * word with a backslash if necessary.
	 * 
	 * @param s
	 *            String that needs to be broken.
	 * @param maxchars
	 *            The maximum number of characters to use per line.
	 * @return String with newlines and indentations included as necessary. The
	 *         result will always have a final newline
	 */
	public static String breakLines(String s, int maxchars) {
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(s, NL);
		while (st.hasMoreTokens()) {
			sb.append(breakLine(st.nextToken(), maxchars));
		}
		return sb.toString();
	}

	/**
	 * Remove the whitespace from the string
	 * 
	 * @param str
	 *            clean this string
	 * @return the string without any whitespace; null, if the string is null
	 */
	public static String removeWhiteSpaces(String str) {
		if (str == null)
			return null;

		StringBuffer sb = new StringBuffer();
		CharacterIterator iter = new StringCharacterIterator(str);
		for (char c = iter.first(); c != CharacterIterator.DONE; c = iter.next()) {
			if (!Character.isWhitespace(c))
				sb.append(c);
		}

		return sb.toString();
	}

	/**
	 * Make a string representing the array, for debugging and error messages
	 * 
	 * @param v
	 *            Array to print.
	 * @return String representing the vector,
	 */
	public static String toString(int[] v) {
		StringBuffer sb = new StringBuffer("(");
		for (int i = 0; i < v.length; ++i) {
			sb.append(Integer.toString(v[i]));
			if (i < v.length - 1) {
				sb.append(",");
			}
		}
		sb.append(")");
		return sb.toString();
	}

	/**
	 * Make a string representing the array, for debugging and error messages
	 * 
	 * @param v
	 *            Array to print.
	 * @return String representing the vector,
	 */
	public static String toString(float[] v) {
		StringBuffer sb = new StringBuffer("(");
		for (int i = 0; i < v.length; ++i) {
			sb.append(Float.toString(v[i]));
			if (i < v.length - 1) {
				sb.append(",");
			}
		}
		sb.append(")");
		return sb.toString();
	}

	/**
	 * Make a string representing the array, for debugging and error messages
	 * 
	 * @param v
	 *            Array to print.
	 * @return String representing the vector,
	 */
	public static String toString(int[][] v) {
		StringBuffer sb = new StringBuffer();
		for (int[] col : v) {
			sb.append(toString(col));
		}
		return sb.toString();
	}

	/**
	 * Make a string representing the array, for debugging and error messages
	 * 
	 * @param v
	 *            Array to print.
	 * @return String representing the vector,
	 */
	public static String toString(float[][] v) {
		StringBuffer sb = new StringBuffer();
		for (float[] col : v) {
			sb.append(toString(col));
		}
		return sb.toString();
	}

	/**
	 * Make a string representing the array, for debugging and error messages
	 * 
	 * @param v
	 *            Array to print.
	 * @return String representing the vector,
	 */
	public static String toString(double[] v) {
		StringBuffer sb = new StringBuffer("(");
		for (int i = 0; i < v.length; ++i) {
			sb.append(Double.toString(v[i]));
			if (i < v.length - 1) {
				sb.append(",");
			}
		}
		sb.append(")");
		return sb.toString();
	}

	/**
	 * Break a line at convenient locations and indent if lines are too long.
	 * Try to break at word boundaries, but resort to breaking in the middle of
	 * a word with a backslash if necessary.
	 * 
	 * @param s
	 *            String that needs to be broken. Do not pass a string that
	 *            already contains newlines.
	 * @param maxchars
	 *            The maximum number of characters to use per line.
	 * @return String with newlines and indentations included as necessary. The
	 *         result will always have a final newline
	 */
	private static String breakLine(String s, int maxchars) {
		StringBuffer sb = new StringBuffer();
		printLine(s, maxchars, sb);
		return sb.toString();
	}

	private static void printLine(String s, int maxline, StringBuffer sb) {
		int maxbreak = maxline / 3, maxindent = 2 * maxline / 3;
		if (s.length() < maxline) {
			sb.append(s + NL);
			return;
		}

		int n = maxline - 1;
		try {
			while (n >= maxbreak && s.charAt(n) != ' ') {
				--n;
			}
		} catch (java.lang.StringIndexOutOfBoundsException e) {
			logger.error(e.getMessage(), e);
			throw new IllegalStateException("s=|" + s + "|" + NL + "s.length()=" + s.length() + NL + "n=" + n + NL
					+ "maxline=" + maxline + NL + "maxbreak=" + maxbreak + NL + e.getMessage());
		}

		String extra = "";
		String indent = "";
		// See if we stopped after all spaces
		boolean allAreSpaces = true;
		for (int i = 0; allAreSpaces && i < n; ++i) {
			allAreSpaces = allAreSpaces && s.charAt(i) == ' ';
		}
		if (allAreSpaces || s.charAt(n) != ' ') {// If must break a word, do so
													// at the end
			n = maxline - 2;
			extra = "";
		} // break at the end of the line
		else {
			indent = indentString(s, maxindent);
		} // break at word with same indentation
		printLine(s.substring(0, n) + extra, maxline, sb); // print before break
		printLine(indent + s.substring(n), maxline, sb); // print after break
	}

	private static String indentString(String s, int maxindent) {
		int n = 0;
		while (n < maxindent && n < s.length() && s.charAt(n) == ' ') {
			++n;
		}
		if (n < 1)
			n = 1;
		return s.substring(0, n - 1);
	}

	/* This encode method is used to convert the text to html equivalent. */
	public static String encode(String theString, boolean asHTML, boolean escapeSpaces) {

		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len * 2);
		for (int x = 0; x < len; x++) {
			char aChar = theString.charAt(x);
			switch (aChar) {
			case '\'':
				outBuffer.append("'");
				break;
			case '&':
				outBuffer.append("&amp;");
				break;
			default:
				outBuffer.append(aChar);
				break;
			case ' ':
				if (asHTML)
					outBuffer.append("&nbsp;");
				else {
					if (escapeSpaces)
						outBuffer.append('\\');
					outBuffer.append(' ');
				}
				break;
			case '\\':
				outBuffer.append('\\');
				break;
			case '\t':
				if (asHTML)
					outBuffer.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				else {
					outBuffer.append('\\');
					outBuffer.append('t');
				}
				break;
			case '\"':
				if (asHTML)
					outBuffer.append("&quot;");
				else
					outBuffer.append('\"');
				break;
			case '>':
				if (asHTML)
					outBuffer.append("&gt;");
				else
					outBuffer.append('>');
				break;
			case '<':
				if (asHTML)
					outBuffer.append("&lt;");
				else
					outBuffer.append('<');
				break;
			case '\n':
				if (asHTML)
					outBuffer.append("<br>");
				else {
					outBuffer.append('\n');
				}
				break;

			case '\f':
				outBuffer.append('\\');
				outBuffer.append('f');
				break;
			case '$':
				outBuffer.append("$");
				break;

			}
		}
		return outBuffer.toString();
	}

	public static String encodeTextArea(String theString) {
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len * 2);
		for (int x = 0; x < len; x++) {
			char aChar = theString.charAt(x);
			switch (aChar) {
			default:
				outBuffer.append(aChar);
				break;
			case '\n':
				outBuffer.append("<br>");
				break;
			}
		}
		return outBuffer.toString();
	}
}

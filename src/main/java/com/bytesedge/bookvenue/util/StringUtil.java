package com.bytesedge.bookvenue.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringUtil {

	private static Logger logger = LoggerFactory.getLogger(StringUtil.class);

	public static boolean isEmpty(String param) {
		if(param == null)
			return true;
		else if("".equals(param.trim()))
			return true;
		return false;
	}

	public static String trimString(String param) {
		if(param == null) {
			return null;
		}else {
			return param.trim();
		}
	}

	public static String convertBytes(long size) {
		//Expected size is in Bites
		final long KB = 1024;
		final long MB = KB * 1024;
		final long GB = MB * 1024;
		final long TB = GB * 1024;
		float convertedSize=0;
		String type;
		if(size < KB) {
			convertedSize = size;
			type = " Bytes";
		}
		else if(size < MB) {
			convertedSize = (float)size/KB;
			type = " KB";
		}
		else if(size >= MB && size < GB) {
			convertedSize = (float)size/MB;
			type = " MB";
		}
		else if(size >= GB && size < TB) {
			convertedSize =  (float)size/GB;
			type = " GB";
		}
		else {
			convertedSize = (float)size/TB;
			type = " TB";
		}
		double sizeInFraction = (int)((convertedSize+0.005)*100.0)/100.0;
		return sizeInFraction+type;
	}

	public static String addSlashes(String str) {
		if(str == null)
			return "";
		else
			return str.replace("'", "\\'");
	}

	public static boolean isEqualStrings(String str1, String str2){
		if(str1 == null && str2 == null)
			return true;
		if(str1 == null && str2 != null)
			return false;
		return str1.equalsIgnoreCase(str2);
	}

	public static String wrap(String withNoWrap, int maxChars) {
		if(withNoWrap == null || maxChars <= 0)
			return withNoWrap;
		return StringFormat.breakLines(withNoWrap, maxChars);
	}

	public static String convertReturnsToLineBreakTags(String withReturns) {
		if (withReturns != null) {
			withReturns = withReturns.replaceAll("<br\r\n/>", "<br />").replaceAll("<br\r/>", "<br />").replaceAll("<br\n/>", "<br />");
			withReturns = withReturns.replaceAll("\r\n", "<br />").replaceAll("\r", "<br />").replaceAll("\n", "<br />");
		}
		return withReturns;
	}

	public static boolean isValidIp(String ipAddr) {
		try {
			char[] ar = ipAddr.toCharArray();
			int c = 0;
			for(int i = 0 ; i < ar.length; i++) {
				if(ipAddr.charAt(i) == '.')
					c++;
			}
			if(c != 3)
				return false;

			StringTokenizer ipToken = new StringTokenizer(ipAddr, ".");
			if (ipToken.countTokens() != 4){
				return false;
			}
			if(!isValidIP(ipAddr)){
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isValidIpRange(String ipRange) {
		String range[] = ipRange.replaceAll("\r\n", "\n").split(",");
		boolean notValid = false, invalidSubnet = false;
		for (int i = 0; i < range.length; i++) {
			notValid = false;
			if (range[i].contains("-")) {
				String ips[] = range[i].split("-");
				if (ips.length != 2) {
					notValid = true;
					break;
				}

				for (int j = 0; j < ips.length; j++) {
					String ipAddr = ips[j].trim();// .replaceAll("*","100");
					// //replace * with some
					// valid ip octet
					if (!isValidIp(ipAddr) || j > 1) {
						notValid = true;
						break;
					} else if (j == 1
							&& (ip2long(ips[j].trim()) < ip2long(ips[j - 1]
									.trim()))) {
						notValid = true;
						break;
					}
					// TODO : handle case for end ip is greater than start ip
				}
			} else if (range[i].contains("*.*")) {
				notValid = true;
				break;
			} else if (range[i].contains("*")) {
				String ipAddr = range[i].trim().replaceAll("\\*", "100"); // replace * with some valid ip octet
				if (!isValidIp(ipAddr)) {
					notValid = true;
					break;
				}
			} else if (range[i].contains("/")) {
				String[] temp = range[i].split("/");
				if (!isValidIp(temp[0])) {
					notValid = true;
					break;
				}
				if (temp[1].length() > 2) {
					invalidSubnet = true;
					break;
				} else if (temp[1].length() <= 2) {
					try {
						if (Long.parseLong(temp[1]) > 32) {
							invalidSubnet = true;
							break;
						}
					} catch (Exception e) {
						invalidSubnet = true;
						break;
					}
				}
			} else {
				if (!isValidIp(range[i].trim())) {
					notValid = true;
					break;
				}
			}
		}
		if (notValid) {
			return false;
		}
		if (invalidSubnet) {
			return false;
		}
		return true;
	}

	private static long ip2long(String ipAddr) {
		long numformat = 0;
		StringTokenizer ipToken = new StringTokenizer(ipAddr, ".");
		while (ipToken != null && ipToken.hasMoreTokens()) {
			numformat <<= 8;
			numformat |= Long.parseLong(ipToken.nextToken().trim());
		}
		return numformat;
	}

	/**
	 * This method ensures that the output String has only
	 * valid XML unicode characters as specified by the
	 * XML 1.0 standard. For reference, please see
	 * <a href="http://www.w3.org/TR/2000/REC-xml-20001006#NT-Char">the
	 * standard</a>. This method will return an empty
	 * String if the input is null or empty.
	 *
	 * @param inputString The String whose non-valid characters we want to remove.
	 * @return The inputString String, stripped of non-valid characters.
	 */
	public static String escapeNonValidXMLCharacters(String inputString) {
		StringBuffer out = new StringBuffer(); // Used to hold the output.
		char current; // Used to reference the current character.
		if(isEmpty(inputString)) return inputString;
		if (inputString == null || ("".equals(inputString))) return ""; // vacancy test.
		for (int i = 0; i < inputString.length(); i++) {
			current = inputString.charAt(i); // NOTE: No IndexOutOfBoundsException caught here; it should not happen.
			if ((current == 0x9) ||
					(current == 0xA) ||
					(current == 0xD) ||
					((current >= 0x20) && (current <= 0xD7FF)) ||
					((current >= 0xE000) && (current <= 0xFFFD)) ||
					((current >= 0x10000) && (current <= 0x10FFFF)))
				out.append(current);
		}
		return out.toString();
	}

	public static Set<Long> convertArrayToSet(long[] array) {
		Set<Long> set = new HashSet<Long>();
		if(array !=null && array.length > 0){
			for(long i : array){
				set.add(i);
			}
		}
		return set;
	}

	public static long[] convertListToArray(List<Long> ids) {
		if(ids == null) {
			return null;
		}
		long[] array = new long[ids.size()];
		for(int i=0 ; i <ids.size() ; i++) {
			array[i] = ids.get(i);
		}
		return array;
	}

	public static List<Long> convertArrayToList(long[] values) {
		List<Long> list = new ArrayList<Long>();
		if(values != null && values.length > 0) {
			for(long val: values) {
				list.add(val);
			}
		}
		return list;
	}

	public static String[] convertListToStringArray(List<String> ids) {
		String[] array = new String[ids.size()];
		for(int i=0 ; i <ids.size() ; i++) {
			array[i] = ids.get(i);
		}
		return array;
	}

	public static Set<Long> convertListToSet(List<Long> ids) {
		Set<Long> set = new HashSet<Long>();
		for(Long id : ids) {
			set.add(id);
		}
		return set;
	}

	public static long[] convertSetToArray(Set<Long> set) {
		long[] array = null;
		if(set != null && set.size() > 0){
			array = new long[set.size()];
			int i =0;
			for(long el: set){
				array[i++] = el;
			}
		}
		return array;
	}

	public static List<Long> convertSetToList(Set<Long> set) {
		List<Long> list = new ArrayList<Long>();
		if(set != null && set.size() > 0){
			for(long el: set){
				list.add(el);
			}
		}
		return list;
	}

	public static List<String> convertStringSetToList(Set<String> set) {
		List<String> list = new ArrayList<String>();
		if(set != null && set.size() > 0){
			for(String el: set){
				list.add(el);
			}
		}
		return list;
	}

	public static String[] removeElement(String[] input, String deleteMe) {
		List<String> result = new ArrayList<String>();
		if(input == null || input.length <= 0 || deleteMe == null) {
			return input;
		}
		for (String item : input) {
			if (!deleteMe.equalsIgnoreCase(item)) {
				result.add(item);
			}
		}
		input = new String[result.size()];
		return result.toArray(input);
	}

	public static List<Long> convertStringToList(String strs,String delimit) {
		List<Long> values = new ArrayList<Long>();
		if(strs != null) {
			for(String str : strs.split(delimit)) {
				try {
					values.add(Long.parseLong(str.trim()));
				} catch(Throwable e) {
					//ignore
				}
			}
		}
		return values;
	}

	public static Set<Long> convertStringToSet(String strs,String delimit) {
		Set<Long> values = new HashSet<Long>();
		if(strs != null) {
			for(String str : strs.split(delimit)) {
				try {
					values.add(Long.parseLong(str.trim()));
				} catch(Throwable e) {
					//ignore
				}
			}
		}
		return values;
	}

	public static List<Integer> convertStringToIntList(String strs,String delimit) {
		List<Integer> values = new ArrayList<Integer>();
		if(strs != null) {
			for(String str : strs.split(delimit)) {
				try {
					values.add(Integer.parseInt(str.trim()));
				} catch(Throwable e) {
					//ignore
				}
			}
		}
		return values;
	}

	public static List<Long> convertStringToLongList(String strs,String delimit) {
		List<Long> values = new ArrayList<Long>();
		if(strs != null) {
			for(String str : strs.split(delimit)) {
				try {
					values.add(Long.parseLong(str.trim()));
				} catch(Throwable e) {
					//ignore
				}
			}
		}
		return values;
	}

	public static List<String> convertStringToStringList(String strs,String delimit) {
		List<String> values = new ArrayList<String>();
		if(strs != null) {
			try {
				values = new ArrayList<String>(Arrays.asList(strs.split(",")));
			} catch(Throwable e) {
				//ignore
			}
		}

		return values;
	}

	public static Set<String> convertStringtoStringSet(String strs, String delimit) {
		Set<String> values = new HashSet<String>();
		if(!isEmpty(strs)) {
			for(String str : strs.split(delimit)) {
				try {
					if(!isEmpty(str)) {
						values.add(str.trim());
					}
				} catch(Throwable e) {
					//ignore
				}
			}
		}
		return values;
	}

	public static String convertStringListToString(List<String> list, String delimit) {
		String finalString = "";
		for(String value : list) {
			finalString = finalString.isEmpty() ? value : finalString+delimit+value ;
		}
		return finalString;
	}

	public static String convertStringSetToString(Set<String> list, String delimit) {
		String finalString = "";
		for(String value : list) {
			finalString = finalString.isEmpty() ? value : finalString+delimit+value ;
		}
		return finalString;
	}

	public static String convertStringArrayToString(String[] array, String delimit) {
		String finalString = "";
		if(array == null || array.length <= 0) {
			return finalString;
		}
		for(String value : array) {
			finalString = finalString.isEmpty() ? value : finalString + delimit + value;
		}
		return finalString;
	}

	public static String convertLongArrayToString(long[] list, String delimit) {
		String finalString = "";
		for(Long value : list) {
			finalString = finalString.isEmpty() ? String.valueOf(value) : finalString+delimit+String.valueOf(value) ;
		}
		return finalString;
	}

	public static String convertLongListToString(List<Long> list, String delimiter) {
		if (list == null || list.isEmpty()){
			return "";
		}
		StringBuffer sb = new StringBuffer();

		for (Long element : list){
			sb.append(element + delimiter);
		}
		sb.delete(sb.length()-delimiter.length(), sb.length());

		return sb.toString();
	}

	public static String convertLongArrayToString(Long[] list, String delimiter) {
		if (list == null || list.length <= 0){
			return "";
		}
		StringBuffer sb = new StringBuffer();

		for (Long element : list){
			sb.append(element + delimiter);
		}
		sb.delete(sb.length()-delimiter.length(), sb.length());

		return sb.toString();
	}

	public static String convertLongListToString(Set<Long> list, String delimiter) {
		if (list == null || list.isEmpty()){
			return "";
		}
		StringBuffer sb = new StringBuffer();

		for (Long element : list){
			sb.append(element + delimiter);
		}
		sb.delete(sb.length()-delimiter.length(), sb.length());

		return sb.toString();
	}

	public static boolean isContained(String src, String pattern, boolean caseSensitive){
		if(isEmpty(src)){
			return false;
		}else if(isEmpty(pattern)){
			return true;
		}else if(src.toLowerCase().contains(pattern.toLowerCase())){
			return true;
		}else{
			Pattern p;
			if(caseSensitive){
				p = Pattern.compile(pattern);
			}else{
				p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
			}
			return p.matcher(src).matches();
		}
	}

	public static void main(String args[]) {
		String text = "&amp;";
		logger.debug(HtmlUtils.htmlEscape(text));
	}

	/*
	 *
	 */
	public static String convertStreamToString(InputStream is) throws IOException
	{
		if (is == null) {
			return "";
		}

		Writer writer = new StringWriter();
		char[] buffer = new char[1024];
		try
		{
			Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			int n;
			while ((n = reader.read(buffer)) != -1)
			{
				writer.write(buffer, 0, n);
			}
		}finally {
			is.close();
		}
		return writer.toString();
	}

	public static String escapeRegExWildChars(String searchString) {
		String result = searchString;
		// replace all the string that contains the following characters
		//   + - && || ! ( ) { } [ ] ^ " ~ * ? : \ with the character preceded by \
		if(result != null && (result.trim().length() > 0)) {
			result = result.replaceAll("\\\\", "\\\\\\\\");
			result = result.replaceAll("\\.", "\\\\.");
			result = result.replaceAll("\\^", "\\\\^");
			result = result.replaceAll("\\?", "\\\\?");
			result = result.replaceAll("\\|", "\\\\|");
			result = result.replaceAll("\\(", "\\\\(");
			result = result.replaceAll("\\)", "\\\\)");
			result = result.replaceAll("\\[", "\\\\[");
			result = result.replaceAll("\\]", "\\\\]");
			result = result.replaceAll("\\{", "\\\\{");
			result = result.replaceAll("\\}", "\\\\}");
			result = result.replaceAll("\\+", "\\\\+");
			result = result.replaceAll("\\*", "\\\\*");
			if(result.indexOf("$") != -1){
				StringBuffer buff = new StringBuffer();
				while(result.indexOf("$") != -1){
					buff.append(result.substring(0,result.indexOf("$"))).append("\\$");
					result = result.substring(result.indexOf("$")+1);
				}
				if(result.length() > 0){
					buff.append(result.trim());
				}
				result = buff.toString().trim();
			}
		}
		return result;
	}

	/**
	 * Normalise the string for escape the special chars
	 *
	 * @param str
	 * @return
	 */
	public static String normalize(String str) {
		if (str == null) {
			return "";
		}

		try {
			str = str.replaceAll("\u2122", "(TM)").replaceAll("\u00A9", "(C)").replaceAll("\u00A5", "(Yen)");
		} catch (Exception e) {
			//ignore
		}
		StringBuffer strbuffer = new StringBuffer();
		int len = str.length();

		for (int i = 0; i < len; i++) {
			char ch = str.charAt(i);
			switch (ch) {
			case '<':
				strbuffer.append("&lt;");
				break;
			case '>':
				strbuffer.append("&gt;");
				break;
			case '&':
				strbuffer.append("&amp;");
				break;
			case '"':
				strbuffer.append("&quot;");
				break;
			default:
				if(ch > 0 && ch <= 126) {
					strbuffer.append(ch);
				} else {
					strbuffer.append(" ");
				}
			}
		}
		return (strbuffer.toString());
	}

	/**
	 * Handle DB special characters
	 *
	 * @param srcStr
	 * @return
	 */
	public static String handleDBSpecialChars(String srcStr) {
		if (srcStr == null || srcStr.equals("")) {
			return srcStr;
		}
		// For handling Search with speacial characters '_' and '%' in DB
		return srcStr.replace("_", "\\_").replace("%", "\\%");
	}

	public static boolean isValidIP(String ip){
		if(StringUtil.isEmpty(ip))
			return false;
		Pattern p = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
		Matcher matcher = p.matcher(ip.trim());
		return matcher.matches();
	}

	public static long[] convertStringToArray(String arrayStr,String delimit) {
		return convertListToArray(convertStringToList(arrayStr, delimit));
	}

	public static long getVersionValue(String version) {
		StringTokenizer st = new StringTokenizer(version, ".");

		int digit  = 0;
		int noof0s = 0;
		int summedValue  = 0;
		String digitString = "";

		noof0s = st.countTokens()-1;
		while(st.hasMoreTokens()) {
			digit = Integer.parseInt(st.nextToken());
			digitString = "1";
			for(int i = 0; i<noof0s; i++)
				digitString = digitString + "0";
			summedValue = summedValue + (digit * Integer.parseInt(digitString));
			noof0s--;
		}
		return summedValue;
	}

	public static boolean isNumeric(String number){
		if(StringUtil.isEmpty(number))
			return false;
		Pattern p = Pattern.compile("\\d+");
		Matcher matcher = p.matcher(number.trim());
		return matcher.matches();
	}

	public static String convertToStringForLikeQuery(List<String> list) {
		String finalString = "";
		for(String value : list) {
			finalString = finalString.isEmpty() ? "'" + value + "'" : finalString+",'"+value + "'";
		}
		return finalString;
	}

	public static List<Long> convertLongArrayToList(long[] list, String delimit) {
		String finalString = "";
		if (list == null) {
			return null;
		}
		finalString = convertLongArrayToString(list, delimit);
		return convertStringToList(finalString, delimit);
	}

	public static boolean isRestrictSpChar(String word){
		if(StringUtil.isEmpty(word)) {
			return false;
		}
		Pattern p = Pattern.compile("[a-zA-Z0-9-:&()/#@,\\._ ]*");
		Matcher matcher = p.matcher(word.trim());
		return matcher.matches();
	}

	public static boolean isFloat(String number){
		if(StringUtil.isEmpty(number))
			return false;
		Pattern p = Pattern.compile("^[0-9]{0,7}(?:[.][0-9]{1,2})?$");
		Matcher matcher = p.matcher(number.trim());
		return matcher.matches();
	}

	public static String convertMapToString(Map<String, String> map) {
		if(map == null || map.size() <= 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for(Entry<String, String> entry : map.entrySet()) {
			sb.append(entry.getKey() + ":" + entry.getValue() +"; ");
		}
		return sb.toString();
	}

	public static List<Long> convertStringListToLongList(List<String> strings) {
		List<Long> longValues = new ArrayList<Long>();
		if(strings != null) {
			for(String str: strings) {
				if (str.equals("")) {
					continue;
				}
				longValues.add(Long.parseLong(str.trim()));
			}
		}
		return longValues;
	}

	public static boolean contains(String str,String[] arr){
		if(arr == null || str == null) {
			return false;
		}
		for(String atr : arr) {
			if(str.equals(atr)) {
				return true;
			}
		}
		return false;
	}

	public static Map<String,Object> getFormatMemoryAndUnit(long size) {
		//Expected size is in Bites
		final long KB = 1024;
		final long MB = KB * 1024;
		final long GB = MB * 1024;
		final long TB = GB * 1024;
		final long PB = TB * 1024;
		float convertedSize=0;
		String type;
		Map<String,Object> formatMemoryAndUnit = new HashMap<String,Object>();
		if(size < KB) {
			convertedSize = size;
			type = " Bytes";
		}
		else if(size < MB) {
			convertedSize = (float)size/KB;
			type = " KB";
		}
		else if(size >= MB && size < GB) {
			convertedSize = (float)size/MB;
			type = " MB";
		}
		else if(size >= GB && size < TB) {
			convertedSize =  (float)size/GB;
			type = " GB";
		}
		else if(size >= TB && size < PB) {
			convertedSize = (float)size/TB;
			type = " TB";
		}
		else {
			convertedSize = (float)size/PB;
			type = " PB";
		}
		double sizeInFraction = (int)((convertedSize+0.005)*100.0)/100.0;
		formatMemoryAndUnit.put("memory",sizeInFraction);
		formatMemoryAndUnit.put("unit",type);
		return formatMemoryAndUnit;
	}

	public static String unescapeHTML(String input) {
		if(isEmpty(input)) {
			return input;
		}
		return HtmlUtils.htmlUnescape(input);
	}

	public static String escapeHTML(String input) {
		if(StringUtil.isEmpty(input)) {
			return input;
		}
		return HtmlUtils.htmlEscape(input);
	}

	public static String escapeHTMLWithLineBreakTags(String input) {
		if(StringUtil.isEmpty(input)) {
			return input;
		}
		return StringUtil.convertReturnsToLineBreakTags(HtmlUtils.htmlEscape(input));
	}

	public static String lineBreakTags(String input) {
		if(StringUtil.isEmpty(input)) {
			return input;
		}
		return StringUtil.convertReturnsToLineBreakTags(input.replaceAll("<script>.*</script>", ""));
	}

	public static String removeSpaces(String srcStr){
		if ( null != srcStr) {
			return srcStr.replaceAll("\\s", "");
		}
		return srcStr;
	}

	/**
	 * converts a json string to map
	 * @param jsonString
	 * @return
	 */
	public static Map<String,String> convertJsonStringToMap(String jsonString){
		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			map = mapper.readValue(jsonString, new TypeReference<HashMap<String, String>>() {});
		} catch (Exception e) {
			logger.debug("exception raised while converting jsonString to Map"+e);
		}
		return map;
	}
}

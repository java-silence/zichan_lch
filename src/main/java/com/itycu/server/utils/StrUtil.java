package com.itycu.server.utils;

import java.util.List;
import java.util.Locale;

import com.google.common.collect.Lists;

/**
 * 字符串转化工具类
 * 
 * @author 小威老师 xiaoweijiagou@163.com
 *
 */
public class StrUtil {

	/**
	 * 字符串转为驼峰
	 * 
	 * @param str
	 * @return
	 */
	public static String str2hump(String str) {
		StringBuffer buffer = new StringBuffer();
		if (str != null && str.length() > 0) {
			if (str.contains("_")) {
				String[] chars = str.split("_");
				int size = chars.length;
				if (size > 0) {
					List<String> list = Lists.newArrayList();
					for (String s : chars) {
						if (s != null && s.trim().length() > 0) {
							list.add(s);
						}
					}

					size = list.size();
					if (size > 0) {
						buffer.append(list.get(0));
						for (int i = 1; i < size; i++) {
							String s = list.get(i);
							buffer.append(s.substring(0, 1).toUpperCase());
							if (s.length() > 1) {
								buffer.append(s.substring(1));
							}
						}
					}
				}
			} else {
				buffer.append(str);
			}
		}

		return buffer.toString();
	}


	public static float getfloat(String text) {
		//arr   cf32c83c    这一串
		//f    -2147483.8   转完是这样的
		System.out.println("text" + text);
		byte[] arr = hexStr2Bytes(text);
		System.out.println("arr" + byteToHex(arr));
		float f= Float.intBitsToFloat(getInt(arr,0));
		f = (float) (Math.round(f * 1000))/1000;
		System.out.println("f" + f);

		return f;
	}

	public static int getInt(byte[] arr,int index){
		return (0xff000000 & (arr[index+3]<<24)) |
				(0x00ff0000 & (arr[index+2]<<16)) |
				(0x0000ff00 & (arr[index+1]<<8)) |
				(0x000000ff & arr[index]);
	}


	/**
	 * bytes字符串转换为Byte值
	 *
	 * @param src String Byte字符串，每个Byte之间没有分隔符(字符范围:0-9 A-F)
	 * @return byte[]
	 */
	public static byte[] hexStr2Bytes(String src) {
		/*对输入值进行规范化整理*/
		src = src.trim().replace(" ", "").toUpperCase(Locale.US);
		//处理值初始化
		int m = 0, n = 0;
		int iLen = src.length() / 2; //计算长度
		byte[] ret = new byte[iLen]; //分配存储空间

		for (int i = 0; i < iLen; i++) {
			m = i * 2 + 1;
			n = m + 1;
			ret[i] = (byte) (Integer.decode("0x" + src.substring(i * 2, m) + src.substring(m, n)) & 0xFF);
		}
		return ret;
	}

	/**
	 * byte数组转hex
	 * @param bytes
	 * @return
	 */
	public static String byteToHex(byte[] bytes){
		String strHex = "";
		StringBuilder sb = new StringBuilder("");
		for (int n = 0; n < bytes.length; n++) {
			strHex = Integer.toHexString(bytes[n] & 0xFF);
			sb.append((strHex.length() == 1) ? "0" + strHex : strHex); // 每个字节由两个字符表示，位数不够，高位补0
		}
		return sb.toString().trim();
	}

	/**
	 * 生成指定位数的数字
	 * @param count
	 * @param num
	 */
	public static String getStaticNum(int count,int num) {

		int length = String.valueOf(count).length();
		int n = num -length;
		if (n == 0) {
			return String.valueOf(count);
		}
		// 生成指定长度0
		String a = "";
		for (int i = 0; i < n; i++) {
			a = a+"0";
		}
		return a+String.valueOf(count);
	}

}

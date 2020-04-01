package net.wanho.util;

import org.apache.commons.codec.digest.DigestUtils;

public class PwdUtils {

	// 加密次数
	static int count = 15;

	public static String getPwd(String pwd, String name, String salt) {
		String res = pwd + name + salt;
		for (int i = 0; i < count; i++) {
			res = DigestUtils.md5Hex(res);
		}
		return res;
	}
	//dc239aa8c351fed684f1d9c523ea0f06
	public static void main(String[] args) {
		System.out.println(PwdUtils.getPwd("admin", "admin", "111111"));
	}

}

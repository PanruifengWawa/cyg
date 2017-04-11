package com.cyg.utils;

import com.qiniu.util.Auth;

public class QiNiuUtil {
	
	public static String getUploadToken() {
		String accessKey = "2EkHs4sPHlelB-JYR5WuDp3jp9spsqyxIkluejva";
		String secretKey = "RniQyKlZ3hpLmS8rp-OhVHtUwkSqQiDFMi6TAF6g";
		String bucket = "cygfile";
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
		return upToken;
	}

}

package com.lzq.life.common;

import com.lzq.life.vo.BaseResponse;

/**
 * 
 * @Title ResponseFactroy.java
 * @Package com.lzq.life.common
 * @Description 构造响应信息
 * @author lizhiqiang
 * @date May 23, 2020 6:44:54 PM
 * 
 */
public class ResponseFactroy {

	public static BaseResponse<?> createSuccessResponse() {
		BaseResponse<?> resp = new BaseResponse<>();
		resp.setCode(ResponseCodeEnum.SUCCESS.getCode());
		resp.setMessage(ResponseCodeEnum.SUCCESS.getMsg());
		return resp;
	}

	public static <T> BaseResponse<T> createSuccessResponse(T data) {
		BaseResponse<T> resp = new BaseResponse<>();
		resp.setCode(ResponseCodeEnum.SUCCESS.getCode());
		resp.setMessage(ResponseCodeEnum.SUCCESS.getMsg());
		resp.setData(data);
		return resp;
	}

	public static BaseResponse<?> createErrorResponse() {
		BaseResponse<?> resp = new BaseResponse<>();
		resp.setCode(ResponseCodeEnum.ERROR.getCode());
		resp.setMessage(ResponseCodeEnum.ERROR.getMsg());
		return resp;
	}

	public static BaseResponse<?> createErrorResponse(String msg) {
		BaseResponse<?> resp = new BaseResponse<>();
		resp.setCode(ResponseCodeEnum.ERROR.getCode());
		resp.setMessage(msg);
		return resp;
	}
}

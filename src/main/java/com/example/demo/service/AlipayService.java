package com.example.demo.service;

import com.alipay.api.AlipayApiException;
import com.example.demo.pojo.font.AlipayBean;

import java.io.IOException;
import java.util.Map;

public interface AlipayService {

        String aliPay(AlipayBean alipayBean) throws AlipayApiException;

//        public String toAlipay(Map<String, String> sourceMap) throws IOException;

}

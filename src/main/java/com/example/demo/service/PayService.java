package com.example.demo.service;

import com.alipay.api.AlipayApiException;
import com.example.demo.pojo.font.AlipayBean;

public interface PayService {
    String aliPay(AlipayBean alipayBean) throws AlipayApiException;
}

package com.example.demo.service.impl;

import com.alipay.api.AlipayApiException;
import com.example.demo.pojo.font.AlipayBean;
import com.example.demo.pojo.utils.AlipayUtil;
import com.example.demo.service.PayService;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {
    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
        return null;
    }
}

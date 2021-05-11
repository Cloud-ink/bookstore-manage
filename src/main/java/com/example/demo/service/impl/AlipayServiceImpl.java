package com.example.demo.service.impl;

import com.alipay.api.AlipayApiException;
import com.example.demo.pojo.font.AlipayBean;
import com.example.demo.util.AlipayUtil;
import com.example.demo.service.AlipayService;
import org.springframework.stereotype.Service;

@Service(value = "alipayOrderService")
public class AlipayServiceImpl implements AlipayService {
//    @Override
//    public String toAlipay(Map<String, String> sourceMap) throws IOException {
//        AlipayClient alipayClient =
//                new DefaultAlipayClient(
//                        AlipayConfig.gatewayUrl,
//                        AlipayConfig.app_id,
//                        AlipayConfig.merchant_private_key,
//                        //AlipayConfig.FORMAT,
//                        AlipayConfig.charset,
//                        AlipayConfig.alipay_public_key,
//                        AlipayConfig.sign_type);
//        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
//        alipayRequest.setReturnUrl(AlipayConfig.return_url);
//        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
//        AlipayTradeWapPayModel alipayTradeWapPayModel = new AlipayTradeWapPayModel();
//        alipayTradeWapPayModel.setOutTradeNo(sourceMap.get("out_trade_no"));
//        alipayTradeWapPayModel.setProductCode("FAST_INSTANT_TRADE_PAY");
//        alipayTradeWapPayModel.setTotalAmount(sourceMap.get("total_amount"));
//        alipayTradeWapPayModel.setSubject("Alipay");
//        alipayTradeWapPayModel.setBody(sourceMap.get("body"));
//        alipayRequest.setBizModel(alipayTradeWapPayModel);
//        String form="";
//        try {
//            form = alipayClient.pageExecute(alipayRequest).getBody();
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }
//        return form;
//    }


    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
        return AlipayUtil.connect(alipayBean);
    }
}

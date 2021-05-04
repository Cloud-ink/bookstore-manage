package com.example.demo.pojo.utils;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.example.demo.config.PropertiesConfig;
import com.example.demo.pojo.font.AlipayBean;
//前台把订单信息传回后端，后端工具类对其进行加密封装，并返回一个加密封装好的字符串
public class AlipayUtil {
    public static String connect(AlipayBean alipayBean) throws AlipayApiException {
 //1、获得初始化的AlipayClient
AlipayClient alipayClient = new DefaultAlipayClient(
                PropertiesConfig.getKey("gatewayUrl"),//支付宝网关
                PropertiesConfig.getKey("app_id"),//appid
                PropertiesConfig.getKey("merchant_private_key"),//商户私钥
                "json",
                PropertiesConfig.getKey("charset"),//字符编码格式
                PropertiesConfig.getKey("alipay_public_key"),//支付宝公钥
                PropertiesConfig.getKey("sign_type")//签名方式
                 );
 //2、设置请求参数
   AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
   //页面跳转同步通知页面路径
    alipayRequest.setReturnUrl(PropertiesConfig.getKey("return_url"));
    // 服务器异步通知页面路径
    alipayRequest.setNotifyUrl(PropertiesConfig.getKey("notify_url"));
    //封装参数
    alipayRequest.setBizContent(JSON.toJSONString(alipayBean));

    //3、请求支付宝进行付款，并获取支付结果
    String result = alipayClient.pageExecute(alipayRequest).getBody();
        System.out.println("*********************\n返回结果为："+result);
        // 输出

        //自动跳转到支付宝支付页面


    //返回付款信息
    return result;
   }

//    public static String connect(String out_trade_no, String total_amount, String subject, String body) throws AlipayApiException {
//        //1、获得初始化的AlipayClient
//        AlipayClient alipayClient = new DefaultAlipayClient(
//                AlipayConfig.gatewayUrl,//支付宝网关
//                AlipayConfig.app_id,//appId
//                AlipayConfig.merchant_private_key,//商户私钥
//                "json",
//                AlipayConfig.charset,//字符编码格式
//                AlipayConfig.alipay_public_key,//支付宝公钥
//                AlipayConfig.sign_type//签名方式
//        );
//        //2、设置请求参数
//        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
//        //页面跳转同步通知页面路径
//        alipayRequest.setReturnUrl(AlipayConfig.return_url);
//        // 服务器异步通知页面路径
//        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
//        //封装参数
//        //alipayRequest.setBizContent(JSON.toJSONString(alipayBean));
//        //设置订单信息
//        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
//        model.setOutTradeNo(out_trade_no);//必填,商户订单号
//        model.setSubject(subject);//必填,订单名称
//        model.setTotalAmount(total_amount);//必填,付款金额
//        model.setBody(body);//选填,商品描述
//        model.setProductCode("FAST_INSTANT_TRADE_PAY");//必填,电脑支付
//        //封装订单信息
//        alipayRequest.setBizModel(model);
//
//        //3、请求支付宝进行付款，并获取支付结果
//        String result = alipayClient.pageExecute(alipayRequest).getBody();
//        //返回付款信息
//        return result;
//    }
}

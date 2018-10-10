package scau.zxck.pay;

/**
 * 常量
 *
 * @author Zero Lee
 * @date 2017-12-20
 * @time 12:34
 */
public class Const {

    /**
     * APPID
     */
    public static final String APPID = "wxad894cd8b91142c4";

    /**
     * 商户ID
     */
    public static final String MCH_ID = "1507987601";

    /**
     * API秘钥
     */
    public static final String KEY = "eBsmO3pUVzJqoabC03Q8PzORIPulMbCy";

    /**
     * 证书地址
     */
    public static final String CERT_PATH = "C://Users//Administrator//Desktop//keke//apiclient_cert.p12";

    /**
     * 统一下单回调地址
     */
    public static final String DO_UNIFIEDORDER_NOTIFY_URL= "http://www.fenglong319.cn/WXPayTradeReciver";
    //public static final String DO_UNIFIEDORDER_NOTIFY_URL= "http://120.77.240.247:8080/agencyOfFarmers/WXPayTradeReciver";

    /**
     * 交易类型，扫码支付固定为NATIVE
     */
    public static final String TRADE_TYPE= "NATIVE";

}



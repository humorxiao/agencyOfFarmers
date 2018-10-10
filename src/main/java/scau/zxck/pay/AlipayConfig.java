package scau.zxck.pay;
import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2018052560195448";

	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC0VPPTsytORqqfnNE1Dg6jc0q4OKhnsB/9pLqK+91Shkfuwv/dYZJM1IxQ2zcqTM+iC8SNkEbQQ5Le/urrqwT6Iw7kESasHXeudA7Ji9CUit/fKmXOKdO/1xtFv+V6b3HnX9cPF8YfBQIepxMolog2FMaFNfC/hGIqsS5t0udpyraUrvLQl5PDepmW/PPSzeiIRKvSn1K8MLnX5CTPIjMZpgEhaGFjRUvhK5Ou0+ydxWs6hFo/O94n2Hoq6vgXhqOT4ad3q0MIC6f6CkVasYHdvjKNmCnlZI1yKqLZZtc9BenV2OsyAXsLQPytow7CJTazFJQpMbB6NBjldRkUNIrtAgMBAAECggEACv4euunyGtxt7Drn+d74f3g4ttAlW+S9nn2bLXu6+xvaaLCUXk9jVrGiOA+U5kw2X/9tpN+FoY5s9pD9iw4qyIC4mKh+/gDx2b2TZS/TsCpjCTyRZibglJItRGw1NehzPhJ2jqH5Xsy0W1/95/x9IybTDy3BljsgrM+OL/2qQ6WQWmSAsdoY9nQVLXa5F8mfoD/7K2Ymiqw40BTaOvHtB8hGtCMeEU9HuzylRGdtqrU+q+kjaBiVUYQrSkZybUcg/MYsi8JrNTZCGF1qbtsLNYkB1p88BnB8iSICDjHAfUZvwVn1sEcawLuuLF8NPp90cMFSdLQqZa3G74zC12jcVQKBgQD18fcGdcCyAQcr4mDXLlpAMdJ8yjZMqGb2JX4zrF36d94UVBfSB34wP4g3T9B13JxPCBJU5X6Wea6hqMc6kp8RPPDxnD7FI73wpoIe6hHNBuGXOGAcpmWLyHZrzWcjwYr0kT0ct67Hhrvl8W51Y5VOkDFftkqEBkUGeR+6Ztci1wKBgQC7tEkfiDiuE2GS5qmpQ/cfmU6HPy2gErbD/3GmDAIPYhDRKDEVYv8cftGHwAzhDf+P2gbsN1Q55Y++2QyP+dCtlHJjU4MdN/Za8mrxTNYrm1FFz0lwp7v69XnTdBPHSAdbCnEw01f0L1Rk2HNLRs7D6uWP9BzdxcDESeC4DtmL2wKBgBD5SQ4mFE10qrUJo5tWNJ5XkepQ3lx+BtUYE1SnobjGiwomH5q8L6ZOzCzdEksAFb2SlglL6WejMD0x88YEFnxPE2XpXZ+nbtVr+HD/wIjA39La7i8sALgb9qhL/cFG+zsTxv7zYlmc39znw7LQU56fbvjnEM95y0u+EkWAEXrzAoGAVT0Eq9YPbzWL7MrkpuIfWfEjkRVmobd92bmpoW74jIXK/0BSMdUX5pfMjOnwr07NDEVFOYXSsMwHn/Iu2z7lvb2YyHRHKUCdliVZ3SQ4aesJWDtOrWLuYYOSBC1FQgz6kiRGzJZc7Z2EpJsKmFeaU6va/aksZqWD6V9nNg5ASlsCgYALQ03PyCIXHbfUTiXbSHaALeq1r0xrkekqirCasN5O5d8GybFjEStg8RHF0wTc9y9HJrmgCykZJn+LHuyJpohdIFJgSYKFjF94+dEC3SH88r2h/6hRaBe55vtbRsXA4TpnHh+9FY086I+QqGMB0imyT4Z2rTXzO18j+6bDSGfQDQ==";

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkKc3XiIk+FnV8Qmh3keHBarKvNxZIElbaw5dQR8Oazw5t5g/vxnfv4C3KezOEgxGwa2GbH1FAFYLNycWLODSEDmQYaDo8jqZ+Antu4OJ/vDpBRGCzk/TKhCgZbD2TwCVUpGzEaLHtuBAsQ2sCt9jz5XCW1bfS2rZVAYXEYGt3JQy3fgN3T++oRiWSYBypb51NNQc1WJ3M7k89Qx4RuuKTWnoeYzzbCu9Xle+r3NXaxHlPRcoz80uV1L2cMNZ2ywcjcB6jheQIqFGxMdg/dVPdQFjPe2SZCP2PGDmhbekwL1MUP819IZ6DwS7HYCZ+57+30QN44NBWv1IIcVN4aVl8wIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//public static String notify_url = "http://localhost:8080/agencyOfFarmers/jsp/notify_url.jsp";
	public static String notify_url = "http://www.fenglong319.cn/jsp/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//public static String return_url = "http://localhost:8080/agencyOfFarmers/jsp/return_url.jsp";
	public static String return_url = "http://www.fenglong319.cn/alipayreturn";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";

	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";

	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

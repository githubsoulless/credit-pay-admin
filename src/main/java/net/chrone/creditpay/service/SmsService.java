package net.chrone.creditpay.service;

public interface SmsService {
    /**
     * 短信发送公共方法
     * 
     * @param mobiles
     *            发送手机号(多个逗号分隔)
     * @param randname
     *            模板ID(可空)
     * @param randValMap
     *            模块参数Map(可空)
     * @param dateTime
     *            发送时间(建议填空)
     * @param content
     *            短信内容
     * @return 0成功，其余失败
     */
    public int sendSMS(String mobiles ,String dateTime, String content) ;

}

package net.chrone.creditpay.service;

import java.util.Map;

public interface RedisService {

	
	 /**
     * 按ID生成对应seq序列号,调用时将会返回自增后的序列号
     * 注:先从redis中根据id取值,若不存在,则查询数据库且设置redis的值
     * @param id
     * @return
     * @throws Exception 若从redis,或数据库取值或写入失败则会抛出异常,调用者根据异常自行生成seq序列号
     */
    long genNextSeqNo(String id) throws Exception;

}

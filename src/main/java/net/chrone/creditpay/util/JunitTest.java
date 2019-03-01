package net.chrone.creditpay.util;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.chrone.creditpay.model.MenuInf;
import net.chrone.creditpay.model.PayChannel;
import net.chrone.creditpay.model.RoleMenu;
import net.chrone.creditpay.service.MenuInfService;
import net.chrone.creditpay.service.PayChannelService;
import net.chrone.creditpay.service.RoleInfService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:applicationContext.xml",
        "classpath:applicationContext-mq.xml" })
public class JunitTest {
	@Autowired 
	private RoleInfService roleInfService;
	@Autowired
	private PayChannelService payChannelService;
	@Test
	public void test()throws Exception{
		
		
		List<PayChannel> payChannels = payChannelService.listAllPayChannel();
		for(int i=payChannels.size()-1;i>=0;i--) {//除去禁用的,不是4快捷 7扫码的通道
			if(payChannels.get(i).getStatus() !=1 || !(payChannels.get(i).getChnlType() ==4 || payChannels.get(i).getChnlType() ==7)) {
				payChannels.remove(i);
			}
		}
		
		for(PayChannel payChannel:payChannels) {
			System.out.println(payChannel);
		}
		
	}
}


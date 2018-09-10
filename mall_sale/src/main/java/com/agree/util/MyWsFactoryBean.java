package com.agree.util;

import java.util.HashMap;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.FactoryBean;

public class MyWsFactoryBean<T> implements FactoryBean<T> {
	private String url;
	private Class<T> t;

	public static <T> T getMyWs(String url, Class<T> t) {
		JaxWsProxyFactoryBean jwfb = new JaxWsProxyFactoryBean();
		jwfb.setAddress(url);
		jwfb.setServiceClass(t);
		T bean = (T) jwfb.create();

		return bean;
	}
	@Override
	public T getObject() throws Exception {
		// TODO Auto-generated method stub
		return getMyWs(url, this.t);
	}
	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return this.t;
	}
	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Class<T> getT() {
		return t;
	}

	public void setT(Class<T> t) {
		this.t = t;
	}
}

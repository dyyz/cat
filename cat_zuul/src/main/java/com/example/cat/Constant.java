package com.example.cat;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

public interface Constant {

	public static final String CORRELATION_ID = "cat-correlation-id";
    public static final String AUTH_TOKEN = "Authorization";
    public static final String USER_ID = "cat-user-id";
    public static final String ORG_ID = "cat-org-id";
    public static final String PRE_FILTER_TYPE = FilterConstants.PRE_TYPE;
    public static final String POST_FILTER_TYPE = FilterConstants.POST_TYPE;
    public static final String ROUTE_FILTER_TYPE = FilterConstants.ROUTE_TYPE;
    
}

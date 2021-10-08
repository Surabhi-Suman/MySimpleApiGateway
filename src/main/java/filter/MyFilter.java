package filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

public class MyFilter extends ZuulFilter {
Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        StringBuffer strLog = new StringBuffer();
        strLog.append("....Logging request.... ");
        strLog.append(String.format("Server: %s Method: %s Path: %s \n", context.getRequest().getServerName(), context.getRequest().getMethod(),
                context.getRequest().getRequestURI()));
        logger.info(strLog.toString());
        return null;
    }
}

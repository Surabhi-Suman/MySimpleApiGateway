package filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URL;

public class MyPreRewriteFilter extends ZuulFilter {
    Logger logger = LoggerFactory.getLogger(MyPreRewriteFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER+1;
    }

    @Override
    public boolean shouldFilter() {
       return RequestContext.getCurrentContext().getRequest()
                .getRequestURI().startsWith("/private");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        StringBuffer strLog = new StringBuffer();
        strLog.append("\n------ Filtering Access for private - PREREWRITE FILTER  ------\n");
        try{
            String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/").path("/myapi").path("/test").build().toUriString();
            String userId = ctx.getRequest().getHeader("userid") == null ? "" : ctx.getRequest().getHeader("userid");
            String password = ctx.getRequest().getHeader("password") == null ? "" : ctx.getRequest().getHeader("password");
            if (!userId.equals("")) {
                if (!userId.equals("su99su") || !password.equals("testpw")) {
                    String msgError = "Invalid UserId or Password";
                    strLog.append("\n" + msgError + "\n");
                    ctx.setResponseBody(msgError);
                    ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
                    ctx.setSendZuulResponse(false);
                    logger.info(strLog.toString());
                    return null;
                }
                ctx.setRouteHost(new URL(url));
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        logger.info(strLog.toString());
        return null;
    }
}

package contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value = "/myapi")
public class MyTestController {
Logger logger = LoggerFactory.getLogger(MyTestController.class);
    final static String EOL = "\n";
    @GetMapping(value = "/test")
    public String test(HttpServletRequest request)
    {
        StringBuffer strLog = new StringBuffer();
        strLog.append("................ Logging path values for api ......  " + EOL);
        strLog.append("Method: "+ request.getMethod()+EOL);
        strLog.append("URL: "+ request.getRequestURL()+EOL);
        strLog.append("Remote Host: "+request.getRemoteHost()+EOL);
        strLog.append("............the above values are logged...........");
        return strLog.toString();
    }
}

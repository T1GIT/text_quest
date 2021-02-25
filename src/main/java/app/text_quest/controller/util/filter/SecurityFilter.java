//package app.text_quest.controller.util.filter;
//
//import app.text_quest.util.LoggerFactory;
//import app.text_quest.util.enums.LogType;
//import org.apache.log4j.Logger;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Date;
//
//
//@Component
//@Order(2)
//public class SecurityFilter implements Filter {
//
//    private static final Logger requestLogger = LoggerFactory.getLogger(LogType.REQUEST);
//
//    @Override
//    public void doFilter(
//            ServletRequest request,
//            ServletResponse response,
//            FilterChain chain) throws IOException, ServletException {
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//
//        long startTime = new Date().getTime();
//
//        chain.doFilter(request, response);
//
//        requestLogger.info(String.format("%3d %-6s %-30s %4d ms   %s",
//                res.getStatus(),
//                req.getMethod(),
//                req.getRequestURI(),
//                new Date().getTime() - startTime,
//                res.getContentType()
//                )
//        );
//    }
//}

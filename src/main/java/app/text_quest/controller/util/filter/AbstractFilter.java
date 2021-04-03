package app.text_quest.controller.util.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;


/**
 * Realisation of the filter with possibility of including and excluding url using a
 * regular expression.
 * <p>
 * Base class for filters. Children must call parent constructor and
 * override {@link AbstractFilter#doAction(HttpServletRequest, HttpServletResponse, FilterChain)}
 * <p>
 * If url is included then it will execute {@link AbstractFilter#doFilter(ServletRequest, ServletResponse, FilterChain)}
 * otherwise just pass request to the next filter
 */
public abstract class AbstractFilter implements Filter {

    /**
     * A regexp pattern to process request via this filter
     */
    private final Pattern include;

    /**
     * A regexp pattern to pass request without processing
     */
    private Pattern exclude;

    /**
     * Class constructor specifies only including expression
     *
     * @param include regExp for including urls
     */
    protected AbstractFilter(String include) {
        this.include = Pattern.compile(include);
    }

    /**
     * Class constructor specifies including and excluding expressions
     *
     * @param include regExp for including urls
     * @param exclude regExp for excluding urls
     */
    protected AbstractFilter(String include, String exclude) {
        this(include);
        this.exclude = Pattern.compile(exclude);
    }

    /**
     * Checks if url is included in this filter, otherwise passes processing
     *
     * @param request  to processing
     * @param response response
     * @param chain    other filters
     * @throws IOException      if error with input output occurs
     * @throws ServletException if error when {@code chain.doFilter()} occurs
     */
    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String url = req.getRequestURI();
        if (include.matcher(url).matches() && (exclude == null || !exclude.matcher(url).matches())) {
            doAction(req, res, chain);
        } else {
            chain.doFilter(req, res);
        }
    }

    /**
     * Abstract method, executing only if request url matches included regexp.
     * In this method children classes must insert their work.
     *
     * @param req   request
     * @param res   response
     * @param chain other filters
     * @throws IOException      if problem with input output occurs
     * @throws ServletException if error when {@code chain.doFilter()} occurs
     */
    protected abstract void doAction(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException;
}

package com.cloud.common.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 1.domain表示的是cookie所在的域，默认为请求的地址，如网址为www.test.com/test/test.aspx，那么domain默认为www.test.com。
 *  而跨域访问，如域A为t1.test.com，域B为t2.test.com，那么在域A生产一个令域A和域B都能访问的cookie就要将该cookie的domain设置为.test.com；
 *  如果要在域A生产一个令域A不能访问而域B能访问的cookie就要将该cookie的domain设置为t2.test.com。
 *
 * 2.path表示cookie所在的目录，asp.net默认为/，就是根目录。
 *  在同一个服务器上有目录如下：/test/,/test/cd/,/test/dd/，现设一个cookie1的path为/test/，cookie2的path为/test/cd/，
 *  那么test下的所有页面都可以访问到cookie1，而/test/和/test/dd/的子页面不能访问cookie2。这是因为cookie能让其path路径下的页面访问。
 */
public class CookieUtil {

    /**
     * 设置cookie
     * @param response
     * @param name  cookie名字
     * @param value cookie值
     * @param path  cookie所在目录。一般可以设置为"/"
     * @param domain 所属域。如果不设置默认是当前域可用
     * @param maxAge 生命周期,以秒为单位
     */
    public static void addCookie(HttpServletResponse response,String name,String value,String path,String domain,Integer maxAge) throws Exception {
        if(Objects.isNull(name) || Objects.isNull(value)){
            throw new Exception("请设置正确的Cookie值");
        }
        try {
            value = URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Cookie cookie = new Cookie(name,value);
        if(StringUtils.isEmpty(path)){
            cookie.setPath("/");
        }else{
            cookie.setPath(path);
        }
        if(!StringUtils.isEmpty(domain)){
            cookie.setDomain(domain);
        }
        if(Objects.nonNull(maxAge) && maxAge>0){
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }

    /**
     * 获取Cookie,封装成Map
     * @param request
     * @return
     */
    public static Map<String,Cookie> getCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    /**
     * 获取Cookie
     * @param request
     * @param name
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request,String name){
        Map<String,Cookie> cookieMap = getCookieMap(request);
        if(cookieMap.containsKey(name)){
            Cookie cookie = cookieMap.get(name);
            return cookie;
        }else{
            return null;
        }
    }

    /**
     * 获取Cookie value
     * @param request
     * @param name
     * @return
     */
    public static String getCookieValue(HttpServletRequest request,String name){
        Cookie cookie = getCookie(request,name);
        if(null == cookie){
            return null;
        }
        try {
            return URLDecoder.decode(cookie.getValue(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除cookie
     * @param request
     * @param response
     * @param cookieName
     * @param path
     * @param domain
     * @return
     */
    public static boolean removeCookie(HttpServletRequest request,HttpServletResponse response,String cookieName,String path,String domain) {
        if (cookieName != null) {
            Cookie cookie = getCookie(request,cookieName);
            if(cookie!=null){
                cookie.setMaxAge(0);
                if(StringUtils.isEmpty(path)){
                    cookie.setPath("/");
                }else{
                    cookie.setPath(path);
                }
                if(StringUtils.isEmpty(domain)){
                    cookie.setDomain(request.getServerName());
                }else{
                    cookie.setDomain(domain);
                }
                response.addCookie(cookie);
                return true;
            }
        }
        return false;
    }
}


package tw.com.bruce.webscoket.security.error.handle;

import com.oracle.javafx.jmx.json.JSONException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tw.com.bruce.webscoket.common.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * @program: webscoket
 * @description: 認證錯誤進入點
 * @author: BruceHsu
 * @create: 2018-11-09 09:45
 */
@Slf4j
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7390984499666171537L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

//		 writer = response.getWriter();
        PrintWriter out = response.getWriter();
        try {
            ErrorHandleBean errorBean = new ErrorHandleBean();
            String jsonObject = JsonUtils.getJson(errorBean);
            out.print(jsonObject);
            out.flush();
        } catch (JSONException e) {
            log.error("entry point create error ", e);
        }

//
//		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

}

package RSR.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface RSRRequestProInter {
	public String requestPro(HttpServletRequest request, HttpServletResponse response)throws Throwable;
}

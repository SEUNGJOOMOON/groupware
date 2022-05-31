package HR.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public interface HRInterface{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable;
}

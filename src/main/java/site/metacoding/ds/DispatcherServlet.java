package site.metacoding.ds;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet{ // extends HttpServlet : 소켓 구현 완료

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet 호출됨");
		doProcess(req, resp);
	}
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("doPost 호출됨");
			doProcess(req, resp);
		}
	
	@Override
		protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("doDelete 호출됨");
			doProcess(req, resp);
		}
	
	@Override
		protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			System.out.println("doPut호출됨");
			doProcess(req, resp);
		}
	
	
	// 위에 따로따로 요청한것을 한 곳에서 호출하는 방법 (모든 요청이 다 모여있음)
	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doProcess 요청됨");
		String httpMethod = req.getMethod(); // ==> httpHeader 값에 들어옴
		System.out.println(httpMethod); // 메소드 호출해보기
		
		String identifier = req.getRequestURI(); // localhost:8000까지를 제외한 그 뒤의 주소를 파싱해줌
		System.out.println(identifier);
		
		// 스프링은 내가 어떤 컨트롤러를 만들지를 모름!
		// ds는 스프링이 만들고, uc는 내가 만든다
		// 공통 로직 처리 = ds에서 분기되기 전에 처리함
		// 이 코드에 문제가 하나 있는데, ds는 팔 수가 없음.. 라이브러리 오픈소스에도 안씀..
		UserController c = new UserController(); 
		if(identifier.equals("/ds/join")) { //주소를 파싱해서
			c.join(); //컨트롤러에 메서드를 때려줌 -> ds는 스프링에 모든 컨트롤러를 때림
		}else if(identifier.equals("/ds/login")) {
			c.login();
		}else {
			System.out.println("잘못된 요청입니다.");
		}
	}
	
	
	
	
	
	
	
	
	
	
}

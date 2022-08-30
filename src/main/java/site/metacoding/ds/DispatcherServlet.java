package site.metacoding.ds;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet{ // extends HttpServlet : 소켓 구현 완료

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("doGet 호출됨");
		doProcess(req, resp);
	}
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//System.out.println("doPost 호출됨");
			doProcess(req, resp);
		}
	
	@Override
		protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//System.out.println("doDelete 호출됨");
			doProcess(req, resp);
		}
	
	@Override
		protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//System.out.println("doPut호출됨");
			doProcess(req, resp);
		}
	
	
	// 위에 따로따로 요청한것을 한 곳에서 호출하는 방법 (모든 요청이 다 모여있음)
	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("doProcess 요청됨");
		String httpMethod = req.getMethod(); // ==> httpHeader 값에 들어옴
		//System.out.println(httpMethod); // 메소드 호출해보기
		
		String identifier = req.getRequestURI(); // localhost:8000까지를 제외한 그 뒤의 주소를 파싱해줌
		//System.out.println(identifier);
		
		// 스프링은 내가 어떤 컨트롤러를 만들지를 모름!
		// ds는 스프링이 만들고, uc는 내가 만든다
		// 공통 로직 처리 = ds에서 분기되기 전에 처리함
		// 이 코드에 문제가 하나 있는데, ds는 팔 수가 없음.. 라이브러리 오픈소스에도 안씀..
		UserController c = new UserController(); 
		
		Method[] methodList = c.getClass().getDeclaredMethods(); // 클래스에 정의된 메소드들을 다 반환
		for (Method method : methodList) { // for-each문: i값만큼 돌아감
			//System.out.println(method.getName());
			// UserController를 런타임시에 찾음 (=리플렉션)
			
			Annotation annotation = method.getDeclaredAnnotation(RequestMapping.class);
			RequestMapping requestMapping = (RequestMapping) annotation; // 다운캐스팅해서 씀
			
			try {
				System.out.println(requestMapping.value());
				if(identifier.equals(requestMapping.value())){
					method.invoke(c);	// 메서드 강제호출 = invoke(메모리에뜬 래퍼런스주소,method의 파라미터-지금은 파라미터가 없음)
				}
			} catch (Exception e) {
				System.out.println(method.getName()+"은 어노테이션이 없습니다");
			}
			
		} 
	}
	
	
	
	
	
	
	
	
	
	
}

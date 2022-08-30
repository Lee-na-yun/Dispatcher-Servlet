package site.metacoding.ds;

public class UserController {

	@RequestMapping("/join")
	public void join() {
		System.out.println("join 호출됨");
	}
	
	@RequestMapping("/login")
	public void login() {
		System.out.println("login 호출됨");
	}
	
	//@RequestMapping("/joinForm")
	public void joinForm() {
		System.out.println("joinForm 호출됨");
	}
}
// 1. 런타임시 분석하기 => ds가 @RequestMapping이걸보고 아래코드를 때림!
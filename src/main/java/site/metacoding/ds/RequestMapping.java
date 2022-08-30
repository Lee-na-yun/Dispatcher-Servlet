package site.metacoding.ds;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD}) // 메소드에만 위치 설정하기 , 배열로 타겟 여러개설정  => 메소드, 클래스, 필드별로 설정 가능함
@Retention(RetentionPolicy.RUNTIME) // 동작시간 설정: runtime(런타임시), source(컴파일시)
public @interface RequestMapping {

	String value(); // value 메서드는 고정 키값 = default로 다른이름으로 쓸 수 없음
	
}

package fr.leo.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import fr.leo.service.SecurityContext;

@Component
@Aspect
@EnableAspectJAutoProxy
public class AuthorizationAspect {
	
	@Around(value = "@annotation(securedByAspect)", argNames="pjp,securedByAspect")
	public Object secure(ProceedingJoinPoint pjp,
			SecuredByAspect securedByAspect	) throws Throwable {
		String[] roles= securedByAspect.roles();
		boolean authorized = false;
		for (String role : roles) {
			if (SecurityContext.hasRole(role)) {
				authorized = true;
				break;
			}
		}
		if (authorized == true ) {
			Object result = pjp.proceed();
			return result;
		}else {
			throw new RuntimeException("Vous n'etes pas autorisé");
		}
	}

}

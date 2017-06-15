package com.stackroute.swisit.log;

	
	import org.aspectj.lang.annotation.Aspect;
	import org.aspectj.lang.annotation.Before;
	import org.springframework.stereotype.Component;

	@Aspect
	@Component
	public class LoggingAspect {
	    
//	    @Before("allServiceMethods()")
//	    public void loggingAdvice() {
//	        System.out.println("Aspect inside the Logging Advice");
//	    }
	//    
//	    @Pointcut("within(com.stackroute.swisit.searchservice.SearchServiceInterface)")
//	    public void allServiceMethods(){        
//	    }

//	    @Before("execution(* getAll())")
//	    public void loggingAspect(){
//	        System.out.println("Inside advice");
//	    }
	    
	    @Before("execution(public String getTitle())")
	    public void onBean()
	    {
	        System.out.println("inside bean advice");
	    }

}

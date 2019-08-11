package themayoras.trackmywaterchange.aop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class RequestMappingLoggerAdvice {

    private final Logger logger = LoggerFactory.getLogger(RequestMappingLoggerAdvice.class);
    
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMapping() {}
    
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMapping() {}
    
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {}
    
    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void controller() {}
    
    @Before("controller() && (getMapping() || postMapping() || requestMapping())")
    public void log(JoinPoint jp) {
        
    }
}

package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.annotation;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.controller.RequestPage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
public class LogAuditTime {

    static Logger logger = LoggerFactory.getLogger(LogAuditTime.class);
    /// ANNOTATION POINTCUT////
    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTimeAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("/////// AROUND START  logExecutionTime annotation //////");
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        Object[] aArguments = joinPoint.getArgs();

        // Signature signature = joinPoint.getSignature();
        // logger.info(signature.getName());
        // logger.info(signature.getDeclaringType().toString());
        // logger.info(signature.getDeclaringTypeName());
        // logger.info(signature.toLongString());
        // logger.info(signature.toShortString());
        // logger.info(signature.getModifiers() + "");
        // logger.info(signature.toString());

        //,user, parámetros


        int numberArgument = 1;
        for(Object a : joinPoint.getArgs()){

            logger.info("Argument N° " + numberArgument + ": " + a.toString());
            numberArgument++;
        }



        logger.info("--------- " + joinPoint.getSignature() + " executed in " + executionTime + "ms");
        logger.info("--------- AROUND FINISH  logExecutionTime annotation -------");
        return proceed;
    }



}



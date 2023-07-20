package vn.com.msb.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.msb.aop.entity.Account;
import vn.com.msb.aop.entity.ActivityLog;
import vn.com.msb.aop.repository.AccountRepository;
import vn.com.msb.aop.repository.ActivityLogRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// Example store user activity
@Aspect
@Component
@Slf4j
public class ActivityAspect {

    private final AccountRepository accountRepository;

    private final ActivityLogRepository activityLogRepository;

    @Autowired
    public ActivityAspect(AccountRepository accountRepository,
                          ActivityLogRepository activityLogRepository) {
        this.accountRepository = accountRepository;
        this.activityLogRepository = activityLogRepository;
    }

    // Function định nghĩa PointCut
    // A pointcut is a predicate or expression that determines
    // which methods in a program should be intercepted by an aspect.
    @Pointcut(value = "execution(* vn.com.msb.aop.controller.*.*(..))")
    public void pointCut() {
    }

    // Running method
    @Around(value = "pointCut() && args(request)")
    public Object aroundAdvice(ProceedingJoinPoint pjp, Object request) throws Throwable {
        // Retrieve the old record from the database
        Account accountRequest = (Account) request;
        Optional<Account> optionalAccount = accountRepository.findById(accountRequest.getAccountId());
        if (optionalAccount.isPresent()) {
            Account oldRecord = optionalAccount.get();
            // Store the old record in mongodb
            ActivityLog activityLog = new ActivityLog();
            Map<String, Object> map = new HashMap<>();
            map.put("oldVersion", oldRecord);
            activityLog.setData(map);
            activityLog.setCreateDate(new Date());
            activityLogRepository.save(activityLog);
        }
        // Proceed with the API
        return pjp.proceed();
    }

}

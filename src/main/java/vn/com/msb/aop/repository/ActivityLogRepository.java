package vn.com.msb.aop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.com.msb.aop.entity.ActivityLog;

public interface ActivityLogRepository extends MongoRepository<ActivityLog, String> {
}

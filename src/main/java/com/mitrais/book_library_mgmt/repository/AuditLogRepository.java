package com.mitrais.book_library_mgmt.repository;

import com.mitrais.book_library_mgmt.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog,String> {
}

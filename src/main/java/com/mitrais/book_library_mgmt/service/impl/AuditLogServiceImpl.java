package com.mitrais.book_library_mgmt.service.impl;

import com.mitrais.book_library_mgmt.model.AuditLog;
import com.mitrais.book_library_mgmt.repository.AuditLogRepository;
import com.mitrais.book_library_mgmt.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("auditLogService")
public class AuditLogServiceImpl implements AuditLogService {

    @Autowired
    AuditLogRepository auditLogRepository;

    @Override
    public void insert(AuditLog auditLog) {
        auditLogRepository.save(auditLog);
    }
}

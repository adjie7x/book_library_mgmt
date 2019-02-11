package com.mitrais.book_library_mgmt.service;

import com.mitrais.book_library_mgmt.model.AuditLog;

public interface AuditLogService {

    public void insert(AuditLog auditLog);
}

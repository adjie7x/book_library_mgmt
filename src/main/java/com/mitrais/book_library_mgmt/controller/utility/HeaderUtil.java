package com.mitrais.book_library_mgmt.controller.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

/**
 * Utility class for HTTP headers creation.
 */
public final class HeaderUtil {

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    private HeaderUtil() {
    }

    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-fidusiaApp-alert", message);
        headers.add("X-fidusiaApp-params", param);
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        return createAlert("Data " + entityName + " baru sukses ditambahkan untuk id " + param, param);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
        return createAlert("Data " + entityName + " sukses diupdate untuk id " + param, param);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
        return createAlert("Data " + entityName + " sukses dihapus untuk id " + param, param);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        log.error("Entity creation failed, {}", defaultMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-LibMgmtApp-error", defaultMessage);
        headers.add("X-LibMgmtApp-params", entityName);
        return headers;
    }

    public static HttpHeaders createSuratPengantarSuccess(String nomorSurat) {
        return createAlert("Sukses Generate Surat Pengantar No. "+nomorSurat, null);
    }
}

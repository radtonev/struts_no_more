package com.nuvei.cashier.ai.util;

import java.nio.file.Path;

public class FileUtil {

    private FileUtil() {
        // Utility class, no instantiation allowed
    }

    public static Path normalizeAdminPath(Path originalPath) {
        String[] variants = {"Cashier Admin", "CashierAdmin", "Cashier%20Admin"};
        String originalPathStr = originalPath.toString();
        for (String variant : variants) {
            Path candidate = Path.of(originalPathStr.replace("Cashier Admin", variant));
            if (candidate.toFile().exists()) {
                return candidate;
            }
        }

        return originalPath;
    }
}

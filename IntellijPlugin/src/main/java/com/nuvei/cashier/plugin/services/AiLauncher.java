package com.nuvei.cashier.plugin.services;

import com.intellij.openapi.diagnostic.Logger;
import com.nuvei.cashier.code.ClassRole;
import com.nuvei.cashier.code.HandlerContext;
import com.nuvei.cashier.plugin.actions.AddPppAdminPropertyDialog;

import java.nio.file.Paths;

public class AiLauncher {
    private static final Logger LOG = Logger.getInstance(AiLauncher.class);

    public void launch(AddPppAdminPropertyDialog dialog, String filePath, String pppAdminDirectory) {
        HandlerContext context = new HandlerContext(Paths.get(filePath),
                ClassRole.ENTITY, dialog.getPropertyName(), dialog.getType(),
                dialog.getFieldSize(), dialog.getHint(), dialog.getDefaultValue(), dialog.isCached(),
                dialog.isNullable(), dialog.getStoryNumber(), pppAdminDirectory);
        LOG.info("Context created: " + context);

        tempSimulation();
    }

    private void tempSimulation() {
        try {
            for (int i = 10; i > 0; i--) {
                Thread.sleep(2000);
                LOG.info("Simulating AI processing... " + i + " seconds remaining");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

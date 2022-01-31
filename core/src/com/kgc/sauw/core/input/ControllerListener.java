package com.kgc.sauw.core.input;

import com.badlogic.gdx.controllers.Controller;

import static com.kgc.sauw.game.gui.GameInterfaces.*;

public class ControllerListener implements com.badlogic.gdx.controllers.ControllerListener {

    @Override
    public void connected(Controller controller) {

    }

    @Override
    public void disconnected(Controller controller) {

    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        if (!com.kgc.sauw.core.gui.Interfaces.isAnyInterfaceOpen()) {
            if (buttonCode == controller.getMapping().buttonY) {
                if (!INVENTORY_INTERFACE.isOpen) INVENTORY_INTERFACE.open();
            }
            if (buttonCode == controller.getMapping().buttonStart) {
                if (!PAUSE_INTERFACE.isOpen) PAUSE_INTERFACE.open();
            }
        } else {
            if (buttonCode == controller.getMapping().buttonB) {
                com.kgc.sauw.core.gui.Interfaces.closeInterface();
            }
        }
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        return false;
    }
}

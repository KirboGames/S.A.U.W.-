package com.kgc.sauw.gui.interfaces;

import com.kgc.sauw.gui.HUD;
import com.kgc.sauw.gui.Interface;
import com.kgc.sauw.gui.interfaces.blockInterfaces.ChestInterface;
import com.kgc.sauw.gui.interfaces.blockInterfaces.FurnaceInterface;

import java.util.ArrayList;

public final class Interfaces {
    public static final HUD GAME_INTERFACE;
    /**
     * S.A.U.W. Interfaces
     **/
    public static final CraftingInterface CRAFTING_INTERFACE;
    public static final ConsoleInterface CONSOLE_INTERFACE;
    public static final PauseInterface PAUSE_INTERFACE;
    public static final DeadInterface DEAD_INTERFACE;
    public static final InventoryInterface INVENTORY_INTERFACE;
    public static final TestInterface TEST_INTERFACE;
    /**
     * BLOCK Interfaces
     **/
    public static final ChestInterface CHEST_INTERFACE;
    public static final FurnaceInterface FURNACE_INTERFACE;
    public static final ToolWallInterface TOOL_WALL_INTERFACE;

    public static final ArrayList<Interface> INTERFACES;

    static {
        GAME_INTERFACE = new HUD();

        CONSOLE_INTERFACE = new ConsoleInterface();
        CRAFTING_INTERFACE = new CraftingInterface();
        PAUSE_INTERFACE = new PauseInterface();
        DEAD_INTERFACE = new DeadInterface();
        INVENTORY_INTERFACE = new InventoryInterface();
        TEST_INTERFACE = new TestInterface();

        CHEST_INTERFACE = new ChestInterface();
        FURNACE_INTERFACE = new FurnaceInterface();
        TOOL_WALL_INTERFACE = new ToolWallInterface();

        INTERFACES = new ArrayList<>();

        addInterface(CONSOLE_INTERFACE);
        addInterface(CRAFTING_INTERFACE);
        addInterface(PAUSE_INTERFACE);
        addInterface(DEAD_INTERFACE);
        addInterface(INVENTORY_INTERFACE);
        addInterface(TEST_INTERFACE);

        addInterface(CHEST_INTERFACE);
        addInterface(FURNACE_INTERFACE);
        addInterface(TOOL_WALL_INTERFACE);
    }


    public static void addInterface(Interface Interface) {
        INTERFACES.add(Interface);
    }

    public static void updateInterfaces() {
        for (Interface i : INTERFACES) i.update(true);
    }

    public static void renderInterfaces() {
        for (Interface i : INTERFACES) i.render();
    }

    public static boolean isAnyInterfaceOpen() {
        for (Interface i : INTERFACES) {
            if (i.isOpen) return true;
        }
        return false;
    }

    public static void closeInterface() {
        for (Interface i : INTERFACES) {
            if (i.isOpen) i.close();
        }
    }
}
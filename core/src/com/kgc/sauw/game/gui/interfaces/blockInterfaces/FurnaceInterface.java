package com.kgc.sauw.game.gui.interfaces.blockInterfaces;

import com.badlogic.gdx.Gdx;
import com.kgc.sauw.core.gui.Interface;
import com.kgc.sauw.core.gui.elements.Image;
import com.kgc.sauw.core.gui.elements.Slot;
import com.kgc.sauw.core.resource.Resource;

public class FurnaceInterface extends Interface {


    public FurnaceInterface() {
        super("FURNACE_INTERFACE");
        createFromXml(Gdx.files.internal("xml/FurnaceInterface.xml"));

        Slot resultSlot = (Slot) getElement("ResultSlot");
        resultSlot.setSF(new Slot.SlotFunctions() {
            @Override
            public boolean isValid(int id, int count, int data, String FromSlotWithId) {
                return false;
            }

            @Override
            public void onClick() {

            }

            @Override
            public boolean possibleToDrag() {
                return true;
            }
        });

        ((Image) getElement("ArrowIcon")).setImg(Resource.getTexture("Interface/button_right_0.png"));

        updateElementsList();
    }
}
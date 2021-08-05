package com.kgc.sauw.game.blocks;

import com.kgc.sauw.core.block.Block;
import com.kgc.sauw.core.resource.Resource;
import com.kgc.sauw.core.utils.ID;
import com.kgc.sauw.game.items.InstrumentItem;

public class Tree extends Block {
    public Tree() {
        super(ID.registeredId("block:tree", 6), Resource.getTexture("Blocks/tree.png"));

        blockConfiguration.setSize(1, 2);
        blockConfiguration.setMaxDamage(5);
        blockConfiguration.setTransparent(true);
        blockConfiguration.setDrop(new int[][]{{ID.get("item:log"), 3}, {ID.get("item:sapling"), 1}, {ID.get("item:stick"), 6}});
        blockConfiguration.setCollisionsRectangleByPixels(11, 0, 10, 10, 32);
        blockConfiguration.setInstrumentType(InstrumentItem.Type.AXE);
    }
}

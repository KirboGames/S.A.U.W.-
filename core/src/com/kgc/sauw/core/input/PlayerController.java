package com.kgc.sauw.core.input;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.kgc.sauw.core.block.Blocks;
import com.kgc.sauw.game.environment.Environment;
import com.kgc.sauw.core.gui.elements.Button;
import com.kgc.sauw.game.gui.Interfaces;
import com.kgc.sauw.core.world.Tile;

import static com.kgc.sauw.core.entity.EntityManager.PLAYER;
import static com.kgc.sauw.core.environment.Environment.getWorld;
import static com.kgc.sauw.game.gui.Interfaces.HUD;

public class PlayerController implements InputProcessor {
    public PlayerController() {
        HUD.interactionButton.addEventListener(new Button.EventListener() {
            @Override
            public void onClick() {
                openBlockInterface();
            }
        });
    }

    public static void openBlockInterface() {
        Tile tile = null;
        int playerX = PLAYER.getCurrentTileX();
        int playerY = PLAYER.getCurrentTileY();
        switch (PLAYER.rotation) {
            case 0:
                tile = getWorld().map.getTile(playerX, playerY + 1, 0);
                break;
            case 1:
                tile = getWorld().map.getTile(playerX + 1, playerY, 0);
                break;
            case 2:
                tile = getWorld().map.getTile(playerX, playerY - 1, 0);
                break;
            case 3:
                tile = getWorld().map.getTile(playerX - 1, playerY, 0);
                break;
        }

        if (tile != null) {
            Blocks.getBlockById(tile.id).onInteractionButtonPressed(tile);
            if (tile.interface_ != null)
                if (!tile.interface_.isOpen)
                    tile.interface_.open(tile.x, tile.y, tile.z);
        }
    }

    public static void update() {
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            PLAYER.setVelocityX((float) HUD.joystick.normD(3).x);
            PLAYER.setVelocityY((float) HUD.joystick.normD(3).y);
        }
        if (Controllers.getCurrent() != null) {
            Controller controller = Controllers.getCurrent();
            float velX, velY;
            velX = controller.getAxis(controller.getMapping().axisLeftX);
            velY = -controller.getAxis(controller.getMapping().axisLeftY);
            if (velX > 0.2f || velX < -0.2f)
                PLAYER.setVelocityX(velX);

            if (velY > 0.2f || velY < -0.2f)
                PLAYER.setVelocityY(velY);
        }
        if (!Interfaces.isAnyInterfaceOpen()) {
            if (Gdx.input.isKeyPressed(Keys.W)) {
                PLAYER.setVelocityY(1);
            }
            if (Gdx.input.isKeyPressed(Keys.S)) {
                PLAYER.setVelocityY(-1);
            }
            if (Gdx.input.isKeyPressed(Keys.A)) {
                PLAYER.setVelocityX(-1);
            }
            if (Gdx.input.isKeyPressed(Keys.D)) {
                PLAYER.setVelocityX(1);
            }
            if (Gdx.input.isKeyPressed(Keys.E)) {
                openBlockInterface();
            }
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}

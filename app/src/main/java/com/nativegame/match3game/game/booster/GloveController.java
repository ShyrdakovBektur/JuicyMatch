package com.nativegame.match3game.game.booster;

import com.nativegame.match3game.algorithm.Match3Algorithm;
import com.nativegame.match3game.asset.Sounds;
import com.nativegame.match3game.asset.Textures;
import com.nativegame.match3game.game.GameEvent;
import com.nativegame.match3game.game.JuicyMatch;
import com.nativegame.match3game.game.effect.booster.GloveEffect;
import com.nativegame.match3game.game.layer.tile.Tile;
import com.nativegame.match3game.game.layer.tile.TileSystem;
import com.nativegame.match3game.game.swap.SwapModifier;
import com.nativegame.nattyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class GloveController extends BoosterController implements SwapModifier.SwapListener {

    private final SwapModifier mSwapModifier;
    private final GloveEffect mGloveEffect;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public GloveController(Engine engine, TileSystem tileSystem) {
        super(engine, tileSystem);
        mSwapModifier = new SwapModifier(engine);
        mSwapModifier.setListener(this);
        mGloveEffect = new GloveEffect(engine, Textures.GLOVE);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected boolean isAddBooster(Tile touchDownTile, Tile touchUpTile) {
        return touchUpTile != null && touchDownTile.isSwappable() && touchUpTile.isSwappable();
    }

    @Override
    protected void onAddBooster(Tile[][] tiles, Tile touchDownTile, Tile touchUpTile, int row, int col) {
        mGloveEffect.activate(JuicyMatch.WORLD_WIDTH / 2f, JuicyMatch.WORLD_HEIGHT / 2f,
                touchDownTile.getX(), touchDownTile.getY(), touchUpTile.getX(), touchUpTile.getY());
        Sounds.TILE_SLIDE.play();
    }

    @Override
    protected void onRemoveBooster(Tile[][] tiles, Tile touchDownTile, Tile touchUpTile, int row, int col) {
        Match3Algorithm.swapTile(tiles, touchDownTile, touchUpTile);
        mSwapModifier.activate(touchDownTile, touchUpTile);
    }

    @Override
    public void onSwap(Tile tileA, Tile tileB) {
        dispatchEvent(GameEvent.PLAYER_USE_BOOSTER);
    }
    //========================================================

}

package com.nativegame.match3game.game.layer.tile.type;

import com.nativegame.match3game.asset.Sounds;
import com.nativegame.match3game.asset.Textures;
import com.nativegame.match3game.game.effect.SmokeEffect;
import com.nativegame.match3game.game.effect.piece.ExplosionPieceEffectSystem;
import com.nativegame.match3game.game.layer.tile.TileSystem;
import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class CakeTile extends LayerObstacleTile {

    private static final int CAKE_PIECE = 4;

    private final ExplosionPieceEffectSystem mCakePieceEffect;
    private final SmokeEffect mSmokeEffect;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public CakeTile(TileSystem tileSystem, Engine engine, Texture texture, int obstacleLayer) {
        super(tileSystem, engine, texture, obstacleLayer);
        mCakePieceEffect = new ExplosionPieceEffectSystem(engine, Textures.CAKE_PIECE, CAKE_PIECE);
        mSmokeEffect = new SmokeEffect(engine, Textures.SMOKE_ANIMATION);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public boolean isSwappable() {
        if (mIsObstacle) {
            return false;
        }
        return super.isSwappable();
    }

    @Override
    protected void onUpdateLayer(int obstacleLayer) {
        // Update next layer texture
        switch (obstacleLayer) {
            case 0:
                playCakeEffect();
                break;
            case 1:
                setTexture(Textures.CAKE_01);
                playLayerEffect();
                break;
            case 2:
                setTexture(Textures.CAKE_02);
                playLayerEffect();
                break;
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void playCakeEffect() {
        mCakePieceEffect.activate(getCenterX(), getCenterY(), CAKE_PIECE);
        Sounds.CAKE_EXPLODE.play();
    }

    private void playLayerEffect() {
        mSmokeEffect.activate(getCenterX(), getCenterY());
    }
    //========================================================

}

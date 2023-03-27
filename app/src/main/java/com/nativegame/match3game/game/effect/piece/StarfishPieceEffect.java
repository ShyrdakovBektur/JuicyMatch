package com.nativegame.match3game.game.effect.piece;

import com.nativegame.match3game.game.layer.Layer;
import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.entity.sprite.Sprite;
import com.nativegame.nattyengine.entity.sprite.modifier.PositionYModifier;
import com.nativegame.nattyengine.entity.sprite.modifier.ScaleOutModifier;
import com.nativegame.nattyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class StarfishPieceEffect extends Sprite {

    private static final long TIME_TO_LIVE = 500;

    private final ScaleOutModifier mScaleOutModifier;
    private final PositionYModifier mPositionModifier;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public StarfishPieceEffect(Engine engine, Texture texture) {
        super(engine, texture);
        mScaleOutModifier = new ScaleOutModifier(TIME_TO_LIVE);
        mPositionModifier = new PositionYModifier(TIME_TO_LIVE);
        mPositionModifier.setAutoRemove(true);
        setLayer(Layer.TEXT_LAYER);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onUpdate(long elapsedMillis) {
        mScaleOutModifier.update(this, elapsedMillis);
        mPositionModifier.update(this, elapsedMillis);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(float x, float y) {
        setCenterX(x);
        setCenterY(y);
        mScaleOutModifier.init(this);
        mPositionModifier.setValue(mY, mY + 300);
        mPositionModifier.init(this);
        addToGame();
    }
    //========================================================

}

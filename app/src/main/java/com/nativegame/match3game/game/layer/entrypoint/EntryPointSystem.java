package com.nativegame.match3game.game.layer.entrypoint;

import com.nativegame.match3game.game.layer.LayerSpriteSystem;
import com.nativegame.match3game.level.Level;
import com.nativegame.nattyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class EntryPointSystem extends LayerSpriteSystem<EntryPoint> {

    private final EntryPoint[][] mEntryPoints;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public EntryPointSystem(Engine engine) {
        super(engine);
        mEntryPoints = new EntryPoint[mTotalRow][mTotalCol];
        init(Level.LEVEL_DATA.getEntry().toCharArray());
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public EntryPoint[][] getChild() {
        return mEntryPoints;
    }

    @Override
    public EntryPoint getChildAt(int row, int col) {
        return mEntryPoints[row][col];
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void init(char[] chars) {
        for (int i = 0; i < mTotalRow; i++) {
            for (int j = 0; j < mTotalCol; j++) {
                char c = chars[i * mTotalCol + j];
                if (c == 'e') {
                    // We skip the empty type
                    continue;
                }
                EntryPointType type = EntryPointInitializer.getType(c);
                EntryPoint entry = new EntryPoint(mEngine, type.getTexture(), type);
                entry.setPosition(i, j);
                entry.addToGame();
                mEntryPoints[i][j] = entry;
            }
        }
    }
    //========================================================

}

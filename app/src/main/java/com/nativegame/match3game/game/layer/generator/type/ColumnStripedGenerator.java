package com.nativegame.match3game.game.layer.generator.type;

import com.nativegame.match3game.game.layer.generator.Generator;
import com.nativegame.match3game.game.layer.tile.SpecialType;
import com.nativegame.match3game.game.layer.tile.Tile;
import com.nativegame.match3game.game.layer.tile.TileResetter;
import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class ColumnStripedGenerator extends Generator {

    private static int mCount;

    private final ColumnStripedGeneratorResetter mResetter = new ColumnStripedGeneratorResetter();

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public ColumnStripedGenerator(Engine engine, Texture texture) {
        super(engine, texture);
        mCount = 9;   // Generate at first match
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public TileResetter getResetter() {
        return mResetter;
    }
    //========================================================

    //--------------------------------------------------------
    // Inner Classes
    //--------------------------------------------------------
    private class ColumnStripedGeneratorResetter implements TileResetter {

        private static final int MAX_COUNT = 12;

        //--------------------------------------------------------
        // Overriding methods
        //--------------------------------------------------------
        @Override
        public void resetTile(Tile tile) {
            // Check is generator and tile at the same column
            if (tile.getColumn() == mCol) {
                // Update and check the accumulated count
                mCount++;
                if (mCount >= MAX_COUNT) {
                    tile.setSpecialType(SpecialType.COLUMN_STRIPED);
                    playGeneratorEffect();
                    mCount = 0;
                }
            }
        }
        //========================================================

    }
    //========================================================

}

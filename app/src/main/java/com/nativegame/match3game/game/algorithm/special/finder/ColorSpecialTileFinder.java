package com.nativegame.match3game.game.algorithm.special.finder;

import com.nativegame.match3game.algorithm.TileState;
import com.nativegame.match3game.algorithm.TileType;
import com.nativegame.match3game.asset.Sounds;
import com.nativegame.match3game.game.effect.flash.TransformFlashEffectSystem;
import com.nativegame.match3game.game.layer.tile.FruitType;
import com.nativegame.match3game.game.layer.tile.SpecialType;
import com.nativegame.match3game.game.layer.tile.Tile;
import com.nativegame.nattyengine.engine.Engine;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class ColorSpecialTileFinder extends QuadSpecialTileFinder {

    private final TransformFlashEffectSystem mTransformFlashEffectSystem;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public ColorSpecialTileFinder(Engine engine) {
        super(engine);
        mTransformFlashEffectSystem = new TransformFlashEffectSystem(engine, MAX_FIND_NUM);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void findSpecialTile(Tile[][] tiles, int row, int col) {
        // Find color special tile in row
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col - 4; j++) {
                // We skip unmatchable tile
                if (!tiles[i][j].isMatchable() || tiles[i][j].getTileState() != TileState.MATCH) {
                    continue;
                }

                // Check is tile type match the next four column
                TileType type = tiles[i][j].getTileType();
                if (type == tiles[i][j + 1].getTileType()
                        && type == tiles[i][j + 2].getTileType()
                        && type == tiles[i][j + 3].getTileType()
                        && type == tiles[i][j + 4].getTileType()) {
                    upgradeRow(tiles, i, j + 2);
                }
            }
        }

        // Find color special tile in column
        for (int j = 0; j < col; j++) {
            for (int i = 0; i < row - 4; i++) {
                // We skip unmatchable tile
                if (!tiles[i][j].isMatchable() || tiles[i][j].getTileState() != TileState.MATCH) {
                    continue;
                }

                // Check is tile type match the next four row
                TileType type = tiles[i][j].getTileType();
                if (type == tiles[i + 1][j].getTileType()
                        && type == tiles[i + 2][j].getTileType()
                        && type == tiles[i + 3][j].getTileType()
                        && type == tiles[i + 4][j].getTileType()) {
                    upgradeColumn(tiles, i + 2, j);
                }
            }
        }
    }

    @Override
    protected void playUpgradeEffect(Tile tile) {
        // Add transform effect
        super.playUpgradeEffect(tile);
        mTransformFlashEffectSystem.activate(tile.getCenterX(), tile.getCenterY());
        Sounds.COLOR_SPECIAL_TILE_UPGRADE.play();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void upgradeRow(Tile[][] tiles, int row, int col) {
        // Row shape:
        // 0 0 X 0 0

        // Set target tile special type
        Tile targetTile = tiles[row][col];
        targetTile.setSpecialType(SpecialType.COLOR_SPECIAL_TILE);

        // Init position factors
        setPositionXFactors(-2, -1, 1, 2);
        setPositionYFactors(0, 0, 0, 0);
        // Set upgrade to others
        setUpgradeTiles(tiles, row, col);

        playUpgradeEffect(targetTile);
        // Important to set it to None, or it may detected as original type
        targetTile.setTileType(FruitType.NONE);
    }

    private void upgradeColumn(Tile[][] tiles, int row, int col) {
        // Column shape:
        // 0
        // 0
        // X
        // 0
        // 0

        // Set target tile special type
        Tile targetTile = tiles[row][col];
        targetTile.setSpecialType(SpecialType.COLOR_SPECIAL_TILE);

        // Init position factors
        setPositionXFactors(0, 0, 0, 0);
        setPositionYFactors(-2, -1, 1, 2);
        // Set upgrade to others
        setUpgradeTiles(tiles, row, col);

        playUpgradeEffect(targetTile);
        // Important to set it to None, or it may detected as original type
        targetTile.setTileType(FruitType.NONE);
    }
    //========================================================

}

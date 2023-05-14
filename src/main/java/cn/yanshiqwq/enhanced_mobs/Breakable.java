package cn.yanshiqwq.enhanced_mobs;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public class Breakable {
    private final boolean isBlock;
    private final Material blockType;
    private final Tag<?> tag;

    public Breakable(Material block) {
        isBlock = true;
        blockType = block;
        tag = null;
    }

    public Breakable(Tag<?> tag) {
        isBlock = false;
        blockType = null;
        this.tag = tag;
    }

    public boolean isBreakable(@NotNull Block block, @NotNull Tag<?> tag) {
        return this.isBlock ?
                block.getType() == blockType :
                tag.equals(this.tag);
    }
}

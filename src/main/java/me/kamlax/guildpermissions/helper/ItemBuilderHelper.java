package me.kamlax.guildpermissions.helper;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilderHelper extends ItemStack {
    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    public ItemBuilderHelper(final Material material, final int amount) {
        this.itemStack = new ItemStack(material, amount);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilderHelper setName(final String name) {
        this.itemMeta.setDisplayName(ChatHelper.fixText(name));
        return this;
    }

    public ItemBuilderHelper addLore(final List<String> lore) {
        this.itemMeta.setLore(ChatHelper.fixText(lore));
        this.itemStack.setItemMeta(this.itemMeta);
        return this;
    }

    public ItemStack build() {
        return this.itemStack;
    }
}

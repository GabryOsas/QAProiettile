package me.gabryosas.utils;

import me.gabryosas.Main;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class General {
    public static ItemStack createItemStack(Material material, String name, int custom, List<String> lore){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setCustomModelData(custom);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack createItemStack(Material material, String name, int custom, List<String> lore, int quantity){
        ItemStack itemStack = new ItemStack(material);
        itemStack.setAmount(quantity);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setCustomModelData(custom);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}

package com.samleighton.trackandtriumph.entities.projectiles;

import com.samleighton.trackandtriumph.entities.TTEntities;
import com.samleighton.trackandtriumph.items.TTItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;

public class Bullet extends AbstractArrow {
    public Bullet(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public Bullet(Level level, double x, double y, double z, ItemStack pickupItemStack, ItemStack firedFromWeapon) {
        super(TTEntities.BULLET.get(), x, y, z, level, pickupItemStack, firedFromWeapon);
    }

    public Bullet(Level level, LivingEntity owner, ItemStack pickupItemStack, ItemStack firedFromWeapon) {
        super(TTEntities.BULLET.get(), owner, level, pickupItemStack, firedFromWeapon);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(TTItems.BULLET.get());
    }
}

package com.samleighton.trackandtriumph.items.weapons;

import com.samleighton.trackandtriumph.items.TTItems;
import com.samleighton.trackandtriumph.items.weapons.projectiles.BulletItem;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class AssaultRifleItem extends ProjectileWeaponItem {
    public static final int MAX_DRAW_DURATION = 20;
    public static final int DEFAULT_RANGE = 25;

    public static final Predicate<ItemStack> _762_ONLY = itemStack -> itemStack.is(TTItems.BULLET.get());

    public AssaultRifleItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return _762_ONLY;
    }

    @Override
    public int getDefaultProjectileRange() {
        return DEFAULT_RANGE;
    }

    @Override
    protected Projectile createProjectile(Level level, LivingEntity shooter, ItemStack weapon, ItemStack ammo, boolean isCrit){
        BulletItem bulletItem = ammo.getItem() instanceof BulletItem bulletItem1 ? bulletItem1 : (BulletItem) TTItems.BULLET.get();
        return bulletItem.createArrow(level, ammo, shooter, weapon);
    }



    @Override
    protected void shootProjectile(@NotNull LivingEntity shooter, Projectile projectile, int index, float velocity, float inaccuracy, float angle, @Nullable LivingEntity target) {
        projectile.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot() + inaccuracy, 0.0F, velocity, angle);
    }

    @Override
    public boolean releaseUsing(ItemStack weapon, Level level, LivingEntity livingEntity, int timeLeft) {
        if (!(livingEntity instanceof Player player)) {
            return false;
        }

        ItemStack projectiles = player.getProjectile(weapon);
        if (projectiles.isEmpty()) {
            return false;
        } else {
            int i = this.getUseDuration(weapon, livingEntity) - timeLeft;
            i = net.neoforged.neoforge.event.EventHooks.onArrowLoose(weapon, level, player, i, !projectiles.isEmpty());
            if (i < 0) return false;
            List<ItemStack> list = draw(weapon, projectiles, player);
            if (level instanceof ServerLevel serverlevel && !list.isEmpty()) {
                this.shoot(serverlevel, player, player.getUsedItemHand(), weapon, list, 3.0F, 1.0F, false, null);
            }

            level.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.FIREWORK_ROCKET_BLAST,
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + 0.5F
            );
            player.awardStat(Stats.ITEM_USED.get(this));
            return true;
        }
    }

    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
        return 7200;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack itemStack) {
        return ItemUseAnimation.BOW;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemstack = player.getItemInHand(interactionHand);
        boolean flag = !player.getProjectile(itemstack).isEmpty();

        InteractionResult ret = net.neoforged.neoforge.event.EventHooks.onArrowNock(itemstack, level, player, interactionHand, flag);
        if (ret != null) return ret;

        if (!player.hasInfiniteMaterials() && !flag) {
            return InteractionResult.FAIL;
        } else {
            player.startUsingItem(interactionHand);
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public ItemStack getDefaultCreativeAmmo(@javax.annotation.Nullable Player player, ItemStack projectileWeaponItem) {
        return TTItems.BULLET.get().getDefaultInstance();
    }

}

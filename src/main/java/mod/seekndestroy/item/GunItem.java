package mod.seekndestroy.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class GunItem extends Item
{
    private final int maxAmmo;
    private final float damage;
    private final float cooldown;
    private final boolean requiresTwoHands;
    private int currentAmmo;

    public GunItem(Settings settings, int maxAmmo, float damage, float cooldown, boolean requiresTwoHands)
    {
        super(settings);
        this.maxAmmo = maxAmmo;
        this.damage = damage;
        this.cooldown = cooldown;
        this.requiresTwoHands = requiresTwoHands;
        this.currentAmmo = maxAmmo;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, Item.TooltipContext context, List<Text> tooltip, TooltipType options)
    {
        tooltip.add(Text.translatable("Ammo: " + currentAmmo + "/" + maxAmmo));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        if (world.isClient())
        {
            return TypedActionResult.success(user.getStackInHand(hand));
        }

        ItemStack stackInHand = user.getStackInHand(hand);

        if (currentAmmo <= 0)
        {
            return TypedActionResult.fail(stackInHand);
        }

        user.playSound(SoundEvents.ENTITY_SILVERFISH_HURT, 1.0f, 1.0f);
        spawnBulletEntity(world, user, 5, user.getRotationVector(), damage);
        currentAmmo--;

        stackInHand.getOrDefault(DataComponentTypes.CUSTOM_DATA, currentAmmo);

        return TypedActionResult.success(stackInHand);
    }


    private void spawnBulletEntity(World world, PlayerEntity user, float speed, Vec3d direction, float damage)
    {
        Vec3d playerLookDirection = direction.normalize();
        double x = user.getX() + playerLookDirection.x;
        double y = user.getEyeY() - 0.1; // Adjust the y-offset as needed
        double z = user.getZ() + playerLookDirection.z;

        ArrowEntity arrowEntity = new ArrowEntity(world, user, Items.ARROW.getDefaultStack(), getDefaultStack());
        arrowEntity.setPosition(x, y, z);
        arrowEntity.setVelocity(playerLookDirection.multiply(speed));
        arrowEntity.setDamage(damage);
        world.spawnEntity(arrowEntity);
    }
}

package mod.seekndestroy.entity.render;

import mod.seekndestroy.SeekAndDestroy;
import mod.seekndestroy.entity.BulletEntity;
import mod.seekndestroy.entity.model.BulletEntityModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class BulletEntityRenderer extends EntityRenderer<BulletEntity>
{
	public static final Identifier TEXTURE = SeekAndDestroy.ID("textures/entity/bullet.png");
	private final BulletEntityModel model = new BulletEntityModel(BulletEntityModel.getTexturedModelData().createModel());

	public BulletEntityRenderer(EntityRendererFactory.Context context)
	{
		super(context);
	}

	@Override
	public void render(BulletEntity bulletEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
		matrixStack.push();
		matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, bulletEntity.prevYaw, bulletEntity.getYaw()) - 90.0F));
		matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, bulletEntity.prevPitch, bulletEntity.getPitch()) + 90.0F));
		VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(
				vertexConsumerProvider, this.model.getLayer(this.getTexture(bulletEntity)), false, false
		);
		model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV);
		matrixStack.pop();
		super.render(bulletEntity, f, g, matrixStack, vertexConsumerProvider, i);
	}


	@Override
	public Identifier getTexture(BulletEntity entity)
	{
		return TEXTURE;
	}
}
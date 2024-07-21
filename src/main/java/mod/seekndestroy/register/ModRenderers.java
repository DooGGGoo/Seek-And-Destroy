package mod.seekndestroy.register;

import mod.seekndestroy.SeekAndDestroy;
import mod.seekndestroy.entity.render.*;
import mod.seekndestroy.entity.model.*;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;

@Environment(EnvType.CLIENT)
public class ModRenderers
{
	public static final EntityModelLayer BULLET_MODEL_LAYER =  new EntityModelLayer(SeekAndDestroy.ID("bullet_render_layer"), "bullet_render_layer");

	public static void registerModRenderers()
	{
		// Entities
		EntityRendererRegistry.register(ModEntities.BULLET, BulletEntityRenderer::new);

		// EntityModelLayers
		EntityModelLayerRegistry.registerModelLayer(BULLET_MODEL_LAYER, BulletEntityModel::getTexturedModelData);
	}
}

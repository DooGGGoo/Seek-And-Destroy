package mod.seekndestroy;

import mod.seekndestroy.register.ModRenderers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class SeekAndDestroyClient implements ClientModInitializer
{
    private static KeyBinding reloadKeyBind;

    @Override
    public void onInitializeClient()
    {
        ModRenderers.registerModRenderers();

        // TODO: Move to separate class
        reloadKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.seekndestroy.reload",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "category.seekndestroy.keys"
        ));
    }
}

package skytils.skytilsmod.mixins;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import skytils.skytilsmod.events.SendChatMessageEvent;

@Mixin(GuiScreen.class)
public class MixinGuiScreen {

    @Inject(method = "sendChatMessage(Ljava/lang/String;Z)V", at=@At("HEAD"), cancellable = true)
    public void onSendChatMessage(String message, boolean addToChat, CallbackInfo ci) {
        if (MinecraftForge.EVENT_BUS.post(new SendChatMessageEvent(message, addToChat))) ci.cancel();
    }

}
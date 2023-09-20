package com.borderlessfullscreen.mixin;

import com.borderlessfullscreen.BorderlessFullScreen;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class ExampleMixin {
	@Inject(method = "onKey", at = @At("HEAD"))
	protected void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
		if(BorderlessFullScreen.F10.matchesKey(key, scancode) && window == MinecraftClient.getInstance().getWindow().getHandle() && action == GLFW.GLFW_PRESS) {
			BorderlessFullScreen.tryToggle();
		}
	}
}
package com.borderlessfullscreen;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.Window;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public class BorderlessFullScreen implements ModInitializer {
	public static KeyBinding F10;
	private static boolean toggled = false;
	private static int x, y, width, height;

	@Override
	public void onInitialize() {
		F10 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"切换到无边框窗口",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_F10,
				"XMod"
		));
	}

	public static void tryToggle() {
		Window window = MinecraftClient.getInstance().getWindow();
		if(!window.isFullscreen()) {
			toggled = !toggled;

			if(toggled) {
				width = window.getWidth();
				height = window.getHeight();
				x = window.getX();
				y = window.getY();

				long monitor = GLFW.glfwGetPrimaryMonitor();
				GLFWVidMode mode = GLFW.glfwGetVideoMode(monitor);
				GLFW.glfwSetWindowAttrib(window.getHandle(), GLFW.GLFW_DECORATED, GLFW.GLFW_FALSE);
				GLFW.glfwSetWindowPos(window.getHandle(), 0, 0);
				GLFW.glfwSetWindowSize(window.getHandle(), mode.width()-1, mode.height()-1);
			}
			else {
				GLFW.glfwSetWindowAttrib(window.getHandle(), GLFW.GLFW_DECORATED, GLFW.GLFW_TRUE);
				GLFW.glfwSetWindowPos(window.getHandle(), x, y);
				GLFW.glfwSetWindowSize(window.getHandle(), width, height);
			}
		}
	}
}
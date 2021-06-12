package ralseiii.skyfabric.utils;

/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.util.math.*;
import org.lwjgl.opengl.GL11;

public class RenderUtils {
    public static void renderSolidBox(float x, float y, float z, float width, float height, float depth, WorldRenderContext renderer) {
        Camera camera = BlockEntityRenderDispatcher.INSTANCE.camera;
        RenderSystem.lineWidth(2.0f);
        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        Vec3d camPos = camera.getPos();
        renderer.matrixStack().push();
        renderer.matrixStack().translate(-camPos.x, -camPos.y, -camPos.z);
        Matrix4f model = renderer.matrixStack().peek().getModel();

        float maxX = x + width;
        float maxY = y + height;
        float maxZ = z + depth;


        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        float green = 1.0f;
        float alpha = 0.5f;

        bufferBuilder.begin(GL11.GL_QUADS, VertexFormats.POSITION_COLOR);

        bufferBuilder.vertex(model, x, y, z).color(0, green, 0, alpha).next();
        bufferBuilder.vertex(model, maxX, y, z).color(0, green, 0, alpha).next();
        bufferBuilder.vertex(model, maxX, y, maxZ).color(0, green, 0, alpha).next();
        bufferBuilder.vertex(model, x, y, maxZ).color(0, green, 0, alpha).next();

        bufferBuilder.vertex(model, x, maxY, z).color(0, green, 0, alpha).next();
        bufferBuilder.vertex(model, x, maxY, maxZ).color(0, green, 0, alpha).next();
        bufferBuilder.vertex(model, maxX, maxY, maxZ).color(0, green, 0, alpha).next();
        bufferBuilder.vertex(model, maxX, maxY, z).color(0, green, 0, alpha).next();

        bufferBuilder.vertex(model, x, y, z).color(0, green, 0, alpha).next();
        bufferBuilder.vertex(model, x, maxY, z).color(0, green, 0, alpha).next();
        bufferBuilder.vertex(model, maxX, maxY, z).color(0, green, 0, alpha).next();
        bufferBuilder.vertex(model, maxX, y, z).color(0, green, 0, alpha).next();

        bufferBuilder.vertex(model, maxX, y, z).color(0, green, 0, alpha).next();
        bufferBuilder.vertex(model, maxX, maxY, z).color(0, green, 0, alpha).next();
        bufferBuilder.vertex(model, maxX, maxY, maxZ).color(0, green, 0, alpha).next();
        bufferBuilder.vertex(model, maxX, y, maxZ).color(0, green, 0, alpha).next();

        bufferBuilder.vertex(model, x, y, maxZ).color(0, green, 0, alpha).next();
        bufferBuilder.vertex(model, maxX, y, maxZ).color(0, green, 0, alpha).next();
        bufferBuilder.vertex(model, maxX, maxY, maxZ).color(0, green, 0, alpha).next();
        bufferBuilder.vertex(model, x, maxY, maxZ).color(0, green, 0, alpha).next();

        bufferBuilder.vertex(model, x, y, z).color(0, green, 0, alpha).next();
        bufferBuilder.vertex(model, x, y, maxZ).color(0, green, 0, alpha).next();
        bufferBuilder.vertex(model, x, maxY, maxZ).color(0, green, 0, alpha).next();
        bufferBuilder.vertex(model, x, maxY, z).color(0, green, 0, alpha).next();
        tessellator.draw();
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        renderer.matrixStack().pop();
    }
}

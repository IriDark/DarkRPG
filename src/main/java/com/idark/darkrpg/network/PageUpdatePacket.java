package com.idark.darkrpg.network;

import com.idark.darkrpg.capability.IPage;
import com.idark.darkrpg.capability.PageCapability;
import com.idark.darkrpg.capability.PageProvider;
import com.idark.darkrpg.client.render.gui.book.LexiconGui;
import com.idark.darkrpg.client.render.gui.book.LexiconPages;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PageUpdatePacket {
    private LexiconPages page;
    private static boolean open;

    public PageUpdatePacket(LexiconPages page, boolean open) {
        this.page = page;
        PageUpdatePacket.open = open;
    }

    public static PageUpdatePacket decode(FriendlyByteBuf buf) {
        return new PageUpdatePacket(LexiconPages.valueOf(buf.readUtf(32767)), buf.readBoolean());
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(page.name());
        buf.writeBoolean(open);
    }

    public static void handle(PageUpdatePacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            context.getSender().getCapability(IPage.INSTANCE, null).ifPresent((k) -> k.makeOpen(packet.page, open));
            context.setPacketHandled(true);
        });
    }
}

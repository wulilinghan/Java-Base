package io.netty.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * Created By gao_e on 2020/3/8 16:21
 */
public class ClientMsgpackEncode extends MessageToByteEncoder<String> {
    @Override
    protected void encode(ChannelHandlerContext ctx, String smartRPCRequest, ByteBuf out) throws Exception {
        MessagePack messagePack = new MessagePack();
        byte[] raw = messagePack.write(smartRPCRequest);
        out.writeBytes(raw);
    }
}

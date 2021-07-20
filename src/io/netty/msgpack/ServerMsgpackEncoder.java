package io.netty.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * Created By gao_e on 2020/3/8 15:08
 * 服务端 编码器
 */
public class ServerMsgpackEncoder extends MessageToByteEncoder<String> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext,
                          String smartRPCResponse, ByteBuf byteBuf) throws Exception {
        MessagePack messagePack = new MessagePack();
        byte[] raw = messagePack.write(smartRPCResponse);
        byteBuf.writeBytes(raw);
    }
}

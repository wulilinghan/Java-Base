package io.netty.msgpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * Created By gao_e on 2020/3/8 15:12
 * 服务端 解码器
 */
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        final byte[] array;
        final int length = byteBuf.readableBytes();
        array = new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(),array,0,length);
        MessagePack messagePack = new MessagePack();
        list.add(messagePack.read(array));
    }
}

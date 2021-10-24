package com.wanli.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ByteProcessor;
import io.netty.util.concurrent.EventExecutorGroup;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.util.logging.Logger;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {


    private  ByteBuf firstMessage;

    private int counter = 0;

    private byte[] req;

    public TimeClientHandler(){
//        byte[] req = "QUERY TIME ORDER".getBytes();
//        firstMessage = Unpooled.buffer(req.length);
//        firstMessage.writeBytes(req);

//        req = ("QUERY TIME ORDER" + System.getProperty("line.separator").length()).getBytes();

        req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
    }


    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
//        channelHandlerContext.writeAndFlush(firstMessage);

        ByteBuf message = null;
        for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            channelHandlerContext.writeAndFlush(message);
//            System.out.println("count : " + counter);
        }


    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
//        ByteBuf buf = (ByteBuf) o;
//
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//
//        String body = new String(req, "UTF-8");
//
//        System.out.println("BODY : " + body +
//                "; the counter is : " + ++ counter);

        String body = (String) o;

        System.out.println("BODY : " + body +
        "; the counter is : " + ++ counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
        System.out.println("Unexpected exception from downstream : "
         + throwable.getMessage());

        channelHandlerContext.close();
    }
}

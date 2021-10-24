package com.wanli.server;

import com.wanli.client.TimeClientHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;

import java.util.Date;
import java.util.logging.Logger;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {


    private int counter = 0 ;

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
//        ByteBuf buf = (ByteBuf)o;
//
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//
//        String body = new String(req, "UTF-8")
//                .substring(0, req.length - System.getProperty("line.separator").length());
//
//        System.out.println("The time receive order:" + body
//        + ";the counter is : " + ++counter);
//
//        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(
//                System.currentTimeMillis()).toString() : "BAD ORDER";
//        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
//
//        channelHandlerContext.write(resp);


        String body = (String) o;
        System.out.println("The time receive order:" + body
        + ";the counter is : " + ++counter);

        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(
                System.currentTimeMillis()).toString() : "BAD ORDER";

        currentTime = currentTime +  System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        channelHandlerContext.write(resp);

    }


    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelHandlerContext.flush();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
        channelHandlerContext.close();
    }
}

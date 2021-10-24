package com.wanli.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {


    private int counter = 0;

    static final String ECHO_REQ = "HELLO WORLD $_";

    public EchoClientHandler(){
    }


    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {

        for (int i = 0; i < 100; i++) {
            channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }


    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("BODY : " + o +
        "; the counter is : " + ++ counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
        System.out.println("Unexpected exception from downstream : "
         + throwable.getMessage());

        channelHandlerContext.close();
    }
}

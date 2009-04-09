package com.hoten.cmpp.message;

/**
 * <p>Title: Submit返回消息</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class CMPPSubmitMessageResp extends CMPPHead{

    public CMPPSubmitMessageResp() {
    }
    public String  nMsgId;                //消息标识
    public int nResult;               //结果 0：正确 其他错误，见错误代码
    public int un_count;
    public byte[] fail_Index;
    public void setMsgID(byte[] id){
        if(id.length!=8)
            nMsgId=null;
        else{
            nMsgId=DecodeCMPPMsgID(id);
        }
    }

    private String DecodeCMPPMsgID(byte abyte0[])
    {
        StringBuffer stringbuffer = new StringBuffer();
        byte byte0 = 0;
        int l1 = 0;
        byte0 = abyte0[0];
        int j = (byte)(byte0 >>> 4) & 0xf;
        byte0 = abyte0[0];
        byte0 <<= 4;
        int k = (byte)(byte0 >>> 3);
        k &= 0x1f;
        byte0 = abyte0[1];
        byte0 >>>= 7;
        byte0 &= 1;
        k += byte0;
        k &= 0x1f;
        byte0 = abyte0[1];
        byte0 <<= 1;
        int l = (byte)(byte0 >>> 3);
        l &= 0x1f;
        byte0 = abyte0[1];
        byte0 <<= 6;
        int i1 = (byte)(byte0 >>> 2);
        i1 &= 0x3f;
        byte0 = abyte0[2];
        byte0 >>>= 4;
        i1 += byte0;
        i1 &= 0x3f;
        byte0 = abyte0[2];
        byte0 <<= 4;
        int j1 = (byte)(byte0 >>> 2);
        j1 &= 0x3f;
        byte0 = abyte0[3];
        byte0 >>>= 6;
        j1 += byte0;
        j1 &= 0x3f;
        byte0 = abyte0[3];
        byte0 <<= 2;
        byte0 >>>= 2;
        byte0 &= 0x3f;
        l1 = abyte0[4] & 0xff;
        int i = byte0 * 0x10000 + l1 * 256;
        l1 = abyte0[5] & 0xff;
        i += l1;
        i &= 0x3fffff;
        byte0 = abyte0[6];
        l1 = byte0;
        l1 &= 0xff;
        int k1 = l1 * 256;
        byte0 = abyte0[7];
        l1 = byte0;
        l1 &= 0xff;
        k1 += l1;
        k1 &= 0xffff;
        stringbuffer.setLength(0);
        if(j < 10)
            stringbuffer.append("0");
        stringbuffer.append(j);
        if(k < 10)
            stringbuffer.append("0");
        stringbuffer.append(k);
        if(l < 10)
            stringbuffer.append("0");
        stringbuffer.append(l);
        if(i1 < 10)
            stringbuffer.append("0");
        stringbuffer.append(i1);
        if(j1 < 10)
            stringbuffer.append("0");
        stringbuffer.append(j1);
        stringbuffer.append(FormatInt(i, 5));
        stringbuffer.append(FormatInt(k1, 5));
        return stringbuffer.toString();
    }

    private String FormatInt(int i, int j)
    {
        String s;
        for(s = String.valueOf(i); s.length() < j; s = "0" + s);
        return s;
    }


}
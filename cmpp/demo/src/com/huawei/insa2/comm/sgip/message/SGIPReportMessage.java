// FrontEnd Plus GUI for JAD
// DeCompiled : SGIPReportMessage.class

package com.huawei.insa2.comm.sgip.message;

import com.huawei.insa2.comm.sgip.SGIPConstant;
import com.huawei.insa2.util.TypeConvert;

// Referenced classes of package com.huawei.insa2.comm.sgip.message:
//            SGIPMessage

public class SGIPReportMessage extends SGIPMessage
{

    public SGIPReportMessage(byte buf[])
        throws IllegalArgumentException
    {
        super.buf = new byte[56];
        if(buf.length != 56)
        {
            throw new IllegalArgumentException(SGIPConstant.SMC_MESSAGE_ERROR);
        } else
        {
            System.arraycopy(buf, 0, super.buf, 0, 56);
            src_node_Id = TypeConvert.byte2int(super.buf, 0);
            time_Stamp = TypeConvert.byte2int(super.buf, 4);
            sequence_Id = TypeConvert.byte2int(super.buf, 8);
            return;
        }
    }

    public int[] getSubmitSequenceNumber()
    {
        int temp[] = new int[3];
        System.arraycopy(buf, 12, temp, 0, 12);
        return temp;
    }

    public int getReportType()
    {
        int tmpId = buf[24];
        return tmpId;
    }

    public String getUserNumber()
    {
        byte tmpId[] = new byte[21];
        System.arraycopy(buf, 25, tmpId, 0, 21);
        String tmpStr = (new String(tmpId)).trim();
        if(tmpStr.indexOf(0) >= 0)
            return tmpStr.substring(0, tmpStr.indexOf(0));
        else
            return tmpStr;
    }

    public int getState()
    {
        int tmpId = buf[46];
        return tmpId;
    }

    public int getErrorCode()
    {
        int tmpId = buf[47];
        return tmpId;
    }

    public String getReserve()
    {
        byte tmpReserve[] = new byte[8];
        System.arraycopy(buf, 48, tmpReserve, 0, 8);
        return (new String(tmpReserve)).trim();
    }

    public String toString()
    {
        String tmpStr = "SGIP_REPORT: ";
        tmpStr = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(tmpStr)))).append("Sequence_Id=").append(getSequenceId())));
        tmpStr = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(tmpStr)))).append(",SubmitSequenceNumber=").append(getSubmitSequenceNumber())));
        tmpStr = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(tmpStr)))).append(",ReportType=").append(getReportType())));
        tmpStr = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(tmpStr)))).append(",UserNumber=").append(getUserNumber())));
        tmpStr = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(tmpStr)))).append(",State=").append(getState())));
        tmpStr = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(tmpStr)))).append(",ErrorCode=").append(getErrorCode())));
        tmpStr = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(tmpStr)))).append(",Reserve=").append(getReserve())));
        return tmpStr;
    }

    public int getCommandId()
    {
        return 5;
    }
}

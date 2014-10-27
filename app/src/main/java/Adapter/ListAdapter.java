package Adapter;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nyle.demo.srtp_nyle_xyh.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengyonghui on 14/10/18.
 */
public class ListAdapter extends BaseAdapter
{
    public final static int STATUS_PROCESS = 0;
    public final static int STATUS_RECEIVE = 1;

    Context context;
    List<ApplicationInfo> processList;
    List<ApplicationInfo> receiverWaybillList;
    LayoutInflater inflater;
    int status = 0;

    public ListAdapter(Context context)
    {
        this.context = context;
        inflater = LayoutInflater.from(context);
        processList = new ArrayList<ApplicationInfo>();
        receiverWaybillList = new ArrayList<ApplicationInfo>();
    }

    public void setProcessList(List<ApplicationInfo> processList)
    {
        this.processList = processList;
        notifyDataSetChanged();
    }

    public void setReceiverWaybillList(List<ApplicationInfo> receiverWaybillList)
    {
        this.receiverWaybillList = receiverWaybillList;
        notifyDataSetChanged();
    }

    public void setWaybillListStatus(int status)
    {
        this.status = status;
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        if (status == STATUS_RECEIVE) return receiverWaybillList.size();
        else if (status == STATUS_PROCESS) return processList.size();
        else return 0;
    }

    @Override
    public Object getItem(int i)
    {
        if (status == STATUS_RECEIVE) return receiverWaybillList.get(i);
        else if (status == STATUS_PROCESS) return processList.get(i);
        else return null;
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (status == STATUS_RECEIVE)
        {
//           return renderReceiverListItem(i, view, viewGroup);
        }
        else if (status == STATUS_PROCESS)
        {
              return renderProcessListItem(i, view, viewGroup);
        }

        return null;
    }

    private View renderProcessListItem(int i, View view, ViewGroup viewGroup)
    {
        ApplicationInfo applicationInfo = processList.get(i);
        if (applicationInfo == null) return null;

        if (view == null || view.getId() != R.id.item_process)
        {
            view = inflater.inflate(R.layout.item_process, viewGroup, false);
        }

        TextView name = (TextView) view.findViewById(R.id.type);


        if(applicationInfo.loadLabel(context.getPackageManager()).toString() != null) name.setText(applicationInfo.loadLabel(context.getPackageManager()).toString());

        return view;
    }

//    private View renderSendListItem(int i, View view, ViewGroup viewGroup)
//    {
//        ApplicationInfo waybill = processList.get(i);
//        if (waybill == null) return null;
//
//        if (view == null || view.getId() != R.id.item_send_waybill)
//            view = inflater.inflate(R.layout.item_send_waybill, viewGroup, false);
//
//        TextView statusText = (TextView) view.findViewById(R.id.text_status);
//        TextView idText = (TextView) view.findViewById(R.id.id);
//        TextView fromLocationText = (TextView) view.findViewById(R.id.from_palce);
//        TextView toLocationText = (TextView) view.findViewById(R.id.to_palce);
//        TextView weightText = (TextView) view.findViewById(R.id.weight);
//        TextView typeText = (TextView) view.findViewById(R.id.type);
//        TextView companyText = (TextView) view.findViewById(R.id.company_name);
//        Button button = (Button) view.findViewById(R.id.send_waybill_btn);
//        TextView msgText1 = (TextView) view.findViewById(R.id.msg1);
//        TextView msgText2 = (TextView) view.findViewById(R.id.msg2);
//
//        ((ViewGroup)button.getParent()).setTag(waybill);
//
//        ////////////这个地方的状态可能需要修改一下，因为后面的这个状态可能不一样
//        if(waybill.status == Waybill.STATUS_DELIVER_OK)
//        {
//            //已送达
//            view.findViewById(R.id.status_sended).setVisibility(View.VISIBLE);
//            view.findViewById(R.id.status_sending).setVisibility(View.GONE);
//            statusText.setText("已送达");
//            button.setBackgroundResource(R.drawable.button_select_sended);
//        }
//        else
//        {
//            //运送中
//            view.findViewById(R.id.status_sended).setVisibility(View.GONE);
//            view.findViewById(R.id.status_sending).setVisibility(View.VISIBLE);
//            statusText.setText("运送中");
//            button.setBackgroundResource(R.drawable.button_select);
//        }
//        ///////////////////////
//
//        idText.setText("(" + waybill.id + ")");
//        weightText.setText("" + waybill.weight + "g");
//        if(waybill.from_customer != null && waybill.from_customer.city != null) fromLocationText.setText(waybill.from_customer.city);
//        if(waybill.to_customer != null && waybill.to_customer.city != null) toLocationText.setText(waybill.to_customer.city);
//        if(waybill.content != null) typeText.setText(waybill.content);
//        if(waybill.org != null && waybill.org.name != null) companyText.setText(waybill.org.name);
//
//        List<WayBillMsg> wayBillMsgsList = WaybillMsgDAO.getInstance(context).getWaybillLittleMsg(waybill.id);
//
//        for(int j = 0; j < 2 && j < wayBillMsgsList.size(); j++)
//        {
//            WayBillMsg wayBillMsg = wayBillMsgsList.get(j);
//            if(wayBillMsg.msg != null)
//            {
//                if (j == 0)
//                {
//                    msgText1.setText(wayBillMsg.msg);
//                    msgText1.setVisibility(View.VISIBLE);
//                }
//                else if (j == 1)
//                {
//                    msgText2.setText(wayBillMsg.msg);
//                    msgText2.setVisibility(View.VISIBLE);
//                }
//            }
//        }
//
//        return view;
//    }
//
//    private View renderReceiverListItem(int i, View view, ViewGroup viewGroup)
//    {
//        Waybill waybill = receiverWaybillList.get(i);
//        if (waybill == null) return null;
//
//        if (view == null || view.getId() != R.id.item_receive_waybill)
//            view = inflater.inflate(R.layout.item_receive_waybill, viewGroup, false);
//        ViewGroup toPayHolder = (ViewGroup) view.findViewById(R.id.to_pay_holder);
//        TextView payPrice = (TextView) view.findViewById(R.id.pay_receiver);
//        TextView statusText = (TextView) view.findViewById(R.id.text_status);
//        TextView idText = (TextView) view.findViewById(R.id.id);
//        TextView fromLocationText = (TextView) view.findViewById(R.id.from_palce);
//        TextView toLocationText = (TextView) view.findViewById(R.id.to_palce);
//        TextView weightText = (TextView) view.findViewById(R.id.weight);
//        TextView typeText = (TextView) view.findViewById(R.id.type);
//        TextView companyText = (TextView) view.findViewById(R.id.company_name);
//        ViewGroup sendedButtonHolder = (ViewGroup) view.findViewById(R.id.sended_holder);
//        ViewGroup sendingButtonHolder = (ViewGroup) view.findViewById(R.id.sending_holder);
//        TextView msgText1 = (TextView) view.findViewById(R.id.msg1);
//        TextView msgText2 = (TextView) view.findViewById(R.id.msg2);
//
//
//        sendedButtonHolder.setTag(waybill);
//        sendingButtonHolder.setTag(waybill);
//
//        if(waybill.status == Waybill.STATUS_DELIVER_OK)
//        {
//            //已送达
//            view.findViewById(R.id.status_sended).setVisibility(View.VISIBLE);
//            view.findViewById(R.id.status_sending).setVisibility(View.GONE);
//            statusText.setText("已送达");
//            sendedButtonHolder.setVisibility(View.VISIBLE);
//            sendingButtonHolder.setVisibility(View.GONE);
//        }
//        else
//        {
//            view.findViewById(R.id.status_sended).setVisibility(View.GONE);
//            view.findViewById(R.id.status_sending).setVisibility(View.VISIBLE);
//            sendedButtonHolder.setVisibility(View.GONE);
//            sendingButtonHolder.setVisibility(View.VISIBLE);
//            String state = "";
//            switch ((int)waybill.status)
//            {
//                case Waybill.STATUS_INIT: state = "待收单"; break;
//                case Waybill.STATUS_PICKING: state = "收单中"; break;
//                case Waybill.STATUS_PICK_OK: state = "已收单"; break;
//                case Waybill.STATUS_PICK_FAIL: state = "收单失败"; break;
//                case Waybill.STATUS_IN_TRANSIT: state = "运输中"; break;
//                case Waybill.STATUS_DELIVERING: state = "未签收"; break;
//                case Waybill.STATUS_DELIVER_FAIL: state = "派送失败"; break;
//                case Waybill.STATUS_LOST: state = "快件丢失"; break;
//                case Waybill.STATUS_FAIL: state = "其他失败"; break;
//                default: state = "其他";
//            }
//            statusText.setText(state);
//        }
//
//        //if this is a to-pay waybill
//        if (waybill.pay_type == 1)
//        {
//            toPayHolder.setVisibility(View.VISIBLE);
//            payPrice.setText(""  + waybill.charge);
//        }
//        else toPayHolder.setVisibility(View.GONE);
//
//        idText.setText("(" + waybill.id + ")");
//        weightText.setText("" + waybill.weight + "g");
//        if(waybill.from_customer != null && waybill.from_customer.city != null) fromLocationText.setText(waybill.from_customer.city);
//        if(waybill.to_customer != null && waybill.to_customer.city != null) toLocationText.setText(waybill.to_customer.city);
//        if(waybill.content != null) typeText.setText(waybill.content);
//        if(waybill.org != null && waybill.org.name != null) companyText.setText(waybill.org.name);
//
//        List<WayBillMsg> wayBillMsgsList = WaybillMsgDAO.getInstance(context).getWaybillLittleMsg(waybill.id);
//
//        for(int j = 0; j < 2 && j < wayBillMsgsList.size(); j++)
//        {
//            WayBillMsg wayBillMsg = wayBillMsgsList.get(j);
//            if(wayBillMsg.msg != null)
//            {
//                if (j == 0)
//                {
//                    msgText1.setText(wayBillMsg.msg);
//                    msgText1.setVisibility(View.VISIBLE);
//                }
//                else if (j == 1)
//                {
//                    msgText2.setText(wayBillMsg.msg);
//                    msgText2.setVisibility(View.VISIBLE);
//                }
//            }
//        }
//
//        return view;
//    }

}
